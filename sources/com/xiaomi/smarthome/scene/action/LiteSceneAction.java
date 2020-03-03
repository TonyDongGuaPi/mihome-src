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

public class LiteSceneAction extends BaseInnerAction implements LocaleGetResourceFixHelper {

    /* renamed from: a  reason: collision with root package name */
    private List<SceneApi.SmartHomeScene> f21503a;

    public int a() {
        return R.string.exectute_one_scene;
    }

    public int a(int i) {
        return -1;
    }

    public int a(SmartHomeSceneActionChooseActivity.InnerValueCallback innerValueCallback, Context context, String str, int i, Object obj, Object obj2) {
        return -2;
    }

    public int a(String str, Object obj, Device device) {
        return -1;
    }

    public LiteSceneAction(List<SceneApi.SmartHomeScene> list) {
        this.f21503a = list;
        this.c = new int[this.f21503a.size()];
        for (int i = 0; i < this.f21503a.size(); i++) {
            this.c[i] = i;
        }
        this.b = new String[this.f21503a.size()];
    }

    public SceneApi.Action a(String str, int i, Object obj, Intent intent) {
        SceneApi.SmartHomeScene smartHomeScene = this.f21503a.get(i);
        SceneApi.Action action = new SceneApi.Action();
        action.f21521a = 2;
        action.b = smartHomeScene.g;
        action.c = smartHomeScene.g;
        SceneApi.SHLiteScenePayload sHLiteScenePayload = new SceneApi.SHLiteScenePayload();
        sHLiteScenePayload.f21529a = smartHomeScene.f;
        action.g = sHLiteScenePayload;
        return action;
    }

    public String a(Object obj) {
        return SHApplication.getAppContext().getString(R.string.exectute_one_scene);
    }

    public int a(SceneApi.Action action, Object obj) {
        if (!(action.g instanceof SceneApi.SHLiteScenePayload)) {
            return -1;
        }
        for (int i = 0; i < this.f21503a.size(); i++) {
            if (TextUtils.equals(this.f21503a.get(i).f, ((SceneApi.SHLiteScenePayload) action.g).f21529a)) {
                return i;
            }
        }
        return -1;
    }
}
