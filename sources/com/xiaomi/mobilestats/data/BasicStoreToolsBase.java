package com.xiaomi.mobilestats.data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

public class BasicStoreToolsBase {
    private SharedPreferences L;
    private SharedPreferences M;

    private SharedPreferences m(Context context) {
        if (this.L == null && context != null) {
            this.L = context.getSharedPreferences("__XiaoMi_Stat_SendRem", 0);
        }
        return this.L;
    }

    private SharedPreferences n(Context context) {
        if (this.M == null && context != null) {
            this.M = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return this.M;
    }

    public boolean getBoolean(Context context, String str, boolean z) {
        return m(context).getBoolean(str, z);
    }

    public Float getFloatt(Context context, String str, int i) {
        return Float.valueOf(m(context).getFloat(str, (float) i));
    }

    public int getInt(Context context, String str, int i) {
        return m(context).getInt(str, i);
    }

    public long getLong(Context context, String str, long j) {
        return m(context).getLong(str, j);
    }

    public boolean getSharedBoolean(Context context, String str, boolean z) {
        return n(context).getBoolean(str, z);
    }

    public int getSharedInt(Context context, String str, int i) {
        return n(context).getInt(str, i);
    }

    public long getSharedLong(Context context, String str, long j) {
        return n(context).getLong(str, j);
    }

    public String getSharedString(Context context, String str, String str2) {
        return n(context).getString(str, str2);
    }

    public String getString(Context context, String str, String str2) {
        return m(context).getString(str, str2);
    }

    public void putBoolean(Context context, String str, boolean z) {
        m(context).edit().putBoolean(str, z).commit();
    }

    public void putFloat(Context context, String str, Float f) {
        m(context).edit().putFloat(str, f.floatValue()).commit();
    }

    public void putInt(Context context, String str, int i) {
        m(context).edit().putInt(str, i).commit();
    }

    public void putLong(Context context, String str, long j) {
        m(context).edit().putLong(str, j).commit();
    }

    public void putSharedBoolean(Context context, String str, boolean z) {
        n(context).edit().putBoolean(str, z).commit();
    }

    public void putSharedInt(Context context, String str, int i) {
        n(context).edit().putInt(str, i).commit();
    }

    public void putSharedLong(Context context, String str, long j) {
        n(context).edit().putLong(str, j).commit();
    }

    public void putSharedString(Context context, String str, String str2) {
        n(context).edit().putString(str, str2).commit();
    }

    public void putString(Context context, String str, String str2) {
        m(context).edit().putString(str, str2).commit();
    }

    public void removeShare(Context context, String str) {
        n(context).edit().remove(str).commit();
    }

    public void removeString(Context context, String str) {
        m(context).edit().remove(str).commit();
    }

    public boolean updateShareBoolean(Intent intent, Activity activity, String str) {
        return updateShareBoolean(intent, activity, str, true);
    }

    public boolean updateShareBoolean(Intent intent, Activity activity, String str, boolean z) {
        boolean booleanExtra;
        if (intent == null || (booleanExtra = intent.getBooleanExtra(str, z)) == getSharedBoolean(activity, str, z)) {
            return false;
        }
        putSharedBoolean(activity, str, booleanExtra);
        return true;
    }

    public boolean updateShareInt(Intent intent, Activity activity, String str, int i) {
        int intExtra;
        if (intent == null || (intExtra = intent.getIntExtra(str, i)) == getSharedInt(activity, str, i)) {
            return false;
        }
        putSharedInt(activity, str, intExtra);
        return true;
    }

    public boolean updateShareString(Intent intent, Activity activity, String str) {
        if (intent == null) {
            return false;
        }
        String stringExtra = intent.getStringExtra(str);
        if (stringExtra != null) {
            stringExtra = stringExtra.trim();
            if (stringExtra.length() == 0) {
                stringExtra = null;
            }
        }
        if (TextUtils.equals(stringExtra, getSharedString(activity, str, (String) null))) {
            return false;
        }
        putSharedString(activity, str, stringExtra);
        return true;
    }
}
