/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flowmanagementsystem;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import static com.mycompany.flowmanagementsystem.copyMap.copyMap;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualAc_typeList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualAdepList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualAdesList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualAtdList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualCallsignList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualDofList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualETAList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualEobtList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualEtdList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualEtd_dofList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualFeederFixList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualFruleList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualOther_infoList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualRegisList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualRflList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualRouteList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualSequenceTypeList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualSpeedList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualStarList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualStatusList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manualWturList;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.reserveSlot;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.strLR;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.ManStringFFT;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.ManualformattedDateTime2;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.manString;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.stringFFT;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.io.File;
import com.ximpleware.ModifyException;
import com.ximpleware.NavException;
import com.ximpleware.TranscodeException;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
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
public class Manual {

    public Map<LocalDateTime, String> createManualETA(HashMap<LocalDateTime, String> tempAuotoMapETA, HashMap<String, String> manflightmap, Map<LocalDateTime, String> tempmanualeta) {

        System.out.println("tempmanualeta in createManualETA method " + tempmanualeta);

        Map<LocalDateTime, String> manualETA = new TreeMap<>();
        Set<String> tempManKeys = manflightmap.keySet();
        for (String mapkey : tempManKeys) {
            System.out.println("Call sign exists in manual ETA list");
            /*   removes flightnumber in auto list (tempAutoMapETA) list that has been moved to manual (manualETA)*/
            List<LocalDateTime> keys = tempAuotoMapETA.entrySet().stream()
                    .filter(entry -> mapkey.equals(entry.getValue()))
                    .map(entry -> entry.getKey())
                    .collect(Collectors.toList());
            // phase 2: remove those keys
            for (LocalDateTime key : keys) {
                // this version of reomve double-checks if that key still has that value
                tempAuotoMapETA.remove(key, mapkey);
            }
        }
        manualETA.putAll(tempmanualeta);
        System.out.println("manualETA in createManualEAT method " + manualETA);
        return manualETA;
    }

