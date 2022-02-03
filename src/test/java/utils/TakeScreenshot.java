package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

/**
 *TakeScreenshot class takes the screeenshot of the current screen and returns its absolute path
 * @author  Igor Kun
 * @version 1.0
 * @since   03-Feb-2022
 */

public class TakeScreenshot {

    public static String takeScreenShot(String dir) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) InitWebDriverSingleton.InitDriver();
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String fileName = String.format("%s/Screenshot_%d.png", dir, System.currentTimeMillis());
        File destinationFile = new File(fileName);
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return fileName;
    }
}
