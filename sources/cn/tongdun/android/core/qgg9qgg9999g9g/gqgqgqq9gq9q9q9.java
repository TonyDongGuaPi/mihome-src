package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.content.Context;
import android.os.Environment;
import android.provider.Settings;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.qgggqg999gg9qqggq;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.qq9q9ggg;
import cn.tongdun.android.core.q9qq99qg9qqgqg9gqgg9.gqg9qq9gqq9q9q;
import cn.tongdun.android.shell.common.HelperJNI;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class gqgqgqq9gq9q9q9 {
    private static final String g999gqq9ggqgqq = gqg9qq9gqq9q9q("505e6e5975577355", 11);
    private static final String g9q9q9g9 = gqg9qq9gqq9q9q("465162517f567642", 15);
    private static final String gqg9qq9gqq9q9q = gqg9qq9gqq9q9q("732c6f0d7c1276145a19", 79);
    private static final String q9gqqq99999qq = gqg9qq9gqq9q9q("1b273a342e25272d362b2d3a3c37", 39);
    private static final String q9q99gq99gggqg9qqqgg = gqg9qq9gqq9q9q("5c5674516f5f695d", 25);
    private static final String q9qq99qg9qqgqg9gqgg9 = gqg9qq9gqq9q9q("537a5e4b534668696a7e696b", 57);
    private static final String qgg9qgg9999g9g = gqg9qq9gqq9q9q("53515e60536d", 18);
    private static final String qqq9gg9gqq9qgg99q = gqg9qq9gqq9q9q("46167a056d106f07", 69);

    public static String gqg9qq9gqq9q9q(Context context) {
        String str = "";
        int i = 0;
        String[] strArr = {qgg9qgg9999g9g(q9qq99qg9qqgqg9gqgg9(qgg9qgg9999g9g(context), 0), 0), qgg9qgg9999g9g(q9qq99qg9qqgqg9gqgg9(q9qq99qg9qqgqg9gqgg9(context), 1), 1), qgg9qgg9999g9g(q9qq99qg9qqgqg9gqgg9(gqg9qq9gqq9q9q(q9gqqq99999qq), 2), 2), qgg9qgg9999g9g(q9qq99qg9qqgqg9gqgg9(qgg9qgg9999g9g(q9gqqq99999qq), 3), 3)};
        int length = strArr.length;
        while (true) {
            if (i >= length) {
                break;
            }
            String str2 = strArr[i];
            if (!qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str2)) {
                str = str2;
                break;
            }
            i++;
        }
        return qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str) ? (String) qq9q9ggg.gqg9qq9gqq9q9q(context, q9qq99qg9qqgqg9gqgg9, qgg9qgg9999g9g, (Object) "") : str;
    }

    private static String qgg9qgg9999g9g(String str, int i) {
        if (str == null) {
            return str;
        }
        switch (i) {
            case 0:
                if (!str.startsWith(g9q9q9g9)) {
                    return "";
                }
                return str.substring(8);
            case 1:
                if (!str.startsWith(qqq9gg9gqq9qgg99q)) {
                    return "";
                }
                return str.substring(8);
            case 2:
                if (!str.startsWith(q9q99gq99gggqg9qqqgg)) {
                    return "";
                }
                return str.substring(8);
            case 3:
                if (!str.startsWith(g999gqq9ggqgqq)) {
                    return "";
                }
                return str.substring(8);
            default:
                return "";
        }
    }

    private static String q9qq99qg9qqgqg9gqgg9(String str, int i) {
        if (qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str)) {
            return "";
        }
        switch (i) {
            case 0:
                return gqg9qq9gqq9q9q.gqg9qq9gqq9q9q(str, g9q9q9g9);
            case 1:
                return gqg9qq9gqq9q9q.gqg9qq9gqq9q9q(str, qqq9gg9gqq9qgg99q);
            case 2:
                return gqg9qq9gqq9q9q.gqg9qq9gqq9q9q(str, q9q99gq99gggqg9qqqgg);
            case 3:
                return gqg9qq9gqq9q9q.gqg9qq9gqq9q9q(str, g999gqq9ggqgqq);
            default:
                return "";
        }
    }

    private static String qgg9qgg9999g9g(Context context) {
        String str = "";
        if (cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, "android.permission.WRITE_SETTINGS")) {
            str = Settings.System.getString(context.getContentResolver(), gqg9qq9gqq9q9q);
        }
        return str == null ? "" : str;
    }

    private static String q9qq99qg9qqgqg9gqgg9(Context context) {
        return (String) qq9q9ggg.gqg9qq9gqq9q9q(context, gqg9qq9gqq9q9q, (Object) "");
    }

    private static String gqg9qq9gqq9q9q(String str) {
        String str2 = "";
        File file = new File(gqg9qq9gqq9q9q("1a", 115));
        if (file.canWrite()) {
            str2 = gqg9qq9gqq9q9q(new File(file, str));
            if (!qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str2)) {
                return str2;
            }
        }
        String[] td_listfiles = HelperJNI.td_listfiles(gqg9qq9gqq9q9q("1a", 115), 10);
        if (td_listfiles == null) {
            return "";
        }
        String str3 = str2;
        int i = 0;
        while (i < td_listfiles.length && td_listfiles[i] != null) {
            File file2 = new File(td_listfiles[i]);
            if (!gqg9qq9gqq9q9q("1a0238053a162c", 22).equals(file2.getAbsolutePath())) {
                if (file2.canWrite()) {
                    str3 = gqg9qq9gqq9q9q(new File(file2, str));
                    if (!qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str3)) {
                        return str3;
                    }
                }
                String[] td_listfiles2 = HelperJNI.td_listfiles(file2.getAbsolutePath(), 10);
                if (td_listfiles2 == null) {
                    continue;
                } else {
                    String str4 = str3;
                    int i2 = 0;
                    while (i2 < td_listfiles2.length && td_listfiles2[i2] != null) {
                        File file3 = new File(td_listfiles2[i2]);
                        if (file3.canWrite()) {
                            str4 = gqg9qq9gqq9q9q(new File(file3, str));
                            if (!qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str4)) {
                                return str4;
                            }
                        }
                        i2++;
                    }
                    str3 = str4;
                }
            }
            i++;
        }
        return str3 == null ? "" : str3;
    }

    private static String qgg9qgg9999g9g(String str) {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            return gqg9qq9gqq9q9q(new File(Environment.getExternalStorageDirectory(), str));
        }
        return "";
    }

    private static String gqg9qq9gqq9q9q(File file) {
        try {
            if (!file.exists() || !file.canRead()) {
                return "";
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[1024];
            int read = fileInputStream.read(bArr);
            fileInputStream.close();
            if (read > 0) {
                return new String(bArr, 0, read, gqg9qq9gqq9q9q("405d671672", 20));
            }
            return "";
        } catch (IOException unused) {
            return "";
        }
    }

    public static String gqg9qq9gqq9q9q(String str, int i) {
        try {
            int length = str.length() / 2;
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) ("0123456789abcdef".indexOf(charArray[i3 + 1]) | ("0123456789abcdef".indexOf(charArray[i3]) << 4));
            }
            byte b = (byte) (i ^ 72);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ TarConstants.R);
            byte b2 = bArr[0];
            int i4 = 1;
            while (i4 < length2) {
                byte b3 = bArr[i4];
                bArr[i4] = (byte) ((b2 ^ bArr[i4]) ^ b);
                i4++;
                b2 = b3;
            }
            return new String(bArr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
