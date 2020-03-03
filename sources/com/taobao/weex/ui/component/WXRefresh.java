package com.taobao.weex.ui.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.list.WXListComponent;
import com.taobao.weex.ui.view.WXFrameLayout;
import com.taobao.weex.ui.view.WXRefreshLayout;
import com.taobao.weex.ui.view.refresh.core.WXSwipeLayout;
import com.taobao.weex.ui.view.refresh.wrapper.BaseBounceView;
import java.util.HashMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXRefresh extends WXBaseRefresh implements WXSwipeLayout.WXOnRefreshListener {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String HIDE = "hide";

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-8714774857525057578L, "com/taobao/weex/ui/component/WXRefresh", 42);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXRefresh(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXRefresh(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
    }

    /* access modifiers changed from: protected */
    public WXFrameLayout initComponentHostView(@NonNull Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        WXRefreshLayout wXRefreshLayout = new WXRefreshLayout(context);
        $jacocoInit[2] = true;
        return wXRefreshLayout;
    }

    public boolean canRecycled() {
        $jacocoInit()[3] = true;
        return false;
    }

    public void onRefresh() {
        boolean[] $jacocoInit = $jacocoInit();
        if (isDestoryed()) {
            $jacocoInit[4] = true;
            return;
        }
        if (!getEvents().contains("refresh")) {
            $jacocoInit[5] = true;
        } else {
            $jacocoInit[6] = true;
            fireEvent("refresh");
            $jacocoInit[7] = true;
        }
        $jacocoInit[8] = true;
    }

    public int getLayoutTopOffsetForSibling() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (getParent() instanceof Scrollable) {
            i = -Math.round(getLayoutHeight());
            $jacocoInit[9] = true;
        } else {
            i = 0;
            $jacocoInit[10] = true;
        }
        $jacocoInit[11] = true;
        return i;
    }

    public void onPullingDown(float f, int i, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getEvents() == null) {
            $jacocoInit[12] = true;
        } else if (!getEvents().contains(Constants.Event.ONPULLING_DOWN)) {
            $jacocoInit[13] = true;
        } else {
            $jacocoInit[14] = true;
            HashMap hashMap = new HashMap();
            $jacocoInit[15] = true;
            hashMap.put(Constants.Name.DISTANCE_Y, Float.valueOf(f));
            $jacocoInit[16] = true;
            hashMap.put(Constants.Name.PULLING_DISTANCE, Integer.valueOf(i));
            $jacocoInit[17] = true;
            hashMap.put(Constants.Name.VIEW_HEIGHT, Float.valueOf(f2));
            $jacocoInit[18] = true;
            fireEvent(Constants.Event.ONPULLING_DOWN, hashMap);
            $jacocoInit[19] = true;
        }
        $jacocoInit[20] = true;
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
            r1 = 21
            r0[r1] = r2
            goto L_0x001f
        L_0x0013:
            java.lang.String r1 = "display"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0021
            r1 = 22
            r0[r1] = r2
        L_0x001f:
            r1 = -1
            goto L_0x0026
        L_0x0021:
            r1 = 0
            r3 = 23
            r0[r3] = r2
        L_0x0026:
            if (r1 == 0) goto L_0x0031
            boolean r5 = super.setProperty(r5, r6)
            r6 = 28
            r0[r6] = r2
            return r5
        L_0x0031:
            r5 = 0
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r5)
            if (r5 != 0) goto L_0x003d
            r5 = 24
            r0[r5] = r2
            goto L_0x0048
        L_0x003d:
            r6 = 25
            r0[r6] = r2
            r4.setDisplay(r5)
            r5 = 26
            r0[r5] = r2
        L_0x0048:
            r5 = 27
            r0[r5] = r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXRefresh.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    @WXComponentProp(name = "display")
    public void setDisplay(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[29] = true;
        } else {
            $jacocoInit[30] = true;
            if (!str.equals("hide")) {
                $jacocoInit[31] = true;
            } else {
                $jacocoInit[32] = true;
                if (getParent() instanceof WXListComponent) {
                    $jacocoInit[33] = true;
                } else if (!(getParent() instanceof WXScroller)) {
                    $jacocoInit[34] = true;
                } else {
                    $jacocoInit[35] = true;
                }
                if (!((BaseBounceView) getParent().getHostView()).getSwipeLayout().isRefreshing()) {
                    $jacocoInit[36] = true;
                } else {
                    $jacocoInit[37] = true;
                    ((BaseBounceView) getParent().getHostView()).finishPullRefresh();
                    $jacocoInit[38] = true;
                    ((BaseBounceView) getParent().getHostView()).onRefreshingComplete();
                    $jacocoInit[39] = true;
                }
            }
        }
        $jacocoInit[40] = true;
    }
}
