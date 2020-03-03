package com.mi.global.shop.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.buy.ConfirmActivity;
import com.mi.global.shop.buy.model.PromotionHintModel;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.dialog.PayActivityWebDialog;

public class PromotionListAdapter extends ArrayAdapter<PromotionHintModel> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f5502a = "PromotionListAdapter";
    ViewHolder b;
    /* access modifiers changed from: private */
    public PayActivityWebDialog c;

    public PromotionListAdapter(Context context) {
        super(context);
    }

    public View a(Context context, int i, PromotionHintModel promotionHintModel, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.shop_buy_confirm_orderdetail_promotion_hint, (ViewGroup) null, false);
        this.b = new ViewHolder();
        this.b.f5504a = (SimpleDraweeView) inflate.findViewById(R.id.iv_promote_icon);
        this.b.b = (CustomTextView) inflate.findViewById(R.id.tv_promote_text);
        this.b.c = (ImageView) inflate.findViewById(R.id.iv_activity_arrow);
        inflate.setTag(this.b);
        return inflate;
    }

    public void a(View view, int i, final PromotionHintModel promotionHintModel) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.b.setText(promotionHintModel.c);
        if (!TextUtils.isEmpty(promotionHintModel.b)) {
            FrescoUtils.a(promotionHintModel.b, viewHolder.f5504a);
        }
        final String str = promotionHintModel.d;
        if (TextUtils.isEmpty(str)) {
            viewHolder.c.setVisibility(8);
        } else {
            viewHolder.c.setVisibility(0);
        }
        if (!TextUtils.isEmpty(str)) {
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PayActivityWebDialog.Builder builder = new PayActivityWebDialog.Builder(PromotionListAdapter.this.d);
                    builder.a(str);
                    PayActivityWebDialog unused = PromotionListAdapter.this.c = builder.a();
                    PromotionListAdapter.this.c.show();
                    MiShopStatInterface.a(String.format("pay_promotion(%s)", new Object[]{promotionHintModel.c}), ConfirmActivity.class.getSimpleName());
                }
            });
        }
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        SimpleDraweeView f5504a;
        CustomTextView b;
        ImageView c;

        ViewHolder() {
        }
    }
}
