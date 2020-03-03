package com.franmontiel.persistentcookiejar.persistence;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import okhttp3.Cookie;

@SuppressLint({"CommitPrefEdits"})
public class SharedPrefsCookiePersistor implements CookiePersistor {

    /* renamed from: a  reason: collision with root package name */
    private final SharedPreferences f5311a;

    public SharedPrefsCookiePersistor(Context context) {
        this(context.getSharedPreferences("CookiePersistence", 0));
    }

    public SharedPrefsCookiePersistor(SharedPreferences sharedPreferences) {
        this.f5311a = sharedPreferences;
    }

    public List<Cookie> a() {
        ArrayList arrayList = new ArrayList(this.f5311a.getAll().size());
        for (Map.Entry<String, ?> value : this.f5311a.getAll().entrySet()) {
            Cookie decode = new SerializableCookie().decode((String) value.getValue());
            if (decode != null) {
                arrayList.add(decode);
            }
        }
        return arrayList;
    }

    public void a(Collection<Cookie> collection) {
        SharedPreferences.Editor edit = this.f5311a.edit();
        for (Cookie next : collection) {
            edit.putString(a(next), new SerializableCookie().encode(next));
        }
        edit.commit();
    }

    public void b(Collection<Cookie> collection) {
        SharedPreferences.Editor edit = this.f5311a.edit();
        for (Cookie a2 : collection) {
            edit.remove(a(a2));
        }
        edit.commit();
    }

    private static String a(Cookie cookie) {
        StringBuilder sb = new StringBuilder();
        sb.append(cookie.secure() ? "https" : "http");
        sb.append("://");
        sb.append(cookie.domain());
        sb.append(cookie.path());
        sb.append("|");
        sb.append(cookie.name());
        return sb.toString();
    }

    public void b() {
        this.f5311a.edit().clear().commit();
    }
}
