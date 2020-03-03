package com.xiaomi.shopviews.widget.homelisttwotype7;

import android.content.Context;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.IHomeItemView;
import java.util.ArrayList;

public class HomeListTwoType7View extends LinearLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private ArrayList<HomePicContentProgressView> f13269a = new ArrayList<>(2);

    public HomeListTwoType7View(Context context) {
        super(context);
    }

    public void bind(HomeSection homeSection, int i, int i2) {
        HomePicContentProgressView homePicContentProgressView;
        ArrayList<HomeSectionItem> arrayList = homeSection.mBody != null ? homeSection.mBody.mItems : null;
        if (arrayList != null) {
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                if (i3 < this.f13269a.size() && (homePicContentProgressView = this.f13269a.get(i3)) != null) {
                    homePicContentProgressView.bind(arrayList.get(i3));
                }
            }
        }
    }

    public void draw(HomeSection homeSection) {
        setBackgroundColor(-1);
        if ((homeSection.mBody != null ? homeSection.mBody.mItems : null) != null) {
            AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new AbsListView.LayoutParams(-1, -1);
            }
            setLayoutParams(layoutParams);
            int e = ScreenInfo.a().e();
            int a2 = Coder.a(getContext(), 2.0f);
            float f = (float) ((e - a2) / 6);
            int i = (int) (3.0f * f);
            int i2 = (int) (f * 4.0f);
            int i3 = (((e - i) - i) - a2) / 2;
            if (i3 >= 1) {
                i += i3;
            }
            int i4 = i;
            HomePicContentProgressView homePicContentProgressView = new HomePicContentProgressView(getContext(), i, i2);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams2.width = i;
            addView(homePicContentProgressView, layoutParams2);
            this.f13269a.add(homePicContentProgressView);
            HomePicContentProgressView homePicContentProgressView2 = new HomePicContentProgressView(getContext(), i4, i2);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams3.width = i4;
            layoutParams3.leftMargin = (e - i) - i4;
            addView(homePicContentProgressView2, layoutParams3);
            this.f13269a.add(homePicContentProgressView2);
        }
    }
}
