package controllers.person;

import static controllers.application.BaseController.getLogger;
import model.beans.Person;
import model.components.PersonComponent;
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
public class PersonUpdate {

    private static final Logger logger = getLogger(PersonUpdate.class);
    
    @Autowired
    private PersonComponent personComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/person/update", "v1/person/update"})
    public @ResponseBody
    String updatePerson(@ModelAttribute Person p) {

        JSONObject jsono;
        Person person;
        Person tmpPerson;        

        try {
            
            tmpPerson = personComponent.getPerson(p.getId());
            person = p;
            person.setPersonType(tmpPerson.getPersonType());
            person.setId(tmpPerson.getId());
            personComponent.updatePerson(person);
            
            jsono = new JSONObject();
            jsono.put("updated", true);

        } catch (Exception e) {
            logger.error("FreelancerUpdate.updateFreelancer", e);
            jsono = new JSONObject("{\"error\":\"" + e.getMessage() + "\",}");
            jsono.put("updated", false);
        }

        return jsono.toString();

    }

}
