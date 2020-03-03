package com.xiaomi.smarthome.library.common.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import com.xiaomi.smarthome.library.deviceId.DeviceIdCompat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
    public static String a(Context context, boolean z) {
        if (context == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("total_mem ");
            double d = (double) memoryInfo.totalMem;
            Double.isNaN(d);
            sb2.append((d / 1024.0d) / 1024.0d);
            sb2.append("MB");
            sb.append(sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append("avai_mem ");
            double d2 = (double) (memoryInfo.availMem >> 10);
            Double.isNaN(d2);
            sb3.append(d2 / 1024.0d);
            sb3.append("MB");
            sb.append(sb3.toString());
            sb.append("is_low_mem " + memoryInfo.lowMemory);
            StringBuilder sb4 = new StringBuilder();
            sb4.append("threshold ");
            double d3 = (double) memoryInfo.threshold;
            Double.isNaN(d3);
            sb4.append((d3 / 1024.0d) / 1024.0d);
            sb4.append("MB");
            sb.append(sb4.toString());
            if (z) {
                long currentTimeMillis = System.currentTimeMillis();
                Debug.dumpHprofData(Environment.getExternalStorageDirectory().getPath() + "/crash_log/" + ("crash-" + new SimpleDateFormat().format(new Date()) + "-" + currentTimeMillis + ".hprof"));
                Debug.MemoryInfo memoryInfo2 = new Debug.MemoryInfo();
                Debug.getMemoryInfo(memoryInfo2);
                StringBuilder sb5 = new StringBuilder();
                sb5.append("nat_mem ");
                double nativeHeapAllocatedSize = (double) Debug.getNativeHeapAllocatedSize();
                Double.isNaN(nativeHeapAllocatedSize);
                sb5.append((nativeHeapAllocatedSize / 1024.0d) / 1024.0d);
                sb5.append("MB");
                sb.append(sb5.toString());
                StringBuilder sb6 = new StringBuilder();
                sb6.append("nat_free_mem ");
                double nativeHeapFreeSize = (double) Debug.getNativeHeapFreeSize();
                Double.isNaN(nativeHeapFreeSize);
                sb6.append((nativeHeapFreeSize / 1024.0d) / 1024.0d);
                sb6.append("MB");
                sb.append(sb6.toString());
                StringBuilder sb7 = new StringBuilder();
                sb7.append("nat_heap_size ");
                double nativeHeapSize = (double) Debug.getNativeHeapSize();
                Double.isNaN(nativeHeapSize);
                sb7.append((nativeHeapSize / 1024.0d) / 1024.0d);
                sb7.append("MB");
                sb.append(sb7.toString());
                StringBuilder sb8 = new StringBuilder();
                sb8.append("d_priv_dir ");
                double d4 = (double) memoryInfo2.dalvikPrivateDirty;
                Double.isNaN(d4);
                sb8.append(d4 / 1024.0d);
                sb8.append("MB");
                sb.append(sb8.toString());
                sb.append("d_pss " + memoryInfo2.dalvikPss);
                sb.append("d_pss " + memoryInfo2.dalvikSharedDirty);
                sb.append("d_pss " + memoryInfo2.nativePrivateDirty);
                sb.append("d_pss " + memoryInfo2.nativePss);
                sb.append("d_pss " + memoryInfo2.nativeSharedDirty);
                sb.append("d_pss " + memoryInfo2.otherPrivateDirty);
                sb.append("d_pss " + memoryInfo2.otherPss);
                sb.append("d_pss " + memoryInfo2.otherSharedDirty);
            }
            StringBuilder sb9 = new StringBuilder();
            sb9.append("应用可使用最大内存 ");
            double maxMemory = (double) Runtime.getRuntime().maxMemory();
            Double.isNaN(maxMemory);
            sb9.append((maxMemory / 1024.0d) / 1024.0d);
            sb9.append("MB");
            sb.append(sb9.toString());
            StringBuilder sb10 = new StringBuilder();
            sb10.append("应用空闲内存 ");
            double freeMemory = (double) Runtime.getRuntime().freeMemory();
            Double.isNaN(freeMemory);
            sb10.append((freeMemory / 1024.0d) / 1024.0d);
            sb10.append("MB");
            sb.append(sb10.toString());
            StringBuilder sb11 = new StringBuilder();
            sb11.append("应用占用总内存 ");
            double d5 = (double) Runtime.getRuntime().totalMemory();
            Double.isNaN(d5);
            sb11.append((d5 / 1024.0d) / 1024.0d);
            sb11.append("MB");
            sb.append(sb11.toString());
            return sb.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return th.getMessage();
        }
    }

    @SuppressLint({"MissingPermission"})
    private static String c(Context context) {
        return DeviceIdCompat.a(context);
    }

    public static String a(Context context) {
        String b = b(context);
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        if (b.length() >= 32) {
            return b.substring(0, 32);
        }
        StringBuilder sb = new StringBuilder(b);
        for (int length = b.length(); length < 32; length++) {
            sb.append(b.charAt(length % b.length()));
        }
        return sb.toString();
    }

    public static String b(Context context) {
        if (context == null) {
            return null;
        }
        return Settings.Secure.getString(context.getApplicationContext().getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
    }
}
