package com.xiaomi.smarthome.light.group;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class LightGroupTestActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private LightGroupTestActivity f19177a;

    @UiThread
    public LightGroupTestActivity_ViewBinding(LightGroupTestActivity lightGroupTestActivity) {
        this(lightGroupTestActivity, lightGroupTestActivity.getWindow().getDecorView());
    }

    @UiThread
    public LightGroupTestActivity_ViewBinding(LightGroupTestActivity lightGroupTestActivity, View view) {
        this.f19177a = lightGroupTestActivity;
        lightGroupTestActivity.moduleA3ReturnBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'moduleA3ReturnBtn'", ImageView.class);
        lightGroupTestActivity.moduleA3ReturnTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'moduleA3ReturnTitle'", TextView.class);
        lightGroupTestActivity.moduleA3RightTextBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_right_text_btn, "field 'moduleA3RightTextBtn'", TextView.class);
        lightGroupTestActivity.moduleA3RightBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_right_btn, "field 'moduleA3RightBtn'", ImageView.class);
        lightGroupTestActivity.tvModify = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_modify, "field 'tvModify'", TextView.class);
        lightGroupTestActivity.shortcut = Utils.findRequiredView(view, R.id.shortcut, "field 'shortcut'");
        lightGroupTestActivity.tvNext = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_next, "field 'tvNext'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        LightGroupTestActivity lightGroupTestActivity = this.f19177a;
        if (lightGroupTestActivity != null) {
            this.f19177a = null;
            lightGroupTestActivity.moduleA3ReturnBtn = null;
            lightGroupTestActivity.moduleA3ReturnTitle = null;
            lightGroupTestActivity.moduleA3RightTextBtn = null;
            lightGroupTestActivity.moduleA3RightBtn = null;
            lightGroupTestActivity.tvModify = null;
            lightGroupTestActivity.shortcut = null;
            lightGroupTestActivity.tvNext = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
