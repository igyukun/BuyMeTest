package utils;

import constants.Constants;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * The Configurator class provides access to the configuration XML file
 * and allows getting an XML element text or its attribute value.
 * @author  Igor Kun
 * @version 1.0
 * @since   03-Feb-2022
 */

public class Configurator {

    /**
     * Accesses the XML file and returns the element text according to the element name provided by a caller
     * @param keyName --> XML element name
     * @return element text or null if element was not found
     * @see #loadXMLDocument()
     */
    public static String getXMLKeyValue(String keyName) {
        try {
            //call loading XML file method
            Document doc = loadXMLDocument();
            //return the element text
            return doc.getElementsByTagName(keyName).item(0).getTextContent();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Accesses the XML file and returns the Attribute value text according to the
     * element and the attribute name provided by a caller
     * @param keyName   - XML element name
     * @param attrName  - Attribute name
     * @return attribute value text or null if element or attribute was not found
     * @see #loadXMLDocument()
     */
    public static String getXMLAttributreValue(String keyName, String attrName) {
        try {
            Document doc = loadXMLDocument();
            return doc.getElementsByTagName(keyName).item(0).getAttributes().getNamedItem(attrName).getTextContent();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Loads the XML file and creates a Document object.The XML file path is taken from the Constants class
     * @see constants.Constants#CONFIG_FILE
     * @return parsed XML file
     */
    private static Document loadXMLDocument() throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        File xmlFile = new File(ResourceFileLocator.getResourceFileLocation(Constants.CONFIG_FILE));
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return doc;
    }
}
