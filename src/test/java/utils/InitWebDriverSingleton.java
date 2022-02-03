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
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static WebDriver InitDriver() {
        if (driver == null) {
            String driverType = Configurator.getXMLKeyValue("activeBrowser");
            System.setProperty(Configurator.getXMLAttributreValue(driverType,"driverName"),
                               Configurator.getXMLAttributreValue(driverType,"driverExec"));
            switch (driverType.toLowerCase()){
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

