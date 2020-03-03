package com.tencent.bugly.proguard;

public final class ao extends k implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    public String f9037a = "";
    private String b = "";

    public final void a(StringBuilder sb, int i) {
    }

    public final void a(j jVar) {
        jVar.a(this.f9037a, 0);
        jVar.a(this.b, 1);
    }

    public final void a(i iVar) {
        this.f9037a = iVar.b(0, true);
        this.b = iVar.b(1, true);
    }
}
