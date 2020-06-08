
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
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

void getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema", "Mohab", "qwa220zxs18MN313");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("connected");
    }

public void close() {

        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

    }
    
final void initializations()
{
// for user review/rating view
    jtextUserName.setBackground(new java.awt.Color(0, 0, 0, 1));
    jtextUserRate.setBackground(new java.awt.Color(0, 0, 0, 1));
    jScrollPane1.setOpaque(false);
    jScrollPane1.getViewport().setOpaque(false);
    jScrollPane1.setBorder(null);
    jScrollPane1.setViewportBorder(null);
    jTextAreaReview.setBorder(null);
    jTextAreaReview.setBackground(new java.awt.Color(0, 0, 0, 1));

    jTextAreaReview.setLineWrap(true); //for adding new line instead of scrolling
    jTextAreaReview.setWrapStyleWord(true);
    
//for user review/rating submit
    jScrollPane1.setOpaque(false);
    jScrollPane1.getViewport().setOpaque(false);
    jScrollPane1.setBorder(null);
    jScrollPane1.setViewportBorder(null);
    jTextAreaReview.setBorder(null);
    jTextAreaReview.setBackground(new java.awt.Color(0, 0, 0, 1));

    jTextAreaReview.setLineWrap(true); //for adding new line instead of scrolling
    jTextAreaReview.setWrapStyleWord(true);
    
//for add/delete movies
    jtextname.setBackground(new java.awt.Color(0, 0, 0, 1));
    jtextgenre.setBackground(new java.awt.Color(0, 0, 0, 1));

    jtextrating.setBackground(new java.awt.Color(0, 0, 0, 1));

    jtextid.setBackground(new java.awt.Color(0, 0, 0, 1));
    
//for moviesTable
    JTableHeader anHeader = jmovieTable.getTableHeader();
    anHeader.setForeground(new java.awt.Color(187, 187, 187));
    anHeader.setBackground(new java.awt.Color(75, 75, 75));
}
    
 public void LoadControlPanel(JPanel panel, String username, int id, int userType)
    {
        base1.removeAll();
        base1.add(panel);
        base1.repaint();
        base1.revalidate();
        if(userType == 50)  // 50 refers to regular user
        {
            userNameL.setText("Username: "+username);
            userIdL.setText("User ID: "+id);
            addDeleteB.setVisible(false);
            deleteMovieB.setVisible(false);
        }
        else if(userType == 100) // 100 refers to admin user
        {
            adminNameL.setText("Adminname: "+username);
            adminIdL.setText("Admin ID: "+id);
            revRateSubmitB.setVisible(false);
            revRateViewB.setVisible(false);
        }
       
        
    }
 
 
public void LoadPanel(JPanel panel)
    {
        contentBase.removeAll();
        contentBase.add(panel);
        contentBase.repaint();
        contentBase.revalidate();
    }         

