package com.xiaomi.smarthome.scene.action;

import android.content.Context;
import android.content.Intent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.scene.SmartHomeSceneActionChooseActivity;
import com.xiaomi.smarthome.scene.SmartHomeSceneTimerDelay;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.condition.LocaleGetResourceFixHelper;

public class DelayInnerAction extends BaseInnerAction implements LocaleGetResourceFixHelper {
    public int a() {
        return R.string.smarthome_scene_delay;
    }

    public int a(int i) {
        return 0;
    }

    public int a(String str, Object obj, Device device) {
        return -1;
    }

    public DelayInnerAction() {
        this.c = new int[1];
        this.b = new String[1];
    }

    public SceneApi.Action a(String str, int i, Object obj, Intent intent) {
        SceneApi.Action action = new SceneApi.Action();
        action.f21521a = 2;
        action.b = SHApplication.getAppContext().getString(R.string.smarthome_scene_delay);
        action.c = str;
        action.e = "delay";
        SceneApi.SHSceneDelayPayload sHSceneDelayPayload = new SceneApi.SHSceneDelayPayload();
        if (intent != null) {
            sHSceneDelayPayload.g = intent.getIntExtra("value", 0);
        }
        sHSceneDelayPayload.c = action.e + ".delay";
        action.g = sHSceneDelayPayload;
        return action;
    }

    public String a(Object obj) {
        return SHApplication.getAppContext().getString(R.string.smarthome_scene_delay);
    }

    public int a(SceneApi.Action action, Object obj) {
        return action.f21521a == 1 ? 0 : -1;
    }

    public int a(SmartHomeSceneActionChooseActivity.InnerValueCallback innerValueCallback, Context context, String str, int i, Object obj, Object obj2) {
        innerValueCallback.a(new Intent(context, SmartHomeSceneTimerDelay.class), 102);
        return 102;
    }
}
