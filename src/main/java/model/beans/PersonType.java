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
@Table(name = "person_type")
@NamedQueries({
    @NamedQuery(name = "getPersonTypeByName", query = "from PersonType pt where pt.name = :name and pt.isSoftDeleted = 0")
})
public class PersonType implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_type_id", nullable = false)
    private long id;
    
    @Column(name = "person_type_name", nullable = false)
    private String name;
    
    @Column(name = "person_type_is_soft_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;

    public PersonType() {
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