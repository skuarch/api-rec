package model.beans;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name ="person")
@NamedQueries({
    @NamedQuery(name = "getPersonByEmailAndPassword", query = "from Person p where p.email = :email and p.password = :password and isSoftDeleted = 0"),
    @NamedQuery(name = "getPersonByEmail", query = "from Person p where p.email = :email and isSoftDeleted = 0")        
})
public class Person implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false)    
    private long id;    
    
    @Column(name = "person_name", nullable = false, columnDefinition = "varchar(128)")
    private String name;
    
    @Column(name = "person_last_name", nullable = false, columnDefinition = "varchar(128)")
    private String lastName;
    
    @Column(name = "person_email", nullable = false,columnDefinition = "varchar(128)")
    private String email;               
    
    @Column(name = "person_password", nullable = false, columnDefinition = "varchar(32)")
    private String password;    
    
    @Column(name = "person_phone", nullable = false)
    private String phone;    
    
    @OneToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;    
    
    @OneToOne
    @JoinColumn(name = "person_type_id", nullable = false)
    private PersonType personType;
    
    @Column(name = "person_is_soft_deleted", nullable = false, columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;    
    
    @Type(type="timestamp")
    @Temporal(TemporalType.DATE)
    @Column(name = "person_registration_date", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP", updatable=false)    
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp registrationDate = new Timestamp(System.currentTimeMillis());

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }  

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }    
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}