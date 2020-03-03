package com.taobao.weex.layout;

import java.io.Serializable;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class MeasureSize implements Serializable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private float height;
    private float width;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-7273184348427468064L, "com/taobao/weex/layout/MeasureSize", 5);
        $jacocoData = a2;
        return a2;
    }

    public MeasureSize() {
        $jacocoInit()[0] = true;
    }

    public float getWidth() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.width;
        $jacocoInit[1] = true;
        return f;
    }

    public void setWidth(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.width = f;
        $jacocoInit[2] = true;
    }

    public float getHeight() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.height;
        $jacocoInit[3] = true;
        return f;
    }

    public void setHeight(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.height = f;
        $jacocoInit[4] = true;
    }
}