    /*  function to perform two tasks */
 /*  1. To take in data from manual xml and store in internal manual list -> internal_manual_list.xml */
 /*  2. To take data from internal manual list and build map list of manual flights */
    public void buildInternalManualList(String[] manualETAList, String[] manualCallsignList, String[] manualSequenceTypeList, String[] manualFeederFixList, String[] manualAdepList, String[] manualAdesList, String[] manualEobtList, String[] manualDofList, String[] manualAc_typeList, String[] manualWturList, String[] manualFruleList, String[] manualAtdList, String[] manualSpeedList, String[] manualRflList, String[] manualRegisList, String[] manualEtdList, String[] manualEtd_dofList, String[] manualRouteList, String[] manualOther_infoList, String[] manualStatusList, String[] manualStarList) throws NavException, FileNotFoundException, IOException, TranscodeException, ModifyException, ParserConfigurationException, SAXException, TransformerConfigurationException, TransformerException {
        /*  take ETA and flightnumber from amtfile.xml and pass to manualETAList and manualCallsignList arrays */

        System.out.println("\n\n ** Running buildInternalManualList method ");

        String[] stringETA = new String[10000];
        String[] stringCallsign = new String[10000];
        String[] stringSequenceType = new String[10000];
        String[] stringFeederFix = new String[10000];
        String[] stringAdep = new String[10000];
        String[] stringAdes = new String[10000];
        String[] stringEobt = new String[10000];
        String[] stringDof = new String[10000];
        String[] stringAc_type = new String[10000];
        String[] stringWtur = new String[10000];
        String[] stringFrule = new String[10000];
        String[] stringAtd = new String[10000];
        String[] stringSpeed = new String[10000];
        String[] stringRfl = new String[10000];
        String[] stringRegis = new String[10000];
        String[] stringEtd = new String[10000];
        String[] stringEtd_dof = new String[10000];
        String[] stringRoute = new String[10000];
        String[] stringOther_info = new String[10000];
        String[] stringStar = new String[10000];
        String[] stringStatus = new String[10000];

        String callSign = null;

        int counter = 0;
        try {
            ObjectMapper mapper = new XmlMapper();
            InputStream inputStream = new FileInputStream(new File("C:/wamp64/www/flowcontrol/public/run/configs/manual.xml"));
//            InputStream inputStream = new FileInputStream(new File("/var/www/html/flowcontrol/public/run/configs/manual.xml"));

            TypeReference<List<flights>> typeReference = new TypeReference<List<flights>>() {
            };
            List<flights> flight = mapper.readValue(inputStream, typeReference);
            for (flights f : flight) {
                String eta;
                String feederFix;

                String adep;
                String ades;
                String eobt;
                String dof;
                String ac_type;
                String wtur;
                String frule;
                String atd;
                String speed;
                String rfl;
                String regis;
                String etd;
                String etd_dof;
                String route;
                String other_info;
                String status;
                String sequenceType;
                String star;

                eta = f.eta;
                feederFix = f.feederFix;
                callSign = f.callsign;
                adep = f.adep;
                ades = f.ades;
                eobt = f.eobt;
                dof = f.dof;
                ac_type = f.ac_type;
                wtur = f.wtur;
                frule = f.frule;
                atd = f.atd;
                speed = f.speed;
                rfl = f.rfl;
                regis = f.regis;
                etd = f.etd;
                etd_dof = f.etd_dof;
                route = f.route;
                other_info = f.other_info;
                status = f.status;
                sequenceType = f.sequenceType;
                star = f.star;

                stringETA[counter] = eta;
                stringFeederFix[counter] = feederFix;
                stringCallsign[counter] = callSign;
                stringAdep[counter] = adep;
                stringAdes[counter] = ades;
                stringEobt[counter] = eobt;
                stringDof[counter] = dof;
                stringAc_type[counter] = ac_type;
                stringWtur[counter] = wtur;
                stringFrule[counter] = frule;
                stringAtd[counter] = atd;
                stringSpeed[counter] = speed;
                stringRfl[counter] = rfl;
                stringRegis[counter] = regis;
                stringEtd[counter] = etd;
                stringEtd_dof[counter] = etd_dof;
                stringRoute[counter] = route;
                stringOther_info[counter] = other_info;
                stringStatus[counter] = status;
                stringSequenceType[counter] = sequenceType;
                stringStar[counter] = star;
                counter++;
            }
        } catch (IOException e) {
        }

        for (int x = 0; x < manualETAList.length; x++) {
            manualETAList[x] = stringETA[x];
            manualFeederFixList[x] = stringFeederFix[x];
            manualCallsignList[x] = stringCallsign[x];
            manualAdepList[x] = stringAdep[x];
            manualAdesList[x] = stringAdes[x];
            manualEobtList[x] = stringEobt[x];
            manualDofList[x] = stringDof[x];
            manualAc_typeList[x] = stringAc_type[x];
            manualWturList[x] = stringWtur[x];
            manualFruleList[x] = stringFrule[x];
            manualAtdList[x] = stringAtd[x];
            manualSpeedList[x] = stringSpeed[x];
            manualRflList[x] = stringRfl[x];
            manualRegisList[x] = stringRegis[x];
            manualEtdList[x] = stringEtd[x];
            manualEtd_dofList[x] = stringEtd_dof[x];
            manualRouteList[x] = stringRoute[x];
            manualOther_infoList[x] = stringOther_info[x];
            manualStatusList[x] = stringStatus[x];
            manualSequenceTypeList[x] = stringSequenceType[x];
            manualStarList[x] = stringStar[x];
        }
        System.out.println("\nManual Call sign: ");

        fmsfunctions.removeNullInStringArrays(manualCallsignList);
        if (callSign == null) {
            System.out.println("Call sign : " + callSign);
            System.out.println("manual list is empty ...");

        } else {
            //}

            /*  Get internal manual list xml file to add new ETA and flightnumber to it */
            String filePath = "C:/wamp64/www/flowcontrol/public/run/Internal_lists/manual/internal_manual_list.xml";
//            String filePath = "/var/www/html/flowcontrol/public/run/Internal_lists/manual/internal_manual_list.xml";
            File xmlFile = new File(filePath);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();
            NodeList nList = document.getElementsByTagName("manual");
            Element eElement = null;
            boolean child = nList.item(0).hasChildNodes();

            if (child == true) {
                System.out.println("Internal manual List not empty. Compare.");

                NodeList nodeList = root.getChildNodes();

                boolean found = false;

                for (int temp = 0; temp < nodeList.getLength(); temp++) {
                    Node childnode = nodeList.item(temp);

                    if (childnode.getNodeType() == Node.ELEMENT_NODE && found == false) {
                        eElement = (Element) childnode;
                        if ("manual".equals(eElement.getElementsByTagName("sequenceType").item(0).getTextContent())) {

                        }
                        String flightContent = eElement.getElementsByTagName("callsign").item(0).getTextContent();
                        String eobtContent = eElement.getElementsByTagName("eobt").item(0).getTextContent();
                        String adesContent = eElement.getElementsByTagName("ades").item(0).getTextContent();
                        String adepContent = eElement.getElementsByTagName("adep").item(0).getTextContent();

                        if (flightContent.equals(manualCallsignList[0]) && eobtContent.equals(manualEobtList[0]) && adesContent.equals(manualAdesList[0]) && adepContent.equals(manualAdepList[0]) && manualCallsignList[0] != null) {
                            found = true;
                            System.out.println("Item found!");
                        }
                    }
                }

                if (found == true && eElement != null) {
                    Node eta = eElement.getElementsByTagName("eta").item(0).getFirstChild();
                    eta.setNodeValue(manualETAList[0]);

                    Node status = eElement.getElementsByTagName("status").item(0).getFirstChild();
                    status.setNodeValue(manualStatusList[0]);

                    Node feederFix = eElement.getElementsByTagName("feederFix").item(0).getFirstChild();
                    feederFix.setNodeValue(manualFeederFixList[0]);

                    Node sequence = eElement.getElementsByTagName("sequenceType").item(0).getFirstChild();
                    sequence.setNodeValue(manualSequenceTypeList[0]);

                    Node callsign = eElement.getElementsByTagName("callsign").item(0).getFirstChild();
                    callsign.setNodeValue(manualCallsignList[0]);

                    Node adep = eElement.getElementsByTagName("adep").item(0).getFirstChild();
                    adep.setNodeValue(manualAdepList[0]);

                    Node ades = eElement.getElementsByTagName("ades").item(0).getFirstChild();
                    ades.setNodeValue(manualAdesList[0]);

                    Node eobt = eElement.getElementsByTagName("eobt").item(0).getFirstChild();
                    eobt.setNodeValue(manualEobtList[0]);

                    Node dof = eElement.getElementsByTagName("dof").item(0).getFirstChild();
                    dof.setNodeValue(manualDofList[0]);

                    Node ac_type = eElement.getElementsByTagName("ac_type").item(0).getFirstChild();
                    ac_type.setNodeValue(manualAc_typeList[0]);

                    Node wtur = eElement.getElementsByTagName("wtur").item(0).getFirstChild();
                    wtur.setNodeValue(manualWturList[0]);

                    Node frule = eElement.getElementsByTagName("frule").item(0).getFirstChild();
                    frule.setNodeValue(manualFruleList[0]);

                    Node atd = eElement.getElementsByTagName("atd").item(0).getFirstChild();
                    atd.setNodeValue(manualAtdList[0]);

                    Node speed = eElement.getElementsByTagName("speed").item(0).getFirstChild();
                    speed.setNodeValue(manualSpeedList[0]);

                    Node rfl = eElement.getElementsByTagName("rfl").item(0).getFirstChild();
                    rfl.setNodeValue(manualRflList[0]);

                    Node regis = eElement.getElementsByTagName("regis").item(0).getFirstChild();
                    regis.setNodeValue(manualRegisList[0]);

                    Node etd = eElement.getElementsByTagName("etd").item(0).getFirstChild();
                    etd.setNodeValue(manualEtdList[0]);

                    Node etd_dof = eElement.getElementsByTagName("etd_dof").item(0).getFirstChild();
                    etd_dof.setNodeValue(manualEtd_dofList[0]);

                    Node route = eElement.getElementsByTagName("route").item(0).getFirstChild();
                    route.setNodeValue(manualRouteList[0]);

                    Node other_info = eElement.getElementsByTagName("other_info").item(0).getFirstChild();
                    other_info.setNodeValue(manualOther_infoList[0]);

                    Node star = eElement.getElementsByTagName("star").item(0).getFirstChild();
                    star.setNodeValue(manualStarList[0]);
                } else {
                }

                if (found == false) {
                    Element newmanuals = document.createElement("manual");

                    Element eta1 = document.createElement("eta");
                    eta1.appendChild(document.createTextNode(manualETAList[0]));
                    newmanuals.appendChild(eta1);

                    Element sequenceType = document.createElement("sequenceType");
                    sequenceType.appendChild(document.createTextNode(manualSequenceTypeList[0]));
                    newmanuals.appendChild(sequenceType);

                    Element status1 = document.createElement("status");
                    status1.appendChild(document.createTextNode(manualStatusList[0]));
                    newmanuals.appendChild(status1);

                    Element feederFix1 = document.createElement("feederFix");
                    feederFix1.appendChild(document.createTextNode(manualFeederFixList[0]));
                    newmanuals.appendChild(feederFix1);

                    Element callsign1 = document.createElement("callsign");
                    callsign1.appendChild(document.createTextNode(manualCallsignList[0]));
                    newmanuals.appendChild(callsign1);

                    Element adep1 = document.createElement("adep");
                    adep1.appendChild(document.createTextNode(manualAdepList[0]));
                    newmanuals.appendChild(adep1);

                    Element ades1 = document.createElement("ades");
                    ades1.appendChild(document.createTextNode(manualAdesList[0]));
                    newmanuals.appendChild(ades1);

                    Element eobt1 = document.createElement("eobt");
                    eobt1.appendChild(document.createTextNode(manualEobtList[0]));
                    newmanuals.appendChild(eobt1);

                    Element dof1 = document.createElement("dof");
                    dof1.appendChild(document.createTextNode(manualDofList[0]));
                    newmanuals.appendChild(dof1);

                    Element ac_type1 = document.createElement("ac_type");
                    ac_type1.appendChild(document.createTextNode(manualAc_typeList[0]));
                    newmanuals.appendChild(ac_type1);

                    Element wtur1 = document.createElement("wtur");
                    wtur1.appendChild(document.createTextNode(manualWturList[0]));
                    newmanuals.appendChild(wtur1);

                    Element frule1 = document.createElement("frule");
                    frule1.appendChild(document.createTextNode(manualFruleList[0]));
                    newmanuals.appendChild(frule1);

                    Element atd1 = document.createElement("atd");
                    atd1.appendChild(document.createTextNode(manualAtdList[0]));
                    newmanuals.appendChild(atd1);

                    Element speed1 = document.createElement("speed");
                    speed1.appendChild(document.createTextNode(manualSpeedList[0]));
                    newmanuals.appendChild(speed1);

                    Element rfl1 = document.createElement("rfl");
                    rfl1.appendChild(document.createTextNode(manualRflList[0]));
                    newmanuals.appendChild(rfl1);

                    Element regis1 = document.createElement("regis");
                    regis1.appendChild(document.createTextNode(manualRegisList[0]));
                    newmanuals.appendChild(regis1);

                    Element etd1 = document.createElement("etd");
                    etd1.appendChild(document.createTextNode(manualEtdList[0]));
                    newmanuals.appendChild(etd1);

                    Element etd_dof1 = document.createElement("etd_dof");
                    etd_dof1.appendChild(document.createTextNode(manualEtd_dofList[0]));
                    newmanuals.appendChild(etd_dof1);

                    Element route1 = document.createElement("route");
                    route1.appendChild(document.createTextNode(manualRouteList[0]));
                    newmanuals.appendChild(route1);

                    Element other_info1 = document.createElement("other_info");
                    other_info1.appendChild(document.createTextNode(manualOther_infoList[0]));
                    newmanuals.appendChild(other_info1);

                    Element star1 = document.createElement("star");
                    star1.appendChild(document.createTextNode(manualStarList[0]));
                    newmanuals.appendChild(star1);

                    root.appendChild(newmanuals);
                }

            } else {
                System.out.println("Internal manual List is empty. Append.");
                for (int x = 0; x < stringETA.length; x++) {
                    if (manualETAList[x] != null && manualCallsignList[x] != null && manualSequenceTypeList[x] != null) {
                        Element newmanuals = document.createElement("manual");

                        Element eta = document.createElement("eta");
                        eta.appendChild(document.createTextNode(manualETAList[x]));
                        newmanuals.appendChild(eta);

                        Element feederFix = document.createElement("feederFix");
                        feederFix.appendChild(document.createTextNode(manualFeederFixList[x]));
                        newmanuals.appendChild(feederFix);

                        Element callsign = document.createElement("callsign");
                        callsign.appendChild(document.createTextNode(manualCallsignList[x]));
                        newmanuals.appendChild(callsign);

                        Element adep = document.createElement("adep");
                        adep.appendChild(document.createTextNode(manualAdepList[x]));
                        newmanuals.appendChild(adep);

                        Element ades = document.createElement("ades");
                        ades.appendChild(document.createTextNode(manualAdesList[x]));
                        newmanuals.appendChild(ades);

                        Element eobt = document.createElement("eobt");
                        eobt.appendChild(document.createTextNode(manualEobtList[x]));
                        newmanuals.appendChild(eobt);

                        Element dof = document.createElement("dof");
                        dof.appendChild(document.createTextNode(manualDofList[x]));
                        newmanuals.appendChild(dof);

                        Element ac_type = document.createElement("ac_type");
                        ac_type.appendChild(document.createTextNode(manualAc_typeList[x]));
                        newmanuals.appendChild(ac_type);

                        Element wtur = document.createElement("wtur");
                        wtur.appendChild(document.createTextNode(manualWturList[x]));
                        newmanuals.appendChild(wtur);

                        Element frule = document.createElement("frule");
                        frule.appendChild(document.createTextNode(manualFruleList[x]));
                        newmanuals.appendChild(frule);

                        Element atd = document.createElement("atd");
                        atd.appendChild(document.createTextNode(manualAtdList[x]));
                        newmanuals.appendChild(atd);

                        Element speed = document.createElement("speed");
                        speed.appendChild(document.createTextNode(manualSpeedList[x]));
                        newmanuals.appendChild(speed);

                        Element rfl = document.createElement("rfl");
                        rfl.appendChild(document.createTextNode(manualRflList[x]));
                        newmanuals.appendChild(rfl);

                        Element regis = document.createElement("regis");
                        regis.appendChild(document.createTextNode(manualRegisList[x]));
                        newmanuals.appendChild(regis);

                        Element etd = document.createElement("etd");
                        etd.appendChild(document.createTextNode(manualEtdList[x]));
                        newmanuals.appendChild(etd);

                        Element etd_dof = document.createElement("etd_dof");
                        etd_dof.appendChild(document.createTextNode(manualEtd_dofList[x]));
                        newmanuals.appendChild(etd_dof);

                        Element route = document.createElement("route");
                        route.appendChild(document.createTextNode(manualRouteList[x]));
                        newmanuals.appendChild(route);

                        Element other_info = document.createElement("other_info");
                        other_info.appendChild(document.createTextNode(manualOther_infoList[x]));
                        newmanuals.appendChild(other_info);

                        Element status = document.createElement("status");
                        status.appendChild(document.createTextNode(manualStatusList[x]));
                        newmanuals.appendChild(status);

                        Element sequence = document.createElement("sequenceType");
                        sequence.appendChild(document.createTextNode(manualSequenceTypeList[x]));
                        newmanuals.appendChild(sequence);

                        Element star = document.createElement("star");
                        star.appendChild(document.createTextNode(manualStarList[x]));
                        newmanuals.appendChild(star);

                        root.appendChild(newmanuals);
                    }
                }
            }
            DOMSource source = new DOMSource(document);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File("C:/wamp64/www/flowcontrol/public/run/Internal_lists/manual/xsltfile.xml")));
//            Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File("/var/www/html/flowcontrol/public/run/Internal_lists/manual/xsltfile.xml")));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            StreamResult result = new StreamResult("C:/wamp64/www/flowcontrol/public/run/Internal_lists/manual/internal_manual_list.xml");
//            StreamResult result = new StreamResult("/var/www/html/flowcontrol/public/run/Internal_lists/manual/internal_manual_list.xml");

