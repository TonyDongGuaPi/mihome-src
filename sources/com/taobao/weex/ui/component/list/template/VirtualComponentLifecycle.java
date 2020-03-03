package com.taobao.weex.ui.component.list.template;

import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class VirtualComponentLifecycle {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String ATTACH = "attach";
    public static final String CREATE = "create";
    public static final String DETACH = "detach";
    public static final String LIFECYCLE = "lifecycle";
    public static final String SYNSTATE = "syncState";

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-222869369400119830L, "com/taobao/weex/ui/component/list/template/VirtualComponentLifecycle", 1);
        $jacocoData = a2;
        return a2;
    }

    public VirtualComponentLifecycle() {
        $jacocoInit()[0] = true;
    }
}
