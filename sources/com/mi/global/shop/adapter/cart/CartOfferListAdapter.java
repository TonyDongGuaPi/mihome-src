package com.mi.global.shop.adapter.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mi.global.shop.R;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.widget.CustomTextView;

public class CartOfferListAdapter extends ArrayAdapter<String> {
    private static final String c = "CartItemListAdapter";

    /* renamed from: a  reason: collision with root package name */
    Context f5526a;
    ViewHolder b;

    public int getItemViewType(int i) {
        return 0;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public CartOfferListAdapter(Context context) {
        super(context);
        this.f5526a = context;
    }

    public View a(Context context, int i, String str, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.shop_cart_offeritem, viewGroup, false);
        this.b = new ViewHolder();
        this.b.f5527a = inflate;
        this.b.b = (CustomTextView) inflate.findViewById(R.id.offer_title);
        inflate.setTag(this.b);
        return inflate;
    }

    public void a(View view, int i, String str) {
        ((ViewHolder) view.getTag()).b.setText(str);
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f5527a;
        CustomTextView b;

        ViewHolder() {
        }
    }
}
