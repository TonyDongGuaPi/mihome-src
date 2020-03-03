package com.mi.global.shop.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.widget.CustomEditTextView;
import com.mi.global.shop.widget.XEditText;

public class GSTActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private GSTActivity f5382a;

    @UiThread
    public GSTActivity_ViewBinding(GSTActivity gSTActivity) {
        this(gSTActivity, gSTActivity.getWindow().getDecorView());
    }

    @UiThread
    public GSTActivity_ViewBinding(GSTActivity gSTActivity, View view) {
        this.f5382a = gSTActivity;
        gSTActivity.ed_gst = (XEditText) Utils.findRequiredViewAsType(view, R.id.ed_gst, "field 'ed_gst'", XEditText.class);
        gSTActivity.bg_gst = (CustomEditTextView) Utils.findRequiredViewAsType(view, R.id.bg_gst, "field 'bg_gst'", CustomEditTextView.class);
        gSTActivity.bt_gst = (Button) Utils.findRequiredViewAsType(view, R.id.bt_gst, "field 'bt_gst'", Button.class);
        gSTActivity.bt_gst_cancel = (Button) Utils.findRequiredViewAsType(view, R.id.bt_gst_cancel, "field 'bt_gst_cancel'", Button.class);
    }

    @CallSuper
    public void unbind() {
        GSTActivity gSTActivity = this.f5382a;
        if (gSTActivity != null) {
            this.f5382a = null;
            gSTActivity.ed_gst = null;
            gSTActivity.bg_gst = null;
            gSTActivity.bt_gst = null;
            gSTActivity.bt_gst_cancel = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
