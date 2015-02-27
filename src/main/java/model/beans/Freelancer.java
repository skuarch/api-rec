package model.beans;

import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "freelancer")
@NamedQueries({
        @NamedQuery(name = "getFreelancer", query = "from Freelancer f where f.person.email = :email and f.password = :password and f.isSoftDeleted = 0")        
})
public class Freelancer {

    @Id    
    @Column(name = "freelancer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;    
    
    @Column(name = "freelancer_password", nullable = false, columnDefinition = "varchar(32)")
    private String password; 
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="person_id", nullable = false)
    private Person person;    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="address_id", nullable = false)
    private Address address;  
    
    @Column(name = "freelance_tax_id")
    private String taxId;
    
    @Column(name = "freelance_key")
    private String key;
    
    @Type(type="timestamp")
    @Temporal(TemporalType.DATE)
    @Column(name = "freelancer_last_login", updatable=true)    
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp lastLogin = new Timestamp(System.currentTimeMillis());
    
    @Column(name = "freelancer_is_soft_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;
    
    //==========================================================================
    public Freelancer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }    

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }
   
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}