package model.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
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
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "affiliate")
@NamedQueries({
    @NamedQuery(name = "getAffiliateByCreator", query = "select new Affiliate(a.id, a.brand, a.person, a.description, a.address, a.bank, a.clabe, a.ownerAccountBank, a.emailNotifications, a.contact, a.taxCompanyName, a.taxId) from Affiliate a where a.creatorId = :id and a.isSoftDeleted = 0"),
    @NamedQuery(name = "getAffiliate", query = "from Affiliate a where a.person.email = :email and a.password = :password and a.isSoftDeleted = 0"),
    @NamedQuery(name = "getAffiliatesList", query = "from Affiliate a where a.isSoftDeleted = 0")
})
public class Affiliate implements Serializable {

    @Id
    @Column(name = "affiliate_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "affiliate_active", columnDefinition = "int default 0")
    private byte active = 0;
    
    @Column(name = "affiliate_discount_percentage", columnDefinition = "int default 0", nullable = false)
    private byte discountPercentage = 12;
    
    @Column(name = "affiliate_password", nullable = false, columnDefinition = "varchar(32)")
    private String password;

    @Column(name = "affiliate_brand", nullable = false, columnDefinition = "varchar(255)")
    private String brand;

    @Column(name = "affiliate_tax_id", nullable = false)
    private String taxId;

    @Column(name = "affiliate_tax_company_name", nullable = false)
    private String taxCompanyName;

    @Column(name = "affiliate_description", nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "affiliate_owner_account_bank", nullable = false)
    private String ownerAccountBank;

    @Column(name = "affiliate_bank", nullable = false)
    private String bank;

    @Column(name = "affiliate_clabe", nullable = false)
    private String clabe;

    @Column(name = "affiliate_email_notifications", nullable = false)
    private String emailNotifications;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = true)
    private Person person;

    @Transient
    private MultipartFile logoFile;

    @Column(name = "affiliate_logo_path_name", unique = false)
    private String logoPathName;

    @OneToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;
    
    @Column(name = "affiliate_url_logo")
    private String urlLogo;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "creator_person_id", nullable = true)
    private long creatorId;

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "affiliate_establishment",
            joinColumns = {
                @JoinColumn(name = "affiliate_id", unique = false, nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "establishment_id", unique = false, nullable = false, updatable = false)}
    )
    private List<Establishment> establishment;

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "affiliate_category",
            joinColumns = {
                @JoinColumn(name = "affiliate_id", referencedColumnName = "affiliate_id", unique = false, nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "category_id", unique = false, nullable = false, updatable = false)})
    private List<Category> category = new ArrayList<>();

    @Column(name = "affiliate_is_soft_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;

    @Column(name = "affiliate_registration_date", nullable = false, length = 19)
    private String registrationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    
    @Column(name = "affiliate_last_login")
    private String lastLogin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());        

    //==========================================================================
    public Affiliate() {
    }

    public Affiliate(long id, String brand, Person person, String description, Address address, String bank, String clabe, String ownerAccountBank, String emailNotifications, Contact contact, String taxCompanyName, String taxId) {
        this.id = id;
        this.brand = brand;
        this.taxId = taxId;
        this.taxCompanyName = taxCompanyName;
        this.description = description;
        this.ownerAccountBank = ownerAccountBank;
        this.bank = bank;
        this.clabe = clabe;
        this.emailNotifications = emailNotifications;
        this.person = person;
        this.contact = contact;
        this.address = address;
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

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getLogoFile() {
        return logoFile;
    }

    public void setLogoFile(MultipartFile logoFile) {
        this.logoFile = logoFile;
    }

    public String getLogoPathName() {
        return logoPathName;
    }

    public void setLogoPathName(String logoPathName) {
        this.logoPathName = logoPathName;
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

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public byte getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(byte discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

}
