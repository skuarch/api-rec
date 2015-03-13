package model.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "affiliate")
@NamedQueries({
    @NamedQuery(name = "getAffiliateByFreelancer", query = "from Affiliate a where a.freelancer.id = :id and a.isSoftDeleted = 0")
})
public class Affiliate implements Serializable {

    @Id
    @Column(name = "affiliate_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "affiliate_password", nullable = false, columnDefinition = "varchar(32)")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToOne
    @JoinColumn(name = "freelancer_id")
    private Freelancer freelancer;

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "affiliate_establishment",
            joinColumns = {
                @JoinColumn(name = "affiliate_id", unique = false, nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "establishment_id", unique = false, nullable = false, updatable = false)}
    )
    private List<Establishment> establishment;
    

    @Column(name = "affiliate_is_soft_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;

    @Column(name = "affiliate_registration_date", nullable = false, length = 19)
    private String registrationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
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

    public List<Establishment> getEstablishment() {
        if (!establishment.isEmpty()) {
            List<Establishment> e = establishment.stream().distinct().collect(Collectors.toList());
            return e;
        } else {
            return establishment;
        }
    }

    public void setEstablishment(List<Establishment> establishment) {
        this.establishment = establishment;
    }

}
