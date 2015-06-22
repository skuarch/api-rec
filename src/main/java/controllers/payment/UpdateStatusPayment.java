package controllers.payment;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.components.PaymentComponent;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch-lap
 */
@RestController
public class UpdateStatusPayment extends BaseController {

    private static final Logger logger = getLogger(UpdateStatusPayment.class);

    @Autowired
    private PaymentComponent paymentComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/payment/update/status", "v1/payment/update/status"})
    public @ResponseBody
    String updateStatus(@RequestParam("payments") String[] payments, HttpServletResponse response) {

        JSONObject jsono = new JSONObject();

        try {

            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);
            
            if (payments.length < 1) {                
                jsono.put("success", false);
                return jsono.toString();
            }
            
            paymentComponent.updateStatusPayment(payments);            
            jsono.put("success", true);

        } catch (Exception e) {
            logger.error("UpdateStatusPayment.updateStatus", e);
            jsono = new JSONObject();
            jsono.put("success", false);
        }

        return jsono.toString();

    }

}
