package com.mi.global.bbs.utils;

import com.mi.mistatistic.sdk.MiStatInterface;

public class GoogleTrackerUtil {
    public static void sendPageShowEvent(String str, String str2) {
    }

    public static void sendRecordPage(String str) {
    }

    public static void sendRecordEvent(String str, String str2, String str3) {
        MiStatInterface.a(str2, "bbs_" + str, "key", str3);
    }
}
