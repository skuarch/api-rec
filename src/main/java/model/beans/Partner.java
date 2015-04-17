package model.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * before to become an affiliate
 * @author skuarch
 */
@Entity
@Table(name = "partner")
public class Partner {

    @Id
    @Column(name = "partner_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "partner_password", nullable = false, columnDefinition = "varchar(32)")
    private String password;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = true)
    private Person person;
    
    @Column(name = "partner_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;
    
    @Column(name = "partner_active", columnDefinition = "int default 0")
    private byte active = 0;

    @Column(name = "partner_registration_date", nullable = false, length = 19)
    private String registrationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }
    
}
