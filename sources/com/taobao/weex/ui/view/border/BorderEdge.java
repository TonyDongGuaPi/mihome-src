package com.taobao.weex.ui.view.border;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import com.taobao.weex.dom.CSSShorthand;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class BorderEdge {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private float mBorderWidth;
    private CSSShorthand.EDGE mEdge;
    @NonNull
    private BorderCorner mPostCorner;
    @NonNull
    private BorderCorner mPreCorner;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3250630790144767374L, "com/taobao/weex/ui/view/border/BorderEdge", 13);
        $jacocoData = a2;
        return a2;
    }

    BorderEdge() {
        $jacocoInit()[0] = true;
    }

    /* access modifiers changed from: package-private */
    public BorderEdge set(@NonNull BorderCorner borderCorner, @NonNull BorderCorner borderCorner2, float f, CSSShorthand.EDGE edge) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mPreCorner = borderCorner;
        this.mPostCorner = borderCorner2;
        this.mBorderWidth = f;
        this.mEdge = edge;
        $jacocoInit[1] = true;
        return this;
    }

    /* access modifiers changed from: package-private */
    public void drawEdge(@NonNull Canvas canvas, @NonNull Paint paint) {
        boolean[] $jacocoInit = $jacocoInit();
        paint.setStrokeWidth(this.mBorderWidth);
        $jacocoInit[2] = true;
        this.mPreCorner.drawRoundedCorner(canvas, paint, this.mPreCorner.getAngleBisectorDegree());
        $jacocoInit[3] = true;
        paint.setStrokeWidth(this.mBorderWidth);
        $jacocoInit[4] = true;
        float roundCornerEndX = this.mPreCorner.getRoundCornerEndX();
        $jacocoInit[5] = true;
        float roundCornerEndY = this.mPreCorner.getRoundCornerEndY();
        $jacocoInit[6] = true;
        float roundCornerStartX = this.mPostCorner.getRoundCornerStartX();
        $jacocoInit[7] = true;
        float roundCornerStartY = this.mPostCorner.getRoundCornerStartY();
        $jacocoInit[8] = true;
        canvas.drawLine(roundCornerEndX, roundCornerEndY, roundCornerStartX, roundCornerStartY, paint);
        $jacocoInit[9] = true;
        this.mPostCorner.drawRoundedCorner(canvas, paint, this.mPostCorner.getAngleBisectorDegree() - 45.0f);
        $jacocoInit[10] = true;
    }

    public CSSShorthand.EDGE getEdge() {
        boolean[] $jacocoInit = $jacocoInit();
        CSSShorthand.EDGE edge = this.mEdge;
        $jacocoInit[11] = true;
        return edge;
    }

    public float getBorderWidth() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mBorderWidth;
        $jacocoInit[12] = true;
        return f;
    }
}
