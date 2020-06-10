package Statistics;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;


import java.util.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import Statistics.Report.*;

/**
 * Version : alpha
 *
 * @author Michael Samir
 */
public class Statistics {

    Date Date;
    ResultSet Movies;
    // Genre Report
    int Genre_counter;
    HashMap Genres;
    
    HashMap Rate;
    
    String DateFormat="yyyy-MM-dd hh:mm:ss";

    public Statistics() {
        this.Date = new Date();
        this.Genres = new HashMap();
        this.Genre_counter = 0;
        this.Movies = getMovies();
        Process();
    }
   
    public Statistics(ResultSet Movies) {
        this.Date = new Date();
        this.Genres = new HashMap();
        this.Genre_counter =0;
        this.Movies = Movies;
        Process();
    }
    
    private void Process() {
    
    }


 //* date */   
    private String getDate() {
        SimpleDateFormat sdf =  new SimpleDateFormat (DateFormat);
        return sdf.format(Date);
    }
    
    private String newDate() {
        this.Date = new Date();
        return getDate(); 
    }
 
    
/////* Reports */
    /*order selector*/
    public void orderReport(int ID,int type){
    switch(type){
        case 1 :
            GenreReport(ID);
            break;
        case 2 :
            RateReport(ID);
            break;    
    }
    }
/*Genre*/    
    private void preGenre(){
        try {
            /* scan Genres */
            while (Movies.next()) {
                Movie xt = new Movie(Movies);
                if (Genres.get(xt.genre) == null) {
                    Genres.put(xt.genre, 1);
                } else {
                    Genres.put(xt.genre, (int) Genres.get(xt.genre) + 1);
                }
            }
            Genre_counter=Genres.size();
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
private Report GenreReport(int ID){
        preGenre();
/*   report will be a separete function   */
          String date=newDate(),data="",Type="Genre Report";
          
          data+="Number of Geners : " + Genres.size()+"\n";
          for (Object str : Genres.keySet()){
                data+=str + ": " + Genres.get(str)+"\n";
        }
          
          Report R=new Report(date,getName(ID),Type,data);
            R.setID(SaveReport(R,ID));
            R.view();
            return R;
}

///* Rate report */
private void preRate(){
        try {
            /* scan Rates */
            while (Movies.next()) {
                Movie xt = new Movie(Movies);
                if (Genres.get(xt.genre) == null) {
                    Genres.put(xt.genre, 1);
                } else {
                    Genres.put(xt.genre, (int) Genres.get(xt.genre) + 1);
                }
            }
            Genre_counter=Genres.size();
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

private Report RateReport(int ID){
    preRate();
      String date=newDate(),data="",Type="Rate Report";
          
          data+="Number of Geners : " + Genres.size()+"\n";
          for (Object str : Genres.keySet()){
                data+=str + ": " + Genres.get(str)+"\n";
        }
          
          Report R=new Report(date,getName(ID),Type,data);
            R.setID(SaveReport(R,ID));
            R.view();
return R;
}



public static void showList(){
ReportList L = new ReportList();
}

////*  db  */
/* connect to db 
override it to connect to the data base u want. 

*/
    public static Connection Connect() {
        Connection con = null;
        String Password = "root";
                 try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
            }
            try {
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Cinema","Mohab","qwa220zxs18MN313");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return con;
   }

    /*   get Result set from data base       */
    private ResultSet getMovies() {
          
try{
          Connection con;
          con = Connect();
          Statement stat= con.createStatement();
          ResultSet out= stat.executeQuery("SELECT * FROM movies");
          return out;  
}catch (Exception e){
    e.printStackTrace();
}
        return null;
          
        }

    
    public static String getName(int ID){
    String owner="" ; 
try{
          Connection con;
          con = Connect();
          Statement stat= con.createStatement();
          ResultSet admin= stat.executeQuery("SELECT * FROM Admin WHERE ID = "
                                                                        +Integer.toString(ID));
         while(admin.next()){
          owner+=admin.getString("FName");
          owner+=" "+admin.getString("LName");
            }
}catch (SQLException e){
    owner="Failed to get the name .";
}
    
return owner;
    
    }
    /* aka save report and get report id . */
    private int SaveReport(Report R,int OwnerID) {
       int ReportID=0;
       String query="insert into Report ( Owner , Type , Date , Report ) " +"values ( ? ,?, ?,?);";
       
      try(
          
              Connection con= Connect();
       // save 
              PreparedStatement stat= con.prepareStatement(query,
                                      Statement.RETURN_GENERATED_KEYS);
              ){
          stat.setString(1,Integer.toString(OwnerID));
          stat.setString(2,R.getType());
          stat.setString(3,newDate());
          stat.setString(4,"\""+R.getData()+"\"");
          int affectedRows = stat.executeUpdate();
          
            if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
            }

        try (ResultSet generatedKeys = stat.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                // get id
                ReportID=generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
    }catch(Exception e){
    e.printStackTrace();
    }
      
        
        return ReportID;
    }

}


