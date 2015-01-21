package controllers.application;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author skuarch
 */
public class BaseController {

    private final static Logger logger = Logger.getLogger(BaseController.class);

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    //==========================================================================
    public void setContentType(HttpServletResponse response, String contenType) {
        response.setContentType(contenType);
    }

    //==========================================================================
    public void setHeaderNoChache(HttpServletResponse response) {

        if (response != null) {
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
        }

    }

    //==========================================================================
    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException(Throwable t, HttpServletRequest request, Exception exception) {
        logger.error("error", t);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    //==========================================================================
    public static Logger getLogger() {
        return logger;
    }

    //==========================================================================
    public static Logger getLogger(Class c) {
        Logger l = Logger.getLogger(c.getName());
        return l;
    }

    //==========================================================================
    public void setStatus(HttpServletResponse response, HttpStatus status) {

        if (response != null && status != null) {
            response.setStatus(status.value());
        }

    }

}
