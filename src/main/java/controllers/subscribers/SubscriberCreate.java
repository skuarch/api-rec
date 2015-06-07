package controllers.subscribers;

import controllers.application.BaseController;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Subscriber;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch
 */
@RestController
public class SubscriberCreate extends BaseController {

    private static final Logger logger = getLogger(SubscriberCreate.class);    
    
    //==========================================================================
    @RequestMapping(value = {"/v1/subscriber/create", "v1/subscriber/create"})    
    public @ResponseBody
    String createSubscriber(@ModelAttribute Subscriber subscriber, HttpServletResponse response, Locale locale) {
        
        long id = 0;
        JSONObject jsono = null;

        try {

            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);
            jsono = new JSONObject();
            
            //TransactionUtil.createTransaction(Constants.TRANSACTION_NEW_SUSBCRIBER, r.getId());
            
        } catch (Exception e) {
            logger.error("SubscriberCreate.createSubscriber", e);
            jsono = new JSONObject("{\"error\":\"" + e.getMessage() + "\",}");
            jsono.put("created", false);
        }

        return jsono.toString();

    }
    
    
}
