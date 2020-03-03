package com.xiaomi.smarthome.scene.condition;

import android.app.Activity;
import android.content.Intent;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.scene.SmartHomeSceneTimerActivity;
import com.xiaomi.smarthome.scene.api.SceneApi;

public class TimerInnerCondition extends BaseInnerCondition implements LocaleGetResourceFixHelper {
    public int a() {
        return R.string.smarthome_scene_start_timer;
    }

    public int c(int i) {
        return 101;
    }

    public boolean g() {
        return false;
    }

    public TimerInnerCondition(Device device) {
        super(device);
    }

    public SceneApi.Condition a(int i, Intent intent) {
        SceneApi.Condition condition = new SceneApi.Condition();
        condition.f21522a = SceneApi.Condition.LAUNCH_TYPE.TIMER;
        condition.k = 101;
        return condition;
    }

    public int a(SceneApi.Condition condition) {
        return condition.b != null ? 0 : -1;
    }

    public int a(int i, Activity activity, SceneApi.Condition condition) {
        activity.startActivityForResult(new Intent(activity, SmartHomeSceneTimerActivity.class), 102);
        return 102;
    }

    public String e() {
        return SHApplication.getAppContext().getString(R.string.smarthome_scene_start_timer);
    }

    public void a(SimpleDraweeView simpleDraweeView) {
        simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.condition_timer_icon));
    }
}
