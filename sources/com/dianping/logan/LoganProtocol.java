package com.dianping.logan;

class LoganProtocol implements LoganProtocolHandler {

    /* renamed from: a  reason: collision with root package name */
    private static LoganProtocol f5166a;
    private LoganProtocolHandler b;
    private boolean c;
    private OnLoganProtocolStatus d;

    private LoganProtocol() {
    }

    static LoganProtocol a() {
        if (f5166a == null) {
            synchronized (LoganProtocol.class) {
                f5166a = new LoganProtocol();
            }
        }
        return f5166a;
    }

    public void c() {
        if (this.b != null) {
            this.b.c();
        }
    }

    public void a(int i, String str, long j, String str2, long j2, boolean z) {
        if (this.b != null) {
            this.b.a(i, str, j, str2, j2, z);
        }
    }

    public void a(String str) {
        if (this.b != null) {
            this.b.a(str);
        }
    }

    public void a(String str, String str2, int i, String str3, String str4) {
        if (!this.c) {
            if (CLoganProtocol.a()) {
                this.b = CLoganProtocol.b();
                this.b.a(this.d);
                this.b.a(str, str2, i, str3, str4);
                this.c = true;
                return;
            }
            this.b = null;
        }
    }

    public void a(boolean z) {
        if (this.b != null) {
            this.b.a(z);
        }
    }

    public void a(OnLoganProtocolStatus onLoganProtocolStatus) {
        this.d = onLoganProtocolStatus;
    }
}
