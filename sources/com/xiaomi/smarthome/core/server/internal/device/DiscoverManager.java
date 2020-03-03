package com.xiaomi.smarthome.core.server.internal.device;

import android.net.wifi.WifiManager;
import android.os.Handler;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.device.MiTVDevice;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.device.api.AsyncResponseCallback;
import com.xiaomi.smarthome.library.common.ThreadPool;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

public class DiscoverManager {

    /* renamed from: a  reason: collision with root package name */
    public static String f14526a = "_miio._udp.local.";
    public static String b = "_rc._tcp.local.";
    private static DiscoverManager c;
    private static Object d = new Object();
    /* access modifiers changed from: private */
    public JmDNS e;
    private MessageHandlerThread f = new MessageHandlerThread("DiscoverManager");
    private Handler g;
    /* access modifiers changed from: private */
    public List<WeakReference<DeviceDiscoverListener>> h = new ArrayList();
    /* access modifiers changed from: private */
    public ConcurrentMap<String, Device> i = new ConcurrentHashMap();

    public interface DeviceDiscoverListener {
        void a(Device device);

        void b(Device device);
    }

    private DiscoverManager() {
    }

    public static DiscoverManager a() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new DiscoverManager();
                }
            }
        }
        return c;
    }

    public synchronized void a(DeviceDiscoverListener deviceDiscoverListener) {
        if (deviceDiscoverListener != null) {
            this.h.add(new WeakReference(deviceDiscoverListener));
        }
    }

    public synchronized void b(DeviceDiscoverListener deviceDiscoverListener) {
        if (deviceDiscoverListener != null) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.h.size()) {
                    break;
                } else if (this.h.get(i2).get() == deviceDiscoverListener) {
                    this.h.remove(i2);
                    break;
                } else {
                    i2++;
                }
            }
        }
    }

    public void b() {
        if (!this.f.isAlive()) {
            this.f.start();
            this.g = new Handler(this.f.getLooper());
        }
    }

    public void c() {
        this.g.post(new Runnable() {
            public void run() {
                try {
                    if (DiscoverManager.this.e != null) {
                        DiscoverManager.this.e.close();
                    }
                    int ipAddress = ((WifiManager) CoreService.getAppContext().getSystemService("wifi")).getConnectionInfo().getIpAddress();
                    JmDNS unused = DiscoverManager.this.e = JmDNS.a(InetAddress.getByAddress(new byte[]{(byte) (ipAddress & 255), (byte) ((ipAddress >> 8) & 255), (byte) ((ipAddress >> 16) & 255), (byte) ((ipAddress >> 24) & 255)}));
                    DiscoverManager.this.e.a(DiscoverManager.f14526a, (ServiceListener) new MiioListener());
                    DiscoverManager.this.e.a(DiscoverManager.b, (ServiceListener) new MitvListener());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void d() {
        this.g.post(new Runnable() {
            public void run() {
                DiscoverManager.this.e();
                try {
                    if (DiscoverManager.this.e != null) {
                        DiscoverManager.this.e.close();
                        JmDNS unused = DiscoverManager.this.e = null;
                    }
                } catch (IOException unused2) {
                }
            }
        });
    }

    public void e() {
        MitvLocalDeviceManager.a().b();
        this.i.clear();
    }

    public List<Device> f() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry value : this.i.entrySet()) {
            arrayList.add(value.getValue());
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void a(final Device device) {
        if (device != null && !TextUtils.isEmpty(device.k()) && device.l().contains("robot")) {
            LocalDeviceApiInternal.a().b(device.t(), new AsyncResponseCallback<String>() {
                public void a(String str) {
                    Device device = (Device) DiscoverManager.this.i.get(device.k());
                    if (device == null) {
                        device.e(str);
                        DiscoverManager.this.i.put(device.k(), device);
                        for (WeakReference weakReference : DiscoverManager.this.h) {
                            if (weakReference.get() != null) {
                                ((DeviceDiscoverListener) weakReference.get()).a(device);
                            }
                        }
                        return;
                    }
                    device.e(str);
                }

                public void a(int i) {
                    if (((Device) DiscoverManager.this.i.remove(device.k())) != null) {
                        for (WeakReference weakReference : DiscoverManager.this.h) {
                            if (weakReference.get() != null) {
                                ((DeviceDiscoverListener) weakReference.get()).b(device);
                            }
                        }
                    }
                }

                public void a(int i, Object obj) {
                    if (((Device) DiscoverManager.this.i.remove(device.k())) != null) {
                        for (WeakReference weakReference : DiscoverManager.this.h) {
                            if (weakReference.get() != null) {
                                ((DeviceDiscoverListener) weakReference.get()).b(device);
                            }
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(ServiceInfo serviceInfo) {
        String d2 = DeviceFactory.d(serviceInfo.d());
        if (this.i.containsKey(d2)) {
            Device device = (Device) this.i.get(d2);
            this.i.remove(d2);
            for (WeakReference next : this.h) {
                if (next.get() != null) {
                    ((DeviceDiscoverListener) next.get()).b(device);
                }
            }
        }
    }

    private class MiioListener implements ServiceListener {
        private MiioListener() {
        }

        public void a(ServiceEvent serviceEvent) {
            DiscoverManager.this.a(DeviceFactory.a(serviceEvent.getInfo().d(), serviceEvent._source.getHostAddress()));
        }

        public void b(ServiceEvent serviceEvent) {
            DiscoverManager.this.a(serviceEvent.getInfo());
        }

        public void c(ServiceEvent serviceEvent) {
            DiscoverManager.this.a(DeviceFactory.a(serviceEvent.getInfo().d(), serviceEvent.getInfo().j().getHostAddress()));
        }
    }

    private class MitvListener implements ServiceListener {
        private MitvListener() {
        }

        public void a(final ServiceEvent serviceEvent) {
            ThreadPool.a(new Runnable() {
                public void run() {
                    ServiceInfo a2 = DiscoverManager.this.e.a(DiscoverManager.b, serviceEvent.getInfo().d());
                    if (a2 != null) {
                        MiTVDevice a3 = DeviceFactory.a(a2);
                        MitvLocalDeviceManager.a().a((Device) a3);
                        DiscoverManager.this.a((Device) a3);
                    }
                }
            });
        }

        public void b(ServiceEvent serviceEvent) {
            ServiceInfo info;
            if (serviceEvent != null && (info = serviceEvent.getInfo()) != null) {
                MitvLocalDeviceManager.a().b(DeviceFactory.a(info));
                DiscoverManager.this.a(info);
            }
        }

        public void c(ServiceEvent serviceEvent) {
            ServiceInfo info;
            if (serviceEvent != null && (info = serviceEvent.getInfo()) != null) {
                MiTVDevice a2 = DeviceFactory.a(info);
                MitvLocalDeviceManager.a().a((Device) a2);
                DiscoverManager.this.a((Device) a2);
            }
        }
    }
}