            transformer.transform(source, result);
        }

    }

    /*  func get manual ETA and flightnumber from internal_manual_list.xml   */
    @SuppressWarnings("empty-statement")
    public Map<LocalDateTime, String> getManual(HashMap<String, String> manFlightMap, Map<LocalDateTime, String> tempManualMapETA, LocalDateTime[] manArrETA, int landingRate, String[] ManfeederFixArray, String[] ManadepArray, String[] ManadesArray, String[] Manac_typeArray, String[] ManwturArray, String[] ManregisArray, String[] MansidArray, String[] ManstarArray, String[] Manother_infoArray, String[] ManrouteArray, String[] Manetd_dofArray, String[] ManetdArray, String[] ManrflArray, String[] ManspeedArray, String[] ManatdArray, String[] ManfruleArray, String[] ManeobtArray, String[] MancallsignArray, String[] MandofArray) throws ParseException, TransformerException {

        LocalDateTime[] tSlots = new LocalDateTime[720];
        Map<LocalDateTime, String> slotList = new TreeMap<>();
        Map<LocalDateTime, String> timeSlot = new TreeMap<>();
        HashMap<String, String> flightStatus = new HashMap<>();
        LocalDateTime[] fEta = new LocalDateTime[10000];
        LocalDateTime[] compETA = new LocalDateTime[10000];
        HashMap<LocalDateTime, String> flightsETA = new HashMap<>();
//        Map<LocalDateTime, String> NewManualETA = new TreeMap<>();
        String stats = "CONTROLLED";
        int stableSequencePeriod = 0;

        sequence slotTimes;
        slotTimes = new sequence();
        Map<LocalDateTime, String> newFlightsETA = new TreeMap<>();
        slotTimes = new sequence();
        fmsfunctions hashMapToTreeMap = new fmsfunctions();
        LocalDateTime[] FFTimes = new LocalDateTime[10000];
//        String formattedDateTime2 = null;
//        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        // Creating Custom formatter
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        int count = 0;

        try {
            //creating a constructor of file class and parsing an XML file  
            File file = new File("C:/wamp64/www/flowcontrol/public/run/Internal_lists/manual/internal_manual_list.xml");
//            File file = new File("/var/www/html/flowcontrol/public/run/Internal_lists/manual/internal_manual_list.xml");

            //an instance of factory that gives a document builder  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file  
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("manual");
            // nodeList is not iterable, so we are using for loop  
            int counter = 0;
            for (int itr = 1; itr < nodeList.getLength(); itr++) {

                System.out.println("nodeList length in internal manual : " + nodeList.getLength());

                Node node = nodeList.item(itr);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    if (eElement.hasChildNodes() == false) {

                    } else {
                        System.out.println("Call sign: " + eElement.getElementsByTagName("callsign").item(0).getTextContent());
                        System.out.println("Sequence Type: " + eElement.getElementsByTagName("sequenceType").item(0).getTextContent());
                        System.out.println("ETA: " + eElement.getElementsByTagName("eta").item(0).getTextContent());
                        System.out.println("status: " + eElement.getElementsByTagName("status").item(0).getTextContent());
                        System.out.println("ETA: " + eElement.getElementsByTagName("eta").item(0).getTextContent());
                        System.out.println("star: " + eElement.getElementsByTagName("star").item(0).getTextContent());
                        System.out.println("ac_type: " + eElement.getElementsByTagName("ac_type").item(0).getTextContent());
                        System.out.println("frule: " + eElement.getElementsByTagName("frule").item(0).getTextContent());
                        System.out.println("feederFix: " + eElement.getElementsByTagName("feederFix").item(0).getTextContent());
                        System.out.println("adep : " + eElement.getElementsByTagName("adep").item(0).getTextContent());

                        String tempCallsign = eElement.getElementsByTagName("callsign").item(0).getTextContent();
                        String tempSequenceType = eElement.getElementsByTagName("sequenceType").item(0).getTextContent();
                        String fETA = eElement.getElementsByTagName("eta").item(0).getTextContent();
//                        String feederFix = eElement.getElementsByTagName("feederFix").item(0).getTextContent();
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
                        String stars = eElement.getElementsByTagName("star").item(0).getTextContent();
                        String tempStatus = eElement.getElementsByTagName("status").item(0).getTextContent();

                        String[] manFlightString = new String[nodeList.getLength()];
                        manFlightString[counter] = tempSequenceType;
                        if (tempSequenceType.equals("manual")) {
                            
                            //remove the flight from auto list if it has been moved to manual --> by calling updateAuto function
                            fmsfunctions.updateAuto(tempCallsign);
                            
                            if (manFlightMap.containsKey(tempCallsign)) {
                            } else {
                                if (stars.contains("...")) {
                                    System.out.println("Star is blank -- > ");
                                } else {

                                    //use getStar method to get rwy using star in atm file                          
                                    String rwy = sequence.getStar(stars);
                                    System.out.println("rwy = " + rwy);

                                    //process aircrafts with stars containing runway 14     
                                    if (tempStatus.equals(stats) && rwy.contains("14")) {

                                        flightStatus.put(tempCallsign, tempStatus);
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        Date date = sdf.parse(fETA);
                                        LocalDateTime ldtDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().truncatedTo(ChronoUnit.MINUTES);
                                        /* Check if ldtDate is within stable sequence period -> if so then add (5 min plus stable sequence 
                                    period) time to bring it out of the stable sequence period*/
 /* Get current time in LocalDateTime format */
                                        LocalDateTime currentTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
                                        System.out.println("currentTime 14:" + currentTime);
                                        System.out.println("ldtDate 14 (manual): " + ldtDate);

                                        long min = ChronoUnit.MINUTES.between(currentTime, ldtDate);

                                        System.out.println("minute diff between currTime and ldtDate rwy 14 (manual) : " + min);
                                        System.out.println("Difference between current time and ETA ( rwy 14 manual) : " + min + " minutes");

                                        /*  Check if difference of ETA and current (diffOfETAandCurrTime) is <= stable sequence period -> if so then add to fEta array */
//                                if (min <= stableSequencePeriod) {
//                                    int timeToAdd = stableSequencePeriod + 5;
//                                    ldtDate = ldtDate.plusMinutes(timeToAdd);
//                                }else{
//                                    
//                                }
                                        System.out.println("ldtDate after comparing with stable sequence period (manual rwy 14) : " + ldtDate);

                                        /*Check if new ETAs do not take up a reserveSlot -> if so then increase its minutes by landingRate*/
                                        for (LocalDateTime reserveSlot1 : reserveSlot) {
                                            if (ldtDate.equals(reserveSlot1)) {
                                                ldtDate = ldtDate.plusMinutes(landingRate);
                                            }
                                        }
                                        System.out.println("ldtDate rwy 14 (manual) :" + ldtDate);

                                        fEta[counter] = ldtDate;

                                        System.out.println("\n\n fEta manual rwy 14 (manual) : ");
                                        fmsfunctions.removeNullInLocalDateTimeArrays(fEta);

                                        //check for same ETA
                                        sequence checkETA = new sequence();
                                        compETA = checkETA.checkForSameETA(fEta, landingRate);

                                        System.out.println("\ncompETA manual rwy 14\n");
                                        fmsfunctions.removeNullInLocalDateTimeArrays(compETA);

//                                flightsETA.put(compETA[itr], tempCallsign);
                                        flightsETA.put(fEta[counter], tempCallsign);

                                        System.out.println("flightsETA (manual rwy 14): " + flightsETA);

                                        //generate slot
                                        tSlots = slotTimes.generateSlot(tSlots, landingRate);

                                        //  sort tSlots in reverse order
                                        System.out.println("\nTime Slot 14 \n");
                                        for (LocalDateTime tSlot : tSlots) {
                                            Arrays.sort(tSlots, Comparator.nullsLast(Comparator.reverseOrder()));
                                            if (tSlot != null) {
//                                        System.out.println(tSlot);
                                            } else {

                                            }
                                        }

                                        /* create new Map Tree with ETA as keys and null string values */
                                        timeSlot = fmsfunctions.createTimeSlot(tSlots);
                                        timeSlot.entrySet().forEach((Map.Entry<LocalDateTime, String> entry) -> {
//                                    System.out.println(entry.getKey() + " : " + entry.getValue());
                                        });

                                        //create slotList with callsigns
                                        System.out.println("NewManualETA in auto rwy 14 before slotLists: " + newFlightsETA);
                                        slotList = fmsfunctions.makeSlotList(timeSlot, flightsETA);

                                        //print updated flightsETA list
                                        newFlightsETA = hashMapToTreeMap.convertToTreeMap(flightsETA);
                                        System.out.println("NewManualETA (rwy 14) " + newFlightsETA);
                                        newFlightsETA = slotList;
//                                 System.out.println("NewAutoETA (slotList) "+NewAutoETA);
                                        while (newFlightsETA.values().remove(null));
                                        System.out.println("NewManualETA as flightsETA (auto rwy 14) " + newFlightsETA);

                                        //extract slotted ETA in x and use to calculate FFT
                                        LocalDateTime[] tempETA = new LocalDateTime[compETA.length];
                                        LocalDateTime[] eta;
                                        newFlightsETA.entrySet().forEach((Map.Entry<LocalDateTime, String> entry) -> {
//                                     if (entry.getValue() != null) {
                                            // System.out.println(entry.getKey() + " : " + entry.getValue());
                                            for (int e = 0; e < tempETA.length; e++) {
                                                if (tempETA[e] == null) {
                                                    tempETA[e] = entry.getKey();
                                                    break;
                                                }

                                            }
//                                     }
                                        });

                                        System.out.println("\nNew ETA" + Arrays.toString(tempETA));
                                        eta = fmsfunctions.removeNullInLocalDateTimeArrays(tempETA);

                                        System.out.println("eta (manual rwy 14) : " + Arrays.toString(eta));
                                        System.out.println("eta length : " + eta.length);
//                                System.out.println("eta : "+Arrays.toString(eta));

                                        LocalDateTime lastETA = null;

                                        if (eta.length == 1) {
                                            lastETA = eta[0];
                                        } else if (eta.length > 1) {
                                            if (eta[1] == null) {
                                                lastETA = eta[0];
                                            } else {
                                                if (eta[eta.length - 1] != null) {
                                                    lastETA = eta[eta.length - 1];
                                                } else {
                                                    for (int l = 1; l < eta.length; l++) {
                                                        if (eta[eta.length - l] != null) {
                                                            lastETA = eta[eta.length - l];
                                                            System.out.println("eta[eta.length - l] " + eta[eta.length - l]);
                                                        }
                                                    }
                                                }

                                            }
                                        }

                                        System.out.println("lastETA manual rwy 14: " + lastETA);

                                        System.out.println("\n\n Flights ETA inside Try-Catch" + flightsETA);

                                        //check if star name exists in star list 
                                        System.out.println("Check if star is in star list ");
                                        boolean starExists = false;
                                        starExists = sequence.checkStarName(starExists, ac_type, stars);
                                        System.out.println("starExists = " + starExists);
                                        System.out.println("after running checkStarname method ");
                                        if (starExists == true) {
                                            /*  calculatte FFT using calculateFFT method*/
                                            System.out.println("compETA\n");
                                            fmsfunctions.removeNullInLocalDateTimeArrays(compETA);
                                            sequence calculateFeederFix = new sequence();
//                                    for(int x=0; x < FFTimes.length; x++){
                                            if (FFTimes[itr] == null) {
                                                FFTimes[itr] = calculateFeederFix.calculateFFT(lastETA, ac_type, stars);
                                            } else {

                                            }
//                                        break;
//                                    }

                                            System.out.println("\nFFTimes just after calculated (rwy 14) ");
                                            fmsfunctions.removeNullInLocalDateTimeArrays(FFTimes);
                                        } else {
                                        }

                                    }

                                    //process aircrafts with stars containing runway 32                              
                                    if (tempStatus.equals(stats) && rwy.contains("32")) {

                                        flightStatus.put(tempCallsign, tempStatus);
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        Date date = sdf.parse(fETA);
                                        LocalDateTime ldtDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().truncatedTo(ChronoUnit.MINUTES);
                                        /* Check if ldtDate is within stable sequence period -> if so then add (5 min plus stable sequence 
                                    period) time to bring it out of the stable sequence period*/
 /* Get current time in LocalDateTime format */
                                        LocalDateTime currentTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
                                        currentTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
                                        System.out.println("currentTime (rwy 32):" + currentTime);
                                        System.out.println("ldtDate (32): " + ldtDate);

                                        long min = ChronoUnit.MINUTES.between(currentTime, ldtDate);
                                        System.out.println("minute diff between currTime and ldtDate 32: " + min);
                                        System.out.println("Difference between current time and ETA ( rwy 32 ) : " + min + " minutes");

                                        /*  Check if difference of ETA and current (diffOfETAandCurrTime) is <= stable sequence period -> if so then add to fEta array */
//                                if (min <= stableSequencePeriod) {
//                                    int timeToAdd = stableSequencePeriod + 5;
//                                    ldtDate = ldtDate.plusMinutes(timeToAdd);
//                                }else{
//                                    
//                                }
                                        System.out.println("ldtDate after comparing with stable sequence period : " + ldtDate);

                                        /*Check if new ETAs do not take up a reserveSlot -> if so then increase its minutes by landingRate*/
                                        for (LocalDateTime reserveSlot1 : reserveSlot) {
                                            if (ldtDate.equals(reserveSlot1)) {
                                                ldtDate = ldtDate.plusMinutes(landingRate);
                                            }
                                        }
                                        System.out.println("ldtDate rwy 32 :" + ldtDate);

                                        fEta[counter] = ldtDate;

                                        System.out.println("\nfEta rwy 32 manual rwy 32 ");
//                                System.out.println(Arrays.toString(fEta));
                                        fmsfunctions.removeNullInLocalDateTimeArrays(fEta);

                                        //check for same ETA
                                        sequence checkETA = new sequence();
                                        compETA = checkETA.checkForSameETA(fEta, landingRate);

                                        System.out.println("\ncompETA ");
                                        fmsfunctions.removeNullInLocalDateTimeArrays(compETA);
//                                flightsETA.put(compETA[itr], tempCallsign);
                                        flightsETA.put(fEta[counter], tempCallsign);

                                        System.out.println("flightsETA (manual rwy 32) " + flightsETA);

                                        //generate slot
                                        tSlots = slotTimes.generateSlot(tSlots, landingRate);

                                        //  sort tSlots in reverse order
                                        System.out.println("\nTime Slot\n");
                                        for (LocalDateTime tSlot : tSlots) {
                                            Arrays.sort(tSlots, Comparator.nullsLast(Comparator.reverseOrder()));
                                            if (tSlot != null) {
//                                        System.out.println(tSlot);
                                            } else {

                                            }
                                        }

                                        /* create new Map Tree with ETA as keys and null string values */
                                        timeSlot = fmsfunctions.createTimeSlot(tSlots);
                                        timeSlot.entrySet().forEach((Map.Entry<LocalDateTime, String> entry) -> {
//                                    System.out.println(entry.getKey() + " : " + entry.getValue());

                                        });

                                        //create slotList with callsigns
                                        slotList = fmsfunctions.makeSlotList(timeSlot, flightsETA);

                                        //print updated flightsETA list
                                        //         Map<LocalDateTime, String> x = new TreeMap<>();
                                        newFlightsETA = hashMapToTreeMap.convertToTreeMap(flightsETA);
                                        newFlightsETA = slotList;

                                        while (newFlightsETA.values().remove(null));

                                        System.out.println("x as flightsETA " + newFlightsETA);

                                        //extract slotted ETA in x and use to calculate FFT
                                        LocalDateTime[] tempETA = new LocalDateTime[compETA.length];
                                        LocalDateTime[] eta;
                                        newFlightsETA.entrySet().forEach((Map.Entry<LocalDateTime, String> entry) -> {
//                                     if (entry.getValue() != null) {
                                            // System.out.println(entry.getKey() + " : " + entry.getValue());
                                            for (int e = 0; e < tempETA.length; e++) {
                                                if (tempETA[e] == null) {
                                                    tempETA[e] = entry.getKey();
                                                    break;
                                                }

                                            }
//                                     }
                                        });

                                        System.out.println("\nNew ETA" + Arrays.toString(tempETA));
                                        eta = fmsfunctions.removeNullInLocalDateTimeArrays(tempETA);

                                        LocalDateTime lastETA = null;
                                        if (eta.length == 1) {
                                            lastETA = eta[0];
                                        } else if (eta.length > 1) {
                                            if (eta[1] == null) {
                                                lastETA = eta[0];
                                            } else {
                                                if (eta[eta.length - 1] != null) {
                                                    lastETA = eta[eta.length - 1];
                                                } else {
                                                    for (int l = 1; l < eta.length; l++) {
                                                        if (eta[eta.length - l] != null) {
                                                            lastETA = eta[eta.length - l];
                                                            System.out.println("eta[eta.length - l] " + eta[eta.length - l]);
                                                        }
                                                    }
                                                }

                                            }
                                        }

                                        System.out.println("lastETA in manual rwy 32 : " + lastETA);

                                        System.out.println("\n\n Flights ETA inside Try-Catch" + flightsETA);

                                        //check if star name exists in star list 
                                        System.out.println("Check if star is in star list ");
                                        boolean starExists = false;
                                        starExists = sequence.checkStarName(starExists, ac_type, stars);

                                        System.out.println("starExists = " + starExists);

                                        System.out.println("after running checkStarname method ");

                                        if (starExists == true) {
                                            /*  calculatte FFT using calculateFFT method*/
                                            System.out.println("\ncompETA");
                                            fmsfunctions.removeNullInLocalDateTimeArrays(compETA);
                                            sequence calculateFeederFix = new sequence();
//                                      for(int x=0; x < FFTimes.length; x++){
//                                        if(FFTimes[itr] == null){
                                            FFTimes[itr] = calculateFeederFix.calculateFFT(lastETA, ac_type, stars);
//                                        }
//                                        break;
//                                    }
                                            System.out.println("\nFFTimes just after calculated (manual rwy 32): ");
                                            System.out.println("callsign of FFT : " + tempCallsign);
                                            fmsfunctions.removeNullInLocalDateTimeArrays(FFTimes);

                                            // Converting LocalDateTime instance to String format using predefined formatter.
                                        } else {
                                        }

                                    }

                                    if (tempStatus.equals("FINISHED")) {
                                        fmsfunctions.removeFinishedStatus(tempCallsign);
                                    }
                                }

                                for (int x = counter; x < ManfeederFixArray.length; x++) {
                                    ManfeederFixArray[x] = ManStringFFT[x];
                                    ManadepArray[x] = adep;
                                    ManadesArray[x] = ades;
                                    Manac_typeArray[x] = ac_type;
                                    ManwturArray[x] = wtur;
                                    ManregisArray[x] = regis;
                                    ManstarArray[x] = star;
                                    Manother_infoArray[x] = other_info;
                                    ManrouteArray[x] = route;
                                    Manetd_dofArray[x] = etd_dof;
                                    ManrflArray[x] = rfl;
                                    ManspeedArray[x] = speed;
                                    ManatdArray[x] = atd;
                                    ManeobtArray[x] = eobt;
                                    MandofArray[x] = dof;
                                    ManetdArray[x] = etd;
                                    ManfruleArray[x] = frule;

                                    System.out.println("Manual ac_type\n");
                                    fmsfunctions.removeNullFromString(Manac_typeArray);
                                    System.out.println("Manual star \n");
                                    fmsfunctions.removeNullInStringArrays(ManstarArray);
                                    break;

                                }

                            }
                        }

                    }

                }
                count++;
                counter++;
            }

            System.out.println("\n\n ManfeederFixArray in getManual method : ");
//            fmsfunctions.removeNullFromString(ManfeederFixArray);
            fmsfunctions.removeNullFromString(ManStringFFT);

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
        }

        for (int f = 0; f < FFTimes.length; f++) {
            if (FFTimes[f] == null) {
            } else {
//                                                formattedDateTime2 = FFTimes[f].format(formatter);
                ManualformattedDateTime2 = FFTimes[f].truncatedTo(ChronoUnit.MINUTES).format(customFormatter);
                ManStringFFT[f] = ManualformattedDateTime2;
            }
        }
        System.out.println("ManualformattedDateTime2 inside getManual method rwy 14 " + ManualformattedDateTime2);

        System.out.println("\nString Array of FFTs");
        fmsfunctions.removeNullFromString(ManStringFFT);

        //remove null from string
