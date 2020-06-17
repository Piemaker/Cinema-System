package ProjectPackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ProjectPackage.RegisterLoginWindow;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mohab
 */
public class RegisterLoginWindowTest {
    
    public RegisterLoginWindowTest() {
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
     * Test of checkUsername method, of class RegisterLoginWindow.
     */
    @Test
    public void testcheckUsernameNotEmpty()
    {
        System.out.println("testcheckUsernameNotempty");
        
        String name = "name";
        RegisterLoginWindow instance = new RegisterLoginWindow();
        
        int expResult = instance.checkUsername(name);
        
        assertEquals(1, expResult);
    }
    
    /**
     * Test of checkUsername method, of class RegisterLoginWindow.
     */
    @Test
    public void testcheckUsernameEmpty()
    {
        System.out.println("testcheckUsernameempty");
        
        String name = "";
        RegisterLoginWindow instance = new RegisterLoginWindow();
        
        int expResult = instance.checkUsername(name);
        
        assertEquals(0, expResult);
    }
    
     /**
     * Test of checkUsername method, of class RegisterLoginWindow.
     */
    @Test
    public void testcheckUsernameLength()
    {
        System.out.println("testcheckUsernameempty");
        
        String name = "name";
        RegisterLoginWindow instance = new RegisterLoginWindow();
        
        int expResult = instance.checkUsernameLength(name);
        
        assertEquals(1, expResult);
    }
    
     /**
     * Test of checkUsername method, of class RegisterLoginWindow.
     */
    @Test
    public void testcheckUsernameLengthOverflow()
    {
        System.out.println("testcheckUsernameLengthOverflow");
        
        int n = 0;
        String name ="";
        while(n < 50){
        name = name.concat("n");
        n++;
        }
        System.out.println(name.length());
        System.out.println(name);
        RegisterLoginWindow instance = new RegisterLoginWindow();
        
        int expResult = instance.checkUsernameLength(name);
        
        assertEquals(0, expResult);
    }
    
     /**
     * Test of checkUsernameLength method, of class RegisterLoginWindow.
     */
    @Test
    public void testcheckUsernameLengthOverflowLowerBoundary()
    {
        System.out.println("testcheckUsernameLengthOverflowLowerBoundary");
        
        String name = "aaa";
        RegisterLoginWindow instance = new RegisterLoginWindow();
        
        int expResult = instance.checkUsernameLength(name);
        
        assertEquals(1, expResult);
    }
    
     /**
     * Test of checkUsernameLength method, of class RegisterLoginWindow.
     */
    @Test
    public void testcheckUsernameLengthOverflowUpperBoundary()
    {
        System.out.println("testcheckUsernameLengthOverflowLowerBoundary");
        
        int n = 0;
        String name ="";
        while(n < 50){
        name = name.concat("n");
        n++;
        }
        System.out.println(name.length());
        System.out.println(name);
        RegisterLoginWindow instance = new RegisterLoginWindow();
        
        int expResult = instance.checkUsernameLength(name);
        
        assertEquals(0, expResult);
    }
    
    
     /**
     * Test of checkPassword method, of class RegisterLoginWindow.
     */
    @Test
    public void testcheckpasswordNotEmpty()
    {
        System.out.println("testcheckpasswordNotEmpty");
        
        String password = "passW";
        RegisterLoginWindow instance = new RegisterLoginWindow();
        
        int expResult = instance.checkPassword(password);
        
        assertEquals(1, expResult);
    }
    
     /**
     * Test of checkPassword method, of class RegisterLoginWindow.
     */
    @Test
    public void testcheckpasswordEmpty()
    {
        System.out.println("testcheckpasswordEmpty");
        
        String password = "";
        RegisterLoginWindow instance = new RegisterLoginWindow();
        
        int expResult = instance.checkPassword(password);
        
        assertEquals(0, expResult);        
    }
    
     /**
     * Test of checkpasswordLength method, of class RegisterLoginWindow.
     */
    @Test
    public void testcheckpasswordLength()
    {
        System.out.println("testcheckpasswordLength");
        
        String password = "passW";
        RegisterLoginWindow instance = new RegisterLoginWindow();
        
        int expResult = instance.checkPasswordLength(password);
        
        assertEquals(1, expResult);
    }
    
     /**
     * Test of checkpasswordLength method, of class RegisterLoginWindow.
     */
    @Test
    public void testcheckpasswordLengthOverflow()
    {
        System.out.println("testcheckpasswordOverflow");
        
        String password = "passWords";
        RegisterLoginWindow instance = new RegisterLoginWindow();
        
        int expResult = instance.checkPasswordLength(password);
        
        assertEquals(0, expResult);
    }
    
     /**
     * Test of checkpasswordLength method, of class RegisterLoginWindow.
     */
    @Test
    public void testcheckpasswordLengthOverflowLowerBoundary()
    {
        System.out.println("testcheckpasswordOverflowLowerBoundary");
        
        String password = "pas";
        RegisterLoginWindow instance = new RegisterLoginWindow();
        
        int expResult = instance.checkPasswordLength(password);
        
        assertEquals(1, expResult);
    }
    
     /**
     * Test of checkpasswordLength method, of class RegisterLoginWindow.
     */
    @Test
    public void testcheckpasswordLengthOverflowUpperBoundary()
    {
        System.out.println("testcheckpasswordOverflowUpperBoundary");
        
        String password = "123456";
        RegisterLoginWindow instance = new RegisterLoginWindow();
        
        int expResult = instance.checkPasswordLength(password);
        
        assertEquals(1, expResult);
    }
}
    

