package com.adobe.xmp.impl;

import com.adobe.xmp.XMPConst;

public class Utils implements XMPConst {
    public static final int ap = 4;
    public static final int aq = 36;
    private static boolean[] ar;
    private static boolean[] as;

    static {
        a();
    }

    private Utils() {
    }

    public static String a(String str) {
        if (XMPConst.aj.equals(str)) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 1;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt != ' ') {
                if (charAt == '-' || charAt == '_') {
                    stringBuffer.append('-');
                    i++;
                } else {
                    stringBuffer.append(i != 2 ? Character.toLowerCase(str.charAt(i2)) : Character.toUpperCase(str.charAt(i2)));
                }
            }
        }
        return stringBuffer.toString();
    }

    public static String a(String str, boolean z, boolean z2) {
        boolean z3;
        String str2;
        int i = 0;
        while (true) {
            if (i >= str.length()) {
                z3 = false;
                break;
            }
            char charAt = str.charAt(i);
            if (charAt == '<' || charAt == '>' || charAt == '&' || ((z2 && (charAt == 9 || charAt == 10 || charAt == 13)) || (z && charAt == '\"'))) {
                z3 = true;
            } else {
                i++;
            }
        }
        z3 = true;
        if (!z3) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer((str.length() * 4) / 3);
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt2 = str.charAt(i2);
            if (!z2 || !(charAt2 == 9 || charAt2 == 10 || charAt2 == 13)) {
                if (charAt2 == '\"') {
                    str2 = z ? "&quot;" : "\"";
                } else if (charAt2 == '&') {
                    str2 = "&amp;";
                } else if (charAt2 == '<') {
                    str2 = "&lt;";
                } else if (charAt2 == '>') {
                    str2 = "&gt;";
                }
                stringBuffer.append(str2);
            } else {
                stringBuffer.append("&#x");
                stringBuffer.append(Integer.toHexString(charAt2).toUpperCase());
                charAt2 = ';';
            }
            stringBuffer.append(charAt2);
        }
        return stringBuffer.toString();
    }

    private static void a() {
        as = new boolean[256];
        ar = new boolean[256];
        char c = 0;
        while (c < as.length) {
            boolean z = true;
            ar[c] = c == ':' || ('A' <= c && c <= 'Z') || c == '_' || (('a' <= c && c <= 'z') || ((192 <= c && c <= 214) || ((216 <= c && c <= 246) || (248 <= c && c <= 255))));
            boolean[] zArr = as;
            if (!ar[c] && c != '-' && c != '.' && (('0' > c || c > '9') && c != 183)) {
                z = false;
            }
            zArr[c] = z;
            c = (char) (c + 1);
        }
    }

    static boolean a(char c) {
        return ((c > 31 && c != 127) || c == 9 || c == 10 || c == 13) ? false : true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:72:0x0140 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean a(java.lang.String r3, java.lang.String r4) {
        /*
            java.lang.String r0 = "http://purl.org/dc/elements/1.1/"
            boolean r0 = r0.equals(r3)
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x001c
            java.lang.String r3 = "dc:format"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0141
            java.lang.String r3 = "dc:language"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0140
            goto L_0x0141
        L_0x001c:
            java.lang.String r0 = "http://ns.adobe.com/xap/1.0/"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x005c
            java.lang.String r3 = "xmp:BaseURL"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0141
            java.lang.String r3 = "xmp:CreatorTool"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0141
            java.lang.String r3 = "xmp:Format"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0141
            java.lang.String r3 = "xmp:Locale"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0141
            java.lang.String r3 = "xmp:MetadataDate"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0141
            java.lang.String r3 = "xmp:ModifyDate"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0140
            goto L_0x0141
        L_0x005c:
            java.lang.String r0 = "http://ns.adobe.com/pdf/1.3/"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x008e
            java.lang.String r3 = "pdf:BaseURL"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0141
            java.lang.String r3 = "pdf:Creator"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0141
            java.lang.String r3 = "pdf:ModDate"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0141
            java.lang.String r3 = "pdf:PDFVersion"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0141
            java.lang.String r3 = "pdf:Producer"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0140
            goto L_0x0141
        L_0x008e:
            java.lang.String r0 = "http://ns.adobe.com/tiff/1.0/"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x00b3
            java.lang.String r3 = "tiff:ImageDescription"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0140
            java.lang.String r3 = "tiff:Artist"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0140
            java.lang.String r3 = "tiff:Copyright"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0141
            goto L_0x0140
        L_0x00b3:
            java.lang.String r0 = "http://ns.adobe.com/exif/1.0/"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x00c5
            java.lang.String r3 = "exif:UserComment"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0141
            goto L_0x0140
        L_0x00c5:
            java.lang.String r0 = "http://ns.adobe.com/exif/1.0/aux/"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x00cf
            goto L_0x0141
        L_0x00cf:
            java.lang.String r0 = "http://ns.adobe.com/photoshop/1.0/"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x00e0
            java.lang.String r3 = "photoshop:ICCProfile"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0140
            goto L_0x0141
        L_0x00e0:
            java.lang.String r0 = "http://ns.adobe.com/camera-raw-settings/1.0/"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0101
            java.lang.String r3 = "crs:Version"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0141
            java.lang.String r3 = "crs:RawFileName"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0141
            java.lang.String r3 = "crs:ToneCurveName"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0140
            goto L_0x0141
        L_0x0101:
            java.lang.String r4 = "http://ns.adobe.com/StockPhoto/1.0/"
            boolean r4 = r4.equals(r3)
            if (r4 == 0) goto L_0x010a
            goto L_0x0141
        L_0x010a:
            java.lang.String r4 = "http://ns.adobe.com/xap/1.0/mm/"
            boolean r4 = r4.equals(r3)
            if (r4 == 0) goto L_0x0113
            goto L_0x0141
        L_0x0113:
            java.lang.String r4 = "http://ns.adobe.com/xap/1.0/t/"
            boolean r4 = r4.equals(r3)
            if (r4 == 0) goto L_0x011c
            goto L_0x0141
        L_0x011c:
            java.lang.String r4 = "http://ns.adobe.com/xap/1.0/t/pg/"
            boolean r4 = r4.equals(r3)
            if (r4 == 0) goto L_0x0125
            goto L_0x0141
        L_0x0125:
            java.lang.String r4 = "http://ns.adobe.com/xap/1.0/g/"
            boolean r4 = r4.equals(r3)
            if (r4 == 0) goto L_0x012e
            goto L_0x0141
        L_0x012e:
            java.lang.String r4 = "http://ns.adobe.com/xap/1.0/g/img/"
            boolean r4 = r4.equals(r3)
            if (r4 == 0) goto L_0x0137
            goto L_0x0141
        L_0x0137:
            java.lang.String r4 = "http://ns.adobe.com/xap/1.0/sType/Font#"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0140
            goto L_0x0141
        L_0x0140:
            r2 = 0
        L_0x0141:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.Utils.a(java.lang.String, java.lang.String):boolean");
    }

    private static boolean b(char c) {
        return (c <= 255 && ar[c]) || (c >= 256 && c <= 767) || ((c >= 880 && c <= 893) || ((c >= 895 && c <= 8191) || ((c >= 8204 && c <= 8205) || ((c >= 8304 && c <= 8591) || ((c >= 11264 && c <= 12271) || ((c >= 12289 && c <= 55295) || ((c >= 63744 && c <= 64975) || ((c >= 65008 && c <= 65533) || (c >= 0 && c <= 65535)))))))));
    }

    static String[] b(String str) {
        int indexOf = str.indexOf(61);
        String substring = str.substring(str.charAt(1) == '?' ? 2 : 1, indexOf);
        int i = indexOf + 1;
        char charAt = str.charAt(i);
        int i2 = i + 1;
        int length = str.length() - 2;
        StringBuffer stringBuffer = new StringBuffer(length - indexOf);
        while (i2 < length) {
            stringBuffer.append(str.charAt(i2));
            i2++;
            if (str.charAt(i2) == charAt) {
                i2++;
            }
        }
        return new String[]{substring, stringBuffer.toString()};
    }

    private static boolean c(char c) {
        return (c <= 255 && as[c]) || b(c) || (c >= 768 && c <= 879) || (c >= 8255 && c <= 8256);
    }

    static boolean c(String str) {
        if (str == null) {
            return false;
        }
        int i = 0;
        boolean z = true;
        int i2 = 0;
        while (i < str.length()) {
            if (str.charAt(i) == '-') {
                i2++;
                z = z && (i == 8 || i == 13 || i == 18 || i == 23);
            }
            i++;
        }
        return z && 4 == i2 && 36 == i;
    }

    public static boolean d(String str) {
        if (str.length() > 0 && !b(str.charAt(0))) {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            if (!c(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean e(String str) {
        if (str.length() > 0 && (!b(str.charAt(0)) || str.charAt(0) == ':')) {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            if (!c(str.charAt(i)) || str.charAt(i) == ':') {
                return false;
            }
        }
        return true;
    }

    static String f(String str) {
        StringBuffer stringBuffer = new StringBuffer(str);
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (a(stringBuffer.charAt(i))) {
                stringBuffer.setCharAt(i, ' ');
            }
        }
        return stringBuffer.toString();
    }
}
