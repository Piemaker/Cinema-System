
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AddDeleteMovie extends javax.swing.JFrame {

    Connection con;
    Statement stat;
    ResultSet res;

    public AddDeleteMovie() {
        initComponents();
        jtextname.setBackground(new java.awt.Color(0, 0, 0, 1));
        jtextgenre.setBackground(new java.awt.Color(0, 0, 0, 1));

        jtextrating.setBackground(new java.awt.Color(0, 0, 0, 1));

        jtextid.setBackground(new java.awt.Color(0, 0, 0, 1));

        getConnection();
    }

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
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
        jback = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator5.setBackground(new java.awt.Color(200, 200, 200));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 320, 10));

        jSeparator6.setBackground(new java.awt.Color(200, 200, 200));
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 320, 10));

        jSeparator4.setBackground(new java.awt.Color(200, 200, 200));
        jSeparator4.setForeground(new java.awt.Color(204, 204, 204));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 320, -1));

        jSeparator3.setBackground(new java.awt.Color(200, 200, 200));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 320, -1));

        jtextname.setForeground(new java.awt.Color(255, 255, 255));
        jtextname.setBorder(null);
        jtextname.setOpaque(false);
        jPanel2.add(jtextname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 320, 20));

        jtextrating.setForeground(new java.awt.Color(255, 255, 255));
        jtextrating.setBorder(null);
        jtextrating.setOpaque(false);
        jtextrating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextratingActionPerformed(evt);
            }
        });
        jPanel2.add(jtextrating, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 320, 20));

        jtextgenre.setForeground(new java.awt.Color(255, 255, 255));
        jtextgenre.setBorder(null);
        jtextgenre.setOpaque(false);
        jtextgenre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextgenreActionPerformed(evt);
            }
        });
        jPanel2.add(jtextgenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 320, 20));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Genre");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 92, -1));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Rating");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 92, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Name");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 92, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Delete Movie");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, 48));

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
        jPanel2.add(jdelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 470, 180, 40));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Add Movie");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 16, 138, 73));

        jtextid.setForeground(new java.awt.Color(255, 255, 255));
        jtextid.setBorder(null);
        jtextid.setOpaque(false);
        jPanel2.add(jtextid, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 320, 20));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("ID");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 92, -1));

        jadd.setBackground(new java.awt.Color(115, 0, 0));
        jadd.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jadd.setForeground(new java.awt.Color(200, 200, 200));
        jadd.setText("ADD");
        jadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jaddActionPerformed(evt);
            }
        });
        jPanel2.add(jadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 180, 40));

        jback.setBackground(new java.awt.Color(115, 0, 0));
        jback.setForeground(new java.awt.Color(200, 200, 200));
        jback.setText("Back");
        jback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbackActionPerformed(evt);
            }
        });
        jPanel2.add(jback, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 90, 40));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gradient-red-linear-black-1366x768-c2-8b0000-000000-a-270-f-14.png"))); // NOI18N
        jLabel7.setToolTipText("");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 580));

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbackActionPerformed
        close();
        MovieTable tableWindow = new MovieTable();
        tableWindow.setVisible(true);
    }//GEN-LAST:event_jbackActionPerformed

    private void jtextgenreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtextgenreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtextgenreActionPerformed

    private void jtextratingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtextratingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtextratingActionPerformed

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
            java.util.logging.Logger.getLogger(AddDeleteMovie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddDeleteMovie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddDeleteMovie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddDeleteMovie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddDeleteMovie().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JButton jadd;
    private javax.swing.JButton jback;
    private javax.swing.JButton jdelete;
    private javax.swing.JTextField jtextgenre;
    private javax.swing.JTextField jtextid;
    private javax.swing.JTextField jtextname;
    private javax.swing.JTextField jtextrating;
    // End of variables declaration//GEN-END:variables
}
