package com.Utils;

import android.content.Context;
import android.content.res.Resources;
import com.xiaomi.stat.d;

public class InternationalUtil {
    public static String a(Context context) {
        Resources resources = context.getResources();
        resources.getDisplayMetrics();
        return resources.getConfiguration().locale.getLanguage();
    }

    public static String a(long j) {
        String str = j + "";
        if (str.endsWith("1") && !str.endsWith("11")) {
            return str + "st";
        } else if (str.endsWith("2") && !str.endsWith("12")) {
            return str + "nd";
        } else if (!str.endsWith("3") || str.endsWith("13")) {
            return str + "th";
        } else {
            return str + d.Z;
        }
    }

    public static boolean b(Context context) {
        return a(context).equals("zh");
    }

    public static String a(String str) {
        char charAt = str.charAt(0);
        if (charAt >= 'a' && charAt <= 'z') {
            charAt = (char) (charAt - ' ');
        }
        char[] charArray = str.toCharArray();
        charArray[0] = charAt;
        return new String(charArray);
    }

    public static String b(String str) {
        char charAt = str.charAt(0);
        if (charAt >= 'A' && charAt <= 'Z') {
            charAt = (char) (charAt + ' ');
        }
        char[] charArray = str.toCharArray();
        charArray[0] = charAt;
        return new String(charArray);
    }
}
