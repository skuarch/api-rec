package model.components;

import java.util.HashMap;
import java.util.List;
import model.beans.Recipient;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch-lap
 */
@Component
public class RecipientComponent {

    //==========================================================================
    public RecipientComponent() {
    }

    //==========================================================================
    public long createRecipient(Recipient recipient) throws Exception {

        if (recipient == null) {
            throw new IllegalArgumentException("recipient is null ");
        }

        long id;

        try {

            id = new DAO().create(recipient);

        } catch (Exception e) {
            throw e;
        }

        return id;

    }

    //==========================================================================
    public Recipient getRecipient(long id) throws Exception {

        if (id < 1) {
            throw new IllegalArgumentException("id is less than 1");
        }

        Recipient recipient = null;

        try {

            recipient = new DAO().get(id, new Recipient());

        } catch (Exception e) {
            throw e;
        }

        return recipient;

    }

    //==========================================================================
    public HashMap getRecipientBySecret(String secret) throws Exception {

        if (secret == null || secret.length() < 1) {
            throw new IllegalArgumentException("secret is null or empty");
        }

        Recipient recipient = null;
        List recipientList;
        HashMap hm = null;
        String sql;

        try {

            //recipientList = new DAO().sqlQuery("SELECT r.* FROM recipient r, secret s, recipient_secret rs WHERE (s.secret_alphanumeric = '" + secret + "') AND (s.secret_id = rs.secret_id) AND (r.recipient_id = rs.recipient_id);");
            
            sql = "SELECT "
                    + "(r.recipient_id)id,"
                    + "(r.recipient_email)email, "
                    + "(r.recipient_last_name)lastName,"
                    + "(r.recipient_name)name,"
                    + "(r.recipient_phone)phone "
                    + "FROM "
                    + "recipient r, "
                    + "secret s, "
                    + "recipient_secret rs "
                    + "WHERE "
                    + "(s.secret_alphanumeric = '" + secret + "') "
                    + "AND (s.secret_id = rs.secret_id) "
                    + "AND (r.recipient_id = rs.recipient_id);";
            recipientList = new DAO().sqlQuery(sql);

            if (recipientList.size() > 0) {
                hm = (HashMap) recipientList.get(0);
            }

        } catch (Exception e) {
            throw e;
        }

        return hm;

    }

    //==========================================================================
    public Recipient getRecipientByEmail(String email) throws Exception {

        if (email == null || email.length() < 1) {
            throw new IllegalArgumentException("email is null or empty");
        }

        Recipient recipient = null;
        List<Recipient> recipientList;
        HashMap hm = null;

        try {

            hm = new HashMap();
            hm.put("email", email);
            recipientList = new DAO().query(hm, "getRecipientByEmail", new Recipient());

            if (recipientList != null && recipientList.size() > 0) {
                recipient = recipientList.get(0);
            }

        } catch (Exception e) {
            throw e;
        }

        return recipient;

    }

    //==========================================================================
    public void updateRecipient(Recipient recipient) throws Exception {
        
        if (recipient == null) {
            throw new IllegalArgumentException("recipient is null or empty");
        }

        try {

            new DAO().update(recipient);

        } catch (Exception e) {
            throw e;
        }
        
    }

}
