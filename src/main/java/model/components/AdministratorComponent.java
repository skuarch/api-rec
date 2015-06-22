package model.components;

import java.util.ArrayList;
import java.util.HashMap;
import model.beans.Administrator;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch-lap
 */
@Component
public class AdministratorComponent {

    //==========================================================================
    public AdministratorComponent() {
    }

    //==========================================================================
    public Administrator getAdministrator(String email, String password) throws Exception {

        if (email == null || email.length() < 1) {
            throw new IllegalArgumentException("email is null or empty");
        }

        if (password == null || password.length() < 1) {
            throw new IllegalArgumentException("password is null or empty");
        }

        HashMap parameters = new HashMap();
        parameters.put("email", email);
        parameters.put("password", password);
        Administrator a = null;
        ArrayList<Administrator> administratorList = null;

        try {

            administratorList = new DAO().query(parameters, "getAdministrator", new Administrator());
            if (administratorList != null && administratorList.size() > 0) {
                a = administratorList.get(0);
            }

        } catch (Exception e) {
            throw e;
        }

        return a;

    }

    //==========================================================================
    public void updateAdministrator(Administrator administrator) throws Exception {

        if (administrator == null) {
            throw new IllegalArgumentException("administrator is null");
        }

        try {

            new DAO().update(administrator);

        } catch (Exception e) {
            throw e;
        }

    }

    //==========================================================================
    public Administrator getAdministrator(long id) throws Exception {

        if (id < 1) {
            throw new IllegalArgumentException("id is null or empty");
        }

        Administrator a = null;

        try {

            a = new DAO().get(id, new Administrator());
            
        } catch (Exception e) {
            throw e;
        }

        return a;

    }

}
