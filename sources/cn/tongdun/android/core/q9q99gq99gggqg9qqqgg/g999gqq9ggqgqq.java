package cn.tongdun.android.core.q9q99gq99gggqg9qqqgg;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.mi.global.shop.model.Tags;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class g999gqq9ggqgqq {
    public static synchronized Map gqg9qq9gqq9q9q(Context context) {
        Hashtable hashtable;
        ApplicationInfo applicationInfo;
        boolean z;
        String str;
        boolean z2;
        synchronized (g999gqq9ggqgqq.class) {
            PackageManager packageManager = context.getPackageManager();
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            List installedPackages = packageManager.getInstalledPackages(0);
            if (installedPackages == null || installedPackages.size() == 0) {
                Intent intent = new Intent(gqg9qq9gqq9q9q("7e1c6b0a760c7b463c4126502d4a77057512681469540a58025f", 31));
                intent.addCategory(gqg9qq9gqq9q9q("7e416b5776517b1b3c1c260d2d17775a754f644d6c506707050a11111c1a110d", 66));
                installedPackages = packageManager.queryIntentActivities(intent, 0);
            }
            Iterator it = installedPackages.iterator();
            int i = 1;
            String[] strArr = {gqg9qq9gqq9q9q("7c4461072e08241e39183452", 68), gqg9qq9gqq9q9q("7c08614b2843284b234268", 8), gqg9qq9gqq9q9q("7c78613b3e3f2a32253e273e64", 120), gqg9qq9gqq9q9q("783b67336c3a2775287f3e62386f72", 63), gqg9qq9gqq9q9q("7c706133272e3338213466", 112), gqg9qq9gqq9q9q("7c44610722033e1f79", 68), gqg9qq9gqq9q9q("7c62612137303f3e3d3a7a", 98), gqg9qq9gqq9q9q("7c136150394f265667", 19), gqg9qq9gqq9q9q("7c19615a2045205a61", 25), gqg9qq9gqq9q9q("7c28616b3c793067367c3f35", 40), gqg9qq9gqq9q9q("7c4761043c123a5f75507f4662406f0a", 71), gqg9qq9gqq9q9q("7c0d614e23472846315f70", 13), gqg9qq9gqq9q9q("7c6d612e36222f212e2867", 109), gqg9qq9gqq9q9q("7c5b611828162e1725176e", 91), gqg9qq9gqq9q9q("7c446107230e320c6a", 68), gqg9qq9gqq9q9q("7c1f615c2e512b41275a67", 31), gqg9qq9gqq9q9q("7c016142364e2d4f3112", 1), gqg9qq9gqq9q9q("7c1c615f3c433d54", 28), gqg9qq9gqq9q9q("7c746137272b3066", 116), gqg9qq9gqq9q9q("7c4b61083506244d", 75), gqg9qq9gqq9q9q("7c606123222b2e362f6d", 96), gqg9qq9gqq9q9q("7c216162226e316a22632221", 33), gqg9qq9gqq9q9q("7c3f617c2377213c", 63), gqg9qq9gqq9q9q("7c39617a2b712b68", 57), gqg9qq9gqq9q9q("7c55611620172b02371b3146", 85), gqg9qq9gqq9q9q("7c556116221e2e0d2156", 85), gqg9qq9gqq9q9q("7c56611527143d02361f2054", 86), gqg9qq9gqq9q9q("7c7f613c3e243c3a67", 127), gqg9qq9gqq9q9q("7c5d611e38003f092c182a136c", 93), gqg9qq9gqq9q9q("7c2461672265397e2463276e68", 36), gqg9qq9gqq9q9q("7c2423643873337b7c", 37), gqg9qq9gqq9q9q("7c78613b3b2c34", 120), gqg9qq9gqq9q9q("7c1761543f5624453b0e", 23), gqg9qq9gqq9q9q("7c2661653c7b306836752c6723", 38), gqg9qq9gqq9q9q("7c1b615835572910", 27), gqg9qq9gqq9q9q("7c58611b280e3f0e340f291c2615245e", 88), gqg9qq9gqq9q9q("7c6661252a36393d202f292f6b", 102), gqg9qq9gqq9q9q("7c5761143e0c3c1267", 87), gqg9qq9gqq9q9q("7c53611029043e04360c7d", 83), gqg9qq9gqq9q9q("7c2a61692e7b287d75", 42), gqg9qq9gqq9q9q("7c1b61583d4f394a3f472910", 27), gqg9qq9gqq9q9q("7c1c615f2c54255b2c542b55221c", 28), gqg9qq9gqq9q9q("7c3061732c7f2c643d6a78", 48), gqg9qq9gqq9q9q("7c09614a2e462a482209", 9), gqg9qq9gqq9q9q("7c4d610e2206230b2b1e3a107f", 77)};
            String[] strArr2 = {gqg9qq9gqq9q9q("3156205820", 23)};
            String[] strArr3 = {gqg9qq9gqq9q9q("5c314e3d4b017e0a7719621125", 22)};
            Hashtable hashtable2 = new Hashtable();
            String str2 = null;
            int i2 = 0;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (next instanceof ResolveInfo) {
                    applicationInfo = ((ResolveInfo) next).activityInfo.applicationInfo;
                    str2 = ((ResolveInfo) next).activityInfo.packageName;
                } else {
                    applicationInfo = null;
                }
                if (next instanceof PackageInfo) {
                    applicationInfo = ((PackageInfo) next).applicationInfo;
                    str2 = applicationInfo.packageName;
                }
                if ((applicationInfo.flags & i) > 0) {
                    int length = strArr.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= length) {
                            z2 = false;
                            break;
                        }
                        String str3 = strArr[i3];
                        if (str2.startsWith(str3)) {
                            String str4 = (String) hashtable2.get(str3);
                            if (str4 == null) {
                                hashtable2.put(str3, gqg9qq9gqq9q9q("2e", 72));
                            } else {
                                hashtable2.put(str3, Integer.toString(Integer.parseInt(str4) + i));
                            }
                            z2 = true;
                        } else {
                            i3++;
                        }
                    }
                    int length2 = strArr2.length;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= length2) {
                            break;
                        } else if (str2.endsWith(strArr2[i4])) {
                            z2 = true;
                            break;
                        } else {
                            i4++;
                        }
                    }
                    if (z2 || gqg9qq9gqq9q9q("7e6e6b78767e7b", 109).equals(str2)) {
                        i = 1;
                    }
                }
                i = 1;
                if ((applicationInfo.flags & 1) <= 0) {
                    int length3 = strArr3.length;
                    int i5 = 0;
                    while (true) {
                        if (i5 >= length3) {
                            z = false;
                            break;
                        } else if (str2.startsWith(strArr3[i5])) {
                            z = true;
                            break;
                        } else {
                            i5++;
                        }
                    }
                    if (!z) {
                        if (i2 < 20) {
                            str = packageManager.getApplicationLabel(applicationInfo).toString();
                        } else {
                            str = gqg9qq9gqq9q9q(Tags.Phone.M2A_PHONE, 57);
                        }
                        i2++;
                        if (i2 > 100) {
                            break;
                        }
                        if (sb.length() > 0) {
                            sb.append(gqg9qq9gqq9q9q("33", 87));
                        }
                        sb.append(str2 + gqg9qq9gqq9q9q("25", 80) + str);
                        if (sb2.length() > 0) {
                            sb2.append(gqg9qq9gqq9q9q("33", 120));
                        }
                        sb2.append(str2);
                        sb2.append(gqg9qq9gqq9q9q("25", 101));
                        sb2.append(str);
                    }
                }
            }
            for (String str5 : hashtable2.keySet()) {
                if (Integer.parseInt((String) hashtable2.get(str5)) > 5) {
                    if (sb.length() > 0) {
                        sb.append(gqg9qq9gqq9q9q("33", 83));
                    }
                    sb.append(str5 + gqg9qq9gqq9q9q("35", 112) + ((String) hashtable2.get(str5)));
                }
            }
            hashtable = new Hashtable();
            hashtable.put(gqg9qq9gqq9q9q("7c0d61107d196c08", 13), sb.toString());
            hashtable.put(gqg9qq9gqq9q9q("7b2c6c2e77337132", 33), sb2.toString());
        }
        return hashtable;
    }

    public static String gqg9qq9gqq9q9q(Context context, ActivityManager activityManager) {
        if (!gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, "android.permission.GET_TASKS")) {
            return gqg9qq9gqq9q9q("722a772a5b05461b", 34);
        }
        StringBuilder sb = new StringBuilder();
        for (ActivityManager.RunningTaskInfo runningTaskInfo : activityManager.getRunningTasks(Integer.MAX_VALUE)) {
            String packageName = runningTaskInfo.baseActivity.getPackageName();
            if (sb.length() > 0) {
                sb.append(gqg9qq9gqq9q9q("33", 110));
            }
            sb.append(packageName);
        }
        return sb.toString();
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
            byte b = (byte) (i ^ 12);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ 31);
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
