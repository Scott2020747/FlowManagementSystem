/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flowmanagementsystem;

import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.stringFFT;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 *
 * @author sbilau
 */
public class sequence {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Calendar cal = Calendar.getInstance();

    /**
     *
     * @param spec
     * @return
     */
    public static LocalDateTime fromString(String spec) {
        // formatter.parse( spec );
        LocalDateTime datetime = LocalDateTime.parse(spec, formatter);
        return datetime;
    }

    /* Function to convert String to LocalDateTime*/
    public static LocalDateTime stringToLocalDateTime(String string) {

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(string, formatter2);
        //System.out.println("Test time: "+dateTime);
        return dateTime;
    }

    /**
     *
     * @param etaTime
     * @param starInt
     * @return
     */
    // public static Date calculateFeederFixTime(Date d1, int starInt){
    public static LocalDateTime calculateFeederFixTime(LocalDateTime etaTime, int starInt) {

        etaTime = etaTime.minusMinutes(starInt);
        return etaTime;
    }

    /**
     *
     * @param FFT
     * @param landRate
     * @return
     */
    public LocalDateTime[] checkForSameFFT(LocalDateTime[] FFT, long landRate) {

        LocalDateTime temp = null;
        LocalDateTime newFFT;

        for (int i = 0; i < FFT.length; i++) {
            for (int k = i + 1; k < FFT.length; k++) {
                if (FFT[k] == null) {

                } else {
                    System.out.println("ETA [0] :" + FFT[i]);
                    System.out.println("ETA [1] :" + FFT[k]);

                    if (FFT[i] != null && FFT[k] != null && FFT[i].equals(FFT[k])) {
                        temp = FFT[k];
                        newFFT = temp.plusMinutes(landRate);
                        FFT[k] = newFFT;
                        long diff = ChronoUnit.MINUTES.between(FFT[i], FFT[k]);
                        System.out.println("Diff in ETAs :" + diff);
                    }
                }

            } //break;
        }
        return FFT;

    }

    //check for same ETA
    public LocalDateTime[] checkForSameETA(LocalDateTime[] ETA, long landRate) {

        System.out.println("fEta inside checkForSameETA method :" + Arrays.toString(ETA));

        LocalDateTime temp = null;
        LocalDateTime newETA;
        long diff = 0;
        long diff2 = 0;
        long diff3=0;

        for (int i = 0; i < ETA.length; i++) {
            for (int k = i + 1; k < ETA.length; k++) {
                if (ETA[k] == null) {

                } else {

                    if (ETA[i] != null && ETA[k] != null && ETA[i].equals(ETA[k])) {
                        temp = ETA[k];
                        newETA = temp.plusMinutes(landRate);
                        ETA[k] = newETA;

                    }

                    if ((ETA[i] != null && ETA[k] != null) && !ETA[i].equals(ETA[k])) {
                        diff = ChronoUnit.MINUTES.between(ETA[i], ETA[k]);

                        //convert long diff to int
                        int diffInt = (int) diff;
                        if (diffInt < landRate) {
                            temp = ETA[k];
                            newETA = temp.plusMinutes(landRate);
                            ETA[k] = newETA;
                            System.out.println("ETA[k]" + ETA[k]);
                            System.out.println("diffInt < landRate:  " + diffInt);
                            diff2 = ChronoUnit.MINUTES.between(ETA[i], ETA[k]);
                            int diffInt2 = (int) diff2;
                            System.out.println("diffInt2: " + diffInt2);
                            if (diffInt2 < landRate) {
                                temp = ETA[k];
                                newETA = temp.plusMinutes(landRate);
                                ETA[k] = newETA;
                                System.out.println("ETA[k]" + ETA[k]);
                                System.out.println("diffInt2 < landRate:  " + diffInt);
                                diff3 = ChronoUnit.MINUTES.between(ETA[i], ETA[k]);
                                int diffInt3 = (int) diff2;
                                System.out.println("diffInt3: " + diffInt3);
                            } else {

                            }

                        }
                    } else {

                    }
                }
            } //break;

        }
        System.out.println("\n\n\n\n***************diff in ETA in checkForSameETA method : " + diff);
        System.out.println("ETA in checkForSameETA method : " + Arrays.toString(ETA));
        return ETA;

    }

    public LocalDateTime[] generateSlot(LocalDateTime[] slot, long landRate) {

        LocalDateTime temp = null;
        LocalDateTime newSlot = null;

        DateTimeFormatter dtf;
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.now().with(LocalTime.MIDNIGHT.truncatedTo(ChronoUnit.MILLIS));
        LocalDateTime currentTime = startTime.truncatedTo(ChronoUnit.MILLIS);

        slot[0] = currentTime;
        temp = currentTime;
        for (int i = 0; i < slot.length; i++) {
            newSlot = temp.plusMinutes(landRate);
            slot[i] = newSlot;
            temp = temp.plusMinutes(landRate);
        }
        return slot;
    }

