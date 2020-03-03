package com.taobao.weex.ui.component;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.Pair;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.CSSShorthand;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.view.WXImageView;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.util.ArrayList;
import java.util.Iterator;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public abstract class WXVContainer<T extends ViewGroup> extends WXComponent<T> {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String TAG = "WXVContainer";
    private WXVContainer<T>.BoxShadowHost mBoxShadowHost;
    /* access modifiers changed from: protected */
    public ArrayList<WXComponent> mChildren;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(350418432772585903L, "com/taobao/weex/ui/component/WXVContainer", 325);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXVContainer(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXVContainer(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
        this.mChildren = new ArrayList<>();
        $jacocoInit[2] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXVContainer(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[3] = true;
        this.mChildren = new ArrayList<>();
        $jacocoInit[4] = true;
    }

    public void interceptFocus() {
        boolean[] $jacocoInit = $jacocoInit();
        ViewGroup viewGroup = (ViewGroup) getHostView();
        if (viewGroup == null) {
            $jacocoInit[5] = true;
        } else {
            $jacocoInit[6] = true;
            viewGroup.setFocusable(true);
            $jacocoInit[7] = true;
            viewGroup.setFocusableInTouchMode(true);
            $jacocoInit[8] = true;
            viewGroup.setDescendantFocusability(131072);
            $jacocoInit[9] = true;
            viewGroup.requestFocus();
            $jacocoInit[10] = true;
        }
        $jacocoInit[11] = true;
    }

    public void ignoreFocus() {
        boolean[] $jacocoInit = $jacocoInit();
        ViewGroup viewGroup = (ViewGroup) getHostView();
        if (viewGroup == null) {
            $jacocoInit[12] = true;
        } else {
            $jacocoInit[13] = true;
            viewGroup.setFocusable(false);
            $jacocoInit[14] = true;
            viewGroup.setFocusableInTouchMode(false);
            $jacocoInit[15] = true;
            viewGroup.clearFocus();
            $jacocoInit[16] = true;
        }
        $jacocoInit[17] = true;
    }

    /* access modifiers changed from: protected */
    public int getChildrenLayoutTopOffset() {
        $jacocoInit()[18] = true;
        return 0;
    }

    @Deprecated
    public ViewGroup getView() {
        boolean[] $jacocoInit = $jacocoInit();
        ViewGroup viewGroup = (ViewGroup) getHostView();
        $jacocoInit[19] = true;
        return viewGroup;
    }

    public void applyLayoutAndEvent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (isLazy()) {
            $jacocoInit[20] = true;
        } else {
            if (wXComponent != null) {
                $jacocoInit[21] = true;
            } else {
                $jacocoInit[22] = true;
                wXComponent = this;
            }
            super.applyLayoutAndEvent(wXComponent);
            $jacocoInit[23] = true;
            int childCount = childCount();
            int i = 0;
            $jacocoInit[24] = true;
            while (i < childCount) {
                $jacocoInit[26] = true;
                WXComponent child = getChild(i);
                $jacocoInit[27] = true;
                child.applyLayoutAndEvent(((WXVContainer) wXComponent).getChild(i));
                i++;
                $jacocoInit[28] = true;
            }
            $jacocoInit[25] = true;
        }
        $jacocoInit[29] = true;
    }

    public ViewGroup.LayoutParams getChildLayoutParams(WXComponent wXComponent, View view, int i, int i2, int i3, int i4, int i5, int i6) {
        ViewGroup.LayoutParams layoutParams;
        boolean[] $jacocoInit = $jacocoInit();
        if (view == null) {
            $jacocoInit[30] = true;
            layoutParams = null;
        } else {
            $jacocoInit[31] = true;
            layoutParams = view.getLayoutParams();
            $jacocoInit[32] = true;
        }
        if (layoutParams == null) {
            $jacocoInit[33] = true;
            layoutParams = new ViewGroup.LayoutParams(i, i2);
            $jacocoInit[34] = true;
        } else {
            layoutParams.width = i;
            layoutParams.height = i2;
            if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                $jacocoInit[35] = true;
            } else {
                $jacocoInit[36] = true;
                setMarginsSupportRTL((ViewGroup.MarginLayoutParams) layoutParams, i3, i5, i4, i6);
                $jacocoInit[37] = true;
            }
        }
        $jacocoInit[38] = true;
        return layoutParams;
    }

    public Scrollable getFirstScroller() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this instanceof Scrollable) {
            Scrollable scrollable = (Scrollable) this;
            $jacocoInit[39] = true;
            return scrollable;
        }
        int i = 0;
        $jacocoInit[40] = true;
        while (i < getChildCount()) {
            $jacocoInit[41] = true;
            Scrollable firstScroller = getChild(i).getFirstScroller();
            if (firstScroller != null) {
                $jacocoInit[42] = true;
                return firstScroller;
            }
            i++;
            $jacocoInit[43] = true;
        }
        $jacocoInit[44] = true;
        return null;
    }

    public void bindData(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (isLazy()) {
            $jacocoInit[45] = true;
        } else {
            if (wXComponent != null) {
                $jacocoInit[46] = true;
            } else {
                $jacocoInit[47] = true;
                wXComponent = this;
            }
            super.bindData(wXComponent);
            $jacocoInit[48] = true;
            int childCount = childCount();
            int i = 0;
            $jacocoInit[49] = true;
            while (i < childCount) {
                $jacocoInit[51] = true;
                getChild(i).bindData(((WXVContainer) wXComponent).getChild(i));
                i++;
                $jacocoInit[52] = true;
            }
            $jacocoInit[50] = true;
        }
        $jacocoInit[53] = true;
    }

    public void refreshData(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent != null) {
            $jacocoInit[54] = true;
        } else {
            $jacocoInit[55] = true;
            wXComponent = this;
        }
        super.refreshData(wXComponent);
        $jacocoInit[56] = true;
        int childCount = childCount();
        int i = 0;
        $jacocoInit[57] = true;
        while (i < childCount) {
            $jacocoInit[58] = true;
            getChild(i).refreshData(((WXVContainer) wXComponent).getChild(i));
            i++;
            $jacocoInit[59] = true;
        }
        $jacocoInit[60] = true;
    }

    public ViewGroup getRealView() {
        boolean[] $jacocoInit = $jacocoInit();
        ViewGroup viewGroup = (ViewGroup) super.getRealView();
        $jacocoInit[61] = true;
        return viewGroup;
    }

    public void createViewImpl() {
        boolean[] $jacocoInit = $jacocoInit();
        super.createViewImpl();
        $jacocoInit[62] = true;
        int childCount = childCount();
        $jacocoInit[63] = true;
        int i = 0;
        while (i < childCount) {
            $jacocoInit[64] = true;
            createChildViewAt(i);
            i++;
            $jacocoInit[65] = true;
        }
        if (getHostView() == null) {
            $jacocoInit[66] = true;
        } else {
            $jacocoInit[67] = true;
            ((ViewGroup) getHostView()).setClipToPadding(false);
            $jacocoInit[68] = true;
        }
        $jacocoInit[69] = true;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mChildren == null) {
            $jacocoInit[70] = true;
        } else {
            $jacocoInit[71] = true;
            int size = this.mChildren.size();
            int i = 0;
            $jacocoInit[72] = true;
            while (i < size) {
                $jacocoInit[73] = true;
                this.mChildren.get(i).destroy();
                i++;
                $jacocoInit[74] = true;
            }
            this.mChildren.clear();
            $jacocoInit[75] = true;
        }
        super.destroy();
        $jacocoInit[76] = true;
    }

    public void recycled() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mChildren == null) {
            $jacocoInit[77] = true;
        } else if (isFixed()) {
            $jacocoInit[78] = true;
        } else if (!getAttrs().canRecycled()) {
            $jacocoInit[79] = true;
        } else {
            $jacocoInit[80] = true;
            int size = this.mChildren.size();
            int i = 0;
            $jacocoInit[81] = true;
            while (i < size) {
                $jacocoInit[83] = true;
                this.mChildren.get(i).recycled();
                i++;
                $jacocoInit[84] = true;
            }
            $jacocoInit[82] = true;
        }
        super.recycled();
        $jacocoInit[85] = true;
    }

    public View detachViewAndClearPreInfo() {
        boolean[] $jacocoInit = $jacocoInit();
        View detachViewAndClearPreInfo = super.detachViewAndClearPreInfo();
        if (this.mChildren == null) {
            $jacocoInit[86] = true;
        } else {
            $jacocoInit[87] = true;
            int childCount = childCount();
            int i = 0;
            $jacocoInit[88] = true;
            while (i < childCount) {
                $jacocoInit[90] = true;
                this.mChildren.get(i).detachViewAndClearPreInfo();
                i++;
                $jacocoInit[91] = true;
            }
            $jacocoInit[89] = true;
        }
        $jacocoInit[92] = true;
        return detachViewAndClearPreInfo;
    }

    public int childCount() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mChildren == null) {
            i = 0;
            $jacocoInit[93] = true;
        } else {
            i = this.mChildren.size();
            $jacocoInit[94] = true;
        }
        $jacocoInit[95] = true;
        return i;
    }

    public WXComponent getChild(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mChildren == null) {
            $jacocoInit[96] = true;
        } else if (i < 0) {
            $jacocoInit[97] = true;
        } else if (i >= this.mChildren.size()) {
            $jacocoInit[98] = true;
        } else {
            WXComponent wXComponent = this.mChildren.get(i);
            $jacocoInit[100] = true;
            return wXComponent;
        }
        $jacocoInit[99] = true;
        return null;
    }

    public int getChildCount() {
        boolean[] $jacocoInit = $jacocoInit();
        int childCount = childCount();
        $jacocoInit[101] = true;
        return childCount;
    }

    public void addChild(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        addChild(wXComponent, -1);
        $jacocoInit[102] = true;
    }

    public void addChild(WXComponent wXComponent, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent == null) {
            $jacocoInit[103] = true;
        } else if (i < -1) {
            $jacocoInit[104] = true;
        } else {
            wXComponent.mDeepInComponentTree = this.mDeepInComponentTree + 1;
            $jacocoInit[106] = true;
            getInstance().setMaxDomDeep(wXComponent.mDeepInComponentTree);
            $jacocoInit[107] = true;
            if (i >= this.mChildren.size()) {
                $jacocoInit[108] = true;
                i = -1;
            } else {
                $jacocoInit[109] = true;
            }
            if (i == -1) {
                $jacocoInit[110] = true;
                this.mChildren.add(wXComponent);
                $jacocoInit[111] = true;
            } else {
                this.mChildren.add(i, wXComponent);
                $jacocoInit[112] = true;
            }
            $jacocoInit[113] = true;
            return;
        }
        $jacocoInit[105] = true;
    }

    public final int indexOf(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        int indexOf = this.mChildren.indexOf(wXComponent);
        $jacocoInit[114] = true;
        return indexOf;
    }

    public void createChildViewAt(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        Pair<WXComponent, Integer> rearrangeIndexAndGetChild = rearrangeIndexAndGetChild(i);
        if (rearrangeIndexAndGetChild.first == null) {
            $jacocoInit[115] = true;
        } else {
            WXComponent wXComponent = (WXComponent) rearrangeIndexAndGetChild.first;
            $jacocoInit[116] = true;
            wXComponent.createView();
            $jacocoInit[117] = true;
            if (wXComponent.isVirtualComponent()) {
                $jacocoInit[118] = true;
            } else {
                $jacocoInit[119] = true;
                addSubView(wXComponent.getHostView(), ((Integer) rearrangeIndexAndGetChild.second).intValue());
                $jacocoInit[120] = true;
            }
        }
        $jacocoInit[121] = true;
    }

    /* access modifiers changed from: protected */
    public Pair<WXComponent, Integer> rearrangeIndexAndGetChild(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i >= 0) {
            $jacocoInit[122] = true;
        } else {
            $jacocoInit[123] = true;
            i = childCount() - 1;
            $jacocoInit[124] = true;
        }
        if (i < 0) {
            $jacocoInit[125] = true;
            Pair<WXComponent, Integer> pair = new Pair<>((Object) null, Integer.valueOf(i));
            $jacocoInit[126] = true;
            return pair;
        }
        Pair<WXComponent, Integer> pair2 = new Pair<>(getChild(i), Integer.valueOf(i));
        $jacocoInit[127] = true;
        return pair2;
    }

    public void addSubView(View view, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (view == null) {
            $jacocoInit[128] = true;
        } else if (getRealView() == null) {
            $jacocoInit[129] = true;
        } else {
            if (i >= getRealView().getChildCount()) {
                $jacocoInit[131] = true;
                i = -1;
            } else {
                $jacocoInit[132] = true;
            }
            if (i == -1) {
                $jacocoInit[133] = true;
                getRealView().addView(view);
                $jacocoInit[134] = true;
            } else {
                getRealView().addView(view, i);
                $jacocoInit[135] = true;
            }
            WXSDKInstance instance = getInstance();
            if (instance == null) {
                $jacocoInit[136] = true;
            } else {
                $jacocoInit[137] = true;
                instance.getExceptionRecorder().hasAddView.set(true);
                $jacocoInit[138] = true;
            }
            $jacocoInit[139] = true;
            return;
        }
        $jacocoInit[130] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void remove(com.taobao.weex.ui.component.WXComponent r5, boolean r6) {
        /*
            r4 = this;
            boolean[] r0 = $jacocoInit()
            r1 = 1
            if (r5 != 0) goto L_0x000c
            r5 = 140(0x8c, float:1.96E-43)
            r0[r5] = r1
            goto L_0x0021
        L_0x000c:
            java.util.ArrayList<com.taobao.weex.ui.component.WXComponent> r2 = r4.mChildren
            if (r2 != 0) goto L_0x0015
            r5 = 141(0x8d, float:1.98E-43)
            r0[r5] = r1
            goto L_0x0021
        L_0x0015:
            java.util.ArrayList<com.taobao.weex.ui.component.WXComponent> r2 = r4.mChildren
            int r2 = r2.size()
            if (r2 != 0) goto L_0x0026
            r5 = 142(0x8e, float:1.99E-43)
            r0[r5] = r1
        L_0x0021:
            r5 = 143(0x8f, float:2.0E-43)
            r0[r5] = r1
            return
        L_0x0026:
            java.util.ArrayList<com.taobao.weex.ui.component.WXComponent> r2 = r4.mChildren
            r2.remove(r5)
            r2 = 144(0x90, float:2.02E-43)
            r0[r2] = r1
            com.taobao.weex.WXSDKInstance r2 = r4.getInstance()
            if (r2 != 0) goto L_0x003a
            r2 = 145(0x91, float:2.03E-43)
            r0[r2] = r1
            goto L_0x005b
        L_0x003a:
            r2 = 146(0x92, float:2.05E-43)
            r0[r2] = r1
            com.taobao.weex.WXSDKInstance r2 = r4.getInstance()
            android.view.View r2 = r2.getRootView()
            if (r2 != 0) goto L_0x004d
            r2 = 147(0x93, float:2.06E-43)
            r0[r2] = r1
            goto L_0x005b
        L_0x004d:
            r2 = 148(0x94, float:2.07E-43)
            r0[r2] = r1
            boolean r2 = r5.isFixed()
            if (r2 != 0) goto L_0x008c
            r2 = 149(0x95, float:2.09E-43)
            r0[r2] = r1
        L_0x005b:
            android.view.ViewGroup r2 = r4.getRealView()
            if (r2 != 0) goto L_0x0066
            r2 = 152(0x98, float:2.13E-43)
            r0[r2] = r1
            goto L_0x009f
        L_0x0066:
            r2 = 153(0x99, float:2.14E-43)
            r0[r2] = r1
            boolean r2 = r5.isVirtualComponent()
            if (r2 != 0) goto L_0x0084
            r2 = 154(0x9a, float:2.16E-43)
            r0[r2] = r1
            android.view.ViewGroup r2 = r4.getRealView()
            android.view.View r3 = r5.getHostView()
            r2.removeView(r3)
            r2 = 155(0x9b, float:2.17E-43)
            r0[r2] = r1
            goto L_0x009f
        L_0x0084:
            r5.removeVirtualComponent()
            r2 = 156(0x9c, float:2.19E-43)
            r0[r2] = r1
            goto L_0x009f
        L_0x008c:
            r2 = 150(0x96, float:2.1E-43)
            r0[r2] = r1
            com.taobao.weex.WXSDKInstance r2 = r4.getInstance()
            android.view.View r3 = r5.getHostView()
            r2.removeFixedView(r3)
            r2 = 151(0x97, float:2.12E-43)
            r0[r2] = r1
        L_0x009f:
            if (r6 != 0) goto L_0x00a6
            r5 = 157(0x9d, float:2.2E-43)
            r0[r5] = r1
            goto L_0x00b1
        L_0x00a6:
            r6 = 158(0x9e, float:2.21E-43)
            r0[r6] = r1
            r5.destroy()
            r5 = 159(0x9f, float:2.23E-43)
            r0[r5] = r1
        L_0x00b1:
            r5 = 160(0xa0, float:2.24E-43)
            r0[r5] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXVContainer.remove(com.taobao.weex.ui.component.WXComponent, boolean):void");
    }

    public void notifyAppearStateChange(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        super.notifyAppearStateChange(str, str2);
        $jacocoInit[161] = true;
        if (getHostView() == null) {
            $jacocoInit[162] = true;
        } else if (this.mChildren == null) {
            $jacocoInit[163] = true;
        } else {
            Iterator<WXComponent> it = this.mChildren.iterator();
            $jacocoInit[165] = true;
            while (it.hasNext()) {
                WXComponent next = it.next();
                $jacocoInit[166] = true;
                if (next.getHostView() == null) {
                    $jacocoInit[167] = true;
                } else if (next.getHostView().getVisibility() == 0) {
                    $jacocoInit[168] = true;
                } else {
                    str = Constants.Event.DISAPPEAR;
                    $jacocoInit[169] = true;
                }
                next.notifyAppearStateChange(str, str2);
                $jacocoInit[170] = true;
            }
            $jacocoInit[171] = true;
            return;
        }
        $jacocoInit[164] = true;
    }

    public void onActivityCreate() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onActivityCreate();
        $jacocoInit[172] = true;
        int childCount = childCount();
        $jacocoInit[173] = true;
        int i = 0;
        while (i < childCount) {
            $jacocoInit[174] = true;
            getChild(i).onActivityCreate();
            i++;
            $jacocoInit[175] = true;
        }
        $jacocoInit[176] = true;
    }

    public void onActivityStart() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onActivityStart();
        $jacocoInit[177] = true;
        int childCount = childCount();
        $jacocoInit[178] = true;
        int i = 0;
        while (i < childCount) {
            $jacocoInit[179] = true;
            getChild(i).onActivityStart();
            i++;
            $jacocoInit[180] = true;
        }
        $jacocoInit[181] = true;
    }

    public void onActivityPause() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onActivityPause();
        $jacocoInit[182] = true;
        int childCount = childCount();
        $jacocoInit[183] = true;
        int i = 0;
        while (i < childCount) {
            $jacocoInit[184] = true;
            getChild(i).onActivityPause();
            i++;
            $jacocoInit[185] = true;
        }
        $jacocoInit[186] = true;
    }

    public void onActivityResume() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onActivityResume();
        $jacocoInit[187] = true;
        int childCount = childCount();
        $jacocoInit[188] = true;
        int i = 0;
        while (i < childCount) {
            $jacocoInit[189] = true;
            getChild(i).onActivityResume();
            i++;
            $jacocoInit[190] = true;
        }
        $jacocoInit[191] = true;
    }

    public void onActivityStop() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onActivityStop();
        $jacocoInit[192] = true;
        int childCount = childCount();
        $jacocoInit[193] = true;
        int i = 0;
        while (i < childCount) {
            $jacocoInit[194] = true;
            getChild(i).onActivityStop();
            i++;
            $jacocoInit[195] = true;
        }
        $jacocoInit[196] = true;
    }

    public void onActivityDestroy() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onActivityDestroy();
        $jacocoInit[197] = true;
        int childCount = childCount();
        $jacocoInit[198] = true;
        int i = 0;
        while (i < childCount) {
            $jacocoInit[199] = true;
            getChild(i).onActivityDestroy();
            i++;
            $jacocoInit[200] = true;
        }
        $jacocoInit[201] = true;
    }

    public boolean onActivityBack() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onActivityBack();
        $jacocoInit[202] = true;
        int childCount = childCount();
        $jacocoInit[203] = true;
        int i = 0;
        while (i < childCount) {
            $jacocoInit[204] = true;
            getChild(i).onActivityBack();
            i++;
            $jacocoInit[205] = true;
        }
        $jacocoInit[206] = true;
        return false;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onActivityResult(i, i2, intent);
        $jacocoInit[207] = true;
        int childCount = childCount();
        $jacocoInit[208] = true;
        int i3 = 0;
        while (i3 < childCount) {
            $jacocoInit[209] = true;
            getChild(i3).onActivityResult(i, i2, intent);
            i3++;
            $jacocoInit[210] = true;
        }
        $jacocoInit[211] = true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onCreateOptionsMenu(menu);
        $jacocoInit[212] = true;
        int childCount = childCount();
        $jacocoInit[213] = true;
        int i = 0;
        while (i < childCount) {
            $jacocoInit[214] = true;
            getChild(i).onCreateOptionsMenu(menu);
            i++;
            $jacocoInit[215] = true;
        }
        $jacocoInit[216] = true;
        return false;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onRequestPermissionsResult(i, strArr, iArr);
        $jacocoInit[217] = true;
        int childCount = childCount();
        $jacocoInit[218] = true;
        int i2 = 0;
        while (i2 < childCount) {
            $jacocoInit[219] = true;
            getChild(i2).onRequestPermissionsResult(i, strArr, iArr);
            i2++;
            $jacocoInit[220] = true;
        }
        $jacocoInit[221] = true;
    }

    public void onRenderFinish(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[222] = true;
        int i2 = 0;
        while (i2 < getChildCount()) {
            $jacocoInit[223] = true;
            WXComponent child = getChild(i2);
            child.mTraceInfo.uiQueueTime = this.mTraceInfo.uiQueueTime;
            $jacocoInit[224] = true;
            child.onRenderFinish(i);
            i2++;
            $jacocoInit[225] = true;
        }
        super.onRenderFinish(i);
        $jacocoInit[226] = true;
    }

    @JSMethod
    public void releaseImageList(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[227] = true;
        } else {
            $jacocoInit[228] = true;
            if (!ViewCompat.isAttachedToWindow(getHostView())) {
                $jacocoInit[229] = true;
            } else {
                $jacocoInit[230] = true;
                if (!(getHostView() instanceof ViewGroup)) {
                    $jacocoInit[231] = true;
                } else {
                    int i = 0;
                    if (WXUtils.getBoolean(str, false).booleanValue()) {
                        $jacocoInit[233] = true;
                        doViewTreeRecycleImageView((ViewGroup) getHostView(), true);
                        $jacocoInit[234] = true;
                    } else {
                        int childCount = getChildCount();
                        $jacocoInit[235] = true;
                        while (i < childCount) {
                            $jacocoInit[237] = true;
                            WXComponent child = getChild(i);
                            $jacocoInit[238] = true;
                            if (!(child instanceof WXImage)) {
                                $jacocoInit[239] = true;
                            } else if (!(((WXImage) child).getHostView() instanceof WXImageView)) {
                                $jacocoInit[240] = true;
                            } else {
                                $jacocoInit[241] = true;
                                WXImageView wXImageView = (WXImageView) child.getHostView();
                                $jacocoInit[242] = true;
                                if (wXImageView == null) {
                                    $jacocoInit[243] = true;
                                } else if (!ViewCompat.isAttachedToWindow(wXImageView)) {
                                    $jacocoInit[244] = true;
                                } else {
                                    $jacocoInit[245] = true;
                                    wXImageView.autoReleaseImage();
                                    $jacocoInit[246] = true;
                                }
                                $jacocoInit[247] = true;
                                i++;
                                $jacocoInit[251] = true;
                            }
                            if (!(child instanceof WXVContainer)) {
                                $jacocoInit[248] = true;
                            } else {
                                $jacocoInit[249] = true;
                                ((WXVContainer) child).releaseImageList(str);
                                $jacocoInit[250] = true;
                            }
                            i++;
                            $jacocoInit[251] = true;
                        }
                        $jacocoInit[236] = true;
                    }
                    $jacocoInit[252] = true;
                    return;
                }
            }
        }
        $jacocoInit[232] = true;
    }

    @JSMethod
    public void recoverImageList(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getHostView() == null) {
            $jacocoInit[253] = true;
        } else {
            $jacocoInit[254] = true;
            if (!ViewCompat.isAttachedToWindow(getHostView())) {
                $jacocoInit[255] = true;
            } else {
                $jacocoInit[256] = true;
                if (!(getHostView() instanceof ViewGroup)) {
                    $jacocoInit[257] = true;
                } else {
                    int i = 0;
                    if (WXUtils.getBoolean(str, false).booleanValue()) {
                        $jacocoInit[259] = true;
                        doViewTreeRecycleImageView((ViewGroup) getHostView(), false);
                        $jacocoInit[260] = true;
                    } else {
                        int childCount = getChildCount();
                        $jacocoInit[261] = true;
                        while (i < childCount) {
                            $jacocoInit[263] = true;
                            WXComponent child = getChild(i);
                            $jacocoInit[264] = true;
                            if (!(child instanceof WXImage)) {
                                $jacocoInit[265] = true;
                            } else if (!(((WXImage) child).getHostView() instanceof WXImageView)) {
                                $jacocoInit[266] = true;
                            } else {
                                $jacocoInit[267] = true;
                                WXImageView wXImageView = (WXImageView) child.getHostView();
                                $jacocoInit[268] = true;
                                if (wXImageView == null) {
                                    $jacocoInit[269] = true;
                                } else if (!ViewCompat.isAttachedToWindow(wXImageView)) {
                                    $jacocoInit[270] = true;
                                } else {
                                    $jacocoInit[271] = true;
                                    wXImageView.autoRecoverImage();
                                    $jacocoInit[272] = true;
                                }
                                $jacocoInit[273] = true;
                                i++;
                                $jacocoInit[277] = true;
                            }
                            if (!(child instanceof WXVContainer)) {
                                $jacocoInit[274] = true;
                            } else {
                                $jacocoInit[275] = true;
                                ((WXVContainer) child).recoverImageList(str);
                                $jacocoInit[276] = true;
                            }
                            i++;
                            $jacocoInit[277] = true;
                        }
                        $jacocoInit[262] = true;
                    }
                    $jacocoInit[278] = true;
                    return;
                }
            }
        }
        $jacocoInit[258] = true;
    }

    private void doViewTreeRecycleImageView(ViewGroup viewGroup, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        int childCount = viewGroup.getChildCount();
        $jacocoInit[279] = true;
        int i = 0;
        while (i < childCount) {
            $jacocoInit[280] = true;
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof WXImageView) {
                if (z) {
                    $jacocoInit[281] = true;
                    ((WXImageView) childAt).autoReleaseImage();
                    $jacocoInit[282] = true;
                } else {
                    ((WXImageView) childAt).autoRecoverImage();
                    $jacocoInit[283] = true;
                }
            } else if (!(childAt instanceof ViewGroup)) {
                $jacocoInit[284] = true;
            } else {
                $jacocoInit[285] = true;
                doViewTreeRecycleImageView((ViewGroup) childAt, z);
                $jacocoInit[286] = true;
            }
            i++;
            $jacocoInit[287] = true;
        }
        $jacocoInit[288] = true;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mGesture == null) {
            $jacocoInit[289] = true;
        } else {
            $jacocoInit[290] = true;
            if (this.mGesture.isRequestDisallowInterceptTouchEvent()) {
                $jacocoInit[291] = true;
                return;
            } else {
                this.mGesture.setRequestDisallowInterceptTouchEvent(z);
                $jacocoInit[292] = true;
            }
        }
        if (getParent() == null) {
            $jacocoInit[293] = true;
        } else {
            $jacocoInit[294] = true;
            getParent().requestDisallowInterceptTouchEvent(z);
            $jacocoInit[295] = true;
        }
        $jacocoInit[296] = true;
    }

    @Nullable
    public View getBoxShadowHost(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (z) {
            WXVContainer<T>.BoxShadowHost boxShadowHost = this.mBoxShadowHost;
            $jacocoInit[297] = true;
            return boxShadowHost;
        }
        ViewGroup viewGroup = (ViewGroup) getHostView();
        if (viewGroup != null) {
            $jacocoInit[298] = true;
            try {
                String componentType = getComponentType();
                $jacocoInit[300] = true;
                if ("div".equals(componentType)) {
                    $jacocoInit[301] = true;
                    WXLogUtils.d("BoxShadow", "Draw box-shadow with BoxShadowHost on div: " + toString());
                    if (this.mBoxShadowHost != null) {
                        $jacocoInit[302] = true;
                    } else {
                        $jacocoInit[303] = true;
                        this.mBoxShadowHost = new BoxShadowHost(this, getContext());
                        $jacocoInit[304] = true;
                        WXViewUtils.setBackGround(this.mBoxShadowHost, (Drawable) null, this);
                        $jacocoInit[305] = true;
                        CSSShorthand padding = getPadding();
                        $jacocoInit[306] = true;
                        CSSShorthand border = getBorder();
                        $jacocoInit[307] = true;
                        $jacocoInit[308] = true;
                        $jacocoInit[309] = true;
                        $jacocoInit[310] = true;
                        $jacocoInit[311] = true;
                        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(viewGroup.getLayoutParams());
                        $jacocoInit[312] = true;
                        setMarginsSupportRTL(marginLayoutParams, -((int) (padding.get(CSSShorthand.EDGE.LEFT) + border.get(CSSShorthand.EDGE.LEFT))), -((int) (padding.get(CSSShorthand.EDGE.TOP) + border.get(CSSShorthand.EDGE.TOP))), -((int) (padding.get(CSSShorthand.EDGE.RIGHT) + border.get(CSSShorthand.EDGE.RIGHT))), -((int) (padding.get(CSSShorthand.EDGE.BOTTOM) + border.get(CSSShorthand.EDGE.BOTTOM))));
                        $jacocoInit[313] = true;
                        this.mBoxShadowHost.setLayoutParams(marginLayoutParams);
                        $jacocoInit[314] = true;
                        viewGroup.addView(this.mBoxShadowHost);
                        $jacocoInit[315] = true;
                    }
                    viewGroup.removeView(this.mBoxShadowHost);
                    $jacocoInit[316] = true;
                    viewGroup.addView(this.mBoxShadowHost);
                    WXVContainer<T>.BoxShadowHost boxShadowHost2 = this.mBoxShadowHost;
                    $jacocoInit[317] = true;
                    return boxShadowHost2;
                }
                $jacocoInit[318] = true;
                $jacocoInit[321] = true;
                return viewGroup;
            } catch (Throwable th) {
                $jacocoInit[319] = true;
                WXLogUtils.w("BoxShadow", th);
                $jacocoInit[320] = true;
            }
        } else {
            $jacocoInit[299] = true;
            return null;
        }
    }

    private class BoxShadowHost extends View {
        private static transient /* synthetic */ boolean[] $jacocoData;
        final /* synthetic */ WXVContainer this$0;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-3256462062417657421L, "com/taobao/weex/ui/component/WXVContainer$BoxShadowHost", 2);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public BoxShadowHost(WXVContainer wXVContainer, Context context) {
            super(context);
            boolean[] $jacocoInit = $jacocoInit();
            this.this$0 = wXVContainer;
            $jacocoInit[0] = true;
            $jacocoInit[1] = true;
        }
    }

    public void appendTreeCreateFinish() {
        $jacocoInit()[322] = true;
    }
}
