package extras;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.InitWebDriverSingleton;

import java.time.Duration;

/**
 * The EmptyCredentialsError class is a part of the project's Extra assignments.
 *      It is built as a testNG script and uses the Selenium platform for accessing web elements.
 *      EmptyCredentialsError class performs the following actions
 *      1) opens website using a predefined URL [https://buyme.co.il]
 *      2) Enters Sign-in screen
 *      3) Presses Sign-in button with empty credentials
 *      4) Assert the correctness of the error messages generated for MAIL and PASSWORD fields
 *
 * @author  Igor Kun
 * @version 1.0
 * @since   03-Feb-2022
 */

public class EmptyCredentialsError {

        private final By SIGN_IN_REG_BTN        = By.cssSelector("li.notSigned"); //כניסה/הרשמה button
        private final By SIGNIN_BUTTON          = By.cssSelector("button[gtm='כניסה ל-BUYME']"); //כניסה ל-BUYME button
        private final By EMAIL_ERROR_LABEL      = By.cssSelector("ul#parsley-id-12 li");
        private final By PASSWORD_ERROR_LABEL   = By.cssSelector("ul#parsley-id-14 li");
        private String LABEL_ERROR_MESSAGE      = "כל המתנות מחכות לך! אבל קודם צריך מייל וסיסמה";

        WebDriver driver;
        WebDriverWait wait;

        @BeforeClass
        /**
         * initTest method gets a webdriver instance from the InitWebDriverSingleton class,
         * sets window size and initializes the explicit wait object. Then it loads the webpage
         * using a predefined URL.
         * @see InitWebDriverSingleton#InitDriver()
         */
        public void initTest(){
            driver = InitWebDriverSingleton.InitDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
            driver.get("https://buyme.co.il");
            wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        }

        @Test(priority = 0)
        /**
         * openSigninScreen method locates "כניסה/הרשמה" button and clicks it.
         */
        public void openSigninScreen(){
            wait.until(ExpectedConditions.elementToBeClickable(SIGN_IN_REG_BTN)).click();
        }

        @Test(priority = 1)
        /**
         * clickSignInButton method locates "כניסה ל-BUYME" button and clicks it without
         * entering user credentials.
         */
        public void clickSignInButton(){
            wait.until(ExpectedConditions.elementToBeClickable(SIGNIN_BUTTON)).click();
        }

        @Test(priority = 2)
        /**
         * assertUsernameError method locates username error message and asserts its correctness.
         */
        public void assertUsernameError(){
            String errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_ERROR_LABEL)).getText();
            Assert.assertEquals(errorMessage,LABEL_ERROR_MESSAGE);
        }

        @Test(priority = 3)
        /**
         * assertPasswordError method locates password error message and asserts its correctness.
         */
        public void assertPasswordError(){
            String errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_ERROR_LABEL)).getText();
            Assert.assertEquals(errorMessage,LABEL_ERROR_MESSAGE);
        }

        @AfterClass
        /**
         * This method finalizes the test flow, by quitting the WebDriver.
         */
        public void endTest(){
            driver.quit();
        }
}

