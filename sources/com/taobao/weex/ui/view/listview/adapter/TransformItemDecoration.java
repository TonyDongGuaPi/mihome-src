package com.taobao.weex.ui.view.listview.adapter;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class TransformItemDecoration extends RecyclerView.ItemDecoration {
    private static transient /* synthetic */ boolean[] $jacocoData;
    float mAlpha = -1.0f;
    boolean mIsVertical = true;
    int mRotation = 0;
    float mScaleX = 0.0f;
    float mScaleY = 0.0f;
    int mXTranslate = 0;
    int mYTranslate = 0;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-2969105070216724714L, "com/taobao/weex/ui/view/listview/adapter/TransformItemDecoration", 33);
        $jacocoData = a2;
        return a2;
    }

    public TransformItemDecoration(boolean z, float f, int i, int i2, int i3, float f2, float f3) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mIsVertical = z;
        this.mAlpha = f;
        this.mXTranslate = i;
        this.mYTranslate = i2;
        this.mRotation = i3;
        this.mScaleX = f2;
        this.mScaleY = f3;
        $jacocoInit[0] = true;
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onDrawOver(canvas, recyclerView, state);
        $jacocoInit[1] = true;
        int width = recyclerView.getWidth();
        $jacocoInit[2] = true;
        int height = recyclerView.getHeight();
        $jacocoInit[3] = true;
        int childCount = recyclerView.getChildCount();
        $jacocoInit[4] = true;
        int i = 0;
        while (i < childCount) {
            $jacocoInit[5] = true;
            updateItem(recyclerView.getChildAt(i), width, height);
            i++;
            $jacocoInit[6] = true;
        }
        $jacocoInit[7] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00f2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateItem(android.view.View r7, int r8, int r9) {
        /*
            r6 = this;
            boolean[] r0 = $jacocoInit()
            boolean r1 = r6.mIsVertical
            r2 = 1
            if (r1 == 0) goto L_0x0024
            r8 = 8
            r0[r8] = r2
            int r8 = r7.getHeight()
            r1 = 9
            r0[r1] = r2
            int r1 = r7.getTop()
            int r3 = r8 / 2
            int r1 = r1 + r3
            r3 = 10
            r0[r3] = r2
            r5 = r9
            r9 = r8
            r8 = r5
            goto L_0x003b
        L_0x0024:
            r9 = 11
            r0[r9] = r2
            int r9 = r7.getWidth()
            r1 = 12
            r0[r1] = r2
            int r1 = r7.getLeft()
            int r3 = r9 / 2
            int r1 = r1 + r3
            r3 = 13
            r0[r3] = r2
        L_0x003b:
            int r9 = r9 + r8
            int r9 = r9 / 2
            r3 = 14
            r0[r3] = r2
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r9 = (float) r9
            r4 = 1065353216(0x3f800000, float:1.0)
            float r9 = r4 / r9
            int r8 = r8 / 2
            int r1 = r1 - r8
            float r8 = (float) r1
            float r9 = r9 * r8
            float r8 = java.lang.Math.max(r3, r9)
            float r8 = java.lang.Math.min(r4, r8)
            float r9 = r6.mAlpha
            r1 = 0
            int r9 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r9 > 0) goto L_0x0063
            r9 = 15
            r0[r9] = r2
            goto L_0x0078
        L_0x0063:
            r9 = 16
            r0[r9] = r2
            float r9 = r6.mAlpha
            float r3 = java.lang.Math.abs(r8)
            float r9 = r9 * r3
            float r9 = r4 - r9
            r7.setAlpha(r9)
            r9 = 17
            r0[r9] = r2
        L_0x0078:
            float r9 = r6.mScaleX
            int r9 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r9 <= 0) goto L_0x0083
            r9 = 18
            r0[r9] = r2
            goto L_0x0092
        L_0x0083:
            float r9 = r6.mScaleY
            int r9 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r9 > 0) goto L_0x008e
            r9 = 19
            r0[r9] = r2
            goto L_0x00b3
        L_0x008e:
            r9 = 20
            r0[r9] = r2
        L_0x0092:
            float r9 = r6.mScaleX
            float r1 = java.lang.Math.abs(r8)
            float r9 = r9 * r1
            float r9 = r4 - r9
            r7.setScaleX(r9)
            r9 = 21
            r0[r9] = r2
            float r9 = r6.mScaleY
            float r1 = java.lang.Math.abs(r8)
            float r9 = r9 * r1
            float r4 = r4 - r9
            r7.setScaleY(r4)
            r9 = 22
            r0[r9] = r2
        L_0x00b3:
            int r9 = r6.mRotation
            if (r9 != 0) goto L_0x00bc
            r9 = 23
            r0[r9] = r2
            goto L_0x00cc
        L_0x00bc:
            r9 = 24
            r0[r9] = r2
            int r9 = r6.mRotation
            float r9 = (float) r9
            float r9 = r9 * r8
            r7.setRotation(r9)
            r9 = 25
            r0[r9] = r2
        L_0x00cc:
            int r9 = r6.mXTranslate
            if (r9 != 0) goto L_0x00d5
            r9 = 26
            r0[r9] = r2
            goto L_0x00e9
        L_0x00d5:
            r9 = 27
            r0[r9] = r2
            int r9 = r6.mXTranslate
            float r9 = (float) r9
            float r1 = java.lang.Math.abs(r8)
            float r9 = r9 * r1
            r7.setTranslationX(r9)
            r9 = 28
            r0[r9] = r2
        L_0x00e9:
            int r9 = r6.mYTranslate
            if (r9 != 0) goto L_0x00f2
            r7 = 29
            r0[r7] = r2
            goto L_0x0106
        L_0x00f2:
            r9 = 30
            r0[r9] = r2
            int r9 = r6.mYTranslate
            float r9 = (float) r9
            float r8 = java.lang.Math.abs(r8)
            float r9 = r9 * r8
            r7.setTranslationY(r9)
            r7 = 31
            r0[r7] = r2
        L_0x0106:
            r7 = 32
            r0[r7] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.view.listview.adapter.TransformItemDecoration.updateItem(android.view.View, int, int):void");
    }
}
