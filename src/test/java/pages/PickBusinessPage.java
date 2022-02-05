package pages;

import constants.PageLocators;
import org.openqa.selenium.By;
import utils.Configurator;
import utils.InitWebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * The PickBusinessPage class is a class that implements all test cases required to be performed
 * in the Pick Business page.
 * It is built using Selenium APIs.
 * The class methods access Constants class to get locators constants
 *  @see constants.PageLocators
 * The required test values is taken from the XML configuration file, accessed using the Configurator class
 *  @see utils.Configurator
 * @author  Igor Kun
 * @version 1.0
 * @since   03-Feb-2022
 */

public class PickBusinessPage extends BasePage{
    private WebDriver driver;       //WebDriver object
    private WebDriverWait wait;     //Explicit wait object

    /**
     * PickBusinessPage class constructor.
     * 1) gets initialized web driver object
     *    @see InitWebDriverSingleton#InitDriver()
     * 2) opens web browser window in a maximized state
     * 3) defines page loading timeout of 20 seconds
     * 4) uses the web page loaded during the Homepage testing flow
     */
    public PickBusinessPage(){
        driver = InitWebDriverSingleton.InitDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        //todo: DEBUG. To comment this out
        //      driver.get(Configurator.getXMLKeyValue("testPageURL"));

    }

    /**
     * Checks the if the page URL generated according to the criteria, selected in the Home page
     * is equal to the expected value stored in the configuration XML file.
     * Uses inherited getPageURL method of the BasePage class.
     *  @see BasePage#getPageURL()
     * The expected URL is taken from the configuration class
     * @see Configurator#getXMLKeyValue(String)
     * @return true if compared values are equal and false if not equal
     */
    public boolean checkURL(){
        return getPageURL().equals(Configurator.getXMLKeyValue("testPageURL"));
    }

    /**
     * Presses the predefined Busines card.
     * Uses inherited clickElement method of the BasePage class.
     *  @see BasePage#clickElement(By)
     * Element locator is taken using the PageLocators class
     *  @see constants.PageLocators
     */
    public void clickBusinessCard() {
        clickElement(PageLocators.BUSINESS_CARD_2);
    }

    /**
     * Enters the price into a relevant input field.
     * Element locator is taken from the PageLocators class.
     * Uses inherited sendKeysToElement method of the BasePage class.
     *  @see BasePage#sendKeysToElement(By, String)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     * The price value is taken from the configuration class
     * @see Configurator#getXMLKeyValue(String)
     */
    public void enterBusinessPrice() {
        sendKeysToElement(PageLocators.BUSINESS_PRICE,Configurator.getXMLKeyValue("businessPrice"));
    }

    /**
     * Presses 'לבחירה' button.
     * Uses inherited clickElement method of the BasePage class.
     *  @see BasePage#clickElement(By)
     * Element locator is taken using the PageLocators class
     *  @see constants.PageLocators
     */
    public void submitBusinessPrice(){
        clickElement(PageLocators.BUSINESS_SUBMIT);
    }

}
