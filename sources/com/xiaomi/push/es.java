package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.push.service.aa;
import java.util.HashMap;

public final class es {

    /* renamed from: a  reason: collision with root package name */
    private static volatile es f12725a;
    private Context b;
    private HashMap<eu, ev> c = new HashMap<>();
    private String d;
    private String e;
    private int f;
    private ew g;

    private es(Context context) {
        this.b = context;
        this.c.put(eu.SERVICE_ACTION, new ey());
        this.c.put(eu.SERVICE_COMPONENT, new ez());
        this.c.put(eu.ACTIVITY, new eq());
        this.c.put(eu.PROVIDER, new ex());
    }

    public static es a(Context context) {
        if (f12725a == null) {
            synchronized (es.class) {
                if (f12725a == null) {
                    f12725a = new es(context);
                }
            }
        }
        return f12725a;
    }

    /* access modifiers changed from: private */
    public void a(eu euVar, Context context, er erVar) {
        this.c.get(euVar).a(context, erVar);
    }

    public static boolean b(Context context) {
        return aa.a(context, context.getPackageName());
    }

    public ew a() {
        return this.g;
    }

    public void a(int i) {
        this.f = i;
    }

    public void a(Context context, String str, int i, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            eo.a(context, "" + str, 1008, "A receive a incorrect message");
            return;
        }
        a(i);
        ai.a(this.b).a((Runnable) new et(this, str, context, str2, str3));
    }

    public void a(eu euVar, Context context, Intent intent, String str) {
        if (euVar != null) {
            this.c.get(euVar).a(context, intent, str);
        } else {
            eo.a(context, "null", 1008, "A receive a incorrect message with empty type");
        }
    }

    public void a(ew ewVar) {
        this.g = ewVar;
    }

    public void a(String str) {
        this.d = str;
    }

    public void a(String str, String str2, int i, ew ewVar) {
        a(str);
        b(str2);
        a(i);
        a(ewVar);
    }

    public String b() {
        return this.d;
    }

    public void b(String str) {
        this.e = str;
    }

    public String c() {
        return this.e;
    }

    public int d() {
        return this.f;
    }
}
