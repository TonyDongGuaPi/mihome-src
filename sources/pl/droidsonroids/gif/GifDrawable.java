package pl.droidsonroids.gif;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.widget.MediaController;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import pl.droidsonroids.gif.transforms.CornerRadiusTransform;
import pl.droidsonroids.gif.transforms.Transform;

public class GifDrawable extends Drawable implements Animatable, MediaController.MediaPlayerControl {

    /* renamed from: a  reason: collision with root package name */
    final ScheduledThreadPoolExecutor f11949a;
    volatile boolean b;
    long c;
    protected final Paint d;
    final Bitmap e;
    final GifInfoHandle f;
    final ConcurrentLinkedQueue<AnimationListener> g;
    final boolean h;
    final InvalidationHandler i;
    ScheduledFuture<?> j;
    private final Rect k;
    private ColorStateList l;
    private PorterDuffColorFilter m;
    private PorterDuff.Mode n;
    private final RenderTask o;
    private final Rect p;
    private int q;
    private int r;
    private Transform s;

    public boolean canPause() {
        return true;
    }

    public int getAudioSessionId() {
        return 0;
    }

    public int getBufferPercentage() {
        return 100;
    }

    public GifDrawable(@NonNull Resources resources, @RawRes @DrawableRes int i2) throws Resources.NotFoundException, IOException {
        this(resources.openRawResourceFd(i2));
        float a2 = GifViewUtils.a(resources, i2);
        this.r = (int) (((float) this.f.t()) * a2);
        this.q = (int) (((float) this.f.s()) * a2);
    }

    public GifDrawable(@NonNull AssetManager assetManager, @NonNull String str) throws IOException {
        this(assetManager.openFd(str));
    }

    public GifDrawable(@NonNull String str) throws IOException {
        this(new GifInfoHandle(str), (GifDrawable) null, (ScheduledThreadPoolExecutor) null, true);
    }

    public GifDrawable(@NonNull File file) throws IOException {
        this(file.getPath());
    }

    public GifDrawable(@NonNull InputStream inputStream) throws IOException {
        this(new GifInfoHandle(inputStream), (GifDrawable) null, (ScheduledThreadPoolExecutor) null, true);
    }

    public GifDrawable(@NonNull AssetFileDescriptor assetFileDescriptor) throws IOException {
        this(new GifInfoHandle(assetFileDescriptor), (GifDrawable) null, (ScheduledThreadPoolExecutor) null, true);
    }

    public GifDrawable(@NonNull FileDescriptor fileDescriptor) throws IOException {
        this(new GifInfoHandle(fileDescriptor), (GifDrawable) null, (ScheduledThreadPoolExecutor) null, true);
    }

    public GifDrawable(@NonNull byte[] bArr) throws IOException {
        this(new GifInfoHandle(bArr), (GifDrawable) null, (ScheduledThreadPoolExecutor) null, true);
    }

    public GifDrawable(@NonNull ByteBuffer byteBuffer) throws IOException {
        this(new GifInfoHandle(byteBuffer), (GifDrawable) null, (ScheduledThreadPoolExecutor) null, true);
    }

    public GifDrawable(@Nullable ContentResolver contentResolver, @NonNull Uri uri) throws IOException {
        this(GifInfoHandle.a(contentResolver, uri), (GifDrawable) null, (ScheduledThreadPoolExecutor) null, true);
    }

    protected GifDrawable(@NonNull InputSource inputSource, @Nullable GifDrawable gifDrawable, @Nullable ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, boolean z, @NonNull GifOptions gifOptions) throws IOException {
        this(inputSource.a(gifOptions), gifDrawable, scheduledThreadPoolExecutor, z);
    }

    GifDrawable(GifInfoHandle gifInfoHandle, GifDrawable gifDrawable, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, boolean z) {
        this.b = true;
        this.c = Long.MIN_VALUE;
        this.k = new Rect();
        this.d = new Paint(6);
        this.g = new ConcurrentLinkedQueue<>();
        this.o = new RenderTask(this);
        this.h = z;
        this.f11949a = scheduledThreadPoolExecutor == null ? GifRenderingExecutor.a() : scheduledThreadPoolExecutor;
        this.f = gifInfoHandle;
        Bitmap bitmap = null;
        if (gifDrawable != null) {
            synchronized (gifDrawable.f) {
                if (!gifDrawable.f.o() && gifDrawable.f.t() >= this.f.t() && gifDrawable.f.s() >= this.f.s()) {
                    gifDrawable.s();
                    Bitmap bitmap2 = gifDrawable.e;
                    bitmap2.eraseColor(0);
                    bitmap = bitmap2;
                }
            }
        }
        if (bitmap == null) {
            this.e = Bitmap.createBitmap(this.f.s(), this.f.t(), Bitmap.Config.ARGB_8888);
        } else {
            this.e = bitmap;
        }
        this.e.setHasAlpha(!gifInfoHandle.v());
        this.p = new Rect(0, 0, this.f.s(), this.f.t());
        this.i = new InvalidationHandler(this);
        this.o.a();
        this.q = this.f.s();
        this.r = this.f.t();
    }

