package com.mi.mistatistic.sdk.controller;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.ArrayList;
import java.util.Iterator;

public class AsyncJobDispatcher {

    /* renamed from: a  reason: collision with root package name */
    private static AsyncJobDispatcher f7324a;
    private static AsyncJobDispatcher b;
    /* access modifiers changed from: private */
    public volatile Handler c;
    /* access modifiers changed from: private */
    public ArrayList<AsyncJob> d = new ArrayList<>();

    public interface AsyncJob {
        void a();
    }

    public static synchronized AsyncJobDispatcher a() {
        AsyncJobDispatcher asyncJobDispatcher;
        synchronized (AsyncJobDispatcher.class) {
            if (f7324a == null) {
                f7324a = new AsyncJobDispatcher("local_job_dispatcher");
            }
            asyncJobDispatcher = f7324a;
        }
        return asyncJobDispatcher;
    }

    public static synchronized AsyncJobDispatcher b() {
        AsyncJobDispatcher asyncJobDispatcher;
        synchronized (AsyncJobDispatcher.class) {
            if (b == null) {
                b = new AsyncJobDispatcher("remote_job_dispatcher");
            }
            asyncJobDispatcher = b;
        }
        return asyncJobDispatcher;
    }

    private AsyncJobDispatcher(String str) {
        new LocalDispatcherThread(str).start();
    }

    public void a(final AsyncJob asyncJob) {
        synchronized (this.d) {
            if (this.c == null) {
                Logger.a("AsyncJobDispatcher.mHander is null, add job to pending queue");
                this.d.add(asyncJob);
            } else {
                this.c.post(new Runnable() {
                    public void run() {
                        try {
                            asyncJob.a();
                        } catch (Exception e) {
                            Logger.a("error while executing job.", (Throwable) e);
                        }
                    }
                });
            }
        }
    }

    public void a(final AsyncJob asyncJob, final long j) {
        if (this.c != null) {
            this.c.postDelayed(new Runnable() {
                public void run() {
                    try {
                        Logger.a("AsyncJobDispatcher run a job with delay " + String.valueOf(j));
                        asyncJob.a();
                    } catch (Exception e) {
                        Logger.a("error while executing job.", (Throwable) e);
                    }
                }
            }, j);
        } else {
            Logger.a("drop the job as handler is not ready.", (Throwable) null);
        }
    }

    private class LocalDispatcherThread extends HandlerThread {
        public LocalDispatcherThread(String str) {
            super(str);
        }

        /* access modifiers changed from: protected */
        public void onLooperPrepared() {
            ArrayList arrayList;
            Handler unused = AsyncJobDispatcher.this.c = new Handler();
            synchronized (AsyncJobDispatcher.this.d) {
                if (!AsyncJobDispatcher.this.d.isEmpty()) {
                    arrayList = (ArrayList) AsyncJobDispatcher.this.d.clone();
                    String valueOf = String.valueOf(AsyncJobDispatcher.this.d.size());
                    AsyncJobDispatcher.this.d.clear();
                    Logger.a("mPendingJob(cnt=" + valueOf + ") not empty, clone a job list and clear original list");
                } else {
                    arrayList = null;
                }
            }
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    AsyncJob asyncJob = (AsyncJob) it.next();
                    try {
                        Logger.a("execute a pending job");
                        asyncJob.a();
                    } catch (Exception e) {
                        Logger.a("error while executing job.", (Throwable) e);
                    }
                }
            }
            super.onLooperPrepared();
        }
    }
}
