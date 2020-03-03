package com.taobao.weex.ui.component.list.template;

import com.taobao.weex.el.parse.ArrayStack;
import java.util.HashMap;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class CellRenderContext {
    private static transient /* synthetic */ boolean[] $jacocoData;
    public Map map;
    public int position;
    public CellRenderState renderState;
    public ArrayStack stack = new ArrayStack();
    public WXRecyclerTemplateList templateList;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(1896200784228062492L, "com/taobao/weex/ui/component/list/template/CellRenderContext", 14);
        $jacocoData = a2;
        return a2;
    }

    public CellRenderContext() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
        this.map = new HashMap(8);
        $jacocoInit[2] = true;
    }

    public CellRenderState getRenderState() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.renderState == null) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            this.renderState = this.templateList.getCellDataManager().getRenderState(this.position);
            $jacocoInit[5] = true;
        }
        CellRenderState cellRenderState = this.renderState;
        $jacocoInit[6] = true;
        return cellRenderState;
    }

    public void clear() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.stack.getList().size() <= 0) {
            $jacocoInit[7] = true;
        } else {
            $jacocoInit[8] = true;
            this.stack.getList().clear();
            $jacocoInit[9] = true;
        }
        if (this.map.size() <= 0) {
            $jacocoInit[10] = true;
        } else {
            $jacocoInit[11] = true;
            this.map.clear();
            $jacocoInit[12] = true;
        }
        this.renderState = null;
        this.position = 0;
        this.templateList = null;
        $jacocoInit[13] = true;
    }
}
