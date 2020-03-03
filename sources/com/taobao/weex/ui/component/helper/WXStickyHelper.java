package com.taobao.weex.ui.component.helper;

import com.taobao.weex.ui.component.Scrollable;
import com.taobao.weex.ui.component.WXComponent;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXStickyHelper {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private Scrollable scrollable;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(2749293608268057455L, "com/taobao/weex/ui/component/helper/WXStickyHelper", 15);
        $jacocoData = a2;
        return a2;
    }

    public WXStickyHelper(Scrollable scrollable2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.scrollable = scrollable2;
        $jacocoInit[0] = true;
    }

    public void bindStickStyle(WXComponent wXComponent, Map<String, Map<String, WXComponent>> map) {
        boolean[] $jacocoInit = $jacocoInit();
        Scrollable parentScroller = wXComponent.getParentScroller();
        if (parentScroller == null) {
            $jacocoInit[1] = true;
            return;
        }
        $jacocoInit[2] = true;
        String ref = parentScroller.getRef();
        $jacocoInit[3] = true;
        Map map2 = map.get(ref);
        if (map2 != null) {
            $jacocoInit[4] = true;
        } else {
            $jacocoInit[5] = true;
            map2 = new ConcurrentHashMap();
            $jacocoInit[6] = true;
        }
        if (map2.containsKey(wXComponent.getRef())) {
            $jacocoInit[7] = true;
            return;
        }
        map2.put(wXComponent.getRef(), wXComponent);
        $jacocoInit[8] = true;
        map.put(parentScroller.getRef(), map2);
        $jacocoInit[9] = true;
    }

    public void unbindStickStyle(WXComponent wXComponent, Map<String, Map<String, WXComponent>> map) {
        boolean[] $jacocoInit = $jacocoInit();
        Scrollable parentScroller = wXComponent.getParentScroller();
        if (parentScroller == null) {
            $jacocoInit[10] = true;
            return;
        }
        $jacocoInit[11] = true;
        String ref = parentScroller.getRef();
        $jacocoInit[12] = true;
        Map map2 = map.get(ref);
        if (map2 == null) {
            $jacocoInit[13] = true;
            return;
        }
        map2.remove(wXComponent.getRef());
        $jacocoInit[14] = true;
    }
}
