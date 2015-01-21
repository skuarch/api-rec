package model.beans;

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
public class TransactionType {
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_type_id", nullable = false)
    private long id;    
    
    @Column(name = "transaction_type_name", nullable = false)
    private String name;
    
    @Column(name = "transaction_type_is_soft_deleted", nullable = false, columnDefinition = "int default 0")
    private short isSoftDeleted = 0;    

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

    public short getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(short isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }
    
}