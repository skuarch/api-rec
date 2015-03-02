package model.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "affiliate_establishment")
public class AffiliateEstablishmet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "affiliate_establishment_id")
    private long id;
    
    @Id
    @Column(name = "affiliate_id")
    private long affiliateId;
    
    @Id
    @Column(name = "establishment_id")
    private long establishmentId;
    
    public AffiliateEstablishmet() {
    }

    public long getAffiliateId() {
        return affiliateId;
    }

    public void setAffiliateId(long affiliateId) {
        this.affiliateId = affiliateId;
    }

    public long getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(long establishmentId) {
        this.establishmentId = establishmentId;
    }   
    
}
