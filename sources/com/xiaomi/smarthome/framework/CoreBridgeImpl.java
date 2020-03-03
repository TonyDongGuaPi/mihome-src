package com.xiaomi.smarthome.framework;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.CoreBridge;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.framework.page.DevelopSharePreManager;
import com.xiaomi.smarthome.framework.page.rndebug.RnDebugConstant;
import com.xiaomi.smarthome.framework.page.rndebug.RnDebugFileUtil;
import com.xiaomi.smarthome.framework.plugin.rn.LoadingRNActivity;
import com.xiaomi.smarthome.framework.plugin.rn.RNCameraFrameManager;
import com.xiaomi.smarthome.framework.plugin.rn.RNRuntime;
import org.json.JSONObject;

public class CoreBridgeImpl extends CoreBridge {
    public static void f() {
        f1509a = new CoreBridgeImpl();
    }

    public void a(DeviceStat deviceStat, PluginRecord pluginRecord, Bundle bundle) {
        RNRuntime.a().a(deviceStat, pluginRecord, bundle);
    }

    public void b() {
        RNRuntime.a().d();
    }

    public byte c() {
        return RNRuntime.a().c();
    }

    public Class d() {
        return LoadingRNActivity.class;
    }

    public boolean a(String str) {
        JSONObject a2;
        if (!TextUtils.isEmpty(str) && DevelopSharePreManager.a().g() && (a2 = RnDebugFileUtil.a(str)) != null && a2.optBoolean(RnDebugConstant.c)) {
            return true;
        }
        return false;
    }

    public void e() {
        RNRuntime.a().j();
    }

    public void a(DeviceStat deviceStat) {
        RNCameraFrameManager.a().b((XmPluginPackage) null, deviceStat);
    }

    public void a(DeviceStat deviceStat, Intent intent) {
        RNCameraFrameManager.a().a((XmPluginPackage) null, deviceStat, intent);
    }

    public void b(DeviceStat deviceStat) {
        RNCameraFrameManager.a().a((XmPluginPackage) null, deviceStat);
    }

    public void c(DeviceStat deviceStat) {
        RNCameraFrameManager.a().c((XmPluginPackage) null, deviceStat);
    }
}
