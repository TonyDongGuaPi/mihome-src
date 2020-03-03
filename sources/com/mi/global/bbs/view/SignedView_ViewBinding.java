package com.mi.global.bbs.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class SignedView_ViewBinding implements Unbinder {
    private SignedView target;

    @UiThread
    public SignedView_ViewBinding(SignedView signedView) {
        this(signedView, signedView);
    }

    @UiThread
    public SignedView_ViewBinding(SignedView signedView, View view) {
        this.target = signedView;
        signedView.signedFeelIv = (ImageView) Utils.findRequiredViewAsType(view, R.id.signed_feel_iv, "field 'signedFeelIv'", ImageView.class);
        signedView.signedPointsTv = (TextView) Utils.findRequiredViewAsType(view, R.id.signed_points_tv, "field 'signedPointsTv'", TextView.class);
        signedView.signedSameTv = (TextView) Utils.findRequiredViewAsType(view, R.id.signed_same_tv, "field 'signedSameTv'", TextView.class);
        signedView.signedCalendarView = (SignCalendarView) Utils.findRequiredViewAsType(view, R.id.signed_calendar_view, "field 'signedCalendarView'", SignCalendarView.class);
    }

    @CallSuper
    public void unbind() {
        SignedView signedView = this.target;
        if (signedView != null) {
            this.target = null;
            signedView.signedFeelIv = null;
            signedView.signedPointsTv = null;
            signedView.signedSameTv = null;
            signedView.signedCalendarView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
