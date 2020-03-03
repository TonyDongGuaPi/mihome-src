package com.xiaomi.smarthome.scene;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.ExpandableItemIndicator;

public class StartConditionActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private StartConditionActivity f21493a;
    private View b;

    @UiThread
    public StartConditionActivity_ViewBinding(StartConditionActivity startConditionActivity) {
        this(startConditionActivity, startConditionActivity.getWindow().getDecorView());
    }

    @UiThread
    public StartConditionActivity_ViewBinding(final StartConditionActivity startConditionActivity, View view) {
        this.f21493a = startConditionActivity;
        startConditionActivity.mContentListView = (ListView) Utils.findRequiredViewAsType(view, R.id.content_list_view, "field 'mContentListView'", ListView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'mModuleA3ReturnTransparentBtn' and method 'close'");
        startConditionActivity.mModuleA3ReturnTransparentBtn = (ImageView) Utils.castView(findRequiredView, R.id.module_a_3_return_btn, "field 'mModuleA3ReturnTransparentBtn'", ImageView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                startConditionActivity.close();
            }
        });
        startConditionActivity.mModuleA3ReturnTransparentTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mModuleA3ReturnTransparentTitle'", TextView.class);
        startConditionActivity.mBuyView = Utils.findRequiredView(view, R.id.buy_empty_view, "field 'mBuyView'");
        startConditionActivity.mBuyButton = (Button) Utils.findRequiredViewAsType(view, R.id.btn_see_now, "field 'mBuyButton'", Button.class);
        startConditionActivity.mTitleBarFL = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'mTitleBarFL'", RelativeLayout.class);
        startConditionActivity.mItemIndicator = (ExpandableItemIndicator) Utils.findRequiredViewAsType(view, R.id.btn_expand_indicator, "field 'mItemIndicator'", ExpandableItemIndicator.class);
        startConditionActivity.mGrayView = Utils.findRequiredView(view, R.id.gray_layout, "field 'mGrayView'");
    }

    @CallSuper
    public void unbind() {
        StartConditionActivity startConditionActivity = this.f21493a;
        if (startConditionActivity != null) {
            this.f21493a = null;
            startConditionActivity.mContentListView = null;
            startConditionActivity.mModuleA3ReturnTransparentBtn = null;
            startConditionActivity.mModuleA3ReturnTransparentTitle = null;
            startConditionActivity.mBuyView = null;
            startConditionActivity.mBuyButton = null;
            startConditionActivity.mTitleBarFL = null;
            startConditionActivity.mItemIndicator = null;
            startConditionActivity.mGrayView = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
