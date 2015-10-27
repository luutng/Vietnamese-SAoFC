package SentimentAnalysis;

import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import vn.hus.nlp.tagger.VietnameseMaxentTagger;
import static SVM.IndirectMain.*;
import static SVM.DirectMain.*;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.*;

public class subjectivity {

    public static String[][] arrAdjectiveDictionary = new String[2357][2];
    public static String[][] arrAdverbDictionary = new String[749][2];
    public static String[][] arrIntensifierDictionary = new String[185][2];
    public static String[][] arrNounDictionary = new String[1546][2];
    public static String[][] arrVerbDictionary = new String[1108][2];

    //--Mảng chứa kết quả
    static float[][] arrSub = new float[1350][7];
    static int n = 0;
    static String addressSubFolder = "data\\dataProcessing\\subjectivity\\";

    //public static void main(String[] args) throws IOException {
        /*readDictionary("Intensifier");
     for (int i = 0; i < arrIntensifierDictionary.length; i++) {
     System.out.println(i + 1 + ": " + arrIntensifierDictionary[i][0] + " ~ " + arrIntensifierDictionary[i][1]);
     }*/
    //trainSubjectivityIndirect();
    //trainSubjectivityIndirectForSentence();
    //}
    //--Đọc dữ liệu từ các file từ điển, ghi vào mảng để dễ duyệt
    public static void readDictionary(String strDic) {
        String[][] arrDic = null;
        if (strDic.equalsIgnoreCase("Adjective")) {
            arrDic = new String[2357][2];
        } else if (strDic.equalsIgnoreCase("Adverb")) {
            arrDic = new String[749][2];
        } else if (strDic.equalsIgnoreCase("Intensifier")) {
            arrDic = new String[185][2];
        } else if (strDic.equalsIgnoreCase("Noun")) {
            arrDic = new String[1546][2];
        } else if (strDic.equalsIgnoreCase("Verb")) {
            arrDic = new String[1108][2];
        }
        try {
            String line = "";
            File file = new File("data\\dataProcessing\\dictionary\\" + strDic + "Dictionary.txt");
            try (FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr)) {
                int i = 0;
                while ((line = br.readLine()) != null) {
                    String[] str = line.split("~");
                    arrDic[i][0] = str[0];
                    arrDic[i][1] = str[1];
                    i++;
                }
            }
        } catch (Exception ex) {
        }
        switch (strDic) {
            case "Adjective": {
                arrAdjectiveDictionary = arrDic;
            }
            case "Adverb": {
                arrAdverbDictionary = arrDic;
            }
            case "Intensifier": {
                arrIntensifierDictionary = arrDic;
            }
            case "Noun": {
                arrNounDictionary = arrDic;
            }
            case "Verb": {
                arrVerbDictionary = arrDic;
            }
            default:
                break;
        }
    }

    //--Đọc dữ liệu kết quả, ghi vào mảng kết quả
    static void readSubResult(String fileName) {
        if (new File(new File(addressSubFolder + fileName), "results" + fileName + ".txt").exists()) {
            File resultsFile = new File(addressSubFolder + fileName + "\\results" + fileName + ".txt");
            String line = "";
            int i = 0;
            try {
                FileReader fr = new FileReader(resultsFile);
                BufferedReader br = new BufferedReader(fr);
                while ((line = br.readLine()) != null) {
                    arrSub[i][0] = Float.parseFloat(line);
                    i++;
                }
            } catch (IOException | NumberFormatException e) {
            }
        } else {
            arrSub[0][0] = -1;
        }
    }

    //--Cắt file dữ liệu ban đầu thành câu, ghi kết quả vào file fileNameSentences.txt
    static void exportSentencesFile(String fileName, String type) {
        String adr = null;
        if (type.equalsIgnoreCase("subjectivity")) {
            adr = "data\\dataProcessing\\subjectivity\\";
        } else if (type.equalsIgnoreCase("sentiment")) {
            adr = "data\\dataProcessing\\sentiment\\";
        }
        try {
            String line = "";
            try (BufferedReader inputStream = new BufferedReader(new FileReader(adr + fileName + "\\" + fileName + ".txt"));
                    BufferedWriter outputStream = new BufferedWriter(new FileWriter(adr + fileName + "\\" + fileName + "Sentences.txt"))) {
                while ((line = inputStream.readLine()) != null) {
                    String[] Sentences = line.split("\\.");
                    for (String Sentence : Sentences) {
                        Sentence = Sentence.trim();
                        if (!Sentence.equals("") && !Sentence.equals(" ")) {
                            outputStream.append(Sentence + "\n");
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    //--Phân tích câu sử dụng vnTagger, input và output là file
    static void taggerProcessing(String fileName, String type) {
        String adr = null;
        if (type.equalsIgnoreCase("subjectivity")) {
            adr = "data\\dataProcessing\\subjectivity\\";
        } else if (type.equalsIgnoreCase("sentiment")) {
            adr = "data\\dataProcessing\\sentiment\\";
        }
        VietnameseMaxentTagger tagger = new VietnameseMaxentTagger();
        try {
            tagger.tagFile(adr + fileName + "\\" + fileName + "Sentences.txt", adr + fileName + "\\tagger" + fileName + ".txt");
        } catch (Exception e) {
        }
    }

    //--Đếm số từ trong một câu (câu truyền vào đã được phân tích thành dạng html)
    static int countWords(String str) {
        int value = 0;
        Document document = (Document) Jsoup.parse(str);
        Elements words = document.select("w");
        for (Element e : words) {
            value++;
            if (e.text().equals("")
                    || e.text().equals(",")
                    || e.text().equals(".")
                    || e.text().equals(";")
                    || e.text().equals(":")
                    || e.text().equals("(")
                    || e.text().equals(")")
                    || e.text().equals("3")
                    || e.text().equals("!")
                    || e.text().equals("^")
                    || e.text().equals("D")
                    || e.text().equals("-")
                    || e.text().equals("_")
                    || e.text().equals("?")
                    || e.text().equals("v")
                    || e.text().equals("=")
                    || e.text().equals("[")
                    || e.text().equals("]")
                    || e.text().equals("|")
                    || e.text().equals("<")
                    || e.text().equals("o")
                    || e.text().equals("O")) {
                value--;
            }
        }
        return value;
    }

    //--Tính từ
    static int adjectiveWords(String str) {
        int value = 0;
        Document document = (Document) Jsoup.parse(str);
        Elements words = document.select("w[pos=A]");
        for (Element e : words) {
            for (int i = 0; i < arrAdjectiveDictionary.length; i++) {
                if (e.text().equalsIgnoreCase(arrAdjectiveDictionary[i][0])) {
                    value += Float.parseFloat(arrAdjectiveDictionary[i][1]);
                }
            }
        }
        //--Trường hợp ngoại lệ, từ "hay" mang nghĩa cảm xúc nhưng bị gán nhãn "C" (từ nối)
        Document document1 = (Document) Jsoup.parse(str);
        Elements words1 = document1.select("w[pos=C]");
        for (Element e : words1) {
            if (e.text().equalsIgnoreCase("hay")) {
                value += 3;
            }
        }
        return value;
    }

    //--Trạng từ
    static int adverbWords(String str) {
        int value = 0;
        Document document = (Document) Jsoup.parse(str);
        Elements words = document.select("w[pos=R]");
        for (Element e : words) {
            for (int i = 0; i < arrAdverbDictionary.length; i++) {
                if (e.text().equalsIgnoreCase(arrAdverbDictionary[i][0])) {
                    value += Float.parseFloat(arrAdverbDictionary[i][1]);
                }
            }
        }
        return value;
    }

    //--Danh từ
    static int nounWords(String str) {
        int value = 0;
        Document document = (Document) Jsoup.parse(str);
        Elements words = document.select("w[pos=N]");
        for (Element e : words) {
            for (int i = 0; i < arrNounDictionary.length; i++) {
                if (e.text().equalsIgnoreCase(arrNounDictionary[i][0])) {
                    value += Float.parseFloat(arrNounDictionary[i][1]);
                }
            }
        }
        return value;
    }

    //--Động từ
    static int verbWords(String str) {
        int value = 0;
        Document document = (Document) Jsoup.parse(str);
        Elements words = document.select("w[pos=V]");
        for (Element e : words) {
            for (int i = 0; i < arrVerbDictionary.length; i++) {
                if (e.text().equalsIgnoreCase(arrVerbDictionary[i][0])) {
                    value += Float.parseFloat(arrVerbDictionary[i][1]);
                }
            }
        }
        return value;
    }

    //--Tất cả các từ
    static float soAllWords(String str) {
        float value = adjectiveWords(str)
                + adverbWords(str)
                + nounWords(str)
                + verbWords(str);
        return value;
    }

    //--Loại trường hợp câu điều kiện không có thực ở hiện tại
    static boolean checkConditionals(String str) {
        int i = 0;
        Document document = (Document) Jsoup.parse(str);
        Elements words = document.select("w[pos=C]");
        for (Element e : words) {
            if (e.text().equalsIgnoreCase("giá như")
                    || e.text().equalsIgnoreCase("giá mà")
                    || e.text().equalsIgnoreCase("nếu")
                    || e.text().equalsIgnoreCase("nếu mà")
                    || e.text().equalsIgnoreCase("nếu như")) {
                i++;
            }
            if (e.text().equalsIgnoreCase("thì")) {
                i++;
            }
        }
        if (i > 1) {
            return true;
        } else {
            return false;
        }
    }

    //--Câu hỏi
    static boolean checkQuestion(String str) {
        Document document = (Document) Jsoup.parse(str);
        Elements words = document.select("w");
        Element last = words.last();
        if (last.text().equalsIgnoreCase("chưa")
                || last.text().equalsIgnoreCase("không")
                || last.text().equalsIgnoreCase("vậy")) {
            return true;
        }
        Document docX = (Document) Jsoup.parse(str);
        Elements wordsX = docX.select("w[pos=X]");
        if (wordsX.first() == words.first()
                || wordsX.last() == words.last()) {
            return true;
        }
        for (Element e : words) {
            //--Tính từ
            for (int i = 0; i < arrAdjectiveDictionary.length; i++) {
                Element t = e.nextElementSibling();
                if (e != last
                        && t != last
                        && t.text().equalsIgnoreCase(arrAdjectiveDictionary[i][0])) {
                    Elements w = document.select("w[pos=A]");
                    for (Element p : w) {
                        if (t == p) {
                            if (t.nextElementSibling().text().equalsIgnoreCase("chưa")
                                    || t.nextElementSibling().text().equalsIgnoreCase("không")) {
                                return true;
                            }
                        }
                    }
                }
            }
            //--Trạng từ
            for (int i = 0; i < arrAdverbDictionary.length; i++) {
                Element t = e.nextElementSibling();
                if (e != last
                        && t != last
                        && t.text().equalsIgnoreCase(arrAdverbDictionary[i][0])) {
                    Elements w = document.select("w[pos=R]");
                    for (Element p : w) {
                        if (t == p) {
                            if (t.nextElementSibling().text().equalsIgnoreCase("chưa")
                                    || t.nextElementSibling().text().equalsIgnoreCase("không")) {
                                return true;
                            }
                        }
                    }
                }
            }
            //--Danh từ
            for (int i = 0; i < arrNounDictionary.length; i++) {
                Element t = e.nextElementSibling();
                if (e != last
                        && t != last
                        && t.text().equalsIgnoreCase(arrNounDictionary[i][0])) {
                    Elements w = document.select("w[pos=N]");
                    for (Element p : w) {
                        if (t == p) {
                            if (t.nextElementSibling().text().equalsIgnoreCase("chưa")
                                    || t.nextElementSibling().text().equalsIgnoreCase("không")) {
                                return true;
                            }
                        }
                    }
                }
            }
            //--Động từ
            for (int i = 0; i < arrVerbDictionary.length; i++) {
                Element t = e.nextElementSibling();
                if (e != last
                        && t != last
                        && t.text().equalsIgnoreCase(arrVerbDictionary[i][0])) {
                    Elements w = document.select("w[pos=V]");
                    for (Element p : w) {
                        if (t == p) {
                            if (t.nextElementSibling().text().equalsIgnoreCase("chưa")
                                    || t.nextElementSibling().text().equalsIgnoreCase("không")) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    //--Dùng những hàm thành phần để phân tích trên bộ dữ liệu, ghi kết quả là những đặc trưng của câu vào mảng
    //--Chuỗi truyền vào dạng html
    static void exSubjectivityFeature(String str) {
        arrSub[n][1] = countWords(str);
        arrSub[n][2] = adjectiveWords(str);
        arrSub[n][3] = adverbWords(str);
        arrSub[n][4] = nounWords(str);
        arrSub[n][5] = verbWords(str);
        if (checkConditionals(str) || checkQuestion(str)) {
            arrSub[n][6] = 0;
        } else {
            arrSub[n][6] = arrSub[n][2] + arrSub[n][3] + arrSub[n][4] + arrSub[n][5];
        }
        n++;
    }

    //--Từ dữ liệu, sử dụng vnTagger để phân tích câu
    //--Sau đó, hàm exSubjectivityTaggerFile sẽ rút trích đặc trưng của từng câu và ghi vào mảng arrSub[][] 
    static void exSubjectivityTaggerFile(String fileName) {
        exportSentencesFile(fileName, "subjectivity");
        //--Tagger để phân tích câu
        taggerProcessing(fileName, "subjectivity");

        String HTMLSTring = "";
        try {
            String temp = "";
            File file = new File(addressSubFolder + fileName + "\\tagger" + fileName + ".txt");
            try (FileReader frFile = new FileReader(file);
                    BufferedReader brFile = new BufferedReader(frFile)) {
                while ((temp = brFile.readLine()) != null) {
                    HTMLSTring += temp;
                }
            }
        } catch (Exception ex) {
        }

        //--Mảng result chứa những câu được cắt theo thẻ <s> từ dữ liệu được Tagger phân tích
        String result[] = HTMLSTring.split("<s>");

        //--Hàm exSubjectivityFeature rút trích đặc trưng của từng câu và ghi vào mảng arrSub[][]
        //--"i = 1" là vì khi cắt dữ liệu kiểu html, câu đầu tiên là thẻ <doc> không cần sử dụng
        for (int i = 1; i < result.length; i++) {
            exSubjectivityFeature(result[i]);
        }
    }

    //--Tạo file huấn luyện cho SVM theo mảng đặc trưng bên trên
    static void exSubjectivityResultFile(String fileName) {
        readDictionary("Adjective");
        readDictionary("Adverb");
        readDictionary("Intensifier");
        readDictionary("Noun");
        readDictionary("Verb");
        readSubResult(fileName);
        exSubjectivityTaggerFile(fileName);
        if (arrSub[0][0] == -1) {
            try {
                FileOutputStream fos = new FileOutputStream(addressSubFolder + fileName + "\\" + fileName + "OUT.txt", false);
                try (PrintWriter pw = new PrintWriter(fos)) {
                    for (int i = 0; i < n; i++) {
                        pw.println("0 1:" + arrSub[i][1]
                                + " 2:" + arrSub[i][2]
                                + " 3:" + arrSub[i][3]
                                + " 4:" + arrSub[i][4]
                                + " 5:" + arrSub[i][5]
                                + " 6:" + arrSub[i][6]);
                    }
                }
            } catch (Exception ex) {
            }
        } else if (arrSub[0][0] != -1) {
            try {
                FileOutputStream fos = new FileOutputStream(addressSubFolder + fileName + "\\" + fileName + "OUT.txt", false);
                try (PrintWriter pw = new PrintWriter(fos)) {
                    for (int i = 0; i < n; i++) {
                        pw.println((int) arrSub[i][0]
                                + " 1:" + arrSub[i][1]
                                + " 2:" + arrSub[i][2]
                                + " 3:" + arrSub[i][3]
                                + " 4:" + arrSub[i][4]
                                + " 5:" + arrSub[i][5]
                                + " 6:" + arrSub[i][6]);
                    }
                }
            } catch (Exception ex) {
            }
        }
        n = 0;
    }

    static void copySubjectivityFileToSVM() throws IOException {
        File source = null, target = null;
        if (new File(new File("data\\dataProcessing\\subjectivity\\train"), "trainOUT.txt").exists()) {
            source = new File("data\\dataProcessing\\subjectivity\\train\\trainOUT.txt");
            target = new File("src\\Data\\trainSub");
            Files.copy(source.toPath(), target.toPath(), REPLACE_EXISTING);
        }
        if (new File(new File("data\\dataProcessing\\subjectivity\\test"), "testOUT.txt").exists()) {
            source = new File("data\\dataProcessing\\subjectivity\\test\\testOUT.txt");
            target = new File("src\\Data\\testSub");
            Files.copy(source.toPath(), target.toPath(), REPLACE_EXISTING);
        }
    }

    /*public static double trainSubjectivityIndirect() throws IOException {
        exSubjectivityResultFile("train");
        exSubjectivityResultFile("test");
        copySubjectivityFileToSVM();
        return runIndirect("Sub");
    }*/

    public static void trainSubjectivityIndirectForSentence() throws IOException {
        exSubjectivityResultFile("sen");
        File source = null, target = null;
        if (new File(new File("data\\dataProcessing\\subjectivity\\sen"), "senOUT.txt").exists()) {
            source = new File("data\\dataProcessing\\subjectivity\\sen\\senOUT.txt");
            target = new File("src\\Data\\testSentenceSub");
            Files.copy(source.toPath(), target.toPath(), REPLACE_EXISTING);
        }
        runIndirectForSentence("Sub");
    }
    
    public static double trainSubjectivityDir() throws IOException {
        exSubjectivityResultFile("train");
        exSubjectivityResultFile("test");
        copySubjectivityFileToSVM();
        return runDirect("Sub");
    }
}
