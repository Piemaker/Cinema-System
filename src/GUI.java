
import com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author OSM
 */
public class GUI extends javax.swing.JFrame {
    
    Connection con;
    Statement stat;
    ResultSet res;
    String[][] movieArray = new String[100][4];
    
    public GUI() {
        initComponents();
        getConnection();
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
     public void close(){
 
 WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
 Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
 
 }
     
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jmovieTable = new javax.swing.JTable();
        jaddDelete = new javax.swing.JButton();
        jrefresh1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jmovieTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Genre", "FilmRating"
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
        jScrollPane1.setViewportView(jmovieTable);

        jaddDelete.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jaddDelete.setText("Refresh");
        jaddDelete.setActionCommand("");
        jaddDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jaddDeleteActionPerformed(evt);
            }
        });

        jrefresh1.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jrefresh1.setText("Add/Delete Movie");
        jrefresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrefresh1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jrefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jaddDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jaddDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        

    }//GEN-LAST:event_jaddDeleteActionPerformed

    private void jrefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrefresh1ActionPerformed
        close();
        addDelete addDeleteWindow = new addDelete();
     addDeleteWindow.setVisible(true);
     
    }//GEN-LAST:event_jrefresh1ActionPerformed
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jaddDelete;
    private javax.swing.JTable jmovieTable;
    private javax.swing.JButton jrefresh1;
    // End of variables declaration//GEN-END:variables

}
