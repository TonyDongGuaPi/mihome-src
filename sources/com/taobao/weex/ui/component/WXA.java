package com.taobao.weex.ui.component;

import android.view.View;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.view.WXFrameLayout;
import com.taobao.weex.utils.ATagUtil;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXA extends WXDiv {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-8747282432487386699L, "com/taobao/weex/ui/component/WXA", 10);
        $jacocoData = a2;
        return a2;
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ void onHostViewInitialized(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        onHostViewInitialized((WXFrameLayout) view);
        $jacocoInit[9] = true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXA(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXA(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
    }

    /* access modifiers changed from: protected */
    public void onHostViewInitialized(WXFrameLayout wXFrameLayout) {
        boolean[] $jacocoInit = $jacocoInit();
        addClickListener(new WXComponent.OnClickListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXA this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(3684568715390670457L, "com/taobao/weex/ui/component/WXA$1", 7);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onHostViewClick() {
                boolean[] $jacocoInit = $jacocoInit();
                WXAttr attrs = this.this$0.getAttrs();
                $jacocoInit[1] = true;
                if (attrs == null) {
                    $jacocoInit[2] = true;
                } else {
                    String str = (String) attrs.get("href");
                    if (str == null) {
                        $jacocoInit[3] = true;
                    } else {
                        $jacocoInit[4] = true;
                        ATagUtil.onClick((View) null, this.this$0.getInstanceId(), str);
                        $jacocoInit[5] = true;
                    }
                }
                $jacocoInit[6] = true;
            }
        });
        $jacocoInit[2] = true;
        super.onHostViewInitialized(wXFrameLayout);
        $jacocoInit[3] = true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setProperty(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r5.hashCode()
            r2 = 1
            r3 = 3211051(0x30ff2b, float:4.499641E-39)
            if (r1 == r3) goto L_0x0012
            r1 = 4
            r0[r1] = r2
            goto L_0x001d
        L_0x0012:
            java.lang.String r1 = "href"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x001f
            r1 = 5
            r0[r1] = r2
        L_0x001d:
            r1 = -1
            goto L_0x0023
        L_0x001f:
            r1 = 0
            r3 = 6
            r0[r3] = r2
        L_0x0023:
            if (r1 == 0) goto L_0x002e
            boolean r5 = super.setProperty(r5, r6)
            r6 = 8
            r0[r6] = r2
            return r5
        L_0x002e:
            r5 = 7
            r0[r5] = r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXA.setProperty(java.lang.String, java.lang.Object):boolean");
    }
}
