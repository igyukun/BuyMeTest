package pages;

import constants.Constants;
import constants.PageLocators;
import org.openqa.selenium.By;
import utils.Configurator;
import utils.InitWebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ResourceFileLocator;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;

/**
 * The PurchasingPage class is a class that implements all test cases required to be performed in the
 * "Sender & Receiver information" page.
 * It is built using Selenium APIs.
 * The class methods access Constants class to get locators constants
 * @see constants.PageLocators
 * The required test values is taken from the XML configuration file, accessed using the Configurator class
 * @see utils.Configurator
 * @author  Igor Kun
 * @version 1.0
 * @since   03-Feb-2022
 */

public class PurchasingPage extends BasePage{
    private WebDriver driver;       //WebDriver object
    private WebDriverWait wait;     //Explicit wait object

    /**
     * PickBusinessPage class constructor.
     * 1) gets initialized web driver object
     *    @see InitWebDriverSingleton#InitDriver()
     * 2) opens web browser window in a maximized state
     * 3) defines page loading timeout of 20 seconds
     * 4) uses the web page loaded during the PickBusinessPage testing flow
     */
    public PurchasingPage(){
        driver = InitWebDriverSingleton.InitDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        //todo comment this out for a standalone run
        //  driver.get(Configurator.getXMLKeyValue("purchasePageURL"));
    }

    /**
     * Presses the 'למישהו אחר' box.
     * Uses inherited clickElement method of the BasePage class.
     *  @see BasePage#clickElement(By)
     * Element locator is taken using the PageLocators class
     *  @see constants.PageLocators
     */
    public void clickForOther() {
        clickElement(PageLocators.FOR_OTHER_BTN);
    }

    /**
     * Clears the receiver name field and enters the predefined receiver name.
     * Element locator is taken from the PageLocators class.
     * Uses inherited clearElement and sendKeysToElement method of the BasePage class.
     * @see BasePage#clearElement(By)
     *  @see BasePage#sendKeysToElement(By, String)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     * The receiver name value is taken from the configuration class
     * @see Configurator#getXMLKeyValue(String)
     */
    public void enterReceiverName() {
        clearElement(PageLocators.RECEIVER_NAME);
        sendKeysToElement(PageLocators.RECEIVER_NAME,
                Configurator.getXMLKeyValue("receiverName"));
    }

    /**
     * Presses "Pick an event" field to open the events list and then selects the event.
     * Uses inherited clickElement method of the BasePage class.
     *  @see BasePage#clickElement(By)
     * Element locators are taken from the PageLocators class
     *  @see constants.PageLocators
     */
    public void selectOccasion(){
        clickElement(PageLocators.OCCASION_BOX);
        try{
            //Unfortunately, neither implicit nor explicit wait methods could allow an event selection
            //and short sleep had to be used, unlike the Homepage criteria selection lists
            Thread.sleep(100);
        }catch (InterruptedException e){e.printStackTrace();}
        clickElement(PageLocators.OCCASION_SELECTED);
    }

    /**
     * Clears the blessing text field and enters the predefined blessing message.
     * Element locator is taken from the PageLocators class.
     * Uses inherited clearElement and sendKeysToElement method of the BasePage class.
     * @see BasePage#clearElement(By)
     *  @see BasePage#sendKeysToElement(By, String)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     * The blessing messagee value is taken from the configuration class
     * @see Configurator#getXMLKeyValue(String)
     */
    public void enterMessage(){
        clearElement(PageLocators.MESSAGE_TEXT_AREA);
        sendKeysToElement(PageLocators.MESSAGE_TEXT_AREA, Configurator.getXMLKeyValue("message"));
    }

    /**
     * Uploads the image file stored in the project Resources folder. The absolute file path is returned by
     * using the ResourceFileLocator class.
     * @see utils.ResourceFileLocator#getResourceFileLocation(String)
     * Element locator is taken from the PageLocators class.
     * Uses inherited uploadFileToElement method of the BasePage class
     *  @see BasePage#uploadFileToElement(By, String)
     * Image file name is taken from the constants list
     *  @see constants.Constants#IMAGE_FILE
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     */
    public void uploadImageFile(){
        String path = ResourceFileLocator.getResourceFileLocation(Constants.IMAGE_FILE);
        uploadFileToElement(PageLocators.FILE_UPLOAD_BTN,path);

    }

