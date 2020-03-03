package com.xiaomi.youpin.share.ui.assemble;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import com.xiaomi.youpin.business_common.FrescoUtils;
import com.xiaomi.youpin.common.base.AsyncCallback;
import com.xiaomi.youpin.common.base.ExceptionError;
import com.xiaomi.youpin.common.base.YouPinError;
import com.xiaomi.youpin.share.R;
import com.xiaomi.youpin.share.ShareEventUtil;
import com.xiaomi.youpin.share.ShareManager;
import com.xiaomi.youpin.share.ShareObject;
import com.xiaomi.youpin.share.ShareRecordConstant;
import com.xiaomi.youpin.share.ShareUtil;
import com.xiaomi.youpin.share.config.YouPinShareApi;
import com.xiaomi.youpin.share.model.PosterData;
import com.xiaomi.youpin.share.model.ShareChannel;
import com.xiaomi.youpin.share.ui.BaseActivity;
import com.xiaomi.youpin.yp_permission.PermissionCallback;
import com.xiaomi.youpin.yp_permission.YouPinPermissionManager;
import com.xiaomiyoupin.toast.YPDToast;

class SharePosterHelper {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Activity f23744a;
    /* access modifiers changed from: private */
    public ShareBaseHelper b;
    /* access modifiers changed from: private */
    public ShareViewHelper c;
    private String d;
    private String e;
    /* access modifiers changed from: private */
    public Bitmap f;

