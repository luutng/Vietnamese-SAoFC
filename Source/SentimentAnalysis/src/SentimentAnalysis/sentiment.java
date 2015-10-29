package SentimentAnalysis;

import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import static SentimentAnalysis.subjectivity.*;
import static SVM.IndirectMain.*;
import static SVM.DirectMain.*;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.*;

public class sentiment {

    //--Mảng chứa kết quả
    static float[][] arrSen = new float[1350][11];
    static int m = 0;
    static String addressSenFolder = "data\\dataProcessing\\sentiment\\";

    //public static void main(String[] args) throws IOException {
        /*readDictionary("Intensifier");
     for (int i = 0; i < arrIntensifierDictionary.length; i++) {
     System.out.println(i + 1 + ": " + arrIntensifierDictionary[i][0] + " ~ " + arrIntensifierDictionary[i][1]);
     }
     exSentimentResultFile("test");*/
    //trainSentimentIndirect();
    //trainSentimentIndirectForSentence();
    //}
    //--Đọc dữ liệu kết quả
    static void readSenResult(String fileName) {
        if (new File(new File(addressSenFolder + fileName), "results" + fileName + ".txt").exists()) {
            File resultsFile = new File(addressSenFolder + fileName + "\\results" + fileName + ".txt");
            String line = "";
            int i = 0;
            try {
                FileReader fr = new FileReader(resultsFile);
                BufferedReader br = new BufferedReader(fr);
                while ((line = br.readLine()) != null) {
                    arrSen[i][0] = Float.parseFloat(line);
                    i++;
                }
            } catch (Exception e) {
            }
        } else {
            arrSen[0][0] = -1;
        }
    }

