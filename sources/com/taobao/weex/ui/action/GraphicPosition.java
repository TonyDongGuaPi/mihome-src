package com.taobao.weex.ui.action;

import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicPosition {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private float mBottom;
    private float mLeft;
    private float mRight;
    private float mTop;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8605411994838243633L, "com/taobao/weex/ui/action/GraphicPosition", 10);
        $jacocoData = a2;
        return a2;
    }

    public GraphicPosition(float f, float f2, float f3, float f4) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mLeft = f;
        this.mTop = f2;
        this.mRight = f3;
        this.mBottom = f4;
        $jacocoInit[0] = true;
    }

    public float getLeft() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mLeft;
        $jacocoInit[1] = true;
        return f;
    }

    public void setLeft(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mLeft = f;
        $jacocoInit[2] = true;
    }

    public float getTop() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mTop;
        $jacocoInit[3] = true;
        return f;
    }

    public void setTop(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mTop = f;
        $jacocoInit[4] = true;
    }

    public float getRight() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mRight;
        $jacocoInit[5] = true;
        return f;
    }

    public void setRight(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRight = f;
        $jacocoInit[6] = true;
    }

    public float getBottom() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mBottom;
        $jacocoInit[7] = true;
        return f;
    }

    public void setBottom(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBottom = f;
        $jacocoInit[8] = true;
    }

    public void update(float f, float f2, float f3, float f4) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mTop = f;
        this.mBottom = f2;
        this.mLeft = f3;
        this.mRight = f4;
        $jacocoInit[9] = true;
    }
}
