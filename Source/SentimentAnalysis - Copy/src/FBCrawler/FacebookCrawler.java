package FBCrawler;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import org.codehaus.jackson.*;

public class FacebookCrawler {

    public static void main(String[] args) {
        //fbCrawler();
    }

    //public static String acctkall = "?access_token=CAALlOH5qfXoBAOIxVFRKmHFRKCZBJosodITTRCj7WZBHbgKoKT3WhDPhnzudQpTGZBcR0aDKZCZBXoeLiZBJcpAuZC1EZAhl8Bwf1kJFooLNFMvf1ZChybTYmHHcUN33ep8rGQr5dvSQHd3qIFi8kQVgc7UQXRoeaf7PMZB3x7hWvZCaA998Ime901h"; //&__mref=message_bubble
    //public static String acctkid = "?access_token=CAALlOH5qfXoBAOIxVFRKmHFRKCZBJosodITTRCj7WZBHbgKoKT3WhDPhnzudQpTGZBcR0aDKZCZBXoeLiZBJcpAuZC1EZAhl8Bwf1kJFooLNFMvf1ZChybTYmHHcUN33ep8rGQr5dvSQHd3qIFi8kQVgc7UQXRoeaf7PMZB3x7hWvZCaA998Ime901h&fields=id";
    public static String acctkall = "?access_token=CAALlOH5qfXoBAJMboYBKgRZCo1VIYozuKe3QE6gGZAeUwPzvWEV1Hn0KJztzquw9nCP7Ta6RrFJjdgT6C5Vp5gkDie7dPMxcx4pMdMTDdDACVH5ZCTKCFVYr8oIj5JuBZBn8Bq34yLnze0dam7AhRGhZAmRpQE8KoZBve3AiwtCY4iIMhjRDLj"; //&__mref=message_bubble
    public static String acctkid = "?access_token=CAALlOH5qfXoBAJMboYBKgRZCo1VIYozuKe3QE6gGZAeUwPzvWEV1Hn0KJztzquw9nCP7Ta6RrFJjdgT6C5Vp5gkDie7dPMxcx4pMdMTDdDACVH5ZCTKCFVYr8oIj5JuBZBn8Bq34yLnze0dam7AhRGhZAmRpQE8KoZBve3AiwtCY4iIMhjRDLj&fields=id";
    //public static String acctkall = "?access_token=CAALlOH5qfXoBAF6H5zZCzAlx0kOSSQLCOwjP6yRtRwFfm55tEVUoPzFTHgNXWkaVr6eqxGY4ZCQsjUdmJY37HBWrf1DnQH9rjOndTma8ZAZC7AK4TmU91gaacfGirr2KxCtr5DPwqajH7nGXv6asFYz7Om8gFPj3FkM78pjZCxbZBCLpeJHrYxLgnjZC8CfJeoZD";
    //public static String acctkid = "?access_token=CAALlOH5qfXoBAF6H5zZCzAlx0kOSSQLCOwjP6yRtRwFfm55tEVUoPzFTHgNXWkaVr6eqxGY4ZCQsjUdmJY37HBWrf1DnQH9rjOndTma8ZAZC7AK4TmU91gaacfGirr2KxCtr5DPwqajH7nGXv6asFYz7Om8gFPj3FkM78pjZCxbZBCLpeJHrYxLgnjZC8CfJeoZD&fields=id&limit=250";

    public static String idPage = "262700667105773";
    public static File fldName = new File("data");
    public static File[] listFile = fldName.listFiles();
    public static int n = 0;

