package model.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
 * @author skuarch-lap
 */
@Entity
@Table(name = "secret")
@NamedQueries({
    @NamedQuery(name = "getSecretBySecret", query = "from Secret s where s.secretAlphanumeric = :secret and s.isSoftDeleted = 0")    
})
public class Secret implements Serializable {

    @Id
    @Column(name = "secret_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;    
    
    @Column(name = "secret_alphanumeric", nullable = false, unique = true)
    private String secretAlphanumeric;
    
    @OneToOne
    @JoinColumn(name = "secret_status_id", nullable = false)
    private SecretStatus secretStatus;
    
    @Column(name = "secret_value", columnDefinition = "numeric(5,2)")
    private BigDecimal value;
    
    @Column(name = "secret_generation_date", nullable = false, length = 19)
    private String generationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());    
    
    @Column(name = "secret_consumed_date", length = 19, nullable = true)
    private String consumedDate;
    
    @Column(name = "secret_expiry_date", nullable = false, length = 19)
    private String expiryDate = LocalDateTime.now().plusDays(90).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    
    @Column(name = "secret_is_soft_deleted", columnDefinition = "int default 0")
    private byte isSoftDeleted = 0;

    public Secret() {         
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSecretAlphanumeric() {
        return secretAlphanumeric;
    }

    public void setSecretAlphanumeric(String secretAlphanumeric) {
        this.secretAlphanumeric = secretAlphanumeric;
    }

    public SecretStatus getSecretStatus() {
        return secretStatus;
    }

    public void setSecretStatus(SecretStatus secretStatus) {
        this.secretStatus = secretStatus;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(String generationDate) {
        this.generationDate = generationDate;
    }

    public String getConsumedDate() {
        return consumedDate;
    }

    public void setConsumedDate(String consumedDate) {
        this.consumedDate = consumedDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public byte getIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(byte isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }
    
}
