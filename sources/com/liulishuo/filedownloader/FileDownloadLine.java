package com.liulishuo.filedownloader;

import android.app.Notification;
import android.os.Looper;
import java.io.File;

public class FileDownloadLine {

    interface ConnectSubscriber {
        void a();

        Object b();
    }

    public void a(final int i, final Notification notification) {
        if (FileDownloader.a().j()) {
            FileDownloader.a().a(i, notification);
        } else {
            a((ConnectSubscriber) new ConnectSubscriber() {
                public Object b() {
                    return null;
                }

                public void a() {
                    FileDownloader.a().a(i, notification);
                }
            });
        }
    }

    public long a(final int i) {
        if (FileDownloader.a().j()) {
            return FileDownloader.a().d(i);
        }
        AnonymousClass2 r0 = new ConnectSubscriber() {
            private long c;

            public void a() {
                this.c = FileDownloader.a().d(i);
            }

            public Object b() {
                return Long.valueOf(this.c);
            }
        };
        a((ConnectSubscriber) r0);
        return ((Long) r0.b()).longValue();
    }

    public long b(final int i) {
        if (FileDownloader.a().j()) {
            return FileDownloader.a().e(i);
        }
        AnonymousClass3 r0 = new ConnectSubscriber() {
            private long c;

            public void a() {
                this.c = FileDownloader.a().e(i);
            }

            public Object b() {
                return Long.valueOf(this.c);
            }
        };
        a((ConnectSubscriber) r0);
        return ((Long) r0.b()).longValue();
    }

    public byte a(final int i, final String str) {
        if (FileDownloader.a().j()) {
            return FileDownloader.a().b(i, str);
        }
        if (str != null && new File(str).exists()) {
            return -3;
        }
        AnonymousClass4 r0 = new ConnectSubscriber() {
            private byte d;

            public void a() {
                this.d = FileDownloader.a().b(i, str);
            }

            public Object b() {
                return Byte.valueOf(this.d);
            }
        };
        a((ConnectSubscriber) r0);
        return ((Byte) r0.b()).byteValue();
    }

    private void a(ConnectSubscriber connectSubscriber) {
        ConnectListener connectListener = new ConnectListener(connectSubscriber);
        synchronized (connectListener) {
            FileDownloader.a().a((Runnable) connectListener);
            if (!connectListener.a()) {
                if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                    try {
                        connectListener.wait(200000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    throw new IllegalThreadStateException("Sorry, FileDownloader can not block the main thread, because the system is also  callbacks ServiceConnection#onServiceConnected method in the main thread.");
                }
            }
        }
    }

    static class ConnectListener implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private boolean f6388a = false;
        private final ConnectSubscriber b;

        ConnectListener(ConnectSubscriber connectSubscriber) {
            this.b = connectSubscriber;
        }

        public boolean a() {
            return this.f6388a;
        }

        public void run() {
            synchronized (this) {
                this.b.a();
                this.f6388a = true;
                notifyAll();
            }
        }
    }
}
