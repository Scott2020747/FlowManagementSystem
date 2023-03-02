/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flowmanagementsystem;

import java.util.Comparator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.w3c.dom.NodeList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author sbilau
 */
public class mainFlowManagementSystem {

    static LocalDateTime[] masterReserveSlot = new LocalDateTime[10000];
    static LocalDateTime[] reserveSlot = new LocalDateTime[10000];
    static String[] manualETAList = new String[10000];
    static String[] manualFeederFixList = new String[10000];
    static String[] manualCallsignList = new String[10000];
    static String[] manualAdepList = new String[10000];
    static String[] manualAdesList = new String[10000];
    static String[] manualEobtList = new String[10000];
    static String[] manualDofList = new String[10000];
    static String[] manualAc_typeList = new String[10000];
    static String[] manualWturList = new String[10000];
    static String[] manualFruleList = new String[10000];
    static String[] manualAtdList = new String[10000];
    static String[] manualSpeedList = new String[10000];
    static String[] manualRflList = new String[10000];
    static String[] manualRegisList = new String[10000];
    static String[] manualEtdList = new String[10000];
    static String[] manualEtd_dofList = new String[10000];
    static String[] manualRouteList = new String[10000];
    static String[] manualOther_infoList = new String[10000];
    static String[] manualStatusList = new String[10000];
    static String[] manualSequenceTypeList = new String[10000];
    static String[] manualStarList = new String[10000];
    static String[] autoETAList = new String[10000];
    static String[] autoFlightList = new String[10000];
    static String[] autoStatusList = new String[10000];
    static String[] autoSequenceTypeList = new String[10000];
    static String[] autoStarnameList = new String[10000];
    static String[] autoAtypeList = new String[10000];
    static String[] autoAcidList = new String[10000];
    static String[] autoFeederFixList = new String[10000];
    static String[] reserveETAList = new String[10000];
    static String[] reserveFlightList = new String[10000];
    static String[] reserveStatusList = new String[10000];
    static String[] reserveSequenceTypeList = new String[10000];
    static String[] reserveStarnameList = new String[10000];
    static String[] reserveAtypeList = new String[10000];
    static String[] reserveAcidList = new String[10000];
    static String[] reserveFeederFixList = new String[10000];
    static String[] autoCallsign = new String[10000];
    static String[] autoAdep = new String[10000];
    static String[] autoAdes = new String[10000];
    static String[] autoEobt = new String[10000];
    static String[] autoDof = new String[10000];
    static String[] autoAc_type = new String[10000];
    static String[] autoWtur = new String[10000];
    static String[] autoFrule = new String[10000];
    static String[] autoAtd = new String[10000];
    static String[] autoSpeed = new String[10000];
    static String[] autoRfl = new String[10000];
    static String[] autoRegis = new String[10000];
    static String[] autoEtd = new String[10000];
    static String[] autoEtd_dof = new String[10000];
    static String[] autoRoute = new String[10000];
    static String[] autoOther_info = new String[10000];
    static String[] autoSTAR = new String[10000];
    static String[] feederFixArray = new String[10000];
    static String[] adepArray = new String[10000];
    static String[] adesArray = new String[10000];
    static String[] ac_typeArray = new String[10000];
    static String[] wturArray = new String[10000];
    static String[] regisArray = new String[10000];
    static String[] sidArray = new String[10000];
    static String[] starArray = new String[10000];
    static String[] other_infoArray = new String[10000];
    static String[] routeArray = new String[10000];
    static String[] etd_dofArray = new String[10000];
    static String[] etdArray = new String[10000];
    static String[] rflArray = new String[10000];
    static String[] speedArray = new String[10000];
    static String[] atdArray = new String[10000];
    static String[] fruleArray = new String[10000];
    static String[] eobtArray = new String[10000];
    static String[] callsignArray = new String[10000];
    static String[] dofArray = new String[10000];
    static String[] ManfeederFixArray = new String[10000];
    static String[] ManadepArray = new String[10000];
    static String[] ManadesArray = new String[10000];
    static String[] Manac_typeArray = new String[10000];
    static String[] ManwturArray = new String[10000];
    static String[] ManregisArray = new String[10000];
    static String[] MansidArray = new String[10000];
    static String[] ManstarArray = new String[10000];
    static String[] Manother_infoArray = new String[10000];
    static String[] ManrouteArray = new String[10000];
    static String[] Manetd_dofArray = new String[10000];
    static String[] ManetdArray = new String[10000];
    static String[] ManrflArray = new String[10000];
    static String[] ManspeedArray = new String[10000];
    static String[] ManatdArray = new String[10000];
    static String[] ManfruleArray = new String[10000];
    static String[] ManeobtArray = new String[10000];
    static String[] MancallsignArray = new String[10000];
    static String[] MandofArray = new String[10000];

