/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flowmanagementsystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author sbilau
 */
public class slot {

    public static HashMap createSlotList(HashMap eta, HashMap slotlist) {
        Calendar cal = Calendar.getInstance();
        Set<Date> etakeys = eta.keySet();
        for (Date key : etakeys) {
            if (slotlist.containsKey(key)) {
                slotlist.put(key, eta.get(key));
            } else {
                cal.setTime(key);
                cal.add(Calendar.MINUTE, 1);
                Date newKey = cal.getTime();
                if (slotlist.containsKey(newKey)) {
                    slotlist.put(newKey, eta.get(key));
                }
            }
            // return slot;        
        }
        return slotlist;
    }
    /* function to create slot list with ETA and flightnumber */
    /**
     *
     * @param etaflightnumberHashMap
     * @param treemap
     * @return
     */
    public Map<LocalDateTime, String> createSlot(Map<LocalDateTime, String> etaflightnumberHashMap, Map<LocalDateTime, String> treemap) {
        Set<LocalDateTime> etakeys = etaflightnumberHashMap.keySet();
        int iterator = 1;
        for (LocalDateTime key : etakeys) {
            //create an iterator to iterate through the Map
            if (treemap.containsKey(key)) {
                treemap.put(key, etaflightnumberHashMap.get(key));
            } else {
                LocalDateTime newKey2 = key.plusMinutes(1);
                Iterator<Map.Entry<LocalDateTime, String>> it = treemap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<LocalDateTime, String> pair = it.next();
                    LocalDateTime newKey = key.plusMinutes(2);
                    if (pair.getKey().equals(newKey2) && pair.getValue().isBlank() == true) {
                        treemap.put(newKey2, etaflightnumberHashMap.get(key));
                    } else if (pair.getKey().equals(newKey) && pair.getValue().isBlank() == true) {
                        treemap.put(newKey, etaflightnumberHashMap.get(key));
                    }
                }
            }
            iterator++;
        }
        return treemap;
    }

    /*  function to create a new HashMap with comapredETA as keys and flightnumber as values */
    public HashMap<LocalDateTime, String> createHashMap(Map<String, String> flightstatus, LocalDateTime[] comparedeta, Map<LocalDateTime, String> etaflightnumberhashmap) {
        //Convert HashMap flightStatus keyset to ArralyList
        Set<String> keySet = flightstatus.keySet();
        ArrayList<String> listOfKeysFromflightStatus = new ArrayList<String>(keySet);
        System.out.println("********************* keySet from flightStatus as ListArray");
        /*  create array string and send arraylist data to it*/
        String[] stringFlightStatusArray = new String[listOfKeysFromflightStatus.size()];
        for (int i = 0; i < listOfKeysFromflightStatus.size(); i++) {
            stringFlightStatusArray[i] = listOfKeysFromflightStatus.get(i);
        }
        for (String str : stringFlightStatusArray) {
            System.out.println(str);
        }
        /*  pass comparedETA values to etaFlightNumberhashMap as keys   */
        for (int i = 0; i < stringFlightStatusArray.length; i++) {
            if (comparedeta[i] != null) {
                Arrays.sort(comparedeta, Comparator.nullsLast(Comparator.reverseOrder()));
                LocalDateTime eta = comparedeta[i];
                etaflightnumberhashmap.put(eta, listOfKeysFromflightStatus.get(i));
            }
        }
        return (HashMap<LocalDateTime, String>) etaflightnumberhashmap;
    }
}
