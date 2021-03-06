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
@Table(name = "company")
@NamedQueries({
    @NamedQuery(name = "getCompaniesByCreator", query = "from Company c where c.creatorId = :id and c.isSoftDeleted = 0"),
    @NamedQuery(name = "getCompanyList", query = "from Company c where c.isSoftDeleted = 0 order by c.id desc"),
    @NamedQuery(name = "getCompanyByEmailPassword", query = "from Company c where c.person.email = :email and c.password = :password and c.isSoftDeleted = 0 order by c.id desc")
})
public class Company implements Serializable {

    @Id
    @Column(name = "company_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "company_name", nullable = false)
    private String name;

    @Column(name = "company_brand", nullable = false)
    private String brand;

    @Column(name = "company_discount_percentage", columnDefinition = "int default 0", nullable = false)
    private byte discountPercentage = 12;

    @Column(name = "company_url_logo")
    private String urlLogo;

    @Column(name = "company_website", nullable = true)
    private String website;

    @Column(name = "company_facebook", nullable = true)
    private String facebook;

    @Column(name = "company_password", nullable = false, columnDefinition = "varchar(32)")
    private String password;

    @Column(name = "company_description", nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    @Transient
    private MultipartFile logoFile;

    @Column(name = "company_logo_path_name", unique = false)
    private String logoPathName;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = true)
    private Person person;

    @OneToOne
    @JoinColumn(name = "tax_contact_id", nullable = true)
    private Contact contact;

    @Column(name = "company_taxId")
    private String taxId;

    @Column(name = "company_tax_company_name", nullable = false)
    private String taxCompanyName;

    @Column(name = "company_owner_account_bank", nullable = false)
    private String ownerAccountBank;

    @Column(name = "company_bank", nullable = false)
    private String bank;

    @Column(name = "company_clabe", nullable = false)
    private String clabe;

    @Column(name = "company_email_notifications", nullable = false)
    private String emailNotifications;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "creator_person_id", nullable = true)
    private long creatorId;

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "company_establishment",
            joinColumns = {
                @JoinColumn(name = "company_id", unique = false, nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "establishment_id", unique = false, nullable = false, updatable = false)}
    )
    private List<Establishment> establishment;

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "company_category",
            joinColumns = {
                @JoinColumn(name = "company_id", referencedColumnName = "company_id", unique = false, nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "category_id", unique = false, nullable = false, updatable = false)})
    private List<Category> category = new ArrayList<>();

    @Column(name = "company_registration_date", nullable = false, length = 19)
    private String registrationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    @Column(name = "company_is_soft_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;

    @Column(name = "company_active", columnDefinition = "int default 0")
    private byte active = 0;

    @Column(name = "affiliate_approved", columnDefinition = "int default 0")
    private byte approved = 0;
    
    @Column(name = "company_last_login")
    private String lastLogin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
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

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public byte getApproved() {
        return approved;
    }

    public void setApproved(byte approved) {
        this.approved = approved;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

}
