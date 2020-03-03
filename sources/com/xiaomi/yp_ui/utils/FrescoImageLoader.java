package com.xiaomi.yp_ui.utils;

import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
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
import com.xiaomi.yp_ui.R;

public class FrescoImageLoader {

    /* renamed from: a  reason: collision with root package name */
    DraweeView<GenericDraweeHierarchy> f1596a;
    String b;
    int c;
    ResizeOptions d;
    ScalingUtils.ScaleType e;
    int f;
    ScalingUtils.ScaleType g;
    int h;
    int i;
    boolean j = false;
    boolean k = false;
    float l;
    float m;
    float n;
    float o;
    boolean p;
    ControllerListener q;

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        DraweeView<GenericDraweeHierarchy> f1597a;
        String b;
        int c = R.drawable.yp_default_placeholder;
        ResizeOptions d;
        ScalingUtils.ScaleType e = ScalingUtils.ScaleType.FIT_XY;
        int f = R.drawable.yp_default_placeholder;
        ScalingUtils.ScaleType g = ScalingUtils.ScaleType.CENTER_INSIDE;
        int h = this.f;
        int i = 0;
        boolean j = false;
        boolean k = false;
        float l;
        float m;
        float n;
        float o;
        boolean p = false;
        ControllerListener q;

        public Builder a(DraweeView<GenericDraweeHierarchy> draweeView) {
            this.f1597a = draweeView;
            return this;
        }

        public Builder a(String str) {
            this.b = str;
            return this;
        }

        public Builder a(int i2) {
            this.c = i2;
            return this;
        }

        public Builder a(ResizeOptions resizeOptions) {
            this.d = resizeOptions;
            return this;
        }

        public Builder a(ScalingUtils.ScaleType scaleType) {
            this.e = scaleType;
            return this;
        }

        public Builder b(int i2) {
            this.f = i2;
            return this;
        }

        public Builder b(ScalingUtils.ScaleType scaleType) {
            this.g = scaleType;
            return this;
        }

        public Builder c(int i2) {
            this.h = i2;
            return this;
        }

        public Builder d(int i2) {
            this.i = i2;
            return this;
        }

        public Builder a(boolean z) {
            this.j = z;
            return this;
        }

        public Builder a(float f2) {
            return a(f2, f2, f2, f2);
        }

        public Builder a(float f2, float f3, float f4, float f5) {
            this.k = true;
            this.l = f2;
            this.m = f3;
            this.n = f4;
            this.o = f5;
            return this;
        }

        public Builder b(boolean z) {
            this.p = z;
            return this;
        }

        public Builder a(ControllerListener controllerListener) {
            this.q = controllerListener;
            return this;
        }

        public FrescoImageLoader a() {
            FrescoImageLoader frescoImageLoader = new FrescoImageLoader();
            frescoImageLoader.f1596a = this.f1597a;
            frescoImageLoader.b = this.b;
            frescoImageLoader.c = this.c;
            frescoImageLoader.d = this.d;
            frescoImageLoader.e = this.e;
            frescoImageLoader.f = this.f;
            frescoImageLoader.g = this.g;
            frescoImageLoader.h = this.h;
            frescoImageLoader.i = this.i;
            frescoImageLoader.j = this.j;
            frescoImageLoader.k = this.k;
            frescoImageLoader.l = this.l;
            frescoImageLoader.m = this.m;
            frescoImageLoader.n = this.n;
            frescoImageLoader.o = this.o;
            frescoImageLoader.p = this.p;
            frescoImageLoader.q = this.q;
            return frescoImageLoader;
        }
    }

    public void a() {
        if (this.f1596a != null) {
            if (TextUtils.isEmpty(this.b)) {
                c();
                return;
            }
            if (this.d != null) {
                int i2 = this.d.width;
                int i3 = this.d.height;
            }
            String a2 = FrescoUtils.a(this.b);
            if (!TextUtils.equals(a2, (String) this.f1596a.getTag(a(this.f1596a)))) {
                this.f1596a.setTag(a(this.f1596a), a2);
                ImageRequestBuilder progressiveRenderingEnabled = ImageRequestBuilder.newBuilderWithSource(Uri.parse(a2)).setProgressiveRenderingEnabled(this.p);
                if (this.d != null) {
                    progressiveRenderingEnabled.setResizeOptions(this.d);
                }
                ImageRequest build = progressiveRenderingEnabled.build();
                if (Fresco.getDraweeControllerBuilderSupplier() != null) {
                    AbstractDraweeController build2 = ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setOldController(this.f1596a.getController())).setImageRequest(build)).setControllerListener(this.q)).setTapToRetryEnabled(true)).setAutoPlayAnimations(true)).build();
                    GenericDraweeHierarchy hierarchy = this.f1596a.getHierarchy();
                    if (hierarchy == null) {
                        GenericDraweeHierarchyBuilder failureImageScaleType = new GenericDraweeHierarchyBuilder(this.f1596a.getResources()).setFadeDuration(this.i).setActualImageScaleType(this.e).setPlaceholderImageScaleType(this.g).setFailureImageScaleType(this.g);
                        if (this.f != 0) {
                            failureImageScaleType.setPlaceholderImage(this.f);
                        }
                        if (this.h != 0) {
                            failureImageScaleType.setFailureImage(this.h);
                        }
                        if (this.j) {
                            failureImageScaleType.setRoundingParams(RoundingParams.asCircle());
                        } else {
                            failureImageScaleType.setRoundingParams(RoundingParams.fromCornersRadii(this.l, this.m, this.o, this.n));
                        }
                        this.f1596a.setHierarchy(failureImageScaleType.build());
                    } else {
                        if (this.f != 0) {
                            hierarchy.setPlaceholderImage(this.f, this.g);
                        }
                        if (this.h != 0) {
                            hierarchy.setFailureImage(this.h, this.g);
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
                        hierarchy.setActualImageScaleType(this.e);
                    }
                    this.f1596a.setController(build2);
                }
            }
        }
    }

    private int a(View view) {
        return ("url" + view.getId()).hashCode();
    }

    public void b() {
        if (this.f1596a != null) {
            this.f1596a.setTag(a(this.f1596a), (Object) null);
            a();
        }
    }

    private void c() {
        if (this.f1596a != null && this.c != 0) {
            if (!TextUtils.equals("" + this.c, (String) this.f1596a.getTag(a(this.f1596a)))) {
                this.f1596a.setImageResource(this.c);
                DraweeView<GenericDraweeHierarchy> draweeView = this.f1596a;
                int a2 = a(this.f1596a);
                draweeView.setTag(a2, "" + this.c);
                this.f1596a.getHierarchy().setFadeDuration(this.i);
                this.f1596a.getHierarchy().setActualImageScaleType(this.e);
            }
        }
    }
}
