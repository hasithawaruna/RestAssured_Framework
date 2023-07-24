package org.testCases;

import com.github.javafaker.Faker;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.exercise.commonMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.utils.ReadProperties.ReadProperties;
import org.utils.jsonIO.JsonReadWrite;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PetManagerTest {

    ReadProperties RP = new ReadProperties();  //Create property object

    JsonReadWrite RW = new JsonReadWrite();  //Create property object

    Faker fake = new Faker();  //Create faker object

    commonMethods CM = new commonMethods();  //Create commonMethods object

    JsonPath jp = JsonReadWrite.readJsonData(); //Create JsonReadWrite object

    private final Logger log = LogManager.getLogger(PetManagerTest.class); //Create Log4JOb


    @Test(priority = 1) //priority : execution priority
    @Severity(SeverityLevel.CRITICAL) //severity : Priority for Allure report
    public void TestCase_001_VerifyAddSinglePet() {

        log.info("TestCase_001_VerifyAddSinglePet has started");

        //Get random values using faker
        String category = fake.name().firstName();
        String tag_name = fake.color().name();
        String name = fake.dog().name();
        int categoryID = fake.number().numberBetween(1, 100000);
        int pet_id = fake.number().numberBetween(1, 100000);
        int tag_id = fake.number().numberBetween(1, 100000);

        String status = CM.getRandomStatus(); //Get values from commonMethods.getRandomStatus method
        String img = RP.getValues("IMG"); //Read image from property file

        //Save request body Json as String
        String Req = "{\"id\":" + pet_id + ",\"category\":{\"id\":" + categoryID + ",\"name\":\"" + category + "\"},\"name\":\"" + name + "\",\"photoUrls\":[\"" + img + "\"],\"tags\":[{\"id\":" + tag_id + ",\"name\":\"" + tag_name + "\"}],\"status\":\"" + status + "\"}";

        //Set payload, tryout endpoint and save response
        Response Res = RestAssured.given().
                contentType(ContentType.JSON).
                body(Req).
                filter(new AllureRestAssured()).
                filter(new RequestLoggingFilter()). //Add request to allure report
                filter(new ResponseLoggingFilter()). //Add response to allure report
                post(RP.getValues("URL") + "/v2/pet"); //Tryout URL

        //Assertions
        //Response Json schema validation
        Res.then().assertThat().body(matchesJsonSchemaInClasspath("json_schema_files/add_pet_schema.json"));
        //Response code validation
        Assert.assertEquals(Res.getStatusCode(), 200);
        //Response data  validation
        Assert.assertEquals(Res.jsonPath().getInt("id"), pet_id);

        log.info("TestCase_001_VerifyAddSinglePet has Ended");

    }

    @Test(invocationCount = 4, priority = 2)
    //invocationCount: refers to iterations of a testcase, priority : execution priority
    @Severity(SeverityLevel.CRITICAL) //severity : Priority for Allure report
    public void TestCase_002_VerifyAddMultiplePets() {

        log.info("TestCase_002_VerifyAddMultiplePets has started");

        //Get random values using faker
        String category = fake.name().firstName();
        String tag_name = fake.color().name();
        String name = fake.dog().name();
        int categoryID = fake.number().numberBetween(1, 100000);
        int pet_id = fake.number().numberBetween(1, 100000);
        int tag_id = fake.number().numberBetween(1, 100000);

        String status = CM.getRandomStatus(); //Get values from commonMethods.getRandomStatus method
        String img = RP.getValues("IMG"); //Read image from property file


        //Save request body Json as String
        String Req = "{\"id\":" + pet_id + ",\"category\":{\"id\":" + categoryID + ",\"name\":\"" + category + "\"},\"name\":\"" + name + "\",\"photoUrls\":[\"" + img + "\"],\"tags\":[{\"id\":" + tag_id + ",\"name\":\"" + tag_name + "\"}],\"status\":\"" + status + "\"}";

        //Set payload, tryout endpoint and save response
        Response Res = (Response) RestAssured.
                given().
                contentType(ContentType.JSON).
                accept("application/json").
                body(Req).
                filter(new AllureRestAssured()).
                filter(new RequestLoggingFilter()). //Add request to allure report
                filter(new ResponseLoggingFilter()). //Add Response to allure report
                post(RP.getValues("URL") + "/v2/pet"); //Tryout URL

        //Assertions
        //Response Json schema validation
        Res.then().assertThat().body(matchesJsonSchemaInClasspath("json_schema_files/add_pet_schema.json"));
        //Response code validation
        Assert.assertEquals(Res.jsonPath().getInt("id"), pet_id);
        //Response data  validation
        Assert.assertEquals(Res.getStatusCode(), 200);


        String ResponseBody = Res.getBody().asString(); //Save response body as string
        JsonPath JP = new JsonPath(ResponseBody); //Create Obj and assign response
        String value = JP.get("id").toString(); //get value by given JsonPath
        RW.saveToJson(value); //Write to Json with retrieved value

        log.info("TestCase_002_VerifyAddMultiplePets has ended");

    }

    @Test(priority = 3) //priority : execution priority
    @Severity(SeverityLevel.CRITICAL) //severity : Priority for Allure report
    //dependsOnMethods = {"TestCase_002_VerifyAddMultiplePets"},
    public void TestCase_003_VerifyUpdatePet() {

        log.info("TestCase_003_VerifyUpdatePet has started");


        JsonPath jp = JsonReadWrite.readJsonData(); //Create Jsonpath object and get returned object from JsonReadWrite.readJsonData()
        int ExistingPetId = jp.getInt("id"); //Get required attribute by Jsonpath

        //Get random values using faker
        String category = fake.name().firstName();
        String tag_name = fake.color().name();
        String name = fake.dog().name();
        int categoryID = fake.number().numberBetween(1, 100000);
        int tag_id = fake.number().numberBetween(1, 100000);

        String status = CM.getRandomStatus(); //Get values from commonMethods.getRandomStatus method
        String img = RP.getValues("IMG"); //Read image from property file

        //Save request body Json as String
        String Req = "{\"id\":" + ExistingPetId + ",\"category\":{\"id\":" + categoryID + ",\"name\":\"" + category + "\"},\"name\":\"" + name + "\",\"photoUrls\":[\"" + img + "\"],\"tags\":[{\"id\":" + tag_id + ",\"name\":\"" + tag_name + "\"}],\"status\":\"" + status + "\"}";

        //Set payload, tryout endpoint and save response
        Response Res = (Response) RestAssured.
                given().
                contentType(ContentType.JSON).
                body(Req).
                filter(new AllureRestAssured()).
                filter(new RequestLoggingFilter()). //Add request to allure report
                filter(new ResponseLoggingFilter()). //Add response to allure report
                put(RP.getValues("URL") + "/v2/pet"); //Tryout URL

        //Assertions
        //Response Json schema validation
        Res.then().assertThat().body(matchesJsonSchemaInClasspath("json_schema_files/update_pet_schema.json"));
        //Response code validation
        Assert.assertEquals(Res.getStatusCode(), 200);
        //Response data  validation
        Assert.assertEquals(Res.jsonPath().getInt("id"), ExistingPetId);

        JsonPath JP = new JsonPath(Res.getBody().asString());
        String updatedTagName = JP.getString("tags[0].name");

        if (tag_name.equals(updatedTagName)) {

            RW.saveToJson(ExistingPetId, tag_name, status);

        } else {

            log.info("Expected tag name no found");

        }

        log.info("TestCase_003_VerifyUpdatePet has ended");


    }

    @Test(priority = 4) //priority : execution priority
    @Severity(SeverityLevel.NORMAL) //severity : Priority for Allure report
    //dependsOnMethods = {"TestCase_003_VerifyUpdatePet"},
    public void TestCase_004_VerifyGetPetByStatus() {

        log.info("TestCase_004_VerifyGetPetByStatus has started");

        //Get status from previously saved Json file
        String status = jp.getString("status");

        //Set payload, tryout endpoint and save response
        Response Res = (Response) RestAssured.
                given().
                filter(new AllureRestAssured()).
                filter(new RequestLoggingFilter()). //Add request to allure report
                filter(new ResponseLoggingFilter()). //Add response to allure report
                get(RP.getValues("URL") + "/v2/pet/findByStatus?status=" + status); //Tryout URL

        //Assertions
        //Response Json schema validation
        Res.then().assertThat().body(matchesJsonSchemaInClasspath("json_schema_files/getByStatus_schema.json"));
        //Response code validation
        Assert.assertEquals(Res.getStatusCode(), 200);
        //Response data  validation
        Assert.assertEquals(Res.jsonPath().getString("[0].status"), status);

        log.info("TestCase_004_VerifyGetPetByStatus has ended");
    }

    @Test(priority = 4) //priority : execution priority
    @Severity(SeverityLevel.NORMAL) //severity : Priority for Allure report
    //dependsOnMethods = {"TestCase_003_VerifyUpdatePet"},
    public void TestCase_005_VerifyGetPetByTag() {

        log.info("TestCase_003_VerifyGetPetByTag has started");

        //Get tag from previously saved Json file
        String tagName = jp.getString("tag_name");

        //Set payload, tryout endpoint and save response
        Response Res = (Response) RestAssured.
                given().
                filter(new AllureRestAssured()).
                filter(new RequestLoggingFilter()). //Add request to allure report
                filter(new ResponseLoggingFilter()). //Add response to allure report
                get(RP.getValues("URL") + "/v2/pet/findByTags?tags=" + tagName); //Tryout URL

        //Assertions
        //Response Json schema validation
        Res.then().assertThat().body(matchesJsonSchemaInClasspath("json_schema_files/getByTag_schema.json"));
        //Response code validation
        Assert.assertEquals(Res.getStatusCode(), 200);
        //Response data  validation
        Assert.assertEquals(Res.jsonPath().getString("[0].tags[0].name"), tagName);

        log.info("TestCase_003_VerifyGetPetByTag has ended");


    }

}
