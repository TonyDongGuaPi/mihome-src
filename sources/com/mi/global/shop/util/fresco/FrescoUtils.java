package com.mi.global.shop.util.fresco;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.internal.Supplier;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.mi.global.shop.util.UrlUtil;
import java.io.File;
import java.util.UUID;
import okhttp3.OkHttpClient;

public class FrescoUtils {

    /* renamed from: a  reason: collision with root package name */
    public static String f7123a = "imageCache";

    public interface BitmapListener {
        void a();

        void a(Bitmap bitmap);
    }

    public interface DownloadListener {
        void a();

        void a(float f);

        void a(File file);
    }

    public static void a(final Context context, int i, String str) {
        ImagePipelineConfig.Builder downsampleEnabled = OkHttpImagePipelineConfigFactory.newBuilder(context, new OkHttpClient.Builder().cookieJar(new FrescoCookieJar()).build()).setMainDiskCacheConfig(DiskCacheConfig.newBuilder(context).setMaxCacheSize((long) (i * 1024 * 1024)).setBaseDirectoryName(f7123a).setBaseDirectoryPathSupplier(new Supplier<File>() {
            /* renamed from: a */
            public File get() {
                return context.getCacheDir();
            }
        }).build()).setDownsampleEnabled(true);
        if (!TextUtils.isEmpty(str) && !str.equals("mihome_sdk")) {
            downsampleEnabled.setBitmapMemoryCacheParamsSupplier(new BitmapMemoryCacheParamsSupplier((ActivityManager) context.getSystemService("activity")));
        }
        Fresco.initialize(context, downsampleEnabled.build());
    }

    public static void a(@NonNull String str, @NonNull SimpleDraweeView simpleDraweeView, BasePostprocessor basePostprocessor, int i, int i2, BaseControllerListener baseControllerListener) {
        if (!TextUtils.isEmpty(str)) {
            b(Uri.parse(str), simpleDraweeView, basePostprocessor, i, i2, baseControllerListener);
        }
    }

    public static void a(@NonNull Uri uri, @NonNull SimpleDraweeView simpleDraweeView, BasePostprocessor basePostprocessor, int i, int i2, BaseControllerListener baseControllerListener) {
        b(uri, simpleDraweeView, basePostprocessor, i, i2, baseControllerListener);
    }

    public static void a(@NonNull String str, @NonNull SimpleDraweeView simpleDraweeView) {
        if (!TextUtils.isEmpty(str)) {
            b(Uri.parse(str), simpleDraweeView, (BasePostprocessor) null, 0, 0, (BaseControllerListener) null);
        }
    }

    public static void a(@NonNull Uri uri, @NonNull SimpleDraweeView simpleDraweeView) {
        b(uri, simpleDraweeView, (BasePostprocessor) null, 0, 0, (BaseControllerListener) null);
    }

    public static void a(@NonNull String str, @NonNull SimpleDraweeView simpleDraweeView, int i, int i2) {
        if (!TextUtils.isEmpty(str)) {
            b(Uri.parse(str), simpleDraweeView, (BasePostprocessor) null, 0, 0, (BaseControllerListener) null);
            a(simpleDraweeView, i, i2);
        }
    }

    public static void b(@NonNull String str, @NonNull SimpleDraweeView simpleDraweeView, BasePostprocessor basePostprocessor, int i, int i2, BaseControllerListener baseControllerListener) {
        if (!TextUtils.isEmpty(str)) {
            b(d(str), simpleDraweeView, basePostprocessor, i, i2, baseControllerListener);
        }
    }

    public static void b(@NonNull String str, @NonNull SimpleDraweeView simpleDraweeView) {
        if (!TextUtils.isEmpty(str)) {
            b(d(str), simpleDraweeView, (BasePostprocessor) null, 0, 0, (BaseControllerListener) null);
        }
    }

    public static void a(int i, @NonNull SimpleDraweeView simpleDraweeView, BasePostprocessor basePostprocessor, int i2, int i3, BaseControllerListener baseControllerListener) {
        b(b(i), simpleDraweeView, basePostprocessor, i2, i3, baseControllerListener);
    }

