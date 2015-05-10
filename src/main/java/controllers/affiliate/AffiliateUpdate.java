package controllers.affiliate;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Affiliate;
import model.components.AffiliateComponent;
import model.components.PersonTypeComponent;
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
public class AffiliateUpdate extends BaseController {

    @Autowired
    private AffiliateComponent affiliateComponent;
    @Autowired
    private PersonTypeComponent personTypeComponent;
    private static final Logger logger = getLogger(AffiliateUpdate.class);

    @RequestMapping(value = {"/v1/affiliate/update", "v1/affiliate/update"})
    public @ResponseBody String updateAffiliate(@ModelAttribute Affiliate affiliate, HttpServletResponse response) {

        JSONObject jsono = null;
        Affiliate a = null;

        try {

            setContentType(response, MediaType.APPLICATION_JSON);            
            
            a = affiliateComponent.getAffiliate(affiliate.getId());
            a.getPerson().setPersonType(personTypeComponent.getPersonType("affiliate"));            
            a.getPerson().setName(affiliate.getPerson().getName());
            a.getPerson().setLastName(affiliate.getPerson().getLastName());            
            a.getPerson().setEmail(affiliate.getPerson().getEmail());
            a.setPassword(affiliate.getPassword());
            a.setAddress(affiliate.getAddress());
            a.getPerson().setPhone(affiliate.getPerson().getPhone());
            
            affiliateComponent.updateAffiliate(a);
            
            jsono = new JSONObject();
            jsono.put("updated", true);
            
            //TransactionUtil.createTransaction(Constants.TRANSACTION_UPDATE_BASIC_INFORMATION_AFFILIATE, affiliate.getId());
            
        } catch (Exception e) {
            logger.error("AffiliateUpdate.updateAffiliate", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
            jsono.put("update", false);
        }

        return jsono.toString();

    }

}
