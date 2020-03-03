package com.xiaomi.smarthome.frame.login.logic;

import android.app.Activity;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.frame.HostSetting;

public class LoginHelper {
    public static void a(Activity activity, int i) {
    }

    public static String a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        return str + Operators.BRACKET_START_STR + i + "-" + HostSetting.e + Operators.BRACKET_END_STR;
    }

    public static String a(String str, int i, String str2) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        return str + Operators.BRACKET_START_STR + i + "-" + HostSetting.e + " " + str2 + Operators.BRACKET_END_STR;
    }
}
