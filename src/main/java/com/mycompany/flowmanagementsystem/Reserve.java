/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flowmanagementsystem;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ximpleware.ModifyException;
import com.ximpleware.NavException;
import com.ximpleware.TranscodeException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author sbilau
 */
public class Reserve {

    /*  function to perform two tasks */
 /*  1. To take in data from reserve xml and store in internal reserve list -> internal_reserve_list.xml */
 /*  2. To take data from internal reserve list and build map list of reserve flights */
    public void buildInternalreserveList(String[] reserveETAList, String[] reserveFlightList, String[] reserveStatusList, String[] reserveSequenceTypeList, String[] reserveStarnameList, String[] reserveFeederFixList, String[] reserveAtypeList, String[] reserveAcidList) throws NavException, FileNotFoundException, IOException, TranscodeException, ModifyException, ParserConfigurationException, SAXException, TransformerConfigurationException, TransformerException, XPathExpressionException {
        /*  take ETA and flightnumber from amtfile.xml and pass to reserveETAList and reserveFlightList arrays */
        String[] stringETA = new String[10000];
        String[] stringCallsign = new String[10000];
        String[] stringStatus = new String[10000];
        String[] stringSequenceType = new String[10000];
        String[] stringStarname = new String[10000];
        String[] stringAtype = new String[10000];
        String[] stringFeederFix = new String[10000];

        int counter = 0;
        try {
            ObjectMapper mapper = new XmlMapper();
            InputStream inputStream = new FileInputStream(new File("C:/wamp64/www/flowcontrol/public/run/configs/reserve.xml"));
//            InputStream inputStream = new FileInputStream(new File("/var/www/html/flowcontrol/public/run/configs/reserve.xml"));

            TypeReference<List<flights>> typeReference = new TypeReference<List<flights>>() {
            };
            List<flights> flight = mapper.readValue(inputStream, typeReference);
            for (flights f : flight) {
                String eta;
                String callsign;
                String status;
                String sequenceType;
                String starname;
                String atype;
                String acid;
                String feederFix;

                eta = f.eta;
                callsign = f.callsign;
                status = f.status;
                sequenceType = f.sequenceType;
                starname = f.starname;
                atype = f.atype;
                feederFix = f.feederFix;

                stringETA[counter] = eta;
                stringCallsign[counter] = callsign;
                stringStatus[counter] = status;
                stringSequenceType[counter] = sequenceType;
                stringStarname[counter] = starname;
                stringAtype[counter] = atype;
                stringFeederFix[counter] = feederFix;
                counter++;
            }
        } catch (IOException e) {
        }

        for (int x = 0; x < reserveETAList.length; x++) {
            reserveETAList[x] = stringETA[x];
            reserveFlightList[x] = stringCallsign[x];
            reserveStatusList[x] = stringStatus[x];
            reserveSequenceTypeList[x] = stringSequenceType[x];
            reserveStarnameList[x] = stringStarname[x];
            reserveAtypeList[x] = stringAtype[x];
            reserveFeederFixList[x] = stringFeederFix[x];

        }

        //check if reserve list is empty
        for (String reserveETAList1 : reserveETAList) {
            if (reserveETAList1 == null) {
                System.out.println("reserve list is null ");
                break;
                
            } else {
                /*  Get internal reserve list xml file to add new ETA and flightnumber to it */
                String filePath = "C:/wamp64/www/flowcontrol/public/run/Internal_lists/reserve/internal_reserve_list.xml";
//                   String filePath = "/var/www/html/flowcontrol/public/run/Internal_lists/reserve/internal_reserve_list.xml";

                File xmlFile = new File(filePath);
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(xmlFile);
                document.getDocumentElement().normalize();
                Element root = document.getDocumentElement();
                NodeList nList = document.getElementsByTagName("reserve");
                Element eElement = null;
                boolean child = nList.item(0).hasChildNodes();

                if (child == true) {
                    System.out.println("Internal reserve List not empty. Compare.");

                    /*                        For loop to go through element in NEW ATM File.
                             For loop to get each element in .internal_reserve_list
                                 If flightnumber && ACID is equal.
                                    Replace internal_reserve_list values with new values from NEW ATM File   */
                    for (int x = 0; x < nList.getLength(); x++) {
                        // for(int temp=0; temp<nList.getLength(); temp++){
                        Node node = nList.item(x);
                        // if (node.getNodeType() == Node.ELEMENT_NODE){                                               
                        eElement = (Element) nList.item(x);

                        String flightContent = eElement.getElementsByTagName("callsign").item(0).getTextContent();
                        System.out.println("flightContent " + flightContent);
                        if (flightContent.equals(reserveFlightList[x])) {
                            System.out.println(" Flight exists in internal reserve list ");

                            Node eta = eElement.getElementsByTagName("eta").item(0).getFirstChild();
                            eta.setNodeValue(reserveETAList[x]);

                            Node sequence = eElement.getElementsByTagName("sequenceType").item(0).getFirstChild();
                            sequence.setNodeValue(reserveSequenceTypeList[x]);

                        } else {
                            if (reserveETAList[x] != null && reserveFlightList[x] != null) {

                                Element reserve = document.createElement("reserve");

                                Element callsign = document.createElement("callsign");
                                callsign.appendChild(document.createTextNode(reserveFlightList[x]));
                                reserve.appendChild(callsign);

                                Element eta = document.createElement("eta");
                                eta.appendChild(document.createTextNode(reserveETAList[x]));
                                reserve.appendChild(eta);

                                Element sequence = document.createElement("sequenceType");
                                sequence.appendChild(document.createTextNode(reserveSequenceTypeList[x]));
                                reserve.appendChild(sequence);

                                root.appendChild(reserve);
                            }
                        }
                        //   }
                    }

                } else {
                    System.out.println("Internal reserve List is empty. Append.");

                    //String sequence = "reserve";
                    for (int x = 0; x < stringETA.length; x++) {

                        if (reserveETAList[x] != null && reserveFlightList[x] != null && reserveSequenceTypeList[x] != null) {
                            Element newreserves = document.createElement("reserve");

                            Element eta = document.createElement("eta");
                            eta.appendChild(document.createTextNode(reserveETAList[x]));
                            newreserves.appendChild(eta);

                            Element callsign = document.createElement("callsign");
                            callsign.appendChild(document.createTextNode(reserveFlightList[x]));
                            newreserves.appendChild(callsign);

                            Element sequencetype = document.createElement("sequenceType");
                            sequencetype.appendChild(document.createTextNode(reserveSequenceTypeList[x]));
                            newreserves.appendChild(sequencetype);

                            root.appendChild(newreserves);

                        }
                    }
                }

                DOMSource source = new DOMSource(document);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File("C:/wamp64/www/flowcontrol/public/run/Internal_lists/reserve/xsltfile.xml")));
//        Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File("/var/www/html/flowcontrol/public/run/Internal_lists/reserve/xsltfile.xml")));

                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                StreamResult result = new StreamResult("C:/wamp64/www/flowcontrol/public/run/Internal_lists/reserve/internal_reserve_list.xml");
//                    StreamResult result = new StreamResult("/var/www/html/flowcontrol/public/run/Internal_lists/reserve/internal_reserve_list.xml");

                transformer.transform(source, result);

            }
        }
    }

    /*  func get reserve ETA and flightnumber from internal_reserve_list.xml   */
    public static void getreserve(HashMap<String, String> reserveFlightMap, Map<LocalDateTime, String> tempreserveMapETA, LocalDateTime[] reserveArrETA, int landingRate) {
        int count = 0;
        try {
            //creating a constructor of file class and parsing an XML file  
            File file = new File("C:/wamp64/www/flowcontrol/public/run/Internal_lists/reserve/internal_reserve_list.xml");
//            File file = new File("/var/www/html/flowcontrol/public/run/Internal_lists/reserve/internal_reserve_list.xml");

            //an instance of factory that gives a document builder  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file  
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("reserve");
            // nodeList is not iterable, so we are using for loop  
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    System.out.println("Call sign: " + eElement.getElementsByTagName("callsign").item(0).getTextContent());
                    System.out.println("Sequence Type: " + eElement.getElementsByTagName("sequenceType").item(0).getTextContent());
                    System.out.println("ETA: " + eElement.getElementsByTagName("eta").item(0).getTextContent());
                    String tempCallsign = eElement.getElementsByTagName("callsign").item(0).getTextContent();
                    String tempSequenceType = eElement.getElementsByTagName("sequenceType").item(0).getTextContent();
                    String reserveETA = eElement.getElementsByTagName("eta").item(0).getTextContent();
                    String[] reserveFlightString = new String[nodeList.getLength()];
                    reserveFlightString[count] = tempSequenceType;
                    if (tempSequenceType.equals("reserve")) {
                        if (reserveFlightMap.containsKey(tempCallsign)) {

                        } else {
                            reserveFlightMap.put(tempCallsign, tempSequenceType);

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = sdf.parse(reserveETA);
                            LocalDateTime ldtDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                            reserveArrETA[itr] = ldtDate;

                            //check for same ETA
                            sequence checkETA = new sequence();
                            LocalDateTime[] compETA = checkETA.checkForSameFFT(reserveArrETA, landingRate);

                            tempreserveMapETA.put(compETA[itr], tempCallsign);
                        }

                    }
                }
            }
        } catch (IOException | ParseException | ParserConfigurationException | DOMException | SAXException e) {
        }

        System.out.println("tempreserveMapETA" + tempreserveMapETA + "\n" + "manFlightMap" + reserveFlightMap + "\n" + "reserveArrETA" + Arrays.toString(reserveArrETA) + "\n");
    }

}
