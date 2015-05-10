package model.logic;

import model.beans.Affiliate;
import model.beans.ConfigurationMailAuthentication;
import model.beans.ConfigurationMail;
import model.beans.Freelancer;
import model.beans.MailTemplate;
import model.beans.Partner;
import model.components.ConfigurationComponent;
import model.components.ConfigurationMailComponent;
import model.components.MailTemplateComponent;
import model.net.Mail;
import model.net.MailAuthentication;

/**
 * wrapper for MailAuthentication and Mail
 *
 * @author skuarch
 */
public class MailSender {    

    //==========================================================================
    private MailSender() {
    }

    //==========================================================================z
    public static void sendMailNewAffiliate(Affiliate affiliate, String displayLanguage) throws Exception {

        if(affiliate == null){
            throw new IllegalArgumentException("affiliate is null or empty");
        }
        
        if(displayLanguage == null || displayLanguage.length() < 1){
            throw new IllegalArgumentException("displayLanguage is null or empty");
        }                
        
        Mail mail = null;
        ConfigurationMail configurationMail = null;        
        MailTemplate mailTemplate = null;

        try {
            
            mailTemplate = new MailTemplateComponent().getTemplate("new affiliate", displayLanguage);
            mailTemplate.setMessage(mailTemplate.getMessage().replace(":name", affiliate.getPerson().getName()));
            
            configurationMail = new ConfigurationMailComponent().getConfigurationMail();
            mail = new Mail(
                    mailTemplate.getFrom(), 
                    configurationMail.getSmtpHost(), 
                    configurationMail.getSmtpPort(), 
                    affiliate.getPerson().getEmail()
            );
            
            mail.send(mailTemplate.getSubject(), mailTemplate.getMessage());
            
        } catch (Exception e) {
            throw e;
        }

    }
    
    //==========================================================================
    public static void sendMailNewFreelancer(Freelancer freelancer,String displayLanguage) throws Exception {

        if(freelancer == null){
            throw new IllegalArgumentException("freelancer is null or empty");
        }
        
        if(displayLanguage == null || displayLanguage.length() < 1){
            throw new IllegalArgumentException("displayLanguage is null or empty");
        }                
        
        Mail mail = null;
        ConfigurationMail configurationMail = null;        
        MailTemplate mailTemplate = null;

        try {
            
            mailTemplate = new MailTemplateComponent().getTemplate("new freelancer", displayLanguage);
            mailTemplate.setMessage(mailTemplate.getMessage().replace(":name", freelancer.getPerson().getName()));
            mailTemplate.setMessage(mailTemplate.getMessage().replace(":email", freelancer.getPerson().getEmail()));
            
            configurationMail = new ConfigurationMailComponent().getConfigurationMail();
            mail = new Mail(
                    mailTemplate.getFrom(), 
                    configurationMail.getSmtpHost(), 
                    configurationMail.getSmtpPort(), 
                    freelancer.getPerson().getEmail()
            );
            
            mail.send(mailTemplate.getSubject(), mailTemplate.getMessage());

        } catch (Exception e) {
            throw e;
        }

    }
    
    
    //==========================================================================
    public static void sendMailNewPartner(Partner partner, String displayLanguage) throws Exception {

        if(partner == null){
            throw new IllegalArgumentException("partner is null or empty");
        }
        
        if(displayLanguage == null || displayLanguage.length() < 1){
            throw new IllegalArgumentException("displayLanguage is null or empty");
        }                
        
        Mail mail = null;
        ConfigurationMail configurationMail = null;        
        MailTemplate mailTemplate = null;

        try {
            
            mailTemplate = new MailTemplateComponent().getTemplate("new partner", displayLanguage);
            
            configurationMail = new ConfigurationMailComponent().getConfigurationMail();
            mail = new Mail(
                    mailTemplate.getFrom(), 
                    configurationMail.getSmtpHost(), 
                    configurationMail.getSmtpPort(), 
                    partner.getPerson().getEmail()
            );
            
            mail.send(mailTemplate.getSubject(), mailTemplate.getMessage());
            
        } catch (Exception e) {
            throw e;
        }

    }
    
    //==========================================================================
    public static void sendMailNewUpdateInformation(String email, String displayLanguage) throws Exception {
        
        if(displayLanguage == null || displayLanguage.length() < 1){
            throw new IllegalArgumentException("displayLanguage is null or empty");
        } 
        
        Mail mail = null;
        ConfigurationMail configurationMail = null;        
        MailTemplate mailTemplate = null;

        try {
            
            mailTemplate = new MailTemplateComponent().getTemplate("update information", displayLanguage);
            
            configurationMail = new ConfigurationMailComponent().getConfigurationMail();
            mail = new Mail(
                    mailTemplate.getFrom(), 
                    configurationMail.getSmtpHost(), 
                    configurationMail.getSmtpPort(), 
                    email
            );
            
            mail.send(mailTemplate.getSubject(), mailTemplate.getMessage());
            
        } catch (Exception e) {
            throw e;
        }
        
    }

}