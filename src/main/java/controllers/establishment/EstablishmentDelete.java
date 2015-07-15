package controllers.establishment;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Cashier;
import model.beans.Establishment;
import model.components.CashierComponent;
import model.components.EstablishmentComponent;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch
 */
@RestController
public class EstablishmentDelete extends BaseController {

    private static final Logger logger = getLogger(EstablishmentDelete.class);        
    @Autowired
    private EstablishmentComponent establishmentComponent;
    @Autowired
    private CashierComponent cashierComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/establishment/delete","v1/establishment/delete"})
    public @ResponseBody String establishmentDelete(@RequestParam long establishmentId, HttpServletResponse response){
    
        JSONObject jsono = null;        
        Establishment establishment = null;
        List<Cashier> cashiers;
        
        try {
            
            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);
            
            establishment = establishmentComponent.getEstablishment(establishmentId);
            establishment.setIsSoftDeleted((byte) 1);
            establishmentComponent.updateEstablishment(establishment);
            
            cashiers = establishment.getCashier();
            for (Cashier cashier : cashiers) {
                cashier.setActive((byte)0);
                cashierComponent.updateCashier(cashier);
            }            
            
            jsono = new JSONObject();
            jsono.put("deleted", true);
            
        } catch (Exception e) {
            logger.error("EstablishmentDelete.establishmentDelete", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");            
            jsono.put("deleted", false);
        }
        
        return jsono.toString();
    
    }
    
}
