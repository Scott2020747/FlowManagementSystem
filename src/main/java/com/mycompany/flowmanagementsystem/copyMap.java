/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flowmanagementsystem;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sbilau
 */
public class copyMap {

// Java program to iterate through the
// first map and put it in the second map
    /**
     *
     * @param <K>
     * @param <V>
     * @param original
     * @return
     */
    public static <K, V> HashMap<K, V> copyMap(Map<K, V> original) {

        HashMap<K, V> second_Map = new HashMap<>();

        // Start the iteration and copy the Key and Value
        // for each Map to the other Map.
        for (Map.Entry<K, V> entry : original.entrySet()) {

            // using put method to copy one Map to Other
            second_Map.put(entry.getKey(),
                    entry.getValue());
        }

        return second_Map;
    }

}
