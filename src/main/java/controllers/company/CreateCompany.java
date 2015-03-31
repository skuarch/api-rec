package controllers.company;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Company;
import model.components.CompanyComponent;
import model.components.ContactComponent;
import model.components.PersonComponent;
import model.components.PersonTypeComponent;
import model.logic.Constants;
import model.util.TransactionUtil;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch
 */
@RestController
public class CreateCompany extends BaseController {

    private static final Logger logger = getLogger(CreateCompany.class);

    @Autowired
    private PersonTypeComponent personTypeComponent;
    @Autowired
    private PersonComponent personComponent;
    @Autowired
    private CompanyComponent companyComponent;    
    @Autowired
    private ContactComponent contactComponent;

    //==========================================================================    
    @RequestMapping(value = {"/v1/company/create", "v1/company/create"})
    public @ResponseBody
    String createAffiliate(@ModelAttribute Company company, HttpServletResponse response, Locale locale) {

        long id = 0;
        JSONObject jsono = null;
        long idPersonCompany = 0;
        long idContactTax = 0;
        long idContact = 0;

        try {
            
            setContentType(response, MediaType.APPLICATION_JSON);
            jsono = new JSONObject();

            //get person type
            company.getPerson().setPersonType(personTypeComponent.getPersonType(Constants.CONTACT));
            company.getContact().getPerson().setPersonType(personTypeComponent.getPersonType(Constants.CONTACT));
            idPersonCompany = personComponent.createPerson(company.getPerson());
            idContactTax = personComponent.createPerson(company.getContact().getPerson());
            
            company.getPerson().setId(idPersonCompany);
            company.getContact().getPerson().setId(idContactTax);
            
            idContact = contactComponent.createContact(company.getContact());            
            company.getContact().setId(idContact);            

            //create affiliate
            id = companyComponent.createCompany(company);
            company.setId(id);
            jsono.append("created", true);
            jsono.append("id", id);

            //create transaction            
            TransactionUtil.createTransaction(Constants.TRANSACTION_NEW_AFFILIATE, company.getId());

            //send email to the affiliate
            //sendMailNewAffiliate(company, locale.getDisplayLanguage());
            //create notification
        } catch (Exception e) {
            logger.error("CreateCompany.createCompany", e);
            jsono = new JSONObject("{\"error\":\"" + e.getMessage() + "\",}");
            jsono.append("created", false);
        }

        return jsono.toString();
        
    }
}