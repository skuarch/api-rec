/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.net;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author skuarch
 */
public class MailTest {
    
    public MailTest() {
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
     * Test of send method, of class Mail.
     */
    @Test
    @Ignore
    public void testSend() throws Exception {
        // Recipient's email ID needs to be mentioned.
      String to = "skuarch@hotmail.com";

      // Sender's email ID needs to be mentioned
      String from = "skuarch@regaloenclave.com";

      // Assuming you are sending email from localhost
      String host = "192.168.1.72";

      // Get system properties
      try{
          
          Mail mail = new Mail(from, host, 25, to);
          mail.send("testing ... ", "test from my pc");
         
      }catch (Exception mex) {
         mex.printStackTrace();
      }
   
    }
    
}
