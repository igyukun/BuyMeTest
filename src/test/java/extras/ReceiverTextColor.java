package extras;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.InitWebDriverSingleton;

import java.time.Duration;

/**
 * The ReceiverTextColor class is a part of the project's Extra assignments.
 *      It is built as a testNG script and uses the Selenium platform for accessing web elements.
 *      ReceiverTextColor class performs the following actions
 *      1) opens website using a predefined URL [https://buyme.co.il/money/398383?price=300]
 *      2) Locates text label with the text "ל מי לשלוח"
 *      3) Check the label color and display its HEX representation as a JS alert text
 *      4) Assert color correctness
 *
 * @author  ****
 * @version 1.0
 * @since   03-Feb-2022
 */

public class ReceiverTextColor {

    private final By RECEIVER_LABEL_LOCATOR = By.xpath("//*[@id=\"ember1220\"]/div/div[2]/div[1]/div[1]");
    private final String HEX_COLOR_VALUE = "#ffa126";
    WebDriver driver;
    WebDriverWait wait;
    WebElement receiverLabel;

    @BeforeClass
    /**
     * initTest method gets a webdriver instance from the InitWebDriverSingleton class,
     * sets window size and initializes the explicit wait object. Then it loads the webpage
     * using a predefined URL.
     * @see InitWebDriverSingleton#InitDriver()
     */
    public void initTest(){
        driver = InitWebDriverSingleton.InitDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.get("https://buyme.co.il/money/398383?price=300");
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    @Test (priority = 0)
    /**
     * locateTestedLabel method locates a text label using the predefined locator constant.
     * @see #RECEIVER_LABEL_LOCATOR
     */
    public void locateTestedLabel(){
        receiverLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(RECEIVER_LABEL_LOCATOR));
    }

    @Test (priority = 1)
    /**
     * getLabelColor method gets a HEX representation of the label text color,
     * asserts it correctness using the predefined value and displays the result
     * as a JS alert text.
     * @see #HEX_COLOR_VALUE
     */
    public void getLabelColor(){
        String hexColorValue = Color.fromString(receiverLabel.getCssValue("color")).asHex();
        System.out.printf("The label color is:%s%n", hexColorValue);
        Assert.assertEquals(hexColorValue, HEX_COLOR_VALUE);
        ((JavascriptExecutor)driver).executeScript(String.format("window.alert('The label color is:%s');", hexColorValue));
    }
}
