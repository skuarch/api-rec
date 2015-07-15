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
import javax.persistence.Transient;

/**
 *
 * @author skuarch-lap
 */
@Entity
@Table(name = "transfer")
@NamedQueries({
        @NamedQuery(name = "getTransferList", query = "from Transfer t where t.isSoftDeleted = 0 order by t.id desc"),
        @NamedQuery(name = "getTransferListByDepositorEmail", query = "from Transfer t where t.depositor.email = :email and t.isSoftDeleted = 0 order by t.date desc"),
        @NamedQuery(name = "getTransferListByRecipientEmail", query = "from Transfer t where t.recipient.email = :email and t.isSoftDeleted = 0 order by t.date desc")        
})
public class Transfer implements Serializable {

    @Id
    @Column(name = "transfer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 
    
    @OneToOne
    @JoinColumn(name = "transfer_type_id", nullable = true)
    private TransferType transferType;
    
    @Column(name = "transfer_amount", columnDefinition = "numeric(7,2)")
    private BigDecimal amount;

    @OneToOne
    @JoinColumn(name = "depositor_id", nullable = true)
    private Depositor depositor;    
    
    @OneToOne
    @JoinColumn(name = "recipient_id", nullable = true)
    private Recipient recipient;
    
    @Column(name = "transfer_date", nullable = false, length = 19)
    private String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    
    @Column(name = "transfer_is_soft_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;        

    @Column(name = "secret_alphanumeric", unique = true, nullable = false)
    private String secretAlphanumeric;    
    
    @Transient
    private byte card; //giftCard
    
    @Transient
    private String cardNumber; //credit card

    public Transfer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }    

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Depositor getDepositor() {
        return depositor;
    }

    public void setDepositor(Depositor depositor) {
        this.depositor = depositor;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }    

    public TransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSecretAlphanumeric() {
        return secretAlphanumeric;
    }

    public void setSecretAlphanumeric(String secretAlphanumeric) {
        this.secretAlphanumeric = secretAlphanumeric;
    }

    public byte getCard() {
        return card;
    }

    public void setCard(byte card) {
        this.card = card;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

}
