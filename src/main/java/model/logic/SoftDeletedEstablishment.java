package model.logic;

import java.util.function.Predicate;
import model.beans.Establishment;

/**
 *
 * @author skuarch
 */
//==========================================================================
public class SoftDeletedEstablishment implements Predicate<Establishment> {

    //==========================================================================
    @Override
    public boolean test(Establishment e) {
        return e.getIsSoftDeleted() != 0;
    }
}
