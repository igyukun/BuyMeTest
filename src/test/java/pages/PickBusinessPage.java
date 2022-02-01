package pages;

import constants.PageLocators;
import utils.Configurator;
import utils.InitWebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PickBusinessPage extends BasePage{
    private WebDriver driver;
    private WebDriverWait wait;

    /*
    Start Page Class Constructor
     */
    public PickBusinessPage(){
        driver = InitWebDriverSingleton.InitDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        //todo: DEBUG. To comment this out
        //driver.get(Configurator.getXMLKeyValue("testPageURL"));
        //todo////////////////////
    }

    public boolean checkURL(){
        return getPageURL().equals(Configurator.getXMLKeyValue("testPageURL"));
    }

    public void clickBusinessCard() {
        clickElement(PageLocators.BUSINESS_CARD_2);
    }

    public void enterBusinessPrice() {
        sendKeysToElement(PageLocators.BUSINESS_PRICE,Configurator.getXMLKeyValue("businessPrice"));
    }

    public void submitBusinessPrice(){
        clickElement(PageLocators.BUSINESS_SUBMIT);
    }

}
