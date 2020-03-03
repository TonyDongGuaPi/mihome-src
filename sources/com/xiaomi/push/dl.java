package com.xiaomi.push;

import com.xiaomi.push.dk;
import java.io.File;
import java.util.Date;

class dl extends dk.b {

    /* renamed from: a  reason: collision with root package name */
    File f12694a;
    final /* synthetic */ int d;
    final /* synthetic */ Date e;
    final /* synthetic */ Date f;
    final /* synthetic */ String g;
    final /* synthetic */ String h;
    final /* synthetic */ boolean i;
    final /* synthetic */ dk j;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    dl(dk dkVar, int i2, Date date, Date date2, String str, String str2, boolean z) {
        super();
        this.j = dkVar;
        this.d = i2;
        this.e = date;
        this.f = date2;
        this.g = str;
        this.h = str2;
        this.i = z;
    }

    public void b() {
        if (aa.d()) {
            try {
                File file = new File(this.j.b.getExternalFilesDir((String) null) + "/.logcache");
                file.mkdirs();
                if (file.isDirectory()) {
                    dj djVar = new dj();
                    djVar.a(this.d);
                    this.f12694a = djVar.a(this.j.b, this.e, this.f, file);
                }
            } catch (NullPointerException unused) {
            }
        }
    }

    public void c() {
        if (this.f12694a != null && this.f12694a.exists()) {
            this.j.f12691a.add(new dk.c(this.g, this.h, this.f12694a, this.i));
        }
        this.j.a(0);
    }
}
