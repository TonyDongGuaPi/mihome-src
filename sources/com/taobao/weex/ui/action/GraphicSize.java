package com.taobao.weex.ui.action;

import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class GraphicSize {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private float mHeight;
    private float mWidth;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6408659639466364448L, "com/taobao/weex/ui/action/GraphicSize", 6);
        $jacocoData = a2;
        return a2;
    }

    public GraphicSize(float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWidth = f;
        this.mHeight = f2;
        $jacocoInit[0] = true;
    }

    public float getWidth() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mWidth;
        $jacocoInit[1] = true;
        return f;
    }

    public void setWidth(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWidth = f;
        $jacocoInit[2] = true;
    }

    public float getHeight() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mHeight;
        $jacocoInit[3] = true;
        return f;
    }

    public void setHeight(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mHeight = f;
        $jacocoInit[4] = true;
    }

    public void update(float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWidth = f;
        this.mHeight = f2;
        $jacocoInit[5] = true;
    }
}
