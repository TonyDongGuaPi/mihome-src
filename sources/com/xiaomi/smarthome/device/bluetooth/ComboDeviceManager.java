package com.xiaomi.smarthome.device.bluetooth;

import android.net.wifi.ScanResult;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.IBluetoothService;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.library.bluetooth.connect.Code;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.util.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ComboDeviceManager {

    /* renamed from: a  reason: collision with root package name */
    private static List<BleDevice> f15105a = new ArrayList();
    private static HashMap<String, Future<String>> b = new HashMap<>();

    private static boolean a(Future<String> future) {
        BluetoothLog.c(String.format("checkFuture %s", new Object[]{future}));
        if (future != null) {
            BluetoothLog.c(String.format(">>> isCanceled = %b", new Object[]{Boolean.valueOf(future.isCancelled())}));
            BluetoothLog.c(String.format(">>> isDone = %b", new Object[]{Boolean.valueOf(future.isDone())}));
        }
        if (future == null || future.isCancelled()) {
            return false;
        }
        try {
            if (!future.isDone()) {
                return true;
            }
            BluetoothLog.c(String.format(">>> result = %s", new Object[]{future.get()}));
            return !TextUtils.isEmpty(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        }
    }

    public static Future<String> a(final ScanResult scanResult) {
        Future<String> future = b.get(scanResult.BSSID);
        if (a(future)) {
            return future;
        }
        FutureTask futureTask = new FutureTask(new Callable<String>() {
            /* renamed from: a */
            public String call() throws Exception {
                return ComboDeviceManager.c(scanResult);
            }
        });
        b.put(scanResult.BSSID, futureTask);
        Task.a(futureTask, AsyncTask.THREAD_POOL_EXECUTOR, 500);
        return futureTask;
    }

    /* access modifiers changed from: private */
    public static String c(ScanResult scanResult) {
        String h = DeviceFactory.h(scanResult);
        LogUtilGrey.a("BleComboStep", "searchComboDeviceSync kuailian device uid is " + h);
        final Bundle bundle = new Bundle();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        BluetoothHelper.a(h, (BluetoothResponse) new BluetoothResponse() {
            public void onResponse(int i, Bundle bundle) throws RemoteException {
                String str = "";
                if (bundle != null) {
                    str = bundle.getString(IBluetoothService.Y, "");
                }
                BluetoothMyLogger.d(String.format("ComboDeviceManager.searchComboDeviceSync onResponse %s, mac = (%s)", new Object[]{Code.a(i), BluetoothMyLogger.a(str)}));
                if (i == 0 && bundle != null) {
                    bundle.putAll(bundle);
                }
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bundle.getString(IBluetoothService.Y, "");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
        r4 = r4.split("\\:");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.String r2, java.lang.String r3, java.lang.String r4) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 != 0) goto L_0x0032
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x0032
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L_0x0032
            java.lang.String r0 = "\\:"
            java.lang.String[] r4 = r4.split(r0)
            if (r4 == 0) goto L_0x0032
            int r0 = r4.length
            r1 = 6
            if (r0 != r1) goto L_0x0032
            r0 = 4
            r0 = r4[r0]
            boolean r2 = r2.equalsIgnoreCase(r0)
            if (r2 == 0) goto L_0x0032
            r2 = 5
            r2 = r4[r2]
            boolean r2 = r3.equalsIgnoreCase(r2)
            if (r2 == 0) goto L_0x0032
            r2 = 1
            return r2
        L_0x0032:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.bluetooth.ComboDeviceManager.a(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    public static BleDevice a(String str, String str2) {
        for (BleDevice next : f15105a) {
            if (a(str, str2, next.mac)) {
                return next;
            }
        }
        return null;
    }
}
