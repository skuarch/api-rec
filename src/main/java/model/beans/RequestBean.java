package model.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "request")
public class RequestBean implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private long id;
    @Column(name = "request_remote_host")
    private String remoteHost;
    @Column(name = "request_content_type")
    private String contentType;
    @Column(name = "request_local_address")
    private String localAddress;
    @Column(name = "request_method")
    private String method;
    @Column(name = "request_path_info")
    private String pathInfo;
    @Column(name = "request_protocol")
    private String protocol;
    @Column(name = "request_remote_user")
    private String remoteUser;
    @Column(name = "request_uri")
    private String uri;
    @Column(name = "request_scheme")
    private String scheme;
    @Column(name = "request_servlet_path")
    private String servletPath;
    @Column(name = "request_content_length")
    private int contentLength;
    @Column(name = "request_url")
    private String url;
    @Column(name = "request_remote_port")
    private int remotePort;
    @Column(name = "request_local_port")
    private int localPort;
    @Column(name = "request_header_names")
    private String headerNames;
    @Column(name = "request_attribute_names", columnDefinition = "text")
    private String attributesNames;
    @Column(name = "request_auth_type")
    private String authType;
    @Column(name = "request_charactect_encoding")
    private String characterEncoding;   
    @Column(name = "request_locale")
    private String locale;
    @Column(name = "request_parameter_names", columnDefinition = "text")
    private String ParameterNames;    
    @Column(name = "request_user_agent")
    private String userAgent;
    @Column(name = "request_accept_encoding")
    private String acceptEncoding;
    @Column(name = "request_origin")
    private String origin;
    @Column(name = "request_accept")
    private String accept;
    @Column(name = "request_connection")
    private String connection;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPathInfo() {
        return pathInfo;
    }

    public void setPathInfo(String pathInfo) {
        this.pathInfo = pathInfo;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getServletPath() {
        return servletPath;
    }

    public void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }

    public int getLocalPort() {
        return localPort;
    }

    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }

    public String getHeaderNames() {
        return headerNames;
    }

    public void setHeaderNames(String headerNames) {
        this.headerNames = headerNames;
    }

    public String getAttributesNames() {
        return attributesNames;
    }

    public void setAttributesNames(String attributesNames) {
        this.attributesNames = attributesNames;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getCharacterEncoding() {
        return characterEncoding;
    }

    public void setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getParameterNames() {
        return ParameterNames;
    }

    public void setParameterNames(String ParameterNames) {
        this.ParameterNames = ParameterNames;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAcceptEncoding() {
        return acceptEncoding;
    }

    public void setAcceptEncoding(String acceptEncoding) {
        this.acceptEncoding = acceptEncoding;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

}