    SharePosterHelper(ShareBaseHelper shareBaseHelper) {
        this.b = shareBaseHelper;
        this.f23744a = shareBaseHelper.a();
        this.c = shareBaseHelper.b();
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.c.d((View.OnClickListener) new View.OnClickListener() {
            public final void onClick(View view) {
                SharePosterHelper.this.e(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e(View view) {
        this.c.g();
        b();
    }

    private void b() {
        Uri parse = Uri.parse(this.f23744a.getIntent().getStringExtra("shareUrl"));
        this.d = parse.getQueryParameter("title");
        this.e = parse.getQueryParameter("content");
        String queryParameter = parse.getQueryParameter("url");
        if (TextUtils.isEmpty(queryParameter)) {
            this.c.i();
            YPDToast.getInstance().toast((Context) this.f23744a, "海报生成失败，请重试(-3)~");
        }
        final PosterData posterData = (PosterData) this.f23744a.getIntent().getParcelableExtra("poster");
        int applyDimension = (int) TypedValue.applyDimension(1, 63.0f, this.f23744a.getResources().getDisplayMetrics());
        ShareUtil.a(queryParameter, Color.parseColor("#865C30"), applyDimension, applyDimension, new AsyncCallback<Bitmap, ExceptionError>() {
            public void a(Bitmap bitmap) {
                SharePosterHelper.this.c.d();
                SharePosterHelper.this.c.a(posterData, FrescoUtils.a(posterData.c), bitmap, new AsyncCallback<Void, YouPinError>() {
                    public void a(Void voidR) {
                        if (!SharePosterHelper.this.c.f()) {
                            SharePosterHelper.this.c.j().post(
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0020: INVOKE  
                                  (wrap: android.view.View : 0x0017: INVOKE  (r2v8 android.view.View) = 
                                  (wrap: com.xiaomi.youpin.share.ui.assemble.ShareViewHelper : 0x0013: INVOKE  (r2v7 com.xiaomi.youpin.share.ui.assemble.ShareViewHelper) = 
                                  (wrap: com.xiaomi.youpin.share.ui.assemble.SharePosterHelper : 0x0011: IGET  (r2v6 com.xiaomi.youpin.share.ui.assemble.SharePosterHelper) = 
                                  (wrap: com.xiaomi.youpin.share.ui.assemble.SharePosterHelper$1 : 0x000f: IGET  (r2v5 com.xiaomi.youpin.share.ui.assemble.SharePosterHelper$1) = 
                                  (r1v0 'this' com.xiaomi.youpin.share.ui.assemble.SharePosterHelper$1$1 A[THIS])
                                 com.xiaomi.youpin.share.ui.assemble.SharePosterHelper.1.1.a com.xiaomi.youpin.share.ui.assemble.SharePosterHelper$1)
                                 com.xiaomi.youpin.share.ui.assemble.SharePosterHelper.1.b com.xiaomi.youpin.share.ui.assemble.SharePosterHelper)
                                 com.xiaomi.youpin.share.ui.assemble.SharePosterHelper.a(com.xiaomi.youpin.share.ui.assemble.SharePosterHelper):com.xiaomi.youpin.share.ui.assemble.ShareViewHelper type: STATIC)
                                 com.xiaomi.youpin.share.ui.assemble.ShareViewHelper.j():android.view.View type: VIRTUAL)
                                  (wrap: com.xiaomi.youpin.share.ui.assemble.-$$Lambda$SharePosterHelper$1$1$s8q3EdhB4uaB71Ku2Bbah-6QI64 : 0x001d: CONSTRUCTOR  (r0v0 com.xiaomi.youpin.share.ui.assemble.-$$Lambda$SharePosterHelper$1$1$s8q3EdhB4uaB71Ku2Bbah-6QI64) = 
                                  (r1v0 'this' com.xiaomi.youpin.share.ui.assemble.SharePosterHelper$1$1 A[THIS])
                                 call: com.xiaomi.youpin.share.ui.assemble.-$$Lambda$SharePosterHelper$1$1$s8q3EdhB4uaB71Ku2Bbah-6QI64.<init>(com.xiaomi.youpin.share.ui.assemble.SharePosterHelper$1$1):void type: CONSTRUCTOR)
                                 android.view.View.post(java.lang.Runnable):boolean type: VIRTUAL in method: com.xiaomi.youpin.share.ui.assemble.SharePosterHelper.1.1.a(java.lang.Void):void, dex: classes9.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
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
                                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001d: CONSTRUCTOR  (r0v0 com.xiaomi.youpin.share.ui.assemble.-$$Lambda$SharePosterHelper$1$1$s8q3EdhB4uaB71Ku2Bbah-6QI64) = 
                                  (r1v0 'this' com.xiaomi.youpin.share.ui.assemble.SharePosterHelper$1$1 A[THIS])
                                 call: com.xiaomi.youpin.share.ui.assemble.-$$Lambda$SharePosterHelper$1$1$s8q3EdhB4uaB71Ku2Bbah-6QI64.<init>(com.xiaomi.youpin.share.ui.assemble.SharePosterHelper$1$1):void type: CONSTRUCTOR in method: com.xiaomi.youpin.share.ui.assemble.SharePosterHelper.1.1.a(java.lang.Void):void, dex: classes9.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                	... 100 more
                                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.xiaomi.youpin.share.ui.assemble.-$$Lambda$SharePosterHelper$1$1$s8q3EdhB4uaB71Ku2Bbah-6QI64, state: NOT_LOADED
                                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                	... 106 more
                                */
                            /*
                                this = this;
                                com.xiaomi.youpin.share.ui.assemble.SharePosterHelper$1 r2 = com.xiaomi.youpin.share.ui.assemble.SharePosterHelper.AnonymousClass1.this
                                com.xiaomi.youpin.share.ui.assemble.SharePosterHelper r2 = com.xiaomi.youpin.share.ui.assemble.SharePosterHelper.this
                                com.xiaomi.youpin.share.ui.assemble.ShareViewHelper r2 = r2.c
                                boolean r2 = r2.f()
                                if (r2 == 0) goto L_0x000f
                                return
                            L_0x000f:
                                com.xiaomi.youpin.share.ui.assemble.SharePosterHelper$1 r2 = com.xiaomi.youpin.share.ui.assemble.SharePosterHelper.AnonymousClass1.this
                                com.xiaomi.youpin.share.ui.assemble.SharePosterHelper r2 = com.xiaomi.youpin.share.ui.assemble.SharePosterHelper.this
                                com.xiaomi.youpin.share.ui.assemble.ShareViewHelper r2 = r2.c
                                android.view.View r2 = r2.j()
                                com.xiaomi.youpin.share.ui.assemble.-$$Lambda$SharePosterHelper$1$1$s8q3EdhB4uaB71Ku2Bbah-6QI64 r0 = new com.xiaomi.youpin.share.ui.assemble.-$$Lambda$SharePosterHelper$1$1$s8q3EdhB4uaB71Ku2Bbah-6QI64
                                r0.<init>(r1)
                                r2.post(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.share.ui.assemble.SharePosterHelper.AnonymousClass1.AnonymousClass1.a(java.lang.Void):void");
                        }

                        /* access modifiers changed from: private */
                        public /* synthetic */ void a() {
                            Bitmap unused = SharePosterHelper.this.f = SharePosterHelper.this.c.k();
                            SharePosterHelper.this.c.b();
                            SharePosterHelper.this.c.e();
                            SharePosterHelper.this.c();
                        }

                        public void a(YouPinError youPinError) {
                            SharePosterHelper.this.c.i();
                            YPDToast instance = YPDToast.getInstance();
                            Activity b = SharePosterHelper.this.f23744a;
                            instance.toast((Context) b, "海报生成失败，请重试(-2)~ " + youPinError.c());
                        }
                    });
                }

                public void a(ExceptionError exceptionError) {
                    SharePosterHelper.this.c.i();
                    YPDToast instance = YPDToast.getInstance();
                    Activity b2 = SharePosterHelper.this.f23744a;
                    instance.toast((Context) b2, "海报生成失败，请重试(-1)~ " + exceptionError.c());
                }
            });
        }

        /* access modifiers changed from: private */
        public void c() {
            YouPinShareApi.a().d().b();
            int i = 0;
            this.b.a(false);
            if (this.f23744a != null && (this.f23744a instanceof BaseActivity)) {
                i = ((BaseActivity) this.f23744a).getIsBackVal();
            }
            YouPinShareApi.a().d().a(ShareRecordConstant.b, "", "", i);
            this.c.f((View.OnClickListener) new View.OnClickListener() {
                public final void onClick(View view) {
                    SharePosterHelper.this.d(view);
                }
            });
            this.c.a((View.OnClickListener) new View.OnClickListener() {
                public final void onClick(View view) {
                    SharePosterHelper.this.c(view);
                }
            });
            this.c.b((View.OnClickListener) new View.OnClickListener() {
                public final void onClick(View view) {
                    SharePosterHelper.this.b(view);
                }
            });
            this.c.c((View.OnClickListener) new View.OnClickListener() {
                public final void onClick(View view) {
                    SharePosterHelper.this.a(view);
                }
            });
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void d(View view) {
            if (!this.b.d()) {
                this.c.g();
                YouPinShareApi.a().d().a(ShareRecordConstant.i, this.b.e(), (String) null);
                this.b.a(true);
                if (YouPinPermissionManager.a((Context) this.f23744a, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                    d();
                } else {
                    YouPinPermissionManager.a(this.f23744a, "android.permission.WRITE_EXTERNAL_STORAGE", (PermissionCallback) new PermissionCallback() {
                        public void a() {
                            SharePosterHelper.this.d();
                        }

                        public void a(boolean z) {
                            if (!z) {
                                YPDToast.getInstance().toast((Context) SharePosterHelper.this.f23744a, R.string.device_shop_share_failure);
                            }
                            SharePosterHelper.this.b.a(false);
                            SharePosterHelper.this.c.b();
                        }

                        public void b() {
                            YPDToast.getInstance().toast((Context) SharePosterHelper.this.f23744a, R.string.device_shop_share_failure);
                            SharePosterHelper.this.b.a(false);
                            SharePosterHelper.this.c.b();
                        }
                    });
                }
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void c(View view) {
            if (!this.b.d()) {
                YouPinShareApi.a().d().a(ShareRecordConstant.e, this.b.e(), (String) null);
                this.b.a(true);
                if (YouPinPermissionManager.a((Context) this.f23744a, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                    e();
                } else {
                    YouPinPermissionManager.a(this.f23744a, "android.permission.WRITE_EXTERNAL_STORAGE", (PermissionCallback) new PermissionCallback() {
                        public void a() {
                            SharePosterHelper.this.e();
                        }

                        public void a(boolean z) {
                            if (!z) {
                                YPDToast.getInstance().toast((Context) SharePosterHelper.this.f23744a, R.string.device_shop_share_failure);
                            }
                            SharePosterHelper.this.b.a(false);
                            SharePosterHelper.this.c.b();
                        }

                        public void b() {
                            YPDToast.getInstance().toast((Context) SharePosterHelper.this.f23744a, R.string.device_shop_share_failure);
                            SharePosterHelper.this.b.a(false);
                            SharePosterHelper.this.c.b();
                        }
                    });
                }
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(View view) {
            if (!this.b.d()) {
                YouPinShareApi.a().d().a(ShareRecordConstant.f, this.b.e(), (String) null);
                this.b.a(true);
                if (YouPinPermissionManager.a((Context) this.f23744a, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                    f();
                } else {
                    YouPinPermissionManager.a(this.f23744a, "android.permission.WRITE_EXTERNAL_STORAGE", (PermissionCallback) new PermissionCallback() {
                        public void a() {
                            SharePosterHelper.this.f();
                        }

                        public void a(boolean z) {
                            if (!z) {
                                YPDToast.getInstance().toast((Context) SharePosterHelper.this.f23744a, R.string.device_shop_share_failure);
                            }
                            SharePosterHelper.this.b.a(false);
                            SharePosterHelper.this.c.b();
                        }

                        public void b() {
                            YPDToast.getInstance().toast((Context) SharePosterHelper.this.f23744a, R.string.device_shop_share_failure);
                            SharePosterHelper.this.b.a(false);
                            SharePosterHelper.this.c.b();
                        }
                    });
                }
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(View view) {
            if (!this.b.d()) {
                YouPinShareApi.a().d().a("weibo", this.b.e(), (String) null);
                this.b.a(true);
                this.b.a("weibo");
                if (!ShareUtil.a((Context) this.f23744a)) {
                    YPDToast.getInstance().toast((Context) this.f23744a, R.string.device_shop_share_no_weibo);
                    this.b.a(false);
                } else if (YouPinPermissionManager.a((Context) this.f23744a, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                    g();
                } else {
                    YouPinPermissionManager.a(this.f23744a, "android.permission.WRITE_EXTERNAL_STORAGE", (PermissionCallback) new PermissionCallback() {
                        public void a() {
                            SharePosterHelper.this.g();
                        }

                        public void a(boolean z) {
                            if (!z) {
                                YPDToast.getInstance().toast((Context) SharePosterHelper.this.f23744a, R.string.device_shop_share_failure);
                            }
                            SharePosterHelper.this.b.a(false);
                            SharePosterHelper.this.c.b();
                        }

                        public void b() {
                            YPDToast.getInstance().toast((Context) SharePosterHelper.this.f23744a, R.string.device_shop_share_failure);
                            SharePosterHelper.this.b.a(false);
                            SharePosterHelper.this.c.b();
                        }
                    });
                }
            }
        }

        /* access modifiers changed from: private */
        public void d() {
            if (TextUtils.isEmpty(ShareUtil.a((Context) this.f23744a, this.f))) {
                YPDToast.getInstance().toast((Context) this.f23744a, "产品海报生成失败，请重试~");
                this.b.a(false);
                this.c.b();
                return;
            }
            YPDToast.getInstance().toast((Context) this.f23744a, "保存成功，快去分享吧");
            ShareEventUtil.a((Context) this.f23744a, "poster");
            this.b.a(false);
            this.c.i();
        }

        /* access modifiers changed from: private */
        public void e() {
            this.b.a(ShareChannel.f);
            String a2 = ShareUtil.a((Context) this.f23744a, this.f);
            if (TextUtils.isEmpty(a2)) {
                YPDToast.getInstance().toast((Context) this.f23744a, R.string.device_shop_share_failure);
                this.b.a(false);
                return;
            }
            Uri a3 = ShareUtil.a((Context) this.f23744a, Uri.parse(a2));
            ShareObject shareObject = new ShareObject();
            shareObject.k("pic");
            shareObject.b(a3);
            if (!ShareManager.b(this.f23744a, shareObject)) {
                this.b.a(false);
            }
        }

        /* access modifiers changed from: private */
        public void f() {
            this.b.a(ShareChannel.e);
            String a2 = ShareUtil.a((Context) this.f23744a, this.f);
            if (TextUtils.isEmpty(a2)) {
                YPDToast.getInstance().toast((Context) this.f23744a, R.string.device_shop_share_failure);
                this.b.a(false);
                return;
            }
            Uri a3 = ShareUtil.a((Context) this.f23744a, Uri.parse(a2));
            ShareObject shareObject = new ShareObject();
            shareObject.k("pic");
            shareObject.b(a3);
            if (!ShareManager.c(this.f23744a, shareObject)) {
                this.b.a(false);
            }
        }

        /* access modifiers changed from: private */
        public void g() {
            String a2 = ShareUtil.a((Context) this.f23744a, this.f);
            if (TextUtils.isEmpty(a2)) {
                YPDToast.getInstance().toast((Context) this.f23744a, R.string.device_shop_share_failure);
                this.b.a(false);
                return;
            }
            Uri a3 = ShareUtil.a((Context) this.f23744a, Uri.parse(a2));
            ShareObject shareObject = new ShareObject();
            shareObject.f(this.d);
            shareObject.g(this.e);
            shareObject.n(ShareObject.d);
            shareObject.b(a3);
            if (!ShareManager.a(this.f23744a, shareObject, true)) {
                this.b.a(false);
            }
        }
    }
