package com.taobao.weex.yp_compoment.pullrefresh.youpinptr.indicator;

import android.graphics.PointF;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class PtrIndicator {
    private static transient /* synthetic */ boolean[] $jacocoData;
    public static final int POS_START = 0;
    private int mCurrentPos;
    private int mHeaderHeight;
    private boolean mIsUnderTouch;
    private int mLastPos;
    private int mOffsetToKeepHeaderWhileLoading;
    protected int mOffsetToRefresh = 0;
    private float mOffsetX;
    private float mOffsetY;
    private int mPressedPos;
    private PointF mPtLastMove;
    private float mRatioOfHeaderHeightToRefresh;
    private int mRefreshCompleteY;
    private float mResistance;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-8717774270037041271L, "com/taobao/weex/yp_compoment/pullrefresh/youpinptr/indicator/PtrIndicator", 84);
        $jacocoData = a2;
        return a2;
    }

    public PtrIndicator() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.mPtLastMove = new PointF();
        this.mCurrentPos = 0;
        this.mLastPos = 0;
        this.mPressedPos = 0;
        this.mRatioOfHeaderHeightToRefresh = 1.2f;
        this.mResistance = 1.7f;
        this.mIsUnderTouch = false;
        this.mOffsetToKeepHeaderWhileLoading = -1;
        this.mRefreshCompleteY = 0;
        $jacocoInit[1] = true;
    }

    public boolean isUnderTouch() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mIsUnderTouch;
        $jacocoInit[2] = true;
        return z;
    }

    public float getResistance() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mResistance;
        $jacocoInit[3] = true;
        return f;
    }

    public void setResistance(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mResistance = f;
        $jacocoInit[4] = true;
    }

    public void onRelease() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mIsUnderTouch = false;
        $jacocoInit[5] = true;
    }

    public void onUIRefreshComplete() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRefreshCompleteY = this.mCurrentPos;
        $jacocoInit[6] = true;
    }

    public boolean goDownCrossFinishPosition() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mCurrentPos >= this.mRefreshCompleteY) {
            $jacocoInit[7] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[8] = true;
        }
        $jacocoInit[9] = true;
        return z;
    }

    /* access modifiers changed from: protected */
    public void processOnMove(float f, float f2, float f3, float f4) {
        boolean[] $jacocoInit = $jacocoInit();
        setOffset(f3, f4 / this.mResistance);
        $jacocoInit[10] = true;
    }

    public void setRatioOfHeaderHeightToRefresh(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRatioOfHeaderHeightToRefresh = f;
        this.mOffsetToRefresh = (int) (((float) this.mHeaderHeight) * f);
        $jacocoInit[11] = true;
    }

    public float getRatioOfHeaderToHeightRefresh() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mRatioOfHeaderHeightToRefresh;
        $jacocoInit[12] = true;
        return f;
    }

    public int getOffsetToRefresh() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mOffsetToRefresh;
        $jacocoInit[13] = true;
        return i;
    }

    public void setOffsetToRefresh(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRatioOfHeaderHeightToRefresh = (((float) this.mHeaderHeight) * 1.0f) / ((float) i);
        this.mOffsetToRefresh = i;
        $jacocoInit[14] = true;
    }

    public void onPressDown(float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mIsUnderTouch = true;
        this.mPressedPos = this.mCurrentPos;
        $jacocoInit[15] = true;
        this.mPtLastMove.set(f, f2);
        $jacocoInit[16] = true;
    }

    public final void onMove(float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[17] = true;
        processOnMove(f, f2, f - this.mPtLastMove.x, f2 - this.mPtLastMove.y);
        $jacocoInit[18] = true;
        this.mPtLastMove.set(f, f2);
        $jacocoInit[19] = true;
    }

    /* access modifiers changed from: protected */
    public void setOffset(float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mOffsetX = f;
        this.mOffsetY = f2;
        $jacocoInit[20] = true;
    }

    public float getOffsetX() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mOffsetX;
        $jacocoInit[21] = true;
        return f;
    }

    public float getOffsetY() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mOffsetY;
        $jacocoInit[22] = true;
        return f;
    }

    public int getLastPosY() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mLastPos;
        $jacocoInit[23] = true;
        return i;
    }

    public int getCurrentPosY() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mCurrentPos;
        $jacocoInit[24] = true;
        return i;
    }

    public final void setCurrentPos(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mLastPos = this.mCurrentPos;
        this.mCurrentPos = i;
        $jacocoInit[25] = true;
        onUpdatePos(i, this.mLastPos);
        $jacocoInit[26] = true;
    }

    /* access modifiers changed from: protected */
    public void onUpdatePos(int i, int i2) {
        $jacocoInit()[27] = true;
    }

    public int getHeaderHeight() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mHeaderHeight;
        $jacocoInit[28] = true;
        return i;
    }

    public void setHeaderHeight(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mHeaderHeight = i;
        $jacocoInit[29] = true;
        updateHeight();
        $jacocoInit[30] = true;
    }

    /* access modifiers changed from: protected */
    public void updateHeight() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mOffsetToRefresh = (int) (this.mRatioOfHeaderHeightToRefresh * ((float) this.mHeaderHeight));
        $jacocoInit[31] = true;
    }

    public void convertFrom(PtrIndicator ptrIndicator) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mCurrentPos = ptrIndicator.mCurrentPos;
        this.mLastPos = ptrIndicator.mLastPos;
        this.mHeaderHeight = ptrIndicator.mHeaderHeight;
        $jacocoInit[32] = true;
    }

    public boolean hasLeftStartPosition() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mCurrentPos > 0) {
            $jacocoInit[33] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[34] = true;
        }
        $jacocoInit[35] = true;
        return z;
    }

    public boolean hasJustLeftStartPosition() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLastPos != 0) {
            $jacocoInit[36] = true;
        } else if (!hasLeftStartPosition()) {
            $jacocoInit[37] = true;
        } else {
            $jacocoInit[38] = true;
            z = true;
            $jacocoInit[40] = true;
            return z;
        }
        z = false;
        $jacocoInit[39] = true;
        $jacocoInit[40] = true;
        return z;
    }

    public boolean hasJustBackToStartPosition() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLastPos == 0) {
            $jacocoInit[41] = true;
        } else if (!isInStartPosition()) {
            $jacocoInit[42] = true;
        } else {
            $jacocoInit[43] = true;
            z = true;
            $jacocoInit[45] = true;
            return z;
        }
        z = false;
        $jacocoInit[44] = true;
        $jacocoInit[45] = true;
        return z;
    }

    public boolean isOverOffsetToRefresh() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mCurrentPos >= getOffsetToRefresh()) {
            $jacocoInit[46] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[47] = true;
        }
        $jacocoInit[48] = true;
        return z;
    }

    public boolean hasMovedAfterPressedDown() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mCurrentPos != this.mPressedPos) {
            $jacocoInit[49] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[50] = true;
        }
        $jacocoInit[51] = true;
        return z;
    }

    public boolean isInStartPosition() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mCurrentPos == 0) {
            $jacocoInit[52] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[53] = true;
        }
        $jacocoInit[54] = true;
        return z;
    }

    public boolean crossRefreshLineFromTopToBottom() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLastPos >= getOffsetToRefresh()) {
            $jacocoInit[55] = true;
        } else if (this.mCurrentPos < getOffsetToRefresh()) {
            $jacocoInit[56] = true;
        } else {
            $jacocoInit[57] = true;
            z = true;
            $jacocoInit[59] = true;
            return z;
        }
        z = false;
        $jacocoInit[58] = true;
        $jacocoInit[59] = true;
        return z;
    }

    public boolean hasJustReachedHeaderHeightFromTopToBottom() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLastPos >= this.mHeaderHeight) {
            $jacocoInit[60] = true;
        } else if (this.mCurrentPos < this.mHeaderHeight) {
            $jacocoInit[61] = true;
        } else {
            $jacocoInit[62] = true;
            z = true;
            $jacocoInit[64] = true;
            return z;
        }
        z = false;
        $jacocoInit[63] = true;
        $jacocoInit[64] = true;
        return z;
    }

    public boolean isOverOffsetToKeepHeaderWhileLoading() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mCurrentPos > getOffsetToKeepHeaderWhileLoading()) {
            $jacocoInit[65] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[66] = true;
        }
        $jacocoInit[67] = true;
        return z;
    }

    public void setOffsetToKeepHeaderWhileLoading(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mOffsetToKeepHeaderWhileLoading = i;
        $jacocoInit[68] = true;
    }

    public int getOffsetToKeepHeaderWhileLoading() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mOffsetToKeepHeaderWhileLoading >= 0) {
            i = this.mOffsetToKeepHeaderWhileLoading;
            $jacocoInit[69] = true;
        } else {
            i = this.mHeaderHeight;
            $jacocoInit[70] = true;
        }
        $jacocoInit[71] = true;
        return i;
    }

    public boolean isAlreadyHere(int i) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mCurrentPos == i) {
            $jacocoInit[72] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[73] = true;
        }
        $jacocoInit[74] = true;
        return z;
    }

    public float getLastPercent() {
        float f;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mHeaderHeight == 0) {
            f = 0.0f;
            $jacocoInit[75] = true;
        } else {
            f = (((float) this.mLastPos) * 1.0f) / ((float) this.mHeaderHeight);
            $jacocoInit[76] = true;
        }
        $jacocoInit[77] = true;
        return f;
    }

    public float getCurrentPercent() {
        float f;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mHeaderHeight == 0) {
            f = 0.0f;
            $jacocoInit[78] = true;
        } else {
            f = (((float) this.mCurrentPos) * 1.0f) / ((float) this.mHeaderHeight);
            $jacocoInit[79] = true;
        }
        $jacocoInit[80] = true;
        return f;
    }

    public boolean willOverTop(int i) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (i < 0) {
            $jacocoInit[81] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[82] = true;
        }
        $jacocoInit[83] = true;
        return z;
    }
}
