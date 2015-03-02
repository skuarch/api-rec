package model.components;

import java.util.ArrayList;
import model.database.DAO;
import org.springframework.stereotype.Component;
import model.beans.Category;

/**
 *
 * @author skuarch
 */
@Component
public class CategoryComponent {

    //==========================================================================
    public CategoryComponent() {
    }

    //==========================================================================
    public ArrayList<Category> getCategories() throws Exception {

        ArrayList<Category> categories = null;

        try {
            
            categories = new DAO().query(new Category(), "getCategories");

        } catch (Exception e) {
            throw e;
        }

        return categories;
    }   
    
    public Category getCategory(long id) throws Exception{
        
        if(id < 1){
            throw new IllegalArgumentException("id is less than 1");
        }
        
        Category category = null;
        
        try {
            
            category = new DAO().get(id, new Category());
            
        } catch (Exception e) {
            throw e;
        }
        
        return category;
        
    }

}
