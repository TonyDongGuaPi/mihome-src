package com.xiaomi.smarthome.scene.action;

import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.condition.LocaleGetResourceFixHelper;

public class DeviceActionFilter extends BaseInnerAction implements LocaleGetResourceFixHelper {

    /* renamed from: a  reason: collision with root package name */
    private String f21496a;

    public int a() {
        return R.string.smarthome_scene_all;
    }

    public int a(int i) {
        return 0;
    }

    public int a(SceneApi.Action action, Object obj) {
        return 0;
    }

    public int a(String str, Object obj, Device device) {
        return 0;
    }

    public SceneApi.Action a(String str, int i, Object obj, Intent intent) {
        return null;
    }

    public String a(Object obj) {
        return SHApplication.getAppContext().getString(R.string.smarthome_scene_all);
    }

    public void a(String str) {
        this.f21496a = str;
    }

    public String f() {
        if (TextUtils.isEmpty(this.f21496a)) {
            return SHApplication.getAppContext().getString(R.string.smarthome_scene_all);
        }
        return this.f21496a;
    }
}
