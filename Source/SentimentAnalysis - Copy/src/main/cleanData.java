package main;

import FontConveter.FontType;
import java.io.*;
import java.util.regex.*;

public class cleanData {

    /*public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
     exFileSentence();
     taggerProcessing();
     }*/
    static void exFileSentence() {
        try {
            String sent = "";
            try (BufferedReader inputStream = new BufferedReader(new FileReader("data\\dataProcessing\\subjectivity\\sen\\senTemp.txt"));
                    BufferedWriter outputStream = new BufferedWriter(new FileWriter("data\\dataProcessing\\subjectivity\\sen\\sen.txt"))) {
                while ((sent = inputStream.readLine()) != null) {
                    if (!checkLink(sent)) {
                        sent = FontConveter.Main.converter(FontType.UNICODETH, FontType.UNICODE, sent);
                        String[] sent1 = sent.split("\\?");
                        for (String sent2 : sent1) {
                            String[] sent3 = sent2.split("\\!");
                            for (String sent4 : sent3) {
                                String[] sent5 = sent4.split("\\.");
                                for (String sent6 : sent5) {
                                    sent6 = delEmotionIcon(sent6);
                                    if (!checkStopWord(sent6)
                                            && sent6.length() > 2) {
                                        sent6 = sent6.trim();
                                        if (!sent6.equalsIgnoreCase("") && !sent6.equalsIgnoreCase(" ")) {
                                            outputStream.append(sent6 + "\n");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public static boolean checkText(String s, String character) {
        Pattern pattern = Pattern.compile(character + "\\w+");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            return true;
        }
        return false;
    }

    static String delEmotionIcon(String str) {
        String[] arrIcon = new String[73];
        int n = 0;
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("data\\dataProcessing\\dictionary\\icon.txt"));
            while ((line = br.readLine()) != null) {
                arrIcon[n] = line;
                n++;
            }
        } catch (IOException e) {
        }
        for (int i = 0; i < n; i++) {
            str = str.replace(arrIcon[i], "");
        }
        return str;
    }

    static boolean checkStopWord(String str) {
        if (checkText(str, "̀")
                || checkText(str, "́")
                || checkText(str, "̉")
                || checkText(str, "̣")
                || checkText(str, "̃")
                || checkText(str, "́")
                || checkText(str, "́")
                || checkText(str, "́")) {
            return true;
        }
        return false;
    }

    static boolean checkLink(String str) {
        if (checkText(str, "http://")
                || checkText(str, "https://")) {
            return true;
        }
        return false;
    }
}
