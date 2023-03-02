package com.mycompany.flowmanagementsystem;

import java.time.LocalDateTime;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author sbilau
 */
public class CreateManualXML {

    public Node createManualSequence(Document doc, String slot, LocalDateTime time, String callsign, String flightstatus, String flightprocedure, String adep, String ades, String feederFix, String star, String ac_type, String wtur, String regis, String dof, String other_info, String route, String etd_dof, String etd, String rfl, String speed, String atd, String eobt, String frule) {
        
        System.out.println("Feeder Fix Time in createManulSequence method : " + feederFix);
        
        Element user = doc.createElement("Slot");

        // create firstName element
        user.appendChild(createManualSequenceTime(doc, user, "eta", time));

        // create secondName element
        user.appendChild(createManualSequenceCallsign(doc, user, "callsign", callsign));

        // create thirdName element
        user.appendChild(createManualSequenceFlighstatus(doc, user, "flightstatus", flightstatus));

        //create fourthName element
        user.appendChild(createManualSequenceFlighprocedure(doc, user, "flightprocedure", flightprocedure));

        //create fifth name element
        user.appendChild(createManualSequenceAdep(doc, user, "adep", adep));

        //create 6th name element
        user.appendChild(createManualSequenceAdes(doc, user, "ades", ades));

        //create 7th name element
        user.appendChild(createManualSequenceFeederFix(doc, user, "feederFix", feederFix));

        //create 8th name element
        user.appendChild(createManualSequenceStar(doc, user, "star", star));

        //create 9th name element
        user.appendChild(createManualSequenceAc_type(doc, user, "ac_type", ac_type));

        //create 10th name element
        user.appendChild(createManualSequenceWtur(doc, user, "wtur", wtur));

        //create 11th name element
        user.appendChild(createManualSequenceRegis(doc, user, "regis", regis));

        //create 12th name element
        user.appendChild(createManualSequenceDof(doc, user, "dof", dof));

        //create 13th name element
        user.appendChild(createManualSequenceOther_info(doc, user, "other_info", other_info));

        //create 14th name element
        user.appendChild(createManualSequenceRoute(doc, user, "route", route));

        //create 15th name element
        user.appendChild(createManualSequenceEtd_dof(doc, user, "etd_dof", etd_dof));

        //create 16th name element
        user.appendChild(createManualSequenceEtd(doc, user, "etd", etd));

        //create 17th name element
        user.appendChild(createManualSequenceRfl(doc, user, "rfl", rfl));

        //create 18th name element
        user.appendChild(createManualSequenceSpeed(doc, user, "speed", speed));

        //create 19th name element
        user.appendChild(createManualSequenceAtd(doc, user, "atd", atd));

        //create 20th name element
        user.appendChild(createManualSequenceEobt(doc, user, "eobt", eobt));

        //create 11th name element
        user.appendChild(createManualSequenceFrule(doc, user, "frule", frule));

        return user;
    }

    // utility method to create text node
    /**
     *
     * @param doc
     * @param element
     * @param name
     * @param callsign
     * @return
     */
    public Node createManualSequenceCallsign(Document doc, Element element, String name, String callsign) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(callsign));
        return node;
    }

    public Node createManualSequenceTime(Document doc, Element element, String name, LocalDateTime time) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(time.toString()));
        return node;
    }

    public Node createManualSequenceFlighstatus(Document doc, Element element, String name, String flightstatus) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(flightstatus));
        return node;
    }

    public Node createManualSequenceFlighprocedure(Document doc, Element element, String name, String flightprocedure) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(flightprocedure));
        return node;
    }

    public Node createManualSequenceAdep(Document doc, Element element, String name, String adep) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(adep));
        return node;
    }

    public Node createManualSequenceAdes(Document doc, Element element, String name, String ades) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(ades));
        return node;
    }

    public Node createManualSequenceFeederFix(Document doc, Element element, String name, String feederFix) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(feederFix));
        return node;
    }

    public Node createManualSequenceStar(Document doc, Element element, String name, String star) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(star));
        return node;
    }

    public Node createManualSequenceAc_type(Document doc, Element element, String name, String ac_type) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(ac_type));
        return node;
    }

    public Node createManualSequenceWtur(Document doc, Element element, String name, String wtur) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(wtur));
        return node;
    }

    public Node createManualSequenceRegis(Document doc, Element element, String name, String regis) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(regis));
        return node;
    }

    public Node createManualSequenceDof(Document doc, Element element, String name, String dof) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(dof));
        return node;
    }

    public Node createManualSequenceOther_info(Document doc, Element element, String name, String other_info) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(other_info));
        return node;
    }

    public Node createManualSequenceRoute(Document doc, Element element, String name, String route) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(route));
        return node;
    }

    public Node createManualSequenceEtd_dof(Document doc, Element element, String name, String etd_dof) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(etd_dof));
        return node;
    }

    public Node createManualSequenceEtd(Document doc, Element element, String name, String etd) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(etd));
        return node;
    }

    public Node createManualSequenceRfl(Document doc, Element element, String name, String rfl) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(rfl));
        return node;
    }

    public Node createManualSequenceSpeed(Document doc, Element element, String name, String speed) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(speed));
        return node;
    }

    public Node createManualSequenceAtd(Document doc, Element element, String name, String atd) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(atd));
        return node;
    }

    public Node createManualSequenceEobt(Document doc, Element element, String name, String eobt) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(eobt));
        return node;
    }

    public Node createManualSequenceFrule(Document doc, Element element, String name, String frule) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(frule));
        return node;
    }
}
