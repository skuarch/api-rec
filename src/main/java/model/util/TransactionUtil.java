package model.util;

import model.components.TransactionComponent;
import org.apache.log4j.Logger;

/**
 * utilities for Transaction class
 * @author skuarch
 */
public class TransactionUtil {

    private static final Logger logger = Logger.getLogger(TransactionUtil.class);

    //==========================================================================
    /**
     * private constructor.
     */
    private TransactionUtil() {
    }

    //==========================================================================
    /**
     * create transaction async (non blocking).
     * @param transactionNumber short
     * @param id long
     */
    public synchronized static void createTransaction(short transactionNumber, long id) {

        if(transactionNumber < 1){
            throw new IllegalArgumentException("transactionNumber is less than 0");
        }
        
        if(id < 1){
            throw new IllegalArgumentException("id is less than 0");
        }
        
        new Thread(() -> {
            try {
                new TransactionComponent()
                        .createTransaction(
                                transactionNumber,
                                id
                        );
            } catch (Exception e) {
                logger.error("TransactionUtil.createTransaction", e);
            }
        }).start();

    }

}