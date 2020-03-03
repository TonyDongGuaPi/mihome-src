package com.ximalaya.ting.android.opensdk.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class BaseSharedPreferencesUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2250a = (Build.VERSION.SDK_INT >= 24 ? 4 : 7);
    private SharedPreferences b;

    public BaseSharedPreferencesUtil(Context context, String str) {
        this.b = context.getSharedPreferences(str, f2250a);
    }

    public BaseSharedPreferencesUtil(Context context, String str, int i) {
        this.b = context.getSharedPreferences(str, i);
    }

    public void a(String str, long j) {
        a(this.b.edit().putLong(str, j));
    }

    public void a(String str, float f) {
        a(this.b.edit().putFloat(str, f));
    }

    public float a(String str) {
        return this.b.getFloat(str, -1.0f);
    }

    @SuppressLint({"NewApi"})
    public void a(SharedPreferences.Editor editor) {
        if (Build.VERSION.SDK_INT >= 9) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public SharedPreferences a() {
        return this.b;
    }

    public long b(String str) {
        return this.b.getLong(str, -1);
    }

    public long b(String str, long j) {
        return this.b.getLong(str, j);
    }

    public void a(String str, String str2) {
        a(this.b.edit().putString(str, str2));
    }

    public String c(String str) {
        return this.b.getString(str, "");
    }

    public void a(String str, int i) {
        a(this.b.edit().putInt(str, i));
    }

    public int b(String str, int i) {
        return this.b.getInt(str, i);
    }

    public Double d(String str) {
        try {
            return Double.valueOf(Double.parseDouble(this.b.getString(str, (String) null)));
        } catch (Exception unused) {
            return null;
        }
    }

    public Boolean e(String str) {
        try {
            return Boolean.valueOf(Boolean.parseBoolean(this.b.getString(str, (String) null)));
        } catch (Exception unused) {
            return null;
        }
    }

    public Double f(String str) {
        String string = this.b.getString(str, (String) null);
        if (string == null) {
            return null;
        }
        try {
            return Double.valueOf(Double.parseDouble(string));
        } catch (Exception unused) {
            return null;
        }
    }

    public void a(String str, Map<String, String> map) {
        a(this.b.edit().putString(str, new JSONObject(map).toString()));
    }

    public void a(String str, ConcurrentHashMap<String, Object> concurrentHashMap) {
        a(this.b.edit().putString(str, new JSONObject(concurrentHashMap).toString()));
    }

    public ConcurrentHashMap<String, String> g(String str) {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(this.b.getString(str, "{}"));
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                concurrentHashMap.put(next, jSONObject.optString(next));
            }
            return concurrentHashMap;
        } catch (Exception unused) {
            return concurrentHashMap;
        }
    }

    public HashMap<String, String> h(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(this.b.getString(str, "{}"));
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.optString(next));
            }
            return hashMap;
        } catch (Exception unused) {
            return hashMap;
        }
    }

    public void a(String str, boolean z) {
        a(this.b.edit().putBoolean(str, z));
    }

    public boolean i(String str) {
        return this.b.getBoolean(str, false);
    }

    public boolean b(String str, boolean z) {
        return this.b.getBoolean(str, z);
    }

    public void a(String str, CopyOnWriteArrayList<String> copyOnWriteArrayList) {
        a(this.b.edit().putString(str, new Gson().toJson((Object) copyOnWriteArrayList)));
    }

    public CopyOnWriteArrayList<String> j(String str) {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        try {
            JSONArray jSONArray = new JSONArray(this.b.getString(str, "{}"));
            for (int i = 0; i < jSONArray.length(); i++) {
                copyOnWriteArrayList.add(jSONArray.optString(i));
            }
            return copyOnWriteArrayList;
        } catch (Exception unused) {
            return copyOnWriteArrayList;
        }
    }

    public void a(String str, ArrayList<String> arrayList) {
        a(this.b.edit().putString(str, new Gson().toJson((Object) arrayList)));
    }

    public ArrayList<String> k(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            JSONArray jSONArray = new JSONArray(this.b.getString(str, "{}"));
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.optString(i));
            }
            return arrayList;
        } catch (Exception unused) {
            return arrayList;
        }
    }

    public void b(String str, String str2) {
        ArrayList<String> k = k(str);
        if (k != null && !k.contains(str2)) {
            k.add(str2);
        }
        a(str, k);
    }

    public void l(String str) {
        a(this.b.edit().remove(str));
    }

    public boolean m(String str) {
        return this.b.contains(str);
    }

    public void b() {
        a(this.b.edit().clear());
    }
}
