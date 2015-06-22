package model.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "payment")
@NamedQueries({        
        @NamedQuery(name = "getPaymentList", query = "from Payment p where p.isSoftDeleted = 0 order by p.creationDate desc")        
})
public class Payment implements Serializable {

    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "payment_amount", columnDefinition = "numeric(5,2)", nullable = false)
    private BigDecimal amount;
    
    @OneToOne
    @JoinColumn(name = "cashier_id", nullable = false)
    private Cashier cashier;
    
    @OneToOne
    @JoinColumn(name = "secret_id", nullable = false)
    private Secret secret;
    
    @OneToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private Recipient recipient;
    
    @OneToOne
    @JoinColumn(name = "payment_status_id", nullable = false)
    private PaymentStatus paymentStatus;
    
    @Column(name = "payment_creation_date", nullable = false, length = 19)
    private String creationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    
    @Column(name = "payment_last_update", nullable = true, length = 19)
    private String lastUpdate;
    
    @Column(name = "payment_is_soft_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;

    public Payment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public Secret getSecret() {
        return secret;
    }

    public void setSecret(Secret secret) {
        this.secret = secret;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
