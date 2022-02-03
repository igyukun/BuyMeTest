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
 * The BasePage class is parent class to be inherited by all pages classes.
 *      It is built using Selenium APIs, and it provides a single place for accessing and manipulating
 *      the web elements throughout the project.
 *
 * @author  Igor Kun
 * @version 1.0
 * @since   03-Feb-2022
 */

public class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public void clickElement(By locator){
        getWebElement(locator,true,false).click();
    }

    public void clickElement(By locator, boolean isClickable, boolean isVisible){
        getWebElement(locator,isClickable,isVisible).click();
    }

    public void clickElement(WebElement element){
        element.click();
    }

    public void sendKeysToElement(By locator, String text){
        getWebElement(locator, false,true).sendKeys(text);
    }

    public void clearElement(By locator){
        getWebElement(locator, false, true).clear();
    }

    public String getInputFieldContents(By locator){
       return getWebElement(locator, false,true).getAttribute("value");
    }

    public String getPageURL(){
        initDriverWait();
        return driver.getCurrentUrl();
    }

    public void waitForUrlToBe(String pageURL){
        initDriverWait();
        wait.until(ExpectedConditions.urlToBe(pageURL));
    }

    public void uploadFileToElement(By locator, String filePath){
        getWebElement(locator, false,false).sendKeys(filePath);
    }

    public WebElement getWebElement(By locator, boolean isClickable, boolean isVisible) {
        initDriverWait();
        WebElement element;
            if (!isVisible && !isClickable)
                element = driver.findElement(locator);
            else
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (isClickable)
                element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            return element;
    }

    public void initDriverWait(){
        driver = InitWebDriverSingleton.InitDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    public void quitDriver(){
        InitWebDriverSingleton.InitDriver().quit();
    }

}
