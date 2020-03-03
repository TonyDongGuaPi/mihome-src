package in.cashify.otex.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import in.cashify.otex.R;
import java.util.ArrayList;
import java.util.Iterator;

public class CalibrationView extends View {

    /* renamed from: a  reason: collision with root package name */
    public Bitmap f2606a;
    public Bitmap b;
    public Bitmap c;
    public Bitmap d;
    public final ArrayList<d> e = new ArrayList<>();
    public final ArrayList<e> f = new ArrayList<>();
    public c g;
    public f h;
    public boolean i;
    public int j;
    public int k;
    public float l = -1.0f;
    public float m = -1.0f;
    public boolean n;
    public Matrix o;
    public Matrix p;
    public Matrix q;
    public Matrix r;
    public d s;
    public int t = 20;
    public int u = 20;
    public int v = 20;
    public int w = 20;
    public boolean x = true;

    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2607a = new int[b.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                in.cashify.otex.widget.CalibrationView$b[] r0 = in.cashify.otex.widget.CalibrationView.b.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f2607a = r0
                int[] r0 = f2607a     // Catch:{ NoSuchFieldError -> 0x0014 }
                in.cashify.otex.widget.CalibrationView$b r1 = in.cashify.otex.widget.CalibrationView.b.HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f2607a     // Catch:{ NoSuchFieldError -> 0x001f }
                in.cashify.otex.widget.CalibrationView$b r1 = in.cashify.otex.widget.CalibrationView.b.VERTICAL     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f2607a     // Catch:{ NoSuchFieldError -> 0x002a }
                in.cashify.otex.widget.CalibrationView$b r1 = in.cashify.otex.widget.CalibrationView.b.DIAGONAL_LEFT_TOP     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f2607a     // Catch:{ NoSuchFieldError -> 0x0035 }
                in.cashify.otex.widget.CalibrationView$b r1 = in.cashify.otex.widget.CalibrationView.b.DIAGONAL_RIGHT_TOP     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: in.cashify.otex.widget.CalibrationView.a.<clinit>():void");
        }
    }

    public enum b {
        HORIZONTAL,
        VERTICAL,
        CENTER_CELL,
        DIAGONAL_LEFT_TOP,
        DIAGONAL_RIGHT_TOP
    }

    public interface c {
        void b();

        void c();
    }

    public class d {

        /* renamed from: a  reason: collision with root package name */
        public final b f2609a;
        public final RectF b;
        public final RectF c;
        public final float d;
        public final float e;
        public boolean f;

        public d(CalibrationView calibrationView, float f2, float f3, float f4, float f5, b bVar) {
            this.d = (f4 / 2.0f) + f2;
            this.e = (f5 / 2.0f) + f3;
            float f6 = f4 + f2;
            float f7 = f5 + f3;
            this.b = new RectF(f2, f3, f6, f7);
            this.c = new RectF(f2 - 7.0f, f3 - 7.0f, f6 + 14.0f, f7 + 14.0f);
            this.f2609a = bVar;
        }
    }

    public class e {

        /* renamed from: a  reason: collision with root package name */
        public final float f2610a;
        public final float b;

        public e(CalibrationView calibrationView, float f, float f2) {
            this.f2610a = f;
            this.b = f2;
        }
    }

    public interface f {
        void a(boolean z);
    }

    public CalibrationView(Context context) {
        super(context);
        a(context);
    }

    public CalibrationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public CalibrationView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context);
    }

    public float a(double d2, double d3, double d4, double d5, double d6, double d7) {
        return (float) Math.toDegrees(Math.atan2(d3 - d7, d2 - d6) - Math.atan2(d5 - d7, d4 - d6));
    }

    public final void a() {
        float f2 = this.l + ((float) this.t);
        float f3 = this.m + ((float) this.w);
        this.s = new d(this, f2, f3, (((float) this.j) - f2) - f2, (((float) this.k) - f3) - f3, b.CENTER_CELL);
    }

    public final void a(float f2, float f3) {
        this.f.add(new e(this, f2, f3));
    }

    public final void a(int i2) {
        float f2 = this.l;
        float f3 = this.m;
        float f4 = (((float) this.k) - f3) - ((float) this.w);
        float f5 = f2;
        for (int i3 = 0; i3 < i2; i3++) {
            float f6 = f5;
            this.e.add(new d(this, f6, f3, (float) this.v, (float) this.w, b.HORIZONTAL));
            this.e.add(new d(this, f6, f4, (float) this.v, (float) this.w, b.HORIZONTAL));
            f5 += (float) this.v;
        }
    }

    public final void a(int i2, int i3) {
        if (this.x) {
            this.j = i2;
            this.k = i3;
            a();
            c();
            a((int) ((((float) this.j) - (this.l * 2.0f)) / ((float) this.v)));
            b((int) ((((float) this.k) - (this.m * 2.0f)) / ((float) this.u)));
            b();
            this.x = false;
        }
    }

    public final void a(Context context) {
        this.f2606a = BitmapFactory.decodeResource(getResources(), R.drawable.vertical_pattern);
        this.b = BitmapFactory.decodeResource(getResources(), R.drawable.vertical_pattern_remove);
        this.c = BitmapFactory.decodeResource(getResources(), R.drawable.horizontal_pattern);
        this.d = BitmapFactory.decodeResource(getResources(), R.drawable.horizontal_pattern_remove);
        this.t = this.f2606a.getWidth();
        this.u = this.f2606a.getHeight();
        this.v = this.c.getWidth();
        this.w = this.c.getHeight();
        if (context instanceof f) {
            this.h = (f) context;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0050 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(in.cashify.otex.widget.CalibrationView.d r8) {
        /*
            r7 = this;
            r0 = 2
            float[] r1 = new float[r0]
            float[] r0 = new float[r0]
            java.util.ArrayList<in.cashify.otex.widget.CalibrationView$e> r2 = r7.f
            java.util.Iterator r2 = r2.iterator()
        L_0x000b:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0053
            java.lang.Object r3 = r2.next()
            in.cashify.otex.widget.CalibrationView$e r3 = (in.cashify.otex.widget.CalibrationView.e) r3
            float r4 = r3.f2610a
            r5 = 0
            r0[r5] = r4
            r1[r5] = r4
            float r3 = r3.b
            r4 = 1
            r0[r4] = r3
            r1[r4] = r3
            in.cashify.otex.widget.CalibrationView$b r3 = r8.f2609a
            in.cashify.otex.widget.CalibrationView$b r6 = in.cashify.otex.widget.CalibrationView.b.DIAGONAL_LEFT_TOP
            if (r3 != r6) goto L_0x0037
            android.graphics.Matrix r3 = r7.o
        L_0x0033:
            r3.mapPoints(r1, r0)
            goto L_0x0042
        L_0x0037:
            in.cashify.otex.widget.CalibrationView$b r3 = r8.f2609a
            in.cashify.otex.widget.CalibrationView$b r6 = in.cashify.otex.widget.CalibrationView.b.DIAGONAL_RIGHT_TOP
            if (r3 != r6) goto L_0x0042
            android.graphics.Matrix r3 = r7.r
            goto L_0x0033
        L_0x0042:
            android.graphics.RectF r3 = r8.b
            r5 = r1[r5]
            r6 = r1[r4]
            boolean r3 = r3.contains(r5, r6)
            if (r3 == 0) goto L_0x000b
            boolean unused = r8.f = r4
        L_0x0053:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: in.cashify.otex.widget.CalibrationView.a(in.cashify.otex.widget.CalibrationView$d):void");
    }

    public final void a(boolean z) {
        f fVar = this.h;
        if (fVar != null) {
            fVar.a(z);
        }
    }

    public final void b() {
        float f2 = (float) this.t;
        float f3 = (float) this.u;
        int height = (int) (this.s.b.height() / f3);
        float b2 = this.s.d - (f2 / 2.0f);
        float height2 = (this.s.b.top + ((this.s.b.height() % f3) / 2.0f)) - f3;
        for (int i2 = 0; i2 < height + 1; i2++) {
            float f4 = b2;
            float f5 = height2;
            float f6 = f2;
            float f7 = f3;
            this.e.add(new d(this, f4, f5, f6, f7, b.DIAGONAL_LEFT_TOP));
            this.e.add(new d(this, f4, f5, f6, f7, b.DIAGONAL_RIGHT_TOP));
            height2 += f3;
        }
    }

    public final void b(int i2) {
        float f2 = this.l;
        int i3 = (int) f2;
        int i4 = (int) ((((float) this.j) - f2) - ((float) this.t));
        int i5 = i2;
        int i6 = (int) this.m;
        for (int i7 = 0; i7 < i5; i7++) {
            float f3 = (float) i6;
            this.e.add(new d(this, (float) i3, f3, (float) this.t, (float) this.u, b.VERTICAL));
            this.e.add(new d(this, (float) i4, f3, (float) this.t, (float) this.u, b.VERTICAL));
            i6 += this.u;
        }
    }

    public final void c() {
        float a2 = a((double) (this.s.b.left + ((float) (this.t / 2))), (double) this.s.b.top, (double) (this.s.b.right - ((float) (this.t / 2))), (double) this.s.b.top, (double) (this.s.b.right - ((float) (this.t / 2))), (double) this.s.b.bottom);
        this.p = new Matrix();
        this.o = new Matrix();
        this.p.preRotate(a2, this.s.d, this.s.e);
        this.p.invert(this.o);
        this.q = new Matrix();
        this.r = new Matrix();
        this.q.preRotate(-a2, this.s.d, this.s.e);
        this.q.invert(this.r);
    }

    public final void d() {
        Iterator<d> it = this.e.iterator();
        while (it.hasNext()) {
            boolean unused = it.next().f = false;
        }
    }

    public void e() {
        this.f.clear();
        d();
        invalidate();
    }

    public int getCompletionPercent() {
        ArrayList<d> arrayList = this.e;
        int i2 = 0;
        if (arrayList == null || arrayList.isEmpty()) {
            return 0;
        }
        Iterator<d> it = this.e.iterator();
        while (it.hasNext()) {
            if (it.next().f) {
                i2++;
            }
        }
        return (i2 * 100) / this.e.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0046, code lost:
        r8.drawBitmap(r3, (android.graphics.Rect) null, in.cashify.otex.widget.CalibrationView.d.a(r1), (android.graphics.Paint) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008c, code lost:
        r8.setMatrix(r5);
        r8.drawBitmap(r7.b, (android.graphics.Rect) null, in.cashify.otex.widget.CalibrationView.d.f(r4), (android.graphics.Paint) null);
        r8.restore();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a1, code lost:
        r8.drawBitmap(r5, (android.graphics.Rect) null, in.cashify.otex.widget.CalibrationView.d.f(r4), (android.graphics.Paint) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0031, code lost:
        r8.setMatrix(r3);
        r8.drawBitmap(r7.f2606a, (android.graphics.Rect) null, in.cashify.otex.widget.CalibrationView.d.a(r1), (android.graphics.Paint) null);
        r8.restore();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDraw(android.graphics.Canvas r8) {
        /*
            r7 = this;
            super.onDraw(r8)
            java.util.ArrayList<in.cashify.otex.widget.CalibrationView$d> r0 = r7.e
            java.util.Iterator r0 = r0.iterator()
        L_0x0009:
            boolean r1 = r0.hasNext()
            r2 = 0
            if (r1 == 0) goto L_0x004e
            java.lang.Object r1 = r0.next()
            in.cashify.otex.widget.CalibrationView$d r1 = (in.cashify.otex.widget.CalibrationView.d) r1
            int[] r3 = in.cashify.otex.widget.CalibrationView.a.f2607a
            in.cashify.otex.widget.CalibrationView$b r4 = r1.f2609a
            int r4 = r4.ordinal()
            r3 = r3[r4]
            switch(r3) {
                case 1: goto L_0x0044;
                case 2: goto L_0x0041;
                case 3: goto L_0x002c;
                case 4: goto L_0x0026;
                default: goto L_0x0025;
            }
        L_0x0025:
            goto L_0x0009
        L_0x0026:
            r8.save()
            android.graphics.Matrix r3 = r7.q
            goto L_0x0031
        L_0x002c:
            r8.save()
            android.graphics.Matrix r3 = r7.p
        L_0x0031:
            r8.setMatrix(r3)
            android.graphics.Bitmap r3 = r7.f2606a
            android.graphics.RectF r1 = r1.b
            r8.drawBitmap(r3, r2, r1, r2)
            r8.restore()
            goto L_0x0009
        L_0x0041:
            android.graphics.Bitmap r3 = r7.f2606a
            goto L_0x0046
        L_0x0044:
            android.graphics.Bitmap r3 = r7.c
        L_0x0046:
            android.graphics.RectF r1 = r1.b
            r8.drawBitmap(r3, r2, r1, r2)
            goto L_0x0009
        L_0x004e:
            java.util.ArrayList<in.cashify.otex.widget.CalibrationView$d> r0 = r7.e
            java.util.Iterator r0 = r0.iterator()
            r1 = 0
            r3 = 1
        L_0x0056:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x00b2
            java.lang.Object r4 = r0.next()
            in.cashify.otex.widget.CalibrationView$d r4 = (in.cashify.otex.widget.CalibrationView.d) r4
            boolean r5 = r4.f
            if (r5 != 0) goto L_0x006b
            r7.a((in.cashify.otex.widget.CalibrationView.d) r4)
        L_0x006b:
            boolean r5 = r4.f
            if (r5 == 0) goto L_0x00a8
            int[] r5 = in.cashify.otex.widget.CalibrationView.a.f2607a
            in.cashify.otex.widget.CalibrationView$b r6 = r4.f2609a
            int r6 = r6.ordinal()
            r5 = r5[r6]
            switch(r5) {
                case 1: goto L_0x009f;
                case 2: goto L_0x009c;
                case 3: goto L_0x0087;
                case 4: goto L_0x0081;
                default: goto L_0x0080;
            }
        L_0x0080:
            goto L_0x00a8
        L_0x0081:
            r8.save()
            android.graphics.Matrix r5 = r7.q
            goto L_0x008c
        L_0x0087:
            r8.save()
            android.graphics.Matrix r5 = r7.p
        L_0x008c:
            r8.setMatrix(r5)
            android.graphics.Bitmap r5 = r7.b
            android.graphics.RectF r6 = r4.c
            r8.drawBitmap(r5, r2, r6, r2)
            r8.restore()
            goto L_0x00a8
        L_0x009c:
            android.graphics.Bitmap r5 = r7.b
            goto L_0x00a1
        L_0x009f:
            android.graphics.Bitmap r5 = r7.d
        L_0x00a1:
            android.graphics.RectF r6 = r4.c
            r8.drawBitmap(r5, r2, r6, r2)
        L_0x00a8:
            if (r3 == 0) goto L_0x0056
            boolean r4 = r4.f
            if (r4 != 0) goto L_0x0056
            r3 = 0
            goto L_0x0056
        L_0x00b2:
            java.util.ArrayList<in.cashify.otex.widget.CalibrationView$e> r8 = r7.f
            r8.clear()
            if (r3 == 0) goto L_0x00c6
            in.cashify.otex.widget.CalibrationView$c r8 = r7.g
            if (r8 == 0) goto L_0x00c6
            boolean r0 = r7.i
            if (r0 == 0) goto L_0x00c6
            r8.c()
            r7.i = r1
        L_0x00c6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: in.cashify.otex.widget.CalibrationView.onDraw(android.graphics.Canvas):void");
    }

    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        a(i4 - i2, i5 - i3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        if (r0 != 2) goto L_0x0038;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            int r0 = r4.getAction()
            r1 = 1
            if (r0 == 0) goto L_0x0016
            if (r0 == r1) goto L_0x000d
            r2 = 2
            if (r0 == r2) goto L_0x002a
            goto L_0x0038
        L_0x000d:
            r3.i = r1
            r3.invalidate()
            r3.a((boolean) r1)
            goto L_0x0038
        L_0x0016:
            r0 = 0
            r3.a((boolean) r0)
            boolean r2 = r3.n
            if (r2 != 0) goto L_0x0028
            in.cashify.otex.widget.CalibrationView$c r4 = r3.g
            if (r4 == 0) goto L_0x0025
            r4.b()
        L_0x0025:
            r3.n = r1
            return r1
        L_0x0028:
            r3.i = r0
        L_0x002a:
            float r0 = r4.getX()
            float r4 = r4.getY()
            r3.a((float) r0, (float) r4)
            r3.invalidate()
        L_0x0038:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: in.cashify.otex.widget.CalibrationView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setOnCalibrationDoneListener(c cVar) {
        this.g = cVar;
    }
}
