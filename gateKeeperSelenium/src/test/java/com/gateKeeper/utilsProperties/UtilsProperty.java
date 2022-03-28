package com.gateKeeper.utilsProperties;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

public class UtilsProperty {

    private static final Logger logger = Logger.getLogger(UtilsProperty.class);
    public static Properties properties;


    @SuppressWarnings("SameReturnValue")
    public String loadProperties() {

        try {
            properties = new Properties();
            File file = new File("./lib/env/project.properties");
            FileReader reader = new FileReader(file);
            properties.load(reader);

            Properties props = new Properties();
            props.load(new FileInputStream("./lib/env/log4j.properties"));
            PropertyConfigurator.configure(props);
        } catch (Exception e) {

            logger.error("Load Properties Failure|Reason: " + ExceptionUtils.getMessage(e));
        }
        return null;
    }

    public static String getProperties(String data) {

        return properties.getProperty(data);
    }

}