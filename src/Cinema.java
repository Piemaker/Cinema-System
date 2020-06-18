import static DataBase.Database.Connect;
import javax.xml.namespace.QName;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;



    public class Cinema {
       static private Connection con;
     private static void getConnection(){
    Cinema.con = Connect();
    }

       // private static User users[] = new User[50]; //allocates 50 users spaces

       /* public class User {
            private String name;
            private String password;
            private String email;
            private int id = 0;



            public void register() {
                String etemp = "";
                String ntemp = "";
                String ptemp = "";
                Scanner input = new Scanner(System.in);
                String emailRegex = "^\\w+[\\W_]{0,2}\\w*\\.[a-zA-Z]+$";
                int passChar = 0;
                int passNum = 0;

                System.out.println("Please enter your E-mail. eg:(someone@domain.com)");
                etemp = input.nextLine();
                Pattern pat = Pattern.compile(emailRegex);

                if (pat.matcher(etemp).matches()) {
                    //to do check if email is already in use
                    System.out.println("Please enter a user name");
                    ntemp = input.nextLine();
                    do {
                        System.out.println("Please enter a password of at least 8 characters");
                        //to do put more constraints on password

                        ptemp = input.nextLine();
                    } while (ptemp.length() < 8);
                    users[id].email = etemp;
                    users[id].name = ntemp;
                    users[id].password = ptemp;
                    id++;
                } else {
                    System.out.println("Please enter a valid E-mail");
                }

            }//end of register()
        }*/

        public static void main(String args[]) {

try{
            getConnection();
            Statement stat= con.createStatement();
            ResultSet res= stat.executeQuery("SELECT * FROM movies");
               while(res.next()){
                   int id = res.getInt("id");
                   String name = res.getString("name");
                   String genre = res.getString("genre");
                   double rating = res.getDouble("rating");
                   System.out.println(id+" "+ name+" "+genre + " "+ rating );
               }
}

catch (Exception e){
    e.printStackTrace();
}
        }


 
    }
