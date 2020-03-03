package com.taobao.weex.ui.module;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.ui.WXRenderManager;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXWeb;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXWebViewModule extends WXModule {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-325212905628590929L, "com/taobao/weex/ui/module/WXWebViewModule", 12);
        $jacocoData = a2;
        return a2;
    }

    public WXWebViewModule() {
        $jacocoInit()[0] = true;
    }

    private enum Action {
        reload,
        goBack,
        goForward,
        postMessage;

        static {
            boolean[] $jacocoInit;
            $jacocoInit[6] = true;
        }
    }

    @JSMethod(uiThread = true)
    public void goBack(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        action(Action.goBack, str);
        $jacocoInit[1] = true;
    }

    @JSMethod(uiThread = true)
    public void goForward(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        action(Action.goForward, str);
        $jacocoInit[2] = true;
    }

    @JSMethod(uiThread = true)
    public void reload(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        action(Action.reload, str);
        $jacocoInit[3] = true;
    }

    @JSMethod(uiThread = true)
    public void postMessage(String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        action(Action.postMessage, str, obj);
        $jacocoInit[4] = true;
    }

    private void action(Action action, String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKManager instance = WXSDKManager.getInstance();
        $jacocoInit[5] = true;
        WXRenderManager wXRenderManager = instance.getWXRenderManager();
        WXSDKInstance wXSDKInstance = this.mWXSDKInstance;
        $jacocoInit[6] = true;
        WXComponent wXComponent = wXRenderManager.getWXComponent(wXSDKInstance.getInstanceId(), str);
        if (!(wXComponent instanceof WXWeb)) {
            $jacocoInit[7] = true;
        } else {
            $jacocoInit[8] = true;
            ((WXWeb) wXComponent).setAction(action.name(), obj);
            $jacocoInit[9] = true;
        }
        $jacocoInit[10] = true;
    }

    private void action(Action action, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        action(action, str, (Object) null);
        $jacocoInit[11] = true;
    }
}
