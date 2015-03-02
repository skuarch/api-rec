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
@Table(name = "transaction_type")
public class TransactionType implements Serializable {
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_type_id", nullable = false)
    private long id;    
    
    @Column(name = "transaction_type_name", nullable = false)
    private String name;
    
    @Column(name = "transaction_type_is_soft_deleted", nullable = false, columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;    

    public TransactionType() {
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