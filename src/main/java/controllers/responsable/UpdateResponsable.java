package controllers.responsable;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.PersonType;
import model.beans.Responsable;
import model.components.PersonTypeComponent;
import model.components.ResponsableComponent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author skuarch
 */
@RestController
public class UpdateResponsable extends BaseController {

    private static final Logger logger = getLogger(UpdateResponsable.class);
    @Autowired
    private ResponsableComponent responsableComponent;
    @Autowired
    private PersonTypeComponent personTypeComponent;        

    @RequestMapping(value = {"/v1/responsable/update", "v1/responsable/update"})
    public @ResponseBody
    String updateResponsable(@ModelAttribute Responsable responsable, HttpServletResponse response, Locale locale) {

        JSONObject jsono = null;
        Responsable r = null;
        PersonType personType = null;        

        try {

            setContentType(response, MediaType.APPLICATION_JSON);
            
            //some validations            
            if (responsable.getPerson() == null) {
                logger.error("UpdateResponsable.updateResponsable", new Exception("responsable.person is null"));
                jsono = new JSONObject("{\"error\":\"responsable.person is null\",}");
                return jsono.toString();
            }            
            
            personType = personTypeComponent.getPersonType("responsable");
            responsable.getPerson().setPersonType(personType);
            
            r = responsableComponent.getResponsable(responsable.getId());
            r.setPassword(responsable.getPassword());
            r.getPerson().setEmail(responsable.getPerson().getEmail());
            r.getPerson().setGender(responsable.getPerson().getGender());
            r.getPerson().setLastName(responsable.getPerson().getLastName());
            r.getPerson().setName(responsable.getPerson().getName());
            r.getPerson().setPhone(responsable.getPerson().getPhone());             
            
            personTypeComponent.updatePerson(r.getPerson());
            
            responsableComponent.updateResponsable(r);
            jsono = new JSONObject();            
            jsono.put("updated", true);

        } catch (Exception e) {
            logger.error("UpdateResponsable.updateResponsable", e);
            jsono = new JSONObject("{\"error\":\"" + e.toString() + "\",}");
        }

        return jsono.toString();

    }

}
