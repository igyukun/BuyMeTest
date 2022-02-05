package utils;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

/**
 *ResourceFileLocator class returns an absolute path to the file stored in the project "resources" folder
 * @author  Igor Kun
 * @version 1.0
 * @since   03-Feb-2022
 */
public class ResourceFileLocator {

    /**
     * Returns an absolute path to the file located in the resources project folder
     * @param fileName - resource file name
     * @return absolute path to the file as String
     */
    public static String getResourceFileLocation(String fileName) {
        String filePath;
        //Create file URL object using the ClassLoader and the file name
        URL fileUrl = ResourceFileLocator.class.getClassLoader().getResource(fileName);
        try {
            //Get the absolute resource file path by converting a URL to URI to overcome a platform-specific issues
            filePath = Paths.get(fileUrl.toURI()).toString();
        }catch (URISyntaxException e){
            e.printStackTrace();
            filePath = fileName;
        }
        return filePath;
    }
}
