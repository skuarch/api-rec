package controllers.affiliate;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Affiliate;
import model.beans.Establishment;
import model.components.AffiliateComponent;
import org.apache.log4j.Logger;
import org.json.JSONArray;
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
public class AffiliateGetEstablishment extends BaseController {

    private static final Logger logger = getLogger(AffiliateGetEstablishment.class);

    @Autowired
    private AffiliateComponent affiliateComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/affiliate/get/establishment", "v1/affiliate/get/establishment"})
    public @ResponseBody
    String getEstablishments(@ModelAttribute Affiliate affiliate, HttpServletResponse response, Locale locale) {

        JSONArray jsona;
        List<Establishment> establishments;

        try {

            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);
            
            affiliate = affiliateComponent.getAffiliate(affiliate.getId());
            establishments = affiliate.getEstablishment();
            jsona = new JSONArray(establishments);

        } catch (Exception e) {
            logger.error("AffiliateGetEstablishment.getEstablishments", e);
            jsona = new JSONArray();
            jsona.put(0, "error");
        }

        return jsona.toString();

    }

}