public void removePanels()
{
    contentBase.removeAll();
}
 public class costumPanel extends JPanel
    {
        private BufferedImage image;

        public costumPanel() {
            try 
            {                
                image = ImageIO.read(getClass().getResourceAsStream("/Images/gradient-red-linear-black-1366x768-c2-8b0000-000000-a-270-f-14.png"));
            } 
            catch (Exception ex) 
            {
                System.out.println(ex.toString());
            }
    }
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
            
        }
    }
 
 static public String getDateTime()
   {
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
        UserPanel = new javax.swing.JPanel();
        userIcon = new javax.swing.JLabel();
        moviesTableB = new javax.swing.JButton();
        signOutButton1 = new javax.swing.JButton();
        userNameL = new javax.swing.JLabel();
        userIdL = new javax.swing.JLabel();
        secondaryPanel = new javax.swing.JPanel();
        contentBase = new javax.swing.JLayeredPane();
        backGroundP = new javax.swing.JPanel();
        movieTableP = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jmovieTable = new javax.swing.JTable();
        jaddDelete = new javax.swing.JButton();
        addDeleteB = new javax.swing.JButton();
        deleteMovieB = new javax.swing.JButton();
        jTextSearch = new javax.swing.JTextField();
        jSearch = new javax.swing.JButton();
        revRateViewB = new javax.swing.JButton();
        revRateSubmitB = new javax.swing.JButton();
        backGroundPa = new javax.swing.JPanel();
        addDeleteMoviesP = new javax.swing.JPanel();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jtextname = new javax.swing.JTextField();
        jtextrating = new javax.swing.JTextField();
        jtextgenre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jdelete = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jtextid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jadd = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        backGroundP1 = new javax.swing.JPanel();
        reviewRatingSubmitP = new javax.swing.JPanel();
        jLabelReview = new javax.swing.JLabel();
        jLabelRate = new javax.swing.JLabel();
        jComboBoxRate = new javax.swing.JComboBox<>();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jButtonSubmit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaReview = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        backGroundP2 = new javax.swing.JPanel();
        reviewRatingViewP = new javax.swing.JPanel();
        jLabelReview1 = new javax.swing.JLabel();
        jLabelReviewCount = new javax.swing.JLabel();
        jLabeCurrentReview = new javax.swing.JLabel();
        jLabeSlash = new javax.swing.JLabel();
        jLabelRate1 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jButtonViewReviews = new javax.swing.JButton();
        jButtonNextReview = new javax.swing.JButton();
        jtextUserName = new javax.swing.JTextField();
        jtextUserRate = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaReview1 = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(0, 255, 0));

        base1.setLayout(new java.awt.CardLayout());

        adminPanel.setBackground(new java.awt.Color(51, 51, 51));

        adminIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/admin.png"))); // NOI18N

        moviesTableBa.setBackground(new java.awt.Color(255, 153, 0));
        moviesTableBa.setText("Movies Table");
        moviesTableBa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moviesTableBaActionPerformed(evt);
            }
        });

        signOutButton.setBackground(new java.awt.Color(255, 153, 0));
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
                            .addComponent(signOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(moviesTableBa, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(adminPanelLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(adminIdL, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(adminNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))))
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
                .addGap(77, 77, 77)
                .addComponent(moviesTableBa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(signOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(211, Short.MAX_VALUE))
        );

        base1.add(adminPanel, "card3");

        UserPanel.setBackground(new java.awt.Color(51, 51, 51));

        userIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Userm.png"))); // NOI18N

        moviesTableB.setBackground(new java.awt.Color(255, 153, 0));
        moviesTableB.setText("Movies Table");
        moviesTableB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moviesTableBActionPerformed(evt);
            }
        });

        signOutButton1.setBackground(new java.awt.Color(255, 153, 0));
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
                .addGap(99, 99, 99)
                .addComponent(signOutButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
        );

        base1.add(UserPanel, "card2");

        secondaryPanel.setBackground(new java.awt.Color(255, 255, 0));

        contentBase.setLayout(new java.awt.CardLayout());

        backGroundP.setBackground(new java.awt.Color(51, 255, 204));

        movieTableP.setBackground(new java.awt.Color(43, 43, 43));

        jmovieTable.setBackground(new java.awt.Color(75, 75, 75));
        jmovieTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jmovieTable.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jmovieTable.setForeground(new java.awt.Color(200, 200, 200));
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

        jaddDelete.setBackground(new java.awt.Color(75, 75, 75));
        jaddDelete.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jaddDelete.setForeground(new java.awt.Color(200, 200, 200));
        jaddDelete.setText("Refresh");
        jaddDelete.setActionCommand("");
        jaddDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jaddDeleteActionPerformed(evt);
            }
        });

        addDeleteB.setBackground(new java.awt.Color(75, 75, 75));
        addDeleteB.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        addDeleteB.setForeground(new java.awt.Color(200, 200, 200));
        addDeleteB.setText("Add/Delete Movie");
        addDeleteB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDeleteBActionPerformed(evt);
            }
        });

        deleteMovieB.setBackground(new java.awt.Color(75, 75, 75));
        deleteMovieB.setForeground(new java.awt.Color(200, 200, 200));
        deleteMovieB.setText("Delete Selected");
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
        jSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchActionPerformed(evt);
            }
        });

        revRateViewB.setBackground(new java.awt.Color(75, 75, 75));
        revRateViewB.setForeground(new java.awt.Color(200, 200, 200));
        revRateViewB.setText("Review/Rating view");
        revRateViewB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revRateViewBActionPerformed(evt);
            }
        });

        revRateSubmitB.setBackground(new java.awt.Color(75, 75, 75));
        revRateSubmitB.setForeground(new java.awt.Color(200, 200, 200));
        revRateSubmitB.setText("Review/Rating submit");
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(movieTablePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(movieTablePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(revRateSubmitB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, movieTablePLayout.createSequentialGroup()
                            .addComponent(jTextSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jaddDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addDeleteB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(revRateViewB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(deleteMovieB, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        movieTablePLayout.setVerticalGroup(
            movieTablePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movieTablePLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(movieTablePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(movieTablePLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(movieTablePLayout.createSequentialGroup()
                        .addGroup(movieTablePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextSearch)
                            .addComponent(jSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jaddDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(revRateSubmitB, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(revRateViewB, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addDeleteB, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteMovieB, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
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

        backGroundPa.setBackground(new java.awt.Color(153, 153, 0));

        addDeleteMoviesP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator12.setBackground(new java.awt.Color(200, 200, 200));
        addDeleteMoviesP.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 320, 10));

        jSeparator13.setBackground(new java.awt.Color(200, 200, 200));
        addDeleteMoviesP.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 320, 10));

        jSeparator4.setBackground(new java.awt.Color(200, 200, 200));
        jSeparator4.setForeground(new java.awt.Color(204, 204, 204));
        addDeleteMoviesP.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 320, -1));

        jSeparator3.setBackground(new java.awt.Color(200, 200, 200));
        addDeleteMoviesP.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 320, -1));

        jtextname.setForeground(new java.awt.Color(255, 255, 255));
        jtextname.setBorder(null);
        jtextname.setOpaque(false);
        addDeleteMoviesP.add(jtextname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 320, 20));

        jtextrating.setForeground(new java.awt.Color(255, 255, 255));
        jtextrating.setBorder(null);
        jtextrating.setOpaque(false);
        jtextrating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextratingActionPerformed(evt);
            }
        });
        addDeleteMoviesP.add(jtextrating, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 320, 20));

        jtextgenre.setForeground(new java.awt.Color(255, 255, 255));
        jtextgenre.setBorder(null);
        jtextgenre.setOpaque(false);
        jtextgenre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextgenreActionPerformed(evt);
            }
        });
        addDeleteMoviesP.add(jtextgenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 320, 20));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Genre");
        addDeleteMoviesP.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 92, -1));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Rating");
        addDeleteMoviesP.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 92, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Name");
        addDeleteMoviesP.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 92, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Delete Movie");
        addDeleteMoviesP.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, 48));

        jdelete.setBackground(new java.awt.Color(115, 0, 0));
        jdelete.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jdelete.setForeground(new java.awt.Color(200, 200, 200));
        jdelete.setText("DELETE");
        jdelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdeleteActionPerformed(evt);
            }
        });
        addDeleteMoviesP.add(jdelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 470, 180, 40));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Add Movie");
        addDeleteMoviesP.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 16, 138, 73));

        jtextid.setForeground(new java.awt.Color(255, 255, 255));
        jtextid.setBorder(null);
        jtextid.setOpaque(false);
        addDeleteMoviesP.add(jtextid, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 320, 20));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("ID");
        addDeleteMoviesP.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 92, -1));

        jadd.setBackground(new java.awt.Color(115, 0, 0));
        jadd.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jadd.setForeground(new java.awt.Color(200, 200, 200));
        jadd.setText("ADD");
        jadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jaddActionPerformed(evt);
            }
        });
        addDeleteMoviesP.add(jadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 180, 40));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gradient-red-linear-black-1366x768-c2-8b0000-000000-a-270-f-14.png"))); // NOI18N
        jLabel9.setToolTipText("");
        addDeleteMoviesP.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 580));

        javax.swing.GroupLayout backGroundPaLayout = new javax.swing.GroupLayout(backGroundPa);
        backGroundPa.setLayout(backGroundPaLayout);
        backGroundPaLayout.setHorizontalGroup(
            backGroundPaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
            .addGroup(backGroundPaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(backGroundPaLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(addDeleteMoviesP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        backGroundPaLayout.setVerticalGroup(
            backGroundPaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(backGroundPaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(backGroundPaLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(addDeleteMoviesP, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        contentBase.add(backGroundPa, "card3");

        reviewRatingSubmitP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelReview.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelReview.setForeground(new java.awt.Color(255, 255, 255));
        jLabelReview.setText("Write Review");
        reviewRatingSubmitP.add(jLabelReview, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 390, 30));

        jLabelRate.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelRate.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRate.setText("Rating:");
        reviewRatingSubmitP.add(jLabelRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 140, 40));

        jComboBoxRate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBoxRate.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxRate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "(1) Appalling", "(2) Horrible", "(3) Very Bad", "(4) Bad", "(5) Average", "(6) Fine", "(7) Good", "(8) Very Good", "(9) Great", "(10) Masterpiece" }));
        reviewRatingSubmitP.add(jComboBoxRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 170, -1));

        jSeparator8.setBackground(new java.awt.Color(200, 200, 200));
        reviewRatingSubmitP.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 400, -1));

        jSeparator6.setBackground(new java.awt.Color(200, 200, 200));
        reviewRatingSubmitP.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 400, 10));

        jSeparator5.setBackground(new java.awt.Color(200, 200, 200));
        reviewRatingSubmitP.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 400, 10));

        jSeparator7.setBackground(new java.awt.Color(200, 200, 200));
        reviewRatingSubmitP.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 400, 10));

        jButtonSubmit.setBackground(new java.awt.Color(115, 0, 0));
        jButtonSubmit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonSubmit.setForeground(new java.awt.Color(200, 200, 200));
        jButtonSubmit.setText("Submit");
        jButtonSubmit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        reviewRatingSubmitP.add(jButtonSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 520, 180, 40));

        jScrollPane1.setFocusable(false);
        jScrollPane1.setOpaque(false);

        jTextAreaReview.setColumns(20);
        jTextAreaReview.setFont(new java.awt.Font("Palatino Linotype", 3, 20)); // NOI18N
        jTextAreaReview.setForeground(new java.awt.Color(255, 255, 255));
        jTextAreaReview.setLineWrap(true);
        jTextAreaReview.setRows(5);
        jTextAreaReview.setText("Your review here........");
        jTextAreaReview.setBorder(null);
        jTextAreaReview.setHighlighter(null);
        jTextAreaReview.setOpaque(false);
        jScrollPane1.setViewportView(jTextAreaReview);

        reviewRatingSubmitP.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 380, 220));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gradient-red-linear-black-1366x768-c2-8b0000-000000-a-270-f-14.png"))); // NOI18N
        jLabel7.setToolTipText("");
        reviewRatingSubmitP.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 580));

        javax.swing.GroupLayout backGroundP1Layout = new javax.swing.GroupLayout(backGroundP1);
        backGroundP1.setLayout(backGroundP1Layout);
        backGroundP1Layout.setHorizontalGroup(
            backGroundP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
            .addGroup(backGroundP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(backGroundP1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(reviewRatingSubmitP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        backGroundP1Layout.setVerticalGroup(
            backGroundP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(backGroundP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(backGroundP1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(reviewRatingSubmitP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        contentBase.add(backGroundP1, "card4");

        backGroundP2.setBackground(new java.awt.Color(204, 204, 255));

        reviewRatingViewP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelReview1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelReview1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelReview1.setText("Review");
        reviewRatingViewP.add(jLabelReview1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 190, 30));

        jLabelReviewCount.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelReviewCount.setForeground(new java.awt.Color(255, 255, 255));
        jLabelReviewCount.setText("Max");
        reviewRatingViewP.add(jLabelReviewCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 540, -1, 40));

        jLabeCurrentReview.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabeCurrentReview.setForeground(new java.awt.Color(255, 255, 255));
        jLabeCurrentReview.setText("1");
        reviewRatingViewP.add(jLabeCurrentReview, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 540, -1, 40));

        jLabeSlash.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabeSlash.setForeground(new java.awt.Color(255, 255, 255));
        jLabeSlash.setText("/");
        reviewRatingViewP.add(jLabeSlash, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 540, -1, 40));

        jLabelRate1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelRate1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRate1.setText("Rating:");
        reviewRatingViewP.add(jLabelRate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 140, 40));

        jSeparator9.setBackground(new java.awt.Color(200, 200, 200));
        reviewRatingViewP.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 400, -1));

        jSeparator10.setBackground(new java.awt.Color(200, 200, 200));
        reviewRatingViewP.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 400, 10));

        jSeparator11.setBackground(new java.awt.Color(200, 200, 200));
        reviewRatingViewP.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 400, 10));

        jButtonViewReviews.setBackground(new java.awt.Color(115, 0, 0));
        jButtonViewReviews.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonViewReviews.setForeground(new java.awt.Color(200, 200, 200));
        jButtonViewReviews.setText("View Reviews");
        jButtonViewReviews.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        reviewRatingViewP.add(jButtonViewReviews, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, 70, 40));

        jButtonNextReview.setBackground(new java.awt.Color(115, 0, 0));
        jButtonNextReview.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonNextReview.setForeground(new java.awt.Color(200, 200, 200));
        jButtonNextReview.setText("Next Review");
        jButtonNextReview.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        reviewRatingViewP.add(jButtonNextReview, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 510, 180, 40));

        jtextUserName.setEditable(false);
        jtextUserName.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jtextUserName.setForeground(new java.awt.Color(255, 255, 255));
        jtextUserName.setText("User Name");
        jtextUserName.setBorder(null);
        jtextUserName.setOpaque(false);
        reviewRatingViewP.add(jtextUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 370, 30));

        jtextUserRate.setEditable(false);
        jtextUserRate.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jtextUserRate.setForeground(new java.awt.Color(255, 255, 255));
        jtextUserRate.setText("10");
        jtextUserRate.setBorder(null);
        jtextUserRate.setOpaque(false);
        reviewRatingViewP.add(jtextUserRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 50, 40));

        jScrollPane2.setFocusable(false);
        jScrollPane2.setOpaque(false);

        jTextAreaReview1.setEditable(false);
        jTextAreaReview1.setColumns(20);
        jTextAreaReview1.setFont(new java.awt.Font("Palatino Linotype", 3, 20)); // NOI18N
        jTextAreaReview1.setForeground(new java.awt.Color(255, 255, 255));
        jTextAreaReview1.setLineWrap(true);
        jTextAreaReview1.setRows(5);
        jTextAreaReview1.setText("The quick brown fox jumps over the lazy dog whatever now");
        jTextAreaReview1.setBorder(null);
        jTextAreaReview1.setHighlighter(null);
        jTextAreaReview1.setOpaque(false);
        jScrollPane2.setViewportView(jTextAreaReview1);

        reviewRatingViewP.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 380, 220));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gradient-red-linear-black-1366x768-c2-8b0000-000000-a-270-f-14.png"))); // NOI18N
        jLabel8.setToolTipText("");
        reviewRatingViewP.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 580));

        javax.swing.GroupLayout backGroundP2Layout = new javax.swing.GroupLayout(backGroundP2);
        backGroundP2.setLayout(backGroundP2Layout);
        backGroundP2Layout.setHorizontalGroup(
            backGroundP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
            .addGroup(backGroundP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(backGroundP2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(reviewRatingViewP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        backGroundP2Layout.setVerticalGroup(
            backGroundP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(backGroundP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(backGroundP2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(reviewRatingViewP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        contentBase.add(backGroundP2, "card5");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(secondaryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(base1)
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

    private void jdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdeleteActionPerformed
        String idText = jtextid.getText();
        try {

            int id = Integer.parseInt(idText);
            PreparedStatement mystatement = con.prepareStatement("DELETE FROM movies WHERE id = ?");

            mystatement.setInt(1, id);
            mystatement.execute();

            JOptionPane.showMessageDialog(null,
                "Success",
                "Successfully Added",
                JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Enter a numeric rating",
                "Rating error",
                JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(AddDeleteMovie.class.getName()).log(Level.SEVERE, null, ex);

            JOptionPane.showMessageDialog(null,
                "Unable to delete",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jdeleteActionPerformed

    private void jaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jaddActionPerformed
        String name = jtextname.getText();
        String genre = jtextgenre.getText();
        String rating = jtextrating.getText();

        //check if values are null/empty
        if ((name.isEmpty() || name == null) || genre.isEmpty() || genre == null || rating.isEmpty() || rating == null) {

            //display error message
            JOptionPane.showMessageDialog(null,
                "Values can't be set to empty or null!",
                "Empty/Null Error!",
                JOptionPane.ERROR_MESSAGE);
        } else {

            try {
                try {
                    stat = con.createStatement();
                    // query to insert data into movie table

                    double rate = Double.parseDouble(rating);

                    PreparedStatement myStatement = con.prepareStatement("INSERT INTO movies (id,name,genre,rating) VALUES(DEFAULT,?,?,?)");
                    myStatement.setString(1, name);
                    myStatement.setString(2, genre);
                    myStatement.setDouble(3, rate);
                    myStatement.execute();

                    JOptionPane.showMessageDialog(null,
                        "Success",
                        "Successfully Added",
                        JOptionPane.INFORMATION_MESSAGE);

                    // res = stat.executeQuery("INSERT INTO movies (id,name,genre,rating) VALUES ( DEFAULT,'" + name + "','" + genre + "','" + rate + "') ");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                        "Enter a numeric rating",
                        "Rating error",
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jaddActionPerformed

    private void jaddDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jaddDeleteActionPerformed
        /*try {
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
        jmovieTable.setAutoCreateRowSorter(true);*/

    }//GEN-LAST:event_jaddDeleteActionPerformed

    private void addDeleteBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDeleteBActionPerformed
        this.LoadPanel(backGroundPa);
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
                JOptionPane.showMessageDialog(null,
                    "Success",
                    "Successfully Added",
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
       /* String search = jTextSearch.getText();
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
        }*/
    }//GEN-LAST:event_jSearchActionPerformed

    private void revRateViewBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revRateViewBActionPerformed
        this.LoadPanel(backGroundP2);
    }//GEN-LAST:event_revRateViewBActionPerformed

    private void revRateSubmitBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revRateSubmitBActionPerformed
        this.LoadPanel(backGroundP1);
    }//GEN-LAST:event_revRateSubmitBActionPerformed

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
    public javax.swing.JPanel UserPanel;
    private javax.swing.JButton addDeleteB;
    private javax.swing.JPanel addDeleteMoviesP;
    private javax.swing.JLabel adminIcon;
    private javax.swing.JLabel adminIdL;
    private javax.swing.JLabel adminNameL;
    public javax.swing.JPanel adminPanel;
    public javax.swing.JPanel backGroundP;
    private javax.swing.JPanel backGroundP1;
    private javax.swing.JPanel backGroundP2;
    public javax.swing.JPanel backGroundPa;
    private javax.swing.JLayeredPane base1;
    private javax.swing.JLayeredPane contentBase;
    private javax.swing.JButton deleteMovieB;
    private javax.swing.JButton jButtonNextReview;
    private javax.swing.JButton jButtonSubmit;
    private javax.swing.JButton jButtonViewReviews;
    private javax.swing.JComboBox<String> jComboBoxRate;
    private javax.swing.JLabel jLabeCurrentReview;
    private javax.swing.JLabel jLabeSlash;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelRate;
    private javax.swing.JLabel jLabelRate1;
    private javax.swing.JLabel jLabelReview;
    private javax.swing.JLabel jLabelReview1;
    private javax.swing.JLabel jLabelReviewCount;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jSearch;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextArea jTextAreaReview;
    private javax.swing.JTextArea jTextAreaReview1;
    private javax.swing.JTextField jTextSearch;
    private javax.swing.JButton jadd;
    private javax.swing.JButton jaddDelete;
    private javax.swing.JButton jdelete;
    private javax.swing.JTable jmovieTable;
    private javax.swing.JTextField jtextUserName;
    private javax.swing.JTextField jtextUserRate;
    private javax.swing.JTextField jtextgenre;
    private javax.swing.JTextField jtextid;
    private javax.swing.JTextField jtextname;
    private javax.swing.JTextField jtextrating;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel movieTableP;
    private javax.swing.JButton moviesTableB;
    private javax.swing.JButton moviesTableBa;
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
