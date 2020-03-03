package com.adobe.xmp;

import com.adobe.xmp.impl.Utils;
import com.adobe.xmp.impl.xpath.XMPPath;
import com.adobe.xmp.impl.xpath.XMPPathParser;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.fastvideo.IOUtils;

public final class XMPPathFactory {
    private XMPPathFactory() {
    }

    public static String a(String str, int i) throws XMPException {
        if (i > 0) {
            return str + Operators.ARRAY_START + i + Operators.ARRAY_END;
        } else if (i == -1) {
            return str + "[last()]";
        } else {
            throw new XMPException("Array index must be larger than zero", 104);
        }
    }

    public static String a(String str, String str2) throws XMPException {
        c(str);
        d(str2);
        XMPPath a2 = XMPPathParser.a(str, str2);
        if (a2.a() == 2) {
            return IOUtils.f15883a + a2.a(1).b();
        }
        throw new XMPException("The field name must be simple", 102);
    }

    public static String a(String str, String str2, String str3, String str4) throws XMPException {
        XMPPath a2 = XMPPathParser.a(str2, str3);
        if (a2.a() == 2) {
            return str + Operators.ARRAY_START + a2.a(1).b() + "=\"" + str4 + "\"]";
        }
        throw new XMPException("The fieldName name must be simple", 102);
    }

    private static void a(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty qualifier namespace URI", 101);
        }
    }

    public static String b(String str, String str2) throws XMPException {
        a(str);
        b(str2);
        XMPPath a2 = XMPPathParser.a(str, str2);
        if (a2.a() == 2) {
            return "/?" + a2.a(1).b();
        }
        throw new XMPException("The qualifier name must be simple", 102);
    }

    private static void b(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty qualifier name", 102);
        }
    }

    public static String c(String str, String str2) {
        return str + "[?xml:lang=\"" + Utils.a(str2) + "\"]";
    }

    private static void c(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty field namespace URI", 101);
        }
    }

    private static void d(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty f name", 102);
        }
    }
}
