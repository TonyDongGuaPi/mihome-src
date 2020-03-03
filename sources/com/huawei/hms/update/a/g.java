package com.huawei.hms.update.a;

import com.amap.api.services.core.AMapException;
import java.io.File;
import java.io.IOException;

class g extends h {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f5908a;
    final /* synthetic */ f b;
    private long c = 0;
    private int d = this.b.e.b();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    g(f fVar, File file, int i, int i2) {
        super(file, i);
        this.b = fVar;
        this.f5908a = i2;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        super.write(bArr, i, i2);
        this.d += i2;
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - this.c) > 1000) {
            this.c = currentTimeMillis;
            a(this.d);
        }
        if (this.d == this.f5908a) {
            a(this.d);
        }
    }

    private void a(int i) {
        this.b.e.a(this.b.a(), i);
        this.b.a(AMapException.CODE_AMAP_NEARBY_INVALID_USERID, i, this.f5908a);
    }
}
