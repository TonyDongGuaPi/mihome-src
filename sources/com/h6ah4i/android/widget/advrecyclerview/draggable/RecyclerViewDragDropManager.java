package com.h6ah4i.android.widget.advrecyclerview.draggable;

import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;
import com.taobao.weex.el.parse.Operators;
import java.lang.ref.WeakReference;

public class RecyclerViewDragDropManager implements DraggableItemConstants {
    public static final Interpolator e = new BasicSwapTargetTranslationInterpolator();
    public static final Interpolator f = new DecelerateInterpolator();
    private static final String h = "ARVDragDropManager";
    private static final int i = 0;
    private static final int j = 1;
    private static final int k = 2;
    private static final int l = 4;
    private static final int m = 8;
    private static final boolean n = false;
    private static final boolean o = false;
    private static final float p = 0.3f;
    private static final float q = 25.0f;
    private static final float r = 1.5f;
    private int A;
    private int B;
    private int C;
    private int D;
    private long E = -1;
    private boolean F;
    private boolean G = true;
    private int H = ViewConfiguration.getLongPressTimeout();
    private boolean I;
    private boolean J;
    private int K;
    private int L;
    private final Rect M = new Rect();
    private int N = 200;
    private Interpolator O = f;
    private DraggableItemWrapperAdapter P;
    private DraggingItemInfo Q;
    private DraggingItemDecorator R;
    private SwapTargetItemOperator S;
    private int T;
    private int U;
    private int V;
    private int W;
    private int X;
    private int Y;
    private int Z;
    private int aa;
    private int ab;
    private int ac;
    private int ad = 0;
    private int ae;
    private ItemDraggableRange af;
    private InternalHandler ag;
    private OnItemDragEventListener ah;
    private boolean ai;
    private boolean aj;
    private float ak = 1.0f;
    private SwapTarget al = new SwapTarget();
    private final Runnable am = new Runnable() {
        public void run() {
            if (RecyclerViewDragDropManager.this.g != null) {
                RecyclerViewDragDropManager.this.b(RecyclerViewDragDropManager.this.l());
            }
        }
    };
    RecyclerView.ViewHolder g;
    private RecyclerView s;
    private Interpolator t = e;
    private ScrollOnDraggingProcessRunnable u = new ScrollOnDraggingProcessRunnable(this);
    private RecyclerView.OnItemTouchListener v = new RecyclerView.OnItemTouchListener() {
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            return RecyclerViewDragDropManager.this.a(recyclerView, motionEvent);
        }

        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            RecyclerViewDragDropManager.this.b(recyclerView, motionEvent);
        }

        public void onRequestDisallowInterceptTouchEvent(boolean z) {
            RecyclerViewDragDropManager.this.d(z);
        }
    };
    private RecyclerView.OnScrollListener w = new RecyclerView.OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            RecyclerViewDragDropManager.this.a(recyclerView, i);
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            RecyclerViewDragDropManager.this.a(recyclerView, i, i2);
        }
    };
    private BaseEdgeEffectDecorator x;
    private NinePatchDrawable y;
    private float z;

    public interface OnItemDragEventListener {
        void a(int i);

        void a(int i, int i2);

        void a(int i, int i2, boolean z);

        void b(int i, int i2);
    }

    static class SwapTarget {

        /* renamed from: a  reason: collision with root package name */
        public RecyclerView.ViewHolder f5710a;
        public int b;
        public boolean c;

        SwapTarget() {
        }

        public void a() {
            this.f5710a = null;
            this.b = -1;
            this.c = false;
        }
    }

    public RecyclerView.Adapter a(@NonNull RecyclerView.Adapter adapter) {
        if (!adapter.hasStableIds()) {
            throw new IllegalArgumentException("The passed adapter does not support stable IDs");
        } else if (this.P == null) {
            this.P = new DraggableItemWrapperAdapter(this, adapter);
            return this.P;
        } else {
            throw new IllegalStateException("already have a wrapped adapter");
        }
    }

    public boolean a() {
        return this.v == null;
    }

    public void a(@NonNull RecyclerView recyclerView) {
        if (a()) {
            throw new IllegalStateException("Accessing released object");
        } else if (this.s != null) {
            throw new IllegalStateException("RecyclerView instance has already been set");
        } else if (this.P == null || c(recyclerView) != this.P) {
            throw new IllegalStateException("adapter is not set properly");
        } else {
            this.s = recyclerView;
            this.s.addOnScrollListener(this.w);
            this.s.addOnItemTouchListener(this.v);
            this.z = this.s.getResources().getDisplayMetrics().density;
            this.A = ViewConfiguration.get(this.s.getContext()).getScaledTouchSlop();
            this.B = (int) ((((float) this.A) * r) + 0.5f);
            this.ag = new InternalHandler(this);
            if (t()) {
                switch (CustomRecyclerViewUtils.e(this.s)) {
                    case 0:
                        this.x = new LeftRightEdgeEffectDecorator(this.s);
                        break;
                    case 1:
                        this.x = new TopBottomEdgeEffectDecorator(this.s);
                        break;
                }
                if (this.x != null) {
                    this.x.a();
                }
            }
        }
    }

    public void b() {
        e(true);
        if (this.ag != null) {
            this.ag.a();
            this.ag = null;
        }
        if (this.x != null) {
            this.x.b();
            this.x = null;
        }
        if (!(this.s == null || this.v == null)) {
            this.s.removeOnItemTouchListener(this.v);
        }
        this.v = null;
        if (!(this.s == null || this.w == null)) {
            this.s.removeOnScrollListener(this.w);
        }
        this.w = null;
        if (this.u != null) {
            this.u.c();
            this.u = null;
        }
        this.P = null;
        this.s = null;
        this.t = null;
    }

    public boolean c() {
        return this.Q != null && !this.ag.e();
    }

    public void a(@Nullable NinePatchDrawable ninePatchDrawable) {
        this.y = ninePatchDrawable;
    }

    public void a(@Nullable Interpolator interpolator) {
        this.t = interpolator;
    }

    public boolean d() {
        return this.F;
    }

    public void a(boolean z2) {
        this.F = z2;
    }

    public boolean e() {
        return this.G;
    }

    public void b(boolean z2) {
        this.G = z2;
    }

    public void a(int i2) {
        this.H = i2;
    }

    public Interpolator f() {
        return this.t;
    }

    @Nullable
    public OnItemDragEventListener g() {
        return this.ah;
    }

    public void a(@Nullable OnItemDragEventListener onItemDragEventListener) {
        this.ah = onItemDragEventListener;
    }

    public void a(float f2) {
        this.ak = Math.min(Math.max(f2, 0.0f), 2.0f);
    }

    public float h() {
        return this.ak;
    }

    public void c(boolean z2) {
        this.I = z2;
    }

    public boolean i() {
        return this.I;
    }

    /* access modifiers changed from: package-private */
    public boolean a(RecyclerView recyclerView, MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        switch (actionMasked) {
            case 0:
                if (c()) {
                    return false;
                }
                c(recyclerView, motionEvent);
                return false;
            case 1:
            case 3:
                a(actionMasked, true);
                return false;
            case 2:
                if (c()) {
                    e(recyclerView, motionEvent);
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
                    a(actionMasked, true);
                    return;
                case 2:
                    e(recyclerView, motionEvent);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void d(boolean z2) {
        if (z2) {
            e(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(RecyclerView recyclerView, int i2, int i3) {
        if (this.J) {
            this.K = i2;
            this.L = i3;
        } else if (c()) {
            ViewCompat.postOnAnimationDelayed(this.s, this.am, 500);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(RecyclerView recyclerView, int i2) {
        if (i2 == 1) {
            e(true);
        }
    }

    private boolean c(RecyclerView recyclerView, MotionEvent motionEvent) {
        RecyclerView.ViewHolder a2 = CustomRecyclerViewUtils.a(recyclerView, motionEvent.getX(), motionEvent.getY());
        boolean z2 = false;
        if (!a(recyclerView, a2)) {
            return false;
        }
        int e2 = CustomRecyclerViewUtils.e(this.s);
        int d = CustomRecyclerViewUtils.d(this.s);
        int x2 = (int) (motionEvent.getX() + 0.5f);
        this.T = x2;
        this.C = x2;
        int y2 = (int) (motionEvent.getY() + 0.5f);
        this.U = y2;
        this.D = y2;
        this.E = a2.getItemId();
        this.ai = e2 == 0 || (e2 == 1 && d > 1);
        if (e2 == 1 || (e2 == 0 && d > 1)) {
            z2 = true;
        }
        this.aj = z2;
        if (this.F) {
            this.ag.a(motionEvent, this.H);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void a(MotionEvent motionEvent) {
        if (this.F) {
            a(this.s, motionEvent, false);
        }
    }

    private void a(RecyclerView recyclerView, MotionEvent motionEvent, RecyclerView.ViewHolder viewHolder, ItemDraggableRange itemDraggableRange) {
        b(recyclerView, viewHolder);
        this.ag.b();
        this.Q = new DraggingItemInfo(recyclerView, viewHolder, this.T, this.U);
        this.g = viewHolder;
        this.af = itemDraggableRange;
        this.ae = ViewCompat.getOverScrollMode(recyclerView);
        ViewCompat.setOverScrollMode(recyclerView, 2);
        this.T = (int) (motionEvent.getX() + 0.5f);
        this.U = (int) (motionEvent.getY() + 0.5f);
        int i2 = this.U;
        this.aa = i2;
        this.Y = i2;
        this.W = i2;
        int i3 = this.T;
        this.Z = i3;
        this.X = i3;
        this.V = i3;
        this.ad = 0;
        this.s.getParent().requestDisallowInterceptTouchEvent(true);
        r();
        this.P.a(this.Q, viewHolder, this.af);
        this.P.onBindViewHolder(viewHolder, viewHolder.getLayoutPosition());
        this.R = new DraggingItemDecorator(this.s, viewHolder, this.af);
        this.R.a(this.y);
        this.R.a(motionEvent, this.Q);
        int a2 = CustomRecyclerViewUtils.a(this.s);
        if (u() && !this.I && (a2 == 1 || a2 == 0)) {
            this.S = new SwapTargetItemOperator(this.s, viewHolder, this.af, this.Q);
            this.S.b(this.t);
            this.S.a();
            this.S.a(this.R.b(), this.R.a());
        }
        if (this.x != null) {
            this.x.d();
        }
        if (this.ah != null) {
            this.ah.a(this.P.d());
            this.ah.b(0, 0);
        }
    }

    public void j() {
        e(false);
    }

    /* access modifiers changed from: package-private */
    public void e(boolean z2) {
        a(3, false);
        if (z2) {
            f(false);
        } else if (c()) {
            this.ag.d();
        }
    }

    private void f(boolean z2) {
        int i2;
        if (c()) {
            if (this.ag != null) {
                this.ag.c();
            }
            if (!(this.s == null || this.g == null)) {
                ViewCompat.setOverScrollMode(this.s, this.ae);
            }
            if (this.R != null) {
                this.R.a(this.N);
                this.R.a(this.O);
                this.R.a(true);
            }
            if (this.S != null) {
                this.S.a(this.N);
                this.R.a(this.O);
                this.S.a(true);
            }
            if (this.x != null) {
                this.x.c();
            }
            s();
            if (!(this.s == null || this.s.getParent() == null)) {
                this.s.getParent().requestDisallowInterceptTouchEvent(false);
            }
            if (this.s != null) {
                this.s.invalidate();
            }
            this.af = null;
            this.R = null;
            this.S = null;
            this.g = null;
            this.Q = null;
            this.T = 0;
            this.U = 0;
            this.V = 0;
            this.W = 0;
            this.X = 0;
            this.Y = 0;
            this.Z = 0;
            this.aa = 0;
            this.ab = 0;
            this.ac = 0;
            this.ai = false;
            this.aj = false;
            int i3 = -1;
            if (this.P != null) {
                i3 = this.P.d();
                i2 = this.P.e();
                this.P.a(z2);
            } else {
                i2 = -1;
            }
            if (this.ah != null) {
                this.ah.a(i3, i2, z2);
            }
        }
    }

    private boolean a(int i2, boolean z2) {
        boolean z3 = i2 == 1;
        if (this.ag != null) {
            this.ag.b();
        }
        this.C = 0;
        this.D = 0;
        this.T = 0;
        this.U = 0;
        this.V = 0;
        this.W = 0;
        this.X = 0;
        this.Y = 0;
        this.Z = 0;
        this.aa = 0;
        this.ab = 0;
        this.ac = 0;
        this.E = -1;
        this.ai = false;
        this.aj = false;
        if (z2 && c()) {
            f(z3);
        }
        return true;
    }

    private boolean d(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.G) {
            return a(recyclerView, motionEvent, true);
        }
        return false;
    }

    private boolean a(RecyclerView recyclerView, MotionEvent motionEvent, boolean z2) {
        RecyclerView.ViewHolder a2;
        int a3;
        if (this.Q != null) {
            return false;
        }
        int x2 = (int) (motionEvent.getX() + 0.5f);
        int y2 = (int) (motionEvent.getY() + 0.5f);
        this.T = x2;
        this.U = y2;
        if (this.E == -1) {
            return false;
        }
        if ((z2 && ((!this.ai || Math.abs(x2 - this.C) <= this.A) && (!this.aj || Math.abs(y2 - this.D) <= this.A))) || (a2 = CustomRecyclerViewUtils.a(recyclerView, (float) this.C, (float) this.D)) == null || (a3 = CustomRecyclerViewUtils.a(a2)) == -1) {
            return false;
        }
        View view = a2.itemView;
        int translationY = (int) (ViewCompat.getTranslationY(view) + 0.5f);
        int left = view.getLeft();
        if (!this.P.a(a2, a3, x2 - (left + ((int) (ViewCompat.getTranslationX(view) + 0.5f))), y2 - (view.getTop() + translationY))) {
            return false;
        }
        ItemDraggableRange a4 = this.P.a(a2, a3);
        if (a4 == null) {
            a4 = new ItemDraggableRange(0, Math.max(0, this.P.getItemCount() - 1));
        }
        a(a4, a2);
        a(recyclerView, motionEvent, a2, a4);
        return true;
    }

    private void a(ItemDraggableRange itemDraggableRange, RecyclerView.ViewHolder viewHolder) {
        int max = Math.max(0, this.P.getItemCount() - 1);
        if (itemDraggableRange.a() > itemDraggableRange.b()) {
            throw new IllegalStateException("Invalid range specified --- start > range (range = " + itemDraggableRange + Operators.BRACKET_END_STR);
        } else if (itemDraggableRange.a() < 0) {
            throw new IllegalStateException("Invalid range specified --- start < 0 (range = " + itemDraggableRange + Operators.BRACKET_END_STR);
        } else if (itemDraggableRange.b() > max) {
            throw new IllegalStateException("Invalid range specified --- end >= count (range = " + itemDraggableRange + Operators.BRACKET_END_STR);
        } else if (!itemDraggableRange.a(viewHolder.getAdapterPosition())) {
            throw new IllegalStateException("Invalid range specified --- does not contain drag target item (range = " + itemDraggableRange + ", position = " + viewHolder.getAdapterPosition() + Operators.BRACKET_END_STR);
        }
    }

    private void e(RecyclerView recyclerView, MotionEvent motionEvent) {
        this.T = (int) (motionEvent.getX() + 0.5f);
        this.U = (int) (motionEvent.getY() + 0.5f);
        this.X = Math.min(this.X, this.T);
        this.Y = Math.min(this.Y, this.U);
        this.Z = Math.max(this.Z, this.T);
        this.aa = Math.max(this.aa, this.U);
        p();
        if (this.R.a(motionEvent, false)) {
            if (this.S != null) {
                this.S.a(this.R.b(), this.R.a());
            }
            b(recyclerView);
            q();
        }
    }

    private void p() {
        if (CustomRecyclerViewUtils.e(this.s) == 1) {
            if (this.W - this.Y > this.B || this.aa - this.U > this.B) {
                this.ad |= 1;
            }
            if (this.aa - this.W > this.B || this.U - this.Y > this.B) {
                this.ad |= 2;
            }
        } else if (CustomRecyclerViewUtils.e(this.s) == 0) {
            if (this.V - this.X > this.B || this.Z - this.T > this.B) {
                this.ad |= 4;
            }
            if (this.Z - this.V > this.B || this.T - this.X > this.B) {
                this.ad |= 8;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(RecyclerView recyclerView) {
        boolean z2;
        RecyclerView.ViewHolder viewHolder = this.g;
        int i2 = this.T - this.Q.f;
        int i3 = this.U - this.Q.g;
        int d = this.P.d();
        int e2 = this.P.e();
        SwapTarget a2 = a(this.al, recyclerView, viewHolder, this.Q, i2, i3, this.af, this.I, false);
        if (a2.b != -1) {
            boolean z3 = !this.I;
            if (!z3) {
                z3 = this.P.d(d, a2.b);
            }
            z2 = z3;
            if (!z2) {
                a2 = a(this.al, recyclerView, viewHolder, this.Q, i2, i3, this.af, this.I, true);
                if (a2.b != -1) {
                    z2 = this.P.d(d, a2.b);
                }
            }
        } else {
            z2 = false;
        }
        if (z2) {
            a(recyclerView, e2, viewHolder, a2.f5710a);
        }
        if (this.S != null) {
            this.S.a(z2 ? a2.f5710a : null);
        }
    }

    private void q() {
        if (this.ah != null) {
            this.ah.b(this.ab + this.R.d(), this.ac + this.R.c());
        }
    }

    /* access modifiers changed from: package-private */
    public void k() {
        RecyclerView recyclerView = this.s;
        switch (CustomRecyclerViewUtils.e(recyclerView)) {
            case 0:
                a(recyclerView, true);
                return;
            case 1:
                a(recyclerView, false);
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x009d, code lost:
        if ((r8 & (r20 ? 8 : 2)) == 0) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ac, code lost:
        if ((r8 & (r20 ? 4 : 1)) == 0) goto L_0x009f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x016b  */
    /* JADX WARNING: Removed duplicated region for block: B:105:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00f2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.support.v7.widget.RecyclerView r19, boolean r20) {
        /*
            r18 = this;
            r0 = r18
            if (r20 == 0) goto L_0x0009
            int r2 = r19.getWidth()
            goto L_0x000d
        L_0x0009:
            int r2 = r19.getHeight()
        L_0x000d:
            if (r2 != 0) goto L_0x0010
            return
        L_0x0010:
            r3 = 1065353216(0x3f800000, float:1.0)
            float r4 = (float) r2
            float r3 = r3 / r4
            if (r20 == 0) goto L_0x0019
            int r4 = r0.T
            goto L_0x001b
        L_0x0019:
            int r4 = r0.U
        L_0x001b:
            float r4 = (float) r4
            float r4 = r4 * r3
            r5 = 1056964608(0x3f000000, float:0.5)
            float r4 = r4 - r5
            float r6 = java.lang.Math.abs(r4)
            r7 = 1050253722(0x3e99999a, float:0.3)
            float r6 = r5 - r6
            float r7 = r7 - r6
            r6 = 0
            float r7 = java.lang.Math.max(r6, r7)
            r8 = 1079334229(0x40555555, float:3.3333333)
            float r7 = r7 * r8
            int r8 = r0.ad
            com.h6ah4i.android.widget.advrecyclerview.draggable.DraggingItemDecorator r9 = r0.R
            float r4 = java.lang.Math.signum(r4)
            int r4 = (int) r4
            r10 = 1103626240(0x41c80000, float:25.0)
            float r11 = r0.ak
            float r11 = r11 * r10
            float r10 = r0.z
            float r11 = r11 * r10
            float r11 = r11 * r7
            float r11 = r11 + r5
            int r7 = (int) r11
            int r4 = r4 * r7
            com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange r7 = r0.af
            android.support.v7.widget.RecyclerView r10 = r0.s
            int r10 = com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils.b((android.support.v7.widget.RecyclerView) r10)
            android.support.v7.widget.RecyclerView r11 = r0.s
            int r11 = com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils.c((android.support.v7.widget.RecyclerView) r11)
            r12 = -1
            r13 = 0
            r14 = 1
            if (r10 == r12) goto L_0x0076
            int r15 = r7.a()
            if (r10 > r15) goto L_0x0069
            r15 = 1
            goto L_0x006a
        L_0x0069:
            r15 = 0
        L_0x006a:
            int r16 = r7.a()
            int r6 = r16 + -1
            if (r10 > r6) goto L_0x0074
            r6 = 1
            goto L_0x0078
        L_0x0074:
            r6 = 0
            goto L_0x0078
        L_0x0076:
            r6 = 0
            r15 = 0
        L_0x0078:
            if (r11 == r12) goto L_0x008e
            int r12 = r7.b()
            if (r11 < r12) goto L_0x0082
            r12 = 1
            goto L_0x0083
        L_0x0082:
            r12 = 0
        L_0x0083:
            int r7 = r7.b()
            int r7 = r7 + r14
            if (r11 < r7) goto L_0x008c
            r7 = 1
            goto L_0x0090
        L_0x008c:
            r7 = 0
            goto L_0x0090
        L_0x008e:
            r7 = 0
            r12 = 0
        L_0x0090:
            r16 = 2
            if (r4 <= 0) goto L_0x00a1
            if (r20 == 0) goto L_0x0099
            r17 = 8
            goto L_0x009b
        L_0x0099:
            r17 = 2
        L_0x009b:
            r8 = r8 & r17
            if (r8 != 0) goto L_0x00af
        L_0x009f:
            r4 = 0
            goto L_0x00af
        L_0x00a1:
            if (r4 >= 0) goto L_0x00af
            if (r20 == 0) goto L_0x00a8
            r17 = 4
            goto L_0x00aa
        L_0x00a8:
            r17 = 1
        L_0x00aa:
            r8 = r8 & r17
            if (r8 != 0) goto L_0x00af
            goto L_0x009f
        L_0x00af:
            if (r6 != 0) goto L_0x00b3
            if (r4 < 0) goto L_0x00b7
        L_0x00b3:
            if (r7 != 0) goto L_0x00e7
            if (r4 <= 0) goto L_0x00e7
        L_0x00b7:
            r18.e((android.support.v7.widget.RecyclerView) r19)
            if (r20 == 0) goto L_0x00c1
            int r6 = r0.d((int) r4)
            goto L_0x00c5
        L_0x00c1:
            int r6 = r0.c((int) r4)
        L_0x00c5:
            if (r4 >= 0) goto L_0x00cd
            r7 = r15 ^ 1
            r9.c(r7)
            goto L_0x00d2
        L_0x00cd:
            r7 = r12 ^ 1
            r9.c(r7)
        L_0x00d2:
            r9.b(r14)
            com.h6ah4i.android.widget.advrecyclerview.draggable.SwapTargetItemOperator r7 = r0.S
            if (r7 == 0) goto L_0x00eb
            com.h6ah4i.android.widget.advrecyclerview.draggable.SwapTargetItemOperator r7 = r0.S
            int r8 = r9.b()
            int r12 = r9.a()
            r7.a((int) r8, (int) r12)
            goto L_0x00eb
        L_0x00e7:
            r9.c(r13)
            r6 = 0
        L_0x00eb:
            if (r6 == 0) goto L_0x00ee
            r13 = 1
        L_0x00ee:
            com.h6ah4i.android.widget.advrecyclerview.draggable.BaseEdgeEffectDecorator r7 = r0.x
            if (r7 == 0) goto L_0x0162
            if (r20 == 0) goto L_0x00f9
            int r7 = r9.k()
            goto L_0x00fd
        L_0x00f9:
            int r7 = r9.i()
        L_0x00fd:
            if (r20 == 0) goto L_0x0104
            int r8 = r9.l()
            goto L_0x0108
        L_0x0104:
            int r8 = r9.j()
        L_0x0108:
            int r12 = r7 + r8
            int r12 = r12 / 2
            if (r10 != 0) goto L_0x0115
            if (r11 != 0) goto L_0x0115
            if (r4 >= 0) goto L_0x0113
            goto L_0x0119
        L_0x0113:
            r7 = r8
            goto L_0x0119
        L_0x0115:
            int r2 = r2 / 2
            if (r12 >= r2) goto L_0x0113
        L_0x0119:
            float r2 = (float) r7
            float r2 = r2 * r3
            float r2 = r2 - r5
            float r3 = java.lang.Math.abs(r2)
            r5 = 1053609165(0x3ecccccd, float:0.4)
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 <= 0) goto L_0x015d
            if (r4 == 0) goto L_0x015d
            if (r13 != 0) goto L_0x015d
            r3 = 0
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            r4 = 1000593162(0x3ba3d70a, float:0.005)
            if (r2 >= 0) goto L_0x0149
            if (r20 == 0) goto L_0x013d
            boolean r2 = r9.g()
            if (r2 == 0) goto L_0x015e
            goto L_0x0143
        L_0x013d:
            boolean r2 = r9.e()
            if (r2 == 0) goto L_0x015e
        L_0x0143:
            float r2 = r0.z
            float r2 = -r2
            float r2 = r2 * r4
            goto L_0x015f
        L_0x0149:
            if (r20 == 0) goto L_0x0152
            boolean r2 = r9.h()
            if (r2 == 0) goto L_0x015e
            goto L_0x0158
        L_0x0152:
            boolean r2 = r9.f()
            if (r2 == 0) goto L_0x015e
        L_0x0158:
            float r2 = r0.z
            float r2 = r2 * r4
            goto L_0x015f
        L_0x015d:
            r3 = 0
        L_0x015e:
            r2 = 0
        L_0x015f:
            r0.b((float) r2)
        L_0x0162:
            android.support.v7.widget.RecyclerView r2 = r0.s
            java.lang.Runnable r3 = r0.am
            android.support.v4.view.ViewCompat.postOnAnimation(r2, r3)
            if (r6 == 0) goto L_0x017b
            if (r20 == 0) goto L_0x0173
            int r1 = r0.ab
            int r1 = r1 + r6
            r0.ab = r1
            goto L_0x0178
        L_0x0173:
            int r1 = r0.ac
            int r1 = r1 + r6
            r0.ac = r1
        L_0x0178:
            r18.q()
        L_0x017b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager.a(android.support.v7.widget.RecyclerView, boolean):void");
    }

    private void b(float f2) {
        if (f2 == 0.0f) {
            this.x.c();
        } else if (f2 < 0.0f) {
            this.x.a(f2);
        } else {
            this.x.b(f2);
        }
    }

    private int c(int i2) {
        this.L = 0;
        this.J = true;
        this.s.scrollBy(0, i2);
        this.J = false;
        return this.L;
    }

    private int d(int i2) {
        this.K = 0;
        this.J = true;
        this.s.scrollBy(i2, 0);
        this.J = false;
        return this.K;
    }

    /* access modifiers changed from: package-private */
    public RecyclerView l() {
        return this.s;
    }

    private void r() {
        this.u.a();
    }

    private void s() {
        if (this.u != null) {
            this.u.b();
        }
    }

    private void a(RecyclerView recyclerView, int i2, @Nullable RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder2) {
        Rect a2 = CustomRecyclerViewUtils.a(viewHolder2.itemView, this.M);
        int adapterPosition = viewHolder2.getAdapterPosition();
        int abs = Math.abs(i2 - adapterPosition);
        if (i2 != -1 && adapterPosition != -1 && recyclerView.getAdapter().getItemId(i2) == this.Q.c) {
            boolean z2 = false;
            boolean z3 = CustomRecyclerViewUtils.b(CustomRecyclerViewUtils.a(recyclerView)) && (!u() || !this.I);
            if (abs != 0) {
                if (abs == 1 && viewHolder != null && z3) {
                    View view = viewHolder.itemView;
                    View view2 = viewHolder2.itemView;
                    Rect rect = this.Q.h;
                    if (this.ai) {
                        int min = Math.min(view.getLeft() - rect.left, view2.getLeft() - a2.left);
                        float max = ((float) min) + (((float) (Math.max(view.getRight() + rect.right, view2.getRight() + a2.right) - min)) * 0.5f);
                        float f2 = ((float) (this.T - this.Q.f)) + (((float) this.Q.f5703a) * 0.5f);
                        if (adapterPosition >= i2 ? f2 > max : f2 < max) {
                            z2 = true;
                        }
                    }
                    if (!z2 && this.aj) {
                        int min2 = Math.min(view.getTop() - rect.top, view2.getTop() - a2.top);
                        float max2 = ((float) min2) + (((float) (Math.max(view.getBottom() + rect.bottom, view2.getBottom() + a2.bottom) - min2)) * 0.5f);
                        float f3 = ((float) (this.U - this.Q.g)) + (((float) this.Q.b) * 0.5f);
                        if (adapterPosition >= i2) {
                        }
                    }
                }
                z2 = true;
            }
            if (z2) {
                a(recyclerView, viewHolder2, a2, i2, adapterPosition);
            }
        }
    }

    private void a(RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, Rect rect, int i2, int i3) {
        if (this.ah != null) {
            this.ah.a(i2, i3);
        }
        RecyclerView.LayoutManager layoutManager = this.s.getLayoutManager();
        boolean z2 = true;
        if (CustomRecyclerViewUtils.a(CustomRecyclerViewUtils.a(this.s)) != 1) {
            z2 = false;
        }
        int a2 = CustomRecyclerViewUtils.a(this.s, false);
        View a3 = CustomRecyclerViewUtils.a(layoutManager, i2);
        View a4 = CustomRecyclerViewUtils.a(layoutManager, i3);
        View a5 = CustomRecyclerViewUtils.a(layoutManager, a2);
        Integer a6 = a(a3, z2);
        Integer a7 = a(a4, z2);
        Integer a8 = a(a5, z2);
        this.P.e(i2, i3);
        if (a2 == i2 && a8 != null && a7 != null) {
            recyclerView.scrollBy(0, -(a7.intValue() - a8.intValue()));
            d(recyclerView);
        } else if (a2 == i3 && a3 != null && a6 != null && !a6.equals(a7)) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) a3.getLayoutParams();
            recyclerView.scrollBy(0, -(layoutManager.getDecoratedMeasuredHeight(a3) + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin));
            d(recyclerView);
        }
    }

    private static Integer a(View view, boolean z2) {
        if (view == null) {
            return null;
        }
        return Integer.valueOf(z2 ? view.getTop() : view.getLeft());
    }

    private static DraggableItemWrapperAdapter c(RecyclerView recyclerView) {
        return (DraggableItemWrapperAdapter) WrapperAdapterUtils.a(recyclerView.getAdapter(), DraggableItemWrapperAdapter.class);
    }

    private boolean a(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (!(viewHolder instanceof DraggableItemViewHolder)) {
            return false;
        }
        int adapterPosition = viewHolder.getAdapterPosition();
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapterPosition < 0 || adapterPosition >= adapter.getItemCount() || viewHolder.getItemId() != adapter.getItemId(adapterPosition)) {
            return false;
        }
        return true;
    }

    private static boolean t() {
        return Build.VERSION.SDK_INT >= 14;
    }

    private static boolean u() {
        return Build.VERSION.SDK_INT >= 11;
    }

    private static void b(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ItemAnimator itemAnimator = recyclerView != null ? recyclerView.getItemAnimator() : null;
        if (itemAnimator != null) {
            itemAnimator.endAnimation(viewHolder);
        }
    }

    private static void d(RecyclerView recyclerView) {
        RecyclerView.ItemAnimator itemAnimator = recyclerView != null ? recyclerView.getItemAnimator() : null;
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
    }

    private void e(RecyclerView recyclerView) {
        if (this.S != null) {
            d(recyclerView);
        }
    }

    static SwapTarget a(SwapTarget swapTarget, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, DraggingItemInfo draggingItemInfo, int i2, int i3, ItemDraggableRange itemDraggableRange, boolean z2, boolean z3) {
        RecyclerView.ViewHolder viewHolder2;
        int i4;
        int i5;
        SwapTarget swapTarget2 = swapTarget;
        RecyclerView.ViewHolder viewHolder3 = viewHolder;
        DraggingItemInfo draggingItemInfo2 = draggingItemInfo;
        ItemDraggableRange itemDraggableRange2 = itemDraggableRange;
        swapTarget.a();
        RecyclerView.ViewHolder viewHolder4 = null;
        if (viewHolder3 == null || (viewHolder.getAdapterPosition() != -1 && viewHolder.getItemId() == draggingItemInfo2.c)) {
            int a2 = CustomRecyclerViewUtils.a(recyclerView);
            boolean z4 = CustomRecyclerViewUtils.a(a2) == 1;
            if (z4) {
                i4 = i3;
                i5 = Math.min(Math.max(i2, recyclerView.getPaddingLeft()), Math.max(0, (recyclerView.getWidth() - recyclerView.getPaddingRight()) - draggingItemInfo2.f5703a));
            } else {
                i5 = i2;
                i4 = Math.min(Math.max(i3, recyclerView.getPaddingTop()), Math.max(0, (recyclerView.getHeight() - recyclerView.getPaddingBottom()) - draggingItemInfo2.b));
            }
            switch (a2) {
                case 0:
                case 1:
                    viewHolder2 = c(recyclerView, viewHolder, draggingItemInfo, i5, i4, z4, z2, z3);
                    break;
                case 2:
                case 3:
                    viewHolder2 = a(recyclerView, viewHolder, draggingItemInfo, i5, i4, z4, z2, z3);
                    break;
                case 4:
                case 5:
                    viewHolder2 = b(recyclerView, viewHolder, draggingItemInfo, i5, i4, z4, z2, z3);
                    break;
            }
        }
        viewHolder2 = null;
        if (viewHolder2 == viewHolder3) {
            swapTarget2.c = true;
            viewHolder2 = null;
        }
        if (viewHolder2 == null || itemDraggableRange2 == null || itemDraggableRange2.a(viewHolder2.getAdapterPosition())) {
            viewHolder4 = viewHolder2;
        }
        swapTarget2.f5710a = viewHolder4;
        swapTarget2.b = CustomRecyclerViewUtils.b(viewHolder4);
        return swapTarget2;
    }

    private static RecyclerView.ViewHolder a(RecyclerView recyclerView, @Nullable RecyclerView.ViewHolder viewHolder, DraggingItemInfo draggingItemInfo, int i2, int i3, boolean z2, boolean z3, boolean z4) {
        if (z4) {
            return null;
        }
        RecyclerView.ViewHolder a2 = a(recyclerView, viewHolder, draggingItemInfo, i2, i3, z2);
        return a2 == null ? b(recyclerView, viewHolder, draggingItemInfo, i2, i3, z2) : a2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0147  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0153  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.support.v7.widget.RecyclerView.ViewHolder b(android.support.v7.widget.RecyclerView r21, @android.support.annotation.Nullable android.support.v7.widget.RecyclerView.ViewHolder r22, com.h6ah4i.android.widget.advrecyclerview.draggable.DraggingItemInfo r23, int r24, int r25, boolean r26, boolean r27, boolean r28) {
        /*
            r0 = r21
            r1 = r22
            r2 = r23
            r5 = 0
            if (r28 == 0) goto L_0x000a
            return r5
        L_0x000a:
            if (r1 != 0) goto L_0x000d
            return r5
        L_0x000d:
            int r6 = com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils.d((android.support.v7.widget.RecyclerView) r21)
            int r7 = com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils.d((android.support.v7.widget.RecyclerView.ViewHolder) r22)
            r8 = 1065353216(0x3f800000, float:1.0)
            r9 = 0
            r10 = 1
            r11 = 2
            if (r26 == 0) goto L_0x0098
            int r12 = r24 + 1
            int r13 = r2.f5703a
            int r3 = r24 + r13
            int r3 = r3 - r11
            int r13 = r25 + 1
            int r14 = r2.b
            int r14 = r14 / r11
            int r14 = r25 + r14
            int r14 = r14 - r10
            int r15 = r2.b
            int r15 = r25 + r15
            int r15 = r15 - r11
            int r16 = r21.getPaddingLeft()
            int r17 = r21.getPaddingRight()
            int r18 = r21.getWidth()
            int r18 = r18 - r16
            int r5 = r18 - r17
            float r5 = (float) r5
            float r11 = (float) r6
            float r8 = r8 / r11
            float r5 = r5 * r8
            android.graphics.Rect r8 = r2.h
            int r8 = r8.left
            int r8 = r12 - r8
            int r8 = r8 - r16
            float r8 = (float) r8
            float r8 = r8 / r5
            int r8 = (int) r8
            int r8 = java.lang.Math.max(r8, r9)
            int r6 = r6 - r10
            int r8 = java.lang.Math.min(r8, r6)
            android.graphics.Rect r2 = r2.h
            int r2 = r2.right
            int r2 = r3 - r2
            int r2 = r2 - r16
            float r2 = (float) r2
            float r2 = r2 / r5
            int r2 = (int) r2
            int r2 = java.lang.Math.max(r2, r9)
            int r2 = java.lang.Math.min(r2, r6)
            android.view.View r1 = r1.itemView
            int r1 = r1.getTop()
            float r5 = (float) r12
            float r6 = (float) r13
            android.support.v7.widget.RecyclerView$ViewHolder r11 = com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r5, (float) r6)
            float r12 = (float) r14
            android.support.v7.widget.RecyclerView$ViewHolder r13 = com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r5, (float) r12)
            float r14 = (float) r15
            android.support.v7.widget.RecyclerView$ViewHolder r5 = com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r5, (float) r14)
            float r3 = (float) r3
            android.support.v7.widget.RecyclerView$ViewHolder r6 = com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r3, (float) r6)
            android.support.v7.widget.RecyclerView$ViewHolder r12 = com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r3, (float) r12)
            android.support.v7.widget.RecyclerView$ViewHolder r0 = com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r3, (float) r14)
            r3 = r25
            r4 = r1
            r1 = r0
            r0 = r6
            r6 = r5
            r5 = r13
            goto L_0x0116
        L_0x0098:
            int r5 = r24 + 1
            int r11 = r2.f5703a
            r12 = 2
            int r11 = r11 / r12
            int r11 = r24 + r11
            int r11 = r11 - r10
            int r13 = r2.f5703a
            int r13 = r24 + r13
            int r13 = r13 - r12
            int r14 = r25 + 1
            int r15 = r2.b
            int r4 = r25 + r15
            int r4 = r4 - r12
            int r12 = r21.getPaddingTop()
            int r15 = r21.getPaddingBottom()
            int r16 = r21.getHeight()
            int r16 = r16 - r12
            int r15 = r16 - r15
            float r15 = (float) r15
            float r10 = (float) r6
            float r8 = r8 / r10
            float r15 = r15 * r8
            android.graphics.Rect r8 = r2.h
            int r8 = r8.top
            int r8 = r5 - r8
            int r8 = r8 - r12
            float r8 = (float) r8
            float r8 = r8 / r15
            int r8 = (int) r8
            int r8 = java.lang.Math.max(r8, r9)
            r10 = 1
            int r6 = r6 - r10
            int r8 = java.lang.Math.min(r8, r6)
            android.graphics.Rect r2 = r2.h
            int r2 = r2.left
            int r2 = r13 - r2
            int r2 = r2 - r12
            float r2 = (float) r2
            float r2 = r2 / r15
            int r2 = (int) r2
            int r2 = java.lang.Math.max(r2, r9)
            int r2 = java.lang.Math.min(r2, r6)
            android.view.View r1 = r1.itemView
            int r1 = r1.getLeft()
            float r5 = (float) r5
            float r6 = (float) r14
            android.support.v7.widget.RecyclerView$ViewHolder r12 = com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r5, (float) r6)
            float r11 = (float) r11
            android.support.v7.widget.RecyclerView$ViewHolder r14 = com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r11, (float) r6)
            float r13 = (float) r13
            android.support.v7.widget.RecyclerView$ViewHolder r6 = com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r13, (float) r6)
            float r4 = (float) r4
            android.support.v7.widget.RecyclerView$ViewHolder r5 = com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r5, (float) r4)
            android.support.v7.widget.RecyclerView$ViewHolder r11 = com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r11, (float) r4)
            android.support.v7.widget.RecyclerView$ViewHolder r0 = com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r13, (float) r4)
            r3 = r24
            r4 = r1
            r1 = r0
            r0 = r5
            r5 = r14
            r20 = r12
            r12 = r11
            r11 = r20
        L_0x0116:
            r13 = 3
            if (r5 == 0) goto L_0x0124
            if (r5 != r11) goto L_0x011d
            r11 = 3
            goto L_0x011e
        L_0x011d:
            r11 = 1
        L_0x011e:
            if (r5 != r6) goto L_0x0125
            r6 = r11 | 4
            r11 = r6
            goto L_0x0125
        L_0x0124:
            r11 = 0
        L_0x0125:
            if (r12 == 0) goto L_0x0130
            if (r12 != r0) goto L_0x012b
            r9 = 3
            goto L_0x012c
        L_0x012b:
            r9 = 1
        L_0x012c:
            if (r12 != r1) goto L_0x0130
            r9 = r9 | 4
        L_0x0130:
            int r0 = java.lang.Integer.bitCount(r11)
            int r1 = java.lang.Integer.bitCount(r9)
            if (r8 == r7) goto L_0x0144
            if (r8 != r2) goto L_0x0144
            if (r0 != r13) goto L_0x0140
            r2 = r5
            goto L_0x0145
        L_0x0140:
            if (r1 != r13) goto L_0x0144
            r2 = r12
            goto L_0x0145
        L_0x0144:
            r2 = 0
        L_0x0145:
            if (r2 != 0) goto L_0x0153
            r6 = 2
            if (r0 != r6) goto L_0x014d
            if (r1 == r6) goto L_0x014d
            goto L_0x0155
        L_0x014d:
            if (r1 != r6) goto L_0x0154
            if (r0 == r6) goto L_0x0154
            r5 = r12
            goto L_0x0155
        L_0x0153:
            r6 = 2
        L_0x0154:
            r5 = r2
        L_0x0155:
            if (r5 == 0) goto L_0x016e
            int r0 = com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils.d((android.support.v7.widget.RecyclerView.ViewHolder) r5)
            if (r7 != r0) goto L_0x016e
            if (r3 > r4) goto L_0x0167
            r0 = r11 | r9
            r0 = r0 & r6
            if (r0 == 0) goto L_0x016e
        L_0x0164:
            r19 = 0
            goto L_0x0170
        L_0x0167:
            r0 = r11 | r9
            r0 = r0 & 4
            if (r0 == 0) goto L_0x016e
            goto L_0x0164
        L_0x016e:
            r19 = r5
        L_0x0170:
            return r19
        */
        throw new UnsupportedOperationException("Method not decompiled: com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager.b(android.support.v7.widget.RecyclerView, android.support.v7.widget.RecyclerView$ViewHolder, com.h6ah4i.android.widget.advrecyclerview.draggable.DraggingItemInfo, int, int, boolean, boolean, boolean):android.support.v7.widget.RecyclerView$ViewHolder");
    }

    private static RecyclerView.ViewHolder a(RecyclerView recyclerView, @Nullable RecyclerView.ViewHolder viewHolder, DraggingItemInfo draggingItemInfo, int i2, int i3, boolean z2) {
        int i4;
        int i5;
        if (z2) {
            int i6 = draggingItemInfo.h.left;
            i5 = i2 + ((int) ((((float) ((draggingItemInfo.f5703a + (draggingItemInfo.h.right + i6)) / draggingItemInfo.i)) * 0.5f) - ((float) i6)));
            i4 = i3 + (draggingItemInfo.b / 2);
        } else {
            int i7 = draggingItemInfo.h.top;
            int i8 = draggingItemInfo.h.bottom;
            i5 = i2 + (draggingItemInfo.f5703a / 2);
            i4 = i3 + ((int) ((((float) ((draggingItemInfo.b + (i8 + i7)) / draggingItemInfo.i)) * 0.5f) - ((float) i7)));
        }
        return CustomRecyclerViewUtils.a(recyclerView, (float) i5, (float) i4);
    }

    private static RecyclerView.ViewHolder b(RecyclerView recyclerView, @Nullable RecyclerView.ViewHolder viewHolder, DraggingItemInfo draggingItemInfo, int i2, int i3, boolean z2) {
        int d = CustomRecyclerViewUtils.d(recyclerView);
        int height = recyclerView.getHeight();
        int width = recyclerView.getWidth();
        int i4 = 0;
        int paddingLeft = z2 ? recyclerView.getPaddingLeft() : 0;
        int paddingTop = !z2 ? recyclerView.getPaddingTop() : 0;
        int paddingRight = z2 ? recyclerView.getPaddingRight() : 0;
        if (!z2) {
            i4 = recyclerView.getPaddingBottom();
        }
        int i5 = ((width - paddingLeft) - paddingRight) / d;
        int i6 = ((height - paddingTop) - i4) / d;
        int i7 = i2 + (draggingItemInfo.f5703a / 2);
        int i8 = i3 + (draggingItemInfo.b / 2);
        for (int i9 = d - 1; i9 >= 0; i9--) {
            RecyclerView.ViewHolder a2 = CustomRecyclerViewUtils.a(recyclerView, (float) (z2 ? (i5 * i9) + paddingLeft + (i5 / 2) : i7), (float) (!z2 ? (i6 * i9) + paddingTop + (i6 / 2) : i8));
            if (a2 != null) {
                int itemCount = recyclerView.getLayoutManager().getItemCount();
                int adapterPosition = a2.getAdapterPosition();
                if (adapterPosition == -1 || adapterPosition != itemCount - 1) {
                    return null;
                }
                return a2;
            }
        }
        return null;
    }

    private static RecyclerView.ViewHolder a(RecyclerView recyclerView, @Nullable RecyclerView.ViewHolder viewHolder, DraggingItemInfo draggingItemInfo, int i2, int i3, boolean z2, RecyclerView.ViewHolder viewHolder2) {
        if (CustomRecyclerViewUtils.d(viewHolder2) != CustomRecyclerViewUtils.d(viewHolder)) {
            return viewHolder2;
        }
        int c = CustomRecyclerViewUtils.c(viewHolder);
        int layoutPosition = viewHolder2.getLayoutPosition();
        if (c == -1) {
            return viewHolder2;
        }
        if (z2) {
            i2 += draggingItemInfo.f5703a / 2;
            if (layoutPosition >= c) {
                i3 += draggingItemInfo.b;
            }
        } else {
            i3 += draggingItemInfo.b / 2;
            if (layoutPosition >= c) {
                i2 += draggingItemInfo.f5703a;
            }
        }
        if (viewHolder2 == CustomRecyclerViewUtils.a(recyclerView, (float) i2, (float) i3)) {
            return null;
        }
        return viewHolder2;
    }

    private static RecyclerView.ViewHolder c(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, DraggingItemInfo draggingItemInfo, int i2, int i3, boolean z2, boolean z3, boolean z4) {
        RecyclerView.ViewHolder findViewHolderForAdapterPosition;
        if (viewHolder == null) {
            return null;
        }
        if (z3 || z4) {
            float f2 = viewHolder.itemView.getResources().getDisplayMetrics().density * 8.0f;
            float min = Math.min(((float) draggingItemInfo.f5703a) * 0.2f, f2);
            float min2 = Math.min(((float) draggingItemInfo.b) * 0.2f, f2);
            float f3 = ((float) i2) + (((float) draggingItemInfo.f5703a) * 0.5f);
            float f4 = ((float) i3) + (((float) draggingItemInfo.b) * 0.5f);
            RecyclerView.ViewHolder a2 = CustomRecyclerViewUtils.a(recyclerView, f3 - min, f4 - min2);
            if (a2 == CustomRecyclerViewUtils.a(recyclerView, f3 + min, f4 + min2)) {
                return a2;
            }
            return null;
        }
        int adapterPosition = viewHolder.getAdapterPosition();
        int top = z2 ? viewHolder.itemView.getTop() : viewHolder.itemView.getLeft();
        if (z2) {
            i2 = i3;
        }
        if (i2 < top) {
            if (adapterPosition <= 0) {
                return null;
            }
            findViewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(adapterPosition - 1);
        } else if (i2 <= top || adapterPosition >= recyclerView.getAdapter().getItemCount() - 1) {
            return null;
        } else {
            findViewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(adapterPosition + 1);
        }
        return findViewHolderForAdapterPosition;
    }

    public void b(int i2) {
        this.N = i2;
    }

    public int m() {
        return this.N;
    }

    public void b(@Nullable Interpolator interpolator) {
        this.O = interpolator;
    }

    @Nullable
    public Interpolator n() {
        return this.O;
    }

    /* access modifiers changed from: package-private */
    public void o() {
        this.g = null;
        this.R.m();
    }

    /* access modifiers changed from: package-private */
    public void a(RecyclerView.ViewHolder viewHolder) {
        this.g = viewHolder;
        this.R.a(viewHolder);
    }

    private static class ScrollOnDraggingProcessRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<RecyclerViewDragDropManager> f5709a;
        private boolean b;

        public ScrollOnDraggingProcessRunnable(RecyclerViewDragDropManager recyclerViewDragDropManager) {
            this.f5709a = new WeakReference<>(recyclerViewDragDropManager);
        }

        public void a() {
            RecyclerViewDragDropManager recyclerViewDragDropManager;
            RecyclerView l;
            if (!this.b && (recyclerViewDragDropManager = (RecyclerViewDragDropManager) this.f5709a.get()) != null && (l = recyclerViewDragDropManager.l()) != null) {
                ViewCompat.postOnAnimation(l, this);
                this.b = true;
            }
        }

        public void b() {
            if (this.b) {
                this.b = false;
            }
        }

        public void c() {
            this.f5709a.clear();
            this.b = false;
        }

        public void run() {
            RecyclerViewDragDropManager recyclerViewDragDropManager = (RecyclerViewDragDropManager) this.f5709a.get();
            if (recyclerViewDragDropManager != null && this.b) {
                recyclerViewDragDropManager.k();
                RecyclerView l = recyclerViewDragDropManager.l();
                if (l == null || !this.b) {
                    this.b = false;
                } else {
                    ViewCompat.postOnAnimation(l, this);
                }
            }
        }
    }

    private static class InternalHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private static final int f5708a = 1;
        private static final int b = 2;
        private RecyclerViewDragDropManager c;
        private MotionEvent d;

        public InternalHandler(RecyclerViewDragDropManager recyclerViewDragDropManager) {
            this.c = recyclerViewDragDropManager;
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
                    this.c.e(true);
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
