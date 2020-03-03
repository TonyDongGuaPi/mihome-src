package com.mi.global.shop.adapter.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.mi.global.shop.R;

public abstract class HomeThemeArrayAdapter<T> extends ArrayAdapter<T> {
    public HomeThemeArrayAdapter(Context context) {
        super(context);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        a(i, view2);
        return view2;
    }

    private void a(int i, View view) {
        if (!this.f) {
            return;
        }
        if (1 == getCount()) {
            view.setBackgroundResource(R.drawable.shop_bg_card_single);
        } else if (i == 0) {
            view.setBackgroundResource(R.drawable.shop_bg_card_top);
        } else if (i == getCount() - 1) {
            view.setBackgroundResource(R.drawable.shop_bg_card_bottom);
        } else {
            view.setBackgroundResource(R.drawable.shop_bg_card_middle);
        }
    }
}
