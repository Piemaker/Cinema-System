
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author OSM
 */
public class MovieTable extends javax.swing.JFrame {

    Connection con;
    Statement stat;
    ResultSet res;
    String[][] movieArray = new String[100][4];

    public MovieTable() {
        initComponents();
        getConnection(); 
        //change color of jtable headers
        JTableHeader anHeader = jmovieTable.getTableHeader();
            anHeader.setForeground(new java.awt.Color(187, 187, 187));
            anHeader.setBackground(new java.awt.Color(75, 75, 75));

    }

    // establishes connection with the DB
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

    //closes the current window only
    public void close() {

        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jmovieTable = new javax.swing.JTable();
        jaddDelete = new javax.swing.JButton();
        jrefresh1 = new javax.swing.JButton();
        jdelete = new javax.swing.JButton();
        jTextSearch = new javax.swing.JTextField();
        jSearch = new javax.swing.JButton();
        revRateViewB = new javax.swing.JButton();
        revRateSubmitB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(43, 43, 43));

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
        jScrollPane1.setViewportView(jmovieTable);
        if (jmovieTable.getColumnModel().getColumnCount() > 0) {
            jmovieTable.getColumnModel().getColumn(0).setResizable(false);
            jmovieTable.getColumnModel().getColumn(0).setPreferredWidth(20);
            jmovieTable.getColumnModel().getColumn(1).setResizable(false);
            jmovieTable.getColumnModel().getColumn(1).setPreferredWidth(220);
            jmovieTable.getColumnModel().getColumn(2).setResizable(false);
            jmovieTable.getColumnModel().getColumn(2).setPreferredWidth(50);
            jmovieTable.getColumnModel().getColumn(3).setResizable(false);
            jmovieTable.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

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

        jrefresh1.setBackground(new java.awt.Color(75, 75, 75));
        jrefresh1.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jrefresh1.setForeground(new java.awt.Color(200, 200, 200));
        jrefresh1.setText("Add/Delete Movie");
        jrefresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrefresh1ActionPerformed(evt);
            }
        });

        jdelete.setBackground(new java.awt.Color(75, 75, 75));
        jdelete.setForeground(new java.awt.Color(200, 200, 200));
        jdelete.setText("Delete Selected");
        jdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdeleteActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jaddDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jrefresh1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addComponent(revRateViewB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(revRateSubmitB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextSearch)
                            .addComponent(jSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jaddDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(revRateSubmitB, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(revRateViewB, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jrefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrefresh1ActionPerformed
        close();
        AddDeleteMovie addDeleteWindow = new AddDeleteMovie();
        addDeleteWindow.setVisible(true);

    }//GEN-LAST:event_jrefresh1ActionPerformed

    private void jaddDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jaddDeleteActionPerformed
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
       
    }//GEN-LAST:event_jaddDeleteActionPerformed

    private void jdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdeleteActionPerformed
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


    }//GEN-LAST:event_jdeleteActionPerformed

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
        // TODO add your handling code here:
    }//GEN-LAST:event_revRateViewBActionPerformed

    private void revRateSubmitBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revRateSubmitBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_revRateSubmitBActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MovieTable().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jSearch;
    private javax.swing.JTextField jTextSearch;
    private javax.swing.JButton jaddDelete;
    private javax.swing.JButton jdelete;
    private javax.swing.JTable jmovieTable;
    private javax.swing.JButton jrefresh1;
    private javax.swing.JButton revRateSubmitB;
    private javax.swing.JButton revRateViewB;
    // End of variables declaration//GEN-END:variables

}
