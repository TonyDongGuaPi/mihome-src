package com.xiaomi.smarthome.newui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.homeroom.DeviceListAssemble;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.multi_item.IAdapter;
import com.xiaomi.smarthome.newui.DropMenuAdapter;
import com.xiaomi.smarthome.newui.widget.topnavi.PageBean;
import com.xiaomi.smarthome.newui.widget.topnavi.widgets.CardConstraintLayout;
import com.xiaomi.smarthome.newui.widget.topnavi.widgets.RoomDropMenu;
import com.xiaomi.smarthome.stat.STAT;
import java.util.List;

public class DropMenuAdapter extends IAdapter {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20253a = "action_room_selected";
    public static final String b = "action_menu_closed";
    public static final String c = "extra_room_selected_id";
    /* access modifiers changed from: private */
    public final String d;
    /* access modifiers changed from: private */
    public boolean e = false;
    /* access modifiers changed from: private */
    public RoomDropMenu f;
    private SimpleViewHolder g;
    /* access modifiers changed from: private */
    public Context h;

    public int a() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public void a(Rect rect, View view, int i, RecyclerView recyclerView, RecyclerView.State state) {
    }

    DropMenuAdapter(Context context, String str) {
        this.h = context;
        this.d = str;
    }

    public void b() {
        if (this.g != null) {
            this.g.a();
        }
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (this.g != null) {
            this.g.e();
        }
        SimpleViewHolder simpleViewHolder = new SimpleViewHolder(LayoutInflater.from(this.h).inflate(R.layout.main_drop_menu_layout, viewGroup, false));
        this.g = simpleViewHolder;
        return simpleViewHolder;
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((SimpleViewHolder) viewHolder).a();
    }

    public int getItemCount() {
        return i() ? 1 : 0;
    }

    private boolean i() {
        return SHApplication.getStateNotifier().a() == 4;
    }

    public void c() {
        this.e = true;
    }

    public void f() {
        this.e = false;
    }

    public boolean g() {
        if (this.g == null) {
            return false;
        }
        return this.g.b();
    }

    public void h() {
        if (this.f != null) {
            this.f = null;
        }
    }

    private class SimpleViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final CardConstraintLayout b;
        /* access modifiers changed from: private */
        public View c;
        private TextView d;
        /* access modifiers changed from: private */
        public boolean e = false;
        private long f = -1;
        private BroadcastReceiver g;

