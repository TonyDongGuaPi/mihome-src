package com.h6ah4i.android.widget.advrecyclerview.swipeable;

import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import com.h6ah4i.android.widget.advrecyclerview.animator.SwipeDismissItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionDefault;
import com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;

public class RecyclerViewSwipeManager implements SwipeableItemConstants {
    private static final String W = "ARVSwipeManager";
    private static final int X = 10;
    private static final int Y = 8;
    private static final boolean Z = false;
    private static final boolean aa = false;
    private InternalHandler aA;
    private int aB = ViewConfiguration.getLongPressTimeout();
    private RecyclerView.OnItemTouchListener ab = new RecyclerView.OnItemTouchListener() {
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            return RecyclerViewSwipeManager.this.a(recyclerView, motionEvent);
        }

        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            RecyclerViewSwipeManager.this.b(recyclerView, motionEvent);
        }

        public void onRequestDisallowInterceptTouchEvent(boolean z) {
            RecyclerViewSwipeManager.this.a(z);
        }
    };
    private RecyclerView ac;
    private long ad = 300;
    private long ae = 200;
    private int af;
    private int ag;
    private int ah;
    private int ai;
    private int aj;
    private long ak = -1;
    private boolean al;
    private ItemSlidingAnimator am;
    private SwipeableItemWrapperAdapter<RecyclerView.ViewHolder> an;
    private RecyclerView.ViewHolder ao;
    private int ap = -1;
    private long aq = -1;
    private final Rect ar = new Rect();
    private int as;
    private int at;
    private int au;
    private int av;
    private int aw;
    private VelocityTracker ax = VelocityTracker.obtain();
    private SwipingItemOperator ay;
    private OnItemSwipeEventListener az;

    public interface OnItemSwipeEventListener {
        void a(int i);

        void a(int i, int i2, int i3);
    }

    private static int a(float f, boolean z) {
        return z ? f < 0.0f ? 1 : 3 : f < 0.0f ? 2 : 4;
    }

    private static int d(int i) {
        switch (i) {
            case 2:
                return 0;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 3;
            default:
                return 0;
        }
    }

    public RecyclerView.Adapter a(@NonNull RecyclerView.Adapter adapter) {
        if (!adapter.hasStableIds()) {
            throw new IllegalArgumentException("The passed adapter does not support stable IDs");
        } else if (this.an == null) {
            this.an = new SwipeableItemWrapperAdapter<>(this, adapter);
            return this.an;
        } else {
            throw new IllegalStateException("already have a wrapped adapter");
        }
    }

    public boolean a() {
        return this.ab == null;
    }

    public void a(@NonNull RecyclerView recyclerView) {
        if (a()) {
            throw new IllegalStateException("Accessing released object");
        } else if (this.ac != null) {
            throw new IllegalStateException("RecyclerView instance has already been set");
        } else if (this.an == null || b(recyclerView) != this.an) {
            throw new IllegalStateException("adapter is not set properly");
        } else {
            int e = CustomRecyclerViewUtils.e(recyclerView);
            if (e != -1) {
                this.ac = recyclerView;
                this.ac.addOnItemTouchListener(this.ab);
                ViewConfiguration viewConfiguration = ViewConfiguration.get(recyclerView.getContext());
                this.af = viewConfiguration.getScaledTouchSlop();
                this.ag = viewConfiguration.getScaledMinimumFlingVelocity();
                this.ah = viewConfiguration.getScaledMaximumFlingVelocity();
                this.am = new ItemSlidingAnimator(this.an);
                this.am.a((int) ((recyclerView.getResources().getDisplayMetrics().density * 8.0f) + 0.5f));
                boolean z = true;
                if (e != 1) {
                    z = false;
                }
                this.al = z;
                this.aA = new InternalHandler(this);
                return;
            }
            throw new IllegalStateException("failed to determine layout orientation");
        }
    }

    public void b() {
        b(true);
        if (this.aA != null) {
            this.aA.a();
            this.aA = null;
        }
        if (!(this.ac == null || this.ab == null)) {
            this.ac.removeOnItemTouchListener(this.ab);
        }
        this.ab = null;
        if (this.ax != null) {
            this.ax.recycle();
            this.ax = null;
        }
        if (this.am != null) {
            this.am.a();
            this.am = null;
        }
        this.an = null;
        this.ac = null;
    }

    public boolean c() {
        return this.ao != null && !this.aA.e();
    }

    public void a(int i) {
        this.aB = i;
    }

    /* access modifiers changed from: package-private */
    public boolean a(RecyclerView recyclerView, MotionEvent motionEvent) {
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case 0:
                if (c()) {
                    return false;
                }
                c(recyclerView, motionEvent);
                return false;
            case 1:
            case 3:
                if (a(motionEvent, true)) {
                    return true;
                }
                return false;
            case 2:
                if (c()) {
                    b(motionEvent);
                    return true;
                } else if (d(recyclerView, motionEvent)) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void b(RecyclerView recyclerView, MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (c()) {
            switch (actionMasked) {
                case 1:
                case 3:
                    a(motionEvent, true);
                    return;
                case 2:
                    b(motionEvent);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        if (z) {
            b(true);
        }
    }

    private boolean c(RecyclerView recyclerView, MotionEvent motionEvent) {
        int a2;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        RecyclerView.ViewHolder b = CustomRecyclerViewUtils.b(recyclerView, motionEvent.getX(), motionEvent.getY());
        if (!(b instanceof SwipeableItemViewHolder) || (a2 = CustomRecyclerViewUtils.a(b)) < 0 || a2 >= adapter.getItemCount() || b.getItemId() != adapter.getItemId(a2)) {
            return false;
        }
        int x = (int) (motionEvent.getX() + 0.5f);
        int y = (int) (motionEvent.getY() + 0.5f);
        View view = b.itemView;
        int translationY = (int) (ViewCompat.getTranslationY(view) + 0.5f);
        int left = view.getLeft();
        int top = y - (view.getTop() + translationY);
        int a3 = this.an.a(b, a2, x - (left + ((int) (ViewCompat.getTranslationX(view) + 0.5f))), top);
        if (a3 == 0) {
            return false;
        }
        this.ai = x;
        this.aj = y;
        this.ak = b.getItemId();
        this.aw = a3;
        if ((16777216 & a3) == 0) {
            return true;
        }
        this.aA.a(motionEvent, this.aB);
        return true;
    }

    private static SwipeableItemWrapperAdapter b(RecyclerView recyclerView) {
        return (SwipeableItemWrapperAdapter) WrapperAdapterUtils.a(recyclerView.getAdapter(), SwipeableItemWrapperAdapter.class);
    }

    private boolean a(MotionEvent motionEvent, boolean z) {
        int i;
        if (motionEvent != null) {
            i = MotionEventCompat.getActionMasked(motionEvent);
            this.au = (int) (motionEvent.getX() + 0.5f);
            this.av = (int) (motionEvent.getY() + 0.5f);
        } else {
            i = 3;
        }
        if (!c()) {
            i();
            return false;
        } else if (!z) {
            return true;
        } else {
            b(i);
            return true;
        }
    }

    private void i() {
        if (this.aA != null) {
            this.aA.b();
        }
        this.ak = -1;
        this.aw = 0;
    }

    private void b(int i) {
        int i2;
        int i3;
        int i4 = 2;
        if (i == 1) {
            boolean z = this.al;
            View view = this.ao.itemView;
            int width = z ? view.getWidth() : view.getHeight();
            if (z) {
                i2 = this.au;
                i3 = this.ai;
            } else {
                i2 = this.av;
                i3 = this.aj;
            }
            float f = (float) (i2 - i3);
            float abs = Math.abs(f);
            this.ax.computeCurrentVelocity(1000, (float) this.ah);
            float xVelocity = z ? this.ax.getXVelocity() : this.ax.getYVelocity();
            float abs2 = Math.abs(xVelocity);
            if (abs > ((float) (this.af * 10)) && xVelocity * f > 0.0f && abs2 <= ((float) this.ah) && (abs > ((float) (width / 2)) || abs2 >= ((float) this.ag))) {
                if (!z || f >= 0.0f || !SwipeReactionUtils.e(this.aw)) {
                    if (!z && f < 0.0f && SwipeReactionUtils.f(this.aw)) {
                        i4 = 3;
                    } else if (z && f > 0.0f && SwipeReactionUtils.g(this.aw)) {
                        i4 = 4;
                    } else if (!z && f > 0.0f && SwipeReactionUtils.h(this.aw)) {
                        i4 = 5;
                    }
                }
                c(i4);
            }
        }
        i4 = 1;
        c(i4);
    }

    private boolean d(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.ak == -1) {
            return false;
        }
        int x = ((int) (motionEvent.getX() + 0.5f)) - this.ai;
        int y = ((int) (motionEvent.getY() + 0.5f)) - this.aj;
        if (this.al) {
            int i = y;
            y = x;
            x = i;
        }
        if (Math.abs(x) > this.af) {
            this.ak = -1;
            return false;
        } else if (Math.abs(y) <= this.af) {
            return false;
        } else {
            boolean z = true;
            if (!this.al ? y >= 0 ? (this.aw & 2097152) == 0 : (this.aw & 512) == 0 : y >= 0 ? (this.aw & 32768) == 0 : (this.aw & 8) == 0) {
                z = false;
            }
            if (z) {
                this.ak = -1;
                return false;
            }
            RecyclerView.ViewHolder b = CustomRecyclerViewUtils.b(recyclerView, motionEvent.getX(), motionEvent.getY());
            if (b != null && b.getItemId() == this.ak) {
                return a(motionEvent, b);
            }
            this.ak = -1;
            return false;
        }
    }

    private void b(MotionEvent motionEvent) {
        this.au = (int) (motionEvent.getX() + 0.5f);
        this.av = (int) (motionEvent.getY() + 0.5f);
        this.ax.addMovement(motionEvent);
        int i = this.au - this.as;
        int i2 = this.av - this.at;
        this.ap = a((RecyclerView.Adapter) this.an, this.aq, this.ap);
        this.ay.a(this.ap, i, i2);
    }

    private boolean a(MotionEvent motionEvent, RecyclerView.ViewHolder viewHolder) {
        int a2 = CustomRecyclerViewUtils.a(viewHolder);
        if (a2 == -1) {
            return false;
        }
        a(motionEvent, viewHolder, a2);
        return true;
    }

    private void a(MotionEvent motionEvent, RecyclerView.ViewHolder viewHolder, int i) {
        this.aA.b();
        this.ao = viewHolder;
        this.ap = i;
        this.aq = this.an.getItemId(i);
        this.au = (int) (motionEvent.getX() + 0.5f);
        this.av = (int) (motionEvent.getY() + 0.5f);
        this.as = this.au;
        this.at = this.av;
        this.ak = -1;
        CustomRecyclerViewUtils.a(viewHolder.itemView, this.ar);
        this.ay = new SwipingItemOperator(this, this.ao, this.aw, this.al);
        this.ay.a();
        this.ax.clear();
        this.ax.addMovement(motionEvent);
        this.ac.getParent().requestDisallowInterceptTouchEvent(true);
        if (this.az != null) {
            this.az.a(i);
        }
        this.an.a(this, viewHolder, this.aq);
    }

    private void c(int i) {
        boolean z;
        int i2 = i;
        RecyclerView.ViewHolder viewHolder = this.ao;
        if (viewHolder != null) {
            this.aA.c();
            this.aA.b();
            if (!(this.ac == null || this.ac.getParent() == null)) {
                this.ac.getParent().requestDisallowInterceptTouchEvent(false);
            }
            int a2 = a((RecyclerView.Adapter) this.an, this.aq, this.ap);
            this.ax.clear();
            SwipeResultAction swipeResultAction = null;
            this.ao = null;
            this.ap = -1;
            this.aq = -1;
            this.au = 0;
            this.av = 0;
            this.ai = 0;
            this.as = 0;
            this.at = 0;
            this.ak = -1;
            this.aw = 0;
            if (this.ay != null) {
                this.ay.b();
                this.ay = null;
            }
            int d = d(i);
            if (this.an != null) {
                swipeResultAction = this.an.a(viewHolder, a2, i2);
            }
            if (swipeResultAction == null) {
                swipeResultAction = new SwipeResultActionDefault();
            }
            SwipeResultAction swipeResultAction2 = swipeResultAction;
            int a3 = swipeResultAction2.a();
            a(i2, a3);
            switch (a3) {
                case 0:
                    z = this.am.a(viewHolder, this.al, true, this.ad, a2, swipeResultAction2);
                    break;
                case 1:
                    RecyclerView.ItemAnimator itemAnimator = this.ac.getItemAnimator();
                    long removeDuration = itemAnimator != null ? itemAnimator.getRemoveDuration() : 0;
                    if (j()) {
                        RemovingItemDecorator removingItemDecorator = r1;
                        RemovingItemDecorator removingItemDecorator2 = new RemovingItemDecorator(this.ac, viewHolder, i, removeDuration, itemAnimator != null ? itemAnimator.getMoveDuration() : 0);
                        removingItemDecorator.a(SwipeDismissItemAnimator.f5688a);
                        removingItemDecorator.a();
                    }
                    z = this.am.a(viewHolder, d, true, removeDuration, a2, swipeResultAction2);
                    break;
                case 2:
                    z = this.am.a(viewHolder, d, true, this.ae, a2, swipeResultAction2);
                    break;
                default:
                    throw new IllegalStateException("Unknown after reaction type: " + a3);
            }
            boolean z2 = z;
            if (this.an != null) {
                this.an.a(viewHolder, a2, i, a3, swipeResultAction2);
            }
            if (this.az != null) {
                this.az.a(a2, i, a3);
            }
            if (!z2) {
                swipeResultAction2.c();
            }
        }
    }

    private static void a(int i, int i2) {
        if (i2 == 2 || i2 == 1) {
            switch (i) {
                case 2:
                case 3:
                case 4:
                case 5:
                    return;
                default:
                    throw new IllegalStateException("Unexpected after reaction has been requested: result = " + i + ", afterReaction = " + i2);
            }
        }
    }

    private static int a(@Nullable RecyclerView.Adapter adapter, long j, int i) {
        if (adapter == null) {
            return -1;
        }
        int itemCount = adapter.getItemCount();
        if (i >= 0 && i < itemCount && adapter.getItemId(i) == j) {
            return i;
        }
        for (int i2 = 0; i2 < itemCount; i2++) {
            if (adapter.getItemId(i2) == j) {
                return i2;
            }
        }
        return -1;
    }

    public void d() {
        b(false);
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z) {
        a((MotionEvent) null, false);
        if (z) {
            c(1);
        } else if (c()) {
            this.aA.d();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(RecyclerView.ViewHolder viewHolder) {
        return this.am != null && this.am.b(viewHolder);
    }

    private void a(RecyclerView.ViewHolder viewHolder, float f, boolean z, boolean z2) {
        if (f == -65536.0f) {
            this.am.a(viewHolder, 0, z2, this.ae);
        } else if (f == -65537.0f) {
            this.am.a(viewHolder, 1, z2, this.ae);
        } else if (f == 65536.0f) {
            this.am.a(viewHolder, 2, z2, this.ae);
        } else if (f == 65537.0f) {
            this.am.a(viewHolder, 3, z2, this.ae);
        } else if (f == 0.0f) {
            this.am.a(viewHolder, z, z2, this.ad);
        } else {
            this.am.a(viewHolder, f, z);
        }
    }

    public long e() {
        return this.ad;
    }

    public void a(long j) {
        this.ad = j;
    }

    public long f() {
        return this.ae;
    }

    public void b(long j) {
        this.ae = j;
    }

    @Nullable
    public OnItemSwipeEventListener g() {
        return this.az;
    }

    public void a(@Nullable OnItemSwipeEventListener onItemSwipeEventListener) {
        this.az = onItemSwipeEventListener;
    }

    /* access modifiers changed from: package-private */
    public boolean h() {
        return this.al;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.support.v7.widget.RecyclerView.ViewHolder r13, int r14, float r15, float r16, boolean r17, boolean r18, boolean r19) {
        /*
            r12 = this;
            r0 = r12
            r8 = r13
            r1 = r15
            r5 = r16
            r9 = r17
            r10 = r18
            r2 = r8
            com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder r2 = (com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder) r2
            android.view.View r3 = r2.k()
            if (r3 != 0) goto L_0x0013
            return
        L_0x0013:
            r3 = 0
            int r4 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r4 != 0) goto L_0x0024
            int r4 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r4 != 0) goto L_0x001f
            r1 = 0
            r7 = 0
            goto L_0x0029
        L_0x001f:
            int r1 = a((float) r15, (boolean) r9)
            goto L_0x0028
        L_0x0024:
            int r1 = a((float) r16, (boolean) r17)
        L_0x0028:
            r7 = r1
        L_0x0029:
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x003e
            r12.a(r13, r5, r9, r10)
            com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemWrapperAdapter<android.support.v7.widget.RecyclerView$ViewHolder> r1 = r0.an
            r2 = r13
            r3 = r14
            r4 = r17
            r5 = r16
            r6 = r19
            r1.a(r2, r3, r4, r5, r6, r7)
            goto L_0x006c
        L_0x003e:
            if (r9 == 0) goto L_0x0045
            float r1 = r2.g()
            goto L_0x0049
        L_0x0045:
            float r1 = r2.h()
        L_0x0049:
            if (r9 == 0) goto L_0x0050
            float r2 = r2.i()
            goto L_0x0054
        L_0x0050:
            float r2 = r2.j()
        L_0x0054:
            float r1 = java.lang.Math.max(r5, r1)
            float r11 = java.lang.Math.min(r1, r2)
            com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemWrapperAdapter<android.support.v7.widget.RecyclerView$ViewHolder> r1 = r0.an
            r2 = r13
            r3 = r14
            r4 = r17
            r5 = r16
            r6 = r19
            r1.a(r2, r3, r4, r5, r6, r7)
            r12.a(r13, r11, r9, r10)
        L_0x006c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager.a(android.support.v7.widget.RecyclerView$ViewHolder, int, float, float, boolean, boolean, boolean):void");
    }

    /* access modifiers changed from: package-private */
    public void b(RecyclerView.ViewHolder viewHolder) {
        if (this.am != null) {
            this.am.a(viewHolder);
        }
    }

    /* access modifiers changed from: package-private */
    public int c(RecyclerView.ViewHolder viewHolder) {
        return this.am.c(viewHolder);
    }

    /* access modifiers changed from: package-private */
    public int d(RecyclerView.ViewHolder viewHolder) {
        return this.am.d(viewHolder);
    }

    /* access modifiers changed from: package-private */
    public void a(MotionEvent motionEvent) {
        RecyclerView.ViewHolder findViewHolderForItemId = this.ac.findViewHolderForItemId(this.ak);
        if (findViewHolderForItemId != null) {
            a(motionEvent, findViewHolderForItemId);
        }
    }

    private static boolean j() {
        return Build.VERSION.SDK_INT >= 11;
    }

    private static class InternalHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private static final int f5724a = 1;
        private static final int b = 2;
        private RecyclerViewSwipeManager c;
        private MotionEvent d;

        public InternalHandler(RecyclerViewSwipeManager recyclerViewSwipeManager) {
            this.c = recyclerViewSwipeManager;
        }

        public void a() {
            removeCallbacks((Runnable) null);
            this.c = null;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.c.a(this.d);
                    return;
                case 2:
                    this.c.b(true);
                    return;
                default:
                    return;
            }
        }

        public void a(MotionEvent motionEvent, int i) {
            b();
            this.d = MotionEvent.obtain(motionEvent);
            sendEmptyMessageAtTime(1, motionEvent.getDownTime() + ((long) i));
        }

        public void b() {
            removeMessages(1);
            if (this.d != null) {
                this.d.recycle();
                this.d = null;
            }
        }

        public void c() {
            removeMessages(2);
        }

        public void d() {
            if (!e()) {
                sendEmptyMessage(2);
            }
        }

        public boolean e() {
            return hasMessages(2);
        }
    }
}
