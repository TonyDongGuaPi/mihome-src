package com.liulishuo.filedownloader;

import android.os.SystemClock;
import com.liulishuo.filedownloader.IDownloadSpeed;

public class DownloadSpeedMonitor implements IDownloadSpeed.Lookup, IDownloadSpeed.Monitor {

    /* renamed from: a  reason: collision with root package name */
    private long f6379a;
    private long b;
    private long c;
    private long d;
    private int e;
    private long f;
    private int g = 1000;

    public void a(long j) {
        this.d = SystemClock.uptimeMillis();
        this.c = j;
    }

    public void b(long j) {
        if (this.d > 0) {
            long j2 = j - this.c;
            this.f6379a = 0;
            long uptimeMillis = SystemClock.uptimeMillis() - this.d;
            if (uptimeMillis <= 0) {
                this.e = (int) j2;
            } else {
                this.e = (int) (j2 / uptimeMillis);
            }
        }
    }

    public void c(long j) {
        if (this.g > 0) {
            boolean z = true;
            if (this.f6379a != 0) {
                long uptimeMillis = SystemClock.uptimeMillis() - this.f6379a;
                if (uptimeMillis >= ((long) this.g) || (this.e == 0 && uptimeMillis > 0)) {
                    this.e = (int) ((j - this.b) / uptimeMillis);
                    this.e = Math.max(0, this.e);
                } else {
                    z = false;
                }
            }
            if (z) {
                this.b = j;
                this.f6379a = SystemClock.uptimeMillis();
            }
        }
    }

    public void a() {
        this.e = 0;
        this.f6379a = 0;
    }

    public int b() {
        return this.e;
    }

    public void a(int i) {
        this.g = i;
    }
}
