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
public class Test extends BaseController {
    
    @RequestMapping(value = {"/test", "test"})
    public ModelAndView test(HttpServletResponse response){
        
        ModelAndView mav = null;
        
        try {           
            
            mav = new ModelAndView("application/test");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return mav;
    
    }
    
}