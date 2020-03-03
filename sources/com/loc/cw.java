package com.loc;

import com.mi.mistatistic.sdk.data.EventData;
import com.xiaomi.smarthome.core.server.debug.NetRequestWarningActivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

final class cw {
    public static final Object a(XmlPullParser xmlPullParser, String[] strArr) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        while (eventType != 2) {
            if (eventType == 3) {
                throw new XmlPullParserException("Unexpected end tag at: " + xmlPullParser.getName());
            } else if (eventType != 4) {
                try {
                    eventType = xmlPullParser.next();
                    if (eventType == 1) {
                        throw new XmlPullParserException("Unexpected end of document");
                    }
                } catch (Exception unused) {
                    throw new XmlPullParserException("Unexpected call next(): " + xmlPullParser.getName());
                }
            } else {
                throw new XmlPullParserException("Unexpected text: " + xmlPullParser.getText());
            }
        }
        return b(xmlPullParser, strArr);
    }

    private static HashMap a(XmlPullParser xmlPullParser, String str, String[] strArr) throws XmlPullParserException, IOException {
        HashMap hashMap = new HashMap();
        int eventType = xmlPullParser.getEventType();
        do {
            if (eventType == 2) {
                Object b = b(xmlPullParser, strArr);
                if (strArr[0] != null) {
                    hashMap.put(strArr[0], b);
                } else {
                    throw new XmlPullParserException("Map value without name attribute: " + xmlPullParser.getName());
                }
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

    private static void a(Object obj, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
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
                byte[] bArr = (byte[]) obj;
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
                for (byte b : bArr) {
                    int i = b >> 4;
                    sb.append(i >= 10 ? (i + 97) - 10 : i + 48);
                    byte b2 = b & 255;
                    sb.append(b2 >= 10 ? (b2 + 97) - 10 : b2 + 48);
                }
                xmlSerializer.text(sb.toString());
                xmlSerializer.endTag((String) null, "byte-array");
                return;
            } else if (obj instanceof int[]) {
                a((int[]) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof Map) {
                a((Map) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof List) {
                a((List) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof CharSequence) {
                xmlSerializer.startTag((String) null, EventData.b);
                if (str != null) {
                    xmlSerializer.attribute((String) null, "name", str);
                }
                xmlSerializer.text(obj.toString());
                xmlSerializer.endTag((String) null, EventData.b);
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

    private static void a(List list, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        String str2;
        if (list == null) {
            xmlSerializer.startTag((String) null, "null");
            str2 = "null";
        } else {
            xmlSerializer.startTag((String) null, "list");
            if (str != null) {
                xmlSerializer.attribute((String) null, "name", str);
            }
            int size = list.size();
            for (int i = 0; i < size; i++) {
                a(list.get(i), (String) null, xmlSerializer);
            }
            str2 = "list";
        }
        xmlSerializer.endTag((String) null, str2);
    }

    public static final void a(Map map, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        String str2;
        if (map == null) {
            xmlSerializer.startTag((String) null, "null");
            str2 = "null";
        } else {
            xmlSerializer.startTag((String) null, "map");
            if (str != null) {
                xmlSerializer.attribute((String) null, "name", str);
            }
            for (Map.Entry entry : map.entrySet()) {
                a(entry.getValue(), (String) entry.getKey(), xmlSerializer);
            }
            str2 = "map";
        }
        xmlSerializer.endTag((String) null, str2);
    }

    private static void a(int[] iArr, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        String str2;
        if (iArr == null) {
            xmlSerializer.startTag((String) null, "null");
            str2 = "null";
        } else {
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
            str2 = "int-array";
        }
        xmlSerializer.endTag((String) null, str2);
    }

    private static int[] a(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        try {
            int[] iArr = new int[Integer.parseInt(xmlPullParser.getAttributeValue((String) null, "num"))];
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
            throw new XmlPullParserException("Need num attribute in byte-array");
        } catch (NumberFormatException unused4) {
            throw new XmlPullParserException("Not a number in num attribute in byte-array");
        }
    }

    private static final Object b(XmlPullParser xmlPullParser, String[] strArr) throws XmlPullParserException, IOException {
        int next;
        Object d;
        Object obj = null;
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
            } else if (name.equals("int")) {
                obj = Integer.valueOf(Integer.parseInt(xmlPullParser.getAttributeValue((String) null, "value")));
            } else if (name.equals("long")) {
                obj = Long.valueOf(xmlPullParser.getAttributeValue((String) null, "value"));
            } else {
                if (name.equals("float")) {
                    d = new Float(xmlPullParser.getAttributeValue((String) null, "value"));
                } else if (name.equals("double")) {
                    d = new Double(xmlPullParser.getAttributeValue((String) null, "value"));
                } else if (name.equals("boolean")) {
                    obj = Boolean.valueOf(xmlPullParser.getAttributeValue((String) null, "value"));
                } else if (name.equals("int-array")) {
                    xmlPullParser.next();
                    int[] a2 = a(xmlPullParser, "int-array");
                    strArr[0] = attributeValue;
                    return a2;
                } else if (name.equals("map")) {
                    xmlPullParser.next();
                    HashMap a3 = a(xmlPullParser, "map", strArr);
                    strArr[0] = attributeValue;
                    return a3;
                } else if (name.equals("list")) {
                    xmlPullParser.next();
                    ArrayList b = b(xmlPullParser, "list", strArr);
                    strArr[0] = attributeValue;
                    return b;
                } else {
                    throw new XmlPullParserException("Unknown tag: " + name);
                }
                obj = d;
            }
        }
        do {
            next = xmlPullParser.next();
            if (next == 1) {
                throw new XmlPullParserException("Unexpected end of document in <" + name + ">");
            } else if (next == 3) {
                if (xmlPullParser.getName().equals(name)) {
                    strArr[0] = attributeValue;
                    return obj;
                }
                throw new XmlPullParserException("Unexpected end tag in <" + name + ">: " + xmlPullParser.getName());
            } else if (next == 4) {
                throw new XmlPullParserException("Unexpected text in <" + name + ">: " + xmlPullParser.getName());
            }
        } while (next != 2);
        throw new XmlPullParserException("Unexpected start tag in <" + name + ">: " + xmlPullParser.getName());
    }

    private static ArrayList b(XmlPullParser xmlPullParser, String str, String[] strArr) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        int eventType = xmlPullParser.getEventType();
        do {
            if (eventType == 2) {
                arrayList.add(b(xmlPullParser, strArr));
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
}
