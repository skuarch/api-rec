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
    
    @Column(name = "payment_behalf", nullable = false)
    private long behalf;
    
    @Column(name = "payment_behalf_brand", nullable = false)
    private String behalfBrand;
    
    @Column(name = "payment_discount_percentage", nullable = false)
    private byte discountPercentage = 12;
    
    @Column(name = "payment_profit", columnDefinition = "numeric(5,2)", nullable = false)
    private BigDecimal profit;
    
    @Column(name = "payment_totla_pay", columnDefinition = "numeric(5,2)", nullable = false)
    private BigDecimal totalPay;
    
    @OneToOne
    @JoinColumn(name = "affiliate_type_id", nullable = false)
    private AffiliateType affiliateType;
    
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
        if(amount.intValue() < 1){
            throw new IllegalArgumentException("amount is less than 1");
        }
        this.amount = amount;
    }

    public long getBehalf() {
        return behalf;
    }

    public void setBehalf(long behalf) {
        this.behalf = behalf;
    }    

    public AffiliateType getAffiliateType() {
        return affiliateType;
    }

    public void setAffiliateType(AffiliateType affiliateType) {
        this.affiliateType = affiliateType;
    }    

    public String getBehalfBrand() {
        return behalfBrand;
    }

    public void setBehalfBrand(String behalfBrand) {
        this.behalfBrand = behalfBrand;
    }

    public byte getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(byte discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(BigDecimal totalPay) {
        this.totalPay = totalPay;
    }
}
