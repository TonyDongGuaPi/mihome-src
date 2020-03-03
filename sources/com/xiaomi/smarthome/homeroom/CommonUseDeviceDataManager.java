package com.xiaomi.smarthome.homeroom;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.homeroom.device_order.HomeOrder;
import com.xiaomi.smarthome.homeroom.device_order.OrderCompat;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.newui.DeviceMainPageHelper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommonUseDeviceDataManager {
    public static final String b = "common_used_device_updated";
    public static final String c = "common_use_device_operation";
    public static final String d = "common_used_device_updated_result_code";
    public static final String e = "operation_sync";
    public static final String f = "operation_delete";
    public static final String g = "com.xiaomi.smarthome.common_use.ir_did";
    private static final String h = "CommonUseDeviceDataManager";
    private static CommonUseDeviceDataManager i;

    /* renamed from: a  reason: collision with root package name */
    public Map<String, List<String>> f17934a = new ConcurrentHashMap();

    public void c() {
    }

    @SuppressLint({"CheckResult"})
    private CommonUseDeviceDataManager() {
        for (HomeOrder next : OrderCompat.f18244a.a()) {
            List list = this.f17934a.get(next.a());
            ArrayList<String> a2 = next.d().a();
            if (a2.isEmpty() || (a2.size() == 1 && TextUtils.equals("-", a2.get(0)))) {
                LogUtilGrey.a("DeviceOrderDebug", "CommonUseDeviceDataManager: <init> id: " + next.a() + " ;order is [-]: continue  ");
            } else if (list == null || list.isEmpty()) {
                this.f17934a.put(next.a(), new ArrayList(a2));
            }
        }
    }

    public static CommonUseDeviceDataManager a() {
        if (i == null) {
            synchronized (CommonUseDeviceDataManager.class) {
                if (i == null) {
                    i = new CommonUseDeviceDataManager();
                }
            }
        }
        return i;
    }

    public void b() {
        i = null;
    }

    public boolean a(List<String> list) {
        boolean z;
        Home m = HomeManager.a().m();
        if (m == null) {
            return false;
        }
        List list2 = this.f17934a.get(m.j());
        if (list != null && list2 != null && list2.size() == list.size()) {
            int i2 = 0;
            while (true) {
                if (i2 >= list.size()) {
                    z = false;
                    break;
                }
                String str = list.get(i2);
                if (!TextUtils.isEmpty(str) && !list2.contains(str)) {
                    break;
                }
                i2++;
            }
        }
        z = true;
        if (!z) {
            return false;
        }
        this.f17934a.put(m.j(), list);
        c(m.j());
        Intent intent = new Intent(b);
        intent.putExtra(d, ErrorCode.SUCCESS);
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
        return true;
    }

    public boolean b(List<String> list) {
        boolean z;
        if (list == null || list.isEmpty()) {
            return true;
        }
        Home m = HomeManager.a().m();
        if (m == null) {
            return false;
        }
        List list2 = this.f17934a.get(m.j());
        if (list2 == null) {
            list2 = new ArrayList();
            this.f17934a.put(m.j(), list2);
            z = true;
        } else {
            z = false;
        }
        boolean z2 = z;
        for (int i2 = 0; i2 < list.size(); i2++) {
            String str = list.get(i2);
            if (!TextUtils.isEmpty(str) && !list2.contains(str)) {
                list2.add(str);
                z2 = true;
            }
        }
        if (!z2) {
            return false;
        }
        c(m.j());
        return true;
    }

    public void a(String str) {
        Home m = HomeManager.a().m();
        if (m != null) {
            List list = this.f17934a.get(m.j());
            if (list == null || !list.contains(str)) {
                if (list == null) {
                    list = new ArrayList();
                    this.f17934a.put(m.j(), list);
                }
                list.add(str);
                c(m.j());
                Intent intent = new Intent(b);
                intent.putExtra(d, ErrorCode.SUCCESS);
                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
            }
        }
    }

    public void b(String str) {
        List list;
        Home m = HomeManager.a().m();
        if (m != null && (list = this.f17934a.get(m.j())) != null && list.remove(str)) {
            Intent intent = new Intent(b);
            intent.putExtra(d, ErrorCode.SUCCESS);
            intent.putExtra(c, f);
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
            c(m.j());
        }
    }

    public void c(String str) {
        Home j = HomeManager.a().j(str);
        if (j != null && j.p()) {
            List list = this.f17934a.get(str);
            if (list == null) {
                list = new ArrayList();
            }
            OrderCompat.f18244a.a((List<String>) list, str);
        }
    }

    public boolean a(String str, List<String> list) {
        this.f17934a.put(str, new ArrayList(list));
        DeviceMainPageHelper.a(true);
        Intent intent = new Intent(b);
        intent.putExtra(d, ErrorCode.SUCCESS);
        intent.putExtra(c, e);
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
        return true;
    }

    public void a(Throwable th) {
        Intent intent = new Intent(b);
        intent.putExtra(d, ErrorCode.ERROR_UNLOGIN);
        intent.putExtra(c, e);
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
    }

    public void a(List<String> list, Home home) {
        if (list != null && !list.isEmpty() && home != null) {
            List list2 = this.f17934a.get(home.j());
            boolean z = false;
            if (list2 != null && list.size() == list2.size()) {
                int i2 = 0;
                while (true) {
                    if (i2 < list2.size()) {
                        if (!TextUtils.equals((String) list2.get(i2), list.get(i2))) {
                            break;
                        }
                        i2++;
                    } else {
                        z = true;
                        break;
                    }
                }
            }
            if (!z) {
                this.f17934a.put(home.j(), list);
                c(home.j());
            }
        }
    }

    public List<String> d(String str) {
        List list = this.f17934a.get(str);
        if (list == null) {
            return new ArrayList();
        }
        return new ArrayList(list);
    }

    public void b(String str, List<String> list) {
        List list2;
        HashSet<Map.Entry> hashSet = new HashSet<>();
        hashSet.addAll(this.f17934a.entrySet());
        boolean z = false;
        for (Map.Entry entry : hashSet) {
            if (!TextUtils.equals((CharSequence) entry.getKey(), str) && (list2 = (List) entry.getValue()) != null && !list2.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    String str2 = (String) list2.get(i2);
                    if (list.contains(str2)) {
                        arrayList.add(str2);
                    }
                }
                if (!arrayList.isEmpty()) {
                    list2.removeAll(arrayList);
                    z = true;
                }
            }
        }
        if (z) {
            c(str);
        }
    }
}
