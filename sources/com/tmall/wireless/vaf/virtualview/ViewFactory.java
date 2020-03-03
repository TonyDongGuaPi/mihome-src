package com.tmall.wireless.vaf.virtualview;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import com.libra.Utils;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.Helper.VVFeatureConfig;
import com.tmall.wireless.vaf.virtualview.container.Container;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import com.tmall.wireless.vaf.virtualview.layout.FlexLayout;
import com.tmall.wireless.vaf.virtualview.layout.FrameLayout;
import com.tmall.wireless.vaf.virtualview.layout.GridLayout;
import com.tmall.wireless.vaf.virtualview.layout.RatioLayout;
import com.tmall.wireless.vaf.virtualview.layout.VH2Layout;
import com.tmall.wireless.vaf.virtualview.layout.VHLayout;
import com.tmall.wireless.vaf.virtualview.loader.BinaryLoader;
import com.tmall.wireless.vaf.virtualview.loader.CodeReader;
import com.tmall.wireless.vaf.virtualview.loader.ExprCodeLoader;
import com.tmall.wireless.vaf.virtualview.loader.UiCodeLoader;
import com.tmall.wireless.vaf.virtualview.view.VirtualContainer;
import com.tmall.wireless.vaf.virtualview.view.VirtualGraph;
import com.tmall.wireless.vaf.virtualview.view.VirtualTime;
import com.tmall.wireless.vaf.virtualview.view.grid.Grid;
import com.tmall.wireless.vaf.virtualview.view.image.NativeImage;
import com.tmall.wireless.vaf.virtualview.view.image.VirtualImage;
import com.tmall.wireless.vaf.virtualview.view.line.NativeLine;
import com.tmall.wireless.vaf.virtualview.view.line.VirtualLine;
import com.tmall.wireless.vaf.virtualview.view.nlayout.NFrameLayout;
import com.tmall.wireless.vaf.virtualview.view.nlayout.NGridLayout;
import com.tmall.wireless.vaf.virtualview.view.nlayout.NRatioLayout;
import com.tmall.wireless.vaf.virtualview.view.nlayout.NVH2Layout;
import com.tmall.wireless.vaf.virtualview.view.nlayout.NVHLayout;
import com.tmall.wireless.vaf.virtualview.view.page.Page;
import com.tmall.wireless.vaf.virtualview.view.progress.VirtualProgress;
import com.tmall.wireless.vaf.virtualview.view.scroller.Scroller;
import com.tmall.wireless.vaf.virtualview.view.slider.Slider;
import com.tmall.wireless.vaf.virtualview.view.slider.SliderCompact;
import com.tmall.wireless.vaf.virtualview.view.text.NativeText;
import com.tmall.wireless.vaf.virtualview.view.text.VirtualText;
import com.tmall.wireless.vaf.virtualview.view.vh.VH;
import java.util.Stack;

public class ViewFactory {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9374a = "ViewFac_TMTEST";
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static UiCodeLoader e = new UiCodeLoader();
    private static ExprCodeLoader f = new ExprCodeLoader();
    private static BinaryLoader g = new BinaryLoader();
    private Stack<ViewBase> h = new Stack<>();
    private SparseArray<ViewBase.IBuilder> i = new SparseArray<>();
    private VafContext j;

    static {
        g.a(e);
        g.a(f);
    }

    public ViewFactory() {
        this.i.put(1, new FrameLayout.Builder());
        this.i.put(4, new GridLayout.Builder());
        this.i.put(2, new VHLayout.Builder());
        this.i.put(5, new FlexLayout.Builder());
        this.i.put(6, new RatioLayout.Builder());
        this.i.put(3, new VH2Layout.Builder());
        this.i.put(7, new NativeText.Builder());
        this.i.put(8, new VirtualText.Builder());
        this.i.put(9, new NativeImage.Builder());
        this.i.put(10, new VirtualImage.Builder());
        this.i.put(14, new VirtualLine.Builder());
        this.i.put(15, new Scroller.Builder());
        this.i.put(16, new Page.Builder());
        this.i.put(17, new Grid.Builder());
        this.i.put(13, new NativeLine.Builder());
        this.i.put(21, new VirtualGraph.Builder());
        this.i.put(18, new VH.Builder());
        this.i.put(20, new VirtualTime.Builder());
        if (VVFeatureConfig.a()) {
            this.i.put(19, new SliderCompact.Builder());
        } else {
            this.i.put(19, new Slider.Builder());
        }
        this.i.put(22, new VirtualProgress.Builder());
        this.i.put(23, new VirtualContainer.Builder());
        this.i.put(25, new NFrameLayout.Builder());
        this.i.put(26, new NGridLayout.Builder());
        this.i.put(27, new NRatioLayout.Builder());
        this.i.put(28, new NVH2Layout.Builder());
        this.i.put(29, new NVHLayout.Builder());
    }

    public void a() {
        this.j = null;
        this.h.clear();
        this.i.clear();
    }

    public void a(VafContext vafContext) {
        this.j = vafContext;
        g.a(vafContext);
    }

