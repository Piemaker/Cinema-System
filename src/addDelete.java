
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
public class addDelete extends javax.swing.JFrame {

    Connection con;
    Statement stat;
    ResultSet res;

    public addDelete() {
        initComponents();
        getConnection();
    }

    void getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jback = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jback.setText("Back");
        jback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbackActionPerformed(evt);
            }
        });

        jtextrating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextratingActionPerformed(evt);
            }
        });

        jtextgenre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextgenreActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Genre");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Rating");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Name");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setText("DELETE Movie");

        jdelete.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jdelete.setText("DELETE");
        jdelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdeleteActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setText("ADD Movie");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("ID");

        jadd.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jadd.setText("ADD");
        jadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jaddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtextname)
                                    .addComponent(jtextgenre)
                                    .addComponent(jtextrating, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtextid, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jback, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(186, Short.MAX_VALUE)
                    .addComponent(jadd, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(132, 132, 132)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtextname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtextgenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtextrating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(72, 72, 72)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtextid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(78, 78, 78))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jback, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(249, Short.MAX_VALUE)
                    .addComponent(jadd, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(217, 217, 217)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbackActionPerformed
        close();
        GUI tableWindow = new GUI();
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
            Logger.getLogger(addDelete.class.getName()).log(Level.SEVERE, null, ex);

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
            java.util.logging.Logger.getLogger(addDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addDelete().setVisible(true);
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
    private javax.swing.JButton jadd;
    private javax.swing.JButton jback;
    private javax.swing.JButton jdelete;
    private javax.swing.JTextField jtextgenre;
    private javax.swing.JTextField jtextid;
    private javax.swing.JTextField jtextname;
    private javax.swing.JTextField jtextrating;
    // End of variables declaration//GEN-END:variables
}
