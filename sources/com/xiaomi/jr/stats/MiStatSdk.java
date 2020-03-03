package com.xiaomi.jr.stats;

import android.content.Context;
import com.xiaomi.jr.common.utils.Constants;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.stat.MiStat;
import com.xiaomi.stat.MiStatParams;
import com.xiaomi.stat.NetAvailableEvent;
import java.util.Map;

public class MiStatSdk {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11028a = "MiStatSdk";

    public static void a(Context context, String str, String str2, String str3) {
        if (Constants.f1410a) {
            MiStat.initialize(context, str, str2, true, str3);
            MiStat.setCustomPrivacyState(true);
            MiStat.setNetworkAccessEnabled(true);
            MiStat.setUploadInterval(5);
            MiStat.setUseSystemUploadingService(true);
            MiStat.setExceptionCatcherEnabled(true);
            MiStat.setDebugModeEnabled(false);
        }
    }

    static void a(String str) {
        if (Constants.f1410a) {
            try {
                MiStat.setUserId(str);
            } catch (Exception e) {
                MifiLog.e(f11028a, e.getMessage());
            }
        }
    }

    static void a(String str, String str2, Map<String, String> map) {
        if (Constants.f1410a) {
            try {
                MiStat.trackEvent(str, str2, a(map, new MiStatParams()));
            } catch (Exception e) {
                MifiLog.e(f11028a, e.getMessage());
            }
        }
    }

    private static MiStatParams a(Map<String, String> map, MiStatParams miStatParams) {
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                miStatParams.putString((String) next.getKey(), (String) next.getValue());
            }
        }
        return miStatParams;
    }

    public static void a(NetAvailableEvent netAvailableEvent) {
        if (Constants.f1410a) {
            MiStat.trackNetAvaliable(netAvailableEvent);
        }
    }
}
