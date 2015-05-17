
package model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author skuarch-lap
 */
@Entity
@Table(name = "depositor")
public class Depositor {
    
    @Id
    @Column(name = "depositor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "depositor_name", nullable = false)
    private String name;

    @Column(name = "depositor_last_name", nullable = false)
    private String lastName;

    @Column(name = "depositor_phone", nullable = false)
    private String phone;

    @Column(name = "depositor_email", nullable = false)
    private String email;
    
    @Column(name = "depositor_is_soft_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;

    public Depositor() {
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
    
    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }
    
}
