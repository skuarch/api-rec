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
@Table(name = "establishment_type")
public class EstablishmentType implements Serializable {

    @Id
    @Column(name = "establishment_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "establishment_type_name")
    private String name;
    
    @Column(name = "establishment_is_soft_deleted", nullable = false, columnDefinition = "int default 0")
    private byte isSoftDeleted;
    
    public EstablishmentType() {
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