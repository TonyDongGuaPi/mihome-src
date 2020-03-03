package com.xiaomi.jr.feature.photo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.xiaomi.jr.capturephoto.CapturePhotoActivity;
import com.xiaomi.jr.common.app.PhotoManager;
import com.xiaomi.jr.common.utils.BitmapUtils;
import com.xiaomi.jr.common.utils.Constants;
import com.xiaomi.jr.common.utils.FileUtils;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.feature.photo.Photo;
import com.xiaomi.jr.http.MifiHttpManager;
import com.xiaomi.jr.http.RequestListener;
import com.xiaomi.jr.hybrid.FeatureUtil;
import com.xiaomi.jr.hybrid.HybridException;
import com.xiaomi.jr.hybrid.HybridFeature;
import com.xiaomi.jr.hybrid.HybridUtils;
import com.xiaomi.jr.hybrid.NativeInterface;
import com.xiaomi.jr.hybrid.Request;
import com.xiaomi.jr.hybrid.Response;
import com.xiaomi.jr.hybrid.annotation.Action;
import com.xiaomi.jr.hybrid.annotation.Feature;
import com.xiaomi.jr.permission.PermissionManager;
import com.xiaomi.jr.permission.Request;
import com.xiaomi.youpin.share.ShareObject;
import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

@Feature("Photo")
public class Photo extends HybridFeature {
    private static int MODE_RETURN_BASE64 = 1;
    private static int MODE_RETURN_URL;

    private static class CapturePhotoParam {
        @SerializedName("isFrontCamera")

        /* renamed from: a  reason: collision with root package name */
        boolean f10413a;
        @SerializedName("photoName")
        String b;
        @SerializedName("width")
        int c;
        @SerializedName("height")
        int d;
        @SerializedName("returnMode")
        int e;
        @SerializedName("maskName")
        String f;
        @SerializedName("showResult")
        boolean g;
        @SerializedName("title")
        String h;
        @SerializedName("description")
        String i;
        @SerializedName("enableSwitchFrontBackCamera")
        boolean j;

        private CapturePhotoParam() {
        }
    }

    private static class PickPhotoParam {
        @SerializedName("photoName")

        /* renamed from: a  reason: collision with root package name */
        String f10414a;
        @SerializedName("width")
        int b;
        @SerializedName("height")
        int c;
        @SerializedName("returnMode")
        int d;

        private PickPhotoParam() {
        }
    }

    private static class UploadPhotoParam {
        @SerializedName("url")

        /* renamed from: a  reason: collision with root package name */
        String f10415a;
        @SerializedName("fields")
        Map<String, String> b = new HashMap();
        @SerializedName("photos")
        Map<String, PhotoPart> c = new HashMap();

        static class PhotoPart {
            @SerializedName("photoName")

            /* renamed from: a  reason: collision with root package name */
            String f10416a;
            @SerializedName("contentType")
            String b;

            PhotoPart() {
            }
        }

        private UploadPhotoParam() {
        }
    }

    private static class UploadPhotoResult {
        @SerializedName("response")

        /* renamed from: a  reason: collision with root package name */
        public String f10417a;

        private UploadPhotoResult() {
        }
    }

    private static class UploadProgress {
        @SerializedName("progress")

        /* renamed from: a  reason: collision with root package name */
        public int f10418a;

        private UploadProgress() {
        }
    }

    @Action(paramClazz = CapturePhotoParam.class)
    public Response capturePhoto(final Request<CapturePhotoParam> request) {
        PermissionManager.a(HybridUtils.a((Request) request), "android.permission.CAMERA", (Request.Callback) new Request.Callback() {
            public /* synthetic */ void b() {
                Request.Callback.CC.$default$b(this);
            }

            public void a() {
                Photo.this.performCapturePhoto(request);
            }

            public void a(String str) {
                HybridUtils.a(request, (Response) new Response.PermissionDeniedResponse(str));
            }
        });
        return Response.j;
    }

