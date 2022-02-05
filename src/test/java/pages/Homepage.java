package pages;

import constants.PageLocators;
import org.openqa.selenium.By;
import utils.Configurator;
import utils.GenerateValidEmail;
import utils.InitWebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.geom.GeneralPath;
import java.time.Duration;

/**
 * The Homepage class is a class that implements all test cases required to be performed in the homepage.
 * It is built using Selenium APIs.
 * The class methods access Constants class to get locators constants
 * @see constants.PageLocators
 * The required test values is taken from the XML configuration file, accessed using the Configurator class
 * @see utils.Configurator
 * @author  Igor Kun
 * @version 1.0
 * @since   03-Feb-2022
 */
public class Homepage extends BasePage{
    private WebDriver driver;

    /**
     * Homepage class constructor.
     * 1) gets initialized web driver object
     *    @see InitWebDriverSingleton#InitDriver()
     * 2) opens web browser window in a maximized state
     * 3) defines page loading timeout of 20 seconds
     * 4) uses the web page loaded during the Startpage testing
     */
    public Homepage(){
        driver = InitWebDriverSingleton.InitDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        //todo: uncomment for a standalone execution for DEBUG purposes
        // driver.get(Configurator.getXMLKeyValue("signinPageURL"));

    }

    /**
     * Presses "Pick price point" field to open the price points list and then selects the price point.
     * Uses inherited clickElement method of the BasePage class.
     *  @see BasePage#clickElement(By)
     * Element locators are taken from the PageLocators class
     *  @see constants.PageLocators
     */
    public void selectPrice() {
        clickElement(PageLocators.PRICE_FIELD);
        clickElement(PageLocators.PRICE_SELECTION);
    }

    /**
     * Presses "Pick region" field to open the regions list and then selects the region.
     * Uses inherited clickElement method of the BasePage class.
     *  @see BasePage#clickElement(By)
     * Element locators are taken from the PageLocators class
     *  @see constants.PageLocators
     */
    public void selectRegion() {
        clickElement(PageLocators.REGION_FIELD);
        clickElement(PageLocators.REGION_SELECTION);
    }

    /**
     * Presses "Pick category" field to open the categories list and then selects the category.
     * Uses inherited clickElement method of the BasePage class.
     *  @see BasePage#clickElement(By)
     * Element locators are taken from the PageLocators class
     *  @see constants.PageLocators
     */
    public void selectCategory() {
        clickElement(PageLocators.CATEG_FIELD);
        clickElement(PageLocators.CATEG_SELECTION);
    }

    /**
     * Presses 'תמצאו לי מתנה' button.
     * Uses inherited clickElement method of the BasePage class.
     *  @see BasePage#clickElement(By)
     * Element locators are taken from the PageLocators class
     *  @see constants.PageLocators
     */
    public void submitPresentSearch(){
        clickElement(PageLocators.FIND_PRESENT_BTN);
    }


    //todo: uncomment for a standalone execution for DEBUG purposes
//    public void clickSigninRegButton() {
//        clickElement(PageLocators.SIGN_IN_REG_BTN);
//    }
//
//    public void enterSignInUser(){
//        sendKeysToElement(PageLocators.EMAIL, GenerateValidEmail.generateValidEmail());
//    }
//
//    public void enterSignInPassword(){
//        sendKeysToElement(PageLocators.PASSWORD, Configurator.getXMLKeyValue("password"));
//    }
//
//    public void clickSignIn(){
//        clickElement(PageLocators.SIGNIN_BUTTON);
//        waitForUrlToBe(Configurator.getXMLKeyValue("signinPageURL"));
//    }
    //todo========================================
}
