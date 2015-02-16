package model.beans;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "establishment")
public class Establishment {

    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "establishment_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "affiliate_id")
    private Affiliate affiliate;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;    
    
    @Column(name = "establishment_name", nullable = false)
    private String name;
    
    @OneToOne
    @JoinColumn(name = "responsable_id")
    private Responsable responsable;
    
    @Type(type="timestamp")
    @Temporal(TemporalType.DATE)
    @Column(name = "establishment_registration_date", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP", updatable=false)    
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp registrationDate = new Timestamp(System.currentTimeMillis());
    
    @Column(name = "establishment_is_soft_deleted", nullable = false, columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Affiliate getAffiliate() {
        return affiliate;
    }

    public void setAffiliate(Affiliate affiliate) {
        this.affiliate = affiliate;
    }

    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

}