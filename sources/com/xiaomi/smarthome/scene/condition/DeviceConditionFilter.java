package com.xiaomi.smarthome.scene.condition;

import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.scene.api.SceneApi;

public class DeviceConditionFilter extends BaseInnerCondition implements LocaleGetResourceFixHelper {
    private String d;

    public int a() {
        return R.string.smarthome_scene_all;
    }

    public int a(SceneApi.Condition condition) {
        return -1;
    }

    public SceneApi.Condition a(int i, Intent intent) {
        return null;
    }

    public int c(int i) {
        return 0;
    }

    public DeviceConditionFilter() {
        super((Device) null);
    }

    public String e() {
        return SHApplication.getAppContext().getString(R.string.smarthome_scene_all);
    }

    public void a(String str) {
        this.d = str;
    }

    public String h() {
        if (TextUtils.isEmpty(this.d)) {
            return SHApplication.getAppContext().getString(R.string.smarthome_scene_all);
        }
        return this.d;
    }
}
