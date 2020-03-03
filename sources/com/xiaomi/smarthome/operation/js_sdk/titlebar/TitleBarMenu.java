package com.xiaomi.smarthome.operation.js_sdk.titlebar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.drew.lang.annotations.NotNull;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.shop.utils.DisplayUtils;
import com.xiaomi.smarthome.shop.utils.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TitleBarMenu extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21109a = "TitleBarMenu";
    private static final String b = "inner:more_menu_id";
    public final Menu MORE_MENU;
    private final List<Menu> c;
    /* access modifiers changed from: private */
    public final List<Menu> d;

    public TitleBarMenu(Context context) {
        this(context, (AttributeSet) null);
    }

    public TitleBarMenu(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TitleBarMenu(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.MORE_MENU = new Menu(b, "") {
            /* access modifiers changed from: package-private */
            public void onClick(View view, Menu menu) {
                View inflate = LayoutInflater.from(TitleBarMenu.this.getContext()).inflate(R.layout.title_drop_down_menu_layout, (ViewGroup) null);
                RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler);
                final PopupWindow popupWindow = new PopupWindow(inflate, -2, -2);
                popupWindow.setClippingEnabled(false);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0));
                popupWindow.setFocusable(true);
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                final ArrayList arrayList = new ArrayList(TitleBarMenu.this.d);
                recyclerView.setAdapter(new RecyclerView.Adapter<AnonymousClass1SimpleMenuAdapter.VH>() {
                    @NonNull
                    /* renamed from: a */
                    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        return new VH((ViewGroup) LayoutInflater.from(TitleBarMenu.this.getContext()).inflate(R.layout.title_icon_text_menu_layout, viewGroup, false));
                    }

                    /* renamed from: a */
                    public void onBindViewHolder(@NonNull VH vh, int i) {
                        vh.a((Menu) arrayList.get(i), i);
                    }

                    public int getItemCount() {
                        return arrayList.size();
                    }

                    /* renamed from: com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$1$1SimpleMenuAdapter$VH */
                    class VH extends RecyclerView.ViewHolder {
                        VH(View view) {
                            super(view);
                        }

                        private void a(int i, int i2) {
                            ViewGroup.LayoutParams layoutParams = TitleBarMenu.this.getLayoutParams();
                            if (i == 0 && i2 == 1) {
                                this.itemView.setBackgroundResource(R.drawable.item_ripple_10_radius);
                                if (layoutParams != null) {
                                    layoutParams.height = DisplayUtils.b(TitleBarMenu.this.getContext(), 62.0f);
                                    int b = DisplayUtils.b(TitleBarMenu.this.getContext(), 6.0f);
                                    this.itemView.setPadding(0, b, 0, b);
                                }
                            } else if (i == 0) {
                                this.itemView.setBackgroundResource(R.drawable.item_ripple_10_radius_lt_rt);
                                if (layoutParams != null) {
                                    int b2 = DisplayUtils.b(TitleBarMenu.this.getContext(), 12.0f);
                                    layoutParams.height = DisplayUtils.b(TitleBarMenu.this.getContext(), 62.0f);
                                    this.itemView.setPadding(0, b2, 0, 0);
                                }
                            } else if (i == i2 - 1) {
                                this.itemView.setBackgroundResource(R.drawable.item_ripple_10_radius_lb_rb);
                                if (layoutParams != null) {
                                    layoutParams.height = DisplayUtils.b(TitleBarMenu.this.getContext(), 62.0f);
                                    this.itemView.setPadding(0, 0, 0, DisplayUtils.b(TitleBarMenu.this.getContext(), 12.0f));
                                }
                            } else {
                                this.itemView.setBackgroundResource(R.drawable.item_ripple_no_radius);
                                if (layoutParams != null) {
                                    layoutParams.height = DisplayUtils.b(TitleBarMenu.this.getContext(), 50.0f);
                                    this.itemView.setPadding(0, 0, 0, 0);
                                }
                            }
                        }

                        public void a(Menu menu, int i) {
                            this.itemView.setOnClickListener(
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000b: INVOKE  
                                  (wrap: android.view.View : 0x0000: IGET  (r0v0 android.view.View) = 
                                  (r9v0 'this' com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$1$1SimpleMenuAdapter$VH A[THIS])
                                 com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu.1.1SimpleMenuAdapter.VH.itemView android.view.View)
                                  (wrap: com.xiaomi.smarthome.operation.js_sdk.titlebar.-$$Lambda$TitleBarMenu$1$1SimpleMenuAdapter$VH$eflYM3382bcLVss1_a3V6Ru_A-g : 0x0008: CONSTRUCTOR  (r2v0 com.xiaomi.smarthome.operation.js_sdk.titlebar.-$$Lambda$TitleBarMenu$1$1SimpleMenuAdapter$VH$eflYM3382bcLVss1_a3V6Ru_A-g) = 
                                  (r9v0 'this' com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$1$1SimpleMenuAdapter$VH A[THIS])
                                  (r10v0 'menu' com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$Menu)
                                  (wrap: android.widget.PopupWindow : 0x0004: IGET  (r1v1 android.widget.PopupWindow) = 
                                  (wrap: com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$1$1SimpleMenuAdapter : 0x0002: IGET  (r1v0 com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$1$1SimpleMenuAdapter) = 
                                  (r9v0 'this' com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$1$1SimpleMenuAdapter$VH A[THIS])
                                 com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu.1.1SimpleMenuAdapter.VH.a com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$1$1SimpleMenuAdapter)
                                 com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu.1.1SimpleMenuAdapter.b android.widget.PopupWindow)
                                 call: com.xiaomi.smarthome.operation.js_sdk.titlebar.-$$Lambda$TitleBarMenu$1$1SimpleMenuAdapter$VH$eflYM3382bcLVss1_a3V6Ru_A-g.<init>(com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$1$1SimpleMenuAdapter$VH, com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$Menu, android.widget.PopupWindow):void type: CONSTRUCTOR)
                                 android.view.View.setOnClickListener(android.view.View$OnClickListener):void type: VIRTUAL in method: com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu.1.1SimpleMenuAdapter.VH.a(com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$Menu, int):void, dex: classes9.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                                	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:429)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0008: CONSTRUCTOR  (r2v0 com.xiaomi.smarthome.operation.js_sdk.titlebar.-$$Lambda$TitleBarMenu$1$1SimpleMenuAdapter$VH$eflYM3382bcLVss1_a3V6Ru_A-g) = 
                                  (r9v0 'this' com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$1$1SimpleMenuAdapter$VH A[THIS])
                                  (r10v0 'menu' com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$Menu)
                                  (wrap: android.widget.PopupWindow : 0x0004: IGET  (r1v1 android.widget.PopupWindow) = 
                                  (wrap: com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$1$1SimpleMenuAdapter : 0x0002: IGET  (r1v0 com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$1$1SimpleMenuAdapter) = 
                                  (r9v0 'this' com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$1$1SimpleMenuAdapter$VH A[THIS])
                                 com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu.1.1SimpleMenuAdapter.VH.a com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$1$1SimpleMenuAdapter)
                                 com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu.1.1SimpleMenuAdapter.b android.widget.PopupWindow)
                                 call: com.xiaomi.smarthome.operation.js_sdk.titlebar.-$$Lambda$TitleBarMenu$1$1SimpleMenuAdapter$VH$eflYM3382bcLVss1_a3V6Ru_A-g.<init>(com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$1$1SimpleMenuAdapter$VH, com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$Menu, android.widget.PopupWindow):void type: CONSTRUCTOR in method: com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu.1.1SimpleMenuAdapter.VH.a(com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$Menu, int):void, dex: classes9.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                	... 106 more
                                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.xiaomi.smarthome.operation.js_sdk.titlebar.-$$Lambda$TitleBarMenu$1$1SimpleMenuAdapter$VH$eflYM3382bcLVss1_a3V6Ru_A-g, state: NOT_LOADED
                                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                	... 112 more
                                */
                            /*
                                this = this;
                                android.view.View r0 = r9.itemView
                                com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$1$1SimpleMenuAdapter r1 = com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu.AnonymousClass1.AnonymousClass1SimpleMenuAdapter.this
                                android.widget.PopupWindow r1 = r1
                                com.xiaomi.smarthome.operation.js_sdk.titlebar.-$$Lambda$TitleBarMenu$1$1SimpleMenuAdapter$VH$eflYM3382bcLVss1_a3V6Ru_A-g r2 = new com.xiaomi.smarthome.operation.js_sdk.titlebar.-$$Lambda$TitleBarMenu$1$1SimpleMenuAdapter$VH$eflYM3382bcLVss1_a3V6Ru_A-g
                                r2.<init>(r9, r10, r1)
                                r0.setOnClickListener(r2)
                                android.view.View r0 = r9.itemView
                                r1 = 2131430833(0x7f0b0db1, float:1.8483378E38)
                                android.view.View r0 = r0.findViewById(r1)
                                android.widget.TextView r0 = (android.widget.TextView) r0
                                android.view.View r1 = r9.itemView
                                r2 = 2131430827(0x7f0b0dab, float:1.8483366E38)
                                android.view.View r1 = r1.findViewById(r2)
                                android.widget.ImageButton r1 = (android.widget.ImageButton) r1
                                android.view.View r2 = r9.itemView
                                r3 = 2131427781(0x7f0b01c5, float:1.8477188E38)
                                android.view.View r2 = r2.findViewById(r3)
                                android.widget.TextView r2 = (android.widget.TextView) r2
                                android.view.View r3 = r9.itemView
                                r4 = 2131427780(0x7f0b01c4, float:1.8477186E38)
                                android.view.View r3 = r3.findViewById(r4)
                                android.widget.TextView r3 = (android.widget.TextView) r3
                                android.graphics.Bitmap r4 = r10.c
                                r5 = 1
                                r6 = 8
                                r7 = 0
                                r8 = -1
                                if (r4 == 0) goto L_0x004d
                                android.graphics.Bitmap r4 = r10.c
                                r1.setImageBitmap(r4)
                                goto L_0x005f
                            L_0x004d:
                                int r4 = r10.d
                                if (r4 == r8) goto L_0x005b
                                int r4 = r10.d
                                r1.setImageResource(r4)
                                goto L_0x005f
                            L_0x005b:
                                r1.setVisibility(r6)
                                r5 = 0
                            L_0x005f:
                                if (r5 == 0) goto L_0x0069
                                int r1 = r10.e
                                if (r1 == r8) goto L_0x0069
                                r1 = 0
                                goto L_0x006b
                            L_0x0069:
                                r1 = 8
                            L_0x006b:
                                r2.setVisibility(r1)
                                if (r5 != 0) goto L_0x0077
                                int r1 = r10.e
                                if (r1 == r8) goto L_0x0077
                                r6 = 0
                            L_0x0077:
                                r3.setVisibility(r6)
                                java.lang.String r10 = r10.b
                                r0.setText(r10)
                                com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$1$1SimpleMenuAdapter r10 = com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu.AnonymousClass1.AnonymousClass1SimpleMenuAdapter.this
                                int r10 = r10.getItemCount()
                                r9.a((int) r11, (int) r10)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu.AnonymousClass1.AnonymousClass1SimpleMenuAdapter.VH.a(com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$Menu, int):void");
                        }

                        /* access modifiers changed from: private */
                        public /* synthetic */ void a(Menu menu, PopupWindow popupWindow, View view) {
                            menu.onClick(view, menu);
                            View view2 = this.itemView;
                            popupWindow.getClass();
                            view2.postDelayed(
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000f: INVOKE  
                                  (r3v1 'view2' android.view.View)
                                  (wrap: com.xiaomi.smarthome.operation.js_sdk.titlebar.-$$Lambda$K3zhJ9HhkSO4qTQ9p9JhLHWMaEQ : 0x000a: CONSTRUCTOR  (r5v1 com.xiaomi.smarthome.operation.js_sdk.titlebar.-$$Lambda$K3zhJ9HhkSO4qTQ9p9JhLHWMaEQ) = (r4v0 'popupWindow' android.widget.PopupWindow) call: com.xiaomi.smarthome.operation.js_sdk.titlebar.-$$Lambda$K3zhJ9HhkSO4qTQ9p9JhLHWMaEQ.<init>(android.widget.PopupWindow):void type: CONSTRUCTOR)
                                  (200 long)
                                 android.view.View.postDelayed(java.lang.Runnable, long):boolean type: VIRTUAL in method: com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu.1.1SimpleMenuAdapter.VH.a(com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$Menu, android.widget.PopupWindow, android.view.View):void, dex: classes9.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                                	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:429)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000a: CONSTRUCTOR  (r5v1 com.xiaomi.smarthome.operation.js_sdk.titlebar.-$$Lambda$K3zhJ9HhkSO4qTQ9p9JhLHWMaEQ) = (r4v0 'popupWindow' android.widget.PopupWindow) call: com.xiaomi.smarthome.operation.js_sdk.titlebar.-$$Lambda$K3zhJ9HhkSO4qTQ9p9JhLHWMaEQ.<init>(android.widget.PopupWindow):void type: CONSTRUCTOR in method: com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu.1.1SimpleMenuAdapter.VH.a(com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$Menu, android.widget.PopupWindow, android.view.View):void, dex: classes9.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                	... 106 more
                                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.xiaomi.smarthome.operation.js_sdk.titlebar.-$$Lambda$K3zhJ9HhkSO4qTQ9p9JhLHWMaEQ, state: NOT_LOADED
                                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                	... 112 more
                                */
                            /*
                                this = this;
                                r3.onClick(r5, r3)
                                android.view.View r3 = r2.itemView
                                r4.getClass()
                                com.xiaomi.smarthome.operation.js_sdk.titlebar.-$$Lambda$K3zhJ9HhkSO4qTQ9p9JhLHWMaEQ r5 = new com.xiaomi.smarthome.operation.js_sdk.titlebar.-$$Lambda$K3zhJ9HhkSO4qTQ9p9JhLHWMaEQ
                                r5.<init>(r4)
                                r0 = 200(0xc8, double:9.9E-322)
                                r3.postDelayed(r5, r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu.AnonymousClass1.AnonymousClass1SimpleMenuAdapter.VH.a(com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$Menu, android.widget.PopupWindow, android.view.View):void");
                        }
                    }
                });
                recyclerView.setLayoutManager(new LinearLayoutManager(TitleBarMenu.this.getContext()));
                popupWindow.showAsDropDown(view, -DisplayUtils.a(TitleBarMenu.this.getContext(), 120.0f), -DisplayUtils.a(TitleBarMenu.this.getContext(), 37.0f));
            }
        };
        this.c = new ArrayList();
        this.d = new ArrayList();
        setOrientation(0);
        this.MORE_MENU.a((int) R.drawable.std_tittlebar_main_device_more);
    }

    public static class Menu {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f21114a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public Bitmap c;
        /* access modifiers changed from: private */
        public int d = -1;
        /* access modifiers changed from: private */
        public int e = -1;

        /* access modifiers changed from: package-private */
        public void onClick(View view, Menu menu) {
        }

        public Menu(@NotNull String str, String str2) {
            this.f21114a = str;
            this.b = str2;
        }

        public void a(String str) {
            this.b = str;
        }

        public String a() {
            return this.f21114a;
        }

        public void a(Bitmap bitmap) {
            this.c = bitmap;
        }

        public void a(int i) {
            this.d = i;
        }

        public int b() {
            return this.d;
        }

        public void b(int i) {
            this.e = i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Menu menu = (Menu) obj;
            if (this.f21114a == null ? menu.f21114a != null : !this.f21114a.equals(menu.f21114a)) {
                return false;
            }
            if (this.b != null) {
                return this.b.equals(menu.b);
            }
            if (menu.b == null) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = (this.f21114a != null ? this.f21114a.hashCode() : 0) * 31;
            if (this.b != null) {
                i = this.b.hashCode();
            }
            return hashCode + i;
        }

        public String toString() {
            return "Menu{id='" + this.f21114a + Operators.SINGLE_QUOTE + ", title='" + this.b + Operators.SINGLE_QUOTE + ", iconResId=" + this.d + ", badge=" + this.e + Operators.BLOCK_END;
        }
    }

    public void setOptionsMenu(List<Menu> list) {
        ArrayList<Menu> arrayList = new ArrayList<>();
        for (Menu next : list) {
            if (next.c == null && next.d == -1) {
                LogUtil.a(f21109a, "setOptionsMenu: menu.iconBitmap is null. " + next.toString());
            } else {
                arrayList.add(next);
            }
        }
        this.c.clear();
        removeAllViews();
        for (Menu appendOptionMenu : arrayList) {
            appendOptionMenu(appendOptionMenu);
        }
        if (!this.d.isEmpty()) {
            appendOptionMenu(this.MORE_MENU);
        }
    }

    public void appendOptionMenu(Menu menu) {
        if (menu.c == null && menu.d == -1) {
            LogUtil.a(f21109a, "appendOptionMenu: menu.iconBitmap is null. " + menu.toString());
        } else if (!a(this.c, menu)) {
            this.c.add(menu);
            addView(a(menu));
        }
    }

    public void insertOptionMenu(Menu menu, int i) {
        if (menu.c == null && menu.d == -1) {
            Log.d(f21109a, "insertOptionMenu: menu.iconBitmap is null. " + menu.toString());
        } else if (!a(this.c, menu)) {
            if (i == -1) {
                this.c.add(menu);
            } else {
                this.c.add(i, menu);
            }
            addView(a(menu), i);
        }
    }

    private boolean a(List<Menu> list, Menu menu) {
        for (Menu e : list) {
            if (TextUtils.equals(e.f21114a, menu.f21114a)) {
                return true;
            }
        }
        return false;
    }

    public List<Menu> getOptionMenus() {
        return this.c;
    }

    public List<Menu> getDropDownMenus() {
        return this.d;
    }

    public void removeOptionMenu(Menu menu) {
        boolean z;
        Iterator<Menu> it = this.c.iterator();
        while (true) {
            if (it.hasNext()) {
                if (TextUtils.equals(menu.f21114a, it.next().f21114a)) {
                    it.remove();
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (z) {
            removeAllViews();
            setOptionsMenu(this.c);
        }
    }

    public void removeAllOptionMenus() {
        setOptionsMenu(Collections.emptyList());
    }

    public void setDropDownMenu(List<Menu> list) {
        ArrayList arrayList = new ArrayList();
        for (Menu next : list) {
            if (next.c == null && TextUtils.isEmpty(next.b) && next.d == -1) {
                Log.d(f21109a, "setDropDownMenu: both menu.iconBitmap && menu.title are null");
            } else {
                arrayList.add(next);
            }
        }
        this.d.clear();
        this.d.addAll(arrayList);
        if (!this.d.isEmpty()) {
            appendOptionMenu(this.MORE_MENU);
        } else {
            removeOptionMenu(this.MORE_MENU);
        }
    }

    public void removeDropDownMenu() {
        this.d.clear();
        removeOptionMenu(this.MORE_MENU);
    }

    private View a(final Menu menu) {
        int i = 0;
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.title_icon_menu_layout, this, false);
        viewGroup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                menu.onClick(view, menu);
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            viewGroup.setBackgroundResource(R.drawable.item_ripple_10_radius);
        }
        ImageButton imageButton = (ImageButton) viewGroup.findViewById(R.id.menu_icon);
        TextView textView = (TextView) viewGroup.findViewById(R.id.badge);
        imageButton.setBackground((Drawable) null);
        if (menu.c != null) {
            imageButton.setImageBitmap(menu.c);
        } else if (menu.d != -1) {
            imageButton.setImageResource(menu.d);
        }
        if (menu.e == -1) {
            i = 8;
        }
        textView.setVisibility(i);
        return viewGroup;
    }
}
