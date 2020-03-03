package com.xiaomi.shopviews.widget.homeseckill;

import android.view.View;
import android.widget.TextView;
import com.xiaomi.base.utils.FontUtils;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.utils.PriceRegUtils;
import com.xiaomi.shopviews.widget.R;

public class ViewHelperOnlyPrice {

    /* renamed from: a  reason: collision with root package name */
    private TextView f13326a;
    private TextView b;
    private TextView c;

    public ViewHelperOnlyPrice(View view) {
        a(view);
    }

    private void a(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_viewhelper_price);
        this.b = textView;
        textView.setTag(Constants.CALLIGRAPHY_TAG_PRICE, Boolean.TRUE);
        this.c = (TextView) view.findViewById(R.id.tv_viewhelper_price_qi);
        this.f13326a = (TextView) view.findViewById(R.id.tv_viewhelper_marketprice);
        FontUtils.a(view.getContext(), this.b);
        FontUtils.a(view.getContext(), this.f13326a);
    }

    public void a(HomeSectionItem homeSectionItem) {
        PriceRegUtils.b(this.b, this.f13326a, this.c, homeSectionItem);
    }
}
