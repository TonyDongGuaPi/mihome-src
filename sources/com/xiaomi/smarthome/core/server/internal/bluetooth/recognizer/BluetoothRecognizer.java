package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.util.SerialExecutor;
import com.xiaomi.smarthome.library.common.util.Task;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class BluetoothRecognizer {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final IBluetoothRecognizer[] f14270a = {BluetoothBeaconRecognizer.a(), BluetoothCacheRecognizer.a(), BluetoothFilterRecognizer.a(), BluetoothConnectRecognizer.a()};
    private static BluetoothRecognizer d;
    /* access modifiers changed from: private */
    public Handler b = new Handler(Looper.getMainLooper());
    private final Executor c = new SerialExecutor();

    private BluetoothRecognizer() {
    }

    public static BluetoothRecognizer a() {
        if (d == null) {
            d = new BluetoothRecognizer();
        }
        return d;
    }

    public void a(BluetoothSearchResult bluetoothSearchResult, IBluetoothRecognizeResponse iBluetoothRecognizeResponse) {
        Task.a((Task) new BluetoothRecognizeTask(bluetoothSearchResult, iBluetoothRecognizeResponse), this.c);
    }

    public BluetoothRecognizeResult a(BluetoothSearchResult bluetoothSearchResult, int i) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final BluetoothRecognizeResult bluetoothRecognizeResult = new BluetoothRecognizeResult();
        a(bluetoothSearchResult, (IBluetoothRecognizeResponse) new IBluetoothRecognizeResponse() {
            public void a(BluetoothRecognizeResult bluetoothRecognizeResult) {
                bluetoothRecognizeResult.a(bluetoothRecognizeResult);
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await((long) i, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
        }
        return bluetoothRecognizeResult;
    }

    public BluetoothRecognizeResult a(BluetoothSearchResult bluetoothSearchResult) {
        return a(bluetoothSearchResult, 5000);
    }

    private class BluetoothRecognizeTask extends Task {

        /* renamed from: a  reason: collision with root package name */
        BluetoothSearchResult f14272a;
        IBluetoothRecognizeResponse b;

        BluetoothRecognizeTask(BluetoothSearchResult bluetoothSearchResult, IBluetoothRecognizeResponse iBluetoothRecognizeResponse) {
            this.f14272a = bluetoothSearchResult;
            this.b = iBluetoothRecognizeResponse;
        }

        public void a() {
            BluetoothRecognizeResult a2;
            BluetoothRecognizeResult bluetoothRecognizeResult = new BluetoothRecognizeResult();
            IBluetoothRecognizer[] b2 = BluetoothRecognizer.f14270a;
            int length = b2.length;
            BluetoothRecognizeResult bluetoothRecognizeResult2 = bluetoothRecognizeResult;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                IBluetoothRecognizer iBluetoothRecognizer = b2[i];
                a2 = iBluetoothRecognizer.a(this.f14272a);
                if (a2 != null && !TextUtils.isEmpty(a2.f14269a)) {
                    if ("xiaomi.ble.v1".equalsIgnoreCase(a2.f14269a)) {
                        a2.f14269a = "";
                        break;
                    } else if (!TextUtils.isEmpty(a2.f14269a)) {
                        if (BluetoothLog.a()) {
                            BluetoothLog.c(String.format("recognize %s by %s: %s", new Object[]{this.f14272a.g(), iBluetoothRecognizer.getClass().getSimpleName(), a2.f14269a}));
                        }
                        BluetoothCache.g(this.f14272a.g(), a2.f14269a);
                        boolean b3 = PluginManager.a().b(a2.f14269a);
                        if (!b3) {
                            BluetoothLog.b(String.format("Model %s is not plugin", new Object[]{a2.f14269a}));
                        }
                        if (!b3 && !"xiaomi.mikey.v1".equalsIgnoreCase(a2.f14269a)) {
                            a2.f14269a = "";
                        }
                    }
                }
                i++;
                bluetoothRecognizeResult2 = a2;
            }
            bluetoothRecognizeResult2 = a2;
            a(bluetoothRecognizeResult2);
        }

        private void a(final BluetoothRecognizeResult bluetoothRecognizeResult) {
            BluetoothRecognizer.this.b.post(new Runnable() {
                public void run() {
                    if (BluetoothRecognizeTask.this.b != null) {
                        BluetoothRecognizeTask.this.b.a(bluetoothRecognizeResult);
                    }
                }
            });
        }
    }
}
