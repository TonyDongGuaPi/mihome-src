package com.xiaomi.shopviews.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.base.utils.FontUtils;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.widget.R;

public class ViewHelperTPSmallPic {

    /* renamed from: a  reason: collision with root package name */
    private View f13226a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private Context f = this.f;

    public ViewHelperTPSmallPic(View view) {
        a(view);
    }

    private void a(View view) {
        this.f13226a = view.findViewById(R.id.viewhelper_small_tp_rootview);
        this.c = (TextView) this.f13226a.findViewById(R.id.viewhelper_small_tp_title);
        View findViewById = this.f13226a.findViewById(R.id.layout_viewhelper_small_tp_price);
        TextView textView = (TextView) findViewById.findViewById(R.id.tv_viewhelper_price);
        this.d = textView;
        textView.setTag(Constants.CALLIGRAPHY_TAG_PRICE, Boolean.TRUE);
        this.e = (TextView) findViewById.findViewById(R.id.tv_viewhelper_price_qi);
        this.b = (TextView) findViewById.findViewById(R.id.tv_viewhelper_marketprice);
        FontUtils.a(this.f, this.d);
        FontUtils.a(this.f, this.b);
    }

    public void a(HomeSectionItem homeSectionItem) {
        this.c.setText(homeSectionItem.mProductName);
        PriceRegUtils.b(this.d, this.b, this.e, homeSectionItem);
    }
}
