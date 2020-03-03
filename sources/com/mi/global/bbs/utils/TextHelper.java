package com.mi.global.bbs.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;
import java.util.regex.Pattern;

public class TextHelper {
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void setText(TextView textView, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            textView.setText(str2);
        } else {
            textView.setText(str);
        }
    }

    public static void setQuantityString(Context context, TextView textView, int i, String str) {
        try {
            textView.setText(context.getResources().getQuantityString(i, Integer.parseInt(str), new Object[]{Integer.valueOf(Integer.parseInt(str))}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setQuantityString(Context context, TextView textView, int i, int i2) {
        try {
            textView.setText(context.getResources().getQuantityString(i, i2, new Object[]{Integer.valueOf(i2)}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getQuantityString(Context context, int i, String str) {
        try {
            return context.getResources().getQuantityString(i, Integer.parseInt(str), new Object[]{Integer.valueOf(Integer.parseInt(str))});
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean isEmail(String str) {
        return Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*").matcher(str).matches();
    }

    public static boolean isLink(String str) {
        return str.startsWith(ConnectionHelper.HTTP_PREFIX) || str.startsWith("https://");
    }
}
