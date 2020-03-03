package com.xiaomi.youpin.youpin_network;

public class NetworkConfigManager {

    /* renamed from: a  reason: collision with root package name */
    private static volatile NetworkConfigManager f23840a;
    private NetworkConfig b;

    private NetworkConfigManager() {
    }

    public static NetworkConfigManager a() {
        if (f23840a == null) {
            synchronized (NetworkConfigManager.class) {
                if (f23840a == null) {
                    f23840a = new NetworkConfigManager();
                }
            }
        }
        return f23840a;
    }

    public NetworkConfig b() {
        return this.b;
    }

    public void a(NetworkConfig networkConfig) {
        this.b = networkConfig;
    }
}
