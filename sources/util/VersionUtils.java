package util;

import android.text.TextUtils;
import com.alipay.mobile.security.bio.config.Constant;
import me.drakeet.support.toast.BuildConfig;

public class VersionUtils {
    private static int a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return -1;
        }
        String[] split = str.split("[._]");
        String[] split2 = str2.split("[._]");
        int min = Math.min(split.length, split2.length);
        int i = 0;
        while (i < min) {
            try {
                int parseInt = Integer.parseInt(split[i]);
                int parseInt2 = Integer.parseInt(split2[i]);
                if (parseInt != parseInt2) {
                    return parseInt - parseInt2;
                }
                i++;
            } catch (Exception unused) {
                return -1;
            }
        }
        return 0;
    }

    public static boolean a(int i, String str) {
        if (i != 1) {
            switch (i) {
                case 4:
                    if (a(str, BuildConfig.f) >= 0) {
                        return true;
                    }
                    return false;
                case 5:
                    return a(str, "1.4.0") >= 0;
                default:
                    return false;
            }
        } else if (a(str, Constant.FRAMEWORK_VERSION) >= 0) {
            return true;
        } else {
            return false;
        }
    }
}
