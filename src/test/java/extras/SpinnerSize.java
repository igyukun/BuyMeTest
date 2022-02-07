package extras;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.InitWebDriverSingleton;

import java.time.Duration;
import java.util.function.Function;

/**
 * Class SpinnerSize locates the spinner icons on the buyme.co.il website loading page
 * and prints its dimensions to the standard output device.
 * Note: since the website loading page is shown for the very limited time and on the loaded page the
 * spinner element cannot be located, the class implements a loop which iterates a limited number of times.
 * During each iteration the method attempts to locate the spinner element, using fluent wait mechanism.
 * If the wait timeout is expired the page is refreshed to show the loading page again and to make another attempt
 * to locate the element.
 * It is not an ideal solution but it does the job in most cases.
 * @author Igor Kun
 * @version 1.0
 * @since   07-Feb-2022
 */

public class SpinnerSize {
    //the locator of the spinner child <div class="bounce2"></div>
    public final By BOUNCE_LOCATOR = By.xpath("/html/body/div[1]/div/div[2]/div/div/div[2]");

    WebElement element;
    WebDriver driver;

    @BeforeClass
    /**
     * Initialize the web driver
     */
    public void initDriver(){

        driver = InitWebDriverSingleton.InitDriver();
        driver.manage().window().maximize();
        driver.get("https://buyme.co.il");
    }

    @Test
    /**
     * The method uses fluent wait API to locate the web element and prints its dimensions when found.
     */
    public void getSpinnerSize() {

        //create fluent wait method
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).
                withTimeout(Duration.ofMillis(5000)).   //general wait timeout
                pollingEvery(Duration.ofMillis(50)).    //polling timeout
                ignoring(NoSuchElementException.class); //ignored exception

        //The spinner element is visible only on the loading screen so it is tricky to catch it while the page is being loaded.
        //The loop iterates up to 10 times, refreshing the webpage each time the wait timeout is expiring
        for (int times = 0, maxIters = 9; times <=maxIters && element == null; times++){

            try {
                //Attempt to find a "bounce#" element
                element = wait.until(new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(BOUNCE_LOCATOR);
                    }
                });
                //If element has been found, print it's size and exit the loop
                System.out.println(createOutputString());
                break;

            }catch (Exception e) {
                //element could not be found
                System.out.println(e.getMessage());
                //refresh the web page
                driver.navigate().refresh();
                //throw an exception on the last iteration to let testNG mark the test case as failed.
                if (times == maxIters) throw e;
            }
        }
    }

    @AfterClass
    /**
     * Close and quite the web driver and the corresponding browser instance and finalize the test
     */
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    /**
     * The function builds an output string by getting the found web element size using two different methods:
     * getSccValue() and getRect().
     * @return formatted string with the element dimensions.
     */
    public String createOutputString(){
        return String.format("%nThe spinner dot size by getCssValue() :%s by %s.%n" +
                        "The spinner dot size by getRect()     :%dpx by %dpx",
                    element.getCssValue("width"),
                    element.getCssValue("height"),
                    element.getRect().getWidth(),
                    element.getRect().getHeight());
    }
}
