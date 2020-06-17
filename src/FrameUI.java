
import Statistics.Statistics;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.lang.ArrayIndexOutOfBoundsException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mohab
 */
public class FrameUI extends javax.swing.JFrame {

    /**
     * Creates new form FrameUI
     */
    public FrameUI() {
        initComponents();
        getConnection();
        initializations();
    }

    Connection con;
    Statement stat;
    ResultSet res;
    PreparedStatement mystat;
    String[][] movieArray = new String[100][4];
    String[][] logsArray = new String[300][8];
    String[][] actorArray = new String[100][4];

    //statment to fetch data of userRevRateView
    PreparedStatement joinStatement;
    PreparedStatement fetchNameStatement;

    //variables for the userRevRateView
    int rate;
    int uID;
    String review;
    int count;// to count the number of reviews
    int currentRevPage = 0;
    String name; //name of user
    //result sets for the userRevRateView
    ResultSet resJoin;
    ResultSet resName;

    void getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema", "Mohab", "qwa220zxs18MN313");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("connected");
    }

    public void close() {

        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

    }

    public class reviewBackground extends JPanel {

        private BufferedImage image;

        public reviewBackground() {
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/Images/gradient-red-linear-black-1366x768-c2-8b0000-000000-a-270-f-14.png"));
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }

    final void initializations() {
// for user review/rating view
        jtextUserName.setBackground(new java.awt.Color(0, 0, 0, 1));
        jtextUserRate.setBackground(new java.awt.Color(0, 0, 0, 1));
        jScrollPaneView.setOpaque(false);
        jScrollPaneView.getViewport().setOpaque(false);
        jScrollPaneView.setBorder(null);
        jScrollPaneView.setViewportBorder(null);
        jTextAreaViewReview.setBorder(null);
        jTextAreaViewReview.setBackground(new java.awt.Color(0, 0, 0, 1));

        jTextAreaViewReview.setLineWrap(true); //for adding new line instead of scrolling
        jTextAreaViewReview.setWrapStyleWord(true);

//for user review/rating submit
        jScrollPaneSubmit.setOpaque(false);
        jScrollPaneSubmit.getViewport().setOpaque(false);
        jScrollPaneSubmit.setBorder(null);
        jScrollPaneSubmit.setViewportBorder(null);
        jTextAreaSubmitReview.setBorder(null);
        jTextAreaSubmitReview.setBackground(new java.awt.Color(0, 0, 0, 1));

        jTextAreaSubmitReview.setLineWrap(true); //for adding new line instead of scrolling
        jTextAreaSubmitReview.setWrapStyleWord(true);

//for add/delete movies
        jtextname.setBackground(new java.awt.Color(0, 0, 0, 1));
        jtextgenre.setBackground(new java.awt.Color(0, 0, 0, 1));

        jtextrating.setBackground(new java.awt.Color(0, 0, 0, 1));

//for moviesTable
        JTableHeader movieHeader = jmovieTable.getTableHeader();
        movieHeader.setForeground(new java.awt.Color(75, 75, 75));
        movieHeader.setBackground(new java.awt.Color(75, 75, 75));

        //logsTable Color 
        JTableHeader logHeader = jlogTable.getTableHeader();
        logHeader.setForeground(new java.awt.Color(75, 75, 75));
        logHeader.setBackground(new java.awt.Color(75, 75, 75));

        //actorsTable Color
        JTableHeader actorsHeader = jActorTable.getTableHeader();
        actorsHeader.setForeground(new java.awt.Color(75, 75, 75));
        actorsHeader.setBackground(new java.awt.Color(75, 75, 75));

        //for add actor
        jtextName.setBackground(new java.awt.Color(0, 0, 0, 1));
        jtextDate.setBackground(new java.awt.Color(0, 0, 0, 1));
    }

//    SECTION FOR HELPER FUNCTIONS
    //method to check if rating is double
    public boolean checkDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }

    }

    //method to check if movie feilds are empty
    public boolean checkEmptyMovie(String name, String genre, String rating) {

        if ((name.isEmpty() || name == null) || genre.isEmpty() || genre == null || rating.isEmpty() || rating == null) {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkRating(double rate) {
        if ((rate < 0) || (rate > 10)) {
            return false;
        } else {
            return true;
        }
    }

    public int checkMovieExist(String name) {
        int count = 0;
        try {
            //check if movie already exists by counting if a row exists
            PreparedStatement mystatement = con.prepareStatement("SELECT COUNT(*) FROM movies  WHERE name = ?");
            mystatement.setString(1, name);
            ResultSet res = mystatement.executeQuery();
            res.next();
            count = res.getInt("COUNT(*)");

        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("FrameUI.checkMovieExist(SQL exception!)");
        }
        return count;
    }

    public int updateMovie(String name, String genre, double rate) {
        int result = 0;
        try {
            PreparedStatement mystatement = con.prepareStatement("UPDATE movies SET rating = ?,genre = ? WHERE name = ?");
            mystatement.setDouble(1, rate);
            mystatement.setString(2, genre);
            mystatement.setString(3, name);
            result = mystatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("FrameUI.updateMovie(Problem in updating movie)");
        }
        return result;
    }

    public int insertMovie(String name, String genre, double rate) {
        int result = 0;
        try {
            PreparedStatement myStatement = con.prepareStatement("INSERT INTO movies (id,name,genre,rating) VALUES(DEFAULT,?,?,?)");
            myStatement.setString(1, name);
            myStatement.setString(2, genre);
            myStatement.setDouble(3, rate);
            result = myStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("FrameUI.insertMovie(Problem in inserting movie)");
        }
        return result;
    }

    public int deleteSelectedMovie(int id) {
        int result = 0;
        try {
            PreparedStatement mystatement = con.prepareStatement("DELETE FROM movies WHERE id = ?");
            mystatement.setInt(1, id);
            result = mystatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("FrameUI.deleteSelectedMovie(Error in deletion)");
        }
        return result;
    }

    public void insertUpdateMovieSystemLogs(int adminID, int movieID, String opeartion, String logMessage) {

        // Write the logs in the table
        try {

            PreparedStatement myStatement = con.prepareStatement("INSERT INTO system_logs (ID, AdminID, movieID,timestamp ,Operation, LogMessage) Values(DEFAULT,?, ?, ?, ?, ?)");

            myStatement.setInt(1, adminID);
            myStatement.setInt(2, movieID);
            myStatement.setString(3, getDateTime());
            myStatement.setString(4, opeartion);
            myStatement.setString(5, logMessage);
            myStatement.execute();
        } catch (SQLException e) {
            System.out.println("FrameUI.insertSystemLogs(Problem in system logs)");
            e.printStackTrace();
        }
    }

    public void insertActorSystemLogs(int adminID, int actorID, String opeartion, String logMessage) {

        // Write the logs in the table
        try {

            PreparedStatement myStatement = con.prepareStatement("INSERT INTO system_logs (ID, AdminID, actorID,timestamp ,Operation, LogMessage) Values(DEFAULT,?, ?, ?, ?, ?)");

            myStatement.setInt(1, adminID);
            myStatement.setInt(2, actorID);
            myStatement.setString(3, getDateTime());
            myStatement.setString(4, opeartion);
            myStatement.setString(5, logMessage);
            myStatement.execute();
        } catch (SQLException e) {
            System.out.println("FrameUI.insertSystemLogs(Problem in system logs)");
            e.printStackTrace();
        }
    }

    public void insertReviewRateSystemLogs(int userID, int movieID, String opeartion, String logMessage) {

        // Write the logs in the table
        try {

            PreparedStatement myStatement = con.prepareStatement("INSERT INTO system_logs (ID, userID, movieID,timestamp ,Operation, LogMessage) Values(DEFAULT,?, ?, ?, ?, ?)");

            myStatement.setInt(1, userID);
            myStatement.setInt(2, movieID);
            myStatement.setString(3, getDateTime());
            myStatement.setString(4, opeartion);
            myStatement.setString(5, logMessage);
            myStatement.execute();
        } catch (SQLException e) {
            System.out.println("FrameUI.insertSystemLogs(Problem in system logs)");
            e.printStackTrace();
        }
    }

    public void insertSystemLogs(int userID, int adminID, int movieID, int actorID, String operation, String logMessage) {
        // Write the logs in the table
        try {

            PreparedStatement myStatement = con.prepareStatement("INSERT INTO system_logs (ID, UserID, AdminID, movieID,actorID, TimeStamp, Operation, LogMessage) Values(DEFAULT, ?,?, ?, ?, ?, ?, ?)");

            myStatement.setInt(1, userID);
            myStatement.setInt(2, adminID);
            myStatement.setInt(3, movieID);
            myStatement.setInt(4, actorID);
            myStatement.setString(5, getDateTime());
            myStatement.setString(6, operation);
            myStatement.setString(7, logMessage);
            myStatement.execute();
        } catch (SQLException e) {
            System.out.println("FrameUI.insertSystemLogs(Problem in system logs)");
            e.printStackTrace();
        }
    }

    public void LoadControlPanel(JPanel panel, String username, int id, int userType) {
        base1.removeAll();
        base1.add(panel);
        base1.repaint();
        base1.revalidate();
        if (userType == 50) // 50 refers to regular user
        {
            userNameL.setText("Username: " + username);
            userIdL.setText("User ID: " + id);
            addDeleteB.setVisible(false);
            deleteMovieB.setVisible(false);
            jButtonAddActor.setVisible(false);
            jButtonDeleteSelectedActor.setVisible(false);
            System.out.println(userIdL.getText().substring(9));
        } else if (userType == 100) // 100 refers to admin user
        {
            adminNameL.setText("Adminname: " + username);
            adminIdL.setText("Admin ID: " + id);
            revRateSubmitB.setVisible(false);
            revRateViewB.setVisible(false);
            System.out.println(adminIdL.getText().substring(10));
        }

    }

    public void LoadPanel(JPanel panel) {
        contentBase.removeAll();
        contentBase.add(panel);
        contentBase.repaint();
        contentBase.revalidate();
    }

    public void removePanels() {
        contentBase.removeAll();
    }

    public class costumPanel extends JPanel {

        private BufferedImage image;

        public costumPanel() {
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/Images/gradient-red-linear-black-1366x768-c2-8b0000-000000-a-270-f-14.png"));
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);

        }
    }

    static public String getDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        base1 = new javax.swing.JLayeredPane();
        adminPanel = new javax.swing.JPanel();
        adminIcon = new javax.swing.JLabel();
        moviesTableBa = new javax.swing.JButton();
        signOutButton = new javax.swing.JButton();
        adminNameL = new javax.swing.JLabel();
        adminIdL = new javax.swing.JLabel();
        moviesTableBa1 = new javax.swing.JButton();
        ReportListButton = new javax.swing.JButton();
        CreateReport = new javax.swing.JButton();
        actorsTablebutton = new javax.swing.JButton();
        UserPanel = new javax.swing.JPanel();
        userIcon = new javax.swing.JLabel();
        moviesTableB = new javax.swing.JButton();
        signOutButton1 = new javax.swing.JButton();
        userNameL = new javax.swing.JLabel();
        userIdL = new javax.swing.JLabel();
        actorsTablebutton1 = new javax.swing.JButton();
        secondaryPanel = new reviewBackground();
        contentBase = new javax.swing.JLayeredPane();
        backGroundP = new reviewBackground();
        movieTableP = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jmovieTable = new javax.swing.JTable();
        refreshB = new javax.swing.JButton();
        addDeleteB = new javax.swing.JButton();
        deleteMovieB = new javax.swing.JButton();
        jTextSearch = new javax.swing.JTextField();
        jSearch = new javax.swing.JButton();
        revRateViewB = new javax.swing.JButton();
        revRateSubmitB = new javax.swing.JButton();
        backGroundP3a = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlogTable = new javax.swing.JTable();
        reviewRatingSubmitP = new javax.swing.JPanel();
        jLabelReview = new javax.swing.JLabel();
        jLabelRate = new javax.swing.JLabel();
        movieIdL = new javax.swing.JLabel();
        jComboBoxRate = new javax.swing.JComboBox<>();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jButtonSubmit = new javax.swing.JButton();
        jScrollPaneSubmit = new javax.swing.JScrollPane();
        jTextAreaSubmitReview = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        addActor = new javax.swing.JPanel();
        jtextName = new javax.swing.JTextField();
        jtextDate = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jButtonAddActor1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        reviewRatingViewP = new javax.swing.JPanel();
        jLabelReview1 = new javax.swing.JLabel();
        jLabelReviewCount = new javax.swing.JLabel();
        jLabelCurrentReview = new javax.swing.JLabel();
        jLabeSlash = new javax.swing.JLabel();
        jLabelRate1 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jButtonNextReview = new javax.swing.JButton();
        jtextUserName = new javax.swing.JTextField();
        jtextUserRate = new javax.swing.JTextField();
        jScrollPaneView = new javax.swing.JScrollPane();
        jTextAreaViewReview = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        addMoviesP = new javax.swing.JPanel();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jtextname = new javax.swing.JTextField();
        jtextrating = new javax.swing.JTextField();
        jtextgenre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jadd = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        ReportTypeP = new reviewBackground();
        ReportType = new javax.swing.JComboBox<>();
        Oreder = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        actors = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jActorTable = new javax.swing.JTable();
        jButtonRefresh = new javax.swing.JButton();
        jButtonAddActor = new javax.swing.JButton();
        jButtonDeleteSelectedActor = new javax.swing.JButton();
        jTextSearch1 = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Navigation Window");
        setResizable(false);

        mainPanel.setBackground(new java.awt.Color(51, 51, 51));

        base1.setLayout(new java.awt.CardLayout());

        adminPanel.setBackground(new java.awt.Color(51, 51, 51));

        adminIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/admin.png"))); // NOI18N

        moviesTableBa.setBackground(new java.awt.Color(255, 153, 0));
        moviesTableBa.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        moviesTableBa.setForeground(new java.awt.Color(255, 255, 255));
        moviesTableBa.setText("Movies Table");
        moviesTableBa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moviesTableBaActionPerformed(evt);
            }
        });

        signOutButton.setBackground(new java.awt.Color(255, 153, 0));
        signOutButton.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        signOutButton.setForeground(new java.awt.Color(255, 255, 255));
        signOutButton.setText("Sign Out");
        signOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOutButtonActionPerformed(evt);
            }
        });

        adminNameL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        adminNameL.setForeground(new java.awt.Color(255, 255, 255));
        adminNameL.setText("Admin name");

        adminIdL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        adminIdL.setForeground(new java.awt.Color(255, 255, 255));
        adminIdL.setText("ID");

        moviesTableBa1.setBackground(new java.awt.Color(255, 153, 0));
        moviesTableBa1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        moviesTableBa1.setForeground(new java.awt.Color(255, 255, 255));
        moviesTableBa1.setText("System Logs");
        moviesTableBa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moviesTableBa1ActionPerformed(evt);
            }
        });

        ReportListButton.setBackground(new java.awt.Color(255, 153, 0));
        ReportListButton.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        ReportListButton.setForeground(new java.awt.Color(255, 255, 255));
        ReportListButton.setText("Report List");
        ReportListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportListButtonActionPerformed(evt);
            }
        });

        CreateReport.setBackground(new java.awt.Color(255, 153, 0));
        CreateReport.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        CreateReport.setForeground(new java.awt.Color(255, 255, 255));
        CreateReport.setText("Create Report ");
        CreateReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateReportActionPerformed(evt);
            }
        });

        actorsTablebutton.setBackground(new java.awt.Color(255, 153, 0));
        actorsTablebutton.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        actorsTablebutton.setForeground(new java.awt.Color(255, 255, 255));
        actorsTablebutton.setText("Actors Table");
        actorsTablebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actorsTablebuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout adminPanelLayout = new javax.swing.GroupLayout(adminPanel);
        adminPanel.setLayout(adminPanelLayout);
        adminPanelLayout.setHorizontalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addGroup(adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(adminPanelLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(adminIcon))
                    .addGroup(adminPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(moviesTableBa, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(adminPanelLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(adminIdL, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(adminNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(ReportListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CreateReport, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(signOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(actorsTablebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(moviesTableBa1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        adminPanelLayout.setVerticalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(adminIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(adminNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminIdL, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(moviesTableBa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(actorsTablebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(moviesTableBa1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ReportListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CreateReport, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(signOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        base1.add(adminPanel, "card3");

        UserPanel.setBackground(new java.awt.Color(51, 51, 51));

        userIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Userm.png"))); // NOI18N

        moviesTableB.setBackground(new java.awt.Color(255, 153, 0));
        moviesTableB.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        moviesTableB.setForeground(new java.awt.Color(255, 255, 255));
        moviesTableB.setText("Movies Table");
        moviesTableB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moviesTableBActionPerformed(evt);
            }
        });

        signOutButton1.setBackground(new java.awt.Color(255, 153, 0));
        signOutButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        signOutButton1.setForeground(new java.awt.Color(255, 255, 255));
        signOutButton1.setText("Sign Out");
        signOutButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOutButton1ActionPerformed(evt);
            }
        });

        userNameL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        userNameL.setForeground(new java.awt.Color(255, 255, 255));
        userNameL.setText("Username");

        userIdL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        userIdL.setForeground(new java.awt.Color(255, 255, 255));
        userIdL.setText("ID");

        actorsTablebutton1.setBackground(new java.awt.Color(255, 153, 0));
        actorsTablebutton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        actorsTablebutton1.setForeground(new java.awt.Color(255, 255, 255));
        actorsTablebutton1.setText("Actors Table");
        actorsTablebutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actorsTablebutton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UserPanelLayout = new javax.swing.GroupLayout(UserPanel);
        UserPanel.setLayout(UserPanelLayout);
        UserPanelLayout.setHorizontalGroup(
            UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(userIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
            .addGroup(UserPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(actorsTablebutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(signOutButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moviesTableB, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(UserPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userIdL, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        UserPanelLayout.setVerticalGroup(
            UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userIdL, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(moviesTableB, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(actorsTablebutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(signOutButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(267, Short.MAX_VALUE))
        );

        base1.add(UserPanel, "card2");

        secondaryPanel.setBackground(new java.awt.Color(255, 255, 0));

        contentBase.setLayout(new java.awt.CardLayout());

        backGroundP.setBackground(new java.awt.Color(51, 255, 204));

        movieTableP.setBackground(new java.awt.Color(43, 43, 43));

        jmovieTable.setBackground(new java.awt.Color(75, 75, 75));
        jmovieTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jmovieTable.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jmovieTable.setForeground(new java.awt.Color(255, 255, 255));
        jmovieTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Genre", "Rating"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jmovieTable.setGridColor(new java.awt.Color(75, 75, 75));
        jmovieTable.setName("Movies"); // NOI18N
        jmovieTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jmovieTable);

        refreshB.setBackground(new java.awt.Color(75, 75, 75));
        refreshB.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        refreshB.setForeground(new java.awt.Color(255, 255, 255));
        refreshB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-find-and-replace-64.png"))); // NOI18N
        refreshB.setText("Refresh          ");
        refreshB.setActionCommand("");
        refreshB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBActionPerformed(evt);
            }
        });

        addDeleteB.setBackground(new java.awt.Color(75, 75, 75));
        addDeleteB.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        addDeleteB.setForeground(new java.awt.Color(255, 255, 255));
        addDeleteB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-plus-64.png"))); // NOI18N
        addDeleteB.setText("      Add Movie      ");
        addDeleteB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDeleteBActionPerformed(evt);
            }
        });

        deleteMovieB.setBackground(new java.awt.Color(75, 75, 75));
        deleteMovieB.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        deleteMovieB.setForeground(new java.awt.Color(255, 255, 255));
        deleteMovieB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-delete-64.png"))); // NOI18N
        deleteMovieB.setText("Delete Selected   ");
        deleteMovieB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMovieBActionPerformed(evt);
            }
        });

        jTextSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextSearchActionPerformed(evt);
            }
        });

        jSearch.setBackground(new java.awt.Color(75, 75, 75));
        jSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-search-30.png"))); // NOI18N
        jSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchActionPerformed(evt);
            }
        });

        revRateViewB.setBackground(new java.awt.Color(75, 75, 75));
        revRateViewB.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        revRateViewB.setForeground(new java.awt.Color(255, 255, 255));
        revRateViewB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-view-64.png"))); // NOI18N
        revRateViewB.setText("View  User Review");
        revRateViewB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revRateViewBActionPerformed(evt);
            }
        });

        revRateSubmitB.setBackground(new java.awt.Color(75, 75, 75));
        revRateSubmitB.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        revRateSubmitB.setForeground(new java.awt.Color(255, 255, 255));
        revRateSubmitB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/submit.png"))); // NOI18N
        revRateSubmitB.setText("Submit a Review     ");
        revRateSubmitB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revRateSubmitBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout movieTablePLayout = new javax.swing.GroupLayout(movieTableP);
        movieTableP.setLayout(movieTablePLayout);
        movieTablePLayout.setHorizontalGroup(
            movieTablePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movieTablePLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(movieTablePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(movieTablePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(revRateSubmitB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addDeleteB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(revRateViewB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteMovieB, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(movieTablePLayout.createSequentialGroup()
                        .addComponent(jTextSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        movieTablePLayout.setVerticalGroup(
            movieTablePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movieTablePLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(movieTablePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextSearch)
                    .addComponent(jSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refreshB, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(revRateSubmitB, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(revRateViewB, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addDeleteB, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteMovieB, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(208, Short.MAX_VALUE))
            .addComponent(jScrollPane3)
        );

        javax.swing.GroupLayout backGroundPLayout = new javax.swing.GroupLayout(backGroundP);
        backGroundP.setLayout(backGroundPLayout);
        backGroundPLayout.setHorizontalGroup(
            backGroundPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(movieTableP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        backGroundPLayout.setVerticalGroup(
            backGroundPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(movieTableP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        contentBase.add(backGroundP, "card2");

        jlogTable.setBackground(new java.awt.Color(75, 75, 75));
        jlogTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jlogTable.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jlogTable.setForeground(new java.awt.Color(200, 200, 200));
        jlogTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "UserID", "AdminID", "MovieID", "ActorID", "TimeStamp", "Operation", "LogMessage"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jlogTable.setToolTipText("");
        jlogTable.setGridColor(new java.awt.Color(75, 75, 75));
        jlogTable.setName("Movies"); // NOI18N
        jlogTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jlogTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout backGroundP3aLayout = new javax.swing.GroupLayout(backGroundP3a);
        backGroundP3a.setLayout(backGroundP3aLayout);
        backGroundP3aLayout.setHorizontalGroup(
            backGroundP3aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        backGroundP3aLayout.setVerticalGroup(
            backGroundP3aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        contentBase.add(backGroundP3a, "card7");

        reviewRatingSubmitP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelReview.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelReview.setForeground(new java.awt.Color(255, 255, 255));
        jLabelReview.setText("Write Review");
        reviewRatingSubmitP.add(jLabelReview, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 390, 30));

        jLabelRate.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelRate.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRate.setText("Rating:");
        reviewRatingSubmitP.add(jLabelRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 140, 40));

        movieIdL.setText("ID");
        reviewRatingSubmitP.add(movieIdL, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jComboBoxRate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBoxRate.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxRate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "(1) Appalling", "(2) Horrible", "(3) Very Bad", "(4) Bad", "(5) Average", "(6) Fine", "(7) Good", "(8) Very Good", "(9) Great", "(10) Masterpiece" }));
        jComboBoxRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRateActionPerformed(evt);
            }
        });
        reviewRatingSubmitP.add(jComboBoxRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 170, -1));

        jSeparator8.setBackground(new java.awt.Color(200, 200, 200));
        reviewRatingSubmitP.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 680, -1));

        jSeparator6.setBackground(new java.awt.Color(200, 200, 200));
        reviewRatingSubmitP.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 680, 10));

        jSeparator5.setBackground(new java.awt.Color(200, 200, 200));
        reviewRatingSubmitP.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 680, 10));

        jSeparator7.setBackground(new java.awt.Color(200, 200, 200));
        reviewRatingSubmitP.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 680, 10));

        jButtonSubmit.setBackground(new java.awt.Color(115, 0, 0));
        jButtonSubmit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonSubmit.setForeground(new java.awt.Color(200, 200, 200));
        jButtonSubmit.setText("Submit");
        jButtonSubmit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmitActionPerformed(evt);
            }
        });
        reviewRatingSubmitP.add(jButtonSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 530, 180, 40));

        jScrollPaneSubmit.setFocusable(false);
        jScrollPaneSubmit.setOpaque(false);

        jTextAreaSubmitReview.setColumns(20);
        jTextAreaSubmitReview.setFont(new java.awt.Font("Palatino Linotype", 3, 24)); // NOI18N
        jTextAreaSubmitReview.setForeground(new java.awt.Color(255, 255, 255));
        jTextAreaSubmitReview.setLineWrap(true);
        jTextAreaSubmitReview.setRows(5);
        jTextAreaSubmitReview.setText("Your review here........");
        jTextAreaSubmitReview.setBorder(null);
        jTextAreaSubmitReview.setHighlighter(null);
        jTextAreaSubmitReview.setOpaque(false);
        jTextAreaSubmitReview.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextAreaSubmitReviewFocusGained(evt);
            }
        });
        jScrollPaneSubmit.setViewportView(jTextAreaSubmitReview);

        reviewRatingSubmitP.add(jScrollPaneSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 660, 220));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gradient-red-linear-black-1366x768-c2-8b0000-000000-a-270-f-14.png"))); // NOI18N
        jLabel7.setToolTipText("");
        reviewRatingSubmitP.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 640));

        contentBase.add(reviewRatingSubmitP, "card9");

        addActor.setToolTipText("");
        addActor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtextName.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jtextName.setForeground(new java.awt.Color(255, 255, 255));
        jtextName.setText("eg: Keanu Reeves");
        jtextName.setBorder(null);
        jtextName.setOpaque(false);
        jtextName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtextNameFocusGained(evt);
            }
        });
        jtextName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextNameActionPerformed(evt);
            }
        });
        addActor.add(jtextName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 650, 20));

        jtextDate.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jtextDate.setForeground(new java.awt.Color(255, 255, 255));
        jtextDate.setText("eg: YYYY-MM-DD");
        jtextDate.setBorder(null);
        jtextDate.setOpaque(false);
        jtextDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtextDateFocusGained(evt);
            }
        });
        jtextDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextDateActionPerformed(evt);
            }
        });
        addActor.add(jtextDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 650, 20));

        jSeparator12.setBackground(new java.awt.Color(200, 200, 200));
        jSeparator12.setForeground(new java.awt.Color(204, 204, 204));
        addActor.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 650, -1));

        jSeparator14.setBackground(new java.awt.Color(200, 200, 200));
        addActor.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 650, -1));

        jButtonAddActor1.setBackground(new java.awt.Color(115, 0, 0));
        jButtonAddActor1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonAddActor1.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddActor1.setText("Add");
        jButtonAddActor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActor1ActionPerformed(evt);
            }
        });
        addActor.add(jButtonAddActor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 520, 180, 40));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Date of Birth");
        addActor.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 130, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Name");
        addActor.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 92, -1));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("Add Actor");
        addActor.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 138, 73));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gradient-red-linear-black-1366x768-c2-8b0000-000000-a-270-f-14.png"))); // NOI18N
        jLabel11.setToolTipText("");
        jLabel11.setMaximumSize(new java.awt.Dimension(1920, 1080));
        jLabel11.setPreferredSize(new java.awt.Dimension(1920, 1080));
        addActor.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 640));

        contentBase.add(addActor, "card9");

        reviewRatingViewP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelReview1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelReview1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelReview1.setText("Review");
        reviewRatingViewP.add(jLabelReview1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 190, 30));

        jLabelReviewCount.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelReviewCount.setForeground(new java.awt.Color(255, 255, 255));
        jLabelReviewCount.setText("Max");
        reviewRatingViewP.add(jLabelReviewCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 540, -1, 40));

        jLabelCurrentReview.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelCurrentReview.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCurrentReview.setText("1");
        reviewRatingViewP.add(jLabelCurrentReview, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 540, -1, 40));

        jLabeSlash.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabeSlash.setForeground(new java.awt.Color(255, 255, 255));
        jLabeSlash.setText("/");
        reviewRatingViewP.add(jLabeSlash, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 540, -1, 40));

        jLabelRate1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelRate1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRate1.setText("Rating:");
        reviewRatingViewP.add(jLabelRate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 140, 40));

        jSeparator9.setBackground(new java.awt.Color(200, 200, 200));
        reviewRatingViewP.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 670, -1));

        jSeparator10.setBackground(new java.awt.Color(200, 200, 200));
        reviewRatingViewP.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 670, 10));

        jSeparator11.setBackground(new java.awt.Color(200, 200, 200));
        reviewRatingViewP.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 670, 10));

        jButtonNextReview.setBackground(new java.awt.Color(115, 0, 0));
        jButtonNextReview.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonNextReview.setForeground(new java.awt.Color(200, 200, 200));
        jButtonNextReview.setText("Next Review");
        jButtonNextReview.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNextReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextReviewActionPerformed(evt);
            }
        });
        reviewRatingViewP.add(jButtonNextReview, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 510, 180, 40));

        jtextUserName.setEditable(false);
        jtextUserName.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jtextUserName.setForeground(new java.awt.Color(255, 255, 255));
        jtextUserName.setText("User XXXX");
        jtextUserName.setBorder(null);
        jtextUserName.setOpaque(false);
        reviewRatingViewP.add(jtextUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 640, 30));

        jtextUserRate.setEditable(false);
        jtextUserRate.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jtextUserRate.setForeground(new java.awt.Color(255, 255, 255));
        jtextUserRate.setText("x");
        jtextUserRate.setBorder(null);
        jtextUserRate.setOpaque(false);
        reviewRatingViewP.add(jtextUserRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, 50, 40));

        jScrollPaneView.setFocusable(false);
        jScrollPaneView.setOpaque(false);

        jTextAreaViewReview.setEditable(false);
        jTextAreaViewReview.setColumns(20);
        jTextAreaViewReview.setFont(new java.awt.Font("Palatino Linotype", 3, 24)); // NOI18N
        jTextAreaViewReview.setForeground(new java.awt.Color(255, 255, 255));
        jTextAreaViewReview.setLineWrap(true);
        jTextAreaViewReview.setRows(5);
        jTextAreaViewReview.setText("No one has reviewd this movie yet, be the first to.");
        jTextAreaViewReview.setBorder(null);
        jTextAreaViewReview.setHighlighter(null);
        jTextAreaViewReview.setOpaque(false);
        jScrollPaneView.setViewportView(jTextAreaViewReview);

        reviewRatingViewP.add(jScrollPaneView, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 650, 220));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gradient-red-linear-black-1366x768-c2-8b0000-000000-a-270-f-14.png"))); // NOI18N
        jLabel8.setToolTipText("");
        reviewRatingViewP.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 640));

        contentBase.add(reviewRatingViewP, "card9");

        addMoviesP.setToolTipText("");
        addMoviesP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator13.setBackground(new java.awt.Color(200, 200, 200));
        addMoviesP.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 610, 10));

        jSeparator4.setBackground(new java.awt.Color(200, 200, 200));
        jSeparator4.setForeground(new java.awt.Color(204, 204, 204));
        addMoviesP.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 610, -1));

        jSeparator3.setBackground(new java.awt.Color(200, 200, 200));
        addMoviesP.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 610, -1));

        jtextname.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jtextname.setForeground(new java.awt.Color(255, 255, 255));
        jtextname.setText("eg: spiderman");
        jtextname.setBorder(null);
        jtextname.setOpaque(false);
        jtextname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtextnameFocusGained(evt);
            }
        });
        jtextname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextnameActionPerformed(evt);
            }
        });
        addMoviesP.add(jtextname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 610, 20));

        jtextrating.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jtextrating.setForeground(new java.awt.Color(255, 255, 255));
        jtextrating.setText("eg: 10, 3.5 , 6");
        jtextrating.setBorder(null);
        jtextrating.setOpaque(false);
        jtextrating.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtextratingFocusGained(evt);
            }
        });
        jtextrating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextratingActionPerformed(evt);
            }
        });
        addMoviesP.add(jtextrating, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 610, 20));

        jtextgenre.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jtextgenre.setForeground(new java.awt.Color(255, 255, 255));
        jtextgenre.setText("eg: action/comedy/adventure");
        jtextgenre.setBorder(null);
        jtextgenre.setOpaque(false);
        jtextgenre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtextgenreFocusGained(evt);
            }
        });
        jtextgenre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextgenreActionPerformed(evt);
            }
        });
        addMoviesP.add(jtextgenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 610, 20));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Genre");
        addMoviesP.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 130, -1));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Rating");
        addMoviesP.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 130, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Name");
        addMoviesP.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 92, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Add Movie");
        addMoviesP.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 138, 73));

        jadd.setBackground(new java.awt.Color(115, 0, 0));
        jadd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jadd.setForeground(new java.awt.Color(255, 255, 255));
        jadd.setText("Add");
        jadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jaddActionPerformed(evt);
            }
        });
        addMoviesP.add(jadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 540, 180, 40));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gradient-red-linear-black-1366x768-c2-8b0000-000000-a-270-f-14.png"))); // NOI18N
        jLabel9.setToolTipText("");
        jLabel9.setMaximumSize(new java.awt.Dimension(1920, 1080));
        jLabel9.setPreferredSize(new java.awt.Dimension(1920, 1080));
        addMoviesP.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 720, 640));

        contentBase.add(addMoviesP, "card8");

        ReportType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "Genre Report", "Rate Report" }));

        Oreder.setBackground(new java.awt.Color(115, 0, 0));
        Oreder.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Oreder.setForeground(new java.awt.Color(255, 255, 255));
        Oreder.setText("Order");
        Oreder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrederActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Choose the report type");

        javax.swing.GroupLayout ReportTypePLayout = new javax.swing.GroupLayout(ReportTypeP);
        ReportTypeP.setLayout(ReportTypePLayout);
        ReportTypePLayout.setHorizontalGroup(
            ReportTypePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportTypePLayout.createSequentialGroup()
                .addGroup(ReportTypePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ReportTypePLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ReportTypePLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ReportType, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ReportTypePLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(Oreder, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(412, Short.MAX_VALUE))
        );
        ReportTypePLayout.setVerticalGroup(
            ReportTypePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportTypePLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(ReportType, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(Oreder, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(376, Short.MAX_VALUE))
        );

        contentBase.add(ReportTypeP, "card6");

        actors.setBackground(new java.awt.Color(43, 43, 43));

        jActorTable.setBackground(new java.awt.Color(75, 75, 75));
        jActorTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jActorTable.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jActorTable.setForeground(new java.awt.Color(255, 255, 255));
        jActorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Date of Birth", "Age"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jActorTable.setGridColor(new java.awt.Color(75, 75, 75));
        jActorTable.setName("Movies"); // NOI18N
        jActorTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jActorTable);

        jButtonRefresh.setBackground(new java.awt.Color(75, 75, 75));
        jButtonRefresh.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonRefresh.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-find-and-replace-64.png"))); // NOI18N
        jButtonRefresh.setText("Refresh          ");
        jButtonRefresh.setActionCommand("");
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        jButtonAddActor.setBackground(new java.awt.Color(75, 75, 75));
        jButtonAddActor.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButtonAddActor.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddActor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-plus-64.png"))); // NOI18N
        jButtonAddActor.setText("      Add Actor      ");
        jButtonAddActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActorActionPerformed(evt);
            }
        });

        jButtonDeleteSelectedActor.setBackground(new java.awt.Color(75, 75, 75));
        jButtonDeleteSelectedActor.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButtonDeleteSelectedActor.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDeleteSelectedActor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-delete-64.png"))); // NOI18N
        jButtonDeleteSelectedActor.setText("Delete Selected   ");
        jButtonDeleteSelectedActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteSelectedActorActionPerformed(evt);
            }
        });

        jTextSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextSearch1ActionPerformed(evt);
            }
        });

        jButtonSearch.setBackground(new java.awt.Color(75, 75, 75));
        jButtonSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-search-30.png"))); // NOI18N
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout actorsLayout = new javax.swing.GroupLayout(actors);
        actors.setLayout(actorsLayout);
        actorsLayout.setHorizontalGroup(
            actorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actorsLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(actorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(actorsLayout.createSequentialGroup()
                        .addComponent(jTextSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonAddActor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDeleteSelectedActor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        actorsLayout.setVerticalGroup(
            actorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actorsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(actorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextSearch1)
                    .addComponent(jButtonSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAddActor, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDeleteSelectedActor, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );

        contentBase.add(actors, "card8");

        javax.swing.GroupLayout secondaryPanelLayout = new javax.swing.GroupLayout(secondaryPanel);
        secondaryPanel.setLayout(secondaryPanelLayout);
        secondaryPanelLayout.setHorizontalGroup(
            secondaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentBase, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        secondaryPanelLayout.setVerticalGroup(
            secondaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentBase)
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(base1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(secondaryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(base1, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
            .addComponent(secondaryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void moviesTableBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moviesTableBActionPerformed
        this.LoadPanel(backGroundP);
    }//GEN-LAST:event_moviesTableBActionPerformed

    private void moviesTableBaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moviesTableBaActionPerformed
        this.LoadPanel(backGroundP);
    }//GEN-LAST:event_moviesTableBaActionPerformed

    private void signOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signOutButtonActionPerformed
        RegisterLoginWindow loginFrame = new RegisterLoginWindow();
        this.setVisible(false);
        loginFrame.setVisible(true);
    }//GEN-LAST:event_signOutButtonActionPerformed

    private void signOutButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signOutButton1ActionPerformed
        RegisterLoginWindow loginFrame = new RegisterLoginWindow();
        this.setVisible(false);
        loginFrame.setVisible(true);
    }//GEN-LAST:event_signOutButton1ActionPerformed

    private void jtextratingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtextratingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtextratingActionPerformed

    private void jtextgenreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtextgenreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtextgenreActionPerformed

    private void jaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jaddActionPerformed
        String name = jtextname.getText();
        String genre = jtextgenre.getText();
        String rating = jtextrating.getText();

        double rate = 0;
        int movieId = 0;
        int adminId = 0;

        //check if values are null/empty
        if (checkEmptyMovie(name, genre, rating)) {

            //display error message
            JOptionPane.showMessageDialog(null,
                    "Values can't be set to empty or null!",
                    "Empty/Null Error!",
                    JOptionPane.ERROR_MESSAGE);
        } else if (checkDouble(rating)) {
            rate = Double.parseDouble(rating);

            if (!checkRating(rate)) {

                JOptionPane.showMessageDialog(null,
                        "Enter a rating from 0 to 10 ",
                        "Rating error",
                        JOptionPane.ERROR_MESSAGE);

            } else {

                try {

                    //check if movie already exists by counting if a row exists
                    int count = checkMovieExist(name);
                    System.out.println(count);
                    if (count != 0) {

                        updateMovie(name, genre, rate);
                        System.out.println("Success 1");

                        //code for system logs.
                        // first retrieve the id of the inserted movie.
                        stat = con.createStatement();
                        res = stat.executeQuery("SELECT ID FROM movies WHERE name = '" + name + "'");
                        if (res.next()) {
                            movieId = res.getInt("ID");
                        }
                        System.out.println("done2");
                        // Write the logs in the table
                        adminId = Integer.parseInt(adminIdL.getText().substring(10));
                        String logMessage = "Admin with adminID = " + adminId + " has updated a movie with movieId = " + movieId;

                        insertUpdateMovieSystemLogs(adminId, movieId, "Update", logMessage);

                        JOptionPane.showMessageDialog(null,
                                "Success",
                                "Successfully Updated",
                                JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        // query to insert data into movie table

                        insertMovie(name, genre, rate);
                        System.out.println("done1");

                        //code for system logs.
                        // first retrieve the id of the inserted movie.
                        stat = con.createStatement();
                        res = stat.executeQuery("SELECT ID FROM movies WHERE name = '" + name + "'");
                        if (res.next()) {
                            movieId = res.getInt("ID");
                        }
                        System.out.println("done2");
                        // Write the logs in the table
                        adminId = Integer.parseInt(adminIdL.getText().substring(10));
                        String logMessage = "Admin with adminID = " + adminId + " has added a movie with movieId = " + movieId;
                        insertUpdateMovieSystemLogs(adminId, movieId, "Add", logMessage);

                        JOptionPane.showMessageDialog(null,
                                "Success",
                                "Successfully Added",
                                JOptionPane.INFORMATION_MESSAGE);

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Unable to submit",
                            "Database error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Enter a numeric rating",
                    "Rating error",
                    JOptionPane.ERROR_MESSAGE);

        }


    }//GEN-LAST:event_jaddActionPerformed

    private void refreshBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBActionPerformed
        try {
            //button to refresh the data in the movie table

            stat = con.createStatement();
            // query to select table
            res = stat.executeQuery("SELECT * FROM movies");

            //model to update movie table values
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("Genre");
            model.addColumn("Rating");
            int id;
            String name;
            String genre;
            double rating;
            int row = 0;
            //loop to aquire data from the DB
            while (res.next()) {
                id = res.getInt("id");
                name = res.getString("name");
                genre = res.getString("genre");
                rating = res.getDouble("rating");
                movieArray[row][0] = String.valueOf(id);
                movieArray[row][1] = String.valueOf(name);
                movieArray[row][2] = String.valueOf(genre);
                movieArray[row][3] = String.valueOf(rating);
                model.addRow(movieArray[row]);
                row++;
            }
            //set model to table
            jmovieTable.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
        jmovieTable.setAutoCreateRowSorter(true);

    }//GEN-LAST:event_refreshBActionPerformed

    private void addDeleteBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDeleteBActionPerformed
        this.LoadPanel(addMoviesP);
    }//GEN-LAST:event_addDeleteBActionPerformed

    private void deleteMovieBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMovieBActionPerformed
        //button to delete selected row without entering id works for id set to column 0

        //geting selected id value
        //you have to typecast to String in order to parse int
        //try catch block for when no row is selected
        try {

            int id = Integer.parseInt((String) jmovieTable.getValueAt(jmovieTable.getSelectedRow(), 0));

            PreparedStatement mystatement;
            try {
                mystatement = con.prepareStatement("DELETE FROM movies WHERE id = ?");
                mystatement.setInt(1, id);
                mystatement.execute();

                // code for system logs.
                int adminId = Integer.parseInt(adminIdL.getText().substring(10));
                String logMessage = "Admin with adminID = " + adminId + " has added a deleted a movie with movieId = " + id;
                insertUpdateMovieSystemLogs(adminId, id, "Delete", logMessage);

                JOptionPane.showMessageDialog(null,
                        "Success",
                        "Successfully Deleted",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                Logger.getLogger(MovieTable.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,
                        "Enter a numeric rating",
                        "Rating error",
                        JOptionPane.ERROR_MESSAGE);

            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Please select a row to delete",
                    "Selection error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_deleteMovieBActionPerformed

    private void jTextSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextSearchActionPerformed

    private void jSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchActionPerformed
        String search = jTextSearch.getText();
        PreparedStatement myStatement;
        ResultSet res;

        try {

            // myStatement = con.prepareStatement("SELECT * FROM movies WHERE name LIKE ?'%'");
            //  myStatement.setString(1, search);
            // res=  myStatement.executeQuery();
            stat = con.createStatement();
            res = stat.executeQuery("SELECT * FROM movies WHERE name LIKE '" + search + "%'");

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("Genre");
            model.addColumn("Rating");
            int id;
            String name;
            String genre;
            double rating;
            int row = 0;
            //loop to aquire data from the DB
            while (res.next()) {
                id = res.getInt("id");
                name = res.getString("name");
                genre = res.getString("genre");
                rating = res.getDouble("rating");
                movieArray[row][0] = String.valueOf(id);
                movieArray[row][1] = String.valueOf(name);
                movieArray[row][2] = String.valueOf(genre);
                movieArray[row][3] = String.valueOf(rating);
                model.addRow(movieArray[row]);
                row++;
            }
            //set model to table
            jmovieTable.setModel(model);

            jmovieTable.setAutoCreateRowSorter(true);

        } catch (SQLException ex) {
            Logger.getLogger(MovieTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jSearchActionPerformed

    private void revRateViewBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revRateViewBActionPerformed
        //button to view reviews of selected movie

        try {

            int movieID = Integer.parseInt((String) jmovieTable.getValueAt(jmovieTable.getSelectedRow(), 0));

            //join review table and rate table to get review,rate and userid
            joinStatement = con.prepareStatement("SELECT userreview.review, userrating.userrating, userrating.userid"
                    + " FROM userreview INNER JOIN userrating ON userrating.UserID = userreview.UserID"
                    + " WHERE userrating.MovieID = ? AND userreview.MovieID = ?");

            joinStatement.setInt(1, movieID);
            joinStatement.setInt(2, movieID);
            resJoin = joinStatement.executeQuery();
            resJoin.next();
            review = resJoin.getString("review");
            rate = resJoin.getInt("userrating");
            uID = resJoin.getInt("userid");

            //get count of reviews
            PreparedStatement getCount = con.prepareStatement("SELECT count(*) FROM userreview"
                    + " INNER JOIN userrating ON userrating.UserID = userreview.UserID"
                    + " WHERE userrating.MovieID = ? AND userreview.MovieID = ?");
            getCount.setInt(1, movieID);
            getCount.setInt(2, movieID);
            res = getCount.executeQuery();
            res.next();
            count = res.getInt("COUNT(*)");

            //set review,rate,count and currentRevPage
            jTextAreaViewReview.setText(review);
            jtextUserRate.setText(Integer.toString(rate));
            jLabelReviewCount.setText(Integer.toString(count));
            currentRevPage = 1;
            jLabelCurrentReview.setText(Integer.toString(currentRevPage));

            //get username from user table
            fetchNameStatement = con.prepareStatement("SELECT name FROM users WHERE id = ?");
            fetchNameStatement.setInt(1, uID);
            resName = fetchNameStatement.executeQuery();

            //set name of user
            resName.next();
            name = resName.getString("name");
            jtextUserName.setText(name);
            this.LoadPanel(reviewRatingViewP);
        } catch (SQLException e) {
            e.printStackTrace();

            jTextAreaViewReview.setText("No one has reviewd this movie yet, be the first to.");
            jtextUserRate.setText("X");
            jtextUserName.setText("XXXX");
            jLabelReviewCount.setText("MAX");
            jLabelCurrentReview.setText("1");
            this.LoadPanel(reviewRatingViewP);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Please select a movie to view its reviews",
                    "Selection error",
                    JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_revRateViewBActionPerformed

    private void revRateSubmitBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revRateSubmitBActionPerformed
        try {
            movieIdL.setText(((String) jmovieTable.getValueAt(jmovieTable.getSelectedRow(), 0)));
            this.LoadPanel(reviewRatingSubmitP);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Please select a movie to review",
                    "Selection error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_revRateSubmitBActionPerformed

    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmitActionPerformed

        PreparedStatement mystatement;
        String review, username, logMessage;
        int movieID, userID, rate;

        movieID = Integer.parseInt(movieIdL.getText());
        userID = Integer.parseInt(userIdL.getText().substring(9));
        username = userNameL.getText().substring(10);
        rate = jComboBoxRate.getSelectedIndex();
        review = jTextAreaSubmitReview.getText();
        if (rate == 0) {
            JOptionPane.showMessageDialog(null,
                    "Please select a rating",
                    "Please select a value",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (review.equals("")) {

            JOptionPane.showMessageDialog(null,
                    "Please write a review",
                    "Empty review isn't accepeted",
                    JOptionPane.INFORMATION_MESSAGE);

        }

        if (rate != 0 && !review.equals("")) {

            try {
                //check if review already exists by counting if a row exists

                mystatement = con.prepareStatement("SELECT COUNT(*) FROM userreview  WHERE movieid = ? AND userid = ?");
                mystatement.setInt(1, movieID);
                mystatement.setInt(2, userID);
                res = mystatement.executeQuery();
                res.next();
                int count = res.getInt("COUNT(*)");
                System.out.println(count);
                if (count != 0) {

                    mystatement = con.prepareStatement("UPDATE userrating SET userrating = ? WHERE movieid = ? AND userid = ?");
                    mystatement.setInt(1, rate);
                    mystatement.setInt(2, movieID);
                    mystatement.setInt(3, userID);
                    mystatement.execute();
                    System.out.println("Success 1");

                    logMessage = "User: " + username + " with User ID = " + userID + " has updated rating for a movie with movieId = " + movieID;
                    insertReviewRateSystemLogs(userID, movieID, "Update", logMessage);

                    mystatement = con.prepareStatement("UPDATE userreview SET review = ? WHERE movieid = ? AND userid = ?");
                    mystatement.setString(1, review);
                    mystatement.setInt(2, movieID);
                    mystatement.setInt(3, userID);
                    mystatement.execute();

                    logMessage = "User: " + username + " with User ID = " + userID + " has updated review for a movie with movieId = " + movieID;
                    mystatement = con.prepareStatement("INSERT INTO system_logs (ID, UserID, AdminID, movieID, TimeStamp, Operation, LogMessage) Values(DEFAULT, ?, DEFAULT, ?, ?, ?, ?)");
                    insertReviewRateSystemLogs(userID, movieID, "Update", logMessage);

                    JOptionPane.showMessageDialog(null,
                            "Success!",
                            "Review and rate have been updated.",
                            JOptionPane.INFORMATION_MESSAGE);

                } else {
                    //insert rate into userrate table
                    mystatement = con.prepareStatement("INSERT INTO userrating (id,movieid,userid,userrating) VALUES(DEFAULT,?,?,?)");
                    mystatement.setInt(1, movieID);
                    mystatement.setInt(2, userID);
                    mystatement.setInt(3, rate);
                    mystatement.execute();

                    //System log
                    logMessage = "User: " + username + " with User ID = " + userID + " has added rating for a movie with movieId = " + movieID;
                    insertReviewRateSystemLogs(userID, movieID, "Add", logMessage);

                    //insert review into userreview table
                    mystatement = con.prepareStatement("INSERT INTO userreview (id,movieid,userid,review) VALUES(DEFAULT,?,?,?)");
                    mystatement.setInt(1, movieID);
                    mystatement.setInt(2, userID);
                    mystatement.setString(3, review);
                    mystatement.execute();

                    //System log
                    logMessage = "User: " + username + " with User ID = " + userID + " has added review for a movie with movieId = " + movieID;
                    insertReviewRateSystemLogs(userID, movieID, "Add", logMessage);

                    JOptionPane.showMessageDialog(null,
                            "Success!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();

                JOptionPane.showMessageDialog(null,
                        "Unable to submit",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

            }

        }


    }//GEN-LAST:event_jButtonSubmitActionPerformed

    private void jComboBoxRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxRateActionPerformed

    private void jButtonNextReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextReviewActionPerformed
        //button to get next user rate/review from global result variable resJoin and resName
        try {
            resJoin.next();
            review = resJoin.getString("review");
            rate = resJoin.getInt("userrating");
            uID = resJoin.getInt("userid");

            //set review,rate,count and currentRevPage
            jTextAreaViewReview.setText(review);
            jtextUserRate.setText(Integer.toString(rate));
            if ((currentRevPage + 1) <= count) {
                jLabelCurrentReview.setText(Integer.toString(++currentRevPage));
            }

            //this query has to be re written beacuse it depends on the value taken from the previous query
            fetchNameStatement = con.prepareStatement("SELECT name FROM users WHERE id = ?");
            fetchNameStatement.setInt(1, uID);
            resName = fetchNameStatement.executeQuery();

            //set name of user
            resName.next();
            name = resName.getString("name");
            jtextUserName.setText(name);

        } catch (SQLException e) {

        }
    }//GEN-LAST:event_jButtonNextReviewActionPerformed

    private void jTextAreaSubmitReviewFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextAreaSubmitReviewFocusGained
        jTextAreaSubmitReview.setText("");
    }//GEN-LAST:event_jTextAreaSubmitReviewFocusGained

    private void jtextnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtextnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtextnameActionPerformed

    private void jtextnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtextnameFocusGained
        jtextname.setText("");
    }//GEN-LAST:event_jtextnameFocusGained

    private void jtextgenreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtextgenreFocusGained
        jtextgenre.setText("");
    }//GEN-LAST:event_jtextgenreFocusGained

    private void jtextratingFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtextratingFocusGained
        jtextrating.setText("");
    }//GEN-LAST:event_jtextratingFocusGained

    private void moviesTableBa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moviesTableBa1ActionPerformed
        try {
            //button to refresh the data in the movie table

            stat = con.createStatement();
            // query to select table
            res = stat.executeQuery("SELECT * FROM system_logs");

            //model to update movie table values
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("UserID");
            model.addColumn("AdminID");
            model.addColumn("MovieID");
            model.addColumn("ActorID");
            model.addColumn("TimeStamp");
            model.addColumn("Opeartion");
            model.addColumn("LogMessage");

            int id;
            int userID;
            int adminID;
            int movieID;
            int actorID;
            String timeStamp;
            String operation;
            String logMessage;

            int row = 0;
            //loop to aquire data from the DB
            while (res.next()) {
                id = res.getInt("id");
                userID = res.getInt("UserID");
                adminID = res.getInt("AdminID");
                movieID = res.getInt("MovieID");
                actorID = res.getInt("actorID");
                timeStamp = res.getString("TimeStamp");
                operation = res.getString("Operation");
                logMessage = res.getString("LogMessage");

                logsArray[row][0] = String.valueOf(id);
                logsArray[row][1] = String.valueOf(userID);
                logsArray[row][2] = String.valueOf(adminID);
                logsArray[row][3] = String.valueOf(movieID);
                logsArray[row][4] = String.valueOf(actorID);
                logsArray[row][5] = String.valueOf(timeStamp);
                logsArray[row][6] = String.valueOf(operation);
                logsArray[row][7] = String.valueOf(logMessage);

                model.addRow(logsArray[row]);
                row++;
            }
            //set model to table

            jlogTable.setModel(model);
            //set prefered width size
            jlogTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            jlogTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            jlogTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            jlogTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            jlogTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            jlogTable.getColumnModel().getColumn(5).setPreferredWidth(150);
            jlogTable.getColumnModel().getColumn(6).setPreferredWidth(100);
            jlogTable.getColumnModel().getColumn(7).setPreferredWidth(750);

        } catch (Exception e) {
            e.printStackTrace();
        }
        jlogTable.setAutoCreateRowSorter(true);

        this.LoadPanel(backGroundP3a);
    }//GEN-LAST:event_moviesTableBa1ActionPerformed

    private void ReportListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportListButtonActionPerformed
        // TODO add your handling code here:
        Statistics.showList();
    }//GEN-LAST:event_ReportListButtonActionPerformed

    private void CreateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateReportActionPerformed
        this.LoadPanel(ReportTypeP);
    }//GEN-LAST:event_CreateReportActionPerformed

    private void OrederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrederActionPerformed
        // TODO add your handling code here:
        Statistics R = new Statistics();
        int ID = Integer.parseInt(adminIdL.getText().substring(10));
        //int ID = 1;
        int Type = ReportType.getSelectedIndex();

        R.orderReport(ID, Type);

    }//GEN-LAST:event_OrederActionPerformed

    private void actorsTablebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actorsTablebuttonActionPerformed
        this.LoadPanel(actors);
    }//GEN-LAST:event_actorsTablebuttonActionPerformed

    private void actorsTablebutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actorsTablebutton1ActionPerformed
        this.LoadPanel(actors);
    }//GEN-LAST:event_actorsTablebutton1ActionPerformed

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        try {
            //button to refresh the data in the movie table

            stat = con.createStatement();
            // query to select table
            res = stat.executeQuery("SELECT * FROM actors");

            //model to update movie table values
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("Date of Birth");
            model.addColumn("Age");
            int id;
            String name;
            String DoB;
            String age;
            int row = 0;
            //loop to aquire data from the DB
            while (res.next()) {
                id = res.getInt("id");
                name = res.getString("name");
                DoB = res.getString("dateOfBirth");
                age = res.getString("age");
                actorArray[row][0] = String.valueOf(id);
                actorArray[row][1] = String.valueOf(name);
                actorArray[row][2] = String.valueOf(DoB);
                actorArray[row][3] = String.valueOf(age);
                model.addRow(actorArray[row]);
                row++;
            }
            //set model to table
            jActorTable.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
        jmovieTable.setAutoCreateRowSorter(true);
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jButtonAddActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActorActionPerformed
        this.LoadPanel(addActor);
    }//GEN-LAST:event_jButtonAddActorActionPerformed

    private void jButtonDeleteSelectedActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteSelectedActorActionPerformed
        //button to delete selected row without entering id works for id set to column 0

        //geting selected id value
        //you have to typecast to String in order to parse int
        //try catch block for when no row is selected
        try {

            int id = Integer.parseInt((String) jActorTable.getValueAt(jActorTable.getSelectedRow(), 0));
            int actorId = id;
            PreparedStatement mystatement;
            try {
                mystatement = con.prepareStatement("DELETE FROM actors WHERE id = ?");
                mystatement.setInt(1, id);
                mystatement.execute();
                
                //System log
                int adminId = Integer.parseInt(adminIdL.getText().substring(10));
                String logMessage = "Admin with adminID = " + adminId + " has delted an actor with actorId = " + actorId;
                insertActorSystemLogs(adminId, actorId, "Delete", logMessage);
                JOptionPane.showMessageDialog(null,
                        "Success",
                        "Successfully Deleted",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                Logger.getLogger(MovieTable.class.getName()).log(Level.SEVERE, null, ex);

            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Please select a row to delete",
                    "Selection error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonDeleteSelectedActorActionPerformed

    private void jTextSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextSearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextSearch1ActionPerformed

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        String search = jTextSearch.getText();

        ResultSet res;

        try {

            // myStatement = con.prepareStatement("SELECT * FROM movies WHERE name LIKE ?'%'");
            //  myStatement.setString(1, search);
            // res=  myStatement.executeQuery();
            stat = con.createStatement();
            res = stat.executeQuery("SELECT * FROM actors WHERE name LIKE '" + search + "%'");

            //model to update movie table values
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("Date of Birth");
            model.addColumn("Age");
            int id;
            String name;
            String DoB;
            String age;
            int row = 0;
            //loop to aquire data from the DB
            while (res.next()) {
                id = res.getInt("id");
                name = res.getString("name");
                DoB = res.getString("dateOfBirth");
                age = res.getString("age");
                actorArray[row][0] = String.valueOf(id);
                actorArray[row][1] = String.valueOf(name);
                actorArray[row][2] = String.valueOf(DoB);
                actorArray[row][3] = String.valueOf(age);
                model.addRow(actorArray[row]);
                row++;
            }
            //set model to table
            jActorTable.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(MovieTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jButtonAddActor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActor1ActionPerformed
        String name = jtextName.getText();
        String DoB = jtextDate.getText();

        //check if values are null/empty
        if ((name.isEmpty() || name == null) || DoB.isEmpty() || DoB == null) {

            //display error message
            JOptionPane.showMessageDialog(null,
                    "Values can't be set to empty or null!",
                    "Empty/Null Error!",
                    JOptionPane.ERROR_MESSAGE);
        } else if (name.length() > 45) {
            JOptionPane.showMessageDialog(null,
                    "Name can't exceed 45 characters",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {

            try {

                // query to insert data into movie table
                int actorId = 0;
                int adminId = 0;

                //get time
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                System.out.println(date);
                String dateString = String.valueOf(date);

                PreparedStatement myStatement = con.prepareStatement("INSERT INTO actors (id,name,dateOfBirth,age) VALUES(DEFAULT,?,?,FLOOR(DATEDIFF( ?, ?)/365))");
                myStatement.setString(1, name);
                myStatement.setString(2, DoB);
                myStatement.setString(3, dateString);
                myStatement.setString(4, DoB);
                myStatement.execute();
                System.out.println("done1");

                //code for system logs.
                stat = con.createStatement();
                res = stat.executeQuery("SELECT ID FROM actors WHERE name = '" + name + "'");
                if (res.next()) {
                    actorId = res.getInt("ID");
                }
                System.out.println("done2");
                // Write the logs in the table
                adminId = Integer.parseInt(adminIdL.getText().substring(10));
                String logMessage = "Admin with adminID = " + adminId + " has added an actor with actorId = " + actorId;
                insertActorSystemLogs(adminId, actorId, "Add", logMessage);
                JOptionPane.showMessageDialog(null,
                        "Success",
                        "Successfully Added",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (MysqlDataTruncation e) {
                JOptionPane.showMessageDialog(null,
                        "Error",
                        "Enter a valid date of birth",
                        JOptionPane.ERROR_MESSAGE);

                e.printStackTrace();

            } catch (SQLException ex) {
                ex.printStackTrace();

                JOptionPane.showMessageDialog(null,
                        "Unable to submit",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        }
    }//GEN-LAST:event_jButtonAddActor1ActionPerformed

    private void jtextDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtextDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtextDateActionPerformed

    private void jtextDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtextDateFocusGained
        jtextDate.setText("");
    }//GEN-LAST:event_jtextDateFocusGained

    private void jtextNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtextNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtextNameActionPerformed

    private void jtextNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtextNameFocusGained
        jtextName.setText("");
    }//GEN-LAST:event_jtextNameFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CreateReport;
    private javax.swing.JButton Oreder;
    private javax.swing.JButton ReportListButton;
    private javax.swing.JComboBox<String> ReportType;
    private javax.swing.JPanel ReportTypeP;
    public javax.swing.JPanel UserPanel;
    private javax.swing.JPanel actors;
    private javax.swing.JButton actorsTablebutton;
    private javax.swing.JButton actorsTablebutton1;
    private javax.swing.JPanel addActor;
    private javax.swing.JButton addDeleteB;
    private javax.swing.JPanel addMoviesP;
    private javax.swing.JLabel adminIcon;
    private javax.swing.JLabel adminIdL;
    private javax.swing.JLabel adminNameL;
    public javax.swing.JPanel adminPanel;
    public javax.swing.JPanel backGroundP;
    private javax.swing.JPanel backGroundP3a;
    private javax.swing.JLayeredPane base1;
    private javax.swing.JLayeredPane contentBase;
    private javax.swing.JButton deleteMovieB;
    private javax.swing.JTable jActorTable;
    private javax.swing.JButton jButtonAddActor;
    private javax.swing.JButton jButtonAddActor1;
    private javax.swing.JButton jButtonDeleteSelectedActor;
    private javax.swing.JButton jButtonNextReview;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonSubmit;
    private javax.swing.JComboBox<String> jComboBoxRate;
    private javax.swing.JLabel jLabeSlash;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCurrentReview;
    private javax.swing.JLabel jLabelRate;
    private javax.swing.JLabel jLabelRate1;
    private javax.swing.JLabel jLabelReview;
    private javax.swing.JLabel jLabelReview1;
    private javax.swing.JLabel jLabelReviewCount;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPaneSubmit;
    private javax.swing.JScrollPane jScrollPaneView;
    private javax.swing.JButton jSearch;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextArea jTextAreaSubmitReview;
    private javax.swing.JTextArea jTextAreaViewReview;
    private javax.swing.JTextField jTextSearch;
    private javax.swing.JTextField jTextSearch1;
    private javax.swing.JButton jadd;
    private javax.swing.JTable jlogTable;
    private javax.swing.JTable jmovieTable;
    private javax.swing.JTextField jtextDate;
    private javax.swing.JTextField jtextName;
    private javax.swing.JTextField jtextUserName;
    private javax.swing.JTextField jtextUserRate;
    private javax.swing.JTextField jtextgenre;
    private javax.swing.JTextField jtextname;
    private javax.swing.JTextField jtextrating;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel movieIdL;
    private javax.swing.JPanel movieTableP;
    private javax.swing.JButton moviesTableB;
    private javax.swing.JButton moviesTableBa;
    private javax.swing.JButton moviesTableBa1;
    private javax.swing.JButton refreshB;
    private javax.swing.JButton revRateSubmitB;
    private javax.swing.JButton revRateViewB;
    private javax.swing.JPanel reviewRatingSubmitP;
    private javax.swing.JPanel reviewRatingViewP;
    private javax.swing.JPanel secondaryPanel;
    private javax.swing.JButton signOutButton;
    private javax.swing.JButton signOutButton1;
    private javax.swing.JLabel userIcon;
    private javax.swing.JLabel userIdL;
    private javax.swing.JLabel userNameL;
    // End of variables declaration//GEN-END:variables
}