    /* access modifiers changed from: private */
    public void performCapturePhoto(final com.xiaomi.jr.hybrid.Request<CapturePhotoParam> request) {
        RectF rectF;
        Intent intent = new Intent(request.a().d(), CapturePhotoActivity.class);
        intent.putExtra(CapturePhotoActivity.KEY_IS_FRONT_CAMERA, request.c().f10413a);
        intent.putExtra(CapturePhotoActivity.KEY_PHOTO_NAME, request.c().b);
        intent.putExtra(CapturePhotoActivity.KEY_PHOTO_WIDTH, request.c().c);
        intent.putExtra(CapturePhotoActivity.KEY_PHOTO_HEIGHT, request.c().d);
        intent.putExtra(CapturePhotoActivity.KEY_SHOW_RESULT, request.c().g);
        intent.putExtra("title", request.c().h);
        intent.putExtra(CapturePhotoActivity.KEY_ENABLE_SWITCH_FRONT_BACK_CAMERA, request.c().j);
        String str = request.c().f;
        if (!TextUtils.isEmpty(str)) {
            Context a2 = HybridUtils.a((com.xiaomi.jr.hybrid.Request) request);
            intent.putExtra(CapturePhotoActivity.KEY_MASK_RESOURCE_ID, a2.getResources().getIdentifier(str, "layout", a2.getPackageName()));
            int identifier = a2.getResources().getIdentifier(str + "_preview_rect", ShareConstants.V, a2.getPackageName());
            if (identifier != 0) {
                InputStream openRawResource = a2.getResources().openRawResource(identifier);
                try {
                    rectF = (RectF) new Gson().fromJson((Reader) new InputStreamReader(openRawResource), RectF.class);
                } catch (JsonParseException unused) {
                    rectF = null;
                }
                Utils.a((Closeable) openRawResource);
                if (rectF != null) {
                    intent.putExtra(CapturePhotoActivity.KEY_MASK_PREVIEW_RECT, rectF);
                }
            }
            intent.putExtra("description", request.c().i);
        }
        HybridUtils.a(request, 24, intent, new NativeInterface.Callback() {
            public void a(Object... objArr) {
                super.a(objArr);
                int intValue = objArr[0].intValue();
                Intent intent = objArr[1];
                Response response = new Response(200, (String) null);
                if (intValue != -1 || intent == null) {
                    response.a("cancel capture");
                } else {
                    String stringExtra = intent.getStringExtra(CapturePhotoActivity.KEY_PHOTO_NAME);
                    if (stringExtra != null) {
                        response.a((Object) Photo.this.buildContent(HybridUtils.a(request), stringExtra, ((CapturePhotoParam) request.c()).e));
                    }
                }
                HybridUtils.a(request, response);
            }
        });
    }

