package model.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author skuarch
 */
public class CompanyBasicInformation {

    public CompanyBasicInformation() {
    }

    private long companyId;
    private String name;
    private String brand;
    private Person person;
    private List<Category> category = new ArrayList<>();    
    private String password;

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
