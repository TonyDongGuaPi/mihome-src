package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.push.ai;
import com.xiaomi.push.cd;
import com.xiaomi.push.service.ah;
import com.xiaomi.push.service.bf;

public class br {
    private static volatile br e;

    /* renamed from: a  reason: collision with root package name */
    private final String f12656a = "push_stat_sp";
    private final String b = "upload_time";
    private final String c = "delete_time";
    private final String d = "check_time";
    /* access modifiers changed from: private */
    public Context f;
    private String g;
    private String h;
    private cg i;
    /* access modifiers changed from: private */
    public ch j;
    private ai.a k = new bs(this);
    private ai.a l = new bt(this);
    private ai.a m = new bu(this);

    private br(Context context) {
        this.f = context;
    }

    public static br a(Context context) {
        if (e == null) {
            synchronized (br.class) {
                if (e == null) {
                    e = new br(context);
                }
            }
        }
        return e;
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        SharedPreferences.Editor edit = this.f.getSharedPreferences("push_stat_sp", 0).edit();
        edit.putLong(str, System.currentTimeMillis());
        r.a(edit);
    }

    private boolean c() {
        return ah.a(this.f).a(ht.StatDataSwitch.a(), true);
    }

    /* access modifiers changed from: private */
    public String d() {
        return this.f.getDatabasePath(bv.f12660a).getAbsolutePath();
    }

    public String a() {
        return this.g;
    }

    public void a(cd.a aVar) {
        cd.a(this.f).a(aVar);
    }

    public void a(hs hsVar) {
        if (c() && bf.a(hsVar.e())) {
            a((cd.a) ca.a(this.f, d(), hsVar));
        }
    }

    public void a(String str) {
        if (c() && !TextUtils.isEmpty(str)) {
            a(ci.a(this.f, str));
        }
    }

    public void a(String str, String str2, Boolean bool) {
        if (this.i == null) {
            return;
        }
        if (bool.booleanValue()) {
            this.i.a(this.f, str2, str);
        } else {
            this.i.b(this.f, str2, str);
        }
    }

    public String b() {
        return this.h;
    }
}
