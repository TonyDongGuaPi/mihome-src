package com.tmall.wireless.vaf.virtualview.core;

import android.view.View;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.framework.cm.ContainerService;

public abstract class Adapter {

    /* renamed from: a  reason: collision with root package name */
    protected static final String f9379a = "type";
    protected VafContext b;
    protected boolean c = true;
    protected int d = 0;
    protected ContainerService e;

    public abstract int a();

    public abstract void a(ViewHolder viewHolder, int i);

    public abstract void a(Object obj);

    public int b(int i) {
        return 0;
    }

    public abstract ViewHolder c(int i);

    public Adapter(VafContext vafContext) {
        this.e = vafContext.q();
        this.b = vafContext;
    }

    public void a(int i) {
        this.d = i;
    }

    public void b() {
        this.c = true;
    }

    public static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public View f9380a;
        public int b;
        public int c;

        public ViewHolder(View view) {
            this.f9380a = view;
            this.f9380a.setTag(this);
        }
    }
}
