package model.beans;

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
 *
 * @author skuarch
 */
@Entity
@Table(name = "freelancer")
public class Freelancer {

    @Id    
    @Column(name = "freelancer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="person_id", nullable = false)
    private Person person;    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="address_id", nullable = false)
    private Address address;  
    
    @Column(name = "freelance_treasury_id")
    private String treasuryId;
    
    @Column(name = "freelance_key")
    private String key;
    
    @Column(name = "freelancer_is_soft_delete", columnDefinition = "int default 0")
    private int isSoftDelete = 0;
    
    //==========================================================================
    public Freelancer() {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTreasuryId() {
        return treasuryId;
    }

    public void setTreasuryId(String treasuryId) {
        this.treasuryId = treasuryId;
    }

    public int getIsSoftDelete() {
        return isSoftDelete;
    }

    public void setIsSoftDelete(int isSoftDelete) {
        this.isSoftDelete = isSoftDelete;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
}