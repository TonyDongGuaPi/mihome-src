package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer;

import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.BleAdvertiseItem;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.BleAdvertisement;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotPacketParser;

public class BluetoothBeaconRecognizer implements IBluetoothRecognizer {
    private BluetoothBeaconRecognizer() {
    }

    public static BluetoothBeaconRecognizer a() {
        return new BluetoothBeaconRecognizer();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000b, code lost:
        r1 = new com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.BleAdvertisement(r6.i());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.BluetoothRecognizeResult a(com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult r6) {
        /*
            r5 = this;
            com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.BluetoothRecognizeResult r0 = new com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.BluetoothRecognizeResult
            r0.<init>()
            byte[] r1 = r6.i()
            if (r1 == 0) goto L_0x00b0
            com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.BleAdvertisement r1 = new com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.BleAdvertisement
            byte[] r2 = r6.i()
            r1.<init>(r2)
            com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket r2 = r5.a((com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.BleAdvertisement) r1)
            if (r2 == 0) goto L_0x00b0
            int r3 = r2.b
            if (r3 <= 0) goto L_0x0027
            java.lang.String r3 = r6.g()
            int r4 = r2.b
            com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache.b((java.lang.String) r3, (int) r4)
        L_0x0027:
            java.lang.String r3 = r2.d
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0038
            java.lang.String r3 = r6.g()
            java.lang.String r4 = r2.d
            com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache.h(r3, r4)
        L_0x0038:
            boolean r3 = r2.a()
            if (r3 == 0) goto L_0x0079
            java.lang.String r3 = r2.f
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0079
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "BluetoothBeaconRecognizer addCombo, comboKey = "
            r3.append(r4)
            java.lang.String r4 = r2.f
            r3.append(r4)
            java.lang.String r4 = ", address = "
            r3.append(r4)
            java.lang.String r4 = r6.g()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog.c((java.lang.String) r3)
            java.lang.String r3 = r2.f
            java.lang.String r4 = r6.g()
            com.xiaomi.smarthome.core.server.internal.bluetooth.ComboCollector.a(r3, r4)
            java.lang.String r3 = r6.g()
            r4 = 1
            com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache.e((java.lang.String) r3, (int) r4)
        L_0x0079:
            com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket$FrameControl r3 = r2.f14276a
            if (r3 != 0) goto L_0x0083
            int r3 = r2.b
            r4 = 91
            if (r3 != r4) goto L_0x008f
        L_0x0083:
            com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r3 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.a()
            int r4 = r2.b
            java.lang.String r3 = r3.a((int) r4)
            r0.f14269a = r3
        L_0x008f:
            java.lang.String r3 = r0.f14269a
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x00b0
            r0.c = r2
            int r2 = r6.h()
            r0.b = r2
            java.lang.String r1 = r1.a()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x00b0
            java.lang.String r6 = r6.g()
            com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache.c((java.lang.String) r6, (java.lang.String) r1)
        L_0x00b0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.BluetoothBeaconRecognizer.a(com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult):com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.BluetoothRecognizeResult");
    }

    private MiotBleAdvPacket a(BleAdvertisement bleAdvertisement) {
        MiotBleAdvPacket miotBleAdvPacket = null;
        try {
            for (BleAdvertiseItem a2 : bleAdvertisement.b()) {
                MiotBleAdvPacket a3 = MiotPacketParser.a(a2);
                if (a3 != null) {
                    return a3;
                }
                miotBleAdvPacket = a3;
            }
            return miotBleAdvPacket;
        } catch (Exception unused) {
            return null;
        }
    }
}
