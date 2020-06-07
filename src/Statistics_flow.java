

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    
        static private Connection con;
        static   void getConnection(){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema","Mohab","qwa220zxs18MN313");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("connected");
        }
       public static void main(String args[]) {

try{
            getConnection();
            Statement stat= con.createStatement();
            ResultSet res= stat.executeQuery("SELECT * FROM movies");
            /*  
            while(res.next()){
                  Movie a= new Movie(res);
                  
                   System.out.println(a.row());
               }
            */
            Statistics a= new Statistics(res);
}catch (Exception e){
    e.printStackTrace();
}

        }

}
