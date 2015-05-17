package model.components;

import model.beans.Responsable;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch
 */
@Component
public class ResponsableComponent {

    public ResponsableComponent() {
    }

    //==========================================================================
    public long saveResponsable(Responsable responsable) throws Exception {

        if (responsable == null) {
            throw new IllegalArgumentException("responsable is null");
        }

        long id;

        try {

            id = new DAO().create(responsable);

        } catch (Exception e) {
            throw e;
        }

        return id;

    }

    //==========================================================================
    public void updateResponsable(Responsable responsable) throws Exception {

        if (responsable == null) {
            throw new IllegalArgumentException("responsable is null");
        }

        try {

            new DAO().update(responsable);

        } catch (Exception e) {
            throw e;
        }

    }

    //==========================================================================
    public Responsable getResponsable(long id) throws Exception {

        if (id < 1) {
            throw new IllegalArgumentException("id is less than 1");
        }

        Responsable responsable = null;

        try {

            responsable = new DAO().get(id, new Responsable());

        } catch (Exception e) {
            throw e;
        }

        return responsable;

    }

}
