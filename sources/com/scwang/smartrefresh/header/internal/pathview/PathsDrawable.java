package com.scwang.smartrefresh.header.internal.pathview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.support.annotation.NonNull;
import com.scwang.smartrefresh.layout.internal.PaintDrawable;
import java.util.ArrayList;
import java.util.List;

public class PathsDrawable extends PaintDrawable {
    protected static final Region g = new Region();
    protected static final Region h = new Region(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

    /* renamed from: a  reason: collision with root package name */
    protected int f8757a = 1;
    protected int b = 1;
    protected int c = 0;
    protected int d = 0;
    protected int e;
    protected int f;
    protected List<Path> i;
    protected List<Integer> j;
    protected List<Path> k;
    protected List<String> l;
    private Bitmap n;
    private boolean o;

    /* access modifiers changed from: protected */
    public boolean a() {
        Integer num;
        Integer num2;
        Integer num3;
        int i2;
        int i3;
        int i4;
        int i5;
        Integer num4 = null;
        if (this.i != null) {
            num3 = null;
            num2 = null;
            num = null;
            for (Path path : this.i) {
                g.setPath(path, h);
                Rect bounds = g.getBounds();
                num3 = Integer.valueOf(Math.min(num3 == null ? bounds.top : num3.intValue(), bounds.top));
                num4 = Integer.valueOf(Math.min(num4 == null ? bounds.left : num4.intValue(), bounds.left));
                num2 = Integer.valueOf(Math.max(num2 == null ? bounds.right : num2.intValue(), bounds.right));
                num = Integer.valueOf(Math.max(num == null ? bounds.bottom : num.intValue(), bounds.bottom));
            }
        } else {
            num3 = null;
            num2 = null;
            num = null;
        }
        if (num4 == null) {
            i2 = 0;
        } else {
            i2 = num4.intValue();
        }
        this.c = i2;
        if (num3 == null) {
            i3 = 0;
        } else {
            i3 = num3.intValue();
        }
        this.d = i3;
        if (num2 == null) {
            i4 = 0;
        } else {
            i4 = num2.intValue() - this.c;
        }
        this.f8757a = i4;
        if (num == null) {
            i5 = 0;
        } else {
            i5 = num.intValue() - this.d;
        }
        this.b = i5;
        if (this.e == 0) {
            this.e = this.f8757a;
        }
        if (this.f == 0) {
            this.f = this.b;
        }
        Rect bounds2 = getBounds();
        if (this.f8757a == 0 || this.b == 0) {
            if (this.e == 0) {
                this.e = 1;
            }
            if (this.f == 0) {
                this.f = 1;
            }
            this.b = 1;
            this.f8757a = 1;
            return false;
        }
        super.setBounds(bounds2.left, bounds2.top, bounds2.left + this.f8757a, bounds2.top + this.b);
        return true;
    }

    public void setBounds(int i2, int i3, int i4, int i5) {
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        if (this.k == null || this.k.size() <= 0 || (i6 == this.f8757a && i7 == this.b)) {
            super.setBounds(i2, i3, i4, i5);
            return;
        }
        int i8 = this.c;
        int i9 = this.d;
        float f2 = (float) i6;
        float f3 = (float) i7;
        this.i = PathParser.a((f2 * 1.0f) / ((float) this.e), (f3 * 1.0f) / ((float) this.f), this.k, this.l);
        if (!a()) {
            this.f8757a = i6;
            this.b = i7;
            this.c = (int) (((((float) i8) * 1.0f) * f2) / ((float) this.e));
            this.d = (int) (((((float) i9) * 1.0f) * f3) / ((float) this.f));
            super.setBounds(i2, i3, i4, i5);
        }
    }

    public void setBounds(@NonNull Rect rect) {
        setBounds(rect.left, rect.top, rect.right, rect.bottom);
    }

    public boolean a(String... strArr) {
        this.f = 0;
        this.e = 0;
        this.l = new ArrayList();
        ArrayList arrayList = new ArrayList();
        this.k = arrayList;
        this.i = arrayList;
        for (String str : strArr) {
            this.l.add(str);
            this.k.add(PathParser.a(str));
        }
        return a();
    }

    public void a(int i2, int i3, int i4, int i5) {
        this.c = i2;
        this.d = i3;
        this.f8757a = i4;
        this.e = i4;
        this.b = i5;
        this.f = i5;
        Rect bounds = getBounds();
        super.setBounds(bounds.left, bounds.top, bounds.left + i4, bounds.top + i5);
    }

    public void a(int... iArr) {
        this.j = new ArrayList();
        for (int valueOf : iArr) {
            this.j.add(Integer.valueOf(valueOf));
        }
    }

    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();
        if (this.m.getAlpha() == 255) {
            canvas.save();
            canvas.translate((float) (bounds.left - this.c), (float) (bounds.top - this.d));
            if (this.i != null) {
                for (int i2 = 0; i2 < this.i.size(); i2++) {
                    if (this.j != null && i2 < this.j.size()) {
                        this.m.setColor(this.j.get(i2).intValue());
                    }
                    canvas.drawPath(this.i.get(i2), this.m);
                }
                this.m.setAlpha(255);
            }
            canvas.restore();
            return;
        }
        a(width, height);
        if (this.o) {
            this.n.eraseColor(0);
            a(new Canvas(this.n));
            this.o = false;
        }
        canvas.drawBitmap(this.n, (float) bounds.left, (float) bounds.top, this.m);
    }

    public void a(int i2) {
        Rect bounds = getBounds();
        float width = (((float) i2) * 1.0f) / ((float) bounds.width());
        setBounds((int) (((float) bounds.left) * width), (int) (((float) bounds.top) * width), (int) (((float) bounds.right) * width), (int) (((float) bounds.bottom) * width));
    }

    public void b(int i2) {
        Rect bounds = getBounds();
        float height = (((float) i2) * 1.0f) / ((float) bounds.height());
        setBounds((int) (((float) bounds.left) * height), (int) (((float) bounds.top) * height), (int) (((float) bounds.right) * height), (int) (((float) bounds.bottom) * height));
    }

    private void a(Canvas canvas) {
        canvas.translate((float) (-this.c), (float) (-this.d));
        if (this.i != null) {
            for (int i2 = 0; i2 < this.i.size(); i2++) {
                if (this.j != null && i2 < this.j.size()) {
                    this.m.setColor(this.j.get(i2).intValue());
                }
                canvas.drawPath(this.i.get(i2), this.m);
            }
        }
    }

    private void a(int i2, int i3) {
        if (this.n == null || i2 != this.n.getWidth() || i3 != this.n.getHeight()) {
            this.n = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
            this.o = true;
        }
    }
}
