package model.logic;

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
    public synchronized final String generateKey(int size){
    
        String key;
        
        try {
            
            key = RandomStringUtils.randomAlphanumeric(size).toUpperCase();
            //check into the database if the key exists
            
        } catch (Exception e) {
            throw e;
        }
        
        return key;
        
    }    
    
}
