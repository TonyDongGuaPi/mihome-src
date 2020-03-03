package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.imagehelper.ImageSource;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressLint({"ViewConstructor"})
class ImageView extends RenderableView {

    /* renamed from: a  reason: collision with root package name */
    private SVGLength f1259a;
    private SVGLength b;
    private SVGLength c;
    private SVGLength d;
    private String e;
    private int f;
    private int g;
    private String h;
    private int i;
    /* access modifiers changed from: private */
    public final AtomicBoolean j = new AtomicBoolean(false);

    public ImageView(ReactContext reactContext) {
        super(reactContext);
    }

    @ReactProp(name = "x")
    public void setX(Dynamic dynamic) {
        this.f1259a = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "y")
    public void setY(Dynamic dynamic) {
        this.b = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "width")
    public void setWidth(Dynamic dynamic) {
        this.c = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "height")
    public void setHeight(Dynamic dynamic) {
        this.d = SVGLength.a(dynamic);
        invalidate();
    }

    @ReactProp(name = "src")
    public void setSrc(@Nullable ReadableMap readableMap) {
        if (readableMap != null) {
            this.e = readableMap.getString("uri");
            if (this.e != null && !this.e.isEmpty()) {
                if (!readableMap.hasKey("width") || !readableMap.hasKey("height")) {
                    this.f = 0;
                    this.g = 0;
                } else {
                    this.f = readableMap.getInt("width");
                    this.g = readableMap.getInt("height");
                }
                if (Uri.parse(this.e).getScheme() == null) {
                    ResourceDrawableIdHelper.getInstance().getResourceDrawableUri(this.mContext, this.e);
                }
            }
        }
    }

    @ReactProp(name = "align")
    public void setAlign(String str) {
        this.h = str;
        invalidate();
    }

    @ReactProp(name = "meetOrSlice")
    public void setMeetOrSlice(int i2) {
        this.i = i2;
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void draw(Canvas canvas, Paint paint, float f2) {
        if (!this.j.get()) {
            ImagePipeline imagePipeline = Fresco.getImagePipeline();
            ImageRequest fromUri = ImageRequest.fromUri(new ImageSource(this.mContext, this.e).getUri());
            if (imagePipeline.isInBitmapMemoryCache(fromUri)) {
                a(imagePipeline, fromUri, canvas, paint, f2 * this.mOpacity);
                return;
            }
            a(imagePipeline, fromUri);
        }
    }

    /* access modifiers changed from: package-private */
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        path.addRect(a(), Path.Direction.CW);
        return path;
    }

    private void a(ImagePipeline imagePipeline, ImageRequest imageRequest) {
        this.j.set(true);
        imagePipeline.fetchDecodedImage(imageRequest, this.mContext).subscribe(new BaseBitmapDataSubscriber() {
            public void onNewResultImpl(Bitmap bitmap) {
                ImageView.this.j.set(false);
                SvgView svgView = ImageView.this.getSvgView();
                if (svgView != null) {
                    svgView.invalidate();
                }
            }

            public void onFailureImpl(DataSource dataSource) {
                ImageView.this.j.set(false);
                FLog.w(ReactConstants.TAG, dataSource.getFailureCause(), "RNSVG: fetchDecodedImage failed!", new Object[0]);
            }
        }, UiThreadImmediateExecutorService.getInstance());
    }

    @Nonnull
    private RectF a() {
        double relativeOnWidth = relativeOnWidth(this.f1259a);
        double relativeOnHeight = relativeOnHeight(this.b);
        double relativeOnWidth2 = relativeOnWidth(this.c);
        double relativeOnHeight2 = relativeOnHeight(this.d);
        if (relativeOnWidth2 == 0.0d) {
            relativeOnWidth2 = (double) (((float) this.f) * this.mScale);
        }
        if (relativeOnHeight2 == 0.0d) {
            relativeOnHeight2 = (double) (((float) this.g) * this.mScale);
        }
        return new RectF((float) relativeOnWidth, (float) relativeOnHeight, (float) (relativeOnWidth + relativeOnWidth2), (float) (relativeOnHeight + relativeOnHeight2));
    }

    private void a(Canvas canvas, Paint paint, Bitmap bitmap, float f2) {
        if (this.f == 0 || this.g == 0) {
            this.f = bitmap.getWidth();
            this.g = bitmap.getHeight();
        }
        RectF a2 = a();
        RectF rectF = new RectF(0.0f, 0.0f, (float) this.f, (float) this.g);
        ViewBox.a(rectF, a2, this.h, this.i).mapRect(rectF);
        canvas.clipPath(getPath(canvas, paint));
        Path clipPath = getClipPath(canvas, paint);
        if (clipPath != null) {
            canvas.clipPath(clipPath);
        }
        Paint paint2 = new Paint();
        paint2.setAlpha((int) (f2 * 255.0f));
        canvas.drawBitmap(bitmap, (Rect) null, rectF, paint2);
        canvas.getMatrix().mapRect(rectF);
        setClientRect(rectF);
    }

    private void a(ImagePipeline imagePipeline, ImageRequest imageRequest, Canvas canvas, Paint paint, float f2) {
        CloseableReference result;
        DataSource<CloseableReference<CloseableImage>> fetchImageFromBitmapCache = imagePipeline.fetchImageFromBitmapCache(imageRequest, this.mContext);
        try {
            result = fetchImageFromBitmapCache.getResult();
            if (result == null) {
                fetchImageFromBitmapCache.close();
                return;
            }
            CloseableImage closeableImage = (CloseableImage) result.get();
            if (!(closeableImage instanceof CloseableBitmap)) {
                CloseableReference.closeSafely((CloseableReference<?>) result);
                fetchImageFromBitmapCache.close();
                return;
            }
            Bitmap underlyingBitmap = ((CloseableBitmap) closeableImage).getUnderlyingBitmap();
            if (underlyingBitmap == null) {
                CloseableReference.closeSafely((CloseableReference<?>) result);
                fetchImageFromBitmapCache.close();
                return;
            }
            a(canvas, paint, underlyingBitmap, f2);
            CloseableReference.closeSafely((CloseableReference<?>) result);
            fetchImageFromBitmapCache.close();
        } catch (Exception e2) {
            throw new IllegalStateException(e2);
        } catch (Exception e3) {
            try {
                throw new IllegalStateException(e3);
            } catch (Throwable th) {
                fetchImageFromBitmapCache.close();
                throw th;
            }
        } catch (Throwable th2) {
            CloseableReference.closeSafely((CloseableReference<?>) result);
            throw th2;
        }
    }
}
