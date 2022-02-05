package main;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.*;
import utils.InitWebDriverSingleton;
import utils.TakeScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


/**
 * The MainTest class executes a full test flow required by the project spec.
 *      It is built using testNG framework and uses Selenium APIs for accessing
 *      and manipulating the web elements.
 *      Each test represents a set of actions on every specific page, according
 *      to a mission definition.
 * @author  Igor Kun
 * @version 1.0
 * @since   03-Feb-2022
 */

public class MainTest {

    // define ExtentReports to attach reporters
    private static ExtentReports extent;
    // creates a toggle for the given test, adds all log events under it
    private static ExtentTest test;
    //the directory to store extent report files and its screenshots
    private static String extentReportDir;


    @BeforeClass
    /**
     * This method is executed first before any other test case.
     * It initialises Extent reporting objects and defines a directory and a full path
     * to the test report html file
     */
    public void initTest(){
        //creates extent report directory [CURRENT_WORKING_DIRECTORY]/src/test/reports
        extentReportDir = String.format("%s/src/test/reports", System.getProperty("user.dir"));
        //generates full report name using current time in ms for uniqueness
        String extentReportName = String.format("%s/testreport_%d.html",
                extentReportDir, System.currentTimeMillis());
        //empty extent report directory from the previous runs leftovers
        emptyExtentReportDir(extentReportDir);
        //init reporter object with given file name
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(extentReportName);
        // attach reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // create test and add description
        test = extent.createTest("BuyMe website test",
                              "Automated Selenium and TestNG-based test of BuyMe.co.il website");
    }


