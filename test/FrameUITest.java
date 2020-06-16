/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import javax.swing.JPanel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author OSM
 */
public class FrameUITest {

    public FrameUITest() {
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
     * Test of checkDouble method, of class FrameUI.
     */
    @Test
    public void testCheckDouble() {
        System.out.println("testCheckDouble");

        //Arrange
        FrameUI instance = new FrameUI();
        String doubleNumber = "3.8";

        //Act
        boolean trueExpected = instance.checkDouble(doubleNumber);

        //Assert
        assertTrue("Value isn't double", trueExpected);
    }

    /**
     * Test of checkDouble method, of class FrameUI.
     */
    @Test
    public void testCheckNotDouble() {
        System.out.println("testCheckNotDouble");

        //Arrange
        FrameUI instance = new FrameUI();
        String text = "OSM";

        //Act
        boolean falseExpected = instance.checkDouble(text);

        //Assert
        assertFalse("Value is double", falseExpected);
    }

    /**
     * Test of checkEmptyMovie method, of class FrameUI.
     */
    @Test
    public void testCheckEmptyMovie() {
        System.out.println("checkEmptyMovie");
        //ARRANGE
        String name = "";
        String genre = null;
        String rating = "";
        FrameUI instance = new FrameUI();

        //ACT
        boolean expResultTrue = instance.checkEmptyMovie(name, genre, rating);

        //ASSERT
        assertTrue("The inputs are not empty", expResultTrue);
    }

    /**
     * Test of checkNotEmptyMovie method, of class FrameUI.
     */
    @Test
    public void testCheckNotEmptyMovie() {
        System.out.println("checkEmptyMovie");
        //ARRANGE
        String name = "TEST";
        String genre = "TEST";
        String rating = "10";
        FrameUI instance = new FrameUI();

        //ACT
        boolean expResultFalse = instance.checkEmptyMovie(name, genre, rating);

        //ASSERT
        assertFalse("The inputs are empty", expResultFalse);
    }

    /**
     * Test of checkRating method, of class FrameUI.
     */
    @Test
    public void testCheckRating() {
        System.out.println("checkRating");

        //ARRANGE
        double rate = 8;
        FrameUI instance = new FrameUI();

        //ACT
        boolean expResultTrue = instance.checkRating(rate);

        //ASSERT
        assertTrue("The rating is either negative or above 10 empty", expResultTrue);

    }

    /**
     * Test of checkRating method, of class FrameUI.
     */
    @Test
    public void testCheckFalseRating() {
        System.out.println("checkRating");

        //ARRANGE
        double rateNegative = -5;
        double rateAboveTen = 12;
        FrameUI instance = new FrameUI();

        //ACT
        boolean expResultFalse1 = instance.checkRating(rateNegative);
        boolean expResultFalse2 = instance.checkRating(rateAboveTen);

        //ASSERT
        assertFalse("The rating is negative", expResultFalse1);
        assertFalse("The rating is above 10", expResultFalse2);

    }

    /**
     * Test of checkMovieExist method, of class FrameUI.
     */
    @Test
    public void testCheckMovieExist() {

        System.out.println("checkMovieExist");

        //ARRANGE
        String name = "MovieThatExists";
        String genre = "TestGenre";
        double rate = 3;
        FrameUI instance = new FrameUI();

        //ACT
        try {
            instance.con.setAutoCommit(false);
            instance.insertMovie(name, genre, rate);
            int expResultOne = instance.checkMovieExist(name);

            //ASSERT
            assertEquals(expResultOne, 1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FrameUITest.testCheckMovieExist()");
        }

    }

    /**
     * Test of checkMovieNotExist method, of class FrameUI.
     */
    @Test
    public void testCheckMovieNotExist() {

        System.out.println("checkMovieExist");

        //ARRANGE
        String name = "MovieThatDoesn'tExist";
        FrameUI instance = new FrameUI();

        //ACT
        int expResultZero = instance.checkMovieExist(name);

        //ASSERT
        assertEquals(expResultZero, 0);

    }

    /**
     * Test of InsertMovie method, of class FrameUI.
     */
    @Test
    public void testInsertMovie() {
        System.out.println("testInsertMovie");

        //ARRANGE
        String name = "TESTNAME";
        String genre = "TESTGENRE";
        double rating = 5;
        FrameUI instance = new FrameUI();

        //ACT
        try {
            instance.con.setAutoCommit(false);
            int expResultOne = instance.insertMovie(name, genre, rating);

            //ASSERT
            assertEquals(1, expResultOne);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("FrameUITest.testInsertMovie(SQL EXCEPTION)");
        }
    }

    /**
     * Test of InsertMovie method, of class FrameUI.
     */
    @Test
    public void testInsertMovieFalse() {
        System.out.println("testInsertMovieFALSE");

        //ARRANGE
        String name = "VEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEERTLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONGTESTNAME";
        String genre = "TESTGENRE";
        double rating = 5;
        FrameUI instance = new FrameUI();

        //ACT
        try {
            instance.con.setAutoCommit(false);
            int expResultZero = instance.insertMovie(name, genre, rating);

            //ASSERT
            assertEquals(0, expResultZero);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("FrameUITest.testInsertMovieFalse(SQL EXCEPTION)");
        }
    }

    /**
     * Test of UpdateMovie method, of class FrameUI.
     */
    @Test
    public void testUpdateMovie() {
        System.out.println("testUpdateMovie");

        //ARRANGE
        String name = "TESTMOVIENAME";
        String genre = "TESTGENRE";
        double rate = 3;
        String newGenre = "NEWTESTGENRE";
        double newRate = 5;
        FrameUI instance = new FrameUI();

        try {
            //ACT
            instance.con.setAutoCommit(false);
            instance.insertMovie(name, genre, rate);
            int expResultOne = instance.updateMovie(name, newGenre, newRate);

            //ASSERT
            assertEquals(1, expResultOne);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("FrameUITest.testUpdateMovie()");
        }
    }

    /**
     * Test of UpdateMovie method, of class FrameUI.
     */
    @Test
    public void testUpdateMovieFalse() {
        System.out.println("testUpdateMovieFalse");

        //ARRANGE
        String name = "MovieThatDoesn'tExist";
        String newGenre = "NEWTESTGENRE";
        double newRate = 5;
        FrameUI instance = new FrameUI();

        try {
            //ACT
            instance.con.setAutoCommit(false);
            int expResultZero = instance.updateMovie(name, newGenre, newRate);

            //ASSERT
            assertEquals(0, expResultZero);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("FrameUITest.testUpdateMovie()");
        }
    }

}
