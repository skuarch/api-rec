package controllers.company;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Company;
import model.components.CompanyComponent;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch
 */
@RestController
public class CompaniesGetByCreator extends BaseController {

    private static final Logger logger = getLogger(CompaniesGetByCreator.class);

    @Autowired
    private CompanyComponent companyComponent;

    //==========================================================================
    @RequestMapping(value = {"v1/company/get/by/creator/"})
    public @ResponseBody
    String getCompaniesByCreator(@RequestParam int creatorId, HttpServletResponse response) {

        JSONArray jsona = null;
        ArrayList<Company> companies = null;

        try {

            if (creatorId < 0) {
                jsona = new JSONArray();
                jsona.put("error");
                return jsona.toString();
            }

            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);
            companies = companyComponent.getCompaniesByCreator(creatorId);
            jsona = new JSONArray(companies);

        } catch (Exception e) {
            logger.error("CompaniesGetByCreator.getCompaniesByCreator", e);
            jsona = new JSONArray();
            jsona.put("error");
        }

        return jsona.toString();

    }

}
