package com.xiaomi.smarthome.core.server.internal.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import java.util.Map;

public class SHConfig {
    private static SHConfig c;

    /* renamed from: a  reason: collision with root package name */
    private Context f14492a = CoreService.getAppContext();
    private SharedPreferences b;

    public static SHConfig a() {
        if (c == null) {
            c = new SHConfig();
        }
        return c;
    }

    private SHConfig() {
        if (AccountManager.a().l()) {
            SharedPreferences sharedPreferences = this.f14492a.getSharedPreferences("SHConfig", 0);
            Context context = this.f14492a;
            this.b = context.getSharedPreferences("SHConfig_" + AccountManager.a().m(), 0);
            Map<String, ?> all = sharedPreferences.getAll();
            if (all != null) {
                SharedPreferences.Editor edit = this.b.edit();
                for (Map.Entry next : all.entrySet()) {
                    Object value = next.getValue();
                    if (value instanceof Integer) {
                        edit.putInt((String) next.getKey(), ((Integer) next.getValue()).intValue());
                    } else if (value instanceof Long) {
                        edit.putLong((String) next.getKey(), ((Long) next.getValue()).longValue());
                    } else if (value instanceof String) {
                        edit.putString((String) next.getKey(), (String) next.getValue());
                    }
                }
                edit.commit();
            }
            SharedPreferences.Editor edit2 = sharedPreferences.edit();
            edit2.clear();
            edit2.commit();
            return;
        }
        this.b = this.f14492a.getSharedPreferences("SHConfig", 0);
    }

    public void b() {
        if (AccountManager.a().l()) {
            Context appContext = CoreService.getAppContext();
            this.b = appContext.getSharedPreferences("SHConfig_" + AccountManager.a().m(), 0);
            return;
        }
        this.b = CoreService.getAppContext().getSharedPreferences("SHConfig", 0);
    }

    public int a(String str) {
        return this.b.getInt(str, -1);
    }

    public void a(int i, String str) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.remove(str);
        edit.putInt(str, i);
        edit.apply();
    }

    public void b(final int i, final String str) {
        AsyncTaskUtils.a(new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Void doInBackground(Void... voidArr) {
                SHConfig.this.a(i, str);
                return null;
            }
        }, new Void[0]);
    }

    public long b(String str) {
        return this.b.getLong(str, -1);
    }

    public void a(String str, long j) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.remove(str);
        edit.putLong(str, j);
        edit.apply();
    }

    public String c(String str) {
        return this.b.getString(str, "");
    }

    public void a(String str, String str2) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.remove(str);
        edit.putString(str, str2);
        edit.apply();
    }
}
