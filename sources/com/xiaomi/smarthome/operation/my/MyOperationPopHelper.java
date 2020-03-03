package com.xiaomi.smarthome.operation.my;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.stat.STAT;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyOperationPopHelper extends ConstraintLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21138a = "MyOperationPopHelper";
    private Disposable b;
    private Paint c;
    private Bitmap d;
    private PorterDuffXfermode e;
    private Rect f;
    private Rect g;
    private List<String> h;
    private boolean i;
    private boolean j;
    private Bitmap k;
    private Subject<Integer> l;

    public MyOperationPopHelper(Context context) {
        this(context, (AttributeSet) null);
    }

    public MyOperationPopHelper(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MyOperationPopHelper(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.i = true;
        this.j = false;
        this.l = null;
        setLayerType(2, (Paint) null);
        this.c = new Paint();
        this.c.setAntiAlias(true);
        this.e = new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP);
    }

    public void setClipBannerOnly(boolean z) {
        if (this.j != z) {
            this.j = z;
            invalidate();
        }
    }

    private Bitmap getClipBg() {
        if (this.j) {
            if (this.k == null) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inScaled = true;
                options.inPreferredConfig = Bitmap.Config.ALPHA_8;
                this.k = BitmapFactory.decodeResource(getResources(), R.drawable.my_operation_duffmode_banner, options);
                this.f = new Rect(0, 0, this.k.getWidth(), this.k.getHeight());
            }
            return this.k;
        }
        if (this.d == null) {
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inScaled = true;
            options2.inPreferredConfig = Bitmap.Config.ALPHA_8;
            this.d = BitmapFactory.decodeResource(getResources(), R.drawable.my_operation_duffmode_top, options2);
            this.f = new Rect(0, 0, this.d.getWidth(), this.d.getHeight());
        }
        return this.d;
    }

    private Subject<Integer> a() {
        if (this.l == null) {
            this.l = PublishSubject.create();
            this.b = this.l.debounce(200, TimeUnit.MICROSECONDS).subscribe(new Consumer() {
                public final void accept(Object obj) {
                    MyOperationPopHelper.this.a((Integer) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        }
        return this.l;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Integer num) throws Exception {
        if (this.h != null) {
            for (String next : this.h) {
                STAT.e.g(next);
                STAT.e.h(next);
            }
        }
    }

    public void dispatchDraw(Canvas canvas) {
        if (this.i) {
            canvas.save();
            super.dispatchDraw(canvas);
            this.c.setXfermode(this.e);
            canvas.drawBitmap(getClipBg(), this.f, this.g, this.c);
            this.c.setXfermode((Xfermode) null);
            canvas.restore();
            return;
        }
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.g = new Rect(0, 0, i2, i3);
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (i2 == 0 && isShown()) {
            a().onNext(1);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.b != null) {
            this.b.dispose();
        }
        if (this.l != null) {
            this.l = null;
        }
    }

    public void attachLogInfos(List<String> list) {
        this.h = list;
    }

    public void logAdPopUp() {
        a().onNext(1);
    }

    public void setClipBounds(boolean z) {
        if (z != this.i) {
            this.i = z;
            invalidate();
        }
    }
}
