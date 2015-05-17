package controllers.company;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Company;
import model.components.CompanyComponent;
import model.logic.Constants;
import model.util.TransactionUtil;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch-lap
 */
@RestController
public class CompanyToggleActive extends BaseController {

    private static final Logger logger = getLogger(CompanyToggleActive.class);
    
    @Autowired
    private CompanyComponent companyComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/company/toggle/active", "v1/company/toggle/active"}, method = RequestMethod.POST)
    public @ResponseBody
    String toggleCompanyActive(@ModelAttribute Company c, HttpServletResponse response, Locale locale) {
        
        JSONObject jsono;
        Company company;

        try {

            setContentType(response, MediaType.APPLICATION_JSON);
            company = companyComponent.getCompany(c.getId());

            jsono = new JSONObject();
            if (company == null) {
                jsono.put("updated", false);
            } else {
                if (company.getActive() == 0) {
                    company.setActive((byte) 1);
                    TransactionUtil.createTransaction(Constants.TRANSACTION_ACTIVATE_COMPANY, company.getId());
                } else {
                    company.setActive((byte) 0);
                    TransactionUtil.createTransaction(Constants.TRANSACTION_DEACTIVATE_COMPANY, company.getId());
                }
                companyComponent.updateCompany(company);                
                jsono.put("updated", true);
            }

        } catch (Exception e) {
            logger.error("ToggleCompanyActive.toggleCompanyActive", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
        }

        return jsono.toString();

    }

}
