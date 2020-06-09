package Statistics;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    int Genre_counter;
    HashMap Genres;
    HashMap Rate;
    ResultSet Movies;
    
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
        case 0:
            GenreReport(ID);
            break;
    }
    }
/*Genre*/    
private Report GenreReport(int ID){
/*   report will be a separete function   */
          String date=newDate(),data="",Type="Genre Report";
            data+="Number of Geners : " + Genres.size()+"\n";
           for (Object str : Genres.keySet()){
                data+=str + ": " + Genres.get(str)+"\n";
        }
            Report R=new Report(date,getName(ID),Type,data);
            SaveReport(R,ID);
            ReportGUI G =new ReportGUI(R);
            return R;
}

////*  db  */
/* connect to db */
    private Connection Connect() {
        Connection con = null;
        String Password = "root";
                 try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
            }
            try {
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Cinema","root",Password);
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

    
    private String getName(int ID){
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
    private void SaveReport(Report R,int ID) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}


