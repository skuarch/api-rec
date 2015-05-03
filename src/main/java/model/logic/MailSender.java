package model.logic;

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

    //==========================================================================
    public static void sendMailNewAffiliate(String name, String to, String displayLanguage) throws Exception {

        if(name == null || name.length() < 1){
            throw new IllegalArgumentException("name is null or empty");
        }
        
        if(displayLanguage == null || displayLanguage.length() < 1){
            throw new IllegalArgumentException("displayLanguage is null or empty");
        }                
        
        Mail mail = null;
        ConfigurationMailAuthentication configuration = null;        
        MailTemplate mailTemplate = null;

        try {
            
            mailTemplate = new MailTemplateComponent().getAffiliateTemplate(displayLanguage);
            configuration = new ConfigurationComponent().getConfiguration();
            
            mail = new Mail(mailTemplate.getFrom(), configuration.getSmtpHost(), configuration.getSmtpPort(), to);
            mail.send(mailTemplate.getSubject(), mailTemplate.getMessage());

        } catch (Exception e) {
            throw e;
        }

    }
    
    //==========================================================================
    public static void sendMailNewFreelancer(Freelancer freelancer,String password,String displayLanguage) throws Exception {

        if(freelancer == null){
            throw new IllegalArgumentException("freelancer is null or empty");
        }
        
        if(displayLanguage == null || displayLanguage.length() < 1){
            throw new IllegalArgumentException("displayLanguage is null or empty");
        }                
        
        if(password == null || password.length() < 1){
            throw new IllegalArgumentException("password is null or empty");
        }                
        
        MailAuthentication mail = null;
        ConfigurationMailAuthentication configuration = null;        
        MailTemplate mailTemplate = null;

        try {
            
            mailTemplate = new MailTemplateComponent().getTemplateNewFreelancer(displayLanguage);
           
            //replaces
            mailTemplate.setMessage(mailTemplate.getMessage().replace(":name", freelancer.getPerson().getName()));
            mailTemplate.setMessage(mailTemplate.getMessage().replace(":password", password));
            mailTemplate.setMessage(mailTemplate.getMessage().replace(":key", freelancer.getKey()));            
            mailTemplate.setMessage(mailTemplate.getMessage().replace(":email", freelancer.getPerson().getEmail()));            
            
            configuration = new ConfigurationComponent().getConfiguration();
            mail = new MailAuthentication(
                    configuration.getSmtpHost(), 
                    configuration.getSmtpPort(), 
                    configuration.getSmtpUsername(), 
                    configuration.getSmtpPassword(), 
                    mailTemplate.getSubject(), 
                    mailTemplate.getMessage(), 
                    mailTemplate.getFrom(), 
                    freelancer.getPerson().getEmail());
            mail.send();

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
            
            mailTemplate = new MailTemplateComponent().getTemplateNewPartner(displayLanguage);
            
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

}