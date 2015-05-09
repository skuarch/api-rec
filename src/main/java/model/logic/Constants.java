package model.logic;

/**
 *
 * @author skuarch
 */
public class Constants {
    
    public static final String AFFILIATE = "affiliate";
    public static final String FREELANCER = "freelancer";
    public static final String PARTNER = "partner";
    public static final String CONTACT = "contact";
    public static final String CONTACT_BILLING = "contact billing";    
    
    public static final short TRANSACTION_NEW_AFFILIATE = 1;
    public static final short TRANSACTION_NEW_FREELANCER = 2;
    public static final short TRANSACTION_NEW_ESTABLISHMENT = 3;
    public static final short TRANSACTION_NEW_CASHIER = 4;
    public static final short TRANSACTION_NEW_PARTNER = 5;
    public static final short TRANSACTION_NEW_SUSBCRIBER = 6;
    public static final short TRANSACTION_NEW_ADMINISTRATOR = 7;
    public static final short TRANSACTION_NEW_CATEGORY = 8;
    public static final short TRANSACTION_NEW_COMPANY = 9;    
    public static final short TRANSACTION_UPDATE_BASIC_INFORMATION_AFFILIATE = 10;    
    public static final short TRANSACTION_UPDATE_AFFILIATE_PASSWORD = 11;    
    public static final short TRANSACTION_UPDATE_TAX_INFORMATION_AFFILIATE = 12;    
    public static final short TRANSACTION_UPDATE_ELECTRONIC_DATA_TRANSFER_AFFILIATE = 13;        
    public static final short TRANSACTION_UPDATE_BANK_INFORMATION_AFFILIATE = 14;    
    public static final short TRANSACTION_UPDATE_BASIC_INFORMATION_COMPANY = 15;    
    public static final short TRANSACTION_UPDATE_COMPANY_PASSWORD = 16;    
    public static final short TRANSACTION_UPDATE_TAX_INFORMATION_COMPANY = 17;    
    public static final short TRANSACTION_UPDATE_ELECTRONIC_DATA_TRANSFER_COMPANY = 18;    
    public static final short TRANSACTION_UPDATE_BANK_INFORMATION_COMPANY = 19;    
    public static final short TRANSACTION_UPDATE_ESTABLISHMENT_BASIC_INFORMATION = 20;
    public static final short TRANSACTION_UPDATE_RESPONSABLE_BASIC_INFORMATION = 21;    
    public static final short TRANSACTION_UPDATE_RESPONSABLE_PASSWORD = 22;    
    public static final short TRANSACTION_UPDATE_FREELANCER = 23;    
    public static final short TRANSACTION_UPDATE_FREELANCER_PASSWORD = 24;    
    public static final short TRANSACTION_UPDATE_CASHIER = 25;    
    public static final short TRANSACTION_UPDATE_CASHIER_PASSWORD = 26;    
    public static final short TRANSACTION_UPDATE_PARTNER = 27;    
    public static final short TRANSACTION_UPDATE_PARTNER_PASSWORD = 28;    
    public static final short TRANSACTION_ACTIVATE_FREELANCER = 29;    
    public static final short TRANSACTION_DEACTIVATE_FREELANCER = 30;    
    public static final short TRANSACTION_ACTIVATE_AFFILIADO = 31;    
    public static final short TRANSACTION_DEACTIVATE_AFFILIADO = 32;        
    
    public static final short PERSON_TYPE_ADMINISTRATOR = 1;
    public static final short PERSON_TYPE_AFFILIATE = 2;
    public static final short PERSON_TYPE_FREELANCER = 3;
    public static final short PERSON_TYPE_CASHIER = 4;
    public static final short PERSON_TYPE_RESPONSABLE = 5;
    public static final short PERSON_TYPE_CONTACT = 6;
    public static final short PERSON_TYPE_CONTACT_COMPANY = 7;
    public static final short PERSON_TYPE_PARTNER = 8;
    
    
    //==========================================================================
    private Constants(){
    }
    
}