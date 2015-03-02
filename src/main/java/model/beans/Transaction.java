package model.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;    
    
    @Column(name = "transaction_object_id", nullable = false)
    private long objectId;    
    
    @OneToOne
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;
    
    @Column(name = "transaction_registration_date", nullable = false, length = 19)
    private String registrationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    
    @Column(name = "transaction_is_soft_deleted", nullable = false, columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;    
    
    
    public Transaction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }    
    
}