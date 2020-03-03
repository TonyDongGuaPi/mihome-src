package com.alipay.mobile.common.logging.helper;

import android.content.Context;
import com.alipay.mobile.common.logging.api.DeviceHWInfo;

public class DeviceHWRenderHelper {
    public static int getNumCoresOfCPU() {
        return DeviceHWInfo.getNumberOfCPUCores();
    }

    public static int getCPUMaxFreqMHz() {
        int cPUMaxFreqKHz = DeviceHWInfo.getCPUMaxFreqKHz();
        if (cPUMaxFreqKHz == -1 || cPUMaxFreqKHz <= 0) {
            return -1;
        }
        return cPUMaxFreqKHz / 1000;
    }

    public static long getTotalMem(Context context) {
        long totalMemory = DeviceHWInfo.getTotalMemory(context);
        if (totalMemory == -1 || totalMemory <= 0) {
            return -1;
        }
        return totalMemory / 1048576;
    }
}
