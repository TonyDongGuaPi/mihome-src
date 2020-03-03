package com.xiaomi.smarthome.framework.log;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.MainThread;
import com.xiaomi.smarthome.application.CommonApplication;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class SerializedAsyncTaskProcessor {
    private static final int c = 0;
    private static final int d = 1;

    /* renamed from: a  reason: collision with root package name */
    private ProcessPackageThread f16523a;
    /* access modifiers changed from: private */
    public Handler b;
    /* access modifiers changed from: private */
    public volatile boolean e;
    private final boolean f;

    public static abstract class SerializedAsyncTask {
        public abstract void a();

        public void b() {
        }

        public void c() {
        }
    }

    public SerializedAsyncTaskProcessor() {
        this(false);
    }

    public SerializedAsyncTaskProcessor(boolean z) {
        this.b = null;
        this.e = false;
        this.b = new Handler(CommonApplication.getGlobalHandler().getLooper()) {
            public void handleMessage(Message message) {
                SerializedAsyncTask serializedAsyncTask = (SerializedAsyncTask) message.obj;
                if (message.what == 0) {
                    serializedAsyncTask.b();
                } else if (message.what == 1) {
                    serializedAsyncTask.c();
                }
                super.handleMessage(message);
            }
        };
        this.f = z;
    }

    @MainThread
    public void a(SerializedAsyncTask serializedAsyncTask) {
        if (this.f16523a == null) {
            this.f16523a = new ProcessPackageThread();
            this.f16523a.setDaemon(this.f);
            this.f16523a.start();
        }
        this.f16523a.a(serializedAsyncTask);
    }

    public void a() {
        if (this.f16523a != null) {
            this.f16523a.b.clear();
        }
    }

    public void a(final SerializedAsyncTask serializedAsyncTask, long j) {
        this.b.postDelayed(new Runnable() {
            public void run() {
                SerializedAsyncTaskProcessor.this.a(serializedAsyncTask);
            }
        }, j);
    }

    public void b() {
        this.e = true;
        a((SerializedAsyncTask) new SerializedAsyncTask() {
            public void a() {
            }
        });
    }

    private class ProcessPackageThread extends Thread {
        private static final String c = "PackageProcessor";
        /* access modifiers changed from: private */
        public final LinkedBlockingQueue<SerializedAsyncTask> b = new LinkedBlockingQueue<>();

        public ProcessPackageThread() {
            super(c);
        }

        public void a(SerializedAsyncTask serializedAsyncTask) {
            this.b.add(serializedAsyncTask);
        }

        public void run() {
            while (!SerializedAsyncTaskProcessor.this.e) {
                try {
                    SerializedAsyncTask poll = this.b.poll(1, TimeUnit.HOURS);
                    if (poll != null && !SerializedAsyncTaskProcessor.this.e) {
                        SerializedAsyncTaskProcessor.this.b.sendMessage(SerializedAsyncTaskProcessor.this.b.obtainMessage(0, poll));
                        poll.a();
                        SerializedAsyncTaskProcessor.this.b.sendMessage(SerializedAsyncTaskProcessor.this.b.obtainMessage(1, poll));
                    }
                } catch (InterruptedException e) {
                    MyLog.a((Throwable) e);
                }
            }
        }
    }
}
