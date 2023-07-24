Pet Store Webservice Automation

#Prerequisites
Java 11 Development Kit (JDK) installed
Maven installed
IDE - IntelliJ installed


--------------------------------------------------------------------------------------------------------------------

#Getting Started
Please follow below sections,

#To Import Project : 
1.Extract "PetStore_webautomation.zip" project to a directory of your choice.
2.Open IntelliJ IDEA.
3.From the IntelliJ IDEA main menu, select "Open" and navigate to the extracted project directory. Select the project's pom.xml file and click "Open as a Project".
4.IntelliJ IDEA will import the project and download the necessary dependencies specified in the pom.xml file. Wait for the process to complete.

#Run Tests :
1.Go to terminal > open "Windows PowerShell" window and navigate to project folder '\WebServiceAutomation'
2.Run "mvn clean install" command to clean and run the project. This step may take some time, depending on your internet connection speed and project size. Also The test execution progress and results will be displayed in the console.
3.Maven will run the tests using TestNG and generate the test reports. The test execution progress and results will be displayed in the console.
4.After the test execution completes, navigate to the 'WebServiceAutomation/allure-results' directory and Verify that the Allure report json files are generated.


#Generate HTML Report (Allure) for the previous execution :
1.Go to terminal > open "Windows PowerShell" window and navigate to project folder '\WebServiceAutomation'
2.Run 'allure generate ./allure-results --clean' command.
3.After process completes, 'index.html file will generate in 'WebServiceAutomation/allure-report' directory. 

#View Allure Report
1.Navigate to the 'WebServiceAutomation/allure-results' directory.
2.Click on 'index.html' file and open in browser.

#Re-run Tests :
1.Navigate to the 'WebServiceAutomation/allure-results' directory and clear the contents.
2.Follow steps mentioned above #Run Tests section
2.Follow steps mentioned above #Generate HTML Report (Allure) for the previous execution section
2.Follow steps mentioned above #View Allure Report section


---------------------------------------------------------------------------------------------------------------------

#Configuration

Testcase files location -> 'src/test/java/org/testCases'
Environment properties customization -> 'src/main/resources/property_fIles/env.properties' - (Used to Add or Edit properties)
Json Schema Files location -> 'src/main/resources/json_schema_files' - (Used for validate each endpoint Jason schema formats)
Json test data location -> 'src/main/resources/json_testdata_files' - (Used for correlation between endpoints. Data will be write and Read to this file runtime)
test.xml file - src/test.xml - (Used for TestNG data execution.)
