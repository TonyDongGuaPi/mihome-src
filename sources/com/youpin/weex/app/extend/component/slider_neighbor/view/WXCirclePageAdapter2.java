package com.youpin.weex.app.extend.component.slider_neighbor.view;

import android.view.View;
import android.view.ViewGroup;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.utils.WXLogUtils;
import java.util.ArrayList;
import java.util.List;

public class WXCirclePageAdapter2 extends BaseBannerPagerAdapter {
    private boolean initLoop;
    private List<View> views;

    public int getItemPosition(Object obj) {
        return -2;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public WXCirclePageAdapter2() {
        this(true);
        this.initLoop = true;
    }

    public WXCirclePageAdapter2(boolean z) {
        this.initLoop = false;
        this.views = new ArrayList();
        this.initLoop = z;
        setInnerLoop(z);
    }

    public void setLoop(boolean z) {
        setInnerLoop(z);
        this.initLoop = z;
    }

    public int getActualCount() {
        if (this.views == null) {
            return 0;
        }
        return this.views.size();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view;
        try {
            int actualPosition = getActualPosition(i);
            view = this.views.get(actualPosition);
            try {
                if (WXEnvironment.isApkDebugable()) {
                    WXLogUtils.d("onPageSelected >>>> instantiateItem >>>>> position:" + actualPosition + ",position % getRealCount()" + (actualPosition % getActualCount()));
                }
                if (view.getParent() == null) {
                    viewGroup.addView(view);
                } else {
                    ((ViewGroup) view.getParent()).removeView(view);
                    viewGroup.addView(view);
                }
            } catch (Exception e) {
                e = e;
                WXLogUtils.e("[CirclePageAdapter] instantiateItem: ", (Throwable) e);
                return view;
            }
        } catch (Exception e2) {
            e = e2;
            view = null;
            WXLogUtils.e("[CirclePageAdapter] instantiateItem: ", (Throwable) e);
            return view;
        }
        return view;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (WXEnvironment.isApkDebugable()) {
            WXLogUtils.d("onPageSelected >>>> destroyItem >>>>> position:" + i);
        }
    }

    public int getRealPosition(int i) {
        return getActualPosition(i);
    }

    public void addPageView(View view) {
        if (WXEnvironment.isApkDebugable()) {
            WXLogUtils.d("onPageSelected >>>> addPageView");
        }
        this.views.add(view);
        if (this.views.size() > 2) {
            setInnerLoop(this.initLoop);
        } else {
            setInnerLoop(false);
        }
        notifyDataSetChanged();
    }

    public void removePageView(View view) {
        if (WXEnvironment.isApkDebugable()) {
            WXLogUtils.d("onPageSelected >>>> removePageView");
        }
        this.views.remove(view);
        if (this.views.size() > 2) {
            setInnerLoop(this.initLoop);
        } else {
            setInnerLoop(false);
        }
        notifyDataSetChanged();
    }

    public void replacePageView(View view, View view2) {
        if (WXEnvironment.isApkDebugable()) {
            WXLogUtils.d("onPageSelected >>>> replacePageView");
        }
        int indexOf = this.views.indexOf(view);
        this.views.remove(indexOf);
        this.views.add(indexOf, view2);
        notifyDataSetChanged();
    }

    public List<View> getViews() {
        return this.views;
    }

    public int getRealCount() {
        return getActualCount();
    }

    public int getPagePosition(View view) {
        return this.views.indexOf(view);
    }

    public int getFirst() {
        return getFirstPostion();
    }
}
