import java.util.Objects;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Michael Samir
 */
public class Movie {
    int id;
    String name, genre;
    double rating;

    public Movie() {
        this.id = 0;
        this.name = "John Doe";
        this.genre = "";
        this.rating = 0;
    }

/* extract from ResultSet  */
    public Movie(ResultSet res) {
        try {
            this.id = res.getInt("id");
            this.name = res.getString("name");
            this.genre = res.getString("genre");
            this.rating = res.getDouble("rating");
        } catch (SQLException ex) {
            Logger.getLogger(Movie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Movie(int id, String name, String genre, double rating) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", name=" + name + ", genre=" + genre + ", rating=" + rating + '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movie other = (Movie) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.rating) != Double.doubleToLongBits(other.rating)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        return true;
    }



    
    

}
