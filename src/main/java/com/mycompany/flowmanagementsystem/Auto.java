/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
 * @author Administrator
 */
public class Auto {

    /*  function to perform two tasks */
 /*  1. To take in data from auto xml and store in internal auto list -> internal_auto_list.xml */
 /*  2. To take data from internal auto list and build map list of auto flights */
    public void buildInternalAutoList(String[] autoETAList, String[] autoStatusList, String[] autoSequenceTypeList, String[] autoStarnameList, String[] autoFeederFixList, String[] autoCallsign, String[] autoAdep, String[] autoAdes, String[] autoEobt, String[] autoDof, String[] autoAc_type, String[] autoWtur, String[] autoFrule, String[] autoAtd, String[] autoSpeed, String[] autoRfl, String[] autoRegis, String[] autoEtd, String[] autoEtd_dof, String[] autoRoute, String[] autoOther_info, String[] autoSTAR) throws NavException, FileNotFoundException, IOException, TranscodeException, ModifyException, ParserConfigurationException, SAXException, TransformerConfigurationException, TransformerException, XPathExpressionException {
        /*  take ETA and flightnumber from amtfile.xml and pass to autoETAList and autoCallsign arrays */
        String[] stringETA = new String[10000];
        String[] stringFeederFix = new String[10000];
        String[] stringCallsign = new String[10000];
        String[] stringAdep = new String[10000];
        String[] stringAdes = new String[10000];
        String[] stringEobt = new String[10000];
        String[] stringDof = new String[10000];
        String[] stringAc_type = new String[10000];
        String[] stringWtur = new String[10000];
        String[] stringFrule = new String[10000];
        String[] intAtd = new String[10000];
        String[] intSpeed = new String[10000];
        String[] intRfl = new String[10000];
        String[] stringRegis = new String[10000];
        String[] stringEtd = new String[10000];
        String[] stringEtd_dof = new String[10000];
        String[] stringRoute = new String[10000];
        String[] stringOther_info = new String[10000];
        String[] stringStatus = new String[10000];
        String[] stringSequenceType = new String[10000];
        String[] stringSTAR = new String[10000];

        /*
        get records from internal_auto_list and compare with atm file coming from stream folder  
         */
        Path path = Paths.get("C:/wamp64/www/flowcontrol/public/run/stream");
//        Path path = Paths.get("/var/www/html/flowcontrol/public/run/stream");
//        Path path = pathStream;

        if (flights.isEmpty(path) == false) {
            File[] xmlFiles = flights.getXMLFiles(new File("C:/wamp64/www/flowcontrol/public/run/stream"));
//            File[] xmlFiles = flights.getXMLFiles(new File("/var/www/html/flowcontrol/public/run/stream"));
//            File[] xmlFiles = fileStream;

            System.out.println(Arrays.toString(xmlFiles));

            int counter = 0;
            String nil = "...";
            String newETA = null;

            String etaStr = null;
            String statusStr = null;
            String sequenceTypeStr = null;
            String feederFixStr = null;
            String callsignStr = null;
            String adepStr = null;
            String adesStr = null;
            String eobtStr = null;
            String dofStr = null;
            String ac_typeStr = null;
            String wturStr = null;
            String fruleStr = null;
            String atdStr = null;
            String speedStr = null;
            String rflStr = null;
            String regisStr = null;
            String etdStr = null;
            String etd_dofStr = null;
            String routeStr = null;
            String other_infoStr = null;
            String STARStr = null;

            try {

                for (File xml : xmlFiles) {
                    ObjectMapper mapper = new XmlMapper();
                    InputStream inputStream = new FileInputStream(new File(xml.toString()));
                    System.out.println("File[] array file item :" + xml.toString());
                    TypeReference<List<flights>> typeReference = new TypeReference<List<flights>>() {
                    };
                    List<flights> flight = mapper.readValue(inputStream, typeReference);
                    for (flights f : flight) {

                        if (f.eta.isEmpty()) {
                            etaStr = nil;
                        } else {
                            etaStr = f.eta;
                        }
                        if (f.status.isEmpty()) {
                            statusStr = nil;
                        } else {
                            statusStr = f.status;
                        }
                        if (f.sequenceType.isEmpty()) {
                            sequenceTypeStr = nil;
                        } else {
                            sequenceTypeStr = f.sequenceType;
                        }
                        if (f.feederFix.isEmpty()) {
                            feederFixStr = nil;
                        } else {
                            feederFixStr = f.feederFix;
                        }
                        if (f.callsign.isEmpty()) {
                            callsignStr = nil;
                        } else {
                            callsignStr = f.callsign;
                        }
                        if (f.adep.isEmpty()) {
                            adepStr = nil;
                        } else {
                            adepStr = f.adep;
                        }
                        if (f.ades.isEmpty()) {
                            adesStr = nil;
                        } else {
                            adesStr = f.ades;
                        }
                        if (f.eobt.isEmpty()) {
                            eobtStr = nil;
                        } else {
                            eobtStr = f.eobt;
                        }
                        if (f.dof.isEmpty()) {
                            dofStr = nil;
                        } else {
                            dofStr = f.dof;
                        }
                        if (f.ac_type.isEmpty()) {
                            ac_typeStr = nil;
                        } else {
                            ac_typeStr = f.ac_type;
                        }
                        if (f.wtur.isEmpty()) {
                            wturStr = nil;
                        } else {
                            wturStr = f.wtur;
                        }
                        if (f.frule.isEmpty()) {
                            fruleStr = nil;
                        } else {
                            fruleStr = f.frule;
                        }
                        if (f.atd.isEmpty()) {
                            atdStr = nil;
                        } else {
                            atdStr = f.atd;
                        }
                        if (f.speed.isEmpty()) {
                            speedStr = nil;
                        } else {
                            speedStr = f.speed;
                        }
                        if (f.rfl.isEmpty()) {
                            rflStr = nil;
                        } else {
                            rflStr = f.rfl;
                        }
                        if (f.regis.isEmpty()) {
                            regisStr = nil;
                        } else {
                            regisStr = f.regis;
                        }
                        if (f.etd_dof.isEmpty()) {
                            etd_dofStr = nil;
                        } else {
                            etd_dofStr = f.etd_dof;
                        }
                        if (f.etd.isEmpty()) {
                            etdStr = nil;
                        } else {
                            etdStr = f.etd;
                        }
                        if (f.route.isEmpty()) {
                            routeStr = nil;
                        } else {
                            routeStr = f.route;
                        }
                        if (f.other_info.isEmpty()) {
                            other_infoStr = nil;
                        } else {
                            other_infoStr = f.other_info;
                        }
                        if (f.star.isEmpty()) {
                            STARStr = nil;
                        } else {
                            STARStr = f.star;
                        }

                        /*  call combineDOF_ETA method to combine dof and eta to get newETA -> in format recognized by Flow Control */
                        newETA = fmsfunctions.combineETA_DOF(dofStr, etaStr);
                        
                        //delete flight in internal auto list if flight exists
                        fmsfunctions.updateAuto(callsignStr);

                        stringETA[counter] = newETA;
                        stringStatus[counter] = statusStr;
                        stringSequenceType[counter] = sequenceTypeStr;
                        stringFeederFix[counter] = feederFixStr;
                        stringCallsign[counter] = callsignStr;
                        stringAdep[counter] = adepStr;
                        stringAdes[counter] = adesStr;
                        stringEobt[counter] = eobtStr;
                        stringDof[counter] = dofStr;
                        stringAc_type[counter] = ac_typeStr;
                        stringWtur[counter] = wturStr;
                        stringFrule[counter] = fruleStr;
                        intAtd[counter] = atdStr;
                        intSpeed[counter] = speedStr;
                        intRfl[counter] = rflStr;
                        stringRegis[counter] = regisStr;
                        stringEtd[counter] = etdStr;
                        stringEtd_dof[counter] = etd_dofStr;
                        stringRoute[counter] = routeStr;
                        stringOther_info[counter] = other_infoStr;
                        stringSTAR[counter] = STARStr;
                        counter++;
                    }
                }

            } catch (IOException e) {
            }

            //check if star value = "..." --> if true then send flight to internal manual list
//            if (STARStr == nil) {
//                fmsfunctions.updateAuto(callsignStr);
//                Manual.manualFromStreamFolder(newETA, statusStr, sequenceTypeStr, feederFixStr, callsignStr, adepStr, adesStr, eobtStr, dofStr, ac_typeStr, wturStr, fruleStr, atdStr, speedStr, rflStr, regisStr, etdStr, etd_dofStr, routeStr, other_infoStr, STARStr);
//                
//            } else {
//
//            }

            if ("auto".equals(sequenceTypeStr) && "CONTROLLED".equals(statusStr)) {
                for (int x = 0; x < autoETAList.length; x++) {
                    autoETAList[x] = stringETA[x];
                    autoStatusList[x] = stringStatus[x];
                    autoSequenceTypeList[x] = stringSequenceType[x];
                    autoFeederFixList[x] = stringFeederFix[x];
                    autoCallsign[x] = stringCallsign[x];
                    autoAdep[x] = stringAdep[x];
                    autoAdes[x] = stringAdes[x];
                    autoEobt[x] = stringEobt[x];
                    autoDof[x] = stringDof[x];
                    autoAc_type[x] = stringAc_type[x];
                    autoWtur[x] = stringWtur[x];
                    autoFrule[x] = stringFrule[x];
                    autoAtd[x] = intAtd[x];
                    autoSpeed[x] = intSpeed[x];
                    autoRfl[x] = intRfl[x];
                    autoRegis[x] = stringRegis[x];
                    autoEtd[x] = stringEtd[x];
                    autoEtd_dof[x] = stringEtd_dof[x];
                    autoRoute[x] = stringRoute[x];
                    autoOther_info[x] = stringOther_info[x];
                    autoSTAR[x] = stringSTAR[x];
                }
                System.out.println("Call sign: " + Arrays.toString(autoCallsign));
                System.out.println("ETA: " + Arrays.toString(autoETAList));
                System.out.println("Sequence: " + Arrays.toString(autoSequenceTypeList));
                System.out.println("ETD: " + Arrays.toString(autoEtd));
                System.out.println("star name: " + Arrays.toString(autoSTAR));
                System.out.println(" \n\n####################### adep value from stream folder : " + Arrays.toString(autoAdep));


                /*  Get internal auto list xml file to add new ETA and flightnumber to it */
                String filePath = "C:/wamp64/www/flowcontrol/public/run/Internal_lists/auto/internal_auto_list.xml";
//                String filePath = "/var/www/html/flowcontrol/public/run/Internal_lists/auto/internal_auto_list.xml";
//                String filePath = stringPathInternalAutoList;

                File xmlFile = new File(filePath);
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(xmlFile);
                document.getDocumentElement().normalize();

//        Transformer transformer = TransformerFactory.newInstance().newTransformer();
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//        StreamResult result = new StreamResult(new StringWriter());
//        DOMSource source = new DOMSource(document);
//        transformer.transform(source, result);
//        String xmlString = result.getWriter().toString();
//        System.out.println(xmlString);
                Element root = document.getDocumentElement();
                NodeList nList = document.getElementsByTagName("flight");
                Element eElement = null;
                boolean child = nList.item(0).hasChildNodes();

                if (child == true) {
                    System.out.println("Internal Auto List not empty. Compare.");

                    NodeList nodeList = root.getChildNodes();

                    boolean found = false;

                    for (int temp = 0; temp < nodeList.getLength(); temp++) {
                        Node childnode = nodeList.item(temp);
                        if (childnode.getNodeType() == Node.ELEMENT_NODE && found == false) {
                            eElement = (Element) childnode;
                            if ("auto".equals(eElement.getElementsByTagName("sequenceType").item(0).getTextContent())) {

                                String flightContent = eElement.getElementsByTagName("callsign").item(0).getTextContent();
                                String eobtContent = eElement.getElementsByTagName("eobt").item(0).getTextContent();
                                String adesContent = eElement.getElementsByTagName("ades").item(0).getTextContent();
                                String adepContent = eElement.getElementsByTagName("adep").item(0).getTextContent();

                                System.out.println("Call sign: " + eElement.getElementsByTagName("callsign").item(0).getTextContent());
                                System.out.println("status: " + eElement.getElementsByTagName("status").item(0).getTextContent());
                                System.out.println("ETA: " + eElement.getElementsByTagName("eta").item(0).getTextContent());
                                System.out.println("star: " + eElement.getElementsByTagName("star").item(0).getTextContent());
                                System.out.println("ac_type: " + eElement.getElementsByTagName("ac_type").item(0).getTextContent());

                                if (flightContent.equals(autoCallsign[0]) && eobtContent.equals(autoEobt[0]) && adesContent.equals(autoAdes[0]) && adepContent.equals(autoAdep[0]) && autoCallsign[0] != null) {
                                    found = true;
                                    System.out.println("Item found!");
                                     
                                }
                            }
                        }
                    }

                    if (found == true && eElement != null) {
                        if (eElement.getElementsByTagName("eta").item(0).getFirstChild() == null) {

                        } else {
                            Node eta = eElement.getElementsByTagName("eta").item(0).getFirstChild();
                            eta.setNodeValue(autoETAList[0]);
                        }

                        if (eElement.getElementsByTagName("status").item(0).getFirstChild() == null) {

                        } else {
                            Node status = eElement.getElementsByTagName("status").item(0).getFirstChild();
                            status.setNodeValue(autoStatusList[0]);
                        }

                        if (eElement.getElementsByTagName("feederFix").item(0).getFirstChild() == null) {

                        } else {
                            Node feederFix = eElement.getElementsByTagName("feederFix").item(0).getFirstChild();
                            feederFix.setNodeValue(autoFeederFixList[0]);
                        }

                        if (eElement.getElementsByTagName("sequenceType").item(0).getFirstChild() == null) {

                        } else {
                            Node sequence = eElement.getElementsByTagName("sequenceType").item(0).getFirstChild();
                            sequence.setNodeValue(autoSequenceTypeList[0]);
                        }

                        if (eElement.getElementsByTagName("callsign").item(0).getFirstChild() == null) {

                        } else {
                            Node callsign = eElement.getElementsByTagName("callsign").item(0).getFirstChild();
                            callsign.setNodeValue(autoCallsign[0]);
                        }

                        if (eElement.getElementsByTagName("adep").item(0).getFirstChild() == null) {

                        } else {
                            Node adep = eElement.getElementsByTagName("adep").item(0).getFirstChild();
                            adep.setNodeValue(autoAdep[0]);
                        }

                        if (eElement.getElementsByTagName("ades").item(0).getFirstChild() == null) {

                        } else {
                            Node ades = eElement.getElementsByTagName("ades").item(0).getFirstChild();
                            ades.setNodeValue(autoAdes[0]);
                        }

                        if (eElement.getElementsByTagName("eobt").item(0).getFirstChild() == null) {

                        } else {
                            Node eobt = eElement.getElementsByTagName("eobt").item(0).getFirstChild();
                            eobt.setNodeValue(autoEobt[0]);
                        }

                        if (eElement.getElementsByTagName("dof").item(0).getFirstChild() == null) {

                        } else {
                            Node dof = eElement.getElementsByTagName("dof").item(0).getFirstChild();
                            dof.setNodeValue(autoDof[0]);
                        }

                        if (eElement.getElementsByTagName("ac_type").item(0).getFirstChild() == null) {

                        } else {
                            Node ac_type = eElement.getElementsByTagName("ac_type").item(0).getFirstChild();
                            ac_type.setNodeValue(autoAc_type[0]);
                        }

                        if (eElement.getElementsByTagName("wtur").item(0).getFirstChild() == null) {

                        } else {
                            Node wtur = eElement.getElementsByTagName("wtur").item(0).getFirstChild();
                            wtur.setNodeValue(autoWtur[0]);
                        }

                        if (eElement.getElementsByTagName("frule").item(0).getFirstChild() == null) {

                        } else {
                            Node frule = eElement.getElementsByTagName("frule").item(0).getFirstChild();
                            frule.setNodeValue(autoFrule[0]);
                        }

                        if (eElement.getElementsByTagName("atd").item(0).getFirstChild() == null) {

                        } else {
                            Node atd = eElement.getElementsByTagName("atd").item(0).getFirstChild();
                            atd.setNodeValue(autoAtd[0]);
                        }

                        if (eElement.getElementsByTagName("speed").item(0).getFirstChild() == null) {

                        } else {
                            Node speed = eElement.getElementsByTagName("speed").item(0).getFirstChild();
                            speed.setNodeValue(autoSpeed[0]);

                        }

                        if (eElement.getElementsByTagName("rfl").item(0).getFirstChild() == null) {

                        } else {
                            Node rfl = eElement.getElementsByTagName("rfl").item(0).getFirstChild();
                            rfl.setNodeValue(autoRfl[0]);
                        }

                        if (eElement.getElementsByTagName("regis").item(0).getFirstChild() == null) {

                        } else {
                            Node regis = eElement.getElementsByTagName("regis").item(0).getFirstChild();
                            regis.setNodeValue(autoRegis[0]);
                        }

                        if (eElement.getElementsByTagName("etd").item(0).getFirstChild() == null) {

                        } else {
                            Node etd = eElement.getElementsByTagName("etd").item(0).getFirstChild();
                            etd.setNodeValue(autoEtd[0]);
                        }

                        if (eElement.getElementsByTagName("etd_dof").item(0).getFirstChild() == null) {

                        } else {
                            Node etd_dof = eElement.getElementsByTagName("etd_dof").item(0).getFirstChild();
                            etd_dof.setNodeValue(autoEtd_dof[0]);
                        }

                        if (eElement.getElementsByTagName("route").item(0).getFirstChild() == null) {

                        } else {
                            Node route = eElement.getElementsByTagName("route").item(0).getFirstChild();
                            route.setNodeValue(autoRoute[0]);
                        }

                        if (eElement.getElementsByTagName("other_info").item(0).getFirstChild() == null) {

                        } else {
                            Node other_info = eElement.getElementsByTagName("other_info").item(0).getFirstChild();
                            other_info.setNodeValue(autoOther_info[0]);
                        }

                        if (eElement.getElementsByTagName("star").item(0).getFirstChild() == null) {

                        } else {
                            Node star = eElement.getElementsByTagName("star").item(0).getFirstChild();
                            star.setNodeValue(autoSTAR[0]);
                        }

                    }

                    if (found == false) {
                        Element newautos = document.createElement("flight");

                        Element eta1 = document.createElement("eta");
                        eta1.appendChild(document.createTextNode(autoETAList[0]));
                        newautos.appendChild(eta1);

                        Element sequenceType = document.createElement("sequenceType");
                        sequenceType.appendChild(document.createTextNode(autoSequenceTypeList[0]));
                        newautos.appendChild(sequenceType);

                        Element status1 = document.createElement("status");
                        status1.appendChild(document.createTextNode(autoStatusList[0]));
                        newautos.appendChild(status1);

                        Element feederFix1 = document.createElement("feederFix");
                        feederFix1.appendChild(document.createTextNode(autoFeederFixList[0]));
                        newautos.appendChild(feederFix1);

                        Element callsign1 = document.createElement("callsign");
                        callsign1.appendChild(document.createTextNode(autoCallsign[0]));
                        newautos.appendChild(callsign1);

                        Element adep1 = document.createElement("adep");
                        adep1.appendChild(document.createTextNode(autoAdep[0]));
                        newautos.appendChild(adep1);

                        Element ades1 = document.createElement("ades");
                        ades1.appendChild(document.createTextNode(autoAdes[0]));
                        newautos.appendChild(ades1);

                        Element eobt1 = document.createElement("eobt");
                        eobt1.appendChild(document.createTextNode(autoEobt[0]));
                        newautos.appendChild(eobt1);

                        Element dof1 = document.createElement("dof");
                        dof1.appendChild(document.createTextNode(autoDof[0]));
                        newautos.appendChild(dof1);

                        Element ac_type1 = document.createElement("ac_type");
                        ac_type1.appendChild(document.createTextNode(autoAc_type[0]));
                        newautos.appendChild(ac_type1);

                        Element wtur1 = document.createElement("wtur");
                        wtur1.appendChild(document.createTextNode(autoWtur[0]));
                        newautos.appendChild(wtur1);

                        Element frule1 = document.createElement("frule");
                        frule1.appendChild(document.createTextNode(autoFrule[0]));
                        newautos.appendChild(frule1);

                        Element atd1 = document.createElement("atd");
                        atd1.appendChild(document.createTextNode(autoAtd[0]));
                        newautos.appendChild(atd1);

                        Element speed1 = document.createElement("speed");
                        speed1.appendChild(document.createTextNode(autoSpeed[0]));
                        newautos.appendChild(speed1);

                        Element rfl1 = document.createElement("rfl");
                        rfl1.appendChild(document.createTextNode(autoRfl[0]));
                        newautos.appendChild(rfl1);

                        Element regis1 = document.createElement("regis");
                        regis1.appendChild(document.createTextNode(autoRegis[0]));
                        newautos.appendChild(regis1);

                        Element etd1 = document.createElement("etd");
                        etd1.appendChild(document.createTextNode(autoEtd[0]));
                        newautos.appendChild(etd1);

                        Element etd_dof1 = document.createElement("etd_dof");
                        etd_dof1.appendChild(document.createTextNode(autoEtd_dof[0]));
                        newautos.appendChild(etd_dof1);

                        Element route1 = document.createElement("route");
                        route1.appendChild(document.createTextNode(autoRoute[0]));
                        newautos.appendChild(route1);

                        Element other_info1 = document.createElement("other_info");
                        other_info1.appendChild(document.createTextNode(autoOther_info[0]));
                        newautos.appendChild(other_info1);

                        Element star1 = document.createElement("star");
                        star1.appendChild(document.createTextNode(autoSTAR[0]));
                        newautos.appendChild(star1);

                        root.appendChild(newautos);
                    }

                } else {
                    System.out.println("Internal Auto List is empty. Append.");
                    for (int x = 0; x < stringETA.length; x++) {
                        if (autoETAList[x] != null && autoCallsign[x] != null && autoStatusList[x] != null) {
                            Element newautos = document.createElement("flight");

                            Element eta = document.createElement("eta");
                            eta.appendChild(document.createTextNode(autoETAList[x]));
                            newautos.appendChild(eta);

                            Element sequenceType = document.createElement("sequenceType");
                            sequenceType.appendChild(document.createTextNode(autoSequenceTypeList[x]));
                            newautos.appendChild(sequenceType);

                            Element status = document.createElement("status");
                            status.appendChild(document.createTextNode(autoStatusList[x]));
                            newautos.appendChild(status);

                            Element feederFix = document.createElement("feederFix");
                            feederFix.appendChild(document.createTextNode(autoFeederFixList[x]));
                            newautos.appendChild(feederFix);

                            Element callsign = document.createElement("callsign");
                            callsign.appendChild(document.createTextNode(autoCallsign[x]));
                            newautos.appendChild(callsign);

                            Element adep = document.createElement("adep");
                            adep.appendChild(document.createTextNode(autoAdep[x]));
                            newautos.appendChild(adep);

                            Element ades = document.createElement("ades");
                            ades.appendChild(document.createTextNode(autoAdes[x]));
                            newautos.appendChild(ades);

                            Element eobt = document.createElement("eobt");
                            eobt.appendChild(document.createTextNode(autoEobt[x]));
                            newautos.appendChild(eobt);

                            Element dof = document.createElement("dof");
                            dof.appendChild(document.createTextNode(autoDof[x]));
                            newautos.appendChild(dof);

                            Element ac_type = document.createElement("ac_type");
                            ac_type.appendChild(document.createTextNode(autoAc_type[x]));
                            newautos.appendChild(ac_type);

                            Element wtur = document.createElement("wtur");
                            wtur.appendChild(document.createTextNode(autoWtur[x]));
                            newautos.appendChild(wtur);

                            Element frule = document.createElement("frule");
                            frule.appendChild(document.createTextNode(autoFrule[x]));
                            newautos.appendChild(frule);

                            Element atd = document.createElement("atd");
                            atd.appendChild(document.createTextNode(autoAtd[x]));
                            newautos.appendChild(atd);

                            Element speed = document.createElement("speed");
                            speed.appendChild(document.createTextNode(autoSpeed[x]));
                            newautos.appendChild(speed);

                            Element rfl = document.createElement("rfl");
                            rfl.appendChild(document.createTextNode(autoRfl[x]));
                            newautos.appendChild(rfl);

                            Element regis = document.createElement("regis");
                            regis.appendChild(document.createTextNode(autoRegis[x]));
                            newautos.appendChild(regis);

                            Element etd = document.createElement("etd");
                            etd.appendChild(document.createTextNode(autoEtd[x]));
                            newautos.appendChild(etd);

                            Element etd_dof = document.createElement("etd_dof");
                            etd_dof.appendChild(document.createTextNode(autoEtd_dof[x]));
                            newautos.appendChild(etd_dof);

                            Element route = document.createElement("route");
                            route.appendChild(document.createTextNode(autoRoute[x]));
                            newautos.appendChild(route);

                            Element other_info = document.createElement("other_info");
                            other_info.appendChild(document.createTextNode(autoOther_info[x]));
                            newautos.appendChild(other_info);

                            Element star = document.createElement("star");
                            star.appendChild(document.createTextNode(autoSTAR[x]));
                            newautos.appendChild(star);

                            root.appendChild(newautos);
                        }
                    }
                }
                DOMSource source = new DOMSource(document);
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer(new StreamSource("C:/wamp64/www/flowcontrol/public/run/Internal_lists/auto/xsltfile.xml"));
//            Transformer transformer = transformerFactory.newTransformer(new StreamSource("/var/www/html/flowcontrol/public/run/Internal_lists/auto/xsltfile.xml"));

                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                StreamResult result = new StreamResult("C:/wamp64/www/flowcontrol/public/run/Internal_lists/auto/internal_auto_list.xml");
//                StreamResult result = new StreamResult("/var/www/html/flowcontrol/public/run/Internal_lists/auto/internal_auto_list.xml");              

                transformer.transform(source, result);

            }
//            if ("manual".equals(sequenceTypeStr) && "CONTROLLED".equals(statusStr)) {
//                fmsfunctions.updateAuto(callsignStr);
//                Manual.manualFromStreamFolder(newETA, statusStr, sequenceTypeStr, feederFixStr, callsignStr, adepStr, adesStr, eobtStr, dofStr, ac_typeStr, wturStr, fruleStr, atdStr, speedStr, rflStr, regisStr, etdStr, etd_dofStr, routeStr, other_infoStr, STARStr);
//                
//            }

            if ("FINISHED".equals(statusStr)) {
                fmsfunctions.removeFinishedStatus(callsignStr);
            }

//            if("V".equals(fruleStr)){
//                Manual.manualFromStreamFolder(newETA, statusStr, sequenceTypeStr, feederFixStr, callsignStr, adepStr, adesStr, eobtStr, dofStr, ac_typeStr, wturStr, fruleStr, atdStr, speedStr, rflStr, regisStr, etdStr, etd_dofStr, routeStr, other_infoStr, STARStr);
//            }
        } else {
            System.out.println("stream folder is empty!");
        }
    }

