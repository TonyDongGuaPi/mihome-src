package com.taobao.weex.ui.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.common.WXRuntimeException;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.view.WXCircleIndicator;
import com.taobao.weex.utils.WXResourceUtils;
import com.taobao.weex.utils.WXViewUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXIndicator extends WXComponent<WXCircleIndicator> {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-7563474808504655600L, "com/taobao/weex/ui/component/WXIndicator", 63);
        $jacocoData = a2;
        return a2;
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ void onHostViewInitialized(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        onHostViewInitialized((WXCircleIndicator) view);
        $jacocoInit[60] = true;
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ void setHostLayoutParams(View view, int i, int i2, int i3, int i4, int i5, int i6) {
        boolean[] $jacocoInit = $jacocoInit();
        setHostLayoutParams((WXCircleIndicator) view, i, i2, i3, i4, i5, i6);
        $jacocoInit[62] = true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXIndicator(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXIndicator(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
    }

    /* access modifiers changed from: protected */
    public void setHostLayoutParams(WXCircleIndicator wXCircleIndicator, int i, int i2, int i3, int i4, int i5, int i6) {
        boolean[] $jacocoInit = $jacocoInit();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i2);
        $jacocoInit[2] = true;
        setMarginsSupportRTL(layoutParams, i3, i5, i4, i6);
        $jacocoInit[3] = true;
        wXCircleIndicator.setLayoutParams(layoutParams);
        $jacocoInit[4] = true;
    }

    /* access modifiers changed from: protected */
    public WXCircleIndicator initComponentHostView(@NonNull Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        WXCircleIndicator wXCircleIndicator = new WXCircleIndicator(context);
        $jacocoInit[5] = true;
        if (getParent() instanceof WXSlider) {
            $jacocoInit[6] = true;
            return wXCircleIndicator;
        } else if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[9] = true;
            return null;
        } else {
            $jacocoInit[7] = true;
            WXRuntimeException wXRuntimeException = new WXRuntimeException("WXIndicator initView error.");
            $jacocoInit[8] = true;
            throw wXRuntimeException;
        }
    }

    /* access modifiers changed from: protected */
    public void onHostViewInitialized(WXCircleIndicator wXCircleIndicator) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onHostViewInitialized(wXCircleIndicator);
        $jacocoInit[10] = true;
        if (!(getParent() instanceof WXSlider)) {
            $jacocoInit[11] = true;
        } else {
            $jacocoInit[12] = true;
            ((WXSlider) getParent()).addIndicator(this);
            $jacocoInit[13] = true;
        }
        $jacocoInit[14] = true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setProperty(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r5.hashCode()
            r2 = 1177488820(0x462f0db4, float:11203.426)
            r3 = 1
            if (r1 == r2) goto L_0x0043
            r2 = 1873297717(0x6fa84135, float:1.0414462E29)
            if (r1 == r2) goto L_0x0030
            r2 = 2127804432(0x7ed3b810, float:1.4071141E38)
            if (r1 == r2) goto L_0x001d
            r1 = 15
            r0[r1] = r3
            goto L_0x004f
        L_0x001d:
            java.lang.String r1 = "itemColor"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x002a
            r1 = 16
            r0[r1] = r3
            goto L_0x004f
        L_0x002a:
            r1 = 0
            r2 = 17
            r0[r2] = r3
            goto L_0x0056
        L_0x0030:
            java.lang.String r1 = "itemSelectedColor"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x003d
            r1 = 18
            r0[r1] = r3
            goto L_0x004f
        L_0x003d:
            r1 = 19
            r0[r1] = r3
            r1 = 1
            goto L_0x0056
        L_0x0043:
            java.lang.String r1 = "itemSize"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0051
            r1 = 20
            r0[r1] = r3
        L_0x004f:
            r1 = -1
            goto L_0x0056
        L_0x0051:
            r1 = 2
            r2 = 21
            r0[r2] = r3
        L_0x0056:
            r2 = 0
            switch(r1) {
                case 0: goto L_0x009d;
                case 1: goto L_0x0082;
                case 2: goto L_0x0063;
                default: goto L_0x005a;
            }
        L_0x005a:
            boolean r5 = super.setProperty(r5, r6)
            r6 = 34
            r0[r6] = r3
            return r5
        L_0x0063:
            java.lang.Integer r5 = com.taobao.weex.utils.WXUtils.getInteger(r6, r2)
            if (r5 != 0) goto L_0x006e
            r5 = 30
            r0[r5] = r3
            goto L_0x007d
        L_0x006e:
            r6 = 31
            r0[r6] = r3
            int r5 = r5.intValue()
            r4.setItemSize(r5)
            r5 = 32
            r0[r5] = r3
        L_0x007d:
            r5 = 33
            r0[r5] = r3
            return r3
        L_0x0082:
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r2)
            if (r5 != 0) goto L_0x008d
            r5 = 26
            r0[r5] = r3
            goto L_0x0098
        L_0x008d:
            r6 = 27
            r0[r6] = r3
            r4.setItemSelectedColor(r5)
            r5 = 28
            r0[r5] = r3
        L_0x0098:
            r5 = 29
            r0[r5] = r3
            return r3
        L_0x009d:
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r2)
            if (r5 != 0) goto L_0x00a8
            r5 = 22
            r0[r5] = r3
            goto L_0x00b3
        L_0x00a8:
            r6 = 23
            r0[r6] = r3
            r4.setItemColor(r5)
            r5 = 24
            r0[r5] = r3
        L_0x00b3:
            r5 = 25
            r0[r5] = r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXIndicator.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    @WXComponentProp(name = "itemColor")
    public void setItemColor(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[35] = true;
        } else {
            $jacocoInit[36] = true;
            int color = WXResourceUtils.getColor(str);
            if (color == Integer.MIN_VALUE) {
                $jacocoInit[37] = true;
            } else {
                $jacocoInit[38] = true;
                ((WXCircleIndicator) getHostView()).setPageColor(color);
                $jacocoInit[39] = true;
                ((WXCircleIndicator) getHostView()).forceLayout();
                $jacocoInit[40] = true;
                ((WXCircleIndicator) getHostView()).requestLayout();
                $jacocoInit[41] = true;
            }
        }
        $jacocoInit[42] = true;
    }

    @WXComponentProp(name = "itemSelectedColor")
    public void setItemSelectedColor(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[43] = true;
        } else {
            $jacocoInit[44] = true;
            int color = WXResourceUtils.getColor(str);
            if (color == Integer.MIN_VALUE) {
                $jacocoInit[45] = true;
            } else {
                $jacocoInit[46] = true;
                ((WXCircleIndicator) getHostView()).setFillColor(color);
                $jacocoInit[47] = true;
                ((WXCircleIndicator) getHostView()).forceLayout();
                $jacocoInit[48] = true;
                ((WXCircleIndicator) getHostView()).requestLayout();
                $jacocoInit[49] = true;
            }
        }
        $jacocoInit[50] = true;
    }

    @WXComponentProp(name = "itemSize")
    public void setItemSize(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i < 0) {
            $jacocoInit[51] = true;
            return;
        }
        ((WXCircleIndicator) getHostView()).setRadius(WXViewUtils.getRealPxByWidth((float) i, getInstance().getInstanceViewPortWidth()) / 2.0f);
        $jacocoInit[52] = true;
        ((WXCircleIndicator) getHostView()).forceLayout();
        $jacocoInit[53] = true;
        ((WXCircleIndicator) getHostView()).requestLayout();
        $jacocoInit[54] = true;
    }

    public void setShowIndicators(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[55] = true;
            return;
        }
        if (z) {
            $jacocoInit[56] = true;
            ((WXCircleIndicator) getHostView()).setVisibility(0);
            $jacocoInit[57] = true;
        } else {
            ((WXCircleIndicator) getHostView()).setVisibility(8);
            $jacocoInit[58] = true;
        }
        $jacocoInit[59] = true;
    }
}
