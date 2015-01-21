package model.components;

import model.beans.Configuration;
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
    public Configuration getConfiguration() throws Exception{
    
        Configuration configuration = null;
        
        try {
            
            configuration = new DAO().get(1, new Configuration());
            
        } catch (Exception e) {
            throw e;
        }
        
        return configuration;        
    }
    
}