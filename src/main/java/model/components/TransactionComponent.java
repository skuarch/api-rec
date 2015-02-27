package model.components;

import model.beans.Transaction;
import model.beans.TransactionType;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch
 */
@Component
public class TransactionComponent {

    public TransactionComponent() {
    }

    //==========================================================================
    public long createTransaction(short transactionNumber, long id) throws Exception {

        long idTransaction;
        Transaction transaction = null;
        TransactionType transactionType = null;

        try {

            transactionType = new TransactionTypeComponent().getTransaction(transactionNumber);

            transaction = new Transaction();
            transaction.setObjectId(id);
            transaction.setTransactionType(transactionType);
            idTransaction = new DAO().create(transaction);

        } catch (Exception e) {
            throw e;
        }

        return idTransaction;

    }

}