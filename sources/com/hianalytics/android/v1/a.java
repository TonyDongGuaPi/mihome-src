package com.hianalytics.android.v1;

import android.content.Context;
import android.content.SharedPreferences;
import com.alipay.sdk.util.i;
import com.hianalytics.android.b.a.c;
import com.xiaomi.stat.a.j;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private Context f5799a;
    private String b;
    private String c;
    private long d;

    public a(Context context, String str, String str2, long j) {
        this.f5799a = context;
        this.b = str.replace(",", "^");
        this.c = str2.replace(",", "^");
        this.d = j;
    }

    public final void run() {
        try {
            SharedPreferences a2 = c.a(this.f5799a, "state");
            if (a2 == null) {
                com.hianalytics.android.b.a.a.h();
                return;
            }
            String string = a2.getString(j.b, "");
            if (!"".equals(string)) {
                string = String.valueOf(string) + i.b;
            }
            String str = String.valueOf(string) + this.b + "," + this.c + "," + new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US).format(new Date(this.d));
            if (str.split(i.b).length <= com.hianalytics.android.b.a.a.d()) {
                SharedPreferences.Editor edit = a2.edit();
                edit.remove(j.b);
                edit.putString(j.b, str);
                edit.commit();
                com.hianalytics.android.b.a.a.h();
            }
            if (!com.hianalytics.android.b.a.a.d(this.f5799a)) {
                return;
            }
            if (com.hianalytics.android.b.a.a.e()) {
                com.hianalytics.android.b.a.a.h();
                HiAnalytics.c(this.f5799a);
                return;
            }
            a2.edit().remove(j.b).commit();
        } catch (Exception e) {
            e.getMessage();
            com.hianalytics.android.b.a.a.h();
            e.printStackTrace();
        }
    }
}
