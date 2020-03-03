package com.h6ah4i.android.widget.advrecyclerview.swipeable;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Interpolator;
import com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils;
import java.lang.ref.WeakReference;

class RemovingItemDecorator extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5725a = "RemovingItemDecorator";
    private static final int b = 0;
    private static final int c = 1;
    private static final long d = 0;
    private RecyclerView e;
    private RecyclerView.ViewHolder f;
    private final long g;
    private final Rect h = new Rect();
    private int i;
    private int j;
    private long k;
    private final long l;
    private final long m;
    private Interpolator n;
    private Drawable o;
    private final boolean p;
    private int q;

    public RemovingItemDecorator(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i2, long j2, long j3) {
        boolean z = false;
        this.q = 0;
        this.e = recyclerView;
        this.f = viewHolder;
        this.g = viewHolder.getItemId();
        this.p = (i2 == 2 || i2 == 4) ? true : z;
        this.l = j2 + 0;
        this.m = j3;
        this.i = (int) (ViewCompat.getTranslationX(viewHolder.itemView) + 0.5f);
        this.j = (int) (ViewCompat.getTranslationY(viewHolder.itemView) + 0.5f);
        CustomRecyclerViewUtils.b(this.f.itemView, this.h);
    }

    public void a(Interpolator interpolator) {
        this.n = interpolator;
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        long a2 = a(this.k);
        a(canvas, this.o, c(a2));
        if (this.g == this.f.getItemId()) {
            this.i = (int) (ViewCompat.getTranslationX(this.f.itemView) + 0.5f);
            this.j = (int) (ViewCompat.getTranslationY(this.f.itemView) + 0.5f);
        }
        if (b(a2)) {
            b();
        }
    }

    private boolean b(long j2) {
        return j2 >= this.l && j2 < this.l + this.m;
    }

    private float c(long j2) {
        if (j2 < this.l) {
            return 1.0f;
        }
        if (j2 >= this.l + this.m || this.m == 0) {
            return 0.0f;
        }
        float f2 = 1.0f - (((float) (j2 - this.l)) / ((float) this.m));
        return this.n != null ? this.n.getInterpolation(f2) : f2;
    }

    private void a(Canvas canvas, Drawable drawable, float f2) {
        Rect rect = this.h;
        int i2 = this.i;
        int i3 = this.j;
        float f3 = this.p ? 1.0f : f2;
        if (!this.p) {
            f2 = 1.0f;
        }
        int width = (int) ((f3 * ((float) rect.width())) + 0.5f);
        int height = (int) ((f2 * ((float) rect.height())) + 0.5f);
        if (height != 0 && width != 0 && drawable != null) {
            int save = canvas.save();
            canvas.clipRect(rect.left + i2, rect.top + i3, rect.left + i2 + width, rect.top + i3 + height);
            canvas.translate((float) ((rect.left + i2) - ((rect.width() - width) / 2)), (float) ((rect.top + i3) - ((rect.height() - height) / 2)));
            drawable.setBounds(0, 0, rect.width(), rect.height());
            drawable.draw(canvas);
            canvas.restoreToCount(save);
        }
    }

    private void b() {
        ViewCompat.postInvalidateOnAnimation(this.e);
    }

    public void a() {
        ViewCompat.animate(((SwipeableItemViewHolder) this.f).k()).cancel();
        this.e.addItemDecoration(this);
        this.k = System.currentTimeMillis();
        this.j = (int) (ViewCompat.getTranslationY(this.f.itemView) + 0.5f);
        this.o = this.f.itemView.getBackground();
        b();
        a(0, this.l);
    }

    private void a(int i2, long j2) {
        int i3 = 1 << i2;
        if ((this.q & i3) == 0) {
            this.q = i3 | this.q;
            ViewCompat.postOnAnimationDelayed(this.e, new DelayedNotificationRunner(this, i2), j2);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        long a2 = a(this.k);
        this.q = ((1 << i2) ^ -1) & this.q;
        switch (i2) {
            case 0:
                if (a2 < this.l) {
                    a(0, this.l - a2);
                    return;
                }
                b();
                a(1, this.m);
                return;
            case 1:
                c();
                return;
            default:
                return;
        }
    }

    private void c() {
        this.e.removeItemDecoration(this);
        b();
        this.e = null;
        this.f = null;
        this.j = 0;
        this.n = null;
    }

    protected static long a(long j2) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis >= j2) {
            return currentTimeMillis - j2;
        }
        return Long.MAX_VALUE;
    }

    private static class DelayedNotificationRunner implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<RemovingItemDecorator> f5726a;
        private final int b;

        public DelayedNotificationRunner(RemovingItemDecorator removingItemDecorator, int i) {
            this.f5726a = new WeakReference<>(removingItemDecorator);
            this.b = i;
        }

        public void run() {
            RemovingItemDecorator removingItemDecorator = (RemovingItemDecorator) this.f5726a.get();
            this.f5726a.clear();
            this.f5726a = null;
            if (removingItemDecorator != null) {
                removingItemDecorator.a(this.b);
            }
        }
    }
}
