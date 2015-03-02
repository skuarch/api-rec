package model.beans;

/**
 *
 * @author skuarch
 */
public class AffiliateEstablishmentBasic {

    private long affiliateId;
    private Establishment establishment;    
    
    public AffiliateEstablishmentBasic() {
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
