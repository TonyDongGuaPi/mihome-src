package com.mi.global.bbs.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.HomeBanner;
import com.mi.global.bbs.utils.ImageLoader;
import java.util.List;

public class LiteBannerAdapter extends PagerAdapter {
    private List<HomeBanner> bannerList;
    private Context context;

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public LiteBannerAdapter(Context context2, List<HomeBanner> list) {
        this.context = context2;
        this.bannerList = list;
    }

    public int getCount() {
        return this.bannerList.size();
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.bbs_lite_banner_item, (ViewGroup) null);
        ImageLoader.showImage((ImageView) inflate.findViewById(R.id.banner_img), this.bannerList.get(i).getPic_url());
        viewGroup.addView(inflate);
        return inflate;
    }
}
