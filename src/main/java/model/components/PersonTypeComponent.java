package model.components;

import java.util.HashMap;
import java.util.List;
import model.beans.PersonType;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch
 */
@Component
public class PersonTypeComponent {

    //==========================================================================
    public PersonTypeComponent() {
    }

    //==========================================================================
    public PersonType getPersonType(String type) throws Exception {

        if (type == null || type.length() < 1) {
            throw new IllegalArgumentException("type is nulll or empty");
        }

        PersonType pt = null;
        List<PersonType> personTypeList = null;
        HashMap parameters = new HashMap();

        try {
            
            parameters.put("name", type);
            personTypeList = new DAO().query("getPersonTypeByName", parameters, new PersonType());                        
            
            if(personTypeList == null || personTypeList.size() < 1){
                throw new Exception("personType doesn't exists");
            }else{
                pt = personTypeList.get(0);
            }

        } catch (Exception e) {
            throw e;
        }

        return pt;

    }

}
