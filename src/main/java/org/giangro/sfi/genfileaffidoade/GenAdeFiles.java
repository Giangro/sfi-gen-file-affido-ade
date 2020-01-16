/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.giangro.sfi.genfileaffidoade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public abstract class GenAdeFiles {

  final static Logger logger
    = LoggerFactory.getLogger(GenAdeFiles.class);

  final static String PROCESSING_EXT = ".processing";
  final static String OK_EXT = ".ok";
  final static String NOK_EXT = ".nok";

  public GenAdeFiles() {  
  } // public method

  public abstract void generate() throws Exception;    
  
} // class GenAdeFiles
