package interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.RequestBean;
import model.database.DAO;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author skuarch
 */
public class RequestInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(RequestInterceptor.class);
    
    //==========================================================================
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        new Thread(() -> {
            createRequestBean(request);
        }).start();
        return true;
    }

    //==========================================================================
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView mav) throws Exception {
    }

    //==========================================================================
    @Override
    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
    }

    //==========================================================================
    private void createRequestBean(HttpServletRequest request) {

        try {

            RequestBean rb = new RequestBean();

            rb.setRemoteHost(request.getRemoteHost());
            rb.setContentType(request.getContentType());
            rb.setLocalAddress(request.getLocalAddr());
            rb.setMethod(request.getMethod());
            rb.setPathInfo(request.getPathInfo());
            rb.setProtocol(request.getProtocol());
            rb.setRemoteUser(request.getRemoteUser());
            rb.setUri(request.getRequestURI());
            rb.setScheme(request.getScheme());
            rb.setServletPath(request.getServletPath());
            rb.setContentLength(request.getContentLength());
            //rb.setUrl(request.getRequestURL().toString());
            rb.setRemotePort(request.getRemotePort());
            rb.setLocalPort(request.getLocalPort());
            //rb.setHeaderNames(request.getHeaderNames().toString());
            //rb.setAttributesNames(request.getAttributeNames().toString());
            rb.setAuthType(request.getAuthType());
            rb.setCharacterEncoding(request.getCharacterEncoding());
            rb.setScheme(request.getScheme());
            rb.setLocale(request.getLocale().toString());
            //rb.setParameterNames(request.getParameterNames().toString());
            rb.setUserAgent(request.getHeader("User-Agent"));
            rb.setAcceptEncoding(request.getHeader("Accept-Encoding"));
            rb.setOrigin(request.getHeader("Origin"));
            rb.setAccept(request.getHeader("Accept"));
            rb.setConnection(request.getHeader("Connection"));

            new DAO().create(rb);

        } catch (Exception e) {
            logger.error("error", e);
        }
    }

} // end class