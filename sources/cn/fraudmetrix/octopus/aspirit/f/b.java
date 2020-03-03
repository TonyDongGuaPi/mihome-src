package cn.fraudmetrix.octopus.aspirit.f;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.HashMap;

public class b implements a {

    /* renamed from: a  reason: collision with root package name */
    private SharedPreferences f641a;
    private HashMap<String, String> b = new HashMap<>();

    public b(Context context) {
        this.f641a = context.getSharedPreferences("octopusSettings", 0);
    }

    public String a(String str) {
        return TextUtils.isEmpty(str) ? "" : this.b.containsKey(str) ? this.b.get(str) : this.f641a.getString(str, "");
    }

    public void a(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            if ("0".equals(str3)) {
                this.b.put(str, str2);
            } else if ("1".equals(str3)) {
                this.f641a.edit().putString(str, str2).commit();
            }
        }
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.b.remove(str);
            this.f641a.edit().remove(str).commit();
        }
    }
}
