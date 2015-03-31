package model.components;

import model.beans.Address;
import model.beans.Contact;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch
 */
@Component
public class AddressComponent {

    public AddressComponent() {
    }

    //==========================================================================
    public long createAddress(Address address) throws Exception {

        if (address == null) {
            throw new IllegalArgumentException("address is null");
        }

        long id;

        try {

            id = new DAO().create(address);

        } catch (Exception e) {
            throw e;
        }

        return id;

    }

    //==========================================================================
    public void updateAddress(Address address) throws Exception {

        if (address == null) {
            throw new IllegalArgumentException("address is null");
        }

        try {

            new DAO().update(address);

        } catch (Exception e) {
            throw e;
        }

    }

}
