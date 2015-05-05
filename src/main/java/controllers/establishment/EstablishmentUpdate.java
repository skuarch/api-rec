package controllers.establishment;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Establishment;
import model.components.EstablishmentComponent;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch
 */
@RestController
public class EstablishmentUpdate extends BaseController {

    private static final Logger logger = getLogger(EstablishmentUpdate.class);
    
    @Autowired
    private EstablishmentComponent establishmentComponent;        
    
    //==========================================================================
    @RequestMapping(value = {"/v1/establishment/update", "v1/establishment/update"})
    public @ResponseBody String updateEstablishment(@ModelAttribute Establishment establishment,HttpServletResponse response) {
        
        JSONObject jsono = null;
        Establishment e = null;
        
        try {
            
            setContentType(response, MediaType.APPLICATION_JSON);
            
            e = establishmentComponent.getEstablishment(establishment.getId());
            e.setName(establishment.getName());
            e.setCategory(establishment.getCategory());
            e.setSubcategory(establishment.getSubcategory());
            e.setAddress(establishment.getAddress());
            e.setLatitude(establishment.getLatitude());
            e.setLongitude(establishment.getLongitude());
            
            establishmentComponent.updateEstablishment(e);
            
            jsono = new JSONObject();
            jsono.put("updated", true);
            
        } catch (Exception ex) {
            logger.error("EstablishmentUpdate.updateEstablishment", ex);
            jsono = new JSONObject("{\"error\":\"" + ex + "\",}");
            jsono.put("update", false);
        }
        
        return jsono.toString();
        
    }   
    
}
