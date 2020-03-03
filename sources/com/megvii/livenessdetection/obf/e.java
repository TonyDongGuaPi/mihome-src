package com.megvii.livenessdetection.obf;

import android.content.Context;
import android.content.SharedPreferences;
import java.security.InvalidParameterException;

public final class e {

    /* renamed from: a  reason: collision with root package name */
    private String f6690a;
    private SharedPreferences b;

    private e(Context context, String str, String str2) {
        this.f6690a = "";
        if (context != null) {
            this.f6690a = str2;
            this.b = context.getApplicationContext().getSharedPreferences(str, 0);
            return;
        }
        throw new InvalidParameterException();
    }

    public e(Context context) {
        this(context, "MegviiSDKPreference", "");
    }

    public final synchronized void a(String str, String str2) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString(str + this.f6690a, str2).apply();
    }

    public final synchronized String a(String str) {
        SharedPreferences sharedPreferences;
        sharedPreferences = this.b;
        return sharedPreferences.getString(str + this.f6690a, (String) null);
    }

    public final synchronized String b(String str) {
        String string;
        SharedPreferences sharedPreferences = this.b;
        string = sharedPreferences.getString(str + this.f6690a, (String) null);
        SharedPreferences.Editor edit = this.b.edit();
        edit.remove(str + this.f6690a).apply();
        return string;
    }
}
