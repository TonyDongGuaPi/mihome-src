package com.mi.global.bbs.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bigkoo.convenientbanner.holder.Holder;
import com.mi.global.bbs.R;
import com.mi.global.bbs.utils.ImageLoader;

public class NetworkImageHolderView implements Holder<String> {
    private ImageView imageView;

    public View createView(Context context) {
        this.imageView = (ImageView) View.inflate(context, R.layout.bbs_home_gallary_image, (ViewGroup) null);
        return this.imageView;
    }

    public void UpdateUI(Context context, int i, String str) {
        ImageLoader.showImage(this.imageView, str);
    }
}
