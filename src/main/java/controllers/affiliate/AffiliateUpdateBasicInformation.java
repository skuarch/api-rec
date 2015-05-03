package controllers.affiliate;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.io.File;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Affiliate;
import model.beans.AffiliateBasicInformation;
import model.components.AffiliateComponent;
import model.components.GeneralConfigurationComponent;
import model.components.PersonTypeComponent;
import model.logic.Constants;
import model.util.FileUtil;
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
public class AffiliateUpdateBasicInformation extends BaseController {

    @Autowired
    private AffiliateComponent affiliateComponent;
    @Autowired
    private PersonTypeComponent personTypeComponent;
    @Autowired
    private GeneralConfigurationComponent generalConfigurationComponent;
    private static final Logger logger = getLogger(AffiliateUpdateBasicInformation.class);

    //==========================================================================
    @RequestMapping(value = {"/v1/affiliate/update/basic/information", "v1/affiliate/update/basic/information"})
    public @ResponseBody
    String updateBasicInformation(@ModelAttribute AffiliateBasicInformation affiliateBasicInformation, HttpServletResponse response) {

        JSONObject jsono = null;
        Affiliate affiliate = null;

        try {

            setContentType(response, MediaType.APPLICATION_JSON);
            affiliateBasicInformation
                    .getPerson()
                    .setPersonType(
                            personTypeComponent.getPersonType(Constants.AFFILIATE)
                    );
            affiliate = affiliateComponent.getAffiliate(affiliateBasicInformation.getId());
            affiliate.setPerson(affiliateBasicInformation.getPerson());
            affiliate.setBrand(affiliateBasicInformation.getBrand());
            affiliate.setCategory(affiliateBasicInformation.getCategory());
            affiliate.setDescription(affiliateBasicInformation.getDescription());
            affiliate.setLogoFile(affiliateBasicInformation.getLogoFile());

            if (affiliate.getLogoFile() != null) {
                //update logo
                deleteFile(affiliate);
                saveFiles(affiliate);
            }

            affiliateComponent.updateAffiliate(affiliate);

            jsono = new JSONObject();
            jsono.put("update", true);

            //create transaction
            TransactionUtil.createTransaction(Constants.TRANSACTION_UPDATE_BASIC_INFORMATION_AFFILIATE, affiliate.getId());

        } catch (Exception e) {
            logger.error("AffiliateUpdateBasicInformation.updateBasicInformation", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
            jsono.put("update", false);
        }

        return jsono.toString();

    }

    //==========================================================================
    private boolean saveFiles(Affiliate affiliate) throws Exception {

        boolean flag = false;
        String originalName;
        String extension = "";

        try {

            if (affiliate.getLogoFile() != null) {

                originalName = affiliate.getLogoFile().getOriginalFilename();
                int i = originalName.lastIndexOf('.');
                int p = Math.max(originalName.lastIndexOf('/'), originalName.lastIndexOf('\\'));

                if (i > p) {
                    extension = ".";
                    extension += originalName.substring(i + 1);
                }

                String path = generalConfigurationComponent.getGeneralConfiguration().getUploadPath();
                String fileName = "affiliate_" + affiliate.getId() + "_logo_" + System.currentTimeMillis() + extension;
                File f = new File(path + fileName);
                affiliate.getLogoFile().transferTo(f);
                affiliate.setLogoPathName(path + fileName);
                flag = true;
            }

        } catch (Exception e) {
            throw e;
        }

        return flag;

    }

    //==========================================================================
    private void deleteFile(Affiliate affiliate) {

        if(affiliate == null){
            throw new IllegalArgumentException("affiliate is null");
        }
        
        File file = null;

        try {

            if (affiliate.getLogoPathName() != null) {
                file = new File(affiliate.getLogoPathName());
                if (file.exists()) {
                    FileUtil.deleteFile(file);
                }
            }

        } catch (Exception e) {
            throw e;
        }

    }

}
