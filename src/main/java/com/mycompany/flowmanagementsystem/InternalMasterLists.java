/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flowmanagementsystem;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
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
 * @author Administrator
 */
public class InternalMasterLists {

    public static LocalDateTime[] reserveSlotList(LocalDateTime[] reserveSlot, LocalDateTime[] masterReserveSlot) {
        LocalDateTime curr = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime[] tempSlot = new LocalDateTime[100];
        tempSlot[0] = curr;

        try {
            //creating a constructor of file class and parsing an XML file  
            File file = new File("C:/wamp64/www/flowcontrol/public/run/Internal_lists/internal_reserve_list.xml");
//            File file = new File("/var/www/html/flowcontrol/run/Internal_lists/reserve/internal_reserve_list.xml");

            //an instance of factory that gives a document builder  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file  
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("Slot");
            // nodeList is not iterable, so we are using for loop  
            int count = 0;
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String strETA = eElement.getElementsByTagName("eta").item(0).getTextContent();
                    String strStatus = eElement.getElementsByTagName("status").item(0).getTextContent();
                    /*Check if status of ETA is reserved -> if so then pass it to reserveSlot */
                    if (strStatus.equals("reserved")) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = sdf.parse(strETA);
                        LocalDateTime ldtDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                        tempSlot[itr] = ldtDate;
                        boolean test = Arrays.asList(masterReserveSlot).contains(ldtDate);
                        if (test == false) {
                            System.arraycopy(tempSlot, 0, reserveSlot, 0, reserveSlot.length);

                        } else {
                            break;
                        }
                    }
                }
            }
        } catch (IOException | ParseException | ParserConfigurationException | DOMException | SAXException e) {
        }
        System.out.println("\nReserved Slot list");
        for (int x = 0; x < reserveSlot.length; x++) {
            if (reserveSlot[x] != null) {
                System.out.println(reserveSlot[x]);
                masterReserveSlot[x] = reserveSlot[x];
            } else {
                break;

            }
        }
        return masterReserveSlot;
    }
}
