/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flowmanagementsystem;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class Print {

    public void printSequence(Map<LocalDateTime, String> treemapslot, Map<LocalDateTime, String> manmapslot) {
        System.out.println("************************* auto sequence timeSlotMap **********************************\n");
        Set<LocalDateTime> newkeys = treemapslot.keySet();
        for (LocalDateTime nkey : newkeys) {
            System.out.println("ETA " + nkey + " is: " + treemapslot.get(nkey));
        }
        System.out.println("************************* manual sequence slot list");
        Set<LocalDateTime> mkeys = manmapslot.keySet();
        for (LocalDateTime mankey : mkeys) {
            System.out.println("ETA " + mankey + " is :" + manmapslot.get(mankey));
        }

    }

}
