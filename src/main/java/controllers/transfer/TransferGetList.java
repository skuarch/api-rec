package controllers.transfer;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Transfer;
import model.components.PartnerComponent;
import model.components.TransferComponent;
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
public class TransferGetList extends BaseController {
   
     private static final Logger logger = getLogger(TransferGetList.class);
    
    @Autowired
    private TransferComponent transferComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/transfer/get/list", "v1/transfer/get/list"})
    public @ResponseBody String getTransferList(HttpServletResponse response){    
        
        JSONArray jsona = null;
        ArrayList<Transfer> transfers = null;
        
        try {            
            
            setContentType(response, MediaType.APPLICATION_JSON);
            transfers = transferComponent.getTransferList();
            jsona = new JSONArray(transfers);
            
        } catch (Exception e) {
            logger.error("TransferGetList.getTransferList", e);            
            jsona = new JSONArray();
            jsona.put("error");
        }
        
        return jsona.toString();
    
    }
    
}
