package com.ximalaya.ting.android.player;

import com.ximalaya.ting.android.player.model.HttpConfig;
import com.ximalaya.ting.android.player.xdcs.IStatToServerFactory;
import java.util.Map;

public class StaticConfig {

    /* renamed from: a  reason: collision with root package name */
    public static IDomainServerIpCallback f2290a;
    public static String b;
    public static HttpConfig c;
    public static Map<String, String> d;
    public static IStatToServerFactory e;

    public static void a(IDomainServerIpCallback iDomainServerIpCallback) {
        f2290a = iDomainServerIpCallback;
    }

    public static void a() {
        f2290a = null;
        c = null;
        e = null;
    }

    public static void a(String str) {
        b = str;
    }

    public static void a(HttpConfig httpConfig) {
        c = httpConfig;
    }

    public static void a(Map<String, String> map) {
        d = map;
    }

    public static void a(IStatToServerFactory iStatToServerFactory) {
        e = iStatToServerFactory;
    }
}
