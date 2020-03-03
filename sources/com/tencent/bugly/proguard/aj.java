package com.tencent.bugly.proguard;

public final class aj extends k implements Cloneable {
    private static byte[] d;

    /* renamed from: a  reason: collision with root package name */
    private byte f9032a = 0;
    private String b = "";
    private byte[] c = null;

    public final void a(StringBuilder sb, int i) {
    }

    public aj() {
    }

    public aj(byte b2, String str, byte[] bArr) {
        this.f9032a = b2;
        this.b = str;
        this.c = bArr;
    }

    public final void a(j jVar) {
        jVar.a(this.f9032a, 0);
        jVar.a(this.b, 1);
        if (this.c != null) {
            jVar.a(this.c, 2);
        }
    }

    public final void a(i iVar) {
        this.f9032a = iVar.a(this.f9032a, 0, true);
        this.b = iVar.b(1, true);
        if (d == null) {
            byte[] bArr = new byte[1];
            d = bArr;
            bArr[0] = 0;
        }
        byte[] bArr2 = d;
        this.c = iVar.c(2, false);
    }
}
