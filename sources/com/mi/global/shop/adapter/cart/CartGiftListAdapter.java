package com.mi.global.shop.adapter.cart;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import com.amap.location.common.model.AmapLoc;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.ShoppingCartActivity;
import com.mi.global.shop.activity.WebActivity;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.newmodel.cart.NewCartGiftItem;
import com.mi.global.shop.newmodel.cart.NewCartSelectInfo;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.ImageUtil;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.log.LogUtil;
import com.mi.util.Coder;
import java.util.ArrayList;

public class CartGiftListAdapter extends ArrayAdapter<NewCartGiftItem> {
    private static final String c = "CartItemListAdapter";

    /* renamed from: a  reason: collision with root package name */
    Context f5514a;
    ViewHolder b;

    public int getItemViewType(int i) {
        return 0;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public CartGiftListAdapter(Context context) {
        super(context);
        this.f5514a = context;
    }

    public View a(Context context, int i, NewCartGiftItem newCartGiftItem, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.shop_cart_list_gift_item, (ViewGroup) null, false);
        this.b = new ViewHolder();
        this.b.f5517a = inflate;
        this.b.b = (SimpleDraweeView) inflate.findViewById(R.id.gift_image);
        this.b.c = (CustomTextView) inflate.findViewById(R.id.gift_type);
        this.b.d = (CustomTextView) inflate.findViewById(R.id.gift_title);
        this.b.e = (CustomTextView) inflate.findViewById(R.id.gift_price);
        this.b.f = (CustomTextView) inflate.findViewById(R.id.gift_num);
        this.b.g = (Spinner) inflate.findViewById(R.id.gift_selection);
        inflate.setTag(this.b);
        return inflate;
    }

    public void a(View view, int i, NewCartGiftItem newCartGiftItem) {
        LogUtil.b(c, "Giftitem bindview");
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        int a2 = Coder.a(50.0f);
        String str = newCartGiftItem.image_url;
        if (!TextUtils.isEmpty(str)) {
            str = ImageUtil.a(a2, a2, str);
        }
        FrescoUtils.a(str, viewHolder.b);
        viewHolder.d.setText(newCartGiftItem.product_name);
        CustomTextView customTextView = viewHolder.f;
        customTextView.setText("X" + newCartGiftItem.num);
        if (TextUtils.isEmpty(newCartGiftItem.activity_name)) {
            viewHolder.c.setVisibility(8);
        } else {
            viewHolder.c.setVisibility(0);
            viewHolder.c.setText(newCartGiftItem.activity_name);
        }
        CustomTextView customTextView2 = viewHolder.e;
        customTextView2.setText(LocaleHelper.e() + newCartGiftItem.salePrice_txt);
        if (newCartGiftItem.selecInfo == null || newCartGiftItem.selecInfo.size() <= 1) {
            viewHolder.g.setVisibility(8);
        } else {
            viewHolder.g.setVisibility(0);
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < newCartGiftItem.selecInfo.size(); i2++) {
                if (newCartGiftItem.selecInfo.get(i2).product_name.equalsIgnoreCase(newCartGiftItem.product_name)) {
                    arrayList.add(newCartGiftItem.selecInfo.get(i2).style_value);
                }
            }
            for (int i3 = 0; i3 < newCartGiftItem.selecInfo.size(); i3++) {
                if (!newCartGiftItem.selecInfo.get(i3).is_cos && !newCartGiftItem.selecInfo.get(i3).product_name.equalsIgnoreCase(newCartGiftItem.product_name)) {
                    arrayList.add(newCartGiftItem.selecInfo.get(i3).style_value);
                }
            }
            viewHolder.g.setAdapter(new android.widget.ArrayAdapter(this.f5514a, R.layout.shop_cart_gift_spinneritem, arrayList));
            viewHolder.g.setOnItemSelectedListener(new MySpinnerSelectedListener(newCartGiftItem, this.f5514a, viewHolder));
            viewHolder.g.setSelection(0);
        }
        viewHolder.b.setOnClickListener(new MyListener(newCartGiftItem, this.f5514a));
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f5517a;
        SimpleDraweeView b;
        CustomTextView c;
        CustomTextView d;
        CustomTextView e;
        CustomTextView f;
        Spinner g;

        ViewHolder() {
        }
    }

    private class MyListener implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        NewCartGiftItem f5515a;
        Context b;

        public MyListener(NewCartGiftItem newCartGiftItem, Context context) {
            this.f5515a = newCartGiftItem;
            this.b = context;
        }

        public void onClick(View view) {
            if (view.getId() == R.id.gift_image && !TextUtils.isEmpty(this.f5515a.commodity_id)) {
                String d = ConnectionHelper.d(this.f5515a.commodity_id);
                Intent intent = new Intent(this.b, WebActivity.class);
                intent.putExtra("url", d);
                intent.putExtra("cart_webview", true);
                ((ShoppingCartActivity) this.b).startActivityForResult(intent, 23);
            }
        }
    }

    private class MySpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        /* renamed from: a  reason: collision with root package name */
        NewCartGiftItem f5516a;
        Context b;
        ViewHolder c;

        public void onNothingSelected(AdapterView<?> adapterView) {
        }

        public MySpinnerSelectedListener(NewCartGiftItem newCartGiftItem, Context context, ViewHolder viewHolder) {
            this.f5516a = newCartGiftItem;
            this.b = context;
            this.c = viewHolder;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            StringBuilder sb = new StringBuilder();
            sb.append("item selected:");
            CustomTextView customTextView = (CustomTextView) view;
            sb.append(customTextView.getText());
            LogUtil.b(CartGiftListAdapter.c, sb.toString());
            String charSequence = customTextView.getText().toString();
            int i2 = 0;
            while (i2 < this.f5516a.selecInfo.size()) {
                if (!this.f5516a.selecInfo.get(i2).style_value.equalsIgnoreCase(charSequence) || !this.f5516a.selecInfo.get(i2).product_name.equalsIgnoreCase(this.f5516a.product_name)) {
                    i2++;
                } else {
                    return;
                }
            }
            LogUtil.b(CartGiftListAdapter.c, "item selected:" + customTextView.getText() + "updateview");
            NewCartSelectInfo newCartSelectInfo = null;
            String str = "";
            int i3 = 0;
            while (true) {
                if (i3 >= this.f5516a.selecInfo.size()) {
                    break;
                } else if (charSequence.equalsIgnoreCase(this.f5516a.selecInfo.get(i3).style_value)) {
                    newCartSelectInfo = this.f5516a.selecInfo.get(i3);
                    str = this.f5516a.selecInfo.get(i3).product_id + "-0-1-" + this.f5516a.actId + AmapLoc.q;
                    break;
                } else {
                    i3++;
                }
            }
            if (!TextUtils.isEmpty(str) && newCartSelectInfo != null) {
                ((ShoppingCartActivity) this.b).addCartRequest(str, false);
                ((ShoppingCartActivity) this.b).chooseGift(this.f5516a, newCartSelectInfo);
            }
        }
    }
}