    public boolean a(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Utils.a(displayMetrics.density, displayMetrics.widthPixels);
        return true;
    }

    public int a(String str) {
        return g.a(str);
    }

    public int a(byte[] bArr) {
        return g.a(bArr);
    }

    public int a(byte[] bArr, boolean z) {
        return g.a(bArr, z);
    }

    public boolean a(int i2, ViewBase.IBuilder iBuilder) {
        if (iBuilder == null) {
            Log.e(f9374a, "register builder failed, builder is null");
        } else if (this.i.get(i2) == null) {
            this.i.put(i2, iBuilder);
            return true;
        } else {
            Log.e(f9374a, "register builder failed, already exist id:" + i2);
        }
        return false;
    }

    public boolean b(int i2, ViewBase.IBuilder iBuilder) {
        if (iBuilder != null) {
            this.i.put(i2, iBuilder);
            return true;
        }
        Log.e(f9374a, "register builder failed, builder is null");
        return false;
    }

    public IContainer b(String str) {
        ViewBase d2 = d(str);
        if (d2 != null) {
            Container container = new Container(this.j.m());
            container.setVirtualView(d2);
            container.attachViews();
            return container;
        }
        Log.e(f9374a, "new view failed type:" + str);
        return null;
    }

    public int c(String str) {
        CodeReader a2 = e.a(str);
        if (a2 != null) {
            return a2.a();
        }
        return 0;
    }

