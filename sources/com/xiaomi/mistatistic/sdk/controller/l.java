package com.xiaomi.mistatistic.sdk.controller;

import android.app.ActivityManager;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.mistatistic.sdk.CustomSettings;
import com.xiaomi.mistatistic.sdk.controller.asyncjobs.b;
import com.xiaomi.mistatistic.sdk.controller.asyncjobs.c;
import com.xiaomi.mistatistic.sdk.controller.d;
import com.xiaomi.mistatistic.sdk.data.AbstractEvent;
import com.xiaomi.mistatistic.sdk.data.h;
import com.xiaomi.stat.a.l;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class l {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static AtomicBoolean f12039a = new AtomicBoolean(false);

    public void a() {
        a(true);
    }

    public void a(boolean z) {
        if (!CustomSettings.d()) {
            h.a("upload is disabled.", (Throwable) null);
            return;
        }
        h.a("trigger upload job with retry: " + z);
        if (f12039a.compareAndSet(false, true)) {
            h.d("upload job start, set Uploading " + f12039a.get());
            if (c()) {
                f();
                e();
                p.a().d();
                return;
            }
            f12039a.set(false);
            h.d("upload is not allowed by the server. set Uploading " + f12039a.get());
        } else if (z) {
            h.a(String.format("sUploading %s, trigger uploading job with delay %d", new Object[]{Boolean.valueOf(f12039a.get()), 10000L}));
            d.a().a((d.a) new a(), 10000);
        }
    }

    public class a implements d.a {
        public a() {
        }

        public void a() {
            l.this.a(false);
        }
    }

    public static boolean b() {
        return f12039a.get();
    }

    private void e() {
        d.a().a((d.a) new b(p.a().f(), new b.a() {
            public void a(String str, long j, long j2, int i) {
                if (!TextUtils.isEmpty(str)) {
                    l.this.a(str, j, j2, i);
                    return;
                }
                l.f12039a.set(false);
                h.a("RDUM", "packing completed with empty data, set Uploading " + l.f12039a.get());
            }
        }));
    }

    /* access modifiers changed from: private */
    public void a(String str, long j, long j2, int i) {
        final long j3 = j;
        final long j4 = j2;
        d.b().a((d.a) new c(str, new c.a() {
            public void a(boolean z) {
                if (z) {
                    l.this.a(j3, j4);
                    return;
                }
                l.f12039a.set(false);
                h.a("RDUM", "upload failed, set Uploading " + l.f12039a.get());
            }
        }, i));
    }

    /* access modifiers changed from: private */
    public void a(long j, long j2) {
        final long j3 = j;
        final long j4 = j2;
        d.a().a((d.a) new d.a() {
            public void a() {
                try {
                    new f().a(j3, j4);
                } catch (Throwable th) {
                    h.a("RDUM", "doDeleting exception: ", th);
                }
                l.f12039a.set(false);
                h.a("RDUM", "delete done, set Uploading " + l.f12039a.get());
            }
        });
    }

    private void f() {
        d.a().a((d.a) new d.a() {
            public void a() {
                l.this.g();
            }
        });
    }

    /* access modifiers changed from: private */
    public void g() {
        try {
            if (CustomSettings.f() && Build.VERSION.SDK_INT <= 21) {
                ArrayList arrayList = new ArrayList();
                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) c.a().getSystemService("activity")).getRunningAppProcesses();
                if (runningAppProcesses != null) {
                    for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                        if (next.importance == 100) {
                            arrayList.add(next.processName);
                        }
                    }
                    LocalEventRecorder.a((AbstractEvent) new h(l.a.h, "foreground_package", TextUtils.join(",", arrayList)));
                }
            }
        } catch (Throwable th) {
            h.a("", th);
        }
    }

    public static void a(long j) {
        long currentTimeMillis = System.currentTimeMillis() + j;
        k.b(c.a(), "next_upload_ts", currentTimeMillis);
        h.a("RDUM", "update next upload time to %d according to server delay %dms", Long.valueOf(currentTimeMillis), Long.valueOf(j));
    }

    public static boolean c() {
        return System.currentTimeMillis() > k.a(c.a(), "next_upload_ts", 0);
    }
}
