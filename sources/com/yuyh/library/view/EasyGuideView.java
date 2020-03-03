package com.yuyh.library.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.yuyh.library.bean.HighlightArea;
import java.util.List;

public class EasyGuideView extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private int f2567a;
    private int b;
    private int c;
    private float d;
    private Paint e;
    private Bitmap f;
    private RectF g;
    private RectF h;
    private Canvas i;
    private List<HighlightArea> j;
    private Xfermode k;

    public EasyGuideView(Context context) {
        this(context, (AttributeSet) null);
    }

    public EasyGuideView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EasyGuideView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.c = -1442840576;
        this.h = new RectF();
        Point point = new Point();
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getSize(point);
        this.f2567a = point.x;
        this.b = point.y;
        a();
    }

    private void a() {
        b();
        this.g = new RectF();
        this.k = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        setWillNotDraw(false);
        setClickable(true);
    }

    private void b() {
        this.e = new Paint();
        this.e.setAntiAlias(true);
        this.e.setColor(this.c);
        this.e.setMaskFilter(new BlurMaskFilter(10.0f, BlurMaskFilter.Blur.INNER));
    }

    private void c() {
        if (this.g.width() <= 0.0f || this.g.height() <= 0.0f) {
            this.f = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
        } else {
            this.f = Bitmap.createBitmap((int) this.g.width(), (int) this.g.height(), Bitmap.Config.ARGB_8888);
        }
        this.d = Math.max(Math.max(this.g.left, this.g.top), Math.max(((float) this.f2567a) - this.g.right, ((float) this.b) - this.g.bottom));
        this.h.left = this.g.left - (this.d / 2.0f);
        this.h.top = this.g.top - (this.d / 2.0f);
        this.h.right = this.g.right + (this.d / 2.0f);
        this.h.bottom = this.g.bottom + (this.d / 2.0f);
        this.i = new Canvas(this.f);
        this.i.drawColor(this.c);
    }

    public void setHightLightAreas(List<HighlightArea> list) {
        this.j = list;
        if (list != null && !list.isEmpty()) {
            for (HighlightArea a2 : list) {
                this.g.union(a2.a());
            }
        }
        c();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.j != null && this.j.size() > 0) {
            this.e.setXfermode(this.k);
            this.e.setStyle(Paint.Style.FILL);
            for (HighlightArea next : this.j) {
                RectF a2 = next.a();
                a2.offset(-this.g.left, -this.g.top);
                switch (next.b) {
                    case 0:
                        this.i.drawCircle(a2.centerX(), a2.centerY(), (float) (Math.min(next.f2563a.getWidth(), next.f2563a.getHeight()) / 2), this.e);
                        break;
                    case 1:
                        this.i.drawRect(a2, this.e);
                        break;
                    case 2:
                        this.i.drawOval(a2, this.e);
                        break;
                }
            }
            canvas.drawBitmap(this.f, this.g.left, this.g.top, (Paint) null);
            this.e.setXfermode((Xfermode) null);
            this.e.setStyle(Paint.Style.STROKE);
            this.e.setStrokeWidth(this.d + 0.1f);
            canvas.drawRect(this.h, this.e);
        }
    }

    public void recyclerBitmap() {
        if (this.f != null) {
            this.f.recycle();
            this.f = null;
        }
    }
}
