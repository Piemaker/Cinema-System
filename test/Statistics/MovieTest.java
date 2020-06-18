/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistics;
/*
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
*/

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Michael Samir
 */
public class MovieTest {
     int id = 0;       
    String name = "Test name";
    String genre = "Comedy";
    double rating = 4.5 ;
  
   
    
    
    /**
     * Test of equals method, of class Movie.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Movie instance = new Movie(id, name, genre, rating);
        Object obj = instance;
        boolean expResult = true;
        boolean result = instance.equals(obj);
         assertEquals(expResult, result);
         //assertEquals( "equals dosen't work !" , !expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    /**
     * Test of ResultSet constructor method, of class Movie.
     */
    
     @Test 
    public void testResultSet() throws SQLException{
        /*fake database*/
        ResultSet a= mock(ResultSet.class);
        when(a.next()).thenReturn(true).thenReturn(false);
        when(a.getInt("id")).thenReturn(id);
        when(a.getString("name")).thenReturn(name);
        when(a.getString("genre")).thenReturn(genre);
        when(a.getDouble("rating")).thenReturn(rating);
        /*new instance*/
        Movie m = new Movie(a);
        Movie m0 = new Movie(id, name, genre, rating);
        /*test*/
        assertTrue(m0.equals(m));
    }
}
    

