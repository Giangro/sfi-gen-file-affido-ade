/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.giangro.sfi.genfileaffidoade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import static org.giangro.sfi.genfileaffidoade.GenAdeFilesDescritta.logger;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author GIANGR40
 */
@Service
public class GenAdeFilesSintetica extends GenAdeFiles {

    final static Logger logger
            = LoggerFactory.getLogger(GenAdeFilesSintetica.class);
    final static String SEP_CONF
            = ",";
    final static String FILE_NAME_PRE
            = "FTP.";
    final static String DEFAULT_DATE_PATTERN
            = "yyyyMMdd";
    final static String DEFAULT_DATE
            = "19700101";

    final static String TIPO_FLUSSO_SINTETICA
            = "DEP010";

    final static String FLUSSO_TAG
            = "DEP-010_DEF-020";

    final static String HEADER_TAG
            = "HEAD";

    final static String HEADER_TYPE_TAG
            = "HEADER_TYPE";
    
    final static String ID_FLUSSO_TAG
            = "ID_FLUSSO";

    final static String NOME_FLUSSO_TAG
            = "NOME_FLUSSO";

    final static String MITTENTE_FLUSSO_TAG
            = "MITTENTE_FLUSSO";
    
    final static String DATA_CREAZIONE_FLUSSO_TAG
            = "DATA_CREAZIONE_FLUSSO";
    
    final static String DESTINATARIO_FLUSSO_TAG
            = "DESTINATARIO_FLUSSO";
    
    final static String TIPO_FLUSSO_TAG
            = "TIPO_FLUSSO";
    
    final static String CODICE_IDENTIFICATIVO_GARA_TAG
            = "CODICE_IDENTIFICATIVO_GARA";
    
    final static String DENOMINAZIONE_COMMITTENTE_TAG
            = "DENOMINAZIONE_COMMITTENTE";
    
    final static String CF_COMMITTENTE_TAG
            = "CF_COMMITTENTE";
    
    final static String NUMERO_CONTRATTO_TAG
            = "NUMERO_CONTRATTO";
    
    final static String RESPONSABILE_TAG
            = "RESPONSABILE";
    
    final static String RESPONSABILE_TYPE_TAG
            = "RESPONSABILE_TYPE";
    
    final static String NOMINATIVO_TAG
            = "NOMINATIVO";
    
    final static String TELEFONO_TAG
            = "TELEFONO";

    final static String CELLULARE_TAG
            = "CELLULARE";
    
    final static String EMAIL_TAG
            = "EMAIL";
    
    final static String MORE_INFO_TAG
            = "MORE_INFO";
        
    @PostConstruct
    void init() {
    }

    @Override
    public void generate() throws Exception {
        logger.info("generating ADE Sintetica");
        logConfParams();

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();

            Element rootElement;
            rootElement = doc.createElement(GenAdeFilesSintetica.FLUSSO_TAG);
            doc.appendChild(rootElement);

            // head element
            Element headElement = doc.createElement(GenAdeFilesSintetica.HEADER_TAG);
            rootElement.appendChild(headElement);

            // header_type element
            Element headerTypeElement;
            headerTypeElement = doc.createElement(GenAdeFilesSintetica.HEADER_TYPE_TAG);
            headElement.appendChild(headerTypeElement);
            
            // id flusso
            
            Element idFlussoElement;
            idFlussoElement = doc.createElement(GenAdeFilesSintetica.ID_FLUSSO_TAG);
            idFlussoElement.appendChild(doc.createTextNode(idFlusso));
            headerTypeElement.appendChild(idFlussoElement);
            
            // nome flusso
            
            Element nomeFlussoElement;
            nomeFlussoElement = doc.createElement(GenAdeFilesSintetica.NOME_FLUSSO_TAG);
            nomeFlussoElement.appendChild(doc.createTextNode(nomeFlusso));
            headerTypeElement.appendChild(nomeFlussoElement);

            // mittente flusso
            
            Element mittenteFlussoElement;
            mittenteFlussoElement = doc.createElement(GenAdeFilesSintetica.MITTENTE_FLUSSO_TAG);
            mittenteFlussoElement.appendChild(doc.createTextNode(mittenteFlusso));
            headerTypeElement.appendChild(mittenteFlussoElement);

            // data creazione flusso
            
            Element dataCreazioneFlussoElement;
            dataCreazioneFlussoElement = doc.createElement(GenAdeFilesSintetica.DATA_CREAZIONE_FLUSSO_TAG);
            dataCreazioneFlussoElement.appendChild(doc.createTextNode(dataCreazioneFlusso));
            headerTypeElement.appendChild(dataCreazioneFlussoElement);

            // destinatario flusso
            
            Element destinatarioFlussoElement;
            destinatarioFlussoElement = doc.createElement(GenAdeFilesSintetica.DESTINATARIO_FLUSSO_TAG);
            destinatarioFlussoElement.appendChild(doc.createTextNode(destinatarioFlusso));
            headerTypeElement.appendChild(destinatarioFlussoElement);

            // tipo flusso
            
            Element tipoFlussoElement;
            tipoFlussoElement = doc.createElement(GenAdeFilesSintetica.TIPO_FLUSSO_TAG);
            tipoFlussoElement.appendChild(doc.createTextNode(tipoFlusso));
            headerTypeElement.appendChild(tipoFlussoElement);

            // codice identificativo gara
            
            Element codiceIdentificativoGaraElement;
            codiceIdentificativoGaraElement = doc.createElement(GenAdeFilesSintetica.CODICE_IDENTIFICATIVO_GARA_TAG);
            codiceIdentificativoGaraElement.appendChild(doc.createTextNode(codiceIdentificativoGara));
            headerTypeElement.appendChild(codiceIdentificativoGaraElement);
            
            // denominazione committente
            
            Element denominazioneCommittenteElement;
            denominazioneCommittenteElement = doc.createElement(GenAdeFilesSintetica.DENOMINAZIONE_COMMITTENTE_TAG);
            denominazioneCommittenteElement.appendChild(doc.createTextNode(denominazioneCommittente));
            headerTypeElement.appendChild(denominazioneCommittenteElement);
            
            // cf committente
            
            Element cfCommittenteElement;
            cfCommittenteElement = doc.createElement(GenAdeFilesSintetica.CF_COMMITTENTE_TAG);
            cfCommittenteElement.appendChild(doc.createTextNode(cfCommittente));
            headerTypeElement.appendChild(cfCommittenteElement);
            
            // numero contratto
            
            Element numeroContrattoElement;
            numeroContrattoElement = doc.createElement(GenAdeFilesSintetica.NUMERO_CONTRATTO_TAG);
            numeroContrattoElement.appendChild(doc.createTextNode(numeroContratto));
            headerTypeElement.appendChild(numeroContrattoElement);
            
            // responsabile
            
            Element responsabileElement;
            responsabileElement = doc.createElement(GenAdeFilesSintetica.RESPONSABILE_TAG);            
            headerTypeElement.appendChild(responsabileElement);
            
            // responsabile type
            
            Element responsabileTypeElement;
            responsabileTypeElement = doc.createElement(GenAdeFilesSintetica.RESPONSABILE_TYPE_TAG);            
            headerTypeElement.appendChild(responsabileTypeElement);
            
            
            
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(".\\file.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            System.out.println("File saved!");

        } // try
        catch (ParserConfigurationException | TransformerException pce) {
            logger.error(pce.getLocalizedMessage());
        } // catch

    }

