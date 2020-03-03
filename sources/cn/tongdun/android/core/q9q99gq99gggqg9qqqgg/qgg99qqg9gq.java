package cn.tongdun.android.core.q9q99gq99gggqg9qqqgg;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.mi.global.shop.model.Tags;

public class qgg99qqg9gq {
    private static final String g9q9q9g9 = gqg9qq9gqq9q9q("680d530f5c08550a4258572a", 66);
    private static final String gqg9qq9gqq9q9q = gqg9qq9gqq9q9q("591661553d423c4033473a452d196f147b0f76047b13", 98);
    private static final String q9gqqq99999qq = gqg9qq9gqq9q9q("682353215c2655244276506a4a19", 108);
    private static final String q9qq99qg9qqgqg9gqgg9 = gqg9qq9gqq9q9q("484b73497c4e754c62", 36);
    private static final String qgg9qgg9999g9g = gqg9qq9gqq9q9q("6878390358055905", 20);

    public static boolean gqg9qq9gqq9q9q(Context context, String str) {
        if (context == null) {
            return false;
        }
        if (!TextUtils.isEmpty(str) && (str.contains(q9gqqq99999qq) || str.contains(g9q9q9g9))) {
            return true;
        }
        String str2 = Build.BRAND;
        if (str2 != null && str2.length() > 30) {
            str2 = str2.substring(0, 30);
        }
        if (q9qq99qg9qqgqg9gqgg9.equals(str2)) {
            return true;
        }
        if (qgg9qgg9999g9g.equals(Build.MODEL)) {
            return true;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        String str3 = "";
        if (gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, "android.permission.GET_TASKS")) {
            StringBuilder sb = new StringBuilder();
            for (ActivityManager.RunningTaskInfo runningTaskInfo : activityManager.getRunningTasks(Integer.MAX_VALUE)) {
                String packageName = runningTaskInfo.baseActivity.getPackageName();
                if (sb.length() > 0) {
                    sb.append(gqg9qq9gqq9q9q(Tags.Phone.M22S_PHONE, 68));
                }
                sb.append(packageName);
            }
            str3 = sb.toString();
        }
        return !TextUtils.isEmpty(str3) && str3.contains(gqg9qq9gqq9q9q);
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
            byte b = (byte) (i ^ 120);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ Constants.TagName.BUSINESS_ORDER_OP_TYPE);
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
