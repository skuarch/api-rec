package model.beans;

/**
 *
 * @author skuarch
 */
public class CompanyTaxInformation {

    private long id;
    private String taxId;    
    private String taxCompanyName;
    private Contact contact;
    private Address address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getTaxCompanyName() {
        return taxCompanyName;
    }

    public void setTaxCompanyName(String taxCompanyName) {
        this.taxCompanyName = taxCompanyName;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
