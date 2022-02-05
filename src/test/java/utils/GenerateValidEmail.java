package utils;

/**
 * The GenerateValidEmail class is a Singleton class that generates unique email address
 * using the current time in ms.
 * @author  Igor Kun
 * @version 1.0
 * @since   03-Feb-2022
 */

public class GenerateValidEmail {

    private static String validEmailAddress;  //static property to hold the generated email address throughout the entire testing session

    /**
     * Generates a unique email address, using constant prefix "testing_" and suffix "@somemail.com"
     * and adding the current time in ms.
     * @return unique email address as String
     * @see System#currentTimeMillis()
     */
    public static String generateValidEmail(){
        if (validEmailAddress == null) {
            validEmailAddress = String.format("testing_%d@somemail.com", System.currentTimeMillis());
            return validEmailAddress;
        }
        return validEmailAddress;
    }
}
