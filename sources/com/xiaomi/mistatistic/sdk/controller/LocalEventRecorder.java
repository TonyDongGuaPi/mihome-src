package com.xiaomi.mistatistic.sdk.controller;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.mi.mistatistic.sdk.controller.RemoteDataUploadManager;
import com.xiaomi.mistatistic.sdk.BuildSetting;
import com.xiaomi.mistatistic.sdk.CustomSettings;
import com.xiaomi.mistatistic.sdk.controller.d;
import com.xiaomi.mistatistic.sdk.data.AbstractEvent;
import com.xiaomi.mistatistic.sdk.data.StatEventPojo;
import com.xiaomi.mistatistic.sdk.data.g;
import com.xiaomi.mistatistic.sdk.data.h;
import com.xiaomi.stat.a.l;
import com.xiaomi.stat.c.c;
import com.xiaomi.xmsf.push.service.b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

public abstract class LocalEventRecorder {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static volatile b f12003a = null;
    /* access modifiers changed from: private */
    public static volatile boolean b = false;
    /* access modifiers changed from: private */
    public static List<AbstractEvent> c = new ArrayList();
    /* access modifiers changed from: private */
    public static Object d = new Object();
    /* access modifiers changed from: private */
    public static ServiceConnection e = new ServiceConnection() {
        public void onServiceDisconnected(ComponentName componentName) {
            h.a("LER", "IStatService has unexpectedly disconnected");
            b unused = LocalEventRecorder.f12003a = null;
            boolean unused2 = LocalEventRecorder.b = false;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                h.a("LER", "IStatService connected");
                b unused = LocalEventRecorder.f12003a = b.a.a(iBinder);
                boolean unused2 = LocalEventRecorder.b = true;
                if (LocalEventRecorder.f12003a != null) {
                    synchronized (LocalEventRecorder.d) {
                        if (LocalEventRecorder.c != null && !LocalEventRecorder.c.isEmpty()) {
                            for (AbstractEvent b : LocalEventRecorder.c) {
                                LocalEventRecorder.f12003a.a(b.b().toString());
                                h.a("LER", "insert a reserved event into IStatService");
                            }
                        }
                        h.a("LER", "pending eventList size: " + LocalEventRecorder.c.size());
                        LocalEventRecorder.c.clear();
                    }
                }
            } catch (RemoteException e) {
                h.a("", (Throwable) e);
            } catch (JSONException e2) {
                h.a("", (Throwable) e2);
                synchronized (LocalEventRecorder.d) {
                    LocalEventRecorder.c.clear();
                }
            } catch (Throwable th) {
                h.a("", th);
            }
        }
    };

    public static void a(final AbstractEvent abstractEvent) {
        Context a2 = c.a();
        if (a2 == null) {
            h.a("LER", "mistats is not initialized properly.");
        } else if (BuildSetting.e()) {
            h.a("LER", "disable local event upload for CTA build");
        } else if (CustomSettings.c()) {
            h.b("LER", "insert event use systemstatsvc");
            d.a().a((d.a) new d.a() {
                public void a() {
                    LocalEventRecorder.c(abstractEvent);
                }
            });
        } else if (!BuildSetting.a(a2) || a(abstractEvent.a())) {
            d.a().a((d.a) new a(abstractEvent));
            p.a().c();
        } else {
            h.a("LER", "disabled local event upload, event category:" + abstractEvent.a());
        }
    }

    private static boolean a(String str) {
        return l.a.h.equals(str) || RemoteDataUploadManager.f.equals(str);
    }

    /* access modifiers changed from: private */
    public static void c(AbstractEvent abstractEvent) {
        try {
            Context a2 = c.a();
            a(a2);
            if (f12003a != null) {
                f12003a.a(abstractEvent.b().toString());
                h.b("LER", "sysservice insert a event");
            } else {
                h.a("LER", "StatSystemService is null, insert event into eventList");
                synchronized (d) {
                    c.add(abstractEvent);
                }
            }
            b(a2);
        } catch (Throwable th) {
            h.a("LER", "insertEventUseSystemService exception: ", th);
        }
    }

    private static void a(Context context) throws InterruptedException {
        if (!b) {
            Intent intent = new Intent();
            intent.setClassName(c.f23036a, "com.xiaomi.xmsf.push.service.StatService");
            context.bindService(intent, e, 1);
            b = true;
            h.a("LER", "bind StatSystemService success");
            return;
        }
        h.a("LER", "StatSystemService is already binded");
    }

    private static void b(final Context context) {
        d.a().a((d.a) new d.a() {
            public void a() {
                try {
                    if (LocalEventRecorder.b) {
                        context.unbindService(LocalEventRecorder.e);
                        boolean unused = LocalEventRecorder.b = false;
                        b unused2 = LocalEventRecorder.f12003a = null;
                        h.a("LER", "unbind StatSystemService success");
                        return;
                    }
                    h.a("LER", "StatSystemService is already disconnected");
                } catch (Exception e) {
                    h.a("", (Throwable) e);
                }
            }
        }, 10000);
    }

    private static class a implements d.a {

        /* renamed from: a  reason: collision with root package name */
        private AbstractEvent f12006a;

        public a(AbstractEvent abstractEvent) {
            this.f12006a = abstractEvent;
        }

        public void a() {
            StatEventPojo c = this.f12006a.c();
            f fVar = new f();
            if ((this.f12006a instanceof g) || (this.f12006a instanceof h)) {
                String str = c.c;
                String str2 = c.f12065a;
                StatEventPojo a2 = fVar.a(str2, str);
                if (a2 == null || !c.d.equals(a2.d)) {
                    fVar.a(c);
                    h.b("LocalEventRecordingJob insert event with new key");
                    return;
                }
                fVar.a(str, str2, c.e);
                h.b("LocalEventRecordingJob update event by key and category");
                return;
            }
            fVar.a(c);
            h.b("LocalEventRecordingJob insert new event");
        }
    }
}
