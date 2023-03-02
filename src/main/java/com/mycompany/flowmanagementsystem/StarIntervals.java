/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flowmanagementsystem;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
public class StarIntervals {

    public void getStarIntervals(HashMap<String, Integer> starname, int[] starintervals) {
        try {
            //creating a constructor of file class and parsing an XML file  
//            File file = fileStarPath;
                File file = new File("C:/wamp64/www/flowcontrol/public/run/configs/star.xml");                
//            File file = new File("/var/www/html/flowcontrol/public/run/configs/star.xml");

            //an instance of factory that gives a document builder  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file  
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("SACTO14");
            // nodeList is not iterable, so we are using for loop  
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    System.out.println("B767: " + eElement.getElementsByTagName("B767").item(0).getTextContent());
                    System.out.println("DASH8: " + eElement.getElementsByTagName("DASH8").item(0).getTextContent());
                    System.out.println("CHESNA: " + eElement.getElementsByTagName("CHESNA").item(0).getTextContent());
                    System.out.println("TWIN OTTER: " + eElement.getElementsByTagName("TWINOTTER").item(0).getTextContent());
                    String stringB767 = eElement.getElementsByTagName("B767").item(0).getTextContent();
                    String stringDASH8 = eElement.getElementsByTagName("DASH8").item(0).getTextContent();
                    String stringCHESNA = eElement.getElementsByTagName("CHESNA").item(0).getTextContent();
                    String stringTWIN_OTTER = eElement.getElementsByTagName("TWINOTTER").item(0).getTextContent();
                    int intervalB767 = Integer.parseInt(stringB767);
                    int intervalDASH8 = Integer.parseInt(stringDASH8);
                    int intervalCHESNA = Integer.parseInt(stringCHESNA);
                    int intervalTWIN_OTTER = Integer.parseInt(stringTWIN_OTTER);

                    String B767 = "B767";
                    String DASH8 = "DASH8";
                    String CHESNA = "CHESNA";
                    String TWINOTTER = "TWINOTTER";
                    starname.put(B767, intervalB767);
                    starname.put(DASH8, intervalDASH8);
                    starname.put(CHESNA, intervalCHESNA);
                    starname.put(TWINOTTER, intervalTWIN_OTTER);

                    //Pass star intervals to int array sacto14interval
                    starintervals[0] = intervalB767;
                    starintervals[1] = intervalDASH8;
                    starintervals[2] = intervalCHESNA;
                    starintervals[3] = intervalTWIN_OTTER;

                }
            }
        } catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
        }
    }

}
