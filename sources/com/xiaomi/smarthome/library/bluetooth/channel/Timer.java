package com.xiaomi.smarthome.library.bluetooth.channel;

import android.os.Handler;
import android.os.Looper;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.util.concurrent.TimeoutException;

public class Timer {

    /* renamed from: a  reason: collision with root package name */
    private Handler f18491a = new Handler(Looper.getMainLooper());
    private TimerCallback b;

    private Timer() {
    }

    public static Timer a() {
        return new Timer();
    }

    public static abstract class TimerCallback implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private String f18492a;

        public abstract void a() throws TimeoutException;

        public abstract void b();

        public TimerCallback(String str) {
            this.f18492a = str;
        }

        public String c() {
            return this.f18492a;
        }

        public final void run() {
            BluetoothLog.b(String.format("%s: Timer expired!!!", new Object[]{this.f18492a}));
            try {
                a();
            } catch (TimeoutException e) {
                BluetoothLog.a((Throwable) e);
            }
            b();
        }
    }

    public synchronized void b() {
        this.b = null;
    }

    public synchronized void c() {
        this.f18491a.removeCallbacksAndMessages((Object) null);
        this.b = null;
    }

    public synchronized boolean d() {
        return this.b != null;
    }

    public synchronized String e() {
        return d() ? this.b.c() : "";
    }

    public synchronized void a(TimerCallback timerCallback, long j) {
        this.f18491a.removeCallbacksAndMessages((Object) null);
        Looper myLooper = Looper.myLooper();
        if (myLooper == null) {
            myLooper = Looper.getMainLooper();
        }
        this.f18491a = new Handler(myLooper);
        this.f18491a.postDelayed(timerCallback, j);
        this.b = timerCallback;
    }
}
