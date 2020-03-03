package com.taobao.weex.ui.component.list.template;

import android.support.v4.util.ArrayMap;
import java.util.ArrayList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class TemplateStickyHelper {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private List<String> mStickyTypes;
    private WXRecyclerTemplateList recyclerTemplateList;
    private ArrayMap<Integer, TemplateViewHolder> stickyHolderCache;
    private List<Integer> stickyPositions = new ArrayList();

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4215354922452727909L, "com/taobao/weex/ui/component/list/template/TemplateStickyHelper", 143);
        $jacocoData = a2;
        return a2;
    }

    public TemplateStickyHelper(WXRecyclerTemplateList wXRecyclerTemplateList) {
        boolean[] $jacocoInit = $jacocoInit();
        this.recyclerTemplateList = wXRecyclerTemplateList;
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
        this.stickyHolderCache = new ArrayMap<>();
        $jacocoInit[2] = true;
        this.mStickyTypes = new ArrayList(8);
        $jacocoInit[3] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:117:0x03b8  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0436  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x0450  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0455  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0484  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x04aa  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x033d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBeforeScroll(int r19, int r20) {
        /*
            r18 = this;
            r0 = r18
            boolean[] r1 = $jacocoInit()
            java.util.List<java.lang.Integer> r2 = r0.stickyPositions
            r3 = 4
            r4 = 1
            if (r2 != 0) goto L_0x000f
            r1[r3] = r4
            goto L_0x001a
        L_0x000f:
            java.util.List<java.lang.Integer> r2 = r0.stickyPositions
            int r2 = r2.size()
            if (r2 != 0) goto L_0x001e
            r2 = 5
            r1[r2] = r4
        L_0x001a:
            r2 = 6
            r1[r2] = r4
            return
        L_0x001e:
            com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList r2 = r0.recyclerTemplateList
            android.view.View r2 = r2.getHostView()
            com.taobao.weex.ui.view.refresh.wrapper.BounceRecyclerView r2 = (com.taobao.weex.ui.view.refresh.wrapper.BounceRecyclerView) r2
            r5 = 7
            r1[r5] = r4
            com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList r5 = r0.recyclerTemplateList
            android.view.View r5 = r5.getHostView()
            com.taobao.weex.ui.view.refresh.wrapper.BounceRecyclerView r5 = (com.taobao.weex.ui.view.refresh.wrapper.BounceRecyclerView) r5
            android.view.View r5 = r5.getInnerView()
            android.support.v7.widget.RecyclerView r5 = (android.support.v7.widget.RecyclerView) r5
            r6 = 8
            r1[r6] = r4
            android.support.v7.widget.RecyclerView$LayoutManager r7 = r5.getLayoutManager()
            boolean r8 = r7 instanceof android.support.v7.widget.LinearLayoutManager
            r9 = -1
            r10 = 0
            if (r8 == 0) goto L_0x005c
            r8 = 9
            r1[r8] = r4
            android.support.v7.widget.LinearLayoutManager r7 = (android.support.v7.widget.LinearLayoutManager) r7
            int r8 = r7.findFirstVisibleItemPosition()
            r11 = 10
            r1[r11] = r4
            int r7 = r7.findLastVisibleItemPosition()
            r11 = 11
            r1[r11] = r4
            goto L_0x0085
        L_0x005c:
            boolean r8 = r7 instanceof android.support.v7.widget.StaggeredGridLayoutManager
            if (r8 != 0) goto L_0x0067
            r7 = 12
            r1[r7] = r4
            r7 = -1
            r8 = -1
            goto L_0x0085
        L_0x0067:
            r8 = 3
            int[] r8 = new int[r8]
            r11 = 13
            r1[r11] = r4
            android.support.v7.widget.StaggeredGridLayoutManager r7 = (android.support.v7.widget.StaggeredGridLayoutManager) r7
            int[] r11 = r7.findFirstVisibleItemPositions(r8)
            r11 = r11[r10]
            r12 = 14
            r1[r12] = r4
            int[] r7 = r7.findLastVisibleItemPositions(r8)
            r7 = r7[r10]
            r8 = 15
            r1[r8] = r4
            r8 = r11
        L_0x0085:
            if (r8 >= 0) goto L_0x008c
            r2 = 16
            r1[r2] = r4
            return
        L_0x008c:
            android.support.v7.widget.RecyclerView$ViewHolder r11 = r5.findViewHolderForAdapterPosition(r8)
            com.taobao.weex.ui.component.list.template.TemplateViewHolder r11 = (com.taobao.weex.ui.component.list.template.TemplateViewHolder) r11
            if (r11 != 0) goto L_0x0099
            r2 = 17
            r1[r2] = r4
            return
        L_0x0099:
            r12 = 18
            r1[r12] = r4
            java.util.List<java.lang.Integer> r12 = r0.stickyPositions
            java.util.Iterator r12 = r12.iterator()
            r13 = 19
            r1[r13] = r4
            r13 = -1
        L_0x00a8:
            boolean r14 = r12.hasNext()
            if (r14 != 0) goto L_0x00b3
            r12 = 20
            r1[r12] = r4
            goto L_0x00ca
        L_0x00b3:
            java.lang.Object r14 = r12.next()
            java.lang.Integer r14 = (java.lang.Integer) r14
            if (r14 != 0) goto L_0x00c0
            r14 = 21
            r1[r14] = r4
            goto L_0x00a8
        L_0x00c0:
            int r15 = r14.intValue()
            if (r15 <= r8) goto L_0x0502
            r12 = 22
            r1[r12] = r4
        L_0x00ca:
            r15 = 0
            if (r13 >= 0) goto L_0x019f
            r3 = 25
            r1[r3] = r4
            int r3 = r2.getChildCount()
            int r3 = r3 - r4
            android.view.View r3 = r2.getChildAt(r3)
            r6 = 26
            r1[r6] = r4
            java.lang.Object r6 = r3.getTag()
            boolean r6 = r6 instanceof com.taobao.weex.ui.component.list.template.TemplateViewHolder
            if (r6 != 0) goto L_0x00eb
            r2 = 27
            r1[r2] = r4
            goto L_0x0140
        L_0x00eb:
            r6 = 28
            r1[r6] = r4
            java.lang.Object r3 = r3.getTag()
            com.taobao.weex.ui.component.list.template.TemplateViewHolder r3 = (com.taobao.weex.ui.component.list.template.TemplateViewHolder) r3
            r6 = 29
            r1[r6] = r4
            android.view.View r6 = r3.itemView
            r2.removeView(r6)
            r2 = 30
            r1[r2] = r4
            android.view.View r2 = r3.itemView
            r2.setTranslationY(r15)
            r2 = 31
            r1[r2] = r4
            com.taobao.weex.ui.component.WXComponent r2 = r3.getComponent()
            if (r2 != 0) goto L_0x0116
            r2 = 32
            r1[r2] = r4
            goto L_0x0140
        L_0x0116:
            r2 = 33
            r1[r2] = r4
            com.taobao.weex.ui.component.WXComponent r2 = r3.getComponent()
            com.taobao.weex.dom.WXEvent r2 = r2.getEvents()
            java.lang.String r6 = "unsticky"
            boolean r2 = r2.contains(r6)
            if (r2 != 0) goto L_0x012f
            r2 = 34
            r1[r2] = r4
            goto L_0x0140
        L_0x012f:
            r2 = 35
            r1[r2] = r4
            com.taobao.weex.ui.component.WXComponent r2 = r3.getComponent()
            java.lang.String r3 = "unsticky"
            r2.fireEvent(r3)
            r2 = 36
            r1[r2] = r4
        L_0x0140:
            r2 = 37
            r1[r2] = r4
            r2 = 0
        L_0x0145:
            int r3 = r5.getChildCount()
            if (r2 >= r3) goto L_0x019a
            r3 = 38
            r1[r3] = r4
            android.view.View r3 = r5.getChildAt(r2)
            r6 = 39
            r1[r6] = r4
            android.support.v7.widget.RecyclerView$ViewHolder r6 = r5.getChildViewHolder(r3)
            com.taobao.weex.ui.component.list.template.TemplateViewHolder r6 = (com.taobao.weex.ui.component.list.template.TemplateViewHolder) r6
            if (r6 != 0) goto L_0x0164
            r3 = 40
            r1[r3] = r4
            goto L_0x0193
        L_0x0164:
            int r6 = r6.getAdapterPosition()
            r7 = 41
            r1[r7] = r4
            java.util.List<java.lang.Integer> r7 = r0.stickyPositions
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            boolean r6 = r7.contains(r6)
            if (r6 != 0) goto L_0x017d
            r3 = 42
            r1[r3] = r4
            goto L_0x0193
        L_0x017d:
            int r6 = r3.getVisibility()
            if (r6 != 0) goto L_0x0188
            r3 = 43
            r1[r3] = r4
            goto L_0x0193
        L_0x0188:
            r6 = 44
            r1[r6] = r4
            r3.setVisibility(r10)
            r3 = 45
            r1[r3] = r4
        L_0x0193:
            int r2 = r2 + 1
            r3 = 46
            r1[r3] = r4
            goto L_0x0145
        L_0x019a:
            r2 = 47
            r1[r2] = r4
            return
        L_0x019f:
            int r12 = r2.getChildCount()
            int r12 = r12 - r4
            android.view.View r12 = r2.getChildAt(r12)
            r14 = 48
            r1[r14] = r4
            java.lang.Object r14 = r12.getTag()
            boolean r14 = r14 instanceof com.taobao.weex.ui.component.list.template.TemplateViewHolder
            if (r14 != 0) goto L_0x01b9
            r14 = 49
            r1[r14] = r4
            goto L_0x01d4
        L_0x01b9:
            r14 = 50
            r1[r14] = r4
            java.lang.Object r14 = r12.getTag()
            com.taobao.weex.ui.component.list.template.TemplateViewHolder r14 = (com.taobao.weex.ui.component.list.template.TemplateViewHolder) r14
            int r14 = r14.getHolderPosition()
            if (r14 != r13) goto L_0x01d0
            r2 = 51
            r1[r2] = r4
            r2 = r12
            goto L_0x0328
        L_0x01d0:
            r14 = 52
            r1[r14] = r4
        L_0x01d4:
            java.lang.Object r14 = r12.getTag()
            boolean r14 = r14 instanceof com.taobao.weex.ui.component.list.template.TemplateViewHolder
            if (r14 != 0) goto L_0x01e1
            r12 = 53
            r1[r12] = r4
            goto L_0x024b
        L_0x01e1:
            r14 = 54
            r1[r14] = r4
            java.lang.Object r14 = r12.getTag()
            com.taobao.weex.ui.component.list.template.TemplateViewHolder r14 = (com.taobao.weex.ui.component.list.template.TemplateViewHolder) r14
            int r14 = r14.getHolderPosition()
            if (r14 != r13) goto L_0x01f6
            r12 = 55
            r1[r12] = r4
            goto L_0x024b
        L_0x01f6:
            r14 = 56
            r1[r14] = r4
            java.lang.Object r12 = r12.getTag()
            com.taobao.weex.ui.component.list.template.TemplateViewHolder r12 = (com.taobao.weex.ui.component.list.template.TemplateViewHolder) r12
            r14 = 57
            r1[r14] = r4
            android.view.View r14 = r12.itemView
            r2.removeView(r14)
            r14 = 58
            r1[r14] = r4
            android.view.View r14 = r12.itemView
            r14.setTranslationY(r15)
            r14 = 59
            r1[r14] = r4
            com.taobao.weex.ui.component.WXComponent r14 = r12.getComponent()
            if (r14 != 0) goto L_0x0221
            r12 = 60
            r1[r12] = r4
            goto L_0x024b
        L_0x0221:
            r14 = 61
            r1[r14] = r4
            com.taobao.weex.ui.component.WXComponent r14 = r12.getComponent()
            com.taobao.weex.dom.WXEvent r14 = r14.getEvents()
            java.lang.String r6 = "unsticky"
            boolean r6 = r14.contains(r6)
            if (r6 != 0) goto L_0x023a
            r6 = 62
            r1[r6] = r4
            goto L_0x024b
        L_0x023a:
            r6 = 63
            r1[r6] = r4
            com.taobao.weex.ui.component.WXComponent r6 = r12.getComponent()
            java.lang.String r12 = "unsticky"
            r6.fireEvent(r12)
            r6 = 64
            r1[r6] = r4
        L_0x024b:
            com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList r6 = r0.recyclerTemplateList
            int r6 = r6.getItemViewType(r13)
            r12 = 65
            r1[r12] = r4
            android.support.v4.util.ArrayMap<java.lang.Integer, com.taobao.weex.ui.component.list.template.TemplateViewHolder> r12 = r0.stickyHolderCache
            java.lang.Integer r14 = java.lang.Integer.valueOf(r6)
            java.lang.Object r12 = r12.get(r14)
            com.taobao.weex.ui.component.list.template.TemplateViewHolder r12 = (com.taobao.weex.ui.component.list.template.TemplateViewHolder) r12
            if (r12 == 0) goto L_0x0268
            r6 = 66
            r1[r6] = r4
            goto L_0x0283
        L_0x0268:
            r12 = 67
            r1[r12] = r4
            com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList r12 = r0.recyclerTemplateList
            com.taobao.weex.ui.component.list.template.TemplateViewHolder r12 = r12.onCreateViewHolder((android.view.ViewGroup) r5, (int) r6)
            r14 = 68
            r1[r14] = r4
            android.support.v4.util.ArrayMap<java.lang.Integer, com.taobao.weex.ui.component.list.template.TemplateViewHolder> r14 = r0.stickyHolderCache
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r14.put(r6, r12)
            r6 = 69
            r1[r6] = r4
        L_0x0283:
            com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList r6 = r0.recyclerTemplateList
            r6.onBindViewHolder((com.taobao.weex.ui.component.list.template.TemplateViewHolder) r12, (int) r13)
            r6 = 70
            r1[r6] = r4
            android.view.View r6 = r12.itemView
            r6.setTranslationY(r15)
            r6 = 71
            r1[r6] = r4
            android.view.View r6 = r12.itemView
            r6.setTag(r12)
            r6 = 72
            r1[r6] = r4
            android.widget.FrameLayout$LayoutParams r6 = new android.widget.FrameLayout$LayoutParams
            r13 = -2
            r6.<init>(r9, r13)
            r9 = 73
            r1[r9] = r4
            com.taobao.weex.ui.component.WXComponent r9 = r12.getComponent()
            r9.clearPreLayout()
            r9 = 74
            r1[r9] = r4
            android.view.View r9 = r12.itemView
            android.view.ViewParent r9 = r9.getParent()
            if (r9 != 0) goto L_0x02c0
            r9 = 75
            r1[r9] = r4
            goto L_0x02d9
        L_0x02c0:
            r9 = 76
            r1[r9] = r4
            android.view.View r9 = r12.itemView
            android.view.ViewParent r9 = r9.getParent()
            android.view.ViewGroup r9 = (android.view.ViewGroup) r9
            r13 = 77
            r1[r13] = r4
            android.view.View r13 = r12.itemView
            r9.removeView(r13)
            r9 = 78
            r1[r9] = r4
        L_0x02d9:
            android.view.View r9 = r12.itemView
            r2.addView(r9, r6)
            r2 = 79
            r1[r2] = r4
            com.taobao.weex.ui.component.WXComponent r2 = r12.getComponent()
            com.taobao.weex.ui.component.WXComponent r6 = r12.getComponent()
            r2.setLayout(r6)
            android.view.View r2 = r12.itemView
            r6 = 80
            r1[r6] = r4
            com.taobao.weex.ui.component.WXComponent r6 = r12.getComponent()
            if (r6 != 0) goto L_0x02fe
            r6 = 81
            r1[r6] = r4
            goto L_0x0328
        L_0x02fe:
            r6 = 82
            r1[r6] = r4
            com.taobao.weex.ui.component.WXComponent r6 = r12.getComponent()
            com.taobao.weex.dom.WXEvent r6 = r6.getEvents()
            java.lang.String r9 = "sticky"
            boolean r6 = r6.contains(r9)
            if (r6 != 0) goto L_0x0317
            r6 = 83
            r1[r6] = r4
            goto L_0x0328
        L_0x0317:
            r6 = 84
            r1[r6] = r4
            com.taobao.weex.ui.component.WXComponent r6 = r12.getComponent()
            java.lang.String r9 = "sticky"
            r6.fireEvent(r9)
            r6 = 85
            r1[r6] = r4
        L_0x0328:
            java.lang.Object r6 = r2.getTag()
            com.taobao.weex.ui.component.list.template.TemplateViewHolder r6 = (com.taobao.weex.ui.component.list.template.TemplateViewHolder) r6
            r9 = 86
            r1[r9] = r4
            r9 = 87
            r1[r9] = r4
            r9 = 0
        L_0x0337:
            int r12 = r5.getChildCount()
            if (r9 >= r12) goto L_0x03ae
            r12 = 88
            r1[r12] = r4
            android.view.View r12 = r5.getChildAt(r9)
            r13 = 89
            r1[r13] = r4
            android.support.v7.widget.RecyclerView$ViewHolder r13 = r5.getChildViewHolder(r12)
            com.taobao.weex.ui.component.list.template.TemplateViewHolder r13 = (com.taobao.weex.ui.component.list.template.TemplateViewHolder) r13
            if (r13 != 0) goto L_0x0356
            r12 = 90
            r1[r12] = r4
            goto L_0x03a6
        L_0x0356:
            int r13 = r13.getAdapterPosition()
            r14 = 91
            r1[r14] = r4
            java.util.List<java.lang.Integer> r14 = r0.stickyPositions
            java.lang.Integer r15 = java.lang.Integer.valueOf(r13)
            boolean r14 = r14.contains(r15)
            if (r14 != 0) goto L_0x036f
            r12 = 92
            r1[r12] = r4
            goto L_0x03a6
        L_0x036f:
            int r14 = r6.getHolderPosition()
            if (r13 != r14) goto L_0x0390
            r13 = 93
            r1[r13] = r4
            int r13 = r12.getVisibility()
            if (r13 != r3) goto L_0x0384
            r12 = 94
            r1[r12] = r4
            goto L_0x03a6
        L_0x0384:
            r13 = 95
            r1[r13] = r4
            r12.setVisibility(r3)
            r12 = 96
            r1[r12] = r4
            goto L_0x03a6
        L_0x0390:
            int r13 = r12.getVisibility()
            if (r13 != 0) goto L_0x039b
            r12 = 97
            r1[r12] = r4
            goto L_0x03a6
        L_0x039b:
            r13 = 98
            r1[r13] = r4
            r12.setVisibility(r10)
            r12 = 99
            r1[r12] = r4
        L_0x03a6:
            int r9 = r9 + 1
            r12 = 100
            r1[r12] = r4
            r15 = 0
            goto L_0x0337
        L_0x03ae:
            com.taobao.weex.ui.component.WXComponent r9 = r11.getComponent()
            boolean r9 = r9.isSticky()
            if (r9 == 0) goto L_0x0436
            r9 = 101(0x65, float:1.42E-43)
            r1[r9] = r4
            android.view.View r9 = r11.itemView
            float r9 = r9.getY()
            r12 = 0
            int r9 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r9 >= 0) goto L_0x0403
            r9 = 102(0x66, float:1.43E-43)
            r1[r9] = r4
            android.view.View r9 = r11.itemView
            int r9 = r9.getVisibility()
            if (r9 != r3) goto L_0x03d8
            r3 = 103(0x67, float:1.44E-43)
            r1[r3] = r4
            goto L_0x03e5
        L_0x03d8:
            r9 = 104(0x68, float:1.46E-43)
            r1[r9] = r4
            android.view.View r9 = r11.itemView
            r9.setVisibility(r3)
            r3 = 105(0x69, float:1.47E-43)
            r1[r3] = r4
        L_0x03e5:
            int r3 = r2.getVisibility()
            if (r3 != 0) goto L_0x03f0
            r3 = 106(0x6a, float:1.49E-43)
            r1[r3] = r4
            goto L_0x03fb
        L_0x03f0:
            r3 = 107(0x6b, float:1.5E-43)
            r1[r3] = r4
            r2.setVisibility(r10)
            r3 = 108(0x6c, float:1.51E-43)
            r1[r3] = r4
        L_0x03fb:
            r2.bringToFront()
            r2 = 109(0x6d, float:1.53E-43)
            r1[r2] = r4
            goto L_0x044c
        L_0x0403:
            android.view.View r3 = r11.itemView
            int r3 = r3.getVisibility()
            if (r3 != 0) goto L_0x0410
            r3 = 110(0x6e, float:1.54E-43)
            r1[r3] = r4
            goto L_0x041d
        L_0x0410:
            r3 = 111(0x6f, float:1.56E-43)
            r1[r3] = r4
            android.view.View r3 = r11.itemView
            r3.setVisibility(r10)
            r3 = 112(0x70, float:1.57E-43)
            r1[r3] = r4
        L_0x041d:
            int r3 = r2.getVisibility()
            r15 = 8
            if (r3 != r15) goto L_0x042a
            r2 = 113(0x71, float:1.58E-43)
            r1[r2] = r4
            goto L_0x044c
        L_0x042a:
            r3 = 114(0x72, float:1.6E-43)
            r1[r3] = r4
            r2.setVisibility(r15)
            r2 = 115(0x73, float:1.61E-43)
            r1[r2] = r4
            goto L_0x044c
        L_0x0436:
            int r3 = r2.getVisibility()
            if (r3 != 0) goto L_0x0441
            r2 = 116(0x74, float:1.63E-43)
            r1[r2] = r4
            goto L_0x044c
        L_0x0441:
            r3 = 117(0x75, float:1.64E-43)
            r1[r3] = r4
            r2.setVisibility(r10)
            r2 = 118(0x76, float:1.65E-43)
            r1[r2] = r4
        L_0x044c:
            int r16 = r8 + 1
            if (r7 > 0) goto L_0x0455
            r2 = 119(0x77, float:1.67E-43)
            r1[r2] = r4
            goto L_0x0461
        L_0x0455:
            r2 = 120(0x78, float:1.68E-43)
            r1[r2] = r4
            r2 = r16
        L_0x045b:
            if (r2 <= r7) goto L_0x0464
            r2 = 121(0x79, float:1.7E-43)
            r1[r2] = r4
        L_0x0461:
            r2 = r16
            goto L_0x0478
        L_0x0464:
            r3 = 122(0x7a, float:1.71E-43)
            r1[r3] = r4
            java.util.List<java.lang.Integer> r3 = r0.stickyPositions
            java.lang.Integer r8 = java.lang.Integer.valueOf(r2)
            boolean r3 = r3.contains(r8)
            if (r3 == 0) goto L_0x04f9
            r3 = 123(0x7b, float:1.72E-43)
            r1[r3] = r4
        L_0x0478:
            java.util.List<java.lang.Integer> r3 = r0.stickyPositions
            java.lang.Integer r7 = java.lang.Integer.valueOf(r2)
            boolean r3 = r3.contains(r7)
            if (r3 != 0) goto L_0x04aa
            r2 = 125(0x7d, float:1.75E-43)
            r1[r2] = r4
            android.view.View r2 = r6.itemView
            float r2 = r2.getTranslationY()
            r3 = 0
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 < 0) goto L_0x0498
            r2 = 126(0x7e, float:1.77E-43)
            r1[r2] = r4
            goto L_0x04a5
        L_0x0498:
            r2 = 127(0x7f, float:1.78E-43)
            r1[r2] = r4
            android.view.View r2 = r6.itemView
            r2.setTranslationY(r3)
            r2 = 128(0x80, float:1.794E-43)
            r1[r2] = r4
        L_0x04a5:
            r2 = 129(0x81, float:1.81E-43)
            r1[r2] = r4
            return
        L_0x04aa:
            android.support.v7.widget.RecyclerView$ViewHolder r2 = r5.findViewHolderForAdapterPosition(r2)
            com.taobao.weex.ui.component.list.template.TemplateViewHolder r2 = (com.taobao.weex.ui.component.list.template.TemplateViewHolder) r2
            if (r2 != 0) goto L_0x04b7
            r2 = 130(0x82, float:1.82E-43)
            r1[r2] = r4
            goto L_0x04c5
        L_0x04b7:
            r3 = 131(0x83, float:1.84E-43)
            r1[r3] = r4
            com.taobao.weex.ui.component.WXComponent r3 = r2.getComponent()
            if (r3 != 0) goto L_0x04ca
            r2 = 132(0x84, float:1.85E-43)
            r1[r2] = r4
        L_0x04c5:
            r2 = 133(0x85, float:1.86E-43)
            r1[r2] = r4
            return
        L_0x04ca:
            android.view.View r2 = r2.itemView
            float r2 = r2.getY()
            android.view.View r3 = r6.itemView
            int r3 = r3.getMeasuredHeight()
            float r3 = (float) r3
            float r2 = r2 - r3
            int r2 = (int) r2
            if (r2 > 0) goto L_0x04ea
            r3 = 134(0x86, float:1.88E-43)
            r1[r3] = r4
            android.view.View r3 = r6.itemView
            float r2 = (float) r2
            r3.setTranslationY(r2)
            r2 = 135(0x87, float:1.89E-43)
            r1[r2] = r4
            goto L_0x04f4
        L_0x04ea:
            android.view.View r2 = r6.itemView
            r3 = 0
            r2.setTranslationY(r3)
            r2 = 136(0x88, float:1.9E-43)
            r1[r2] = r4
        L_0x04f4:
            r2 = 137(0x89, float:1.92E-43)
            r1[r2] = r4
            return
        L_0x04f9:
            r3 = 0
            int r2 = r2 + 1
            r8 = 124(0x7c, float:1.74E-43)
            r1[r8] = r4
            goto L_0x045b
        L_0x0502:
            r15 = 8
            r6 = 23
            r1[r6] = r4
            int r6 = r14.intValue()
            int r13 = java.lang.Math.max(r13, r6)
            r6 = 24
            r1[r6] = r4
            r6 = 8
            goto L_0x00a8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.list.template.TemplateStickyHelper.onBeforeScroll(int, int):void");
    }

    public List<Integer> getStickyPositions() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.stickyPositions != null) {
            $jacocoInit[138] = true;
        } else {
            $jacocoInit[139] = true;
            this.stickyPositions = new ArrayList();
            $jacocoInit[140] = true;
        }
        List<Integer> list = this.stickyPositions;
        $jacocoInit[141] = true;
        return list;
    }

    public List<String> getStickyTypes() {
        boolean[] $jacocoInit = $jacocoInit();
        List<String> list = this.mStickyTypes;
        $jacocoInit[142] = true;
        return list;
    }
}
