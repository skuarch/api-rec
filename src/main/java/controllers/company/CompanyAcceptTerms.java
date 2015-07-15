package controllers.company;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Company;
import model.components.CompanyComponent;
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
public class CompanyAcceptTerms extends BaseController {

    private static final Logger logger = getLogger(CompanyAcceptTerms.class);

    @Autowired
    private CompanyComponent companyComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/company/accept/terms", "v1/company/accept/terms"})
    public @ResponseBody
    String acceptTerms(@RequestParam("id") long id, HttpServletResponse response) {

        JSONObject jsono = new JSONObject();
        Company company;

        try {

            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);

            company = companyComponent.getCompany(id);

            if (company == null) {
                jsono.put("accept", false);
            } else {
                company.setApproved((byte) 1);
                companyComponent.updateCompany(company);
                jsono.put("accept", true);
            }

        } catch (Exception e) {
            logger.error("CompanyAcceptTerms.acceptTerms", e);
            jsono = new JSONObject();
            jsono.put("error", "error");
        }

        return jsono.toString();

    }

}
