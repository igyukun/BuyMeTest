package extras;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.InitWebDriverSingleton;
import utils.TakeScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

/**
 * The ChooseGiftScreenScreenshot class is a part of the project's Extra assignments.
 *      It is built as a testNG script usin Selenium platform for accessing web elements.
 *      ChooseGiftScreenScreenshot class performs the following actions:
 *      1) opens website using a predefined URL [https://buyme.co.il/money/398383?price=300]
 *      2) creates a test report using Extent API
 *      3) scrolls down to the bottom of the page
 *      4) takes page screenshot and adds it to the Extent report html stored
 *         in the src/main/test/extra_reports directory
 *
 * @author  Igor Kun
 * @version 1.0
 * @since   03-Feb-2022
 */

public class ChooseGiftScreenScreenshot {


    WebDriver driver;
    // create ExtentReports and attach reporter(s)
    private static ExtentReports extent;
    // creates a toggle for the given test, adds all log events under it
    private static ExtentTest test;
    //the directory to store extent report files and its screenshots
    private static String extentReportDir;


    @BeforeClass
    /**
     * initTest method:
     * 1) gets a webdriver instance from the InitWebDriverSingleton object
     * 2) sets window size to maximized
     * 3) loads the webpage using a predefined URL
     * 4) initializes the extent test report objects
     * @see InitWebDriverSingleton
     */
    public void initTest(){
        driver = InitWebDriverSingleton.InitDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.get("https://buyme.co.il/money/398383?price=300");

        //creates extent report directory [CURRENT_WORKING_DIRECTORY]/src/test/extras_reports
        extentReportDir = String.format("%s/src/test/extras_reports", System.getProperty("user.dir"));
        //generates full report name using current time in ms for uniqueness
        String extentReportName = String.format("%s/testreport_%d.html",
                extentReportDir, System.currentTimeMillis());
        //empty extras_reports directory from the previous runs' leftovers
        emptyExtentReportDir(extentReportDir);
        //init reporter object with given file name
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(extentReportName);
        // attach reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // create test and add description
        test = extent.createTest("Extra assignment: Gift Page screenshot",
                "Open gift screen, scroll to the bottom and take a screenshot.");
    }

    @Test
    /**
     * takePageScreenshot method:
     * 1) scrolls the page to the bottom using JavascriptExecutor class
     * 2) logs "pass" or "fail" status into the extent report
     * 3) takes a screenshot using TakeScreenshot class and adds it to the report
     * 4) initializes the extent test report objects
     * @see TakeScreenshot#takeScreenShot(String)
     */
    public void takePageScreenshot(){
        try {
            //scroll the screen down using the JS executor
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight);");
            test.log(Status.PASS, "Gift page has been scrolled down");
        }catch (Exception e){
            test.log(Status.FAIL, String.format("Page scrolling down failed with an error:%n%s", e.getMessage()));
            throw e;
        }finally {
            test.log(Status.INFO, "Current page state screenshot",
                            MediaEntityBuilder.createScreenCaptureFromPath(
                            TakeScreenshot.takeScreenShot(extentReportDir)).build());
        }
    }

    @AfterClass
    public void endTest(){
        extent.flush();
        driver.quit();
    }

    private void emptyExtentReportDir(String dir){
        File f = new File(dir);
        if (f.isDirectory())
            try {
                FileUtils.cleanDirectory(f);
            }catch (IOException e){
                e.printStackTrace();
            }
    }}
