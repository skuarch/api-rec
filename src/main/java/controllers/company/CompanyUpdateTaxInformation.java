package controllers.company;

import controllers.affiliate.AffiliateUpdateTaxInformation;
import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Address;
import model.beans.Company;
import model.beans.CompanyTaxInformation;
import model.beans.Contact;
import model.components.AddressComponent;
import model.components.CompanyComponent;
import model.components.PersonComponent;
import model.components.PersonTypeComponent;
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
public class CompanyUpdateTaxInformation extends BaseController{

    @Autowired
    private CompanyComponent companyComponent;
    @Autowired
    private PersonTypeComponent personTypeComponent;
    @Autowired
    private PersonComponent personComponent;
    @Autowired
    private AddressComponent addressComponent;
    private static final Logger logger = getLogger(AffiliateUpdateTaxInformation.class);
    
    //==========================================================================
        @RequestMapping(value = {"/v1/company/update/tax/information","v1/company/update/tax/information"})
    public @ResponseBody String updateTaxInformation(@ModelAttribute CompanyTaxInformation companyTaxInformation, HttpServletResponse response){
    
        JSONObject jsono = null;
        Company company = null;
        Contact contact = null;
        Address address = null;
        
        try {
            
            setContentType(response, MediaType.APPLICATION_JSON);    

            //get company from database
            company = companyComponent.getCompany(companyTaxInformation.getId());
            
            //update contact
            contact = companyTaxInformation.getContact();
            contact.getPerson().setPersonType(personTypeComponent.getPersonType(Constants.CONTACT));
            contact.setId(company.getContact().getId());
            contact.getPerson().setId(company.getContact().getPerson().getId());              
            personComponent.updatePerson(contact.getPerson());
            
            //update address
            address = companyTaxInformation.getAddress();
            address.setId(company.getAddress().getId());
            addressComponent.updateAddress(address);
            
            company.setTaxId(companyTaxInformation.getTaxId());
            company.setTaxCompanyName(companyTaxInformation.getTaxCompanyName());
            company.setTaxId(companyTaxInformation.getTaxId());
            company.setTaxCompanyName(companyTaxInformation.getTaxCompanyName());
            company.setAddress(companyTaxInformation.getAddress());               
            
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
