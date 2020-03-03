package com.mi.global.bbs.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.bigkoo.convenientbanner.holder.Holder;

public class LocalImageHolderView implements Holder<Integer> {
    private ImageView imageView;

    public View createView(Context context) {
        this.imageView = new ImageView(context);
        this.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return this.imageView;
    }

    public void UpdateUI(Context context, int i, Integer num) {
        this.imageView.setImageResource(num.intValue());
    }
}
