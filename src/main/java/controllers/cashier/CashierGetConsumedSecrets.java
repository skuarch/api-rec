package controllers.cashier;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.CashierSecretConsumed;
import model.components.CashierSecretConsumedComponent;
import org.apache.log4j.Logger;
import org.json.JSONArray;
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
public class CashierGetConsumedSecrets extends BaseController {

    private static final Logger logger = getLogger(CashierGetConsumedSecrets.class);

    @Autowired
    private CashierSecretConsumedComponent cscc;

    //==========================================================================
    @RequestMapping(value = {"/v1/cashier/get/consumed/secrets", "v1/cashier/get/consumed/secrets"})
    public @ResponseBody
    String getConsumedSecretList(@RequestParam("cashierId") long cashierId, HttpServletResponse response) {

        ArrayList<CashierSecretConsumed> cashierSecretConsumedList;
        JSONArray jsona;

        try {

            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);

            cashierSecretConsumedList = cscc.getCashierListConsumedSecrets(cashierId);
            jsona = new JSONArray(cashierSecretConsumedList);

        } catch (Exception e) {
            logger.error("CashierGetConsumedSecrets.getConsumedSecretList", e);
            jsona = new JSONArray();
            jsona.put("error");
        }

        return jsona.toString();

    }

}
