package model.logic;

import model.beans.Secret;
import model.components.SecretComponent;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author skuarch-lap
 */
public class KeyGenerator {

    //==========================================================================
    public KeyGenerator() {
    }

    //==========================================================================
    public synchronized final String generateSecret(int size) throws Exception {

        String secret;
        Secret s;

        try {

            secret = RandomStringUtils.randomAlphanumeric(size).toUpperCase();            

            //check into the database if the secret exists
            s = new SecretComponent().getSecret(secret);

            while (true) {
                if (s == null) {
                    break;
                } else {
                    s = new SecretComponent().getSecret(secret);
                }
            }

        } catch (Exception e) {
            throw e;
        }

        return secret;

    }

}
