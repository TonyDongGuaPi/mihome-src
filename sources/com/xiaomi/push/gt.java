package com.xiaomi.push;

import android.text.TextUtils;
import com.adobe.xmp.XMPConst;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.shop.model.Tags;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.gj;
import com.xiaomi.push.gn;
import com.xiaomi.push.gp;
import com.xiaomi.push.service.am;
import com.xiaomi.push.service.av;
import com.xiaomi.push.service.e;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class gt {

    /* renamed from: a  reason: collision with root package name */
    private static XmlPullParser f12759a;

    public static gi a(String str, String str2, XmlPullParser xmlPullParser) {
        Object a2 = gs.a().a(Tags.BaiduLbs.ADDRTYPE, "xm:chat");
        if (a2 == null || !(a2 instanceof e)) {
            return null;
        }
        return ((e) a2).b(xmlPullParser);
    }

    public static gj a(XmlPullParser xmlPullParser, fu fuVar) {
        String attributeValue = xmlPullParser.getAttributeValue("", "id");
        String attributeValue2 = xmlPullParser.getAttributeValue("", "to");
        String attributeValue3 = xmlPullParser.getAttributeValue("", "from");
        String attributeValue4 = xmlPullParser.getAttributeValue("", "chid");
        gj.a a2 = gj.a.a(xmlPullParser.getAttributeValue("", "type"));
        HashMap hashMap = new HashMap();
        boolean z = false;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            hashMap.put(attributeName, xmlPullParser.getAttributeValue("", attributeName));
        }
        gj gjVar = null;
        gp gpVar = null;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals("error")) {
                    gpVar = d(xmlPullParser);
                } else {
                    gjVar = new gj();
                    gjVar.a(a(name, namespace, xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("iq")) {
                z = true;
            }
        }
        if (gjVar == null) {
            if (gj.a.f12751a == a2 || gj.a.b == a2) {
                gu guVar = new gu();
                guVar.k(attributeValue);
                guVar.m(attributeValue3);
                guVar.n(attributeValue2);
                guVar.a(gj.a.d);
                guVar.l(attributeValue4);
                guVar.a(new gp(gp.a.e));
                fuVar.a((gl) guVar);
                b.d("iq usage error. send packet in packet parser.");
                return null;
            }
            gjVar = new gv();
        }
        gjVar.k(attributeValue);
        gjVar.m(attributeValue2);
        gjVar.l(attributeValue4);
        gjVar.n(attributeValue3);
        gjVar.a(a2);
        gjVar.a(gpVar);
        gjVar.a((Map<String, String>) hashMap);
        return gjVar;
    }

    public static gl a(XmlPullParser xmlPullParser) {
        String str;
        boolean z = false;
        String str2 = null;
        if ("1".equals(xmlPullParser.getAttributeValue("", "s"))) {
            String attributeValue = xmlPullParser.getAttributeValue("", "chid");
            String attributeValue2 = xmlPullParser.getAttributeValue("", "id");
            String attributeValue3 = xmlPullParser.getAttributeValue("", "from");
            String attributeValue4 = xmlPullParser.getAttributeValue("", "to");
            String attributeValue5 = xmlPullParser.getAttributeValue("", "type");
            am.b a2 = am.a().a(attributeValue, attributeValue4);
            if (a2 == null) {
                a2 = am.a().a(attributeValue, attributeValue3);
            }
            if (a2 != null) {
                gl glVar = null;
                while (!z) {
                    int next = xmlPullParser.next();
                    if (next == 2) {
                        if (!"s".equals(xmlPullParser.getName())) {
                            throw new gf("error while receiving a encrypted message with wrong format");
                        } else if (xmlPullParser.next() == 4) {
                            String text = xmlPullParser.getText();
                            if ("5".equals(attributeValue) || "6".equals(attributeValue)) {
                                gk gkVar = new gk();
                                gkVar.l(attributeValue);
                                gkVar.b(true);
                                gkVar.n(attributeValue3);
                                gkVar.m(attributeValue4);
                                gkVar.k(attributeValue2);
                                gkVar.f(attributeValue5);
                                String[] strArr = null;
                                gi giVar = new gi("s", (String) null, strArr, strArr);
                                giVar.b(text);
                                gkVar.a(giVar);
                                return gkVar;
                            }
                            a(av.a(av.a(a2.h, attributeValue2), text));
                            f12759a.next();
                            glVar = a(f12759a);
                        } else {
                            throw new gf("error while receiving a encrypted message with wrong format");
                        }
                    } else if (next == 3 && xmlPullParser.getName().equals("message")) {
                        z = true;
                    }
                }
                if (glVar != null) {
                    return glVar;
                }
                throw new gf("error while receiving a encrypted message with wrong format");
            }
            throw new gf("the channel id is wrong while receiving a encrypted message");
        }
        gk gkVar2 = new gk();
        String attributeValue6 = xmlPullParser.getAttributeValue("", "id");
        if (attributeValue6 == null) {
            attributeValue6 = "ID_NOT_AVAILABLE";
        }
        gkVar2.k(attributeValue6);
        gkVar2.m(xmlPullParser.getAttributeValue("", "to"));
        gkVar2.n(xmlPullParser.getAttributeValue("", "from"));
        gkVar2.l(xmlPullParser.getAttributeValue("", "chid"));
        gkVar2.a(xmlPullParser.getAttributeValue("", "appid"));
        try {
            str = xmlPullParser.getAttributeValue("", FacebookRequestErrorClassification.KEY_TRANSIENT);
        } catch (Exception unused) {
            str = null;
        }
        try {
            String attributeValue7 = xmlPullParser.getAttributeValue("", "seq");
            if (!TextUtils.isEmpty(attributeValue7)) {
                gkVar2.b(attributeValue7);
            }
        } catch (Exception unused2) {
        }
        try {
            String attributeValue8 = xmlPullParser.getAttributeValue("", "mseq");
            if (!TextUtils.isEmpty(attributeValue8)) {
                gkVar2.c(attributeValue8);
            }
        } catch (Exception unused3) {
        }
        try {
            String attributeValue9 = xmlPullParser.getAttributeValue("", "fseq");
            if (!TextUtils.isEmpty(attributeValue9)) {
                gkVar2.d(attributeValue9);
            }
        } catch (Exception unused4) {
        }
        try {
            String attributeValue10 = xmlPullParser.getAttributeValue("", "status");
            if (!TextUtils.isEmpty(attributeValue10)) {
                gkVar2.e(attributeValue10);
            }
        } catch (Exception unused5) {
        }
        gkVar2.a(!TextUtils.isEmpty(str) && str.equalsIgnoreCase("true"));
        gkVar2.f(xmlPullParser.getAttributeValue("", "type"));
        String f = f(xmlPullParser);
        if (f == null || "".equals(f.trim())) {
            gl.u();
        } else {
            gkVar2.j(f);
        }
        while (!z) {
            int next2 = xmlPullParser.next();
            if (next2 == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (TextUtils.isEmpty(namespace)) {
                    namespace = "xm";
                }
                if (name.equals("subject")) {
                    String f2 = f(xmlPullParser);
                    gkVar2.g(e(xmlPullParser));
                } else if (name.equals("body")) {
                    String attributeValue11 = xmlPullParser.getAttributeValue("", "encode");
                    String e = e(xmlPullParser);
                    if (!TextUtils.isEmpty(attributeValue11)) {
                        gkVar2.a(e, attributeValue11);
                    } else {
                        gkVar2.h(e);
                    }
                } else if (name.equals(Constants.TitleMenu.TYPE_FAVORITE)) {
                    if (str2 == null) {
                        str2 = xmlPullParser.nextText();
                    }
                } else if (name.equals("error")) {
                    gkVar2.a(d(xmlPullParser));
                } else {
                    gkVar2.a(a(name, namespace, xmlPullParser));
                }
            } else if (next2 == 3 && xmlPullParser.getName().equals("message")) {
                z = true;
            }
        }
        gkVar2.i(str2);
        return gkVar2;
    }

    private static void a(byte[] bArr) {
        if (f12759a == null) {
            try {
                f12759a = XmlPullParserFactory.newInstance().newPullParser();
                f12759a.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }
        f12759a.setInput(new InputStreamReader(new ByteArrayInputStream(bArr)));
    }

    public static gn b(XmlPullParser xmlPullParser) {
        gn.b bVar = gn.b.f12754a;
        String attributeValue = xmlPullParser.getAttributeValue("", "type");
        if (attributeValue != null && !attributeValue.equals("")) {
            try {
                bVar = gn.b.valueOf(attributeValue);
            } catch (IllegalArgumentException unused) {
                PrintStream printStream = System.err;
                printStream.println("Found invalid presence type " + attributeValue);
            }
        }
        gn gnVar = new gn(bVar);
        gnVar.m(xmlPullParser.getAttributeValue("", "to"));
        gnVar.n(xmlPullParser.getAttributeValue("", "from"));
        gnVar.l(xmlPullParser.getAttributeValue("", "chid"));
        String attributeValue2 = xmlPullParser.getAttributeValue("", "id");
        if (attributeValue2 == null) {
            attributeValue2 = "ID_NOT_AVAILABLE";
        }
        gnVar.k(attributeValue2);
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals("status")) {
                    gnVar.a(xmlPullParser.nextText());
                } else if (name.equals("priority")) {
                    try {
                        gnVar.a(Integer.parseInt(xmlPullParser.nextText()));
                    } catch (NumberFormatException unused2) {
                    } catch (IllegalArgumentException unused3) {
                        gnVar.a(0);
                    }
                } else if (name.equals("show")) {
                    String nextText = xmlPullParser.nextText();
                    try {
                        gnVar.a(gn.a.valueOf(nextText));
                    } catch (IllegalArgumentException unused4) {
                        PrintStream printStream2 = System.err;
                        printStream2.println("Found invalid presence mode " + nextText);
                    }
                } else if (name.equals("error")) {
                    gnVar.a(d(xmlPullParser));
                } else {
                    gnVar.a(a(name, namespace, xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("presence")) {
                z = true;
            }
        }
        return gnVar;
    }

    public static go c(XmlPullParser xmlPullParser) {
        go goVar = null;
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                goVar = new go(xmlPullParser.getName());
            } else if (next == 3 && xmlPullParser.getName().equals("error")) {
                z = true;
            }
        }
        return goVar;
    }

    public static gp d(XmlPullParser xmlPullParser) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        String str = "-1";
        String str2 = null;
        String str3 = null;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            if (xmlPullParser.getAttributeName(i).equals("code")) {
                str = xmlPullParser.getAttributeValue("", "code");
            }
            if (xmlPullParser.getAttributeName(i).equals("type")) {
                str2 = xmlPullParser.getAttributeValue("", "type");
            }
            if (xmlPullParser.getAttributeName(i).equals("reason")) {
                str3 = xmlPullParser.getAttributeValue("", "reason");
            }
        }
        String str4 = null;
        String str5 = null;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("text")) {
                    str5 = xmlPullParser.nextText();
                } else {
                    String name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    if ("urn:ietf:params:xml:ns:xmpp-stanzas".equals(namespace)) {
                        str4 = name;
                    } else {
                        arrayList.add(a(name, namespace, xmlPullParser));
                    }
                }
            } else if (next == 3) {
                if (xmlPullParser.getName().equals("error")) {
                    z = true;
                }
            } else if (next == 4) {
                str5 = xmlPullParser.getText();
            }
        }
        return new gp(Integer.parseInt(str), str2 == null ? "cancel" : str2, str3, str4, str5, arrayList);
    }

    private static String e(XmlPullParser xmlPullParser) {
        String str = "";
        int depth = xmlPullParser.getDepth();
        while (true) {
            if (xmlPullParser.next() == 3 && xmlPullParser.getDepth() == depth) {
                return str;
            }
            str = str + xmlPullParser.getText();
        }
    }

    private static String f(XmlPullParser xmlPullParser) {
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            if (XMPConst.ak.equals(attributeName) || (TSMAuthContants.PARAM_LANGUAGE.equals(attributeName) && "xml".equals(xmlPullParser.getAttributePrefix(i)))) {
                return xmlPullParser.getAttributeValue(i);
            }
        }
        return null;
    }
}
