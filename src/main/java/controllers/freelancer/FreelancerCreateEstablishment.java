package controllers.freelancer;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Affiliate;
import model.beans.AffiliateEstablishmentBasic;
import model.beans.Cashier;
import model.beans.Establishment;
import model.beans.Person;
import model.beans.PersonType;
import model.beans.Responsable;
import model.components.AffiliateComponent;
import model.components.CashierComponent;
import model.components.EstablishmentComponent;
import model.components.PersonComponent;
import model.components.PersonTypeComponent;
import model.components.ResponsableComponent;
import model.logic.Constants;
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
public class FreelancerCreateEstablishment extends BaseController {

    private static final Logger logger = getLogger(FreelancerCreateEstablishment.class);

    @Autowired
    private AffiliateComponent affiliateComponent;
    @Autowired
    private PersonTypeComponent personTypeComponent;
    @Autowired
    private EstablishmentComponent establishmentComponent;
    @Autowired
    private CashierComponent cashierComponent;
    @Autowired
    private ResponsableComponent responsableComponent;
    @Autowired
    private PersonComponent personComponent;

    //==========================================================================
    @RequestMapping(value = {"/v1/affiliate/create/establishment", "v1/affiliate/create/establishment"})
    public @ResponseBody
    String createEstablishment(
            @ModelAttribute AffiliateEstablishmentBasic affiliateEstablishmentBasic,
            HttpServletResponse response,
            Locale locale) {

        JSONObject jsono = null;

        Affiliate affiliate = null;

        List<Establishment> establishmentSet = null;
        Establishment establishment = null;
        long establishmentId;

        PersonType responsablePersonType = null;
        Person responsablePerson = null;
        Responsable responsable = null;
        long responsablePersonId;
        long responsableId;

        List<Cashier> cashierList = null;
        PersonType cashierPersonType = null;
        Person cashierPerson = null;
        Cashier cashier = null;
        long cashierPersonId;
        long cashierId;

        try {

            setContentType(response, MediaType.APPLICATION_JSON);
            jsono = new JSONObject();

            //do some validations
            if (affiliateEstablishmentBasic.getAffiliateId() < 1) {
                jsono.put("created", false);
                return jsono.toString();
            }

            if (affiliateEstablishmentBasic.getEstablishment().getAddress() == null) {
                jsono.put("created", false);
                return jsono.toString();
            }

            if (affiliateEstablishmentBasic.getEstablishment().getResponsable() == null) {
                jsono.put("created", false);
                return jsono.toString();
            }

            if (affiliateEstablishmentBasic.getEstablishment() == null) {
                jsono.put("created", false);
                return jsono.toString();
            }

            if (affiliateEstablishmentBasic.getEstablishment().getName() == null || affiliateEstablishmentBasic.getEstablishment().getName().length() < 0) {
                jsono.put("created", false);
                return jsono.toString();
            }

            if (affiliateEstablishmentBasic.getEstablishment().getCashier() == null || affiliateEstablishmentBasic.getEstablishment().getCashier().size() < 0) {
                jsono.put("created", false);
                return jsono.toString();
            }

            if (affiliateEstablishmentBasic.getEstablishment().getCategory() == null || affiliateEstablishmentBasic.getEstablishment().getCategory().size() < 0) {
                jsono.put("created", false);
                return jsono.toString();
            }

            //get affiliate
            affiliate = affiliateComponent.getAffiliate(
                    affiliateEstablishmentBasic.getAffiliateId()
            );
            
            //get establishment
            establishment = affiliateEstablishmentBasic.getEstablishment();
            responsable = establishment.getResponsable();
            cashier = establishment.getCashier().get(0);

            //get personType responsable----------------------------------------
            responsablePersonType = personTypeComponent.getPersonType("responsable");

            //create person responsable
            responsablePerson = responsable.getPerson();
            responsablePerson.setPersonType(responsablePersonType);
            responsablePersonId = personComponent.createPerson(responsablePerson);
            responsablePerson.setId(responsablePersonId);

            //create responsable            
            responsable.setPerson(responsablePerson);
            responsableId = responsableComponent.createResponsable(responsable);
            responsable.setId(responsableId);

            //get personType cashier--------------------------------------------
            cashierPersonType = personTypeComponent.getPersonType("cashier");

            //create person cashier           
            cashierPerson = cashier.getPerson();
            cashierPerson.setPersonType(cashierPersonType);
            cashierPersonId = personComponent.createPerson(cashierPerson);
            cashierPerson.setId(cashierPersonId);

            //create cashier            
            cashier.setPerson(cashierPerson);
            cashierId = cashierComponent.createCashier(cashier);
            cashier.setId(cashierId);
            cashierList = new ArrayList<>();
            cashierList.add(cashier);

            //create establishment----------------------------------------------            
            establishment.setResponsable(responsable);
            establishment.setCashier(cashierList);
            establishmentId = establishmentComponent.createEstablishment(establishment);
            establishment.setId(establishmentId);
            establishmentSet = affiliate.getEstablishment();
            establishmentSet.add(establishment);
            
            //update affiliate            
            affiliate.setEstablishment(establishmentSet);
            affiliateComponent.updateAffiliate(affiliate);

            //everything is ok            
            jsono.put("created", true);

            //create transaction
            TransactionUtil.createTransaction(Constants.TRANSACTION_NEW_ESTABLISHMENT, establishment.getId());

            //send email to responsable
            //send mail to cashier            
        } catch (Exception e) {
            logger.error("FreelancerCreateEstablishment.createEstablishment", e);
            jsono = new JSONObject("{\"error\":\"" + e.toString() + "\",}");
            jsono.put("created", false);
        }

        return jsono.toString();

    }

}
