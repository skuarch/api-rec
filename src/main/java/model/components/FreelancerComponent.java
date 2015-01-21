package model.components;

import model.beans.Freelancer;
import model.database.DAO;

/**
 *
 * @author skuarch
 */
//@Component
public class FreelancerComponent {

    //==========================================================================
    /**
     * constructor.
     */
    public FreelancerComponent() {
    }

    //==========================================================================
    /**
     * insert a new row into freelancer table.
     *
     * @param freelancer Freelancer
     * @return long id
     * @throws Exception
     */
    public long createFreelancer(Freelancer freelancer) throws Exception {

        if (freelancer == null) {
            throw new NullPointerException("freelancer is null");
        }

        long id = 0;

        try {

            id = new DAO().create(freelancer);

        } catch (Exception e) {
            throw e;
        }

        return id;

    }

    //==========================================================================
    public void updateFreelancer(Freelancer freelancer) throws Exception {

        if (freelancer == null) {
            throw new IllegalArgumentException("freelancer is null");
        }

        try {
            new DAO().update(freelancer);
        } catch (Exception e) {
            throw e;
        }

    }

}
