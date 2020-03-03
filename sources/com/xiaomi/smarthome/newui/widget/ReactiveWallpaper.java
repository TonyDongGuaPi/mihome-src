package com.xiaomi.smarthome.newui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.WindowManager;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.exif.makernotes.OlympusCameraSettingsMakernoteDirectory;
import com.drew.metadata.iptc.IptcDirectory;
import com.taobao.weex.el.parse.Operators;
import com.tencent.smtt.sdk.TbsListener;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.newui.widget.PercentEvaluator;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class ReactiveWallpaper extends View implements PercentEvaluator.OnPercentChangeListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20879a = "ReactiveWallpaper";
    private static final boolean b = false;
    private static final int e = -8860468;
    private static final int f = -591879;
    private static final int[] o = {R.drawable.top_0, R.drawable.top_1, R.drawable.top_2, R.drawable.bottom_0, R.drawable.bottom_1, R.drawable.bottom_2, R.drawable.bottom_3};
    private PercentEvaluator c;
    private volatile float d;
    private Paint g;
    private boolean h;
    private Point i;
    private Bitmap j;
    private final SparseIntArray k;
    private final SparseArray<Point> l;
    private final SparseArray<Point> m;
    private final SparseArray<Point> n;
    private final List<Circle> p;

    public ReactiveWallpaper(Context context) {
        this(context, (AttributeSet) null);
    }

    public ReactiveWallpaper(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = 0.5f;
        this.h = false;
        this.k = new SparseIntArray(7);
        this.l = new SparseArray<>(7);
        this.m = new SparseArray<>(7);
        this.n = new SparseArray<>(7);
        this.p = new CopyOnWriteArrayList();
        this.c = new PercentEvaluator();
        this.h = DarkModeCompat.a(getContext());
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.h) {
            canvas.drawColor(-1);
            return;
        }
        if (b()) {
            LogUtil.a("hzd1", "onGLDraw shouldReinitialized");
            c();
        }
        a(canvas);
        b(canvas);
    }

    private Point getDisplaySize() {
        if (this.i == null) {
            this.i = new Point();
            ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getRealSize(this.i);
        }
        return this.i;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        Point displaySize = getDisplaySize();
        setMeasuredDimension(displaySize.x, displaySize.y);
    }

    private void a(Canvas canvas) {
        Point displaySize = getDisplaySize();
        Bitmap bgBitmap = getBgBitmap();
        canvas.drawBitmap(bgBitmap, new Rect(0, 0, bgBitmap.getWidth(), getBgBitmap().getHeight()), new Rect(0, 0, displaySize.x, displaySize.y), this.g);
    }

    private Bitmap getBgBitmap() {
        if (this.j != null) {
            return this.j;
        }
        Point displaySize = getDisplaySize();
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, (float) displaySize.y, new int[]{e, f, f}, new float[]{-0.4f, 0.25f, 1.0f}, Shader.TileMode.CLAMP);
        if (this.g == null) {
            this.g = new Paint();
            this.g.setShader(linearGradient);
        }
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), displaySize.y, Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawRect(0.0f, 0.0f, (float) getWidth(), (float) displaySize.y, this.g);
        this.j = createBitmap;
        return createBitmap;
    }

    private void b(Canvas canvas) {
        for (Circle a2 : this.p) {
            a2.a(canvas, this.d);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void attach() {
        this.c.a(this, this);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        Log.d(f20879a, "onDetachedFromWindow: ");
        super.onDetachedFromWindow();
        this.c.a();
        a();
    }

    private void a() {
        LogUtil.a(f20879a, "detachMaterials: ");
        for (Circle next : this.p) {
            if (next.j != null) {
                next.j.recycle();
                next.j = null;
            }
        }
        if (this.j != null) {
            this.j.recycle();
            this.j = null;
        }
        this.k.clear();
        this.l.clear();
        this.m.clear();
        this.n.clear();
        this.p.clear();
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean b() {
        /*
            r4 = this;
            java.lang.String r0 = "ReactiveWallpaper"
            java.lang.String r1 = "shouldReinitialized: "
            android.util.Log.d(r0, r1)
            android.graphics.Bitmap r0 = r4.j
            r1 = 1
            if (r0 == 0) goto L_0x003f
            android.graphics.Bitmap r0 = r4.j
            boolean r0 = r0.isRecycled()
            if (r0 == 0) goto L_0x0015
            goto L_0x003f
        L_0x0015:
            java.util.List<com.xiaomi.smarthome.newui.widget.ReactiveWallpaper$Circle> r0 = r4.p
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x001e
            return r1
        L_0x001e:
            java.util.List<com.xiaomi.smarthome.newui.widget.ReactiveWallpaper$Circle> r0 = r4.p
            java.util.Iterator r0 = r0.iterator()
        L_0x0024:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x003d
            java.lang.Object r2 = r0.next()
            com.xiaomi.smarthome.newui.widget.ReactiveWallpaper$Circle r2 = (com.xiaomi.smarthome.newui.widget.ReactiveWallpaper.Circle) r2
            android.graphics.Bitmap r3 = r2.j
            if (r3 == 0) goto L_0x003c
            android.graphics.Bitmap r2 = r2.j
            boolean r2 = r2.isRecycled()
            if (r2 == 0) goto L_0x0024
        L_0x003c:
            return r1
        L_0x003d:
            r0 = 0
            return r0
        L_0x003f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.widget.ReactiveWallpaper.b():boolean");
    }

    public void onPercentChange(float f2) {
        if (Float.compare(f2, this.d) != 0 || Float.compare(f2, 0.5f) == 0) {
            this.d = f2;
            if (!this.h) {
                invalidate();
            }
        }
    }

    private void c() {
        a();
        LogUtil.a(f20879a, "initMaterials: ");
        int width = getWidth() / 360;
        this.l.put(0, new Point(width * 50, width * 15));
        this.l.put(1, new Point(width * TbsListener.ErrorCode.RENAME_SUCCESS, width * 73));
        this.l.put(2, new Point(width * 345, width * -15));
        int i2 = width * 181;
        this.l.put(3, new Point(0, i2));
        this.l.put(4, new Point(width * 92, width * 157));
        this.l.put(5, new Point(width * TbsListener.ErrorCode.UNLZMA_FAIURE, width * IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE));
        this.l.put(6, new Point(width * 294, i2));
        this.m.put(0, new Point(width * -758, width * -857));
        this.m.put(1, new Point(width * 616, width * -487));
        this.m.put(2, new Point(width * OlympusCameraSettingsMakernoteDirectory.X, width * -1979));
        this.m.put(3, new Point(width * -299, width * -189));
        this.m.put(4, new Point(width * -39, 0));
        this.m.put(5, new Point(width * ExifDirectoryBase.J, width * -85));
        this.m.put(6, new Point(width * IptcDirectory.q, width * 32));
        this.n.put(0, new Point(width * 245, width * 1188));
        int i3 = width * 129;
        this.n.put(1, new Point(i3, width * 745));
        this.n.put(2, new Point(width * 52, width * 2157));
        this.n.put(3, new Point(width * 199, width * 615));
        this.n.put(4, new Point(width * 148, width * 353));
        this.n.put(5, new Point(width * Opcodes.cW, width * 544));
        this.n.put(6, new Point(width * 273, width * 349));
        this.k.put(0, width * 70);
        this.k.put(1, width * 40);
        this.k.put(2, i3);
        this.k.put(3, width * 34);
        this.k.put(4, width * 19);
        this.k.put(5, width * 10);
        this.k.put(6, width * 12);
        for (int i4 = 0; i4 < 7; i4++) {
            Bitmap a2 = a(o[i4], this.k.get(i4) * 2, this.k.get(i4) * 2);
            Circle circle = new Circle(this.l.get(i4).x, this.l.get(i4).y, this.m.get(i4).x, this.m.get(i4).y, this.n.get(i4).x, this.n.get(i4).y, this.k.get(i4), a2);
            if (a2 != null) {
                this.p.add(circle);
            }
        }
    }

    private Bitmap a(@IdRes int i2, int i3, int i4) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;
        options.inJustDecodeBounds = false;
        options.outWidth = i3;
        options.outHeight = i4;
        if (Build.VERSION.SDK_INT >= 26) {
            options.outConfig = Bitmap.Config.ARGB_4444;
        }
        return BitmapFactory.decodeResource(getResources(), i2, options);
    }

    static class Circle {
        private static final float o = 0.03f;
        private static final int p = 60;

        /* renamed from: a  reason: collision with root package name */
        public List<Point> f20880a;
        public float b;
        int c;
        int d;
        int e;
        int f;
        PathMeasure g;
        Path h;
        Paint i;
        Bitmap j;
        public float k = 0.0f;
        public float l = 0.0f;
        private Paint m;
        private Paint n;

        public Circle(int i2, int i3, int i4, int i5, int i6, int i7, int i8, Bitmap bitmap) {
            this.c = i2;
            this.d = i3;
            this.e = i8;
            this.j = bitmap;
            this.i = new Paint();
            this.i.setAntiAlias(false);
            ArrayList<Point> arrayList = new ArrayList<>();
            arrayList.add(new Point(i4, i5));
            arrayList.add(new Point(i2, i3));
            arrayList.add(new Point(i6, i7));
            this.f20880a = arrayList;
            ArrayList arrayList2 = new ArrayList();
            for (Point point : arrayList) {
                arrayList2.add(new EPointF((float) point.x, (float) point.y));
            }
            this.h = PolyBezierPathUtil.a((List<EPointF>) arrayList2);
            this.g = new PathMeasure(this.h, false);
        }

        /* access modifiers changed from: package-private */
        public void a(Canvas canvas, float f2) {
            this.b = f2;
            float length = this.g.getLength() * a(f2);
            float[] fArr = new float[2];
            this.g.getPosTan(length, fArr, (float[]) null);
            this.k = fArr[0];
            this.l = fArr[1];
            if (this.j != null) {
                canvas.drawBitmap(this.j, this.k - ((float) this.e), this.l - ((float) this.e), this.i);
            }
        }

        private float a(float f2) {
            int compare = Float.compare(f2, 0.5f);
            if (compare == 0) {
                return f2;
            }
            if (compare <= 0) {
                return 0.5f - ((0.5f - f2) / 60.0f);
            }
            float f3 = f2 - 0.5f;
            return (o * f3 * f3) + 0.5f;
        }

        private void a(Canvas canvas) {
            a();
        }

        private void a() {
            if (this.n == null || this.m == null) {
                this.n = new Paint();
                this.n.setAntiAlias(true);
                this.n.setColor(-65536);
                this.n.setStrokeWidth(10.0f);
                this.n.setStyle(Paint.Style.STROKE);
                this.m = new Paint();
                this.m.setColor(-16777216);
                this.m.setStrokeWidth(20.0f);
            }
        }

        public String toString() {
            return "Circle{ctrlX=" + this.c + ", ctrlY=" + this.d + ", radius=" + this.e + Operators.BLOCK_END;
        }
    }
}
