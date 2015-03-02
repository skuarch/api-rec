package model.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "mail_template")
@NamedQueries({
    @NamedQuery(name = "getTemplateAffiliate", query = "from MailTemplate mt where mt.name = :name and mt.displayLanguage = :displayLanguage and isSoftDeleted = 0"),
    @NamedQuery(name = "getTemplate", query = "from MailTemplate mt where mt.name = :name and mt.displayLanguage = :displayLanguage and isSoftDeleted = 0")
})
public class MailTemplate implements Serializable {

    @Id
    @Min(1)
    @Column(name = "mail_template_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotNull
    @Column(name = "mail_template_name", nullable = false)
    private String name;
    
    @NotNull
    @Column(name = "mail_template_from", nullable = false)
    private String from;
    
    @NotNull
    @Column(name = "mail_template_subject" , nullable = false)
    private String subject;
    
    @NotNull
    @Column(name = "mail_template_message", nullable = false, columnDefinition = "text")
    private String message;
    
    @NotNull
    @Column(name = "mail_template_display_language", nullable = false)
    private String displayLanguage;
    
    @NotNull
    @Column(name = "mail_template_is_soft_deleted", nullable = false, columnDefinition = "int default 0")
    private byte isSoftDeleted;
    
    public MailTemplate() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDisplayLanguage() {
        return displayLanguage;
    }

    public void setDisplayLanguage(String displayLanguage) {
        this.displayLanguage = displayLanguage;
    }

    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }
    
}