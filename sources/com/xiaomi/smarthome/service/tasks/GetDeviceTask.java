package com.xiaomi.smarthome.service.tasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.xiaomi.miot.DeviceInfo;
import com.xiaomi.miot.service.ICallback;
import com.xiaomi.passport.ui.settings.UserAvatarUpdateActivity;
import com.xiaomi.smarthome.AppStateNotifier;
import com.xiaomi.smarthome.MainPageOpManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.renderer.SubTitleHelper;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.devicelistswitch.model.DeviceListSwitchManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.homeroom.model.GridViewData;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.listcamera.CameraGroupManager;
import com.xiaomi.smarthome.listcamera.CameraInfoRefreshManager;
import com.xiaomi.smarthome.miio.camera.CameraAlarm;
import com.xiaomi.smarthome.miio.camera.CameraCloudStorage;
import com.xiaomi.smarthome.miio.camera.CameraDeviceDataManager;
import com.xiaomi.smarthome.miio.camera.alarm.ICameraAlarmCallback;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.ControlCardInfoManager;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class GetDeviceTask implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22065a = "key_show_camera";
    public static final String b = "pref_show_camera";
    public static final String c = "device_infos";
    public static final String d = "switch_refresh_success";
    public static final String e = "switch_refresh_failed";
    public static final String f = "miui.action_refresh_success";
    public static final String g = "miui.action_refresh_failed";
    public static final String h = "get_camera_success";
    public static final String i = "get_camera_faild";
    private static final String k = "device_card_data";
    ICallback j;
    private boolean l = true;
    /* access modifiers changed from: private */
    public boolean m = false;
    /* access modifiers changed from: private */
    public boolean n = false;
    /* access modifiers changed from: private */
    public boolean o = false;
    /* access modifiers changed from: private */
    public CameraAlarm p;
    /* access modifiers changed from: private */
    public CameraCloudStorage q;
    /* access modifiers changed from: private */
    public String r;

    public GetDeviceTask(ICallback iCallback, boolean z) {
        this.j = iCallback;
        this.l = z;
    }

    GetDeviceTask(ICallback iCallback) {
        this.j = iCallback;
    }

    public void run() {
        JSONObject f2;
        if (!this.l) {
            List<GridViewData> F = HomeManager.a().F();
            Bundle bundle = new Bundle();
            ArrayList<DeviceInfo> a2 = a(F);
            if (a2 == null || a2.size() <= 0) {
                bundle.putInt("error_code", 4);
                try {
                    if (this.j != null) {
                        this.j.onFailure(bundle);
                    }
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            } else {
                bundle.setClassLoader(DeviceInfo.class.getClassLoader());
                bundle.putString(LoginTask.f22078a, CoreApi.a().s());
                bundle.putParcelableArrayList(c, a2);
                if (SharePrefsManager.b(SHApplication.getAppContext().getSharedPreferences(b, 0), f22065a, true) && (f2 = f()) != null) {
                    bundle.putString("device_camera_data", f2.toString());
                }
                Log.e("GetDeviceTask", "notify success cache return:" + a2.size() + "  devices:" + F);
                try {
                    if (this.j != null) {
                        this.j.onSuccess(bundle);
                    }
                } catch (RemoteException e3) {
                    e3.printStackTrace();
                }
            }
        } else {
            CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                public void onCoreReady() {
                    int a2 = SHApplication.getStateNotifier().a();
                    if (a2 == 0 || a2 == 3) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("error_code", 1);
                        if (GetDeviceTask.this.j == null) {
                            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("miui.action_refresh_failed"));
                            return;
                        }
                        try {
                            GetDeviceTask.this.j.onFailure(bundle);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    } else {
                        SHApplication.getStateNotifier().a((AppStateNotifier.LoginCallback) new AppStateNotifier.LoginCallback() {
                            public void a() {
                                Log.e("GetDeviceTask", "login success");
                                IntentFilter intentFilter = new IntentFilter(HomeManager.S);
                                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(new BroadcastReceiver() {
                                    public void onReceive(Context context, Intent intent) {
                                        Log.e("GetDeviceTask", "receive device refresh success");
                                        List<GridViewData> F = HomeManager.a().F();
                                        if (F == null || F.size() == 0) {
                                            boolean unused = GetDeviceTask.this.n = true;
                                            boolean unused2 = GetDeviceTask.this.m = true;
                                            boolean unused3 = GetDeviceTask.this.o = true;
                                            GetDeviceTask.this.e();
                                            return;
                                        }
                                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this);
                                        GetDeviceTask.this.c();
                                        GetDeviceTask.this.d();
                                        GetDeviceTask.this.a();
                                    }
                                }, intentFilter);
                                HomeManager.a().L();
                                CoreApi.a().P();
                            }

                            public void b() {
                                Bundle bundle = new Bundle();
                                bundle.setClassLoader(DeviceInfo.class.getClassLoader());
                                bundle.putInt("error_code", 2);
                                if (GetDeviceTask.this.j == null) {
                                    LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("miui.action_refresh_failed"));
                                    return;
                                }
                                try {
                                    GetDeviceTask.this.j.onFailure(bundle);
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(h);
        intentFilter.addAction(i);
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this);
                if (intent.getAction().equals(GetDeviceTask.h)) {
                    ArrayList arrayList = new ArrayList();
                    for (Device next : MultiHomeDeviceManager.a().d()) {
                        PluginRecord d = CoreApi.a().d(next.model);
                        if (d != null && !IRDeviceUtil.a(d) && next.model.contains(UserAvatarUpdateActivity.CAMERA) && !next.model.contains("virtual")) {
                            arrayList.add(next);
                        }
                    }
                    for (Device next2 : MultiHomeDeviceManager.a().e()) {
                        PluginRecord d2 = CoreApi.a().d(next2.model);
                        if (d2 != null && !IRDeviceUtil.a(d2) && next2.model.contains(UserAvatarUpdateActivity.CAMERA) && !next2.model.contains("virtual")) {
                            arrayList.add(next2);
                        }
                    }
                    CameraGroupManager.a().c((List<Device>) arrayList);
                    GetDeviceTask.this.b();
                    CameraAlarm unused = GetDeviceTask.this.p = null;
                    return;
                }
                CameraCloudStorage unused2 = GetDeviceTask.this.q = null;
                boolean unused3 = GetDeviceTask.this.o = true;
                GetDeviceTask.this.e();
            }
        }, intentFilter);
        CameraInfoRefreshManager.a().f();
    }

    /* access modifiers changed from: private */
    public void b() {
        if (CameraGroupManager.a().c() == null || CameraGroupManager.a().c().size() == 0) {
            a(new boolean[]{true, true, true});
            return;
        }
        final boolean[] zArr = {false, false, false};
        CameraDeviceDataManager.getInstance().getAlarmStatus(CameraGroupManager.a().c().get(0).f19240a, new ICameraAlarmCallback<CameraAlarm>() {
            /* renamed from: a */
            public void onSuccess(CameraAlarm cameraAlarm, Object obj) {
                zArr[0] = true;
                CameraAlarm unused = GetDeviceTask.this.p = cameraAlarm;
                GetDeviceTask.this.a(zArr);
            }

            public void onFailure(int i, String str) {
                zArr[0] = true;
                CameraAlarm unused = GetDeviceTask.this.p = null;
                GetDeviceTask.this.a(zArr);
            }
        });
        CameraDeviceDataManager.getInstance().getCloudStorageStatus(CameraGroupManager.a().c().get(0).f19240a, new CameraDeviceDataManager.ICameraDeviceDataCallback<CameraCloudStorage>() {
            /* renamed from: a */
            public void onSuccess(CameraCloudStorage cameraCloudStorage, Object obj) {
                zArr[1] = true;
                CameraCloudStorage unused = GetDeviceTask.this.q = cameraCloudStorage;
                GetDeviceTask.this.a(zArr);
            }

            public void onFailure(int i, String str) {
                zArr[1] = true;
                CameraCloudStorage unused = GetDeviceTask.this.q = null;
                GetDeviceTask.this.a(zArr);
            }
        });
        CameraDeviceDataManager.getInstance().getLatestImageUri(CameraGroupManager.a().c().get(0).f19240a, new CameraDeviceDataManager.ICameraDeviceDataCallback<String>() {
            /* renamed from: a */
            public void onSuccess(String str, Object obj) {
                zArr[2] = true;
                String unused = GetDeviceTask.this.r = str;
                GetDeviceTask.this.a(zArr);
            }

            public void onFailure(int i, String str) {
                zArr[2] = true;
                String unused = GetDeviceTask.this.r = null;
                GetDeviceTask.this.a(zArr);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(boolean[] zArr) {
        if (zArr[0] && zArr[1] && zArr[2]) {
            this.o = true;
            e();
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(e);
        intentFilter.addAction(d);
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this);
                if (intent.getAction().equals(GetDeviceTask.e)) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("error_code", 4);
                    Log.e("GetDeviceTask", "receive switch refresh failed");
                    if (GetDeviceTask.this.j == null) {
                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("miui.action_refresh_failed"));
                        return;
                    }
                    try {
                        GetDeviceTask.this.j.onFailure(bundle);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("GetDeviceTask", "receive switch refresh success");
                    boolean unused = GetDeviceTask.this.m = true;
                    GetDeviceTask.this.e();
                }
            }
        }, intentFilter);
        DeviceListSwitchManager.a().b();
    }

    /* access modifiers changed from: private */
    public void d() {
        IntentFilter intentFilter = new IntentFilter(ControlCardInfoManager.h);
        AnonymousClass7 r1 = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                Log.e("GetDeviceTask", "receive card refresh success");
                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this);
                boolean unused = GetDeviceTask.this.n = true;
                GetDeviceTask.this.e();
            }
        };
        ControlCardInfoManager.f().i();
        MiotSpecCardManager.f().g();
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(r1, intentFilter);
    }

    /* access modifiers changed from: private */
    public void e() {
        JSONObject f2;
        if (this.n && this.m && this.o) {
            if (this.j == null) {
                Log.e("GetDeviceTask", "send broadcast");
                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("miui.action_refresh_success"));
                return;
            }
            List<GridViewData> F = HomeManager.a().F();
            ArrayList<DeviceInfo> a2 = a(F);
            Bundle bundle = new Bundle();
            if (a2 == null || a2.size() <= 0) {
                bundle.putInt("error_code", 4);
                try {
                    if (this.j != null) {
                        this.j.onFailure(bundle);
                    }
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            } else {
                bundle.putString(LoginTask.f22078a, CoreApi.a().s());
                bundle.setClassLoader(DeviceInfo.class.getClassLoader());
                bundle.putParcelableArrayList(c, a2);
                if (SharePrefsManager.b(SHApplication.getAppContext().getSharedPreferences(b, 0), f22065a, true) && (f2 = f()) != null) {
                    bundle.putString("device_camera_data", f2.toString());
                }
                Log.e("GetDeviceTask", "notify success return:" + a2.size() + "  devices:" + F);
                try {
                    if (this.j != null) {
                        this.j.onSuccess(bundle);
                    }
                } catch (RemoteException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0082 A[Catch:{ JSONException -> 0x00a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008f A[Catch:{ JSONException -> 0x00a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a0 A[Catch:{ JSONException -> 0x00a8 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.json.JSONObject f() {
        /*
            r8 = this;
            com.xiaomi.smarthome.listcamera.CameraGroupManager r0 = com.xiaomi.smarthome.listcamera.CameraGroupManager.a()
            java.util.List r0 = r0.c()
            r1 = 0
            if (r0 == 0) goto L_0x00a9
            int r2 = r0.size()
            if (r2 != 0) goto L_0x0013
            goto L_0x00a9
        L_0x0013:
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r2 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            r3 = 0
            java.lang.Object r4 = r0.get(r3)
            com.xiaomi.smarthome.listcamera.CameraGroupManager$GroupInfo r4 = (com.xiaomi.smarthome.listcamera.CameraGroupManager.GroupInfo) r4
            java.lang.String r4 = r4.f19240a
            com.xiaomi.smarthome.device.Device r2 = r2.b((java.lang.String) r4)
            if (r2 != 0) goto L_0x0027
            return r1
        L_0x0027:
            org.json.JSONObject r4 = new org.json.JSONObject
            r4.<init>()
            java.lang.String r5 = "camera_title"
            java.lang.String r6 = r2.name     // Catch:{ JSONException -> 0x00a8 }
            r4.put(r5, r6)     // Catch:{ JSONException -> 0x00a8 }
            android.os.Bundle r5 = r2.property     // Catch:{ JSONException -> 0x00a8 }
            if (r5 == 0) goto L_0x0053
            android.os.Bundle r5 = r2.property     // Catch:{ JSONException -> 0x00a8 }
            java.lang.String r6 = "device_list_switch_subtitle"
            java.lang.String r7 = ""
            java.lang.String r5 = r5.getString(r6, r7)     // Catch:{ JSONException -> 0x00a8 }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ JSONException -> 0x00a8 }
            if (r5 == 0) goto L_0x0048
            goto L_0x0053
        L_0x0048:
            android.os.Bundle r2 = r2.property     // Catch:{ JSONException -> 0x00a8 }
            java.lang.String r3 = "device_list_switch_subtitle"
            java.lang.String r5 = ""
            java.lang.String r2 = r2.getString(r3, r5)     // Catch:{ JSONException -> 0x00a8 }
            goto L_0x0070
        L_0x0053:
            android.content.Context r5 = com.xiaomi.smarthome.application.SHApplication.getAppContext()     // Catch:{ JSONException -> 0x00a8 }
            java.lang.String r5 = r2.getSubtitleByDesc(r5, r3)     // Catch:{ JSONException -> 0x00a8 }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ JSONException -> 0x00a8 }
            if (r6 == 0) goto L_0x006f
            com.xiaomi.smarthome.device.renderer.SubTitleHelper r5 = new com.xiaomi.smarthome.device.renderer.SubTitleHelper     // Catch:{ JSONException -> 0x00a8 }
            r5.<init>(r2)     // Catch:{ JSONException -> 0x00a8 }
            android.content.Context r2 = com.xiaomi.smarthome.application.SHApplication.getAppContext()     // Catch:{ JSONException -> 0x00a8 }
            java.lang.String r2 = r5.a(r2, r3)     // Catch:{ JSONException -> 0x00a8 }
            goto L_0x0070
        L_0x006f:
            r2 = r5
        L_0x0070:
            java.lang.String r3 = "camera_desc"
            r4.put(r3, r2)     // Catch:{ JSONException -> 0x00a8 }
            java.lang.String r2 = "camera_num"
            int r0 = r0.size()     // Catch:{ JSONException -> 0x00a8 }
            r4.put(r2, r0)     // Catch:{ JSONException -> 0x00a8 }
            com.xiaomi.smarthome.miio.camera.CameraAlarm r0 = r8.p     // Catch:{ JSONException -> 0x00a8 }
            if (r0 == 0) goto L_0x008b
            java.lang.String r0 = "support_alarm"
            com.xiaomi.smarthome.miio.camera.CameraAlarm r2 = r8.p     // Catch:{ JSONException -> 0x00a8 }
            boolean r2 = r2.isAlarmEnabled     // Catch:{ JSONException -> 0x00a8 }
            r4.put(r0, r2)     // Catch:{ JSONException -> 0x00a8 }
        L_0x008b:
            com.xiaomi.smarthome.miio.camera.CameraCloudStorage r0 = r8.q     // Catch:{ JSONException -> 0x00a8 }
            if (r0 == 0) goto L_0x0098
            java.lang.String r0 = "support_cloud"
            com.xiaomi.smarthome.miio.camera.CameraCloudStorage r2 = r8.q     // Catch:{ JSONException -> 0x00a8 }
            boolean r2 = r2.isCloudInUse     // Catch:{ JSONException -> 0x00a8 }
            r4.put(r0, r2)     // Catch:{ JSONException -> 0x00a8 }
        L_0x0098:
            java.lang.String r0 = r8.r     // Catch:{ JSONException -> 0x00a8 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ JSONException -> 0x00a8 }
            if (r0 != 0) goto L_0x00a7
            java.lang.String r0 = "camera_image_url"
            java.lang.String r2 = r8.r     // Catch:{ JSONException -> 0x00a8 }
            r4.put(r0, r2)     // Catch:{ JSONException -> 0x00a8 }
        L_0x00a7:
            return r4
        L_0x00a8:
            return r1
        L_0x00a9:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.service.tasks.GetDeviceTask.f():org.json.JSONObject");
    }

    public static DeviceInfo a(GridViewData gridViewData) {
        if (gridViewData.f18311a != GridViewData.GridType.TYPE_IR) {
            return a(gridViewData.b, gridViewData.c);
        }
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.f11363a = DeviceUtils.a();
        deviceInfo.g = true;
        deviceInfo.b = SHApplication.getAppContext().getString(R.string.phone_ir_device);
        int c2 = IRDeviceUtil.c(SHApplication.getAppContext());
        deviceInfo.e = SHApplication.getAppContext().getResources().getQuantityString(R.plurals.ir_device_count, c2, new Object[]{Integer.valueOf(c2)});
        return deviceInfo;
    }

    @NonNull
    public static DeviceInfo a(Device device, String str) {
        Pair pair;
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.f11363a = device.did;
        deviceInfo.b = str;
        deviceInfo.d = device.model;
        deviceInfo.g = device.isOnlineAdvance();
        PluginRecord d2 = CoreApi.a().d(device.model);
        if (d2 != null) {
            deviceInfo.c = d2.t();
            if (device.property == null || TextUtils.isEmpty(device.property.getString(DeviceListSwitchManager.f15515a, ""))) {
                deviceInfo.e = device.getSubtitleByDesc(SHApplication.getAppContext(), false);
                if (TextUtils.isEmpty(deviceInfo.e)) {
                    deviceInfo.e = new SubTitleHelper(device).a(SHApplication.getAppContext(), false);
                }
            } else {
                deviceInfo.e = device.property.getString(DeviceListSwitchManager.f15515a, "");
            }
            ArrayList<Pair> c2 = MainPageOpManager.a().c(device);
            if (c2 != null) {
                Bundle bundle = new Bundle();
                Iterator<Pair> it = c2.iterator();
                while (it.hasNext()) {
                    Pair next = it.next();
                    if ((next.first instanceof String) && next.second != null) {
                        bundle.putString((String) next.first, next.second.toString());
                    }
                }
                deviceInfo.f = bundle;
                if (c2.size() <= 0 || (pair = c2.get(0)) == null || !(pair.first instanceof CardItem.State)) {
                    deviceInfo.h = false;
                } else if (CardItem.State.NOR == pair.first) {
                    deviceInfo.h = true;
                    deviceInfo.i = false;
                } else if (CardItem.State.SELECTED == pair.first) {
                    deviceInfo.h = true;
                    deviceInfo.i = true;
                } else {
                    deviceInfo.h = false;
                }
            }
        }
        return deviceInfo;
    }

    public static ArrayList<DeviceInfo> a(List<GridViewData> list) {
        Pair pair;
        ArrayList<DeviceInfo> arrayList = new ArrayList<>();
        if (list == null) {
            return arrayList;
        }
        for (GridViewData next : list) {
            DeviceInfo deviceInfo = new DeviceInfo();
            if (next.f18311a == GridViewData.GridType.TYPE_IR) {
                deviceInfo.f11363a = DeviceUtils.a();
                deviceInfo.b = SHApplication.getAppContext().getString(R.string.phone_ir_device);
                deviceInfo.g = true;
                int c2 = IRDeviceUtil.c(SHApplication.getAppContext());
                if (list.size() != 1 || c2 != 0) {
                    deviceInfo.e = SHApplication.getAppContext().getResources().getQuantityString(R.plurals.ir_device_count, c2, new Object[]{Integer.valueOf(c2)});
                    arrayList.add(deviceInfo);
                }
            } else {
                deviceInfo.f11363a = next.b.did;
                deviceInfo.b = next.c;
                deviceInfo.d = next.b.model;
                deviceInfo.g = next.b.isOnlineAdvance();
                PluginRecord d2 = CoreApi.a().d(next.b.model);
                if (d2 != null) {
                    deviceInfo.c = d2.t();
                    if (next.b.property == null || TextUtils.isEmpty(next.b.property.getString(DeviceListSwitchManager.f15515a, ""))) {
                        deviceInfo.e = next.b.getSubtitleByDesc(SHApplication.getAppContext(), false);
                        if (TextUtils.isEmpty(deviceInfo.e)) {
                            deviceInfo.e = new SubTitleHelper(next.b).a(SHApplication.getAppContext(), false);
                        }
                    } else {
                        deviceInfo.e = next.b.property.getString(DeviceListSwitchManager.f15515a, "");
                    }
                    ArrayList<Pair> c3 = MainPageOpManager.a().c(next.b);
                    if (c3 != null) {
                        Bundle bundle = new Bundle();
                        Iterator<Pair> it = c3.iterator();
                        while (it.hasNext()) {
                            Pair next2 = it.next();
                            if (!(next2 == null || !(next2.first instanceof String) || next2.second == null)) {
                                bundle.putString((String) next2.first, next2.second.toString());
                            }
                        }
                        deviceInfo.f = bundle;
                        if (c3.size() <= 0 || (pair = c3.get(0)) == null || !(pair.first instanceof CardItem.State)) {
                            deviceInfo.h = false;
                        } else if (CardItem.State.NOR == pair.first) {
                            deviceInfo.h = true;
                            deviceInfo.i = false;
                        } else if (CardItem.State.SELECTED == pair.first) {
                            deviceInfo.h = true;
                            deviceInfo.i = true;
                        } else {
                            deviceInfo.h = false;
                        }
                    }
                    arrayList.add(deviceInfo);
                }
            }
        }
        return arrayList;
    }
}
