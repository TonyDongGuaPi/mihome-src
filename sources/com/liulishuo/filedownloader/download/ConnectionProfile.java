package com.liulishuo.filedownloader.download;

import com.liulishuo.filedownloader.util.FileDownloadUtils;

public class ConnectionProfile {

    /* renamed from: a  reason: collision with root package name */
    final long f6419a;
    final long b;
    final long c;
    final long d;

    ConnectionProfile(long j, long j2, long j3, long j4) {
        this.f6419a = j;
        this.b = j2;
        this.c = j3;
        this.d = j4;
    }

    public String toString() {
        return FileDownloadUtils.a("range[%d, %d) current offset[%d]", Long.valueOf(this.f6419a), Long.valueOf(this.c), Long.valueOf(this.b));
    }
}
