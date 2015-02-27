package interceptors;

import java.util.Enumeration;
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

            if (request.getRemoteHost() != null) {
                rb.setRemoteHost(request.getRemoteHost());
            }

            if (request.getContentType() != null) {
                rb.setContentType(request.getContentType());
            }

            if (request.getRemoteHost() != null) {
                rb.setRemoteHost(request.getRemoteHost());
            }

            if (request.getLocalAddr() != null) {
                rb.setLocalAddress(request.getLocalAddr());
            }

            if (request.getMethod() != null) {
                rb.setMethod(request.getMethod());
            }

            if (request.getPathInfo() != null) {
                rb.setPathInfo(request.getPathInfo());
            }

            if (request.getProtocol() != null) {
                rb.setProtocol(request.getProtocol());
            }

            if (request.getRemoteUser() != null) {
                rb.setRemoteUser(request.getRemoteUser());
            }

            if (request.getRequestURI() != null) {
                rb.setUri(request.getRequestURI());
            }

            if (request.getScheme() != null) {
                rb.setScheme(request.getScheme());
            }

            if (request.getServletPath() != null) {
                rb.setServletPath(request.getServletPath());
            }

            rb.setContentLength(request.getContentLength());

            if (request.getRequestURL().toString() != null) {
                rb.setUrl(request.getRequestURL().toString());
            }

            rb.setRemotePort(request.getRemotePort());

            rb.setLocalPort(request.getLocalPort());

            if (request.getHeaderNames() != null) {
                Enumeration e = request.getHeaderNames();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; e.hasMoreElements(); i++) {
                    sb.append(e.nextElement().toString()).append(", ");
                }
                rb.setHeaderNames(sb.toString());
            }

            if (request.getAttributeNames() != null) {
                Enumeration e = request.getAttributeNames();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; e.hasMoreElements(); i++) {
                    sb.append(e.nextElement().toString()).append(", ");
                }
                rb.setAttributesNames(sb.toString());
            }

            if (request.getAuthType() != null) {
                rb.setAuthType(request.getAuthType());
            }

            if (request.getCharacterEncoding() != null) {
                rb.setCharacterEncoding(request.getCharacterEncoding());
            }

            if (request.getScheme() != null) {
                rb.setScheme(request.getScheme());
            }

            if (request.getLocale() != null) {
                rb.setLocale(request.getLocale().toString());
            }

            if (request.getParameterNames() != null) {
                Enumeration e = request.getParameterNames();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; e.hasMoreElements(); i++) {
                    sb.append(e.nextElement().toString()).append(", ");
                }
                rb.setParameterNames(sb.toString());
            }

            if (request.getHeader("User-Agent") != null) {
                rb.setUserAgent(request.getHeader("User-Agent"));
            }

            if (request.getHeader("Accept-Encoding") != null) {
                rb.setAcceptEncoding(request.getHeader("Accept-Encoding"));
            }

            if (request.getHeader("Origin") != null) {
                rb.setOrigin(request.getHeader("Origin"));
            }

            if (request.getHeader("Accept") != null) {
                rb.setAccept(request.getHeader("Accept"));
            }

            if (request.getHeader("Connection") != null) {
                rb.setConnection(request.getHeader("Connection"));
            }

            new DAO().create(rb);

        } catch (Exception e) {
            logger.error("RequestInterceptor.createRequestBean", e);
        }
    }

} // end class
