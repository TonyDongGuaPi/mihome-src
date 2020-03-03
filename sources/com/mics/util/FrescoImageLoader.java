package com.mics.util;

import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseArray;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.mics.R;

public class FrescoImageLoader {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public String f7777a;
    /* access modifiers changed from: private */
    public int b;
    /* access modifiers changed from: private */
    public ResizeOptions c;
    /* access modifiers changed from: private */
    public ScalingUtils.ScaleType d;
    /* access modifiers changed from: private */
    public DraweeView<GenericDraweeHierarchy> e;
    /* access modifiers changed from: private */
    public int f;
    /* access modifiers changed from: private */
    public int g;
    /* access modifiers changed from: private */
    public ScalingUtils.ScaleType h;
    /* access modifiers changed from: private */
    public int i;
    /* access modifiers changed from: private */
    public boolean j = false;
    /* access modifiers changed from: private */
    public boolean k = false;
    /* access modifiers changed from: private */
    public float l;
    /* access modifiers changed from: private */
    public float m;
    /* access modifiers changed from: private */
    public float n;
    /* access modifiers changed from: private */
    public float o;
    /* access modifiers changed from: private */
    public boolean p;
    /* access modifiers changed from: private */
    public ControllerListener q;
    private SparseArray<String> r = new SparseArray<>();

