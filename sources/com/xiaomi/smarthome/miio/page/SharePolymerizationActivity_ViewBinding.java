package com.xiaomi.smarthome.miio.page;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class SharePolymerizationActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private SharePolymerizationActivity f19696a;
    private View b;
    private View c;
    private View d;
    private View e;

    @UiThread
    public SharePolymerizationActivity_ViewBinding(SharePolymerizationActivity sharePolymerizationActivity) {
        this(sharePolymerizationActivity, sharePolymerizationActivity.getWindow().getDecorView());
    }

    @UiThread
    public SharePolymerizationActivity_ViewBinding(final SharePolymerizationActivity sharePolymerizationActivity, View view) {
        this.f19696a = sharePolymerizationActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'moduleA3ReturnBtn' and method 'onViewClicked'");
        sharePolymerizationActivity.moduleA3ReturnBtn = (ImageView) Utils.castView(findRequiredView, R.id.module_a_3_return_btn, "field 'moduleA3ReturnBtn'", ImageView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                sharePolymerizationActivity.onViewClicked(view);
            }
        });
        sharePolymerizationActivity.moduleA3ReturnTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'moduleA3ReturnTitle'", TextView.class);
        sharePolymerizationActivity.moduleA3RightIvSettingBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_right_iv_setting_btn, "field 'moduleA3RightIvSettingBtn'", ImageView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.cr_share_home, "field 'crShareHome' and method 'onViewClicked'");
        sharePolymerizationActivity.crShareHome = (FrameLayout) Utils.castView(findRequiredView2, R.id.cr_share_home, "field 'crShareHome'", FrameLayout.class);
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                sharePolymerizationActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.cr_share_device, "field 'crShareDevice' and method 'onViewClicked'");
        sharePolymerizationActivity.crShareDevice = (FrameLayout) Utils.castView(findRequiredView3, R.id.cr_share_device, "field 'crShareDevice'", FrameLayout.class);
        this.d = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                sharePolymerizationActivity.onViewClicked(view);
            }
        });
        sharePolymerizationActivity.rlShareWx = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rl_share_wx, "field 'rlShareWx'", RelativeLayout.class);
        View findRequiredView4 = Utils.findRequiredView(view, R.id.cr_share_wx, "field 'crShareWx' and method 'onViewClicked'");
        sharePolymerizationActivity.crShareWx = (FrameLayout) Utils.castView(findRequiredView4, R.id.cr_share_wx, "field 'crShareWx'", FrameLayout.class);
        this.e = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                sharePolymerizationActivity.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        SharePolymerizationActivity sharePolymerizationActivity = this.f19696a;
        if (sharePolymerizationActivity != null) {
            this.f19696a = null;
            sharePolymerizationActivity.moduleA3ReturnBtn = null;
            sharePolymerizationActivity.moduleA3ReturnTitle = null;
            sharePolymerizationActivity.moduleA3RightIvSettingBtn = null;
            sharePolymerizationActivity.crShareHome = null;
            sharePolymerizationActivity.crShareDevice = null;
            sharePolymerizationActivity.rlShareWx = null;
            sharePolymerizationActivity.crShareWx = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            this.c.setOnClickListener((View.OnClickListener) null);
            this.c = null;
            this.d.setOnClickListener((View.OnClickListener) null);
            this.d = null;
            this.e.setOnClickListener((View.OnClickListener) null);
            this.e = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
