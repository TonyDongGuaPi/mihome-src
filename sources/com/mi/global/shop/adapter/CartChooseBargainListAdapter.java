package com.mi.global.shop.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.ShoppingCartBargainActivity;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.model.ShoppingCartBargainModel;
import com.mi.global.shop.util.ImageUtil;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.log.LogUtil;
import com.mi.util.Coder;
import java.util.ArrayList;

public class CartChooseBargainListAdapter extends ArrayAdapter<ShoppingCartBargainModel.BargainsItem> {
    private static final String c = "CartChooseBargainListAdapter";

    /* renamed from: a  reason: collision with root package name */
    Context f5486a;
    ViewHolder b;

    public int getItemViewType(int i) {
        return 0;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public CartChooseBargainListAdapter(Context context) {
        super(context);
        this.f5486a = context;
    }

    public View a(Context context, int i, ShoppingCartBargainModel.BargainsItem bargainsItem, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.shop_cart_choosebargainitem, (ViewGroup) null, false);
        this.b = new ViewHolder();
        this.b.f5488a = inflate;
        this.b.e = (CustomTextView) inflate.findViewById(R.id.item_compatible);
        this.b.f = (CustomTextView) inflate.findViewById(R.id.item_price);
        this.b.d = (CustomTextView) inflate.findViewById(R.id.item_title);
        this.b.b = (ImageView) inflate.findViewById(R.id.item_select);
        this.b.c = (SimpleDraweeView) inflate.findViewById(R.id.item_bargainimage);
        this.b.g = (LinearLayout) inflate.findViewById(R.id.item_all);
        inflate.setTag(this.b);
        return inflate;
    }

    public void a(View view, int i, ShoppingCartBargainModel.BargainsItem bargainsItem) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.d.setText(bargainsItem.bargainName);
        int a2 = Coder.a(50.0f);
        String str = bargainsItem.bargainImg;
        if (!TextUtils.isEmpty(str)) {
            str = ImageUtil.a(a2, a2, str);
        }
        FrescoUtils.a(str, viewHolder.c);
        viewHolder.f.setText(LocaleHelper.e() + " " + bargainsItem.bargainPrice_txt);
        if (bargainsItem.bargainAdapt == null || bargainsItem.bargainAdapt.size() <= 0) {
            viewHolder.e.setVisibility(8);
        } else {
            viewHolder.e.setVisibility(0);
            String str2 = "";
            for (int i2 = 0; i2 < bargainsItem.bargainAdapt.size(); i2++) {
                str2 = str2 + " " + bargainsItem.bargainAdapt.get(i2);
            }
            viewHolder.e.setText(ShopApp.g().getString(R.string.cart_bargain_compatible) + str2);
        }
        if (bargainsItem.Selected.booleanValue()) {
            viewHolder.b.setImageDrawable(ShopApp.g().getResources().getDrawable(R.drawable.shop_radio_selected));
        } else {
            viewHolder.b.setImageDrawable(ShopApp.g().getResources().getDrawable(R.drawable.shop_radio_unselected));
        }
        viewHolder.g.setOnClickListener(new MyListener(bargainsItem, this.d));
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f5488a;
        ImageView b;
        SimpleDraweeView c;
        CustomTextView d;
        CustomTextView e;
        CustomTextView f;
        LinearLayout g;

        ViewHolder() {
        }
    }

    private class MyListener implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        ShoppingCartBargainModel.BargainsItem f5487a;
        Context b;
        ArrayList<ShoppingCartBargainModel.BargainsItem> c;
        CartChooseBargainListAdapter d;

        public MyListener(ShoppingCartBargainModel.BargainsItem bargainsItem, Context context) {
            this.f5487a = bargainsItem;
            this.b = context;
            ShoppingCartBargainActivity shoppingCartBargainActivity = (ShoppingCartBargainActivity) context;
            this.c = shoppingCartBargainActivity.shoppingCartBargainModel.bargainList;
            this.d = shoppingCartBargainActivity.mCartChooseBargainListAdapter;
        }

        public void onClick(View view) {
            LogUtil.b(CartChooseBargainListAdapter.c, "clicked:" + this.f5487a.bargainName);
            if (this.c != null && this.d != null) {
                for (int i = 0; i < this.c.size(); i++) {
                    if (this.f5487a.bargainName.equalsIgnoreCase(this.c.get(i).bargainName)) {
                        this.c.get(i).Selected = true;
                    } else {
                        this.c.get(i).Selected = false;
                    }
                }
                this.d.notifyDataSetChanged();
            }
        }
    }
}