    public ViewBase d(String str) {
        return a(str, (SparseArray<ViewBase>) null);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.tmall.wireless.vaf.virtualview.core.ViewBase a(java.lang.String r10, android.util.SparseArray<com.tmall.wireless.vaf.virtualview.core.ViewBase> r11) {
        /*
            r9 = this;
            com.tmall.wireless.vaf.virtualview.loader.BinaryLoader r0 = g
            r1 = 0
            if (r0 == 0) goto L_0x0196
            com.tmall.wireless.vaf.virtualview.loader.UiCodeLoader r0 = e
            com.tmall.wireless.vaf.virtualview.loader.CodeReader r0 = r0.a(r10)
            if (r0 == 0) goto L_0x017f
            java.util.Stack<com.tmall.wireless.vaf.virtualview.core.ViewBase> r10 = r9.h
            r10.clear()
            byte r10 = r0.g()
            r2 = 0
            com.tmall.wireless.vaf.virtualview.core.ViewCache r3 = new com.tmall.wireless.vaf.virtualview.core.ViewCache
            r3.<init>()
            r2 = r1
            r4 = 0
        L_0x001e:
            r5 = 2
            r6 = 1
            switch(r10) {
                case 0: goto L_0x0065;
                case 1: goto L_0x003c;
                default: goto L_0x0023;
            }
        L_0x0023:
            java.lang.String r4 = "ViewFac_TMTEST"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "invalidate tag type:"
            r7.append(r8)
            r7.append(r10)
            java.lang.String r10 = r7.toString()
            android.util.Log.e(r4, r10)
        L_0x0039:
            r4 = 2
            goto L_0x0161
        L_0x003c:
            java.util.Stack<com.tmall.wireless.vaf.virtualview.core.ViewBase> r10 = r9.h
            int r10 = r10.size()
            if (r10 <= 0) goto L_0x0062
            java.util.Stack<com.tmall.wireless.vaf.virtualview.core.ViewBase> r10 = r9.h
            java.lang.Object r10 = r10.pop()
            com.tmall.wireless.vaf.virtualview.core.ViewBase r10 = (com.tmall.wireless.vaf.virtualview.core.ViewBase) r10
            boolean r7 = r10 instanceof com.tmall.wireless.vaf.virtualview.core.Layout
            if (r7 == 0) goto L_0x0057
            r5 = r10
            com.tmall.wireless.vaf.virtualview.core.Layout r5 = (com.tmall.wireless.vaf.virtualview.core.Layout) r5
            r5.a((com.tmall.wireless.vaf.virtualview.core.ViewBase) r2)
            goto L_0x005f
        L_0x0057:
            java.lang.String r2 = "ViewFac_TMTEST"
            java.lang.String r4 = "com can not contain subcomponent"
            android.util.Log.e(r2, r4)
            r4 = 2
        L_0x005f:
            r2 = r10
            goto L_0x0161
        L_0x0062:
            r4 = 1
            goto L_0x0161
        L_0x0065:
            short r10 = r0.h()
            com.tmall.wireless.vaf.framework.VafContext r7 = r9.j
            com.tmall.wireless.vaf.virtualview.core.ViewBase r7 = r9.a(r7, r10, r3)
            if (r7 == 0) goto L_0x0149
            if (r2 == 0) goto L_0x0080
            r10 = r2
            com.tmall.wireless.vaf.virtualview.core.Layout r10 = (com.tmall.wireless.vaf.virtualview.core.Layout) r10
            com.tmall.wireless.vaf.virtualview.core.Layout$Params r10 = r10.a()
            java.util.Stack<com.tmall.wireless.vaf.virtualview.core.ViewBase> r5 = r9.h
            r5.push(r2)
            goto L_0x0085
        L_0x0080:
            com.tmall.wireless.vaf.virtualview.core.Layout$Params r10 = new com.tmall.wireless.vaf.virtualview.core.Layout$Params
            r10.<init>()
        L_0x0085:
            r7.a((com.tmall.wireless.vaf.virtualview.core.Layout.Params) r10)
            byte r10 = r0.g()
        L_0x008c:
            if (r10 <= 0) goto L_0x009d
            int r2 = r0.i()
            int r5 = r0.i()
            r7.g(r2, r5)
            int r10 = r10 + -1
            byte r10 = (byte) r10
            goto L_0x008c
        L_0x009d:
            byte r10 = r0.g()
        L_0x00a1:
            if (r10 <= 0) goto L_0x00b2
            int r2 = r0.i()
            int r5 = r0.i()
            r7.f(r2, r5)
            int r10 = r10 + -1
            byte r10 = (byte) r10
            goto L_0x00a1
        L_0x00b2:
            byte r10 = r0.g()
        L_0x00b6:
            if (r10 <= 0) goto L_0x00cb
            int r2 = r0.i()
            int r5 = r0.i()
            float r5 = java.lang.Float.intBitsToFloat(r5)
            r7.d((int) r2, (float) r5)
            int r10 = r10 + -1
            byte r10 = (byte) r10
            goto L_0x00b6
        L_0x00cb:
            byte r10 = r0.g()
        L_0x00cf:
            if (r10 <= 0) goto L_0x00e4
            int r2 = r0.i()
            int r5 = r0.i()
            float r5 = java.lang.Float.intBitsToFloat(r5)
            r7.c((int) r2, (float) r5)
            int r10 = r10 + -1
            byte r10 = (byte) r10
            goto L_0x00cf
        L_0x00e4:
            byte r10 = r0.g()
        L_0x00e8:
            if (r10 <= 0) goto L_0x00f9
            int r2 = r0.i()
            int r5 = r0.i()
            r7.e(r2, r5)
            int r10 = r10 + -1
            byte r10 = (byte) r10
            goto L_0x00e8
        L_0x00f9:
            byte r10 = r0.g()
        L_0x00fd:
            if (r10 <= 0) goto L_0x0114
            int r2 = r0.i()
            int r5 = r0.i()
            com.tmall.wireless.vaf.virtualview.loader.ExprCodeLoader r8 = f
            com.libra.expr.common.ExprCode r5 = r8.a(r5)
            r7.a((int) r2, (com.libra.expr.common.ExprCode) r5)
            int r10 = r10 + -1
            byte r10 = (byte) r10
            goto L_0x00fd
        L_0x0114:
            byte r10 = r0.g()
        L_0x0118:
            if (r10 <= 0) goto L_0x012d
            byte r2 = r0.g()
            int r5 = r0.i()
            int r8 = r0.i()
            r7.b(r2, r5, r8)
            int r10 = r10 + -1
            byte r10 = (byte) r10
            goto L_0x0118
        L_0x012d:
            int r10 = r7.v()
            if (r10 <= 0) goto L_0x0138
            if (r11 == 0) goto L_0x0138
            r11.put(r10, r7)
        L_0x0138:
            java.util.List r10 = r3.b(r7)
            if (r10 == 0) goto L_0x0144
            boolean r10 = r10.isEmpty()
            if (r10 == 0) goto L_0x0147
        L_0x0144:
            r7.f()
        L_0x0147:
            r2 = r7
            goto L_0x0161
        L_0x0149:
            java.lang.String r4 = "ViewFac_TMTEST"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "can not find view id:"
            r7.append(r8)
            r7.append(r10)
            java.lang.String r10 = r7.toString()
            android.util.Log.e(r4, r10)
            goto L_0x0039
        L_0x0161:
            if (r4 == 0) goto L_0x0179
            if (r6 != r4) goto L_0x019d
            java.lang.String r10 = "ALIVV"
            int r10 = r10.length()
            int r10 = r10 + 4
            r0.c(r10)
            short r10 = r0.h()
            r2.h(r10)
            r1 = r2
            goto L_0x019d
        L_0x0179:
            byte r10 = r0.g()
            goto L_0x001e
        L_0x017f:
            java.lang.String r11 = "ViewFac_TMTEST"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "can not find component type:"
            r0.append(r2)
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            android.util.Log.e(r11, r10)
            goto L_0x019d
        L_0x0196:
            java.lang.String r10 = "ViewFac_TMTEST"
            java.lang.String r11 = "loader is null"
            android.util.Log.e(r10, r11)
        L_0x019d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tmall.wireless.vaf.virtualview.ViewFactory.a(java.lang.String, android.util.SparseArray):com.tmall.wireless.vaf.virtualview.core.ViewBase");
    }

    private ViewBase a(VafContext vafContext, int i2, ViewCache viewCache) {
        ViewBase.IBuilder iBuilder = this.i.get(i2);
        if (iBuilder != null) {
            return iBuilder.a(vafContext, viewCache);
        }
        return null;
    }
}
