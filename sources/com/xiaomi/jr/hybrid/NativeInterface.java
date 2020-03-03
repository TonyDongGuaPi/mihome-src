package com.xiaomi.jr.hybrid;

public interface NativeInterface {
    void a(int i, Object obj, Callback callback);

    Object b(int i);

    public static class Callback {

        /* renamed from: a  reason: collision with root package name */
        private int f1443a = -1;

        public void a(Object... objArr) {
            HybridCallbackManager.c(this);
        }

        public int a() {
            if (this.f1443a < 0) {
                this.f1443a = HybridCallbackManager.b(this);
            }
            return this.f1443a;
        }
    }
}
