package com.xiaomi.push;

import com.xiaomi.channel.commonutils.logger.LoggerInterface;

public class dn implements LoggerInterface {

    /* renamed from: a  reason: collision with root package name */
    private LoggerInterface f12696a = null;
    private LoggerInterface b = null;

    public dn(LoggerInterface loggerInterface, LoggerInterface loggerInterface2) {
        this.f12696a = loggerInterface;
        this.b = loggerInterface2;
    }

    public void a(String str) {
    }

    public void a(String str, Throwable th) {
        if (this.f12696a != null) {
            this.f12696a.a(str, th);
        }
        if (this.b != null) {
            this.b.a(str, th);
        }
    }

    public void b(String str) {
        if (this.f12696a != null) {
            this.f12696a.b(str);
        }
        if (this.b != null) {
            this.b.b(str);
        }
    }
}
