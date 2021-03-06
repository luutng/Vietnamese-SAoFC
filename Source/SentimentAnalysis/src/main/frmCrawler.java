/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import FBCrawler.FacebookCrawler;

/**
 *
 * @author luu
 */
public class frmCrawler extends javax.swing.JFrame {

    public static String idPage = "262700667105773";
    
    public frmCrawler() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnGetData = new javax.swing.JButton();
        btnShowData = new javax.swing.JButton();
        btnGetComments = new javax.swing.JButton();
        cbPageName = new javax.swing.JComboBox();
        btnRemoveData = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaPostsID = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtaPosts = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crawler");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Get Data"));

        jLabel1.setText("Facebook Page Name");

        btnGetData.setText("GET DATA");
        btnGetData.setEnabled(false);
        btnGetData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetDataActionPerformed(evt);
            }
        });

        btnShowData.setText("SHOW DATA");
        btnShowData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowDataActionPerformed(evt);
            }
        });

        btnGetComments.setText("GET COMMENTS");
        btnGetComments.setEnabled(false);
        btnGetComments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetCommentsActionPerformed(evt);
            }
        });

        cbPageName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VnExpress.net", "VnReview.vn", "Tinhte.vn", "Foody.vn" }));
        cbPageName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPageNameActionPerformed(evt);
            }
        });

        btnRemoveData.setText("DELETE OLD FILE");
        btnRemoveData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cbPageName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 168, Short.MAX_VALUE)
                        .addComponent(btnRemoveData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGetData, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnShowData, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGetComments, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbPageName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGetData)
                    .addComponent(btnGetComments)
                    .addComponent(btnShowData)
                    .addComponent(btnRemoveData)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));

        txtaPostsID.setColumns(20);
        txtaPostsID.setRows(5);
        jScrollPane2.setViewportView(txtaPostsID);

        tabbedPane.addTab("Posts ID", jScrollPane2);

        txtaPosts.setColumns(20);
        txtaPosts.setRows(5);
        jScrollPane3.setViewportView(txtaPosts);

        tabbedPane.addTab("Posts", jScrollPane3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGetDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetDataActionPerformed
        // TODO add your handling code here:
        String dataPosts = FacebookCrawler.getData(idPage, "posts");
        System.out.println(dataPosts.length());
        FacebookCrawler.writeFile(dataPosts, "dataPosts.txt");
        
        String dataPostsID = FacebookCrawler.getDataPosts(idPage, "posts");
        FacebookCrawler.writeFile(dataPostsID, "dataPostsID.txt");
        String postsID = FacebookCrawler.parseJSON("dataPostsID.txt", "id");
        FacebookCrawler.writeFile(postsID, "postsID.txt");
        
        String postsDescription = FacebookCrawler.parseDescription("dataPosts.txt", "description");
        FacebookCrawler.writeFile(postsDescription, "postsDescription.txt");
    }//GEN-LAST:event_btnGetDataActionPerformed

    private void btnShowDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowDataActionPerformed
        txtaPostsID.setText(FacebookCrawler.readFile("postsID.txt"));
        txtaPosts.setText(FacebookCrawler.readFile("postsDescription.txt"));
    }//GEN-LAST:event_btnShowDataActionPerformed

    private void btnGetCommentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetCommentsActionPerformed
        // TODO add your handling code here:
        FacebookCrawler.getComments();
    }//GEN-LAST:event_btnGetCommentsActionPerformed

    private void cbPageNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPageNameActionPerformed
        // TODO add your handling code here:
        String namePage = cbPageName.getSelectedItem().toString();
        switch (namePage) {
            case "VnExpress.net": {
                idPage = FacebookCrawler.getIDPageByName("congdongvnexpress");
                break;            
            }
            case "VnReview.vn": {
                idPage = FacebookCrawler.getIDPageByName("VnReview");
                break;
            }
        }
    }//GEN-LAST:event_cbPageNameActionPerformed

    private void btnRemoveDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveDataActionPerformed
        // TODO add your handling code here:
        FacebookCrawler.deleteOldFile();
        btnGetData.setEnabled(true);
        btnGetComments.setEnabled(true);
    }//GEN-LAST:event_btnRemoveDataActionPerformed

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
            java.util.logging.Logger.getLogger(frmCrawler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCrawler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCrawler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCrawler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCrawler().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGetComments;
    private javax.swing.JButton btnGetData;
    private javax.swing.JButton btnRemoveData;
    private javax.swing.JButton btnShowData;
    private javax.swing.JComboBox cbPageName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTextArea txtaPosts;
    private javax.swing.JTextArea txtaPostsID;
    // End of variables declaration//GEN-END:variables
}
