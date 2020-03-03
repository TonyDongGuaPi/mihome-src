package com.huawei.hms.update.a;

import com.huawei.hms.c.c;
import com.huawei.hms.support.log.a;
import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

class d {

    /* renamed from: a  reason: collision with root package name */
    private String f5905a = "";
    private String b = "";
    private String c = "";
    private String d = "";
    private String e = "";
    private String f = "";
    private String g = "";
    private int h = 0;

    d() {
    }

    public String a() {
        return this.b;
    }

    public int b() {
        try {
            return Integer.parseInt(this.c);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public String c() {
        return this.d;
    }

    public int d() {
        return this.h;
    }

    public String toString() {
        return Operators.BLOCK_START + "Name: " + this.f5905a + ", " + "File: " + this.b + ", " + "Size: " + this.c + ", " + "Hash: " + this.d + ", " + "PackageName: " + this.e + ", " + "PackageType: " + this.f + ", " + "VersionName: " + this.g + ", " + "VersionCode: " + this.h + Operators.BLOCK_END;
    }

    public static d a(String str) {
        d dVar = new d();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes(Charset.defaultCharset()));
        try {
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            newPullParser.setInput(byteArrayInputStream, "UTF-8");
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType == 2) {
                    a(dVar, newPullParser);
                }
            }
            return dVar;
        } catch (IOException | XmlPullParserException e2) {
            a.d("FilelistResponse", "In parseResponse, Failed to parse xml for get-filelist response." + e2.getMessage());
            return new d();
        } finally {
            c.a((InputStream) byteArrayInputStream);
        }
    }

    private static void a(d dVar, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String name = xmlPullParser.getName();
        if (xmlPullParser.getDepth() == 3 && "name".equals(name)) {
            dVar.f5905a = xmlPullParser.nextText();
        }
        if (xmlPullParser.getDepth() != 4) {
            return;
        }
        if ("spath".equals(name)) {
            dVar.b = xmlPullParser.nextText();
        } else if ("size".equals(name)) {
            dVar.c = xmlPullParser.nextText();
        } else if ("sha256".equals(name)) {
            dVar.d = xmlPullParser.nextText();
        } else if ("packageName".equals(name)) {
            dVar.e = xmlPullParser.nextText();
        } else if ("packageType".equals(name)) {
            dVar.f = xmlPullParser.nextText();
        } else if ("versionName".equals(name)) {
            dVar.g = xmlPullParser.nextText();
        } else if ("versionCode".equals(name)) {
            dVar.h = b(xmlPullParser.nextText());
        }
    }

    private static int b(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }
}
