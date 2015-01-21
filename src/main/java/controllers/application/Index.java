package controllers.application;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author skuarch
 */
@RestController
public class Index extends BaseController {
    
    @RequestMapping(value = {"/"})
    public ModelAndView test(HttpServletResponse response){
        
        ModelAndView mav = null;
        
        try {           
            
            mav = new ModelAndView("application/index");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return mav;
    
    }
    
}