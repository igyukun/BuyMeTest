package pages;

import constants.PageLocators;
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
 *
 * @author  Igor Kun
 * @version 1.0
 * @since   03-Feb-2022
 */

public class Startpage extends BasePage{
    private WebDriver driver;
    private WebDriverWait wait;

    //Startpage Class Constructor
    public Startpage(){
        driver = InitWebDriverSingleton.InitDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.get(Configurator.getXMLKeyValue("webSiteURL"));
    }

    public void clickSigninRegButton() {
        clickElement(PageLocators.SIGN_IN_REG_BTN);
    }

    public void pressRegisterLink(){
        clickElement(PageLocators.REGISTER_LINK);
    }

    public void enterFirstName(){
        sendKeysToElement(PageLocators.FIRST_NAME, Configurator.getXMLKeyValue("firstName"));
    }

    public void enterEmail(){
        sendKeysToElement(PageLocators.EMAIL, GenerateValidEmail.generateValidEmail());
    }

    public void enterPassword(){
        sendKeysToElement(PageLocators.PASSWORD, Configurator.getXMLKeyValue("password"));
    }

    public void enterPasswordValidation(){
        sendKeysToElement(PageLocators.PASSWORD_VALID, Configurator.getXMLKeyValue("password"));
    }

    public boolean assertFirstName(){
        return getInputFieldContents(PageLocators.FIRST_NAME).equals(
                                     Configurator.getXMLKeyValue("firstName"));
    }

    public boolean assertEmail(){
        return getInputFieldContents(PageLocators.EMAIL).equals(
                GenerateValidEmail.generateValidEmail());
    }

    public boolean assertPassword(){
        return getInputFieldContents(PageLocators.PASSWORD).equals(
                Configurator.getXMLKeyValue("password"));
    }

    public boolean assertPasswordValidation(){
        return getInputFieldContents(PageLocators.PASSWORD_VALID).equals(
                Configurator.getXMLKeyValue("password"));
    }

    public void submitRegisterButton(){
        clickElement(PageLocators.REGISTER_BUTTON);
    }

    public void findDummyLocator(){
        getInputFieldContents(PageLocators.DUMMY_LOCATOR);
    }

}
