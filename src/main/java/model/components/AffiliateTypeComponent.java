/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.components;

import model.beans.AffiliateType;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch
 */
@Component
public class AffiliateTypeComponent {
    
    //==========================================================================
    public AffiliateType getAffiliateType(short affiliateTypeNumber) throws Exception{
        
        AffiliateType affiliateType = null;
        
        try {
            
            affiliateType = new DAO().get(affiliateTypeNumber, new AffiliateType());
            
        } catch (Exception e) {
            throw e;
        }
        
        return affiliateType;
        
    }
    
}
