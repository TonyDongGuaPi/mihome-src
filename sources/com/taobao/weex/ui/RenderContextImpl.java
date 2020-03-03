package com.taobao.weex.ui;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.RenderContext;
import com.taobao.weex.ui.component.WXComponent;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class RenderContextImpl implements RenderContext {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private Map<String, WXComponent> mRegistry = new ConcurrentHashMap();
    private WXSDKInstance mWXSDKInstance;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4819910050373460103L, "com/taobao/weex/ui/RenderContextImpl", 13);
        $jacocoData = a2;
        return a2;
    }

    public RenderContextImpl(WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWXSDKInstance = wXSDKInstance;
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWXSDKInstance = null;
        try {
            $jacocoInit[2] = true;
            this.mRegistry.clear();
            $jacocoInit[3] = true;
        } catch (Throwable th) {
            $jacocoInit[4] = true;
            th.printStackTrace();
            $jacocoInit[5] = true;
        }
        $jacocoInit[6] = true;
    }

    public WXSDKInstance getWXSDKInstance() {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance wXSDKInstance = this.mWXSDKInstance;
        $jacocoInit[7] = true;
        return wXSDKInstance;
    }

    public WXSDKInstance getInstance() {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance wXSDKInstance = this.mWXSDKInstance;
        $jacocoInit[8] = true;
        return wXSDKInstance;
    }

    public WXComponent getComponent(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent wXComponent = this.mRegistry.get(str);
        $jacocoInit[9] = true;
        return wXComponent;
    }

    public void registerComponent(String str, WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRegistry.put(str, wXComponent);
        $jacocoInit[10] = true;
    }

    public WXComponent unregisterComponent(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent remove = this.mRegistry.remove(str);
        $jacocoInit[11] = true;
        return remove;
    }

    public int getComponentCount() {
        boolean[] $jacocoInit = $jacocoInit();
        int size = this.mRegistry.size();
        $jacocoInit[12] = true;
        return size;
    }
}
