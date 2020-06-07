package Statistics;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Version : alpha
 *
 * @author Michael Samir
 */
public class Statistics {

    Date now;
    int Genre_counter;
    HashMap Genres;
    ResultSet Movies;

    public Statistics(ResultSet Movies) {
        this.now = new Date();
        this.Genres = new HashMap();
        this.Genre_counter = Genre_counter;
        this.Movies = Movies;
        calc();
    }


    private void calc() {
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
                GenreReport();
            Genre_counter=Genres.size();
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
public void GenreReport(){
/*   report will be a separete function   */
            
            System.out.println("Number of Geners : " + Genres.size());
            Genres.keySet().forEach((str) -> {
                System.out.println(str + ": " + Genres.get(str));
        });
}
    
}