package org.utils.jsonIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.exercise.commonMethods;
import org.json.JSONObject;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import io.restassured.path.json.JsonPath;

/**
 * Created by Hasitha
 * Project : WebServiceAutomation
 * Date: 7/8/2023
 * Time: 9:01 PM
 */
public class JsonReadWrite {


    static Logger logger = LogManager.getLogger(commonMethods.class); //Create Log4j object

    //Below class will create Json files with given $myJsonData data
    public void saveToJson(String myJsonData) {

        JSONObject JO = new JSONObject(); //Create Json object

        JO.put("id", myJsonData); //Put value to Json object

        String jsonFilePath = "/src/main/resources/json_testdata_files/pet_details.json"; //Target file path

        //Create FileWriter object and provide filepath
        try (FileWriter fw = new FileWriter(System.getProperty("user.dir") + jsonFilePath, false)) {


            fw.write(JO.toString()); //Write JSON object into Json file
            logger.info("JSON file write success with updated  " + myJsonData + " value.");


        } catch (IOException exp) {

            logger.error("JSON file write error",exp);
            throw new RuntimeException(exp);

        }


    }

    //Method : saveToJson overloading
    public void saveToJson(int value_1, String value_2, String value_3) {

        JSONObject JO = new JSONObject(); //Create Json object

        //Put values to Json object
        JO.put("id", value_1);
        JO.put("tag_name", value_2);
        JO.put("status", value_3);

        String jsonFilePath = "/src/main/resources/json_testdata_files/pet_details.json";

        //Create FileWriter object and provide filepath
        try (FileWriter fw = new FileWriter(System.getProperty("user.dir") + jsonFilePath, false)) {

            fw.write(JO.toString()); //Write JSON object into Json file
            logger.info("JSON file write success with updated " + value_1 + ", " + value_2 + ", " + value_3 + " values.");

        } catch (IOException exp) {

            logger.error("JSON file write error",exp);
            throw new RuntimeException(exp);

        }


    }

    //Return JSON dataset from External Json file
    public static JsonPath readJsonData() {

        String jsonFilePath = "/src/main/resources/json_testdata_files/pet_details.json"; //Provide Json file path

        try {

            File jsFile = new File(System.getProperty("user.dir") + jsonFilePath); //Create file to read Json data
            FileReader FR = new FileReader(jsFile); //Create FileReader for file

            JsonPath jp = new JsonPath(FR); //Read data and store into a JsonPath object

            return jp; //Return JsonPath object


        } catch (IOException exp) {

            logger.error("JSON file read error",exp);
            throw new RuntimeException(exp);

        }


    }

//    public int readFromJson_2(String attribute) {
//
//
//        String jsonFilePath = "/src/main/resources/json_testdata_files/pet_details.json";
//        try {
//            String jsonContent = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + jsonFilePath)));
//
//            JSONObject JB = new JSONObject(jsonContent);
//
//            this.endpoint = JB.getString("endpoint");
//
//    }
//        catch (IOException exp){
//
//        throw new RuntimeException(exp);
//
//        }
//    }

}