    @Action(paramClazz = PickPhotoParam.class)
    public Response pickPhoto(final com.xiaomi.jr.hybrid.Request<PickPhotoParam> request) {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType(ShareObject.d);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        HybridUtils.a(request, 24, intent, new NativeInterface.Callback() {
            public void a(Object... objArr) {
                super.a(objArr);
                int intValue = objArr[0].intValue();
                Intent intent = objArr[1];
                final Response response = new Response(200, (String) null);
                if (intValue != -1 || intent == null || intent.getData() == null) {
                    response.a("cancel pick");
                } else {
                    final String access$200 = Photo.getFilePathFromImageUri(HybridUtils.a(request), intent.getData());
                    if (!TextUtils.isEmpty(access$200)) {
                        PermissionManager.a(HybridUtils.a(request), "android.permission.READ_EXTERNAL_STORAGE", (Request.Callback) new Request.Callback() {
                            public /* synthetic */ void b() {
                                Request.Callback.CC.$default$b(this);
                            }

                            public void a() {
                                HybridUtils.a((Runnable) 
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000d: INVOKE  
                                      (wrap: com.xiaomi.jr.feature.photo.-$$Lambda$Photo$3$1$Hdr2ZLvdVFNWC_8NVS0xTovW6D8 : 0x000a: CONSTRUCTOR  (r3v0 com.xiaomi.jr.feature.photo.-$$Lambda$Photo$3$1$Hdr2ZLvdVFNWC_8NVS0xTovW6D8) = 
                                      (r4v0 'this' com.xiaomi.jr.feature.photo.Photo$3$1 A[THIS])
                                      (wrap: com.xiaomi.jr.hybrid.Request : 0x0002: IGET  (r0v1 com.xiaomi.jr.hybrid.Request) = 
                                      (wrap: com.xiaomi.jr.feature.photo.Photo$3 : 0x0000: IGET  (r0v0 com.xiaomi.jr.feature.photo.Photo$3) = (r4v0 'this' com.xiaomi.jr.feature.photo.Photo$3$1 A[THIS]) com.xiaomi.jr.feature.photo.Photo.3.1.c com.xiaomi.jr.feature.photo.Photo$3)
                                     com.xiaomi.jr.feature.photo.Photo.3.a com.xiaomi.jr.hybrid.Request)
                                      (wrap: java.lang.String : 0x0004: IGET  (r1v0 java.lang.String) = (r4v0 'this' com.xiaomi.jr.feature.photo.Photo$3$1 A[THIS]) com.xiaomi.jr.feature.photo.Photo.3.1.a java.lang.String)
                                      (wrap: com.xiaomi.jr.hybrid.Response : 0x0006: IGET  (r2v0 com.xiaomi.jr.hybrid.Response) = (r4v0 'this' com.xiaomi.jr.feature.photo.Photo$3$1 A[THIS]) com.xiaomi.jr.feature.photo.Photo.3.1.b com.xiaomi.jr.hybrid.Response)
                                     call: com.xiaomi.jr.feature.photo.-$$Lambda$Photo$3$1$Hdr2ZLvdVFNWC_8NVS0xTovW6D8.<init>(com.xiaomi.jr.feature.photo.Photo$3$1, com.xiaomi.jr.hybrid.Request, java.lang.String, com.xiaomi.jr.hybrid.Response):void type: CONSTRUCTOR)
                                     com.xiaomi.jr.hybrid.HybridUtils.a(java.lang.Runnable):void type: STATIC in method: com.xiaomi.jr.feature.photo.Photo.3.1.a():void, dex: classes5.dex
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
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:156)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
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
                                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000a: CONSTRUCTOR  (r3v0 com.xiaomi.jr.feature.photo.-$$Lambda$Photo$3$1$Hdr2ZLvdVFNWC_8NVS0xTovW6D8) = 
                                      (r4v0 'this' com.xiaomi.jr.feature.photo.Photo$3$1 A[THIS])
                                      (wrap: com.xiaomi.jr.hybrid.Request : 0x0002: IGET  (r0v1 com.xiaomi.jr.hybrid.Request) = 
                                      (wrap: com.xiaomi.jr.feature.photo.Photo$3 : 0x0000: IGET  (r0v0 com.xiaomi.jr.feature.photo.Photo$3) = (r4v0 'this' com.xiaomi.jr.feature.photo.Photo$3$1 A[THIS]) com.xiaomi.jr.feature.photo.Photo.3.1.c com.xiaomi.jr.feature.photo.Photo$3)
                                     com.xiaomi.jr.feature.photo.Photo.3.a com.xiaomi.jr.hybrid.Request)
                                      (wrap: java.lang.String : 0x0004: IGET  (r1v0 java.lang.String) = (r4v0 'this' com.xiaomi.jr.feature.photo.Photo$3$1 A[THIS]) com.xiaomi.jr.feature.photo.Photo.3.1.a java.lang.String)
                                      (wrap: com.xiaomi.jr.hybrid.Response : 0x0006: IGET  (r2v0 com.xiaomi.jr.hybrid.Response) = (r4v0 'this' com.xiaomi.jr.feature.photo.Photo$3$1 A[THIS]) com.xiaomi.jr.feature.photo.Photo.3.1.b com.xiaomi.jr.hybrid.Response)
                                     call: com.xiaomi.jr.feature.photo.-$$Lambda$Photo$3$1$Hdr2ZLvdVFNWC_8NVS0xTovW6D8.<init>(com.xiaomi.jr.feature.photo.Photo$3$1, com.xiaomi.jr.hybrid.Request, java.lang.String, com.xiaomi.jr.hybrid.Response):void type: CONSTRUCTOR in method: com.xiaomi.jr.feature.photo.Photo.3.1.a():void, dex: classes5.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                    	... 105 more
                                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.xiaomi.jr.feature.photo.-$$Lambda$Photo$3$1$Hdr2ZLvdVFNWC_8NVS0xTovW6D8, state: NOT_LOADED
                                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                    	... 111 more
                                    */
                                /*
                                    this = this;
                                    com.xiaomi.jr.feature.photo.Photo$3 r0 = com.xiaomi.jr.feature.photo.Photo.AnonymousClass3.this
                                    com.xiaomi.jr.hybrid.Request r0 = r4
                                    java.lang.String r1 = r5
                                    com.xiaomi.jr.hybrid.Response r2 = r1
                                    com.xiaomi.jr.feature.photo.-$$Lambda$Photo$3$1$Hdr2ZLvdVFNWC_8NVS0xTovW6D8 r3 = new com.xiaomi.jr.feature.photo.-$$Lambda$Photo$3$1$Hdr2ZLvdVFNWC_8NVS0xTovW6D8
                                    r3.<init>(r4, r0, r1, r2)
                                    com.xiaomi.jr.hybrid.HybridUtils.a((java.lang.Runnable) r3)
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.feature.photo.Photo.AnonymousClass3.AnonymousClass1.a():void");
                            }

                            /* access modifiers changed from: private */
                            public /* synthetic */ void a(com.xiaomi.jr.hybrid.Request request, String str, Response response) {
                                int i = ((PickPhotoParam) request.c()).b;
                                int i2 = ((PickPhotoParam) request.c()).c;
                                if (i == 0 && i2 == 0) {
                                    i = Utils.e(HybridUtils.a(request)).y;
                                }
                                Bitmap b2 = com.xiaomi.jr.feature.photo.utils.Utils.b(i, i2, str);
                                if (b2 != null) {
                                    PhotoManager.b(((PickPhotoParam) request.c()).f10414a, b2);
                                    response.a((Object) Photo.this.buildContent(HybridUtils.a(request), ((PickPhotoParam) request.c()).f10414a, ((PickPhotoParam) request.c()).d));
                                } else {
                                    response.a("scale picked file fail");
                                }
                                HybridUtils.a(request, response);
                            }

                            public void a(String str) {
                                HybridUtils.a(request, (Response) new Response.PermissionDeniedResponse(str));
                            }
                        });
                        return;
                    }
                    response.a("pick file path not found");
                }
                HybridUtils.a(request, response);
            }
        });
        return Response.j;
    }

    @Action(paramClazz = String.class)
    public Response savePhoto(final com.xiaomi.jr.hybrid.Request<String> request) {
        final String c = request.c();
        PermissionManager.a(HybridUtils.a((com.xiaomi.jr.hybrid.Request) request), "android.permission.WRITE_EXTERNAL_STORAGE", (Request.Callback) new Request.Callback() {
            public /* synthetic */ void b() {
                Request.Callback.CC.$default$b(this);
            }

            public void a() {
                HybridUtils.a((Runnable) new Runnable(c, request) {
                    private final /* synthetic */ String f$0;
                    private final /* synthetic */ com.xiaomi.jr.hybrid.Request f$1;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                    }

                    public final void run() {
                        Photo.AnonymousClass4.a(this.f$0, this.f$1);
                    }
                });
            }

            /* access modifiers changed from: private */
            public static /* synthetic */ void a(String str, com.xiaomi.jr.hybrid.Request request) {
                Bitmap a2 = BitmapUtils.a(str);
                if (a2 == null) {
                    HybridUtils.a(request, (Response) new Response.RuntimeErrorResponse(request, "fail to decode to bitmap. bitmap = null"));
                    return;
                }
                String str2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + File.separator + System.currentTimeMillis() + ".jpg";
                BitmapUtils.a(a2, str2);
                HybridUtils.a(request).sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(str2))));
                HybridUtils.a(request, Response.j);
            }