    public static String getData(String id, String getCode) {
        String link = "https://graph.facebook.com/" + id + "/" + getCode + acctkall;
        String data = "";
        URL url;
        try {
            url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")))) {
                String line = br.readLine();
                while (line != null) {
                    data += line;
                    line = br.readLine();
                }
            }
        } catch (Exception ex) {
        }
        return data;
    }

    public static String getDataPosts(String id, String getCode) {
        String link = "https://graph.facebook.com/" + id + "/" + getCode + acctkid;
        String data = "";
        URL url;
        try {
            url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")))) {
                String line = br.readLine();
                while (line != null) {
                    data += line;
                    line = br.readLine();
                }
            }
        } catch (Exception ex) {
        }
        return data;
    }

    public static void writeFile(String inputData, String filename) {
        Boolean k = false;
        for (File list : listFile) {
            if (list.getName().equals(filename)) {
                k = true;
                break;
            }
        }
        if (k == false) {
            File file = new File("data\\dataFacebookCrawler\\" + filename);
            try {
                FileOutputStream fos = new FileOutputStream("data\\dataFacebookCrawler\\" + filename, true);
                try (PrintWriter pw = new PrintWriter(fos)) {
                    pw.println(inputData);
                }
            } catch (Exception ex) {
            }
        }
    }

    public static String readFile(String fileName) {
        String data = "";
        try {
            File file = new File("data\\dataFacebookCrawler\\" + fileName);
            try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
                String line;
                while ((line = br.readLine()) != null) {
                    data += line + "\n";
                }
            }
        } catch (Exception ex) {
        }
        return data;
    }

    public static String getIDPageByName(String idName) {
        String link = "https://graph.facebook.com/" + idName;
        String temp = "";
        String data = "";
        URL url;
        try {
            url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")))) {
                String line = br.readLine();
                while (line != null) {
                    data += line;
                    line = br.readLine();
                }
            }
        } catch (Exception ex) {
        }

        writeFile(data, "infoPage.txt");

        try {
            int i = 1;
            JsonFactory jsonfactory = new JsonFactory();
            File source = new File("data\\dataFacebookCrawler\\infoPage.txt");
            try (JsonParser parser = jsonfactory.createJsonParser(source)) {
                do {
                    String token = parser.getCurrentName();
                    if ("id".equals(token)) {
                        parser.nextToken();
                        temp = parser.getText();
                        i++;
                    }
                } while (parser.nextToken() != null && i < 2);
            }
        } catch (Exception ex) {
        }
        return temp;
    }

    public static void getCommentsByID(String id) {
        String link = "https://graph.facebook.com/" + id + "/comments" + acctkall + "&limit=2000";
        String dataComments = "";
        URL url;
        try {
            url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")))) {
                String line = br.readLine();
                while (line != null) {
                    dataComments += line;
                    line = br.readLine();
                }
            }
        } catch (Exception ex) {
        }
        FacebookCrawler.writeFile(dataComments, "comment\\dataOf_" + id + ".txt");
        String comments = FacebookCrawler.parseJSON("comment\\dataOf_" + id + ".txt", "message");
        FacebookCrawler.writeFile(comments, "comment\\" + id + ".txt");
    }

    public static void getComments() {
        try {
            File file = new File("data\\dataFacebookCrawler\\postsID.txt");
            try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
                String line;
                int i = 1;
                while ((line = br.readLine()) != null && i < n) {
                    getCommentsByID(line);
                    i++;
                }
            }
        } catch (Exception ex) {
        }
    }

    public static String parseDescription(String inFile, String info) {
        String data = "";
        int i = 1;
        try {
            JsonFactory jsonfactory = new JsonFactory();
            File source = new File("data\\dataFacebookCrawler\\" + inFile);
            try (JsonParser parser = jsonfactory.createJsonParser(source)) {
                do {
                    String token = parser.getCurrentName();
                    if ("description".equals(token)) {
                        parser.nextToken();
                        if (parser.getText().length() != 0) {
                            String temp = Integer.toString(i) + ". " + parser.getText() + "\r\n";
                            data += temp;
                            i++;
                        }
                    }
                } while (parser.nextToken() != null);
            }
        } catch (Exception ex) {
        }
        n = i;
        return data;
    }

    public static String parseJSON(String inFile, String info) {
        String data = "";
        try {
            JsonFactory jsonfactory = new JsonFactory();
            File source = new File("data\\dataFacebookCrawler\\" + inFile);
            try (JsonParser parser = jsonfactory.createJsonParser(source)) {
                do {
                    String token = parser.getCurrentName();
                    if (info.equals(token)) {
                        parser.nextToken();
                        if (parser.getText() != null) {
                            String temp = parser.getText() + "\r\n";
                            data += temp;
                        }
                    }
                } while (parser.nextToken() != null);
            }
        } catch (Exception ex) {
        }
        return data;
    }

    public static void fbCrawler() {
        //--Lấy toàn bộ dữ liệu dạng json, ghi vào file data.txt
        //--Lấy dữ liệu posts, ghi vào file dataPosts.txt
        String dataPosts = FacebookCrawler.getData(idPage, "posts");
        System.out.println(dataPosts.length());
        FacebookCrawler.writeFile(dataPosts, "dataPosts.txt");

        //--Phân tích dữ liệu, trả về ID của post và ghi vào file postsID.txt
        String dataPostsID = FacebookCrawler.getDataPosts(idPage, "posts");
        FacebookCrawler.writeFile(dataPostsID, "dataPostsID.txt");
        String postsID = FacebookCrawler.parseJSON("dataPostsID.txt", "id");
        FacebookCrawler.writeFile(postsID, "postsID.txt");

        //--Phân tích dữ liệu, trả về description của post và ghi vào file postsDescription.txt
        String postsDescription = FacebookCrawler.parseDescription("dataPosts.txt", "description");
        FacebookCrawler.writeFile(postsDescription, "postsDescription.txt");

        //--Ứng với từng ID của mỗi posts lấy toàn bộ bình luận về và ghi vào file *.txt ("*" chính là ID của post đó)
        FacebookCrawler.getComments();
    }

    static void deleteFolder(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File fileTmp : file.listFiles()) {
                deleteFolder(fileTmp);
            }
        }
        file.delete();
    }

    public static void deleteOldFile() {
        String pathFolder = "data\\dataFacebookCrawler";
        File dirData = new File("data\\dataFacebookCrawler");
        File dirComment = new File(dirData, "comment");
        try {
            File fileFolder = new File(pathFolder);
            deleteFolder(fileFolder);
            dirData.mkdir();
            dirComment.mkdir();
        } catch (Exception e) {
        }
    }
}
