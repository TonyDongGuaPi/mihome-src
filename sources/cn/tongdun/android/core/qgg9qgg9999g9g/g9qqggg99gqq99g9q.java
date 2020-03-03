package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.tongdun.android.core.O0o0o0o0o;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.gqgqgqq9gq9q9q9;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.qq9q9ggg;
import cn.tongdun.android.core.q9qq99qg9qqgqg9gqgg9.gqg9qq9gqq9q9q;
import cn.tongdun.android.shell.common.HelperJNI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class g9qqggg99gqq99g9q {
    private static final String gqg9qq9gqq9q9q = gqg9qq9gqq9q9q("25324f2e432e472c4b2046", 91);
    private static final g9qqggg99gqq99g9q q9gqqq99999qq = new g9qqggg99gqq99g9q();
    private static final String qgg9qgg9999g9g = gqg9qq9gqq9q9q("5f2a3e2d36", 9);
    /* access modifiers changed from: private */
    public String q9qq99qg9qqgqg9gqgg9 = null;

    public static g9qqggg99gqq99g9q gqg9qq9gqq9q9q() {
        return q9gqqq99999qq;
    }

    private g9qqggg99gqq99g9q() {
    }

    public String gqg9qq9gqq9q9q(Context context) {
        if (context == null) {
            return null;
        }
        if (this.q9qq99qg9qqgqg9gqgg9 == null) {
            this.q9qq99qg9qqgqg9gqgg9 = q9qq99qg9qqgqg9gqgg9(context);
        }
        return this.q9qq99qg9qqgqg9gqgg9;
    }

    public void gqg9qq9gqq9q9q(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.q9qq99qg9qqgqg9gqgg9 = str;
        }
    }

    public void qgg9qgg9999g9g(Context context) {
        if (!TextUtils.isEmpty(this.q9qq99qg9qqgqg9gqgg9)) {
            new Thread(new q9gg9qqqqggqggq(this, context)).start();
        }
    }

    /* access modifiers changed from: private */
    public String qgg9qgg9999g9g(String str) {
        if (O0o0o0o0o.o0OOO0ooo0o == null || str == null) {
            return null;
        }
        try {
            return new String(gqg9qq9gqq9q9q.gqg9qq9gqq9q9q(str.getBytes(gqg9qq9gqq9q9q("041a675172", 98)), O0o0o0o0o.o0OOO0ooo0o.getBytes(gqg9qq9gqq9q9q("040a674172", 114))), gqg9qq9gqq9q9q("0472673972", 10));
        } catch (Exception unused) {
            return null;
        }
    }

    private String q9qq99qg9qqgqg9gqgg9(Context context) {
        String[] strArr = {q9gqqq99999qq(context), g9q9q9g9(context), qgg9qgg9999g9g()};
        int length = strArr.length;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            if (!TextUtils.isEmpty(strArr[i])) {
                arrayList.add(strArr[i]);
            }
        }
        int size = arrayList.size();
        String str = null;
        for (int i2 = 0; i2 < size; i2++) {
            if (str == null) {
                str = (String) arrayList.get(i2);
            }
            if (!str.equals(arrayList.get(i2))) {
                return null;
            }
        }
        if (O0o0o0o0o.o0OOO0ooo0o == null || str == null) {
            return null;
        }
        try {
            return new String(gqg9qq9gqq9q9q.qgg9qgg9999g9g(str.getBytes(gqg9qq9gqq9q9q("0449670272", 49)), O0o0o0o0o.o0OOO0ooo0o.getBytes(gqg9qq9gqq9q9q("042e676572", 86))), gqg9qq9gqq9q9q("0479673272", 1));
        } catch (Exception unused) {
            return null;
        }
    }

    private static String q9gqqq99999qq(Context context) {
        String str = (String) qq9q9ggg.gqg9qq9gqq9q9q(context, gqg9qq9gqq9q9q, (Object) "");
        if (str.equals("")) {
            return null;
        }
        return str;
    }

    /* access modifiers changed from: private */
    public static void q9gqqq99999qq(Context context, String str) {
        qq9q9ggg.gqg9qq9gqq9q9q(context, gqg9qq9gqq9q9q, str);
    }

    private static String g9q9q9g9(Context context) {
        return gqg9qq9gqq9q9q(new File(context.getFilesDir().getAbsolutePath(), qgg9qgg9999g9g));
    }

    /* access modifiers changed from: private */
    public static void g9q9q9g9(Context context, String str) {
        gqg9qq9gqq9q9q(new File(context.getFilesDir().getAbsolutePath(), qgg9qgg9999g9g), str);
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
            return new String(bArr, 0, read, gqg9qq9gqq9q9q("040e674572", 118));
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
            fileOutputStream.write(str.getBytes(gqg9qq9gqq9q9q("0425676e72", 93)));
            fileOutputStream.close();
            if (!gqgqgqq9gq9q9q9.qgg9qgg9999g9g(9)) {
                file.getClass().getMethod(gqg9qq9gqq9q9q("0228620e550a500f53015a", 71), new Class[]{Boolean.TYPE, Boolean.TYPE}).invoke(file, new Object[]{true, false});
            } else if (!file.setReadable(true, false)) {
                Runtime runtime = Runtime.getRuntime();
                runtime.exec(gqg9qq9gqq9q9q("123666346d70797079", 68) + file.getAbsolutePath());
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static String qgg9qgg9999g9g() {
        new HashMap();
        String externalStorageState = Environment.getExternalStorageState();
        String gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q("5e58385f3a4c2c", 125);
        if ("mounted".equals(externalStorageState)) {
            gqg9qq9gqq9q9q2 = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return HelperJNI.tdread(gqg9qq9gqq9q9q2, 15, 0);
    }

    /* access modifiers changed from: private */
    public static void qqq9gg9gqq9qgg99q(Context context, String str) {
        String externalStorageState = Environment.getExternalStorageState();
        String gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q("5e1138163a052c", 52);
        if ("mounted".equals(externalStorageState)) {
            gqg9qq9gqq9q9q2 = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        if (gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            HelperJNI.tdwrite(gqg9qq9gqq9q9q2, 15, 0, str);
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
            byte b = (byte) (i ^ 121);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ Constants.TagName.ELECTRONIC_TYPE_ID);
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
