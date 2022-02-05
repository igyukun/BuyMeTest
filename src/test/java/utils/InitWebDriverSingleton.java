package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Singleton class initializes the Webdriver according to the driver type defined in the config.xml
 * @author  Igor Kun
 * @version 1.0
 * @since   03-Feb-2022
 */
public class InitWebDriverSingleton {
    private static WebDriver driver;    //Web driver object to be used throughout the entire testing session
    private static WebDriverWait wait;  //Explicit wait object

    /**
     * Instantiates a web driver object of the specific type according to the configuration key "activeBrowser".
     * The values of the driver name and driver executable are taken from the configuration XML file.
     * @return Web driver instance
     * @see utils.Configurator#getXMLAttributreValue(String, String)
     */
    public static WebDriver InitDriver() {
        if (driver == null) {
            //if driver is not yet instantiated

            //Check the driver type to be used (Chrome, FireFox, Opera etc.)
            String driverType = Configurator.getXMLKeyValue("activeBrowser");
            //Initialize system properties
            System.setProperty(Configurator.getXMLAttributreValue(driverType,"driverName"),
                               Configurator.getXMLAttributreValue(driverType,"driverExec"));
            switch (driverType.toLowerCase()){
                //load the relevant driver
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "opera":
                    driver = new OperaDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "ie":
                    driver = new InternetExplorerDriver();
                    break;
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            return driver;
        }
        return driver;
    }

}

