package utils;

import constants.Constants;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Configurator {

    public static String getXMLKeyValue(String keyName) {
        try {
            Document doc = loadXMLDocument();
            return doc.getElementsByTagName(keyName).item(0).getTextContent();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getXMLAttributreValue(String keyName, String attrName) {
        try {
            Document doc = loadXMLDocument();
            return doc.getElementsByTagName(keyName).item(0).getAttributes().getNamedItem(attrName).getTextContent();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static Document loadXMLDocument() throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        File xmlFile = new File(ResourceFileLocator.getResourceFileLocation(Constants.CONFIG_FILE));
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return doc;
    }
}