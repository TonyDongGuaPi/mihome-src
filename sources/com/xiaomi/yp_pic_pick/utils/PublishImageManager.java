package com.xiaomi.yp_pic_pick.utils;

import android.os.Process;
import java.util.concurrent.LinkedBlockingQueue;

public class PublishImageManager {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final PublishImageManager f19524a = new PublishImageManager();
    /* access modifiers changed from: private */
    public Thread b = null;
    /* access modifiers changed from: private */
    public LinkedBlockingQueue<PublishTask> c = new LinkedBlockingQueue<>();

    public interface PublishTask {
        void a();
    }

    private PublishImageManager() {
    }

    public static PublishImageManager a() {
        synchronized (f19524a) {
            if (f19524a.b == null) {
                f19524a.b = new Thread(new Runnable() {
                    public void run() {
                        Process.setThreadPriority(10);
                        while (PublishImageManager.f19524a.b != null && !PublishImageManager.f19524a.b.isInterrupted()) {
                            try {
                                PublishTask publishTask = (PublishTask) PublishImageManager.f19524a.c.take();
                                if (publishTask != null) {
                                    publishTask.a();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        Thread unused = PublishImageManager.f19524a.b = null;
                    }
                }, "publish image task");
                f19524a.b.start();
            }
        }
        return f19524a;
    }

    public void a(PublishTask publishTask) {
        this.c.offer(publishTask);
    }

    public static void b() {
        f19524a.c.clear();
        if (f19524a.b != null) {
            f19524a.b.interrupt();
        }
    }
}
