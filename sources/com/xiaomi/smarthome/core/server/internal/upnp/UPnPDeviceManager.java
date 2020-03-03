package com.xiaomi.smarthome.core.server.internal.upnp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.xiaomi.smarthome.core.client.IClientApi;
import com.xiaomi.smarthome.core.entity.device.UPnPDevice;
import com.xiaomi.smarthome.core.entity.message.CoreMessageType;
import com.xiaomi.smarthome.core.entity.upnp.UPnPConstants;
import com.xiaomi.smarthome.core.entity.upnp.UPnPDeviceEvent;
import com.xiaomi.smarthome.core.server.CoreAsyncTask;
import com.xiaomi.smarthome.core.server.CoreManager;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.device.DiscoverManager;
import com.xiaomi.smarthome.core.server.internal.device.api.AsyncResponseCallback;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.core.server.internal.network.NetworkManager;
import com.xiaomi.youpin.hawkeye.entity.StatType;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.device.DeviceChangeListener;
import org.cybergarage.upnp.event.EventListener;

public class UPnPDeviceManager {
    static final String e = "WiFiTAG";
    /* access modifiers changed from: private */
    public static final Byte[] f = new Byte[0];
    private static final Byte[] g = new Byte[0];
    private static volatile UPnPDeviceManager p = null;

    /* renamed from: a  reason: collision with root package name */
    long f14723a = 0;
    long b = 10000;
    int c = 0;
    long d = 3000;
    private volatile boolean h = false;
    /* access modifiers changed from: private */
    public volatile boolean i = false;
    /* access modifiers changed from: private */
    public ControlPoint j = new ControlPoint();
    private List<Device> k = new ArrayList();
    private List<com.xiaomi.smarthome.core.entity.device.Device> l = new ArrayList();
    private List<com.xiaomi.smarthome.core.entity.device.Device> m = new ArrayList();
    private Handler n = new Handler(Looper.getMainLooper());
    private List<AsyncResponseCallback<List<com.xiaomi.smarthome.core.entity.device.Device>>> o = new ArrayList();
    private List<WeakReference<DiscoverManager.DeviceDiscoverListener>> q = new ArrayList();
    private EventListener r = new EventListener() {
        public void eventNotifyReceived(String str, long j, String str2, String str3) {
            Device a2 = UPnPDeviceManager.this.c(str);
            if (a2 != null) {
                UPnPDeviceEvent uPnPDeviceEvent = new UPnPDeviceEvent();
                uPnPDeviceEvent.f13999a = a2.getUDN();
                uPnPDeviceEvent.b = j;
                uPnPDeviceEvent.c = str2;
                uPnPDeviceEvent.d = str3;
                CoreManager a3 = CoreManager.a();
                Bundle bundle = new Bundle();
                bundle.putParcelable(UPnPConstants.c, uPnPDeviceEvent);
                try {
                    a3.a("com.xiaomi.smarthome").onCoreMessage(CoreMessageType.UPNP_DEVICE_EVENT, bundle);
                } catch (RemoteException unused) {
                }
            }
        }
    };
    private DeviceChangeListener s = new DeviceChangeListener() {
        public void deviceAdded(Device device) {
            if (UPnPDeviceManager.b(device.getUDN())) {
                UPnPDeviceManager.this.a(new UPnPDevice(device), device);
            }
        }

        public void deviceRemoved(Device device) {
            if (UPnPDeviceManager.this.j != null) {
                UPnPDeviceManager.this.j.unsubscribe(device);
            }
            if (UPnPDeviceManager.b(device.getUDN())) {
                UPnPDeviceManager.this.a((com.xiaomi.smarthome.core.entity.device.Device) new UPnPDevice(device));
            }
        }
    };
    private WiFiBroadCastReceiver t;

    public static UPnPDeviceManager a() {
        if (p == null) {
            synchronized (f) {
                if (p == null) {
                    p = new UPnPDeviceManager();
                    p.d();
                }
            }
        }
        return p;
    }

