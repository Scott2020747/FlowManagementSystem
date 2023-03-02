/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flowmanagementsystem;

import java.io.File;
import java.io.IOException;
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
public class ControllerSelection {

    /*  func to get input selection from controller */
    public void getControllerSelection(int runway, int landingRate, String starName, int stableSequencePeriod) {

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
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("input");
            // nodeList is not iterable, so we are using for loop  
            int count = 0;

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    System.out.println("runway: " + eElement.getElementsByTagName("rwy").item(0).getTextContent());
                    System.out.println("landing rate: " + eElement.getElementsByTagName("landingrate").item(0).getTextContent());
                    System.out.println("Stable Sequence Period: " + eElement.getElementsByTagName("ssperiod").item(0).getTextContent());
                    String strRWY = eElement.getElementsByTagName("rwy").item(0).getTextContent();
                    String strLR = eElement.getElementsByTagName("landingrate").item(0).getTextContent();
                    String ssp = eElement.getElementsByTagName("ssperiod").item(0).getTextContent();
                    runway = Integer.parseInt(strRWY);
                    landingRate = Integer.parseInt(strLR);
                    stableSequencePeriod = Integer.parseInt(ssp);
                }
            }
        } catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
        }

        System.out.println("Runway and landing rate values from controller affected file ");
        System.out.println("Runway: " + runway);
        System.out.println("Landing Rate: " + landingRate);
        System.out.println("Stable Sequence Period: " + stableSequencePeriod);
    }

}
