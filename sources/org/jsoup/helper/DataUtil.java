package org.jsoup.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.cybergarage.http.HTTP;
import org.jsoup.internal.ConstrainableInputStream;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.Parser;

public final class DataUtil {

    /* renamed from: a  reason: collision with root package name */
    static final String f3647a = "UTF-8";
    static final int b = 32768;
    static final int c = 32;
    private static final Pattern d = Pattern.compile("(?i)\\bcharset=\\s*(?:[\"'])?([^\\s,;\"']*)");
    private static final int e = 5120;
    private static final char[] f = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private DataUtil() {
    }

    public static Document a(File file, String str, String str2) throws IOException {
        return b(new FileInputStream(file), str, str2, Parser.e());
    }

    public static Document a(InputStream inputStream, String str, String str2) throws IOException {
        return b(inputStream, str, str2, Parser.e());
    }

    public static Document a(InputStream inputStream, String str, String str2, Parser parser) throws IOException {
        return b(inputStream, str, str2, parser);
    }

    static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[32768];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    static Document b(InputStream inputStream, String str, String str2, Parser parser) throws IOException {
        if (inputStream == null) {
            return new Document(str2);
        }
        ConstrainableInputStream a2 = ConstrainableInputStream.a(inputStream, 32768, 0);
        a2.mark(5120);
        ByteBuffer a3 = a((InputStream) a2, 5119);
        boolean z = a2.read() == -1;
        a2.reset();
        BomCharset a4 = a(a3);
        if (a4 != null) {
            str = a4.f3648a;
            a2.skip((long) a4.b);
        }
        Document document = null;
        if (str == null) {
            Document a5 = parser.a(Charset.forName("UTF-8").decode(a3).toString(), str2);
            Iterator it = a5.k("meta[http-equiv=content-type], meta[charset]").iterator();
            String str3 = null;
            while (it.hasNext()) {
                Element element = (Element) it.next();
                if (element.c("http-equiv")) {
                    str3 = a(element.d("content"));
                }
                if (str3 == null && element.c(HTTP.CHARSET)) {
                    str3 = element.d(HTTP.CHARSET);
                    continue;
                }
                if (str3 != null) {
                    break;
                }
            }
            if (str3 == null && a5.c() > 0 && (a5.e(0) instanceof XmlDeclaration)) {
                XmlDeclaration xmlDeclaration = (XmlDeclaration) a5.e(0);
                if (xmlDeclaration.b().equals("xml")) {
                    str3 = xmlDeclaration.d("encoding");
                }
            }
            String b2 = b(str3);
            if (b2 != null && !b2.equalsIgnoreCase("UTF-8")) {
                str = b2.trim().replaceAll("[\"']", "");
            } else if (z) {
                document = a5;
            }
        } else {
            Validate.a(str, "Must set charset arg to character set of file to parse. Set to null to attempt to detect from HTML");
        }
        if (document == null) {
            if (str == null) {
                str = "UTF-8";
            }
            document = parser.a((Reader) new BufferedReader(new InputStreamReader(a2, str), 32768), str2);
            document.m().a(str);
        }
        a2.close();
        return document;
    }

    public static ByteBuffer a(InputStream inputStream, int i) throws IOException {
        Validate.a(i >= 0, "maxSize must be 0 (unlimited) or larger");
        return ConstrainableInputStream.a(inputStream, 32768, i).a(i);
    }

    static ByteBuffer a(InputStream inputStream) throws IOException {
        return a(inputStream, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0020  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.nio.ByteBuffer a(java.io.File r4) throws java.io.IOException {
        /*
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ all -> 0x001c }
            java.lang.String r2 = "r"
            r1.<init>(r4, r2)     // Catch:{ all -> 0x001c }
            long r2 = r1.length()     // Catch:{ all -> 0x001a }
            int r4 = (int) r2     // Catch:{ all -> 0x001a }
            byte[] r4 = new byte[r4]     // Catch:{ all -> 0x001a }
            r1.readFully(r4)     // Catch:{ all -> 0x001a }
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.wrap(r4)     // Catch:{ all -> 0x001a }
            r1.close()
            return r4
        L_0x001a:
            r4 = move-exception
            goto L_0x001e
        L_0x001c:
            r4 = move-exception
            r1 = r0
        L_0x001e:
            if (r1 == 0) goto L_0x0023
            r1.close()
        L_0x0023:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.helper.DataUtil.a(java.io.File):java.nio.ByteBuffer");
    }

    static ByteBuffer a() {
        return ByteBuffer.allocate(0);
    }

    static String a(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = d.matcher(str);
        if (matcher.find()) {
            return b(matcher.group(1).trim().replace("charset=", ""));
        }
        return null;
    }

    private static String b(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String replaceAll = str.trim().replaceAll("[\"']", "");
        try {
            if (Charset.isSupported(replaceAll)) {
                return replaceAll;
            }
            String upperCase = replaceAll.toUpperCase(Locale.ENGLISH);
            if (Charset.isSupported(upperCase)) {
                return upperCase;
            }
            return null;
        } catch (IllegalCharsetNameException unused) {
        }
    }

    static String b() {
        StringBuilder sb = new StringBuilder(32);
        Random random = new Random();
        for (int i = 0; i < 32; i++) {
            sb.append(f[random.nextInt(f.length)]);
        }
        return sb.toString();
    }

    private static BomCharset a(ByteBuffer byteBuffer) {
        byteBuffer.mark();
        byte[] bArr = new byte[4];
        if (byteBuffer.remaining() >= bArr.length) {
            byteBuffer.get(bArr);
            byteBuffer.rewind();
        }
        if ((bArr[0] == 0 && bArr[1] == 0 && bArr[2] == -2 && bArr[3] == -1) || (bArr[0] == -1 && bArr[1] == -2 && bArr[2] == 0 && bArr[3] == 0)) {
            return new BomCharset("UTF-32", 0);
        }
        if ((bArr[0] == -2 && bArr[1] == -1) || (bArr[0] == -1 && bArr[1] == -2)) {
            return new BomCharset("UTF-16", 0);
        }
        if (bArr[0] == -17 && bArr[1] == -69 && bArr[2] == -65) {
            return new BomCharset("UTF-8", 3);
        }
        return null;
    }

    private static class BomCharset {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public final String f3648a;
        /* access modifiers changed from: private */
        public final int b;

        public BomCharset(String str, int i) {
            this.f3648a = str;
            this.b = i;
        }
    }
}
