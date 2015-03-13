package controllers.establishment;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
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
public class GetEstablishment extends BaseController {

    @Autowired
    private EstablishmentComponent establishmentComponent;    
    private static final Logger logger = getLogger(GetEstablishment.class);
    
    @RequestMapping(value = {"/v1/establishment/get","v1/establishment/get"})
    public @ResponseBody String getEstablishment(@ModelAttribute Establishment establishment){
        
        JSONObject jsono = null;
        Establishment e = null;
        
        if(establishment.getId() < 1){
            logger.error("GetEstablishment.getEstablishment");
            jsono = new JSONObject("{\"error\":\"\",}");
            jsono.put("update", false);
            return jsono.toString();
        }
        
        try {
            
            e = establishmentComponent.getEstablishment(establishment.getId());
            
            if(e !=null){
                jsono = new JSONObject(e);
            }else{
                jsono = new JSONObject();
                jsono.put("error", "establishment doesn't exits");
            }            
            
        } catch (Exception ex) {
            logger.error("GetEstablishment.getEstablishment", ex);
            jsono = new JSONObject("{\"error\":\"" + ex + "\",}");
            jsono.put("error", ex.toString());
        }
        
        return jsono.toString();
        
    }
    
    
}
