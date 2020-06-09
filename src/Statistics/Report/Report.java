package Statistics.Report;
import java.util.Date;
/**
 *
 * @author Michael Samir
 */
public class Report {
    int ID;
    String Owner,Type,Data,Date;

    public Report(String Date, String Owner, String Type, String Data) {
        this.Date = Date;
        this.Owner = Owner;
        this.Type = Type;
        this.Data = Data;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Report() {
    }
    
    public void view(){
    ReportGUI G =new ReportGUI(this);
    }

    public String getData() {
        return Data;
    }
    
}
