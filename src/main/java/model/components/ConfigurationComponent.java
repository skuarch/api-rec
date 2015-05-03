package model.components;

import model.beans.ConfigurationMailAuthentication;
import model.database.DAO;

/**
 *
 * @author skuarch
 */
//@Component
public class ConfigurationComponent {

    //==========================================================================
    public ConfigurationComponent() {
    }
    
    //==========================================================================
    public ConfigurationMailAuthentication getConfiguration() throws Exception{
    
        ConfigurationMailAuthentication configuration = null;
        
        try {
            
            configuration = new DAO().get(1, new ConfigurationMailAuthentication());
            
        } catch (Exception e) {
            throw e;
        }
        
        return configuration;        
    }
    
}