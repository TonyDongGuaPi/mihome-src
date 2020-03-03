package com.mi.global.shop.adapter.cart;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.ShoppingCartActivity;
import com.mi.global.shop.activity.ShoppingCartBargainActivity;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.newmodel.cart.NewCartBargainItem;
import com.mi.global.shop.widget.CustomTextView;

public class CartBargainListAdapter extends ArrayAdapter<NewCartBargainItem> {
    static long c = 0;
    private static final String g = "CartItemListAdapter";

    /* renamed from: a  reason: collision with root package name */
    Context f5511a;
    ViewHolder b;

    public int getItemViewType(int i) {
        return 0;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public CartBargainListAdapter(Context context) {
        super(context);
        this.f5511a = context;
    }

    public View a(Context context, int i, NewCartBargainItem newCartBargainItem, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.shop_cart_bargainitem, viewGroup, false);
        this.b = new ViewHolder();
        this.b.f5513a = inflate;
        this.b.c = (CustomTextView) inflate.findViewById(R.id.bargain_title);
        this.b.b = (ImageView) inflate.findViewById(R.id.bargain_box);
        this.b.d = (LinearLayout) inflate.findViewById(R.id.bargain_all);
        this.b.e = (CustomTextView) inflate.findViewById(R.id.change);
        c = 0;
        inflate.setTag(this.b);
        return inflate;
    }

    public void a(View view, int i, NewCartBargainItem newCartBargainItem) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.c.setText(newCartBargainItem.bargain_name);
        if (newCartBargainItem.checked) {
            viewHolder.b.setImageDrawable(ShopApp.g().getResources().getDrawable(R.drawable.shop_bargain_box_checked));
            viewHolder.e.setVisibility(0);
        } else {
            viewHolder.b.setImageDrawable(ShopApp.g().getResources().getDrawable(R.drawable.shop_bargain_box));
            viewHolder.e.setVisibility(8);
        }
        viewHolder.d.setOnClickListener(new MyListener(newCartBargainItem, this.d));
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f5513a;
        ImageView b;
        CustomTextView c;
        LinearLayout d;
        CustomTextView e;

        ViewHolder() {
        }
    }

    private class MyListener implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        NewCartBargainItem f5512a;
        Context b;

        public MyListener(NewCartBargainItem newCartBargainItem, Context context) {
            this.f5512a = newCartBargainItem;
            this.b = context;
        }

        public void onClick(View view) {
            if (view.getId() != R.id.bargain_all) {
                return;
            }
            if (CartBargainListAdapter.c == 0 || System.currentTimeMillis() - CartBargainListAdapter.c > 4000) {
                CartBargainListAdapter.c = System.currentTimeMillis();
                if (!this.f5512a.checked && this.f5512a.selectable) {
                    Intent intent = new Intent(this.b, ShoppingCartBargainActivity.class);
                    intent.putExtra("cart_bargain", this.f5512a.json_data);
                    ((ShoppingCartActivity) this.b).startActivityForResult(intent, 20);
                } else if (this.f5512a.checked || this.f5512a.selectable) {
                    String barginItembyId = ((ShoppingCartActivity) this.b).getBarginItembyId(this.f5512a.bargainItemId);
                    if (!TextUtils.isEmpty(barginItembyId)) {
                        if (this.f5512a.checked && !this.f5512a.selectable) {
                            ((ShoppingCartActivity) this.b).delCartRequest(barginItembyId);
                        } else if (this.f5512a.checked && this.f5512a.selectable) {
                            ((ShoppingCartActivity) this.b).delCartRequest(barginItembyId);
                            Intent intent2 = new Intent(this.b, ShoppingCartBargainActivity.class);
                            intent2.putExtra("cart_bargain", this.f5512a.json_data);
                            ((ShoppingCartActivity) this.b).startActivityForResult(intent2, 20);
                        }
                    }
                } else {
                    ((ShoppingCartActivity) this.b).addCartRequest(this.f5512a.bargainGoodsId, true);
                }
            }
        }
    }
}
