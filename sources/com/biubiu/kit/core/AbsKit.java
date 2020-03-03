package com.biubiu.kit.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class AbsKit {

    /* renamed from: a  reason: collision with root package name */
    private KitBaseAdapter f4796a;

    public abstract View a(ViewGroup viewGroup);

    public abstract void a(int i, Object obj);

    protected AbsKit() {
    }

    public static View a(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        return layoutInflater.inflate(i, viewGroup, false);
    }

    public static View a(ViewGroup viewGroup, int i) {
        return a((LayoutInflater) null, viewGroup, i);
    }

    public void a(KitBaseAdapter kitBaseAdapter) {
        this.f4796a = kitBaseAdapter;
    }

    /* access modifiers changed from: protected */
    public boolean a() {
        return this.f4796a != null && this.f4796a.b();
    }

    /* access modifiers changed from: protected */
    public OnItemClickListener b() {
        if (this.f4796a == null) {
            return null;
        }
        return this.f4796a.a();
    }
}
