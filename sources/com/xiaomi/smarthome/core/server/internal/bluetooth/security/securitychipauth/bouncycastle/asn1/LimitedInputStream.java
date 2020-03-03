package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.InputStream;

abstract class LimitedInputStream extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    protected final InputStream f14447a;
    private int b;

    LimitedInputStream(InputStream inputStream, int i) {
        this.f14447a = inputStream;
        this.b = i;
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public void b(boolean z) {
        if (this.f14447a instanceof IndefiniteLengthInputStream) {
            ((IndefiniteLengthInputStream) this.f14447a).a(z);
        }
    }
}
