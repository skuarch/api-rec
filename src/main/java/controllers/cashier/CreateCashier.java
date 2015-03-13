package controllers.cashier;

import static controllers.application.BaseController.getLogger;
import controllers.responsable.UpdateResponsable;
import java.util.List;
import model.beans.Cashier;
import model.beans.Establishment;
import model.beans.Person;
import model.beans.PersonType;
import model.beans.Responsable;
import model.components.CashierComponent;
import model.components.EstablishmentComponent;
import model.components.PersonComponent;
import model.components.PersonTypeComponent;
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
public class CreateCashier {

    private static final Logger logger = getLogger(UpdateResponsable.class);
    @Autowired
    private CashierComponent cashierComponent;
    @Autowired
    private PersonTypeComponent personTypeComponent; 
    @Autowired
    private PersonComponent personComponent; 
    @Autowired
    private EstablishmentComponent establishmentComponent;
    
    //==========================================================================
    @RequestMapping(value = {"/v1/cashier/create","v1/cashier/create"})
    public @ResponseBody String createCashier(@ModelAttribute Establishment establishment, @ModelAttribute Cashier cashier){

        JSONObject jsono = null;
        Responsable r = null;
        Person person = null; 
        PersonType personType = null; 
        Establishment e = null;
        List<Cashier> cashiers = null;
        long cashierId;

        try {
            
            //create cashier
            personType = personTypeComponent.getPersonType("cashier");
            cashier.getPerson().setPersonType(personType);
            person = cashier.getPerson();
            personComponent.createPerson(person);            
            cashierId = cashierComponent.createCashier(cashier);
            cashier.setId(cashierId);            
            
            //get all the cashier
            e = establishmentComponent.getEstablishment(establishment.getId());
            cashiers = e.getCashier();
            cashiers.add(cashier);
            e.setCashier(cashiers);
            
            //update establishment
            establishmentComponent.updateEstablishment(e);
            
            jsono = new JSONObject();
            jsono.put("created", true);
            
        } catch (Exception ex) {
            logger.error("CreateCashier.createCashier", ex);
            jsono = new JSONObject("{\"error\":\"" + ex + "\",}");
        }

        return jsono.toString();

    }

}
