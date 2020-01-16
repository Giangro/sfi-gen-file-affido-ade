/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.giangro.sfi.genfileaffidoade;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import static org.giangro.sfi.genfileaffidoade.GenAdeFilesDescritta.logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author GIANGR40
 */
@Service
public class GenAdeFilesIndescritta extends GenAdeFiles {

    final static Logger logger
            = LoggerFactory.getLogger(GenAdeFilesIndescritta.class);
    final static String SEP_CONF
            = ",";
    final static String FILE_NAME_PRE
            = "FTP.";
    final static String DEFAULT_DATE_PATTERN
            = "yyyyMMdd";
    final static String DEFAULT_DATE
            = "19700101";
  
    @PostConstruct
    void init() {     
    }

    @Override
    public void generate() throws Exception {
        logger.info("generating ADE Indescritta");
        logConfParams(); 
    }  

    private String getRandomID() {
        String id = "";     
        return id;
    }

    private String getNewFileName() {
        StringBuilder newfilename = new StringBuilder();       
        return newfilename.toString();
    }

    private String[] splitElem(String value) {
        String[] array;
        array = value.split(GenAdeFilesDescritta.SEP_CONF, -1);
        return array;
    }

    private String getCurrentDate(String pattern) {
        String date = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            date = simpleDateFormat.format(new Date());
        } // try
        catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
            logger.warn("using default pattern: " + DEFAULT_DATE_PATTERN);
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
                date = simpleDateFormat.format(new Date());
            } // try
            catch (Exception ex1) {
                logger.error(ex1.getLocalizedMessage());
                logger.warn("using default date: " + DEFAULT_DATE);
                date = DEFAULT_DATE;
            } // catch

        } // catch        
        return date;
    }

    private void logConfParams() {

        if (logConfParams.equals(Boolean.FALSE)) {
            return;
        } // if
        else {
          return;
        } // else   
    }

    @Value("${log_conf_params}")
    Boolean logConfParams;

    @Value("${filler_char}")
    private String fillerChar;

    @Value("${max_num_values_indescr}")
    private Integer maxNumValuesIndescr;

    @Value("${template_file_indescr}")
    private String templateFileIndescr;

    @Value("${destination_path}")
    private String destinationPath;
    
    @Value("${date_format}")
    private String dateFormat;

    @Value("${date_format_filename}")
    private String dateFormatFileName;      

} // GenAdeFilesIndescritta
