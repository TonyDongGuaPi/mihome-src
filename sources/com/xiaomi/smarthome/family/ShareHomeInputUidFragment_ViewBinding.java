package com.xiaomi.smarthome.family;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;

public class ShareHomeInputUidFragment_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ShareHomeInputUidFragment f15777a;
    private View b;

    @UiThread
    public ShareHomeInputUidFragment_ViewBinding(final ShareHomeInputUidFragment shareHomeInputUidFragment, View view) {
        this.f15777a = shareHomeInputUidFragment;
        shareHomeInputUidFragment.accountEditor = (AutoCompleteTextView) Utils.findRequiredViewAsType(view, R.id.account_editor, "field 'accountEditor'", AutoCompleteTextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.button_search, "field 'buttonSearch' and method 'onSearchClicked'");
        shareHomeInputUidFragment.buttonSearch = (TextView) Utils.castView(findRequiredView, R.id.button_search, "field 'buttonSearch'", TextView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                shareHomeInputUidFragment.onSearchClicked();
            }
        });
        shareHomeInputUidFragment.tvUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_user_name, "field 'tvUserName'", TextView.class);
        shareHomeInputUidFragment.linearShareDevice = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.linear_share_device, "field 'linearShareDevice'", LinearLayout.class);
        shareHomeInputUidFragment.iconImage = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.icon_image, "field 'iconImage'", SimpleDraweeView.class);
        shareHomeInputUidFragment.root = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.root, "field 'root'", ConstraintLayout.class);
    }

    @CallSuper
    public void unbind() {
        ShareHomeInputUidFragment shareHomeInputUidFragment = this.f15777a;
        if (shareHomeInputUidFragment != null) {
            this.f15777a = null;
            shareHomeInputUidFragment.accountEditor = null;
            shareHomeInputUidFragment.buttonSearch = null;
            shareHomeInputUidFragment.tvUserName = null;
            shareHomeInputUidFragment.linearShareDevice = null;
            shareHomeInputUidFragment.iconImage = null;
            shareHomeInputUidFragment.root = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
