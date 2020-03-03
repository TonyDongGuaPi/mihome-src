package com.xiaomi.smarthome.scene.condition;

import android.content.Intent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.scene.api.SceneApi;

public class IntelligentTextCondition extends BaseInnerCondition implements LocaleGetResourceFixHelper {
    public String d;

    public int a() {
        return R.string.smarthome_scene_create_auto_device;
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

    public int h() {
        return R.string.smarthome_scene_create_filter;
    }

    public IntelligentTextCondition(Device device) {
        super(device);
    }

    public String e() {
        return SHApplication.getAppContext().getString(R.string.smarthome_scene_create_auto_device);
    }
}
