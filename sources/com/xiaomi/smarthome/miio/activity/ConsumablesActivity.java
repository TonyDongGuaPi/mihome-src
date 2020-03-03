package com.xiaomi.smarthome.miio.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.homedevicelist.SharedHomeDeviceManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.activity.ConsumablesActivity;
import com.xiaomi.smarthome.miio.consumables.Consumable;
import com.xiaomi.smarthome.miio.consumables.ConsumableDataManager;
import com.xiaomi.smarthome.miio.consumables.DeviceConsumble;
import com.xiaomi.smarthome.newui.HomeListDialogHelper;
import com.xiaomi.smarthome.shop.utils.LogUtil;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class ConsumablesActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private XQProgressDialog f11720a;
    /* access modifiers changed from: private */
    public String b;
    /* access modifiers changed from: private */
    public boolean c;
    /* access modifiers changed from: private */
    public ComsumableAdapter d;
    /* access modifiers changed from: private */
    public Dialog e;
    /* access modifiers changed from: private */
    public boolean f = false;
    /* access modifiers changed from: private */
    public boolean g = false;
    /* access modifiers changed from: private */
    public SmartHomeDeviceManager.IClientDeviceListener h = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void a(int i) {
            SmartHomeDeviceManager.a().c(ConsumablesActivity.this.h);
            if (ConsumablesActivity.this.mData != null && ConsumablesActivity.this.mData.size() > 0) {
                ConsumablesActivity.this.d();
            }
        }

        public void b(int i) {
            SmartHomeDeviceManager.a().c(ConsumablesActivity.this.h);
        }
    };
    @BindView(2131428536)
    RecyclerView list;
    List<DeviceConsumble> mData = new ArrayList();
    @BindView(2131428501)
    TextView mEmptyTV;
    @BindView(2131428503)
    View mEmptyView;
    @BindView(2131432956)
    View mGroupTitle;
    LinearLayoutManager mLayoutManager = null;
    @BindView(2131430797)
    View mMaskView;
    @BindView(2131429586)
    View mMenuIcom;
    @BindView(2131431674)
    PtrFrameLayout mPullRefreshLL;
    @BindView(2131430975)
    TextView mTitle;
    @BindView(2131432919)
    View mTitleBar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_consumables_list);
        ButterKnife.bind((Activity) this);
        if (SHApplication.getStateNotifier().a() != 4) {
            finish();
            ToastUtil.a((int) R.string.login_des_tips);
            return;
        }
        this.b = HomeManager.a().l();
        this.mTitleBar.setBackgroundResource(R.drawable.common_title_bar_bg);
        a(this.b);
    }

    /* access modifiers changed from: private */
    public void a(final String str) {
        Home j = HomeManager.a().j(str);
        if (j == null) {
            c();
            this.g = false;
            return;
        }
        this.c = j.p();
        this.mTitle.setText(j.k());
        this.mEmptyTV.setText(R.string.no_data_tips);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ConsumablesActivity.this.c(view);
            }
        });
        this.mEmptyView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ConsumablesActivity.this.b(view);
            }
        });
        this.mLayoutManager = new LinearLayoutManager(this);
        this.d = new ComsumableAdapter();
        this.list.setLayoutManager(this.mLayoutManager);
        this.list.setAdapter(this.d);
        this.mPullRefreshLL.setPtrHandler(new PtrDefaultHandler() {
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                if (SHApplication.getStateNotifier().a() != 4) {
                    ConsumablesActivity.this.c();
                    ConsumablesActivity.this.mPullRefreshLL.refreshComplete();
                    ConsumablesActivity.this.e();
                    boolean unused = ConsumablesActivity.this.g = false;
                } else if (ConsumablesActivity.this.f) {
                    LogUtil.a("ConsumableData", "isRefreshing==true");
                } else {
                    boolean unused2 = ConsumablesActivity.this.f = true;
                    if (!SmartHomeDeviceManager.a().u() || SmartHomeDeviceManager.a().t()) {
                        SmartHomeDeviceManager.a().a(ConsumablesActivity.this.h);
                        SmartHomeDeviceManager.a().p();
                    }
                    ConsumablesActivity.this.mData.clear();
                    ConsumablesActivity.this.d.a(ConsumablesActivity.this.mData);
                    ConsumableDataManager.a().a((Context) ConsumablesActivity.this, str, false, (ConsumableDataManager.IConsumableListener) new ConsumableDataManager.IConsumableListener() {
                        public void a(int i, String str, List<DeviceConsumble> list) {
                            boolean unused = ConsumablesActivity.this.f = false;
                            if (i == 1) {
                                boolean unused2 = ConsumablesActivity.this.g = false;
                            }
                            if (ConsumablesActivity.this.isValid()) {
                                ConsumablesActivity.this.c();
                                ConsumablesActivity.this.mData.clear();
                                ConsumablesActivity.this.mData.addAll(ConsumableDataManager.a().a(ConsumablesActivity.this.b));
                                ConsumablesActivity.this.d();
                            }
                        }

                        public void a(String str, String str2) {
                            boolean unused = ConsumablesActivity.this.f = false;
                            boolean unused2 = ConsumablesActivity.this.g = false;
                            if (ConsumablesActivity.this.isValid()) {
                                ConsumablesActivity.this.c();
                                if (TextUtils.equals(ConsumablesActivity.this.b, str2)) {
                                    LogUtil.b("Consumables", str);
                                    ToastUtil.a((int) R.string.retrieve_data_fail);
                                }
                            }
                        }

                        public void a(String str) {
                            boolean unused = ConsumablesActivity.this.f = false;
                            boolean unused2 = ConsumablesActivity.this.g = false;
                            if (ConsumablesActivity.this.isValid()) {
                                ConsumablesActivity.this.c();
                                if (ConsumablesActivity.this.mPullRefreshLL.isRefreshing()) {
                                    ConsumablesActivity.this.mPullRefreshLL.refreshComplete();
                                }
                            }
                        }
                    });
                }
            }
        });
        this.mPullRefreshLL.autoRefresh(true);
        this.mGroupTitle.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ConsumablesActivity.this.a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        if (!this.mPullRefreshLL.isRefreshing()) {
            this.mPullRefreshLL.autoRefresh();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        a();
    }

    private void a() {
        if (!this.mPullRefreshLL.isRefreshing() && !this.g && !this.f) {
            this.g = true;
            this.mPullRefreshLL.refreshComplete();
            a(true);
            HomeListDialogHelper.a((Context) this, this.mTitleBar, false, this.b, false, (HomeListDialogHelper.HomeListDialogV2Listener) new HomeListDialogHelper.HomeListDialogV2Listener() {
                public void a() {
                    ConsumablesActivity.this.a(false);
                    boolean unused = ConsumablesActivity.this.g = false;
                }

                public void a(Home home) {
                    if (home == null || TextUtils.equals(home.j(), ConsumablesActivity.this.b)) {
                        boolean unused = ConsumablesActivity.this.g = false;
                        return;
                    }
                    String unused2 = ConsumablesActivity.this.b = home.j();
                    ConsumablesActivity.this.mTitle.setText(home.k());
                    ConsumablesActivity.this.b();
                    ConsumablesActivity.this.mData.clear();
                    ConsumablesActivity.this.d.a(ConsumablesActivity.this.mData);
                    try {
                        if (HomeManager.a().o(ConsumablesActivity.this.b).isEmpty()) {
                            SharedHomeDeviceManager.b().a(Long.parseLong(ConsumablesActivity.this.b), (AsyncCallback) new AsyncCallback() {
                                /* access modifiers changed from: private */
                                public /* synthetic */ void b() {
                                    ConsumablesActivity.this.a(ConsumablesActivity.this.b);
                                }

                                public void onSuccess(Object obj) {
                                    ConsumablesActivity.this.mHandler.post(
                                    /*  JADX ERROR: Method code generation error
                                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000b: INVOKE  
                                          (wrap: android.os.Handler : 0x0004: IGET  (r2v3 android.os.Handler) = 
                                          (wrap: com.xiaomi.smarthome.miio.activity.ConsumablesActivity : 0x0002: IGET  (r2v2 com.xiaomi.smarthome.miio.activity.ConsumablesActivity) = 
                                          (wrap: com.xiaomi.smarthome.miio.activity.ConsumablesActivity$3 : 0x0000: IGET  (r2v1 com.xiaomi.smarthome.miio.activity.ConsumablesActivity$3) = 
                                          (r1v0 'this' com.xiaomi.smarthome.miio.activity.ConsumablesActivity$3$1 A[THIS])
                                         com.xiaomi.smarthome.miio.activity.ConsumablesActivity.3.1.a com.xiaomi.smarthome.miio.activity.ConsumablesActivity$3)
                                         com.xiaomi.smarthome.miio.activity.ConsumablesActivity.3.a com.xiaomi.smarthome.miio.activity.ConsumablesActivity)
                                         com.xiaomi.smarthome.miio.activity.ConsumablesActivity.mHandler android.os.Handler)
                                          (wrap: com.xiaomi.smarthome.miio.activity.-$$Lambda$ConsumablesActivity$3$1$jBUTFIHlzoqyLD4Ikg9wUu1Ue3c : 0x0008: CONSTRUCTOR  (r0v0 com.xiaomi.smarthome.miio.activity.-$$Lambda$ConsumablesActivity$3$1$jBUTFIHlzoqyLD4Ikg9wUu1Ue3c) = 
                                          (r1v0 'this' com.xiaomi.smarthome.miio.activity.ConsumablesActivity$3$1 A[THIS])
                                         call: com.xiaomi.smarthome.miio.activity.-$$Lambda$ConsumablesActivity$3$1$jBUTFIHlzoqyLD4Ikg9wUu1Ue3c.<init>(com.xiaomi.smarthome.miio.activity.ConsumablesActivity$3$1):void type: CONSTRUCTOR)
                                         android.os.Handler.post(java.lang.Runnable):boolean type: VIRTUAL in method: com.xiaomi.smarthome.miio.activity.ConsumablesActivity.3.1.onSuccess(java.lang.Object):void, dex: classes6.dex
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
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:311)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:68)
                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
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
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
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
                                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0008: CONSTRUCTOR  (r0v0 com.xiaomi.smarthome.miio.activity.-$$Lambda$ConsumablesActivity$3$1$jBUTFIHlzoqyLD4Ikg9wUu1Ue3c) = 
                                          (r1v0 'this' com.xiaomi.smarthome.miio.activity.ConsumablesActivity$3$1 A[THIS])
                                         call: com.xiaomi.smarthome.miio.activity.-$$Lambda$ConsumablesActivity$3$1$jBUTFIHlzoqyLD4Ikg9wUu1Ue3c.<init>(com.xiaomi.smarthome.miio.activity.ConsumablesActivity$3$1):void type: CONSTRUCTOR in method: com.xiaomi.smarthome.miio.activity.ConsumablesActivity.3.1.onSuccess(java.lang.Object):void, dex: classes6.dex
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                        	... 114 more
                                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.xiaomi.smarthome.miio.activity.-$$Lambda$ConsumablesActivity$3$1$jBUTFIHlzoqyLD4Ikg9wUu1Ue3c, state: NOT_LOADED
                                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                        	... 120 more
                                        */
                                    /*
                                        this = this;
                                        com.xiaomi.smarthome.miio.activity.ConsumablesActivity$3 r2 = com.xiaomi.smarthome.miio.activity.ConsumablesActivity.AnonymousClass3.this
                                        com.xiaomi.smarthome.miio.activity.ConsumablesActivity r2 = com.xiaomi.smarthome.miio.activity.ConsumablesActivity.this
                                        android.os.Handler r2 = r2.mHandler
                                        com.xiaomi.smarthome.miio.activity.-$$Lambda$ConsumablesActivity$3$1$jBUTFIHlzoqyLD4Ikg9wUu1Ue3c r0 = new com.xiaomi.smarthome.miio.activity.-$$Lambda$ConsumablesActivity$3$1$jBUTFIHlzoqyLD4Ikg9wUu1Ue3c
                                        r0.<init>(r1)
                                        r2.post(r0)
                                        return
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.activity.ConsumablesActivity.AnonymousClass3.AnonymousClass1.onSuccess(java.lang.Object):void");
                                }

                                /* access modifiers changed from: private */
                                public /* synthetic */ void a() {
                                    ConsumablesActivity.this.a(ConsumablesActivity.this.b);
                                }

                                public void onFailure(Error error) {
                                    ConsumablesActivity.this.mHandler.post(
                                    /*  JADX ERROR: Method code generation error
                                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000b: INVOKE  
                                          (wrap: android.os.Handler : 0x0004: IGET  (r2v3 android.os.Handler) = 
                                          (wrap: com.xiaomi.smarthome.miio.activity.ConsumablesActivity : 0x0002: IGET  (r2v2 com.xiaomi.smarthome.miio.activity.ConsumablesActivity) = 
                                          (wrap: com.xiaomi.smarthome.miio.activity.ConsumablesActivity$3 : 0x0000: IGET  (r2v1 com.xiaomi.smarthome.miio.activity.ConsumablesActivity$3) = 
                                          (r1v0 'this' com.xiaomi.smarthome.miio.activity.ConsumablesActivity$3$1 A[THIS])
                                         com.xiaomi.smarthome.miio.activity.ConsumablesActivity.3.1.a com.xiaomi.smarthome.miio.activity.ConsumablesActivity$3)
                                         com.xiaomi.smarthome.miio.activity.ConsumablesActivity.3.a com.xiaomi.smarthome.miio.activity.ConsumablesActivity)
                                         com.xiaomi.smarthome.miio.activity.ConsumablesActivity.mHandler android.os.Handler)
                                          (wrap: com.xiaomi.smarthome.miio.activity.-$$Lambda$ConsumablesActivity$3$1$CsuI9bmxEGKYcgdh4G70WmZiSZ4 : 0x0008: CONSTRUCTOR  (r0v0 com.xiaomi.smarthome.miio.activity.-$$Lambda$ConsumablesActivity$3$1$CsuI9bmxEGKYcgdh4G70WmZiSZ4) = 
                                          (r1v0 'this' com.xiaomi.smarthome.miio.activity.ConsumablesActivity$3$1 A[THIS])
                                         call: com.xiaomi.smarthome.miio.activity.-$$Lambda$ConsumablesActivity$3$1$CsuI9bmxEGKYcgdh4G70WmZiSZ4.<init>(com.xiaomi.smarthome.miio.activity.ConsumablesActivity$3$1):void type: CONSTRUCTOR)
                                         android.os.Handler.post(java.lang.Runnable):boolean type: VIRTUAL in method: com.xiaomi.smarthome.miio.activity.ConsumablesActivity.3.1.onFailure(com.xiaomi.smarthome.frame.Error):void, dex: classes6.dex
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
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:311)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:68)
                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
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
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
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
                                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0008: CONSTRUCTOR  (r0v0 com.xiaomi.smarthome.miio.activity.-$$Lambda$ConsumablesActivity$3$1$CsuI9bmxEGKYcgdh4G70WmZiSZ4) = 
                                          (r1v0 'this' com.xiaomi.smarthome.miio.activity.ConsumablesActivity$3$1 A[THIS])
                                         call: com.xiaomi.smarthome.miio.activity.-$$Lambda$ConsumablesActivity$3$1$CsuI9bmxEGKYcgdh4G70WmZiSZ4.<init>(com.xiaomi.smarthome.miio.activity.ConsumablesActivity$3$1):void type: CONSTRUCTOR in method: com.xiaomi.smarthome.miio.activity.ConsumablesActivity.3.1.onFailure(com.xiaomi.smarthome.frame.Error):void, dex: classes6.dex
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                        	... 114 more
                                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.xiaomi.smarthome.miio.activity.-$$Lambda$ConsumablesActivity$3$1$CsuI9bmxEGKYcgdh4G70WmZiSZ4, state: NOT_LOADED
                                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                        	... 120 more
                                        */
                                    /*
                                        this = this;
                                        com.xiaomi.smarthome.miio.activity.ConsumablesActivity$3 r2 = com.xiaomi.smarthome.miio.activity.ConsumablesActivity.AnonymousClass3.this
                                        com.xiaomi.smarthome.miio.activity.ConsumablesActivity r2 = com.xiaomi.smarthome.miio.activity.ConsumablesActivity.this
                                        android.os.Handler r2 = r2.mHandler
                                        com.xiaomi.smarthome.miio.activity.-$$Lambda$ConsumablesActivity$3$1$CsuI9bmxEGKYcgdh4G70WmZiSZ4 r0 = new com.xiaomi.smarthome.miio.activity.-$$Lambda$ConsumablesActivity$3$1$CsuI9bmxEGKYcgdh4G70WmZiSZ4
                                        r0.<init>(r1)
                                        r2.post(r0)
                                        return
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.activity.ConsumablesActivity.AnonymousClass3.AnonymousClass1.onFailure(com.xiaomi.smarthome.frame.Error):void");
                                }
                            });
                        } else {
                            ConsumablesActivity.this.mHandler.post(new Runnable() {
                                public final void run() {
                                    ConsumablesActivity.AnonymousClass3.this.c();
                                }
                            });
                        }
                    } catch (Exception e) {
                        LogUtil.a("Consumables", "Exception:" + e.getMessage());
                        ConsumablesActivity.this.mHandler.post(new Runnable() {
                            public final void run() {
                                ConsumablesActivity.AnonymousClass3.this.b();
                            }
                        });
                    }
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void c() {
                    ConsumablesActivity.this.a(ConsumablesActivity.this.b);
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void b() {
                    ConsumablesActivity.this.a(ConsumablesActivity.this.b);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (isValid() && this.mMenuIcom != null && this.mMaskView != null) {
            this.mMenuIcom.animate().rotation(z ? -180.0f : 0.0f).setDuration(200).setInterpolator(new AccelerateDecelerateInterpolator());
            this.mMaskView.setVisibility(z ? 0 : 8);
            this.mMaskView.setAnimation(AnimationUtils.loadAnimation(SHApplication.getAppContext(), z ? R.anim.dd_mask_in : R.anim.dd_mask_out));
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        if (isValid()) {
            c();
            this.f11720a = XQProgressDialog.a(this, "", getString(R.string.loading));
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        if (isValid() && this.f11720a != null && this.f11720a.isShowing()) {
            this.f11720a.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        ArrayList arrayList = new ArrayList();
        for (int size = this.mData.size() - 1; size >= 0; size--) {
            Device b2 = SmartHomeDeviceManager.a().b(this.mData.get(size).c);
            if (b2 == null || TextUtils.isEmpty(b2.name)) {
                this.mData.remove(size);
            } else {
                this.mData.get(size).f = b2.isOnline;
                if (!this.mData.get(size).f) {
                    DeviceConsumble deviceConsumble = this.mData.get(size);
                    deviceConsumble.f = false;
                    deviceConsumble.e = this.mData.get(size).e;
                    deviceConsumble.c = this.mData.get(size).c;
                    deviceConsumble.d = this.mData.get(size).d;
                    deviceConsumble.f13554a = 0;
                    if (deviceConsumble.b != null) {
                        deviceConsumble.b.clear();
                    }
                    arrayList.add(0, deviceConsumble);
                    this.mData.remove(size);
                }
            }
        }
        if (arrayList.size() > 0) {
            this.mData.addAll(arrayList);
        }
        this.d.a(this.mData);
        e();
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.d.b() == 0) {
            this.mEmptyView.setVisibility(0);
            this.list.setVisibility(8);
            return;
        }
        this.list.setVisibility(0);
        this.mEmptyView.setVisibility(8);
    }

    class ComsumableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int d = 2;
        private static final int e = 1;
        private static final int f = 0;
        private static final int g = 5;
        private static final int h = 3;
        private static final int i = 4;

        /* renamed from: a  reason: collision with root package name */
        ShapeDrawable f11728a;
        Drawable[] b = new Drawable[3];
        private List<DeviceConsumble> j = new ArrayList();

        public long getItemId(int i2) {
            return (long) i2;
        }

        ComsumableAdapter() {
            setHasStableIds(true);
            this.f11728a = new ShapeDrawable(new OvalShape());
            this.f11728a.getPaint().setColor(-1);
            this.f11728a.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
            this.b[2] = this.f11728a;
        }

        private void a() {
            if (ConsumablesActivity.this.e == null) {
                Dialog unused = ConsumablesActivity.this.e = XQProgressDialog.a(ConsumablesActivity.this, "", ConsumablesActivity.this.getString(R.string.unauthing));
            }
        }

        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            if (i2 == 0) {
                return new GroupViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_consumable_first, viewGroup, false));
            }
            if (i2 == 5) {
                return new GroupViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_consumable_first_2, viewGroup, false));
            }
            if (i2 == 4) {
                return new GroupViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_consumable_group_last_0, viewGroup, false));
            }
            if (i2 == 1) {
                return new ChildViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_consumable_second, viewGroup, false));
            }
            if (i2 == 2) {
                return new ChildViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_consumable_last, viewGroup, false));
            }
            return new ChildViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_consumable_group_last, viewGroup, false));
        }

        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
            String str;
            viewHolder.itemView.setOnClickListener((View.OnClickListener) null);
            if (viewHolder instanceof GroupViewHolder) {
                DeviceConsumble b2 = b(i2);
                if (b2 != null) {
                    Device b3 = SmartHomeDeviceManager.a().b(b2.c);
                    if (b2.f) {
                        str = "";
                    } else if (b2.e) {
                        str = Operators.BRACKET_START_STR + ConsumablesActivity.this.getString(R.string.cant_connect_ble) + Operators.BRACKET_END_STR;
                    } else {
                        str = Operators.BRACKET_START_STR + ConsumablesActivity.this.getString(R.string.offline_device) + Operators.BRACKET_END_STR;
                    }
                    GroupViewHolder groupViewHolder = (GroupViewHolder) viewHolder;
                    TextView textView = groupViewHolder.f11730a;
                    if (b3 != null && !TextUtils.isEmpty(b3.name)) {
                        str = b3.name + str;
                    }
                    textView.setText(str);
                    if (!b2.f) {
                        groupViewHolder.f11730a.setTextColor(ConsumablesActivity.this.getResources().getColor(R.color.class_text_3));
                    } else {
                        groupViewHolder.f11730a.setTextColor(ConsumablesActivity.this.getResources().getColor(R.color.class_V));
                    }
                    String k = HomeManager.a().q(b2.c) != null ? HomeManager.a().q(b2.c).k() : "";
                    String e2 = HomeManager.a().p(b2.c) != null ? HomeManager.a().p(b2.c).e() : "";
                    if (TextUtils.isEmpty(e2)) {
                        e2 = ConsumablesActivity.this.getString(R.string.default_room);
                    }
                    if (!TextUtils.isEmpty(k) && !TextUtils.isEmpty(e2)) {
                        groupViewHolder.b.setText(k + " | " + e2);
                    } else if (!TextUtils.isEmpty(k) || !TextUtils.isEmpty(e2)) {
                        TextView textView2 = groupViewHolder.b;
                        if (!TextUtils.isEmpty(k)) {
                            e2 = k;
                        }
                        textView2.setText(e2);
                    } else {
                        groupViewHolder.b.setText("");
                    }
                } else {
                    GroupViewHolder groupViewHolder2 = (GroupViewHolder) viewHolder;
                    groupViewHolder2.f11730a.setText("");
                    groupViewHolder2.b.setText("");
                }
            } else {
                Consumable c2 = c(i2);
                if (c2 != null) {
                    if (c2.g) {
                        ChildViewHolder childViewHolder = (ChildViewHolder) viewHolder;
                        childViewHolder.f11727a.setVisibility(8);
                        childViewHolder.d.setVisibility(0);
                        a((View) childViewHolder.d, (int) c2.f13545a, c2.b);
                    } else {
                        ChildViewHolder childViewHolder2 = (ChildViewHolder) viewHolder;
                        childViewHolder2.f11727a.setVisibility(0);
                        childViewHolder2.d.setVisibility(8);
                        a((View) childViewHolder2.f11727a, (int) c2.f13545a, c2.b);
                    }
                    if (!TextUtils.isEmpty(c2.h)) {
                        ChildViewHolder childViewHolder3 = (ChildViewHolder) viewHolder;
                        childViewHolder3.c.setVisibility(0);
                        childViewHolder3.c.setText(c2.h);
                    } else if (c2.c >= 0) {
                        ChildViewHolder childViewHolder4 = (ChildViewHolder) viewHolder;
                        childViewHolder4.c.setVisibility(0);
                        childViewHolder4.c.setText(ConsumablesActivity.this.getResources().getQuantityString(R.plurals.consumables_remain_days, c2.c, new Object[]{Integer.valueOf(c2.c)}));
                    } else if (c2.f13545a == 0.0d) {
                        ChildViewHolder childViewHolder5 = (ChildViewHolder) viewHolder;
                        childViewHolder5.c.setVisibility(0);
                        childViewHolder5.c.setText(R.string.run_out);
                    } else {
                        ((ChildViewHolder) viewHolder).c.setVisibility(8);
                    }
                    ChildViewHolder childViewHolder6 = (ChildViewHolder) viewHolder;
                    childViewHolder6.b.setText(c2.d);
                    if (c2.g) {
                        childViewHolder6.e.setVisibility(4);
                        viewHolder.itemView.setClickable(false);
                        childViewHolder6.f.setVisibility(0);
                    } else if (!TextUtils.isEmpty(c2.e)) {
                        childViewHolder6.e.setVisibility(0);
                        viewHolder.itemView.setClickable(true);
                        childViewHolder6.f.setVisibility(8);
                    } else {
                        childViewHolder6.e.setVisibility(4);
                        viewHolder.itemView.setClickable(false);
                        childViewHolder6.f.setVisibility(8);
                    }
                    if (!ConsumablesActivity.this.c) {
                        childViewHolder6.f.setVisibility(8);
                    }
                    viewHolder.itemView.setOnClickListener(new View.OnClickListener(i2, c2) {
                        private final /* synthetic */ int f$1;
                        private final /* synthetic */ Consumable f$2;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                        }

                        public final void onClick(View view) {
                            ConsumablesActivity.ComsumableAdapter.this.a(this.f$1, this.f$2, view);
                        }
                    });
                    childViewHolder6.f.setOnClickListener(new View.OnClickListener(c2, i2) {
                        private final /* synthetic */ Consumable f$1;
                        private final /* synthetic */ int f$2;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                        }

                        public final void onClick(View view) {
                            ConsumablesActivity.ComsumableAdapter.this.a(this.f$1, this.f$2, view);
                        }
                    });
                    return;
                }
                ChildViewHolder childViewHolder7 = (ChildViewHolder) viewHolder;
                a((View) childViewHolder7.f11727a, 0, "");
                childViewHolder7.b.setText("");
                childViewHolder7.f11727a.setVisibility(8);
                childViewHolder7.d.setVisibility(8);
            }
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:2:0x000e, code lost:
            r6 = com.xiaomi.smarthome.device.SmartHomeDeviceHelper.a().b().c(r4.model);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ void a(int r4, com.xiaomi.smarthome.miio.consumables.Consumable r5, android.view.View r6) {
            /*
                r3 = this;
                java.lang.String r4 = r3.d(r4)
                com.xiaomi.smarthome.device.SmartHomeDeviceManager r6 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
                com.xiaomi.smarthome.device.Device r4 = r6.b((java.lang.String) r4)
                if (r4 == 0) goto L_0x0021
                com.xiaomi.smarthome.device.SmartHomeDeviceHelper r6 = com.xiaomi.smarthome.device.SmartHomeDeviceHelper.a()
                com.xiaomi.smarthome.device.utils.DeviceTagInterface r6 = r6.b()
                java.lang.String r0 = r4.model
                com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category r6 = r6.c((java.lang.String) r0)
                if (r6 == 0) goto L_0x0021
                java.lang.String r6 = r6.d
                goto L_0x0022
            L_0x0021:
                r6 = 0
            L_0x0022:
                com.xiaomi.smarthome.stat.StatClick r0 = com.xiaomi.smarthome.stat.STAT.d
                boolean r1 = android.text.TextUtils.isEmpty(r6)
                if (r1 == 0) goto L_0x002c
                java.lang.String r6 = ""
            L_0x002c:
                java.lang.String r1 = r5.d
                boolean r1 = android.text.TextUtils.isEmpty(r1)
                if (r1 == 0) goto L_0x0037
                java.lang.String r1 = ""
                goto L_0x0039
            L_0x0037:
                java.lang.String r1 = r5.d
            L_0x0039:
                r0.g((java.lang.String) r6, (java.lang.String) r1)
                com.xiaomi.smarthome.stat.StatClick r6 = com.xiaomi.smarthome.stat.STAT.d
                java.lang.String r4 = r4.model
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = r5.d
                boolean r1 = android.text.TextUtils.isEmpty(r1)
                if (r1 == 0) goto L_0x0050
                java.lang.String r1 = "剩余："
                goto L_0x0063
            L_0x0050:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = r5.d
                r1.append(r2)
                java.lang.String r2 = "剩余："
                r1.append(r2)
                java.lang.String r1 = r1.toString()
            L_0x0063:
                r0.append(r1)
                double r1 = r5.f13545a
                r0.append(r1)
                java.lang.String r1 = "%"
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                java.lang.String r1 = r5.e
                boolean r1 = android.text.TextUtils.isEmpty(r1)
                if (r1 == 0) goto L_0x007f
                java.lang.String r1 = ""
                goto L_0x0081
            L_0x007f:
                java.lang.String r1 = r5.e
            L_0x0081:
                r6.a((java.lang.String) r4, (java.lang.String) r0, (java.lang.String) r1)
                java.lang.String r4 = r5.e
                boolean r4 = android.text.TextUtils.isEmpty(r4)
                if (r4 != 0) goto L_0x0095
                com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger r4 = com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger.a()
                java.lang.String r5 = r5.e
                r4.c(r5)
            L_0x0095:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.activity.ConsumablesActivity.ComsumableAdapter.a(int, com.xiaomi.smarthome.miio.consumables.Consumable, android.view.View):void");
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(final Consumable consumable, int i2, View view) {
            if (ConsumablesActivity.this.c && consumable.g && !TextUtils.isEmpty(consumable.f)) {
                a();
                if (ConsumablesActivity.this.e != null && !ConsumablesActivity.this.e.isShowing()) {
                    ConsumablesActivity.this.e.show();
                }
                final String d2 = d(i2);
                ConsumableDataManager.a().a((Context) ConsumablesActivity.this, d2, consumable.f, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        if (ConsumablesActivity.this.isValid()) {
                            ConsumablesActivity.this.a(d2, consumable.f);
                        }
                    }

                    public void onFailure(Error error) {
                        if (ConsumablesActivity.this.isValid()) {
                            if (ConsumablesActivity.this.e != null && ConsumablesActivity.this.e.isShowing()) {
                                ConsumablesActivity.this.e.dismiss();
                            }
                            ToastUtil.a((int) R.string.action_fail);
                        }
                    }
                });
            }
        }

        public int getItemCount() {
            return b();
        }

        public int getItemViewType(int i2) {
            return a(i2);
        }

        private int a(View view, int i2, String str) {
            int i3;
            float f2 = (i2 < 0 || i2 > 100) ? 0.0f : ((float) i2) * 3.6f;
            int a2 = DisplayUtils.a(3.0f);
            if (i2 <= 0) {
                i3 = Color.parseColor("#FF3C2F");
            } else {
                i3 = Color.parseColor("#32BAC0");
            }
            ShapeDrawable shapeDrawable = new ShapeDrawable(new ArcShape(-90.0f, f2));
            shapeDrawable.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
            if (f2 == 0.0f) {
                shapeDrawable.getPaint().setColor(0);
            } else {
                shapeDrawable.getPaint().setColor(i3);
            }
            this.b[0] = shapeDrawable;
            ShapeDrawable shapeDrawable2 = new ShapeDrawable(new ArcShape(f2 - 90.0f, 360.0f - f2));
            if (f2 == 360.0f) {
                shapeDrawable2.getPaint().setColor(0);
            } else {
                shapeDrawable2.getPaint().setColor(Color.parseColor("#eeeeee"));
            }
            shapeDrawable2.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
            this.b[1] = shapeDrawable2;
            LayerDrawable layerDrawable = new LayerDrawable(this.b);
            layerDrawable.setLayerInset(2, a2, a2, a2, a2);
            view.setBackground(layerDrawable);
            if (view instanceof TextView) {
                if (i2 >= 0 && i2 <= 100) {
                    TextView textView = (TextView) view;
                    textView.setText(i2 + Operators.MOD);
                    textView.getPaint().setFakeBoldText(false);
                } else if (!TextUtils.isEmpty(str)) {
                    TextView textView2 = (TextView) view;
                    textView2.setText(str);
                    textView2.getPaint().setFakeBoldText(true);
                } else {
                    ((TextView) view).setText("");
                }
                ((TextView) view).setTextColor(i3);
            }
            return i3;
        }

        /* access modifiers changed from: private */
        public int b() {
            if (this.j == null) {
                return 0;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.j.size(); i3++) {
                i2 += this.j.get(i3).f13554a + 1;
            }
            return i2;
        }

        private int a(int i2) {
            if (this.j != null) {
                if (i2 == getItemCount() - 1) {
                    return this.j.get(this.j.size() - 1).b.size() > 0 ? 3 : 4;
                }
                int i3 = 0;
                int i4 = 0;
                while (i3 < this.j.size()) {
                    if (i2 != i4) {
                        i4 = i4 + 1 + this.j.get(i3).f13554a;
                        if (i2 == getItemCount() - 1) {
                            return this.j.get(this.j.size() - 1).b.size() > 0 ? 3 : 4;
                        }
                        if (i2 == i4 - 1) {
                            return 2;
                        }
                        if (i2 < i4) {
                            return 1;
                        }
                        i3++;
                    } else if (this.j.get(i3).f13554a == 0) {
                        return 5;
                    } else {
                        return 0;
                    }
                }
            }
            return 0;
        }

        private DeviceConsumble b(int i2) {
            if (this.j == null) {
                return null;
            }
            int i3 = 0;
            for (int i4 = 0; i4 < this.j.size(); i4++) {
                if (i2 == i3) {
                    return this.j.get(i4);
                }
                i3 += this.j.get(i4).f13554a + 1;
            }
            return null;
        }

        private Consumable c(int i2) {
            if (this.j == null) {
                return null;
            }
            int i3 = 0;
            for (int i4 = 0; i4 < this.j.size(); i4++) {
                int i5 = i3 + 1;
                int i6 = i2 - i5;
                if (i6 < this.j.get(i4).f13554a) {
                    return this.j.get(i4).b.get(i6);
                }
                i3 = i5 + this.j.get(i4).f13554a;
            }
            return null;
        }

        private String d(int i2) {
            if (this.j == null) {
                return null;
            }
            int i3 = 0;
            for (int i4 = 0; i4 < this.j.size(); i4++) {
                int i5 = i3 + 1;
                if (i2 - i5 < this.j.get(i4).f13554a) {
                    return this.j.get(i4).c;
                }
                i3 = i5 + this.j.get(i4).f13554a;
            }
            return null;
        }

        public void a(List<DeviceConsumble> list) {
            this.j.clear();
            if (!list.isEmpty()) {
                this.j.addAll(list);
            }
            ConsumablesActivity.this.list.stopScroll();
            ConsumablesActivity.this.list.getRecycledViewPool().clear();
            ConsumablesActivity.this.d.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: private */
    public void a(final String str, final String str2) {
        if (this.e != null && this.e.isShowing()) {
            this.e.dismiss();
        }
        ConsumableDataManager.a().a((Context) this, this.b, true, (ConsumableDataManager.IConsumableListener) new ConsumableDataManager.IConsumableListener() {
            public void a(int i, String str, List<DeviceConsumble> list) {
                List<Consumable> list2;
                List<Consumable> list3;
                if (ConsumablesActivity.this.isValid() && TextUtils.equals(ConsumablesActivity.this.b, str) && i == 1) {
                    DeviceConsumble deviceConsumble = null;
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        if (TextUtils.equals(list.get(i2).c, str) && (list3 = list.get(i2).b) != null && list3.size() > 0) {
                            int i3 = 0;
                            while (true) {
                                if (i3 >= list3.size()) {
                                    break;
                                } else if (TextUtils.equals(list3.get(i3).f, str2)) {
                                    deviceConsumble = list.get(i2);
                                    break;
                                } else {
                                    i3++;
                                }
                            }
                        }
                    }
                    if (deviceConsumble == null) {
                        ToastUtil.a((int) R.string.retrieve_data_fail);
                        return;
                    }
                    for (int i4 = 0; i4 < ConsumablesActivity.this.mData.size(); i4++) {
                        if (TextUtils.equals(ConsumablesActivity.this.mData.get(i4).c, str) && (list2 = ConsumablesActivity.this.mData.get(i4).b) != null && list2.size() > 0) {
                            int i5 = 0;
                            while (true) {
                                if (i5 >= list2.size()) {
                                    break;
                                } else if (TextUtils.equals(list2.get(i5).f, str2)) {
                                    ConsumablesActivity.this.mData.add(i4, deviceConsumble);
                                    ConsumablesActivity.this.mData.remove(i4 + 1);
                                    break;
                                } else {
                                    i5++;
                                }
                            }
                        }
                    }
                    ConsumablesActivity.this.d();
                }
            }

            public void a(String str, String str2) {
                if (ConsumablesActivity.this.isValid() && TextUtils.equals(ConsumablesActivity.this.b, str2)) {
                    LogUtil.b("Consumables", str);
                    ToastUtil.a((int) R.string.retrieve_data_fail);
                }
            }

            public void a(String str) {
                if (ConsumablesActivity.this.mPullRefreshLL.isRefreshing()) {
                    ConsumablesActivity.this.mPullRefreshLL.refreshComplete();
                }
                ConsumablesActivity.this.e();
            }
        });
    }

    private static class GroupViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f11730a;
        TextView b;

        GroupViewHolder(View view) {
            super(view);
            this.f11730a = (TextView) view.findViewById(R.id.device_name);
            this.b = (TextView) view.findViewById(R.id.home_room_name);
        }
    }

    private static class ChildViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f11727a;
        TextView b;
        TextView c;
        ImageView d;
        View e;
        View f;

        ChildViewHolder(View view) {
            super(view);
            this.f11727a = (TextView) view.findViewById(R.id.percentage);
            this.b = (TextView) view.findViewById(R.id.description);
            this.e = view.findViewById(R.id.to_buy);
            this.c = (TextView) view.findViewById(R.id.remain_days);
            this.f = view.findViewById(R.id.btn_reset);
            this.d = (ImageView) view.findViewById(R.id.icon_low_battery);
        }
    }
}
