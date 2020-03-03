package com.loc;

public final class bw extends bz {

    /* renamed from: a  reason: collision with root package name */
    private StringBuilder f6528a = new StringBuilder();
    private boolean b = true;

    public bw() {
    }

    public bw(bz bzVar) {
        super(bzVar);
    }

    /* access modifiers changed from: protected */
    public final byte[] a(byte[] bArr) {
        byte[] a2 = ad.a(this.f6528a.toString());
        this.d = a2;
        this.b = true;
        this.f6528a.delete(0, this.f6528a.length());
        return a2;
    }

    public final void b(byte[] bArr) {
        String a2 = ad.a(bArr);
        if (this.b) {
            this.b = false;
        } else {
            this.f6528a.append(",");
        }
        StringBuilder sb = this.f6528a;
        sb.append("{\"log\":\"");
        sb.append(a2);
        sb.append("\"}");
    }
}
