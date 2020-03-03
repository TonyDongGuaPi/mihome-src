package com.loc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class bt extends bz {

    /* renamed from: a  reason: collision with root package name */
    ByteArrayOutputStream f6525a = new ByteArrayOutputStream();

    public bt() {
    }

    public bt(bz bzVar) {
        super(bzVar);
    }

    /* access modifiers changed from: protected */
    public final byte[] a(byte[] bArr) {
        byte[] byteArray = this.f6525a.toByteArray();
        try {
            this.f6525a.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.f6525a = new ByteArrayOutputStream();
        return byteArray;
    }

    public final void b(byte[] bArr) {
        try {
            this.f6525a.write(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
