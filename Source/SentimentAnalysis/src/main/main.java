/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.*;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luu
 */
public class main {

    public static void main(String[] args) {
        //frmAbout.main(args);
        //frmCrawler.main(args);
        //frmDictionary.main(args);
        //frmMain.main(args);
        //frmResults.main(args);
        //frmScreen.main(args);
        //frmSentimentAnalysis.main(args);
        //frmViewData.main(args);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmScreen().setVisible(true);
            }
        });
    }

    static void cleanDataForTrain() {
        cleanData.exFileSentence();
    }

    static void copyFile(String source, String target) throws IOException {
        File src = new File(source);
        File tar = new File(target);
        Files.copy(src.toPath(), tar.toPath(), REPLACE_EXISTING);
    }

    static void copyFileForTrain(String type) throws IOException {
        copyFile("data\\dataProcessing\\" + type + "\\sub\\trainSub", "src\\Data\\trainSub");
        copyFile("data\\dataProcessing\\" + type + "\\sen\\trainSen", "src\\Data\\trainSen");
    }

    static int readSentence(File file, String source) {
        int count = 0;
        if (new File(source + file).exists()) {
            File fileRe = new File(source + file);
            try {
                FileReader fr = new FileReader(fileRe);
                BufferedReader br = new BufferedReader(fr);
                while ((br.readLine()) != null) {
                    count++;
                }
            } catch (IOException | NumberFormatException e) {
            }
        }
        return count;
    }

    static int readResult(File file, String source, String id) {
        int count = 0;
        if (new File(source + file).exists()) {
            File fileRe = new File(source + file);
            String line;
            try {
                FileReader fr = new FileReader(fileRe);
                BufferedReader br = new BufferedReader(fr);
                while ((line = br.readLine()) != null) {
                    if (line.equalsIgnoreCase(id)) {
                        count++;
                    }
                }
            } catch (IOException | NumberFormatException e) {
            }
        }
        return count;
    }

    static void exSentimentFileForSentence() {
        
        String[][] arr = new String[700][2];
        int n = 0;
        if (new File("src\\Data\\outputSub").exists()
                && new File("data\\dataProcessing\\subjectivity\\sen\\senSentences.txt").exists()) {
            File fileRe = new File("src\\Data\\outputSub");
            try {
                String line;
                try (FileReader fr = new FileReader(fileRe);
                        BufferedReader br = new BufferedReader(fr)) {
                    while ((line = br.readLine()) != null) {
                        arr[n][0] = line;
                        n++;
                    }
                    n = 0;
                }
            } catch (IOException | NumberFormatException e) {
            }
            File fileSe = new File("data\\dataProcessing\\subjectivity\\sen\\senSentences.txt");
            try {
                String line;
                try (FileReader fr = new FileReader(fileSe);
                        BufferedReader br = new BufferedReader(fr)) {
                    while ((line = br.readLine()) != null) {
                        arr[n][1] = line;
                        n++;
                    }
                }
            } catch (IOException | NumberFormatException e) {
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data\\dataProcessing\\sentiment\\sen\\sen.txt"))) {
            for (int i = 0; i < n; i++) {
                if (arr[i][0].equalsIgnoreCase("1.0")) {
                    bw.write(arr[i][1] + ".");
                }
            }
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(frmSentimentAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static String checkAttention(double per) {
        if (per >= 85) {
            return "Excellence";
        } else if (per < 85 && per >= 75) {
            return "Good";
        } else if (per < 75 && per >= 65) {
            return "Rather";
        } else if (per < 65 && per >= 50) {
            return "Medium";
        } else if (per < 50 && per >= 35) {
            return "Low";
        } else {
            return "Very low";
        }
    }
}
