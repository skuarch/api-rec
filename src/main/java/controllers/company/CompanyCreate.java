package controllers.company;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.io.File;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Address;
import model.beans.Company;
import model.beans.GeneralConfiguration;
import model.components.AddressComponent;
import model.components.CompanyComponent;
import model.components.ContactComponent;
import model.components.GeneralConfigurationComponent;
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
public class CompanyCreate extends BaseController {

    private static final Logger logger = getLogger(CompanyCreate.class);

    @Autowired
    private AddressComponent addressComponent;
    @Autowired
    private PersonTypeComponent personTypeComponent;
    @Autowired
    private PersonComponent personComponent;
    @Autowired
    private CompanyComponent companyComponent;
    @Autowired
    private ContactComponent contactComponent;
    @Autowired
    private GeneralConfigurationComponent generalConfigurationComponent;

    //==========================================================================    
    @RequestMapping(value = {"/v1/company/create", "v1/company/create"})
    public @ResponseBody
    String createCompany(@ModelAttribute Company company, HttpServletResponse response, Locale locale) {

        long id = 0;
        JSONObject jsono = null;
        long idPersonCompany = 0;
        long idContactTax = 0;
        long idContact = 0;
        boolean updateCompany;
        Address a;
        long addressId;

        try {

            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);

            //validations
            if (company == null || company.getCreatorId() < 1) {
                Exception e = new Exception("company is null or company creatorId is less than 0");
                logger.error("CompanyCreate.createCompany", e);
                jsono = new JSONObject("{\"error\":\"" + e + "\",}");
                jsono.append("created", false);
            }
            
            if (company.getPerson() == null) {
                Exception e = new Exception("company.getPerson() is null");
                logger.error("CompanyCreate.createCompany", e);
                jsono = new JSONObject("{\"error\":\"" + e + "\",}");
                jsono.append("created", false);
            }

            //get person type
            company.getPerson().setPersonType(personTypeComponent.getPersonType(Constants.CONTACT_COMPANY));
            company.getContact().getPerson().setPersonType(personTypeComponent.getPersonType(Constants.CONTACT_BILLING));
            idPersonCompany = personComponent.savePerson(company.getPerson());
            idContactTax = personComponent.savePerson(company.getContact().getPerson());

            company.getPerson().setId(idPersonCompany);
            company.getContact().getPerson().setId(idContactTax);

            idContact = contactComponent.saveContact(company.getContact());
            company.getContact().setId(idContact);

            //create address
            a = company.getAddress();
            addressId = addressComponent.saveAddress(a);
            a.setId(addressId);
            company.setAddress(a);

            //create affiliate
            id = companyComponent.saveCompany(company);
            company.setId(id);

            //if any file exists, saved it
            updateCompany = saveFiles(company);

            //update affiliate because maybe he has a new file/path
            if (updateCompany) {
                companyComponent.updateCompany(company);
            }

            jsono = new JSONObject();
            jsono.append("created", true);
            jsono.append("id", id);

            //create transaction            
            TransactionUtil.createTransaction(Constants.TRANSACTION_NEW_COMPANY, company.getId());

            //send email to the affiliate
            //sendMailNewAffiliate(company, locale.getDisplayLanguage());
            //create notification
        } catch (Exception e) {
            logger.error("CompanyCreate.createCompany", e);
            jsono = new JSONObject("{\"error\":\"" + e.getMessage() + "\",}");
            jsono.append("created", false);
        }

        return jsono.toString();

    }

    //==========================================================================
    private boolean saveFiles(Company company) throws Exception {

        boolean flag = false;
        String originalName;
        String extension = "";
        GeneralConfiguration gc;

        try {

            if (company.getLogoFile() != null) {

                originalName = company.getLogoFile().getOriginalFilename();
                int i = originalName.lastIndexOf('.');
                int p = Math.max(originalName.lastIndexOf('/'), originalName.lastIndexOf('\\'));

                if (i > p) {
                    extension = ".";
                    extension += originalName.substring(i + 1);
                }

                gc = generalConfigurationComponent.getGeneralConfiguration();
                String path = gc.getUploadPath();
                String fileName = "company_" + company.getId() + "_logo_" + System.currentTimeMillis() + extension;
                File f = new File(path + fileName);
                company.getLogoFile().transferTo(f);
                company.setLogoPathName(path + fileName);
                company.setUrlLogo(gc.getUrlStaticImages() + fileName);
                flag = true;
            }

        } catch (Exception e) {
            throw e;
        }

        return flag;

    }

}
