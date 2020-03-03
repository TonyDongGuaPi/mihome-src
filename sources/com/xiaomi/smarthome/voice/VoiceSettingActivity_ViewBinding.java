package com.xiaomi.smarthome.voice;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class VoiceSettingActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private VoiceSettingActivity f22805a;

    @UiThread
    public VoiceSettingActivity_ViewBinding(VoiceSettingActivity voiceSettingActivity) {
        this(voiceSettingActivity, voiceSettingActivity.getWindow().getDecorView());
    }

    @UiThread
    public VoiceSettingActivity_ViewBinding(VoiceSettingActivity voiceSettingActivity, View view) {
        this.f22805a = voiceSettingActivity;
        voiceSettingActivity.mAnotherNameSetting = Utils.findRequiredView(view, R.id.voice_another_name_setting, "field 'mAnotherNameSetting'");
        voiceSettingActivity.mAnoterNameDot = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_anohter_name_dot, "field 'mAnoterNameDot'", ImageView.class);
        voiceSettingActivity.mXiaoaiRoomSetting = Utils.findRequiredView(view, R.id.xiaoai_room_setting, "field 'mXiaoaiRoomSetting'");
        voiceSettingActivity.mXiaoaiRoomDot = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_xiaoai_dot, "field 'mXiaoaiRoomDot'", ImageView.class);
        voiceSettingActivity.mDeviceAuth = Utils.findRequiredView(view, R.id.device_auth, "field 'mDeviceAuth'");
        voiceSettingActivity.mDeviceAuthDot = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_device_auth_dot, "field 'mDeviceAuthDot'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        VoiceSettingActivity voiceSettingActivity = this.f22805a;
        if (voiceSettingActivity != null) {
            this.f22805a = null;
            voiceSettingActivity.mAnotherNameSetting = null;
            voiceSettingActivity.mAnoterNameDot = null;
            voiceSettingActivity.mXiaoaiRoomSetting = null;
            voiceSettingActivity.mXiaoaiRoomDot = null;
            voiceSettingActivity.mDeviceAuth = null;
            voiceSettingActivity.mDeviceAuthDot = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
