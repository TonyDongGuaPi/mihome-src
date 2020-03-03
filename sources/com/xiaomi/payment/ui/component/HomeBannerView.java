package com.xiaomi.payment.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomi.payment.data.UnevenGridData;
import com.xiaomi.payment.ui.adapter.HomeGridAdapter;

public class HomeBannerView extends CommonBannerView<UnevenGridData.NormalGridItemInfo> {

    /* renamed from: a  reason: collision with root package name */
    private HomeGridAdapter.GridItemClickListener f12464a;

    public HomeBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnBannerViewClickListener(HomeGridAdapter.GridItemClickListener gridItemClickListener) {
        this.f12464a = gridItemClickListener;
    }

    /* access modifiers changed from: protected */
    public String getBannerItemImageUrl(UnevenGridData.NormalGridItemInfo normalGridItemInfo) {
        return normalGridItemInfo.c;
    }

    /* access modifiers changed from: protected */
    public void onBannerItemClick(View view, UnevenGridData.NormalGridItemInfo normalGridItemInfo) {
        if (this.f12464a != null) {
            this.f12464a.a(normalGridItemInfo);
        }
    }
}