    private String[] splitElem(String value) {
        String[] array;
        array = value.split(GenAdeFilesSintetica.SEP_CONF, -1);
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
            logger.debug("ID_FLUSSO: \"" + idFlusso + "\"");
            logger.debug("NOME_FLUSSO: \"" + nomeFlusso + "\"");
            logger.debug("MITTENTE_FLUSSO: \"" + mittenteFlusso + "\"");
            logger.debug("DATA_CREAZIONE_FLUSSO: \"" + dataCreazioneFlusso + "\"");
            logger.debug("DESTINATARIO_FLUSSO: \"" + destinatarioFlusso + "\"");
            logger.debug("TIPO_FLUSSO: \"" + tipoFlusso + "\"");
            logger.debug("CODICE_IDENTIFICATIVO_GARA: \"" + codiceIdentificativoGara + "\"");
            logger.debug("CF_COMMITTENTE: \"" + cfCommittente + "\"");
            logger.debug("NUMERO_CONTRATTO: \"" + numeroContratto + "\"");
            logger.debug("RESPONSABILE_NOMINATIVO: \"" + responsabileNominativo + "\"");
            logger.debug("RESPONSABILE_TELEFONO: \"" + responsabileTelefono + "\"");
            logger.debug("RESPONSABILE_EMAIL: \"" + responsabileEmail + "\"");            
            
            return;
        } // else   
    }

    @Value("${log_conf_params}")
    Boolean logConfParams;
    @Value("${ID_FLUSSO}")
    String idFlusso;
    @Value("${NOME_FLUSSO}")
    String nomeFlusso;
    @Value("${MITTENTE_FLUSSO}")
    String mittenteFlusso;
    @Value("${DATA_CREAZIONE_FLUSSO}")
    String dataCreazioneFlusso;
    @Value("${DESTINATARIO_FLUSSO}")
    String destinatarioFlusso;
    @Value("${TIPO_FLUSSO}")
    String tipoFlusso;
    @Value("${CODICE_IDENTIFICATIVO_GARA}")
    String codiceIdentificativoGara;
    @Value("${DENOMINAZIONE_COMMITTENTE}")
    String denominazioneCommittente;
    @Value("${CF_COMMITTENTE}")
    String cfCommittente;
    @Value("${NUMERO_CONTRATTO}")
    String numeroContratto;
    @Value("${RESPONSABILE_NOMINATIVO}")
    String responsabileNominativo;
    @Value("${RESPONSABILE_TELEFONO}")
    String responsabileTelefono;
    @Value("${RESPONSABILE_CELLULARE}")
    String responsabileCellulare;
    @Value("${RESPONSABILE_EMAIL}")
    String responsabileEmail;

} // class GenAdeFilesDescritta
