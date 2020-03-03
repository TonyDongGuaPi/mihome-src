package com.h6ah4i.android.widget.advrecyclerview.swipeable;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ItemSlidingAnimator {

    /* renamed from: a  reason: collision with root package name */
    public static final int f5719a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    private static final String e = "ItemSlidingAnimator";
    private final SwipeableItemWrapperAdapter<RecyclerView.ViewHolder> f;
    private final Interpolator g = new AccelerateDecelerateInterpolator();
    private final Interpolator h = new AccelerateInterpolator(0.8f);
    private final List<RecyclerView.ViewHolder> i;
    private final List<WeakReference<ViewHolderDeferredProcess>> j;
    private final int[] k = new int[2];
    private final Rect l = new Rect();
    private int m;

    public ItemSlidingAnimator(SwipeableItemWrapperAdapter<RecyclerView.ViewHolder> swipeableItemWrapperAdapter) {
        this.f = swipeableItemWrapperAdapter;
        this.i = new ArrayList();
        this.j = new ArrayList();
    }

    public void a(RecyclerView.ViewHolder viewHolder, boolean z, boolean z2, long j2) {
        e(viewHolder);
        a(viewHolder, 0.0f, z, z2, j2, (SwipeFinishInfo) null);
    }

    public void a(RecyclerView.ViewHolder viewHolder, int i2, boolean z, long j2) {
        e(viewHolder);
        a(viewHolder, i2, z, j2, (SwipeFinishInfo) null);
    }

    public void a(RecyclerView.ViewHolder viewHolder, float f2, boolean z) {
        e(viewHolder);
        a(viewHolder, f2, z, false, 0, (SwipeFinishInfo) null);
    }

    public boolean a(RecyclerView.ViewHolder viewHolder, boolean z, boolean z2, long j2, int i2, SwipeResultAction swipeResultAction) {
        e(viewHolder);
        return a(viewHolder, 0.0f, z, z2, j2, new SwipeFinishInfo(i2, swipeResultAction));
    }

    public boolean a(RecyclerView.ViewHolder viewHolder, int i2, boolean z, long j2, int i3, SwipeResultAction swipeResultAction) {
        e(viewHolder);
        return a(viewHolder, i2, z, j2, new SwipeFinishInfo(i3, swipeResultAction));
    }

    private void e(RecyclerView.ViewHolder viewHolder) {
        for (int size = this.j.size() - 1; size >= 0; size--) {
            ViewHolderDeferredProcess viewHolderDeferredProcess = (ViewHolderDeferredProcess) this.j.get(size).get();
            if (viewHolderDeferredProcess != null && viewHolderDeferredProcess.c(viewHolder)) {
                viewHolder.itemView.removeCallbacks(viewHolderDeferredProcess);
                this.j.remove(size);
            } else if (viewHolderDeferredProcess == null || viewHolderDeferredProcess.b(viewHolder)) {
                this.j.remove(size);
            }
        }
    }

    private void a(RecyclerView.ViewHolder viewHolder, ViewHolderDeferredProcess viewHolderDeferredProcess) {
        this.j.add(new WeakReference(viewHolderDeferredProcess));
        viewHolder.itemView.post(viewHolderDeferredProcess);
    }

    private boolean a(RecyclerView.ViewHolder viewHolder, float f2, boolean z, boolean z2, long j2, SwipeFinishInfo swipeFinishInfo) {
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        float f3 = f2;
        boolean z3 = z;
        Interpolator interpolator = this.g;
        long j3 = z2 ? j2 : 0;
        if (f3 == 0.0f) {
            return a(viewHolder, z, 0, 0, j3, interpolator, swipeFinishInfo);
        }
        View k2 = ((SwipeableItemViewHolder) viewHolder2).k();
        int width = k2.getWidth();
        int height = k2.getHeight();
        if (z3 && width != 0) {
            return a(viewHolder, z, (int) ((((float) width) * f3) + 0.5f), 0, j3, interpolator, swipeFinishInfo);
        } else if (!z3 && height != 0) {
            return a(viewHolder, z, 0, (int) ((((float) height) * f3) + 0.5f), j3, interpolator, swipeFinishInfo);
        } else if (swipeFinishInfo == null) {
            a(viewHolder, new DeferredSlideProcess(viewHolder, f2, z));
            return false;
        } else {
            throw new IllegalStateException("Unexpected state in slideToSpecifiedPositionInternal (swipeFinish == null)");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005c, code lost:
        r12 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0072, code lost:
        r4 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0079, code lost:
        r12 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007d, code lost:
        r11 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0082, code lost:
        r4 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(android.support.v7.widget.RecyclerView.ViewHolder r15, int r16, boolean r17, long r18, com.h6ah4i.android.widget.advrecyclerview.swipeable.ItemSlidingAnimator.SwipeFinishInfo r20) {
        /*
            r14 = this;
            r9 = r14
            r1 = r15
            r0 = r16
            boolean r2 = r1 instanceof com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
            r3 = 0
            if (r2 != 0) goto L_0x000a
            return r3
        L_0x000a:
            r2 = r1
            com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder r2 = (com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder) r2
            android.view.View r2 = r2.k()
            android.view.ViewParent r4 = r2.getParent()
            android.view.ViewGroup r4 = (android.view.ViewGroup) r4
            if (r4 != 0) goto L_0x001a
            return r3
        L_0x001a:
            int r5 = r2.getLeft()
            int r6 = r2.getRight()
            int r7 = r2.getTop()
            int r8 = r2.getBottom()
            int r6 = r6 - r5
            int r8 = r8 - r7
            boolean r10 = r4.isShown()
            android.graphics.Rect r11 = r9.l
            r4.getWindowVisibleDisplayFrame(r11)
            android.graphics.Rect r11 = r9.l
            int r11 = r11.width()
            android.graphics.Rect r12 = r9.l
            int r12 = r12.height()
            r13 = 1
            if (r6 == 0) goto L_0x0075
            if (r8 == 0) goto L_0x0075
            if (r10 != 0) goto L_0x0049
            goto L_0x0075
        L_0x0049:
            int[] r10 = r9.k
            r4.getLocationInWindow(r10)
            int[] r4 = r9.k
            r4 = r4[r3]
            int[] r10 = r9.k
            r10 = r10[r13]
            switch(r0) {
                case 0: goto L_0x006e;
                case 1: goto L_0x0069;
                case 2: goto L_0x0064;
                case 3: goto L_0x005e;
                default: goto L_0x0059;
            }
        L_0x0059:
            r4 = r17
            r11 = 0
        L_0x005c:
            r12 = 0
            goto L_0x0083
        L_0x005e:
            int r10 = r10 - r7
            int r12 = r12 - r10
            r4 = r17
            r11 = 0
            goto L_0x0083
        L_0x0064:
            int r4 = r4 - r5
            int r11 = r11 - r4
            r4 = r17
            goto L_0x005c
        L_0x0069:
            int r10 = r10 + r8
            int r4 = -r10
            r12 = r4
            r11 = 0
            goto L_0x0072
        L_0x006e:
            int r4 = r4 + r6
            int r4 = -r4
            r11 = r4
            r12 = 0
        L_0x0072:
            r4 = r17
            goto L_0x0083
        L_0x0075:
            switch(r0) {
                case 0: goto L_0x007f;
                case 1: goto L_0x007b;
                case 2: goto L_0x0079;
                case 3: goto L_0x007d;
                default: goto L_0x0078;
            }
        L_0x0078:
            r11 = 0
        L_0x0079:
            r12 = 0
            goto L_0x0082
        L_0x007b:
            int r4 = -r12
            r12 = r4
        L_0x007d:
            r11 = 0
            goto L_0x0082
        L_0x007f:
            int r4 = -r11
            r11 = r4
            goto L_0x0079
        L_0x0082:
            r4 = 0
        L_0x0083:
            if (r4 == 0) goto L_0x0089
            boolean r4 = r2.isShown()
        L_0x0089:
            if (r4 == 0) goto L_0x008e
            r5 = r18
            goto L_0x0091
        L_0x008e:
            r4 = 0
            r5 = r4
        L_0x0091:
            if (r0 == 0) goto L_0x0099
            r2 = 2
            if (r0 != r2) goto L_0x0097
            goto L_0x0099
        L_0x0097:
            r2 = 0
            goto L_0x009a
        L_0x0099:
            r2 = 1
        L_0x009a:
            android.view.animation.Interpolator r7 = r9.h
            r0 = r14
            r1 = r15
            r3 = r11
            r4 = r12
            r8 = r20
            boolean r0 = r0.a(r1, r2, r3, r4, r5, r7, r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.h6ah4i.android.widget.advrecyclerview.swipeable.ItemSlidingAnimator.a(android.support.v7.widget.RecyclerView$ViewHolder, int, boolean, long, com.h6ah4i.android.widget.advrecyclerview.swipeable.ItemSlidingAnimator$SwipeFinishInfo):boolean");
    }

    private boolean a(RecyclerView.ViewHolder viewHolder, boolean z, int i2, int i3, long j2, Interpolator interpolator, SwipeFinishInfo swipeFinishInfo) {
        if (d()) {
            return b(viewHolder, z, i2, i3, j2, interpolator, swipeFinishInfo);
        }
        return b(viewHolder, z, i2, i3);
    }

    static void a(RecyclerView.ViewHolder viewHolder, boolean z, int i2, int i3) {
        if (d()) {
            c(viewHolder, z, i2, i3);
        } else {
            b(viewHolder, z, i2, i3);
        }
    }

    @SuppressLint({"RtlHardcoded"})
    private static boolean b(RecyclerView.ViewHolder viewHolder, boolean z, int i2, int i3) {
        if (!(viewHolder instanceof SwipeableItemViewHolder)) {
            return false;
        }
        View k2 = ((SwipeableItemViewHolder) viewHolder).k();
        ViewGroup.LayoutParams layoutParams = k2.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.leftMargin = i2;
            marginLayoutParams.rightMargin = -i2;
            marginLayoutParams.topMargin = i3;
            marginLayoutParams.bottomMargin = -i3;
            if (layoutParams instanceof FrameLayout.LayoutParams) {
                ((FrameLayout.LayoutParams) layoutParams).gravity = 51;
            }
            k2.setLayoutParams(marginLayoutParams);
        } else {
            Log.w(e, "should use MarginLayoutParams supported view for compatibility on Android 2.3");
        }
        return false;
    }

    private static int f(RecyclerView.ViewHolder viewHolder) {
        ViewGroup.LayoutParams layoutParams = ((SwipeableItemViewHolder) viewHolder).k().getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
        }
        Log.w(e, "should use MarginLayoutParams supported view for compatibility on Android 2.3");
        return 0;
    }

    private static int g(RecyclerView.ViewHolder viewHolder) {
        ViewGroup.LayoutParams layoutParams = ((SwipeableItemViewHolder) viewHolder).k().getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        }
        Log.w(e, "should use MarginLayoutParams supported view for compatibility on Android 2.3");
        return 0;
    }

    private static void c(RecyclerView.ViewHolder viewHolder, boolean z, int i2, int i3) {
        if (viewHolder instanceof SwipeableItemViewHolder) {
            View k2 = ((SwipeableItemViewHolder) viewHolder).k();
            ViewCompat.animate(k2).cancel();
            ViewCompat.setTranslationX(k2, (float) i2);
            ViewCompat.setTranslationY(k2, (float) i3);
        }
    }

    private boolean b(RecyclerView.ViewHolder viewHolder, boolean z, int i2, int i3, long j2, Interpolator interpolator, SwipeFinishInfo swipeFinishInfo) {
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        int i4 = i2;
        int i5 = i3;
        if (!(viewHolder2 instanceof SwipeableItemViewHolder)) {
            return false;
        }
        View k2 = ((SwipeableItemViewHolder) viewHolder2).k();
        int translationX = (int) (ViewCompat.getTranslationX(k2) + 0.5f);
        int translationY = (int) (ViewCompat.getTranslationY(k2) + 0.5f);
        a(viewHolder);
        int translationX2 = (int) (ViewCompat.getTranslationX(k2) + 0.5f);
        int translationY2 = (int) (ViewCompat.getTranslationY(k2) + 0.5f);
        if (j2 == 0 || ((translationX2 == i4 && translationY2 == i5) || Math.max(Math.abs(i4 - translationX), Math.abs(i5 - translationY)) <= this.m)) {
            ViewCompat.setTranslationX(k2, (float) i4);
            ViewCompat.setTranslationY(k2, (float) i5);
            return false;
        }
        ViewCompat.setTranslationX(k2, (float) translationX);
        ViewCompat.setTranslationY(k2, (float) translationY);
        new SlidingAnimatorListenerObject(this.f, this.i, viewHolder, i2, i3, j2, z, interpolator, swipeFinishInfo).a();
        return true;
    }

    public void a(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof SwipeableItemViewHolder) {
            e(viewHolder);
            ViewCompat.animate(((SwipeableItemViewHolder) viewHolder).k()).cancel();
            if (this.i.remove(viewHolder)) {
                throw new IllegalStateException("after animation is cancelled, item should not be in the active animation list [slide]");
            }
        }
    }

    public void a() {
        for (int size = this.i.size() - 1; size >= 0; size--) {
            a(this.i.get(size));
        }
    }

    public boolean b(RecyclerView.ViewHolder viewHolder) {
        return this.i.contains(viewHolder);
    }

    public boolean b() {
        return !this.i.isEmpty();
    }

    public int c() {
        return this.m;
    }

    public void a(int i2) {
        this.m = i2;
    }

    private static boolean d() {
        return Build.VERSION.SDK_INT >= 11;
    }

    public int c(RecyclerView.ViewHolder viewHolder) {
        if (d()) {
            return (int) (ViewCompat.getTranslationX(((SwipeableItemViewHolder) viewHolder).k()) + 0.5f);
        }
        return f(viewHolder);
    }

    public int d(RecyclerView.ViewHolder viewHolder) {
        if (d()) {
            return (int) (ViewCompat.getTranslationY(((SwipeableItemViewHolder) viewHolder).k()) + 0.5f);
        }
        return g(viewHolder);
    }

    private static abstract class ViewHolderDeferredProcess implements Runnable {
        final WeakReference<RecyclerView.ViewHolder> c;

        /* access modifiers changed from: protected */
        public abstract void a(RecyclerView.ViewHolder viewHolder);

        public ViewHolderDeferredProcess(RecyclerView.ViewHolder viewHolder) {
            this.c = new WeakReference<>(viewHolder);
        }

        public void run() {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) this.c.get();
            if (viewHolder != null) {
                a(viewHolder);
            }
        }

        public boolean b(RecyclerView.ViewHolder viewHolder) {
            return ((RecyclerView.ViewHolder) this.c.get()) == null;
        }

        public boolean c(RecyclerView.ViewHolder viewHolder) {
            return ((RecyclerView.ViewHolder) this.c.get()) == viewHolder;
        }
    }

    private static final class DeferredSlideProcess extends ViewHolderDeferredProcess {

        /* renamed from: a  reason: collision with root package name */
        final float f5720a;
        final boolean b;

        public DeferredSlideProcess(RecyclerView.ViewHolder viewHolder, float f, boolean z) {
            super(viewHolder);
            this.f5720a = f;
            this.b = z;
        }

        /* access modifiers changed from: protected */
        public void a(RecyclerView.ViewHolder viewHolder) {
            View k = ((SwipeableItemViewHolder) viewHolder).k();
            if (this.b) {
                ItemSlidingAnimator.a(viewHolder, this.b, (int) ((((float) k.getWidth()) * this.f5720a) + 0.5f), 0);
                return;
            }
            ItemSlidingAnimator.a(viewHolder, this.b, 0, (int) ((((float) k.getHeight()) * this.f5720a) + 0.5f));
        }
    }

    private static class SlidingAnimatorListenerObject implements ViewPropertyAnimatorListener, ViewPropertyAnimatorUpdateListener {

        /* renamed from: a  reason: collision with root package name */
        private SwipeableItemWrapperAdapter<RecyclerView.ViewHolder> f5721a;
        private List<RecyclerView.ViewHolder> b;
        private RecyclerView.ViewHolder c;
        private ViewPropertyAnimatorCompat d;
        private final int e;
        private final int f;
        private final long g;
        private final boolean h;
        private final SwipeFinishInfo i;
        private final Interpolator j;
        private float k;

        public void onAnimationCancel(View view) {
        }

        public void onAnimationStart(View view) {
        }

        SlidingAnimatorListenerObject(SwipeableItemWrapperAdapter<RecyclerView.ViewHolder> swipeableItemWrapperAdapter, List<RecyclerView.ViewHolder> list, RecyclerView.ViewHolder viewHolder, int i2, int i3, long j2, boolean z, Interpolator interpolator, SwipeFinishInfo swipeFinishInfo) {
            this.f5721a = swipeableItemWrapperAdapter;
            this.b = list;
            this.c = viewHolder;
            this.e = i2;
            this.f = i3;
            this.h = z;
            this.i = swipeFinishInfo;
            this.g = j2;
            this.j = interpolator;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            View k2 = ((SwipeableItemViewHolder) this.c).k();
            this.k = 1.0f / Math.max(1.0f, (float) (this.h ? k2.getWidth() : k2.getHeight()));
            this.d = ViewCompat.animate(k2);
            this.d.setDuration(this.g);
            this.d.translationX((float) this.e);
            this.d.translationY((float) this.f);
            if (this.j != null) {
                this.d.setInterpolator(this.j);
            }
            this.d.setListener(this);
            this.d.setUpdateListener(this);
            this.b.add(this.c);
            this.d.start();
        }

        public void onAnimationUpdate(View view) {
            this.f5721a.a(this.c, this.c.getLayoutPosition(), this.h, (this.h ? ViewCompat.getTranslationX(view) : ViewCompat.getTranslationY(view)) * this.k, false);
        }

        public void onAnimationEnd(View view) {
            this.d.setListener((ViewPropertyAnimatorListener) null);
            if (Build.VERSION.SDK_INT >= 19) {
                InternalHelperKK.a(view);
            } else {
                this.d.setUpdateListener((ViewPropertyAnimatorUpdateListener) null);
            }
            ViewCompat.setTranslationX(view, (float) this.e);
            ViewCompat.setTranslationY(view, (float) this.f);
            this.b.remove(this.c);
            ViewParent parent = this.c.itemView.getParent();
            if (parent != null) {
                ViewCompat.postInvalidateOnAnimation((View) parent);
            }
            if (this.i != null) {
                this.i.b.c();
            }
            this.b = null;
            this.d = null;
            this.c = null;
            this.f5721a = null;
        }
    }

    private static class SwipeFinishInfo {

        /* renamed from: a  reason: collision with root package name */
        final int f5722a;
        SwipeResultAction b;

        public SwipeFinishInfo(int i, SwipeResultAction swipeResultAction) {
            this.f5722a = i;
            this.b = swipeResultAction;
        }

        public void a() {
            this.b = null;
        }
    }
}
