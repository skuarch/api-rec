package model.logic;

import java.util.function.Predicate;
import model.beans.Cashier;

/**
 *
 * @author skuarch
 */
//==========================================================================
public class SoftDeletedCashier implements Predicate<Cashier> {

    //==========================================================================
    @Override
    public boolean test(Cashier c) {
        return c.getIsSoftDeleted() != 0;
    }
}
