package controllers.cashier;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Cashier;
import model.components.CashierComponent;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch
 */
@RestController
public class CashierGetList extends BaseController {

    private static final Logger logger = getLogger(CashierGetList.class);
    
    @Autowired
    private CashierComponent cashierComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/cashier/get/list","v1/cashier/get/list"})
    public @ResponseBody String getList(HttpServletResponse response){
    
        JSONArray jsona;
        ArrayList<Cashier> cashiers = null;
        
        try {            
            
            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);            
            
            cashiers = (ArrayList<Cashier>) cashierComponent.getCashierList();
            jsona = new JSONArray(cashiers);
            
        } catch (Exception e) {
            logger.error("CashierGetList.getList", e);            
            jsona = new JSONArray();
            jsona.put("error");
        }
        
        return jsona.toString();
    
    }
    
    
    
}
