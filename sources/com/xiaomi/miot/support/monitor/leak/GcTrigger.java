package com.xiaomi.miot.support.monitor.leak;

public interface GcTrigger {

    /* renamed from: a  reason: collision with root package name */
    public static final GcTrigger f11485a = new GcTrigger() {
        public void a() {
            Runtime.getRuntime().gc();
            b();
            System.runFinalization();
        }

        private void b() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException unused) {
                throw new AssertionError();
            }
        }
    };

    void a();
}
