package com.huawei.hms.support.api.push.a.a.a;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;

public class c {

    /* renamed from: a  reason: collision with root package name */
    protected SharedPreferences f5892a;

    public boolean a(String str) {
        return this.f5892a != null && this.f5892a.getBoolean(str, false);
    }

    public String b(String str) {
        return this.f5892a != null ? this.f5892a.getString(str, "") : "";
    }

    public c(Context context, String str) {
        if (context != null) {
            this.f5892a = context.getSharedPreferences(str, 4);
            return;
        }
        throw new NullPointerException("context is null!");
    }

    public boolean a(String str, Object obj) {
        SharedPreferences.Editor edit = this.f5892a.edit();
        if (obj instanceof String) {
            edit.putString(str, String.valueOf(obj));
        } else if (obj instanceof Integer) {
            edit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Short) {
            edit.putInt(str, ((Short) obj).shortValue());
        } else if (obj instanceof Byte) {
            edit.putInt(str, ((Byte) obj).byteValue());
        } else if (obj instanceof Long) {
            edit.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof Float) {
            edit.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Double) {
            edit.putFloat(str, (float) ((Double) obj).doubleValue());
        } else if (obj instanceof Boolean) {
            edit.putBoolean(str, ((Boolean) obj).booleanValue());
        }
        return edit.commit();
    }

    public boolean a(String str, String str2) {
        SharedPreferences.Editor edit;
        if (this.f5892a == null || (edit = this.f5892a.edit()) == null) {
            return false;
        }
        return edit.putString(str, str2).commit();
    }

    public void a(String str, boolean z) {
        SharedPreferences.Editor edit;
        if (this.f5892a != null && (edit = this.f5892a.edit()) != null) {
            edit.putBoolean(str, z).commit();
        }
    }

    public boolean c(String str) {
        return this.f5892a != null && this.f5892a.contains(str);
    }

    public boolean d(String str) {
        if (this.f5892a == null || !this.f5892a.contains(str)) {
            return false;
        }
        SharedPreferences.Editor remove = this.f5892a.edit().remove(str);
        remove.commit();
        return remove.commit();
    }

    public Map<String, ?> a() {
        if (this.f5892a != null) {
            return this.f5892a.getAll();
        }
        return new HashMap();
    }
}
