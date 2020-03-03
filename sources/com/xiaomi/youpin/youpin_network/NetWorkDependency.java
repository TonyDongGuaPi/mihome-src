package com.xiaomi.youpin.youpin_network;

public interface NetWorkDependency {

    public interface ServiceTokenCallback {
        void a();

        void a(int i, String str, boolean z);
    }

    void a();

    void a(ServiceTokenCallback serviceTokenCallback);

    boolean b();

    String c();

    boolean d();

    String e();
}
