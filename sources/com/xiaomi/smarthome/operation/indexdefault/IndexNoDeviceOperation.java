package com.xiaomi.smarthome.operation.indexdefault;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.miio.page.devicelistadv.DeviceListEmptyAdView;
import com.xiaomi.smarthome.operation.BaseOperationProvider;
import com.xiaomi.smarthome.operation.Operation;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class IndexNoDeviceOperation extends BaseOperationProvider {
    private static final String d = "action_on_no_device_operation_visible_to_user";
    private WeakReference<DeviceListEmptyAdView> b;
    private BroadcastReceiver c;

    /* access modifiers changed from: protected */
    public void a() {
    }

    /* access modifiers changed from: protected */
    public String b() {
        return "17,19";
    }

    public void a(DeviceListEmptyAdView deviceListEmptyAdView) {
        this.b = new WeakReference<>(deviceListEmptyAdView);
        n();
        a(0, TimeUnit.MILLISECONDS);
    }

    private void n() {
        if (this.c == null) {
            this.c = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    IndexNoDeviceOperation.this.o();
                }
            };
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.c, new IntentFilter(d));
        }
    }

    /* access modifiers changed from: private */
    public void o() {
        DeviceListEmptyAdView deviceListEmptyAdView = (DeviceListEmptyAdView) this.b.get();
        if (deviceListEmptyAdView != null && deviceListEmptyAdView.isShown()) {
            deviceListEmptyAdView.logAdPopUp();
        }
    }

    public static void m() {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(d));
    }

    /* access modifiers changed from: protected */
    public void a(List<Operation> list) {
        DeviceListEmptyAdView deviceListEmptyAdView;
        List<Operation> e;
        if (this.b != null && (deviceListEmptyAdView = (DeviceListEmptyAdView) this.b.get()) != null) {
            if (list.size() > 1) {
                ArrayList arrayList = new ArrayList();
                Iterator<Operation> it = list.iterator();
                while (it.hasNext()) {
                    Operation next = it.next();
                    if (TextUtils.equals(next.f21043a, "19")) {
                        arrayList.add(next);
                        it.remove();
                    }
                }
                list.addAll(arrayList);
                Iterator<Operation> it2 = list.iterator();
                if (it2.hasNext()) {
                    it2.next();
                    while (it2.hasNext()) {
                        Operation next2 = it2.next();
                        if (TextUtils.equals(next2.f21043a, "17")) {
                            it2.remove();
                        }
                        if (TextUtils.equals(next2.f21043a, "19")) {
                            break;
                        }
                    }
                }
            }
            if (!TextUtils.equals(list.get(0).f21043a, "17") && (e = e()) != null && !e.isEmpty()) {
                list.add(0, e.get(0));
            }
            deviceListEmptyAdView.bindOperation(list);
        }
    }

    /* access modifiers changed from: protected */
    public List<Operation> e() {
        if (HomeManager.A()) {
            return super.e();
        }
        Operation operation = new Operation();
        operation.i = BitmapFactory.decodeResource(SHApplication.getAppContext().getResources(), R.drawable.device_list_empty_intro);
        operation.c = SHApplication.getAppContext().getString(R.string.device_shop);
        operation.f = 0;
        operation.g = Long.MAX_VALUE;
        operation.e = 2;
        operation.d = "https://youpin.mi.com/";
        operation.f21043a = "17";
        ArrayList arrayList = new ArrayList();
        arrayList.add(operation);
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public synchronized void i() {
        super.i();
        if (this.c != null) {
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.c);
            this.c = null;
        }
    }
}
