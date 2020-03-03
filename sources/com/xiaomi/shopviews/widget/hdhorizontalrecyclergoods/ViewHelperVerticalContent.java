package com.xiaomi.shopviews.widget.hdhorizontalrecyclergoods;

import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.utils.PriceRegUtils;
import com.xiaomi.shopviews.widget.R;

public class ViewHelperVerticalContent {

    /* renamed from: a  reason: collision with root package name */
    private View f13239a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;

    public ViewHelperVerticalContent(View view) {
        a(view);
    }

    private void a(View view) {
        this.f13239a = view.findViewById(R.id.viewhelper_vertical_content_root);
        this.g = (TextView) this.f13239a.findViewById(R.id.viewhelper_title);
        this.b = (TextView) this.f13239a.findViewById(R.id.viewhelper_brief);
        TextView textView = (TextView) this.f13239a.findViewById(R.id.tv_viewhelper_price);
        this.d = textView;
        textView.setTag(Constants.CALLIGRAPHY_TAG_PRICE, Boolean.TRUE);
        this.f = (TextView) this.f13239a.findViewById(R.id.tv_viewhelper_price_qi);
        this.e = (TextView) this.f13239a.findViewById(R.id.tv_viewhelper_marketprice);
        this.c = (TextView) this.f13239a.findViewById(R.id.tv_btnTxt);
        this.f13239a.setMinimumHeight(Coder.a(view.getContext(), 114.0f));
    }

    public void a(HomeSectionItem homeSectionItem, int i, int i2) {
        if (!TextUtils.isEmpty(homeSectionItem.mProductName)) {
            this.g.setText(Html.fromHtml(homeSectionItem.mProductName));
        } else {
            this.g.setText("");
        }
        if (TextUtils.isEmpty(homeSectionItem.mProductBrief)) {
            this.b.setVisibility(4);
        } else {
            this.b.setVisibility(0);
            this.b.setText(Html.fromHtml(homeSectionItem.mProductBrief));
        }
        PriceRegUtils.a(this.d, this.e, this.f, homeSectionItem, i);
        GradientDrawable gradientDrawable = (GradientDrawable) this.c.getBackground();
        if (gradientDrawable != null) {
            gradientDrawable.setColor(i);
            this.c.setBackgroundDrawable(gradientDrawable);
        }
        this.c.setTextColor(i2);
        if (!TextUtils.isEmpty(homeSectionItem.mBtnTxt)) {
            this.c.setText(homeSectionItem.mBtnTxt);
        } else {
            this.c.setText("立即购买");
        }
    }
}
