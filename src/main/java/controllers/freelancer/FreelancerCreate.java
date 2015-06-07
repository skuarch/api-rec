package controllers.freelancer;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Address;
import model.beans.Freelancer;
import model.beans.Person;
import model.beans.PersonType;
import model.components.AddressComponent;
import model.components.FreelancerComponent;
import model.components.PersonComponent;
import model.components.PersonTypeComponent;
import model.logic.Constants;
import model.util.MD5Util;
import model.util.MailUtil;
import model.util.TransactionUtil;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author skuarch
 */
@RestController
public class FreelancerCreate extends BaseController {

    private static final Logger logger = getLogger(FreelancerCreate.class);
    
    @Autowired
    private AddressComponent addressComponent;
    @Autowired
    private FreelancerComponent freelancerComponent;
    @Autowired
    private PersonComponent personComponent;
    @Autowired
    private PersonTypeComponent personTypeComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/freelancer/create", "v1/freelancer/create"})
    public @ResponseBody
    String createFreelancer(@ModelAttribute Freelancer freelancer, HttpServletResponse response, Locale locale) {
        
        long id = 0;
        long personId;
        long addressId;
        JSONObject jsono = new JSONObject();
        String password = freelancer.getPassword();        

        try {

            //basic configuration
            setContentType(response, MediaType.APPLICATION_JSON);
            setHeaderNoChache(response);

            //some validations            
            if (freelancer.getPerson() == null) {
                logger.error("FreelancerCreate.createFreelancer", new Exception("freelancer.person is null"));
                jsono = new JSONObject("{\"error\":\"freelancer.person is null\",}");
                return jsono.toString();
            }

            if (freelancer.getAddress() == null) {
                logger.error("FreelancerCreate.createFreelancer", new Exception("freelancer.address is null"));
                jsono = new JSONObject("{\"error\":\"freelancer.address is null\",}");
                return jsono.toString();
            }

            if (freelancer.getPassword() == null || freelancer.getPassword().length() < 1) {
                logger.error("FreelancerCreate.createFreelancer", new Exception("freelancer.password is null"));
                jsono = new JSONObject("{\"error\":\"freelancer.password is null\",}");
                return jsono.toString();
            }

            if (existsPerson(freelancer.getPerson().getEmail())) {
                //the email already exists
                jsono.append("exists", true);
            } else {
                
                //create person
                personId = createPerson(freelancer.getPerson());
                freelancer.getPerson().setId(personId);
                
                //create address
                addressId = createAddress(freelancer.getAddress());
                freelancer.getAddress().setId(addressId);
                
                //create freelancer because I will use the id
                id = createFreelancer(freelancer);
                freelancer.setId(id);

                //every freelancer has a unique key
                freelancer.setKey(createKey(freelancer));

                //update freelancer because he has a new key
                freelancerComponent.updateFreelancer(freelancer);
                jsono.put("key", freelancer.getKey());

                //send mail to user
                MailUtil.sendMailNewFreelancer(freelancer, locale.getDisplayLanguage());                
                
                TransactionUtil.createTransaction(Constants.TRANSACTION_NEW_FREELANCER, freelancer.getId());

            }

        } catch (Exception e) {
            logger.error("FreelancerCreate.createFreelancer", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
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
            
            if(freelancer.getPassword().length() != 32){
                freelancer.setPassword(MD5Util.getHash(freelancer.getPassword()));
            }     
            
            id = new FreelancerComponent().saveFreelancer(freelancer);

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
    private long createPerson(Person p) throws Exception{
    
        long id;
        
        try {
            
            p.setPersonType(personTypeComponent.getPersonType(Constants.FREELANCER));
            id = personComponent.savePerson(p);
            
        } catch (Exception e) {
            throw e;
        }
        
        return id;
    
    }
    
    //==========================================================================
    private long createAddress(Address address) throws Exception{
    
        long id;
        
        try {
            
            id = addressComponent.saveAddress(address);
            
        } catch (Exception e) {
            throw e;
        }
        
        return id;
        
    }

}