    /**
     * Presses 'המשך' button.
     * Uses inherited clickElement method of the BasePage class.
     *  @see BasePage#clickElement(By)
     * Element locators are taken from the PageLocators class
     *  @see constants.PageLocators
     */
    public void pressContinueButton(){
        clickElement(PageLocators.CONTINUE_BTN);
    }

    /**
     * Presses 'עכשיו' radio button.
     * Uses inherited clickElement method of the BasePage class.
     *  @see BasePage#clickElement(By)
     * Element locators are taken from the PageLocators class
     *  @see constants.PageLocators
     */
    public void selectSendNow(){
        clickElement(PageLocators.SEND_NOW_RADIO);
    }

    /**
     * Presses SMS button.
     * Uses inherited clickElement method of the BasePage class.
     *  @see BasePage#clickElement(By)
     * Element locators are taken from the PageLocators class
     *  @see constants.PageLocators
     */
    public void selectSendBySMS(){
        clickElement(PageLocators.SEND_BY_SMS);
    }

    /**
     * Clears the SMS number field and enters the predefined receiver phone number value.
     * Element locator is taken from the PageLocators class.
     * Uses inherited clearElement and sendKeysToElement method of the BasePage class.
     * @see BasePage#clearElement(By)
     *  @see BasePage#sendKeysToElement(By, String)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     * The phone number value is taken from the configuration class
     * @see Configurator#getXMLKeyValue(String)
     */
    public void enterSMSNumebr(){
        clearElement(PageLocators.SMS_NUMBER);
        sendKeysToElement(PageLocators.SMS_NUMBER,Configurator.getXMLKeyValue("receiverPhoneNumber"));
    }

    /**
     * Clears the Sender Name field and enters the predefined receiver phone number value.
     * Element locator is taken from the PageLocators class.
     * Uses inherited clearElement and sendKeysToElement method of the BasePage class.
     * @see BasePage#clearElement(By)
     *  @see BasePage#sendKeysToElement(By, String)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     * The sender name value is taken from the configuration class
     * @see Configurator#getXMLKeyValue(String)
     */
    public void enterSenderName(){
        clearElement(PageLocators.SENDER_NAME);
        sendKeysToElement(PageLocators.SENDER_NAME, Configurator.getXMLKeyValue("senderName"));
    }

    /**
     * Clears the Sender Number field and enters the predefined receiver phone number value.
     * Element locator is taken from the PageLocators class.
     * Uses inherited clearElement and sendKeysToElement method of the BasePage class.
     * @see BasePage#clearElement(By)
     *  @see BasePage#sendKeysToElement(By, String)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     * The Sender Number value is taken from the configuration class
     * @see Configurator#getXMLKeyValue(String)
     */
    public void enterSenderNumber(){
        clearElement(PageLocators.SENDER_NUMBER);
        sendKeysToElement(PageLocators.SENDER_NUMBER,Configurator.getXMLKeyValue("senderPhoneNumber"));
    }

    /**
     * Checks the Sender name correctness by reading a contents of the input field and
     * comparing it to the value stored in the configuration XML file.
     * Element locator is taken from the PageLocators class.
     * Uses inherited getInputFieldContents method of the BasePage class.
     *  @see BasePage#getInputFieldContents(By)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     * The Sender name is taken from the configuration class
     * @see Configurator#getXMLKeyValue(String)
     * @return true if compared values are equal and false if not equal
     */
    public boolean checkSenderName(){
        return getInputFieldContents(PageLocators.SENDER_NAME).
                equals(Configurator.getXMLKeyValue("senderName"));
    }

    /**
     * Checks the Sender phone number correctness by reading a contents of the input field and
     * comparing it to the value stored in the configuration XML file.
     * Element locator is taken from the PageLocators class.
     * Uses inherited getInputFieldContents method of the BasePage class.
     *  @see BasePage#getInputFieldContents(By)
     * Element locator is taken from the PageLocators class
     *  @see constants.PageLocators
     * The sender number is taken from the configuration class
     * @see Configurator#getXMLKeyValue(String)
     * @return true if compared values are equal and false if not equal
     */
    public boolean checkSenderNumber(){
        return getInputFieldContents(PageLocators.SENDER_NUMBER).
                equals(Configurator.getXMLKeyValue("senderPhoneNumber"));
    }

    /**
     * Presses the Payment button.
     * Uses inherited clickElement method of the BasePage class.
     *  @see BasePage#clickElement(By)
     * Element locators are taken from the PageLocators class
     *  @see constants.PageLocators
     */
    public void pushPaymentButton(){
        clickElement(PageLocators.PAYMENT_BTN);
    }

}
