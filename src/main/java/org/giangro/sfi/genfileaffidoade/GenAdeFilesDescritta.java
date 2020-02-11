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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author GIANGR40
 */
@Service
public class GenAdeFilesDescritta extends GenAdeFiles {

    final static Logger logger
            = LoggerFactory.getLogger(GenAdeFilesDescritta.class);
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
        logger.info("generating ADE Descritta");
        logConfParams();
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

    private String getNewFileName() {
        StringBuilder newfilename = new StringBuilder();
        return newfilename.toString();
    }

    private void logConfParams() {

        if (logConfParams.equals(Boolean.FALSE)) {
            return;
        } // if
        else {            
            logger.debug("template file: \"" + templateFileDescr + "\"");
            logger.debug("destination path: \"" + destinationPath + "\"");
            logger.debug("filler_char: \"" + fillerChar + "\"");           
            logger.debug("cod_ade: \"" + codAde + "\"");
            logger.debug("cod_forn: \"" + codForn + "\"");            
            logger.debug("date_format: \"" + dateFormat + "\"");
            logger.debug("date_format_filename: \"" + dateFormatFileName + "\"");
            return;
        } // else   
    }

    @Value("${log_conf_params}")
    Boolean logConfParams;

    @Value("${filler_char}")
    private String fillerChar;

    @Value("${template_file_descr}")
    private String templateFileDescr;

    @Value("${destination_path}")
    private String destinationPath;

    @Value("${date_format}")
    private String dateFormat;

    @Value("${date_format_filename}")
    private String dateFormatFileName;

    @Value("${cod_ade}")
    private String codAde;

    @Value("${cod_forn}")
    private String codForn;

} // class GenAdeFilesDescritta