        public SimpleViewHolder(View view) {
            super(view);
            this.b = (CardConstraintLayout) view.findViewById(R.id.card_layout);
            if (DarkModeCompat.a(view.getContext())) {
                this.b.updateBgColor(-16777216);
            }
            this.c = view.findViewById(R.id.arrow_down_img);
            this.d = (TextView) view.findViewById(R.id.room_name);
            this.b.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    DropMenuAdapter.SimpleViewHolder.this.a(view);
                }
            });
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(View view) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.f > 300) {
                this.f = currentTimeMillis;
                if (this.e) {
                    this.e = false;
                    c();
                    LocalBroadcastManager.getInstance(DropMenuAdapter.this.h).sendBroadcast(new Intent(DropMenuAdapter.f20253a));
                    return;
                }
                this.e = true;
                c();
                DropMenuStateHelper a2 = DropMenuStateHelper.a();
                PageBean b2 = a2.b();
                List<PageBean> d2 = a2.d();
                List<PageBean.Classify> e2 = a2.e();
                if (b2 != null && !d2.isEmpty() && !DropMenuAdapter.this.e) {
                    View decorView = ((Activity) DropMenuAdapter.this.h).getWindow().getDecorView();
                    String b3 = DropMenuAdapter.this.d;
                    StringBuilder sb = new StringBuilder();
                    sb.append("RoomDropMenu.show: mSelectPage: ");
                    sb.append(b2 != null ? b2.e : "null");
                    Log.d(b3, sb.toString());
                    RoomDropMenu unused = DropMenuAdapter.this.f = RoomDropMenu.a(DropMenuAdapter.this.h, (ViewGroup) decorView, this.b, e2, b2);
                    d();
                    STAT.d.ag();
                    this.b.updateReactRadius(true, true, false, false);
                }
            }
        }

        /* access modifiers changed from: private */
        public void c() {
            if (this.e) {
                this.c.setContentDescription(DropMenuAdapter.this.h.getString(R.string.navi_open_accessibility_desc));
            } else {
                this.c.setContentDescription(DropMenuAdapter.this.h.getString(R.string.navi_close_accessibility_desc));
            }
        }

        private void d() {
            IntentFilter intentFilter = new IntentFilter(DropMenuAdapter.b);
            this.g = new BroadcastReceiver() {
                /* access modifiers changed from: private */
                public /* synthetic */ void a() {
                    SimpleViewHolder.this.b.updateReactRadius(true, true, true, true);
                }

                public void onReceive(Context context, Intent intent) {
                    SimpleViewHolder.this.c.postDelayed(
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000d: INVOKE  
                          (wrap: android.view.View : 0x0002: INVOKE  (r3v2 android.view.View) = 
                          (wrap: com.xiaomi.smarthome.newui.DropMenuAdapter$SimpleViewHolder : 0x0000: IGET  (r3v1 com.xiaomi.smarthome.newui.DropMenuAdapter$SimpleViewHolder) = 
                          (r2v0 'this' com.xiaomi.smarthome.newui.DropMenuAdapter$SimpleViewHolder$1 A[THIS])
                         com.xiaomi.smarthome.newui.DropMenuAdapter.SimpleViewHolder.1.a com.xiaomi.smarthome.newui.DropMenuAdapter$SimpleViewHolder)
                         com.xiaomi.smarthome.newui.DropMenuAdapter.SimpleViewHolder.b(com.xiaomi.smarthome.newui.DropMenuAdapter$SimpleViewHolder):android.view.View type: STATIC)
                          (wrap: com.xiaomi.smarthome.newui.-$$Lambda$DropMenuAdapter$SimpleViewHolder$1$jktryfeJkH5SVodRd4k1lUmTQ5g : 0x0008: CONSTRUCTOR  (r4v1 com.xiaomi.smarthome.newui.-$$Lambda$DropMenuAdapter$SimpleViewHolder$1$jktryfeJkH5SVodRd4k1lUmTQ5g) = 
                          (r2v0 'this' com.xiaomi.smarthome.newui.DropMenuAdapter$SimpleViewHolder$1 A[THIS])
                         call: com.xiaomi.smarthome.newui.-$$Lambda$DropMenuAdapter$SimpleViewHolder$1$jktryfeJkH5SVodRd4k1lUmTQ5g.<init>(com.xiaomi.smarthome.newui.DropMenuAdapter$SimpleViewHolder$1):void type: CONSTRUCTOR)
                          (250 long)
                         android.view.View.postDelayed(java.lang.Runnable, long):boolean type: VIRTUAL in method: com.xiaomi.smarthome.newui.DropMenuAdapter.SimpleViewHolder.1.onReceive(android.content.Context, android.content.Intent):void, dex: classes9.dex
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
                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0008: CONSTRUCTOR  (r4v1 com.xiaomi.smarthome.newui.-$$Lambda$DropMenuAdapter$SimpleViewHolder$1$jktryfeJkH5SVodRd4k1lUmTQ5g) = 
                          (r2v0 'this' com.xiaomi.smarthome.newui.DropMenuAdapter$SimpleViewHolder$1 A[THIS])
                         call: com.xiaomi.smarthome.newui.-$$Lambda$DropMenuAdapter$SimpleViewHolder$1$jktryfeJkH5SVodRd4k1lUmTQ5g.<init>(com.xiaomi.smarthome.newui.DropMenuAdapter$SimpleViewHolder$1):void type: CONSTRUCTOR in method: com.xiaomi.smarthome.newui.DropMenuAdapter.SimpleViewHolder.1.onReceive(android.content.Context, android.content.Intent):void, dex: classes9.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                        	... 74 more
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.xiaomi.smarthome.newui.-$$Lambda$DropMenuAdapter$SimpleViewHolder$1$jktryfeJkH5SVodRd4k1lUmTQ5g, state: NOT_LOADED
                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                        	... 80 more
                        */
                    /*
                        this = this;
                        com.xiaomi.smarthome.newui.DropMenuAdapter$SimpleViewHolder r3 = com.xiaomi.smarthome.newui.DropMenuAdapter.SimpleViewHolder.this
                        android.view.View r3 = r3.c
                        com.xiaomi.smarthome.newui.-$$Lambda$DropMenuAdapter$SimpleViewHolder$1$jktryfeJkH5SVodRd4k1lUmTQ5g r4 = new com.xiaomi.smarthome.newui.-$$Lambda$DropMenuAdapter$SimpleViewHolder$1$jktryfeJkH5SVodRd4k1lUmTQ5g
                        r4.<init>(r2)
                        r0 = 250(0xfa, double:1.235E-321)
                        r3.postDelayed(r4, r0)
                        com.xiaomi.smarthome.newui.DropMenuAdapter$SimpleViewHolder r3 = com.xiaomi.smarthome.newui.DropMenuAdapter.SimpleViewHolder.this
                        r4 = 0
                        boolean unused = r3.e = r4
                        com.xiaomi.smarthome.newui.DropMenuAdapter$SimpleViewHolder r3 = com.xiaomi.smarthome.newui.DropMenuAdapter.SimpleViewHolder.this
                        r3.c()
                        com.xiaomi.smarthome.newui.DropMenuAdapter$SimpleViewHolder r3 = com.xiaomi.smarthome.newui.DropMenuAdapter.SimpleViewHolder.this
                        com.xiaomi.smarthome.newui.DropMenuAdapter r3 = com.xiaomi.smarthome.newui.DropMenuAdapter.this
                        android.content.Context r3 = r3.h
                        android.support.v4.content.LocalBroadcastManager r3 = android.support.v4.content.LocalBroadcastManager.getInstance(r3)
                        r3.unregisterReceiver(r2)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.DropMenuAdapter.SimpleViewHolder.AnonymousClass1.onReceive(android.content.Context, android.content.Intent):void");
                }
            };
            LocalBroadcastManager.getInstance(DropMenuAdapter.this.h).registerReceiver(this.g, intentFilter);
        }

        /* access modifiers changed from: private */
        public void e() {
            LocalBroadcastManager.getInstance(DropMenuAdapter.this.h).unregisterReceiver(this.g);
        }

        public void a() {
            PageBean b2 = DropMenuStateHelper.a().b();
            String b3 = DropMenuAdapter.this.d;
            StringBuilder sb = new StringBuilder();
            sb.append("update: ");
            sb.append(b2 != null ? b2.e : "null");
            Log.d(b3, sb.toString());
            if (!DropMenuAdapter.this.e) {
                int size = DeviceListAssemble.f17943a.a(b2).size();
                String b4 = DropMenuAdapter.this.d;
                LogUtilGrey.a(b4, "drop menu show size: " + size);
                this.d.setText(String.format("%s (%s)", new Object[]{b2.e, Integer.valueOf(size)}));
            }
        }

        public boolean b() {
            if (DropMenuAdapter.this.f == null) {
                return false;
            }
            RoomDropMenu d2 = DropMenuAdapter.this.f;
            RoomDropMenu unused = DropMenuAdapter.this.f = null;
            return d2.c();
        }
    }
}
