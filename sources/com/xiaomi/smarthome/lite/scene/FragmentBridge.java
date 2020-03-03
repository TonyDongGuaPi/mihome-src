package com.xiaomi.smarthome.lite.scene;

import java.lang.ref.WeakReference;

public class FragmentBridge implements ChildFragmentDelegate, ParentFragmentDelegate {

    /* renamed from: a  reason: collision with root package name */
    private WeakReference<ChildFragmentDelegate> f19377a;
    private WeakReference<ParentFragmentDelegate> b;

    public boolean b() {
        return false;
    }

    public void a(ChildFragmentDelegate childFragmentDelegate) {
        if (childFragmentDelegate == null) {
            this.f19377a = null;
        } else {
            this.f19377a = new WeakReference<>(childFragmentDelegate);
        }
    }

    public void a(ParentFragmentDelegate parentFragmentDelegate) {
        this.b = new WeakReference<>(parentFragmentDelegate);
    }

    public void b(ParentFragmentDelegate parentFragmentDelegate) {
        if (this.b != null && !parentFragmentDelegate.equals(parentFragmentDelegate)) {
            this.b = null;
        }
    }

    public void b(ChildFragmentDelegate childFragmentDelegate) {
        if (this.f19377a != null && !childFragmentDelegate.equals(childFragmentDelegate)) {
            this.f19377a = null;
        }
    }

    public static FragmentBridge f() {
        return Holder.f19378a;
    }

    public void a(boolean z) {
        if (this.f19377a != null && this.f19377a.get() != null) {
            ((ChildFragmentDelegate) this.f19377a.get()).a(z);
        }
    }

    public void a() {
        if (this.f19377a != null && this.f19377a.get() != null) {
            ((ChildFragmentDelegate) this.f19377a.get()).a();
        }
    }

    public void c() {
        if (this.f19377a != null && this.f19377a.get() != null) {
            ((ChildFragmentDelegate) this.f19377a.get()).c();
        }
    }

    public void d() {
        if (this.f19377a != null && this.f19377a.get() != null) {
            ((ChildFragmentDelegate) this.f19377a.get()).d();
        }
    }

    public boolean e() {
        return (this.f19377a == null || this.f19377a.get() == null || !((ChildFragmentDelegate) this.f19377a.get()).e()) ? false : true;
    }

    public void g() {
        if (this.b != null && this.b.get() != null) {
            ((ParentFragmentDelegate) this.b.get()).g();
        }
    }

    public void b(boolean z) {
        if (this.b != null && this.b.get() != null) {
            ((ParentFragmentDelegate) this.b.get()).b(z);
        }
    }

    public void h() {
        if (this.b != null && this.b.get() != null) {
            ((ParentFragmentDelegate) this.b.get()).h();
        }
    }

    public void i() {
        if (this.b != null && this.b.get() != null) {
            ((ParentFragmentDelegate) this.b.get()).i();
        }
    }

    private static class Holder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static FragmentBridge f19378a = new FragmentBridge();

        private Holder() {
        }
    }

    private FragmentBridge() {
    }
}
