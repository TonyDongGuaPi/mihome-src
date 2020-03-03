package com.xiaomi.smarthome.scenenew.actiivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity;
import com.xiaomi.smarthome.scenenew.manager.PluginRecommendSceneManager;
import java.util.List;

public class TestPluginRecomendSceneActivity extends BaseActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_test_recommend_scene);
        final List<Device> g = SmartHomeDeviceManager.a().g("lumi.lock.mcn01");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter<ActionViewHolder>() {
            public long getItemId(int i) {
                return (long) i;
            }

            @NonNull
            /* renamed from: a */
            public ActionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new ActionViewHolder(LayoutInflater.from(TestPluginRecomendSceneActivity.this).inflate(R.layout.item_plugin_rec_action, (ViewGroup) null));
            }

            /* renamed from: a */
            public void onBindViewHolder(@NonNull ActionViewHolder actionViewHolder, int i) {
                if (!TextUtils.isEmpty(((Device) g.get(i)).name)) {
                    actionViewHolder.f21921a.setText(((Device) g.get(i)).name);
                } else {
                    actionViewHolder.f21921a.setText("");
                }
                actionViewHolder.itemView.setOnClickListener(new View.OnClickListener(g, i) {
                    private final /* synthetic */ List f$1;
                    private final /* synthetic */ int f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void onClick(View view) {
                        TestPluginRecomendSceneActivity.AnonymousClass1.this.a(this.f$1, this.f$2, view);
                    }
                });
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void a(final List list, final int i, View view) {
                PluginRecommendSceneManager.a().b(((Device) list.get(i)).did, -1, new AsyncCallback<PluginRecommendSceneInfo, Error>() {
                    public void onFailure(Error error) {
                    }

                    /* renamed from: a */
                    public void onSuccess(PluginRecommendSceneInfo pluginRecommendSceneInfo) {
                        TestPluginRecomendSceneActivity.this.mHandler.post(
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000f: INVOKE  
                              (wrap: android.os.Handler : 0x0004: IGET  (r0v2 android.os.Handler) = 
                              (wrap: com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity : 0x0002: IGET  (r0v1 com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity) = 
                              (wrap: com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity$1 : 0x0000: IGET  (r0v0 com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity$1) = 
                              (r4v0 'this' com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity$1$1 A[THIS])
                             com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity.1.1.c com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity$1)
                             com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity.1.b com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity)
                             com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity.mHandler android.os.Handler)
                              (wrap: com.xiaomi.smarthome.scenenew.actiivity.-$$Lambda$TestPluginRecomendSceneActivity$1$1$rQqEuz1ZvSL-tiN8rt94fdTkJPs : 0x000c: CONSTRUCTOR  (r3v0 com.xiaomi.smarthome.scenenew.actiivity.-$$Lambda$TestPluginRecomendSceneActivity$1$1$rQqEuz1ZvSL-tiN8rt94fdTkJPs) = 
                              (r4v0 'this' com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity$1$1 A[THIS])
                              (r5v0 'pluginRecommendSceneInfo' com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo)
                              (wrap: java.util.List : 0x0006: IGET  (r1v0 java.util.List) = 
                              (r4v0 'this' com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity$1$1 A[THIS])
                             com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity.1.1.a java.util.List)
                              (wrap: int : 0x0008: IGET  (r2v0 int) = 
                              (r4v0 'this' com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity$1$1 A[THIS])
                             com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity.1.1.b int)
                             call: com.xiaomi.smarthome.scenenew.actiivity.-$$Lambda$TestPluginRecomendSceneActivity$1$1$rQqEuz1ZvSL-tiN8rt94fdTkJPs.<init>(com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity$1$1, com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo, java.util.List, int):void type: CONSTRUCTOR)
                             android.os.Handler.post(java.lang.Runnable):boolean type: VIRTUAL in method: com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity.1.1.a(com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo):void, dex: classes9.dex
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
                            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                            	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                            	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                            	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                            	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000c: CONSTRUCTOR  (r3v0 com.xiaomi.smarthome.scenenew.actiivity.-$$Lambda$TestPluginRecomendSceneActivity$1$1$rQqEuz1ZvSL-tiN8rt94fdTkJPs) = 
                              (r4v0 'this' com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity$1$1 A[THIS])
                              (r5v0 'pluginRecommendSceneInfo' com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo)
                              (wrap: java.util.List : 0x0006: IGET  (r1v0 java.util.List) = 
                              (r4v0 'this' com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity$1$1 A[THIS])
                             com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity.1.1.a java.util.List)
                              (wrap: int : 0x0008: IGET  (r2v0 int) = 
                              (r4v0 'this' com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity$1$1 A[THIS])
                             com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity.1.1.b int)
                             call: com.xiaomi.smarthome.scenenew.actiivity.-$$Lambda$TestPluginRecomendSceneActivity$1$1$rQqEuz1ZvSL-tiN8rt94fdTkJPs.<init>(com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity$1$1, com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo, java.util.List, int):void type: CONSTRUCTOR in method: com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity.1.1.a(com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo):void, dex: classes9.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                            	... 93 more
                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.xiaomi.smarthome.scenenew.actiivity.-$$Lambda$TestPluginRecomendSceneActivity$1$1$rQqEuz1ZvSL-tiN8rt94fdTkJPs, state: NOT_LOADED
                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                            	... 99 more
                            */
                        /*
                            this = this;
                            com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity$1 r0 = com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity.AnonymousClass1.this
                            com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity r0 = com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity.this
                            android.os.Handler r0 = r0.mHandler
                            java.util.List r1 = r3
                            int r2 = r4
                            com.xiaomi.smarthome.scenenew.actiivity.-$$Lambda$TestPluginRecomendSceneActivity$1$1$rQqEuz1ZvSL-tiN8rt94fdTkJPs r3 = new com.xiaomi.smarthome.scenenew.actiivity.-$$Lambda$TestPluginRecomendSceneActivity$1$1$rQqEuz1ZvSL-tiN8rt94fdTkJPs
                            r3.<init>(r4, r5, r1, r2)
                            r0.post(r3)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scenenew.actiivity.TestPluginRecomendSceneActivity.AnonymousClass1.AnonymousClass1.onSuccess(com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo):void");
                    }

                    /* access modifiers changed from: private */
                    public /* synthetic */ void a(PluginRecommendSceneInfo pluginRecommendSceneInfo, List list, int i) {
                        if (pluginRecommendSceneInfo != null && pluginRecommendSceneInfo.mSceneItems != null && pluginRecommendSceneInfo.mSceneItems.size() > 0) {
                            Intent intent = new Intent(TestPluginRecomendSceneActivity.this, PluginRecommendSceneActivity.class);
                            intent.putExtra("did", ((Device) list.get(i)).did);
                            intent.putExtra("sr_id", new Integer(pluginRecommendSceneInfo.mSceneItems.get(0).sr_id));
                            TestPluginRecomendSceneActivity.this.startActivity(intent);
                        }
                    }
                });
            }

            public int getItemCount() {
                if (g == null) {
                    return 0;
                }
                return g.size();
            }
        });
    }

    public static class ActionViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f21921a;

        public ActionViewHolder(@NonNull View view) {
            super(view);
            this.f21921a = (TextView) view.findViewById(R.id.device_name);
        }
    }
}
