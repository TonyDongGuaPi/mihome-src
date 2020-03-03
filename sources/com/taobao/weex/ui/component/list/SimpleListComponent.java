package com.taobao.weex.ui.component.list;

import android.content.Context;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXVContainer;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class SimpleListComponent extends BasicListComponent<SimpleRecyclerView> {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(1071196851009240632L, "com/taobao/weex/ui/component/list/SimpleListComponent", 4);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SimpleListComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* access modifiers changed from: protected */
    public SimpleRecyclerView generateListView(Context context, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        SimpleRecyclerView simpleRecyclerView = new SimpleRecyclerView(context);
        $jacocoInit[1] = true;
        simpleRecyclerView.initView(context, 1, i);
        $jacocoInit[2] = true;
        return simpleRecyclerView;
    }
}
