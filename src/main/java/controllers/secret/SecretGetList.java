package controllers.transfer;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Secret;
import model.components.SecretComponent;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch-lap
 */
@RestController
public class SecretGetList extends BaseController {
   
     private static final Logger logger = getLogger(TransferGetList.class);
    
    @Autowired
    private SecretComponent secretComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/secret/get/list", "v1/secret/get/list"})
    public @ResponseBody String getScretList(HttpServletResponse response){    
        
        JSONArray jsona = null;
        ArrayList<Secret> secrets = null;
        
        try {            
            
            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);
            secrets = secretComponent.getSecretList();
            jsona = new JSONArray(secrets);
            
        } catch (Exception e) {
            logger.error("SecretGetList.getScretList", e);            
            jsona = new JSONArray();
            jsona.put("error");
        }
        
        return jsona.toString();
    
    }
    
}
