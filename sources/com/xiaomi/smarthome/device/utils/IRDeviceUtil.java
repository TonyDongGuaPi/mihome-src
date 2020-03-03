package com.xiaomi.smarthome.device.utils;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.hardware.ConsumerIrManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.IRRemoteInfo;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.miio.device.PhoneIRDevice;
import com.xiaomi.smarthomedevice.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IRDeviceUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15473a = "phone_ir_card_mode";
    public static final String b = "phone_ir_card_mode_tip";
    public static final String c = "vaiable_added_remotes";
    public static final String d = "phone_ir_configs";
    public static final String e = "state_variable";
    public static final String f = "ir_plugin_mode";
    protected static final int g = 1;
    public static final String h = "com.duokan.phone.remotecontroller";
    public static List<IRRemoteInfo> i = null;
    public static List<IRRemoteInfo> j = null;
    static Boolean k = null;
    private static final String l = "com.xiaomi.mitv.phone.remotecontroller.main";
    private static final String m = "call_from";
    private static final String n = "device_type";
    private static final String o = "mihome";
    /* access modifiers changed from: private */
    public static final String p = "IRDeviceUtil";
    private static final Uri q = Uri.parse("content://com.xiaomi.mitv.phone.remotecontroller.provider.LockScreenProvider");
    /* access modifiers changed from: private */
    public static final String[] r = {"controller_id", "controller_name", "device_type", "intent_action"};
    private static QueryHandler s;
    private static Device t = null;
    private static int u = -1;
    /* access modifiers changed from: private */
    public static AtomicBoolean v = new AtomicBoolean(false);

    public static class DKDeviceType {

        /* renamed from: a  reason: collision with root package name */
        public static final int f15479a = 0;
        public static final int b = 1;
        public static final int c = 2;
        public static final int d = 3;
        public static final int e = 4;
        public static final int f = 5;
        public static final int g = 6;
        public static final int h = 8;
        public static final int i = 10;
        public static final int j = 11;
        public static final int k = 12;
        public static final int l = 13;
        public static final int m = 14;
        public static final int n = 100;
        public static final int o = 101;
        public static final int p = 10000;
        public static final int q = 10001;
        public static final int r = 100000;
        public static final int[] s = {1, 2, 4, 3, 5, 12, 6, 10, 11, 8, 13, 14};
        public static final int[] t = {10000, 10001};
    }

    public interface HasIrDeviceCallback {
        void a(boolean z);
    }

    public interface IRDeviceListener {
        void onQueryComplete(List<IRRemoteInfo> list);
    }

    public static boolean d() {
        return true;
    }

    public static boolean e() {
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000f, code lost:
        r5 = com.xiaomi.smarthome.frame.core.CoreApi.a().d(r0.model);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(final android.content.Context r12, android.content.Intent r13) {
        /*
            com.xiaomi.smarthome.device.Device r0 = b()
            if (r0 == 0) goto L_0x0066
            java.lang.String r1 = r0.model
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x000f
            goto L_0x0066
        L_0x000f:
            com.xiaomi.smarthome.frame.core.CoreApi r1 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r2 = r0.model
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r5 = r1.d((java.lang.String) r2)
            if (r5 != 0) goto L_0x001c
            return
        L_0x001c:
            android.content.Intent r7 = new android.content.Intent
            r7.<init>()
            if (r13 == 0) goto L_0x0026
            r7.putExtras(r13)
        L_0x0026:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            int r1 = com.xiaomi.smarthomedevice.R.string.plugin_downloading
            java.lang.String r1 = r12.getString(r1)
            r13.append(r1)
            java.lang.String r1 = r5.p()
            r13.append(r1)
            int r1 = com.xiaomi.smarthomedevice.R.string.plugin
            java.lang.String r1 = r12.getString(r1)
            r13.append(r1)
            java.lang.String r13 = r13.toString()
            com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog r13 = com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog.b(r12, r13)
            com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask r1 = new com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask
            r1.<init>()
            com.xiaomi.smarthome.frame.plugin.PluginApi r3 = com.xiaomi.smarthome.frame.plugin.PluginApi.getInstance()
            r6 = 1
            com.xiaomi.smarthome.device.api.DeviceStat r8 = r0.newDeviceStat()
            r9 = 0
            r10 = 0
            com.xiaomi.smarthome.device.utils.IRDeviceUtil$1 r11 = new com.xiaomi.smarthome.device.utils.IRDeviceUtil$1
            r11.<init>(r1, r13, r12, r5)
            r4 = r12
            r3.sendMessage(r4, r5, r6, r7, r8, r9, r10, r11)
            return
        L_0x0066:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.IRDeviceUtil.a(android.content.Context, android.content.Intent):void");
    }

    public static String a() {
        return e() ? "xiaomi.phone_ir.v1" : "xiaomi.phone_ir.t1";
    }

    public static Device b() {
        String a2 = a();
        if (t != null && a2.equals(t.model)) {
            return t;
        }
        Device k2 = DeviceFactory.k(a2);
        k2.did = DeviceUtils.a();
        k2.canAuth = false;
        k2.setOwner(true);
        k2.location = Device.Location.LOCAL;
        k2.isOnline = true;
        t = k2;
        return t;
    }

    public static boolean a(PluginRecord pluginRecord) {
        PluginDeviceInfo c2 = pluginRecord.c();
        if (c2 == null || c2.t() != 15) {
            return false;
        }
        return true;
    }

    public static boolean a(String str) {
        return DeviceUtils.a(str);
    }

    public static void a(final HasIrDeviceCallback hasIrDeviceCallback) {
        if (hasIrDeviceCallback != null) {
            if (!c()) {
                hasIrDeviceCallback.a(false);
                return;
            }
            SystemApi.a();
            if (!SystemApi.c()) {
                hasIrDeviceCallback.a(false);
            } else {
                CoreApi.a().a(CommonApplication.getAppContext(), (CoreApi.IsPluginReadyCallback) new CoreApi.IsPluginReadyCallback() {
                    public void a() {
                        if (CoreApi.a().c(IRDeviceUtil.a())) {
                            hasIrDeviceCallback.a(true);
                        } else if (IRDeviceUtil.j()) {
                            hasIrDeviceCallback.a(true);
                        } else {
                            hasIrDeviceCallback.a(false);
                        }
                    }
                });
            }
        }
    }

    public static boolean c() {
        if (CoreApi.a().D()) {
            return false;
        }
        if (k != null) {
            return k.booleanValue();
        }
        try {
            if (Build.VERSION.SDK_INT >= 19) {
                ConsumerIrManager consumerIrManager = (ConsumerIrManager) CommonApplication.getAppContext().getSystemService("consumer_ir");
                k = Boolean.valueOf(consumerIrManager != null && consumerIrManager.hasIrEmitter());
            } else {
                k = false;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            k = false;
        }
        return k.booleanValue();
    }

    public static void a(boolean z) {
        SharePrefsManager.a(CommonApplication.getAppContext(), d, f, z ? "tianjia" : "duokan");
        f();
    }

    public static void f() {
        List<Device> d2 = SmartHomeDeviceManager.a().d();
        if (d2 != null && d2.size() > 0) {
            for (Device next : d2) {
                if ((next instanceof PhoneIRDevice) || a(next.did)) {
                    next.model = a();
                }
            }
        }
    }

    public static void a(Context context) {
        if (e()) {
            i = p();
        } else {
            j = g(context);
        }
        int c2 = c(context);
        if (u != c2) {
            u = c2;
            b(DeviceUtils.a());
        }
    }

    public static void b(String str) {
        if (str != null) {
            Intent intent = new Intent("prop_change");
            intent.putExtra("did", str);
            LocalBroadcastManager.getInstance(CommonApplication.getAppContext()).sendBroadcast(intent);
        }
    }

    public static List<IRRemoteInfo> b(Context context) {
        if (e()) {
            if (i == null) {
                i = p();
            }
            return i;
        }
        if (j == null) {
            j = g(context);
        }
        return j;
    }

    public static void a(Context context, IRDeviceListener iRDeviceListener) {
        if (e()) {
            i = p();
            iRDeviceListener.onQueryComplete(i);
            return;
        }
        c(context, iRDeviceListener);
    }

    public static int b(Context context, IRDeviceListener iRDeviceListener) {
        if (!c()) {
            return 0;
        }
        final WeakReference weakReference = new WeakReference(iRDeviceListener);
        if (e()) {
            if (i != null) {
                return i.size();
            }
            if (v.getAndSet(true)) {
                return 0;
            }
            CommonApplication.getThreadExecutor().submit(new Runnable() {
                public void run() {
                    try {
                        IRDeviceUtil.i = IRDeviceUtil.p();
                        IRDeviceListener iRDeviceListener = (IRDeviceListener) weakReference.get();
                        IRDeviceUtil.v.set(false);
                        if (iRDeviceListener != null) {
                            iRDeviceListener.onQueryComplete(IRDeviceUtil.j);
                            IRDeviceUtil.v.set(false);
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
            return 0;
        } else if (j != null) {
            return j.size();
        } else {
            if (v.getAndSet(true)) {
                return 0;
            }
            CommonApplication.getThreadExecutor().submit(new Runnable() {
                public void run() {
                    IRDeviceUtil.v.set(false);
                    try {
                        IRDeviceUtil.j = IRDeviceUtil.g(CommonApplication.getAppContext());
                        IRDeviceListener iRDeviceListener = (IRDeviceListener) weakReference.get();
                        IRDeviceUtil.v.set(false);
                        if (iRDeviceListener != null) {
                            iRDeviceListener.onQueryComplete(IRDeviceUtil.j);
                            IRDeviceUtil.v.set(false);
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
            return 0;
        }
    }

    public static int c(Context context) {
        List<IRRemoteInfo> b2;
        if (c() && (b2 = b(context)) != null && b2.size() > 0) {
            return b2.size();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public static List<IRRemoteInfo> p() {
        String string = CommonApplication.getAppContext().getSharedPreferences(e, 4).getString(c, (String) null);
        if (TextUtils.isEmpty(string)) {
            return new ArrayList();
        }
        try {
            JSONArray optJSONArray = new JSONObject(string).optJSONArray("remotes");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject jSONObject = (JSONObject) optJSONArray.get(i2);
                    IRRemoteInfo iRRemoteInfo = new IRRemoteInfo();
                    iRRemoteInfo.f14856a = jSONObject.optString("id");
                    iRRemoteInfo.b = jSONObject.optString("devicename");
                    iRRemoteInfo.c = jSONObject.optInt("type");
                    arrayList.add(iRRemoteInfo);
                }
                return arrayList;
            }
        } catch (JSONException unused) {
        }
        return new ArrayList();
    }

    public static boolean g() {
        PluginRecord d2 = CoreApi.a().d(a());
        if (d2 == null) {
            return false;
        }
        return d2.l();
    }

    /* access modifiers changed from: private */
    public static List<IRRemoteInfo> g(Context context) {
        return e(context);
    }

    private static int d(int i2, boolean z) {
        if (i2 == 8) {
            return z ? R.drawable.ir_amplifier_lock : R.drawable.ir_amplifier;
        }
        if (i2 == 100000) {
            return z ? R.drawable.ir_mi_controller_lock : R.drawable.ir_mi_controller;
        }
        switch (i2) {
            case 1:
                if (z) {
                    return R.drawable.ir_tv_lock;
                }
                return R.drawable.ir_tv;
            case 2:
                if (z) {
                    return R.drawable.ir_stb_lock;
                }
                return R.drawable.ir_stb;
            case 3:
                return z ? R.drawable.ir_air_lock : R.drawable.ir_air;
            case 4:
                return z ? R.drawable.ir_dvd_lock : R.drawable.ir_dvd;
            case 5:
                return z ? R.drawable.ir_iptv_lock : R.drawable.ir_iptv;
            case 6:
                return z ? R.drawable.ir_fan_lock : R.drawable.ir_fan;
            default:
                switch (i2) {
                    case 10:
                        return z ? R.drawable.ir_projector_lock : R.drawable.ir_projector;
                    case 11:
                        return z ? R.drawable.ir_satellite_lock : R.drawable.ir_satellite;
                    default:
                        switch (i2) {
                            case 10000:
                                return z ? R.drawable.ir_mibox_lock : R.drawable.ir_mibox;
                            case 10001:
                                return z ? R.drawable.ir_mitv_lock : R.drawable.ir_mitv;
                            default:
                                if (z) {
                                    return R.drawable.ir_others_lock;
                                }
                                return R.drawable.ir_others;
                        }
                }
        }
    }

    public static Drawable a(int i2, boolean z) {
        return CommonApplication.getAppContext().getResources().getDrawable(d(i2, z));
    }

    private static int e(int i2, boolean z) {
        if (i2 == -1) {
            return z ? R.drawable.ir_others_lock : R.drawable.ir_others;
        }
        switch (i2) {
            case 1:
                if (z) {
                    return R.drawable.ir_tv_lock;
                }
                return R.drawable.ir_tv;
            case 2:
                return z ? R.drawable.ir_air_lock : R.drawable.ir_air;
            case 3:
                return z ? R.drawable.ir_fan_lock : R.drawable.ir_fan;
            case 4:
                return z ? R.drawable.ir_projector_lock : R.drawable.ir_projector;
            case 5:
                return z ? R.drawable.ir_stb_lock : R.drawable.ir_stb;
            case 6:
                return z ? R.drawable.ir_dvd_lock : R.drawable.ir_dvd;
            case 7:
                return z ? R.drawable.ir_camera_lock : R.drawable.ir_camera;
            case 8:
                return z ? R.drawable.ir_others_lock : R.drawable.ir_others;
            case 9:
                return z ? R.drawable.ir_amplifier_lock : R.drawable.ir_amplifier;
            case 10:
                return z ? R.drawable.ir_stb_lock : R.drawable.ir_stb;
            case 11:
                return z ? R.drawable.ir_box_lock : R.drawable.ir_box;
            case 12:
                return z ? R.drawable.ir_mi_controller_lock : R.drawable.ir_mi_controller;
            default:
                if (z) {
                    return R.drawable.ir_others_lock;
                }
                return R.drawable.ir_others;
        }
    }

    public static Drawable b(int i2, boolean z) {
        return CommonApplication.getAppContext().getResources().getDrawable(e(i2, z));
    }

    public static boolean a(int i2) {
        return e() ? i2 == 12 : i2 == 10000 || i2 == 10001;
    }

    public static int a(List<IRRemoteInfo> list, int i2) {
        if (list == null || list.size() <= 0) {
            return -1;
        }
        int min = Math.min(list.size(), i2);
        for (int i3 = 0; i3 < min; i3++) {
            if (a(list.get(i3).c)) {
                return i3;
            }
        }
        return -1;
    }

    public static Drawable c(int i2, boolean z) {
        Drawable drawable;
        float f2;
        if (e()) {
            drawable = b(i2, z);
        } else {
            drawable = a(i2, z);
        }
        if (z) {
            f2 = 56.0f;
        } else {
            f2 = 53.0f;
        }
        int a2 = DisplayUtils.a(f2);
        drawable.setBounds(0, 0, a2, a2);
        return drawable;
    }

    public static Drawable b(boolean z) {
        float f2;
        Drawable a2 = a(100000, z);
        if (z) {
            f2 = 56.0f;
        } else {
            f2 = 53.0f;
        }
        int a3 = DisplayUtils.a(f2);
        a2.setBounds(0, 0, a3, a3);
        return a2;
    }

    public static boolean h() {
        return SharePrefsManager.b(CommonApplication.getAppContext(), d, b, false);
    }

    public static void i() {
        SharePrefsManager.a(CommonApplication.getAppContext(), d, b, true);
    }

    public static boolean j() {
        try {
            CommonApplication.getAppContext().getPackageManager().getPackageInfo(h, 128);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0063 A[SYNTHETIC, Splitter:B:24:0x0063] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0084 A[SYNTHETIC, Splitter:B:30:0x0084] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int d(android.content.Context r7) {
        /*
            android.content.ContentResolver r0 = r7.getContentResolver()
            r7 = 0
            r6 = 0
            android.net.Uri r1 = q     // Catch:{ Exception -> 0x004a }
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x004a }
            if (r0 == 0) goto L_0x0027
            int r1 = r0.getCount()     // Catch:{ Exception -> 0x0024, all -> 0x0021 }
            if (r1 <= 0) goto L_0x0027
            r0.moveToFirst()     // Catch:{ Exception -> 0x0024, all -> 0x0021 }
            int r1 = r0.getCount()     // Catch:{ Exception -> 0x0024, all -> 0x0021 }
            r7 = r1
            goto L_0x0027
        L_0x0021:
            r7 = move-exception
            r6 = r0
            goto L_0x0082
        L_0x0024:
            r1 = move-exception
            r6 = r0
            goto L_0x004b
        L_0x0027:
            if (r0 == 0) goto L_0x0047
            r0.close()     // Catch:{ Exception -> 0x002d }
            goto L_0x0047
        L_0x002d:
            r0 = move-exception
            r0.printStackTrace()
            java.lang.String r1 = p
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ""
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            android.util.Log.e(r1, r0)
        L_0x0047:
            return r7
        L_0x0048:
            r7 = move-exception
            goto L_0x0082
        L_0x004a:
            r1 = move-exception
        L_0x004b:
            java.lang.String r0 = p     // Catch:{ all -> 0x0048 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0048 }
            r2.<init>()     // Catch:{ all -> 0x0048 }
            java.lang.String r3 = ""
            r2.append(r3)     // Catch:{ all -> 0x0048 }
            r2.append(r1)     // Catch:{ all -> 0x0048 }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x0048 }
            android.util.Log.e(r0, r1)     // Catch:{ all -> 0x0048 }
            if (r6 == 0) goto L_0x0081
            r6.close()     // Catch:{ Exception -> 0x0067 }
            goto L_0x0081
        L_0x0067:
            r0 = move-exception
            r0.printStackTrace()
            java.lang.String r1 = p
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ""
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            android.util.Log.e(r1, r0)
        L_0x0081:
            return r7
        L_0x0082:
            if (r6 == 0) goto L_0x00a2
            r6.close()     // Catch:{ Exception -> 0x0088 }
            goto L_0x00a2
        L_0x0088:
            r0 = move-exception
            r0.printStackTrace()
            java.lang.String r1 = p
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ""
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            android.util.Log.e(r1, r0)
        L_0x00a2:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.IRDeviceUtil.d(android.content.Context):int");
    }

    public static boolean b(Context context, Intent intent) {
        if (intent == null || context.getPackageManager().resolveActivity(intent, 0) == null) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ca A[SYNTHETIC, Splitter:B:27:0x00ca] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00f2 A[SYNTHETIC, Splitter:B:35:0x00f2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.xiaomi.smarthome.device.IRRemoteInfo> e(android.content.Context r12) {
        /*
            android.content.ContentResolver r0 = r12.getContentResolver()
            r12 = 0
            android.net.Uri r1 = q     // Catch:{ Exception -> 0x00a9, all -> 0x00a4 }
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x00a9, all -> 0x00a4 }
            if (r0 == 0) goto L_0x007d
            int r1 = r0.getCount()     // Catch:{ Exception -> 0x007b }
            if (r1 <= 0) goto L_0x007d
            r0.moveToFirst()     // Catch:{ Exception -> 0x007b }
            int r12 = r0.getCount()     // Catch:{ Exception -> 0x007b }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Exception -> 0x007b }
            r1.<init>(r12)     // Catch:{ Exception -> 0x007b }
            r2 = 0
            r3 = 0
        L_0x0025:
            if (r3 >= r12) goto L_0x0079
            java.lang.String[] r4 = r     // Catch:{ Exception -> 0x007b }
            r4 = r4[r2]     // Catch:{ Exception -> 0x007b }
            int r4 = r0.getColumnIndex(r4)     // Catch:{ Exception -> 0x007b }
            int r4 = r0.getInt(r4)     // Catch:{ Exception -> 0x007b }
            java.lang.String[] r5 = r     // Catch:{ Exception -> 0x007b }
            r6 = 1
            r5 = r5[r6]     // Catch:{ Exception -> 0x007b }
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x007b }
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x007b }
            java.lang.String[] r6 = r     // Catch:{ Exception -> 0x007b }
            r7 = 2
            r6 = r6[r7]     // Catch:{ Exception -> 0x007b }
            int r6 = r0.getColumnIndex(r6)     // Catch:{ Exception -> 0x007b }
            int r6 = r0.getInt(r6)     // Catch:{ Exception -> 0x007b }
            java.lang.String[] r7 = r     // Catch:{ Exception -> 0x007b }
            r8 = 3
            r7 = r7[r8]     // Catch:{ Exception -> 0x007b }
            int r7 = r0.getColumnIndex(r7)     // Catch:{ Exception -> 0x007b }
            java.lang.String r7 = r0.getString(r7)     // Catch:{ Exception -> 0x007b }
            com.xiaomi.smarthome.device.IRRemoteInfo r8 = new com.xiaomi.smarthome.device.IRRemoteInfo     // Catch:{ Exception -> 0x007b }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x007b }
            r9.<init>()     // Catch:{ Exception -> 0x007b }
            java.lang.String r10 = ""
            r9.append(r10)     // Catch:{ Exception -> 0x007b }
            r9.append(r4)     // Catch:{ Exception -> 0x007b }
            java.lang.String r4 = r9.toString()     // Catch:{ Exception -> 0x007b }
            r8.<init>(r4, r5, r6, r7)     // Catch:{ Exception -> 0x007b }
            r1.add(r8)     // Catch:{ Exception -> 0x007b }
            r0.moveToNext()     // Catch:{ Exception -> 0x007b }
            int r3 = r3 + 1
            goto L_0x0025
        L_0x0079:
            r12 = r1
            goto L_0x007d
        L_0x007b:
            r12 = move-exception
            goto L_0x00ad
        L_0x007d:
            if (r0 == 0) goto L_0x00a3
            r0.close()     // Catch:{ Exception -> 0x0083 }
            goto L_0x00a3
        L_0x0083:
            r12 = move-exception
            r12.printStackTrace()
            java.lang.String r0 = p
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = ""
            r1.append(r2)
            r1.append(r12)
            java.lang.String r12 = r1.toString()
            android.util.Log.e(r0, r12)
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            return r12
        L_0x00a3:
            return r12
        L_0x00a4:
            r0 = move-exception
            r11 = r0
            r0 = r12
            r12 = r11
            goto L_0x00f0
        L_0x00a9:
            r0 = move-exception
            r11 = r0
            r0 = r12
            r12 = r11
        L_0x00ad:
            java.lang.String r1 = p     // Catch:{ all -> 0x00ef }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ef }
            r2.<init>()     // Catch:{ all -> 0x00ef }
            java.lang.String r3 = ""
            r2.append(r3)     // Catch:{ all -> 0x00ef }
            r2.append(r12)     // Catch:{ all -> 0x00ef }
            java.lang.String r12 = r2.toString()     // Catch:{ all -> 0x00ef }
            android.util.Log.e(r1, r12)     // Catch:{ all -> 0x00ef }
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ all -> 0x00ef }
            r12.<init>()     // Catch:{ all -> 0x00ef }
            if (r0 == 0) goto L_0x00ee
            r0.close()     // Catch:{ Exception -> 0x00ce }
            goto L_0x00ee
        L_0x00ce:
            r12 = move-exception
            r12.printStackTrace()
            java.lang.String r0 = p
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = ""
            r1.append(r2)
            r1.append(r12)
            java.lang.String r12 = r1.toString()
            android.util.Log.e(r0, r12)
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            return r12
        L_0x00ee:
            return r12
        L_0x00ef:
            r12 = move-exception
        L_0x00f0:
            if (r0 == 0) goto L_0x0116
            r0.close()     // Catch:{ Exception -> 0x00f6 }
            goto L_0x0116
        L_0x00f6:
            r12 = move-exception
            r12.printStackTrace()
            java.lang.String r0 = p
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = ""
            r1.append(r2)
            r1.append(r12)
            java.lang.String r12 = r1.toString()
            android.util.Log.e(r0, r12)
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            return r12
        L_0x0116:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.IRDeviceUtil.e(android.content.Context):java.util.List");
    }

    public static void a(Activity activity, int i2) {
        if (activity != null && i2 >= 0) {
            String str = p;
            Log.d(str, "addIrController: " + i2);
            Intent intent = new Intent(l);
            intent.putExtra(m, "mihome");
            intent.putExtra("device_type", i2);
            if (b(CommonApplication.getAppContext(), intent)) {
                activity.startActivity(intent);
            } else {
                new MLAlertDialog.Builder(activity).b(R.string.ir_hint_download_remote_controller).a(R.string.update_install, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(activity) {
                    private final /* synthetic */ Activity f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final void onClick(DialogInterface dialogInterface, int i) {
                        IRDeviceUtil.a(this.f$0, dialogInterface, i);
                    }
                }).b(R.string.cancel, (DialogInterface.OnClickListener) $$Lambda$IRDeviceUtil$CaVyA7GtTEKyTeBdhtYz5aTMoRI.INSTANCE).b().show();
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Activity activity, DialogInterface dialogInterface, int i2) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("market://details?id=com.duokan.phone.remotecontroller"));
        if (b(CommonApplication.getAppContext(), intent)) {
            activity.startActivity(intent);
        }
        dialogInterface.dismiss();
    }

    private static void c(Context context, IRDeviceListener iRDeviceListener) {
        if (s == null) {
            s = new QueryHandler(context.getContentResolver());
        } else {
            s.removeCallbacksAndMessages(1);
        }
        try {
            s.startQuery(1, iRDeviceListener, q, (String[]) null, (String) null, (String[]) null, (String) null);
        } catch (Exception e2) {
            String str = p;
            Log.e(str, "" + e2);
        }
    }

    public static void k() {
        if (s != null) {
            s.removeCallbacksAndMessages(1);
            s = null;
        }
    }

    private static final class QueryHandler extends AsyncQueryHandler {
        public QueryHandler(ContentResolver contentResolver) {
            super(contentResolver);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(int i, Object obj, Cursor cursor) {
            StringBuilder sb;
            String str;
            super.onQueryComplete(i, obj, cursor);
            if (cursor != null) {
                try {
                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        int count = cursor.getCount();
                        if (i == 1) {
                            ArrayList arrayList = new ArrayList(count);
                            for (int i2 = 0; i2 < count; i2++) {
                                int i3 = cursor.getInt(cursor.getColumnIndex(IRDeviceUtil.r[0]));
                                String string = cursor.getString(cursor.getColumnIndex(IRDeviceUtil.r[1]));
                                int i4 = cursor.getInt(cursor.getColumnIndex(IRDeviceUtil.r[2]));
                                String string2 = cursor.getString(cursor.getColumnIndex(IRDeviceUtil.r[3]));
                                arrayList.add(new IRRemoteInfo("" + i3, string, i4, string2));
                                cursor.moveToNext();
                            }
                            IRDeviceUtil.j = arrayList;
                            if (obj != null && (obj instanceof IRDeviceListener)) {
                                ((IRDeviceListener) obj).onQueryComplete(arrayList);
                            }
                        }
                    }
                } catch (Exception e) {
                    String o = IRDeviceUtil.p;
                    Log.e(o, "" + e);
                    if (cursor != null) {
                        try {
                            cursor.close();
                            return;
                        } catch (Exception e2) {
                            e = e2;
                            e.printStackTrace();
                            str = IRDeviceUtil.p;
                            sb = new StringBuilder();
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        try {
                            cursor.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            String o2 = IRDeviceUtil.p;
                            Log.e(o2, "" + e3);
                        }
                    }
                    throw th;
                }
            }
            if (cursor != null) {
                try {
                    cursor.close();
                    return;
                } catch (Exception e4) {
                    e = e4;
                    e.printStackTrace();
                    str = IRDeviceUtil.p;
                    sb = new StringBuilder();
                }
            } else {
                return;
            }
            sb.append("");
            sb.append(e);
            Log.e(str, sb.toString());
        }
    }
}
