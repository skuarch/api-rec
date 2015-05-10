package controllers.affiliate;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.io.File;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Affiliate;
import model.beans.GeneralConfiguration;
import model.components.AddressComponent;
import model.components.AffiliateComponent;
import model.components.ContactComponent;
import model.components.GeneralConfigurationComponent;
import model.components.PersonComponent;
import model.components.PersonTypeComponent;
import model.logic.Constants;
import model.util.MailUtil;
import model.util.TransactionUtil;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author skuarch
 */
@Controller
public class AffiliateCreate extends BaseController {

    private static final Logger logger = getLogger(AffiliateCreate.class);

    @Autowired
    private AddressComponent addressComponent;
    @Autowired
    private AffiliateComponent affiliateComponent;
    @Autowired
    private PersonTypeComponent personTypeComponent;
    @Autowired
    private ContactComponent contactComponent;
    @Autowired
    private PersonComponent personComponent;
    @Autowired
    private GeneralConfigurationComponent generalConfigurationComponent;

    //==========================================================================
    /**
     * create a new Affiliate into database.
     *
     * @param affiliate Affiliate
     * @param response HttpServletResponse
     * @param locale locale
     * @return String
     */
    @RequestMapping(value = {"/v1/affiliate/create", "v1/affiliate/create"})
    public @ResponseBody
    String createAffiliate(@ModelAttribute Affiliate affiliate, HttpServletResponse response, Locale locale) {
        
        long id = 0;
        JSONObject jsono = null;
        long contactId = 0;
        long personContacId = 0;
        long addressId;
        long personId;
        boolean updateAffiliate;

        try {
            
            //basic configuration
            setHeaderNoChache(response);
            setContentType(response, MediaType.APPLICATION_JSON);

            //get person type
            affiliate.getContact().getPerson().setPersonType(personTypeComponent.getPersonType(Constants.CONTACT_BILLING));
            affiliate.getPerson().setPersonType(personTypeComponent.getPersonType(Constants.AFFILIATE));

            //save person
            personId = personComponent.createPerson(affiliate.getPerson());
            affiliate.getPerson().setId(personId);
            
            //save contact
            personContacId = personComponent.createPerson(affiliate.getContact().getPerson());
            affiliate.getContact().getPerson().setId(personContacId);
            contactId = contactComponent.createContact(affiliate.getContact());
            affiliate.getContact().setId(contactId);

            //save address
            addressId = addressComponent.createAddress(affiliate.getAddress());
            affiliate.getAddress().setId(addressId);
            
            //activate affiliate
            //affiliate.setActive((byte) 1);
            
            //create affiliate
            id = affiliateComponent.createAffiliate(affiliate);
            affiliate.setId(id);

            //if any file exists, saved it
            updateAffiliate = saveFiles(affiliate);

            //update affiliate because maybe he has a new file/path
            if (updateAffiliate) {
                affiliateComponent.updateAffiliate(affiliate);
            }

            //create json
            jsono = new JSONObject();
            jsono.append("created", true);
            jsono.append("id", id);

            //create transaction            
            TransactionUtil.createTransaction(Constants.TRANSACTION_NEW_AFFILIATE, affiliate.getId());

            //send email to the affiliate
            MailUtil.sendMailNewAffiliate(affiliate, locale.getDisplayLanguage());

            //create notification
        } catch (Exception e) {
            logger.error("AffiliateCreate.createAffiliate", e);
            jsono = new JSONObject("{\"error\":\"" + e.getMessage() + "\",}");
            jsono.append("created", false);
        }

        return jsono.toString();

    }

    //==========================================================================
    private boolean saveFiles(Affiliate affiliate) throws Exception {

        boolean flag = false;
        String originalName;
        String extension = "";
        GeneralConfiguration gc;

        try {

            if (affiliate.getLogoFile() != null) {

                originalName = affiliate.getLogoFile().getOriginalFilename();
                int i = originalName.lastIndexOf('.');
                int p = Math.max(originalName.lastIndexOf('/'), originalName.lastIndexOf('\\'));

                if (i > p) {
                    extension = ".";
                    extension += originalName.substring(i + 1);
                }

                gc = generalConfigurationComponent.getGeneralConfiguration();
                String path = gc.getUploadPath();
                String fileName = "affiliate_" + affiliate.getId() + "_logo_" + System.currentTimeMillis() + extension;                
                File f = new File(path + fileName);
                affiliate.getLogoFile().transferTo(f);
                affiliate.setLogoPathName(path + fileName);
                affiliate.setUrlLogo(gc.getUrlStaticImages() + fileName);
                flag = true;
            }

        } catch (Exception e) {
            throw e;
        }

        return flag;

    }
    
}
