package controllers.cashier;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Cashier;
import model.beans.PersonType;
import model.components.CashierComponent;
import model.components.PersonTypeComponent;
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
public class UpdateCashier extends BaseController{

    @Autowired
    private CashierComponent cashierComponent;
    private static final Logger logger = getLogger(UpdateCashier.class);    
    @Autowired
    private PersonTypeComponent personTypeComponent; 
    PersonType personType = null;        
    
    //==========================================================================
    @RequestMapping(value = {"/v1/cashier/update", "v1/cashier/update"})
    public @ResponseBody String updateCashier(@ModelAttribute Cashier cashier, HttpServletResponse response){
    
        JSONObject jsono = null;
        Cashier c = null;
        
        try {

            setContentType(response, MediaType.APPLICATION_JSON);
            
            personType = personTypeComponent.getPersonType("cashier");
            cashier.getPerson().setPersonType(personType);
            
            c = cashierComponent.getCashier(cashier.getId());
            c.getPerson().setEmail(cashier.getPerson().getEmail());
            c.getPerson().setPhone(cashier.getPerson().getPhone());
            c.getPerson().setLastName(cashier.getPerson().getLastName());
            c.getPerson().setName(cashier.getPerson().getName());
            c.getPerson().setGender(cashier.getPerson().getGender());
            
            personTypeComponent.updatePerson(c.getPerson());
            
            cashierComponent.updateCashier(c);
            jsono = new JSONObject();
            jsono.put("updated", true);
            
        } catch (Exception e) {
            logger.error("UpdateCashier.updateCashier", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
            jsono.put("update", false);
        }  
        
        return jsono.toString();
        
    }
    
}
