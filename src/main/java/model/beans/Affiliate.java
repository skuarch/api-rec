package model.beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "affiliate")
public class Affiliate {

    @Id
    @Column(name = "affiliate_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)      
    private long id; 
    
    @Column(name = "affiliate_password", nullable = false, columnDefinition = "varchar(32)")
    private String password; 
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="person_id", nullable = false)
    private Person person;    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="address_id", nullable = false)
    private Address address;        
    
    @OneToOne
    @JoinColumn(name ="freelancer_id")
    private Freelancer freelancer;        
    
    @OneToMany(mappedBy = "affiliate", cascade = CascadeType.ALL)    
    private List<Establishment> establishment = new ArrayList<>();
    
    @Column(name = "affiliate_is_soft_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;
    
    @Type(type="timestamp")
    @Temporal(TemporalType.DATE)
    @Column(name = "affiliate_registration_date", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP", updatable=false)    
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp registrationDate = new Timestamp(System.currentTimeMillis());
    
    public Affiliate() {
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

    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }    

    public List<Establishment> getEstablishment() {
        return establishment;
    }

    public void setEstablishment(List<Establishment> establishment) {
        this.establishment = establishment;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }   

    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    
}