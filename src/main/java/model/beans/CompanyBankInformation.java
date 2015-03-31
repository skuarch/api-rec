package model.beans;

/**
 *
 * @author skuarch
 */
public class CompanyBankInformation {

    private long id;
    private String ownerAccountBank;
    private String bank;
    private String clabe;
    private String emailNotifications;

    public CompanyBankInformation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwnerAccountBank() {
        return ownerAccountBank;
    }

    public void setOwnerAccountBank(String ownerAccountBank) {
        this.ownerAccountBank = ownerAccountBank;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getClabe() {
        return clabe;
    }

    public void setClabe(String clabe) {
        this.clabe = clabe;
    }

    public String getEmailNotifications() {
        return emailNotifications;
    }

    public void setEmailNotifications(String emailNotifications) {
        this.emailNotifications = emailNotifications;
    }
    
}
