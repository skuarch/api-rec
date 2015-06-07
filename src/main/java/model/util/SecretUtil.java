package model.util;

import java.math.BigDecimal;
import model.beans.Secret;
import model.components.SecretStatusComponent;
import model.logic.Constants;
import model.logic.KeyGenerator;

/**
 *
 * @author skuarch
 */
public class SecretUtil {

    private static final SecretStatusComponent secretStatusComponent = new SecretStatusComponent();
    private static final KeyGenerator keyGenerator = new KeyGenerator();
    
    //==========================================================================
    private SecretUtil() {
    }

    //==========================================================================
    public static Secret getSecret(BigDecimal amount) throws Exception {

        // 0 equals 1, the first is biggest -1, the second is biggest            
        if(amount.compareTo(BigDecimal.ZERO) == 0 || amount.compareTo(BigDecimal.ZERO) == -1){
            throw new IllegalArgumentException("amount is incorrect");
        }
        
        Secret secret = null;

        try {
            secret = new Secret();
            secret.setSecretAlphanumeric(keyGenerator.generateSecret(8));
            secret.setValue(amount);
            secret.setSecretStatus(secretStatusComponent.getStatus(Constants.SECRET_STATUS_ACTIVE));
        } catch (Exception e) {
            throw e;
        }
        
        return secret;

    }

}
