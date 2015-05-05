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
public class CashierUpdatePassword extends BaseController {

    private static final Logger logger = getLogger(CashierUpdatePassword.class);    
    
    @Autowired
    private CashierComponent cashierComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/cashier/update/password", "v1/cashier/update/password"})
    public @ResponseBody
    String updateCashier(@ModelAttribute Cashier cashier, HttpServletResponse response) {

        JSONObject jsono = null;
        Cashier c = null;

        try {

            setContentType(response, MediaType.APPLICATION_JSON);
            jsono = new JSONObject();

            if (cashier.getPassword() != null && cashier.getPassword().length() == 32) {
                c = cashierComponent.getCashier(cashier.getId());
                c.setPassword(cashier.getPassword());
                cashierComponent.updateCashier(c);                
                jsono.put("updated", true);
            } else {                
                jsono.put("updated", false);
            }

        } catch (Exception e) {
            logger.error("CashierUpdatePassword.updatePasswordCashier", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
            jsono.put("update", false);
        }

        return jsono.toString();

    }

}
