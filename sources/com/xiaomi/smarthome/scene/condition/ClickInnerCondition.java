package com.xiaomi.smarthome.scene.condition;

import android.content.Intent;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.scene.api.SceneApi;

public class ClickInnerCondition extends BaseInnerCondition implements LocaleGetResourceFixHelper {
    public int a() {
        return R.string.smarthome_scene_click_start;
    }

    public int c(int i) {
        return 101;
    }

    public boolean g() {
        return false;
    }

    public ClickInnerCondition(Device device) {
        super(device);
    }

    public SceneApi.Condition a(int i, Intent intent) {
        SceneApi.Condition condition = new SceneApi.Condition();
        condition.k = 101;
        condition.f21522a = SceneApi.Condition.LAUNCH_TYPE.CLICK;
        return condition;
    }

    public int a(SceneApi.Condition condition) {
        return condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.CLICK ? 0 : -1;
    }

    public String e() {
        return SHApplication.getAppContext().getString(R.string.smarthome_scene_click_start);
    }

    public void a(SimpleDraweeView simpleDraweeView) {
        simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.scene_click_lite_scene_icon));
    }
}
