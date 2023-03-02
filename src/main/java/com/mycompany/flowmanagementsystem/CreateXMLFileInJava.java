package com.mycompany.flowmanagementsystem;

import java.time.LocalDateTime;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author sbilau
 */
public class CreateXMLFileInJava {

    public Node createUserElement(Document doc, String slot, LocalDateTime time, String flightnumber, String flightstatus) {
        Element user = doc.createElement("Slot");

        // set id attribute
        //user.setAttribute("slot", slot);
        // create firstName element
        user.appendChild(createUserElementsTime(doc, user, "time", time));

        // create secondName element
        user.appendChild(createUserElementsFlightnumber(doc, user, "flightnumber", flightnumber));

        // create thirdName element
        user.appendChild(createUserElementsFlighstatus(doc, user, "flightstatus", flightstatus));

        return user;
    }

    // utility method to create text node
    /**
     *
     * @param doc
     * @param element
     * @param name
     * @param flightnumber
     * @return
     */
    public Node createUserElementsFlightnumber(Document doc, Element element, String name, String flightnumber) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(flightnumber));
        return node;
    }

    public Node createUserElementsTime(Document doc, Element element, String name, LocalDateTime time) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(time.toString()));
        return node;
    }

    public Node createUserElementsFlighstatus(Document doc, Element element, String name, String flightstatus) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(flightstatus));
        return node;
    }
}
