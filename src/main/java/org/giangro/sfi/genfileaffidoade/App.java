/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.giangro.sfi.genfileaffidoade;

import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 *
 * @author Alex
 */
@ComponentScan(basePackages = "org.giangro.sfi")
@Configuration
@PropertySource("classpath:gen_adefiles.properties")
@PropertySource("classpath:gen_adefiles_descr.properties")
@PropertySource("classpath:gen_adefiles_indescr.properties")
@PropertySource("classpath:gen_adefiles_sintetica.properties")
public class App {

    final static Logger logger
            = LoggerFactory.getLogger(App.class);

    final static String FILE_TYPE_DESCRITTA = "D";
    final static String FILE_TYPE_INDESCRITTA = "I";
    final static String FILE_TYPE_SINTETICA = "S";
    final static String FILE_TYPE_DEFAULT = FILE_TYPE_DESCRITTA;

    public static void main(String[] args) {
        logger.info("=================================================");
        logger.debug("Initializing spring framework");
        AnnotationConfigApplicationContext ctx = null;
        try {
            ctx = new AnnotationConfigApplicationContext(App.class);
            App app = ctx.getBean(App.class);
            app.doStart(args);
        } // try
        finally {
            if (ctx != null) {
                ctx.close();
                logger.info("exit");
            } // if
        } // finally

    }

    private void doStart(String[] args) {

        final CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);

        } catch (CmdLineException clEx) {
            logger.error("ERROR: Unable to parse command-line options: " + clEx);
            System.exit(-1);
        }

        if (type == null) {
            logger.info("setting file type to default: " + FILE_TYPE_DEFAULT);
            type = FILE_TYPE_DEFAULT;
        } // if

        logger.info("Starting ADE File Generation Tool version " + this.version);
        logger.debug("file type: " + type);

        try {

            if (FILE_TYPE_INDESCRITTA.equals(type)) {
                this.genAdeFilesIndescritta
                        .generate();
            } //if
            else if (FILE_TYPE_DESCRITTA.equals(type)) {
                this.genAdeFilesDescritta
                        .generate();
            } // else if
            else {
                this.GenAdeFilesSintetica
                        .generate();

            }
        } // try
        catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
        } // catch
    }

    @Option(name = "-t", aliases = "--type", usage = "D=descritta,I=indescritta,S=sintetica", required = false)
    private String type;

    @Value("${version}")
    private String version;

    @Autowired
    private GenAdeFilesDescritta genAdeFilesDescritta;

    @Autowired
    private GenAdeFilesIndescritta genAdeFilesIndescritta;
    
    @Autowired
    private GenAdeFilesSintetica GenAdeFilesSintetica;

} // class App
