package com.mi.global.shop.util;

public class ChannelUtil extends com.mi.util.ChannelUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f7050a = {"GOOGLEPLAY", "MSITE", "OTA"};

    public static void a() {
        if (sChannelUtil == null) {
            sChannelUtil = new ChannelUtil();
        }
    }

    private ChannelUtil() {
        this.channels = f7050a;
    }

    public static ChannelUtil b() {
        return (ChannelUtil) sChannelUtil;
    }
}
