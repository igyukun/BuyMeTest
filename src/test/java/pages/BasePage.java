package pages;

import utils.InitWebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The BasePage class is a parent class to be inherited by all pages classes.
 *      It is built using Selenium APIs, and it provides a single place for accessing and manipulating
 *      the web drivers and web elements throughout the project.
 * @author  Igor Kun
 * @version 1.0
 * @since   03-Feb-2022
 */

public class BasePage {
    private WebDriver driver;   //WebDriver object
    private WebDriverWait wait; //Explicit wait object

    /**
     * Finds a WebElement by passing a locator to getWebElement
     * method and performs the "click" event. It is expected that the element
     * becomes clickable before the "click" method is called.
     * @param locator - By.* locator of the WebElement to be clicked
     * @see #getWebElement(By, boolean, boolean)
     * @see WebElement#click()
     */
    public void clickElement(By locator){
        //Pass a locator to WebElement to be waited to become clickable
        getWebElement(locator,true,false).click();
    }

    /**
     * Finds a WebElement by passing a locator to getWebElement
     * method and sends it the text. It is expected that the element
     * becomes visible before the "SendKeys" method is called.
     * @param locator   - By.* locator of the WebElement
     * @param text      - String object containing a text to be sent to the WebElement
     * @see #getWebElement(By, boolean, boolean)
     * @see WebElement#sendKeys(CharSequence...)
     */
    public void sendKeysToElement(By locator, String text){
        getWebElement(locator, false,true).sendKeys(text);
    }

    /**
     * Finds a WebElement by passing a locator to getWebElement
     * method and clears its. It is expected that the element
     * becomes visible before the "clear" method is called
     * @param locator   - By.* locator of the WebElement
     * @see #getWebElement(By, boolean, boolean)
     * @see WebElement#clear()
     */
    public void clearElement(By locator){
        getWebElement(locator, false, true).clear();
    }

    /**
     * Finds a WebElement by passing a locator to getWebElement
     * method and reads its contents by accessing the "value" attribute.
     * It is expected that the element becomes visible before the "getAttribute" method is called.
     * @param locator   - By.* locator of the WebElement
     * @see #getWebElement(By, boolean, boolean)
     * @see WebElement#getAttribute(String)
     */
    public String getInputFieldContents(By locator){
       return getWebElement(locator, false,true).getAttribute("value");
    }

    /**
     * Reads a URL of the currently loaded web page.
     * It gets a WebDriver, using initDriverWait method, and calls its getCurrentUrl method
     * @see #initDriverWait()
     * @see WebDriver#getCurrentUrl()
     */
    public String getPageURL(){
        //Initialize the class WebDriver and the WebDriverWait objects
        initDriverWait();
        //Return a current page URL as a string
        return driver.getCurrentUrl();
    }

    /**
     * Waits for a page URL to be equal to the provided by a calling method.
     * It gets a WebDriver, using initDriverWait method, and uses an explicit wait
     * with "urlToBe" condition.
     * @param pageURL -> expected page URL
     * @see #initDriverWait()
     * @see ExpectedConditions#urlToBe(String)
     */
    public void waitForUrlToBe(String pageURL){
        //Initialize the class WebDriver and the WebDriverWait objects
        initDriverWait();
        wait.until(ExpectedConditions.urlToBe(pageURL));
    }

    /**
     * Finds a WebElement by passing a locator to getWebElement
     * method and sends the file path string, provided by a calling method.
     * @param locator   - By.* locator of the WebElement
     * @param filePath  - The path to the file to be uploaded to the webpage
     * @see #getWebElement(By, boolean, boolean)
     * @see WebElement#sendKeys(CharSequence...)
     */
    public void uploadFileToElement(By locator, String filePath){
        getWebElement(locator, false,false).sendKeys(filePath);
    }

    /**
     * Locates web element, using the locator provided by the calling method. It manages also the following
     * explicit wait conditions according to the caller demand:
     * 1) visibilityOfElementLocated
     * @see ExpectedConditions#visibilityOfElementLocated(By)
     * 2) elementToBeClickable
     * @see ExpectedConditions#elementToBeClickable(By)
     * @param locator       - By.* locator of the WebElement
     * @param isClickable   - Tells method to use elementToBeClickable wait condition
     * @param isVisible     - Tells method to use visibilityOfElementLocated wait condition
     * @return located object of type WebElement
     */
    public WebElement getWebElement(By locator, boolean isClickable, boolean isVisible) {
        //Initialize the class WebDriver and the WebDriverWait objects
        initDriverWait();
        //Define a local WebElement object
        WebElement element;

            if (!isVisible && !isClickable)
                //Do not apply the explicit wait if both isVisible and isClickable are false
                element = driver.findElement(locator);
            else
                //Wait for the web element to become attached to the DOM document and fully visible
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (isClickable)
                ////Wait for the web element to become clickable
                element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            return element;
    }

    /**
     * Gets an initialised WebDriver object accessing InitWebDriverSingleton class
     * @see InitWebDriverSingleton#InitDriver()
     * Creates an explicite wait object with a wait timeout of 20 seconds.
     * @see WebDriverWait
     */
    public void initDriverWait(){
        driver = InitWebDriverSingleton.InitDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

}
