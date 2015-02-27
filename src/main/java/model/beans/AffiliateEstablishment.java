package model.beans;

/**
 *
 * @author skuarch
 */
public class AffiliateEstablishment {

    private long affiliateId;
    private Establishment establishment;    
    
    public AffiliateEstablishment() {
    }

    public long getAffiliateId() {
        return affiliateId;
    }

    public void setAffiliateId(long affiliateId) {
        this.affiliateId = affiliateId;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }
    
}
