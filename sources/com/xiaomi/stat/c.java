package com.xiaomi.stat;

import android.os.Handler;
import android.os.HandlerThread;
import com.xiaomi.stat.d.k;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23032a = "DBExecutor";
    private static String b = "mistat_db";
    private static final String c = "mistat";
    private static final String d = "db.lk";
    private static Handler e;
    private static FileLock f;
    private static FileChannel g;

    private static void c() {
        if (e == null) {
            synchronized (c.class) {
                if (e == null) {
                    HandlerThread handlerThread = new HandlerThread(b);
                    handlerThread.start();
                    e = new Handler(handlerThread.getLooper());
                }
            }
        }
    }

    public static void a(Runnable runnable) {
        c();
        e.post(new a(runnable));
    }

    private static class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private Runnable f23033a;

        public a(Runnable runnable) {
            this.f23033a = runnable;
        }

        public void run() {
            if (c.d()) {
                if (this.f23033a != null) {
                    this.f23033a.run();
                }
                c.e();
            }
        }
    }

    /* access modifiers changed from: private */
    public static boolean d() {
        File file = new File(ak.a().getFilesDir(), "mistat");
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            try {
                g = new FileOutputStream(new File(file, d)).getChannel();
                f = g.lock();
                k.c(f23032a, "acquire lock for db");
                return true;
            } catch (Exception e2) {
                k.c(f23032a, "acquire lock for db failed with " + e2);
                try {
                    g.close();
                    g = null;
                } catch (Exception e3) {
                    k.c(f23032a, "close file stream failed with " + e3);
                }
                return false;
            }
        } catch (Exception e4) {
            k.c(f23032a, "acquire lock for db failed with " + e4);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static void e() {
        try {
            if (f != null) {
                f.release();
                f = null;
            }
            k.c(f23032a, "release sDBFileLock for db");
        } catch (Exception e2) {
            k.c(f23032a, "release sDBFileLock for db failed with " + e2);
        }
        try {
            if (g != null) {
                g.close();
                g = null;
            }
            k.c(f23032a, "release sLockFileChannel for db");
        } catch (Exception e3) {
            k.c(f23032a, "release sLockFileChannel for db failed with " + e3);
        }
    }
}
