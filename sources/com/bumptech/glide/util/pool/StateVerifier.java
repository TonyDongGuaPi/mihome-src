package com.bumptech.glide.util.pool;

import android.support.annotation.NonNull;

public abstract class StateVerifier {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f5111a = false;

    /* access modifiers changed from: package-private */
    public abstract void a(boolean z);

    public abstract void b();

    @NonNull
    public static StateVerifier a() {
        return new DefaultStateVerifier();
    }

    private StateVerifier() {
    }

    private static class DefaultStateVerifier extends StateVerifier {

        /* renamed from: a  reason: collision with root package name */
        private volatile boolean f5113a;

        DefaultStateVerifier() {
            super();
        }

        public void b() {
            if (this.f5113a) {
                throw new IllegalStateException("Already released");
            }
        }

        public void a(boolean z) {
            this.f5113a = z;
        }
    }

    private static class DebugStateVerifier extends StateVerifier {

        /* renamed from: a  reason: collision with root package name */
        private volatile RuntimeException f5112a;

        DebugStateVerifier() {
            super();
        }

        public void b() {
            if (this.f5112a != null) {
                throw new IllegalStateException("Already released", this.f5112a);
            }
        }

        /* access modifiers changed from: package-private */
        public void a(boolean z) {
            if (z) {
                this.f5112a = new RuntimeException("Released");
            } else {
                this.f5112a = null;
            }
        }
    }
}