    /*    method to calculate FFT*/
    /**
     *
     * @param competa
     * @param ac_types
     * @param stars
     * @return
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     */
    public LocalDateTime calculateFFT(LocalDateTime competa, String ac_types, String stars) throws ParserConfigurationException, SAXException, IOException {
        LocalDateTime FFT = null;
        long interval = 0;
        System.out.println("\ncompETA in calculateFFT method " + competa);
//        fmsfunctions.removeNullInLocalDateTimeArrays(competa);

        try {
            //creating a constructor of file class and parsing an XML file  
           
            File file = new File("C:/wamp64/www/flowcontrol/public/run/configs/star.xml");
//                File file = new File("/var/www/html/flowcontrol/public/run/configs/star.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
//            Element root = doc.getDocumentElement();
            doc.getDocumentElement().normalize();
            System.out.println(" star Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName(stars);
//            NodeList nodes = doc.getElementsByTagName();
            Node node = nList.item(0);

            NodeList childNodes = node.getChildNodes();

//            NodeList starnode = node.getChildNodes();
            Element elementary;
            for (int i = 0; i < childNodes.getLength(); i++) {

                if (childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    elementary = (Element) childNodes.item(i);
                    String enames = elementary.getNodeName();
                    System.out.println("enames : " + enames);
                    if (ac_types.contains(enames)) {
                        System.out.println(" aircraft type element names -> " + enames);
                        System.out.println("interval :" + elementary.getElementsByTagName("interval").item(0).getTextContent());
                        System.out.println("runway :" + elementary.getElementsByTagName("runway").item(0).getTextContent());
                        System.out.println("airport :" + elementary.getElementsByTagName("airport").item(0).getTextContent());
                        System.out.println("star interval  :" + elementary.getElementsByTagName("airport").item(0).getTextContent());
                        String strinterval = elementary.getElementsByTagName("interval").item(0).getTextContent();

                        interval = Long.parseLong(strinterval);
//                        for (int t = 0; t < FFT.length; t++) {
                        if (competa != null) {
                            LocalDateTime temp = competa.minus(interval, ChronoUnit.MINUTES);
//                                if (FFT[t] == null) {

                            FFT = temp;
                            System.out.println("\nFFT value in calculateFFT: " + FFT);
//                                    break;
//                                }
                        } else {
                        }
//                        }
                    } else {
                    }
                }
//                counter++;
            }
        } catch (NumberFormatException | DOMException e) {
        }
        System.out.println("\nFFT in calculateFFT method ");
//        fmsfunctions.removeNullInLocalDateTimeArrays(FFT);
        return FFT;
    }

    /**
     * function to check whether star name from atm exists in star list or not
     *
     * @param starExists
     * @param ac_types
     * @param stars
     * @return
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     */
    public static Boolean checkStarName(boolean starExists, String ac_types, String stars) throws ParserConfigurationException, SAXException, IOException {

        System.out.println("running the checkStarName method ...");
        try {
            //creating a constructor of file class and parsing an XML file  
            
            File file = new File("C:/wamp64/www/flowcontrol/public/run/configs/star.xml");
//                File file = new File("/var/www/html/flowcontrol/public/run/configs/star.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println(" star Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName(stars);
            Node node = nList.item(0);
            NodeList childNodes = node.getChildNodes();
            Element elementary;
            for (int i = 0; i < childNodes.getLength(); i++) {
                if (childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    elementary = (Element) childNodes.item(i);
                    String enames = elementary.getNodeName();
                    System.out.println("enames " + enames);
                    if (ac_types.contains(enames)) {
                        System.out.println("enames equal ac_type");
                        starExists = true;
                        break;
                    } else {
                        System.out.println("check done --> " + ac_types + " not in star list " + enames);
                    }
                }
            }
        } catch (NumberFormatException | DOMException e) {
        }
        return starExists;
    }

    /**
     * function to calculate runway value based on star in atm file
     *
     * @param star
     * @return
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     */
    public static String getStar(String star) throws ParserConfigurationException, SAXException, IOException {
        String runway = null;

        try {
            File file = new File("C:/wamp64/www/flowcontrol/public/run/configs/star.xml");
//            File file = new File("/var/www/html/flowcontrol/public/run/configs/star.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println(" star Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName(star);
            Node node = nList.item(0);
            NodeList starnode = node.getChildNodes();
            Element elementary;
            for (int i = 0; i < starnode.getLength(); i++) {
                if (starnode.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    elementary = (Element) starnode.item(i);
                    runway = elementary.getElementsByTagName("runway").item(0).getTextContent();
                }
            }
        } catch (NumberFormatException | DOMException e) {
        }
        return runway;
    }

}
