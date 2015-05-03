package model.components;

import model.beans.ConfigurationMail;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch-lap
 */
@Component
public class ConfigurationMailComponent {

    //==========================================================================
    public ConfigurationMailComponent() {
    }
    
    //==========================================================================
    public ConfigurationMail getConfigurationMail() throws Exception{
    
        ConfigurationMail configurationMail = null;
        
        try {
            
            configurationMail = new DAO().get(1, new ConfigurationMail());
            
        } catch (Exception e) {
            throw e;
        }
        
        return configurationMail;
        
    }
    
}
