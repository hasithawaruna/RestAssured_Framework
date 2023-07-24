package org.exercise;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Random;

/**
 * Created by Hasitha
 * Project : WebServiceAutomation
 * Date: 7/9/2023
 * Time: 5:22 AM
 */
public class commonMethods {

    //Create Logger object
    public Logger logger = LogManager.getLogger(commonMethods.class);

    //Below method will return random status values as Strings
    public String getRandomStatus() {

        String[] status = {"available", "pending", "sold"}; //Create Array and assign status values

        Random RN = new Random(status.length); //Create object from random Class

        int randomIndex = RN.nextInt(status.length); //Create int between array size and store

        logger.info("Status has selected randomly");

        return status[randomIndex]; //Return element of given index


    }


}
