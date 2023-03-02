/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flowmanagementsystem;

import com.google.common.base.Splitter;
import static com.mycompany.flowmanagementsystem.mainFlowManagementSystem.reserveSlot;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
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
 * @author sbilau
 */
public class fmsfunctions {

    public void sortbykey(HashMap hmap) {
        Map<String, String> sorted
                = new TreeMap<String, String>(Collections.reverseOrder());

        // Copy all data from hashMap into TreeMap
        sorted.putAll(hmap);

        /* Display the TreeMap which is naturally sorted but in reverse order */
        Set set = sorted.entrySet();
        Iterator i = set.iterator();

        // Traverse map and print elements
        System.out.println("ETA               Fligh Number");
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            System.out.print(me.getKey() + " : ");
            System.out.println(me.getValue());
        }
    }

    /* A function to sort HashMap array in descending order by passing HashMap values to TreeMap and sorting it in descending order*/
    public <K, V> Map<K, V> convertToTreeMap(Map<K, V> hashMap) {
        // Create a new TreeMap
        Map<K, V> treeMap = (Map<K, V>) new TreeMap<LocalDateTime, String>(Collections.reverseOrder());

        // Pass the hashMap to putAll() method
        // Get the iterator over the HashMap
        Iterator<Map.Entry<K, V>> iterator = hashMap.entrySet().iterator();

        // Iterate over the HashMap
        while (iterator.hasNext()) {

            // Get the entry at this iteration
            Map.Entry<K, V> entry
                    = iterator.next();

            // Check if this key is the required key
            if (null == entry.getKey()) {

            } else {
                treeMap.putAll(hashMap);
            }
        }

        return treeMap;
    }

    /*  Function to convert Java object to xml  */
    public void jaxbObjectToXML(flights timeslot) {
        try {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(flights.class);

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Store XML to File
            File file = new File("slot_list.xml");

            //Writes XML file to file-system
            jaxbMarshaller.marshal(timeslot, file);
        } catch (JAXBException e) {
        }
    }

    //function to remove null values from string array
    /**
     *
     * @param target
     * @return
     */
    public String[] removeNullFromStringArray(String[] target) {
        List<String> values = new ArrayList<>();
        for (String data : target) {
            if (data != null) {
                values.add(data);
            }
        }
        target = values.toArray(new String[values.size()]);
        return target;
    }

    //fucntion : Converting java.util.Date to java.time.LocalDateTime using Instant object 
    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {

        //Date tempDate = dateToConvert;
        LocalDateTime convertedDate = null;
        convertedDate = dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        return convertedDate;
    }

    //method to get timestamp on xml file
    /**
     *
     * @param file
     * @return
     */
    public FileTime xmlTimeStamp(Path file) {
        FileTime timeStamp = null;
        try {

            BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
            timeStamp = attr.creationTime();
        } catch (IOException e) {
        }
        return timeStamp;

    }

    //method to check if timestamp on xml file has changed 
    public void xmlWatcher(Path file, FileTime timestamp) throws IOException {
        FileTime newTimeStamp = null;
        BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
        newTimeStamp = attr.lastModifiedTime();
        if (newTimeStamp != timestamp) {
            System.out.println("The xml file has changed...");
        }
    }

    /*  Func to compare arrays containing data from internal_auto_list.xml against arrays containing data from atmfile.xml file -> if flightnumber exist then update values in internal_atuto_list.xml with values from atmfile.xml
            1. Take in values from internal_auto_list.xml and store them in arrays
            2. Taken in values from atmfile.xml in arrays
            3. compare them -> if same then update values
                            -> if not same then add values
     */
    public static String[] createAutoListArray(String[] flightnumber, NodeList nodeList) {
        for (int itr = 0; itr < nodeList.getLength(); itr++) {
            Node node = nodeList.item(itr);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                String tempflight = eElement.getElementsByTagName("flightnumber").item(0).getTextContent();
                flightnumber[itr] = tempflight;
            }
        }

        return flightnumber;

    }

    public static Iterable<Node> iterable(final NodeList nodeList) {
        return () -> new Iterator<Node>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < nodeList.getLength();
            }

            @Override
            public Node next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return nodeList.item(index++);
            }
        };
    }

    /*  function to create TreeMap to contain time slots*/
    public static Map<LocalDateTime, String> createMap(LocalDateTime[] tSlots) {
        Map<LocalDateTime, String> treeMap = new TreeMap<>();
        String string = " ";
        for (LocalDateTime tSlot : tSlots) {
            treeMap.put(tSlot, string);
        }
        return treeMap;

    }

    /*  functiont to split <dof>, add 20 infront of date, and combine with <eta> to give string of date and time -> return value containes new eta value of date and time in UTC */
    /**
     *
     * @param dof
     * @param eta
     * @return
     */
    public static String combineETA_DOF(String dof, String eta) {
        String newETA = null;
        /*  split the string dof into 2 digits each*/
        int chunkSize = 2;
        Iterable<String> dofString = Splitter.fixedLength(chunkSize).split(dof);
        String[] nums = new String[dof.length()];
        int ctr = 0;
        for (String string : dofString) {
            nums[ctr] = string;
//            System.out.println(nums[ctr]);
            ctr++;
        }
        /*  split the string eta into 2 digits each*/
        Iterable<String> etaString = Splitter.fixedLength(chunkSize).split(eta);
        String[] nums2 = new String[eta.length()];
        int ctr2 = 0;
        for (String string : etaString) {
            nums2[ctr2] = string;
//            System.out.println(nums2[ctr2]);
            ctr2++;
        }
        newETA = "20" + nums[0] + "-" + nums[1] + "-" + nums[2] + " " + nums2[0] + ":" + nums2[1] + ":" + nums2[2];

        return newETA;
    }

    /*  function to remove flight from internal auto list , internal manual list and sequence list if status = FINSHED */
    /**
     *
     * @param tempCallsign
     * @throws javax.xml.transform.TransformerConfigurationException
     */
    public static void removeFinishedStatus(String tempCallsign) throws TransformerConfigurationException, TransformerException {
        try {
            String file = "C:/wamp64/www/flowcontrol/public/run/Internal_lists/auto/internal_auto_list.xml";
//            String file = "/var/www/html/flowcontrol/public/run/Internal_lists/auto/internal_auto_list.xml";
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            // Get the parent node
            Node flightParent = doc.getFirstChild();
            // Get the child flight element
            Node flightChild = doc.getElementsByTagName("flight").item(0);
            // Get the list of child nodes of flight 
            NodeList list = flightChild.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String callsign = eElement.getElementsByTagName("callsign").item(0).getTextContent();
                    //Remove flightChild node with callsign equal to tempCallsign node
                    if (tempCallsign.equals(callsign)) {
                        flightChild.removeChild(node);
                        System.out.println(tempCallsign + "equals" + eElement);
                    }
                }
            }
            // write the content to the xml file
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource src = new DOMSource(doc);
            StreamResult res = new StreamResult(new File(file));
            transformer.transform(src, res);
        } catch (IOException | ParserConfigurationException | TransformerException | DOMException | SAXException e) {
        }

        /* include another try - catch statement for internal manual list as well */
        try {
            String file = "C:/wamp64/www/flowcontrol/public/run/Internal_lists/manul/internal_manual_list.xml";
//            String file = "/var/www/html/flowcontrol/public/run/Internal_lists/manual/internal_manual_list.xml";

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            // Get the parent node
            Node flightParent = doc.getFirstChild();
            // Get the child flight element
            Node flightChild = doc.getElementsByTagName("manual").item(0);
            // Get the list of child nodes of flight 
            NodeList list = flightChild.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String callsign = eElement.getElementsByTagName("callsign").item(0).getTextContent();
                    //Remove flightChild node with callsign equal to tempCallsign node
                    if (tempCallsign.equals(callsign)) {
                        flightChild.removeChild(node);
                        System.out.println(tempCallsign + "equals" + eElement);
                    }
                }
            }
            // write the content to the xml file
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource src = new DOMSource(doc);
            StreamResult res = new StreamResult(new File(file));
            transformer.transform(src, res);
        } catch (IOException | ParserConfigurationException | TransformerException | DOMException | SAXException e) {
        }
    }

    /**
     * function to convert LocalDateTime to String
     *
     * @param compFFT
     * @return
     */
    public static String formatLocalDateTimeToString(LocalDateTime[] compFFT) {

//        System.out.println("FFT in calculateFFT method -->"+Arrays.toString(compFFT));
//        DateTimeFormatter formatter;
        String formattedDateTime = null;
        String formattedDateTime2 = null;
        // Inbuilt format
//        formatter = DateTimeFormatter.ISO_DATE_TIME;

        // Creating formatter using predefined library constants
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        // Creating Custom formatter
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (LocalDateTime compFFT1 : compFFT) {
            // Converting LocalDateTime instance to String format using predefined formatter.
            formattedDateTime2 = compFFT1.format(customFormatter);
        }

        // Verify
        System.out.println("Formatted LocalDateTime : " + formattedDateTime);
        return formattedDateTime2;

    }

    /**
     * Function to build Map Tree with ETA as keys and null strings as values
     *
     * @param slotTime
     * @return
     */
    public static Map<LocalDateTime, String> createTimeSlot(LocalDateTime[] slotTime) {
        Map<LocalDateTime, String> timeSlot = new TreeMap<>();
        String value = null;

        for (LocalDateTime slotTime1 : slotTime) {
            timeSlot.put(slotTime1, value);
        }

        System.out.println("\nTime Slot with ETA as keys and String null values\n");
        //Using an iterator to print timeSlot values
        timeSlot.entrySet().forEach(entry -> {
//            System.out.println(entry.getKey() + " : " + entry.getValue());
        });

        return timeSlot;
    }

    /**
     * Function to get timeSlot Map , manualETA Map and flightsETA Map and slot
     * manualETA and flightsETA times into timeSlot Map
     *
     * @param timeSlots
     * @param flightsETA
     * @return
     */
    public static Map<LocalDateTime, String> makeSlotList(Map<LocalDateTime, String> timeSlots, Map<LocalDateTime, String> flightsETA) {
        
        
        //compare auto and manual ETAs in manualETA and NewAutoETA to ensure there is no conflicting times --> include a third parameter with Map<LocalDateTime, String>ETA
        /*
            code goes in here
        */
        

        System.out.println("flightsETA inside makeSlotList method: " + flightsETA);
        System.out.println("timeSlots inside makeSlotList method: " + timeSlots);

        //send auto flight ETA into slot list
        Map<LocalDateTime, String> slotList = new TreeMap<>();
        Set<LocalDateTime> etakeys = flightsETA.keySet();
        int iterator = 1;

        for (LocalDateTime key : etakeys) {
                String flight = flightsETA.get(key);
            // If seconds equals 30 or more, add 1 minute     

            if (key == null) {
                System.out.println("key is null in method  : " + key);
            } else {
                int seconds = key.getSecond();
                if (seconds >= 30) {
                    key = key.plusMinutes(1);
                } else {
                    // Round down
                    key = key.truncatedTo(ChronoUnit.MINUTES); //create an iterator to iterate through the Map 
                }
                if (timeSlots.containsKey(key) && timeSlots.get(key) != flight) {
                    timeSlots.put(key, flight);
                    if (timeSlots.get(key) != null) {
                        System.out.println("timeslot with callsign : " + timeSlots.get(key));
                    }

                } else {
                    LocalDateTime newKey2 = key.plusMinutes(iterator);
                    if (timeSlots.containsKey(newKey2) && timeSlots.get(newKey2) != flight) {
                        timeSlots.put(newKey2, flight);
                    } else {
                        LocalDateTime newKey3 = key.plusMinutes(iterator + 1);
                        if (timeSlots.containsKey(newKey3) && timeSlots.get(newKey3) != flight) {
                            timeSlots.put(newKey3, flight);
                        }
                    }
                }
                slotList.putAll(timeSlots);

            }

        }
        iterator++;

        System.out.println("\n\nSlot List values ");
        slotList.entrySet().forEach(entry -> {
            if (entry.getValue() != null) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        });
        return slotList;
    }

    /**
     * this method checks the Slot list and send the updated ETAs to the
     * flightsETA and manualETA Map Trees
     *
     * @param slotList
     * @param flightsETA
     * @param manualETA
     * @return
     */
    public static HashMap<LocalDateTime, String> updateETAafterSlotted(Map<LocalDateTime, String> slotList, Map<LocalDateTime, String> flightsETA, Map<LocalDateTime, String> manualETA) {

        flightsETA = slotList;

        System.out.println("\nNew flightsETA ETA values ");
        flightsETA.entrySet().forEach(entry -> {
            if (entry.getValue() != null) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        });

        System.out.println("\nslotlist values ");
        slotList.entrySet().forEach(entry -> {
            if (entry.getValue() != null) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        });
        return (HashMap<LocalDateTime, String>) flightsETA;
    }

    /**
     * function to reverse order a string array
     *
     * @param arr
     */
    public static void reverseArray(String[] arr) {
        // Converting Array to List
        List<String> list = Arrays.asList(arr);
        // Reversing the list using Collections.reverse() method
        Collections.reverse(list);
        // Converting list back to Array
        String[] reversedArray = list.toArray(arr);
        // Printing the reverse Array
        System.out.print("Reverse Array : " + Arrays.toString(reversedArray));
    }

    /**
     * method to remove null values in array
     *
     *
     * @param arr
     * @return
     */
    public static String[] removeNullFromString(String[] arr) {
        List<String> values = new ArrayList<String>();
        for (String data : arr) {
            if (data != null) {
                values.add(data);
            }
        }
        String[] target = values.toArray(new String[values.size()]);
        for (String data : target) {
            System.out.println(data + " ");
        }
        return target;

    }

    /**
     * function to remove null values String arrays
     *
     * @param arr
     */
    @SuppressWarnings("empty-statement")
    public static void removeNullInStringArrays(String[] arr) {
        List<String> values = new ArrayList<String>();
        for (String data : arr) {
            if (data != null) {
                values.add(data);
            }
        }
        String[] target = values.toArray(new String[values.size()]);
        for (String data : target) {
            System.out.println(data);
        }
    }

    /**
     * function to remove null values LocalDateTime arrays
     *
     * @param arr
     * @return
     */
    @SuppressWarnings("empty-statement")
    public static LocalDateTime[] removeNullInLocalDateTimeArrays(LocalDateTime[] arr) {
        List<LocalDateTime> values = new ArrayList<LocalDateTime>();
        for (LocalDateTime data : arr) {
            if (data != null) {
                values.add(data);
            }
        }
        LocalDateTime[] target = values.toArray(new LocalDateTime[values.size()]);
        for (LocalDateTime data : target) {
            System.out.println(data);
        }
        return target;
    }

    //creating a generic function that converts the Array into List  
    public static <T> List<T> ArrayToListConversion(T arr[]) {
        //creating the constructor of thr List class  
        List<T> list = new ArrayList<>();
        //the method adds Array to the List  
        Collections.addAll(list, arr);
        //returns the list  
        return list;
    }

    /**
     * function to remove flight in internal auto list when flight procedure has
     * been changed from auto to manual function will get manual call sign and
     * check in internal auto list, if call sign exist in internal auto list, it
     * will remove it from internal to avoid duplicate flight records
     *
     * @param callsign
     * @throws org.xml.sax.SAXException
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws java.io.IOException
     * @throws javax.xml.transform.TransformerConfigurationException
     */
    public static void updateAuto(String callsign) throws SAXException, ParserConfigurationException, IOException, TransformerConfigurationException, TransformerException {

        System.out.println("running updateAto method...");

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

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                NodeList childList = node.getChildNodes();

                //Looking through all children nodes
                for (int x = 0; x < childList.getLength(); x++) {

                    Node child = childList.item(x);

                    // To search only "book" children
                    System.out.println("\nNode Name in updateAuto:" + node.getNodeName());
                    if (child.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) child;
//                    if (eElement.hasChildNodes() == true && eElement.getElementsByTagName("callsign").getLength() > 0) {
                        if (eElement.hasChildNodes() == true && eElement.getElementsByTagName("callsign").getLength() > 0) {
                            System.out.println("Call sign in autoUpdate method " + eElement.getElementsByTagName("callsign").item(0).getTextContent());
                            String tempCallsign = eElement.getElementsByTagName("callsign").item(0).getTextContent();

                            if (callsign.equals(tempCallsign)) {
                                System.out.println(" manual call sign exists in auto internal list " + tempCallsign);
                                // Delete node here
                                child.getParentNode().removeChild(child);
                                break;
                            }
                        } else {

                        }

//                    }
                    }

                } 

            }

            DOMSource source = new DOMSource(doc);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(new StreamSource("C:/wamp64/www/flowcontrol/public/run/Internal_lists/auto/xsltfile.xml"));
//            Transformer transformer = transformerFactory.newTransformer(new StreamSource("/var/www/html/flowcontrol/public/run/Internal_lists/auto/xsltfile.xml"));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result = new StreamResult("C:/wamp64/www/flowcontrol/public/run/Internal_lists/auto/internal_auto_list.xml");
//                StreamResult result = new StreamResult("/var/www/html/flowcontrol/public/run/Internal_lists/auto/internal_auto_list.xml");              

            transformer.transform(source, result);

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
        }

    }
}
