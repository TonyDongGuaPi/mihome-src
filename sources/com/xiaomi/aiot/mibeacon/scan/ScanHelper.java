package com.xiaomi.aiot.mibeacon.scan;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import com.xiaomi.aiot.mibeacon.BeaconManager;
import com.xiaomi.aiot.mibeacon.BeaconParserV4;
import com.xiaomi.aiot.mibeacon.IBeaconParser;
import com.xiaomi.aiot.mibeacon.MiBeacon;
import com.xiaomi.aiot.mibeacon.RangeNotifier;
import com.xiaomi.aiot.mibeacon.logging.LogManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat;
import no.nordicsemi.android.support.v18.scanner.ScanCallback;
import no.nordicsemi.android.support.v18.scanner.ScanFilter;
import no.nordicsemi.android.support.v18.scanner.ScanResult;
import no.nordicsemi.android.support.v18.scanner.ScanSettings;

public class ScanHelper {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f9983a = "ScanHelper";
    private static final String b = "78:11:DC:92:42:1A";
    private static final String c = "78:11:DC:92:42:3E";
    private static final String d = "7C:49:EB:27:DA:C9";
    private static final String e = "7C:49:EB:28:22:6E";
    private static final String f = "7C:49:EB:28:22:8B";
    private static final String g = "78:11:DC:92:42:42";
    private static final String h = "7C:49:EB:F1:8C:D3";
    private static final String i = "7C:49:EB:F1:8C:D1";
    private static final String j = "7C:49:EB:F1:8D:66";
    private static final String k = "7C:49:EB:F2:59:5D";
    private static final String l = "7C:49:EB:F2:59:60";
    private static final String m = "7C:49:EB:F2:58:6F";
    private static final int n = 17;
    /* access modifiers changed from: private */
    public BeaconManager o;
    /* access modifiers changed from: private */
    public IBeaconParser p;
    private BluetoothLeScannerCompat q;
    private ScanCallback r;
    private Context s;
    /* access modifiers changed from: private */
    public Handler t;
    /* access modifiers changed from: private */
    public Map<String, MiBeacon> u;
    /* access modifiers changed from: private */
    public Collection<MiBeacon> v = new ArrayList();
    private final ScanSettings w = new ScanSettings.Builder().a(2).c(false).a();

    public ScanHelper(Context context) {
        this.s = context;
        this.o = BeaconManager.a(context);
        this.p = new BeaconParserV4();
        this.q = BluetoothLeScannerCompat.a();
        this.u = new HashMap();
        this.t = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                if (message.what == 17) {
                    ScanHelper.this.v.clear();
                    ScanHelper.this.v.add((MiBeacon) message.obj);
                    Set<RangeNotifier> a2 = ScanHelper.this.o.a();
                    if (a2 != null) {
                        for (RangeNotifier a3 : a2) {
                            a3.a(ScanHelper.this.v);
                        }
                    }
                }
            }
        };
        this.r = new ScanCallback() {
            public void a(int i, @NonNull ScanResult scanResult) {
                MiBeacon a2 = ScanHelper.this.p.a(scanResult.b().g(), scanResult.c(), scanResult.a());
                if (a2 != null) {
                    MiBeacon miBeacon = (MiBeacon) ScanHelper.this.u.get(a2.f());
                    if (miBeacon != null) {
                        miBeacon.a(a2);
                        a2 = miBeacon;
                    } else {
                        ScanHelper.this.u.put(a2.f(), a2);
                    }
                    Message obtainMessage = ScanHelper.this.t.obtainMessage(17);
                    obtainMessage.obj = a2;
                    ScanHelper.this.t.sendMessage(obtainMessage);
                }
            }

            public void a(int i) {
                String c = ScanHelper.f9983a;
                LogManager.e(c, "onScanFail %d" + i, new Object[0]);
            }
        };
    }

    public boolean a() {
        try {
            this.q.a((List<ScanFilter>) null, this.w, this.r);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public void b() {
        try {
            this.q.b(this.r);
            if (this.u != null) {
                this.u.clear();
            }
            this.v.clear();
        } catch (Exception unused) {
        }
    }
}
