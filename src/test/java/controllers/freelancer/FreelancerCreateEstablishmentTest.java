package controllers.freelancer;

import java.util.ArrayList;
import model.beans.Affiliate;
import model.beans.Establishment;
import model.database.DAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author skuarch
 */
public class FreelancerCreateEstablishmentTest {
    
    public FreelancerCreateEstablishmentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createEstablishment method, of class FreelancerCreateEstablishment.
     */
    @Test
    public void testCreateEstablishment() throws Exception {
        
        Affiliate affiliate = new DAO().get(1, new Affiliate());
        ArrayList<Establishment> establiments =  (ArrayList<Establishment>) affiliate.getEstablishment();
        System.out.println(establiments.get(0));
        
    }
    
}
