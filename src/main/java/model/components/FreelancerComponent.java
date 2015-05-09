package model.components;

import java.util.ArrayList;
import java.util.HashMap;
import model.beans.Freelancer;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch
 */
@Component
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

    //==========================================================================
    public Freelancer getFreelancer(String email, String password) throws Exception {

        if (email == null || email.length() < 1) {
            throw new IllegalArgumentException("email is null or empty");
        }

        if (password == null || password.length() < 1) {
            throw new IllegalArgumentException("password is null or empty");
        }

        HashMap parameters = new HashMap();
        parameters.put("email", email);
        parameters.put("password", password);
        Freelancer f = null;
        ArrayList<Freelancer> freelacerList = null;

        try {

            freelacerList = new DAO().query(parameters, "getFreelancer", new Freelancer());
            if (freelacerList != null && freelacerList.size() > 0) {
                f = freelacerList.get(0);
            }

        } catch (Exception e) {
            throw e;
        }

        return f;

    }

    //==========================================================================
    public Freelancer getFreelancer(long id) throws Exception {

        Freelancer f = null;
        try {

            f = new DAO().get(id, new Freelancer());

        } catch (Exception e) {
            throw e;
        }

        return f;

    }

    //==========================================================================
    public ArrayList<Freelancer> getFreelancersList() throws Exception {

        ArrayList<Freelancer> freelancers = new ArrayList<>();

        try {

            freelancers = new ArrayList<>(new DAO().query("getFreelancerList", new Freelancer()));

        } catch (Exception e) {
            throw e;
        }

        return freelancers;

    }

}
