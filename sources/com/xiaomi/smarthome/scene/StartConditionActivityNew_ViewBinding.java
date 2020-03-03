package com.xiaomi.smarthome.scene;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.ExpandableItemIndicator;

public class StartConditionActivityNew_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private StartConditionActivityNew f21491a;
    private View b;

    @UiThread
    public StartConditionActivityNew_ViewBinding(StartConditionActivityNew startConditionActivityNew) {
        this(startConditionActivityNew, startConditionActivityNew.getWindow().getDecorView());
    }

    @UiThread
    public StartConditionActivityNew_ViewBinding(final StartConditionActivityNew startConditionActivityNew, View view) {
        this.f21491a = startConditionActivityNew;
        startConditionActivityNew.mContentListView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.content_list_view, "field 'mContentListView'", RecyclerView.class);
        startConditionActivityNew.topFilterLayout = Utils.findRequiredView(view, R.id.top_filter_item, "field 'topFilterLayout'");
        startConditionActivityNew.topDeviceTitleLayout = Utils.findRequiredView(view, R.id.top_device_title_item, "field 'topDeviceTitleLayout'");
        startConditionActivityNew.mModuleA3ReturnTransparentTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mModuleA3ReturnTransparentTitle'", TextView.class);
        startConditionActivityNew.mBuyView = Utils.findRequiredView(view, R.id.buy_empty_view, "field 'mBuyView'");
        startConditionActivityNew.mBuyButton = (Button) Utils.findRequiredViewAsType(view, R.id.btn_see_now, "field 'mBuyButton'", Button.class);
        startConditionActivityNew.mItemIndicator = (ExpandableItemIndicator) Utils.findRequiredViewAsType(view, R.id.btn_expand_indicator, "field 'mItemIndicator'", ExpandableItemIndicator.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "method 'close'");
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                startConditionActivityNew.close();
            }
        });
    }

    @CallSuper
    public void unbind() {
        StartConditionActivityNew startConditionActivityNew = this.f21491a;
        if (startConditionActivityNew != null) {
            this.f21491a = null;
            startConditionActivityNew.mContentListView = null;
            startConditionActivityNew.topFilterLayout = null;
            startConditionActivityNew.topDeviceTitleLayout = null;
            startConditionActivityNew.mModuleA3ReturnTransparentTitle = null;
            startConditionActivityNew.mBuyView = null;
            startConditionActivityNew.mBuyButton = null;
            startConditionActivityNew.mItemIndicator = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
