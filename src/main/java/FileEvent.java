/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sbilau
 */

import java.io.File;

import java.util.EventObject;

public class FileEvent extends EventObject {

  public FileEvent(File file) {

    super(file);

  }

  public File getFile() {

    return (File) getSource();

  }

}