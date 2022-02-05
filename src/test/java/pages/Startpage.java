package pages;

import constants.PageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Configurator;
import utils.GenerateValidEmail;
import utils.InitWebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
/**
 * The Startpage class is a class that implements all test cases required to be performed in the
 * "Intro and registration" page.
 * It is built using Selenium APIs.
 * The class methods access Constants class to get locators constants
 * @see constants.PageLocators
 * The required test values is taken from the XML configuration file, accessed using the Configurator class
 * @see utils.Configurator
 * @author  Igor Kun
 * @version 1.0
 * @since   03-Feb-2022
 */

public class Startpage extends BasePage{
    private WebDriver driver;       //WebDriver object
    private WebDriverWait wait;     //Explicit wait object

    /**
     * Startpage class constructor. 
     * 1) gets initialized web driver object
     *    @see InitWebDriverSingleton#InitDriver()
     * 2) opens web browser window in a maximized state
     * 3) defines page loading timeout of 20 seconds
     * 4) opens web page by a URL read from the configuration XML file
     *    @see Configurator#getXMLKeyValue(String)
     */    
    public Startpage(){
        //Get initialized web driver
        driver = InitWebDriverSingleton.InitDriver();
        //Maximize the web browser size
        driver.manage().window().maximize();
        //Define the page loading timeout
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        //Get page URL from the configuration XML file and load the web page
        driver.get(Configurator.getXMLKeyValue("webSiteURL"));
    }

    /**
     * Presses כניסה/הרשמה button.
     * Uses inherited clickElement method of the BasePage class.
     *  @see BasePage#clickElement(By)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     */
    public void clickSigninRegButton() {
        clickElement(PageLocators.SIGN_IN_REG_BTN);
    }

    /**
     * Presses הרשמה link.
     * Element locator is taken from the PageLocators class
     * Uses inherited clickElement method of the BasePage class.
     *  @see BasePage#clickElement(By)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     */
    public void pressRegisterLink(){
        clickElement(PageLocators.REGISTER_LINK);
    }

    /**
     * Enters the first name into a relevant input field.
     * Element locator is taken from the PageLocators class.
     * Uses inherited sendKeysToElement method of the BasePage class.
     *  @see BasePage#sendKeysToElement(By, String)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     * The name is taken from the configuration class
     * @see Configurator#getXMLKeyValue(String)
     */
    public void enterFirstName(){
        sendKeysToElement(PageLocators.FIRST_NAME, Configurator.getXMLKeyValue("firstName"));
    }

    /**
     * Enters the email address into a relevant input field.
     * Element locator is taken from the PageLocators class.
     * Uses inherited sendKeysToElement method of the BasePage class.
     *  @see BasePage#sendKeysToElement(By, String)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     * The unique email address is generated automatically by GenerateValidEmail class
     * @see GenerateValidEmail#generateValidEmail()
     */
    public void enterEmail(){
        sendKeysToElement(PageLocators.EMAIL, GenerateValidEmail.generateValidEmail());
    }

    /**
     * Enters the password into a relevant input field.
     * Element locator is taken from the PageLocators class.
     * Uses inherited sendKeysToElement method of the BasePage class.
     *  @see BasePage#sendKeysToElement(By, String)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     * The password is taken from the configuration class
     * @see Configurator#getXMLKeyValue(String)
     */
    public void enterPassword(){
        sendKeysToElement(PageLocators.PASSWORD,
                          Configurator.getXMLKeyValue("password"));
    }

    /**
     * Enters the password validation into a relevant input field.
     * Element locator is taken from the PageLocators class.
     * Uses inherited sendKeysToElement method of the BasePage class.
     *  @see BasePage#sendKeysToElement(By, String)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     * The password is taken from the configuration class
     * @see Configurator#getXMLKeyValue(String)
     */
    public void enterPasswordValidation(){
        sendKeysToElement(PageLocators.PASSWORD_VALID,
                          Configurator.getXMLKeyValue("password"));
    }

    /**
     * Checks the first name correctness by reading a contents of the input field and
     * comparing it to the value stored in the configuration XML file.
     * Element locator is taken from the PageLocators class.
     * Uses inherited getInputFieldContents method of the BasePage class.
     *  @see BasePage#getInputFieldContents(By)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     * The first name is taken from the configuration class
     * @see Configurator#getXMLKeyValue(String)
     * @return true if compared values are equal and false if not equal
     */
    public boolean assertFirstName(){
        return getInputFieldContents(PageLocators.FIRST_NAME).equals(
                                     Configurator.getXMLKeyValue("firstName"));
    }

    /**
     * Checks the email address correctness by reading a contents of the input field and
     * comparing it to the value generated by GenerateValidEmail singleton class.
     * Element locator is taken from the PageLocators class.
     * Uses inherited getInputFieldContents method of the BasePage class.
     *  @see BasePage#getInputFieldContents(By)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     * @return true if compared values are equal and false if not equal
     */
    public boolean assertEmail(){
        return getInputFieldContents(PageLocators.EMAIL).equals(
                GenerateValidEmail.generateValidEmail());
    }

    /**
     * Checks the password correctness by reading a contents of the input field and
     * comparing it to the value stored in the configuration XML file.
     * Element locator is taken from the PageLocators class.
     * Uses inherited getInputFieldContents method of the BasePage class.
     *  @see BasePage#getInputFieldContents(By)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     * The password is taken from the configuration class
     * @see Configurator#getXMLKeyValue(String)
     * @return true if compared values are equal and false if not equal
     */
    public boolean assertPassword(){
        return getInputFieldContents(PageLocators.PASSWORD).equals(
                Configurator.getXMLKeyValue("password"));
    }

    /**
     * Checks the password validation correctness by reading a contents of the input field and
     * comparing it to the value stored in the configuration XML file.
     * Element locator is taken from the PageLocators class.
     * Uses inherited getInputFieldContents method of the BasePage class.
     *  @see BasePage#getInputFieldContents(By)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     * The password is taken from the configuration class
     * @see Configurator#getXMLKeyValue(String)
     * @return true if compared values are equal and false if not equal
     */
    public boolean assertPasswordValidation(){
        return getInputFieldContents(PageLocators.PASSWORD_VALID).equals(
                Configurator.getXMLKeyValue("password"));
    }

    /**
     * Pressing sign-up button.
     * Element locator is taken from the PageLocators class
     * Uses inherited clickElement method of the BasePage class.
     *  @see BasePage#clickElement(By)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     */
    public void submitRegisterButton(){
        clickElement(PageLocators.REGISTER_BUTTON);
    }

    /**
     * DEBUG method trying to locate a non-existing web element.
     * Element locator is taken from the PageLocators class
     * Uses inherited getInputFieldContents method of the BasePage class.
     *  @see BasePage#getInputFieldContents(By)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     */
    public void findDummyLocator(){
        getInputFieldContents(PageLocators.DUMMY_LOCATOR);
    }

}
