package cn.bingoogolapple.bgabanner;

import android.content.Context;
import android.widget.Scroller;

public class BGABannerScroller extends Scroller {

    /* renamed from: a  reason: collision with root package name */
    private int f537a = 1000;

    public BGABannerScroller(Context context, int i) {
        super(context);
        this.f537a = i;
    }

    public void startScroll(int i, int i2, int i3, int i4) {
        super.startScroll(i, i2, i3, i4, this.f537a);
    }

    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        super.startScroll(i, i2, i3, i4, this.f537a);
    }
}
