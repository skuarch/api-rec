package controllers.company;

import controllers.application.BaseController;
import static controllers.application.BaseController.getLogger;
import java.io.File;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import model.beans.Company;
import model.beans.CompanyBasicInformation;
import model.components.CompanyComponent;
import model.components.ContactComponent;
import model.components.GeneralConfigurationComponent;
import model.components.PersonTypeComponent;
import model.components.PersonComponent;
import model.logic.Constants;
import model.util.FileUtil;
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
public class CompanyUpdateBasicInformation extends BaseController {

    private static final Logger logger = getLogger(CompanyUpdateBasicInformation.class);
    
    @Autowired
    private CompanyComponent companyComponent;    
    @Autowired
    private PersonTypeComponent personTypeComponent;
    @Autowired
    private PersonComponent personComponent;
    @Autowired
    private GeneralConfigurationComponent generalConfigurationComponent;    

    //==========================================================================
    @RequestMapping(value = {"/v1/company/update/basic/information", "v1/company/update/basic/information"})
    public @ResponseBody
    String updateCompanyBasicInformation(@ModelAttribute CompanyBasicInformation companyBasicInformation, HttpServletResponse response) {
        
        JSONObject jsono = null;
        Company company = null;        
        
        try {
            
            setContentType(response, MediaType.APPLICATION_JSON);
            
            companyBasicInformation
                    .getPerson()
                    .setPersonType(
                            personTypeComponent.getPersonType(Constants.CONTACT)
                    );
            company = companyComponent.getCompany(companyBasicInformation.getCompanyId());            
            companyBasicInformation.setCompanyId(company.getId());
            company.setName(companyBasicInformation.getName());
            company.setBrand(companyBasicInformation.getBrand());            
            companyBasicInformation.getPerson().setId(company.getPerson().getId());            
            company.setPerson(companyBasicInformation.getPerson());
            personComponent.updatePerson(company.getPerson());
            
            company.setBrand(companyBasicInformation.getBrand());
            company.setCategory(companyBasicInformation.getCategory());            
            company.setLogoFile(companyBasicInformation.getLogoFile());
            company.setDescription(companyBasicInformation.getDescription());            
            
            if (company.getLogoFile() != null) {
                //update logo
                deleteFile(company);
                saveFiles(company);
            }
            
            companyComponent.updateCompany(company);

            jsono = new JSONObject();
            jsono.put("updated", true);

        } catch (Exception e) {
            logger.error("CompanyUpdateBasicInformation.updateCompanyBasicInformation", e);
            jsono = new JSONObject("{\"error\":\"" + e + "\",}");
            jsono.put("updated", false);
        }

        return jsono.toString();

    }
    
    
    //==========================================================================
    private boolean saveFiles(Company company) throws Exception {

        boolean flag = false;
        String originalName;
        String extension = "";

        try {

            if (company.getLogoFile() != null) {

                originalName = company.getLogoFile().getOriginalFilename();
                int i = originalName.lastIndexOf('.');
                int p = Math.max(originalName.lastIndexOf('/'), originalName.lastIndexOf('\\'));

                if (i > p) {
                    extension = ".";
                    extension += originalName.substring(i + 1);
                }

                String path = generalConfigurationComponent.getGeneralConfiguration().getUploadPath();
                String fileName = "company_" + company.getId() + "_logo_" + System.currentTimeMillis() + extension;
                File f = new File(path + fileName);
                company.getLogoFile().transferTo(f);
                company.setLogoPathName(path + fileName);
                flag = true;
            }

        } catch (Exception e) {
            throw e;
        }

        return flag;

    }

    //==========================================================================
    private void deleteFile(Company company) {

        if(company == null){
            throw new IllegalArgumentException("company is null");
        }
        
        File file = null;

        try {

            if (company.getLogoPathName() != null) {
                file = new File(company.getLogoPathName());
                if (file.exists()) {
                    FileUtil.deleteFile(file);
                }
            }

        } catch (Exception e) {
            throw e;
        }

    }

}
