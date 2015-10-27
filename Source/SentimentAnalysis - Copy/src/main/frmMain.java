/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author luu
 */
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static main.main.*;
import static SentimentAnalysis.subjectivity.*;
import static SentimentAnalysis.sentiment.*;
import javax.swing.table.DefaultTableModel;

public class frmMain extends javax.swing.JFrame {

    static DefaultTableModel modelDataTest;

    public frmMain() throws IOException {
        initComponents();
        modelDataTest = (DefaultTableModel) tbDataTrain.getModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpTrialData = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDataTrain = new javax.swing.JTable();
        cbSubjectivity = new javax.swing.JCheckBox();
        cbPassive = new javax.swing.JCheckBox();
        cbPositive = new javax.swing.JCheckBox();
        cbNegative = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSentence = new javax.swing.JTextArea();
        btnSentimentAnalysis = new javax.swing.JButton();
        jpCrossValidationAccuracy = new javax.swing.JPanel();
        lbPercentSub = new javax.swing.JLabel();
        lbPercentSen = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jpDatasets = new javax.swing.JPanel();
        cbEducation = new javax.swing.JCheckBox();
        cbMovie = new javax.swing.JCheckBox();
        cbSport = new javax.swing.JCheckBox();
        cbDefault = new javax.swing.JCheckBox();
        btnRunSVM = new javax.swing.JButton();
        btnDictionary = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Main Processing");
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        jpTrialData.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Train"));

        tbDataTrain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Sentences", "Subjectivity", "Sentiment"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDataTrain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDataTrainMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDataTrain);
        if (tbDataTrain.getColumnModel().getColumnCount() > 0) {
            tbDataTrain.getColumnModel().getColumn(0).setMinWidth(50);
            tbDataTrain.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbDataTrain.getColumnModel().getColumn(0).setMaxWidth(50);
            tbDataTrain.getColumnModel().getColumn(2).setMinWidth(75);
            tbDataTrain.getColumnModel().getColumn(2).setPreferredWidth(75);
            tbDataTrain.getColumnModel().getColumn(2).setMaxWidth(75);
            tbDataTrain.getColumnModel().getColumn(3).setMinWidth(75);
            tbDataTrain.getColumnModel().getColumn(3).setPreferredWidth(75);
            tbDataTrain.getColumnModel().getColumn(3).setMaxWidth(75);
        }

        cbSubjectivity.setText("Subjectivity");
        cbSubjectivity.setEnabled(false);

        cbPassive.setText("Passive");
        cbPassive.setEnabled(false);

        cbPositive.setText("Positive");
        cbPositive.setEnabled(false);

        cbNegative.setText("Negative");
        cbNegative.setEnabled(false);

        txtSentence.setEditable(false);
        txtSentence.setColumns(20);
        txtSentence.setLineWrap(true);
        txtSentence.setRows(4);
        txtSentence.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtSentence);

