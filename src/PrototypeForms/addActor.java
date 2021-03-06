package PrototypeForms;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author OSM
 */
public class addActor extends javax.swing.JFrame {

    /**
     * Creates new form addActor
     */
    public addActor() {
        initComponents();

        //for add actor
        jtextName.setBackground(new java.awt.Color(0, 0, 0, 1));
        jtextDate.setBackground(new java.awt.Color(0, 0, 0, 1));

        getConnection();
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
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema", "Mohab", "qwa220zxs18MN313");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("connected");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addDeleteMoviesP = new javax.swing.JPanel();
        jtextName = new javax.swing.JTextField();
        jtextDate = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jButtonAddActor = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addDeleteMoviesP.setToolTipText("");
        addDeleteMoviesP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        addDeleteMoviesP.add(jtextName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 570, 20));

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
        addDeleteMoviesP.add(jtextDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 570, 20));

        jSeparator4.setBackground(new java.awt.Color(200, 200, 200));
        jSeparator4.setForeground(new java.awt.Color(204, 204, 204));
        addDeleteMoviesP.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 570, -1));

        jSeparator3.setBackground(new java.awt.Color(200, 200, 200));
        addDeleteMoviesP.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 570, -1));

        jButtonAddActor.setBackground(new java.awt.Color(115, 0, 0));
        jButtonAddActor.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonAddActor.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddActor.setText("Add");
        jButtonAddActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActorActionPerformed(evt);
            }
        });
        addDeleteMoviesP.add(jButtonAddActor, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 520, 180, 40));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Date of Birth");
        addDeleteMoviesP.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 130, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Name");
        addDeleteMoviesP.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 92, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Add Actor");
        addDeleteMoviesP.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 138, 73));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gradient-red-linear-black-1366x768-c2-8b0000-000000-a-270-f-14.png"))); // NOI18N
        jLabel9.setToolTipText("");
        jLabel9.setMaximumSize(new java.awt.Dimension(1920, 1080));
        jLabel9.setPreferredSize(new java.awt.Dimension(1920, 1080));
        addDeleteMoviesP.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 650, 620));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(addDeleteMoviesP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(addDeleteMoviesP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActorActionPerformed
        String name = jtextName.getText();
        String DoB = jtextDate.getText();

        //check if values are null/empty
        if ((name.isEmpty() || name == null) || DoB.isEmpty() || DoB == null) {

            //display error message
            JOptionPane.showMessageDialog(null,
                    "Values can't be set to empty or null!",
                    "Empty/Null Error!",
                    JOptionPane.ERROR_MESSAGE);
        } else {

            try {

                // query to insert data into movie table
                int movieId = 0;
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

//                //code for system logs.
//                // first retrieve the id of the inserted movie.
//                stat = con.createStatement();
//                res = stat.executeQuery("SELECT ID FROM movies ORDER BY ID DESC LIMIT 1");
//                if (res.next()) {
//                    movieId = res.getInt("ID");
//                }
//                System.out.println("done2");
//                // Write the logs in the table
//                adminId = Integer.parseInt(adminIdL.getText().substring(10));
//                String logMessage = "Admin with adminID = " + adminId + " has added a movie with movieId = " + movieId;
//                myStatement = con.prepareStatement("INSERT INTO system_logs (ID, UserID, AdminID, movieID, TimeStamp, Operation, LogMessage) Values(DEFAULT, ?, ?, ?, ?, ?, ?)");
//                myStatement.setNull(1, java.sql.Types.INTEGER);
//                myStatement.setjButtonAdd2, adminId);
//                myStatement.setInt(3, movieId);
//                myStatement.setString(4, getDateTime());
//                myStatement.setString(5, "Add");
//                myStatement.setString(6, logMessage);
//                myStatement.execute();
                JOptionPane.showMessageDialog(null,
                        "Success",
                        "Successfully Added",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null,
                        "Error",
                        "Enter a valid date of birth",
                        JOptionPane.ERROR_MESSAGE);

                e1.printStackTrace();
            }
            catch(Exception e){
            e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButtonAddActorActionPerformed

    private void jtextNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtextNameFocusGained
        jtextName.setText("");
    }//GEN-LAST:event_jtextNameFocusGained

    private void jtextNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtextNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtextNameActionPerformed

    private void jtextDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtextDateFocusGained
        jtextDate.setText("");
    }//GEN-LAST:event_jtextDateFocusGained

    private void jtextDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtextDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtextDateActionPerformed

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
            java.util.logging.Logger.getLogger(addActor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addActor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addActor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addActor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addActor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addDeleteMoviesP;
    private javax.swing.JButton jButtonAddActor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jtextDate;
    private javax.swing.JTextField jtextName;
    // End of variables declaration//GEN-END:variables
}
