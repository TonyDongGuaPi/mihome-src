package com.taobao.weex.yp_compoment.pullrefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.taobao.weex.yp_compoment.pullrefresh.youpinptr.PtrDefaultHandler;
import com.taobao.weex.yp_compoment.pullrefresh.youpinptr.PtrFrameLayout;
import com.taobao.weex.yp_compoment.pullrefresh.youpinptr.PtrHandler;
import com.taobao.weex.yp_compoment.pullrefresh.youpinptr.XRePtrFrameLayout;
import com.taobao.weex.yp_compoment.pullrefresh.youpinptr.indicator.PtrIndicator;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class YPRefreshLayout extends XRePtrFrameLayout implements PtrHandler {
    private static transient /* synthetic */ boolean[] $jacocoData;
    PtrIndicator mPtrIndicator;
    private WXOnLoadingListener onLoadingListener;
    private WXOnRefreshListener onRefreshListener;

    public interface WXOnLoadingListener {
        void onLoading();

        void onPullingUp(float f, int i, float f2);
    }

    public interface WXOnRefreshListener {
        void onPullingDown(float f, int i, float f2);

        void onRefresh();
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(56474899361912350L, "com/taobao/weex/yp_compoment/pullrefresh/YPRefreshLayout", 36);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public YPRefreshLayout(Context context) {
        this(context, (AttributeSet) null);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public YPRefreshLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public YPRefreshLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[2] = true;
        init();
        $jacocoInit[3] = true;
    }

    private void init() {
        boolean[] $jacocoInit = $jacocoInit();
        setPullToRefresh(false);
        $jacocoInit[4] = true;
        disableWhenHorizontalMove(true);
        $jacocoInit[5] = true;
        this.mPtrIndicator = new PtrIndicator();
        $jacocoInit[6] = true;
        setPtrIndicator(this.mPtrIndicator);
        $jacocoInit[7] = true;
        setResistance(1.7f);
        $jacocoInit[8] = true;
        setRatioOfHeaderHeightToRefresh(1.2f);
        $jacocoInit[9] = true;
        setDurationToClose(300);
        $jacocoInit[10] = true;
        setDurationToCloseHeader(500);
        $jacocoInit[11] = true;
        setKeepHeaderWhenRefresh(true);
        $jacocoInit[12] = true;
        setPtrHandler(this);
        $jacocoInit[13] = true;
    }

    public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mPtrIndicator.getCurrentPosY() > 0) {
            $jacocoInit[14] = true;
            boolean checkContentCanBePulledDown = PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, view, view2);
            $jacocoInit[15] = true;
            return checkContentCanBePulledDown;
        } else if (getMoveX() > getMoveY()) {
            $jacocoInit[16] = true;
            return false;
        } else {
            boolean checkContentCanBePulledDown2 = PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, view, view2);
            $jacocoInit[17] = true;
            return checkContentCanBePulledDown2;
        }
    }

    public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.onRefreshListener == null) {
            $jacocoInit[18] = true;
        } else {
            $jacocoInit[19] = true;
            this.onRefreshListener.onRefresh();
            $jacocoInit[20] = true;
        }
        $jacocoInit[21] = true;
    }

    /* access modifiers changed from: protected */
    public void onPositionChange(boolean z, byte b, PtrIndicator ptrIndicator) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onPositionChange(z, b, ptrIndicator);
        if (this.onRefreshListener == null) {
            $jacocoInit[22] = true;
        } else {
            $jacocoInit[23] = true;
            this.onRefreshListener.onPullingDown(ptrIndicator.getOffsetY(), (int) ptrIndicator.getOffsetY(), (float) ptrIndicator.getHeaderHeight());
            $jacocoInit[24] = true;
        }
        $jacocoInit[25] = true;
    }

    public ViewGroup getFooterView() {
        $jacocoInit()[26] = true;
        return null;
    }

    public void setFooterView(View view) {
        $jacocoInit()[27] = true;
    }

    public void setLoadingHeight(int i) {
        $jacocoInit()[28] = true;
    }

    public void setPullRefreshEnable(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        setEnabled(z);
        $jacocoInit[29] = true;
        setPullToRefresh(z);
        $jacocoInit[30] = true;
    }

    public void setPullLoadEnable(boolean z) {
        $jacocoInit()[31] = true;
    }

    public void setOnRefreshListener(WXOnRefreshListener wXOnRefreshListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.onRefreshListener = wXOnRefreshListener;
        $jacocoInit[32] = true;
    }

    public void setOnLoadingListener(WXOnLoadingListener wXOnLoadingListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.onLoadingListener = wXOnLoadingListener;
        $jacocoInit[33] = true;
    }

    public void finishPullRefresh() {
        boolean[] $jacocoInit = $jacocoInit();
        refreshComplete();
        $jacocoInit[34] = true;
    }

    public void finishPullLoad() {
        $jacocoInit()[35] = true;
    }
}
