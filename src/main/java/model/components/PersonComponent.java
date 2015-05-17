package model.components;

import java.util.HashMap;
import java.util.List;
import model.beans.Person;
import model.beans.Responsable;
import model.database.DAO;
import org.springframework.stereotype.Component;

/**
 *
 * @author skuarch
 */
@Component
public class PersonComponent {

    public PersonComponent() {
    }

    //==========================================================================
    public Person getPerson(String email) throws Exception {

        if (email == null || email.length() < 1) {
            throw new NullPointerException("email is null or empty");
        }

        HashMap parameters = new HashMap();
        List<Person> personList = null;
        Person person = null;

        try {

            parameters.put("email", email);
            personList = new DAO().query(parameters, "getPersonByEmail", new Person());

            if (personList != null && personList.size() > 0) {
                person = personList.get(0);
            }

        } catch (Exception e) {
            throw e;
        }

        return person;

    }

    //==========================================================================
    public Person getPerson(String email, String password) throws Exception {

        if (email == null || email.length() < 1) {
            throw new NullPointerException("email is null or empty");
        }

        if (password == null || password.length() < 1) {
            throw new NullPointerException("password is null or empty");
        }

        HashMap parameters = new HashMap();
        List<Person> personList = null;
        Person person = null;

        try {

            parameters.put("email", email);
            parameters.put("password", password);
            personList = new DAO().query(parameters, "getPersonByEmailAndPassword", new Person());

            if (personList != null && personList.size() > 0) {
                person = personList.get(0);
            }

        } catch (Exception e) {
            throw e;
        }

        return person;

    }

    //==========================================================================
    public Person getPerson(long id) throws Exception {

        if (id < 1) {
            throw new IllegalArgumentException("id is less than 1");
        }

        Person p = null;

        try {

            p = new DAO().get(id, new Person());

            if (p == null) {
                throw new Exception("person doesn't exits");
            }

        } catch (Exception e) {
            throw e;
        }

        return p;

    }

    //==========================================================================
    public void updatePerson(Person p) throws Exception {

        if (p == null) {
            throw new IllegalArgumentException("person is null");
        }

        try {
            new DAO().update(p);
        } catch (Exception e) {
            throw e;
        }

    }
    
    //==========================================================================
    public long savePerson(Person person) throws Exception{
    
        if(person == null){
            throw new IllegalArgumentException("person is null");
        }
        
        long id;
        
        try {
            
            id = new DAO().create(person);
            
        } catch (Exception e) {
            throw e;
        }
        
        return id;
        
    }

}
