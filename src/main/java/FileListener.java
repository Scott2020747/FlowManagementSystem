/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sbilau
 */

import java.util.EventListener;

public interface FileListener extends EventListener {


    public void onCreated(FileEvent event);


    public void onModified(FileEvent event);


    public void onDeleted(FileEvent event);



}