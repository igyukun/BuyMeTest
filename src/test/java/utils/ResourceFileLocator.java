package utils;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

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
        //ClassLoader classloader = getClass().getClassLoader();
        //return String.valueOf(new File(classloader.getResource(fileName).getFile()));
    }

}
