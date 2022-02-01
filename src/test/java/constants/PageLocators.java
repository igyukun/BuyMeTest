package constants;

import org.openqa.selenium.By;

public class PageLocators {

    //==============================================================================================
    //     Start page locators
    public static final By SIGN_IN_REG_BTN      = By.cssSelector("li.notSigned");
    //==============================================================================================

    //==============================================================================================
    //     Register and Sign In page locators
    public static final By REGISTER_LINK    = By.cssSelector("div.register-or-login span");
    public static final By FIRST_NAME       = By.cssSelector("input[placeholder='שם פרטי']");
    public static final By EMAIL            = By.cssSelector("input[placeholder='מייל']");
    public static final By PASSWORD         = By.cssSelector("input[placeholder='סיסמה']");
    public static final By PASSWORD_VALID   = By.cssSelector("input[placeholder='אימות סיסמה']");
    public static final By REGISTER_BUTTON  = By.cssSelector("button[gtm='הרשמה ל-BUYME']");
    public static final By SIGNIN_BUTTON    = By.cssSelector("button[gtm='כניסה ל-BUYME']");
    //==============================================================================================

    //==============================================================================================
    //     Home page locators
    public static final By PRICE_FIELD      = By.xpath("//*[contains(text(),'סכום')]");
    public static final By PRICE_SELECTION  = By.xpath("//*[contains(text(),'200-299')]");
    public static final By REGION_FIELD     = By.xpath("//*[contains(text(),'אזור')]");
    public static final By REGION_SELECTION = By.xpath("//*[contains(text(),'ת\"א והסביבה')]");
    public static final By CATEG_FIELD      = By.xpath("//*[contains(text(),'קטגוריה')]");
    public static final By CATEG_SELECTION  = By.xpath("//*[contains(text(),'גיפט קארד למותגי אופנה')]");
    public static final By FIND_PRESENT_BTN = By.linkText("תמצאו לי מתנה");
    //==============================================================================================

    //==============================================================================================
    //     Pick business page locators
    public static final By BUSINESS_CARD_2  = By.cssSelector("img[title='עזריאלי גיפט קארד']");
    public static final By BUSINESS_PRICE   = By.cssSelector("input[placeholder='הכנס סכום']");
    public static final By BUSINESS_SUBMIT  = By.cssSelector("button[gtm='בחירה']");
    //==============================================================================================

    //==============================================================================================
    //     Purchase page locators
    public static final By FOR_OTHER_BTN     = By.cssSelector("div[gtm='למישהו אחר']");
    public static final By RECEIVER_NAME     = By.cssSelector("input[data-parsley-required-message*='מי הזוכה המאושר? יש להשלים את שם המקבל']");
    public static final By OCCASION_BOX      = By.xpath("//form/div[2]/div[2]/label/div/div[1]/span");
    public static final By OCCASION_SELECTED = By.xpath("//*[contains(text(),'סתם כי בא לי לפנק')]");
    public static final By MESSAGE_TEXT_AREA = By.cssSelector("textarea[placeholder*='כאן כותבים מילים טובות ואיחולים שמחים']");
    public static final By FILE_UPLOAD_BTN   = By.cssSelector("input[accept*='image/png,image/jpeg,video/quicktime,video/mp4,.mov,.qt']");
    public static final By CONTINUE_BTN      = By.cssSelector("button[gtm='המשך']");
    //==============================================================================================

    //==============================================================================================
    //     Sending locators
    public static final By SEND_NOW_RADIO    = By.cssSelector("div[gtm='עכשיו']");
    public static final By SEND_BY_SMS       = By.cssSelector("svg[gtm='method-sms']");
    public static final By SMS_NUMBER        = By.cssSelector("input#sms");
    public static final By SENDER_NAME     = By.cssSelector("input[placeholder='שם שולח המתנה']");
    public static final By SENDER_NUMBER     = By.cssSelector("input[placeholder='מספר נייד']");
    public static final By PAYMENT_BTN      = By.cssSelector("button[gtm='המשך לתשלום']");
    //==============================================================================================

    //==============================================================================================
    //     Dummy locators
    public static final By DUMMY_LOCATOR    = By.cssSelector("dummy");

}
