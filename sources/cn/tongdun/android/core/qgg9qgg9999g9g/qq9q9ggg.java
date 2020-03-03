package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.tongdun.android.core.O0o0o0o0o;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.gqgqgqq9gq9q9q9;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.qgggqg999gg9qqggq;
import cn.tongdun.android.core.q9qq99qg9qqgqg9gqgg9.gqg9qq9gqq9q9q;
import cn.tongdun.android.shell.common.HelperJNI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class qq9q9ggg {
    private static final String gqg9qq9gqq9q9q = gqg9qq9gqq9q9q("071b3d5532503e5b2402600f2911", 3);
    private static int q9gqqq99999qq = 0;
    /* access modifiers changed from: private */
    public static Map q9qq99qg9qqgqg9gqgg9;
    private static final String qgg9qgg9999g9g = gqg9qq9gqq9q9q("5d253e6c20", 119);

    public static String gqg9qq9gqq9q9q(Context context) {
        q9gqqq99999qq = 0;
        String[] strArr = new String[4];
        String[] strArr2 = {"", qgg9qgg9999g9g(context), q9qq99qg9qqgqg9gqgg9(context), q9gqqq99999qq()};
        for (int i = 1; i < strArr2.length; i++) {
            String str = strArr2[i];
            if (str == null || str.length() == 0) {
                q9gqqq99999qq |= 1 << i;
            }
        }
        strArr[1] = q9qq99qg9qqgqg9gqgg9(1, strArr2[1]);
        strArr[2] = q9qq99qg9qqgqg9gqgg9(2, strArr2[2]);
        strArr[3] = q9qq99qg9qqgqg9gqgg9(3, strArr2[3]);
        if (qgggqg999gg9qqggq.gqg9qq9gqq9q9q(strArr)) {
            String q9qq99qg9qqgqg9gqgg92 = q9qq99qg9qqgqg9gqgg9();
            new Thread(new qq9gq9g9g99(context, q9qq99qg9qqgqg9gqgg92)).start();
            return q9qq99qg9qqgqg9gqgg92;
        }
        q9qq99qg9qqgqg9gqgg9 = new HashMap();
        for (String str2 : strArr) {
            if (!TextUtils.isEmpty(str2) && !gqg9qq9gqq9q9q("1d517751", 66).equals(str2)) {
                if (q9qq99qg9qqgqg9gqgg9.containsKey(str2)) {
                    q9qq99qg9qqgqg9gqgg9.put(str2, Integer.valueOf(((Integer) q9qq99qg9qqgqg9gqgg9.get(str2)).intValue() + 1));
                } else {
                    q9qq99qg9qqgqg9gqgg9.put(str2, 1);
                }
            }
        }
        TreeMap treeMap = new TreeMap(new qgggqg999gg9qqggq());
        treeMap.putAll(q9qq99qg9qqgqg9gqgg9);
        String str3 = (String) treeMap.firstKey();
        new Thread(new qq9gq9g9g99(context, str3)).start();
        if (strArr[1] != null && !str3.equals(strArr[1])) {
            q9gqqq99999qq |= 512;
        }
        if (strArr[2] != null && !str3.equals(strArr[2])) {
            q9gqqq99999qq |= 1024;
        }
        return str3;
    }

    static int gqg9qq9gqq9q9q() {
        return q9gqqq99999qq;
    }

    private static String q9qq99qg9qqgqg9gqgg9() {
        O0o0o0o0o.o0ooo0oo0oo0o = UUID.randomUUID().toString();
        HelperJNI.exprot(2, 0);
        return O0o0o0o0o.o0o0oo;
    }

    /* access modifiers changed from: private */
    public static String qgg9qgg9999g9g(int i, String str) {
        HelperJNI.exprot(3, i);
        if (O0o0o0o0o.o0OOO0ooo0o == null) {
            HelperJNI.exprot(3, i);
        }
        if (O0o0o0o0o.o0OOO0ooo0o == null || str == null) {
            return null;
        }
        try {
            return new String(gqg9qq9gqq9q9q.gqg9qq9gqq9q9q(str.getBytes(gqg9qq9gqq9q9q("065d671672", 84)), O0o0o0o0o.o0OOO0ooo0o.getBytes(gqg9qq9gqq9q9q("067d673672", 116))), gqg9qq9gqq9q9q("060f674472", 6));
        } catch (Exception unused) {
            return null;
        }
    }

    private static String q9qq99qg9qqgqg9gqgg9(int i, String str) {
        HelperJNI.exprot(3, i);
        if (O0o0o0o0o.o0OOO0ooo0o == null) {
            HelperJNI.exprot(3, i);
        }
        if (O0o0o0o0o.o0OOO0ooo0o == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new String(gqg9qq9gqq9q9q.qgg9qgg9999g9g(str.getBytes(gqg9qq9gqq9q9q("060e674572", 7)), O0o0o0o0o.o0OOO0ooo0o.getBytes(gqg9qq9gqq9q9q("0616675d72", 31))), gqg9qq9gqq9q9q("062e676572", 39));
        } catch (Exception unused) {
            q9gqqq99999qq = (16 << i) | q9gqqq99999qq;
            return null;
        }
    }

    private static String qgg9qgg9999g9g(Context context) {
        String str = (String) cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.qq9q9ggg.gqg9qq9gqq9q9q(context, gqg9qq9gqq9q9q, (Object) "");
        if (str.equals("")) {
            return null;
        }
        return str;
    }

    /* access modifiers changed from: private */
    public static void q9gqqq99999qq(Context context, String str) {
        cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.qq9q9ggg.gqg9qq9gqq9q9q(context, gqg9qq9gqq9q9q, str);
    }

    private static String q9qq99qg9qqgqg9gqgg9(Context context) {
        return gqg9qq9gqq9q9q(new File(context.getFilesDir().getAbsolutePath(), qgg9qgg9999g9g));
    }

    /* access modifiers changed from: private */
    public static void g9q9q9g9(Context context, String str) {
        gqg9qq9gqq9q9q(new File(context.getFilesDir().getAbsolutePath(), qgg9qgg9999g9g), str);
    }

    private static String q9gqqq99999qq() {
        new HashMap();
        String externalStorageState = Environment.getExternalStorageState();
        String gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q("5c3538323a212c", 97);
        if ("mounted".equals(externalStorageState)) {
            gqg9qq9gqq9q9q2 = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return HelperJNI.tdread(gqg9qq9gqq9q9q2, 10, 1);
    }

    /* access modifiers changed from: private */
    public static void qqq9gg9gqq9qgg99q(Context context, String str) {
        String externalStorageState = Environment.getExternalStorageState();
        String gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q("5c3338343a272c", 103);
        if ("mounted".equals(externalStorageState)) {
            gqg9qq9gqq9q9q2 = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        if (gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            HelperJNI.tdwrite(gqg9qq9gqq9q9q2, 10, 1, str);
        }
    }

    private static String gqg9qq9gqq9q9q(File file) {
        try {
            if (!file.exists() || !file.canRead()) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[1024];
            int read = fileInputStream.read(bArr);
            fileInputStream.close();
            if (read == -1) {
                return null;
            }
            return new String(bArr, 0, read, gqg9qq9gqq9q9q("0625676e72", 44));
        } catch (Exception unused) {
            return null;
        }
    }

    private static boolean gqg9qq9gqq9q9q(File file, String str) {
        try {
            File file2 = new File(file.toString());
            if (!file2.exists()) {
                file2.createNewFile();
            }
            if (!file.canWrite()) {
                return false;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            if (str == null) {
                return false;
            }
            fileOutputStream.write(str.getBytes(gqg9qq9gqq9q9q("0637677c72", 62)));
            fileOutputStream.close();
            if (!gqgqgqq9gq9q9q9.qgg9qgg9999g9g(9)) {
                file.getClass().getMethod(gqg9qq9gqq9q9q("002e6208550c500953075a", 48), new Class[]{Boolean.TYPE, Boolean.TYPE}).invoke(file, new Object[]{true, false});
            } else if (!file.setReadable(true, false)) {
                Runtime runtime = Runtime.getRuntime();
                runtime.exec(gqg9qq9gqq9q9q("102166236d67796779", 34) + file.getAbsolutePath());
            }
            return true;
        } catch (Exception unused) {
            return false;
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
            byte b = (byte) (i ^ 8);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ Constants.TagName.ELECTRONIC_TYPE);
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
