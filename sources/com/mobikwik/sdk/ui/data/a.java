package com.mobikwik.sdk.ui.data;

import android.content.Context;
import android.content.SharedPreferences;
import com.mobikwik.sdk.lib.User;
import com.mobikwik.sdk.lib.utils.Utils;
import com.taobao.weex.annotation.JSMethod;

public class a {
    public static String a(Context context, User user) {
        if (user == null) {
            return null;
        }
        String lowerCase = user.getEmail() != null ? user.getEmail().toLowerCase() : "";
        String lowerCase2 = user.getCell() != null ? user.getCell().toLowerCase() : "";
        SharedPreferences sharedPreferences = context.getSharedPreferences("SDKStorage", 0);
        return sharedPreferences.getString("token_" + lowerCase + JSMethod.NOT_SET + lowerCase2, (String) null);
    }

    public static void a(Context context, User user, String str) {
        Utils.print("putToken  " + user + ", token " + str);
        if (user != null) {
            String lowerCase = user.getEmail() != null ? user.getEmail().toLowerCase() : "";
            String lowerCase2 = user.getCell() != null ? user.getCell().toLowerCase() : "";
            SharedPreferences.Editor edit = context.getSharedPreferences("SDKStorage", 0).edit();
            edit.putString("token_" + lowerCase + JSMethod.NOT_SET + lowerCase2, str);
            edit.commit();
        }
    }
}
