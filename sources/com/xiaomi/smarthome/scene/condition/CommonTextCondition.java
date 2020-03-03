package com.xiaomi.smarthome.scene.condition;

import android.content.Intent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.scene.api.SceneApi;

public class CommonTextCondition extends BaseInnerCondition implements LocaleGetResourceFixHelper {
    public int a() {
        return R.string.smarthome_scene_create_common;
    }

    public int a(SceneApi.Condition condition) {
        return 0;
    }

    public SceneApi.Condition a(int i, Intent intent) {
        return null;
    }

    public int c(int i) {
        return 0;
    }

    public CommonTextCondition(Device device) {
        super(device);
    }

    public String e() {
        return SHApplication.getAppContext().getString(R.string.smarthome_scene_create_common);
    }
}
