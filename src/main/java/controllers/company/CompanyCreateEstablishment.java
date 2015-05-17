package controllers.company;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Address;
import model.beans.Cashier;
import model.beans.Company;
import model.beans.CompanyEstablishmentBasic;
import model.beans.Establishment;
import model.beans.Person;
import model.beans.PersonType;
import model.beans.Responsable;
import model.components.AddressComponent;
import model.components.CashierComponent;
import model.components.CompanyComponent;
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
public class CompanyCreateEstablishment extends BaseController {

    private static final Logger logger = getLogger(CompanyCreateEstablishment.class);

    @Autowired
    private AddressComponent addressComponent;
    @Autowired
    private CompanyComponent companyComponent;
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

    @RequestMapping(value = {"/v1/company/create/establishment", "v1/company/create/establishment"})
    public @ResponseBody
    String createEstablishment(
            @ModelAttribute CompanyEstablishmentBasic companyEstablishmentBasic,
            HttpServletResponse response,
            Locale locale) {

        JSONObject jsono = null;

        Company company = null;

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
        
        Address address;
        long addressId;
        
        try {

            setContentType(response, MediaType.APPLICATION_JSON);
            jsono = new JSONObject();

            //do some validations
            if (companyEstablishmentBasic.getCompanyId()< 1) {
                logger.error("CompanyCreateEstablishment.createEstablishment", new Exception("companyId is null"));
                jsono.put("created", false);
                return jsono.toString();
            }

            if (companyEstablishmentBasic.getEstablishment().getAddress() == null) {
                logger.error("CompanyCreateEstablishment.createEstablishment", new Exception("address is null"));
                jsono.put("created", false);
                return jsono.toString();
            }

            if (companyEstablishmentBasic.getEstablishment().getResponsable() == null) {
                logger.error("CompanyCreateEstablishment.createEstablishment", new Exception("responsable is null"));
                jsono.put("created", false);
                return jsono.toString();
            }

            if (companyEstablishmentBasic.getEstablishment() == null) {
                logger.error("CompanyCreateEstablishment.createEstablishment", new Exception("establishment is null"));
                jsono.put("created", false);
                return jsono.toString();
            }

            if (companyEstablishmentBasic.getEstablishment().getName() == null || companyEstablishmentBasic.getEstablishment().getName().length() < 0) {
                logger.error("CompanyCreateEstablishment.createEstablishment", new Exception("establishment.getName is null"));
                jsono.put("created", false);
                return jsono.toString();
            }

            if (companyEstablishmentBasic.getEstablishment().getCashier() == null || companyEstablishmentBasic.getEstablishment().getCashier().size() < 0) {
                logger.error("CompanyCreateEstablishment.createEstablishment", new Exception("cashier is null"));
                jsono.put("created", false);
                return jsono.toString();
            }

            if (companyEstablishmentBasic.getEstablishment().getCategory() == null || companyEstablishmentBasic.getEstablishment().getCategory().size() < 0) {
                logger.error("CompanyCreateEstablishment.createEstablishment", new Exception("category is null"));
                jsono.put("created", false);
                return jsono.toString();
            }

            //get affiliate
            company = companyComponent.getCompany(
                    companyEstablishmentBasic.getCompanyId()
            );

            //get establishment
            establishment = companyEstablishmentBasic.getEstablishment();
            responsable = establishment.getResponsable();
            cashier = establishment.getCashier().get(0);
            address = establishment.getAddress();

            //get personType responsable----------------------------------------
            responsablePersonType = personTypeComponent.getPersonType("responsable");

            //create person responsable
            responsablePerson = responsable.getPerson();
            responsablePerson.setPersonType(responsablePersonType);
            responsablePersonId = personComponent.savePerson(responsablePerson);
            responsablePerson.setId(responsablePersonId);

            //create responsable            
            responsable.setPerson(responsablePerson);
            responsableId = responsableComponent.saveResponsable(responsable);
            responsable.setId(responsableId);

            //get personType cashier--------------------------------------------
            cashierPersonType = personTypeComponent.getPersonType("cashier");

            //create person cashier           
            cashierPerson = cashier.getPerson();
            cashierPerson.setPersonType(cashierPersonType);
            cashierPersonId = personComponent.savePerson(cashierPerson);
            cashierPerson.setId(cashierPersonId);

            //create cashier            
            cashier.setPerson(cashierPerson);
            cashierId = cashierComponent.saveCashier(cashier);
            cashier.setId(cashierId);
            cashierList = new ArrayList<>();
            cashierList.add(cashier);

            //create address
            addressId = addressComponent.saveAddress(address);
            establishment.getAddress().setId(addressId);
            
            //create establishment----------------------------------------------            
            establishment.setResponsable(responsable);
            establishment.setCashier(cashierList);
            establishmentId = establishmentComponent.saveEstablishment(establishment);
            establishment.setId(establishmentId);
            establishmentSet = company.getEstablishment();
            establishmentSet.add(establishment);

            //update affiliate            
            company.setEstablishment(establishmentSet);
            companyComponent.updateCompany(company);

            //everything is ok            
            jsono.put("created", true);

            //create transaction
            TransactionUtil.createTransaction(Constants.TRANSACTION_NEW_ESTABLISHMENT, establishment.getId());

            //send email to responsable
            //send mail to cashier            
        } catch (Exception e) {
            logger.error("CompanyCreateEstablishment.createEstablishment", e);
            jsono = new JSONObject("{\"error\":\"" + e.toString() + "\",}");
            jsono.put("created", false);
        }

        return jsono.toString();

    }

}
