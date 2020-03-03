package org.apache.commons.lang.time;

public class StopWatch {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3432a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 10;
    private static final int f = 11;
    private int g = 0;
    private int h = 10;
    private long i = -1;
    private long j = -1;

    public void a() {
        if (this.g == 2) {
            throw new IllegalStateException("Stopwatch must be reset before being restarted. ");
        } else if (this.g == 0) {
            this.j = -1;
            this.i = System.currentTimeMillis();
            this.g = 1;
        } else {
            throw new IllegalStateException("Stopwatch already started. ");
        }
    }

    public void b() {
        if (this.g == 1 || this.g == 3) {
            if (this.g == 1) {
                this.j = System.currentTimeMillis();
            }
            this.g = 2;
            return;
        }
        throw new IllegalStateException("Stopwatch is not running. ");
    }

    public void c() {
        this.g = 0;
        this.h = 10;
        this.i = -1;
        this.j = -1;
    }

    public void d() {
        if (this.g == 1) {
            this.j = System.currentTimeMillis();
            this.h = 11;
            return;
        }
        throw new IllegalStateException("Stopwatch is not running. ");
    }

    public void e() {
        if (this.h == 11) {
            this.j = -1;
            this.h = 10;
            return;
        }
        throw new IllegalStateException("Stopwatch has not been split. ");
    }

    public void f() {
        if (this.g == 1) {
            this.j = System.currentTimeMillis();
            this.g = 3;
            return;
        }
        throw new IllegalStateException("Stopwatch must be running to suspend. ");
    }

    public void g() {
        if (this.g == 3) {
            this.i += System.currentTimeMillis() - this.j;
            this.j = -1;
            this.g = 1;
            return;
        }
        throw new IllegalStateException("Stopwatch must be suspended to resume. ");
    }

    public long h() {
        if (this.g == 2 || this.g == 3) {
            return this.j - this.i;
        }
        if (this.g == 0) {
            return 0;
        }
        if (this.g == 1) {
            return System.currentTimeMillis() - this.i;
        }
        throw new RuntimeException("Illegal running state has occured. ");
    }

    public long i() {
        if (this.h == 11) {
            return this.j - this.i;
        }
        throw new IllegalStateException("Stopwatch must be split to get the split time. ");
    }

    public long j() {
        if (this.g != 0) {
            return this.i;
        }
        throw new IllegalStateException("Stopwatch has not been started");
    }

    public String toString() {
        return DurationFormatUtils.a(h());
    }

    public String k() {
        return DurationFormatUtils.a(i());
    }
}
