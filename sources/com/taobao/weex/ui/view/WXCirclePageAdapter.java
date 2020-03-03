package com.taobao.weex.ui.view;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.utils.WXLogUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXCirclePageAdapter extends PagerAdapter {
    private static transient /* synthetic */ boolean[] $jacocoData;
    public boolean isRTL;
    private boolean needLoop;
    private List<View> originalViews;
    private List<View> shadow;
    private List<View> views;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-272778573563523016L, "com/taobao/weex/ui/view/WXCirclePageAdapter", 90);
        $jacocoData = a2;
        return a2;
    }

    public WXCirclePageAdapter(List<View> list, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.views = new ArrayList();
        $jacocoInit[1] = true;
        this.shadow = new ArrayList();
        this.needLoop = true;
        this.isRTL = false;
        $jacocoInit[2] = true;
        this.originalViews = new ArrayList();
        $jacocoInit[3] = true;
        this.views = new ArrayList(list);
        $jacocoInit[4] = true;
        this.originalViews = new ArrayList(list);
        this.needLoop = z;
        $jacocoInit[5] = true;
    }

    public void setLayoutDirectionRTL(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (z == this.isRTL) {
            $jacocoInit[6] = true;
            return;
        }
        this.isRTL = z;
        $jacocoInit[7] = true;
        this.views = new ArrayList(this.originalViews);
        if (!z) {
            $jacocoInit[8] = true;
        } else {
            $jacocoInit[9] = true;
            Collections.reverse(this.views);
            $jacocoInit[10] = true;
        }
        ensureShadow();
        $jacocoInit[11] = true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WXCirclePageAdapter() {
        this(true);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[12] = true;
    }

    public WXCirclePageAdapter(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[13] = true;
        this.views = new ArrayList();
        $jacocoInit[14] = true;
        this.shadow = new ArrayList();
        this.needLoop = true;
        this.isRTL = false;
        $jacocoInit[15] = true;
        this.originalViews = new ArrayList();
        this.needLoop = z;
        $jacocoInit[16] = true;
    }

    public void addPageView(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[17] = true;
        } else {
            $jacocoInit[18] = true;
            WXLogUtils.d("onPageSelected >>>> addPageView");
            $jacocoInit[19] = true;
        }
        this.originalViews.add(view);
        if (this.isRTL) {
            $jacocoInit[20] = true;
            this.views.add(0, view);
            $jacocoInit[21] = true;
        } else {
            this.views.add(view);
            $jacocoInit[22] = true;
        }
        ensureShadow();
        $jacocoInit[23] = true;
    }

    public void removePageView(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[24] = true;
        } else {
            $jacocoInit[25] = true;
            WXLogUtils.d("onPageSelected >>>> removePageView");
            $jacocoInit[26] = true;
        }
        this.views.remove(view);
        $jacocoInit[27] = true;
        this.originalViews.remove(view);
        $jacocoInit[28] = true;
        ensureShadow();
        $jacocoInit[29] = true;
    }

    public void replacePageView(View view, View view2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[30] = true;
        } else {
            $jacocoInit[31] = true;
            WXLogUtils.d("onPageSelected >>>> replacePageView");
            $jacocoInit[32] = true;
        }
        int indexOf = this.views.indexOf(view);
        $jacocoInit[33] = true;
        this.views.remove(indexOf);
        $jacocoInit[34] = true;
        this.views.add(indexOf, view2);
        $jacocoInit[35] = true;
        ensureShadow();
        $jacocoInit[36] = true;
        int indexOf2 = this.originalViews.indexOf(view);
        $jacocoInit[37] = true;
        this.originalViews.remove(indexOf2);
        $jacocoInit[38] = true;
        this.originalViews.add(indexOf2, view2);
        $jacocoInit[39] = true;
    }

    public int getCount() {
        boolean[] $jacocoInit = $jacocoInit();
        int size = this.shadow.size();
        $jacocoInit[40] = true;
        return size;
    }

    public int getRealCount() {
        boolean[] $jacocoInit = $jacocoInit();
        int size = this.views.size();
        $jacocoInit[41] = true;
        return size;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[42] = true;
            view = this.shadow.get(i);
            try {
                $jacocoInit[43] = true;
                if (!WXEnvironment.isApkDebugable()) {
                    $jacocoInit[44] = true;
                } else {
                    $jacocoInit[45] = true;
                    WXLogUtils.d("onPageSelected >>>> instantiateItem >>>>> position:" + i + ",position % getRealCount()" + (i % getRealCount()));
                    $jacocoInit[46] = true;
                }
                if (view.getParent() == null) {
                    $jacocoInit[47] = true;
                    viewGroup.addView(view);
                    $jacocoInit[48] = true;
                } else {
                    ((ViewGroup) view.getParent()).removeView(view);
                    $jacocoInit[49] = true;
                    viewGroup.addView(view);
                    $jacocoInit[50] = true;
                }
                $jacocoInit[51] = true;
            } catch (Exception e) {
                e = e;
                $jacocoInit[52] = true;
                WXLogUtils.e("[CirclePageAdapter] instantiateItem: ", (Throwable) e);
                $jacocoInit[53] = true;
                $jacocoInit[54] = true;
                return view;
            }
        } catch (Exception e2) {
            e = e2;
            view = null;
            $jacocoInit[52] = true;
            WXLogUtils.e("[CirclePageAdapter] instantiateItem: ", (Throwable) e);
            $jacocoInit[53] = true;
            $jacocoInit[54] = true;
            return view;
        }
        $jacocoInit[54] = true;
        return view;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[55] = true;
        } else {
            $jacocoInit[56] = true;
            WXLogUtils.d("onPageSelected >>>> destroyItem >>>>> position:" + i);
            $jacocoInit[57] = true;
        }
        $jacocoInit[58] = true;
    }

    public boolean isViewFromObject(View view, Object obj) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (view == obj) {
            $jacocoInit[59] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[60] = true;
        }
        $jacocoInit[61] = true;
        return z;
    }

    public int getItemPosition(Object obj) {
        $jacocoInit()[62] = true;
        return -2;
    }

    public int getPagePosition(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        int indexOf = this.views.indexOf(view);
        $jacocoInit[63] = true;
        return indexOf;
    }

    public int getItemIndex(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (obj instanceof View) {
            $jacocoInit[64] = true;
            int indexOf = this.views.indexOf(obj);
            $jacocoInit[65] = true;
            return indexOf;
        }
        $jacocoInit[66] = true;
        return -1;
    }

    public List<View> getViews() {
        boolean[] $jacocoInit = $jacocoInit();
        List<View> list = this.views;
        $jacocoInit[67] = true;
        return list;
    }

    private void ensureShadow() {
        boolean[] $jacocoInit = $jacocoInit();
        ArrayList arrayList = new ArrayList();
        $jacocoInit[68] = true;
        if (!this.needLoop) {
            $jacocoInit[69] = true;
        } else if (this.views.size() <= 2) {
            $jacocoInit[70] = true;
        } else {
            $jacocoInit[71] = true;
            arrayList.add(0, this.views.get(this.views.size() - 1));
            $jacocoInit[72] = true;
            $jacocoInit[73] = true;
            for (View add : this.views) {
                $jacocoInit[74] = true;
                arrayList.add(add);
                $jacocoInit[75] = true;
            }
            arrayList.add(this.views.get(0));
            $jacocoInit[76] = true;
            this.shadow.clear();
            $jacocoInit[78] = true;
            notifyDataSetChanged();
            $jacocoInit[79] = true;
            this.shadow.addAll(arrayList);
            $jacocoInit[80] = true;
            notifyDataSetChanged();
            $jacocoInit[81] = true;
        }
        arrayList.addAll(this.views);
        $jacocoInit[77] = true;
        this.shadow.clear();
        $jacocoInit[78] = true;
        notifyDataSetChanged();
        $jacocoInit[79] = true;
        this.shadow.addAll(arrayList);
        $jacocoInit[80] = true;
        notifyDataSetChanged();
        $jacocoInit[81] = true;
    }

    public int getRealPosition(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i < 0) {
            $jacocoInit[82] = true;
        } else if (i >= this.shadow.size()) {
            $jacocoInit[83] = true;
        } else {
            int itemIndex = getItemIndex(this.shadow.get(i));
            $jacocoInit[85] = true;
            return itemIndex;
        }
        $jacocoInit[84] = true;
        return -1;
    }

    public int getFirst() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.needLoop) {
            $jacocoInit[86] = true;
        } else if (this.views.size() <= 2) {
            $jacocoInit[87] = true;
        } else {
            $jacocoInit[88] = true;
            return 1;
        }
        $jacocoInit[89] = true;
        return 0;
    }
}
