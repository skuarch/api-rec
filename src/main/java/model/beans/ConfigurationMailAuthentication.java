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
@Table(name = "configuration_mail_authentication")
public class ConfigurationMailAuthentication implements Serializable {

    @Id    
    @Column(name = "configuration_mail_authentication_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private long id;    
    
    @Column(name = "configuration_mail_authentication_smtp_host", nullable = false)
    private String smtpHost;    
    
    @Column(name = "configuration_mail_authentication_smtp_port", nullable = false)
    private int smtpPort;    
    
    @Column(name = "configuration_mail_authentication_smtp_username", nullable = false)
    private String smtpUsername;    
    
    @Column(name = "configuration_mail_authentication_smtp_password", nullable = false)
    private String smtpPassword;
    
    @Column(name = "configuration_mail_authentication_smtp_require_authentication", columnDefinition = "int default 0")
    private byte smtpRequireAuthentication = 0;
    
    @Column(name = "configuration_mail_authentication_is_soft_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;
    
    //==========================================================================
    public ConfigurationMailAuthentication() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSmtpUsername() {
        return smtpUsername;
    }

    public void setSmtpUsername(String smtpUsername) {
        this.smtpUsername = smtpUsername;
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }

    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }

    public byte getSmtpRequireAuthentication() {
        return smtpRequireAuthentication;
    }

    public void setSmtpRequireAuthentication(byte smtpRequireAuthentication) {
        this.smtpRequireAuthentication = smtpRequireAuthentication;
    }
    
}