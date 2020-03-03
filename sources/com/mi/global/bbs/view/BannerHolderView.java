package com.mi.global.bbs.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bigkoo.convenientbanner.holder.Holder;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.HomeBanner;
import com.mi.global.bbs.utils.ImageLoader;

public class BannerHolderView implements Holder<HomeBanner> {
    private ImageView mImageView;

    public View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.bbs_lite_banner_item, (ViewGroup) null);
        this.mImageView = (ImageView) inflate.findViewById(R.id.banner_img);
        return inflate;
    }

    public void UpdateUI(Context context, int i, HomeBanner homeBanner) {
        if (homeBanner != null && !TextUtils.isEmpty(homeBanner.getPic_url())) {
            ImageLoader.showImage(this.mImageView, homeBanner.getPic_url().replaceAll(" ", "%20"));
        }
    }
}
