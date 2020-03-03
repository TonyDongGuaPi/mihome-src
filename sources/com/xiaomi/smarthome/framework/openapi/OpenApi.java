package com.xiaomi.smarthome.framework.openapi;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.family.FamilyActivity;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.update.ui.UpdateActivity;
import com.xiaomi.smarthome.homeroom.ManageDeviceRoomActivity;
import com.xiaomi.smarthome.messagecenter.ui.MessageCenterActivity;
import com.xiaomi.smarthome.miio.activity.LogActivity;
import com.xiaomi.smarthome.miio.activity.MiioActivity;
import com.xiaomi.smarthome.miio.activity.WifiLogActivity;
import com.xiaomi.smarthome.miio.activity.YeeLinkBulbActivityV2;
import com.xiaomi.smarthome.miio.miband.MiBandMainActivity;
import com.xiaomi.smarthome.miio.page.MessageCenterListActivity;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OpenApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f16685a = "com.xiaomi.smarthome.action.OPEN_API";
    public static final String b = "target_page";
    public static final String c = "target_args";
    public static final String d = "need_login";
    public static final String e = "MessageCenter";
    public static final String f = "MessageCenterList";
    public static final String g = "MiioActivity";
    public static final String h = "YeeLinkBulbActivity";
    public static final String i = "WifiLogActivity";
    public static final String j = "FamilyActivity";
    public static final String k = "BlePageActivity";
    public static final String l = "LogActivity";
    public static final String m = "SmartHomeMainActivity";
    public static final String n = "UpdateActivity";
    public static final String o = "ManageDeviceRoomActivity";
    private static Map<String, Class<?>> p = new HashMap();

    static {
        p.put(e, MessageCenterActivity.class);
        p.put(f, MessageCenterListActivity.class);
        p.put(g, MiioActivity.class);
        p.put(h, YeeLinkBulbActivityV2.class);
        p.put(i, WifiLogActivity.class);
        p.put(j, FamilyActivity.class);
        p.put(k, MiBandMainActivity.class);
        p.put(l, LogActivity.class);
        p.put("SmartHomeMainActivity", SmartHomeMainActivity.class);
        p.put(n, UpdateActivity.class);
        p.put(o, ManageDeviceRoomActivity.class);
    }

    public static boolean a(Class<?> cls) {
        return p.containsValue(cls);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r0 = r6.getStringExtra(b);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void a(android.content.Intent r6) {
        /*
            java.lang.String r0 = r6.getAction()
            java.lang.String r1 = "com.xiaomi.smarthome.action.OPEN_API"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0044
            java.lang.String r0 = "target_page"
            java.lang.String r0 = r6.getStringExtra(r0)
            java.util.Map<java.lang.String, java.lang.Class<?>> r1 = p
            java.lang.Object r1 = r1.get(r0)
            java.lang.Class r1 = (java.lang.Class) r1
            if (r1 != 0) goto L_0x001d
            return
        L_0x001d:
            java.lang.String r2 = "target_args"
            android.os.Bundle r2 = r6.getBundleExtra(r2)
            java.lang.String r3 = "need_login"
            r4 = 0
            boolean r6 = r6.getBooleanExtra(r3, r4)
            java.lang.String r3 = "LogActivity"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0041
            android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            com.xiaomi.smarthome.framework.statistic.MiStatType r3 = com.xiaomi.smarthome.framework.statistic.MiStatType.CLICK
            java.lang.String r3 = r3.getValue()
            java.lang.String r5 = "scene_log_push"
            com.umeng.analytics.MobclickAgent.a((android.content.Context) r0, (java.lang.String) r3, (java.lang.String) r5)
        L_0x0041:
            a(r1, r2, r6, r4)
        L_0x0044:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.openapi.OpenApi.a(android.content.Intent):void");
    }

    public static void a(Class<?> cls, Bundle bundle, boolean z, int i2) {
        boolean z2;
        ComponentName componentName;
        Iterator<ActivityManager.RunningTaskInfo> it = ((ActivityManager) SHApplication.getAppContext().getSystemService("activity")).getRunningTasks(50).iterator();
        while (true) {
            if (!it.hasNext()) {
                z2 = false;
                componentName = null;
                break;
            }
            ActivityManager.RunningTaskInfo next = it.next();
            if (next.baseActivity.equals(new ComponentName(SHApplication.getAppContext().getPackageName(), SmartHomeMainActivity.class.getName()))) {
                z2 = true;
                componentName = next.topActivity;
                break;
            }
        }
        if (!z2) {
            Intent intent = new Intent(SHApplication.getAppContext(), SmartHomeMainActivity.class);
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            if ((!z || CoreApi.a().q()) && cls != null && a(cls)) {
                intent.putExtra("source", 2);
                intent.putExtra("target_activity", cls);
                intent.putExtra("target_args", bundle);
                intent.putExtra(ApiConst.K, i2);
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
            }
            SHApplication.getAppContext().startActivity(intent);
        } else if (CoreApi.a().q()) {
            Intent intent2 = new Intent(SHApplication.getAppContext(), cls);
            intent2.addFlags(335675392);
            intent2.addFlags(i2);
            if (bundle != null) {
                intent2.putExtras(bundle);
            }
            SHApplication.getAppContext().startActivity(intent2);
        } else if (z) {
            Intent intent3 = new Intent();
            intent3.setComponent(componentName);
            intent3.addFlags(335675392);
            if (bundle != null) {
                intent3.putExtras(bundle);
            }
            SHApplication.getAppContext().startActivity(intent3);
        } else {
            Intent intent4 = new Intent(SHApplication.getAppContext(), cls);
            intent4.addFlags(335675392);
            intent4.addFlags(i2);
            if (bundle != null) {
                intent4.putExtras(bundle);
            }
            SHApplication.getAppContext().startActivity(intent4);
        }
    }

    public static void b(Class<?> cls, Bundle bundle, boolean z, int i2) {
        boolean z2;
        ComponentName componentName;
        Iterator<ActivityManager.RunningTaskInfo> it = ((ActivityManager) SHApplication.getAppContext().getSystemService("activity")).getRunningTasks(50).iterator();
        while (true) {
            if (!it.hasNext()) {
                z2 = false;
                componentName = null;
                break;
            }
            ActivityManager.RunningTaskInfo next = it.next();
            if (next.baseActivity.equals(new ComponentName(SHApplication.getAppContext().getPackageName(), SmartHomeMainActivity.class.getName()))) {
                z2 = true;
                componentName = next.topActivity;
                break;
            }
        }
        if (!z2) {
            Intent intent = new Intent(SHApplication.getAppContext(), SmartHomeMainActivity.class);
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            if ((!z || CoreApi.a().q()) && cls != null && a(cls)) {
                intent.putExtra("source", 2);
                intent.putExtra("target_activity", cls);
                intent.putExtra("target_args", bundle);
                intent.putExtra(ApiConst.K, i2);
            }
            SHApplication.getAppContext().startActivity(intent);
        } else if (CoreApi.a().q()) {
            Intent intent2 = new Intent(SHApplication.getAppContext(), cls);
            intent2.addFlags(872546304);
            intent2.addFlags(i2);
            if (bundle != null) {
                intent2.putExtras(bundle);
            }
            SHApplication.getAppContext().startActivity(intent2);
        } else if (z) {
            Intent intent3 = new Intent();
            intent3.setComponent(componentName);
            intent3.addFlags(335675392);
            if (bundle != null) {
                intent3.putExtras(bundle);
            }
            SHApplication.getAppContext().startActivity(intent3);
        } else {
            Intent intent4 = new Intent(SHApplication.getAppContext(), cls);
            intent4.addFlags(335675392);
            intent4.addFlags(i2);
            if (bundle != null) {
                intent4.putExtras(bundle);
            }
            SHApplication.getAppContext().startActivity(intent4);
        }
    }

    public static void a() {
        a(4);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0061  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(int r5) {
        /*
            android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            java.lang.String r1 = "activity"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0
            r1 = 50
            java.util.List r0 = r0.getRunningTasks(r1)
            if (r0 == 0) goto L_0x0043
            java.util.Iterator r0 = r0.iterator()
        L_0x0018:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0043
            java.lang.Object r1 = r0.next()
            android.app.ActivityManager$RunningTaskInfo r1 = (android.app.ActivityManager.RunningTaskInfo) r1
            android.content.ComponentName r2 = new android.content.ComponentName
            android.content.Context r3 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            java.lang.String r3 = r3.getPackageName()
            java.lang.Class<com.xiaomi.smarthome.SmartHomeMainActivity> r4 = com.xiaomi.smarthome.SmartHomeMainActivity.class
            java.lang.String r4 = r4.getName()
            r2.<init>(r3, r4)
            android.content.ComponentName r3 = r1.baseActivity
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0018
            r0 = 1
            android.content.ComponentName r1 = r1.baseActivity
            goto L_0x0045
        L_0x0043:
            r0 = 0
            r1 = 0
        L_0x0045:
            if (r0 == 0) goto L_0x0061
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            r0.setComponent(r1)
            r1 = 872415232(0x34000000, float:1.1920929E-7)
            r0.addFlags(r1)
            java.lang.String r1 = "source"
            r0.putExtra(r1, r5)
            android.content.Context r5 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            r5.startActivity(r0)
            goto L_0x0088
        L_0x0061:
            android.content.Intent r0 = new android.content.Intent
            android.content.Context r1 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            java.lang.Class<com.xiaomi.smarthome.SmartHomeMainActivity> r2 = com.xiaomi.smarthome.SmartHomeMainActivity.class
            r0.<init>(r1, r2)
            boolean r1 = com.xiaomi.smarthome.library.common.ApiHelper.g
            if (r1 == 0) goto L_0x0077
            r1 = 268468224(0x10008000, float:2.5342157E-29)
            r0.setFlags(r1)
            goto L_0x007c
        L_0x0077:
            r1 = 335544320(0x14000000, float:6.4623485E-27)
            r0.setFlags(r1)
        L_0x007c:
            java.lang.String r1 = "source"
            r0.putExtra(r1, r5)
            android.content.Context r5 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            r5.startActivity(r0)
        L_0x0088:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.openapi.OpenApi.a(int):void");
    }

    public static void a(Context context) {
        Intent intent = new Intent("xiaomi.intent.action.SHOW_SECURE_KEYGUARD");
        intent.setPackage("com.android.keyguard");
        context.sendOrderedBroadcast(intent, (String) null);
    }
}
