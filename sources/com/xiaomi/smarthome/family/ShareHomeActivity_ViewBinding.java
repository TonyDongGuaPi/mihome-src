package com.xiaomi.smarthome.family;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.ListItemView;

public class ShareHomeActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ShareHomeActivity f15769a;
    private View b;
    private View c;
    private View d;

    @UiThread
    public ShareHomeActivity_ViewBinding(ShareHomeActivity shareHomeActivity) {
        this(shareHomeActivity, shareHomeActivity.getWindow().getDecorView());
    }

    @UiThread
    public ShareHomeActivity_ViewBinding(final ShareHomeActivity shareHomeActivity, View view) {
        this.f15769a = shareHomeActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'moduleA3ReturnBtn' and method 'onViewClicked'");
        shareHomeActivity.moduleA3ReturnBtn = (ImageView) Utils.castView(findRequiredView, R.id.module_a_3_return_btn, "field 'moduleA3ReturnBtn'", ImageView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                shareHomeActivity.onViewClicked(view);
            }
        });
        shareHomeActivity.moduleA3ReturnTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'moduleA3ReturnTitle'", TextView.class);
        shareHomeActivity.moduleA3RightIvSettingBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_right_iv_setting_btn, "field 'moduleA3RightIvSettingBtn'", ImageView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.share_friend, "field 'shareFriend' and method 'onViewClicked'");
        shareHomeActivity.shareFriend = (LinearLayout) Utils.castView(findRequiredView2, R.id.share_friend, "field 'shareFriend'", LinearLayout.class);
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                shareHomeActivity.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.share_wx, "field 'shareWx' and method 'onViewClicked'");
        shareHomeActivity.shareWx = (LinearLayout) Utils.castView(findRequiredView3, R.id.share_wx, "field 'shareWx'", LinearLayout.class);
        this.d = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                shareHomeActivity.onViewClicked(view);
            }
        });
        shareHomeActivity.shareFamily = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.share_family, "field 'shareFamily'", LinearLayout.class);
        shareHomeActivity.lastTitle = (ListItemView) Utils.findRequiredViewAsType(view, R.id.last_title, "field 'lastTitle'", ListItemView.class);
        shareHomeActivity.lastList = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.last_list, "field 'lastList'", LinearLayout.class);
        shareHomeActivity.fragmentContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.fragment_container, "field 'fragmentContainer'", FrameLayout.class);
        shareHomeActivity.listSpace = (ListItemView) Utils.findRequiredViewAsType(view, R.id.list_space, "field 'listSpace'", ListItemView.class);
    }

    @CallSuper
    public void unbind() {
        ShareHomeActivity shareHomeActivity = this.f15769a;
        if (shareHomeActivity != null) {
            this.f15769a = null;
            shareHomeActivity.moduleA3ReturnBtn = null;
            shareHomeActivity.moduleA3ReturnTitle = null;
            shareHomeActivity.moduleA3RightIvSettingBtn = null;
            shareHomeActivity.shareFriend = null;
            shareHomeActivity.shareWx = null;
            shareHomeActivity.shareFamily = null;
            shareHomeActivity.lastTitle = null;
            shareHomeActivity.lastList = null;
            shareHomeActivity.fragmentContainer = null;
            shareHomeActivity.listSpace = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            this.c.setOnClickListener((View.OnClickListener) null);
            this.c = null;
            this.d.setOnClickListener((View.OnClickListener) null);
            this.d = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
