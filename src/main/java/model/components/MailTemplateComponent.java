package model.components;

import java.util.ArrayList;
import java.util.HashMap;
import model.beans.MailTemplate;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch
 */
@Component
public class MailTemplateComponent {

    //==========================================================================
    public MailTemplateComponent() {
    }
    
    //==========================================================================
    public MailTemplate getTemplate(String templateName, String displayLanguage) throws Exception {

        if(templateName == null || templateName.length() < 0){
            throw new IllegalArgumentException("templateName is null or empty");
        }
        
        if(displayLanguage == null || displayLanguage.length() < 0){
            throw new IllegalArgumentException("displayLanguage is null or empty");
        }
        
        MailTemplate mailTemplate = null;
        HashMap parameters = new HashMap();
        ArrayList<MailTemplate> mailTemplatesList = null;

        try {

            parameters.put("name", templateName);
            parameters.put("displayLanguage", displayLanguage);
            mailTemplatesList = new DAO().query(parameters, "getTemplate", new MailTemplate());

            if (mailTemplatesList != null && mailTemplatesList.size() > 0) {
                mailTemplate = mailTemplatesList.get(0);
            } else {
                throw new NullPointerException("template doesn't exists");
            }

        } catch (Exception e) {
            throw e;
        }

        return mailTemplate;

    }

}