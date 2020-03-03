package com.xiaomi.plugin;

import android.text.TextUtils;
import com.alipay.sdk.sys.a;

public class SPM {
    String b = "";
    String c = "";
    String d = "";

    SPM(String str, String str2, String str3) {
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    public static SPM newSPM(String str, String str2, String str3) {
        return new SPM(str, str2, str3);
    }

    public String toString() {
        return String.format("%s.%s.%s", new Object[]{this.b, this.c, this.d});
    }

    public static String appendSpmrefToUrl(String str, String str2) {
        String str3;
        if (!TextUtils.isEmpty(str)) {
            if (TextUtils.isEmpty(str2)) {
                return str;
            }
            if (!str.contains("?")) {
                str3 = str + "?";
            } else {
                str3 = str + a.b;
            }
            return str3 + "spmref=" + str2;
        } else if (TextUtils.isEmpty(str2)) {
            return str;
        } else {
            return "null?spmref=" + str2;
        }
    }
}
