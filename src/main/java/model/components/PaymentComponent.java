package model.components;

import java.util.ArrayList;
import model.beans.Payment;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch
 */
@Component
public class PaymentComponent {    
    
    //==========================================================================
    public PaymentComponent() {
    }
    
    //==========================================================================
    public long savePayment(Payment payment) throws Exception{
        
        if(payment == null){
            throw new IllegalArgumentException("payment is null");
        }
        
        long id;
        
        try {
            
            id = new DAO().create(payment);
            
        } catch (Exception e) {
            throw e;
        }
        
        return id;        
    }
    
    //==========================================================================
    public void updatePayment(Payment payment) throws Exception{                
        
        if(payment == null){
            throw new IllegalArgumentException("payment is null");
        }
        
        try {
            
            new DAO().update(payment);
            
        } catch (Exception e) {
            throw e;
        }
        
    }
    
    //==========================================================================
    public ArrayList<Payment> getPaymentList() throws Exception {

        ArrayList<Payment> payment = new ArrayList<>();

        try {

            payment = new ArrayList<>(new DAO().query("getPaymentList", new Payment()));

        } catch (Exception e) {
            throw e;
        }

        return payment;

    }
    
}
