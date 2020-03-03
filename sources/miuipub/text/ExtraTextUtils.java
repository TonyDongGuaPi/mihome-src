package miuipub.text;

import android.content.Context;
import com.google.code.microlog4android.format.PatternFormatter;
import com.xiaomi.smarthome.MainPageOpManager;
import miuipub.util.Pools;
import miuipub.v6.R;

public class ExtraTextUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final long f3015a = 1000;
    public static final long b = 1000000;
    public static final long c = 1000000000;
    private static final long d = 1000;
    private static final char[] e = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f'};

    protected ExtraTextUtils() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate utility class");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(byte[] r5, java.lang.Appendable r6) {
        /*
            if (r5 != 0) goto L_0x0003
            return
        L_0x0003:
            int r0 = r5.length     // Catch:{ IOException -> 0x0024 }
            r1 = 0
        L_0x0005:
            if (r1 >= r0) goto L_0x0023
            byte r2 = r5[r1]     // Catch:{ IOException -> 0x0024 }
            if (r2 >= 0) goto L_0x000d
            int r2 = r2 + 256
        L_0x000d:
            int r3 = r2 >> 4
            r2 = r2 & 15
            char[] r4 = e     // Catch:{ IOException -> 0x0024 }
            char r3 = r4[r3]     // Catch:{ IOException -> 0x0024 }
            java.lang.Appendable r3 = r6.append(r3)     // Catch:{ IOException -> 0x0024 }
            char[] r4 = e     // Catch:{ IOException -> 0x0024 }
            char r2 = r4[r2]     // Catch:{ IOException -> 0x0024 }
            r3.append(r2)     // Catch:{ IOException -> 0x0024 }
            int r1 = r1 + 1
            goto L_0x0005
        L_0x0023:
            return
        L_0x0024:
            r5 = move-exception
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            java.lang.String r0 = "Exception throw during when append"
            r6.<init>(r0, r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.text.ExtraTextUtils.a(byte[], java.lang.Appendable):void");
    }

    public static String a(byte[] bArr) {
        StringBuilder b2 = Pools.a().b();
        a(bArr, (Appendable) b2);
        String sb = b2.toString();
        Pools.a().b(b2);
        return sb;
    }

    public static byte[] a(String str) {
        int i;
        int i2;
        int length = str.length();
        if (length % 2 == 0) {
            byte[] bArr = new byte[(length >> 1)];
            for (int i3 = 0; i3 < length; i3 += 2) {
                char charAt = str.charAt(i3);
                if (charAt >= '0' && charAt <= '9') {
                    i = charAt - '0';
                } else if (charAt >= 'a' && charAt <= 'f') {
                    i = (charAt - 'a') + 10;
                } else if (charAt < 'A' || charAt > 'F') {
                    throw new IllegalArgumentException("s is not a readable string: " + str);
                } else {
                    i = (charAt - 'A') + 10;
                }
                int i4 = i << 4;
                char charAt2 = str.charAt(i3 + 1);
                if (charAt2 >= '0' && charAt2 <= '9') {
                    i2 = charAt2 - '0';
                } else if (charAt2 >= 'a' && charAt2 <= 'f') {
                    i2 = (charAt2 - 'a') + 10;
                } else if (charAt2 < 'A' || charAt2 > 'F') {
                    throw new IllegalArgumentException("s is not a readable string: " + str);
                } else {
                    i2 = (charAt2 - 'A') + 10;
                }
                bArr[i3 >> 1] = (byte) (i4 + i2);
            }
            return bArr;
        }
        throw new IllegalArgumentException("s is not a readable string: " + str);
    }

    public static String a(Context context, long j) {
        return a(context, j, false);
    }

    public static String b(Context context, long j) {
        return a(context, j, true);
    }

    private static String a(Context context, long j, boolean z) {
        String str;
        if (context == null) {
            return "";
        }
        float f = (float) j;
        int i = R.string.v6_size_byte;
        if (f > 900.0f) {
            i = R.string.v6_size_kilo_byte;
            f /= 1000.0f;
        }
        if (f > 900.0f) {
            i = R.string.v6_size_mega_byte;
            f /= 1000.0f;
        }
        if (f > 900.0f) {
            i = R.string.v6_size_giga_byte;
            f /= 1000.0f;
        }
        if (f > 900.0f) {
            i = R.string.v6_size_tera_byte;
            f /= 1000.0f;
        }
        if (f > 900.0f) {
            i = R.string.v6_size_peta_byte;
            f /= 1000.0f;
        }
        if (f < 1.0f) {
            str = String.format("%.2f", new Object[]{Float.valueOf(f)});
        } else if (f < 10.0f) {
            if (z) {
                str = String.format("%.1f", new Object[]{Float.valueOf(f)});
            } else {
                str = String.format("%.2f", new Object[]{Float.valueOf(f)});
            }
        } else if (f >= 100.0f) {
            str = String.format(MainPageOpManager.f13390a, new Object[]{Float.valueOf(f)});
        } else if (z) {
            str = String.format(MainPageOpManager.f13390a, new Object[]{Float.valueOf(f)});
        } else {
            str = String.format("%.2f", new Object[]{Float.valueOf(f)});
        }
        return context.getResources().getString(R.string.v6_size_suffix, new Object[]{str, context.getString(i)});
    }
}
