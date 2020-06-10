package Statistics;


import Statistics.Report.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michael Samir
 */
public class Statistics_flow {
    public static String getDate(Date date) {
        SimpleDateFormat sdf =  new SimpleDateFormat ("yyyy-MM-dd HH:MM:SS a");
        return sdf.format(date);
    }
 
       public static void main(String args[]) {


           Statistics R = new Statistics();

           R.orderReport(1, 1);
        
       }

}
