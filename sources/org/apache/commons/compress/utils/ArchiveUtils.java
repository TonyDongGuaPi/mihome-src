package org.apache.commons.compress.utils;

import com.google.code.microlog4android.format.PatternFormatter;
import com.taobao.weex.el.parse.Operators;
import java.io.UnsupportedEncodingException;
import java.lang.Character;
import java.util.Arrays;
import org.apache.commons.compress.archivers.ArchiveEntry;

public class ArchiveUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3343a = 255;

    private ArchiveUtils() {
    }

    public static String a(ArchiveEntry archiveEntry) {
        StringBuilder sb = new StringBuilder();
        sb.append(archiveEntry.isDirectory() ? PatternFormatter.DATE_CONVERSION_CHAR : '-');
        String l = Long.toString(archiveEntry.getSize());
        sb.append(' ');
        for (int i = 7; i > l.length(); i--) {
            sb.append(' ');
        }
        sb.append(l);
        sb.append(' ');
        sb.append(archiveEntry.getName());
        return sb.toString();
    }

    public static boolean a(String str, byte[] bArr, int i, int i2) {
        try {
            byte[] bytes = str.getBytes("US-ASCII");
            return a(bytes, 0, bytes.length, bArr, i, i2, false);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean a(String str, byte[] bArr) {
        return a(str, bArr, 0, bArr.length);
    }

    public static byte[] a(String str) {
        try {
            return str.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String a(byte[] bArr) {
        try {
            return new String(bArr, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String a(byte[] bArr, int i, int i2) {
        try {
            return new String(bArr, i, i2, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean a(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4, boolean z) {
        int i5 = i2 < i4 ? i2 : i4;
        for (int i6 = 0; i6 < i5; i6++) {
            if (bArr[i + i6] != bArr2[i3 + i6]) {
                return false;
            }
        }
        if (i2 == i4) {
            return true;
        }
        if (!z) {
            return false;
        }
        if (i2 > i4) {
            while (i4 < i2) {
                if (bArr[i + i4] != 0) {
                    return false;
                }
                i4++;
            }
        } else {
            while (i2 < i4) {
                if (bArr2[i3 + i2] != 0) {
                    return false;
                }
                i2++;
            }
        }
        return true;
    }

    public static boolean a(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        return a(bArr, i, i2, bArr2, i3, i4, false);
    }

    public static boolean a(byte[] bArr, byte[] bArr2) {
        return a(bArr, 0, bArr.length, bArr2, 0, bArr2.length, false);
    }

    public static boolean a(byte[] bArr, byte[] bArr2, boolean z) {
        return a(bArr, 0, bArr.length, bArr2, 0, bArr2.length, z);
    }

    public static boolean b(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        return a(bArr, i, i2, bArr2, i3, i4, true);
    }

    public static boolean a(byte[] bArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static String b(String str) {
        Character.UnicodeBlock of;
        char[] charArray = str.toCharArray();
        char[] copyOf = charArray.length <= 255 ? charArray : Arrays.copyOf(charArray, 255);
        if (charArray.length > 255) {
            for (int i = 252; i < 255; i++) {
                copyOf[i] = '.';
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : copyOf) {
            if (Character.isISOControl(c) || (of = Character.UnicodeBlock.of(c)) == null || of == Character.UnicodeBlock.SPECIALS) {
                sb.append(Operators.CONDITION_IF);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
