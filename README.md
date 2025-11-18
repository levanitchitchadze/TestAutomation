**Test Automation Framework Java | Appium | Selenium | RestAssured | TestNG | Cucumber**
Project Overview 
This is a multifuncional test automation framework, that provides support for Web, Mobile and API test.

**Tech Stack**

Java 21+

Selenium WebDriver

Appium (Android / iOS)

RestAssured

TestNG

Cucumber (BDD, Gherkin)

Maven

**Project Structure **

<img width="929" height="733" alt="image" src="https://github.com/user-attachments/assets/12075651-bfb8-4180-b331-c012d9f369cb" />



**Requirements**

Java 21+

Maven 3.6+

Appium Server 3.0.0+

Java Appium Client 9.2.2+

Android SDK 12+

Node.js 20.19+



**Installation:**

git clone https://github.com/levanitchitchadze/TestAutomationFramework.git 

cd TestAutomationFramework 

mvn clean install

**Running Tests Run TestNG: **

mvn clean test -Ptestng

**Run Cucumber Scenarios: **
mvn clean test -Pcucumber

**Configuration:**

.env file example:

username = "j.cerry" // your Tbc mobile bank username

username = "********" // your Tbc mobile bank password

APPIUM_SERVER_PATH = "/usr/local/lib/node_modules/appium/build/lib/main.js" //If you have path added to environmental variables you don't need it

NODE_EXECUTABLE_FILE_PATH = "/home/usr/.nvm/versions/node/v20.xx.x/bin/node" //If you have path added to environmental variables you don't need it

DEVICE_SERIAL_NUMBER = "18e7eb0c" // That is physical device serial number which is connected to your computer. If you don't know serial number exec command: adb devices

ANDROID_HOME = "/home/usr/Android/Sdk" //If you have path added to environmental variables you don't need it

baseURI = "https://reqres.in/api"

Reporting

CI/CD Simple Integration With:

Jenkins

GitHub Actions

GitLab CI

Azure DevOps
