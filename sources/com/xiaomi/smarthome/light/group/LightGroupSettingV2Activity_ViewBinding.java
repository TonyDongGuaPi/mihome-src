package com.xiaomi.smarthome.light.group;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;

public class LightGroupSettingV2Activity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private LightGroupSettingV2Activity f19170a;

    @UiThread
    public LightGroupSettingV2Activity_ViewBinding(LightGroupSettingV2Activity lightGroupSettingV2Activity) {
        this(lightGroupSettingV2Activity, lightGroupSettingV2Activity.getWindow().getDecorView());
    }

    @UiThread
    public LightGroupSettingV2Activity_ViewBinding(LightGroupSettingV2Activity lightGroupSettingV2Activity, View view) {
        this.f19170a = lightGroupSettingV2Activity;
        lightGroupSettingV2Activity.divider = Utils.findRequiredView(view, R.id.divider, "field 'divider'");
        lightGroupSettingV2Activity.next = (TextView) Utils.findRequiredViewAsType(view, R.id.create_button, "field 'next'", TextView.class);
        lightGroupSettingV2Activity.bgLight = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.bg_light, "field 'bgLight'", SimpleDraweeView.class);
        lightGroupSettingV2Activity.recyclerview = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recyclerview, "field 'recyclerview'", RecyclerView.class);
        lightGroupSettingV2Activity.moduleA3ReturnBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'moduleA3ReturnBtn'", ImageView.class);
        lightGroupSettingV2Activity.moduleA3ReturnTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'moduleA3ReturnTitle'", TextView.class);
        lightGroupSettingV2Activity.moduleA3RightTextBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_right_text_btn, "field 'moduleA3RightTextBtn'", TextView.class);
        lightGroupSettingV2Activity.moduleA3RightBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_right_btn, "field 'moduleA3RightBtn'", ImageView.class);
        lightGroupSettingV2Activity.funcTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.title_left, "field 'funcTitle'", TextView.class);
        lightGroupSettingV2Activity.iconOn = (TextView) Utils.findRequiredViewAsType(view, R.id.icon_on, "field 'iconOn'", TextView.class);
        lightGroupSettingV2Activity.iconBright = (TextView) Utils.findRequiredViewAsType(view, R.id.icon_bright, "field 'iconBright'", TextView.class);
        lightGroupSettingV2Activity.iconColorTemperature = (TextView) Utils.findRequiredViewAsType(view, R.id.icon_color_temperature, "field 'iconColorTemperature'", TextView.class);
        lightGroupSettingV2Activity.iconColor = (TextView) Utils.findRequiredViewAsType(view, R.id.icon_color, "field 'iconColor'", TextView.class);
        lightGroupSettingV2Activity.tvLightGroupTop1 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_light_group_top_1, "field 'tvLightGroupTop1'", TextView.class);
        lightGroupSettingV2Activity.tvLightGroupTop2 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_light_group_top_2, "field 'tvLightGroupTop2'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        LightGroupSettingV2Activity lightGroupSettingV2Activity = this.f19170a;
        if (lightGroupSettingV2Activity != null) {
            this.f19170a = null;
            lightGroupSettingV2Activity.divider = null;
            lightGroupSettingV2Activity.next = null;
            lightGroupSettingV2Activity.bgLight = null;
            lightGroupSettingV2Activity.recyclerview = null;
            lightGroupSettingV2Activity.moduleA3ReturnBtn = null;
            lightGroupSettingV2Activity.moduleA3ReturnTitle = null;
            lightGroupSettingV2Activity.moduleA3RightTextBtn = null;
            lightGroupSettingV2Activity.moduleA3RightBtn = null;
            lightGroupSettingV2Activity.funcTitle = null;
            lightGroupSettingV2Activity.iconOn = null;
            lightGroupSettingV2Activity.iconBright = null;
            lightGroupSettingV2Activity.iconColorTemperature = null;
            lightGroupSettingV2Activity.iconColor = null;
            lightGroupSettingV2Activity.tvLightGroupTop1 = null;
            lightGroupSettingV2Activity.tvLightGroupTop2 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
