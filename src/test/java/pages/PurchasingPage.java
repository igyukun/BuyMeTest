package pages;

import constants.Constants;
import constants.PageLocators;
import utils.Configurator;
import utils.InitWebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ResourceFileLocator;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;

public class PurchasingPage extends BasePage{
    private WebDriver driver;
    private WebDriverWait wait;

    /*
    Start Page Class Constructor
     */
    public PurchasingPage(){
        driver = InitWebDriverSingleton.InitDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        //todo comment this out
        //driver.get(Configurator.getXMLKeyValue("purchasePageURL"));
        //todo////////////////////
    }

    public void clickForOther() {
        clickElement(PageLocators.FOR_OTHER_BTN);
    }

    public void enterReceiverName() {
        clearElement(PageLocators.RECEIVER_NAME);
        sendKeysToElement(PageLocators.RECEIVER_NAME,
                Configurator.getXMLKeyValue("receiverName"));
    }

    public void selectOccasion(){
        clickElement(PageLocators.OCCASION_BOX);
        clickElement(PageLocators.OCCASION_SELECTED);
    }

    public void enterMessage(){
        clearElement(PageLocators.MESSAGE_TEXT_AREA);
        sendKeysToElement(PageLocators.MESSAGE_TEXT_AREA, Configurator.getXMLKeyValue("message"));
    }

    public void uploadImageFile(){
        String path = ResourceFileLocator.getResourceFileLocation(Constants.IMAGE_FILE);
        uploadFileToElement(PageLocators.FILE_UPLOAD_BTN,path);
                //uploadFileToElement(PageLocators.FILE_UPLOAD_BTN, Constants.IMAGE_FILE);
                //Configurator.getXMLKeyValue("giftImageFile"));
    }

    public void pressContinueButton(){
        clickElement(PageLocators.CONTINUE_BTN);
    }

    public void selectSendNow(){
        clickElement(PageLocators.SEND_NOW_RADIO);
    }

    public void selectSendBySMS(){
        clickElement(PageLocators.SEND_BY_SMS);
    }

    public void enterSMSNumebr(){
        clearElement(PageLocators.SMS_NUMBER);
        sendKeysToElement(PageLocators.SMS_NUMBER,Configurator.getXMLKeyValue("receiverPhoneNumber"));
    }

    public void enterSenderName(){
        clearElement(PageLocators.SENDER_NAME);
        sendKeysToElement(PageLocators.SENDER_NAME, Configurator.getXMLKeyValue("senderName"));
    }

    public void enterSenderNumber(){
        clearElement(PageLocators.SENDER_NUMBER);
        sendKeysToElement(PageLocators.SENDER_NUMBER,Configurator.getXMLKeyValue("senderPhoneNumber"));
    }

    public boolean checkSenderName(){
        return getInputFieldContents(PageLocators.SENDER_NAME).
                equals(Configurator.getXMLKeyValue("senderName"));
    }

    public boolean checkSenderNumber(){
        return getInputFieldContents(PageLocators.SENDER_NUMBER).
                equals(Configurator.getXMLKeyValue("senderPhoneNumber"));
    }

    public void pushPaymentBButton(){
        clickElement(PageLocators.PAYMENT_BTN);
    }

//    public String getResourceFileLocation(String fileName) {
//        String filePath;
//        URL fileUrl = getClass().getClassLoader().getResource(fileName);
//        try {
//            filePath = Paths.get(fileUrl.toURI()).toString();
//        }catch (URISyntaxException e){
//            e.printStackTrace();
//            filePath = fileName;
//        }
//        return filePath;
//        //ClassLoader classloader = getClass().getClassLoader();
//        //return String.valueOf(new File(classloader.getResource(fileName).getFile()));
//    }
}
