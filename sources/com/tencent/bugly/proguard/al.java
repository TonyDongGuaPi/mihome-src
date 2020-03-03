package com.tencent.bugly.proguard;

import java.util.ArrayList;

public final class al extends k implements Cloneable {
    private static ArrayList<ak> b;

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<ak> f9034a = null;

    public final void a(StringBuilder sb, int i) {
    }

    public final void a(j jVar) {
        jVar.a(this.f9034a, 0);
    }

    public final void a(i iVar) {
        if (b == null) {
            b = new ArrayList<>();
            b.add(new ak());
        }
        this.f9034a = (ArrayList) iVar.a(b, 0, true);
    }
}
