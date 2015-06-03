package model.util;

import model.beans.Affiliate;
import model.beans.Depositor;
import model.beans.Freelancer;
import model.beans.Partner;
import model.beans.Transfer;
import model.logic.MailSender;
import org.apache.log4j.Logger;

/**
 * send mails using threads.
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
    
    //==========================================================================
    public static void sendMailNewAffiliate(Affiliate affiliate, String displayLanguage){
    
        new Thread(() -> {
            try {
                
                MailSender.sendMailNewAffiliate(affiliate, displayLanguage);                       
                
            } catch (Exception e) {
                logger.error("MailUtil.sendMailNewAffiliatePerson", e);
            }
        }).start();
        
    }
    
    //==========================================================================
    public static void sendMailUpdateInformation(String email, String displayLanguage){
    
        new Thread(() -> {
            try {
                
                MailSender.sendMailNewUpdateInformation(email, displayLanguage);                       
                
            } catch (Exception e) {
                logger.error("MailUtil.sendMailUpdateInformation", e);
            }
        }).start();
        
    }   
    
    //==========================================================================
    public static void sendMailNewFreelancer(Freelancer freelancer, String displayLanguage){
    
        new Thread(() -> {
            try {
                
                MailSender.sendMailNewFreelancer(freelancer, displayLanguage);                       
                
            } catch (Exception e) {
                logger.error("MailUtil.sendMailNewAffiliatePerson", e);
            }
        }).start();
        
    }
    
    //==========================================================================
    public static void sendMailDepositorNewTransfer(Transfer transfer, String displayLanguage){
    
        new Thread(()->{
            
            try {
                
                MailSender.sendMailDepositorNewTransfer(transfer, displayLanguage);                       
                
            } catch (Exception e) {
                logger.error("MailUtil.sendMailDepositorNewTransfer", e);
            }
            
        }).start();
        
    }
    
    //==========================================================================
    public static void sendMailRecipientNewTransfer(Transfer transfer, String displayLanguage){
    
        new Thread(()->{
            
            try {
                
                MailSender.sendMailDepositorNewTransfer(transfer, displayLanguage);                       
                
            } catch (Exception e) {
                logger.error("MailUtil.sendMailRecipientNewTransfer", e);
            }
            
        }).start();
        
    }
    
}
