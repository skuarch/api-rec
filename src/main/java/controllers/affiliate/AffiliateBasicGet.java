package controllers.affiliate;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Affiliate;
import model.beans.AffiliateCompanyBasic;
import model.beans.Company;
import model.beans.Establishment;
import model.components.AffiliateComponent;
import model.components.CompanyComponent;
import model.components.EstablishmentComponent;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author skuarch
 */
@Controller
public class AffiliateBasicGet extends BaseController {

    private static final Logger logger = getLogger(AffiliateBasicGet.class);

    @Autowired
    private AffiliateComponent affiliateComponent;
    @Autowired
    private CompanyComponent companyComponent;
    @Autowired
    private EstablishmentComponent establishmentComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/affiliate/basic/get", "v1/affiliate/basic/get"})
    public @ResponseBody
    String getAffiliateBasic(@RequestParam("id") long id, @RequestParam("type") long type, HttpServletResponse response) {

        JSONObject jsono;
        Affiliate affiliate;
        Company company;
        ArrayList<Establishment> establishments = new ArrayList<>();
        AffiliateCompanyBasic affiliateCompanyBasic;

        try {

            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);

            if (type == 1) {
                affiliate = affiliateComponent.getAffiliate(id);
                if (affiliate.getEstablishment().size() > 0) {
                    establishments = (ArrayList<Establishment>) affiliate.getEstablishment();
                }
                affiliateCompanyBasic = new AffiliateCompanyBasic((BigInteger.valueOf(affiliate.getId())), affiliate.getUrlLogo(), affiliate.getBrand(), affiliate.getDescription(), "1");
                affiliateCompanyBasic.setWebsite(affiliate.getWebsite());
                affiliateCompanyBasic.setFacebook(affiliate.getFacebook());
            } else {
                company = companyComponent.getCompany(id);
                if (company.getEstablishment().size() > 0) {
                    establishments = (ArrayList<Establishment>) company.getEstablishment();
                }                
                affiliateCompanyBasic = new AffiliateCompanyBasic((BigInteger.valueOf(company.getId())), company.getUrlLogo(), company.getBrand(), company.getDescription(), "2");
                affiliateCompanyBasic.setWebsite(company.getWebsite());
                affiliateCompanyBasic.setFacebook(company.getFacebook());
            }

            affiliateCompanyBasic.setEstablishment(establishments);
            jsono = new JSONObject(affiliateCompanyBasic);

        } catch (Exception e) {
            logger.error("AffiliateBasicList.getAffiliatesList", e);
            jsono = new JSONObject();
            jsono.put("error", "error");
        }

        return jsono.toString();

    }

}
