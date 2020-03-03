package com.taobao.weex.ui.flat;

import android.support.annotation.RestrictTo;
import android.view.ViewGroup;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.flat.widget.Widget;
import java.util.LinkedList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public abstract class WidgetContainer<T extends ViewGroup> extends WXVContainer<T> {
    private static transient /* synthetic */ boolean[] $jacocoData;
    protected List<Widget> widgets;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5286972981149205968L, "com/taobao/weex/ui/flat/WidgetContainer", 33);
        $jacocoData = a2;
        return a2;
    }

    /* access modifiers changed from: protected */
    public abstract void mountFlatGUI();

    /* access modifiers changed from: protected */
    public abstract void unmountFlatGUI();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WidgetContainer(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public boolean intendToBeFlatContainer() {
        $jacocoInit()[1] = true;
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x005e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void createChildViewAt(int r9) {
        /*
            r8 = this;
            boolean[] r0 = $jacocoInit()
            boolean r1 = r8.intendToBeFlatContainer()
            r2 = 1
            if (r1 == 0) goto L_0x00c5
            r1 = 2
            r0[r1] = r2
            android.util.Pair r9 = r8.rearrangeIndexAndGetChild(r9)
            java.lang.Object r1 = r9.first
            if (r1 != 0) goto L_0x001b
            r9 = 3
            r0[r9] = r2
            goto L_0x00c0
        L_0x001b:
            java.lang.Object r1 = r9.first
            com.taobao.weex.ui.component.WXComponent r1 = (com.taobao.weex.ui.component.WXComponent) r1
            r3 = 4
            r0[r3] = r2
            com.taobao.weex.WXSDKInstance r3 = r8.getInstance()
            com.taobao.weex.ui.flat.FlatGUIContext r3 = r3.getFlatUIContext()
            r4 = 5
            r0[r4] = r2
            com.taobao.weex.ui.flat.WidgetContainer r4 = r3.getFlatComponentAncestor(r8)
            r5 = 6
            r0[r5] = r2
            if (r4 != 0) goto L_0x003a
            r4 = 7
            r0[r4] = r2
            goto L_0x0049
        L_0x003a:
            com.taobao.weex.ui.flat.widget.AndroidViewWidget r5 = r3.getAndroidViewWidget(r8)
            if (r5 != 0) goto L_0x0045
            r5 = 8
            r0[r5] = r2
            goto L_0x004e
        L_0x0045:
            r4 = 9
            r0[r4] = r2
        L_0x0049:
            r4 = 10
            r0[r4] = r2
            r4 = r8
        L_0x004e:
            r3.register((com.taobao.weex.ui.component.WXComponent) r1, (com.taobao.weex.ui.flat.WidgetContainer) r4)
            r5 = 11
            r0[r5] = r2
            boolean r5 = r1 instanceof com.taobao.weex.ui.flat.FlatComponent
            if (r5 != 0) goto L_0x005e
            r5 = 12
            r0[r5] = r2
            goto L_0x006c
        L_0x005e:
            r5 = r1
            com.taobao.weex.ui.flat.FlatComponent r5 = (com.taobao.weex.ui.flat.FlatComponent) r5
            r6 = 0
            boolean r6 = r5.promoteToView(r6)
            if (r6 == 0) goto L_0x009e
            r5 = 13
            r0[r5] = r2
        L_0x006c:
            com.taobao.weex.ui.flat.widget.AndroidViewWidget r5 = new com.taobao.weex.ui.flat.widget.AndroidViewWidget
            r5.<init>(r3)
            r6 = 16
            r0[r6] = r2
            r6 = r5
            com.taobao.weex.ui.flat.widget.AndroidViewWidget r6 = (com.taobao.weex.ui.flat.widget.AndroidViewWidget) r6
            r3.register((com.taobao.weex.ui.component.WXComponent) r1, (com.taobao.weex.ui.flat.widget.AndroidViewWidget) r6)
            r7 = 17
            r0[r7] = r2
            r1.createView()
            r7 = 18
            r0[r7] = r2
            android.view.View r7 = r1.getHostView()
            r6.setContentView(r7)
            r6 = 19
            r0[r6] = r2
            android.view.View r6 = r1.getHostView()
            r7 = -1
            r4.addSubView(r6, r7)
            r4 = 20
            r0[r4] = r2
            goto L_0x00aa
        L_0x009e:
            r4 = 14
            r0[r4] = r2
            com.taobao.weex.ui.flat.widget.Widget r5 = r5.getOrCreateFlatWidget()
            r4 = 15
            r0[r4] = r2
        L_0x00aa:
            r3.register((com.taobao.weex.ui.flat.widget.Widget) r5, (com.taobao.weex.ui.component.WXComponent) r1)
            r1 = 21
            r0[r1] = r2
            java.lang.Object r9 = r9.second
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8.addFlatChild(r5, r9)
            r9 = 22
            r0[r9] = r2
        L_0x00c0:
            r9 = 23
            r0[r9] = r2
            goto L_0x00cc
        L_0x00c5:
            super.createChildViewAt(r9)
            r9 = 24
            r0[r9] = r2
        L_0x00cc:
            r9 = 25
            r0[r9] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.flat.WidgetContainer.createChildViewAt(int):void");
    }

    private void addFlatChild(Widget widget, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.widgets != null) {
            $jacocoInit[26] = true;
        } else {
            $jacocoInit[27] = true;
            this.widgets = new LinkedList();
            $jacocoInit[28] = true;
        }
        if (i >= this.widgets.size()) {
            $jacocoInit[29] = true;
            this.widgets.add(widget);
            $jacocoInit[30] = true;
        } else {
            this.widgets.add(i, widget);
            $jacocoInit[31] = true;
        }
        mountFlatGUI();
        $jacocoInit[32] = true;
    }
}
