package com.mi.global.shop.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.widget.CustomTextView;

public class OrderViewActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private OrderViewActivity f5409a;

    @UiThread
    public OrderViewActivity_ViewBinding(OrderViewActivity orderViewActivity) {
        this(orderViewActivity, orderViewActivity.getWindow().getDecorView());
    }

    @UiThread
    public OrderViewActivity_ViewBinding(OrderViewActivity orderViewActivity, View view) {
        this.f5409a = orderViewActivity;
        orderViewActivity.showTips = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.show_tips, "field 'showTips'", CustomTextView.class);
        orderViewActivity.llNotice = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_notice, "field 'llNotice'", LinearLayout.class);
        orderViewActivity.showTag = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.show_tag, "field 'showTag'", CustomTextView.class);
        orderViewActivity.tipsShadow = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.tips_shadow, "field 'tipsShadow'", LinearLayout.class);
        orderViewActivity.exchangeCouponGroup = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.orderview_exchange_couponLL, "field 'exchangeCouponGroup'", LinearLayout.class);
        orderViewActivity.exchangeCouponView = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.orderview_exchange_coupon, "field 'exchangeCouponView'", CustomTextView.class);
    }

    @CallSuper
    public void unbind() {
        OrderViewActivity orderViewActivity = this.f5409a;
        if (orderViewActivity != null) {
            this.f5409a = null;
            orderViewActivity.showTips = null;
            orderViewActivity.llNotice = null;
            orderViewActivity.showTag = null;
            orderViewActivity.tipsShadow = null;
            orderViewActivity.exchangeCouponGroup = null;
            orderViewActivity.exchangeCouponView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
