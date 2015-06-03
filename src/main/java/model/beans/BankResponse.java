package model.beans;

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
@Table(name = "bank_response")
public class BankResponse {

    @Id
    @Column(name = "bank_response_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "bank_response_text", columnDefinition = "varchar(768)")
    private String response;
    
    @Column(name = "bank_response_email_depositor", nullable = true)
    private String emailDepositor;
    
    @OneToOne
    @JoinColumn(name = "transfer_id", nullable = true)
    private Transfer transfer;

    public BankResponse() {
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public String getEmailDepositor() {
        return emailDepositor;
    }

    public void setEmailDepositor(String emailDepositor) {
        this.emailDepositor = emailDepositor;
    }
    
}
