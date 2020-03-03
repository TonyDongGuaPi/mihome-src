package com.xiaomi.shopviews.widget.homelisttwotypetcp;

import android.content.Context;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.IHomeItemView;
import java.util.ArrayList;

public class HomeListTwoTypeTCPView extends LinearLayout implements IHomeItemView {
    protected int imgHeightRatio = 1;
    protected int imgLeftWidthRatio = 1;
    protected int imgRightWidthRatio = 1;
    protected ArrayList<HomePicContentDescVerticalView> mProductViews = new ArrayList<>(2);

    public HomeListTwoTypeTCPView(Context context) {
        super(context);
    }

    public HomeListTwoTypeTCPView(Context context, int i, int i2, int i3) {
        super(context);
        this.imgLeftWidthRatio = i;
        this.imgRightWidthRatio = i2;
        this.imgHeightRatio = i3;
    }

    public void bind(HomeSection homeSection, int i, int i2) {
        HomePicContentDescVerticalView homePicContentDescVerticalView;
        ArrayList<HomeSectionItem> arrayList = homeSection.mBody != null ? homeSection.mBody.mItems : null;
        if (arrayList != null) {
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                if (i3 < this.mProductViews.size() && (homePicContentDescVerticalView = this.mProductViews.get(i3)) != null) {
                    homePicContentDescVerticalView.bind(homeSection.mViewType, arrayList.get(i3));
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
            float f = (float) ((e - a2) / (this.imgLeftWidthRatio + this.imgRightWidthRatio));
            int i = (int) (((float) this.imgLeftWidthRatio) * f);
            int i2 = (int) (((float) this.imgRightWidthRatio) * f);
            int i3 = (int) (f * ((float) this.imgHeightRatio));
            int i4 = (((e - i) - i2) - a2) / 2;
            if (i4 >= 1) {
                i += i4;
                i2 += i4;
            }
            HomePicContentDescVerticalView homePicContentDescVerticalView = new HomePicContentDescVerticalView(getContext(), i, i3);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams2.width = i;
            addView(homePicContentDescVerticalView, layoutParams2);
            this.mProductViews.add(homePicContentDescVerticalView);
            HomePicContentDescVerticalView homePicContentDescVerticalView2 = new HomePicContentDescVerticalView(getContext(), i2, i3);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams3.width = i2;
            layoutParams3.leftMargin = (e - i) - i2;
            addView(homePicContentDescVerticalView2, layoutParams3);
            this.mProductViews.add(homePicContentDescVerticalView2);
        }
    }
}
