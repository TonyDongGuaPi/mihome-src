package com.youpin.weex.app.extend;

import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;
import com.xiaomi.youpin.common.util.ImageUtils;
import com.xiaomi.youpin.log.LogUtils;
import java.util.Map;

public class FrescoImageAdapter implements IWXImgLoaderAdapter {
    public void setImage(final String str, final ImageView imageView, WXImageQuality wXImageQuality, final WXImageStrategy wXImageStrategy) {
        WXSDKManager.getInstance().postOnUiThread(new Runnable() {
            public void run() {
                Bitmap e;
                if (imageView != null && imageView.getLayoutParams() != null) {
                    if (TextUtils.isEmpty(str)) {
                        imageView.setImageBitmap((Bitmap) null);
                        return;
                    }
                    String str = str;
                    if (str.startsWith("//")) {
                        str = "http:" + str;
                    }
                    if (imageView.getLayoutParams().width > 0 && imageView.getLayoutParams().height > 0) {
                        Uri parse = Uri.parse(str);
                        ImageRequestBuilder progressiveRenderingEnabled = ImageRequestBuilder.newBuilderWithSource(parse).setImageDecodeOptions(ImageDecodeOptions.newBuilder().build()).setRotationOptions(RotationOptions.autoRotate()).setLocalThumbnailPreviewsEnabled(true).setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH).setProgressiveRenderingEnabled(false);
                        if (wXImageStrategy.blurRadius > 0) {
                            progressiveRenderingEnabled.setPostprocessor(new IterativeBoxBlurPostProcessor(wXImageStrategy.blurRadius));
                        }
                        ImageRequest build = progressiveRenderingEnabled.build();
                        if (imageView instanceof SimpleDraweeView) {
                            LogUtils.v("FrescoImageAdapter", "load: " + str);
                            AnonymousClass1 r3 = new BaseControllerListener<ImageInfo>() {
                                /* renamed from: a */
                                public void onFinalImageSet(String str, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
                                    if (imageInfo != null) {
                                        QualityInfo qualityInfo = imageInfo.getQualityInfo();
                                        FLog.d("Final image received! Size %d x %d", "Quality level %d, good enough: %s, full quality: %s", Integer.valueOf(imageInfo.getWidth()), Integer.valueOf(imageInfo.getHeight()), Integer.valueOf(qualityInfo.getQuality()), Boolean.valueOf(qualityInfo.isOfGoodEnoughQuality()), Boolean.valueOf(qualityInfo.isOfFullQuality()));
                                        if (wXImageStrategy.getImageListener() != null) {
                                            wXImageStrategy.getImageListener().onImageFinish(str, imageView, true, (Map) null);
                                        }
                                    }
                                }

                                /* renamed from: a */
                                public void onIntermediateImageSet(String str, @Nullable ImageInfo imageInfo) {
                                    FLog.d("", "Intermediate image received");
                                }

                                public void onFailure(String str, Throwable th) {
                                    FLog.e(getClass(), th, "Error loading %s", str);
                                    if (wXImageStrategy.getImageListener() != null) {
                                        wXImageStrategy.getImageListener().onImageFinish(str, imageView, false, (Map) null);
                                    }
                                }
                            };
                            GenericDraweeHierarchy genericDraweeHierarchy = (GenericDraweeHierarchy) ((SimpleDraweeView) imageView).getHierarchy();
                            if (!(genericDraweeHierarchy == null || (e = ImageUtils.e(wXImageStrategy.placeHolder)) == null)) {
                                BitmapDrawable bitmapDrawable = new BitmapDrawable(imageView.getContext().getResources(), e);
                                genericDraweeHierarchy.setPlaceholderImage((Drawable) bitmapDrawable, ScalingUtils.ScaleType.CENTER_INSIDE);
                                genericDraweeHierarchy.setFailureImage((Drawable) bitmapDrawable);
                            }
                            ((DraweeView) imageView).setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setAutoPlayAnimations(true)).setControllerListener(r3)).setUri(parse).setImageRequest(build)).build());
                            return;
                        }
                        Fresco.getImagePipeline().fetchDecodedImage(build, new Object()).subscribe(new BaseDataSubscriber<CloseableReference<CloseableImage>>() {
                            public void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                                if (wXImageStrategy.getImageListener() != null) {
                                    wXImageStrategy.getImageListener().onImageFinish(str, imageView, true, (Map) null);
                                }
                                CloseableReference result = dataSource.getResult();
                                if (result != null) {
                                    try {
                                        Preconditions.checkState(CloseableReference.isValid(result));
                                        CloseableImage closeableImage = (CloseableImage) result.get();
                                        if (closeableImage instanceof CloseableStaticBitmap) {
                                            imageView.setImageBitmap(((CloseableStaticBitmap) closeableImage).getUnderlyingBitmap());
                                            return;
                                        }
                                        throw new UnsupportedOperationException("Unrecognized image class: " + closeableImage);
                                    } finally {
                                        result.close();
                                    }
                                }
                            }

                            public void onFailureImpl(DataSource dataSource) {
                                if (wXImageStrategy.getImageListener() != null) {
                                    wXImageStrategy.getImageListener().onImageFinish(str, imageView, false, (Map) null);
                                }
                            }
                        }, UiThreadImmediateExecutorService.getInstance());
                    }
                }
            }
        }, 0);
    }
}
