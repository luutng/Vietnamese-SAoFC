package FontConveter;

public class Main {

    public static String converter(FontType to, String str) {
        FontType from = FontConverter.findFontType(str);
        return converter(from, to, str);
    }

    public static String converter(FontType from, FontType to, String str) {
        str = FontConverter.convert(from, to, str);
        return str;
    }
}
