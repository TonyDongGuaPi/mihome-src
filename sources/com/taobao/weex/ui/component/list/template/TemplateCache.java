package com.taobao.weex.ui.component.list.template;

import com.taobao.weex.ui.component.list.WXCell;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class TemplateCache {
    private static transient /* synthetic */ boolean[] $jacocoData;
    ConcurrentLinkedQueue<WXCell> cells = new ConcurrentLinkedQueue<>();
    boolean isLoadIng = false;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-9113842157883561769L, "com/taobao/weex/ui/component/list/template/TemplateCache", 2);
        $jacocoData = a2;
        return a2;
    }

    TemplateCache() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
    }
}
