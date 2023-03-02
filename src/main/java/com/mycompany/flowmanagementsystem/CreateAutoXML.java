package com.mycompany.flowmanagementsystem;

import java.time.LocalDateTime;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author sbilau
 */
public class CreateAutoXML {

    public Node createAutoSequence(Document doc, String slot, LocalDateTime time, String callsign, String flightstatus, String flightprocedure, String adep, String ades, String feederFix, String star, String ac_type, String wtur, String regis, String dof, String other_info, String route, String etd_dof, String etd, String rfl, String speed, String atd, String eobt, String frule) {
        Element user = doc.createElement("Slot");

        // set id attribute
        //user.setAttribute("slot", slot);
        // create firstName element
        user.appendChild(createAutoSequenceTime(doc, user, "eta", time));

        // create secondName element
        user.appendChild(createAutoSequenceCallsign(doc, user, "callsign", callsign));

        // create thirdName element
        user.appendChild(createAutoSequenceFlighstatus(doc, user, "flightstatus", flightstatus));

        //create fourthName element
        user.appendChild(createAutoSequenceFlighprocedure(doc, user, "flightprocedure", flightprocedure));

        //create fifth name element
        user.appendChild(createAutoSequenceAdep(doc, user, "adep", adep));

        //create 6th name element
        user.appendChild(createAutoSequenceAdes(doc, user, "ades", ades));

        //create 7th name element
        user.appendChild(createAutoSequenceFeederFix(doc, user, "feederFix", feederFix));

        //create 8th name element
        user.appendChild(createAutoSequenceStar(doc, user, "star", star));

        //create 9th name element
        user.appendChild(createAutoSequenceAc_type(doc, user, "ac_type", ac_type));

        //create 10th name element
        user.appendChild(createAutoSequenceWtur(doc, user, "wtur", wtur));

        //create 11th name element
        user.appendChild(createAutoSequenceRegis(doc, user, "regis", regis));

        //create 12th name element
        user.appendChild(createAutoSequenceDof(doc, user, "dof", dof));

        //create 13th name element
        user.appendChild(createAutoSequenceOther_info(doc, user, "other_info", other_info));

        //create 14th name element
        user.appendChild(createAutoSequenceRoute(doc, user, "route", route));

        //create 15th name element
        user.appendChild(createAutoSequenceEtd_dof(doc, user, "etd_dof", etd_dof));

        //create 16th name element
        user.appendChild(createAutoSequenceEtd(doc, user, "etd", etd));

        //create 17th name element
        user.appendChild(createAutoSequenceRfl(doc, user, "rfl", rfl));

        //create 18th name element
        user.appendChild(createAutoSequenceSpeed(doc, user, "speed", speed));

        //create 19th name element
        user.appendChild(createAutoSequenceAtd(doc, user, "atd", atd));

        //create 20th name element
        user.appendChild(createAutoSequenceEobt(doc, user, "eobt", eobt));

        //create 11th name element
        user.appendChild(createAutoSequenceFrule(doc, user, "frule", frule));

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
    public Node createAutoSequenceCallsign(Document doc, Element element, String name, String callsign) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(callsign));
        return node;
    }

    public Node createAutoSequenceTime(Document doc, Element element, String name, LocalDateTime time) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(time.toString()));
        return node;
    }

    public Node createAutoSequenceFlighstatus(Document doc, Element element, String name, String flightstatus) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(flightstatus));
        return node;

    }

    public Node createAutoSequenceFlighprocedure(Document doc, Element element, String name, String flightprocedure) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(flightprocedure));
        return node;

    }

    public Node createAutoSequenceAdep(Document doc, Element element, String name, String adep) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(adep));
        return node;
    }

    public Node createAutoSequenceAdes(Document doc, Element element, String name, String ades) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(ades));
        return node;
    }

    public Node createAutoSequenceFeederFix(Document doc, Element element, String name, String feederFix) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(feederFix));
        return node;
    }

    public Node createAutoSequenceStar(Document doc, Element element, String name, String star) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(star));
        return node;
    }

    public Node createAutoSequenceAc_type(Document doc, Element element, String name, String ac_type) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(ac_type));
        return node;
    }

    public Node createAutoSequenceWtur(Document doc, Element element, String name, String wtur) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(wtur));
        return node;
    }

    public Node createAutoSequenceRegis(Document doc, Element element, String name, String regis) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(regis));
        return node;
    }

    public Node createAutoSequenceDof(Document doc, Element element, String name, String dof) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(dof));
        return node;
    }

    public Node createAutoSequenceOther_info(Document doc, Element element, String name, String other_info) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(other_info));
        return node;
    }

    public Node createAutoSequenceRoute(Document doc, Element element, String name, String route) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(route));
        return node;
    }

    public Node createAutoSequenceEtd_dof(Document doc, Element element, String name, String etd_dof) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(etd_dof));
        return node;
    }

    public Node createAutoSequenceEtd(Document doc, Element element, String name, String etd) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(etd));
        return node;
    }

    public Node createAutoSequenceRfl(Document doc, Element element, String name, String rfl) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(rfl));
        return node;
    }

    public Node createAutoSequenceSpeed(Document doc, Element element, String name, String speed) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(speed));
        return node;
    }

    public Node createAutoSequenceAtd(Document doc, Element element, String name, String atd) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(atd));
        return node;
    }

    public Node createAutoSequenceEobt(Document doc, Element element, String name, String eobt) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(eobt));
        return node;
    }

    public Node createAutoSequenceFrule(Document doc, Element element, String name, String frule) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(frule));
        return node;
    }

    /*   add new values to internal auto list   */
    public static void addAutoXML() {

    }
}
