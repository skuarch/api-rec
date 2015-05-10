package model.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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

/**
 *
 * @author skuarch
 */
@Entity
@Table(name ="person")
@NamedQueries({    
    @NamedQuery(name = "getPersonByEmail", query = "from Person p where p.email = :email and isSoftDeleted = 0")        
})
public class Person implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")    
    private long id;    
    
    @Column(name = "person_name", nullable = false, columnDefinition = "varchar(128)")
    private String name;
    
    @Column(name = "person_last_name", nullable = false, columnDefinition = "varchar(128)")
    private String lastName;
    
    @Column(name = "person_email", nullable = false,columnDefinition = "varchar(128)")
    private String email;       
    
    @Column(name = "person_phone", nullable = true)
    private String phone;    
    
    @OneToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;    
    
    @OneToOne
    @JoinColumn(name = "person_type_id", nullable = false)
    private PersonType personType;
    
    @Column(name = "person_is_soft_deleted", nullable = false, columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;        

    @Column(name = "person_registration_date", nullable = false, length = 19)
    private String registrationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());    
    
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

    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }  

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
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