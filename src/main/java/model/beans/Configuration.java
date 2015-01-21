package model.beans;

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
@Table(name = "configuration")
public class Configuration {

    @Id    
    @Column(name = "configuration_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private long id;    
    
    @Column(name = "configuration_smtp_host", nullable = false)
    private String smtpHost;    
    
    @Column(name = "configuration_smtp_port", nullable = false)
    private int smtpPort;    
    
    @Column(name = "configuration_smtp_username", nullable = false)
    private String smtpUsername;    
    
    @Column(name = "configuration_smtp_password", nullable = false)
    private String smtpPassword;
    
    @Column(name = "configuration_is_soft_delete", columnDefinition = "int default 0")
    private int isSoftDelete = 0;
    
    //==========================================================================
    public Configuration() {
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

    public int getIsSoftDelete() {
        return isSoftDelete;
    }

    public void setIsSoftDelete(int isSoftDelete) {
        this.isSoftDelete = isSoftDelete;
    }
    
}