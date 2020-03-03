package com.taobao.weex.ui.component.list.template;

import com.taobao.weex.el.parse.ArrayStack;
import java.util.HashMap;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class CellRenderState {
    private static transient /* synthetic */ boolean[] $jacocoData;
    boolean hasDataUpdate = false;
    boolean hasPositionChange = false;
    boolean hasVirtualCompoentUpdate = false;
    long itemId = -1;
    private Map<String, ArrayStack> onceComponentStates;
    int position;
    private Map<String, Object> virtualComponentDatas;
    private Map<String, String> virtualComponentIds;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-5988327554219791803L, "com/taobao/weex/ui/component/list/template/CellRenderState", 26);
        $jacocoData = a2;
        return a2;
    }

    public CellRenderState() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public Map<String, String> getVirtualComponentIds() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.virtualComponentIds != null) {
            $jacocoInit[1] = true;
        } else {
            $jacocoInit[2] = true;
            this.virtualComponentIds = new HashMap(8);
            $jacocoInit[3] = true;
        }
        Map<String, String> map = this.virtualComponentIds;
        $jacocoInit[4] = true;
        return map;
    }

    public boolean hasVirtualComponents() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.virtualComponentIds == null) {
            $jacocoInit[5] = true;
        } else if (this.virtualComponentIds.size() <= 0) {
            $jacocoInit[6] = true;
        } else {
            $jacocoInit[7] = true;
            z = true;
            $jacocoInit[9] = true;
            return z;
        }
        z = false;
        $jacocoInit[8] = true;
        $jacocoInit[9] = true;
        return z;
    }

    public Map<String, Object> getVirtualComponentDatas() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.virtualComponentDatas != null) {
            $jacocoInit[10] = true;
        } else {
            $jacocoInit[11] = true;
            this.virtualComponentDatas = new HashMap(4);
            $jacocoInit[12] = true;
        }
        Map<String, Object> map = this.virtualComponentDatas;
        $jacocoInit[13] = true;
        return map;
    }

    public Map<String, ArrayStack> getOnceComponentStates() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.onceComponentStates != null) {
            $jacocoInit[14] = true;
        } else {
            $jacocoInit[15] = true;
            this.onceComponentStates = new HashMap();
            $jacocoInit[16] = true;
        }
        Map<String, ArrayStack> map = this.onceComponentStates;
        $jacocoInit[17] = true;
        return map;
    }

    public boolean isDirty() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.hasDataUpdate) {
            $jacocoInit[18] = true;
        } else if (this.hasVirtualCompoentUpdate) {
            $jacocoInit[19] = true;
        } else if (this.hasPositionChange) {
            $jacocoInit[20] = true;
        } else {
            z = false;
            $jacocoInit[22] = true;
            $jacocoInit[23] = true;
            return z;
        }
        $jacocoInit[21] = true;
        z = true;
        $jacocoInit[23] = true;
        return z;
    }

    public boolean isHasDataUpdate() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.hasDataUpdate;
        $jacocoInit[24] = true;
        return z;
    }

    public void resetDirty() {
        boolean[] $jacocoInit = $jacocoInit();
        this.hasDataUpdate = false;
        this.hasVirtualCompoentUpdate = false;
        this.hasPositionChange = false;
        $jacocoInit[25] = true;
    }
}
