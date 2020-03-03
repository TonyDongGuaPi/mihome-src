package com.mi.global.shop.buy;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.NoScrollListView;

public class CardlessEMIfragment_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private CardlessEMIfragment f6813a;
    private View b;

    @UiThread
    public CardlessEMIfragment_ViewBinding(final CardlessEMIfragment cardlessEMIfragment, View view) {
        this.f6813a = cardlessEMIfragment;
        cardlessEMIfragment.mContentGroup = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.ll_content_group, "field 'mContentGroup'", RelativeLayout.class);
        cardlessEMIfragment.mPlanTips = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_plan_tips, "field 'mPlanTips'", CustomTextView.class);
        cardlessEMIfragment.mPlanListView = (NoScrollListView) Utils.findRequiredViewAsType(view, R.id.lv_plan_list, "field 'mPlanListView'", NoScrollListView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.bt_cardless_pay_now, "field 'mPayNowBtn' and method 'cardlessPayNow'");
        cardlessEMIfragment.mPayNowBtn = (CustomButtonView) Utils.castView(findRequiredView, R.id.bt_cardless_pay_now, "field 'mPayNowBtn'", CustomButtonView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cardlessEMIfragment.cardlessPayNow(view);
            }
        });
        cardlessEMIfragment.mEMIErrorTip = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.emi_less_error_tip, "field 'mEMIErrorTip'", CustomTextView.class);
    }

    @CallSuper
    public void unbind() {
        CardlessEMIfragment cardlessEMIfragment = this.f6813a;
        if (cardlessEMIfragment != null) {
            this.f6813a = null;
            cardlessEMIfragment.mContentGroup = null;
            cardlessEMIfragment.mPlanTips = null;
            cardlessEMIfragment.mPlanListView = null;
            cardlessEMIfragment.mPayNowBtn = null;
            cardlessEMIfragment.mEMIErrorTip = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