    @Test(priority = 0)
    /**
     * This method executes actions on the Intro and Registration page
     * It uses the "Startpage" class methods to test the registration and
     * the sign-in flow.
     * Every step is registered in extent the test report file as PASS if passed
     * successfully. In case one of the test fails, the general Exception is caught
     * and it is written into the report as FAIL with addition of screenshot.
     * @exception Exception On any exception thrown during the execution .
     * @see Exception
     */
    public void introAndRegPageTest() {
        test.log(Status.INFO, "Intro and Registration test started");
        try {
            Startpage startpage = new Startpage();
            test.log(Status.PASS,"Enter BuyMe website.");
            startpage.clickSigninRegButton();
            test.log(Status.PASS,"Press כניסה/הרשמה");
            startpage.pressRegisterLink();
            test.log(Status.PASS,"Press הרשמה");
            startpage.enterFirstName();
            test.log(Status.PASS,"Enter first name");
            startpage.enterEmail();
            test.log(Status.PASS,"Enter email address");
            startpage.enterPassword();
            test.log(Status.PASS,"Enter password");
            startpage.enterPasswordValidation();
            test.log(Status.PASS,"Enter password validation");
            Assert.assertTrue(startpage.assertFirstName());
            test.log(Status.PASS,"First name input correctness asserted");
            Assert.assertTrue(startpage.assertEmail());
            test.log(Status.PASS,"Email address input correctness asserted");
            Assert.assertTrue(startpage.assertPassword());
            test.log(Status.PASS,"Password input correctness asserted");
            Assert.assertTrue(startpage.assertPasswordValidation());
            test.log(Status.PASS,"Password validation input correctness asserted");
            //Taking the screenshot of the page before pressing the "Register" button.
            test.log(Status.INFO, "Current page state screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(
                    TakeScreenshot.takeScreenShot(extentReportDir)).build());
            startpage.submitRegisterButton();
            test.log(Status.PASS,"Pressing sign-up button");

            //todo: unccomment for the intentional error generation
            //           startpage.findDummyLocator();

        }catch (Exception e){
            //Report the test as FAIL and save a screenshot in the case of a failure
            test.fail(e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath
                    (TakeScreenshot.takeScreenShot(extentReportDir)).build());
            //Throw an exception to activate TestNG failure response
            throw e;
        }
    }

    @Test (priority = 1)
    /**
     * This method executes actions on the Home page
     * It uses the "Homepage" class methods to test the filtering of the gifts by three criteria:
     * price, region and category.
     * Every step is registered in extent the test report file as PASS if passed
     * successfully. In case one of the test fails, the general Exception is caught
     * and it is written into the report as FAIL with addition of screenshot.
     * @exception Exception On any exception thrown during the execution .
     * @see Exception
     */
    public void homePageTest(){
        test.log(Status.INFO, "Home Screen test started");
        try {
            Homepage homepage = new Homepage();
//todo uncomment for a standalone execution
//            test.log(Status.PASS,"Enter Home Page");
//            homepage.clickSigninRegButton();
//            test.log(Status.PASS,"Open sign-in page");
//            homepage.enterSignInUser();
//            test.log(Status.PASS,"Enter signed user name(email)");
//            homepage.enterSignInPassword();
//            test.log(Status.PASS,"Enter signed user password ");
//            homepage.clickSignIn();
//            test.log(Status.PASS,"Click Signin button");
//todo
            homepage.selectPrice();
            test.log(Status.PASS,"Pick price point");
            homepage.selectRegion();
            test.log(Status.PASS,"Pick region");
            homepage.selectCategory();
            test.log(Status.PASS,"Pick category");
            //Taking the screenshot of the page before pressing the "Search" button.
            test.log(Status.INFO, "Current page state screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(
                    TakeScreenshot.takeScreenShot(extentReportDir)).build());
            homepage.submitPresentSearch();
            test.log(Status.PASS,"Press 'תמצאו לי מתנה'");
        }catch (Exception e){
            //Report the test as FAIL and save a screenshot in the case of a failure
            test.fail(e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(
                    TakeScreenshot.takeScreenShot(extentReportDir)).build());
            //Throw an exception to activate TestNG failure response
            throw e;
        }
    }

    @Test (priority = 2)
    /**
     * This method executes actions on the PickBusiness page
     * It uses the "PickBusinessPage" class methods to do the following actions:
     * 1) Check that the page URL generated correctly according to the criteria selected in the Home page
     * 2) Pick a Business card
     * 3) Enter a gift price
     * 4) Submit the selection
     * Every step is registered in extent the test report file as PASS if passed
     * successfully. In case one of the test fails, the general Exception is caught
     * and it is written into the report as FAIL with addition of screenshot.
     * @exception Exception On any exception thrown during the execution .
     * @see Exception
     */
    public void pickBusinessPageTest(){
        test.log(Status.INFO, "Pick business screen test started");
        try {
            PickBusinessPage pickbusiness = new PickBusinessPage();
            //Check that the page URL generated correctly according to the criteria selected in the Home page
            Assert.assertTrue(pickbusiness.checkURL());
            test.log(Status.PASS,"Checked the URL generated according to " +
                                        "the selections made on previous page");
            pickbusiness.clickBusinessCard();
            test.log(Status.PASS,"Pick business");
            pickbusiness.enterBusinessPrice();
            test.log(Status.PASS,"Enter/Choose a price");
            test.log(Status.INFO, "Current page state screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath
                    (TakeScreenshot.takeScreenShot(extentReportDir)).build());
            pickbusiness.submitBusinessPrice();
            test.log(Status.PASS,"Press 'לבחירה'",
                    MediaEntityBuilder.createScreenCaptureFromPath
                    (TakeScreenshot.takeScreenShot(extentReportDir)).build());
        }catch (Exception e){
            //Report the test as FAIL and save a screenshot in the case of a failure
            test.fail(e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath
                    (TakeScreenshot.takeScreenShot(extentReportDir)).build());
            //Throw an exception to activate TestNG failure response
            throw e;
        }
    }

    @Test (priority = 3)
    /**
     * This method executes actions on the Purchasing page
     * It uses the "PurchasingPage" class methods to perform the following:
     * 1) Fill receiver details
     * 2) Pick a sender details
     * 3) Assert entered sender's details correctness
     * Every step is registered in extent the test report file as PASS if passed
     * successfully. In case one of the test fails, the general Exception is caught
     * and it is written into the report as FAIL with addition of screenshot.
     * @exception Exception On any exception thrown during the execution .
     * @see Exception
     */
    public void purchasePageTest() throws Exception{
        test.log(Status.INFO, "Sender & Receiver (purchasing) screen test started");
        try {
            PurchasingPage purchasepage = new PurchasingPage();
            purchasepage.clickForOther();
            test.log(Status.PASS,"Press 'למישהו אחר'");
            purchasepage.enterReceiverName();
            test.log(Status.PASS,"Enter receiver name");
            purchasepage.selectOccasion();
            test.log(Status.PASS,"Pick an event");
            purchasepage.enterMessage();
            test.log(Status.PASS,"Enter a blessing");
            purchasepage.uploadImageFile();
            test.log(Status.PASS,"Upload a picture",
                    MediaEntityBuilder.createScreenCaptureFromPath
                    (TakeScreenshot.takeScreenShot(extentReportDir)).build());
            purchasepage.pressContinueButton();
            test.log(Status.PASS,"Press 'המשך'");
            purchasepage.selectSendNow();
            test.log(Status.PASS,"Press button 'עכשיו'");
            purchasepage.selectSendBySMS();
            test.log(Status.PASS,"Pick SMS");
            purchasepage.enterSMSNumebr();
            test.log(Status.PASS,"Enter a phone number for SMS");
            purchasepage.enterSenderName();
            test.log(Status.PASS,"Enter a sender name");
            purchasepage.enterSenderNumber();
            test.log(Status.PASS,"Enter a sender phone number");
            Assert.assertTrue(purchasepage.checkSenderName());
            test.log(Status.PASS,"Assert correctness of the entered sender name");
            Assert.assertTrue(purchasepage.checkSenderNumber());
            test.log(Status.PASS,"Assert correctness of the entered sender number",
                    MediaEntityBuilder.createScreenCaptureFromPath
                    (TakeScreenshot.takeScreenShot(extentReportDir)).build());
            purchasepage.pushPaymentButton();
        }catch (Exception e){
            //Report the test as FAIL and save a screenshot in the case of a failure
            test.fail(e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath
                    (TakeScreenshot.takeScreenShot(extentReportDir)).build());
            //Throw an exception to activate TestNG failure response
            throw e;
        }
    }

    @AfterClass
    /**
     * This method finalizes the test flow, by closing the Extent test report and
     * quitting the WebDriver.
     */
    public void finalizeTest(){
        //close extent test report to generate HTML report file
        extent.flush();
        //Get the driver static instance and call its quit() method
        InitWebDriverSingleton.InitDriver().quit();
    }

    /**
     * This method checks if the provided path represent a directory and clears it from all of it contents.
     * The IOException is caught by the method and exception StackTrace is printed ito the
     * standard output device (console)
     * @param dir generated random email for creating new user.
     */
    private void emptyExtentReportDir(String dir){
        File f = new File(dir);
        //Check if the File object is representing a directory.
        if (f.isDirectory())
            try {
                //Attempt to clear directory's contents
                FileUtils.cleanDirectory(f);
            }catch (IOException e){
                e.printStackTrace();
            }
    }

}

