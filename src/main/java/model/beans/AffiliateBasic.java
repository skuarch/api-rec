package model.beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author skuarch
 */
public class AffiliateBasic implements Serializable {

    private BigInteger id;
    private String logo = "http://static.regaloenclave.com/uploads/images/noLogo.gif";
    private String brand;
    private String description;
    private String type; 
    private int discountPercentage;
    private List<Category> category = new ArrayList<>();
    private String website;
    private String facebook;
    
    public AffiliateBasic() {
    }

    public AffiliateBasic(BigInteger id, String logo, String brand, String description, String type) {
        this.id = id;
        this.logo = logo;
        this.brand = brand;
        this.description = description;
        this.type = type;
    }
    
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
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
}
