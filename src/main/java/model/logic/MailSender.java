package model.logic;

import model.beans.Configuration;
import model.beans.Freelancer;
import model.beans.MailTemplate;
import model.components.ConfigurationComponent;
import model.components.MailTemplateComponent;
import model.net.Mail;

/**
 * wrapper for Mail
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
        Configuration configuration = null;        
        MailTemplate mailTemplate = null;

        try {
            
            mailTemplate = new MailTemplateComponent().getAffiliateTemplate(displayLanguage);
            configuration = new ConfigurationComponent().getConfiguration();
            mail = new Mail(
                    configuration.getSmtpHost(), 
                    configuration.getSmtpPort(), 
                    configuration.getSmtpUsername(), 
                    configuration.getSmtpPassword(), 
                    mailTemplate.getSubject(), 
                    mailTemplate.getMessage(), 
                    mailTemplate.getFrom(), 
                    to);
            mail.send();

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
        
        Mail mail = null;
        Configuration configuration = null;        
        MailTemplate mailTemplate = null;

        try {
            
            mailTemplate = new MailTemplateComponent().getTemplateNewFreelancer(displayLanguage);
           
            //replaces
            mailTemplate.setMessage(mailTemplate.getMessage().replace(":name", freelancer.getPerson().getName()));
            mailTemplate.setMessage(mailTemplate.getMessage().replace(":password", password));
            mailTemplate.setMessage(mailTemplate.getMessage().replace(":key", freelancer.getKey()));            
            mailTemplate.setMessage(mailTemplate.getMessage().replace(":email", freelancer.getPerson().getEmail()));            
            
            configuration = new ConfigurationComponent().getConfiguration();
            mail = new Mail(
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

}