    public static void a(int i, @NonNull SimpleDraweeView simpleDraweeView) {
        b(b(i), simpleDraweeView, (BasePostprocessor) null, 0, 0, (BaseControllerListener) null);
    }

    public static void b(@NonNull Uri uri, @NonNull SimpleDraweeView simpleDraweeView, BasePostprocessor basePostprocessor, int i, int i2, BaseControllerListener baseControllerListener) {
        if (uri != null) {
            Uri parse = Uri.parse(UrlUtil.c(uri.toString()));
            ResizeOptions resizeOptions = null;
            if (i > 0 && i2 > 0) {
                resizeOptions = new ResizeOptions(i, i2);
            }
            simpleDraweeView.setController((PipelineDraweeController) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequestBuilder.newBuilderWithSource(parse).setPostprocessor(basePostprocessor).setResizeOptions(resizeOptions).setProgressiveRenderingEnabled(true).setAutoRotateEnabled(true).build())).setControllerListener(baseControllerListener)).setOldController(simpleDraweeView.getController())).setAutoPlayAnimations(true)).build());
        }
    }

    public static void a(@NonNull SimpleDraweeView simpleDraweeView, int i, int i2) {
        RoundingParams asCircle = RoundingParams.asCircle();
        asCircle.setBorderColor(i);
        asCircle.setBorderWidth((float) i2);
        ((GenericDraweeHierarchy) simpleDraweeView.getHierarchy()).setRoundingParams(asCircle);
    }

    public static void a(@NonNull SimpleDraweeView simpleDraweeView, float f, int i) {
        RoundingParams fromCornersRadius = RoundingParams.fromCornersRadius(f);
        fromCornersRadius.setOverlayColor(i);
        ((GenericDraweeHierarchy) simpleDraweeView.getHierarchy()).setRoundingParams(fromCornersRadius);
    }

    public static void a() {
        Fresco.getImagePipeline().pause();
    }

    public static void b() {
        Fresco.getImagePipeline().resume();
    }

    public static void c() {
        Fresco.getImagePipeline().clearDiskCaches();
    }

    public static void d() {
        Fresco.getImagePipeline().clearMemoryCaches();
    }

    public static void a(int i) {
        if (i >= 60) {
            try {
                ImagePipelineFactory.getInstance().getImagePipeline().clearMemoryCaches();
            } catch (Exception unused) {
            }
        }
    }

    public static void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            Fresco.getImagePipeline().evictFromDiskCache(Uri.parse(str));
        }
    }

    public static File b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        CacheKey encodedCacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(ImageRequest.fromUri(str), (Object) null);
        if (ImagePipelineFactory.getInstance().getMainFileCache().hasKey(encodedCacheKey)) {
            return ((FileBinaryResource) ImagePipelineFactory.getInstance().getMainFileCache().getResource(encodedCacheKey)).getFile();
        }
        if (ImagePipelineFactory.getInstance().getSmallImageFileCache().hasKey(encodedCacheKey)) {
            return ((FileBinaryResource) ImagePipelineFactory.getInstance().getSmallImageFileCache().getResource(encodedCacheKey)).getFile();
        }
        return null;
    }

    public static boolean a(String str, File file, String str2) {
        return a(str, new File(file, str2));
    }

    public static boolean a(String str, File file) {
        File b;
        if (file == null || (b = b(str)) == null) {
            return false;
        }
        if (!file.isDirectory()) {
            return b.renameTo(file);
        }
        throw new RuntimeException(file + "is a directory,you should call copyCacheFileToDir(String url,File dir)");
    }

    public static boolean c(String str) {
        DataSource<Boolean> isInDiskCache;
        if (TextUtils.isEmpty(str) || (isInDiskCache = Fresco.getImagePipeline().isInDiskCache(Uri.parse(str))) == null) {
            return false;
        }
        try {
            return isInDiskCache.getResult().booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static void a(String str, Context context, final File file, final DownloadListener downloadListener) {
        final String c = UrlUtil.c(str);
        if (!TextUtils.isEmpty(c)) {
            Fresco.getImagePipeline().prefetchToDiskCache(ImageRequestBuilder.newBuilderWithSource(Uri.parse(c)).build(), context, Priority.HIGH).subscribe(new BaseDataSubscriber<Void>() {
                /* access modifiers changed from: protected */
                public void onNewResultImpl(DataSource<Void> dataSource) {
                    File b2 = FrescoUtils.b(c, file);
                    FrescoUtils.a(c);
                    if (downloadListener != null) {
                        if (b2 == null || !b2.exists()) {
                            downloadListener.a();
                        } else {
                            downloadListener.a(b2);
                        }
                    }
                }

                public void onProgressUpdate(DataSource<Void> dataSource) {
                    super.onProgressUpdate(dataSource);
                    if (downloadListener != null) {
                        downloadListener.a(dataSource.getProgress());
                    }
                }

                /* access modifiers changed from: protected */
                public void onFailureImpl(DataSource<Void> dataSource) {
                    if (downloadListener != null) {
                        downloadListener.a();
                    }
                }
            }, CallerThreadExecutor.getInstance());
        }
    }

    public static File b(String str, File file) {
        if (file == null) {
            return null;
        }
        if (file.isDirectory()) {
            if (!file.exists()) {
                file.mkdirs();
            }
            String guessFileName = URLUtil.guessFileName(str, "", "");
            if (TextUtils.isEmpty(guessFileName)) {
                guessFileName = UUID.randomUUID().toString();
            }
            File file2 = new File(file, guessFileName);
            if (a(str, file2)) {
                return file2;
            }
            return null;
        }
        throw new RuntimeException(file + "is not a directory,you should call copyCacheFile(String url,File path)");
    }

    public static void a(String str, Context context, int i, int i2, BitmapListener bitmapListener) {
        a(str, context, i, i2, (BasePostprocessor) null, bitmapListener);
    }

    public static void a(String str, Context context, BitmapListener bitmapListener) {
        a(str, context, 0, 0, (BasePostprocessor) null, bitmapListener);
    }

    private static void a(final String str, Context context, final int i, final int i2, BasePostprocessor basePostprocessor, final BitmapListener bitmapListener) {
        if (!TextUtils.isEmpty(str)) {
            ResizeOptions resizeOptions = null;
            if (!(i == 0 || i2 == 0)) {
                resizeOptions = new ResizeOptions(i, i2);
            }
            Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).setProgressiveRenderingEnabled(false).setPostprocessor(basePostprocessor).setResizeOptions(resizeOptions).build(), context).subscribe(new BaseBitmapDataSubscriber() {
                /* access modifiers changed from: protected */
                public void onNewResultImpl(Bitmap bitmap) {
                    if (str.contains("gif")) {
                        File b2 = FrescoUtils.b(str);
                        if (b2.exists()) {
                            Bitmap a2 = GifUtils.a(b2);
                            if (!(i == 0 || i2 == 0)) {
                                a2 = BitmapUtils.b(a2, true, i, i2);
                            }
                            if (a2 != null && bitmapListener != null) {
                                bitmapListener.a(a2);
                            } else if (bitmapListener != null) {
                                bitmapListener.a();
                            }
                        } else if (bitmapListener != null) {
                            bitmapListener.a();
                        }
                    } else if (bitmapListener != null) {
                        bitmapListener.a(bitmap);
                    }
                }

                /* access modifiers changed from: protected */
                public void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                    if (bitmapListener != null) {
                        bitmapListener.a();
                    }
                }
            }, CallerThreadExecutor.getInstance());
        }
    }

    public static Uri a(File file) {
        return Uri.fromFile(file);
    }

    public static Uri d(String str) {
        return Uri.fromFile(new File(str));
    }

    public static Uri b(int i) {
        return Uri.parse("res://xxyy/" + i);
    }
}
