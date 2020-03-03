package com.tencent.bugly.proguard;

import java.util.ArrayList;

public final class ai extends k implements Cloneable {
    private static ArrayList<String> c;

    /* renamed from: a  reason: collision with root package name */
    private String f9031a = "";
    private ArrayList<String> b = null;

    public final void a(StringBuilder sb, int i) {
    }

    public final void a(j jVar) {
        jVar.a(this.f9031a, 0);
        if (this.b != null) {
            jVar.a(this.b, 1);
        }
    }

    public final void a(i iVar) {
        this.f9031a = iVar.b(0, true);
        if (c == null) {
            c = new ArrayList<>();
            c.add("");
        }
        this.b = (ArrayList) iVar.a(c, 1, false);
    }
}
