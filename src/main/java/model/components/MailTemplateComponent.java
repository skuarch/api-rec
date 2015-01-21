package model.components;

import java.util.ArrayList;
import java.util.HashMap;
import model.beans.MailTemplate;
import model.database.DAO;

/**
 *
 * @author skuarch
 */
//@Component
public class MailTemplateComponent {

    //==========================================================================
    public MailTemplateComponent() {
    }

    //==========================================================================
    public MailTemplate getAffiliateTemplate(String displayLanguage) throws Exception {

        MailTemplate mailTemplate = null;
        HashMap parameters = new HashMap();
        ArrayList<MailTemplate> mailTemplatesList = null;

        try {

            parameters.put("name", "affiliate");
            parameters.put("displayLanguage", displayLanguage);
            mailTemplatesList = new DAO().query(parameters, "getTemplateAffiliate", new MailTemplate());

            if (mailTemplatesList != null && mailTemplatesList.size() > 0) {
                mailTemplate = mailTemplatesList.get(0);
            } else {
                throw new NullPointerException("template doens't exists");
            }

        } catch (Exception e) {
            throw e;
        }

        return mailTemplate;

    }
    
    //==========================================================================
    public MailTemplate getTemplateNewFreelancer(String displayLanguage) throws Exception {

        MailTemplate mailTemplate = null;
        HashMap parameters = new HashMap();
        ArrayList<MailTemplate> mailTemplatesList = null;

        try {

            parameters.put("name", "new Freelancer");
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