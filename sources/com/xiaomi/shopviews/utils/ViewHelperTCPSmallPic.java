package com.xiaomi.shopviews.utils;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.base.utils.FontUtils;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.widget.R;

public class ViewHelperTCPSmallPic {

    /* renamed from: a  reason: collision with root package name */
    private View f13225a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private Context g;

    public ViewHelperTCPSmallPic(View view, Context context) {
        a(view);
        this.g = context;
    }

    private void a(View view) {
        this.f13225a = view.findViewById(R.id.viewhelper_small_tcp_rootview);
        this.d = (TextView) this.f13225a.findViewById(R.id.viewhelper_small_tcp_title);
        this.b = (TextView) this.f13225a.findViewById(R.id.viewhelper_small_tcp_breif);
        View findViewById = this.f13225a.findViewById(R.id.layout_viewhelper_price);
        TextView textView = (TextView) findViewById.findViewById(R.id.tv_viewhelper_price);
        this.e = textView;
        textView.setTag(Constants.CALLIGRAPHY_TAG_PRICE, Boolean.TRUE);
        this.f = (TextView) findViewById.findViewById(R.id.tv_viewhelper_price_qi);
        this.c = (TextView) findViewById.findViewById(R.id.tv_viewhelper_marketprice);
        FontUtils.a(this.g, this.e);
        FontUtils.a(this.g, this.c);
    }

    public void a(HomeSectionItem homeSectionItem) {
        if (!TextUtils.isEmpty(homeSectionItem.mProductName)) {
            this.d.setText(Html.fromHtml(homeSectionItem.mProductName));
        } else {
            this.d.setText("");
        }
        if (TextUtils.isEmpty(homeSectionItem.mProductBrief)) {
            this.b.setVisibility(8);
        } else {
            this.b.setVisibility(0);
            this.b.setText(Html.fromHtml(homeSectionItem.mProductBrief));
        }
        PriceRegUtils.b(this.e, this.c, this.f, homeSectionItem);
    }
}
