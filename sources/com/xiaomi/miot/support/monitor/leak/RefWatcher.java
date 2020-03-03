package com.xiaomi.miot.support.monitor.leak;

import android.app.Activity;
import android.text.TextUtils;
import com.xiaomi.miot.support.monitor.Env;
import com.xiaomi.miot.support.monitor.MiotMonitorManager;
import com.xiaomi.miot.support.monitor.config.MiotMonitorConfig;
import com.xiaomi.miot.support.monitor.exceptions.MiotMonitorBaseException;
import com.xiaomi.miot.support.monitor.exceptions.MiotMonitorLeakException;
import com.xiaomi.miot.support.monitor.leak.Retryable;
import com.xiaomi.miot.support.monitor.report.IReport;
import com.xiaomi.miot.support.monitor.utils.LogX;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONException;
import org.json.JSONObject;

public final class RefWatcher {

    /* renamed from: a  reason: collision with root package name */
    public static final RefWatcher f11488a = new RefWatcherBuilder().f();
    private final WatchExecutor b;
    private final GcTrigger c;
    private final Set<String> d = new CopyOnWriteArraySet();
    private final ReferenceQueue<Object> e = new ReferenceQueue<>();
    private final Map<String, KeyedWeakReference> f = new HashMap();

    RefWatcher(WatchExecutor watchExecutor, GcTrigger gcTrigger) {
        this.b = (WatchExecutor) Preconditions.a(watchExecutor, "watchExecutor");
        this.c = (GcTrigger) Preconditions.a(gcTrigger, "gcTrigger");
    }

    public boolean a() {
        e();
        return this.d.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public Set<String> b() {
        return new HashSet(this.d);
    }

    private void b(final KeyedWeakReference keyedWeakReference) {
        this.b.a(new Retryable() {
            public Retryable.Result a() {
                return RefWatcher.this.a(keyedWeakReference);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public Retryable.Result a(KeyedWeakReference keyedWeakReference) {
        e();
        if (c(keyedWeakReference)) {
            return Retryable.Result.DONE;
        }
        this.c.a();
        e();
        if (c(keyedWeakReference)) {
            return Retryable.Result.DONE;
        }
        String str = keyedWeakReference.f11486a;
        LogX.a(Env.c, "leakInfo", keyedWeakReference.f11486a);
        MiotMonitorConfig c2 = MiotMonitorManager.a().c();
        String str2 = "Leak Activity:" + str;
        if (TextUtils.equals(c2.c.report_type, "1")) {
            c2.f1475a.a(IReport.Func_type.LEAK, c2.b.report_type, (MiotMonitorBaseException) new MiotMonitorLeakException(str2));
        } else {
            try {
                c2.f1475a.a(IReport.Func_type.BLOCK, c2.c.report_type, new JSONObject().put("lan", str));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        a(str);
        return Retryable.Result.LEAKED;
    }

    private boolean c(KeyedWeakReference keyedWeakReference) {
        return !this.d.contains(keyedWeakReference.f11486a);
    }

    private void e() {
        while (true) {
            KeyedWeakReference keyedWeakReference = (KeyedWeakReference) this.e.poll();
            if (keyedWeakReference != null) {
                a(keyedWeakReference.f11486a);
            } else {
                return;
            }
        }
    }

    public void a(Activity activity) {
        String className = activity.getComponentName().getClassName();
        this.d.add(className);
        this.f.put(className, new KeyedWeakReference(activity, className, "", this.e));
    }

    public void a(String str) {
        if (this.d.contains(str)) {
            this.d.remove(str);
        }
        if (this.f.containsKey(str)) {
            this.f.remove(str);
        }
    }

    public void c() {
        this.d.clear();
        this.f.clear();
    }

    public void d() {
        LogX.a(Env.c, "leakInfo", "begin");
        e();
        for (String str : this.f.keySet()) {
            b(this.f.get(str));
        }
    }
}
