package com.alipay.mobile.common.logging.helper;

import android.content.Context;
import com.alipay.mobile.common.logging.api.DeviceHWInfo;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import java.util.ArrayList;
import java.util.Collections;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class YearClass {
    public static final int CLASS_2008 = 2008;
    public static final int CLASS_2009 = 2009;
    public static final int CLASS_2010 = 2010;
    public static final int CLASS_2011 = 2011;
    public static final int CLASS_2012 = 2012;
    public static final int CLASS_2013 = 2013;
    public static final int CLASS_2014 = 2014;
    public static final int CLASS_UNKNOWN = -1;

    /* renamed from: a  reason: collision with root package name */
    private static volatile Integer f959a;

    public static int get(Context context) {
        if (f959a == null) {
            synchronized (YearClass.class) {
                if (f959a == null) {
                    f959a = Integer.valueOf(a(context));
                    LoggerFactory.getTraceLogger().info("YearClass", String.valueOf(f959a));
                }
            }
        }
        return f959a.intValue();
    }

    private static void a(ArrayList<Integer> arrayList, int i) {
        if (i != -1) {
            arrayList.add(Integer.valueOf(i));
        }
    }

    private static int a(Context context) {
        ArrayList arrayList = new ArrayList();
        a(arrayList, a());
        a(arrayList, b());
        a(arrayList, b(context));
        if (arrayList.isEmpty()) {
            return -1;
        }
        Collections.sort(arrayList);
        if ((arrayList.size() & 1) == 1) {
            return ((Integer) arrayList.get(arrayList.size() / 2)).intValue();
        }
        int size = (arrayList.size() / 2) - 1;
        return ((Integer) arrayList.get(size)).intValue() + ((((Integer) arrayList.get(size + 1)).intValue() - ((Integer) arrayList.get(size)).intValue()) / 2);
    }

    private static int a() {
        int numberOfCPUCores = DeviceHWInfo.getNumberOfCPUCores();
        if (numberOfCPUCores < 1) {
            return -1;
        }
        if (numberOfCPUCores == 1) {
            return 2008;
        }
        return numberOfCPUCores <= 3 ? 2011 : 2012;
    }

    private static int b() {
        long cPUMaxFreqKHz = (long) DeviceHWInfo.getCPUMaxFreqKHz();
        if (cPUMaxFreqKHz == -1) {
            return -1;
        }
        if (cPUMaxFreqKHz <= 528000) {
            return 2008;
        }
        if (cPUMaxFreqKHz <= 620000) {
            return 2009;
        }
        if (cPUMaxFreqKHz <= 1020000) {
            return 2010;
        }
        if (cPUMaxFreqKHz <= 1220000) {
            return 2011;
        }
        if (cPUMaxFreqKHz <= 1520000) {
            return 2012;
        }
        return cPUMaxFreqKHz <= 2020000 ? 2013 : 2014;
    }

    private static int b(Context context) {
        long totalMemory = DeviceHWInfo.getTotalMemory(context);
        if (totalMemory <= 0) {
            return -1;
        }
        if (totalMemory <= 201326592) {
            return 2008;
        }
        if (totalMemory <= 304087040) {
            return 2009;
        }
        if (totalMemory <= IjkMediaMeta.AV_CH_STEREO_LEFT) {
            return 2010;
        }
        if (totalMemory <= 1073741824) {
            return 2011;
        }
        if (totalMemory <= IjkMediaMeta.AV_CH_LAYOUT_STEREO_DOWNMIX) {
            return 2012;
        }
        return totalMemory <= IjkMediaMeta.AV_CH_WIDE_LEFT ? 2013 : 2014;
    }
}