    //--Từ tăng cường    
    static float soIntensifier(String str) {
        float value = 0;
        Document document = (Document) Jsoup.parse(str);
        Elements words = document.select("w");
        Element last = words.last();
        for (Element e : words) {
            //--Tính từ
            for (int i = 0; i < arrAdjectiveDictionary.length; i++) {
                Element t = e.nextElementSibling();
                if (e != last
                        && t != last
                        && t.text().equalsIgnoreCase(arrAdjectiveDictionary[i][0])) {
                    float tVal = Float.parseFloat(arrAdjectiveDictionary[i][1]);
                    Elements w = document.select("w[pos=A]");
                    for (Element p : w) {
                        if (t == p) {
                            for (int j = 0; j < arrIntensifierDictionary.length; j++) {
                                if (e.text().equalsIgnoreCase(arrIntensifierDictionary[j][0])
                                        || t.nextElementSibling().text().equalsIgnoreCase(arrIntensifierDictionary[j][0])) {
                                    float iVal = tVal * (Float.parseFloat(arrIntensifierDictionary[j][1]) + 1);
                                    value = value + iVal - tVal;
                                }
                            }
                            value += tVal;
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
                    float tVal = Float.parseFloat(arrAdverbDictionary[i][1]);
                    Elements w = document.select("w[pos=R]");
                    for (Element p : w) {
                        if (t == p) {
                            for (int j = 0; j < arrIntensifierDictionary.length; j++) {
                                if (e.text().equalsIgnoreCase(arrIntensifierDictionary[j][0])
                                        || t.nextElementSibling().text().equalsIgnoreCase(arrIntensifierDictionary[j][0])) {
                                    float iVal = tVal * (Float.parseFloat(arrIntensifierDictionary[j][1]) + 1);
                                    value = value + iVal - tVal;
                                }
                            }
                            value += tVal;
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
                    float tVal = Float.parseFloat(arrNounDictionary[i][1]);
                    Elements w = document.select("w[pos=N]");
                    for (Element p : w) {
                        if (t == p) {
                            for (int j = 0; j < arrIntensifierDictionary.length; j++) {
                                if (e.text().equalsIgnoreCase(arrIntensifierDictionary[j][0])
                                        || t.nextElementSibling().text().equalsIgnoreCase(arrIntensifierDictionary[j][0])) {
                                    float iVal = tVal * (Float.parseFloat(arrIntensifierDictionary[j][1]) + 1);
                                    value = value + iVal - tVal;
                                }
                            }
                            value += tVal;
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
                    float tVal = Float.parseFloat(arrVerbDictionary[i][1]);
                    Elements w = document.select("w[pos=V]");
                    for (Element p : w) {
                        if (t == p) {
                            for (int j = 0; j < arrIntensifierDictionary.length; j++) {
                                if (e.text().equalsIgnoreCase(arrIntensifierDictionary[j][0])
                                        || t.nextElementSibling().text().equalsIgnoreCase(arrIntensifierDictionary[j][0])) {
                                    float iVal = tVal * (Float.parseFloat(arrIntensifierDictionary[j][1]) + 1);
                                    value = value + iVal - tVal;
                                }
                            }
                            value += tVal;
                        }
                    }
                }
            }
        }
        if (value == 0) {
            value = soAllWords(str);
        }
        return value;
    }

    //--Từ phủ định thay đổi
    static float soShiftNegation(String str) {
        float value = 0;
        float cVal = 0;
        Elements w = null;
        Document document = (Document) Jsoup.parse(str);
        Elements words = document.select("w");
        Element last = words.last();
        for (Element e : words) {
            if ((e.text().equalsIgnoreCase("chẳng")
                    || e.text().equalsIgnoreCase("không"))
                    && e != last) {
                e = e.nextElementSibling();
                w = document.select("w[pos=A]");
                for (Element p : w) {
                    if (e == p) {
                        for (int j = 0; j < arrAdjectiveDictionary.length; j++) {
                            if (p.text().equalsIgnoreCase(arrAdjectiveDictionary[j][0])) {
                                float t = (-2) * Float.parseFloat(arrAdjectiveDictionary[j][1]);
                                cVal += t;
                            }
                        }
                    }
                }
                w = document.select("w[pos=R]");
                for (Element p : w) {
                    if (e == p) {
                        for (int j = 0; j < arrAdverbDictionary.length; j++) {
                            if (p.text().equalsIgnoreCase(arrAdverbDictionary[j][0])) {
                                float t = (-2) * Float.parseFloat(arrAdverbDictionary[j][1]);
                                cVal += t;
                            }
                        }
                    }
                }
                w = document.select("w[pos=N]");
                for (Element p : w) {
                    if (e == p) {
                        for (int j = 0; j < arrNounDictionary.length; j++) {
                            if (p.text().equalsIgnoreCase(arrNounDictionary[j][0])) {
                                float t = (-2) * Float.parseFloat(arrNounDictionary[j][1]);
                                cVal += t;
                            }
                        }
                    }
                }
                w = document.select("w[pos=V]");
                for (Element p : w) {
                    if (e == p) {
                        for (int j = 0; j < arrVerbDictionary.length; j++) {
                            if (p.text().equalsIgnoreCase(arrVerbDictionary[j][0])) {
                                float t = (-2) * Float.parseFloat(arrVerbDictionary[j][1]);
                                cVal += t;
                            }
                        }
                    }
                }

                if ((e.text().equalsIgnoreCase("có")
                        || e.text().equalsIgnoreCase("được")
                        || e.text().equalsIgnoreCase("phải"))
                        && e != last) {
                    e = e.nextElementSibling();
                    w = document.select("w[pos=A]");
                    for (Element p : w) {
                        if (e == p) {
                            for (int j = 0; j < arrAdjectiveDictionary.length; j++) {
                                if (p.text().equalsIgnoreCase(arrAdjectiveDictionary[j][0])) {
                                    float t = (-2) * Float.parseFloat(arrAdjectiveDictionary[j][1]);
                                    cVal += t;
                                }
                            }
                        }
                    }
                    w = document.select("w[pos=R]");
                    for (Element p : w) {
                        if (e == p) {
                            for (int j = 0; j < arrAdverbDictionary.length; j++) {
                                if (p.text().equalsIgnoreCase(arrAdverbDictionary[j][0])) {
                                    float t = (-2) * Float.parseFloat(arrAdverbDictionary[j][1]);
                                    cVal += t;
                                }
                            }
                        }
                    }
                    w = document.select("w[pos=N]");
                    for (Element p : w) {
                        if (e == p) {
                            for (int j = 0; j < arrNounDictionary.length; j++) {
                                if (p.text().equalsIgnoreCase(arrNounDictionary[j][0])) {
                                    float t = (-2) * Float.parseFloat(arrNounDictionary[j][1]);
                                    cVal += t;
                                }
                            }
                        }
                    }
                    w = document.select("w[pos=V]");
                    for (Element p : w) {
                        if (e == p) {
                            for (int j = 0; j < arrVerbDictionary.length; j++) {
                                if (p.text().equalsIgnoreCase(arrVerbDictionary[j][0])) {
                                    float t = (-2) * Float.parseFloat(arrVerbDictionary[j][1]);
                                    cVal += t;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (cVal == 0) {
            value = soAllWords(str);
        } else {
            value = soAllWords(str) + cVal;
        }
        return value;
    }

    //--Tăng 50% giá trị SO của từ tiêu cực
    static float soNegativeWeigh(String str) {
        float value = 0;
        Document document = (Document) Jsoup.parse(str);
        Elements adjectiveWords = document.select("w[pos=A]");
        for (Element e : adjectiveWords) {
            for (int i = 0; i < arrAdjectiveDictionary.length; i++) {
                if (e.text().equalsIgnoreCase(arrAdjectiveDictionary[i][0])) {
                    if (Float.parseFloat(arrAdjectiveDictionary[i][1]) < 0) {
                        value += 1.5 * Float.parseFloat(arrAdjectiveDictionary[i][1]);
                    } else {
                        value += Float.parseFloat(arrAdjectiveDictionary[i][1]);
                    }
                }
            }
        }
        Elements adverbWords = document.select("w[pos=R]");
        for (Element e : adverbWords) {
            for (int i = 0; i < arrAdverbDictionary.length; i++) {
                if (e.text().equalsIgnoreCase(arrAdverbDictionary[i][0])) {
                    if (Float.parseFloat(arrAdverbDictionary[i][1]) < 0) {
                        value += 1.5 * Float.parseFloat(arrAdverbDictionary[i][1]);
                    } else {
                        value += Float.parseFloat(arrAdverbDictionary[i][1]);
                    }
                }
            }
        }
        Elements nounWords = document.select("w[pos=N]");
        for (Element e : nounWords) {
            for (int i = 0; i < arrNounDictionary.length; i++) {
                if (e.text().equalsIgnoreCase(arrNounDictionary[i][0])) {
                    if (Float.parseFloat(arrNounDictionary[i][1]) < 0) {
                        value += 1.5 * Float.parseFloat(arrNounDictionary[i][1]);
                    } else {
                        value += Float.parseFloat(arrNounDictionary[i][1]);
                    }
                }
            }
        }
        Elements verbWords = document.select("w[pos=V]");
        for (Element e : verbWords) {
            for (int i = 0; i < arrVerbDictionary.length; i++) {
                if (e.text().equalsIgnoreCase(arrVerbDictionary[i][0])) {
                    if (Float.parseFloat(arrVerbDictionary[i][1]) < 0) {
                        value += 1.5 * Float.parseFloat(arrVerbDictionary[i][1]);
                    } else {
                        value += Float.parseFloat(arrVerbDictionary[i][1]);
                    }
                }
            }
        }
        if (value == 0) {
            value = soAllWords(str);
        }
        return value;
    }

    //--Trường hợp câu có 2 vế liên kết bằng từ: nhưng, nhưng mà, cơ mà, mà thì bỏ giá trị cảm xúc của vế trước từ "nhưng"
    static float soIrrealisBlocking(String str) {
        float value = 0;
        float bVal = 0;
        Elements w = null;
        Document document = (Document) Jsoup.parse(str);
        Elements words = document.select("w");
        value = soAllWords(str);
        for (Element e : words) {
            if (e.text().equalsIgnoreCase("cơ mà")
                    || e.text().equalsIgnoreCase("mà")
                    || e.text().equalsIgnoreCase("nhưng")
                    || e.text().equalsIgnoreCase("nhưng mà")) {
                return value - bVal;
            }
            w = document.select("w[pos=A]");
            for (Element p : w) {
                if (e == p) {
                    for (int i = 0; i < arrAdjectiveDictionary.length; i++) {
                        if (e.text().equalsIgnoreCase(arrAdjectiveDictionary[i][0])) {
                            bVal += Float.parseFloat(arrAdjectiveDictionary[i][1]);
                        }
                    }
                }
            }
            w = document.select("w[pos=R]");
            for (Element p : w) {
                if (e == p) {
                    for (int i = 0; i < arrAdverbDictionary.length; i++) {
                        if (e.text().equalsIgnoreCase(arrAdverbDictionary[i][0])) {
                            bVal += Float.parseFloat(arrAdverbDictionary[i][1]);
                        }
                    }
                }
            }
            w = document.select("w[pos=N]");
            for (Element p : w) {
                if (e == p) {
                    for (int i = 0; i < arrNounDictionary.length; i++) {
                        if (e.text().equalsIgnoreCase(arrNounDictionary[i][0])) {
                            bVal += Float.parseFloat(arrNounDictionary[i][1]);
                        }
                    }
                }
            }
            w = document.select("w[pos=V]");
            for (Element p : w) {
                if (e == p) {
                    for (int i = 0; i < arrVerbDictionary.length; i++) {
                        if (e.text().equalsIgnoreCase(arrVerbDictionary[i][0])) {
                            bVal += Float.parseFloat(arrVerbDictionary[i][1]);
                        }
                    }
                }
            }
        }
        return value;
    }

    //--Câu có từ khiếm khuyết: nên, phải, có thể
    static float soModals(String str) {
        float value = 0;
        Document document = (Document) Jsoup.parse(str);
        Elements words = document.select("w");
        for (Element e : words) {
            if (e.text().equalsIgnoreCase("nên")
                    || e.text().equalsIgnoreCase("phải")
                    || e.text().equalsIgnoreCase("có thể")) {
                return (float) (0.5 * soAllWords(str));
            }
        }
        value = soAllWords(str);
        return value;
    }

    //--Dùng những hàm thành phần để phân tích trên bộ dữ liệu, ghi kết quả là những đặc trưng của câu vào mảng
    //--Chuỗi truyền vào dạng html
    static void exSentimentFeature(String str) {
        arrSen[m][1] = adjectiveWords(str);
        arrSen[m][2] = adverbWords(str);
        arrSen[m][3] = nounWords(str);
        arrSen[m][4] = verbWords(str);
        arrSen[m][5] = arrSen[m][1] + arrSen[m][2] + arrSen[m][3] + arrSen[m][4];
        arrSen[m][6] = soIntensifier(str);
        arrSen[m][7] = soIrrealisBlocking(str);
        arrSen[m][8] = soModals(str);
        arrSen[m][9] = soNegativeWeigh(str);
        arrSen[m][10] = soShiftNegation(str);
        m++;
    }

    //--Từ dữ liệu, sử dụng vnTagger để phân tích câu
    //--Sau đó, hàm exSentimentFeature sẽ rút trích đặc trưng của từng câu và ghi vào mảng arr[][] 
    static void exSentimentTaggerFile(String fileName) {
        exportSentencesFile(fileName, "sentiment");

        //--Tagger để phân tích câu
        taggerProcessing(fileName, "sentiment");

        String HTMLSTring = "";
        try {
            String temp = "";
            File file = new File(addressSenFolder + fileName + "\\tagger" + fileName + ".txt");
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

        //--Hàm exSentimentFeature rút trích đặc trưng của từng câu và ghi vào mảng arr[][]
        //--"i = 1" là vì khi cắt dữ liệu kiểu html, câu đầu tiên là thẻ <doc> không cần sử dụng
        for (int i = 1; i < result.length; i++) {
            exSentimentFeature(result[i]);
        }
    }

    //--Tạo file huấn luyện cho SVM theo mảng đặc trưng bên trên
    static void exSentimentResultFile(String fileName) {
        readDictionary("Adjective");
        readDictionary("Adverb");
        readDictionary("Intensifier");
        readDictionary("Noun");
        readDictionary("Verb");
        readSenResult(fileName);
        exSentimentTaggerFile(fileName);
        if (arrSen[0][0] == -1) {
            try {
                FileOutputStream fos = new FileOutputStream(addressSenFolder + fileName + "\\" + fileName + "OUT.txt", false);
                try (PrintWriter pw = new PrintWriter(fos)) {
                    for (int i = 0; i < m; i++) {
                        pw.println("0 1:" + arrSen[i][1]
                                + " 2:" + arrSen[i][2]
                                + " 3:" + arrSen[i][3]
                                + " 4:" + arrSen[i][4]
                                + " 5:" + arrSen[i][5]
                                + " 6:" + arrSen[i][6]
                                + " 7:" + arrSen[i][7]
                                + " 8:" + arrSen[i][8]
                                + " 9:" + arrSen[i][9]
                                + " 10:" + arrSen[i][10]);
                    }
                }
            } catch (Exception ex) {
            }
        } else if (arrSen[0][0] != -1) {
            try {
                FileOutputStream fos = new FileOutputStream(addressSenFolder + fileName + "\\" + fileName + "OUT.txt", false);
                try (PrintWriter pw = new PrintWriter(fos)) {
                    for (int i = 0; i < m; i++) {
                        pw.println((int) arrSen[i][0]
                                + " 1:" + arrSen[i][1]
                                + " 2:" + arrSen[i][2]
                                + " 3:" + arrSen[i][3]
                                + " 4:" + arrSen[i][4]
                                + " 5:" + arrSen[i][5]
                                + " 6:" + arrSen[i][6]
                                + " 7:" + arrSen[i][7]
                                + " 8:" + arrSen[i][8]
                                + " 9:" + arrSen[i][9]
                                + " 10:" + arrSen[i][10]);
                    }
                }
            } catch (Exception ex) {
            }
        }
        m = 0;
    }

    static void copySentimentFileToSVM() throws IOException {
        File source = null, target = null;
        if (new File(new File("data\\dataProcessing\\sentiment\\train"), "trainOUT.txt").exists()) {
            source = new File("data\\dataProcessing\\sentiment\\train\\trainOUT.txt");
            target = new File("src\\Data\\trainSen");
            Files.copy(source.toPath(), target.toPath(), REPLACE_EXISTING);
        }
        if (new File(new File("data\\dataProcessing\\sentiment\\test"), "testOUT.txt").exists()) {
            source = new File("data\\dataProcessing\\sentiment\\test\\testOUT.txt");
            target = new File("src\\Data\\testSen");
            Files.copy(source.toPath(), target.toPath(), REPLACE_EXISTING);
        }
    }

    /*public static double trainSentimentIndirect() throws IOException {
        exSentimentResultFile("train");
        exSentimentResultFile("test");
        copySentimentFileToSVM();
        return runIndirect("Sen");
    }*/

    public static void trainSentimentIndirectForSentence() throws IOException {
        exSentimentResultFile("sen");
        File source = null, target = null;
        if (new File(new File("data\\dataProcessing\\sentiment\\sen"), "senOUT.txt").exists()) {
            source = new File("data\\dataProcessing\\sentiment\\sen\\senOUT.txt");
            target = new File("src\\Data\\testSentenceSen");
            Files.copy(source.toPath(), target.toPath(), REPLACE_EXISTING);
        }
        runIndirectForSentence("Sen");
    }    
    
    public static double trainSentimentDir() throws IOException {
        exSentimentResultFile("train");
        exSentimentResultFile("test");
        copySentimentFileToSVM();
        return runDirect("Sen");
    }
    
}
