BuyMeTest project with extras.

BuyMeTest
=============
BuyMeTest executable class: /src/test/java/main/MainTest.java
Test uses the XML configuration file: src/main/resources/config.xml
The browser type can be selected by changing <activeBrowser> element value to one of the following:
1) Chrome
2) FireFox
3) Opera
4) Edge
5) IE

Extent HTML report is stored in the src/test/reports folder. It is getting cleared from its contents before
every test execution.

The test is contains the following packages:
1)  src/test/java/constants: contains two files with static final variables (constants):
    a) Constants.java - contains the names of the XML configuration file and the image file,
    used to be uploaded in the Sender&Receiver page
    b) PageLocators.java - contains By locators of all web elements used in the project.
2)  src/test/java/main: contains main test file - the test entry point
3)  src/test/java/pages - contains the WebPages classes, including the BasePage.java class - the parent class
    of all pages implemented in the test
4)  src/test/java/utils - contains all service programs used in the project:
    a) Configurator.java - implements a single point access to the configuration XML file
    b) GenerateValidEmail.java - generates a unique email address, using the Singleton DP.
    c) InitWebDriverSingleton.java - instantiates the WebDriver object, using the Singleton DP
    d) ResourceFileLocator.java - generates an absolute path to the resource file as required by XML
    DocumentBuilder class.
    e) TakeScreenshot.java - takes the screenshot of the current screen and stores in the requested folder.

Extras
=======
Four extras out of five were implemented (check src/test/java/extras folder)
1) Adding JavaDoc API documentation to the source code to be used with the JDK tool.
2) ReceiverTextColor.java - getting and printing the color of the Receiver name error label.
3) EmptyCredentialsError.java - validating the error message displayed on the sign-in page submitted with
the empty credentials
4) ChooseGiftScreenScreenshot.java - Scrolling the Choose Gift page to the bottom and taking the screenshot