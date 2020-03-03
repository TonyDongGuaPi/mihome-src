package com.amap.api.services.a;

public class dt extends dw {

    /* renamed from: a  reason: collision with root package name */
    private StringBuilder f4417a = new StringBuilder();
    private boolean b = true;

    public dt() {
    }

    public dt(dw dwVar) {
        super(dwVar);
    }

    /* access modifiers changed from: protected */
    public byte[] a(byte[] bArr) {
        byte[] a2 = bz.a(this.f4417a.toString());
        c(a2);
        this.b = true;
        this.f4417a.delete(0, this.f4417a.length());
        return a2;
    }

    public void b(byte[] bArr) {
        String a2 = bz.a(bArr);
        if (this.b) {
            this.b = false;
        } else {
            this.f4417a.append(",");
        }
        StringBuilder sb = this.f4417a;
        sb.append("{\"log\":\"");
        sb.append(a2);
        sb.append("\"}");
    }
}
