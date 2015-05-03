package model.util;

import model.beans.Partner;
import model.logic.MailSender;
import org.apache.log4j.Logger;

/**
 *
 * @author skuarch-lap
 */
public class MailUtil {

    private static final Logger logger = Logger.getLogger(MailUtil.class);
    
    //==========================================================================
    private MailUtil() {
    }

    //==========================================================================
    public static void sendMailNewPartner(Partner partner, String displayLanguage){
    
        new Thread(() -> {
            try {
                
                MailSender.sendMailNewPartner(partner, displayLanguage);                       
                
            } catch (Exception e) {
                logger.error("MailUtil.sendMailNewAffiliatePerson", e);
            }
        }).start();
        
    }
    
}
