package com.taobao.weex.ui.component;

import android.content.Context;
import android.support.annotation.NonNull;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.view.refresh.circlebar.CircleProgressBar;
import com.taobao.weex.utils.WXResourceUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXLoadingIndicator extends WXComponent<CircleProgressBar> {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-1070870369587285488L, "com/taobao/weex/ui/component/WXLoadingIndicator", 17);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXLoadingIndicator(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* access modifiers changed from: protected */
    public CircleProgressBar initComponentHostView(@NonNull Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        CircleProgressBar circleProgressBar = new CircleProgressBar(context);
        $jacocoInit[1] = true;
        return circleProgressBar;
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
            r3 = 94842723(0x5a72f63, float:1.5722012E-35)
            if (r1 == r3) goto L_0x0012
            r1 = 2
            r0[r1] = r2
            goto L_0x001d
        L_0x0012:
            java.lang.String r1 = "color"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x001f
            r1 = 3
            r0[r1] = r2
        L_0x001d:
            r1 = -1
            goto L_0x0023
        L_0x001f:
            r1 = 0
            r3 = 4
            r0[r3] = r2
        L_0x0023:
            if (r1 == 0) goto L_0x002e
            boolean r5 = super.setProperty(r5, r6)
            r6 = 9
            r0[r6] = r2
            return r5
        L_0x002e:
            r5 = 0
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r5)
            if (r5 != 0) goto L_0x0039
            r5 = 5
            r0[r5] = r2
            goto L_0x0042
        L_0x0039:
            r6 = 6
            r0[r6] = r2
            r4.setColor(r5)
            r5 = 7
            r0[r5] = r2
        L_0x0042:
            r5 = 8
            r0[r5] = r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXLoadingIndicator.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    @WXComponentProp(name = "color")
    public void setColor(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[10] = true;
        } else if (str.equals("")) {
            $jacocoInit[11] = true;
        } else {
            $jacocoInit[12] = true;
            int color = WXResourceUtils.getColor(str, -65536);
            $jacocoInit[13] = true;
            ((CircleProgressBar) getHostView()).setColorSchemeColors(color);
            $jacocoInit[14] = true;
        }
        $jacocoInit[15] = true;
    }
}
