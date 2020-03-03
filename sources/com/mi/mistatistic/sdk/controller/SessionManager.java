package com.mi.mistatistic.sdk.controller;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.mi.mistatistic.sdk.data.PageViewEvent;
import com.mi.mistatistic.sdk.data.SessionEvent;
import com.mi.mistatistic.sdk.data.ViewShowEvent;
import java.util.ArrayList;
import java.util.List;

public class SessionManager {

    /* renamed from: a  reason: collision with root package name */
    private static SessionManager f7343a = null;
    private static final int c = 31415927;
    private static final long d = 30000;
    private static final long e = 1800000;
    private static final String f = "session_begin";
    private static final String g = "page_ref";
    private static final String h = "page_close_closed_ref";
    private static final List<PageViewEvent> i = new ArrayList();
    private Handler b = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == SessionManager.c) {
                SessionManager.this.g();
            }
        }
    };
    private List<ViewShowEvent> j = new ArrayList();

    public static SessionManager a() {
        if (f7343a == null) {
            f7343a = new SessionManager();
        }
        return f7343a;
    }

    private SessionManager() {
    }

    public void a(String str, String str2, String str3) {
        if (!a(str)) {
            ViewShowEvent viewShowEvent = new ViewShowEvent(str, str2, str3);
            this.j.add(viewShowEvent);
            LocalEventRecorder.a(viewShowEvent);
        }
    }

    private boolean a(String str) {
        boolean z = false;
        for (ViewShowEvent next : this.j) {
            if (!TextUtils.isEmpty(str) && str.equals(next.j())) {
                z = true;
            }
        }
        return z;
    }

    public String b() {
        return String.valueOf(PrefPersistUtils.a(ApplicationContextHolder.a(), f, 0));
    }

    public void c() {
        long b2 = TimeUtil.a().b();
        PrefPersistUtils.b(ApplicationContextHolder.a(), f, b2);
        PrefPersistUtils.b(ApplicationContextHolder.a(), g, "");
        Logger.a("Session resetSessionId pageRef  sessionid " + b2, (Throwable) null);
    }

    public void d() {
        if (TimeUtil.a().b() - PrefPersistUtils.a(ApplicationContextHolder.a(), f, 0) > e) {
            this.b.sendEmptyMessage(c);
        } else {
            this.b.removeMessages(c);
        }
    }

    public void e() {
        this.b.sendEmptyMessageDelayed(c, 30000);
    }

    public void a(Context context, String str) {
        if (context != null) {
            try {
                long b2 = TimeUtil.a().b();
                String a2 = PrefPersistUtils.a(ApplicationContextHolder.a(), g, "");
                boolean a3 = PrefPersistUtils.a(ApplicationContextHolder.a(), h);
                Logger.a("Session recordActActivated pageClosedNormal " + a3, (Throwable) null);
                if (!a3 || TextUtils.isEmpty(a2)) {
                    c();
                    a2 = "";
                }
                PageViewEvent pageViewEvent = new PageViewEvent(b(), b2, b2, 0, str, a2);
                i.clear();
                i.add(pageViewEvent);
                PrefPersistUtils.b(ApplicationContextHolder.a(), g, str);
                PrefPersistUtils.a(ApplicationContextHolder.a(), h, false);
                Logger.a("Session recordActActivated pageRef " + a2 + " pageName " + str + " sessionid " + pageViewEvent.c(), (Throwable) null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void f() {
        try {
            Long valueOf = Long.valueOf(TimeUtil.a().b());
            if (!i.isEmpty() && i.size() > 0) {
                PageViewEvent pageViewEvent = i.get(0);
                i.clear();
                pageViewEvent.c(valueOf.longValue());
                LocalEventRecorder.a(pageViewEvent);
                this.j.clear();
                PrefPersistUtils.a(ApplicationContextHolder.a(), h, true);
                Logger.a("Session recordActDeactivated ptEvent " + pageViewEvent.i() + " pageRef " + pageViewEvent.j() + " sessionid " + pageViewEvent.c() + " page_time " + (pageViewEvent.h() - pageViewEvent.g()), (Throwable) null);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        String b2 = NetworkUtils.b(ApplicationContextHolder.a());
        if (TextUtils.isEmpty(b2)) {
            b2 = "NULL";
        }
        long longValue = Long.valueOf(b()).longValue();
        long b3 = TimeUtil.a().b();
        SessionEvent sessionEvent = new SessionEvent(longValue, b3, b2);
        LocalEventRecorder.a(sessionEvent);
        Logger.a("Session recordSession beginTs " + longValue + " endTs " + b3 + " sessionid " + sessionEvent.c() + " session_time " + (b3 - longValue), (Throwable) null);
        PrefPersistUtils.b(ApplicationContextHolder.a(), g, "");
    }

    private String a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        return str + "," + str2;
    }

    private String b(Context context, String str) {
        if (context == null) {
            return str;
        }
        if (TextUtils.isEmpty(str)) {
            str = context.getClass().getName();
        }
        String packageName = context.getPackageName();
        return str.startsWith(packageName) ? str.replace(packageName, "") : str;
    }
}
