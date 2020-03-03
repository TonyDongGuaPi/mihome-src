package cn.com.xm.bt.e;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import cn.com.xm.bt.b.b;
import cn.com.xm.bt.e.c;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.device.BleDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@SuppressLint({"NewApi"})
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static a f569a;
    /* access modifiers changed from: private */
    public static volatile int q;
    /* access modifiers changed from: private */
    public static volatile int r;
    private static volatile int s;
    private static volatile int t;
    private final boolean b = false;
    private final BluetoothAdapter c = BluetoothAdapter.getDefaultAdapter();
    private BluetoothAdapter.LeScanCallback d = null;
    private Handler e = null;
    /* access modifiers changed from: private */
    public Context f = null;
    private final int g = 0;
    private final int h = 3000;
    private final int i = 0;
    private final int j = 1;
    private final int k = 2;
    private final int l = 3;
    private final int m = 4;
    private final int n = 5;
    /* access modifiers changed from: private */
    public volatile boolean o = false;
    /* access modifiers changed from: private */
    public final List<b> p = new ArrayList();

    static /* synthetic */ int b() {
        int i2 = q + 1;
        q = i2;
        return i2;
    }

    static /* synthetic */ int e() {
        int i2 = r + 1;
        r = i2;
        return i2;
    }

    public static void a(Context context) {
        if (f569a == null) {
            f569a = new a(context);
        }
    }

    public static a a() {
        return f569a;
    }

    private a(Context context) {
        this.f = context;
        HandlerThread handlerThread = new HandlerThread("BleScanCenter");
        handlerThread.start();
        this.e = new Handler(handlerThread.getLooper()) {
            public void handleMessage(Message message) {
                List a2;
                boolean z = true;
                switch (message.what) {
                    case 0:
                        cn.com.xm.bt.a.a.a("BleScanCenter", "COUNT-SCAN******MSG_START_SCAN:<" + a.b() + "," + a.r + ">");
                        if (a.this.o) {
                            cn.com.xm.bt.a.a.b("BleScanCenter", "need stop,return!!!");
                            return;
                        }
                        a.this.g();
                        a.this.f();
                        removeMessages(3);
                        sendEmptyMessageDelayed(3, 3000);
                        return;
                    case 1:
                        cn.com.xm.bt.a.a.a("BleScanCenter", "COUNT-SCAN******MSG_STOP_SCAN:<" + a.q + "," + a.e() + ">");
                        a.this.g();
                        return;
                    case 2:
                        cn.com.xm.bt.d.a aVar = (cn.com.xm.bt.d.a) message.obj;
                        if (aVar != null && !a.this.o) {
                            for (b bVar : a.this.p) {
                                List<UUID> b = bVar.b();
                                if (b == null || b.size() <= 0) {
                                    if (bVar.c() == null) {
                                        bVar.d().a(aVar, bVar);
                                    } else if (aVar.f564a.getAddress().contains(bVar.c())) {
                                        bVar.d().a(aVar, bVar);
                                    }
                                } else if (a.this.a(b, aVar)) {
                                    bVar.d().a(aVar, bVar);
                                }
                            }
                            return;
                        }
                        return;
                    case 3:
                        cn.com.xm.bt.a.a.a("BleScanCenter", "COUNT-SCAN******MSG_PAUSE_SCAN:<" + a.q + "," + a.e() + ">");
                        a.this.g();
                        Message message2 = new Message();
                        message2.what = 0;
                        message2.obj = message.obj;
                        sendMessageDelayed(message2, 0);
                        return;
                    case 4:
                        b bVar2 = (b) message.obj;
                        if (a.this.p.remove(bVar2)) {
                            bVar2.d().b(bVar2);
                        }
                        if (a.this.p.size() < 1) {
                            boolean unused = a.this.o = true;
                            removeMessages(3);
                            removeMessages(0);
                            sendEmptyMessage(1);
                        }
                        cn.com.xm.bt.a.a.a("BleScanCenter", "COUNT-SCAN******MSG_SCAN_TIMEOUT:<" + bVar2 + ">[" + a.this.p.size() + Operators.ARRAY_END_STR);
                        return;
                    case 5:
                        b bVar3 = (b) message.obj;
                        if (bVar3 == null || a.this.p.contains(bVar3)) {
                            cn.com.xm.bt.a.a.a("BleScanCenter", "null or exist BleScanOption,return now!!!");
                            return;
                        }
                        boolean unused2 = a.this.o = false;
                        a.this.p.add(bVar3);
                        cn.com.xm.bt.a.a.a("BleScanCenter", "COUNT-SCAN******MSG_ADD_SCAN_OPTION:<" + bVar3 + ">[" + a.this.p.size() + Operators.ARRAY_END_STR);
                        d d = bVar3.d();
                        if (d != null) {
                            d.a(bVar3);
                        }
                        if (bVar3.e() && (a2 = a.this.b(a.this.f)) != null && a2.size() > 0) {
                            a aVar2 = a.this;
                            Context e = a.this.f;
                            if (bVar3.b() == null) {
                                z = false;
                            }
                            aVar2.a(e, (List<BluetoothDevice>) a2, z);
                        }
                        removeMessages(0);
                        sendEmptyMessage(0);
                        if (bVar3.a() > 0) {
                            Message message3 = new Message();
                            message3.what = 4;
                            message3.obj = bVar3;
                            sendMessageDelayed(message3, (long) bVar3.a());
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public boolean a(List<UUID> list, cn.com.xm.bt.d.a aVar) {
        for (UUID a2 : list) {
            if (aVar.a(a2)) {
                return true;
            }
        }
        return false;
    }

    public void a(b bVar) {
        cn.com.xm.bt.a.a.a("BleScanCenter", "startScan<" + bVar + ">");
        if (bVar != null) {
            Message message = new Message();
            message.what = 5;
            message.obj = bVar;
            this.e.sendMessage(message);
        }
    }

    public void b(b bVar) {
        cn.com.xm.bt.a.a.a("BleScanCenter", "stopScan<" + bVar + ">");
        if (bVar != null) {
            Message message = new Message();
            message.what = 4;
            message.obj = bVar;
            this.e.sendMessage(message);
        }
    }

    /* access modifiers changed from: private */
    public List<BluetoothDevice> b(Context context) {
        HashMap hashMap = new HashMap();
        for (BluetoothDevice next : ((BluetoothManager) context.getSystemService(BleDevice.f14751a)).getConnectedDevices(7)) {
            hashMap.put(next.getAddress(), next);
        }
        return new ArrayList(hashMap.values());
    }

    /* access modifiers changed from: private */
    public void a(Context context, List<BluetoothDevice> list, boolean z) {
        for (BluetoothDevice next : list) {
            cn.com.xm.bt.a.a.a("BleScanCenter", "connected device:" + next.getAddress() + " " + next.getName());
            if (!z) {
                a(next, (ArrayList<String>) null, b.VDEVICE);
            } else {
                c.a(context, next, new c.a(next) {
                    private final /* synthetic */ BluetoothDevice f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onDeviceInfoObtained(ArrayList arrayList, cn.com.xm.bt.d.b bVar) {
                        a.this.a(this.f$1, arrayList, bVar);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(BluetoothDevice bluetoothDevice, ArrayList arrayList, cn.com.xm.bt.d.b bVar) {
        cn.com.xm.bt.a.a.b("BleScanCenter", "onDeviceInfoObtained:" + bVar);
        a(bluetoothDevice, (ArrayList<String>) arrayList, bVar != null ? bVar.a() : b.VDEVICE);
    }

    /* access modifiers changed from: private */
    public void f() {
        StringBuilder sb = new StringBuilder();
        sb.append("startForNormal:");
        int i2 = s + 1;
        s = i2;
        sb.append(i2);
        cn.com.xm.bt.a.a.a("BleScanCenter", sb.toString());
        if (this.d == null) {
            this.d = new BluetoothAdapter.LeScanCallback() {
                public final void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                    a.this.a(bluetoothDevice, i, bArr);
                }
            };
        }
        try {
            this.c.startLeScan(this.d);
        } catch (Exception e2) {
            cn.com.xm.bt.a.a.a("BleScanCenter", "startLeScan exception:" + e2.getMessage());
            g();
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.d != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("stopForNormal:");
            int i2 = t + 1;
            t = i2;
            sb.append(i2);
            cn.com.xm.bt.a.a.a("BleScanCenter", sb.toString());
            try {
                this.c.stopLeScan(this.d);
            } catch (Exception e2) {
                cn.com.xm.bt.a.a.a("BleScanCenter", "stopLeScan exception:" + e2.getMessage());
            } catch (Throwable th) {
                this.d = null;
                throw th;
            }
            this.d = null;
        }
    }

    private void a(BluetoothDevice bluetoothDevice, ArrayList<String> arrayList, b bVar) {
        cn.com.xm.bt.d.a aVar = new cn.com.xm.bt.d.a();
        if (arrayList != null) {
            aVar.f.addAll(arrayList);
        }
        aVar.f564a = bluetoothDevice;
        aVar.b = 0;
        aVar.o = bVar;
        a(aVar);
    }

    /* access modifiers changed from: private */
    public void a(BluetoothDevice bluetoothDevice, int i2, byte[] bArr) {
        cn.com.xm.bt.d.a c2 = cn.com.xm.bt.c.c.c(bArr);
        c2.f564a = bluetoothDevice;
        c2.b = i2;
        a(c2);
    }

    private void a(cn.com.xm.bt.d.a aVar) {
        Message message = new Message();
        message.what = 2;
        message.obj = aVar;
        this.e.sendMessage(message);
    }
}