        javax.swing.GroupLayout jpTrialDataLayout = new javax.swing.GroupLayout(jpTrialData);
        jpTrialData.setLayout(jpTrialDataLayout);
        jpTrialDataLayout.setHorizontalGroup(
            jpTrialDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTrialDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTrialDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                    .addGroup(jpTrialDataLayout.createSequentialGroup()
                        .addComponent(cbSubjectivity)
                        .addGap(18, 18, 18)
                        .addComponent(cbPassive)
                        .addGap(18, 18, 18)
                        .addComponent(cbPositive)
                        .addGap(18, 18, 18)
                        .addComponent(cbNegative)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jpTrialDataLayout.setVerticalGroup(
            jpTrialDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTrialDataLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpTrialDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSubjectivity)
                    .addComponent(cbPassive)
                    .addComponent(cbPositive)
                    .addComponent(cbNegative))
                .addContainerGap())
        );

        btnSentimentAnalysis.setText("Sentiment Analysis");
        btnSentimentAnalysis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSentimentAnalysisActionPerformed(evt);
            }
        });

        jpCrossValidationAccuracy.setBorder(javax.swing.BorderFactory.createTitledBorder("Accuracy"));

        lbPercentSub.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        lbPercentSub.setForeground(java.awt.Color.red);
        lbPercentSub.setText("0%");

        lbPercentSen.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        lbPercentSen.setForeground(java.awt.Color.red);
        lbPercentSen.setText("0%");

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel1.setForeground(java.awt.SystemColor.activeCaption);
        jLabel1.setText("Subjectivity");

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel2.setForeground(java.awt.SystemColor.activeCaption);
        jLabel2.setText("Sentiment");

        javax.swing.GroupLayout jpCrossValidationAccuracyLayout = new javax.swing.GroupLayout(jpCrossValidationAccuracy);
        jpCrossValidationAccuracy.setLayout(jpCrossValidationAccuracyLayout);
        jpCrossValidationAccuracyLayout.setHorizontalGroup(
            jpCrossValidationAccuracyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCrossValidationAccuracyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCrossValidationAccuracyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPercentSub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPercentSen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpCrossValidationAccuracyLayout.setVerticalGroup(
            jpCrossValidationAccuracyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCrossValidationAccuracyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPercentSub)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(lbPercentSen)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpDatasets.setBorder(javax.swing.BorderFactory.createTitledBorder("Datasets"));

        cbEducation.setText("Education");
        cbEducation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEducationActionPerformed(evt);
            }
        });

        cbMovie.setText("Movie");
        cbMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMovieActionPerformed(evt);
            }
        });

        cbSport.setText("Sport");
        cbSport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSportActionPerformed(evt);
            }
        });

        cbDefault.setText("Default");
        cbDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDefaultActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpDatasetsLayout = new javax.swing.GroupLayout(jpDatasets);
        jpDatasets.setLayout(jpDatasetsLayout);
        jpDatasetsLayout.setHorizontalGroup(
            jpDatasetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatasetsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDatasetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbEducation)
                    .addComponent(cbMovie)
                    .addComponent(cbSport)
                    .addComponent(cbDefault))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jpDatasetsLayout.setVerticalGroup(
            jpDatasetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatasetsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbDefault)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbEducation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbMovie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbSport)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnRunSVM.setText("Run SVM");
        btnRunSVM.setEnabled(false);
        btnRunSVM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunSVMActionPerformed(evt);
            }
        });

        btnDictionary.setText("Dictionary");
        btnDictionary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDictionaryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpTrialData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpCrossValidationAccuracy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRunSVM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSentimentAnalysis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDictionary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpDatasets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpTrialData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(btnDictionary)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpDatasets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRunSVM)
                        .addGap(18, 18, 18)
                        .addComponent(jpCrossValidationAccuracy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSentimentAnalysis)))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSentimentAnalysisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSentimentAnalysisActionPerformed
        // TODO add your handling code here:
        frmSentimentAnalysis sa = new frmSentimentAnalysis();
        sa.setVisible(true);
    }//GEN-LAST:event_btnSentimentAnalysisActionPerformed

    private void cbEducationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEducationActionPerformed
        try {
            showDataTest("education");
        } catch (IOException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (cbDefault.isSelected()
                || cbEducation.isSelected()
                || cbMovie.isSelected()
                || cbSport.isSelected()) {
            btnRunSVM.setEnabled(true);
        } else {
            modelDataTest.setRowCount(0);
            btnRunSVM.setEnabled(false);
        }
        cbDefault.setSelected(false);
        cbMovie.setSelected(false);
        cbSport.setSelected(false);
    }//GEN-LAST:event_cbEducationActionPerformed

    private void cbSportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSportActionPerformed
        try {
            showDataTest("sport");
        } catch (IOException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (cbDefault.isSelected()
                || cbEducation.isSelected()
                || cbMovie.isSelected()
                || cbSport.isSelected()) {
            btnRunSVM.setEnabled(true);
        } else {
            modelDataTest.setRowCount(0);
            btnRunSVM.setEnabled(false);
        }        
        cbDefault.setSelected(false);
        cbEducation.setSelected(false);
        cbMovie.setSelected(false);
    }//GEN-LAST:event_cbSportActionPerformed

    private void btnRunSVMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunSVMActionPerformed
        if (cbDefault.isSelected()) {
            try {
                copyFile("data\\dataProcessing\\default\\sub\\train.txt", "data\\dataProcessing\\subjectivity\\train\\train.txt");
                copyFile("data\\dataProcessing\\default\\sub\\resultstrain.txt", "data\\dataProcessing\\subjectivity\\train\\resultstrain.txt");
                copyFile("data\\dataProcessing\\default\\sub\\test.txt", "data\\dataProcessing\\subjectivity\\test\\test.txt");
                copyFile("data\\dataProcessing\\default\\sub\\resultstest.txt", "data\\dataProcessing\\subjectivity\\test\\resultstest.txt");

                copyFile("data\\dataProcessing\\default\\sen\\train.txt", "data\\dataProcessing\\sentiment\\train\\train.txt");
                copyFile("data\\dataProcessing\\default\\sen\\resultstrain.txt", "data\\dataProcessing\\sentiment\\train\\resultstrain.txt");
                copyFile("data\\dataProcessing\\default\\sen\\test.txt", "data\\dataProcessing\\sentiment\\test\\test.txt");
                copyFile("data\\dataProcessing\\default\\sen\\resultstest.txt", "data\\dataProcessing\\sentiment\\test\\resultstest.txt");
            } catch (IOException ex) {
                Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (cbEducation.isSelected()) {
            try {
                copyFile("data\\dataProcessing\\education\\sub\\train.txt", "data\\dataProcessing\\subjectivity\\train\\train.txt");
                copyFile("data\\dataProcessing\\education\\sub\\resultstrain.txt", "data\\dataProcessing\\subjectivity\\train\\resultstrain.txt");
                copyFile("data\\dataProcessing\\education\\sub\\test.txt", "data\\dataProcessing\\subjectivity\\test\\test.txt");
                copyFile("data\\dataProcessing\\education\\sub\\resultstest.txt", "data\\dataProcessing\\subjectivity\\test\\resultstest.txt");

                copyFile("data\\dataProcessing\\education\\sen\\train.txt", "data\\dataProcessing\\sentiment\\train\\train.txt");
                copyFile("data\\dataProcessing\\education\\sen\\resultstrain.txt", "data\\dataProcessing\\sentiment\\train\\resultstrain.txt");
                copyFile("data\\dataProcessing\\education\\sen\\test.txt", "data\\dataProcessing\\sentiment\\test\\test.txt");
                copyFile("data\\dataProcessing\\education\\sen\\resultstest.txt", "data\\dataProcessing\\sentiment\\test\\resultstest.txt");
            } catch (IOException ex) {
                Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (cbMovie.isSelected()) {
            try {
                copyFile("data\\dataProcessing\\movie\\sub\\train.txt", "data\\dataProcessing\\subjectivity\\train\\train.txt");
                copyFile("data\\dataProcessing\\movie\\sub\\resultstrain.txt", "data\\dataProcessing\\subjectivity\\train\\resultstrain.txt");
                copyFile("data\\dataProcessing\\movie\\sub\\test.txt", "data\\dataProcessing\\subjectivity\\test\\test.txt");
                copyFile("data\\dataProcessing\\movie\\sub\\resultstest.txt", "data\\dataProcessing\\subjectivity\\test\\resultstest.txt");

                copyFile("data\\dataProcessing\\movie\\sen\\train.txt", "data\\dataProcessing\\sentiment\\train\\train.txt");
                copyFile("data\\dataProcessing\\movie\\sen\\resultstrain.txt", "data\\dataProcessing\\sentiment\\train\\resultstrain.txt");
                copyFile("data\\dataProcessing\\movie\\sen\\test.txt", "data\\dataProcessing\\sentiment\\test\\test.txt");
                copyFile("data\\dataProcessing\\movie\\sen\\resultstest.txt", "data\\dataProcessing\\sentiment\\test\\resultstest.txt");
            } catch (IOException ex) {
                Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (cbSport.isSelected()) {
            try {
                copyFile("data\\dataProcessing\\sport\\sub\\train.txt", "data\\dataProcessing\\subjectivity\\train\\train.txt");
                copyFile("data\\dataProcessing\\sport\\sub\\resultstrain.txt", "data\\dataProcessing\\subjectivity\\train\\resultstrain.txt");
                copyFile("data\\dataProcessing\\sport\\sub\\test.txt", "data\\dataProcessing\\subjectivity\\test\\test.txt");
                copyFile("data\\dataProcessing\\sport\\sub\\resultstest.txt", "data\\dataProcessing\\subjectivity\\test\\resultstest.txt");

                copyFile("data\\dataProcessing\\sport\\sen\\train.txt", "data\\dataProcessing\\sentiment\\train\\train.txt");
                copyFile("data\\dataProcessing\\sport\\sen\\resultstrain.txt", "data\\dataProcessing\\sentiment\\train\\resultstrain.txt");
                copyFile("data\\dataProcessing\\sport\\sen\\test.txt", "data\\dataProcessing\\sentiment\\test\\test.txt");
                copyFile("data\\dataProcessing\\sport\\sen\\resultstest.txt", "data\\dataProcessing\\sentiment\\test\\resultstest.txt");

            } catch (IOException ex) {
                Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            //double perSub = trainSubjectivityIndirect();
            //lbPercentSub.setText((double) Math.round(perSub * 10) / 10 + "%");
            //double perSen = trainSentimentIndirect();
            //lbPercentSen.setText((double) Math.round(perSen * 10) / 10 + "%");
            double dirSub = trainSubjectivityDir();
            lbPercentSub.setText((double) Math.round(dirSub * 10) / 10 + "%");
            double dirSen = trainSentimentDir();
            lbPercentSen.setText((double) Math.round(dirSen * 10) / 10 + "%");
        } catch (IOException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRunSVMActionPerformed

    private void cbMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMovieActionPerformed
        try {
            showDataTest("movie");
        } catch (IOException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (cbDefault.isSelected()
                || cbEducation.isSelected()
                || cbMovie.isSelected()
                || cbSport.isSelected()) {
            btnRunSVM.setEnabled(true);
        } else {
            modelDataTest.setRowCount(0);
            btnRunSVM.setEnabled(false);
        }
        cbDefault.setSelected(false);
        cbEducation.setSelected(false);
        cbSport.setSelected(false);
    }//GEN-LAST:event_cbMovieActionPerformed

    private void btnDictionaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDictionaryActionPerformed
        frmDictionary di = new frmDictionary();
        di.setVisible(true);
    }//GEN-LAST:event_btnDictionaryActionPerformed

    private void tbDataTrainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDataTrainMouseClicked
        try {
            cbNegative.setSelected(false);
            cbPassive.setSelected(false);
            cbPositive.setSelected(false);
            cbSubjectivity.setSelected(false);
            int row = tbDataTrain.getSelectedRow();
            txtSentence.setText(tbDataTrain.getModel().getValueAt(row, 1).toString());
            if (tbDataTrain.getModel().getValueAt(row, 2).toString().equalsIgnoreCase("0")) {
                cbPassive.setSelected(true);
            } else {
                cbSubjectivity.setSelected(true);
                if (tbDataTrain.getModel().getValueAt(row, 3).toString().equalsIgnoreCase("0")) {
                    cbNegative.setSelected(true);
                } else {
                    cbPositive.setSelected(true);
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tbDataTrainMouseClicked

    private void cbDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDefaultActionPerformed
        try {
            showDataTest("default");
        } catch (IOException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (cbDefault.isSelected()
                || cbEducation.isSelected()
                || cbMovie.isSelected()
                || cbSport.isSelected()) {
            btnRunSVM.setEnabled(true);
        } else {
            modelDataTest.setRowCount(0);
            btnRunSVM.setEnabled(false);
        }
        cbEducation.setSelected(false);
        cbMovie.setSelected(false);
        cbSport.setSelected(false);
    }//GEN-LAST:event_cbDefaultActionPerformed

    private String[][] readDataTest(String type) throws FileNotFoundException, IOException {
        int n;
        try (BufferedReader subResults = new BufferedReader(new FileReader("data\\dataProcessing\\" + type + "\\sub\\resultstrain.txt"))) {
            n = 0;
            while (subResults.readLine() != null) {
                n++;
            }
        }
        String[][] arr = new String[n][3];
        String[] arrSub = new String[n];
        String[] arrSen = new String[n];
        try {
            String line = "";
            try (FileReader fr = new FileReader("data\\dataProcessing\\" + type + "\\sub\\train.txt");
                    BufferedReader br = new BufferedReader(fr)) {
                int i = 0;
                while ((line = br.readLine()) != null && i < n) {
                    arr[i][0] = line;
                    i++;
                }
            }
            try (FileReader fr = new FileReader("data\\dataProcessing\\" + type + "\\sub\\resultstrain.txt");
                    BufferedReader br = new BufferedReader(fr)) {
                int i = 0;
                while ((line = br.readLine()) != null && i < n) {
                    arrSub[i] = line;
                    i++;
                }
            }
            try (FileReader fr = new FileReader("data\\dataProcessing\\" + type + "\\sen\\resultstrain.txt");
                    BufferedReader br = new BufferedReader(fr)) {
                int i = 0;
                while ((line = br.readLine()) != null) {
                    arrSen[i] = line;
                    i++;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        int j = 0;
        for (int i = 0; i < arrSub.length; i++) {
            arr[i][1] = arrSub[i];
            if (arr[i][1].equalsIgnoreCase("1")) {
                arr[i][2] = arrSen[j];
                j++;
            } else {
                arr[i][2] = "---";
            }
        }
        j = 0;
        return arr;
    }

    private void showDataTest(String type) throws IOException {
        modelDataTest.setRowCount(0);
        String[][] arr = readDataTest(type);
        for (int i = 0; i < arr.length; i++) {
            modelDataTest.insertRow(modelDataTest.getRowCount(),
                    new Object[]{Integer.toString(i + 1), arr[i][0], arr[i][1], arr[i][2]});
        }
    }

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
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new frmMain().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDictionary;
    private javax.swing.JButton btnRunSVM;
    private javax.swing.JButton btnSentimentAnalysis;
    private javax.swing.JCheckBox cbDefault;
    private javax.swing.JCheckBox cbEducation;
    private javax.swing.JCheckBox cbMovie;
    private javax.swing.JCheckBox cbNegative;
    private javax.swing.JCheckBox cbPassive;
    private javax.swing.JCheckBox cbPositive;
    private javax.swing.JCheckBox cbSport;
    private javax.swing.JCheckBox cbSubjectivity;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpCrossValidationAccuracy;
    private javax.swing.JPanel jpDatasets;
    private javax.swing.JPanel jpTrialData;
    private javax.swing.JLabel lbPercentSen;
    private javax.swing.JLabel lbPercentSub;
    private javax.swing.JTable tbDataTrain;
    private javax.swing.JTextArea txtSentence;
    // End of variables declaration//GEN-END:variables
}
