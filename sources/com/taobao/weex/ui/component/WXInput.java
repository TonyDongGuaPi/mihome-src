package com.taobao.weex.ui.component;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.view.WXEditText;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXInput extends AbstractEditComponent {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6602451917976416192L, "com/taobao/weex/ui/component/WXInput", 4);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXInput(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXInput(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
    }

    /* access modifiers changed from: protected */
    public void appleStyleAfterCreated(WXEditText wXEditText) {
        boolean[] $jacocoInit = $jacocoInit();
        super.appleStyleAfterCreated(wXEditText);
        $jacocoInit[2] = true;
        wXEditText.setSingleLine();
        $jacocoInit[3] = true;
    }
}
