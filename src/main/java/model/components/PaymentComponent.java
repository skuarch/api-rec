package model.components;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    
    //==========================================================================
    public Payment getPayment(long id) throws Exception {

        Payment payment;

        try {

            payment = new DAO().get(id, new Payment());

        } catch (Exception e) {
            throw e;
        }

        return payment;

    }
    
    //==========================================================================
    public void updateStatusPayment(String... paymentsId) throws Exception {

        if (paymentsId == null || paymentsId.length < 1) {
            throw new IllegalArgumentException("paymentsId is null or empty");
        }

        Payment payment;        
        
        try {
            for (String id : paymentsId) {
                payment = getPayment(Long.parseLong(id));
                payment.getPaymentStatus().setId(1);
                payment.setLastUpdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                new DAO().update(payment);
            }
        } catch (Exception e) {
            throw e;
        }

    }
    
    //==========================================================================
    public void updateStatusPayment(long... paymentsId) throws Exception {

        if (paymentsId == null || paymentsId.length < 1) {
            throw new IllegalArgumentException("paymentsId is null or empty");
        }

        Payment payment;        
        
        try {
            for (long id : paymentsId) {
                payment = getPayment(id);
                payment.getPaymentStatus().setId(2);
                new DAO().update(payment);
            }
        } catch (Exception e) {
            throw e;
        }

    }
    
}
