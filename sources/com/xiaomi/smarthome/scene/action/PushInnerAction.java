package com.xiaomi.smarthome.scene.action;

import android.content.Intent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.condition.LocaleGetResourceFixHelper;
import java.util.Locale;

public class PushInnerAction extends BaseInnerAction implements LocaleGetResourceFixHelper {
    public int a() {
        return R.string.smarthome_scene_push_action;
    }

    public int a(int i) {
        return 0;
    }

    public int a(String str, Object obj, Device device) {
        return -1;
    }

    public SceneApi.Action a(String str, int i, Object obj, Intent intent) {
        SceneApi.Action action = new SceneApi.Action();
        action.f21521a = 1;
        action.b = LanguageUtil.a((Locale) null, (int) R.string.smarthome_scene_push_action);
        action.c = str;
        action.e = "phone";
        SceneApi.SHScenePushPayload sHScenePushPayload = new SceneApi.SHScenePushPayload();
        sHScenePushPayload.f21532a = LanguageUtil.a((Locale) null, (int) R.string.smarthome_scene_push_title);
        sHScenePushPayload.c = action.e + ".push";
        action.g = sHScenePushPayload;
        return action;
    }

    public String a(Object obj) {
        return LanguageUtil.a((Locale) null, (int) R.string.smarthome_scene_push_action);
    }

    public int a(SceneApi.Action action, Object obj) {
        return action.f21521a == 1 ? 0 : -1;
    }
}
