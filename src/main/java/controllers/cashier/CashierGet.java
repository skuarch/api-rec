package controllers.cashier;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Cashier;
import model.components.CashierComponent;
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
public class CashierGet extends BaseController {

    @Autowired
    private CashierComponent cashierComponent;
    private static final Logger logger = getLogger(CashierGet.class);

    //==========================================================================
    @RequestMapping(value = {"/v1/cashier/get", "v1/cashier/get"})
    public @ResponseBody String getCashier(@ModelAttribute Cashier cashier, HttpServletResponse response) {

        JSONObject jsono = null;
        Cashier c = null;

        try {

            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);
            
            if (cashier.getId() < 1) {
                logger.error("GetCashier.getCashier", new Exception("cashier id is less than 1"));
                jsono = new JSONObject("{\"error\":\"error\",}");
                return jsono.toString();
            }

            setContentType(response, MediaType.APPLICATION_JSON);
            c = cashierComponent.getCashier(cashier.getId());
            jsono = new JSONObject(c);

        } catch (Exception e) {
            logger.error("CashierGet.getCashier", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
            jsono.put("update", false);
        }

        return jsono.toString();

    }

}
