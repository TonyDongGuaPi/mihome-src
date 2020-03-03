package com.xiaomi.smarthome.framework.plugin.rn.nativemodule;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import com.coloros.mcssdk.PushManager;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.utils.DeviceLauncher2;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.framework.plugin.rn.RNRuntime;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class MIOTPackageModule extends MIOTBaseJavaModule {
    public static final String EVENTRANDOM = "_36621";
    public static final String EXTRA_NOFITY = "extra";
    private static final int PAGE_CHANGE = 1;
    private static List<WeakReference<ReactInstanceManager>> ReactInstanceRef = new ArrayList(1);
    public static final String VALUE_SCENE = "value";
    private final boolean isDebug;
    private boolean mIsFirstReportLoadTime = true;
    private boolean mIsFirstReportOpenTime = true;
    private boolean mIsFirstReportRenderTime = true;

    public String getName() {
        return "MIOTPackage";
    }

    public MIOTPackageModule(ReactApplicationContext reactApplicationContext, boolean z) {
        super(reactApplicationContext);
        this.isDebug = z;
        ReactInstanceRef.clear();
    }

    public Map<String, Object> getConstants() {
        PluginRecord pluginRecord = getPluginRecord();
        HashMap hashMap = new HashMap();
        if (pluginRecord != null) {
            PluginPackageInfo i = pluginRecord.i();
            hashMap.put("pluginID", Long.valueOf(pluginRecord.d()));
            hashMap.put("packageID", Long.valueOf(pluginRecord.e()));
            hashMap.put("packageName", pluginRecord.E());
            if (i == null) {
                i = pluginRecord.h();
            }
            if (i != null) {
                hashMap.put("version", Integer.valueOf(i.g()));
                String a2 = MIOTUtils.a(i.m(), "|");
                if (a2.isEmpty()) {
                    a2 = pluginRecord.o();
                }
                hashMap.put("models", a2);
            }
        }
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        WritableMap s = RNRuntime.a().s();
        int d = MIOTUtils.d(s, "package_msgType");
        if (d == 3) {
            createMap.putString("entrance", PageUrl.j);
            createMap2.putMap(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, s);
        } else if (d == 1) {
            createMap.putString("entrance", "main");
            createMap2.putString("extra", MIOTUtils.a((ReadableMap) s, "extra"));
        } else if (d == 2) {
            createMap.putString("entrance", "main");
            addAppPushMessageInfo(RNRuntime.a().i(), createMap2);
        } else {
            createMap.putString("entrance", "main");
            Log.e("MIOTPackageModule", "RN init activity is null");
        }
        Bundle i2 = RNRuntime.a().i();
        if (i2 != null) {
            String string = i2.getString("pageName", "");
            String string2 = i2.getString("pageParams", new JSONObject().toString());
            if (!TextUtils.isEmpty(string)) {
                createMap.putString("entrance", string);
            }
            createMap2.putString("pageParams", string2);
        }
        createMap.putMap("info", createMap2);
        hashMap.put("entryInfo", createMap);
        hashMap.put("buildType", this.isDebug ? "debug" : "release");
        hashMap.put("basePath", RNRuntime.a().g());
        hashMap.put("plugPath", RNRuntime.a().h());
        hashMap.put("localFilePath", getFilesPath().getAbsolutePath());
        hashMap.put("eventRandom", EVENTRANDOM);
        LogUtil.c("PluginStartTime", "ReactNativeGetConstants  " + System.currentTimeMillis());
        return hashMap;
    }

    private void addAppPushMessageInfo(Bundle bundle, WritableMap writableMap) {
        if (bundle != null && writableMap != null) {
            for (String str : bundle.keySet()) {
                try {
                    writableMap.putString(str, bundle.get(str).toString());
                } catch (Exception e) {
                    RnPluginLog.b("MIOTPackageModule addAppPushMessageInfo-->" + e.toString());
                }
            }
        }
    }

    @ReactMethod
    public final void setExitInfo(String str, Dynamic dynamic) {
        RNRuntime.a().a(str, dynamic);
    }

    public static final List getReactInstanceHolderForDetox() {
        return ReactInstanceRef;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0141  */
    /* JADX WARNING: Removed duplicated region for block: B:66:? A[RETURN, SYNTHETIC] */
    @com.facebook.react.bridge.ReactMethod
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onPackageLifecycle(java.lang.String r11, java.lang.String r12) {
        /*
            r10 = this;
            com.xiaomi.smarthome.framework.plugin.rn.RNPluginReportTime r12 = com.xiaomi.smarthome.framework.plugin.rn.RNPluginReportTime.a()
            boolean r12 = r12.e()
            int r0 = r11.hashCode()
            r1 = -1794592921(0xffffffff9508af67, float:-2.760335E-26)
            r2 = 0
            if (r0 == r1) goto L_0x0031
            r1 = -176570662(0xfffffffff579beda, float:-3.1659005E32)
            if (r0 == r1) goto L_0x0027
            r1 = 447167726(0x1aa73cee, float:6.916802E-23)
            if (r0 == r1) goto L_0x001d
            goto L_0x003b
        L_0x001d:
            java.lang.String r0 = "willUnmount"
            boolean r11 = r11.equals(r0)
            if (r11 == 0) goto L_0x003b
            r11 = 2
            goto L_0x003c
        L_0x0027:
            java.lang.String r0 = "didMount"
            boolean r11 = r11.equals(r0)
            if (r11 == 0) goto L_0x003b
            r11 = 1
            goto L_0x003c
        L_0x0031:
            java.lang.String r0 = "willMount"
            boolean r11 = r11.equals(r0)
            if (r11 == 0) goto L_0x003b
            r11 = 0
            goto L_0x003c
        L_0x003b:
            r11 = -1
        L_0x003c:
            r0 = 10000(0x2710, double:4.9407E-320)
            r3 = 0
            switch(r11) {
                case 0: goto L_0x0141;
                case 1: goto L_0x004c;
                case 2: goto L_0x0045;
                default: goto L_0x0043;
            }
        L_0x0043:
            goto L_0x01c4
        L_0x0045:
            java.util.List<java.lang.ref.WeakReference<com.facebook.react.ReactInstanceManager>> r11 = ReactInstanceRef
            r11.clear()
            goto L_0x01c4
        L_0x004c:
            boolean r11 = com.xiaomi.smarthome.setting.PluginSetting.a()
            if (r11 == 0) goto L_0x006d
            boolean r11 = com.xiaomi.smarthome.globalsetting.GlobalSetting.t
            if (r11 == 0) goto L_0x006d
            java.util.List<java.lang.ref.WeakReference<com.facebook.react.ReactInstanceManager>> r11 = ReactInstanceRef
            r11.clear()
            java.util.List<java.lang.ref.WeakReference<com.facebook.react.ReactInstanceManager>> r11 = ReactInstanceRef
            java.lang.ref.WeakReference r5 = new java.lang.ref.WeakReference
            com.xiaomi.smarthome.framework.plugin.rn.RNRuntime r6 = com.xiaomi.smarthome.framework.plugin.rn.RNRuntime.a()
            com.facebook.react.ReactInstanceManager r6 = r6.l()
            r5.<init>(r6)
            r11.add(r5)
        L_0x006d:
            java.lang.String r11 = "plugin componentDidMount"
            com.xiaomi.smarthome.setting.PluginSetting.a((java.lang.String) r11)
            com.xiaomi.smarthome.setting.PluginSetting.c()
            com.xiaomi.smarthome.device.api.DeviceStat r11 = r10.getDevice()
            java.lang.String r5 = r10.getStatDevloperLabel()
            com.xiaomi.smarthome.framework.plugin.rn.RNPluginReportTime r6 = com.xiaomi.smarthome.framework.plugin.rn.RNPluginReportTime.a()
            long r6 = r6.d()
            if (r11 == 0) goto L_0x0131
            boolean r11 = android.text.TextUtils.isEmpty(r5)
            if (r11 != 0) goto L_0x0131
            int r11 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r11 <= 0) goto L_0x0131
            long r8 = java.lang.System.currentTimeMillis()
            long r8 = r8 - r6
            long r6 = java.lang.Math.abs(r8)
            java.lang.String r11 = "statistic-time"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "statistic time:  rn plugin render time is "
            r8.append(r9)
            r8.append(r6)
            java.lang.String r9 = " and mIsFirstReportRenderTime is "
            r8.append(r9)
            boolean r9 = r10.mIsFirstReportRenderTime
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            android.util.Log.i(r11, r8)
            int r11 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r11 <= 0) goto L_0x00dd
            int r11 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r11 >= 0) goto L_0x00dd
            if (r12 == 0) goto L_0x00dd
            boolean r11 = r10.mIsFirstReportRenderTime
            if (r11 == 0) goto L_0x00d4
            r10.mIsFirstReportRenderTime = r2
            com.xiaomi.smarthome.device.api.DeviceStat r11 = r10.getDevice()
            java.lang.String r11 = r11.model
            com.xiaomi.smarthome.stat.PluginStatReporter.f(r5, r6, r11)
            goto L_0x00dd
        L_0x00d4:
            com.xiaomi.smarthome.device.api.DeviceStat r11 = r10.getDevice()
            java.lang.String r11 = r11.model
            com.xiaomi.smarthome.stat.PluginStatReporter.f(r5, r6, r11)
        L_0x00dd:
            long r6 = java.lang.System.currentTimeMillis()
            com.xiaomi.smarthome.framework.plugin.rn.RNPluginReportTime r11 = com.xiaomi.smarthome.framework.plugin.rn.RNPluginReportTime.a()
            long r8 = r11.b()
            long r6 = r6 - r8
            long r6 = java.lang.Math.abs(r6)
            java.lang.String r11 = "statistic-time"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "statistic time:  rn plugin open time is "
            r8.append(r9)
            r8.append(r6)
            java.lang.String r9 = " and mIsFirstReportOpenTime is "
            r8.append(r9)
            boolean r9 = r10.mIsFirstReportOpenTime
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            android.util.Log.i(r11, r8)
            int r11 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r11 <= 0) goto L_0x0131
            int r11 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r11 >= 0) goto L_0x0131
            if (r12 == 0) goto L_0x0131
            boolean r11 = r10.mIsFirstReportOpenTime
            if (r11 == 0) goto L_0x0128
            r10.mIsFirstReportOpenTime = r2
            com.xiaomi.smarthome.device.api.DeviceStat r11 = r10.getDevice()
            java.lang.String r11 = r11.model
            com.xiaomi.smarthome.stat.PluginStatReporter.b(r5, r6, r11)
            goto L_0x0131
        L_0x0128:
            com.xiaomi.smarthome.device.api.DeviceStat r11 = r10.getDevice()
            java.lang.String r11 = r11.model
            com.xiaomi.smarthome.stat.PluginStatReporter.b(r5, r6, r11)
        L_0x0131:
            com.xiaomi.smarthome.framework.plugin.rn.RNPluginReportTime r11 = com.xiaomi.smarthome.framework.plugin.rn.RNPluginReportTime.a()
            r11.c(r3)
            com.xiaomi.smarthome.framework.plugin.rn.RNPluginReportTime r11 = com.xiaomi.smarthome.framework.plugin.rn.RNPluginReportTime.a()
            r11.a((long) r3)
            goto L_0x01c4
        L_0x0141:
            java.lang.String r11 = "plugin componentWillMount"
            com.xiaomi.smarthome.setting.PluginSetting.a((java.lang.String) r11)
            com.xiaomi.smarthome.device.api.DeviceStat r11 = r10.getDevice()
            java.lang.String r5 = r10.getStatDevloperLabel()
            if (r11 == 0) goto L_0x01b2
            boolean r11 = android.text.TextUtils.isEmpty(r5)
            if (r11 != 0) goto L_0x01b2
            long r6 = java.lang.System.currentTimeMillis()
            com.xiaomi.smarthome.framework.plugin.rn.RNPluginReportTime r11 = com.xiaomi.smarthome.framework.plugin.rn.RNPluginReportTime.a()
            long r8 = r11.c()
            long r6 = r6 - r8
            long r6 = java.lang.Math.abs(r6)
            java.lang.String r11 = "statistic-time"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "statistic time:  rn plugin load time is "
            r8.append(r9)
            r8.append(r6)
            java.lang.String r9 = " and mIsFirstReportLoadTime is "
            r8.append(r9)
            boolean r9 = r10.mIsFirstReportLoadTime
            r8.append(r9)
            java.lang.String r9 = "  shouldReportTime: "
            r8.append(r9)
            r8.append(r12)
            java.lang.String r8 = r8.toString()
            android.util.Log.i(r11, r8)
            int r11 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r11 <= 0) goto L_0x01b2
            int r11 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r11 >= 0) goto L_0x01b2
            if (r12 == 0) goto L_0x01b2
            boolean r11 = r10.mIsFirstReportLoadTime
            if (r11 == 0) goto L_0x01a9
            r10.mIsFirstReportLoadTime = r2
            com.xiaomi.smarthome.device.api.DeviceStat r11 = r10.getDevice()
            java.lang.String r11 = r11.model
            com.xiaomi.smarthome.stat.PluginStatReporter.d(r5, r6, r11)
            goto L_0x01b2
        L_0x01a9:
            com.xiaomi.smarthome.device.api.DeviceStat r11 = r10.getDevice()
            java.lang.String r11 = r11.model
            com.xiaomi.smarthome.stat.PluginStatReporter.d(r5, r6, r11)
        L_0x01b2:
            com.xiaomi.smarthome.framework.plugin.rn.RNPluginReportTime r11 = com.xiaomi.smarthome.framework.plugin.rn.RNPluginReportTime.a()
            r11.b(r3)
            com.xiaomi.smarthome.framework.plugin.rn.RNPluginReportTime r11 = com.xiaomi.smarthome.framework.plugin.rn.RNPluginReportTime.a()
            long r0 = java.lang.System.currentTimeMillis()
            r11.c(r0)
        L_0x01c4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTPackageModule.onPackageLifecycle(java.lang.String, java.lang.String):void");
    }

    @ReactMethod
    public final void notifyCation(String str, String str2, String str3) {
        DeviceStat device = getDevice();
        Intent intent = new Intent(ApiConst.f16684a);
        intent.setComponent(new ComponentName(SHApplication.getAppContext().getPackageName(), DeviceLauncher2.class.getName()));
        intent.putExtra("device_mac", device.mac);
        intent.putExtra("device_id", device.did);
        intent.putExtra(ApiConst.i, "short_cut");
        intent.putExtra(ApiConst.f, device.model);
        intent.putExtra("timestamp", System.currentTimeMillis());
        intent.putExtra(ApiConst.n, true);
        intent.putExtra("extra", str3);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(SHApplication.getAppContext());
        builder.setContentTitle(str);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setWhen(System.currentTimeMillis());
        builder.setAutoCancel(true);
        PendingIntent activity = PendingIntent.getActivity(SHApplication.getAppContext(), R.string.app_name, intent, 134217728);
        builder.setContentText(str2);
        builder.setContentIntent(activity);
        NotificationManager notificationManager = (NotificationManager) SHApplication.getAppContext().getSystemService(PushManager.MESSAGE_TYPE_NOTI);
        if (notificationManager != null) {
            notificationManager.notify(device.did.hashCode(), builder.build());
        }
    }

    @ReactMethod
    public void entry(Callback callback) {
        callback.invoke(new Object[0]);
    }

    @ReactMethod
    public void exit(boolean z, Callback callback) {
        callback.invoke(true);
    }

    @ReactMethod
    public void onDeventJs(int i, ReadableMap readableMap) {
        if (i == 1) {
            int d = MIOTUtils.d(readableMap, "routeIndex");
            String a2 = MIOTUtils.a(readableMap, "event");
            if ("show".equals(a2)) {
                RNRuntime.a().a(d);
            } else if ("hide".equals(a2)) {
                RNRuntime.a().b(d);
            }
        }
    }
}
