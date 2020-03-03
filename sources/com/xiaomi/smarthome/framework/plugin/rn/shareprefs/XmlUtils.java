package com.xiaomi.smarthome.framework.plugin.rn.shareprefs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Xml;
import com.mi.mistatistic.sdk.data.EventData;
import com.xiaomi.smarthome.core.server.debug.NetRequestWarningActivity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public class XmlUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final Charset f17504a = Charset.forName("UTF-8");
    private static final String b = ":";

    public interface ReadMapCallback {
        Object a(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException;
    }

    public interface WriteMapCallback {
        void a(Object obj, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException;
    }

    public static void a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            if (next == 3 && xmlPullParser.getDepth() <= depth) {
                return;
            }
        }
    }

    public static final int a(CharSequence charSequence, String[] strArr, int i) {
        if (charSequence != null) {
            for (int i2 = 0; i2 < strArr.length; i2++) {
                if (charSequence.equals(strArr[i2])) {
                    return i2;
                }
            }
        }
        return i;
    }

    public static final boolean a(CharSequence charSequence, boolean z) {
        if (charSequence == null) {
            return z;
        }
        return charSequence.equals("1") || charSequence.equals("true") || charSequence.equals("TRUE");
    }

    public static final int a(CharSequence charSequence, int i) {
        int i2;
        int i3;
        int i4;
        if (charSequence == null) {
            return i;
        }
        String charSequence2 = charSequence.toString();
        int length = charSequence2.length();
        int i5 = 10;
        if ('-' == charSequence2.charAt(0)) {
            i3 = 1;
            i2 = -1;
        } else {
            i3 = 0;
            i2 = 1;
        }
        if ('0' != charSequence2.charAt(i3)) {
            if ('#' == charSequence2.charAt(i3)) {
                i4 = i3 + 1;
            }
            return Integer.parseInt(charSequence2.substring(i3), i5) * i2;
        } else if (i3 == length - 1) {
            return 0;
        } else {
            int i6 = i3 + 1;
            char charAt = charSequence2.charAt(i6);
            if ('x' == charAt || 'X' == charAt) {
                i4 = i3 + 2;
            } else {
                i5 = 8;
                i3 = i6;
                return Integer.parseInt(charSequence2.substring(i3), i5) * i2;
            }
        }
        i5 = 16;
        return Integer.parseInt(charSequence2.substring(i3), i5) * i2;
    }

    public static int a(String str, int i) {
        return str == null ? i : a((CharSequence) str);
    }

    public static int a(CharSequence charSequence) {
        String charSequence2 = charSequence.toString();
        int length = charSequence2.length();
        int i = 0;
        int i2 = 16;
        if ('0' == charSequence2.charAt(0)) {
            if (length - 1 == 0) {
                return 0;
            }
            char charAt = charSequence2.charAt(1);
            if ('x' == charAt || 'X' == charAt) {
                i = 2;
            } else {
                i = 1;
                i2 = 8;
            }
        } else if ('#' == charSequence2.charAt(0)) {
            i = 1;
        } else {
            i2 = 10;
        }
        return (int) Long.parseLong(charSequence2.substring(i), i2);
    }

    public static final void a(Map map, OutputStream outputStream) throws XmlPullParserException, IOException {
        XmlSerializer newSerializer = Xml.newSerializer();
        newSerializer.setOutput(outputStream, f17504a.name());
        newSerializer.startDocument((String) null, true);
        newSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
        a(map, (String) null, newSerializer);
        newSerializer.endDocument();
    }

    public static final void a(List list, OutputStream outputStream) throws XmlPullParserException, IOException {
        XmlSerializer newSerializer = Xml.newSerializer();
        newSerializer.setOutput(outputStream, f17504a.name());
        newSerializer.startDocument((String) null, true);
        newSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
        a(list, (String) null, newSerializer);
        newSerializer.endDocument();
    }

    public static final void a(Map map, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        a(map, str, xmlSerializer, (WriteMapCallback) null);
    }

    public static final void a(Map map, String str, XmlSerializer xmlSerializer, WriteMapCallback writeMapCallback) throws XmlPullParserException, IOException {
        if (map == null) {
            xmlSerializer.startTag((String) null, "null");
            xmlSerializer.endTag((String) null, "null");
            return;
        }
        xmlSerializer.startTag((String) null, "map");
        if (str != null) {
            xmlSerializer.attribute((String) null, "name", str);
        }
        a(map, xmlSerializer, writeMapCallback);
        xmlSerializer.endTag((String) null, "map");
    }

    public static final void a(Map map, XmlSerializer xmlSerializer, WriteMapCallback writeMapCallback) throws XmlPullParserException, IOException {
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                a(entry.getValue(), (String) entry.getKey(), xmlSerializer, writeMapCallback);
            }
        }
    }

    public static final void a(List list, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (list == null) {
            xmlSerializer.startTag((String) null, "null");
            xmlSerializer.endTag((String) null, "null");
            return;
        }
        xmlSerializer.startTag((String) null, "list");
        if (str != null) {
            xmlSerializer.attribute((String) null, "name", str);
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            a(list.get(i), (String) null, xmlSerializer);
        }
        xmlSerializer.endTag((String) null, "list");
    }

    public static final void a(Set set, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (set == null) {
            xmlSerializer.startTag((String) null, "null");
            xmlSerializer.endTag((String) null, "null");
            return;
        }
        xmlSerializer.startTag((String) null, "set");
        if (str != null) {
            xmlSerializer.attribute((String) null, "name", str);
        }
        for (Object a2 : set) {
            a(a2, (String) null, xmlSerializer);
        }
        xmlSerializer.endTag((String) null, "set");
    }

    public static final void a(byte[] bArr, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (bArr == null) {
            xmlSerializer.startTag((String) null, "null");
            xmlSerializer.endTag((String) null, "null");
            return;
        }
        xmlSerializer.startTag((String) null, "byte-array");
        if (str != null) {
            xmlSerializer.attribute((String) null, "name", str);
        }
        xmlSerializer.attribute((String) null, "num", Integer.toString(r7));
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            int i = (b2 >> 4) & 15;
            sb.append((char) (i >= 10 ? (i + 97) - 10 : i + 48));
            byte b3 = b2 & 15;
            sb.append((char) (b3 >= 10 ? (b3 + 97) - 10 : b3 + 48));
        }
        xmlSerializer.text(sb.toString());
        xmlSerializer.endTag((String) null, "byte-array");
    }

    public static final void a(int[] iArr, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (iArr == null) {
            xmlSerializer.startTag((String) null, "null");
            xmlSerializer.endTag((String) null, "null");
            return;
        }
        xmlSerializer.startTag((String) null, "int-array");
        if (str != null) {
            xmlSerializer.attribute((String) null, "name", str);
        }
        xmlSerializer.attribute((String) null, "num", Integer.toString(r5));
        for (int num : iArr) {
            xmlSerializer.startTag((String) null, NetRequestWarningActivity.KEY_ITEM);
            xmlSerializer.attribute((String) null, "value", Integer.toString(num));
            xmlSerializer.endTag((String) null, NetRequestWarningActivity.KEY_ITEM);
        }
        xmlSerializer.endTag((String) null, "int-array");
    }

    public static final void a(long[] jArr, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (jArr == null) {
            xmlSerializer.startTag((String) null, "null");
            xmlSerializer.endTag((String) null, "null");
            return;
        }
        xmlSerializer.startTag((String) null, "long-array");
        if (str != null) {
            xmlSerializer.attribute((String) null, "name", str);
        }
        xmlSerializer.attribute((String) null, "num", Integer.toString(r6));
        for (long l : jArr) {
            xmlSerializer.startTag((String) null, NetRequestWarningActivity.KEY_ITEM);
            xmlSerializer.attribute((String) null, "value", Long.toString(l));
            xmlSerializer.endTag((String) null, NetRequestWarningActivity.KEY_ITEM);
        }
        xmlSerializer.endTag((String) null, "long-array");
    }

    public static final void a(double[] dArr, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (dArr == null) {
            xmlSerializer.startTag((String) null, "null");
            xmlSerializer.endTag((String) null, "null");
            return;
        }
        xmlSerializer.startTag((String) null, "double-array");
        if (str != null) {
            xmlSerializer.attribute((String) null, "name", str);
        }
        xmlSerializer.attribute((String) null, "num", Integer.toString(r6));
        for (double d : dArr) {
            xmlSerializer.startTag((String) null, NetRequestWarningActivity.KEY_ITEM);
            xmlSerializer.attribute((String) null, "value", Double.toString(d));
            xmlSerializer.endTag((String) null, NetRequestWarningActivity.KEY_ITEM);
        }
        xmlSerializer.endTag((String) null, "double-array");
    }

    public static final void a(String[] strArr, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (strArr == null) {
            xmlSerializer.startTag((String) null, "null");
            xmlSerializer.endTag((String) null, "null");
            return;
        }
        xmlSerializer.startTag((String) null, "string-array");
        if (str != null) {
            xmlSerializer.attribute((String) null, "name", str);
        }
        xmlSerializer.attribute((String) null, "num", Integer.toString(r5));
        for (String attribute : strArr) {
            xmlSerializer.startTag((String) null, NetRequestWarningActivity.KEY_ITEM);
            xmlSerializer.attribute((String) null, "value", attribute);
            xmlSerializer.endTag((String) null, NetRequestWarningActivity.KEY_ITEM);
        }
        xmlSerializer.endTag((String) null, "string-array");
    }

    public static final void a(boolean[] zArr, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (zArr == null) {
            xmlSerializer.startTag((String) null, "null");
            xmlSerializer.endTag((String) null, "null");
            return;
        }
        xmlSerializer.startTag((String) null, "boolean-array");
        if (str != null) {
            xmlSerializer.attribute((String) null, "name", str);
        }
        xmlSerializer.attribute((String) null, "num", Integer.toString(r5));
        for (boolean bool : zArr) {
            xmlSerializer.startTag((String) null, NetRequestWarningActivity.KEY_ITEM);
            xmlSerializer.attribute((String) null, "value", Boolean.toString(bool));
            xmlSerializer.endTag((String) null, NetRequestWarningActivity.KEY_ITEM);
        }
        xmlSerializer.endTag((String) null, "boolean-array");
    }

    public static final void a(Object obj, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        a(obj, str, xmlSerializer, (WriteMapCallback) null);
    }

    private static final void a(Object obj, String str, XmlSerializer xmlSerializer, WriteMapCallback writeMapCallback) throws XmlPullParserException, IOException {
        String str2;
        if (obj == null) {
            xmlSerializer.startTag((String) null, "null");
            if (str != null) {
                xmlSerializer.attribute((String) null, "name", str);
            }
            xmlSerializer.endTag((String) null, "null");
        } else if (obj instanceof String) {
            xmlSerializer.startTag((String) null, EventData.b);
            if (str != null) {
                xmlSerializer.attribute((String) null, "name", str);
            }
            xmlSerializer.text(obj.toString());
            xmlSerializer.endTag((String) null, EventData.b);
        } else {
            if (obj instanceof Integer) {
                str2 = "int";
            } else if (obj instanceof Long) {
                str2 = "long";
            } else if (obj instanceof Float) {
                str2 = "float";
            } else if (obj instanceof Double) {
                str2 = "double";
            } else if (obj instanceof Boolean) {
                str2 = "boolean";
            } else if (obj instanceof byte[]) {
                a((byte[]) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof int[]) {
                a((int[]) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof long[]) {
                a((long[]) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof double[]) {
                a((double[]) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof String[]) {
                a((String[]) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof boolean[]) {
                a((boolean[]) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof Map) {
                a((Map) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof List) {
                a((List) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof Set) {
                a((Set) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof CharSequence) {
                xmlSerializer.startTag((String) null, EventData.b);
                if (str != null) {
                    xmlSerializer.attribute((String) null, "name", str);
                }
                xmlSerializer.text(obj.toString());
                xmlSerializer.endTag((String) null, EventData.b);
                return;
            } else if (writeMapCallback != null) {
                writeMapCallback.a(obj, str, xmlSerializer);
                return;
            } else {
                throw new RuntimeException("writeValueXml: unable to write value " + obj);
            }
            xmlSerializer.startTag((String) null, str2);
            if (str != null) {
                xmlSerializer.attribute((String) null, "name", str);
            }
            xmlSerializer.attribute((String) null, "value", obj.toString());
            xmlSerializer.endTag((String) null, str2);
        }
    }

    public static final HashMap<String, ?> a(InputStream inputStream) throws XmlPullParserException, IOException {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, f17504a.name());
        return (HashMap) a(newPullParser, new String[1]);
    }

    public static final ArrayList b(InputStream inputStream) throws XmlPullParserException, IOException {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, f17504a.name());
        return (ArrayList) a(newPullParser, new String[1]);
    }

    public static final HashSet c(InputStream inputStream) throws XmlPullParserException, IOException {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, (String) null);
        return (HashSet) a(newPullParser, new String[1]);
    }

    public static final HashMap<String, ?> a(XmlPullParser xmlPullParser, String str, String[] strArr) throws XmlPullParserException, IOException {
        return a(xmlPullParser, str, strArr, (ReadMapCallback) null);
    }

    public static final HashMap<String, ?> a(XmlPullParser xmlPullParser, String str, String[] strArr, ReadMapCallback readMapCallback) throws XmlPullParserException, IOException {
        HashMap<String, ?> hashMap = new HashMap<>();
        int eventType = xmlPullParser.getEventType();
        do {
            if (eventType == 2) {
                hashMap.put(strArr[0], a(xmlPullParser, strArr, readMapCallback, false));
            } else if (eventType == 3) {
                if (xmlPullParser.getName().equals(str)) {
                    return hashMap;
                }
                throw new XmlPullParserException("Expected " + str + " end tag at: " + xmlPullParser.getName());
            }
            eventType = xmlPullParser.next();
        } while (eventType != 1);
        throw new XmlPullParserException("Document ended before " + str + " end tag");
    }

    public static final ArrayMap<String, ?> b(XmlPullParser xmlPullParser, String str, String[] strArr, ReadMapCallback readMapCallback) throws XmlPullParserException, IOException {
        ArrayMap<String, ?> arrayMap = new ArrayMap<>();
        int eventType = xmlPullParser.getEventType();
        do {
            if (eventType == 2) {
                arrayMap.put(strArr[0], a(xmlPullParser, strArr, readMapCallback, true));
            } else if (eventType == 3) {
                if (xmlPullParser.getName().equals(str)) {
                    return arrayMap;
                }
                throw new XmlPullParserException("Expected " + str + " end tag at: " + xmlPullParser.getName());
            }
            eventType = xmlPullParser.next();
        } while (eventType != 1);
        throw new XmlPullParserException("Document ended before " + str + " end tag");
    }

    public static final ArrayList b(XmlPullParser xmlPullParser, String str, String[] strArr) throws XmlPullParserException, IOException {
        return a(xmlPullParser, str, strArr, (ReadMapCallback) null, false);
    }

    private static final ArrayList a(XmlPullParser xmlPullParser, String str, String[] strArr, ReadMapCallback readMapCallback, boolean z) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        int eventType = xmlPullParser.getEventType();
        do {
            if (eventType == 2) {
                arrayList.add(a(xmlPullParser, strArr, readMapCallback, z));
            } else if (eventType == 3) {
                if (xmlPullParser.getName().equals(str)) {
                    return arrayList;
                }
                throw new XmlPullParserException("Expected " + str + " end tag at: " + xmlPullParser.getName());
            }
            eventType = xmlPullParser.next();
        } while (eventType != 1);
        throw new XmlPullParserException("Document ended before " + str + " end tag");
    }

    public static final HashSet c(XmlPullParser xmlPullParser, String str, String[] strArr) throws XmlPullParserException, IOException {
        return b(xmlPullParser, str, strArr, (ReadMapCallback) null, false);
    }

    private static final HashSet b(XmlPullParser xmlPullParser, String str, String[] strArr, ReadMapCallback readMapCallback, boolean z) throws XmlPullParserException, IOException {
        HashSet hashSet = new HashSet();
        int eventType = xmlPullParser.getEventType();
        do {
            if (eventType == 2) {
                hashSet.add(a(xmlPullParser, strArr, readMapCallback, z));
            } else if (eventType == 3) {
                if (xmlPullParser.getName().equals(str)) {
                    return hashSet;
                }
                throw new XmlPullParserException("Expected " + str + " end tag at: " + xmlPullParser.getName());
            }
            eventType = xmlPullParser.next();
        } while (eventType != 1);
        throw new XmlPullParserException("Document ended before " + str + " end tag");
    }

    public static final byte[] d(XmlPullParser xmlPullParser, String str, String[] strArr) throws XmlPullParserException, IOException {
        try {
            int parseInt = Integer.parseInt(xmlPullParser.getAttributeValue((String) null, "num"));
            byte[] bArr = new byte[parseInt];
            int eventType = xmlPullParser.getEventType();
            do {
                if (eventType == 4) {
                    if (parseInt > 0) {
                        String text = xmlPullParser.getText();
                        if (text == null || text.length() != parseInt * 2) {
                            throw new XmlPullParserException("Invalid value found in byte-array: " + text);
                        }
                        for (int i = 0; i < parseInt; i++) {
                            int i2 = i * 2;
                            char charAt = text.charAt(i2);
                            char charAt2 = text.charAt(i2 + 1);
                            bArr[i] = (byte) (((charAt2 > 'a' ? (charAt2 - 'a') + 10 : charAt2 - '0') & 15) | (((charAt > 'a' ? (charAt - 'a') + 10 : charAt - '0') & 15) << 4));
                        }
                    }
                } else if (eventType == 3) {
                    if (xmlPullParser.getName().equals(str)) {
                        return bArr;
                    }
                    throw new XmlPullParserException("Expected " + str + " end tag at: " + xmlPullParser.getName());
                }
                eventType = xmlPullParser.next();
            } while (eventType != 1);
            throw new XmlPullParserException("Document ended before " + str + " end tag");
        } catch (NullPointerException unused) {
            throw new XmlPullParserException("Need num attribute in byte-array");
        } catch (NumberFormatException unused2) {
            throw new XmlPullParserException("Not a number in num attribute in byte-array");
        }
    }

    public static final int[] e(XmlPullParser xmlPullParser, String str, String[] strArr) throws XmlPullParserException, IOException {
        try {
            int parseInt = Integer.parseInt(xmlPullParser.getAttributeValue((String) null, "num"));
            xmlPullParser.next();
            int[] iArr = new int[parseInt];
            int i = 0;
            int eventType = xmlPullParser.getEventType();
            do {
                if (eventType == 2) {
                    if (xmlPullParser.getName().equals(NetRequestWarningActivity.KEY_ITEM)) {
                        try {
                            iArr[i] = Integer.parseInt(xmlPullParser.getAttributeValue((String) null, "value"));
                        } catch (NullPointerException unused) {
                            throw new XmlPullParserException("Need value attribute in item");
                        } catch (NumberFormatException unused2) {
                            throw new XmlPullParserException("Not a number in value attribute in item");
                        }
                    } else {
                        throw new XmlPullParserException("Expected item tag at: " + xmlPullParser.getName());
                    }
                } else if (eventType == 3) {
                    if (xmlPullParser.getName().equals(str)) {
                        return iArr;
                    }
                    if (xmlPullParser.getName().equals(NetRequestWarningActivity.KEY_ITEM)) {
                        i++;
                    } else {
                        throw new XmlPullParserException("Expected " + str + " end tag at: " + xmlPullParser.getName());
                    }
                }
                eventType = xmlPullParser.next();
            } while (eventType != 1);
            throw new XmlPullParserException("Document ended before " + str + " end tag");
        } catch (NullPointerException unused3) {
            throw new XmlPullParserException("Need num attribute in int-array");
        } catch (NumberFormatException unused4) {
            throw new XmlPullParserException("Not a number in num attribute in int-array");
        }
    }

    public static final long[] f(XmlPullParser xmlPullParser, String str, String[] strArr) throws XmlPullParserException, IOException {
        try {
            int parseInt = Integer.parseInt(xmlPullParser.getAttributeValue((String) null, "num"));
            xmlPullParser.next();
            long[] jArr = new long[parseInt];
            int i = 0;
            int eventType = xmlPullParser.getEventType();
            do {
                if (eventType == 2) {
                    if (xmlPullParser.getName().equals(NetRequestWarningActivity.KEY_ITEM)) {
                        try {
                            jArr[i] = Long.parseLong(xmlPullParser.getAttributeValue((String) null, "value"));
                        } catch (NullPointerException unused) {
                            throw new XmlPullParserException("Need value attribute in item");
                        } catch (NumberFormatException unused2) {
                            throw new XmlPullParserException("Not a number in value attribute in item");
                        }
                    } else {
                        throw new XmlPullParserException("Expected item tag at: " + xmlPullParser.getName());
                    }
                } else if (eventType == 3) {
                    if (xmlPullParser.getName().equals(str)) {
                        return jArr;
                    }
                    if (xmlPullParser.getName().equals(NetRequestWarningActivity.KEY_ITEM)) {
                        i++;
                    } else {
                        throw new XmlPullParserException("Expected " + str + " end tag at: " + xmlPullParser.getName());
                    }
                }
                eventType = xmlPullParser.next();
            } while (eventType != 1);
            throw new XmlPullParserException("Document ended before " + str + " end tag");
        } catch (NullPointerException unused3) {
            throw new XmlPullParserException("Need num attribute in long-array");
        } catch (NumberFormatException unused4) {
            throw new XmlPullParserException("Not a number in num attribute in long-array");
        }
    }

    public static final double[] g(XmlPullParser xmlPullParser, String str, String[] strArr) throws XmlPullParserException, IOException {
        try {
            int parseInt = Integer.parseInt(xmlPullParser.getAttributeValue((String) null, "num"));
            xmlPullParser.next();
            double[] dArr = new double[parseInt];
            int i = 0;
            int eventType = xmlPullParser.getEventType();
            do {
                if (eventType == 2) {
                    if (xmlPullParser.getName().equals(NetRequestWarningActivity.KEY_ITEM)) {
                        try {
                            dArr[i] = Double.parseDouble(xmlPullParser.getAttributeValue((String) null, "value"));
                        } catch (NullPointerException unused) {
                            throw new XmlPullParserException("Need value attribute in item");
                        } catch (NumberFormatException unused2) {
                            throw new XmlPullParserException("Not a number in value attribute in item");
                        }
                    } else {
                        throw new XmlPullParserException("Expected item tag at: " + xmlPullParser.getName());
                    }
                } else if (eventType == 3) {
                    if (xmlPullParser.getName().equals(str)) {
                        return dArr;
                    }
                    if (xmlPullParser.getName().equals(NetRequestWarningActivity.KEY_ITEM)) {
                        i++;
                    } else {
                        throw new XmlPullParserException("Expected " + str + " end tag at: " + xmlPullParser.getName());
                    }
                }
                eventType = xmlPullParser.next();
            } while (eventType != 1);
            throw new XmlPullParserException("Document ended before " + str + " end tag");
        } catch (NullPointerException unused3) {
            throw new XmlPullParserException("Need num attribute in double-array");
        } catch (NumberFormatException unused4) {
            throw new XmlPullParserException("Not a number in num attribute in double-array");
        }
    }

    public static final String[] h(XmlPullParser xmlPullParser, String str, String[] strArr) throws XmlPullParserException, IOException {
        try {
            int parseInt = Integer.parseInt(xmlPullParser.getAttributeValue((String) null, "num"));
            xmlPullParser.next();
            String[] strArr2 = new String[parseInt];
            int i = 0;
            int eventType = xmlPullParser.getEventType();
            do {
                if (eventType == 2) {
                    if (xmlPullParser.getName().equals(NetRequestWarningActivity.KEY_ITEM)) {
                        try {
                            strArr2[i] = xmlPullParser.getAttributeValue((String) null, "value");
                        } catch (NullPointerException unused) {
                            throw new XmlPullParserException("Need value attribute in item");
                        } catch (NumberFormatException unused2) {
                            throw new XmlPullParserException("Not a number in value attribute in item");
                        }
                    } else {
                        throw new XmlPullParserException("Expected item tag at: " + xmlPullParser.getName());
                    }
                } else if (eventType == 3) {
                    if (xmlPullParser.getName().equals(str)) {
                        return strArr2;
                    }
                    if (xmlPullParser.getName().equals(NetRequestWarningActivity.KEY_ITEM)) {
                        i++;
                    } else {
                        throw new XmlPullParserException("Expected " + str + " end tag at: " + xmlPullParser.getName());
                    }
                }
                eventType = xmlPullParser.next();
            } while (eventType != 1);
            throw new XmlPullParserException("Document ended before " + str + " end tag");
        } catch (NullPointerException unused3) {
            throw new XmlPullParserException("Need num attribute in string-array");
        } catch (NumberFormatException unused4) {
            throw new XmlPullParserException("Not a number in num attribute in string-array");
        }
    }

    public static final boolean[] i(XmlPullParser xmlPullParser, String str, String[] strArr) throws XmlPullParserException, IOException {
        try {
            int parseInt = Integer.parseInt(xmlPullParser.getAttributeValue((String) null, "num"));
            xmlPullParser.next();
            boolean[] zArr = new boolean[parseInt];
            int i = 0;
            int eventType = xmlPullParser.getEventType();
            do {
                if (eventType == 2) {
                    if (xmlPullParser.getName().equals(NetRequestWarningActivity.KEY_ITEM)) {
                        try {
                            zArr[i] = Boolean.parseBoolean(xmlPullParser.getAttributeValue((String) null, "value"));
                        } catch (NullPointerException unused) {
                            throw new XmlPullParserException("Need value attribute in item");
                        } catch (NumberFormatException unused2) {
                            throw new XmlPullParserException("Not a number in value attribute in item");
                        }
                    } else {
                        throw new XmlPullParserException("Expected item tag at: " + xmlPullParser.getName());
                    }
                } else if (eventType == 3) {
                    if (xmlPullParser.getName().equals(str)) {
                        return zArr;
                    }
                    if (xmlPullParser.getName().equals(NetRequestWarningActivity.KEY_ITEM)) {
                        i++;
                    } else {
                        throw new XmlPullParserException("Expected " + str + " end tag at: " + xmlPullParser.getName());
                    }
                }
                eventType = xmlPullParser.next();
            } while (eventType != 1);
            throw new XmlPullParserException("Document ended before " + str + " end tag");
        } catch (NullPointerException unused3) {
            throw new XmlPullParserException("Need num attribute in string-array");
        } catch (NumberFormatException unused4) {
            throw new XmlPullParserException("Not a number in num attribute in string-array");
        }
    }

    public static final Object a(XmlPullParser xmlPullParser, String[] strArr) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        while (eventType != 2) {
            if (eventType == 3) {
                throw new XmlPullParserException("Unexpected end tag at: " + xmlPullParser.getName());
            } else if (eventType != 4) {
                eventType = xmlPullParser.next();
                if (eventType == 1) {
                    throw new XmlPullParserException("Unexpected end of document");
                }
            } else {
                throw new XmlPullParserException("Unexpected text: " + xmlPullParser.getText());
            }
        }
        return a(xmlPullParser, strArr, (ReadMapCallback) null, false);
    }

    private static final Object a(XmlPullParser xmlPullParser, String[] strArr, ReadMapCallback readMapCallback, boolean z) throws XmlPullParserException, IOException {
        int next;
        Object obj;
        Object obj2 = null;
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "name");
        String name = xmlPullParser.getName();
        if (!name.equals("null")) {
            if (name.equals(EventData.b)) {
                String str = "";
                while (true) {
                    int next2 = xmlPullParser.next();
                    if (next2 == 1) {
                        throw new XmlPullParserException("Unexpected end of document in <string>");
                    } else if (next2 == 3) {
                        if (xmlPullParser.getName().equals(EventData.b)) {
                            strArr[0] = attributeValue;
                            return str;
                        }
                        throw new XmlPullParserException("Unexpected end tag in <string>: " + xmlPullParser.getName());
                    } else if (next2 == 4) {
                        str = str + xmlPullParser.getText();
                    } else if (next2 == 2) {
                        throw new XmlPullParserException("Unexpected start tag in <string>: " + xmlPullParser.getName());
                    }
                }
            } else {
                obj2 = j(xmlPullParser, name);
                if (obj2 == null) {
                    if (name.equals("byte-array")) {
                        byte[] d = d(xmlPullParser, "byte-array", strArr);
                        strArr[0] = attributeValue;
                        return d;
                    } else if (name.equals("int-array")) {
                        int[] e = e(xmlPullParser, "int-array", strArr);
                        strArr[0] = attributeValue;
                        return e;
                    } else if (name.equals("long-array")) {
                        long[] f = f(xmlPullParser, "long-array", strArr);
                        strArr[0] = attributeValue;
                        return f;
                    } else if (name.equals("double-array")) {
                        double[] g = g(xmlPullParser, "double-array", strArr);
                        strArr[0] = attributeValue;
                        return g;
                    } else if (name.equals("string-array")) {
                        String[] h = h(xmlPullParser, "string-array", strArr);
                        strArr[0] = attributeValue;
                        return h;
                    } else if (name.equals("boolean-array")) {
                        boolean[] i = i(xmlPullParser, "boolean-array", strArr);
                        strArr[0] = attributeValue;
                        return i;
                    } else if (name.equals("map")) {
                        xmlPullParser.next();
                        if (z) {
                            obj = b(xmlPullParser, "map", strArr, readMapCallback);
                        } else {
                            obj = a(xmlPullParser, "map", strArr, readMapCallback);
                        }
                        strArr[0] = attributeValue;
                        return obj;
                    } else if (name.equals("list")) {
                        xmlPullParser.next();
                        ArrayList a2 = a(xmlPullParser, "list", strArr, readMapCallback, z);
                        strArr[0] = attributeValue;
                        return a2;
                    } else if (name.equals("set")) {
                        xmlPullParser.next();
                        HashSet b2 = b(xmlPullParser, "set", strArr, readMapCallback, z);
                        strArr[0] = attributeValue;
                        return b2;
                    } else if (readMapCallback != null) {
                        Object a3 = readMapCallback.a(xmlPullParser, name);
                        strArr[0] = attributeValue;
                        return a3;
                    } else {
                        throw new XmlPullParserException("Unknown tag: " + name);
                    }
                }
            }
        }
        do {
            next = xmlPullParser.next();
            if (next == 1) {
                throw new XmlPullParserException("Unexpected end of document in <" + name + ">");
            } else if (next == 3) {
                if (xmlPullParser.getName().equals(name)) {
                    strArr[0] = attributeValue;
                    return obj2;
                }
                throw new XmlPullParserException("Unexpected end tag in <" + name + ">: " + xmlPullParser.getName());
            } else if (next == 4) {
                throw new XmlPullParserException("Unexpected text in <" + name + ">: " + xmlPullParser.getName());
            }
        } while (next != 2);
        throw new XmlPullParserException("Unexpected start tag in <" + name + ">: " + xmlPullParser.getName());
    }

    private static final Object j(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        try {
            if (str.equals("int")) {
                return Integer.valueOf(Integer.parseInt(xmlPullParser.getAttributeValue((String) null, "value")));
            }
            if (str.equals("long")) {
                return Long.valueOf(xmlPullParser.getAttributeValue((String) null, "value"));
            }
            if (str.equals("float")) {
                return new Float(xmlPullParser.getAttributeValue((String) null, "value"));
            }
            if (str.equals("double")) {
                return new Double(xmlPullParser.getAttributeValue((String) null, "value"));
            }
            if (str.equals("boolean")) {
                return Boolean.valueOf(xmlPullParser.getAttributeValue((String) null, "value"));
            }
            return null;
        } catch (NullPointerException unused) {
            throw new XmlPullParserException("Need value attribute in <" + str + ">");
        } catch (NumberFormatException unused2) {
            throw new XmlPullParserException("Not a number in value attribute in <" + str + ">");
        }
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public static final void a(org.xmlpull.v1.XmlPullParser r3, java.lang.String r4) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        L_0x0000:
            int r0 = r3.next()
            r1 = 2
            if (r0 == r1) goto L_0x000b
            r2 = 1
            if (r0 == r2) goto L_0x000b
            goto L_0x0000
        L_0x000b:
            if (r0 != r1) goto L_0x003b
            java.lang.String r0 = r3.getName()
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x0018
            return
        L_0x0018:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unexpected start tag: found "
            r1.append(r2)
            java.lang.String r3 = r3.getName()
            r1.append(r3)
            java.lang.String r3 = ", expected "
            r1.append(r3)
            r1.append(r4)
            java.lang.String r3 = r1.toString()
            r0.<init>(r3)
            throw r0
        L_0x003b:
            org.xmlpull.v1.XmlPullParserException r3 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r4 = "No start tag found"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.shareprefs.XmlUtils.a(org.xmlpull.v1.XmlPullParser, java.lang.String):void");
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public static final void b(org.xmlpull.v1.XmlPullParser r2) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        L_0x0000:
            int r0 = r2.next()
            r1 = 2
            if (r0 == r1) goto L_0x000b
            r1 = 1
            if (r0 == r1) goto L_0x000b
            goto L_0x0000
        L_0x000b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.shareprefs.XmlUtils.b(org.xmlpull.v1.XmlPullParser):void");
    }

    public static boolean a(XmlPullParser xmlPullParser, int i) throws IOException, XmlPullParserException {
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return false;
            }
            if (next == 3 && xmlPullParser.getDepth() == i) {
                return false;
            }
            if (next == 2 && xmlPullParser.getDepth() == i + 1) {
                return true;
            }
        }
    }

    public static int a(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        if (TextUtils.isEmpty(attributeValue)) {
            return i;
        }
        try {
            return Integer.parseInt(attributeValue);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static int b(XmlPullParser xmlPullParser, String str) throws IOException {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        try {
            return Integer.parseInt(attributeValue);
        } catch (NumberFormatException unused) {
            throw new ProtocolException("problem parsing " + str + "=" + attributeValue + " as int");
        }
    }

    public static void a(XmlSerializer xmlSerializer, String str, int i) throws IOException {
        xmlSerializer.attribute((String) null, str, Integer.toString(i));
    }

    public static long a(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        if (TextUtils.isEmpty(attributeValue)) {
            return j;
        }
        try {
            return Long.parseLong(attributeValue);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public static long c(XmlPullParser xmlPullParser, String str) throws IOException {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        try {
            return Long.parseLong(attributeValue);
        } catch (NumberFormatException unused) {
            throw new ProtocolException("problem parsing " + str + "=" + attributeValue + " as long");
        }
    }

    public static void a(XmlSerializer xmlSerializer, String str, long j) throws IOException {
        xmlSerializer.attribute((String) null, str, Long.toString(j));
    }

    public static float d(XmlPullParser xmlPullParser, String str) throws IOException {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        try {
            return Float.parseFloat(attributeValue);
        } catch (NumberFormatException unused) {
            throw new ProtocolException("problem parsing " + str + "=" + attributeValue + " as long");
        }
    }

    public static void a(XmlSerializer xmlSerializer, String str, float f) throws IOException {
        xmlSerializer.attribute((String) null, str, Float.toString(f));
    }

    public static boolean e(XmlPullParser xmlPullParser, String str) {
        return Boolean.parseBoolean(xmlPullParser.getAttributeValue((String) null, str));
    }

    public static boolean a(XmlPullParser xmlPullParser, String str, boolean z) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        if (attributeValue == null) {
            return z;
        }
        return Boolean.parseBoolean(attributeValue);
    }

    public static void a(XmlSerializer xmlSerializer, String str, boolean z) throws IOException {
        xmlSerializer.attribute((String) null, str, Boolean.toString(z));
    }

    public static Uri f(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        if (attributeValue != null) {
            return Uri.parse(attributeValue);
        }
        return null;
    }

    public static void a(XmlSerializer xmlSerializer, String str, Uri uri) throws IOException {
        if (uri != null) {
            xmlSerializer.attribute((String) null, str, uri.toString());
        }
    }

    public static String g(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue((String) null, str);
    }

    public static void a(XmlSerializer xmlSerializer, String str, CharSequence charSequence) throws IOException {
        if (charSequence != null) {
            xmlSerializer.attribute((String) null, str, charSequence.toString());
        }
    }

    public static byte[] h(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        if (attributeValue != null) {
            return Base64.decode(attributeValue, 0);
        }
        return null;
    }

    public static void a(XmlSerializer xmlSerializer, String str, byte[] bArr) throws IOException {
        if (bArr != null) {
            xmlSerializer.attribute((String) null, str, Base64.encodeToString(bArr, 0));
        }
    }

    public static Bitmap i(XmlPullParser xmlPullParser, String str) {
        byte[] h = h(xmlPullParser, str);
        if (h != null) {
            return BitmapFactory.decodeByteArray(h, 0, h.length);
        }
        return null;
    }

    @Deprecated
    public static void a(XmlSerializer xmlSerializer, String str, Bitmap bitmap) throws IOException {
        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
            a(xmlSerializer, str, byteArrayOutputStream.toByteArray());
        }
    }
}