    static String strRWY = null;
    static String strLR = null;
    static String ssp = null;

    static String[] stringFFT = new String[10000];
    static String[] ManStringFFT = new String[10000];
    static Map<LocalDateTime, String> tempManualMapETA = new TreeMap<>();

    static String ManualformattedDateTime2 = null;
    static String[] manString = new String[10000];

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws Exception {

        //opening the localhost url
        FMSGUI gui = new FMSGUI();
        gui.setVisible(true);
        
      


        /*  Getting reserved flight list into the reserveSlot */
        masterReserveSlot = InternalMasterLists.reserveSlotList(reserveSlot, masterReserveSlot);
        int landingRate = 0;
        int stableSequencePeriod = 0;
        try {
            //creating a constructor of file class and parsing an XML file  
            File file = new File("C:/wamp64/www/flowcontrol/public/run/configs/config.xml");
//            File file = new File("/var/www/html/flowcontrol/public/run/configs/config.xml");

            //an instance of factory that gives a document builder  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file  
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("input");
            // nodeList is not iterable, so we are using for loop  
            int count = 0;
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    strRWY = eElement.getElementsByTagName("rwy").item(0).getTextContent();
                    strLR = eElement.getElementsByTagName("landingrate").item(0).getTextContent();
                    ssp = eElement.getElementsByTagName("ssperiod").item(0).getTextContent();
                    landingRate = Integer.parseInt(strLR);
                    stableSequencePeriod = Integer.parseInt(ssp);
                }
            }
        } catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
        }
        /*  USING SWITCH CASE STATEMENTS TO EXECUTE CONTROLLER INPUT VALUES   */
//        switch (runway) {
//            case 14:
        Calendar cal = Calendar.getInstance();
        /*  build internal auto list flights */
        Auto buildInternalAutolist = new Auto();
        buildInternalAutolist.buildInternalAutoList(autoETAList, autoStatusList, autoSequenceTypeList, autoStarnameList, autoFeederFixList, autoCallsign, autoAdep, autoAdes, autoEobt, autoDof, autoAc_type, autoWtur, autoFrule, autoAtd, autoSpeed, autoRfl, autoRegis, autoEtd, autoEtd_dof, autoRoute, autoOther_info, autoSTAR);
        System.out.println("auto adep afterbuildInternalAutoList : " + Arrays.toString(autoAdep));

        /*  getting auto flight details from internal_auto_list.xml file by calling getAtuo() method */
        HashMap<String, String> autoFlightMap = new HashMap<>();
        HashMap<LocalDateTime, String> tempAutoMapETA = new HashMap<>();
        LocalDateTime[] autoArrETA = new LocalDateTime[10000];
        Auto getAutoFlightList = new Auto();
        getAutoFlightList.getAuto(autoFlightMap, tempAutoMapETA, autoArrETA, landingRate, feederFixArray, adepArray, adesArray, ac_typeArray, wturArray, regisArray, sidArray, starArray, other_infoArray, routeArray, etd_dofArray, etdArray, rflArray, speedArray, atdArray, fruleArray, eobtArray, callsignArray, dofArray);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%% adep value after getAuto method : " + Arrays.toString(adepArray));
        /*  build internal reserve list */
        Reserve buildInternalReserveList = new Reserve();
        buildInternalReserveList.buildInternalreserveList(reserveETAList, reserveFlightList, reserveStatusList, reserveSequenceTypeList, reserveStarnameList, reserveFeederFixList, reserveAtypeList, reserveAcidList);

        System.out.println("building internal auto list ");
        /**
         * ************* calling XML file statuses to get flight status and
         * flight name from it ********************************************
         */
