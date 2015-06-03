package model.components;

import model.beans.PaymentStatus;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch
 */
@Component
public class PaymentStatusComponent {

    public PaymentStatusComponent() {
    }
    
    //==========================================================================
    public PaymentStatus getStatus(short paymentStatusNumber) throws Exception{
        
        PaymentStatus paymentStatus = null;
        
        try {
            
            paymentStatus = new DAO().get(paymentStatusNumber, new PaymentStatus());
            
        } catch (Exception e) {
            throw e;
        }
        
        return paymentStatus;
        
    }
    
}
