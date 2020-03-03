package com.xiaomi.payment.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomi.payment.task.rxjava.RxHomePageGridTask;

public class HomeDiscountsView extends CommonBannerView<RxHomePageGridTask.Result.DiscountsItemData> {

    /* renamed from: a  reason: collision with root package name */
    private DiscountItemClickListener f12465a;

    public interface DiscountItemClickListener {
        void a(RxHomePageGridTask.Result.DiscountsItemData discountsItemData);
    }

    public HomeDiscountsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setDiscountViewClickListener(DiscountItemClickListener discountItemClickListener) {
        this.f12465a = discountItemClickListener;
    }

    /* access modifiers changed from: protected */
    public String getBannerItemImageUrl(RxHomePageGridTask.Result.DiscountsItemData discountsItemData) {
        return discountsItemData.f12422a;
    }

    /* access modifiers changed from: protected */
    public void onBannerItemClick(View view, RxHomePageGridTask.Result.DiscountsItemData discountsItemData) {
        if (this.f12465a != null) {
            this.f12465a.a(discountsItemData);
        }
    }
}
