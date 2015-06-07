package model.beans;

import java.io.Serializable;
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
@Table(name = "cashier")
@NamedQueries({
    @NamedQuery(name = "getCashierList", query = "from Cashier c where c.isSoftDeleted = 0"),
    @NamedQuery(name = "getCashierByEmailPassword", query = "from Cashier c where c.person.email = :email and c.password = :password and c.active = 1 and c.isSoftDeleted = 0")
})
public class Cashier implements Serializable {

    @Id
    @Column(name = "cashier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cashier_password", nullable = false, columnDefinition = "varchar(32)")
    private String password;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "chashier_is_soft_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;
    
    @Column(name = "cashier_active", columnDefinition = "int default 0")
    private byte active = 1;
    
    @Column(name = "cashier_last_login")
    private String lastLogin;

    public Cashier() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

}
