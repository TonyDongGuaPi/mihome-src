package com.xiaomi.smarthome.scene.action;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.scene.SmartHomeSceneActionChooseActivity;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.condition.LocaleGetResourceFixHelper;
import java.util.List;

public class AutoSceneAction extends BaseInnerAction implements LocaleGetResourceFixHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String f21495a = "enable";
    private List<SceneApi.SmartHomeScene> f;

    public int a() {
        return R.string.control_scene_action;
    }

    public int a(int i) {
        return -1;
    }

    public int a(SmartHomeSceneActionChooseActivity.InnerValueCallback innerValueCallback, Context context, String str, int i, Object obj, Object obj2) {
        return -3;
    }

    public int a(String str, Object obj, Device device) {
        return -1;
    }

    public AutoSceneAction(List<SceneApi.SmartHomeScene> list) {
        this.f = list;
        this.c = new int[this.f.size()];
        for (int i = 0; i < this.f.size(); i++) {
            this.c[i] = i;
        }
        this.b = new String[this.f.size()];
    }

    public SceneApi.Action a(String str, int i, Object obj, Intent intent) {
        String str2;
        if (i >= this.f.size()) {
            return new SceneApi.Action();
        }
        SceneApi.SmartHomeScene smartHomeScene = this.f.get(i);
        SceneApi.Action action = new SceneApi.Action();
        action.f21521a = 3;
        action.b = smartHomeScene.g;
        action.e = "";
        SceneApi.SHSceneAutoPayload sHSceneAutoPayload = new SceneApi.SHSceneAutoPayload();
        sHSceneAutoPayload.f21530a = smartHomeScene.f;
        int i2 = -1;
        if (intent != null && intent.hasExtra(f21495a)) {
            i2 = intent.getIntExtra(f21495a, -1);
        }
        sHSceneAutoPayload.b = i2;
        action.g = sHSceneAutoPayload;
        if (i2 == 1) {
            str2 = SHApplication.getAppContext().getResources().getString(R.string.scene_open);
        } else {
            str2 = SHApplication.getAppContext().getResources().getString(R.string.scene_close);
        }
        action.c = str2;
        return action;
    }

    public String a(Object obj) {
        return SHApplication.getAppContext().getString(R.string.control_scene_action);
    }

    public int a(SceneApi.Action action, Object obj) {
        if (!(action.g instanceof SceneApi.SHSceneAutoPayload)) {
            return -1;
        }
        for (int i = 0; i < this.f.size(); i++) {
            if (TextUtils.equals(this.f.get(i).f, ((SceneApi.SHSceneAutoPayload) action.g).f21530a)) {
                return i;
            }
        }
        return -1;
    }
}
