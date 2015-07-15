package model.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "affiliate_type")
public class AffiliateType implements Serializable {    
    
    public static final byte AFFILIATE_TYPE_PERSON = 1;
    public static final byte AFFILIATE_TYPE_COMPANY = 2;
    
    @Id
    @Column(name = "affiliate_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "affiliate_type_name")
    private String name;

    public AffiliateType() {
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
}
