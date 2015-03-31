package controllers.affiliate;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Address;
import model.beans.Affiliate;
import model.beans.AffiliateTaxInformation;
import model.beans.Contact;
import model.components.AddressComponent;
import model.components.AffiliateComponent;
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
public class AffiliateUpdateTaxInformation extends BaseController {

    @Autowired
    private AffiliateComponent affiliateComponent;
    @Autowired
    private PersonTypeComponent personTypeComponent;
    @Autowired
    private PersonComponent personComponent;
    @Autowired
    private AddressComponent addressComponent;
    private static final Logger logger = getLogger(AffiliateUpdateTaxInformation.class);
    
    //==========================================================================
    @RequestMapping(value = {"/v1/affiliate/update/tax/information", "v1/affiliate/update/tax/information"})
    public @ResponseBody String updateTaxInformation(@ModelAttribute AffiliateTaxInformation affiliateTaxInformation, HttpServletResponse response){
    
        JSONObject jsono = null;
        Affiliate affiliate = null;
        Contact contact = null;
        Address address = null;
        
        try {
            
            setContentType(response, MediaType.APPLICATION_JSON);    

            //get affiliate from database
            affiliate = affiliateComponent.getAffiliate(affiliateTaxInformation.getId());
            
            //update contact
            contact = affiliateTaxInformation.getContact();
            contact.getPerson().setPersonType(personTypeComponent.getPersonType(Constants.CONTACT));
            contact.setId(affiliate.getContact().getId());
            contact.getPerson().setId(affiliate.getContact().getPerson().getId());              
            personComponent.updatePerson(contact.getPerson());
            
            //update address
            address = affiliateTaxInformation.getAddress();
            address.setId(affiliate.getAddress().getId());
            addressComponent.updateAddress(address);
            
            affiliate.setTaxId(affiliateTaxInformation.getTaxId());
            affiliate.setTaxCompanyName(affiliateTaxInformation.getTaxCompanyName());
            affiliate.setAddress(affiliateTaxInformation.getAddress());               
            
            affiliateComponent.updateAffiliate(affiliate);            
            
            jsono = new JSONObject();
            jsono.put("update", true);
            
        } catch (Exception e) {
            logger.error("AffiliateUpdateBasicInformation.updateBasicInformation", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
            jsono.put("update", false);
        }
        
        return jsono.toString();
        
    }
}
