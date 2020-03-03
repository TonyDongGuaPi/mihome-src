package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Vector;

public final class Strings {

    /* renamed from: a  reason: collision with root package name */
    private static String f14461a;

    static {
        try {
            f14461a = (String) AccessController.doPrivileged(new PrivilegedAction<String>() {
                /* renamed from: a */
                public String run() {
                    return System.getProperty("line.separator");
                }
            });
        } catch (Exception unused) {
            try {
                f14461a = String.format("%n", new Object[0]);
            } catch (Exception unused2) {
                f14461a = "\n";
            }
        }
    }

    public static String a(byte[] bArr) {
        char c;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < bArr.length) {
            i3++;
            if ((bArr[i2] & 240) == 240) {
                i3++;
                i2 += 4;
            } else if ((bArr[i2] & 224) == 224) {
                i2 += 3;
            } else {
                i2 = (bArr[i2] & Constants.TagName.STATION_ENAME) == 192 ? i2 + 2 : i2 + 1;
            }
        }
        char[] cArr = new char[i3];
        int i4 = 0;
        while (i < bArr.length) {
            if ((bArr[i] & 240) == 240) {
                int i5 = (((((bArr[i] & 3) << 18) | ((bArr[i + 1] & Constants.TagName.CARD_APP_ACTIVATION_STATUS) << 12)) | ((bArr[i + 2] & Constants.TagName.CARD_APP_ACTIVATION_STATUS) << 6)) | (bArr[i + 3] & Constants.TagName.CARD_APP_ACTIVATION_STATUS)) - 65536;
                c = (char) ((i5 & 1023) | 56320);
                cArr[i4] = (char) (55296 | (i5 >> 10));
                i += 4;
                i4++;
            } else if ((bArr[i] & 224) == 224) {
                c = (char) (((bArr[i] & 15) << 12) | ((bArr[i + 1] & Constants.TagName.CARD_APP_ACTIVATION_STATUS) << 6) | (bArr[i + 2] & Constants.TagName.CARD_APP_ACTIVATION_STATUS));
                i += 3;
            } else if ((bArr[i] & 208) == 208) {
                c = (char) (((bArr[i] & 31) << 6) | (bArr[i + 1] & Constants.TagName.CARD_APP_ACTIVATION_STATUS));
                i += 2;
            } else if ((bArr[i] & Constants.TagName.STATION_ENAME) == 192) {
                c = (char) (((bArr[i] & 31) << 6) | (bArr[i + 1] & Constants.TagName.CARD_APP_ACTIVATION_STATUS));
                i += 2;
            } else {
                c = (char) (bArr[i] & 255);
                i++;
            }
            cArr[i4] = c;
            i4++;
        }
        return new String(cArr);
    }

    public static byte[] a(String str) {
        return a(str.toCharArray());
    }

    public static byte[] a(char[] cArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            a(cArr, (OutputStream) byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new IllegalStateException("cannot encode string to byte array!");
        }
    }

    public static void a(char[] cArr, OutputStream outputStream) throws IOException {
        int i = 0;
        while (i < cArr.length) {
            char c = cArr[i];
            if (c < 128) {
                outputStream.write(c);
            } else if (c < 2048) {
                outputStream.write((c >> 6) | 192);
                outputStream.write((c & Operators.CONDITION_IF) | 128);
            } else if (c < 55296 || c > 57343) {
                outputStream.write((c >> 12) | 224);
                outputStream.write(((c >> 6) & 63) | 128);
                outputStream.write((c & Operators.CONDITION_IF) | 128);
            } else {
                i++;
                if (i < cArr.length) {
                    char c2 = cArr[i];
                    if (c <= 56319) {
                        int i2 = (((c & 1023) << 10) | (c2 & 1023)) + 0;
                        outputStream.write((i2 >> 18) | PsExtractor.VIDEO_STREAM_MASK);
                        outputStream.write(((i2 >> 12) & 63) | 128);
                        outputStream.write(((i2 >> 6) & 63) | 128);
                        outputStream.write((i2 & 63) | 128);
                    } else {
                        throw new IllegalStateException("invalid UTF-16 codepoint");
                    }
                } else {
                    throw new IllegalStateException("invalid UTF-16 codepoint");
                }
            }
            i++;
        }
    }

    public static String b(String str) {
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int i = 0; i != charArray.length; i++) {
            char c = charArray[i];
            if ('a' <= c && 'z' >= c) {
                charArray[i] = (char) ((c - 'a') + 65);
                z = true;
            }
        }
        return z ? new String(charArray) : str;
    }

    public static String c(String str) {
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int i = 0; i != charArray.length; i++) {
            char c = charArray[i];
            if ('A' <= c && 'Z' >= c) {
                charArray[i] = (char) ((c - 'A') + 97);
                z = true;
            }
        }
        return z ? new String(charArray) : str;
    }

    public static byte[] b(char[] cArr) {
        byte[] bArr = new byte[cArr.length];
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = (byte) cArr[i];
        }
        return bArr;
    }

    public static byte[] d(String str) {
        byte[] bArr = new byte[str.length()];
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = (byte) str.charAt(i);
        }
        return bArr;
    }

    public static int a(String str, byte[] bArr, int i) {
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            bArr[i + i2] = (byte) str.charAt(i2);
        }
        return length;
    }

    public static String b(byte[] bArr) {
        return new String(c(bArr));
    }

    public static char[] c(byte[] bArr) {
        char[] cArr = new char[bArr.length];
        for (int i = 0; i != cArr.length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        return cArr;
    }

    public static String[] a(String str, char c) {
        int i;
        Vector vector = new Vector();
        boolean z = true;
        while (true) {
            if (!z) {
                break;
            }
            int indexOf = str.indexOf(c);
            if (indexOf > 0) {
                vector.addElement(str.substring(0, indexOf));
                str = str.substring(indexOf + 1);
            } else {
                vector.addElement(str);
                z = false;
            }
        }
        String[] strArr = new String[vector.size()];
        for (i = 0; i != strArr.length; i++) {
            strArr[i] = (String) vector.elementAt(i);
        }
        return strArr;
    }

    public static StringList a() {
        return new StringListImpl();
    }

    public static String b() {
        return f14461a;
    }

    private static class StringListImpl extends ArrayList<String> implements StringList {
        private StringListImpl() {
        }

        public /* bridge */ /* synthetic */ String get(int i) {
            return (String) super.get(i);
        }

        public boolean add(String str) {
            return super.add(str);
        }

        public String set(int i, String str) {
            return (String) super.set(i, str);
        }

        public void add(int i, String str) {
            super.add(i, str);
        }

        public String[] toStringArray() {
            String[] strArr = new String[size()];
            for (int i = 0; i != strArr.length; i++) {
                strArr[i] = (String) get(i);
            }
            return strArr;
        }

        public String[] toStringArray(int i, int i2) {
            String[] strArr = new String[(i2 - i)];
            int i3 = i;
            while (i3 != size() && i3 != i2) {
                strArr[i3 - i] = (String) get(i3);
                i3++;
            }
            return strArr;
        }
    }
}