    public void a() {
        if (this.e != null) {
            if (TextUtils.isEmpty(this.f7777a)) {
                c();
                return;
            }
            String str = this.f7777a;
            if (!TextUtils.equals(str, this.r.get(this.e.getId()))) {
                this.r.put(this.e.getId(), str);
                ImageRequestBuilder progressiveRenderingEnabled = ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).setProgressiveRenderingEnabled(this.p);
                if (this.c != null) {
                    progressiveRenderingEnabled.setResizeOptions(this.c);
                }
                ImageRequest build = progressiveRenderingEnabled.build();
                if (Fresco.getDraweeControllerBuilderSupplier() != null) {
                    AbstractDraweeController build2 = ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setOldController(this.e.getController())).setImageRequest(build)).setControllerListener(this.q)).setTapToRetryEnabled(true)).build();
                    GenericDraweeHierarchy hierarchy = this.e.getHierarchy();
                    if (hierarchy == null) {
                        GenericDraweeHierarchyBuilder failureImageScaleType = new GenericDraweeHierarchyBuilder(this.e.getResources()).setFadeDuration(this.i).setActualImageScaleType(this.d).setPlaceholderImageScaleType(this.h).setFailureImageScaleType(this.h);
                        if (this.g != 0) {
                            failureImageScaleType.setPlaceholderImage(this.g);
                        }
                        if (this.f != 0) {
                            failureImageScaleType.setFailureImage(this.f);
                        }
                        if (this.j) {
                            failureImageScaleType.setRoundingParams(RoundingParams.asCircle());
                        } else {
                            failureImageScaleType.setRoundingParams(RoundingParams.fromCornersRadii(this.l, this.m, this.o, this.n));
                        }
                        this.e.setHierarchy(failureImageScaleType.build());
                    } else {
                        if (this.g != 0) {
                            hierarchy.setPlaceholderImage(this.g, this.h);
                        }
                        if (this.f != 0) {
                            hierarchy.setFailureImage(this.f, this.h);
                        }
                        RoundingParams roundingParams = hierarchy.getRoundingParams();
                        if (roundingParams != null) {
                            if (this.j) {
                                roundingParams.setRoundAsCircle(true);
                            } else {
                                roundingParams.setCornersRadii(this.l, this.m, this.o, this.n);
                            }
                        } else if (this.j) {
                            roundingParams = RoundingParams.asCircle();
                        } else if (this.k) {
                            roundingParams = RoundingParams.fromCornersRadii(this.l, this.m, this.o, this.n);
                        }
                        if (roundingParams != null) {
                            hierarchy.setRoundingParams(roundingParams);
                        }
                        hierarchy.setFadeDuration(this.i);
                        hierarchy.setActualImageScaleType(this.d);
                    }
                    this.e.setController(build2);
                }
            }
        }
    }

    public void b() {
        if (this.e != null) {
            this.r.put(this.e.getId(), (Object) null);
            a();
        }
    }

    private void c() {
        if (this.e != null && this.b != 0) {
            if (!TextUtils.equals("" + this.b, this.r.get(this.e.getId()))) {
                this.e.setImageResource(this.b);
                SparseArray<String> sparseArray = this.r;
                int id = this.e.getId();
                sparseArray.put(id, "" + this.b);
                this.e.getHierarchy().setFadeDuration(this.i);
                this.e.getHierarchy().setActualImageScaleType(this.d);
            }
        }
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        boolean f7778a = false;
        ControllerListener b;
        private String c;
        private int d = R.drawable.mics_placeholder_image;
        private DraweeView<GenericDraweeHierarchy> e;
        private ResizeOptions f;
        private ScalingUtils.ScaleType g = ScalingUtils.ScaleType.FIT_XY;
        private int h = R.drawable.mics_placeholder_image;
        private int i = this.h;
        private ScalingUtils.ScaleType j = ScalingUtils.ScaleType.CENTER_INSIDE;
        private int k = 0;
        private boolean l = false;
        private boolean m = false;
        private float n;
        private float o;
        private float p;
        private float q;

        public Builder a(DraweeView<GenericDraweeHierarchy> draweeView) {
            this.e = draweeView;
            return this;
        }

        public Builder a(String str) {
            this.c = str;
            return this;
        }

        public Builder a(int i2) {
            this.d = i2;
            return this;
        }

        public Builder a(ResizeOptions resizeOptions) {
            this.f = resizeOptions;
            return this;
        }

        public Builder a(ScalingUtils.ScaleType scaleType) {
            this.g = scaleType;
            return this;
        }

        public Builder b(int i2) {
            this.h = i2;
            return this;
        }

        public Builder b(ScalingUtils.ScaleType scaleType) {
            this.j = scaleType;
            return this;
        }

        public Builder c(int i2) {
            this.i = i2;
            return this;
        }

        public Builder d(int i2) {
            this.k = i2;
            return this;
        }

        public Builder a(boolean z) {
            this.l = z;
            return this;
        }

        public Builder a(float f2) {
            return a(f2, f2, f2, f2);
        }

        public Builder a(float f2, float f3, float f4, float f5) {
            this.m = true;
            this.n = f2;
            this.o = f3;
            this.p = f4;
            this.q = f5;
            return this;
        }

        public Builder b(boolean z) {
            this.f7778a = z;
            return this;
        }

        public Builder a(ControllerListener controllerListener) {
            this.b = controllerListener;
            return this;
        }

        public FrescoImageLoader a() {
            FrescoImageLoader frescoImageLoader = new FrescoImageLoader();
            DraweeView unused = frescoImageLoader.e = this.e;
            String unused2 = frescoImageLoader.f7777a = this.c;
            int unused3 = frescoImageLoader.b = this.d;
            ResizeOptions unused4 = frescoImageLoader.c = this.f;
            ScalingUtils.ScaleType unused5 = frescoImageLoader.d = this.g;
            int unused6 = frescoImageLoader.g = this.h;
            ScalingUtils.ScaleType unused7 = frescoImageLoader.h = this.j;
            int unused8 = frescoImageLoader.f = this.i;
            int unused9 = frescoImageLoader.i = this.k;
            boolean unused10 = frescoImageLoader.j = this.l;
            boolean unused11 = frescoImageLoader.k = this.m;
            float unused12 = frescoImageLoader.l = this.n;
            float unused13 = frescoImageLoader.m = this.o;
            float unused14 = frescoImageLoader.n = this.p;
            float unused15 = frescoImageLoader.o = this.q;
            boolean unused16 = frescoImageLoader.p = this.f7778a;
            ControllerListener unused17 = frescoImageLoader.q = this.b;
            return frescoImageLoader;
        }
    }
}
