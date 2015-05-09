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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author skuarch-lap
 */
@Entity
@Table(name = "administrator")
@NamedQueries({
    @NamedQuery(name = "getAdministrator", query = "from Administrator a where a.person.email = :email and a.password = :password and a.active = 1 and a.isSoftDeleted = 0")
})
public class Administrator {

    @Id
    @Column(name = "administrator_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "administrator_password", nullable = false, columnDefinition = "varchar(32)")
    private String password;
    @OneToOne
    @JoinColumn(name = "person_id", nullable = true)
    private Person person;
    @Column(name = "administrator_active", columnDefinition = "int default 0")
    private byte active = 0;
    @Column(name = "administrator_last_login")
    private String lastLogin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    @Column(name = "administrator_is_soft_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;

    //==========================================================================
    public Administrator() {
    }

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

    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }

}
