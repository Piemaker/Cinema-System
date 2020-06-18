/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Michael Samir
 */
public class Database {
    
        static String Name = "root";
        static String Password = "root";
        static String URL="jdbc:mysql://localhost:3306/cinema";
    
    
    
    /* connect to db 
override it to connect to the data base u want. 

*/
    public static Connection Connect() {
        Connection con = null;
                 try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
            }
            try {
               
                con=DriverManager.getConnection(URL,Name,Password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return con;
   }
}