    public void a() {
        s();
        this.e.recycle();
    }

    private void s() {
        this.b = false;
        this.i.removeMessages(-1);
        this.f.a();
    }

    public boolean b() {
        return this.f.o();
    }

    public void invalidateSelf() {
        super.invalidateSelf();
        u();
    }

    public int getIntrinsicHeight() {
        return this.r;
    }

    public int getIntrinsicWidth() {
        return this.q;
    }

    public void setAlpha(@IntRange(from = 0, to = 255) int i2) {
        this.d.setAlpha(i2);
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.d.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return (!this.f.v() || this.d.getAlpha() < 255) ? -2 : -1;
    }

    public void start() {
        synchronized (this) {
            if (!this.b) {
                this.b = true;
                a(this.f.b());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(long j2) {
        if (this.h) {
            this.c = 0;
            this.i.sendEmptyMessageAtTime(-1, 0);
            return;
        }
        t();
        this.j = this.f11949a.schedule(this.o, Math.max(j2, 0), TimeUnit.MILLISECONDS);
    }

    public void c() {
        this.f11949a.execute(new SafeRunnable(this) {
            public void a() {
                if (GifDrawable.this.f.c()) {
                    GifDrawable.this.start();
                }
            }
        });
    }

    public void stop() {
        synchronized (this) {
            if (this.b) {
                this.b = false;
                t();
                this.f.d();
            }
        }
    }

    private void t() {
        if (this.j != null) {
            this.j.cancel(false);
        }
        this.i.removeMessages(-1);
    }

    public boolean isRunning() {
        return this.b;
    }

    @Nullable
    public String d() {
        return this.f.e();
    }

    public int e() {
        return this.f.f();
    }

    public void a(@IntRange(from = 0, to = 65535) int i2) {
        this.f.a(i2);
    }

    @NonNull
    public String toString() {
        return String.format(Locale.ENGLISH, "GIF: size: %dx%d, frames: %d, error: %d", new Object[]{Integer.valueOf(this.f.s()), Integer.valueOf(this.f.t()), Integer.valueOf(this.f.u()), Integer.valueOf(this.f.h())});
    }

    public int f() {
        return this.f.u();
    }

    @NonNull
    public GifError g() {
        return GifError.fromCode(this.f.h());
    }

    @Nullable
    public static GifDrawable a(@NonNull Resources resources, @RawRes @DrawableRes int i2) {
        try {
            return new GifDrawable(resources, i2);
        } catch (IOException unused) {
            return null;
        }
    }

    public void a(@FloatRange(from = 0.0d, fromInclusive = false) float f2) {
        this.f.a(f2);
    }

    public void pause() {
        stop();
    }

    public int getDuration() {
        return this.f.i();
    }

    public int getCurrentPosition() {
        return this.f.j();
    }

    public void seekTo(@IntRange(from = 0, to = 2147483647L) final int i2) {
        if (i2 >= 0) {
            this.f11949a.execute(new SafeRunnable(this) {
                public void a() {
                    GifDrawable.this.f.a(i2, GifDrawable.this.e);
                    this.c.i.sendEmptyMessageAtTime(-1, 0);
                }
            });
            return;
        }
        throw new IllegalArgumentException("Position is not positive");
    }

    public void b(@IntRange(from = 0, to = 2147483647L) int i2) {
        if (i2 >= 0) {
            synchronized (this.f) {
                this.f.a(i2, this.e);
            }
            this.i.sendEmptyMessageAtTime(-1, 0);
            return;
        }
        throw new IllegalArgumentException("Position is not positive");
    }

    public void c(@IntRange(from = 0, to = 2147483647L) final int i2) {
        if (i2 >= 0) {
            this.f11949a.execute(new SafeRunnable(this) {
                public void a() {
                    GifDrawable.this.f.b(i2, GifDrawable.this.e);
                    GifDrawable.this.i.sendEmptyMessageAtTime(-1, 0);
                }
            });
            return;
        }
        throw new IndexOutOfBoundsException("Frame index is not positive");
    }

    public Bitmap d(@IntRange(from = 0, to = 2147483647L) int i2) {
        Bitmap m2;
        if (i2 >= 0) {
            synchronized (this.f) {
                this.f.b(i2, this.e);
                m2 = m();
            }
            this.i.sendEmptyMessageAtTime(-1, 0);
            return m2;
        }
        throw new IndexOutOfBoundsException("Frame index is not positive");
    }

    public Bitmap e(@IntRange(from = 0, to = 2147483647L) int i2) {
        Bitmap m2;
        if (i2 >= 0) {
            synchronized (this.f) {
                this.f.a(i2, this.e);
                m2 = m();
            }
            this.i.sendEmptyMessageAtTime(-1, 0);
            return m2;
        }
        throw new IllegalArgumentException("Position is not positive");
    }

    public boolean isPlaying() {
        return this.b;
    }

    public boolean canSeekBackward() {
        return f() > 1;
    }

    public boolean canSeekForward() {
        return f() > 1;
    }

    public int h() {
        return this.e.getRowBytes() * this.e.getHeight();
    }

    public long i() {
        long m2 = this.f.m();
        if (Build.VERSION.SDK_INT >= 19) {
            return m2 + ((long) this.e.getAllocationByteCount());
        }
        return m2 + ((long) h());
    }

    public long j() {
        return this.f.n();
    }

    public long k() {
        return this.f.g();
    }

    public void a(@NonNull int[] iArr) {
        this.e.getPixels(iArr, 0, this.f.s(), 0, 0, this.f.s(), this.f.t());
    }

    public int a(@IntRange(from = 0) int i2, @IntRange(from = 0) int i3) {
        if (i2 >= this.f.s()) {
            throw new IllegalArgumentException("x must be < width");
        } else if (i3 < this.f.t()) {
            return this.e.getPixel(i2, i3);
        } else {
            throw new IllegalArgumentException("y must be < height");
        }
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        this.k.set(rect);
        if (this.s != null) {
            this.s.a(rect);
        }
    }

    public void draw(@NonNull Canvas canvas) {
        boolean z;
        if (this.m == null || this.d.getColorFilter() != null) {
            z = false;
        } else {
            this.d.setColorFilter(this.m);
            z = true;
        }
        if (this.s == null) {
            canvas.drawBitmap(this.e, this.p, this.k, this.d);
        } else {
            this.s.a(canvas, this.d, this.e);
        }
        if (z) {
            this.d.setColorFilter((ColorFilter) null);
        }
    }

    private void u() {
        if (this.h && this.b && this.c != Long.MIN_VALUE) {
            long max = Math.max(0, this.c - SystemClock.uptimeMillis());
            this.c = Long.MIN_VALUE;
            this.f11949a.remove(this.o);
            this.j = this.f11949a.schedule(this.o, max, TimeUnit.MILLISECONDS);
        }
    }

    @NonNull
    public final Paint l() {
        return this.d;
    }

    public int getAlpha() {
        return this.d.getAlpha();
    }

    public void setFilterBitmap(boolean z) {
        this.d.setFilterBitmap(z);
        invalidateSelf();
    }

    @Deprecated
    public void setDither(boolean z) {
        this.d.setDither(z);
        invalidateSelf();
    }

    public void a(@NonNull AnimationListener animationListener) {
        this.g.add(animationListener);
    }

    public boolean b(AnimationListener animationListener) {
        return this.g.remove(animationListener);
    }

    public ColorFilter getColorFilter() {
        return this.d.getColorFilter();
    }

    public Bitmap m() {
        Bitmap copy = this.e.copy(this.e.getConfig(), this.e.isMutable());
        copy.setHasAlpha(this.e.hasAlpha());
        return copy;
    }

    private PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    public void setTintList(ColorStateList colorStateList) {
        this.l = colorStateList;
        this.m = a(colorStateList, this.n);
        invalidateSelf();
    }

    public void setTintMode(@Nullable PorterDuff.Mode mode) {
        this.n = mode;
        this.m = a(this.l, mode);
        invalidateSelf();
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        if (this.l == null || this.n == null) {
            return false;
        }
        this.m = a(this.l, this.n);
        return true;
    }

    public boolean isStateful() {
        return super.isStateful() || (this.l != null && this.l.isStateful());
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (!this.h) {
            if (z) {
                if (z2) {
                    c();
                }
                if (visible) {
                    start();
                }
            } else if (visible) {
                stop();
            }
        }
        return visible;
    }

    public int n() {
        return this.f.k();
    }

    public int o() {
        int l2 = this.f.l();
        return (l2 == 0 || l2 < this.f.f()) ? l2 : l2 - 1;
    }

    public boolean p() {
        return this.f.q();
    }

    public int f(@IntRange(from = 0) int i2) {
        return this.f.b(i2);
    }

    public void b(@FloatRange(from = 0.0d) float f2) {
        this.s = new CornerRadiusTransform(f2);
        this.s.a(this.k);
    }

    @FloatRange(from = 0.0d)
    public float q() {
        if (this.s instanceof CornerRadiusTransform) {
            return ((CornerRadiusTransform) this.s).a();
        }
        return 0.0f;
    }

    public void a(@Nullable Transform transform) {
        this.s = transform;
        if (this.s != null) {
            this.s.a(this.k);
        }
    }

    @Nullable
    public Transform r() {
        return this.s;
    }
}
