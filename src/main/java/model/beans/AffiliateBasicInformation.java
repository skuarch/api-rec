package model.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author skuarch
 */
public class AffiliateBasicInformation {

    private long id;
    private Person person;
    private String password;
    private String brand;
    private List<Category> category = new ArrayList<>();    
    
    public AffiliateBasicInformation() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    
}
