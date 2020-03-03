package pl.droidsonroids.gif;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Surface;
import android.view.TextureView;
import android.widget.ImageView;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.mi.mistatistic.sdk.data.EventData;
import java.io.IOException;
import java.lang.ref.WeakReference;
import pl.droidsonroids.gif.GifViewUtils;
import pl.droidsonroids.gif.InputSource;

public class GifTextureView extends TextureView {

    /* renamed from: a  reason: collision with root package name */
    private static final ImageView.ScaleType[] f11958a = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    private ImageView.ScaleType b = ImageView.ScaleType.FIT_CENTER;
    private final Matrix c = new Matrix();
    /* access modifiers changed from: private */
    public InputSource d;
    private RenderThread e;
    /* access modifiers changed from: private */
    public float f = 1.0f;
    /* access modifiers changed from: private */
    public GifViewUtils.GifViewAttributes g;

    public interface PlaceholderDrawListener {
        void a(Canvas canvas);
    }

    public TextureView.SurfaceTextureListener getSurfaceTextureListener() {
        return null;
    }

    public GifTextureView(Context context) {
        super(context);
        a((AttributeSet) null, 0, 0);
    }

    public GifTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0, 0);
    }

    public GifTextureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet, i, 0);
    }

    @RequiresApi(21)
    public GifTextureView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(attributeSet, i, i2);
    }

    private void a(AttributeSet attributeSet, int i, int i2) {
        if (attributeSet != null) {
            int attributeIntValue = attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "scaleType", -1);
            if (attributeIntValue >= 0 && attributeIntValue < f11958a.length) {
                this.b = f11958a[attributeIntValue];
            }
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.GifTextureView, i, i2);
            this.d = a(obtainStyledAttributes);
            super.setOpaque(obtainStyledAttributes.getBoolean(R.styleable.GifTextureView_isOpaque, false));
            obtainStyledAttributes.recycle();
            this.g = new GifViewUtils.GifViewAttributes(this, attributeSet, i, i2);
        } else {
            super.setOpaque(false);
            this.g = new GifViewUtils.GifViewAttributes();
        }
        if (!isInEditMode()) {
            this.e = new RenderThread(this);
            if (this.d != null) {
                this.e.start();
            }
        }
    }

    public void setSurfaceTextureListener(TextureView.SurfaceTextureListener surfaceTextureListener) {
        throw new UnsupportedOperationException("Changing SurfaceTextureListener is not supported");
    }

    public void setSurfaceTexture(SurfaceTexture surfaceTexture) {
        throw new UnsupportedOperationException("Changing SurfaceTexture is not supported");
    }

    private static InputSource a(TypedArray typedArray) {
        TypedValue typedValue = new TypedValue();
        if (!typedArray.getValue(R.styleable.GifTextureView_gifSource, typedValue)) {
            return null;
        }
        if (typedValue.resourceId != 0) {
            String resourceTypeName = typedArray.getResources().getResourceTypeName(typedValue.resourceId);
            if (GifViewUtils.b.contains(resourceTypeName)) {
                return new InputSource.ResourcesSource(typedArray.getResources(), typedValue.resourceId);
            }
            if (!EventData.b.equals(resourceTypeName)) {
                throw new IllegalArgumentException("Expected string, drawable, mipmap or raw resource type. '" + resourceTypeName + "' is not supported");
            }
        }
        return new InputSource.AssetSource(typedArray.getResources().getAssets(), typedValue.string.toString());
    }

    private static class RenderThread extends Thread implements TextureView.SurfaceTextureListener {

        /* renamed from: a  reason: collision with root package name */
        final ConditionVariable f11960a = new ConditionVariable();
        long[] b;
        /* access modifiers changed from: private */
        public GifInfoHandle c = new GifInfoHandle();
        /* access modifiers changed from: private */
        public IOException d;
        private final WeakReference<GifTextureView> e;

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        RenderThread(GifTextureView gifTextureView) {
            super("GifRenderThread");
            this.e = new WeakReference<>(gifTextureView);
        }

        public void run() {
            try {
                GifTextureView gifTextureView = (GifTextureView) this.e.get();
                if (gifTextureView != null) {
                    this.c = gifTextureView.d.a();
                    this.c.a(1, gifTextureView.isOpaque());
                    if (gifTextureView.g.d >= 0) {
                        this.c.a(gifTextureView.g.d);
                    }
                    final GifTextureView gifTextureView2 = (GifTextureView) this.e.get();
                    if (gifTextureView2 == null) {
                        this.c.a();
                        return;
                    }
                    gifTextureView2.setSuperSurfaceTextureListener(this);
                    boolean isAvailable = gifTextureView2.isAvailable();
                    this.f11960a.a(isAvailable);
                    if (isAvailable) {
                        gifTextureView2.post(new Runnable() {
                            public void run() {
                                gifTextureView2.a(RenderThread.this.c);
                            }
                        });
                    }
                    this.c.a(gifTextureView2.f);
                    while (!isInterrupted()) {
                        try {
                            this.f11960a.c();
                            GifTextureView gifTextureView3 = (GifTextureView) this.e.get();
                            if (gifTextureView3 == null) {
                                break;
                            }
                            SurfaceTexture surfaceTexture = gifTextureView3.getSurfaceTexture();
                            if (surfaceTexture != null) {
                                Surface surface = new Surface(surfaceTexture);
                                try {
                                    this.c.a(surface, this.b);
                                } finally {
                                    surface.release();
                                }
                            }
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    this.c.a();
                    this.c = new GifInfoHandle();
                }
            } catch (IOException e2) {
                this.d = e2;
            }
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            GifTextureView gifTextureView = (GifTextureView) this.e.get();
            if (gifTextureView != null) {
                gifTextureView.a(this.c);
            }
            this.f11960a.a();
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            this.f11960a.b();
            this.c.p();
            interrupt();
            return true;
        }

        /* access modifiers changed from: package-private */
        public void a(@NonNull GifTextureView gifTextureView, @Nullable PlaceholderDrawListener placeholderDrawListener) {
            this.f11960a.b();
            gifTextureView.setSuperSurfaceTextureListener(placeholderDrawListener != null ? new PlaceholderDrawingSurfaceTextureListener(placeholderDrawListener) : null);
            this.c.p();
            interrupt();
        }
    }

    /* access modifiers changed from: private */
    public void setSuperSurfaceTextureListener(TextureView.SurfaceTextureListener surfaceTextureListener) {
        super.setSurfaceTextureListener(surfaceTextureListener);
    }

    public void setOpaque(boolean z) {
        if (z != isOpaque()) {
            super.setOpaque(z);
            setInputSource(this.d);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.e.a(this, (PlaceholderDrawListener) null);
        super.onDetachedFromWindow();
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (surfaceTexture != null) {
            surfaceTexture.release();
        }
    }

    public synchronized void setInputSource(@Nullable InputSource inputSource) {
        setInputSource(inputSource, (PlaceholderDrawListener) null);
    }

    public synchronized void setInputSource(@Nullable InputSource inputSource, @Nullable PlaceholderDrawListener placeholderDrawListener) {
        this.e.a(this, placeholderDrawListener);
        try {
            this.e.join();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        this.d = inputSource;
        this.e = new RenderThread(this);
        if (inputSource != null) {
            this.e.start();
        }
    }

    public void setSpeed(@FloatRange(from = 0.0d, fromInclusive = false) float f2) {
        this.f = f2;
        this.e.c.a(f2);
    }

    @Nullable
    public IOException getIOException() {
        if (this.e.d != null) {
            return this.e.d;
        }
        return GifIOException.fromCode(this.e.c.h());
    }

    public void setScaleType(@NonNull ImageView.ScaleType scaleType) {
        this.b = scaleType;
        a(this.e.c);
    }

    public ImageView.ScaleType getScaleType() {
        return this.b;
    }

    /* access modifiers changed from: private */
    public void a(GifInfoHandle gifInfoHandle) {
        Matrix matrix = new Matrix();
        float width = (float) getWidth();
        float height = (float) getHeight();
        float s = ((float) gifInfoHandle.s()) / width;
        float t = ((float) gifInfoHandle.t()) / height;
        RectF rectF = new RectF(0.0f, 0.0f, (float) gifInfoHandle.s(), (float) gifInfoHandle.t());
        RectF rectF2 = new RectF(0.0f, 0.0f, width, height);
        float f2 = 1.0f;
        switch (AnonymousClass1.f11959a[this.b.ordinal()]) {
            case 1:
                matrix.setScale(s, t, width / 2.0f, height / 2.0f);
                break;
            case 2:
                float min = 1.0f / Math.min(s, t);
                matrix.setScale(s * min, min * t, width / 2.0f, height / 2.0f);
                break;
            case 3:
                if (((float) gifInfoHandle.s()) > width || ((float) gifInfoHandle.t()) > height) {
                    f2 = Math.min(1.0f / s, 1.0f / t);
                }
                matrix.setScale(s * f2, f2 * t, width / 2.0f, height / 2.0f);
                break;
            case 4:
                matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
                matrix.preScale(s, t);
                break;
            case 5:
                matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.END);
                matrix.preScale(s, t);
                break;
            case 6:
                matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.START);
                matrix.preScale(s, t);
                break;
            case 7:
                return;
            case 8:
                matrix.set(this.c);
                matrix.preScale(s, t);
                break;
        }
        super.setTransform(matrix);
    }

    /* renamed from: pl.droidsonroids.gif.GifTextureView$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f11959a = new int[ImageView.ScaleType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|(3:15|16|18)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                android.widget.ImageView$ScaleType[] r0 = android.widget.ImageView.ScaleType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f11959a = r0
                int[] r0 = f11959a     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f11959a     // Catch:{ NoSuchFieldError -> 0x001f }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_CROP     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f11959a     // Catch:{ NoSuchFieldError -> 0x002a }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_INSIDE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f11959a     // Catch:{ NoSuchFieldError -> 0x0035 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_CENTER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f11959a     // Catch:{ NoSuchFieldError -> 0x0040 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_END     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f11959a     // Catch:{ NoSuchFieldError -> 0x004b }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_START     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f11959a     // Catch:{ NoSuchFieldError -> 0x0056 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_XY     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f11959a     // Catch:{ NoSuchFieldError -> 0x0062 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.MATRIX     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: pl.droidsonroids.gif.GifTextureView.AnonymousClass1.<clinit>():void");
        }
    }

    public void setImageMatrix(Matrix matrix) {
        setTransform(matrix);
    }

    public void setTransform(Matrix matrix) {
        this.c.set(matrix);
        a(this.e.c);
    }

    public Matrix getTransform(Matrix matrix) {
        if (matrix == null) {
            matrix = new Matrix();
        }
        matrix.set(this.c);
        return matrix;
    }

    public Parcelable onSaveInstanceState() {
        this.e.b = this.e.c.r();
        return new GifViewSavedState(super.onSaveInstanceState(), this.g.c ? this.e.b : null);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof GifViewSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        GifViewSavedState gifViewSavedState = (GifViewSavedState) parcelable;
        super.onRestoreInstanceState(gifViewSavedState.getSuperState());
        this.e.b = gifViewSavedState.f11962a[0];
    }

    public void setFreezesAnimation(boolean z) {
        this.g.c = z;
    }
}
