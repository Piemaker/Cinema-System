package Statistics.Report;
import java.util.Date;
/**
 *
 * @author Michael Samir
 */
public class Report {
    String Owner,Type,Data,Date;

    public Report(String Date, String Owner, String Type, String Data) {
        this.Date = Date;
        this.Owner = Owner;
        this.Type = Type;
        this.Data = Data;
    }

    public Report() {
    }
    
    
}