    public List<com.xiaomi.smarthome.core.entity.device.Device> b() {
        ArrayList arrayList = new ArrayList();
        if (!GlobalDynamicSettingManager.a().e()) {
            synchronized (f) {
                arrayList.addAll(this.l);
            }
        }
        return arrayList;
    }

    public String a(String str, String str2) {
        for (Device next : this.k) {
            if (next.getUDN().equalsIgnoreCase(str)) {
                return next.getRootNode().getNodeValue(str2);
            }
        }
        return "";
    }

    public synchronized void a(DiscoverManager.DeviceDiscoverListener deviceDiscoverListener) {
        if (deviceDiscoverListener != null) {
            this.q.add(new WeakReference(deviceDiscoverListener));
        }
    }

    public synchronized void b(DiscoverManager.DeviceDiscoverListener deviceDiscoverListener) {
        if (deviceDiscoverListener != null) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.q.size()) {
                    break;
                } else if (this.q.get(i2).get() == deviceDiscoverListener) {
                    this.q.remove(i2);
                    break;
                } else {
                    i2++;
                }
            }
        }
    }

    public Device a(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        for (Device next : this.k) {
            if (str.equalsIgnoreCase(next.getUDN())) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public Device c(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        for (Device next : this.k) {
            if (str.equalsIgnoreCase(next.getUUID())) {
                return next;
            }
        }
        return null;
    }

    public void c() {
        if (GlobalDynamicSettingManager.a().e()) {
            i();
        } else {
            new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    UPnPDeviceManager.this.h();
                }
            }.execute();
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        if (!NetworkManager.a().f() || !NetworkManager.a().e()) {
            synchronized (f) {
                if (this.c == 0) {
                    this.f14723a = System.currentTimeMillis();
                }
                if (this.c < 3 && System.currentTimeMillis() - this.f14723a < this.b) {
                    this.n.postDelayed(new Runnable() {
                        public void run() {
                            UPnPDeviceManager.this.c();
                        }
                    }, 3000);
                    this.c++;
                }
                if (System.currentTimeMillis() - this.f14723a > this.b * 3) {
                    this.c = 0;
                    this.n.post(new Runnable() {
                        public void run() {
                            UPnPDeviceManager.this.c();
                        }
                    });
                }
            }
            return;
        }
        if (this.h) {
            this.j.search();
        } else {
            synchronized (g) {
                try {
                    this.h = this.j.start();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        synchronized (f) {
            this.i = true;
        }
        this.n.postDelayed(new Runnable() {
            public void run() {
                synchronized (UPnPDeviceManager.f) {
                    boolean unused = UPnPDeviceManager.this.i = false;
                }
                UPnPDeviceManager.this.i();
            }
        }, this.d);
    }

    /* access modifiers changed from: private */
    public void i() {
        synchronized (f) {
            this.l.clear();
            this.l.addAll(this.m);
            for (int i2 = 0; i2 < this.o.size(); i2++) {
                AsyncResponseCallback asyncResponseCallback = this.o.get(i2);
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(this.l);
                }
            }
            this.o.clear();
        }
    }

    public void a(UPnPDevice uPnPDevice, Device device) {
        if (uPnPDevice != null && uPnPDevice.a() != null && b(uPnPDevice.a())) {
            synchronized (f) {
                Iterator<com.xiaomi.smarthome.core.entity.device.Device> it = this.m.iterator();
                while (it.hasNext()) {
                    if (uPnPDevice.a().equalsIgnoreCase(((UPnPDevice) it.next()).a())) {
                        return;
                    }
                }
                this.m.add(uPnPDevice);
                this.k.add(device);
                c((com.xiaomi.smarthome.core.entity.device.Device) uPnPDevice);
            }
        }
    }

    private void b(com.xiaomi.smarthome.core.entity.device.Device device) {
        if (device != null && device.k() != null) {
            for (WeakReference next : this.q) {
                if (!(next == null || next.get() == null)) {
                    ((DiscoverManager.DeviceDiscoverListener) next.get()).b(device);
                }
            }
            CoreManager a2 = CoreManager.a();
            Bundle bundle = new Bundle();
            bundle.putString(UPnPConstants.b, device.k());
            try {
                IClientApi a3 = a2.a("com.xiaomi.smarthome");
                if (a3 != null) {
                    a3.onCoreMessage(CoreMessageType.UPNP_DEVICE_CHANGED, bundle);
                }
            } catch (RemoteException unused) {
            }
        }
    }

    private void c(com.xiaomi.smarthome.core.entity.device.Device device) {
        for (WeakReference next : this.q) {
            if (!(next == null || next.get() == null)) {
                ((DiscoverManager.DeviceDiscoverListener) next.get()).a(device);
            }
        }
        CoreManager a2 = CoreManager.a();
        Bundle bundle = new Bundle();
        bundle.putString(UPnPConstants.f13998a, device.k());
        try {
            IClientApi a3 = a2.a("com.xiaomi.smarthome");
            if (a3 != null) {
                a3.onCoreMessage(CoreMessageType.UPNP_DEVICE_CHANGED, bundle);
            }
        } catch (RemoteException unused) {
        }
    }

    public void a(com.xiaomi.smarthome.core.entity.device.Device device) {
        if (device != null && device.k() != null) {
            b(device);
            String k2 = device.k();
            synchronized (f) {
                Iterator<com.xiaomi.smarthome.core.entity.device.Device> it = this.l.iterator();
                while (it.hasNext()) {
                    if (k2.equalsIgnoreCase(((UPnPDevice) it.next()).a())) {
                        it.remove();
                    }
                }
                for (Device udn : this.k) {
                    if (k2.equalsIgnoreCase(udn.getUDN())) {
                        it.remove();
                    }
                }
            }
        }
    }

    class WiFiBroadCastReceiver extends BroadcastReceiver {
        private String b;
        private String c;
        private WifiInfo d;

        public WiFiBroadCastReceiver(Context context) {
            this.d = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            if (this.d != null) {
                this.b = this.d.getBSSID();
            }
        }

        public void onReceive(Context context, Intent intent) {
            NetworkInfo networkInfo;
            if (intent.getAction().equals("android.net.wifi.STATE_CHANGE") && (networkInfo = (NetworkInfo) intent.getParcelableExtra(StatType.NETWORKINFO)) != null) {
                NetworkInfo.State state = networkInfo.getState();
                this.d = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
                if (this.d != null && this.d.getSupplicantState().equals(SupplicantState.COMPLETED)) {
                    this.c = this.d.getBSSID();
                    if (state == NetworkInfo.State.CONNECTED) {
                        if (this.c != null) {
                            if (this.b == null) {
                                UPnPDeviceManager.this.c();
                            }
                            this.b = this.c;
                        }
                    } else if (state == NetworkInfo.State.DISCONNECTED && this.c == null) {
                        if (this.b != null) {
                            UPnPDeviceManager.this.e();
                        }
                        this.b = this.c;
                    }
                }
            }
        }
    }

    public static boolean b(String str) {
        if (str == null || str.isEmpty() || !str.startsWith("uuid:MiShare")) {
            return false;
        }
        return true;
    }

    public void d() {
        this.j.addDeviceChangeListener(this.s);
        this.j.addEventListener(this.r);
        this.t = new WiFiBroadCastReceiver(CoreService.getAppContext());
        CoreService.getAppContext().registerReceiver(this.t, new IntentFilter("android.net.wifi.STATE_CHANGE"));
    }

    public void e() {
        synchronized (g) {
            this.h = false;
        }
        synchronized (f) {
            for (com.xiaomi.smarthome.core.entity.device.Device b2 : this.l) {
                b(b2);
            }
            this.k.clear();
            this.l.clear();
            this.m.clear();
        }
    }

    public void f() {
        this.j.removeDeviceChangeListener(this.s);
        this.j.removeEventListener(this.r);
        this.j.stop();
        CoreService.getAppContext().unregisterReceiver(this.t);
        p = null;
    }

    public void a(AsyncResponseCallback<List<com.xiaomi.smarthome.core.entity.device.Device>> asyncResponseCallback) {
        if (asyncResponseCallback != null && !this.o.contains(asyncResponseCallback)) {
            this.o.add(asyncResponseCallback);
        }
        if (!this.i) {
            c();
        }
    }
}