    /*  func get auto ETA and flightnumber from internal_auto_list.xml   */
    @SuppressWarnings("empty-statement")
    public void getAuto(HashMap<String, String> autoFlightMap, Map<LocalDateTime, String> tempAutoMapETA, LocalDateTime[] autoArrETA, int landingRate, String[] feederFixArray, String[] adepArray, String[] adesArray, String[] ac_typeArray, String[] wturArray, String[] regisArray, String[] sidArray, String[] starArray, String[] other_infoArray, String[] routeArray, String[] etd_dofArray, String[] etdArray, String[] rflArray, String[] speedArray, String[] atdArray, String[] fruleArray, String[] eobtArray, String[] callsignArray, String[] dofArray) throws IOException {

        int count = 0;
        String[] TempfeederFixArray = new String[10000];
        String[] TempadepArray = new String[10000];
        String[] TempadesArray = new String[10000];
        String[] Tempac_typeArray = new String[10000];
        String[] TempwturArray = new String[10000];
        String[] TempregisArray = new String[10000];
        String[] TempstarArray = new String[10000];
        String[] Tempother_infoArray = new String[10000];
        String[] TemprouteArray = new String[10000];
        String[] Tempetd_dofArray = new String[10000];
        String[] TempetdArray = new String[10000];
        String[] TemprflArray = new String[10000];
        String[] TempspeedArray = new String[10000];
        String[] TempatdArray = new String[10000];
        String[] TempfruleArray = new String[10000];
        String[] TempeobtArray = new String[10000];
        String[] TempdofArray = new String[10000];
        try {
            //creating a constructor of file class and parsing an XML file  
//            File file = filePathInternalAutoList;
            File file = new File("C:/wamp64/www/flowcontrol/public/run/Internal_lists/auto/internal_auto_list.xml");
//                File file = new File("/var/www/html/flowcontrol/public/run/Internal_lists/auto/internal_auto_list.xml");

            //an instance of factory that gives a document builder  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file  
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("flight");
            System.out.println("nodeList :" + nodeList);
            Node parentFlight = doc.getChildNodes().item(0);
            System.out.println("parents :" + parentFlight);
            // nodeList is not iterable, so we are using for loop  
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    Node flightChilds = parentFlight.getChildNodes().item(itr);
                    System.out.println("flightChilds :" + flightChilds);

                    if (eElement.hasChildNodes() == true && eElement.getElementsByTagName("callsign").getLength() > 0) {
                        System.out.println("internal auto list not empty");
                        System.out.println("Call sign: " + eElement.getElementsByTagName("callsign").item(0).getTextContent());
                        System.out.println("Sequence Type: " + eElement.getElementsByTagName("sequenceType").item(0).getTextContent());
                        System.out.println("ETA: " + eElement.getElementsByTagName("eta").item(0).getTextContent());
                        String tempCallsign = eElement.getElementsByTagName("callsign").item(0).getTextContent();
                        String tempSequenceType = eElement.getElementsByTagName("sequenceType").item(0).getTextContent();
                        String autoETA = eElement.getElementsByTagName("eta").item(0).getTextContent();
                        String feederFix = eElement.getElementsByTagName("feederFix").item(0).getTextContent();
                        String adep = eElement.getElementsByTagName("adep").item(0).getTextContent();
                        String ades = eElement.getElementsByTagName("ades").item(0).getTextContent();
                        String ac_type = eElement.getElementsByTagName("ac_type").item(0).getTextContent();
                        String wtur = eElement.getElementsByTagName("wtur").item(0).getTextContent();
                        String regis = eElement.getElementsByTagName("regis").item(0).getTextContent();
                        String star = eElement.getElementsByTagName("star").item(0).getTextContent();
                        String other_info = eElement.getElementsByTagName("other_info").item(0).getTextContent();
                        String route = eElement.getElementsByTagName("route").item(0).getTextContent();
                        String etd_dof = eElement.getElementsByTagName("etd_dof").item(0).getTextContent();
                        String etd = eElement.getElementsByTagName("etd").item(0).getTextContent();
                        String rfl = eElement.getElementsByTagName("rfl").item(0).getTextContent();
                        String speed = eElement.getElementsByTagName("speed").item(0).getTextContent();
                        String atd = eElement.getElementsByTagName("atd").item(0).getTextContent();
                        String frule = eElement.getElementsByTagName("frule").item(0).getTextContent();
                        String eobt = eElement.getElementsByTagName("eobt").item(0).getTextContent();
                        String dof = eElement.getElementsByTagName("dof").item(0).getTextContent();

                        String[] autoFlightString = new String[nodeList.getLength()];
                        autoFlightString[count] = tempSequenceType;
                        if (tempSequenceType.equals("auto")) {
                            if (autoFlightMap.containsKey(tempCallsign)) {

                            } else {
                                autoFlightMap.put(tempCallsign, tempSequenceType);
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date date = sdf.parse(autoETA);
                                LocalDateTime ldtDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                                autoArrETA[itr] = ldtDate;
                                //check for same ETA
                                sequence checkETA = new sequence();
                                LocalDateTime[] compETA = checkETA.checkForSameETA(autoArrETA, landingRate);
                                tempAutoMapETA.put(compETA[itr], tempCallsign);
                                /*  other required flight info to be added here */
                                for (int r = 0; r < TempfeederFixArray.length; r++) {
                                    TempfeederFixArray[r] = feederFix;
                                    TempadepArray[r] = adep;
                                    TempadesArray[r] = ades;
                                    Tempac_typeArray[r] = ac_type;
                                    TempwturArray[r] = wtur;
                                    TempregisArray[r] = regis;
                                    TempstarArray[r] = star;
                                    Tempother_infoArray[r] = other_info;
                                    TemprouteArray[r] = route;
                                    Tempetd_dofArray[r] = etd_dof;
                                    TemprflArray[r] = rfl;
                                    TempspeedArray[r] = speed;
                                    TempatdArray[r] = atd;
                                    TempeobtArray[r] = eobt;
                                    TempdofArray[r] = dof;
                                    TempetdArray[r] = etd;
                                    TempfruleArray[r] = frule;
                                }
                                for (int x = count; x < feederFixArray.length; x++) {
                                    feederFixArray[x] = TempfeederFixArray[x];
                                    adepArray[x] = TempadepArray[x];
                                    adesArray[x] = TempadesArray[x];
                                    ac_typeArray[x] = Tempac_typeArray[x];
                                    wturArray[x] = TempwturArray[x];
                                    regisArray[x] = TempregisArray[x];
                                    starArray[x] = TempstarArray[x];
                                    other_infoArray[x] = Tempother_infoArray[x];
                                    routeArray[x] = TemprouteArray[x];
                                    etd_dofArray[x] = Tempetd_dofArray[x];
                                    rflArray[x] = TemprflArray[x];
                                    speedArray[x] = TempspeedArray[x];
                                    atdArray[x] = TempatdArray[x];
                                    eobtArray[x] = TempeobtArray[x];
                                    dofArray[x] = TempdofArray[x];
                                    etdArray[x] = TempetdArray[x];
                                    fruleArray[x] = TempfruleArray[x];
//                                        System.out.println("ac_type :"+Arrays.toString(ac_typeArray));
//                                        System.out.println("star :"+Arrays.toString(starArray));
                                    count++;
                                    break;
                                }
                            }
                        }

                    } else if (eElement.hasChildNodes() == false) {
                        System.out.println("internal auto list is empty...");
                        /*  create blank xml */
                        try {
//                            String path = pathAuto;
                            String path = "C:/wamp64/www/flowcontrol/public/run/Internal_lists/auto/";
//                            String path = "/var/www/html/flowcontrol/public/run/Internal_lists/auto/";

                            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                            Document document = documentBuilder.newDocument();
                            Element root = document.createElement("flight");
                            document.appendChild(root);

                            DOMSource source = new DOMSource(document);

                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            Transformer transformer = transformerFactory.newTransformer();
                            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                            StreamResult result = new StreamResult(path + "internal_auto_list.xml");
                            transformer.transform(source, result);

                        } catch (IllegalArgumentException | ParserConfigurationException | TransformerException | DOMException e) {

                        }

                    }

                }
            }
        } catch (IOException | ParseException | ParserConfigurationException | DOMException | SAXException e) {
        }

        //remove null values before printout
        System.out.println("\nac_typeArray");
        fmsfunctions.removeNullInStringArrays(ac_typeArray);

        System.out.println("\nstarArray");
        fmsfunctions.removeNullInStringArrays(starArray);

        System.out.println("\nautoArETA");
        fmsfunctions.removeNullInLocalDateTimeArrays(autoArrETA);
        while (tempAutoMapETA.values().remove(null));
        while (autoFlightMap.values().remove(null));
        System.out.println("tempAutoMapETA" + tempAutoMapETA + "\n" + "autoCallsignMap" + autoFlightMap);

    }

    /**
     * | This function checks to ensure the new manual flight coming from the
     * atm does not have ETA equal to any existing flights in the internal auto
     * list --> if ETA equals that of any flight in internal auto list then add
     * minutes according to landing rate
     *
     * @param landingrate
     * @param manualETA
     * @return
     * @throws java.text.ParseException
     */
    public static String compareStreamManualETAtoInternalAutoETA(String landingrate, String manualETA) throws ParseException {

//        LocalDateTime[] FFT = new LocalDateTime[1000];
        //convert String landingrate to long int
        long lrate = Long.parseLong(landingrate);
        //convert String manualETA to LocalDateTime 
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = simpledateformat.parse(manualETA);
        LocalDateTime ldtmanualETA = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        try {
            //creating a constructor of file class and parsing an XML file  
            File file = new File("C:/wamp64/www/flowcontrol/public/run/Internal_lists/auto/internal_auto_list.xml");
//            File file = new File("/var/www/html/flowcontrol/public/run/Internal_lists/auto/internal_auto_list.xml");
            //an instance of factory that gives a document builder  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file  
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("Slotlist");
//            int counter = 0;
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if (eElement.hasChildNodes() == true && eElement.getElementsByTagName("callsign").getLength() > 0) {
                        System.out.println(" INTERNAL AUTO LIST  ETA : " + eElement.getElementsByTagName("eta").item(0).getTextContent());
                        //convert String fETA to LocalDateTime 
                        String fETA = eElement.getElementsByTagName("eta").item(0).getTextContent();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = sdf.parse(fETA);
                        LocalDateTime ldtDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                        if (ldtDate.equals(ldtmanualETA)) {
                            ldtmanualETA = ldtDate.plusMinutes(lrate);
                            System.out.println(" INTERNAL AUTO LIST  ETA : " + eElement.getElementsByTagName("eta").item(0).getTextContent());
                        } else {
                            // do nothing ...   
                        }
                    }

                }
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
        }
        //convert LocalDateTime to String 
        // Create DateTimeFormatter instance with specified format
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format LocalDateTime to String
        String formattedDateTime = ldtmanualETA.format(dateTimeFormatter);
        return formattedDateTime;
    }   

}
