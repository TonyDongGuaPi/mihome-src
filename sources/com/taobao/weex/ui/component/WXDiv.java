package com.taobao.weex.ui.component;

import android.content.Context;
import android.support.annotation.NonNull;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.ui.ComponentCreator;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.flat.FlatComponent;
import com.taobao.weex.ui.flat.WidgetContainer;
import com.taobao.weex.ui.flat.widget.WidgetGroup;
import com.taobao.weex.ui.view.WXFrameLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXDiv extends WidgetContainer<WXFrameLayout> implements FlatComponent<WidgetGroup> {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private WidgetGroup mWidgetGroup;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(3884811847777250668L, "com/taobao/weex/ui/component/WXDiv", 41);
        $jacocoData = a2;
        return a2;
    }

    public static class Ceator implements ComponentCreator {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-3624357487194027147L, "com/taobao/weex/ui/component/WXDiv$Ceator", 2);
            $jacocoData = a2;
            return a2;
        }

        public Ceator() {
            $jacocoInit()[0] = true;
        }

        public WXComponent createInstance(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) throws IllegalAccessException, InvocationTargetException, InstantiationException {
            boolean[] $jacocoInit = $jacocoInit();
            WXDiv wXDiv = new WXDiv(wXSDKInstance, wXVContainer, basicComponentData);
            $jacocoInit[1] = true;
            return wXDiv;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXDiv(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXDiv(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
    }

    /* access modifiers changed from: protected */
    public WXFrameLayout initComponentHostView(@NonNull Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        WXFrameLayout wXFrameLayout = new WXFrameLayout(context);
        $jacocoInit[2] = true;
        wXFrameLayout.holdComponent(this);
        $jacocoInit[3] = true;
        return wXFrameLayout;
    }

    public boolean promoteToView(boolean z) {
        boolean z2;
        boolean[] $jacocoInit = $jacocoInit();
        if (!intendToBeFlatContainer()) {
            $jacocoInit[4] = true;
        } else {
            $jacocoInit[5] = true;
            if (getInstance().getFlatUIContext().promoteToView(this, z, WXDiv.class)) {
                $jacocoInit[6] = true;
            } else {
                z2 = false;
                $jacocoInit[8] = true;
                $jacocoInit[9] = true;
                return z2;
            }
        }
        $jacocoInit[7] = true;
        z2 = true;
        $jacocoInit[9] = true;
        return z2;
    }

    @NonNull
    public WidgetGroup getOrCreateFlatWidget() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWidgetGroup != null) {
            $jacocoInit[10] = true;
        } else {
            $jacocoInit[11] = true;
            this.mWidgetGroup = new WidgetGroup(getInstance().getFlatUIContext());
            $jacocoInit[12] = true;
            int i = 0;
            $jacocoInit[13] = true;
            while (i < getChildCount()) {
                $jacocoInit[14] = true;
                createChildViewAt(i);
                i++;
                $jacocoInit[15] = true;
            }
            mountFlatGUI();
            $jacocoInit[16] = true;
        }
        WidgetGroup widgetGroup = this.mWidgetGroup;
        $jacocoInit[17] = true;
        return widgetGroup;
    }

    /* access modifiers changed from: protected */
    public void mountFlatGUI() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.widgets != null) {
            $jacocoInit[18] = true;
        } else {
            $jacocoInit[19] = true;
            this.widgets = new LinkedList();
            $jacocoInit[20] = true;
        }
        if (promoteToView(true)) {
            $jacocoInit[21] = true;
            if (getHostView() == null) {
                $jacocoInit[22] = true;
            } else {
                $jacocoInit[23] = true;
                ((WXFrameLayout) getHostView()).mountFlatGUI(this.widgets);
                $jacocoInit[24] = true;
            }
        } else {
            this.mWidgetGroup.replaceAll(this.widgets);
            $jacocoInit[25] = true;
        }
        $jacocoInit[26] = true;
    }

    public void unmountFlatGUI() {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[27] = true;
        } else {
            $jacocoInit[28] = true;
            ((WXFrameLayout) getHostView()).unmountFlatGUI();
            $jacocoInit[29] = true;
        }
        $jacocoInit[30] = true;
    }

    public boolean intendToBeFlatContainer() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (!getInstance().getFlatUIContext().isFlatUIEnabled(this)) {
            $jacocoInit[31] = true;
        } else if (!WXDiv.class.equals(getClass())) {
            $jacocoInit[32] = true;
        } else {
            $jacocoInit[33] = true;
            z = true;
            $jacocoInit[35] = true;
            return z;
        }
        z = false;
        $jacocoInit[34] = true;
        $jacocoInit[35] = true;
        return z;
    }

    public boolean isVirtualComponent() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (!promoteToView(true)) {
            $jacocoInit[36] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[37] = true;
        }
        $jacocoInit[38] = true;
        return z;
    }
}