//                                          manString = fmsfunctions.removeNullFromString(ManStringFFT);
        manString = fmsfunctions.removeNullFromString(ManStringFFT);
//                                        fmsfunctions.removeNullInStringArrays(ManStringFFT);

//        System.out.println("\nfEta rwy 32 in manual : " + Arrays.toString(fEta));
        //System.out.println("tempManualMapETA"+tempManualMapETA+"\n"+"manFlightMap"+manFlightMap+"\n"+"manArrETA"+Arrays.toString(manArrETA)+"\n");
        System.out.println("newFlightsETA in getManual method: " + newFlightsETA);
        tempManualMapETA = newFlightsETA;
        System.out.println("tempManualMapETA in getManual method : " + tempManualMapETA);
        return tempManualMapETA;
    }

    /*  method to get manual flights for stream folder and send to internal manual list */
    /**
     *
     * @param newETA
     * @param statusStr
     * @param sequenceTypeStr
     * @param feederFixStr
     * @param callsignStr
     * @param adepStr
     * @param adesStr
     * @param eobtStr
     * @param dofStr
     * @param ac_typeStr
     * @param sturStr
     * @param fruleStr
     * @param atdStr
     * @param speedStr
     * @param rflStr
     * @param regisStr
     * @param etdStr
     * @param etd_dofStr
     * @param routeStr
     * @param other_infoStr
     * @param STARStr
     * @throws TransformerException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static void manualFromStreamFolder(String newETA, String statusStr, String sequenceTypeStr, String feederFixStr, String callsignStr, String adepStr, String adesStr, String eobtStr, String dofStr, String ac_typeStr, String sturStr, String fruleStr, String atdStr, String speedStr, String rflStr, String regisStr, String etdStr, String etd_dofStr, String routeStr, String other_infoStr, String STARStr) throws TransformerException, ParserConfigurationException, SAXException, IOException {

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

        int counter = 0;

        try {
            //check if ETA from atm equals ETAs in sequence list by calling compareStreamManualETAtoInternalAutoETA method
            newETA = Auto.compareStreamManualETAtoInternalAutoETA(strLR, newETA);
            System.out.println("ETA AFTER CHECKING WITH STREAM FOLDER " + newETA);
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
            stringWtur[counter] = sturStr;
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

        } catch (ParseException e) {
            System.out.println(e);
        }

        for (int x = 0; x < manualETAList.length; x++) {
            manualETAList[x] = stringETA[x];
            manualStatusList[x] = stringStatus[x];
            manualSequenceTypeList[x] = stringSequenceType[x];
            manualFeederFixList[x] = stringFeederFix[x];
            manualCallsignList[x] = stringCallsign[x];
            manualAdepList[x] = stringAdep[x];
            manualAdesList[x] = stringAdes[x];
            manualEobtList[x] = stringEobt[x];
            manualDofList[x] = stringDof[x];
            manualAc_typeList[x] = stringAc_type[x];
            manualWturList[x] = stringWtur[x];
            manualFruleList[x] = stringFrule[x];
            manualAtdList[x] = intAtd[x];
            manualSpeedList[x] = intSpeed[x];
            manualRflList[x] = intRfl[x];
            manualRegisList[x] = stringRegis[x];
            manualEtdList[x] = stringEtd[x];
            manualEtd_dofList[x] = stringEtd_dof[x];
            manualRouteList[x] = stringRoute[x];
            manualOther_infoList[x] = stringOther_info[x];
            manualStarList[x] = stringSTAR[x];
        }

        /*  Get internal manual list xml file to add new ETA and flightnumber to it */
        String filePath = "C:/wamp64/www/flowcontrol/public/run/Internal_lists/manual/internal_manual_list.xml";
