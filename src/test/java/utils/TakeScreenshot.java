package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

/**
 *TakeScreenshot class takes the screenshot of the current screen and returns its absolute path
 * @author  Igor Kun
 * @version 1.0
 * @since   03-Feb-2022
 */
public class TakeScreenshot {

    /**
     * Makes screenshot of the current screen, stores it into the folder provided as a parameter by a caller
     * with a unique name and returns an absolute path as a string
     * @param dir - directory to store a screenshot
     * @return the full path to a screenshot file
     */
    public static String takeScreenShot(String dir) {
        //Make a screenshot of a current screen
        TakesScreenshot takesScreenshot = (TakesScreenshot) InitWebDriverSingleton.InitDriver();
        //Create a file object with the taken screenshot
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        //Generate the screenshot file path using the current time in ms for uniqueness
        String fileName = String.format("%s/Screenshot_%d.png", dir, System.currentTimeMillis());
        //Create a file object using the created physical path
        File destinationFile = new File(fileName);
        try {
            //Attempt to store the screenshot into the physical file
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return fileName;
    }
}
