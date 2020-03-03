package cn.tongdun.android.shell.common;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import cn.tongdun.android.shell.utils.LogUtil;
import java.util.Arrays;

public class gqg9qq9gqq9q9q {
    public static String gqg9qq9gqq9q9q(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString(gqg9qq9gqq9q9q("60064300590b4e06520a590b", 109));
        } catch (Exception unused) {
            return null;
        }
    }

    public static String qgg9qgg9999g9g(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString(gqg9qq9gqq9q9q("647d4f635e63406c4c64", 23));
        } catch (Exception e) {
            LogUtil.err(gqg9qq9gqq9q9q("516b6144504b5c430359", 0) + CollectorError.catchErr(e));
            return null;
        }
    }

    public static String q9qq99qg9qqgqg9gqgg9(Context context) {
        if (context == null) {
            return "";
        }
        try {
            String packageName = context.getPackageName();
            String str = context.getPackageManager().getPackageInfo(packageName, 0).versionName;
            return packageName + gqg9qq9gqq9q9q("1a2b", 93) + str;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String q9gqqq99999qq(Context context) {
        int myPid = Process.myPid();
        try {
            for (ActivityManager.RunningAppProcessInfo next : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (next.pid == myPid) {
                    return next.processName;
                }
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String g9q9q9g9(Context context) {
        String str;
        if (Build.VERSION.SDK_INT < 21) {
            str = Arrays.toString(new String[]{Build.CPU_ABI, Build.CPU_ABI2});
        } else {
            str = Arrays.toString(Build.SUPPORTED_ABIS);
        }
        String n1 = HelperJNI.n1();
        return Build.BRAND + gqg9qq9gqq9q9q("6e24", 94) + Build.MODEL + gqg9qq9gqq9q9q("6e38", 66) + Build.VERSION.SDK_INT + gqg9qq9gqq9q9q("6e11", 107) + qqq9gg9gqq9qgg99q(context) + gqg9qq9gqq9q9q("6e7b", 1) + str + gqg9qq9gqq9q9q("6e20", 90) + n1;
    }

    static String qqq9gg9gqq9qgg99q(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return HelperJNI.simplemd5(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray());
        } catch (Exception unused) {
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
            byte b = (byte) (i ^ 122);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ 48);
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
