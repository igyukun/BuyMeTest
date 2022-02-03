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

    public static String getResourceFileLocation(String fileName) {
        String filePath;
        URL fileUrl = ResourceFileLocator.class.getClassLoader().getResource(fileName);
        try {
            filePath = Paths.get(fileUrl.toURI()).toString();
        }catch (URISyntaxException e){
            e.printStackTrace();
            filePath = fileName;
        }
        return filePath;
    }
}
