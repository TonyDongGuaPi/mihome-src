package com.projectseptember.RNGL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.taobao.weex.annotation.JSMethod;
import java.util.concurrent.Executor;

public class GLImage {

    /* renamed from: a  reason: collision with root package name */
    private Uri f8568a;
    /* access modifiers changed from: private */
    public GLTexture b;
    /* access modifiers changed from: private */
    public Runnable c;
    private Executor d;
    private Executor e;
    private DataSource<CloseableReference<CloseableImage>> f;

    public GLImage(Executor executor, Executor executor2, Runnable runnable) {
        this.c = runnable;
        this.d = executor;
        this.e = executor2;
        this.b = new GLTexture(executor);
    }

    public void a(Uri uri) {
        if (this.f8568a == uri) {
            return;
        }
        if (this.f8568a == null || !this.f8568a.equals(uri)) {
            this.f8568a = uri;
            b();
        }
    }

    private void b() {
        if (this.f != null && !this.f.isFinished()) {
            this.f.close();
        }
        final Uri uri = this.f8568a;
        this.f = Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(uri).setAutoRotateEnabled(false).build(), (Object) null);
        this.f.subscribe(new BaseBitmapDataSubscriber() {
            /* access modifiers changed from: protected */
            public void onNewResultImpl(@Nullable Bitmap bitmap) {
                GLImage.this.a(bitmap);
            }

            /* access modifiers changed from: protected */
            public void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                Log.e("GLImage", "Failed to load '" + uri.getPath() + "'", dataSource.getFailureCause());
            }
        }, this.e);
    }

    public void a(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(1.0f, -1.0f);
        final Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        createBitmap.setHasAlpha(true);
        this.d.execute(new Runnable() {
            public void run() {
                GLImage.this.b.a(createBitmap);
                createBitmap.recycle();
                GLImage.this.c.run();
            }
        });
    }

    public GLTexture a() {
        return this.b;
    }

    @Nullable
    public static Uri a(Context context, @Nullable String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return new Uri.Builder().scheme("res").path(String.valueOf(context.getResources().getIdentifier(str.toLowerCase().replace("-", JSMethod.NOT_SET), "drawable", context.getPackageName()))).build();
    }
}
