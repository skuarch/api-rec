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
@Table(name = "configuration_mail")
public class ConfigurationMail implements Serializable {

    @Id    
    @Column(name = "configuration_mail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private long id;    
    
    @Column(name = "configuration_mail_smtp_host", nullable = false)
    private String smtpHost;
    
    @Column(name = "configuration_mail_smtp_port", nullable = false)
    private int smtpPort;
    
    @Column(name = "configuration_is_soft_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;
    
    //==========================================================================
    public ConfigurationMail() {
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

    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }
    
}