package controllers.reports;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.ArrayList;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import model.beans.Transfer;
import model.components.TransferComponent;
import model.util.MailUtil;
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
public class ReportGiftEmail extends BaseController {

    private static final Logger logger = getLogger(ReportGiftEmail.class);       
        
    @Autowired
    private TransferComponent transferComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/report/gift/email", "v1/report/gift/email"})
    public @ResponseBody
    String report(@RequestParam String email, HttpServletResponse response, Locale locale) {

        JSONObject jsono = null;        
        ArrayList<Transfer> transferlistDepositor;
        ArrayList<Transfer> transferlistRecipient;        
        
        try {
            
            transferlistDepositor = transferComponent.getTransferByDepositorEmail(email);            
            transferlistRecipient = transferComponent.getTransferByRecipientEmail(email);                        
            
            // send report by mail
            MailUtil.sendReportGiftByEmail(transferlistDepositor, transferlistRecipient, email, locale.getDisplayLanguage());
            
            jsono = new JSONObject();
            jsono.put("status", true);
            
        } catch (Exception e) {
            logger.error("ReportGiftEmail.report", e);
            jsono = new JSONObject();
            jsono.put("status", false);
        }
        
        return jsono.toString();
        
    }
    
}
