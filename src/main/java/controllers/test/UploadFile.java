package controllers.test;

import controllers.application.BaseController;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Affiliate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch-lap
 */
@RestController
public class UploadFile extends BaseController{
    
    @RequestMapping(value = "testbean")    
    //public @ResponseBody String uploadFile(@ModelAttribute TestBean tb, HttpServletResponse response){    
    public @ResponseBody String uploadFile(@ModelAttribute Affiliate affiliate, HttpServletResponse response){    
    
        try {           
            
            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);
            System.out.println("que pedo");
            System.out.println("descripcion del afiliado " + affiliate.getBank());
            System.out.println("descripcion del afiliado " + affiliate.getAddress().getAll());
            System.out.println("nombre del archivo " + affiliate.getLogoFile().getOriginalFilename());
            java.io.File file = new java.io.File("/home/skuarch-lap/Documents/api_" + System.currentTimeMillis());
            affiliate.getLogoFile().transferTo(file);
            
        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
        }  
        
        return "{\"termnino\":\"true\"}";
    
    }
    
}
