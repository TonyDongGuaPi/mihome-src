package com.xiaomi.smarthome.framework.plugin.rn.nativemodule.viewshot;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.zip.Deflater;
import javax.annotation.Nullable;

public class ViewShot implements UIBlock {

    /* renamed from: a  reason: collision with root package name */
    public static final String f17488a = "E_UNABLE_TO_SNAPSHOT";
    private static final String b = "ViewShot";
    private static final int c = 65536;
    private static final int d = 4;
    private static byte[] e = new byte[65536];
    private static final Object r = new Object();
    private static final Set<Bitmap> s = Collections.newSetFromMap(new WeakHashMap());
    private final int f;
    private final String g;
    @Formats
    private final int h;
    private final double i;
    private final Integer j;
    private final Integer k;
    private final File l;
    @Results
    private final String m;
    private final Promise n;
    private final Boolean o;
    private final ReactApplicationContext p;
    private final Activity q;

    public @interface Formats {
        public static final int JPEG = 0;
        public static final int PNG = 1;
        public static final int RAW = -1;
        public static final int WEBP = 2;
        public static final Bitmap.CompressFormat[] mapping = {Bitmap.CompressFormat.JPEG, Bitmap.CompressFormat.PNG, Bitmap.CompressFormat.WEBP};
    }

