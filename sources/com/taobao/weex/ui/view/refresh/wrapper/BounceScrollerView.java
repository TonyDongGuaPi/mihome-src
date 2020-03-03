package com.taobao.weex.ui.view.refresh.wrapper;

import android.content.Context;
import com.taobao.weex.ui.component.WXScroller;
import com.taobao.weex.ui.view.WXScrollView;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class BounceScrollerView extends BaseBounceView<WXScrollView> {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(3929158436811855451L, "com/taobao/weex/ui/view/refresh/wrapper/BounceScrollerView", 10);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BounceScrollerView(Context context, int i, WXScroller wXScroller) {
        super(context, i);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        init(context);
        $jacocoInit[1] = true;
        if (getInnerView() == null) {
            $jacocoInit[2] = true;
        } else {
            $jacocoInit[3] = true;
            ((WXScrollView) getInnerView()).setWAScroller(wXScroller);
            $jacocoInit[4] = true;
        }
        $jacocoInit[5] = true;
    }

    public WXScrollView setInnerView(Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        WXScrollView wXScrollView = new WXScrollView(context);
        $jacocoInit[6] = true;
        return wXScrollView;
    }

    public void onRefreshingComplete() {
        $jacocoInit()[7] = true;
    }

    public void onLoadmoreComplete() {
        $jacocoInit()[8] = true;
    }
}
