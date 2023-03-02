package com.mycompany.flowmanagementsystem;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author sbilau
 */
public final class SlotList {

    private LocalDateTime eta;
    private String flightnumber;

    public SlotList(LocalDateTime ldt, String string) {
        setETA(ldt);
        setFlightnumber(string);
    }

    public LocalDateTime getETA() {
        return eta;
    }

    public void setETA(LocalDateTime eta) {
        this.eta = eta;
    }

    public String getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(String flightnumber) {
        this.flightnumber = flightnumber;
    }

    /* function to create slot list with ETA and flightnumber */
    /**
     *
     * @param etaflightnumberHashMap
     * @param treemap
     * @return
     */
    public Map<LocalDateTime, String> createSlotList(Map<LocalDateTime, String> etaflightnumberHashMap, Map<LocalDateTime, String> treemap) {

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

}
