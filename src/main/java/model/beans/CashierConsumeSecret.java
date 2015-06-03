package model.beans;

import java.math.BigDecimal;

/**
 *
 * @author skuarch
 */
public class CashierConsumeSecret {

    private long cashierId;
    private String secret;
    private BigDecimal amount;
    
    public CashierConsumeSecret() {
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
