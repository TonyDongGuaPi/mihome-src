package com.amap.openapi;

import android.content.Context;
import android.os.Handler;
import com.amap.openapi.df;
import java.util.ArrayList;
import java.util.List;

public class dh implements df.a {

    /* renamed from: a  reason: collision with root package name */
    private final List<a> f4696a = new ArrayList();
    private di b;

    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private Handler f4697a;

        public void a() {
            this.f4697a.sendEmptyMessage(0);
        }
    }

    public dh(Context context, di diVar) {
        this.b = diVar;
        this.b.a(context, this);
    }

    public void a() {
        synchronized (this.f4696a) {
            for (a a2 : this.f4696a) {
                a2.a();
            }
        }
    }
}
