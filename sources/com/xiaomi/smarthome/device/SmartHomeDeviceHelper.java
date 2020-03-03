package com.xiaomi.smarthome.device;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.coloros.mcssdk.PushManager;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.server.internal.plugin.NotificationChannelCreator;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.miio.device.PhoneDevice;
import com.xiaomi.smarthome.miio.device.PhoneIRDevice;
import com.xiaomi.smarthomedevice.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class SmartHomeDeviceHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14973a = 0;
    public static final int b = 1;
    public static final int c = 2;
    private static final int d = 1002;
    private static final int e = 1003;
    private static final String f = "notified_devices_pref";
    private static final long g = 604800000;
    private static volatile SmartHomeDeviceHelper h;
    private DeviceTagInterface i;
    private List<IPluginInfoEventListener> j;
    private List<ModelGroupInfo> k;
    private List<ModelGroupInfo> l;
    private boolean m;
    private int n;
    private Context o;
    private NotificationManager p;
    private SharedPreferences q;

    public interface IPluginInfoEventListener {
        void a(int i);
    }

    private SmartHomeDeviceHelper(Context context) {
        this.i = PluginHostApi.instance() == null ? null : ((PluginHostApi) PluginHostApi.instance()).getDeviceTagManager();
        this.j = new ArrayList();
        this.k = null;
        this.l = null;
        this.n = -1;
        this.o = context.getApplicationContext();
        this.p = (NotificationManager) this.o.getSystemService(PushManager.MESSAGE_TYPE_NOTI);
    }

    public static SmartHomeDeviceHelper a() {
        if (h == null) {
            synchronized (SmartHomeDeviceHelper.class) {
                if (h == null) {
                    h = new SmartHomeDeviceHelper(CommonApplication.getAppContext());
                }
            }
        }
        return h;
    }

    public DeviceTagInterface<Device> b() {
        return this.i;
    }

    public boolean a(int i2, String str, String str2) {
        if (!this.i.a(i2, str, str2)) {
            return false;
        }
        d(SmartHomeDeviceManager.a().d());
        return true;
    }

    public String c() {
        return this.i.b();
    }

    public List<Device> a(List<Device> list) {
        if (!IRDeviceUtil.c()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (Device next : list) {
            if (!IRDeviceUtil.a(next.did)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public void b(List<Device> list) {
        List<Device> a2 = a(list);
        if (this.i != null) {
            this.i.a(a2);
        }
    }

    public void c(List<Device> list) {
        if (this.i != null) {
            this.i.b(list);
        }
    }

    public void d(List<Device> list) {
        if (this.i != null) {
            this.i.c(a(list));
        }
    }

    public boolean d() {
        return this.i.a();
    }

    public void a(IPluginInfoEventListener iPluginInfoEventListener) {
        this.j.add(iPluginInfoEventListener);
    }

    public void b(IPluginInfoEventListener iPluginInfoEventListener) {
        this.j.remove(iPluginInfoEventListener);
    }

    private void b(int i2) {
        for (int i3 = 0; i3 < this.j.size(); i3++) {
            if (this.j.get(i3) != null) {
                this.j.get(i3).a(i2);
            }
        }
    }

    public void e() {
        b(0);
    }

    public void f() {
        b(1);
    }

    public void g() {
        b(2);
    }

    public void a(boolean z) {
        this.m = z;
    }

    public boolean h() {
        return this.m;
    }

    public void i() {
        if (CoreApi.a().q()) {
            Device device = null;
            Iterator<Device> it = SmartHomeDeviceManager.a().d().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Device next = it.next();
                if ((next instanceof RouterDevice) && !next.isBinded()) {
                    device = next;
                    break;
                }
            }
            if (device != null) {
                c(device);
            } else if (this.p != null) {
                this.p.cancel(1003);
            }
        }
    }

    public boolean a(Device device) {
        if (this.q == null) {
            this.q = this.o.getSharedPreferences(f, 0);
        }
        if (System.currentTimeMillis() - this.q.getLong(device.did, 0) > 604800000) {
            return false;
        }
        return true;
    }

    public void b(Device device) {
        if (this.q == null) {
            this.q = this.o.getSharedPreferences(f, 0);
        }
        this.q.edit().putLong(device.did, System.currentTimeMillis()).apply();
    }

    private void c(Device device) {
        Notification notification;
        if (!a(device)) {
            b(device);
            boolean z = device instanceof MiTVDevice;
            if ((z || (device instanceof RouterDevice)) && this.p != null) {
                Intent intent = new Intent(this.o, GlobalSetting.z);
                intent.setFlags(335544320);
                intent.putExtra("source", 5);
                intent.putExtra("device_id", device.did);
                PendingIntent activity = PendingIntent.getActivity(this.o, R.string.app_name, intent, 134217728);
                String str = "";
                if (z) {
                    str = this.o.getString(R.string.found_new_unbind_mitvdevice);
                } else if (device instanceof RouterDevice) {
                    str = this.o.getString(R.string.found_new_unbind_routerdevice);
                }
                if (Build.VERSION.SDK_INT >= 26) {
                    Notification.Builder builder = new Notification.Builder(this.o, NotificationChannelCreator.b(this.p));
                    builder.setContentTitle(this.o.getString(R.string.wifi_scan_new_device_title));
                    builder.setSmallIcon(R.drawable.notify_icon);
                    builder.setWhen(System.currentTimeMillis());
                    builder.setAutoCancel(true);
                    builder.setContentTitle(this.o.getString(R.string.wifi_scan_new_device_title));
                    builder.setContentIntent(activity);
                    builder.setContentText(str);
                    notification = builder.build();
                } else {
                    NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this.o);
                    builder2.setContentTitle(this.o.getString(R.string.wifi_scan_new_device_title));
                    builder2.setSmallIcon(R.drawable.notify_icon);
                    builder2.setWhen(System.currentTimeMillis());
                    builder2.setAutoCancel(true);
                    builder2.setContentTitle(this.o.getString(R.string.wifi_scan_new_device_title));
                    builder2.setContentIntent(activity);
                    builder2.setContentText(str);
                    notification = builder2.build();
                }
                if (z) {
                    this.p.notify(1002, notification);
                } else if (device instanceof RouterDevice) {
                    this.p.notify(1003, notification);
                }
            }
        }
    }

    public void a(int i2) {
        this.n = i2;
    }

    public int j() {
        int i2 = this.n;
        this.n = -1;
        return i2;
    }

    public void e(List<ModelGroupInfo> list) {
        this.l = list;
        this.k = f(list);
    }

    public List<ModelGroupInfo> k() {
        return this.k;
    }

    public List<ModelGroupInfo> l() {
        return this.l;
    }

    public String[] a(ModelGroupInfo modelGroupInfo) {
        List<Device> d2;
        if (modelGroupInfo == null || modelGroupInfo.c.length <= 0 || (d2 = SmartHomeDeviceManager.a().d()) == null || d2.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < d2.size(); i2++) {
            Device device = d2.get(i2);
            if (device != null && !TextUtils.isEmpty(device.model)) {
                int i3 = 0;
                while (true) {
                    if (i3 >= modelGroupInfo.c.length) {
                        break;
                    } else if (device.model.equals(modelGroupInfo.c[i3])) {
                        arrayList.add(device.did);
                        break;
                    } else {
                        i3++;
                    }
                }
            }
        }
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        return strArr;
    }

    public ModelGroupInfo a(List<ModelGroupInfo> list, String str) {
        if (list == null || list.size() <= 0 || TextUtils.isEmpty(str)) {
            return null;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ModelGroupInfo modelGroupInfo = list.get(i2);
            if (modelGroupInfo.d.equals(str)) {
                return modelGroupInfo;
            }
        }
        return null;
    }

    private List<ModelGroupInfo> f(List<ModelGroupInfo> list) {
        List<Device> d2;
        if (list == null || list.size() <= 0 || (d2 = SmartHomeDeviceManager.a().d()) == null || d2.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            ModelGroupInfo modelGroupInfo = list.get(i2);
            if (modelGroupInfo.c != null && modelGroupInfo.c.length > 0 && b(modelGroupInfo) >= 2) {
                arrayList.add(modelGroupInfo);
            }
        }
        return arrayList;
    }

    public int b(ModelGroupInfo modelGroupInfo) {
        List<Device> d2 = SmartHomeDeviceManager.a().d();
        if (d2 == null || d2.size() <= 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < d2.size(); i3++) {
            Device device = d2.get(i3);
            int i4 = 0;
            while (true) {
                if (i4 >= modelGroupInfo.c.length) {
                    break;
                }
                String str = modelGroupInfo.c[i4];
                if (!TextUtils.isEmpty(str) && str.equals(device.model)) {
                    i2++;
                    break;
                }
                i4++;
            }
        }
        return i2;
    }

    public List<Device> m() {
        ArrayList arrayList = new ArrayList();
        List<Device> d2 = SmartHomeDeviceManager.a().d();
        if (d2 == null) {
            return arrayList;
        }
        for (Device next : d2) {
            if (!(next instanceof PhoneDevice) && !(next instanceof PhoneIRDevice) && !IRDeviceUtil.a(next.did) && next.isBinded()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }
}
