package com.xiaomi.shopviews.widget.homeproduct2type4container;

import android.content.Context;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.base.utils.ImageAdapUtil;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.IHomeItemView;
import java.util.ArrayList;

public class HomeProduct2Type4ContainerView extends LinearLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private ArrayList<HomeProduct2ItemType4ChildView> f13314a = new ArrayList<>();

    public HomeProduct2Type4ContainerView(Context context) {
        super(context);
    }

    public void bind(HomeSection homeSection, int i, int i2) {
        HomeProduct2ItemType4ChildView homeProduct2ItemType4ChildView;
        ArrayList<HomeSectionItem> arrayList = homeSection.mBody != null ? homeSection.mBody.mItems : null;
        if (arrayList != null) {
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                if (i3 < this.f13314a.size() && (homeProduct2ItemType4ChildView = this.f13314a.get(i3)) != null) {
                    homeProduct2ItemType4ChildView.bind(arrayList.get(i3));
                }
            }
        }
    }

    public void draw(HomeSection homeSection) {
        setBackgroundColor(-1);
        if ((homeSection.mBody != null ? homeSection.mBody.mItems : null) != null) {
            int e = ScreenInfo.a().e();
            ImageAdapUtil.ImageInfo a2 = ImageAdapUtil.a(e, 537, 484, 1080);
            int i = a2.b;
            int i2 = a2.f10029a;
            int a3 = Coder.a(getContext(), 2.0f);
            int i3 = (e - a3) / 2;
            int i4 = (i2 * i3) / i;
            AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new AbsListView.LayoutParams(-1, -1);
            }
            setLayoutParams(layoutParams);
            setMinimumHeight(i4);
            for (int i5 = 0; i5 < 2; i5++) {
                HomeProduct2ItemType4ChildView homeProduct2ItemType4ChildView = new HomeProduct2ItemType4ChildView(getContext());
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams2.width = i3;
                layoutParams2.height = i4;
                if (i5 < 2) {
                    layoutParams2.rightMargin = a3;
                }
                addView(homeProduct2ItemType4ChildView, layoutParams2);
                this.f13314a.add(homeProduct2ItemType4ChildView);
            }
        }
    }
}
