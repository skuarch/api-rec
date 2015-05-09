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
    public static final short TRANSACTION_NEW_SUBSCRIBER = 5;
    public static final short TRANSACTION_UPDATE_BASIC_INFORMATION_AFFILIATE = 5;    
    
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