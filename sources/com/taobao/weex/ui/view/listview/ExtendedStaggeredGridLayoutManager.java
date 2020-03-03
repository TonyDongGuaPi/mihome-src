package com.taobao.weex.ui.view.listview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.utils.WXLogUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ExtendedStaggeredGridLayoutManager extends StaggeredGridLayoutManager {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-467842243675879520L, "com/taobao/weex/ui/view/listview/ExtendedStaggeredGridLayoutManager", 7);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExtendedStaggeredGridLayoutManager(int i, int i2) {
        super(i, i2);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i == -1) {
            $jacocoInit[1] = true;
            WXLogUtils.e("ExtendedStaggeredGridLayoutManager: onItemsRemoved  Error Invalid Index : positionStart :" + i + "  itemCount:" + i2);
            $jacocoInit[2] = true;
            return;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            WXLogUtils.e("ExtendedStaggeredGridLayoutManager: onItemsRemoved  positionStart :" + i + "  itemCount:" + i2);
            $jacocoInit[5] = true;
        }
        super.onItemsRemoved(recyclerView, i, i2);
        $jacocoInit[6] = true;
    }
}
