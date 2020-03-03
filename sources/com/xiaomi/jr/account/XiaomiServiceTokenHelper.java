package com.xiaomi.jr.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.CookieManager;
import com.xiaomi.jr.QualityMonitor;
import com.xiaomi.jr.account.SimpleFutureTask;
import com.xiaomi.jr.account.XiaomiAccountCookie;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.UrlUtils;
import com.xiaomi.jr.common.utils.Utils;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class XiaomiServiceTokenHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10283a = "XiaomiServiceTokenHelper";
    private static final long b = 300000;
    private static final ExecutorService c = Executors.newCachedThreadPool();
    private static final Object g = new Object();
    private static final CopyOnWriteArraySet<String> h = new CopyOnWriteArraySet<>();
    private static boolean i = false;
    private final ConcurrentHashMap<String, Object> d = new ConcurrentHashMap<>();
    private final Map<String, XiaomiAccountInfo> e = new ConcurrentHashMap();
    private final Map<String, Long> f = new ConcurrentHashMap();

    public interface XiaomiServiceTokenObserver {
        void onServiceTokenReady(XiaomiAccountInfo xiaomiAccountInfo);
    }

    XiaomiServiceTokenHelper() {
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.d.clear();
        this.e.clear();
        this.f.clear();
    }

    /* access modifiers changed from: package-private */
    public void a(@Nullable Context context, @NonNull String str, @NonNull String str2, @Nullable XiaomiServiceTokenObserver xiaomiServiceTokenObserver) {
        a((Callable<XiaomiAccountInfo>) new Callable(context, str, str2) {
            private final /* synthetic */ Context f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ String f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final Object call() {
                return XiaomiServiceTokenHelper.this.d(this.f$1, this.f$2, this.f$3);
            }
        }, xiaomiServiceTokenObserver);
    }

    /* access modifiers changed from: package-private */
    public void b(@Nullable Context context, @NonNull String str, @NonNull String str2, @Nullable XiaomiServiceTokenObserver xiaomiServiceTokenObserver) {
        a((Callable<XiaomiAccountInfo>) new Callable(context, str, str2) {
            private final /* synthetic */ Context f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ String f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final Object call() {
                return XiaomiServiceTokenHelper.this.c(this.f$1, this.f$2, this.f$3);
            }
        }, xiaomiServiceTokenObserver);
    }

    private static void a(Callable<XiaomiAccountInfo> callable, final XiaomiServiceTokenObserver xiaomiServiceTokenObserver) {
        c.submit(new SimpleFutureTask(callable, new SimpleFutureTask.Callback<XiaomiAccountInfo>() {
            public void a(SimpleFutureTask<XiaomiAccountInfo> simpleFutureTask) {
                try {
                    if (xiaomiServiceTokenObserver != null) {
                        xiaomiServiceTokenObserver.onServiceTokenReady((XiaomiAccountInfo) simpleFutureTask.get());
                    }
                } catch (Exception e) {
                    MifiLog.e(XiaomiServiceTokenHelper.f10283a, "fetchServiceToken throw exception - " + e);
                }
            }
        }));
    }

    private boolean a(String str, String str2) {
        String str3;
        MifiLog.c(f10283a, "start invalidateAccountInfo");
        XiaomiAccountInfo xiaomiAccountInfo = this.e.get(str);
        if (xiaomiAccountInfo == null) {
            Bundle bundle = null;
            try {
                Bundle result = XiaomiAccountManager.j().a(str2).getResult(30, TimeUnit.SECONDS);
                str3 = null;
                bundle = result;
            } catch (Exception unused) {
                str3 = "sdk error: getServiceToken failed for sid " + str2;
            }
            if (bundle == null) {
                QualityMonitor.a(Constants.j, "invalidate_account_info", str3);
                return false;
            }
            xiaomiAccountInfo = XiaomiAccountInfo.a(bundle);
        }
        this.e.remove(str);
        try {
            XiaomiAccountManager.j().a(xiaomiAccountInfo.g);
            MifiLog.c(f10283a, "finish invalidateAccountInfo");
            return true;
        } catch (Exception e2) {
            QualityMonitor.a(Constants.j, "invalidate_account_info", "Exception in invalidateAccountInfo: " + e2.toString());
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public XiaomiAccountInfo d(@Nullable Context context, @NonNull String str, @NonNull String str2) {
        Utils.b();
        this.d.putIfAbsent(str2, str2);
        Object obj = this.d.get(str2);
        obj.getClass();
        synchronized (obj) {
            MifiLog.c(f10283a, "start syncSetupServiceToken");
            if (!XiaomiAccountManager.a().d()) {
                return null;
            }
            XiaomiAccountInfo xiaomiAccountInfo = this.e.get(str2);
            if (xiaomiAccountInfo != null) {
                return xiaomiAccountInfo;
            }
            boolean a2 = a(context, str, str2, "setup_service_token");
            MifiLog.c(f10283a, "syncSetupServiceToken - setupServiceToken return " + a2);
            CookieManager instance = CookieManager.getInstance();
            MifiLog.c("TestCookie", "syncSetupServiceToken getCookie: " + instance.getCookie(str2) + ", url=" + str2);
            XiaomiAccountInfo xiaomiAccountInfo2 = this.e.get(str2);
            return xiaomiAccountInfo2;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public XiaomiAccountInfo c(@Nullable Context context, @NonNull String str, @NonNull String str2) {
        Utils.b();
        String a2 = UrlUtils.a(str2);
        this.d.putIfAbsent(a2, a2);
        Object obj = this.d.get(a2);
        obj.getClass();
        synchronized (obj) {
            MifiLog.c(f10283a, "start syncReSetupServiceToken");
            if (!XiaomiAccountManager.a().d()) {
                return null;
            }
            Long l = this.f.get(a2);
            long currentTimeMillis = System.currentTimeMillis();
            if (l != null && currentTimeMillis - l.longValue() < 300000) {
                QualityMonitor.a(Constants.j, "reset_account_info", "ServiceToken invalidated just now. Do NOT repeat in 5 minutes. url = " + str2);
                XiaomiAccountInfo xiaomiAccountInfo = this.e.get(a2);
                return xiaomiAccountInfo;
            } else if (!a(a2, str)) {
                QualityMonitor.a(Constants.j, "reset_account_info_failure", "reason", "fail to invalidate account info for " + str, "url", str2);
                XiaomiAccountInfo xiaomiAccountInfo2 = this.e.get(a2);
                return xiaomiAccountInfo2;
            } else {
                boolean a3 = a(context, str, a2, "resetup_service_token");
                MifiLog.c(f10283a, "syncReSetupServiceToken - setupServiceToken return " + a3);
                CookieManager instance = CookieManager.getInstance();
                MifiLog.c("TestCookie", "syncReSetupServiceToken getCookie: " + instance.getCookie(str2) + ", url=" + str2);
                this.f.put(a2, Long.valueOf(currentTimeMillis));
                XiaomiAccountInfo xiaomiAccountInfo3 = this.e.get(a2);
                return xiaomiAccountInfo3;
            }
        }
    }

    private boolean a(Context context, String str, String str2, String str3) {
        XiaomiAccountCookie a2 = new XiaomiAccountCookie.Builder().a(context).a(str).b(str2).a();
        Bundle a3 = a2.a();
        if (a3 == null) {
            QualityMonitor.a(Constants.j, "get_account_info", "getServiceToken failed. sid=" + str + ", scene=" + str3);
            return false;
        }
        Intent intent = (Intent) a3.getParcelable("intent");
        if (intent != null) {
            MifiLog.e(f10283a, "Interaction is required for serviceToken fetch. sid = " + str);
            if (a(context, intent, str2)) {
                return a(context, str, str2, "retry");
            }
            QualityMonitor.a(Constants.j, "get_account_info", "require user interaction but give up. sid=" + str + ", scene=" + str3);
            return false;
        }
        XiaomiAccountInfo a4 = XiaomiAccountInfo.a(a3);
        a4.h = a2.a(a3);
        this.e.put(str2, a4);
        return true;
    }

    private static void a(String str) {
        h.add(str);
    }

    private static void b(String str) {
        h.remove(str);
        if (h.isEmpty()) {
            i = false;
        }
    }

    private static boolean a(Context context, Intent intent, String str) {
        boolean z;
        a(str);
        synchronized (h) {
            boolean z2 = false;
            try {
                if (i) {
                    b(str);
                    return true;
                }
                i = false;
                AccountResultActivity.startActivity(context, intent, 2);
                synchronized (g) {
                    try {
                        g.wait(300000);
                        z = i;
                        try {
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            z2 = z;
                            th = th2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            throw th;
                        } catch (InterruptedException e2) {
                            e = e2;
                            z = z2;
                        }
                    }
                }
            } catch (InterruptedException e3) {
                e = e3;
                z = false;
                e.printStackTrace();
                b(str);
                return z;
            }
        }
    }

    static void a(boolean z) {
        synchronized (g) {
            i = z;
            g.notifyAll();
        }
    }
}
