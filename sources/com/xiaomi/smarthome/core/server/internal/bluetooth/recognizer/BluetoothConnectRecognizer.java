package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothApi;
import com.xiaomi.smarthome.core.server.internal.bluetooth.IBluetoothService;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleConnectResponse;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.bluetooth.utils.UUIDUtils;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BluetoothConnectRecognizer implements IBluetoothRecognizer {

    /* renamed from: a  reason: collision with root package name */
    private static final UUID f14266a = UUIDUtils.a("fee0");
    private static final Set<String> b = new HashSet();

    private BluetoothConnectRecognizer() {
    }

    public static BluetoothConnectRecognizer a() {
        return new BluetoothConnectRecognizer();
    }

    public BluetoothRecognizeResult a(BluetoothSearchResult bluetoothSearchResult) {
        BluetoothRecognizeResult bluetoothRecognizeResult = new BluetoothRecognizeResult();
        String g = bluetoothSearchResult.g();
        if (b.contains(g)) {
            return bluetoothRecognizeResult;
        }
        b.add(g);
        if (!bluetoothSearchResult.b() || !BluetoothUtils.a(bluetoothSearchResult.f())) {
            return bluetoothRecognizeResult;
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        final BluetoothSearchResult bluetoothSearchResult2 = bluetoothSearchResult;
        final String str = g;
        final BluetoothRecognizeResult bluetoothRecognizeResult2 = bluetoothRecognizeResult;
        final CountDownLatch countDownLatch2 = countDownLatch;
        BleConnectManager.a().a(g, (BleConnectOptions) null, (BleConnectResponse) new BleConnectResponse() {
            public void a(int i, Bundle bundle) {
                BluetoothLog.c(String.format("BluetoothConnectRecognizer Device: %s, connect code = %d", new Object[]{bluetoothSearchResult2, Integer.valueOf(i)}));
                if (i == 0) {
                    BluetoothApi.a(str, BluetoothConstants.i, BluetoothConstants.I, (IBleResponse) new IBleResponse() {
                        public IBinder asBinder() {
                            return null;
                        }

                        public void onResponse(int i, Bundle bundle) throws RemoteException {
                            BluetoothLog.c(String.format("BluetoothConnectRecognizer Device: %s, read pid code = %d", new Object[]{bluetoothSearchResult2, Integer.valueOf(i)}));
                            byte[] byteArray = bundle.getByteArray(IBluetoothService.P);
                            if (i != 0 || byteArray == null) {
                                countDownLatch2.countDown();
                                BluetoothConnectRecognizer.b(str);
                                return;
                            }
                            int h = ByteUtils.h(byteArray);
                            if (h > 0) {
                                BluetoothCache.b(str, h);
                                BluetoothCache.a(str, bluetoothSearchResult2.k());
                                bluetoothRecognizeResult2.f14269a = PluginManager.a().a(h);
                            }
                            BluetoothApi.a(str, BluetoothConstants.ag, BluetoothConstants.ah, (IBleResponse) new IBleResponse() {
                                public IBinder asBinder() {
                                    return null;
                                }

                                public void onResponse(int i, Bundle bundle) throws RemoteException {
                                    BluetoothLog.c(String.format("BluetoothConnectRecognizer Device: %s, read smac code = %d", new Object[]{bluetoothSearchResult2, Integer.valueOf(i)}));
                                    byte[] byteArray = bundle.getByteArray(IBluetoothService.P);
                                    if (i == 0 && byteArray != null) {
                                        String str = new String(byteArray);
                                        if (!TextUtils.isEmpty(str)) {
                                            BluetoothCache.h(str, str);
                                        }
                                    }
                                    countDownLatch2.countDown();
                                    BluetoothConnectRecognizer.b(str);
                                }
                            });
                        }
                    });
                    return;
                }
                countDownLatch2.countDown();
                BluetoothConnectRecognizer.b(str);
            }
        });
        try {
            countDownLatch.await(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            b(g);
        }
        return bluetoothRecognizeResult;
    }

    /* access modifiers changed from: private */
    public static void b(String str) {
        BleConnectManager.a().a(str);
    }

    private static boolean a(List<UUID> list) {
        if (ListUtils.a(list)) {
            return false;
        }
        for (UUID equals : list) {
            if (equals.equals(f14266a)) {
                return true;
            }
        }
        return false;
    }
}
