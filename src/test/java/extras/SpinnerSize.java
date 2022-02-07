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

public class SpinnerSize {

    public final By BOUNCE_LOCATOR = By.xpath("/html/body/div[1]/div/div[2]/div/div/div[2]");

    WebElement element;
    WebDriver driver;

    @BeforeClass
    public void initDriver(){
        driver = InitWebDriverSingleton.InitDriver();
        driver.manage().window().maximize();
        driver.get("https://buyme.co.il");

    }

    @Test
    public void getSpinnerSize() {

        //create fluent wait method
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).
                withTimeout(Duration.ofMillis(5000)).   //general wait timeout
                pollingEvery(Duration.ofMillis(50)).    //polling timeout
                ignoring(NoSuchElementException.class); //ignored exception

        //The spinner element is visible only on the loading screen.
        //iterate up to 10 times while refreshing the webpage each time the wait timeout is expiring
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
                //element could not been found
                System.out.println(e.getMessage());
                //refresh the web page
                driver.navigate().refresh();
                //throw an exception on the last iteration
                if (times == maxIters) throw e;
            }
        }
    }

    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    public String createOutputString(){
        return String.format("%nThe spinner dot size by getCssValue() :%s by %s.%n" +
                        "The spinner dot size by getRect()     :%dpx by %dpx",
                    element.getCssValue("width"),
                    element.getCssValue("height"),
                    element.getRect().getWidth(),
                    element.getRect().getHeight());
    }
}
