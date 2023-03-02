/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flowmanagementsystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sbilau
 */
public class flights {

    String eta;
    String active;
    String feederFix;
    String acid;
    String atype;
    String starname;
    String sequenceType;
    String flightnumber;
    String status;
    int starInterval;
    int rwy;
    int landingRate;
    int landing_runway;
    int takeoff_runway;

    String callsign;
    String adep;
    String ades;
    String eobt;

    String dof;
    String ac_nbr;
    String ac_type;
    String wtur;
    String frule;
    String ftype;
    String cna;
    String ssr_equi;
    String ssr_mode;
    String atd;
    String eet;
    String speed;
    String rfl;
    String regis;
    String ads_c_equip;
    String acft_ads_code;
    String rnp_capable;
    String etd;
    String etd_dof;
    String route;
    String other_info;
    String sup_info;
    String sid;
    String star;
    String interval;

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public int getLanding_runway() {
        return landing_runway;
    }

    public void setLanding_runway(int landing_runway) {
        this.landing_runway = landing_runway;
    }

    public int getTakeoff_runway() {
        return takeoff_runway;
    }

    public void setTakeoff_runway(int takeoff_runway) {
        this.takeoff_runway = takeoff_runway;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getEobt() {
        return eobt;
    }

    public void setEobt(String eobt) {
        this.eobt = eobt;
    }

    public String getDof() {
        return dof;
    }

    public void setDof(String dof) {
        this.dof = dof;
    }

    public String getAc_nbr() {
        return ac_nbr;
    }

    public void setAc_nbr(String ac_nbr) {
        this.ac_nbr = ac_nbr;
    }

    public String getAtd() {
        return atd;
    }

    public void setAtd(String atd) {
        this.atd = atd;
    }

    public String getEet() {
        return eet;
    }

    public void setEet(String eet) {
        this.eet = eet;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getRfl() {
        return rfl;
    }

    public void setRfl(String rfl) {
        this.rfl = rfl;
    }

    public String getAds_c_equip() {
        return ads_c_equip;
    }

    public void setAds_c_equip(String ads_c_equip) {
        this.ads_c_equip = ads_c_equip;
    }

    public String getAcft_ads_code() {
        return acft_ads_code;
    }

    public void setAcft_ads_code(String acft_ads_code) {
        this.acft_ads_code = acft_ads_code;
    }

    public String getRnp_capable() {
        return rnp_capable;
    }

    public void setRnp_capable(String rnp_capable) {
        this.rnp_capable = rnp_capable;
    }

    public String getEtd() {
        return etd;
    }

    public void setEtd(String etd) {
        this.etd = etd;
    }

    public String getWtur() {
        return wtur;
    }

    public void setWtur(String wtur) {
        this.wtur = wtur;
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public String getAdep() {
        return adep;
    }

    public void setAdep(String adep) {
        this.adep = adep;
    }

    public String getAdes() {
        return ades;
    }

    public void setAdes(String ades) {
        this.ades = ades;
    }

    public String getAc_type() {
        return ac_type;
    }

    public void setAc_type(String ac_type) {
        this.ac_type = ac_type;
    }

    public String getFrule() {
        return frule;
    }

    public void setFrule(String frule) {
        this.frule = frule;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getCna() {
        return cna;
    }

    public void setCna(String cna) {
        this.cna = cna;
    }

    public String getSsr_equi() {
        return ssr_equi;
    }

    public void setSsr_equi(String ssr_equi) {
        this.ssr_equi = ssr_equi;
    }

    public String getSsr_mode() {
        return ssr_mode;
    }

    public void setSsr_mode(String ssr_mode) {
        this.ssr_mode = ssr_mode;
    }

    public String getRegis() {
        return regis;
    }

    public void setRegis(String regis) {
        this.regis = regis;
    }

    public String getEtd_dof() {
        return etd_dof;
    }

    public void setEtd_dof(String etd_dof) {
        this.etd_dof = etd_dof;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getOther_info() {
        return other_info;
    }

    public void setOther_info(String other_info) {
        this.other_info = other_info;
    }

    public String getSup_info() {
        return sup_info;
    }

    public void setSup_info(String sup_info) {
        this.sup_info = sup_info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLandingRate() {
        return landingRate;
    }

    public void setLandingRate(int landingRate) {
        this.landingRate = landingRate;
    }

    public int getRwy() {
        return rwy;
    }

    public void setRwy(int rwy) {
        this.rwy = rwy;
    }

    public int getStarInterval() {
        return starInterval;
    }

    public void setStarInterval(int starInterval) {
        this.starInterval = starInterval;
    }

    public String getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(String flightnumber) {
        this.flightnumber = flightnumber;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getFeederFix() {
        return feederFix;
    }

    public void setFeederFix(String feederFix) {
        this.feederFix = feederFix;
    }

    public String getAcid() {
        return acid;
    }

    public void setAcid(String acid) {
        this.acid = acid;
    }

    public String getAtype() {
        return atype;
    }

    public void setAtype(String atype) {
        this.atype = atype;
    }

    public String getStarname() {
        return starname;
    }

    public void setStarname(String starname) {
        this.starname = starname;
    }

    public String getSequenceType() {
        return sequenceType;
    }

    public void setSequenceType(String sequenceType) {
        this.sequenceType = sequenceType;
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

    public static File[] getXMLFiles(File folder) {
        List<File> aList = new ArrayList<File>();

        File[] files = folder.listFiles();
        for (File pf : files) {

            if (pf.isFile() && getFileExtensionName(pf).contains("xml")) {
                aList.add(pf);
                // pf.delete();
            }
        }
        return aList.toArray(new File[aList.size()]);

    }
    public static void deleteXMLFiles(File folder) {
        File[] files = folder.listFiles();
        for (File pf : files) {
            if (pf.isFile() && getFileExtensionName(pf).contains("xml")) {
                pf.delete();
            }
        }
    }

    public static String getFileExtensionName(File f) {
        if (!f.getName().contains(".")) {
            return "";
        } else {
            return f.getName().substring(f.getName().length() - 3, f.getName().length());
        }
    }

    public static boolean isEmpty(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> directory = Files.newDirectoryStream(path)) {
                return !directory.iterator().hasNext();
            }
        }

        return false;
    }

}
