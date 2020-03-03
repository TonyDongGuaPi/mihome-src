package com.taobao.weex.ui.component;

import android.graphics.Rect;
import android.view.View;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class AppearanceHelper {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final int APPEAR = 0;
    public static final int DISAPPEAR = 1;
    public static final int RESULT_APPEAR = 1;
    public static final int RESULT_DISAPPEAR = -1;
    public static final int RESULT_NO_CHANGE = 0;
    private boolean mAppearStatus;
    private final WXComponent mAwareChild;
    private int mCellPositionInScrollable;
    private Rect mVisibleRect;
    private boolean[] mWatchFlags;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8322955704545366937L, "com/taobao/weex/ui/component/AppearanceHelper", 37);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AppearanceHelper(WXComponent wXComponent) {
        this(wXComponent, 0);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public AppearanceHelper(WXComponent wXComponent, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mAppearStatus = false;
        this.mWatchFlags = new boolean[]{false, false};
        $jacocoInit[1] = true;
        this.mVisibleRect = new Rect();
        this.mAwareChild = wXComponent;
        this.mCellPositionInScrollable = i;
        $jacocoInit[2] = true;
    }

    public void setCellPosition(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mCellPositionInScrollable = i;
        $jacocoInit[3] = true;
    }

    public int getCellPositionINScollable() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mCellPositionInScrollable;
        $jacocoInit[4] = true;
        return i;
    }

    public void setWatchEvent(int i, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWatchFlags[i] = z;
        $jacocoInit[5] = true;
    }

    public boolean isWatch() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = false;
        if (this.mWatchFlags[0]) {
            $jacocoInit[6] = true;
        } else if (this.mWatchFlags[1]) {
            $jacocoInit[7] = true;
        } else {
            $jacocoInit[9] = true;
            $jacocoInit[10] = true;
            return z;
        }
        $jacocoInit[8] = true;
        z = true;
        $jacocoInit[10] = true;
        return z;
    }

    public WXComponent getAwareChild() {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent wXComponent = this.mAwareChild;
        $jacocoInit[11] = true;
        return wXComponent;
    }

    public boolean isAppear() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mAppearStatus;
        $jacocoInit[12] = true;
        return z;
    }

    public int setAppearStatus(boolean z) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mAppearStatus != z) {
            this.mAppearStatus = z;
            if (z) {
                $jacocoInit[13] = true;
                i = 1;
            } else {
                i = -1;
                $jacocoInit[14] = true;
            }
            $jacocoInit[15] = true;
            return i;
        }
        $jacocoInit[16] = true;
        return 0;
    }

    public boolean isViewVisible(boolean z) {
        boolean z2;
        boolean[] $jacocoInit = $jacocoInit();
        View hostView = this.mAwareChild.getHostView();
        if (!z) {
            $jacocoInit[17] = true;
        } else {
            $jacocoInit[18] = true;
            if (hostView.getVisibility() != 0) {
                $jacocoInit[19] = true;
            } else {
                $jacocoInit[20] = true;
                if (hostView.getMeasuredHeight() != 0) {
                    $jacocoInit[21] = true;
                } else {
                    $jacocoInit[22] = true;
                    return true;
                }
            }
        }
        if (hostView == null) {
            $jacocoInit[23] = true;
        } else if (!hostView.getLocalVisibleRect(this.mVisibleRect)) {
            $jacocoInit[24] = true;
        } else {
            $jacocoInit[25] = true;
            z2 = true;
            $jacocoInit[27] = true;
            return z2;
        }
        z2 = false;
        $jacocoInit[26] = true;
        $jacocoInit[27] = true;
        return z2;
    }

    public boolean isViewVisible(View view) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (view.getVisibility() != 0) {
            $jacocoInit[28] = true;
        } else {
            $jacocoInit[29] = true;
            if (view.getMeasuredHeight() != 0) {
                $jacocoInit[30] = true;
            } else {
                $jacocoInit[31] = true;
                return true;
            }
        }
        if (view == null) {
            $jacocoInit[32] = true;
        } else if (!view.getLocalVisibleRect(this.mVisibleRect)) {
            $jacocoInit[33] = true;
        } else {
            $jacocoInit[34] = true;
            z = true;
            $jacocoInit[36] = true;
            return z;
        }
        z = false;
        $jacocoInit[35] = true;
        $jacocoInit[36] = true;
        return z;
    }
}
