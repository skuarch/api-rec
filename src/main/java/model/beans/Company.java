package model.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "company")
public class Company implements Serializable {

    @Id
    @Column(name = "company_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "company_name", nullable = false)
    private String name;
    
    @Column(name = "company_brand", nullable = false)
    private String brand;
    
    @Column(name = "company_email", nullable = false)
    private String email;
    
    @Column(name = "company_phone", nullable = false)
    private String phone;
    
    @Column(name = "company_password", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    
    @OneToOne
    @JoinColumn(name ="freelancer_id")
    private Freelancer freelancer;        

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Establishment> establishment = new ArrayList<>();
    
    @Column(name = "company_registration_date", nullable = false, length = 19)
    private String registrationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());    

    @Column(name = "company_is_soft_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }

    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }
    
}
