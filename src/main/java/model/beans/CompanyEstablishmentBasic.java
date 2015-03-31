package model.beans;

/**
 *
 * @author skuarch
 */
public class CompanyEstablishmentBasic {

    private long companyId;
    private Establishment establishment;    
    
    public CompanyEstablishmentBasic() {
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }
    
}
