package model.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author skuarch
 */
public class Validator {
    
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);    
    private static Matcher matcher;
    
    private Validator(){
    
    }
    
    //==========================================================================
    public static boolean validateEmail(String email) {
        
        if(email == null || email.length() < 1 ){
            return false;
        }

        matcher = pattern.matcher(email);
        return matcher.matches();

    }
    
    //==========================================================================
    public static boolean validatePassword(String password) {

        if (password == null || password.length() < 1) {
            return false;
        }

        return password.length() == 32;

    }
    
}
