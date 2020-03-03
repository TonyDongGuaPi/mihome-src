package com.xiaomi.smarthome.service.tasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.miot.service.ICallback;
import com.xiaomi.smarthome.AppStateNotifier;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.devicelistswitch.model.DeviceListSwitchManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.stat.STAT;

public class DeviceOnTask implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22060a = "device_rpc_success";
    private static final String b = "DeviceOnTask";
    private final boolean c;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public ICallback e;

    DeviceOnTask(String str, boolean z, ICallback iCallback) {
        this.e = iCallback;
        this.d = str;
        this.c = z;
    }

    public void run() {
        Device b2 = SmartHomeDeviceManager.a().b(this.d);
        if (b2 == null) {
            Log.i(b, "device is null");
            STAT.d.V((String) null);
            a();
            return;
        }
        Log.i(b, "updateOpConfig");
        STAT.d.V(b2.model);
        a(b2);
    }

    private void a() {
        CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                int a2 = SHApplication.getStateNotifier().a();
                if (a2 == 0 || a2 == 3) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("error_code", 1);
                    try {
                        Log.i(DeviceOnTask.b, "updateDeviceList no login onFailure");
                        DeviceOnTask.this.e.onFailure(bundle);
                    } catch (RemoteException e) {
                        Log.e(DeviceOnTask.b, Log.getStackTraceString(e));
                    }
                } else {
                    SHApplication.getStateNotifier().a((AppStateNotifier.LoginCallback) new AppStateNotifier.LoginCallback() {
                        public void a() {
                            Log.i(DeviceOnTask.b, "updateDeviceList onLoginSuccess");
                            IntentFilter intentFilter = new IntentFilter(HomeManager.S);
                            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(new BroadcastReceiver() {
                                public void onReceive(Context context, Intent intent) {
                                    Log.i(DeviceOnTask.b, "updateDeviceList onLoginSuccess onReceive");
                                    LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this);
                                    DeviceOnTask.this.a(SmartHomeDeviceManager.a().b(DeviceOnTask.this.d));
                                }
                            }, intentFilter);
                            HomeManager.a().L();
                            CoreApi.a().P();
                        }

                        public void b() {
                            Bundle bundle = new Bundle();
                            bundle.putInt("error_code", 2);
                            try {
                                Log.i(DeviceOnTask.b, "updateDeviceList onLoginFailed");
                                DeviceOnTask.this.e.onFailure(bundle);
                            } catch (RemoteException e) {
                                Log.e(DeviceOnTask.b, Log.getStackTraceString(e));
                            }
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0076 A[Catch:{ Exception -> 0x0091 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x008d A[Catch:{ Exception -> 0x0091 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(final com.xiaomi.smarthome.device.Device r10) {
        /*
            r9 = this;
            com.xiaomi.smarthome.MainPageOpManager r0 = com.xiaomi.smarthome.MainPageOpManager.a()     // Catch:{ Exception -> 0x0091 }
            com.xiaomi.smarthome.newui.card.Card r0 = r0.a(r10)     // Catch:{ Exception -> 0x0091 }
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ Exception -> 0x0091 }
            r1.<init>()     // Catch:{ Exception -> 0x0091 }
            com.xiaomi.smarthome.MainPageOpManager r2 = com.xiaomi.smarthome.MainPageOpManager.a()     // Catch:{ Exception -> 0x0091 }
            java.util.ArrayList r2 = r2.c(r10)     // Catch:{ Exception -> 0x0091 }
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0069
            android.os.Bundle r5 = new android.os.Bundle     // Catch:{ Exception -> 0x0091 }
            r5.<init>()     // Catch:{ Exception -> 0x0091 }
            java.util.Iterator r6 = r2.iterator()     // Catch:{ Exception -> 0x0091 }
        L_0x0022:
            boolean r7 = r6.hasNext()     // Catch:{ Exception -> 0x0091 }
            if (r7 == 0) goto L_0x0048
            java.lang.Object r7 = r6.next()     // Catch:{ Exception -> 0x0091 }
            android.util.Pair r7 = (android.util.Pair) r7     // Catch:{ Exception -> 0x0091 }
            if (r7 == 0) goto L_0x0022
            java.lang.Object r8 = r7.first     // Catch:{ Exception -> 0x0091 }
            boolean r8 = r8 instanceof java.lang.String     // Catch:{ Exception -> 0x0091 }
            if (r8 == 0) goto L_0x0022
            java.lang.Object r8 = r7.second     // Catch:{ Exception -> 0x0091 }
            if (r8 == 0) goto L_0x0022
            java.lang.Object r8 = r7.first     // Catch:{ Exception -> 0x0091 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ Exception -> 0x0091 }
            java.lang.Object r7 = r7.second     // Catch:{ Exception -> 0x0091 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0091 }
            r5.putString(r8, r7)     // Catch:{ Exception -> 0x0091 }
            goto L_0x0022
        L_0x0048:
            java.lang.String r6 = "subtitleMap"
            r1.putBundle(r6, r5)     // Catch:{ Exception -> 0x0091 }
            int r5 = r2.size()     // Catch:{ Exception -> 0x0091 }
            if (r5 <= 0) goto L_0x0069
            java.lang.Object r2 = r2.get(r4)     // Catch:{ Exception -> 0x0091 }
            android.util.Pair r2 = (android.util.Pair) r2     // Catch:{ Exception -> 0x0091 }
            if (r2 == 0) goto L_0x0069
            java.lang.Object r5 = r2.first     // Catch:{ Exception -> 0x0091 }
            boolean r5 = r5 instanceof com.xiaomi.smarthome.newui.card.CardItem.State     // Catch:{ Exception -> 0x0091 }
            if (r5 == 0) goto L_0x0069
            com.xiaomi.smarthome.newui.card.CardItem$State r5 = com.xiaomi.smarthome.newui.card.CardItem.State.SELECTED     // Catch:{ Exception -> 0x0091 }
            java.lang.Object r2 = r2.first     // Catch:{ Exception -> 0x0091 }
            if (r5 != r2) goto L_0x0069
            r2 = 1
            goto L_0x006a
        L_0x0069:
            r2 = 0
        L_0x006a:
            java.lang.String r5 = "new_state"
            boolean r6 = r9.c     // Catch:{ Exception -> 0x0091 }
            r3 = r3 ^ r6
            r1.putBoolean(r5, r3)     // Catch:{ Exception -> 0x0091 }
            boolean r3 = r9.c     // Catch:{ Exception -> 0x0091 }
            if (r2 != r3) goto L_0x008d
            com.xiaomi.smarthome.MainPageOpManager r2 = com.xiaomi.smarthome.MainPageOpManager.a()     // Catch:{ Exception -> 0x0091 }
            java.util.List r0 = r0.a()     // Catch:{ Exception -> 0x0091 }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ Exception -> 0x0091 }
            com.xiaomi.smarthome.newui.card.Card$CardType r0 = (com.xiaomi.smarthome.newui.card.Card.CardType) r0     // Catch:{ Exception -> 0x0091 }
            com.xiaomi.smarthome.service.tasks.DeviceOnTask$2 r3 = new com.xiaomi.smarthome.service.tasks.DeviceOnTask$2     // Catch:{ Exception -> 0x0091 }
            r3.<init>(r1, r10)     // Catch:{ Exception -> 0x0091 }
            r2.a((com.xiaomi.smarthome.device.Device) r10, (int) r4, (com.xiaomi.smarthome.newui.card.Card.CardType) r0, (com.xiaomi.smarthome.frame.AsyncCallback<java.lang.Void, com.xiaomi.smarthome.frame.Error>) r3)     // Catch:{ Exception -> 0x0091 }
            goto L_0x00b6
        L_0x008d:
            r9.a((android.os.Bundle) r1, (com.xiaomi.smarthome.device.Device) r10)     // Catch:{ Exception -> 0x0091 }
            goto L_0x00b6
        L_0x0091:
            r10 = move-exception
            java.lang.String r0 = "DeviceOnTask"
            java.lang.String r10 = android.util.Log.getStackTraceString(r10)
            android.util.Log.e(r0, r10)
            android.os.Bundle r10 = new android.os.Bundle     // Catch:{ RemoteException -> 0x00ac }
            r10.<init>()     // Catch:{ RemoteException -> 0x00ac }
            java.lang.String r0 = "error_code"
            r1 = -3
            r10.putInt(r0, r1)     // Catch:{ RemoteException -> 0x00ac }
            com.xiaomi.miot.service.ICallback r0 = r9.e     // Catch:{ RemoteException -> 0x00ac }
            r0.onFailure(r10)     // Catch:{ RemoteException -> 0x00ac }
            goto L_0x00b6
        L_0x00ac:
            r10 = move-exception
            java.lang.String r0 = "DeviceOnTask"
            java.lang.String r10 = android.util.Log.getStackTraceString(r10)
            android.util.Log.e(r0, r10)
        L_0x00b6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.service.tasks.DeviceOnTask.a(com.xiaomi.smarthome.device.Device):void");
    }

    /* access modifiers changed from: private */
    public void a(Bundle bundle, Device device) {
        if (device.property == null || TextUtils.isEmpty(device.property.getString(DeviceListSwitchManager.f15515a, ""))) {
            bundle.putString("new_description", device.getSubtitleByDesc(SHApplication.getAppContext(), false));
        } else {
            bundle.putString("new_description", device.property.getString(DeviceListSwitchManager.f15515a, ""));
        }
        try {
            this.e.onSuccess(bundle);
        } catch (RemoteException e2) {
            Log.e(b, Log.getStackTraceString(e2));
        }
    }
}
