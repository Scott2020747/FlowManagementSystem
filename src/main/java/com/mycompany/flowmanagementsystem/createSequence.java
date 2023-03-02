/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flowmanagementsystem;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Administrator
 */
public class createSequence {

    /* method to create XML sequence list   */
    /**
     *
     * @param autoSequenceMap
     * @param manualSequenceMap
     * @param adepArray
     * @param adesArray
     * @param stringfft
     * @param ac_typeArray
     * @param wturArray
     * @param regisArray
     * @param starArray
     * @param other_infoArray
     * @param routeArray
     * @param etd_dofArray
     * @param etdArray
     * @param rflArray
     * @param speedArray
     * @param atdArray
     * @param fruleArray
     * @param dofArray
     * @param eobtArray
     * @param ManadepArray
     * @param ManadesArray
     * @param ManfeederFixArray1
     * @param Manac_typeArray
     * @param ManwturArray
     * @param ManregisArray
     * @param ManstarArray
     * @param Manother_infoArray
     * @param ManrouteArray
     * @param Manetd_dofArray
     * @param ManetdArray
     * @param ManrflArray
     * @param ManspeedArray
     * @param ManatdArray
     * @param ManfruleArray
     * @param MandofArray
     * @param ManeobtArray
     */
    public void sequenceInXMLform(Map<LocalDateTime, String> autoSequenceMap, Map<LocalDateTime, String> manualSequenceMap, String[] adepArray, String[] adesArray, String[] stringfft, String[] ac_typeArray, String[] wturArray, String[] regisArray, String[] starArray, String[] other_infoArray, String[] routeArray, String[] etd_dofArray, String[] etdArray, String[] rflArray, String[] speedArray, String[] atdArray, String[] fruleArray, String[] dofArray, String[] eobtArray, String[] ManadepArray, String[] ManadesArray, String[] ManfeederFixArray1, String[] Manac_typeArray, String[] ManwturArray, String[] ManregisArray, String[] ManstarArray, String[] Manother_infoArray, String[] ManrouteArray, String[] Manetd_dofArray, String[] ManetdArray, String[] ManrflArray, String[] ManspeedArray, String[] ManatdArray, String[] ManfruleArray, String[] MandofArray, String[] ManeobtArray) {

        System.out.println("stringfft in sequenceInXMLform method \n "+Arrays.toString(stringfft));
//        fmsfunctions.removeNullFromString(ManfeederFixArray1);

        System.out.println("stringFFT in sequenceInXMLform method\n");
        fmsfunctions.removeNullInStringArrays(stringfft);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        CreateAutoXML autoXMLcreator = new CreateAutoXML();
        CreateManualXML manualXMLcreator = new CreateManualXML();
        try {
            System.out.println("Inside try-catch ");

            String tempFlightStatus = "CONTROLLED";  // <- pass active status values from atmflight.xml to an array and feed them into createUserElement to create the proper slotlist.xml file
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            String autoFlightProcedure = "auto";
            String manualFlightProcedure = "manual";
            // add elements to Document
            Element rootElement = doc.createElement("Slotlist");
            // append root element to document
            doc.appendChild(rootElement);
            //use for loop to get other flight details from global arrays into createAutoSequence() method
            int i = 0;
            Set<LocalDateTime> autoKeys = autoSequenceMap.keySet();
            for (LocalDateTime xmlKey : autoKeys) {

                System.out.println("auto adep array\n");
                fmsfunctions.removeNullFromString(adepArray);

                if (adepArray[i] != null) {
                    System.out.println("aircraft array in sequnece list: " + ac_typeArray[i]);
                    //get eta and flightnumber from treeMapSlots
                    // append first child element to root element
                    rootElement.appendChild(autoXMLcreator.createAutoSequence(doc, "slot", xmlKey, autoSequenceMap.get(xmlKey), tempFlightStatus, autoFlightProcedure, adepArray[i], adesArray[i], stringfft[i], starArray[i], ac_typeArray[i], wturArray[i], regisArray[i], dofArray[i], other_infoArray[i], routeArray[i], etd_dofArray[i], etdArray[i], rflArray[i], speedArray[i], atdArray[i], eobtArray[i], fruleArray[i]));
                }
                i++;
            }
            //use for loop to get other flight details from global manual arrays into createManualSequence() method
            int x = 0;
            Set<LocalDateTime> manualKeys = manualSequenceMap.keySet();
            for (LocalDateTime xmlKey1 : manualKeys) {
                if (ManadepArray[x] != null) {
                    // append first child element to root element
                    rootElement.appendChild(manualXMLcreator.createManualSequence(doc, "slot", xmlKey1, manualSequenceMap.get(xmlKey1), tempFlightStatus, manualFlightProcedure, ManadepArray[x], ManadesArray[x], ManfeederFixArray1[x], ManstarArray[x], Manac_typeArray[x], ManwturArray[x], ManregisArray[x], MandofArray[x], Manother_infoArray[x], ManrouteArray[x], Manetd_dofArray[x], ManetdArray[x], ManrflArray[x], ManspeedArray[x], ManatdArray[x], ManeobtArray[x], ManfruleArray[x]));
                }
                x++;
            }
            // for output to file, console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // for pretty print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            // write to console or file
            // StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("C:/wamp64/www/flowcontrol/public/run/data/sequence_list.xml"));
//            StreamResult file = new StreamResult(new File("/var/www/html/flowcontrol/public/run/data/sequence_list.xml"));
//
            // write data
            // transformer.transform(source, console);
            transformer.transform(source, file);
        } catch (IllegalArgumentException | ParserConfigurationException | TransformerException | DOMException e) {
        }
    }
}
