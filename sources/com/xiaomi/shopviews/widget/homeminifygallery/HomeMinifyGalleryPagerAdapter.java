package com.xiaomi.shopviews.widget.homeminifygallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.utils.FontUtils;
import com.xiaomi.shopviews.adapter.adapter.BasePageAdapter;
import com.xiaomi.shopviews.adapter.adapter.UIAdapter;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.utils.PriceRegUtils;
import com.xiaomi.shopviews.widget.R;

public class HomeMinifyGalleryPagerAdapter extends BasePageAdapter<HomeSectionItem> {
    private Context b;

    /* access modifiers changed from: private */
    public void a(HomeSectionItem homeSectionItem) {
    }

    public float getPageWidth(int i) {
        return 0.7777778f;
    }

    public HomeMinifyGalleryPagerAdapter(Context context) {
        super(context);
        this.b = context;
    }

    public void a(View view, int i, final HomeSectionItem homeSectionItem) {
        if (homeSectionItem != null) {
            TextView textView = (TextView) view.findViewById(R.id.listitem_home_minify_gallery_viewpager_price);
            ImageLoader.a().a(homeSectionItem.mImageUrl, (ImageView) view.findViewById(R.id.listitem_home_minify_gallery_viewpager_image));
            ((TextView) view.findViewById(R.id.listitem_home_minify_gallery_viewpager_title)).setText(homeSectionItem.mCommentBrief);
            ((TextView) view.findViewById(R.id.listitem_home_minify_gallery_viewpager_from)).setText(this.b.getString(R.string.listitem_home_minify_gallery_viewpager_from_text, new Object[]{homeSectionItem.mCommentAuthor}));
            ((TextView) view.findViewById(R.id.listitem_home_minify_gallery_viewpager_name)).setText(homeSectionItem.mProductName);
            PriceRegUtils.a(textView, homeSectionItem.mProductPrice);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    HomeMinifyGalleryPagerAdapter.this.a(homeSectionItem);
                }
            });
            FontUtils.a(this.b, textView);
        }
    }

    public int getItemPosition(Object obj) {
        return this.f13068a.contains(((View) obj).getTag()) ? -1 : -2;
    }

    public View a(Context context, HomeSectionItem homeSectionItem, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.listitem_home_minify_gallerypager, viewGroup, false);
        int a2 = UIAdapter.a().a(UIAdapter.m);
        int a3 = UIAdapter.a().a(UIAdapter.l);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.height = a3;
        layoutParams.width = a2;
        ((ImageView) inflate.findViewById(R.id.listitem_home_minify_gallery_viewpager_image)).setLayoutParams(layoutParams);
        return inflate;
    }
}
