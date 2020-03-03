package com.xiaomi.shopviews.widget.hdhorizontalrecyclergoods;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.xiaomi.base.utils.PlainUtils;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.widget.R;
import java.util.ArrayList;
import java.util.Iterator;

public class ViewHelperProductTagArray {

    /* renamed from: a  reason: collision with root package name */
    private LinearLayout f13238a;
    private Context b;

    public ViewHelperProductTagArray(View view) {
        a(view);
    }

    private void a(String str) {
        if (this.b != null) {
            ImageView imageView = new ImageView(this.b);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 16;
            this.f13238a.addView(imageView, layoutParams);
            a(imageView, str);
        }
    }

    private void a(ArrayList<String> arrayList) {
        this.f13238a.removeAllViews();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (!TextUtils.isEmpty(next)) {
                a(next);
            }
        }
    }

    private void a(View view) {
        this.f13238a = (LinearLayout) view.findViewById(R.id.viewhelper_product_tag_array_root);
        this.b = view.getContext();
    }

    public void a(HomeSectionItem homeSectionItem) {
        ArrayList<String> arrayList = homeSectionItem.mProductTagArray;
        if (arrayList == null || arrayList.size() == 0) {
            this.f13238a.setVisibility(0);
            a(arrayList);
            return;
        }
        this.f13238a.setVisibility(8);
    }

    public void a(ImageView imageView, String str) {
        if (!TextUtils.isEmpty(str)) {
            PlainUtils.WH a2 = PlainUtils.a(str);
            if (a2 != null) {
                int i = a2.b;
                int i2 = a2.f10034a;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = new LinearLayout.LayoutParams(i, i2);
                    layoutParams.gravity = 16;
                }
                layoutParams.width = i;
                layoutParams.height = i2;
                imageView.setLayoutParams(layoutParams);
            }
            imageView.setVisibility(0);
            return;
        }
        imageView.setVisibility(8);
    }
}
