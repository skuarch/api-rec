package controllers.company;

import controllers.affiliate.AffiliateUpdateBasicInformation;
import static controllers.application.BaseController.getLogger;
import model.beans.Company;
import model.beans.CompanyBasicInformation;
import model.components.CompanyComponent;
import model.components.PersonTypeComponent;
import model.components.PersonComponent;
import model.logic.Constants;
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
public class CompanyUpdateBasicInformation {

    @Autowired
    private CompanyComponent companyComponent;
    @Autowired
    private PersonTypeComponent personTypeComponent;
    @Autowired
    private PersonComponent personComponent;
    private static final Logger logger = getLogger(AffiliateUpdateBasicInformation.class);

    //==========================================================================
    @RequestMapping(value = {"/v1/company/update/basic/information", "v1/company/update/basic/information"})
    public @ResponseBody
    String updateCompanyBasicInformation(@ModelAttribute CompanyBasicInformation companyBasicInformation) {

        JSONObject jsono = null;
        Company company = null;        

        try {
            
            companyBasicInformation
                    .getPerson()
                    .setPersonType(
                            personTypeComponent.getPersonType(Constants.CONTACT_COMPANY)
                    );
            company = companyComponent.getCompany(companyBasicInformation.getCompanyId());            
            companyBasicInformation.setCompanyId(company.getId());
            company.setName(companyBasicInformation.getName());
            company.setBrand(companyBasicInformation.getBrand());            
            companyBasicInformation.getPerson().setId(company.getPerson().getId());            
            company.setPerson(companyBasicInformation.getPerson());
            personComponent.updatePerson(company.getPerson());
            
            company.setBrand(companyBasicInformation.getBrand());
            company.setCategory(companyBasicInformation.getCategory());            
            company.setPassword(companyBasicInformation.getPassword());
            System.out.println("company na,e " + company.getName());
            companyComponent.updateCompany(company);

            jsono = new JSONObject();
            jsono.put("updated", true);

        } catch (Exception e) {
            logger.error("AffiliateUpdateBasicInformation.updateBasicInformation", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
            jsono.put("updated", false);
        }

        return jsono.toString();

    }

}
