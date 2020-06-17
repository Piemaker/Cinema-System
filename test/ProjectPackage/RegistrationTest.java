package ProjectPackage;

import ProjectPackage.Registration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michael Samir
 */
public class RegistrationTest {
    
    public RegistrationTest() {
    }
    /**
     * Test of verfiyName method, of class Registration.
     */
    // table generator 
    private int table(int name_case, int Password_case){
        if ((name_case == 1)||(Password_case==1)) {
        return 1;
        }else if ( (name_case == 2 ) || (name_case==4) ){
        return 2;
        }else if (name_case != Password_case){
        return 3;
        }
        return 0;
    }
    @Test
    public void testVerfiyName() {
        System.out.println("verfiyName");
        String[] name = {"","ab","mike","veryverylongname"};
        String[] password = {"","cd","12345","password"};
        Registration instance = new Registration();
        for (int n=1;n<=4;n++){
        for (int p=1;p<=4;p++){
        int expResult = table(n,p);
        int result = instance.verfiyName(name[n-1], password[p-1]);
        assertEquals("failed at TC"+Integer.toString(n+((p-1)*4))
                ,expResult
                , result);
        }}
    }

}
