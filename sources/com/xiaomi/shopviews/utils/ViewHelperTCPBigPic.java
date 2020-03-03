package com.xiaomi.shopviews.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.base.utils.FontUtils;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.widget.R;

public class ViewHelperTCPBigPic {

    /* renamed from: a  reason: collision with root package name */
    private RelativeLayout f13224a;
    private RelativeLayout b;
    private View c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private Context l;

    public ViewHelperTCPBigPic(View view, Context context) {
        a(view);
        this.l = context;
    }

    private void a(View view) {
        this.c = view.findViewById(R.id.viewhelper_big_tcp_root);
        this.g = (TextView) this.c.findViewById(R.id.viewhelper_big_tcp_title);
        this.d = (TextView) this.c.findViewById(R.id.viewhelper_big_tcp_brif);
        TextView textView = (TextView) this.c.findViewById(R.id.viewhelper_big_tcp_price_txt_bottom);
        this.h = textView;
        textView.setTag(Constants.CALLIGRAPHY_TAG_PRICE, Boolean.TRUE);
        this.i = (TextView) this.c.findViewById(R.id.viewhelper_big_tcp_price_qi_bottom);
        this.e = (TextView) this.c.findViewById(R.id.viewhelper_big_tcp_marketprice_txt_bottom);
        this.f13224a = (RelativeLayout) this.c.findViewById(R.id.viewhelper_big_tcp_price_layout_bottom);
        this.k = (TextView) this.c.findViewById(R.id.viewhelper_big_tcp_price_txt_right);
        this.j = (TextView) this.c.findViewById(R.id.viewhelper_big_tcp_price_qi_right);
        this.f = (TextView) this.c.findViewById(R.id.viewhelper_big_tcp_marketprice_txt_right);
        this.b = (RelativeLayout) this.c.findViewById(R.id.viewhelper_big_tcp_price_layout_right);
        FontUtils.a(this.l, this.h);
        FontUtils.a(this.l, this.e);
        FontUtils.a(this.l, this.k);
        FontUtils.a(this.l, this.f);
    }

    public View a() {
        return this.c;
    }

    public void a(HomeSectionItem homeSectionItem) {
        this.g.setText(!TextUtils.isEmpty(homeSectionItem.mProductName) ? homeSectionItem.mProductName : "");
        if (TextUtils.isEmpty(homeSectionItem.mProductBrief)) {
            this.d.setVisibility(8);
            this.f13224a.setVisibility(0);
            this.b.setVisibility(8);
            PriceRegUtils.b(this.h, this.e, this.i, homeSectionItem);
            return;
        }
        this.d.setVisibility(0);
        this.f13224a.setVisibility(8);
        this.b.setVisibility(0);
        this.d.setText(homeSectionItem.mProductBrief);
        PriceRegUtils.b(this.k, this.f, this.j, homeSectionItem);
        if (!homeSectionItem.showPriceQi) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.k.getLayoutParams();
            layoutParams.addRule(11);
            this.k.setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
            layoutParams2.addRule(11);
            this.f.setLayoutParams(layoutParams2);
            return;
        }
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.k.getLayoutParams();
        if (Build.VERSION.SDK_INT >= 17) {
            layoutParams3.removeRule(11);
        } else {
            layoutParams3.addRule(11, 0);
        }
        this.k.setLayoutParams(layoutParams3);
        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
        if (Build.VERSION.SDK_INT >= 17) {
            layoutParams4.removeRule(11);
        } else {
            layoutParams4.addRule(11, 0);
        }
        this.f.setLayoutParams(layoutParams4);
    }
}
