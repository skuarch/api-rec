package controllers.cashier;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Cashier;
import model.beans.Freelancer;
import model.components.CashierComponent;
import model.logic.Constants;
import model.util.TransactionUtil;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch-lap
 */
@RestController
public class CashierToggleActive extends BaseController {

    private static final Logger logger = getLogger(CashierToggleActive.class);

    @Autowired
    private CashierComponent cashierComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/cashier/toggle/active", "v1/cashier/toggle/active"})
    public @ResponseBody
    String toggleCashierActive(@ModelAttribute Freelancer f, HttpServletResponse response, Locale locale) {
        
        JSONObject jsono;
        Cashier cashier;

        try {

            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);
            cashier = cashierComponent.getCashier(f.getId());

            jsono = new JSONObject();
            if (cashier == null) {
                jsono.put("updated", false);
            } else {
                if (cashier.getActive() == 0) {
                    cashier.setActive((byte) 1);
                    TransactionUtil.createTransaction(Constants.TRANSACTION_ACTIVATE_CASHIER, cashier.getId());
                } else {
                    cashier.setActive((byte) 0);
                    TransactionUtil.createTransaction(Constants.TRANSACTION_DEACTIVATE_CASHIER, cashier.getId());
                }
                cashierComponent.updateCashier(cashier);
                jsono.put("updated", true);
            }

        } catch (Exception e) {
            logger.error("CashierToggleActive.toggleCashierActive", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
        }

        return jsono.toString();

    }

}
