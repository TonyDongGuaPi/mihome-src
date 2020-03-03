package com.xiaomi.youpin.youpin_network;

import android.content.Context;

public class NetworkConfig {

    /* renamed from: a  reason: collision with root package name */
    private Context f23838a;
    private NetWorkDependency b;

    public NetworkConfig(Builder builder) {
        this.f23838a = builder.b;
        this.b = builder.a();
    }

    public NetWorkDependency a() {
        return this.b;
    }

    public void a(NetWorkDependency netWorkDependency) {
        this.b = netWorkDependency;
    }

    public Context b() {
        return this.f23838a;
    }

    public void a(Context context) {
        this.f23838a = context;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        NetWorkDependency f23839a;
        /* access modifiers changed from: private */
        public Context b;

        public Builder(Context context) {
            this.b = context.getApplicationContext();
        }

        public NetWorkDependency a() {
            return this.f23839a;
        }

        public Builder a(NetWorkDependency netWorkDependency) {
            this.f23839a = netWorkDependency;
            return this;
        }

        public Context b() {
            return this.b;
        }

        public void a(Context context) {
            this.b = context;
        }

        public NetworkConfig c() {
            return new NetworkConfig(this);
        }
    }
}
