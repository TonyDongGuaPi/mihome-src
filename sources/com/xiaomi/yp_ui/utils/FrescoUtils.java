package com.xiaomi.yp_ui.utils;

import android.os.Build;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.xiaomi.yp_ui.utils.FrescoImageLoader;

public class FrescoUtils {

    public interface OnImageLoadedCallback {
        void a(ImageInfo imageInfo);
    }

    @Deprecated
    public static void a(SimpleDraweeView simpleDraweeView, int i) {
        new FrescoImageLoader.Builder().a((DraweeView<GenericDraweeHierarchy>) simpleDraweeView).a(i).a().a();
    }

    @Deprecated
    public static void a(SimpleDraweeView simpleDraweeView, String str, int i) {
        new FrescoImageLoader.Builder().a((DraweeView<GenericDraweeHierarchy>) simpleDraweeView).a(str).b(i).a().a();
    }

    @Deprecated
    public static void a(DraweeView<GenericDraweeHierarchy> draweeView, String str, int i) {
        new FrescoImageLoader.Builder().a(draweeView).a(str).a(ScalingUtils.ScaleType.CENTER_INSIDE).b(i).a().a();
    }

    @Deprecated
    public static void a(SimpleDraweeView simpleDraweeView, String str, ResizeOptions resizeOptions) {
        new FrescoImageLoader.Builder().a((DraweeView<GenericDraweeHierarchy>) simpleDraweeView).a(str).a(resizeOptions).a().a();
    }

    @Deprecated
    public static void a(SimpleDraweeView simpleDraweeView, String str, ResizeOptions resizeOptions, int i) {
        new FrescoImageLoader.Builder().a((DraweeView<GenericDraweeHierarchy>) simpleDraweeView).a(str).a(resizeOptions).a(ScalingUtils.ScaleType.CENTER_CROP).b(i).a().a();
    }

    public static String a(String str) {
        if (Build.VERSION.SDK_INT <= 18 || str == null || !str.contains("/app/shop/img?id=shop_") || str.contains("t=")) {
            return str;
        }
        return str + "&t=webp";
    }

    @Deprecated
    public static void a(SimpleDraweeView simpleDraweeView, String str, ScalingUtils.ScaleType scaleType, ResizeOptions resizeOptions) {
        new FrescoImageLoader.Builder().a((DraweeView<GenericDraweeHierarchy>) simpleDraweeView).a(str).a(scaleType).a(resizeOptions).a().a();
    }

    @Deprecated
    public static void a(SimpleDraweeView simpleDraweeView, String str, ScalingUtils.ScaleType scaleType, ResizeOptions resizeOptions, int i) {
        new FrescoImageLoader.Builder().a((DraweeView<GenericDraweeHierarchy>) simpleDraweeView).a(str).a(scaleType).a(resizeOptions).b(i).a().a();
    }
}
