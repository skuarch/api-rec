package model.beans;

import java.math.BigDecimal;

/**
 * this bean is used as @ModelAttribute
 * @author skuarch
 */
public class SecretConsumed {

    private long cashierId;
    private String secret;
    private BigDecimal amount;
    
    public SecretConsumed() {
    }

    public long getCashierId() {
        return cashierId;
    }

    public void setCashierId(long cashierId) {
        this.cashierId = cashierId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
}
