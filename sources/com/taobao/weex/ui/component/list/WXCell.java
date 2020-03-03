package com.taobao.weex.ui.component.list;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.performance.WXInstanceApm;
import com.taobao.weex.ui.ComponentCreator;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXHeader;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.flat.WidgetContainer;
import com.taobao.weex.ui.view.WXFrameLayout;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXCell extends WidgetContainer<WXFrameLayout> {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private CellAppendTreeListener cellAppendTreeListener;
    private boolean isAppendTreeDone;
    private boolean isSourceUsed = false;
    private boolean mFlatUIEnabled = false;
    private View mHeadView;
    private int mLastLocationY = 0;
    private ViewGroup mRealView;
    private int mScrollPosition = -1;
    private View mTempStickyView;
    private Object renderData;

    public interface CellAppendTreeListener {
        void onAppendTreeDone();
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5725458339347382242L, "com/taobao/weex/ui/component/list/WXCell", 108);
        $jacocoData = a2;
        return a2;
    }

    public static class Creator implements ComponentCreator {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-3349038994908786325L, "com/taobao/weex/ui/component/list/WXCell$Creator", 2);
            $jacocoData = a2;
            return a2;
        }

        public Creator() {
            $jacocoInit()[0] = true;
        }

        public WXComponent createInstance(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) throws IllegalAccessException, InvocationTargetException, InstantiationException {
            boolean[] $jacocoInit = $jacocoInit();
            WXCell wXCell = new WXCell(wXSDKInstance, wXVContainer, true, basicComponentData);
            $jacocoInit[1] = true;
            return wXCell;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXCell(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXCell(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
        lazy(true);
        if (Build.VERSION.SDK_INT >= 21) {
            $jacocoInit[2] = true;
        } else {
            try {
                $jacocoInit[3] = true;
                WXAttr attrs = getAttrs();
                $jacocoInit[4] = true;
                if (!attrs.containsKey(Constants.Name.FLAT)) {
                    $jacocoInit[5] = true;
                } else {
                    $jacocoInit[6] = true;
                    this.mFlatUIEnabled = WXUtils.getBoolean(attrs.get(Constants.Name.FLAT), false).booleanValue();
                    $jacocoInit[7] = true;
                }
                $jacocoInit[8] = true;
            } catch (NullPointerException e) {
                $jacocoInit[9] = true;
                WXLogUtils.e("Cell", WXLogUtils.getStackTrace(e));
                $jacocoInit[10] = true;
            }
        }
        if (canRecycled()) {
            $jacocoInit[11] = true;
        } else {
            $jacocoInit[12] = true;
            wXSDKInstance.getApmForInstance().updateDiffStats(WXInstanceApm.KEY_PAGE_STATS_CELL_DATA_UN_RECYCLE_NUM, 1.0d);
            $jacocoInit[13] = true;
        }
        if (!TextUtils.isEmpty(getAttrs().getScope())) {
            $jacocoInit[14] = true;
        } else {
            $jacocoInit[15] = true;
            wXSDKInstance.getApmForInstance().updateDiffStats(WXInstanceApm.KEY_PAGE_STATS_CELL_UN_RE_USE_NUM, 1.0d);
            $jacocoInit[16] = true;
        }
        $jacocoInit[17] = true;
    }

    public boolean isLazy() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (!super.isLazy()) {
            $jacocoInit[18] = true;
        } else if (isFixed()) {
            $jacocoInit[19] = true;
        } else {
            $jacocoInit[20] = true;
            z = true;
            $jacocoInit[22] = true;
            return z;
        }
        z = false;
        $jacocoInit[21] = true;
        $jacocoInit[22] = true;
        return z;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean isFlatUIEnabled() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mFlatUIEnabled;
        $jacocoInit[23] = true;
        return z;
    }

    /* access modifiers changed from: protected */
    public WXFrameLayout initComponentHostView(@NonNull Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        if (isSticky()) {
            $jacocoInit[24] = true;
        } else if (this instanceof WXHeader) {
            $jacocoInit[25] = true;
        } else {
            WXFrameLayout wXFrameLayout = new WXFrameLayout(context);
            this.mRealView = wXFrameLayout;
            $jacocoInit[33] = true;
            if (!isFlatUIEnabled()) {
                $jacocoInit[34] = true;
            } else {
                $jacocoInit[35] = true;
                wXFrameLayout.setLayerType(2, (Paint) null);
                $jacocoInit[36] = true;
            }
            $jacocoInit[37] = true;
            return wXFrameLayout;
        }
        WXFrameLayout wXFrameLayout2 = new WXFrameLayout(context);
        $jacocoInit[26] = true;
        this.mRealView = new WXFrameLayout(context);
        $jacocoInit[27] = true;
        wXFrameLayout2.addView(this.mRealView);
        $jacocoInit[28] = true;
        if (!isFlatUIEnabled()) {
            $jacocoInit[29] = true;
        } else {
            $jacocoInit[30] = true;
            wXFrameLayout2.setLayerType(2, (Paint) null);
            $jacocoInit[31] = true;
        }
        $jacocoInit[32] = true;
        return wXFrameLayout2;
    }

    public int getLocationFromStart() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mLastLocationY;
        $jacocoInit[38] = true;
        return i;
    }

    public void setLocationFromStart(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mLastLocationY = i;
        $jacocoInit[39] = true;
    }

    /* access modifiers changed from: package-private */
    public void setScrollPositon(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mScrollPosition = i;
        $jacocoInit[40] = true;
    }

    public int getScrollPositon() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mScrollPosition;
        $jacocoInit[41] = true;
        return i;
    }

    public ViewGroup getRealView() {
        boolean[] $jacocoInit = $jacocoInit();
        ViewGroup viewGroup = this.mRealView;
        $jacocoInit[42] = true;
        return viewGroup;
    }

    public void removeSticky() {
        boolean[] $jacocoInit = $jacocoInit();
        if (((WXFrameLayout) getHostView()).getChildCount() <= 0) {
            $jacocoInit[43] = true;
        } else {
            $jacocoInit[44] = true;
            this.mHeadView = ((WXFrameLayout) getHostView()).getChildAt(0);
            int[] iArr = new int[2];
            int[] iArr2 = new int[2];
            $jacocoInit[45] = true;
            ((WXFrameLayout) getHostView()).getLocationOnScreen(iArr);
            $jacocoInit[46] = true;
            getParentScroller().getView().getLocationOnScreen(iArr2);
            int i = iArr[0] - iArr2[0];
            $jacocoInit[47] = true;
            int top = getParent().getHostView().getTop();
            $jacocoInit[48] = true;
            ((WXFrameLayout) getHostView()).removeView(this.mHeadView);
            this.mRealView = (ViewGroup) this.mHeadView;
            $jacocoInit[49] = true;
            this.mTempStickyView = new FrameLayout(getContext());
            $jacocoInit[50] = true;
            $jacocoInit[51] = true;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) getLayoutWidth(), (int) getLayoutHeight());
            $jacocoInit[52] = true;
            ((WXFrameLayout) getHostView()).addView(this.mTempStickyView, layoutParams);
            $jacocoInit[53] = true;
            this.mHeadView.setTranslationX((float) i);
            $jacocoInit[54] = true;
            this.mHeadView.setTranslationY((float) top);
            $jacocoInit[55] = true;
        }
        $jacocoInit[56] = true;
    }

    public void recoverySticky() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mHeadView == null) {
            $jacocoInit[57] = true;
        } else {
            $jacocoInit[58] = true;
            if (this.mHeadView.getLayoutParams() == null) {
                $jacocoInit[59] = true;
            } else {
                $jacocoInit[60] = true;
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mHeadView.getLayoutParams();
                if (marginLayoutParams.topMargin <= 0) {
                    $jacocoInit[61] = true;
                } else {
                    marginLayoutParams.topMargin = 0;
                    $jacocoInit[62] = true;
                }
            }
            if (this.mHeadView.getVisibility() == 0) {
                $jacocoInit[63] = true;
            } else {
                $jacocoInit[64] = true;
                this.mHeadView.setVisibility(0);
                $jacocoInit[65] = true;
            }
            if (this.mHeadView.getParent() == null) {
                $jacocoInit[66] = true;
            } else {
                $jacocoInit[67] = true;
                ((ViewGroup) this.mHeadView.getParent()).removeView(this.mHeadView);
                $jacocoInit[68] = true;
            }
            ((WXFrameLayout) getHostView()).removeView(this.mTempStickyView);
            $jacocoInit[69] = true;
            ((WXFrameLayout) getHostView()).addView(this.mHeadView);
            $jacocoInit[70] = true;
            this.mHeadView.setTranslationX(0.0f);
            $jacocoInit[71] = true;
            this.mHeadView.setTranslationY(0.0f);
            $jacocoInit[72] = true;
        }
        $jacocoInit[73] = true;
    }

    /* access modifiers changed from: protected */
    public void mountFlatGUI() {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[74] = true;
        } else {
            if (this.widgets != null) {
                $jacocoInit[75] = true;
            } else {
                $jacocoInit[76] = true;
                this.widgets = new LinkedList();
                $jacocoInit[77] = true;
            }
            ((WXFrameLayout) getHostView()).mountFlatGUI(this.widgets);
            $jacocoInit[78] = true;
        }
        $jacocoInit[79] = true;
    }

    public void unmountFlatGUI() {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[80] = true;
        } else {
            $jacocoInit[81] = true;
            ((WXFrameLayout) getHostView()).unmountFlatGUI();
            $jacocoInit[82] = true;
        }
        $jacocoInit[83] = true;
    }

    public boolean intendToBeFlatContainer() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (!getInstance().getFlatUIContext().isFlatUIEnabled(this)) {
            $jacocoInit[84] = true;
        } else if (!WXCell.class.equals(getClass())) {
            $jacocoInit[85] = true;
        } else if (isSticky()) {
            $jacocoInit[86] = true;
        } else {
            $jacocoInit[87] = true;
            z = true;
            $jacocoInit[89] = true;
            return z;
        }
        z = false;
        $jacocoInit[88] = true;
        $jacocoInit[89] = true;
        return z;
    }

    public int getStickyOffset() {
        boolean[] $jacocoInit = $jacocoInit();
        if (getAttrs().get(Constants.Name.STICKY_OFFSET) == null) {
            $jacocoInit[90] = true;
            return 0;
        }
        float f = WXUtils.getFloat(getAttrs().get(Constants.Name.STICKY_OFFSET));
        $jacocoInit[91] = true;
        int realPxByWidth = (int) WXViewUtils.getRealPxByWidth(f, getViewPortWidth());
        $jacocoInit[92] = true;
        return realPxByWidth;
    }

    public Object getRenderData() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = this.renderData;
        $jacocoInit[93] = true;
        return obj;
    }

    public void setRenderData(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        this.renderData = obj;
        $jacocoInit[94] = true;
    }

    public boolean isSourceUsed() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.isSourceUsed;
        $jacocoInit[95] = true;
        return z;
    }

    public void setSourceUsed(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.isSourceUsed = z;
        $jacocoInit[96] = true;
    }

    public boolean isAppendTreeDone() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.isAppendTreeDone;
        $jacocoInit[97] = true;
        return z;
    }

    public void appendTreeCreateFinish() {
        boolean[] $jacocoInit = $jacocoInit();
        super.appendTreeCreateFinish();
        this.isAppendTreeDone = true;
        if (this.cellAppendTreeListener == null) {
            $jacocoInit[98] = true;
        } else {
            $jacocoInit[99] = true;
            this.cellAppendTreeListener.onAppendTreeDone();
            $jacocoInit[100] = true;
        }
        $jacocoInit[101] = true;
    }

    public void setCellAppendTreeListener(CellAppendTreeListener cellAppendTreeListener2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.cellAppendTreeListener = cellAppendTreeListener2;
        if (!this.isAppendTreeDone) {
            $jacocoInit[102] = true;
        } else {
            $jacocoInit[103] = true;
            cellAppendTreeListener2.onAppendTreeDone();
            $jacocoInit[104] = true;
        }
        $jacocoInit[105] = true;
    }
}
