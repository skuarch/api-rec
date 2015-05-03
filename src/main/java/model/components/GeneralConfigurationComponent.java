package model.components;

import model.beans.GeneralConfiguration;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch-lap
 */
@Component
public class GeneralConfigurationComponent {

    //==========================================================================
    public GeneralConfigurationComponent() {
    }
    
    //==========================================================================
    public GeneralConfiguration getGeneralConfiguration() throws Exception{
    
        long id = 1l;
        GeneralConfiguration gc;
        
        try {
            
            gc = new DAO().get(id, new GeneralConfiguration());
            
        } catch (Exception e) {
            throw e;
        }
        
        return gc;
    
    }
    
}
