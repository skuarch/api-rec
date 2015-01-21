package controllers.application;

import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author skuarch
 */
@Controller
public class Error404 extends BaseController {

    private static final Logger logger = Logger.getLogger(Error404.class);

    //==========================================================================
    @RequestMapping(value = {"/error404", "error404"})
    public ModelAndView error404(HttpServletResponse response) {

        ModelAndView mav = null;

        try {

            setStatus(response, HttpStatus.NOT_FOUND);
            mav = new ModelAndView("application/error404");

        } catch (Exception e) {
            logger.error("error", e);
        }

        return mav;

    }

}
