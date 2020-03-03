package com.xiaomi.smarthome.devicesubscribe;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

public class DevicePropSubscriber {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15537a = "event.dev_online";
    public static final String b = "event.dev_offline";
    public static Stack<DeviceSubscriberInterface> c = new Stack<>();
    /* access modifiers changed from: private */
    public static final String d = "DevicePropSubscriber";
    private static final int j = 120000;
    private static final int k = 1;
    private static final int l = 2;
    private static final int m = 3;
    private AtomicBoolean e = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public boolean f = false;
    /* access modifiers changed from: private */
    public final List<List<Device>> g = new ArrayList();
    /* access modifiers changed from: private */
    public final Map<String, List<Device>> h = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public DeviceSubscriberInterface i;
    /* access modifiers changed from: private */
    public final Handler n;
    private SmartHomeDeviceManager.IClientDeviceListener o = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void b(int i) {
        }

        public void a(int i) {
            DevicePropSubscriber.this.n.sendMessage(DevicePropSubscriber.this.n.obtainMessage(2, ""));
        }
    };

    public interface DeviceSubscriberInterface {
        public static final String c = "prop_change";
        public static final String d = "did";
        public static final String e = "did";

        List<String> a(String str);

        void a(String str, JSONArray jSONArray);

        JSONArray b(String str);
    }

    public DevicePropSubscriber() {
        HandlerThread handlerThread = new HandlerThread(d);
        handlerThread.start();
        this.n = new Handler(handlerThread.getLooper()) {
            public void handleMessage(Message message) {
                if (!DevicePropSubscriber.this.f) {
                    switch (message.what) {
                        case 1:
                            String str = (String) message.obj;
                            DevicePropSubscriber.this.a((List<Device>) (List) DevicePropSubscriber.this.h.get(str), str);
                            return;
                        case 2:
                            HashSet hashSet = new HashSet(SmartHomeDeviceManager.a().d());
                            List a2 = DevicePropSubscriber.this.a((Set<Device>) hashSet);
                            String b = DevicePropSubscriber.d;
                            LogUtil.c(b, "onRefreshClientDeviceSuccess all device size=" + hashSet.size());
                            DevicePropSubscriber.this.a((List<Device>) a2);
                            DevicePropSubscriber.this.b((Set<Device>) hashSet);
                            hashSet.clear();
                            return;
                        case 3:
                            DevicePropSubscriber.this.a((List<Device>) (List) message.obj);
                            return;
                        default:
                            return;
                    }
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public void a(List<Device> list) {
        if (list.size() > 300) {
            int i2 = 0;
            while (i2 < list.size()) {
                int i3 = i2 + 300;
                a((List<Device>) new ArrayList(list.subList(i2, Math.min(i3, list.size()))), (String) null);
                i2 = i3;
            }
            return;
        }
        a(list, (String) null);
    }

    /* access modifiers changed from: private */
    public List<Device> a(Set<Device> set) {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, List<Device>> value : this.h.entrySet()) {
            for (Device device : (List) value.getValue()) {
                hashMap.put(device.did, device);
            }
        }
        for (List<Device> it : this.g) {
            for (Device device2 : it) {
                hashMap.put(device2.did, device2);
            }
        }
        for (Device next : set) {
            if (next != null && !TextUtils.isEmpty(next.did) && hashMap.get(next.did) == null) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public void a(List<Device> list, DeviceSubscriberInterface deviceSubscriberInterface) {
        if (!this.e.getAndSet(true)) {
            SmartHomeDeviceManager.a().a(this.o);
            this.i = deviceSubscriberInterface;
            c.push(this.i);
            this.n.sendMessage(this.n.obtainMessage(3, list));
        }
    }

    /* access modifiers changed from: private */
    public void a(final List<Device> list, String str) {
        List<String> a2;
        Log.d(d, "subscribe subid=" + str);
        if (list != null && list.size() != 0 && this.i != null) {
            JSONObject jSONObject = new JSONObject();
            for (int i2 = 0; i2 < list.size(); i2++) {
                Device device = list.get(i2);
                if (!(device == null || (a2 = this.i.a(device.did)) == null)) {
                    a2.add(f15537a);
                    a2.add(b);
                    JSONArray jSONArray = new JSONArray();
                    for (String next : a2) {
                        if (!TextUtils.isEmpty(next)) {
                            if (!next.startsWith("prop.") && !next.startsWith(Device.EVENT_PREFIX)) {
                                next = "prop." + next;
                            }
                            jSONArray.put(next);
                        }
                    }
                    try {
                        jSONObject.put(device.did, jSONArray);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            if (jSONObject.keys() != null && jSONObject.keys().hasNext()) {
                this.g.add(list);
                Log.d(d, jSONObject.toString());
                DeviceSubscribeManager.a().a(jSONObject, str, 120000, (SubscribeCallback) new SubscribeCallback() {
                    public void a(String str) {
                        DevicePropSubscriber.this.g.remove(list);
                        DevicePropSubscriber.this.h.put(str, list);
                        DevicePropSubscriber.this.n.sendMessageDelayed(DevicePropSubscriber.this.n.obtainMessage(1, str), 120000);
                    }

                    public void a(Error error) {
                        String str;
                        DevicePropSubscriber.this.g.remove(list);
                        DevicePropSubscriber.this.h.put("", list);
                        DevicePropSubscriber.this.n.sendMessageDelayed(DevicePropSubscriber.this.n.obtainMessage(1, ""), 5000);
                        String b2 = DevicePropSubscriber.d;
                        StringBuilder sb = new StringBuilder();
                        sb.append("startSubscribeBatch fail =");
                        if (error == null) {
                            str = null;
                        } else {
                            str = error.a() + "," + error.b();
                        }
                        sb.append(str);
                        sb.append(" mDeviceSubscriberInterface:");
                        sb.append(DevicePropSubscriber.this.i);
                        LogUtilGrey.a(b2, sb.toString());
                    }

                    public void a(String str, String str2, JSONArray jSONArray) {
                        String b2 = DevicePropSubscriber.d;
                        StringBuilder sb = new StringBuilder();
                        sb.append("subscribe onReceive did=");
                        sb.append(str);
                        sb.append(",model=");
                        sb.append(str2);
                        sb.append(", prop=");
                        sb.append(jSONArray == null ? null : jSONArray.toString());
                        Log.d(b2, sb.toString());
                        DevicePropSubscriber.this.i.a(str, jSONArray);
                    }
                });
            }
        }
    }

    public void a() {
        try {
            this.n.removeMessages(1);
            c.remove(this.i);
            SmartHomeDeviceManager.a().c(this.o);
            c();
            this.n.getLooper().quitSafely();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.f = true;
    }

    private void c() {
        for (String a2 : this.h.keySet()) {
            DeviceSubscribeManager.a().a(a2, (UnSubscribeCallback) null);
        }
        this.h.clear();
    }

    /* access modifiers changed from: private */
    public void b(Set<Device> set) {
        if (set != null && !set.isEmpty()) {
            Iterator<String> it = this.h.keySet().iterator();
            while (it.hasNext()) {
                List list = this.h.get(it.next());
                if (list == null || list.size() == 0) {
                    it.remove();
                } else {
                    int i2 = 0;
                    while (i2 < list.size()) {
                        Device device = (Device) list.get(i2);
                        if (device == null) {
                            list.remove(i2);
                            i2--;
                            if (list.size() == 0) {
                                it.remove();
                            }
                        } else if (!set.contains(device)) {
                            list.remove(i2);
                            i2--;
                            if (list.size() == 0) {
                                it.remove();
                            }
                        }
                        i2++;
                    }
                }
            }
        }
    }

    public static JSONArray a(String str) {
        if (TextUtils.isEmpty(str) || d() == null) {
            return null;
        }
        return d().b(str);
    }

    private static DeviceSubscriberInterface d() {
        try {
            return c.peek();
        } catch (EmptyStackException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
