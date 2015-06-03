package controllers.payment;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Payment;
import model.components.PaymentComponent;
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
public class PaymentGetList extends BaseController {
   
     private static final Logger logger = getLogger(PaymentGetList.class);
    
    @Autowired
    private PaymentComponent paymentComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/payment/get/list", "v1/payment/get/list"})
    public @ResponseBody String getPaymentList(HttpServletResponse response){    
        
        JSONArray jsona = null;
        ArrayList<Payment> payments = null;
        
        try {            
            
            setContentType(response, MediaType.APPLICATION_JSON);
            payments = paymentComponent.getPaymentList();
            jsona = new JSONArray(payments);
            
        } catch (Exception e) {
            logger.error("PaymentGetList.getPaymentList", e);            
            jsona = new JSONArray();
            jsona.put("error");
        }
        
        return jsona.toString();
    
    }
    
}
