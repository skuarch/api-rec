package model.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "responsable")
public class Responsable implements Serializable {

    @Id
    @Column(name = "responsable_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private long id;
    
    @Column(name = "responsable_password",nullable = true ,columnDefinition = "varchar(32)")
    private String password; 
    
    @OneToOne
    @JoinColumn(name ="person_id")
    private Person person;
    
    @Column(name = "responsable_is_soft_deleted")
    private byte softDeleted;
    
    public Responsable() {
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

    public byte getSoftDeleted() {
        return softDeleted;
    }

    public void setSoftDeleted(byte softDeleted) {
        this.softDeleted = softDeleted;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}