//                    System.out.println("flight statuses obtained from XML and stored in hashtable");
        HashMap<String, String> flightStatus = new HashMap<>();
        HashMap<LocalDateTime, String> flightsETA = new HashMap<>();
        LocalDateTime[] fEta = new LocalDateTime[10000];
        LocalDateTime[] FFTimes = new LocalDateTime[10000];
        LocalDateTime[] compETA = new LocalDateTime[10000];
        /* create new Map Tree with ETA as keys and null string values */
        Map<LocalDateTime, String> timeSlot = new TreeMap<>();

        Map<LocalDateTime, String> slotList = new TreeMap<>();

        LocalDateTime[] tSlots = new LocalDateTime[720];

        Map<LocalDateTime, String> NewAutoETA = new TreeMap<>();

        sequence slotTimes;
        slotTimes = new sequence();

        fmsfunctions hashMapToTreeMap = new fmsfunctions();

        String stats = "CONTROLLED";

        String formattedDateTime2 = null;
//        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        // Creating Custom formatter
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        int counter = 0;

        try {
            //creating a constructor of file class and parsing an XML file  
            File file = new File("C:/wamp64/www/flowcontrol/public/run/Internal_lists/auto/internal_auto_list.xml");
//                    File file = new File("/var/www/html/flowcontrol/public/run/Internal_lists/auto/internal_auto_list.xml");

            //an instance of factory that gives a document builder  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file  
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("flight");

            for (int itr = 1; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if (eElement.hasChildNodes() == true && eElement.getElementsByTagName("callsign").getLength() > 0) {

//                        System.out.println(" XX \n");
                        System.out.println("Call sign: " + eElement.getElementsByTagName("callsign").item(0).getTextContent());
                        System.out.println("status: " + eElement.getElementsByTagName("status").item(0).getTextContent());
                        System.out.println("ETA: " + eElement.getElementsByTagName("eta").item(0).getTextContent());
                        System.out.println("star: " + eElement.getElementsByTagName("star").item(0).getTextContent());
                        System.out.println("ac_type: " + eElement.getElementsByTagName("ac_type").item(0).getTextContent());
                        System.out.println("frule: " + eElement.getElementsByTagName("frule").item(0).getTextContent());
                        System.out.println("feederFix: " + eElement.getElementsByTagName("feederFix").item(0).getTextContent());
                        System.out.println("adep : " + eElement.getElementsByTagName("adep").item(0).getTextContent());

                        String tempCallsign = eElement.getElementsByTagName("callsign").item(0).getTextContent();
                        String tempStatus = eElement.getElementsByTagName("status").item(0).getTextContent();
                        String fETA = eElement.getElementsByTagName("eta").item(0).getTextContent();
                        String ac_type = eElement.getElementsByTagName("ac_type").item(0).getTextContent();
                        String stars = eElement.getElementsByTagName("star").item(0).getTextContent();
//                        String feederFix = eElement.getElementsByTagName("feederFix").item(0).getTextContent();

                        String[] status_string = new String[nodeList.getLength()];
                        status_string[counter] = tempStatus;

                        //check if stars = "..." --> if true then do nothing, else process lines of codes
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
                                System.out.println("ldtDate 14: " + ldtDate);

                                long min = ChronoUnit.MINUTES.between(currentTime, ldtDate);

                                System.out.println("minute diff between currTime and ldtDate rwy 14 : " + min);
                                System.out.println("Difference between current time and ETA ( rwy 14 ) : " + min + " minutes");

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
                                System.out.println("ldtDate rwy 14 :" + ldtDate);

                                fEta[counter] = ldtDate;

                                System.out.println("\n\n fEta auto rwy 14 : ");
                                fmsfunctions.removeNullInLocalDateTimeArrays(fEta);

                                //check for same ETA
                                sequence checkETA = new sequence();
                                compETA = checkETA.checkForSameETA(fEta, landingRate);

                                System.out.println("\ncompETA 14\n");
                                fmsfunctions.removeNullInLocalDateTimeArrays(compETA);

//                                flightsETA.put(compETA[itr], tempCallsign);
                                flightsETA.put(fEta[counter], tempCallsign);

                                System.out.println("flightsETA 14 --> " + flightsETA);

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
                                System.out.println("NewAutoETA in auto rwy 14 before slotLists: " + NewAutoETA);
                                slotList = fmsfunctions.makeSlotList(timeSlot, flightsETA);

                                //print updated flightsETA list
                                NewAutoETA = hashMapToTreeMap.convertToTreeMap(flightsETA);
                                System.out.println("NewAutoETA (rwy 14) " + NewAutoETA);
                                NewAutoETA = slotList;
//                                 System.out.println("NewAutoETA (slotList) "+NewAutoETA);
                                while (NewAutoETA.values().remove(null));
                                System.out.println("x as flightsETA (auto rwy 14) " + NewAutoETA);

                                //extract slotted ETA in x and use to calculate FFT
                                LocalDateTime[] tempETA = new LocalDateTime[compETA.length];
                                LocalDateTime[] eta;
                                NewAutoETA.entrySet().forEach((Map.Entry<LocalDateTime, String> entry) -> {
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

                                System.out.println("eta (auto rwy 14) : " + Arrays.toString(eta));
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

                                System.out.println("lastETA auto rwy 14: " + lastETA);

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

                                System.out.println("\nfEta rwy 32 auto rwy 32 ");
//                                System.out.println(Arrays.toString(fEta));
                                fmsfunctions.removeNullInLocalDateTimeArrays(fEta);

                                //check for same ETA
                                sequence checkETA = new sequence();
                                compETA = checkETA.checkForSameETA(fEta, landingRate);

                                System.out.println("\ncompETA ");
                                fmsfunctions.removeNullInLocalDateTimeArrays(compETA);
//                                flightsETA.put(compETA[itr], tempCallsign);
                                flightsETA.put(fEta[counter], tempCallsign);

                                System.out.println("flightsETA (auto rwy 14) " + flightsETA);

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
                                NewAutoETA = hashMapToTreeMap.convertToTreeMap(flightsETA);
                                NewAutoETA = slotList;

                                while (NewAutoETA.values().remove(null));

                                System.out.println("x as flightsETA " + NewAutoETA);

                                //extract slotted ETA in x and use to calculate FFT
                                LocalDateTime[] tempETA = new LocalDateTime[compETA.length];
                                LocalDateTime[] eta;
                                NewAutoETA.entrySet().forEach((Map.Entry<LocalDateTime, String> entry) -> {
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

                                System.out.println("lastETA in auto rwy 32 : " + lastETA);

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
                                    System.out.println("\nFFTimes just after calculated (auto rwy 32): " + Arrays.toString(FFTimes));
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

                    } else if (eElement.hasChildNodes() == false) {
                        System.out.println("internal auto list is empty...");
                    }
                }
                counter++;
            }

        } catch (IOException | ParseException | ParserConfigurationException | DOMException | SAXException e) {
        }
        // Printing LocalDateTime in String format using predefined formatter
        System.out.println("\nPrinting flight status details from hashmap table");
        System.out.println("flightStatus " + flightStatus);
        System.out.println("fligtsETA " + flightsETA);
        System.out.println("\nFFTimes array");
        fmsfunctions.removeNullInLocalDateTimeArrays(FFTimes);
        fmsfunctions.removeNullInLocalDateTimeArrays(FFTimes);

        for (int f = 0; f < FFTimes.length; f++) {
            if (FFTimes[f] == null) {
            } else {
                formattedDateTime2 = FFTimes[f].truncatedTo(ChronoUnit.MINUTES).format(customFormatter);
                System.out.println("formattedDateTime2 without seconds : " + formattedDateTime2);
                stringFFT[f] = formattedDateTime2;
            }
        }
        System.out.println("FFT DateTime in String Form Predefined Formatter : " + formattedDateTime2);

        System.out.println("\nString Array of FFTs");
        fmsfunctions.removeNullFromString(stringFFT);

        //remove null from string
        String[] NewString = fmsfunctions.removeNullFromString(stringFFT);

        //arrange stringArray in reverse order
//        fmsfunctions.reverseArray(NewString);
//        System.out.println("\nNew string Array\n "+Arrays.toString(NewString));

        /*  Building internal list of manual flights */
//        Manual createInternalManualList = new Manual();
//        createInternalManualList.buildInternalManualList(manualETAList, manualCallsignList, manualSequenceTypeList, manualFeederFixList, manualAdepList, manualAdesList, manualEobtList, manualDofList, manualAc_typeList, manualWturList, manualFruleList, manualAtdList, manualSpeedList, manualRflList, manualRegisList, manualEtdList, manualEtd_dofList, manualRouteList, manualOther_infoList, manualStatusList, manualStarList);
        HashMap<String, String> manFlightMap = new HashMap<>();

        LocalDateTime[] manArrETA = new LocalDateTime[10000];
        Manual getmanualCallsignList = new Manual();

        System.out.println("tempManualMapETA value before going into getManual method : " + tempManualMapETA);

        tempManualMapETA = getmanualCallsignList.getManual(manFlightMap, tempManualMapETA, manArrETA, landingRate, ManfeederFixArray, ManadepArray, ManadesArray, Manac_typeArray, ManwturArray, ManregisArray, MansidArray, ManstarArray, Manother_infoArray, ManrouteArray, Manetd_dofArray, ManetdArray, ManrflArray, ManspeedArray, ManatdArray, ManfruleArray, ManeobtArray, MancallsignArray, MandofArray);

        /*  copy tempAutoMapETA to manualETA if no existing callsign   */
        System.out.println("tempAutoMapETA value before going into createManualETA method : " + tempAutoMapETA);
        System.out.println("manFlightMap value before going into createManualETA method : " + manFlightMap);
        System.out.println("tempManualMapETA value before going into createManualETA method : " + tempManualMapETA);

        Map<LocalDateTime, String> manualETA = new TreeMap<>();
        Manual createManETA = new Manual();

        System.out.println("tempAutoMapETA before createManualETA method: " + tempAutoMapETA);
        System.out.println("tempManualMapETA before going into createManualETA method: " + tempManualMapETA);

        manualETA = createManETA.createManualETA(tempAutoMapETA, manFlightMap, tempManualMapETA);
        System.out.println("manualETA value from createManualETA method : " + manualETA);
        System.out.println("tempAutoMapETA after createManualETA method: " + tempAutoMapETA);

        /* construct a new TreeMap from HashMap for auto sequence slot */
        Map<LocalDateTime, String> treeMapSlots = new TreeMap<>();

        if (flightsETA.containsKey(null)) {
            System.out.println("flightsETA is null");
        } else {
            treeMapSlots = hashMapToTreeMap.convertToTreeMap(flightsETA);
            System.out.println("treeMapSlots " + treeMapSlots);
        }
        /*  construct a new TreeMap for manual sequence slot*/
        Map<LocalDateTime, String> ManMapSlots = new TreeMap<>();
        fmsfunctions MapToTreeMap2 = new fmsfunctions();

        ManMapSlots = MapToTreeMap2.convertToTreeMap(manualETA);
        /*  Create slot display with ETA and callsign  */
        slot createSlotList = new slot();

        // Check if this key is the required key
        if (flightsETA.containsKey(null)) {
            System.out.println("flights is NULL ");
        } else {
            createSlotList.createSlot(flightsETA, treeMapSlots);
            System.out.println("treeMapSlots" + treeMapSlots);
        }

        /*  printout auto and manual sequence   */
        Print printManualAndAutoSequence = new Print();
        printManualAndAutoSequence.printSequence(treeMapSlots, ManMapSlots);

        /*  pass flightStatus flightname to etacallsignHashMap as values     */
        FileTime fileTimeStamp1 = null;
        Path filePath = Paths.get("C:/wamp64/www/flowcontrol/public/run/configs/star.xml");
//            Path filePath = Paths.get("/var/www/html/flowcontrol/public/run/configs/star.xml");
//                Path filePath = star;

        fmsfunctions getTimeStamp = new fmsfunctions();
        fileTimeStamp1 = getTimeStamp.xmlTimeStamp(filePath);

        //check if xml file has changed
        Path filePath2 = Paths.get("C:/wamp64/www/flowcontrol/public/run/configs/star.xml");
//          Path filePath2 = Paths.get("/var/www/html/flowcontrol/public/run/configs/star.xml");
//                Path filePath2 = star;
        fmsfunctions compareXML = new fmsfunctions();
        compareXML.xmlWatcher(filePath2, fileTimeStamp1);
        /*  call sortbykey method */

 /*   Produce xml output file  */
        System.out.println("treeMapSlots values" + treeMapSlots);
        createSequence generateSequenceList = new createSequence();

        System.out.println("**** stringFFT content before building sequence list **** " + Arrays.toString(NewString));
        System.out.println(" flightsETA before going to sequence " + NewAutoETA);
        System.out.println("manString in just before going into sequenceInXMLform method :" + Arrays.toString(manString));
//        fmsfunctions.removeNullFromString(ManfeederFixArray);
//        System.out.println("\nManStringFFT in just before going into sequenceInXMLform method :");
//        fmsfunctions.removeNullFromString(ManStringFFT);

        System.out.println("ManualformattedDateTime2 : " + ManualformattedDateTime2);

        System.out.println("manualETA before going into sequenceInXMLform : " + manualETA);

        System.out.println("adep array before going into sequence list : " + Arrays.toString(adepArray));

        generateSequenceList.sequenceInXMLform(NewAutoETA, manualETA, adepArray, adesArray, NewString, ac_typeArray, wturArray, regisArray, starArray, other_infoArray, routeArray, etd_dofArray, etdArray, rflArray, speedArray, atdArray, fruleArray, dofArray, eobtArray, ManadepArray, ManadesArray, manString, Manac_typeArray, ManwturArray, ManregisArray, ManstarArray, Manother_infoArray, ManrouteArray, Manetd_dofArray, ManetdArray, ManrflArray, ManspeedArray, ManatdArray, ManfruleArray, MandofArray, ManeobtArray);
        System.out.println("\nWaiting for atm files...");

        File[] xmlFiles = flights.getXMLFiles(new File("C:/wamp64/www/flowcontrol/public/run/stream"));
//        File[] xmlFiles = flights.getXMLFiles(new File("/var/www/html/flowcontrol/public/run/stream"));
        System.out.println(Arrays.toString(xmlFiles));

        flights.deleteXMLFiles(new File("C:/wamp64/www/flowcontrol/public/run/stream"));
//        flights.deleteXMLFiles(new File("/var/www/html/flowcontrol/public/run/stream"));

        Path dir = Paths.get("C:/wamp64/www/flowcontrol/public/run");
//        Path dir = Paths.get("/var/www/html/flowcontrol/public/run");
        new Java8WatchServiceExample(dir).processEvents();
        
    }
}
