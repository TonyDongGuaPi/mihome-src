package com.taobao.weex.ui.component;

import android.content.Context;
import android.support.annotation.NonNull;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.view.WXFrameLayout;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXBaseRefresh extends WXVContainer<WXFrameLayout> {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private WXLoadingIndicator mLoadingIndicator;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-9188771728258480645L, "com/taobao/weex/ui/component/WXBaseRefresh", 10);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXBaseRefresh(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public void addChild(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        super.addChild(wXComponent);
        $jacocoInit[1] = true;
        checkLoadingIndicator(wXComponent);
        $jacocoInit[2] = true;
    }

    /* access modifiers changed from: protected */
    public WXFrameLayout initComponentHostView(@NonNull Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        WXFrameLayout wXFrameLayout = new WXFrameLayout(context);
        $jacocoInit[3] = true;
        return wXFrameLayout;
    }

    public void addChild(WXComponent wXComponent, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        super.addChild(wXComponent, i);
        $jacocoInit[4] = true;
        checkLoadingIndicator(wXComponent);
        $jacocoInit[5] = true;
    }

    private void checkLoadingIndicator(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!(wXComponent instanceof WXLoadingIndicator)) {
            $jacocoInit[6] = true;
        } else {
            this.mLoadingIndicator = (WXLoadingIndicator) wXComponent;
            $jacocoInit[7] = true;
        }
        $jacocoInit[8] = true;
    }
}
