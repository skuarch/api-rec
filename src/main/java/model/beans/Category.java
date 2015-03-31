package model.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "category")
@NamedQueries({
    @NamedQuery(name = "getCategories", query = "from Category c where isSoftDeleted = 0")    
})
public class Category implements Serializable {

    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")    
    private long id;    
    
    @Column(name = "category_name", nullable = false)
    private String name;
        
    @Column(name = "category_is_soft_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;
    
    public Category() {
    }

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

    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }    
    
}