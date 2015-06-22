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
@Table(name = "cashier_secret_consumed")
@NamedQueries({
    @NamedQuery(name = "getCashierListSecretConsumed", query = "from CashierSecretConsumed c where c.cashier.id = :cashierId and c.isSoftDeleted = 0 order by c.consumedDate desc")
})
public class CashierSecretConsumed implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cashier_secret_consumed_id")
    private long id;
    
    @OneToOne
    @JoinColumn(name = "cashier_id", nullable = false)
    private Cashier cashier;
    
    @OneToOne
    @JoinColumn(name = "secret_id", nullable = false)
    private Secret secret;
    
    @Column(name = "cashier_secret_consumed_amount", nullable = false)
    private BigDecimal amount;
    
    @OneToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private Recipient recipient;    
    
    @Column(name = "cashier_secret_consumed_date", nullable = false, length = 19)
    private String consumedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    
    @Column(name = "cashier_secret_consumed_is_soft_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;    
    
    public CashierSecretConsumed() {
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

    public String getConsumedDate() {
        return consumedDate;
    }

    public void setConsumedDate(String consumedDate) {
        this.consumedDate = consumedDate;
    }

    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
        
}
