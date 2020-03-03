package com.taobao.weex.ui.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.list.WXListComponent;
import com.taobao.weex.ui.view.WXFrameLayout;
import com.taobao.weex.ui.view.WXLoadingLayout;
import com.taobao.weex.ui.view.refresh.core.WXSwipeLayout;
import com.taobao.weex.ui.view.refresh.wrapper.BaseBounceView;
import java.util.HashMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXLoading extends WXBaseRefresh implements WXSwipeLayout.WXOnLoadingListener {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String HIDE = "hide";

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8628404295302953672L, "com/taobao/weex/ui/component/WXLoading", 38);
        $jacocoData = a2;
        return a2;
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ void setHostLayoutParams(View view, int i, int i2, int i3, int i4, int i5, int i6) {
        boolean[] $jacocoInit = $jacocoInit();
        setHostLayoutParams((WXFrameLayout) view, i, i2, i3, i4, i5, i6);
        $jacocoInit[37] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXLoading(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* access modifiers changed from: protected */
    public WXFrameLayout initComponentHostView(@NonNull Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        WXLoadingLayout wXLoadingLayout = new WXLoadingLayout(context);
        $jacocoInit[1] = true;
        return wXLoadingLayout;
    }

    public void onLoading() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!getEvents().contains("loading")) {
            $jacocoInit[2] = true;
        } else {
            $jacocoInit[3] = true;
            fireEvent("loading");
            $jacocoInit[4] = true;
        }
        $jacocoInit[5] = true;
    }

    /* access modifiers changed from: protected */
    public void setHostLayoutParams(WXFrameLayout wXFrameLayout, int i, int i2, int i3, int i4, int i5, int i6) {
        boolean[] $jacocoInit = $jacocoInit();
        super.setHostLayoutParams(wXFrameLayout, i, i2, 0, 0, 0, 0);
        $jacocoInit[6] = true;
    }

    public void onPullingUp(float f, int i, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!getEvents().contains(Constants.Event.ONPULLING_UP)) {
            $jacocoInit[7] = true;
        } else {
            $jacocoInit[8] = true;
            HashMap hashMap = new HashMap();
            $jacocoInit[9] = true;
            hashMap.put(Constants.Name.DISTANCE_Y, Float.valueOf(f));
            $jacocoInit[10] = true;
            hashMap.put(Constants.Name.PULLING_DISTANCE, Integer.valueOf(i));
            $jacocoInit[11] = true;
            hashMap.put(Constants.Name.VIEW_HEIGHT, Float.valueOf(f2));
            $jacocoInit[12] = true;
            fireEvent(Constants.Event.ONPULLING_UP, hashMap);
            $jacocoInit[13] = true;
        }
        $jacocoInit[14] = true;
    }

    public boolean canRecycled() {
        $jacocoInit()[15] = true;
        return false;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setProperty(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r5.hashCode()
            r2 = 1
            r3 = 1671764162(0x63a518c2, float:6.0909935E21)
            if (r1 == r3) goto L_0x0013
            r1 = 16
            r0[r1] = r2
            goto L_0x001f
        L_0x0013:
            java.lang.String r1 = "display"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0021
            r1 = 17
            r0[r1] = r2
        L_0x001f:
            r1 = -1
            goto L_0x0026
        L_0x0021:
            r1 = 0
            r3 = 18
            r0[r3] = r2
        L_0x0026:
            if (r1 == 0) goto L_0x0031
            boolean r5 = super.setProperty(r5, r6)
            r6 = 23
            r0[r6] = r2
            return r5
        L_0x0031:
            r5 = 0
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r5)
            if (r5 != 0) goto L_0x003d
            r5 = 19
            r0[r5] = r2
            goto L_0x0048
        L_0x003d:
            r6 = 20
            r0[r6] = r2
            r4.setDisplay(r5)
            r5 = 21
            r0[r5] = r2
        L_0x0048:
            r5 = 22
            r0[r5] = r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXLoading.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    @WXComponentProp(name = "display")
    public void setDisplay(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[24] = true;
        } else {
            $jacocoInit[25] = true;
            if (!str.equals("hide")) {
                $jacocoInit[26] = true;
            } else {
                $jacocoInit[27] = true;
                if (getParent() instanceof WXListComponent) {
                    $jacocoInit[28] = true;
                } else if (!(getParent() instanceof WXScroller)) {
                    $jacocoInit[29] = true;
                } else {
                    $jacocoInit[30] = true;
                }
                if (!((BaseBounceView) getParent().getHostView()).getSwipeLayout().isRefreshing()) {
                    $jacocoInit[31] = true;
                } else {
                    $jacocoInit[32] = true;
                    ((BaseBounceView) getParent().getHostView()).finishPullLoad();
                    $jacocoInit[33] = true;
                    ((BaseBounceView) getParent().getHostView()).onLoadmoreComplete();
                    $jacocoInit[34] = true;
                }
            }
        }
        $jacocoInit[35] = true;
    }
}