//        String filePath = "/var/www/html/flowcontrol/public/run/Internal_lists/manual/internal_manual_list.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();

        Element root = document.getDocumentElement();
        NodeList nList = document.getElementsByTagName("manual");
        Element eElement = null;
        boolean child = nList.item(0).hasChildNodes();

        if (child == true) {
            System.out.println("Internal manual List not empty. Compare.");

            NodeList nodeList = root.getChildNodes();

            boolean found = false;

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node childnode = nodeList.item(temp);
                if (childnode.getNodeType() == Node.ELEMENT_NODE && found == false) {
                    eElement = (Element) childnode;
                    if ("manual".equals(eElement.getElementsByTagName("sequenceType").item(0).getTextContent())) {

                    }
                    String flightContent = eElement.getElementsByTagName("callsign").item(0).getTextContent();
                    String eobtContent = eElement.getElementsByTagName("eobt").item(0).getTextContent();
                    String adesContent = eElement.getElementsByTagName("ades").item(0).getTextContent();
                    String adepContent = eElement.getElementsByTagName("adep").item(0).getTextContent();

                    if (flightContent.equals(manualCallsignList[0]) && eobtContent.equals(manualEobtList[0]) && adesContent.equals(manualAdesList[0]) && adepContent.equals(manualAdepList[0]) && manualCallsignList[0] != null) {
                        found = true;
                        System.out.println("Item found!");
                    }
                }
            }

            if (found == true) {
                if (eElement.getElementsByTagName("eta").item(0).getFirstChild() == null) {

                } else {
                    Node eta = eElement.getElementsByTagName("eta").item(0).getFirstChild();
                    eta.setNodeValue(manualETAList[0]);
                }

                if (eElement.getElementsByTagName("status").item(0).getFirstChild() == null) {

                } else {
                    Node status = eElement.getElementsByTagName("status").item(0).getFirstChild();
                    status.setNodeValue(manualStatusList[0]);
                }

                if (eElement.getElementsByTagName("feederFix").item(0).getFirstChild() == null) {

                } else {
                    Node feederFix = eElement.getElementsByTagName("feederFix").item(0).getFirstChild();
                    feederFix.setNodeValue(manualFeederFixList[0]);

                }

                if (eElement.getElementsByTagName("sequenceType").item(0).getFirstChild() == null) {

                } else {
                    Node sequence = eElement.getElementsByTagName("sequenceType").item(0).getFirstChild();
                    sequence.setNodeValue(manualSequenceTypeList[0]);
                }

                if (eElement.getElementsByTagName("callsign").item(0).getFirstChild() == null) {

                } else {
                    Node callsign = eElement.getElementsByTagName("callsign").item(0).getFirstChild();
                    callsign.setNodeValue(manualCallsignList[0]);
                }

                if (eElement.getElementsByTagName("adep").item(0).getFirstChild() == null) {

                } else {
                    Node adep = eElement.getElementsByTagName("adep").item(0).getFirstChild();
                    adep.setNodeValue(manualAdepList[0]);
                }

                if (eElement.getElementsByTagName("ades").item(0).getFirstChild() == null) {

                } else {
                    Node ades = eElement.getElementsByTagName("ades").item(0).getFirstChild();
                    ades.setNodeValue(manualAdesList[0]);
                }

                if (eElement.getElementsByTagName("eobt").item(0).getFirstChild() == null) {

                } else {
                    Node eobt = eElement.getElementsByTagName("eobt").item(0).getFirstChild();
                    eobt.setNodeValue(manualEobtList[0]);
                }

                if (eElement.getElementsByTagName("dof").item(0).getFirstChild() == null) {

                } else {
                    Node dof = eElement.getElementsByTagName("dof").item(0).getFirstChild();
                    dof.setNodeValue(manualDofList[0]);
                }

                if (eElement.getElementsByTagName("ac_type").item(0).getFirstChild() == null) {

                } else {
                    Node ac_type = eElement.getElementsByTagName("ac_type").item(0).getFirstChild();
                    ac_type.setNodeValue(manualAc_typeList[0]);
                }

                if (eElement.getElementsByTagName("wtur").item(0).getFirstChild() == null) {

                } else {
                    Node wtur = eElement.getElementsByTagName("wtur").item(0).getFirstChild();
                    wtur.setNodeValue(manualWturList[0]);
                }

                if (eElement.getElementsByTagName("frule").item(0).getFirstChild() == null) {

                } else {
                    Node frule = eElement.getElementsByTagName("frule").item(0).getFirstChild();
                    frule.setNodeValue(manualFruleList[0]);
                }

                if (eElement.getElementsByTagName("atd").item(0).getFirstChild() == null) {

                } else {
                    Node atd = eElement.getElementsByTagName("atd").item(0).getFirstChild();
                    atd.setNodeValue(manualAtdList[0]);
                }

                if (eElement.getElementsByTagName("speed").item(0).getFirstChild() == null) {

                } else {
                    Node speed = eElement.getElementsByTagName("speed").item(0).getFirstChild();
                    speed.setNodeValue(manualSpeedList[0]);
                }

                if (eElement.getElementsByTagName("rfl").item(0).getFirstChild() == null) {

                } else {
                    Node rfl = eElement.getElementsByTagName("rfl").item(0).getFirstChild();
                    rfl.setNodeValue(manualRflList[0]);
                }

                if (eElement.getElementsByTagName("regis").item(0).getFirstChild() == null) {

                } else {
                    Node regis = eElement.getElementsByTagName("regis").item(0).getFirstChild();
                    regis.setNodeValue(manualRegisList[0]);
                }

                if (eElement.getElementsByTagName("etd").item(0).getFirstChild() == null) {

                } else {
                    Node etd = eElement.getElementsByTagName("etd").item(0).getFirstChild();
                    etd.setNodeValue(manualEtdList[0]);
                }

                if (eElement.getElementsByTagName("etd_dof").item(0).getFirstChild() == null) {

                } else {
                    Node etd_dof = eElement.getElementsByTagName("etd_dof").item(0).getFirstChild();
                    etd_dof.setNodeValue(manualEtd_dofList[0]);
                }

                if (eElement.getElementsByTagName("route").item(0).getFirstChild() == null) {

                } else {
                    Node route = eElement.getElementsByTagName("route").item(0).getFirstChild();
                    route.setNodeValue(manualRouteList[0]);
                }

                if (eElement.getElementsByTagName("other_info").item(0).getFirstChild() == null) {

                } else {
                    Node other_info = eElement.getElementsByTagName("other_info").item(0).getFirstChild();
                    other_info.setNodeValue(manualOther_infoList[0]);
                }

                if (eElement.getElementsByTagName("star").item(0).getFirstChild() == null) {

                } else {
                    Node star = eElement.getElementsByTagName("star").item(0).getFirstChild();
                    star.setNodeValue(manualStarList[0]);
                }

            }

            if (found == false) {
                Element newmanuals = document.createElement("manual");

                Element eta1 = document.createElement("eta");
                eta1.appendChild(document.createTextNode(manualETAList[0]));
                newmanuals.appendChild(eta1);

                Element sequenceType = document.createElement("sequenceType");
                sequenceType.appendChild(document.createTextNode(manualSequenceTypeList[0]));
                newmanuals.appendChild(sequenceType);

                Element status1 = document.createElement("status");
                status1.appendChild(document.createTextNode(manualStatusList[0]));
                newmanuals.appendChild(status1);

                Element feederFix1 = document.createElement("feederFix");
                feederFix1.appendChild(document.createTextNode(manualFeederFixList[0]));
                newmanuals.appendChild(feederFix1);

                Element callsign1 = document.createElement("callsign");
                callsign1.appendChild(document.createTextNode(manualCallsignList[0]));
                newmanuals.appendChild(callsign1);

                Element adep1 = document.createElement("adep");
                adep1.appendChild(document.createTextNode(manualAdepList[0]));
                newmanuals.appendChild(adep1);

                Element ades1 = document.createElement("ades");
                ades1.appendChild(document.createTextNode(manualAdesList[0]));
                newmanuals.appendChild(ades1);

                Element eobt1 = document.createElement("eobt");
                eobt1.appendChild(document.createTextNode(manualEobtList[0]));
                newmanuals.appendChild(eobt1);

                Element dof1 = document.createElement("dof");
                dof1.appendChild(document.createTextNode(manualDofList[0]));
                newmanuals.appendChild(dof1);

                Element ac_type1 = document.createElement("ac_type");
                ac_type1.appendChild(document.createTextNode(manualAc_typeList[0]));
                newmanuals.appendChild(ac_type1);

                Element wtur1 = document.createElement("wtur");
                wtur1.appendChild(document.createTextNode(manualWturList[0]));
                newmanuals.appendChild(wtur1);

                Element frule1 = document.createElement("frule");
                frule1.appendChild(document.createTextNode(manualFruleList[0]));
                newmanuals.appendChild(frule1);

                Element atd1 = document.createElement("atd");
                atd1.appendChild(document.createTextNode(manualAtdList[0]));
                newmanuals.appendChild(atd1);

                Element speed1 = document.createElement("speed");
                speed1.appendChild(document.createTextNode(manualSpeedList[0]));
                newmanuals.appendChild(speed1);

                Element rfl1 = document.createElement("rfl");
                rfl1.appendChild(document.createTextNode(manualRflList[0]));
                newmanuals.appendChild(rfl1);

                Element regis1 = document.createElement("regis");
                regis1.appendChild(document.createTextNode(manualRegisList[0]));
                newmanuals.appendChild(regis1);

                Element etd1 = document.createElement("etd");
                etd1.appendChild(document.createTextNode(manualEtdList[0]));
                newmanuals.appendChild(etd1);

                Element etd_dof1 = document.createElement("etd_dof");
                etd_dof1.appendChild(document.createTextNode(manualEtd_dofList[0]));
                newmanuals.appendChild(etd_dof1);

                Element route1 = document.createElement("route");
                route1.appendChild(document.createTextNode(manualRouteList[0]));
                newmanuals.appendChild(route1);

                Element other_info1 = document.createElement("other_info");
                other_info1.appendChild(document.createTextNode(manualOther_infoList[0]));
                newmanuals.appendChild(other_info1);

                Element star1 = document.createElement("star");
                star1.appendChild(document.createTextNode(manualStarList[0]));
                newmanuals.appendChild(star1);

                root.appendChild(newmanuals);
            }

        } else {
            System.out.println("Internal manual List is empty. Append.");
            for (int x = 0; x < stringETA.length; x++) {
                if (manualETAList[x] != null && manualCallsignList[x] != null && manualStatusList[x] != null) {
                    Element newmanuals = document.createElement("manual");

                    Element eta = document.createElement("eta");
                    eta.appendChild(document.createTextNode(manualETAList[x]));
                    newmanuals.appendChild(eta);

                    Element sequenceType = document.createElement("sequenceType");
                    sequenceType.appendChild(document.createTextNode(manualSequenceTypeList[x]));
                    newmanuals.appendChild(sequenceType);

                    Element status = document.createElement("status");
                    status.appendChild(document.createTextNode(manualStatusList[x]));
                    newmanuals.appendChild(status);

                    Element feederFix = document.createElement("feederFix");
                    feederFix.appendChild(document.createTextNode(manualFeederFixList[x]));
                    newmanuals.appendChild(feederFix);

                    Element callsign = document.createElement("callsign");
                    callsign.appendChild(document.createTextNode(manualCallsignList[x]));
                    newmanuals.appendChild(callsign);

                    Element adep = document.createElement("adep");
                    adep.appendChild(document.createTextNode(manualAdepList[x]));
                    newmanuals.appendChild(adep);

                    Element ades = document.createElement("ades");
                    ades.appendChild(document.createTextNode(manualAdesList[x]));
                    newmanuals.appendChild(ades);

                    Element eobt = document.createElement("eobt");
                    eobt.appendChild(document.createTextNode(manualEobtList[x]));
                    newmanuals.appendChild(eobt);

                    Element dof = document.createElement("dof");
                    dof.appendChild(document.createTextNode(manualDofList[x]));
                    newmanuals.appendChild(dof);

                    Element ac_type = document.createElement("ac_type");
                    ac_type.appendChild(document.createTextNode(manualAc_typeList[x]));
                    newmanuals.appendChild(ac_type);

                    Element wtur = document.createElement("wtur");
                    wtur.appendChild(document.createTextNode(manualWturList[x]));
                    newmanuals.appendChild(wtur);

                    Element frule = document.createElement("frule");
                    frule.appendChild(document.createTextNode(manualFruleList[x]));
                    newmanuals.appendChild(frule);

                    Element atd = document.createElement("atd");
                    atd.appendChild(document.createTextNode(manualAtdList[x]));
                    newmanuals.appendChild(atd);

                    Element speed = document.createElement("speed");
                    speed.appendChild(document.createTextNode(manualSpeedList[x]));
                    newmanuals.appendChild(speed);

                    Element rfl = document.createElement("rfl");
                    rfl.appendChild(document.createTextNode(manualRflList[x]));
                    newmanuals.appendChild(rfl);

                    Element regis = document.createElement("regis");
                    regis.appendChild(document.createTextNode(manualRegisList[x]));
                    newmanuals.appendChild(regis);

                    Element etd = document.createElement("etd");
                    etd.appendChild(document.createTextNode(manualEtdList[x]));
                    newmanuals.appendChild(etd);

                    Element etd_dof = document.createElement("etd_dof");
                    etd_dof.appendChild(document.createTextNode(manualEtd_dofList[x]));
                    newmanuals.appendChild(etd_dof);

                    Element route = document.createElement("route");
                    route.appendChild(document.createTextNode(manualRouteList[x]));
                    newmanuals.appendChild(route);

                    Element other_info = document.createElement("other_info");
                    other_info.appendChild(document.createTextNode(manualOther_infoList[x]));
                    newmanuals.appendChild(other_info);

                    Element star = document.createElement("star");
                    star.appendChild(document.createTextNode(manualStarList[x]));
                    newmanuals.appendChild(star);

                    root.appendChild(newmanuals);
                }
            }
        }
        DOMSource source = new DOMSource(document);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File("C:/wamp64/www/flowcontrol/public/run/Internal_lists/manual/xsltfile.xml")));
//        Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File("/var/www/html/flowcontrol/public/run/Internal_lists/manual/xsltfile.xml")));

//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StreamResult result = new StreamResult("C:/wamp64/www/flowcontrol/public/run/Internal_lists/manual/internal_manual_list.xml");
//        StreamResult result = new StreamResult("/var/www/html/flowcontrol/public/run/Internal_lists/manual/internal_manual_list.xml");

        transformer.transform(source, result);
    }

}
