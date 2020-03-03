package com.mi.global.shop.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.mi.global.shop.R;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.newmodel.home.NewHomeBlockInfoItem;
import com.mi.global.shop.util.UIAdapter;
import com.mi.global.shop.widget.home.HomeThemeItemClick;

@Deprecated
public class AdHomeSectionListAdapter extends ArrayAdapter<NewHomeBlockInfoItem> {

    /* renamed from: a  reason: collision with root package name */
    public final int f5482a = UIAdapter.a().a(21);
    public final int b = UIAdapter.a().a(19);
    private ImageView c;
    private Context g;

    public AdHomeSectionListAdapter(Context context) {
        super(context);
        this.g = context;
    }

    public View a(Context context, int i, NewHomeBlockInfoItem newHomeBlockInfoItem, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.shop_home_ad_item, (ViewGroup) null);
        this.c = (ImageView) inflate.findViewById(R.id.home_ad_item_image);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.c.getLayoutParams();
        layoutParams.width = this.b;
        if (!TextUtils.isEmpty(newHomeBlockInfoItem.mImageHeight)) {
            try {
                Integer.parseInt(newHomeBlockInfoItem.mImageHeight);
            } catch (Exception unused) {
            }
        }
        this.c.setLayoutParams(layoutParams);
        return inflate;
    }

    public void a(View view, int i, final NewHomeBlockInfoItem newHomeBlockInfoItem) {
        if (newHomeBlockInfoItem != null) {
            if (!TextUtils.isEmpty(newHomeBlockInfoItem.getImageUrl())) {
                this.c = (ImageView) view.findViewById(R.id.home_ad_item_image);
            }
            view.setClickable(true);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AdHomeSectionListAdapter.this.a(newHomeBlockInfoItem);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(NewHomeBlockInfoItem newHomeBlockInfoItem) {
        HomeThemeItemClick.a(this.g, newHomeBlockInfoItem);
    }
}
