package controllers.partner;

import java.util.Locale;
import model.beans.Partner;
import model.beans.Person;
import model.components.PartnerComponent;
import model.components.PersonComponent;
import model.components.PersonTypeComponent;
import model.logic.Constants;
import model.util.MailUtil;
import org.apache.log4j.Logger;
import static org.apache.log4j.Logger.getLogger;
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
public class PartnerCreate {
    
    private static final Logger logger = getLogger(PartnerCreate.class);    
    
    @Autowired
    private PartnerComponent partnerComponent;
    @Autowired
    private PersonComponent personComponent;
    @Autowired
    private PersonTypeComponent personTypeComponent;
    
    //==========================================================================
    @RequestMapping(value ="/v1/partner/create")
    public @ResponseBody String createPartner(@ModelAttribute Partner partner,Locale locale){
    
        long id;
        long personId;
        JSONObject jsono = null;
        
        try {
           
           //set personType
           partner.getPerson().setPersonType(personTypeComponent.getPersonType(Constants.PARTNER));
            
           //create person
           personId = createPerson(partner.getPerson());
           partner.getPerson().setId(personId);
           partner.setActive((byte) 1);
           
           //save new partner into db
           id = partnerComponent.createPartner(partner);
           jsono = new JSONObject();
           jsono.put("created", true);
           jsono.put("id", id);
           
           //send email to new partner
            MailUtil.sendMailNewPartner(partner, locale.getDisplayLanguage());
            
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
            id = personComponent.createPerson(p);
            
        } catch (Exception e) {
            throw e;
        }
        
        return id;
    
    }
    
}
