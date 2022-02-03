package utils;

/**
 * The GenerateValidEmail class is a Singleton class tat generates unique email address
 * using the current time in ms.
 * @author  Igor Kun
 * @version 1.0
 * @since   03-Feb-2022
 */

public class GenerateValidEmail {

    private static String validEmailAddress;

    public static String generateValidEmail(){
        if (validEmailAddress == null) {
            validEmailAddress = String.format("testing_%d@somemail.com", System.currentTimeMillis());
            return validEmailAddress;
        }
        return validEmailAddress;
    }
}
