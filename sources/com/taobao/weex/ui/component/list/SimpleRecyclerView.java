package com.taobao.weex.ui.component.list;

import android.content.Context;
import com.taobao.weex.ui.view.listview.WXRecyclerView;
import com.taobao.weex.ui.view.listview.adapter.RecyclerViewBaseAdapter;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class SimpleRecyclerView extends WXRecyclerView implements ListComponentView {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private RecyclerViewBaseAdapter mAdapter = null;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6878871807737617228L, "com/taobao/weex/ui/component/list/SimpleRecyclerView", 7);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SimpleRecyclerView(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public WXRecyclerView getInnerView() {
        $jacocoInit()[1] = true;
        return this;
    }

    public void setRecyclerViewBaseAdapter(RecyclerViewBaseAdapter recyclerViewBaseAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        setAdapter(recyclerViewBaseAdapter);
        this.mAdapter = recyclerViewBaseAdapter;
        $jacocoInit[2] = true;
    }

    public void notifyStickyShow(WXCell wXCell) {
        $jacocoInit()[3] = true;
    }

    public void notifyStickyRemove(WXCell wXCell) {
        $jacocoInit()[4] = true;
    }

    public void updateStickyView(int i) {
        $jacocoInit()[5] = true;
    }

    public RecyclerViewBaseAdapter getRecyclerViewBaseAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        RecyclerViewBaseAdapter recyclerViewBaseAdapter = this.mAdapter;
        $jacocoInit[6] = true;
        return recyclerViewBaseAdapter;
    }
}
