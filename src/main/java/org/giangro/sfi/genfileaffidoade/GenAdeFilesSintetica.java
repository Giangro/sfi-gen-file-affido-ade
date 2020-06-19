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

    final static String NUMERO_ORDINE_TAG
            = "NUMERO_ORDINE";

    final static String DATA_ORDINE_TAG
            = "DATA_ORDINE";

    final static String NUM_TOT_DISTINTE_TAG
            = "NUM_TOT_DISTINTE";

    final static String NUM_TOT_DOCUMENTI_TAG
            = "NUM_TOT_DOCUMENTI";

    final static String FLAG_PICCO_TAG
            = "FLAG_PICCO";

    final static String CODICE_RECAPITISTA_TAG
            = "CODICE_RECAPITISTA";

    final static String CODICE_STAMPATORE_TAG
            = "CODICE_STAMPATORE";

    final static String DATA_SPEDIZIONE_TAG
            = "DATA_SPEDIZIONE";

    final static String CODICE_PRENOTAZIONE_TAG
            = "CODICE_PRENOTAZIONE";

    final static String NOTE_TAG
            = "NOTE";

    final static String DISTINTE_TAG
            = "DISTINTE";

    final static String DISTINTA_TYPE_TAG
            = "DISTINTA_TYPE";

    final static String ID_DISTINTA_TAG
            = "ID_DISTINTA";

    final static String TIPO_DISTINTA_TAG
            = "TIPO_DISTINTA";

    final static String LUOGO_CONSEGNA_TAG
            = "LUOGO_CONSEGNA";

    final static String CODICE_IPA_CDC_TAG
            = "CODICE_IPA_CDC";

    final static String DENOMINAZIONE_CDC_TAG
            = "DENOMINAZIONE_CDC";

    final static String CODICE_AMBITO_TAG
            = "CODICE_AMBITO";

    final static String TIPO_MODELLO_TAG
            = "TIPO_MODELLO";

    final static String INDIRIZZO_RITORNO_TAG
            = "INDIRIZZO_RITORNO";

    final static String INDIRIZZO_TYPE_TAG
            = "INDIRIZZO_TYPE";

    final static String RIGA1_TAG
            = "RIGA1";

    final static String RIGA2_TAG
            = "RIGA2";

    final static String RIGA3_TAG
            = "RIGA3";

    final static String RIGA4_TAG
            = "RIGA4";

    final static String RIGA5_TAG
            = "RIGA5";

    final static String CAP_TAG
            = "CAP";

    final static String STATO_ESTERO_TAG
            = "STATO_ESTERO";

    final static String TIPO_SPEDIZIONE_TAG
            = "TIPO_SPEDIZIONE";

    final static String CODICE_PRODOTTO_FORNITORE_TAG
            = "CODICE_PRODOTTO_FORNITORE";

    final static String CODICE_LOTTO_TERRITORIALE_TAG
            = "CODICE_LOTTO_TERRITORIALE";

    final static String NUM_DOCUMENTI_DISTINTA_TAG
            = "NUM_DOCUMENTI_DISTINTA";

    final static String FLAG_COPERTURA_CAP_TAG
            = "FLAG_COPERTURA_CAP";

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
            responsabileElement.appendChild(responsabileTypeElement);

            // responsabile nominativo
            Element responsabileNominativoElement;
            responsabileNominativoElement = doc.createElement(GenAdeFilesSintetica.NOMINATIVO_TAG);
            responsabileNominativoElement.appendChild(doc.createTextNode(responsabileNominativo));
            responsabileTypeElement.appendChild(responsabileNominativoElement);

            // responsabile telefono
            Element responsabileTelefonoElement;
            responsabileTelefonoElement = doc.createElement(GenAdeFilesSintetica.TELEFONO_TAG);
            responsabileTelefonoElement.appendChild(doc.createTextNode(responsabileTelefono));
            responsabileTypeElement.appendChild(responsabileTelefonoElement);

            // responsabile cellulare
            Element responsabileCellulareElement;
            responsabileCellulareElement = doc.createElement(GenAdeFilesSintetica.CELLULARE_TAG);
            responsabileCellulareElement.appendChild(doc.createTextNode(responsabileCellulare));
            responsabileTypeElement.appendChild(responsabileCellulareElement);

            // responsabile email
            Element responsabileEMailElement;
            responsabileEMailElement = doc.createElement(GenAdeFilesSintetica.EMAIL_TAG);
            responsabileEMailElement.appendChild(doc.createTextNode(responsabileEmail));
            responsabileTypeElement.appendChild(responsabileEMailElement);

            // more info
            Element moreInfoElement;
            moreInfoElement = doc.createElement(GenAdeFilesSintetica.MORE_INFO_TAG);
            moreInfoElement.appendChild(doc.createTextNode(moreInfo));
            headerTypeElement.appendChild(moreInfoElement);

            // numero ordine
            Element numeroOrdineElement;
            numeroOrdineElement = doc.createElement(GenAdeFilesSintetica.NUMERO_ORDINE_TAG);
            numeroOrdineElement.appendChild(doc.createTextNode(numeroOrdine));
            rootElement.appendChild(numeroOrdineElement);

            // data ordine
            Element dataOrdineElement;
            dataOrdineElement = doc.createElement(GenAdeFilesSintetica.DATA_ORDINE_TAG);
            dataOrdineElement.appendChild(doc.createTextNode(dataOrdine));
            rootElement.appendChild(dataOrdineElement);

            // num tot distinte
            Element numTotDistinteElement;
            numTotDistinteElement = doc.createElement(GenAdeFilesSintetica.NUM_TOT_DISTINTE_TAG);
            numTotDistinteElement.appendChild(doc.createTextNode(numTotDistinte.toString()));
            rootElement.appendChild(numTotDistinteElement);

            // num tot documenti
            Element numTotDocumentiElement;
            numTotDocumentiElement = doc.createElement(GenAdeFilesSintetica.NUM_TOT_DOCUMENTI_TAG);
            numTotDocumentiElement.appendChild(doc.createTextNode(numTotDocumenti.toString()));
            rootElement.appendChild(numTotDocumentiElement);

            // flag picco
            Element flagPiccoElement;
            flagPiccoElement = doc.createElement(GenAdeFilesSintetica.FLAG_PICCO_TAG);
            flagPiccoElement.appendChild(doc.createTextNode(flagPicco));
            rootElement.appendChild(flagPiccoElement);

            // codice recapitista
            Element codiceRecapitistaElement;
            codiceRecapitistaElement = doc.createElement(GenAdeFilesSintetica.CODICE_RECAPITISTA_TAG);
            codiceRecapitistaElement.appendChild(doc.createTextNode(codiceRecapitista));
            rootElement.appendChild(codiceRecapitistaElement);

            // codice stampatore
            Element codiceStampatoreElement;
            codiceStampatoreElement = doc.createElement(GenAdeFilesSintetica.CODICE_STAMPATORE_TAG);
            codiceStampatoreElement.appendChild(doc.createTextNode(codiceStampatore));
            rootElement.appendChild(codiceStampatoreElement);

            // data spedizione
            Element dataSpedizioneElement;
            dataSpedizioneElement = doc.createElement(GenAdeFilesSintetica.DATA_SPEDIZIONE_TAG);
            dataSpedizioneElement.appendChild(doc.createTextNode(dataSpedizione));
            rootElement.appendChild(dataSpedizioneElement);

            // codice prenotazione
            Element codicePrenotazioneElement;
            codicePrenotazioneElement = doc.createElement(GenAdeFilesSintetica.CODICE_PRENOTAZIONE_TAG);
            codicePrenotazioneElement.appendChild(doc.createTextNode(codicePrenotazione));
            rootElement.appendChild(codicePrenotazioneElement);

            // note
            Element noteElement;
            noteElement = doc.createElement(GenAdeFilesSintetica.NOTE_TAG);
            noteElement.appendChild(doc.createTextNode(note));
            rootElement.appendChild(noteElement);

            // sezione distinte            
            // distinte
            Element distinteElement;
            distinteElement = doc.createElement(GenAdeFilesSintetica.DISTINTE_TAG);
            headerTypeElement.appendChild(distinteElement);

            for (int ndist = 0; ndist < numDistinte; ndist++) {

                // distinta type
                Element distintaTypeElement;
                distintaTypeElement = doc.createElement(GenAdeFilesSintetica.DISTINTA_TYPE_TAG);
                distinteElement.appendChild(distintaTypeElement);

                // id distinta
                Element idDistintaElement;
                idDistintaElement = doc.createElement(GenAdeFilesSintetica.ID_DISTINTA_TAG);
                idDistintaElement.appendChild(doc.createTextNode(String.valueOf(startIdDistinte + ndist)));
                distintaTypeElement.appendChild(idDistintaElement);

                // tipo distinta
                Element tipoDistintaElement;
                tipoDistintaElement = doc.createElement(GenAdeFilesSintetica.TIPO_DISTINTA_TAG);
                tipoDistintaElement.appendChild(doc.createTextNode(tipoDistinta));
                distintaTypeElement.appendChild(tipoDistintaElement);

                // luogo consegna
                Element luogoConsegnaElement;
                luogoConsegnaElement = doc.createElement(GenAdeFilesSintetica.LUOGO_CONSEGNA_TAG);
                luogoConsegnaElement.appendChild(doc.createTextNode(luogoConsegna));
                distintaTypeElement.appendChild(luogoConsegnaElement);

                // codice ipa cdc
                Element codiceIpaCdcElement;
                codiceIpaCdcElement = doc.createElement(GenAdeFilesSintetica.CODICE_IPA_CDC_TAG);
                codiceIpaCdcElement.appendChild(doc.createTextNode(codiceIpaCdc));
                distintaTypeElement.appendChild(codiceIpaCdcElement);

                // denominazione cdc
                Element denominazioneCdcElement;
                denominazioneCdcElement = doc.createElement(GenAdeFilesSintetica.DENOMINAZIONE_CDC_TAG);
                denominazioneCdcElement.appendChild(doc.createTextNode(denominazioneCdc));
                distintaTypeElement.appendChild(denominazioneCdcElement);

                // codice ambito
                Element codiceAmbitoElement;
                codiceAmbitoElement = doc.createElement(GenAdeFilesSintetica.CODICE_AMBITO_TAG);
                codiceAmbitoElement.appendChild(doc.createTextNode(codiceAmbito));
                distintaTypeElement.appendChild(codiceAmbitoElement);

                // tipo modello
                Element tipoModelloElement;
                tipoModelloElement = doc.createElement(GenAdeFilesSintetica.TIPO_MODELLO_TAG);
                tipoModelloElement.appendChild(doc.createTextNode(tipoModello));
                distintaTypeElement.appendChild(tipoModelloElement);

                // indirizzo ritorno
                Element indirizzoRitornoElement;
                indirizzoRitornoElement = doc.createElement(GenAdeFilesSintetica.INDIRIZZO_RITORNO_TAG);
                distintaTypeElement.appendChild(indirizzoRitornoElement);

                // indirizzo type
                Element indirizzoTypeElement;
                indirizzoTypeElement = doc.createElement(GenAdeFilesSintetica.INDIRIZZO_TYPE_TAG);
                indirizzoRitornoElement.appendChild(indirizzoTypeElement);

                // RIGA1
                Element riga1Element;
                riga1Element = doc.createElement(GenAdeFilesSintetica.RIGA1_TAG);
                riga1Element.appendChild(doc.createTextNode(riga1));
                indirizzoTypeElement.appendChild(riga1Element);

                // RIGA2
                Element riga2Element;
                riga2Element = doc.createElement(GenAdeFilesSintetica.RIGA2_TAG);
                riga2Element.appendChild(doc.createTextNode(riga2));
                indirizzoTypeElement.appendChild(riga2Element);

                // RIGA3
                Element riga3Element;
                riga3Element = doc.createElement(GenAdeFilesSintetica.RIGA3_TAG);
                riga3Element.appendChild(doc.createTextNode(riga3));
                indirizzoTypeElement.appendChild(riga3Element);

                // RIGA4
                Element riga4Element;
                riga4Element = doc.createElement(GenAdeFilesSintetica.RIGA4_TAG);
                riga4Element.appendChild(doc.createTextNode(riga4));
                indirizzoTypeElement.appendChild(riga4Element);

                // RIGA5
                Element riga5Element;
                riga5Element = doc.createElement(GenAdeFilesSintetica.RIGA5_TAG);
                riga5Element.appendChild(doc.createTextNode(riga5));
                indirizzoTypeElement.appendChild(riga5Element);
                
                // cap
                Element capElement;
                capElement = doc.createElement(GenAdeFilesSintetica.CAP_TAG);
                capElement.appendChild(doc.createTextNode(cap));
                indirizzoTypeElement.appendChild(capElement);
                
                // stato estero
                Element statoEsteroElement;
                statoEsteroElement = doc.createElement(GenAdeFilesSintetica.STATO_ESTERO_TAG);
                statoEsteroElement.appendChild(doc.createTextNode(statoEstero));
                indirizzoTypeElement.appendChild(statoEsteroElement);

                // tipo spedizione
                Element tipoSpedizioneElement;
                tipoSpedizioneElement = doc.createElement(GenAdeFilesSintetica.TIPO_SPEDIZIONE_TAG);
                tipoSpedizioneElement.appendChild(doc.createTextNode(tipoSpedizione));
                distintaTypeElement.appendChild(tipoSpedizioneElement);
                
                // codice prodotto fornitore
                Element codiceProdottoFornitoreElement;
                codiceProdottoFornitoreElement = doc.createElement(GenAdeFilesSintetica.CODICE_PRODOTTO_FORNITORE_TAG);
                codiceProdottoFornitoreElement.appendChild(doc.createTextNode(codiceProdottoFornitore));
                distintaTypeElement.appendChild(codiceProdottoFornitoreElement);
                
                // codice lotto territoriale
                Element codiceLottoTerritorialeElement;
                codiceLottoTerritorialeElement = doc.createElement(GenAdeFilesSintetica.CODICE_LOTTO_TERRITORIALE_TAG);
                codiceLottoTerritorialeElement.appendChild(doc.createTextNode(codiceLottoTerritoriale));
                distintaTypeElement.appendChild(codiceLottoTerritorialeElement);
                
                // num documenti distinta
                Element numDocumentiDistintaElement;
                numDocumentiDistintaElement = doc.createElement(GenAdeFilesSintetica.NUM_DOCUMENTI_DISTINTA_TAG);
                numDocumentiDistintaElement.appendChild(doc.createTextNode(numDocumentiDistinta.toString()));
                distintaTypeElement.appendChild(numDocumentiDistintaElement);
                
                // flag copertura cap
                Element flagCoperturaCapElement;
                flagCoperturaCapElement = doc.createElement(GenAdeFilesSintetica.FLAG_COPERTURA_CAP_TAG);
                flagCoperturaCapElement.appendChild(doc.createTextNode(flagCoperturaCap));
                distintaTypeElement.appendChild(flagCoperturaCapElement);

                // more info distinta type
                Element moreInfoDistintaTypeElement;
                moreInfoDistintaTypeElement = doc.createElement(GenAdeFilesSintetica.MORE_INFO_TAG);
                moreInfoDistintaTypeElement.appendChild(doc.createTextNode(moreInfoDistintaType));
                distintaTypeElement.appendChild(moreInfoDistintaTypeElement);
                
                
                
            } // distinte type

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(destinationPath + "/" + fileNameSintetica));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

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
            logger.debug("file_name_sintetica: \"" + fileNameSintetica + "\"");
            logger.debug("destination_path: \"" + destinationPath + "\"");
            logger.debug("start_id_distinte: \"" + startIdDistinte + "\"");
            logger.debug("num_distinte: \"" + numDistinte + "\"");
            logger.debug("NOME_FLUSSO: \"" + nomeFlusso + "\"");
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
            logger.debug("MORE_INFO: \"" + moreInfo + "\"");
            logger.debug("NUMERO_ORDINE: \"" + numeroOrdine + "\"");
            logger.debug("DATA_ORDINE: \"" + dataOrdine + "\"");
            logger.debug("NUM_TOT_DISTINTE: \"" + numTotDistinte.toString() + "\"");
            logger.debug("NUM_TOT_DOCUMENTI: \"" + numTotDocumenti.toString() + "\"");
            logger.debug("FLAG_PICCO: \"" + flagPicco + "\"");
            logger.debug("CODICE_RECAPITISTA: \"" + codiceRecapitista + "\"");
            logger.debug("CODICE_STAMPATORE: \"" + codiceStampatore + "\"");
            logger.debug("DATA_SPEDIZIONE: \"" + dataSpedizione + "\"");
            logger.debug("CODICE_PRENOTAZIONE: \"" + codicePrenotazione + "\"");
            logger.debug("NOTE: \"" + note + "\"");
            logger.debug("TIPO_DISTINTA: \"" + tipoDistinta + "\"");
            logger.debug("LUOGO_CONSEGNA: \"" + luogoConsegna + "\"");
            logger.debug("CODICE_IPA_CDC: \"" + codiceIpaCdc + "\"");
            logger.debug("DENOMINAZIONE_CDC: \"" + denominazioneCdc + "\"");
            logger.debug("CODICE_AMBITO: \"" + codiceAmbito + "\"");
            logger.debug("TIPO_MODELLO: \"" + tipoModello + "\"");
            logger.debug("RIGA1: \"" + riga1 + "\"");
            logger.debug("RIGA2: \"" + riga2 + "\"");
            logger.debug("RIGA3: \"" + riga3 + "\"");
            logger.debug("RIGA4: \"" + riga4 + "\"");
            logger.debug("RIGA5: \"" + riga5 + "\"");
            logger.debug("CAP: \"" + cap + "\"");
            logger.debug("STATO_ESTERO: \"" + statoEstero + "\"");
            logger.debug("TIPO_SPEDIZIONE: \"" + tipoSpedizione + "\"");
            logger.debug("CODICE_PRODOTTO_FORNITORE: \"" + codiceProdottoFornitore + "\"");
            logger.debug("CODICE_LOTTO_TERRITORIALE: \"" + codiceLottoTerritoriale + "\"");
            logger.debug("NUM_DOCUMENTI_DISTINTA: \"" + numDocumentiDistinta.toString() + "\"");
            logger.debug("FLAG_COPERTURA_CAP: \"" + flagCoperturaCap + "\"");

            return;
        } // else   
    }

    @Value("${log_conf_params}")
    Boolean logConfParams;
    @Value("${file_name_sintetica}")
    String fileNameSintetica;
    @Value("${destination_path}")
    String destinationPath;
    @Value("${start_id_distinte}")
    Integer startIdDistinte;
    @Value("${num_distinte}")
    Integer numDistinte;
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
    @Value("${MORE_INFO}")
    String moreInfo;
    @Value("${NUMERO_ORDINE}")
    String numeroOrdine;
    @Value("${DATA_ORDINE}")
    String dataOrdine;
    @Value("${NUM_TOT_DISTINTE}")
    Integer numTotDistinte;
    @Value("${NUM_TOT_DOCUMENTI}")
    Integer numTotDocumenti;
    @Value("${FLAG_PICCO}")
    String flagPicco;
    @Value("${CODICE_RECAPITISTA}")
    String codiceRecapitista;
    @Value("${CODICE_STAMPATORE}")
    String codiceStampatore;
    @Value("${DATA_SPEDIZIONE}")
    String dataSpedizione;
    @Value("${CODICE_PRENOTAZIONE}")
    String codicePrenotazione;
    @Value("${NOTE}")
    String note;
    @Value("${TIPO_DISTINTA}")
    String tipoDistinta;
    @Value("${LUOGO_CONSEGNA}")
    String luogoConsegna;
    @Value("${CODICE_IPA_CDC}")
    String codiceIpaCdc;
    @Value("${DENOMINAZIONE_CDC}")
    String denominazioneCdc;
    @Value("${CODICE_AMBITO}")
    String codiceAmbito;
    @Value("${TIPO_MODELLO}")
    String tipoModello;
    @Value("${RIGA1}")
    String riga1;
    @Value("${RIGA2}")
    String riga2;
    @Value("${RIGA3}")
    String riga3;
    @Value("${RIGA4}")
    String riga4;
    @Value("${RIGA5}")
    String riga5;
    @Value("${CAP}")
    String cap;
    @Value("${STATO_ESTERO}")
    String statoEstero;
    @Value("${TIPO_SPEDIZIONE}")
    String tipoSpedizione;
    @Value("${CODICE_PRODOTTO_FORNITORE}")
    String codiceProdottoFornitore;
    @Value("${CODICE_LOTTO_TERRITORIALE}")
    String codiceLottoTerritoriale;   
    @Value("${NUM_DOCUMENTI_DISTINTA}")
    Integer numDocumentiDistinta; 
    @Value("${FLAG_COPERTURA_CAP}")
    String flagCoperturaCap;
    @Value("${MORE_INFO_DISTINTA_TYPE}")
    String moreInfoDistintaType;
    
} // class GenAdeFilesDescritta
