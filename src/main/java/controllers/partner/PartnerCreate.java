package controllers.partner;

import controllers.application.BaseController;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import model.beans.Partner;
import model.beans.Person;
import model.components.PartnerComponent;
import model.components.PersonComponent;
import model.components.PersonTypeComponent;
import model.logic.Constants;
import model.util.MailUtil;
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
 * @author skuarch-lap
 */
@RestController
public class PartnerCreate extends BaseController {
    
    private static final Logger logger = getLogger(PartnerCreate.class);    
    
    @Autowired
    private PartnerComponent partnerComponent;
    @Autowired
    private PersonComponent personComponent;
    @Autowired
    private PersonTypeComponent personTypeComponent;
    
    //==========================================================================
    @RequestMapping(value ="/v1/partner/create")
    public @ResponseBody String createPartner(@ModelAttribute Partner partner,Locale locale, HttpServletResponse response){
    
        long id;
        long personId;
        JSONObject jsono = null;
        
        try {
           
            setHeaderNoChache(response);
            
           //set personType
           partner.getPerson().setPersonType(personTypeComponent.getPersonType(Constants.PARTNER));
            
           //create person
           personId = createPerson(partner.getPerson());
           partner.getPerson().setId(personId);
           partner.setActive((byte) 1);
           
           //save new partner into db
           id = partnerComponent.savePartner(partner);
           jsono = new JSONObject();
           jsono.put("created", true);
           jsono.put("id", id);
           
           //send email to new partner
            MailUtil.sendMailNewPartner(partner, locale.getDisplayLanguage());
            
            TransactionUtil.createTransaction(Constants.TRANSACTION_NEW_PARTNER, partner.getId());
            
        } catch (Exception e) {
            logger.error("PartnerCreate.createPartner", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
            jsono.put("created", false);
        }
        
        return jsono.toString();
    
    }
    
    //==========================================================================
    private long createPerson(Person p) throws Exception{
    
        long id;
        
        try {
            
            p.setPersonType(personTypeComponent.getPersonType(Constants.PARTNER));
            id = personComponent.savePerson(p);
            
        } catch (Exception e) {
            throw e;
        }
        
        return id;
    
    }
    
}
