package pages;

import constants.PageLocators;
import utils.Configurator;
import utils.InitWebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Homepage extends BasePage{
    private WebDriver driver;
    private WebDriverWait wait;

    /*
    Start Page Class Constructor
     */
    public Homepage(){
        driver = InitWebDriverSingleton.InitDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.get(Configurator.getXMLKeyValue("signinPageURL"));
    }

    public void clickSigninRegButton() {
        clickElement(PageLocators.SIGN_IN_REG_BTN);
    }

    public void enterSignInUser(){
        sendKeysToElement(PageLocators.EMAIL,Configurator.getXMLKeyValue("validUser"));
    }

    public void enterSignInPassword(){
        sendKeysToElement(PageLocators.PASSWORD, Configurator.getXMLKeyValue("validPassword"));
    }

    public void clickSignIn(){
        clickElement(PageLocators.SIGNIN_BUTTON);
        waitForUrlToBe(Configurator.getXMLKeyValue("signinPageURL"));
    }



    public void selectPrice() {
        clickElement(PageLocators.PRICE_FIELD);
        clickElement(PageLocators.PRICE_SELECTION);
    }

    public void selectRegion() {
        clickElement(PageLocators.REGION_FIELD);
        clickElement(PageLocators.REGION_SELECTION);
    }

    public void selectCategory() {
        clickElement(PageLocators.CATEG_FIELD);
        clickElement(PageLocators.CATEG_SELECTION);
    }

    public void submitPresentSearch(){
        clickElement(PageLocators.FIND_PRESENT_BTN);
    }


}
