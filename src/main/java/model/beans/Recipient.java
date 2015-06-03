package model.beans;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author skuarch-lap
 */
@Entity
@Table(name = "recipient")
@NamedQueries({
    @NamedQuery(name = "getRecipientByEmail", query = "from Recipient r where r.email = :email and r.isSoftDeleted = 0")        
})
public class Recipient {
    
    @Id
    @Column(name = "recipient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "recipient_name", nullable = false)
    private String name;

    @Column(name = "recipient_last_name", nullable = false)
    private String lastName;

    @Column(name = "recipient_phone", nullable = false)
    private String phone;

    @Column(name = "recipient_email", nullable = false, unique = true)
    private String email;
    
    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "recipient_secret",
            joinColumns = {
                @JoinColumn(name = "recipient_id", unique = false, nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "secret_id", unique = false, nullable = false, updatable = false)}
    )
    private List<Secret> secret;
    
    @Column(name = "recipient_is_soft_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;

    public Recipient() {
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Secret> getSecret() {
        return secret;
    }

    public void setSecret(List<Secret> secret) {
        this.secret = secret;
    }

    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }    
    
}
