package com.mi.global.shop.adapter.home;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.newmodel.home.NewHomeBlockInfoItem;
import com.mi.global.shop.util.UIAdapter;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.home.HomeThemeItemClick;
import java.util.ArrayList;

public class HomeImageGridAdapter extends ArrayAdapter<NewHomeBlockInfoItem> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5541a = "HomeImageGridAdapter";
    private ImageView b;

    public HomeImageGridAdapter(Context context) {
        super(context);
        this.d = context;
    }

    public View a(Context context, int i, NewHomeBlockInfoItem newHomeBlockInfoItem, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.shop_gallery_image_view, viewGroup, false);
        inflate.getLayoutParams().height = UIAdapter.a().a(26);
        return inflate;
    }

    public void a(View view, int i, final NewHomeBlockInfoItem newHomeBlockInfoItem) {
        if (newHomeBlockInfoItem != null) {
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    HomeThemeItemClick.a(HomeImageGridAdapter.this.d, newHomeBlockInfoItem);
                }
            });
            HomeThemeItemClick.a(newHomeBlockInfoItem);
            String imageUrl = newHomeBlockInfoItem.getImageUrl();
            if (!TextUtils.isEmpty(imageUrl) && !imageUrl.equals(view.getTag())) {
                view.setTag(imageUrl);
                FrescoUtils.a(imageUrl, (SimpleDraweeView) view);
            }
        }
    }

    public void b(ArrayList<NewHomeBlockInfoItem> arrayList) {
        a(arrayList);
    }
}
