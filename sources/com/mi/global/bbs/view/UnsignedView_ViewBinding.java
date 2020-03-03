package com.mi.global.bbs.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class UnsignedView_ViewBinding implements Unbinder {
    private UnsignedView target;
    private View view2131493248;
    private View view2131493249;
    private View view2131493250;

    @UiThread
    public UnsignedView_ViewBinding(UnsignedView unsignedView) {
        this(unsignedView, unsignedView);
    }

    @UiThread
    public UnsignedView_ViewBinding(final UnsignedView unsignedView, View view) {
        this.target = unsignedView;
        unsignedView.unsignTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.unsign_title, "field 'unsignTitle'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.face1_iv, "field 'face1Iv' and method 'onClick'");
        unsignedView.face1Iv = (ImageView) Utils.castView(findRequiredView, R.id.face1_iv, "field 'face1Iv'", ImageView.class);
        this.view2131493248 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                unsignedView.onClick(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.face2_iv, "field 'face2Iv' and method 'onClick'");
        unsignedView.face2Iv = (ImageView) Utils.castView(findRequiredView2, R.id.face2_iv, "field 'face2Iv'", ImageView.class);
        this.view2131493249 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                unsignedView.onClick(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.face3_iv, "field 'face3Iv' and method 'onClick'");
        unsignedView.face3Iv = (ImageView) Utils.castView(findRequiredView3, R.id.face3_iv, "field 'face3Iv'", ImageView.class);
        this.view2131493250 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                unsignedView.onClick(view);
            }
        });
        unsignedView.unsignTip = (TextView) Utils.findRequiredViewAsType(view, R.id.unsign_tip, "field 'unsignTip'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        UnsignedView unsignedView = this.target;
        if (unsignedView != null) {
            this.target = null;
            unsignedView.unsignTitle = null;
            unsignedView.face1Iv = null;
            unsignedView.face2Iv = null;
            unsignedView.face3Iv = null;
            unsignedView.unsignTip = null;
            this.view2131493248.setOnClickListener((View.OnClickListener) null);
            this.view2131493248 = null;
            this.view2131493249.setOnClickListener((View.OnClickListener) null);
            this.view2131493249 = null;
            this.view2131493250.setOnClickListener((View.OnClickListener) null);
            this.view2131493250 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
