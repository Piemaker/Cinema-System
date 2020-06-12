package Statistics.Report;

import static Statistics.Statistics.Connect;
import static Statistics.Statistics.getName;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Michael Samir
 */
public class Report {
    int ID,OwnerID;
    String Owner,Type,Data,Date;

    public Report(String Date, String Owner, String Type, String Data) {
        this.Date = Date;
        this.Owner = Owner;
        this.Type = Type;
        this.Data = Data;
    }

    public Report(ResultSet S) {
        try {
                    this.ID = S.getInt("ID");
                    this.OwnerID = S.getInt("Owner");
                    this.Type = S.getString("Type");
                    this.Data = S.getString("Report");
                    this.Date = S.getString("Date");
                    this.Owner = getName(OwnerID);
        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Report() {
    }
    

    
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getData() {
        return Data;
    }

    public String getType() {
        return Type;
    }
    
    
    public void view(){
    ReportGUI G =new ReportGUI(this);
    }

    public Object[] getRow(){
        Object[] Row= {(int) this.ID, (String) this.Type, (String) this.Date, (String) getName(this.OwnerID), (int) this.OwnerID};
        return Row;
    }
    
    public static ResultSet getReportlist(){        
try{
          Connection con;
          con = Connect();
          Statement stat= con.createStatement();
          ResultSet out= stat.executeQuery("SELECT * FROM Report");
          return out;  
}catch (Exception e){
    e.printStackTrace();
}
        return null;
}
    public static boolean delete(int id){
    try{
          Connection con;
          con = Connect();
          PreparedStatement stat= con.prepareStatement("delete from Report where ID = ?");
          stat.setString(1,Integer.toString(id));
          int affectedRows = stat.executeUpdate();
          if (affectedRows == 0) {
                return false;
          }
          return true;
}catch (Exception e){
    e.printStackTrace();
}
    return false;
    }
  public static void View(int id){        
Report R;
      try{
          Connection con;
          con = Connect();
          Statement stat= con.createStatement();
          ResultSet r= stat.executeQuery("SELECT * FROM Report where ID = "+Integer.toString(id));
          while (r.next()){
          R=new Report(r);
          R.view();
          }
}catch (Exception e){
    e.printStackTrace();
}
}
    
}
