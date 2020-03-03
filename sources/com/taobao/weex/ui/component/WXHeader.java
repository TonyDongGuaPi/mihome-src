package com.taobao.weex.ui.component;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.list.WXCell;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXHeader extends WXCell {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8169426213084487574L, "com/taobao/weex/ui/component/WXHeader", 14);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXHeader(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXHeader(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
        String componentType = wXVContainer.getComponentType();
        $jacocoInit[2] = true;
        if ("list".equals(componentType)) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            if (!WXBasicComponentType.RECYCLE_LIST.equals(componentType)) {
                $jacocoInit[5] = true;
                $jacocoInit[9] = true;
            }
            $jacocoInit[6] = true;
        }
        getStyles().put("position", (Object) "sticky");
        $jacocoInit[7] = true;
        setSticky("sticky");
        $jacocoInit[8] = true;
        $jacocoInit[9] = true;
    }

    public boolean isLazy() {
        $jacocoInit()[10] = true;
        return false;
    }

    public boolean canRecycled() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (!isSticky()) {
            $jacocoInit[11] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[12] = true;
        }
        $jacocoInit[13] = true;
        return z;
    }
}
