package model.components;

import java.util.ArrayList;
import java.util.HashMap;
import model.beans.Secret;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch-lap
 */
@Component
public class SecretComponent {

    //==========================================================================
    public SecretComponent() {
    }
    
    //==========================================================================
    public long saveSecret(Secret secret) throws Exception{
        
        if(secret == null){
            throw new IllegalArgumentException("secret is null");
        }
        
        long id;
        
        try {
    
            id = new DAO().create(secret);
            
        } catch (Exception e) {
            throw e;
        }
        
        return id;
    
    }
    
    //==========================================================================
    public void updateSecret(Secret secret) throws Exception{
        
        try {
    
            new DAO().update(secret);
            
        } catch (Exception e) {
            throw e;
        }
    
    }
    
    //==========================================================================
    public Secret getSecret(long id) throws Exception{
        
        Secret secret;
        
        try {
    
            secret = new DAO().get(id, new Secret());
            
        } catch (Exception e) {
            throw e;
        }
        
        return secret;
    
    }   
    
    //==========================================================================
    public Secret getSecret(String secret) throws Exception{
        
        Secret s = null;
        ArrayList<Secret> secrets;
        HashMap parameters;
        
        try {
    
            parameters = new HashMap();
            parameters.put("secret", secret);
            secrets = new DAO().query(parameters, "getSecretBySecret", new Secret());
            
            if(secrets != null && secrets.size() > 0){
                s = secrets.get(0);
            }
            
        } catch (Exception e) {
            throw e;
        }
        
        return s;
    
    }
    
    //==========================================================================
    public Secret getActiveSecret(String secret) throws Exception{
        
        Secret s = null;
        ArrayList<Secret> secrets;
        HashMap parameters;
        
        try {
    
            parameters = new HashMap();
            parameters.put("secret", secret);
            secrets = new DAO().query(parameters, "getSecretActived", new Secret());
            
            if(secrets != null && secrets.size() > 0){
                s = secrets.get(0);
            }
            
        } catch (Exception e) {
            throw e;
        }
        
        return s;
    
    }
    
    
    //==========================================================================
    public ArrayList<Secret> getSecretList() throws Exception {

        ArrayList<Secret> secrets = new ArrayList<>();

        try {

            secrets = new ArrayList<>(new DAO().query("getSecretList", new Secret()));
            
        } catch (Exception e) {
            throw e;
        }

        return secrets;

    }
    
}
