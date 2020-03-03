package com.taobao.weex.ui.view;

import android.content.Context;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXRefreshLayout extends WXBaseRefreshLayout {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8135766927440648339L, "com/taobao/weex/ui/view/WXRefreshLayout", 1);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXRefreshLayout(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }
}
