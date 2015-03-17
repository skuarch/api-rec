package controllers.category;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.ArrayList;
import model.components.CategoryComponent;
import model.beans.Category;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author skuarch
 */
@RestController
public class GetCategories extends BaseController {

    private static final Logger logger = getLogger(GetCategories.class);

    @Autowired
    private CategoryComponent categoryComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/category/get", "v1/category/get"})
    public @ResponseBody
    String getCategories() {

        JSONObject jsono = null;
        ArrayList<Category> categories = null;
        JSONArray array = null;

        try {

            categories = categoryComponent.getCategories();

            if (categories == null || categories.size() < 1) {
                array = new JSONArray();
            } else {
                array = new JSONArray(categories);
            }

        } catch (Exception e) {
            logger.error("GetCategories.getCategories", e);
            jsono = new JSONObject("{\"error\":\"" + e.getMessage() + "\",}");
            return jsono.toString();
        }

        return array.toString();

    }

}