    public @interface Results {
        public static final String BASE_64 = "base64";
        public static final String DATA_URI = "data-uri";
        public static final String TEMP_FILE = "tmpfile";
        public static final String ZIP_BASE_64 = "zip-base64";
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [A, T] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T extends A, A> T a(A r0) {
        /*
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.viewshot.ViewShot.a(java.lang.Object):java.lang.Object");
    }

    public ViewShot(int i2, String str, @Formats int i3, double d2, @Nullable Integer num, @Nullable Integer num2, File file, @Results String str2, Boolean bool, ReactApplicationContext reactApplicationContext, Activity activity, Promise promise) {
        this.f = i2;
        this.g = str;
        this.h = i3;
        this.i = d2;
        this.j = num;
        this.k = num2;
        this.l = file;
        this.m = str2;
        this.o = bool;
        this.p = reactApplicationContext;
        this.q = activity;
        this.n = promise;
    }

    public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
        View view;
        if (this.f == -1) {
            view = this.q.getWindow().getDecorView().findViewById(16908290);
        } else {
            view = nativeViewHierarchyManager.resolveView(this.f);
        }
        if (view == null) {
            String str = b;
            Log.e(str, "No view found with reactTag: " + this.f, new AssertionError());
            Promise promise = this.n;
            promise.reject(f17488a, "No view found with reactTag: " + this.f);
            return;
        }
        try {
            ReusableByteArrayOutputStream reusableByteArrayOutputStream = new ReusableByteArrayOutputStream(e);
            reusableByteArrayOutputStream.b(f(view));
            e = reusableByteArrayOutputStream.a();
            if (Results.TEMP_FILE.equals(this.m) && -1 == this.h) {
                b(view);
            } else if (!Results.TEMP_FILE.equals(this.m) || -1 == this.h) {
                if (!Results.BASE_64.equals(this.m)) {
                    if (!Results.ZIP_BASE_64.equals(this.m)) {
                        if (Results.DATA_URI.equals(this.m)) {
                            c(view);
                            return;
                        }
                        return;
                    }
                }
                d(view);
            } else {
                a(view);
            }
        } catch (Throwable th) {
            Log.e(b, "Failed to capture view snapshot", th);
            this.n.reject(f17488a, "Failed to capture view snapshot");
        }
    }

    private void a(@NonNull View view) throws IOException {
        a(view, (OutputStream) new FileOutputStream(this.l));
        this.n.resolve(Uri.fromFile(this.l).toString());
    }

    private void b(@NonNull View view) throws IOException {
        String uri = Uri.fromFile(this.l).toString();
        FileOutputStream fileOutputStream = new FileOutputStream(this.l);
        ReusableByteArrayOutputStream reusableByteArrayOutputStream = new ReusableByteArrayOutputStream(e);
        Point a2 = a(view, (OutputStream) reusableByteArrayOutputStream);
        e = reusableByteArrayOutputStream.a();
        int size = reusableByteArrayOutputStream.size();
        fileOutputStream.write(String.format(Locale.US, "%d:%d|", new Object[]{Integer.valueOf(a2.x), Integer.valueOf(a2.y)}).getBytes(Charset.forName("US-ASCII")));
        fileOutputStream.write(e, 0, size);
        fileOutputStream.close();
        this.n.resolve(uri);
    }

    private void c(@NonNull View view) throws IOException {
        ReusableByteArrayOutputStream reusableByteArrayOutputStream = new ReusableByteArrayOutputStream(e);
        a(view, (OutputStream) reusableByteArrayOutputStream);
        e = reusableByteArrayOutputStream.a();
        String encodeToString = Base64.encodeToString(e, 0, reusableByteArrayOutputStream.size(), 2);
        String str = "jpg".equals(this.g) ? "jpeg" : this.g;
        Promise promise = this.n;
        promise.resolve("data:image/" + str + ";base64," + encodeToString);
    }

    private void d(@NonNull View view) throws IOException {
        String str;
        boolean z = -1 == this.h;
        boolean equals = Results.ZIP_BASE_64.equals(this.m);
        ReusableByteArrayOutputStream reusableByteArrayOutputStream = new ReusableByteArrayOutputStream(e);
        Point a2 = a(view, (OutputStream) reusableByteArrayOutputStream);
        e = reusableByteArrayOutputStream.a();
        int size = reusableByteArrayOutputStream.size();
        String format = String.format(Locale.US, "%d:%d|", new Object[]{Integer.valueOf(a2.x), Integer.valueOf(a2.y)});
        if (!z) {
            format = "";
        }
        if (equals) {
            Deflater deflater = new Deflater();
            deflater.setInput(e, 0, size);
            deflater.finish();
            ReusableByteArrayOutputStream reusableByteArrayOutputStream2 = new ReusableByteArrayOutputStream(new byte[32]);
            byte[] bArr = new byte[1024];
            while (!deflater.finished()) {
                reusableByteArrayOutputStream2.write(bArr, 0, deflater.deflate(bArr));
            }
            str = format + Base64.encodeToString(reusableByteArrayOutputStream2.a(), 0, reusableByteArrayOutputStream2.size(), 2);
        } else {
            str = format + Base64.encodeToString(e, 0, size, 2);
        }
        this.n.resolve(str);
    }

    @NonNull
    private List<View> e(@NonNull View view) {
        if (!(view instanceof ViewGroup)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(view);
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            arrayList2.addAll(e(viewGroup.getChildAt(i2)));
        }
        return arrayList2;
    }

    private Point a(@NonNull View view, @NonNull OutputStream outputStream) throws IOException {
        try {
            DebugViews.a(b, DebugViews.a(this.q));
            return b(view, outputStream);
        } finally {
            outputStream.close();
        }
    }

    private Point b(@NonNull View view, @NonNull OutputStream outputStream) {
        Bitmap bitmap;
        int width = view.getWidth();
        int height = view.getHeight();
        if (width <= 0 || height <= 0) {
            throw new RuntimeException("Impossible to snapshot the view: view is invalid");
        }
        if (this.o.booleanValue()) {
            ScrollView scrollView = (ScrollView) view;
            int i2 = 0;
            for (int i3 = 0; i3 < scrollView.getChildCount(); i3++) {
                i2 += scrollView.getChildAt(i3).getHeight();
            }
            height = i2;
        }
        Point point = new Point(width, height);
        Bitmap a2 = a(width, height);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        Canvas canvas = new Canvas(a2);
        view.draw(canvas);
        for (View next : e(view)) {
            if ((next instanceof TextureView) && next.getVisibility() == 0) {
                TextureView textureView = (TextureView) next;
                textureView.setOpaque(false);
                Bitmap bitmap2 = textureView.getBitmap(b(next.getWidth(), next.getHeight()));
                int save = canvas.save();
                a(canvas, view, next);
                canvas.drawBitmap(bitmap2, 0.0f, 0.0f, paint);
                canvas.restoreToCount(save);
                a(bitmap2);
            }
        }
        if (this.j == null || this.k == null || (this.j.intValue() == width && this.k.intValue() == height)) {
            bitmap = a2;
        } else {
            bitmap = Bitmap.createScaledBitmap(a2, this.j.intValue(), this.k.intValue(), true);
            a(a2);
        }
        if (-1 != this.h || !(outputStream instanceof ReusableByteArrayOutputStream)) {
            bitmap.compress(Formats.mapping[this.h], (int) (this.i * 100.0d), outputStream);
        } else {
            int i4 = width * height * 4;
            ReusableByteArrayOutputStream reusableByteArrayOutputStream = (ReusableByteArrayOutputStream) a(outputStream);
            bitmap.copyPixelsToBuffer(reusableByteArrayOutputStream.a(i4));
            reusableByteArrayOutputStream.b(i4);
        }
        a(bitmap);
        return point;
    }

    @NonNull
    private Matrix a(Canvas canvas, @NonNull View view, @NonNull View view2) {
        Matrix matrix = new Matrix();
        LinkedList linkedList = new LinkedList();
        View view3 = view2;
        do {
            linkedList.add(view3);
            view3 = (View) view3.getParent();
        } while (view3 != view);
        Collections.reverse(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            View view4 = (View) it.next();
            canvas.save();
            int i2 = 0;
            float left = ((float) (view4.getLeft() + (view4 != view2 ? view4.getPaddingLeft() : 0))) + view4.getTranslationX();
            int top = view4.getTop();
            if (view4 != view2) {
                i2 = view4.getPaddingTop();
            }
            float translationY = ((float) (top + i2)) + view4.getTranslationY();
            canvas.translate(left, translationY);
            canvas.rotate(view4.getRotation(), view4.getPivotX(), view4.getPivotY());
            canvas.scale(view4.getScaleX(), view4.getScaleY());
            matrix.postTranslate(left, translationY);
            matrix.postRotate(view4.getRotation(), view4.getPivotX(), view4.getPivotY());
            matrix.postScale(view4.getScaleX(), view4.getScaleY());
        }
        return matrix;
    }

    private static int f(@NonNull View view) {
        return Math.min(view.getWidth() * view.getHeight() * 4, 32);
    }

    private static void a(@NonNull Bitmap bitmap) {
        synchronized (r) {
            s.add(bitmap);
        }
    }

    @NonNull
    private static Bitmap a(int i2, int i3) {
        synchronized (r) {
            for (Bitmap next : s) {
                if (next.getWidth() == i2 && next.getHeight() == i3) {
                    s.remove(next);
                    next.eraseColor(0);
                    return next;
                }
            }
            return Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        }
    }

    @NonNull
    private static Bitmap b(int i2, int i3) {
        synchronized (r) {
            for (Bitmap next : s) {
                if (next.getWidth() == i2 && next.getHeight() == i3) {
                    s.remove(next);
                    next.eraseColor(0);
                    return next;
                }
            }
            return Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        }
    }

    public static class ReusableByteArrayOutputStream extends ByteArrayOutputStream {

        /* renamed from: a  reason: collision with root package name */
        private static final int f17489a = 2147483639;

        public ReusableByteArrayOutputStream(@NonNull byte[] bArr) {
            super(0);
            this.buf = bArr;
        }

        public byte[] a() {
            return this.buf;
        }

        @NonNull
        public ByteBuffer a(int i) {
            if (this.buf.length < i) {
                c(i);
            }
            return ByteBuffer.wrap(this.buf);
        }

        public void b(int i) {
            this.count = i;
        }

        /* access modifiers changed from: protected */
        public void c(int i) {
            int length = this.buf.length << 1;
            if (length - i < 0) {
                length = i;
            }
            if (length - f17489a > 0) {
                length = d(i);
            }
            this.buf = Arrays.copyOf(this.buf, length);
        }

        protected static int d(int i) {
            if (i < 0) {
                throw new OutOfMemoryError();
            } else if (i > f17489a) {
                return Integer.MAX_VALUE;
            } else {
                return f17489a;
            }
        }
    }
}
