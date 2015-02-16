package controllers.freelancer;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Freelancer;
import model.beans.Person;
import model.beans.PersonType;
import model.components.FreelancerComponent;
import model.components.PersonComponent;
import model.components.PersonTypeComponent;
import model.util.MD5Util;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch
 */
@RestController
public class FreelancerController extends BaseController {

    private static final Logger logger = getLogger(FreelancerController.class);
    @Autowired
    private FreelancerComponent freelancerComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/freelancer/create", "v1/freelancer/create"}, method = RequestMethod.POST)
    public @ResponseBody
    String createFreelancer(@ModelAttribute Freelancer freelancer, HttpServletResponse response, Locale locale) {

        long id = 0;
        JSONObject jsono = new JSONObject();
        String password = freelancer.getPassword();

        try {

            setContentType(response, MediaType.APPLICATION_JSON);

            //some validations            
            if (freelancer.getPerson() == null) {
                logger.error("FreelancerController.createFreelancer", new Exception("freelancer.person is null"));
                jsono = new JSONObject("{\"error\":\"freelancer.person is null\",}");
                return jsono.toString();
            }

            if (freelancer.getAddress() == null) {
                logger.error("FreelancerController.createFreelancer", new Exception("freelancer.address is null"));
                jsono = new JSONObject("{\"error\":\"freelancer.address is null\",}");
                return jsono.toString();
            }

            if (freelancer.getPassword() == null || freelancer.getPassword().length() < 1) {
                logger.error("FreelancerController.createFreelancer", new Exception("freelancer.password is null"));
                jsono = new JSONObject("{\"error\":\"freelancer.password is null\",}");
                return jsono.toString();
            }

            if (existsPerson(freelancer.getPerson().getEmail())) {
                //the email already exists
                jsono.append("exists", "true");
            } else {

                //create freelancer because I will use the id
                id = createFreelancer(freelancer);
                freelancer.setId(id);

                //every freelancer has a unique key
                freelancer.setKey(createKey(freelancer));

                //update freelancer because he has a new key
                freelancerComponent.updateFreelancer(freelancer);
                jsono.put("key", freelancer.getKey());

                //send mail to user
                sendMailNewFreelancer(freelancer, password, locale.getDisplayLanguage());

            }

        } catch (Exception e) {
            logger.error("FreelancerController.createFreelancer", e);
            jsono = new JSONObject("{\"error\":\"" + e.getMessage() + "\",}");
        }

        return jsono.toString();

    }

    //==========================================================================
    private boolean existsPerson(String email) throws Exception {

        Person person = null;
        boolean exists = false;

        try {

            person = new PersonComponent().getPerson(email);

            if (person == null) {
                exists = false;
            } else {
                //it can't exist 2 freelancers with the same email
                exists = person.getPersonType().getName().equalsIgnoreCase("freelancer");
            }

        } catch (Exception e) {
            throw e;
        } finally {
            person = null;
        }

        return exists;
    }

    //==========================================================================
    private long createFreelancer(Freelancer freelancer) throws Exception {

        PersonType personType = null;
        long id = 0;

        try {

            personType = new PersonTypeComponent().getPersonType("freelancer");
            freelancer.getPerson().setPersonType(personType);
            freelancer.setPassword(MD5Util.getHash(freelancer.getPassword()));
            id = new FreelancerComponent().createFreelancer(freelancer);

        } catch (Exception e) {
            throw e;
        }

        return id;

    }

    //==========================================================================
    private String createKey(Freelancer freelancer) {

        String key = null;

        try {

            key = freelancer.getPerson().getName().substring(0, 2)
                    + freelancer.getPerson().getLastName().substring(0, 2)
                    + freelancer.getId();

        } catch (Exception e) {
            throw e;
        }

        return key;
    }

    //==========================================================================
    private void sendMailNewFreelancer(Freelancer freelancer, String password, String displayLanguage) {

        if (freelancer == null) {
            return;
        }

        new Thread(() -> {
            try {
                model.logic.MailSender.sendMailNewFreelancer(
                        freelancer,
                        password,
                        displayLanguage
                );
            } catch (Exception e) {
                logger.error("AffiliateController.sendMailNewAffiliate", e);
            }
        }).start();

    }

}
