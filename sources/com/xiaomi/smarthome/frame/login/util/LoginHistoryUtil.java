package com.xiaomi.smarthome.frame.login.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LoginHistoryUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f16349a = "sp_login_list_recent";
    private static final String b = "user.recently.mid";
    private static final int c = 4;

    public static void a(Context context) {
        String s = CoreApi.a().s();
        SharedPreferences sharedPreferences = context.getSharedPreferences(f16349a, 0);
        ArrayList<String> b2 = b(context);
        if (b2.contains(s)) {
            if (b2.size() != 1) {
                Collections.swap(b2, b2.indexOf(s), 0);
            }
        } else if (b2.size() >= 4) {
            b2.remove(0);
            b2.add(s);
            sharedPreferences.edit().putString(b, a((List<String>) b2)).apply();
        } else {
            b2.add(s);
            sharedPreferences.edit().putString(b, a((List<String>) b2)).apply();
        }
    }

    public static ArrayList<String> b(Context context) {
        String string = context.getSharedPreferences(f16349a, 0).getString(b, "");
        if (TextUtils.isEmpty(string)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(string.split(":")));
    }

    private static String a(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1) {
                sb.append(":");
            }
        }
        return sb.toString();
    }
}
