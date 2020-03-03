package cn.fraudmetrix.octopus.aspirit.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;

public class e {

    /* renamed from: a  reason: collision with root package name */
    private final SharedPreferences f653a;

    public e(Context context) {
        this.f653a = context.getSharedPreferences(OctopusConstants.y, 4);
    }

    public Map<String, ?> a() {
        return this.f653a.getAll();
    }

    public void a(String str) {
        SharedPreferences.Editor edit = this.f653a.edit();
        edit.remove(str);
        edit.commit();
    }

    public void a(String str, Object obj) {
        SharedPreferences.Editor edit = this.f653a.edit();
        if (obj instanceof String) {
            edit.putString(str, (String) obj);
        } else if (obj instanceof Boolean) {
            edit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Integer) {
            edit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Float) {
            edit.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            edit.putLong(str, ((Long) obj).longValue());
        }
        edit.commit();
    }

    public <T> T b(String str, T t) {
        T t2 = this.f653a.getAll().get(str);
        return t2 != null ? t2 : t;
    }
}
