package com.taobao.weex.ui.view.border;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.NonNull;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

abstract class BorderCorner {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    static final float SWEEP_ANGLE = 45.0f;
    private boolean hasInnerCorner = false;
    private boolean hasOuterCorner = false;
    protected float mAngleBisector;
    private RectF mBorderBox;
    private float mCornerRadius = 0.0f;
    private float mOvalBottom;
    private float mOvalLeft;
    private float mOvalRight;
    private float mOvalTop;
    private float mPostBorderWidth = 0.0f;
    private float mPreBorderWidth = 0.0f;
    private float mRoundCornerEndX;
    private float mRoundCornerEndY;
    private float mRoundCornerStartX;
    private float mRoundCornerStartY;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-74470543422014211L, "com/taobao/weex/ui/view/border/BorderCorner", 60);
        $jacocoData = a2;
        return a2;
    }

    /* access modifiers changed from: protected */
    public abstract void prepareOval();

    /* access modifiers changed from: protected */
    public abstract void prepareRoundCorner();

    BorderCorner() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x010a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void set(float r6, float r7, float r8, @android.support.annotation.NonNull android.graphics.RectF r9, float r10) {
        /*
            r5 = this;
            boolean[] r0 = $jacocoInit()
            float r1 = r5.mCornerRadius
            boolean r1 = com.taobao.weex.base.FloatUtil.floatsEqual(r1, r6)
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x0011
            r0[r3] = r3
            goto L_0x0061
        L_0x0011:
            float r1 = r5.mPreBorderWidth
            r4 = 2
            r0[r4] = r3
            boolean r1 = com.taobao.weex.base.FloatUtil.floatsEqual(r1, r7)
            if (r1 != 0) goto L_0x0020
            r1 = 3
            r0[r1] = r3
            goto L_0x0061
        L_0x0020:
            float r1 = r5.mPostBorderWidth
            r4 = 4
            r0[r4] = r3
            boolean r1 = com.taobao.weex.base.FloatUtil.floatsEqual(r1, r8)
            if (r1 != 0) goto L_0x002f
            r1 = 5
            r0[r1] = r3
            goto L_0x0061
        L_0x002f:
            float r1 = r5.mAngleBisector
            r4 = 6
            r0[r4] = r3
            boolean r1 = com.taobao.weex.base.FloatUtil.floatsEqual(r1, r10)
            if (r1 != 0) goto L_0x003e
            r1 = 7
            r0[r1] = r3
            goto L_0x0061
        L_0x003e:
            android.graphics.RectF r1 = r5.mBorderBox
            if (r1 != 0) goto L_0x0047
            r1 = 8
            r0[r1] = r3
            goto L_0x0057
        L_0x0047:
            android.graphics.RectF r1 = r5.mBorderBox
            r4 = 9
            r0[r4] = r3
            boolean r1 = r1.equals(r9)
            if (r1 != 0) goto L_0x005d
            r1 = 10
            r0[r1] = r3
        L_0x0057:
            r1 = 13
            r0[r1] = r3
            r1 = 0
            goto L_0x0066
        L_0x005d:
            r1 = 11
            r0[r1] = r3
        L_0x0061:
            r1 = 12
            r0[r1] = r3
            r1 = 1
        L_0x0066:
            if (r1 != 0) goto L_0x006e
            r6 = 14
            r0[r6] = r3
            goto L_0x011c
        L_0x006e:
            r5.mCornerRadius = r6
            r5.mPreBorderWidth = r7
            r5.mPostBorderWidth = r8
            r5.mBorderBox = r9
            r5.mAngleBisector = r10
            r6 = 15
            r0[r6] = r3
            float r6 = r5.mCornerRadius
            r7 = 0
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 > 0) goto L_0x0088
            r6 = 16
            r0[r6] = r3
            goto L_0x0094
        L_0x0088:
            float r6 = r5.mCornerRadius
            boolean r6 = com.taobao.weex.base.FloatUtil.floatsEqual(r7, r6)
            if (r6 == 0) goto L_0x009a
            r6 = 17
            r0[r6] = r3
        L_0x0094:
            r6 = 19
            r0[r6] = r3
            r6 = 0
            goto L_0x009f
        L_0x009a:
            r6 = 18
            r0[r6] = r3
            r6 = 1
        L_0x009f:
            r5.hasOuterCorner = r6
            boolean r6 = r5.hasOuterCorner
            if (r6 != 0) goto L_0x00aa
            r6 = 20
            r0[r6] = r3
            goto L_0x00f5
        L_0x00aa:
            r6 = 21
            r0[r6] = r3
            float r6 = r5.getPreBorderWidth()
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 >= 0) goto L_0x00bb
            r6 = 22
            r0[r6] = r3
            goto L_0x00f5
        L_0x00bb:
            r6 = 23
            r0[r6] = r3
            float r6 = r5.getPostBorderWidth()
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 >= 0) goto L_0x00cc
            r6 = 24
            r0[r6] = r3
            goto L_0x00f5
        L_0x00cc:
            r6 = 25
            r0[r6] = r3
            float r6 = r5.getOuterCornerRadius()
            float r7 = r5.getPreBorderWidth()
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 > 0) goto L_0x00e1
            r6 = 26
            r0[r6] = r3
            goto L_0x00f5
        L_0x00e1:
            r6 = 27
            r0[r6] = r3
            float r6 = r5.getOuterCornerRadius()
            float r7 = r5.getPostBorderWidth()
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 > 0) goto L_0x00fa
            r6 = 28
            r0[r6] = r3
        L_0x00f5:
            r6 = 30
            r0[r6] = r3
            goto L_0x00ff
        L_0x00fa:
            r6 = 29
            r0[r6] = r3
            r2 = 1
        L_0x00ff:
            r5.hasInnerCorner = r2
            boolean r6 = r5.hasOuterCorner
            if (r6 != 0) goto L_0x010a
            r6 = 31
            r0[r6] = r3
            goto L_0x0115
        L_0x010a:
            r6 = 32
            r0[r6] = r3
            r5.prepareOval()
            r6 = 33
            r0[r6] = r3
        L_0x0115:
            r5.prepareRoundCorner()
            r6 = 34
            r0[r6] = r3
        L_0x011c:
            r6 = 35
            r0[r6] = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.view.border.BorderCorner.set(float, float, float, android.graphics.RectF, float):void");
    }

    public final void drawRoundedCorner(@NonNull Canvas canvas, @NonNull Paint paint, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!hasOuterCorner()) {
            canvas.drawLine(getRoundCornerStartX(), getRoundCornerStartY(), getRoundCornerEndX(), getRoundCornerEndY(), paint);
            $jacocoInit[39] = true;
        } else if (Build.VERSION.SDK_INT >= 21) {
            $jacocoInit[36] = true;
            canvas.drawArc(this.mOvalLeft, this.mOvalTop, this.mOvalRight, this.mOvalBottom, f, SWEEP_ANGLE, false, paint);
            $jacocoInit[37] = true;
        } else {
            canvas.drawArc(new RectF(this.mOvalLeft, this.mOvalTop, this.mOvalRight, this.mOvalBottom), f, SWEEP_ANGLE, false, paint);
            $jacocoInit[38] = true;
        }
        $jacocoInit[40] = true;
    }

    public final float getRoundCornerStartX() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mRoundCornerStartX;
        $jacocoInit[41] = true;
        return f;
    }

    /* access modifiers changed from: package-private */
    public final void setRoundCornerStartX(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRoundCornerStartX = f;
        $jacocoInit[42] = true;
    }

    public final float getRoundCornerStartY() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mRoundCornerStartY;
        $jacocoInit[43] = true;
        return f;
    }

    /* access modifiers changed from: package-private */
    public final void setRoundCornerStartY(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRoundCornerStartY = f;
        $jacocoInit[44] = true;
    }

    public final float getRoundCornerEndX() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mRoundCornerEndX;
        $jacocoInit[45] = true;
        return f;
    }

    /* access modifiers changed from: package-private */
    public final void setRoundCornerEndX(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRoundCornerEndX = f;
        $jacocoInit[46] = true;
    }

    public final float getRoundCornerEndY() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mRoundCornerEndY;
        $jacocoInit[47] = true;
        return f;
    }

    /* access modifiers changed from: package-private */
    public final void setRoundCornerEndY(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRoundCornerEndY = f;
        $jacocoInit[48] = true;
    }

    /* access modifiers changed from: package-private */
    public final void setOvalLeft(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mOvalLeft = f;
        $jacocoInit[49] = true;
    }

    /* access modifiers changed from: package-private */
    public final void setOvalTop(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mOvalTop = f;
        $jacocoInit[50] = true;
    }

    /* access modifiers changed from: package-private */
    public final void setOvalRight(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mOvalRight = f;
        $jacocoInit[51] = true;
    }

    /* access modifiers changed from: package-private */
    public final void setOvalBottom(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mOvalBottom = f;
        $jacocoInit[52] = true;
    }

    /* access modifiers changed from: package-private */
    public boolean hasInnerCorner() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.hasInnerCorner;
        $jacocoInit[53] = true;
        return z;
    }

    /* access modifiers changed from: package-private */
    public boolean hasOuterCorner() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.hasOuterCorner;
        $jacocoInit[54] = true;
        return z;
    }

    /* access modifiers changed from: protected */
    public final float getPreBorderWidth() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mPreBorderWidth;
        $jacocoInit[55] = true;
        return f;
    }

    /* access modifiers changed from: protected */
    public final float getPostBorderWidth() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mPostBorderWidth;
        $jacocoInit[56] = true;
        return f;
    }

    /* access modifiers changed from: protected */
    public final float getOuterCornerRadius() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mCornerRadius;
        $jacocoInit[57] = true;
        return f;
    }

    /* access modifiers changed from: protected */
    public final float getAngleBisectorDegree() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mAngleBisector;
        $jacocoInit[58] = true;
        return f;
    }

    /* access modifiers changed from: protected */
    public final RectF getBorderBox() {
        boolean[] $jacocoInit = $jacocoInit();
        RectF rectF = this.mBorderBox;
        $jacocoInit[59] = true;
        return rectF;
    }
}
