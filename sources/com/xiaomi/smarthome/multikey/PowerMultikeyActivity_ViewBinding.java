package com.xiaomi.smarthome.multikey;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class PowerMultikeyActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private PowerMultikeyActivity f20184a;

    @UiThread
    public PowerMultikeyActivity_ViewBinding(PowerMultikeyActivity powerMultikeyActivity) {
        this(powerMultikeyActivity, powerMultikeyActivity.getWindow().getDecorView());
    }

    @UiThread
    public PowerMultikeyActivity_ViewBinding(PowerMultikeyActivity powerMultikeyActivity, View view) {
        this.f20184a = powerMultikeyActivity;
        powerMultikeyActivity.mReturnBtn = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'mReturnBtn'");
        powerMultikeyActivity.mTitleTv = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitleTv'", TextView.class);
        powerMultikeyActivity.mRightImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_right_iv_setting_btn, "field 'mRightImage'", ImageView.class);
        powerMultikeyActivity.mSkipTv = (TextView) Utils.findRequiredViewAsType(view, R.id.skip, "field 'mSkipTv'", TextView.class);
        powerMultikeyActivity.recycle_view = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycle_view, "field 'recycle_view'", RecyclerView.class);
        powerMultikeyActivity.titleBar = Utils.findRequiredView(view, R.id.title_bar, "field 'titleBar'");
        powerMultikeyActivity.step = Utils.findRequiredView(view, R.id.step, "field 'step'");
    }

    @CallSuper
    public void unbind() {
        PowerMultikeyActivity powerMultikeyActivity = this.f20184a;
        if (powerMultikeyActivity != null) {
            this.f20184a = null;
            powerMultikeyActivity.mReturnBtn = null;
            powerMultikeyActivity.mTitleTv = null;
            powerMultikeyActivity.mRightImage = null;
            powerMultikeyActivity.mSkipTv = null;
            powerMultikeyActivity.recycle_view = null;
            powerMultikeyActivity.titleBar = null;
            powerMultikeyActivity.step = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