            public void a(String str) {
                HybridUtils.a(request, (Response) new Response.PermissionDeniedResponse(str));
            }
        });
        return Response.j;
    }

    /* access modifiers changed from: private */
    public String buildContent(Context context, String str, int i) {
        if (i == MODE_RETURN_BASE64) {
            return BitmapUtils.b(PhotoManager.b(str));
        }
        return "localresource://photo" + getPhotoFilePath(context, str) + "?t=" + System.currentTimeMillis();
    }

    private static String getPhotoFilePath(Context context, String str) {
        return FileUtils.a(context, Constants.k + File.separator + str);
    }

    @Action(mode = FeatureUtil.Mode.ASYNC, paramClazz = UploadPhotoParam.class)
    public Response uploadPhoto(com.xiaomi.jr.hybrid.Request<UploadPhotoParam> request) {
        String str;
        try {
            List<MultipartBody.Part> generateFileParts = generateFileParts(request.c());
            int i = 200;
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            for (Map.Entry next : request.c().b.entrySet()) {
                builder.addFormDataPart((String) next.getKey(), (String) next.getValue());
            }
            for (MultipartBody.Part addPart : generateFileParts) {
                builder.addPart(addPart);
            }
            UploadPhotoResult uploadPhotoResult = null;
            try {
                okhttp3.Response execute = MifiHttpManager.a().a().newCall(new Request.Builder().url(request.c().f10415a).post(builder.build()).tag(new Object[]{new RequestListener() {
                    public final void onRequest(long j, long j2, boolean z) {
                        Photo.lambda$uploadPhoto$0(com.xiaomi.jr.hybrid.Request.this, j, j2, z);
                    }
                }}).build()).execute();
                if (execute.isSuccessful()) {
                    try {
                        UploadPhotoResult uploadPhotoResult2 = new UploadPhotoResult();
                        try {
                            uploadPhotoResult2.f10417a = execute.body().string();
                            str = null;
                            uploadPhotoResult = uploadPhotoResult2;
                            i = 0;
                        } catch (Exception e) {
                            e = e;
                            uploadPhotoResult = uploadPhotoResult2;
                            i = 0;
                            str = e.getMessage();
                            return new Response(i, uploadPhotoResult, str);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        i = 0;
                        str = e.getMessage();
                        return new Response(i, uploadPhotoResult, str);
                    }
                } else {
                    str = "http code=" + execute.code();
                }
            } catch (Exception e3) {
                e = e3;
                str = e.getMessage();
                return new Response(i, uploadPhotoResult, str);
            }
            return new Response(i, uploadPhotoResult, str);
        } catch (HybridException e4) {
            return e4.getResponse();
        }
    }

    static /* synthetic */ void lambda$uploadPhoto$0(com.xiaomi.jr.hybrid.Request request, long j, long j2, boolean z) {
        UploadProgress uploadProgress = new UploadProgress();
        uploadProgress.f10418a = (int) (((float) j) / ((float) j2));
        HybridUtils.a(request, new Response(uploadProgress));
    }

    public void cleanup() {
        PhotoManager.a();
    }

    private List<MultipartBody.Part> generateFileParts(UploadPhotoParam uploadPhotoParam) throws HybridException {
        ArrayList arrayList = new ArrayList();
        for (String next : uploadPhotoParam.c.keySet()) {
            UploadPhotoParam.PhotoPart photoPart = uploadPhotoParam.c.get(next);
            if (!TextUtils.isEmpty(photoPart.f10416a)) {
                Bitmap b = PhotoManager.b(photoPart.f10416a);
                if (b == null || b.isRecycled()) {
                    throw new HybridException(photoPart.f10416a + " not available");
                }
                arrayList.add(MultipartBody.Part.createFormData(next, photoPart.f10416a, RequestBody.create(MediaType.parse(photoPart.b != null ? photoPart.b : "application/octet-stream"), BitmapUtils.a(b))));
            } else {
                throw new HybridException("photoName is empty");
            }
        }
        return arrayList;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        if (r9 != null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r9.close();
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003b, code lost:
        if (r9 != null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r1 = r1;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0036 A[SYNTHETIC, Splitter:B:16:0x0036] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getFilePathFromImageUri(android.content.Context r9, android.net.Uri r10) {
        /*
            r0 = 1
            r1 = 0
            java.lang.String[] r0 = new java.lang.String[r0]     // Catch:{ Exception -> 0x003a, all -> 0x0033 }
            java.lang.String r2 = "_data"
            r8 = 0
            r0[r8] = r2     // Catch:{ Exception -> 0x003a, all -> 0x0033 }
            android.content.ContentResolver r2 = r9.getContentResolver()     // Catch:{ Exception -> 0x003a, all -> 0x0033 }
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r10
            r4 = r0
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x003a, all -> 0x0033 }
            if (r9 == 0) goto L_0x002d
            boolean r10 = r9.moveToFirst()     // Catch:{ Exception -> 0x003b, all -> 0x002a }
            if (r10 == 0) goto L_0x002d
            r10 = r0[r8]     // Catch:{ Exception -> 0x003b, all -> 0x002a }
            int r10 = r9.getColumnIndex(r10)     // Catch:{ Exception -> 0x003b, all -> 0x002a }
            java.lang.String r10 = r9.getString(r10)     // Catch:{ Exception -> 0x003b, all -> 0x002a }
            r1 = r10
            goto L_0x002d
        L_0x002a:
            r10 = move-exception
            r1 = r9
            goto L_0x0034
        L_0x002d:
            if (r9 == 0) goto L_0x003e
        L_0x002f:
            r9.close()     // Catch:{ Exception -> 0x003e }
            goto L_0x003e
        L_0x0033:
            r10 = move-exception
        L_0x0034:
            if (r1 == 0) goto L_0x0039
            r1.close()     // Catch:{ Exception -> 0x0039 }
        L_0x0039:
            throw r10
        L_0x003a:
            r9 = r1
        L_0x003b:
            if (r9 == 0) goto L_0x003e
            goto L_0x002f
        L_0x003e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.feature.photo.Photo.getFilePathFromImageUri(android.content.Context, android.net.Uri):java.lang.String");
    }
}
