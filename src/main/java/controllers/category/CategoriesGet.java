package controllers.category;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Category;
import model.components.CategoryComponent;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch
 */
@RestController
public class CategoriesGet extends BaseController {

    private static final Logger logger = getLogger(CategoriesGet.class);

    @Autowired
    private CategoryComponent categoryComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/category/get", "v1/category/get"})
    public @ResponseBody
    String getCategories(HttpServletResponse response) {

        JSONObject jsono = null;
        ArrayList<Category> categories = null;
        JSONArray array = null;

        try {

            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);
            
            categories = categoryComponent.getCategories();

            if (categories == null || categories.size() < 1) {
                array = new JSONArray();
            } else {
                array = new JSONArray(categories);
            }

        } catch (Exception e) {
            logger.error("CategoriesGet.getCategories", e);
            jsono = new JSONObject("{\"error\":\"" + e.getMessage() + "\",}");
            return jsono.toString();
        }

        return array.toString();

    }

}
