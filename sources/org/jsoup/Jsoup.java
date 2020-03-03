package org.jsoup;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.jsoup.helper.DataUtil;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;

public class Jsoup {
    private Jsoup() {
    }

    public static Document a(String str, String str2) {
        return Parser.b(str, str2);
    }

    public static Document a(String str, String str2, Parser parser) {
        return parser.a(str, str2);
    }

    public static Document a(String str) {
        return Parser.b(str, "");
    }

    public static Connection b(String str) {
        return HttpConnection.g(str);
    }

    public static Document a(File file, String str, String str2) throws IOException {
        return DataUtil.a(file, str, str2);
    }

    public static Document a(File file, String str) throws IOException {
        return DataUtil.a(file, str, file.getAbsolutePath());
    }

    public static Document a(InputStream inputStream, String str, String str2) throws IOException {
        return DataUtil.a(inputStream, str, str2);
    }

    public static Document a(InputStream inputStream, String str, String str2, Parser parser) throws IOException {
        return DataUtil.a(inputStream, str, str2, parser);
    }

    public static Document b(String str, String str2) {
        return Parser.d(str, str2);
    }

    public static Document c(String str) {
        return Parser.d(str, "");
    }

    public static Document a(URL url, int i) throws IOException {
        Connection b = HttpConnection.b(url);
        b.a(i);
        return b.a();
    }

    public static String a(String str, String str2, Whitelist whitelist) {
        return new Cleaner(whitelist).a(b(str, str2)).f().V();
    }

    public static String a(String str, Whitelist whitelist) {
        return a(str, "", whitelist);
    }

    public static String a(String str, String str2, Whitelist whitelist, Document.OutputSettings outputSettings) {
        Document a2 = new Cleaner(whitelist).a(b(str, str2));
        a2.a(outputSettings);
        return a2.f().V();
    }

    public static boolean b(String str, Whitelist whitelist) {
        return new Cleaner(whitelist).a(str);
    }
}
