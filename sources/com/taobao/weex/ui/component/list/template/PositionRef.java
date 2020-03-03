package com.taobao.weex.ui.component.list.template;

import com.alibaba.fastjson.JSONAware;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class PositionRef extends Number implements JSONAware {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private CellRenderState renderState;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-339891760153054090L, "com/taobao/weex/ui/component/list/template/PositionRef", 9);
        $jacocoData = a2;
        return a2;
    }

    public PositionRef(CellRenderState cellRenderState) {
        boolean[] $jacocoInit = $jacocoInit();
        this.renderState = cellRenderState;
        $jacocoInit[0] = true;
    }

    public int intValue() {
        boolean[] $jacocoInit = $jacocoInit();
        int position = getPosition();
        $jacocoInit[1] = true;
        return position;
    }

    public long longValue() {
        boolean[] $jacocoInit = $jacocoInit();
        long position = (long) getPosition();
        $jacocoInit[2] = true;
        return position;
    }

    public float floatValue() {
        boolean[] $jacocoInit = $jacocoInit();
        float position = (float) getPosition();
        $jacocoInit[3] = true;
        return position;
    }

    public double doubleValue() {
        boolean[] $jacocoInit = $jacocoInit();
        double position = (double) getPosition();
        $jacocoInit[4] = true;
        return position;
    }

    private int getPosition() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.renderState == null) {
            $jacocoInit[5] = true;
            return -1;
        }
        int i = this.renderState.position;
        $jacocoInit[6] = true;
        return i;
    }

    public String toString() {
        boolean[] $jacocoInit = $jacocoInit();
        String valueOf = String.valueOf(getPosition());
        $jacocoInit[7] = true;
        return valueOf;
    }

    public String toJSONString() {
        boolean[] $jacocoInit = $jacocoInit();
        String valueOf = String.valueOf(getPosition());
        $jacocoInit[8] = true;
        return valueOf;
    }
}
