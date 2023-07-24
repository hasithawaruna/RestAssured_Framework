package org.utils.ReadProperties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by Hasitha
 * Project : WebServiceAutomation
 * Date: 7/8/2023
 * Time: 7:27 PM
 */
public class ReadProperties {

    public Logger logger = LogManager.getLogger(ReadProperties.class);

    public ReadProperties() {

        //Calling property reading method automatically when creating a new object
        loadAllProperties();
    }

    //Create a public property
    public Properties prop;

    //Read property file
    public void loadAllProperties() {

        prop = new Properties();

        try {

            //Providing property file directory
            String fileName = System.getProperty("user.dir") + "/src/main/resources/property_files/env.properties";
            prop.load(new FileInputStream((fileName))); //Create FileInputStream and read $file, then assign to a property
            logger.info("Property file loaded successfully");


        } catch (Exception exp) {

            logger.error("Property file error",exp);
            throw new RuntimeException("Property File Not Found");

        }

    }

    //Read and return property values by key
    public String getValues(String key) {

        return prop.getProperty(key);

    }


}
