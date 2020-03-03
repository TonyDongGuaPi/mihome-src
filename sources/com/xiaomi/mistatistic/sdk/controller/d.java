package com.xiaomi.mistatistic.sdk.controller;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.ArrayList;
import java.util.Iterator;

public class d {

    /* renamed from: a  reason: collision with root package name */
    private static d f12018a;
    private static d b;
    /* access modifiers changed from: private */
    public volatile Handler c;
    /* access modifiers changed from: private */
    public ArrayList<a> d = new ArrayList<>();

    public interface a {
        void a();
    }

    public static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            if (f12018a == null) {
                f12018a = new d("local_job_dispatcher");
            }
            dVar = f12018a;
        }
        return dVar;
    }

    public static synchronized d b() {
        d dVar;
        synchronized (d.class) {
            if (b == null) {
                b = new d("remote_job_dispatcher");
            }
            dVar = b;
        }
        return dVar;
    }

    private d(String str) {
        new b(str).start();
    }

    public void a(final a aVar) {
        synchronized (this.d) {
            if (this.c == null) {
                h.a("AsyncJobDispatcher.mHander is null, add job to pending queue");
                this.d.add(aVar);
            } else {
                this.c.post(new Runnable() {
                    public void run() {
                        try {
                            aVar.a();
                        } catch (Exception e) {
                            h.a("error while executing job.", (Throwable) e);
                        }
                    }
                });
            }
        }
    }

    public void a(final a aVar, final long j) {
        if (this.c != null) {
            this.c.postDelayed(new Runnable() {
                public void run() {
                    try {
                        h.a("AsyncJobDispatcher run a job with delay " + String.valueOf(j));
                        aVar.a();
                    } catch (Exception e) {
                        h.a("error while executing job.", (Throwable) e);
                    }
                }
            }, j);
        } else {
            h.a("drop the job as handler is not ready.", (Throwable) null);
        }
    }

    private class b extends HandlerThread {
        public b(String str) {
            super(str);
        }

        /* access modifiers changed from: protected */
        public void onLooperPrepared() {
            ArrayList arrayList;
            Handler unused = d.this.c = new Handler();
            synchronized (d.this.d) {
                if (!d.this.d.isEmpty()) {
                    arrayList = (ArrayList) d.this.d.clone();
                    String valueOf = String.valueOf(d.this.d.size());
                    d.this.d.clear();
                    h.a("mPendingJob(cnt=" + valueOf + ") not empty, clone a job list and clear original list");
                } else {
                    arrayList = null;
                }
            }
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    a aVar = (a) it.next();
                    try {
                        h.a("execute a pending job");
                        aVar.a();
                    } catch (Exception e) {
                        h.a("error while executing job.", (Throwable) e);
                    }
                }
            }
            super.onLooperPrepared();
        }
    }
}
