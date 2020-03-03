package com.xiaomi.youpin.utils;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class FrescoUtils {

    public interface OnImageLoadedCallback {
        void a(ImageInfo imageInfo);
    }

    public static void a(SimpleDraweeView simpleDraweeView, int i) {
        if (simpleDraweeView != null && i != 0) {
            if (!TextUtils.equals("" + i, (String) simpleDraweeView.getTag(simpleDraweeView.getId()))) {
                simpleDraweeView.setImageResource(i);
                int id = simpleDraweeView.getId();
                simpleDraweeView.setTag(id, "" + i);
                ((GenericDraweeHierarchy) simpleDraweeView.getHierarchy()).setFadeDuration(0);
                ((GenericDraweeHierarchy) simpleDraweeView.getHierarchy()).setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
            }
        }
    }

    public static void a(SimpleDraweeView simpleDraweeView, String str, ResizeOptions resizeOptions) {
        a(simpleDraweeView, str, resizeOptions, (OnImageLoadedCallback) null);
    }

    public static String a(String str) {
        if (!str.contains("/app/shop/img?id=shop_")) {
            return str;
        }
        return str + "&t=webp";
    }

    public static void a(final SimpleDraweeView simpleDraweeView, final String str, ResizeOptions resizeOptions, final OnImageLoadedCallback onImageLoadedCallback) {
        if (simpleDraweeView != null && !TextUtils.isEmpty(str) && !TextUtils.equals(str, (String) simpleDraweeView.getTag(simpleDraweeView.getId()))) {
            ImageRequestBuilder progressiveRenderingEnabled = ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).setProgressiveRenderingEnabled(true);
            if (resizeOptions != null) {
                progressiveRenderingEnabled.setResizeOptions(resizeOptions);
            }
            ImageRequest build = progressiveRenderingEnabled.build();
            simpleDraweeView.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setOldController(simpleDraweeView.getController())).setImageRequest(build)).setControllerListener(new BaseControllerListener() {
                public void onFinalImageSet(String str, Object obj, Animatable animatable) {
                    simpleDraweeView.setTag(simpleDraweeView.getId(), str);
                    if (onImageLoadedCallback != null && (obj instanceof ImageInfo)) {
                        onImageLoadedCallback.a((ImageInfo) obj);
                    }
                }
            })).build());
            ((GenericDraweeHierarchy) simpleDraweeView.getHierarchy()).setFadeDuration(0);
            ((GenericDraweeHierarchy) simpleDraweeView.getHierarchy()).setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
        }
    }
}
