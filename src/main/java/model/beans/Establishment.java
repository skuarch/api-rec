package model.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "establishment")
public class Establishment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "establishment_id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "establishment_name", nullable = false)
    private String name;

    @Column(name = "establishment_subcategory", nullable = true)
    private String subcategory;

    @OneToOne
    @JoinColumn(name = "responsable_id")
    private Responsable responsable;
    
    @Column(name = "establishment_latitude", nullable = true)
    private String latitude;
    
    @Column(name = "establishment_longitude", nullable = true)
    private String longitude;

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "establishment_cashier",
            joinColumns = {
                @JoinColumn(name = "establishment_id", unique = false, nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "cashier_id", unique = false, nullable = false, updatable = false)})
    private List<Cashier> cashier = new ArrayList<>();    
    

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "establishment_category",
            joinColumns = {
                @JoinColumn(name = "establishment_id", referencedColumnName = "establishment_id", unique = false, nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "category_id", unique = false, nullable = false, updatable = false)})
    private List<Category> category = new ArrayList<>();
    

    @Column(name = "establishment_registration_date", nullable = false, length = 19)
    private String registrationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public List<Cashier> getCashier() {
        if (!cashier.isEmpty()) {
            List<Cashier> c = cashier.stream().distinct().collect(Collectors.toList());
            return c;
        } else {
            return cashier;
        }
    }

    public void setCashier(List<Cashier> cashier) {        
        this.cashier = cashier;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
