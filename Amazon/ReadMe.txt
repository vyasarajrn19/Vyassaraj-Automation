
TestNg Framework for Mobile Applications Automation with Appium

Required Setup :
Java
Android Studio
Setting up of SDK path in environment variables
Node.js
Appium

Execution Flow:
		testng.xml 
		|
		

* **src/main/java/amazonBase/BaseClass.java** - All the desired capabilities with respect to Appium is defined .
* **src/main/java/amazonBase/TestData.java** - All the test data used in the project are defined.
* **/src/main/java/amazonBase/Uitilities.java** - All the common methods w.r.t framework like taking screen shot , get date and time , scroll, etc are defined 
* **src/main/java/amazonBase/global.properties** - It contains the all variables like apk ,device used in project which is configured in base class 
* **src/main/java/pageObjects** - It contains all the pages where variables are declared and business logic is written using POM
* **src/test/java/appiumTest** - All the test scripts are written here.
* **Amazon/Reports** - HTML Extent reports are generated here with date and time stamp with screen shot for failed test cases 
* **Amazon/log** - Application user defined logs will be generated here
* **Amazon/Screenshots** Screen shots is created for failed scenarios and same is also attached in reports

