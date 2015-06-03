package model.components;

import java.util.ArrayList;
import java.util.HashMap;
import model.beans.Depositor;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch-lap
 */
@Component
public class DepositorComponent {

    //==========================================================================
    public DepositorComponent() {        
    }
    
    //==========================================================================
    public long createDepositor(Depositor depositor) throws Exception{
    
        if(depositor == null){
            throw new IllegalArgumentException("depositor is null ");
        }
        
        long id;
        
        try {
            
            id = new DAO().create(depositor);
            
        } catch (Exception e) {
            throw e;
        }
        
        return id;
        
    }
    
    //==========================================================================
    public Depositor getDepositor(String email) throws Exception{
    
        if(email == null || email.length() < 1){
            throw new IllegalArgumentException("email is null or empty");
        }
        
        ArrayList<Depositor> depositors;
        Depositor depositor = null;
        HashMap parameters;
        
        try {
            
            parameters = new HashMap();
            parameters.put("email", email);
            depositors = new DAO().query(parameters, "getDepositorByEmail", new Depositor());
            
            if(depositors != null || depositors.size() > 0){
                depositor = depositors.get(0);
            }
            
        } catch (Exception e) {
            throw e;
        }
        
        return depositor;
        
    }
    
    //==========================================================================
    public void updateDepositor(Depositor depositor) throws Exception{
    
        if(depositor == null){
            throw new IllegalArgumentException("depositor is null or empty");
        }        
                
        try {
            
            new DAO().update(depositor);
            
        } catch (Exception e) {
            throw e;
        }
        
    }
    
}
