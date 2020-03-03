package com.xiaomi.smarthome.framework.redpoint;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Pair;
import com.tencent.bugly.crashreport.CrashReport;
import com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.miio.Miio;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.TimeZone;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import org.aspectj.runtime.internal.AroundClosure;

public class ServerTimerManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17682a = "ServerTimerManager";
    private static ServerTimerManager b;
    /* access modifiers changed from: private */
    public volatile long c = -1;
    /* access modifiers changed from: private */
    public volatile long d = -1;
    /* access modifiers changed from: private */
    public WeakReference<Context> e;
    private ServerTimerManagerInternal f;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            return ServerTimerManager.a((URL) this.state[0]);
        }
    }

    public class AjcClosure3 extends AroundClosure {
        public AjcClosure3(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            return ServerTimerManager.b((URL) this.state[0]);
        }
    }

    private ServerTimerManager(Context context) {
        this.e = new WeakReference<>(context.getApplicationContext());
        this.f = new ServerTimerManagerInternal();
    }

    public static ServerTimerManager a(Context context) {
        if (b == null) {
            synchronized (ServerTimerManager.class) {
                if (b == null) {
                    b = new ServerTimerManager(context);
                }
            }
        }
        return b;
    }

    public void a() {
        this.f.b();
    }

    public boolean b() {
        return (this.c == -1 || this.d == -1 || this.c == 0 || this.d == 0) ? false : true;
    }

    public long c() {
        Miio.b("ProfileRedPointManager", "isServerTimeValid" + b());
        if (b()) {
            return this.c - this.d;
        }
        return 0;
    }

    public void d() {
        this.f.a();
        this.c = -1;
        this.d = -1;
        this.e.clear();
        b = null;
    }

    public static Pair<Long, Boolean> e() {
        String str;
        ServerBean a2 = ServerCompact.a(SHApplication.getAppContext());
        if (a2 == null || ServerCompact.c(a2)) {
            str = "https://api.io.mi.com/app";
        } else {
            str = "https://" + a2.f1530a + ".api.io.mi.com/app";
        }
        try {
            if (GlobalSetting.u) {
                LogUtilGrey.a(f17682a, "getServerTimeSync start");
            }
            URLConnection b2 = TraceNetTrafficMonitor.b().b(new URL(str));
            b2.setConnectTimeout(10000);
            try {
                b2.connect();
                long date = b2.getDate();
                if (GlobalSetting.u) {
                    LogUtilGrey.a(f17682a, "getServerTimeSync success");
                }
                return new Pair<>(Long.valueOf(date - System.currentTimeMillis()), true);
            } catch (Exception e2) {
                e2.printStackTrace();
                return f();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            return f();
        }
    }

    static final URLConnection a(URL url) {
        return url.openConnection();
    }

    private static Pair<Long, Boolean> f() {
        try {
            if (GlobalSetting.u) {
                LogUtilGrey.a(f17682a, "getServerTimeSync start");
            }
            URLConnection b2 = TraceNetTrafficMonitor.b().b(new URL("https://account.xiaomi.com/pass/serviceLogin"));
            b2.setConnectTimeout(10000);
            try {
                b2.connect();
                long date = b2.getDate();
                if (GlobalSetting.u) {
                    LogUtilGrey.a(f17682a, "getServerTimeSync success");
                }
                return new Pair<>(Long.valueOf(date - System.currentTimeMillis()), true);
            } catch (Exception e2) {
                e2.printStackTrace();
                LogUtilGrey.a(f17682a, "getServerTimeSync fail1:" + e2.getMessage() + ":" + "https://account.xiaomi.com/pass/serviceLogin");
                if (SHApplication.shouldEnableBugly()) {
                    if (HomeManager.A()) {
                        InternationalServerTimerException internationalServerTimerException = new InternationalServerTimerException("getServerTimeSync fail1:" + e2.getMessage() + ":" + "https://account.xiaomi.com/pass/serviceLogin");
                        internationalServerTimerException.initCause(e2);
                        CrashReport.postCatchedException(internationalServerTimerException);
                    } else {
                        ServerTimerException serverTimerException = new ServerTimerException("getServerTimeSync fail1:" + e2.getMessage() + ":" + "https://account.xiaomi.com/pass/serviceLogin");
                        serverTimerException.initCause(e2);
                        CrashReport.postCatchedException(serverTimerException);
                    }
                }
                return new Pair<>(0L, false);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            LogUtilGrey.a(f17682a, "getServerTimeSync fail2:" + e3.getMessage() + ":" + "https://account.xiaomi.com/pass/serviceLogin");
            if (SHApplication.shouldEnableBugly()) {
                ServerTimerException serverTimerException2 = new ServerTimerException("getServerTimeSync fail2:" + e3.getMessage() + ":" + "https://account.xiaomi.com/pass/serviceLogin");
                serverTimerException2.initCause(e3);
                CrashReport.postCatchedException(serverTimerException2);
            }
            if (HomeManager.A()) {
                InternationalServerTimerException internationalServerTimerException2 = new InternationalServerTimerException("getServerTimeSync fail2:" + e3.getMessage() + ":" + "https://account.xiaomi.com/pass/serviceLogin");
                internationalServerTimerException2.initCause(e3);
                CrashReport.postCatchedException(internationalServerTimerException2);
            } else {
                ServerTimerException serverTimerException3 = new ServerTimerException("getServerTimeSync fail2:" + e3.getMessage() + ":" + "https://account.xiaomi.com/pass/serviceLogin");
                serverTimerException3.initCause(e3);
                CrashReport.postCatchedException(serverTimerException3);
            }
            return new Pair<>(0L, false);
        }
    }

    static final URLConnection b(URL url) {
        return url.openConnection();
    }

    public static class InternationalServerTimerException extends Exception {
        public InternationalServerTimerException(String str) {
            super(str);
        }
    }

    public static class ServerTimerException extends Exception {
        public ServerTimerException(String str) {
            super(str);
        }
    }

    private class ServerTimerManagerInternal {
        private Future<Void> b;
        private IntentFilter c = new IntentFilter();
        private BroadcastReceiver d = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                long unused = ServerTimerManager.this.c = -1;
                long unused2 = ServerTimerManager.this.d = -1;
                ServerTimerManagerInternal.this.b();
            }
        };

        public ServerTimerManagerInternal() {
            this.c.addAction("android.intent.action.TIME_SET");
            this.c.addAction("android.intent.action.DATE_CHANGED");
            ((Context) ServerTimerManager.this.e.get()).registerReceiver(this.d, this.c);
        }

        public void a() {
            Context context = (Context) ServerTimerManager.this.e.get();
            if (context != null) {
                try {
                    context.unregisterReceiver(this.d);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void b() {
            if (this.b != null) {
                this.b.cancel(true);
            }
            this.b = SHApplication.getThreadExecutor().submit(new Callable<Void>() {
                /* renamed from: a */
                public Void call() throws Exception {
                    long unused = ServerTimerManager.this.c = ((Long) ServerTimerManager.e().first).longValue();
                    if (ServerTimerManager.this.c == 0) {
                        long unused2 = ServerTimerManager.this.c = -1;
                        long unused3 = ServerTimerManager.this.d = System.currentTimeMillis();
                    } else {
                        long unused4 = ServerTimerManager.this.d = System.currentTimeMillis();
                        long unused5 = ServerTimerManager.this.c = ServerTimerManager.this.c + ServerTimerManager.this.d;
                    }
                    ProfileRedPointManager.a().a(ServerTimerManager.a(SHApplication.getAppContext()).c());
                    return null;
                }
            });
        }

        private long c() {
            long currentTimeMillis = System.currentTimeMillis();
            return (long) (TimeZone.getDefault().getOffset(currentTimeMillis) - TimeZone.getTimeZone("Asia/Shanghai").getOffset(currentTimeMillis));
        }
    }
}
