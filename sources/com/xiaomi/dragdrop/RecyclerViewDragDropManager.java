package com.xiaomi.dragdrop;

import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import com.taobao.weex.el.parse.Operators;
import java.lang.ref.WeakReference;

public class RecyclerViewDragDropManager implements DraggableItemConstants {
    public static final Interpolator e = new BasicSwapTargetTranslationInterpolator();
    public static final Interpolator f = new DecelerateInterpolator();
    private static final String o = "ARVDragDropManager";
    private static final int p = 0;
    private static final int q = 1;
    private static final int r = 2;
    private static final int s = 4;
    private static final int t = 8;
    private static final boolean u = true;
    private static final boolean v = false;
    private static final float w = 0.3f;
    private static final float x = 25.0f;
    private static final float y = 1.5f;
    private Interpolator A = e;
    private ScrollOnDraggingProcessRunnable B = new ScrollOnDraggingProcessRunnable(this);
    private RecyclerView.OnItemTouchListener C = new RecyclerView.OnItemTouchListener() {
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            return RecyclerViewDragDropManager.this.a(recyclerView, motionEvent);
        }

        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            RecyclerViewDragDropManager.this.b(recyclerView, motionEvent);
        }

        public void onRequestDisallowInterceptTouchEvent(boolean z) {
            RecyclerViewDragDropManager.this.e(z);
        }
    };
    private RecyclerView.OnScrollListener D = new RecyclerView.OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            RecyclerViewDragDropManager.this.a(recyclerView, i);
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            RecyclerViewDragDropManager.this.a(recyclerView, i, i2);
        }
    };
    private BaseEdgeEffectDecorator E;
    private NinePatchDrawable F;
    private float G;
    private int H;
    private int I;
    private int J;
    private int K;
    private long L = -1;
    private boolean M;
    private boolean N = true;
    private int O = ViewConfiguration.getLongPressTimeout();
    private boolean P;
    private boolean Q;
    private int R;
    private int S;
    private final Rect T = new Rect();
    private int U = 200;
    private Interpolator V = f;
    private DraggableItemWrapperAdapter W;
    private DraggingItemInfo X;
    private DraggingItemDecorator Y;
    private SwapTargetItemOperator Z;
    private boolean aA = false;
    private final Runnable aB = new Runnable() {
        public void run() {
            if (RecyclerViewDragDropManager.this.g != null) {
                RecyclerViewDragDropManager.this.b(RecyclerViewDragDropManager.this.l());
            }
        }
    };
    private int aa;
    private int ab;
    private int ac;
    private int ad;
    private int ae;
    private int af;
    private int ag;
    private int ah;
    private int ai;
    private int aj;
    private int ak = 0;
    private int al;
    private ItemDraggableRange am;
    private InternalHandler an;
    private OnItemDragEventListener ao;
    private boolean ap;
    private boolean aq;
    private float ar = 1.0f;
    private boolean as = false;
    private SwapTarget at = new SwapTarget();
    private TouchOnItemDecoration au;
    private int av;
    private int aw;
    private int ax;
    private int ay;
    private VelocityTracker az;
    RecyclerView.ViewHolder g;
    HorizonFlingDetection h;
    boolean i = false;
    boolean j = false;
    OnScrollLitener k;
    boolean l = false;
    boolean m = false;
    int n = -1;
    private RecyclerView z;

    public interface HorizonFlingDetection {
        void a(int i);
    }

    public interface OnItemDragEventListener {
        void a(int i);

        void a(int i, int i2);

        void a(int i, int i2, boolean z);

        void b(int i, int i2);
    }

    public interface OnScrollLitener {
        void a();
    }

    static class SwapTarget {

        /* renamed from: a  reason: collision with root package name */
        public RecyclerView.ViewHolder f10108a;
        public int b;
        public boolean c;

        SwapTarget() {
        }

        public void a() {
            this.f10108a = null;
            this.b = -1;
            this.c = false;
        }
    }

    public void a(HorizonFlingDetection horizonFlingDetection) {
        this.h = horizonFlingDetection;
    }

    public void a(OnScrollLitener onScrollLitener) {
        this.k = onScrollLitener;
    }

    public void a(boolean z2) {
        this.aA = z2;
    }

    public RecyclerView.Adapter a(@NonNull RecyclerView.Adapter adapter) {
        if (!adapter.hasStableIds()) {
            throw new IllegalArgumentException("The passed adapter does not support stable IDs");
        } else if (this.W == null) {
            this.W = new DraggableItemWrapperAdapter(this, adapter);
            return this.W;
        } else {
            throw new IllegalStateException("already have a wrapped adapter");
        }
    }

    public boolean a() {
        return this.C == null;
    }

    public void a(@NonNull RecyclerView recyclerView) {
        if (a()) {
            throw new IllegalStateException("Accessing released object");
        } else if (this.z != null) {
            throw new IllegalStateException("RecyclerView instance has already been set");
        } else if (this.W == null || d(recyclerView) != this.W) {
            throw new IllegalStateException("adapter is not set properly");
        } else {
            this.z = recyclerView;
            this.z.addOnScrollListener(this.D);
            this.z.addOnItemTouchListener(this.C);
            this.G = this.z.getResources().getDisplayMetrics().density;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(this.z.getContext());
            this.H = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
            this.av = viewConfiguration.getScaledMinimumFlingVelocity();
            this.aw = viewConfiguration.getScaledMaximumFlingVelocity();
            this.I = (int) ((((float) this.H) * y) + 0.5f);
            this.an = new InternalHandler(this);
            if (v()) {
                switch (CustomRecyclerViewUtils.e(this.z)) {
                    case 0:
                        this.E = new LeftRightEdgeEffectDecorator(this.z);
                        break;
                    case 1:
                        this.E = new TopBottomEdgeEffectDecorator(this.z);
                        break;
                }
                if (this.E != null) {
                    this.E.a();
                }
            }
        }
    }

    public void b() {
        q();
        f(true);
        if (this.an != null) {
            this.an.a();
            this.an = null;
        }
        if (this.E != null) {
            this.E.b();
            this.E = null;
        }
        if (!(this.z == null || this.C == null)) {
            this.z.removeOnItemTouchListener(this.C);
        }
        this.C = null;
        if (!(this.z == null || this.D == null)) {
            this.z.removeOnScrollListener(this.D);
        }
        this.D = null;
        if (this.B != null) {
            this.B.c();
            this.B = null;
        }
        this.W = null;
        this.z = null;
        this.A = null;
    }

    private void p() {
        if (this.az != null) {
            this.az.recycle();
        }
        this.az = VelocityTracker.obtain();
    }

    private void q() {
        if (this.az != null) {
            this.az.recycle();
            this.az = null;
        }
    }

    public boolean c() {
        return this.X != null && !this.an.e();
    }

    public void a(@Nullable NinePatchDrawable ninePatchDrawable) {
        this.F = ninePatchDrawable;
    }

    public void a(@Nullable Interpolator interpolator) {
        this.A = interpolator;
    }

    public boolean d() {
        return this.M;
    }

    public void b(boolean z2) {
        this.M = z2;
    }

    public boolean e() {
        return this.N;
    }

    public void c(boolean z2) {
        this.N = z2;
    }

    public void a(int i2) {
        this.O = i2;
    }

    public Interpolator f() {
        return this.A;
    }

    @Nullable
    public OnItemDragEventListener g() {
        return this.ao;
    }

    public void a(@Nullable OnItemDragEventListener onItemDragEventListener) {
        this.ao = onItemDragEventListener;
    }

    public void a(float f2) {
        this.ar = Math.min(Math.max(f2, 0.0f), 2.0f);
    }

    public float h() {
        return this.ar;
    }

    public void d(boolean z2) {
        this.P = z2;
    }

    public boolean i() {
        return this.P;
    }

    /* access modifiers changed from: package-private */
    public boolean a(RecyclerView recyclerView, MotionEvent motionEvent) {
        RecyclerView.ViewHolder a2;
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        Log.v(o, "onInterceptTouchEvent() action = " + actionMasked);
        if (this.az != null) {
            this.az.addMovement(motionEvent);
        }
        switch (actionMasked) {
            case 0:
                this.ax = (int) motionEvent.getX();
                this.ay = (int) motionEvent.getY();
                this.i = false;
                this.j = false;
                p();
                this.as = false;
                if (!c()) {
                    c(recyclerView, motionEvent);
                    break;
                }
                break;
            case 1:
            case 3:
                if ((actionMasked == 1 || actionMasked == 3) && this.au != null) {
                    this.au.a();
                    this.au = null;
                }
                if (!c() && actionMasked == 1 && !this.as && (a2 = CustomRecyclerViewUtils.a(recyclerView, motionEvent.getX(), motionEvent.getY())) != null) {
                    this.W.b(a2, a2.getAdapterPosition());
                }
                a(actionMasked, true);
                break;
            case 2:
                if (!c()) {
                    return a(motionEvent) || d(recyclerView, motionEvent);
                }
                e(recyclerView, motionEvent);
                return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(MotionEvent motionEvent) {
        if (this.h != null && !this.i && !this.j) {
            int abs = (int) Math.abs(motionEvent.getX() - ((float) this.ax));
            int abs2 = (int) Math.abs(motionEvent.getY() - ((float) this.ay));
            Log.d(o, "absDiffX:" + abs + " absDiffY:" + abs2);
            if (abs - abs2 > 5) {
                this.i = true;
                this.an.b();
                if (this.ax > 50) {
                    this.az.computeCurrentVelocity(1000, (float) this.aw);
                    this.h.a((int) this.az.getXVelocity());
                }
                return true;
            } else if (abs2 - abs > 5) {
                this.j = true;
            }
        }
        if (this.i) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void b(RecyclerView recyclerView, MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        Log.v(o, "onTouchEvent() action = " + actionMasked);
        if (this.az != null) {
            this.az.addMovement(motionEvent);
        }
        if ((actionMasked == 1 || actionMasked == 3) && this.au != null) {
            this.au.a();
            this.au = null;
        }
        if (c()) {
            switch (actionMasked) {
                case 1:
                case 3:
                    if (this.az != null) {
                        this.az.clear();
                    }
                    a(actionMasked, true);
                    return;
                case 2:
                    if (this.au != null) {
                        if (CustomRecyclerViewUtils.a(recyclerView, motionEvent.getX(), motionEvent.getY()) != this.au.f10109a) {
                            this.au.a();
                            this.au = null;
                        } else {
                            this.au.a(motionEvent);
                        }
                    }
                    e(recyclerView, motionEvent);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void e(boolean z2) {
        if (z2) {
            f(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(RecyclerView recyclerView, int i2, int i3) {
        Log.v(o, "onScrolled(dx = " + i2 + ", dy = " + i3 + Operators.BRACKET_END_STR);
        this.as = true;
        if (this.Q) {
            this.R = i2;
            this.S = i3;
        } else if (c()) {
            ViewCompat.postOnAnimationDelayed(this.z, this.aB, 500);
        }
        if (!c() && this.k != null) {
            this.k.a();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(RecyclerView recyclerView, int i2) {
        Log.v(o, "onScrollStateChanged(newState = " + i2 + Operators.BRACKET_END_STR);
        if (i2 == 1) {
            f(true);
        }
    }

    private boolean c(RecyclerView recyclerView, MotionEvent motionEvent) {
        RecyclerView.ViewHolder a2 = CustomRecyclerViewUtils.a(recyclerView, motionEvent.getX(), motionEvent.getY());
        boolean z2 = false;
        if (!a(recyclerView, a2)) {
            return false;
        }
        int e2 = CustomRecyclerViewUtils.e(this.z);
        int d = CustomRecyclerViewUtils.d(this.z);
        int x2 = (int) (motionEvent.getX() + 0.5f);
        this.aa = x2;
        this.J = x2;
        int y2 = (int) (motionEvent.getY() + 0.5f);
        this.ab = y2;
        this.K = y2;
        this.L = a2.getItemId();
        this.ap = e2 == 0 || (e2 == 1 && d > 1);
        if (e2 == 1 || (e2 == 0 && d > 1)) {
            z2 = true;
        }
        this.aq = z2;
        if (this.M) {
            this.an.a(motionEvent, this.O);
        }
        if (this.au != null) {
            this.au.a();
            this.au = null;
        }
        if (this.aA) {
            this.au = new TouchOnItemDecoration();
            this.au.a(this.z, motionEvent, a2);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b(MotionEvent motionEvent) {
        if (this.M) {
            a(this.z, motionEvent, false);
        }
    }

    private void a(RecyclerView recyclerView, MotionEvent motionEvent, RecyclerView.ViewHolder viewHolder, ItemDraggableRange itemDraggableRange) {
        b(recyclerView, viewHolder);
        this.an.b();
        this.X = new DraggingItemInfo(recyclerView, viewHolder, this.aa, this.ab);
        this.g = viewHolder;
        this.am = itemDraggableRange;
        this.al = ViewCompat.getOverScrollMode(recyclerView);
        ViewCompat.setOverScrollMode(recyclerView, 2);
        this.aa = (int) (motionEvent.getX() + 0.5f);
        this.ab = (int) (motionEvent.getY() + 0.5f);
        int i2 = this.ab;
        this.ah = i2;
        this.af = i2;
        this.ad = i2;
        int i3 = this.aa;
        this.ag = i3;
        this.ae = i3;
        this.ac = i3;
        this.ak = 0;
        this.z.getParent().requestDisallowInterceptTouchEvent(true);
        t();
        this.W.a(this.X, viewHolder, this.am);
        this.W.onBindViewHolder(viewHolder, viewHolder.getLayoutPosition());
        this.Y = new DraggingItemDecorator(this.z, viewHolder, this.am);
        this.Y.a(this.F);
        this.Y.a(motionEvent, this.X);
        int a2 = CustomRecyclerViewUtils.a(this.z);
        if (w() && !this.P && (a2 == 1 || a2 == 0)) {
            this.Z = new SwapTargetItemOperator(this.z, viewHolder, this.am, this.X);
            this.Z.b(this.A);
            this.Z.a();
            this.Z.a(this.Y.b(), this.Y.a());
        }
        if (this.E != null) {
            this.E.d();
        }
        if (this.ao != null) {
            this.ao.a(this.W.h());
            this.ao.b(0, 0);
        }
    }

    public void j() {
        f(false);
    }

    /* access modifiers changed from: package-private */
    public void f(boolean z2) {
        a(3, false);
        if (z2) {
            h(false);
        } else if (c()) {
            this.an.d();
        }
    }

    private void h(boolean z2) {
        int i2;
        if (c()) {
            if (this.an != null) {
                this.an.c();
            }
            if (!(this.z == null || this.g == null)) {
                ViewCompat.setOverScrollMode(this.z, this.al);
            }
            if (this.Y != null) {
                this.Y.a(this.U);
                this.Y.a(this.V);
                this.Y.a(true);
            }
            if (this.Z != null) {
                this.Z.a(this.U);
                this.Y.a(this.V);
                this.Z.a(true);
            }
            if (this.E != null) {
                this.E.c();
            }
            u();
            if (!(this.z == null || this.z.getParent() == null)) {
                this.z.getParent().requestDisallowInterceptTouchEvent(false);
            }
            if (this.z != null) {
                this.z.invalidate();
            }
            this.am = null;
            this.Y = null;
            this.Z = null;
            this.g = null;
            this.X = null;
            this.aa = 0;
            this.ab = 0;
            this.ac = 0;
            this.ad = 0;
            this.ae = 0;
            this.af = 0;
            this.ag = 0;
            this.ah = 0;
            this.ai = 0;
            this.aj = 0;
            this.ap = false;
            this.aq = false;
            int i3 = -1;
            if (this.W != null) {
                i3 = this.W.h();
                i2 = this.W.i();
                this.W.a(z2, this.m);
            } else {
                i2 = -1;
            }
            if (this.ao != null) {
                this.ao.a(i3, i2, z2);
            }
        }
    }

    private boolean a(int i2, boolean z2) {
        boolean z3 = i2 == 1;
        if (this.an != null) {
            this.an.b();
        }
        this.J = 0;
        this.K = 0;
        this.aa = 0;
        this.ab = 0;
        this.ac = 0;
        this.ad = 0;
        this.ae = 0;
        this.af = 0;
        this.ag = 0;
        this.ah = 0;
        this.ai = 0;
        this.aj = 0;
        this.L = -1;
        this.ap = false;
        this.aq = false;
        b(-1);
        if (z2 && c()) {
            h(z3);
        }
        return true;
    }

    private boolean d(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.N) {
            return a(recyclerView, motionEvent, true);
        }
        return false;
    }

    private boolean a(RecyclerView recyclerView, MotionEvent motionEvent, boolean z2) {
        RecyclerView.ViewHolder a2;
        int a3;
        if (this.X != null) {
            return false;
        }
        int x2 = (int) (motionEvent.getX() + 0.5f);
        int y2 = (int) (motionEvent.getY() + 0.5f);
        this.aa = x2;
        this.ab = y2;
        if (this.L == -1) {
            return false;
        }
        if ((z2 && ((!this.ap || Math.abs(x2 - this.J) <= this.H) && (!this.aq || Math.abs(y2 - this.K) <= this.H))) || (a2 = CustomRecyclerViewUtils.a(recyclerView, (float) this.J, (float) this.K)) == null || (a3 = CustomRecyclerViewUtils.a(a2)) == -1) {
            return false;
        }
        View view = a2.itemView;
        int translationY = (int) (ViewCompat.getTranslationY(view) + 0.5f);
        int left = view.getLeft();
        if (!this.W.a(a2, a3, x2 - (left + ((int) (ViewCompat.getTranslationX(view) + 0.5f))), y2 - (view.getTop() + translationY))) {
            return false;
        }
        ItemDraggableRange a4 = this.W.a(a2, a3);
        if (a4 == null) {
            a4 = new ItemDraggableRange(0, Math.max(0, this.W.getItemCount() - 1));
        }
        a(a4, a2);
        if (this.au != null) {
            this.au.a();
            this.au = null;
        }
        a(recyclerView, motionEvent, a2, a4);
        return true;
    }

    private void a(ItemDraggableRange itemDraggableRange, RecyclerView.ViewHolder viewHolder) {
        int max = Math.max(0, this.W.getItemCount() - 1);
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
        this.aa = (int) (motionEvent.getX() + 0.5f);
        this.ab = (int) (motionEvent.getY() + 0.5f);
        this.ae = Math.min(this.ae, this.aa);
        this.af = Math.min(this.af, this.ab);
        this.ag = Math.max(this.ag, this.aa);
        this.ah = Math.max(this.ah, this.ab);
        r();
        if (this.Y.a(motionEvent, false)) {
            if (this.Z != null) {
                this.Z.a(this.Y.b(), this.Y.a());
            }
            b(recyclerView);
            s();
        }
    }

    private void r() {
        if (CustomRecyclerViewUtils.e(this.z) == 1) {
            if (this.ad - this.af > this.I || this.ah - this.ab > this.I) {
                this.ak |= 1;
            }
            if (this.ah - this.ad > this.I || this.ab - this.af > this.I) {
                this.ak |= 2;
            }
        } else if (CustomRecyclerViewUtils.e(this.z) == 0) {
            if (this.ac - this.ae > this.I || this.ag - this.aa > this.I) {
                this.ak |= 4;
            }
            if (this.ag - this.ac > this.I || this.aa - this.ae > this.I) {
                this.ak |= 8;
            }
        }
    }

    public void g(boolean z2) {
        this.l = z2;
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3) {
        Log.d(o, "move:" + i2 + " to:" + i3);
        if (i3 != -1) {
            this.W.j(i2, i3);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(int i2, int i3) {
        Log.d(o, "merge:" + i2 + " to:" + i3);
        if (i3 != -1) {
            this.W.i(i2, i3);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(int i2) {
        View a2;
        View a3;
        int i3 = i2;
        if (i3 != this.n) {
            RecyclerView.LayoutManager layoutManager = this.z.getLayoutManager();
            if (this.n >= 0 && (a3 = CustomRecyclerViewUtils.a(layoutManager, this.n)) != null) {
                ScaleAnimation scaleAnimation = r7;
                ScaleAnimation scaleAnimation2 = new ScaleAnimation(1.2f, 1.0f, 1.2f, 1.0f, 1, 0.5f, 1, 0.5f);
                scaleAnimation.setDuration(200);
                scaleAnimation.setFillAfter(true);
                a3.startAnimation(scaleAnimation);
            }
            this.n = i3;
            if (this.n >= 0 && (a2 = CustomRecyclerViewUtils.a(layoutManager, this.n)) != null) {
                ScaleAnimation scaleAnimation3 = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, 1, 0.5f, 1, 0.5f);
                scaleAnimation3.setDuration(200);
                scaleAnimation3.setFillAfter(true);
                a2.startAnimation(scaleAnimation3);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(RecyclerView recyclerView) {
        if (!this.l) {
            c(recyclerView);
        } else if (this.az != null) {
            this.az.computeCurrentVelocity(300);
            Math.abs(this.az.getXVelocity());
            Math.abs(this.az.getYVelocity());
            RecyclerView.ViewHolder viewHolder = this.g;
            if (viewHolder != null && viewHolder.itemView != null) {
                int width = (this.aa - this.X.f) + (viewHolder.itemView.getWidth() / 2);
                int height = (this.ab - this.X.g) + (viewHolder.itemView.getHeight() / 2);
                this.W.h();
                int i2 = this.W.i();
                this.at.a();
                RecyclerView.ViewHolder b = CustomRecyclerViewUtils.b(recyclerView, (float) width, (float) height);
                if (!(b == null || this.am == null || this.am.a(b.getAdapterPosition()))) {
                    b = null;
                }
                if (b != null) {
                    if (b == viewHolder) {
                        this.at.f10108a = b;
                        this.at.c = true;
                        this.at.b = CustomRecyclerViewUtils.b(b);
                    } else {
                        this.at.f10108a = b;
                        this.at.b = CustomRecyclerViewUtils.b(b);
                    }
                    Rect rect = new Rect();
                    b.itemView.getHitRect(rect);
                    rect.inset(b.itemView.getWidth() / 4, b.itemView.getHeight() / 4);
                    Math.abs(i2 - this.at.b);
                    if (b == viewHolder || !rect.contains(width, height)) {
                        b(-1);
                        if (!this.m) {
                            this.W.a(this.at.b);
                        }
                        this.m = true;
                        int j2 = this.W.j();
                        if ((width > rect.right && this.at.b > j2) || (width < rect.left && this.at.b < j2)) {
                            a(j2, this.at.b);
                            return;
                        }
                        return;
                    }
                    this.m = false;
                    b(this.at.b);
                    b(i2, this.at.b);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void c(RecyclerView recyclerView) {
        boolean z2;
        this.m = true;
        RecyclerView.ViewHolder viewHolder = this.g;
        int i2 = this.aa - this.X.f;
        int i3 = this.ab - this.X.g;
        int h2 = this.W.h();
        int i4 = this.W.i();
        SwapTarget a2 = a(this.at, recyclerView, viewHolder, this.X, i2, i3, this.am, this.P, false);
        if (a2.b != -1) {
            z2 = true ^ this.P;
            if (!z2) {
                z2 = this.W.g(h2, a2.b);
            }
            if (!z2) {
                a2 = a(this.at, recyclerView, viewHolder, this.X, i2, i3, this.am, this.P, true);
                if (a2.b != -1) {
                    z2 = this.W.g(h2, a2.b);
                }
            }
        } else {
            z2 = false;
        }
        if (z2) {
            a(recyclerView, i4, viewHolder, a2.f10108a);
        }
        if (this.Z != null) {
            this.Z.a(z2 ? a2.f10108a : null);
        }
    }

    private void s() {
        if (this.ao != null) {
            this.ao.b(this.ai + this.Y.d(), this.aj + this.Y.c());
        }
    }

    /* access modifiers changed from: package-private */
    public void k() {
        RecyclerView recyclerView = this.z;
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
            int r4 = r0.aa
            goto L_0x001b
        L_0x0019:
            int r4 = r0.ab
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
            int r8 = r0.ak
            com.xiaomi.dragdrop.DraggingItemDecorator r9 = r0.Y
            float r4 = java.lang.Math.signum(r4)
            int r4 = (int) r4
            r10 = 1103626240(0x41c80000, float:25.0)
            float r11 = r0.ar
            float r11 = r11 * r10
            float r10 = r0.G
            float r11 = r11 * r10
            float r11 = r11 * r7
            float r11 = r11 + r5
            int r7 = (int) r11
            int r4 = r4 * r7
            com.xiaomi.dragdrop.ItemDraggableRange r7 = r0.am
            android.support.v7.widget.RecyclerView r10 = r0.z
            int r10 = com.xiaomi.dragdrop.CustomRecyclerViewUtils.b((android.support.v7.widget.RecyclerView) r10)
            android.support.v7.widget.RecyclerView r11 = r0.z
            int r11 = com.xiaomi.dragdrop.CustomRecyclerViewUtils.c((android.support.v7.widget.RecyclerView) r11)
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
            r18.f((android.support.v7.widget.RecyclerView) r19)
            if (r20 == 0) goto L_0x00c1
            int r6 = r0.e((int) r4)
            goto L_0x00c5
        L_0x00c1:
            int r6 = r0.d((int) r4)
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
            com.xiaomi.dragdrop.SwapTargetItemOperator r7 = r0.Z
            if (r7 == 0) goto L_0x00eb
            com.xiaomi.dragdrop.SwapTargetItemOperator r7 = r0.Z
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
            com.xiaomi.dragdrop.BaseEdgeEffectDecorator r7 = r0.E
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
            float r2 = r0.G
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
            float r2 = r0.G
            float r2 = r2 * r4
            goto L_0x015f
        L_0x015d:
            r3 = 0
        L_0x015e:
            r2 = 0
        L_0x015f:
            r0.b((float) r2)
        L_0x0162:
            android.support.v7.widget.RecyclerView r2 = r0.z
            java.lang.Runnable r3 = r0.aB
            android.support.v4.view.ViewCompat.postOnAnimation(r2, r3)
            if (r6 == 0) goto L_0x017b
            if (r20 == 0) goto L_0x0173
            int r1 = r0.ai
            int r1 = r1 + r6
            r0.ai = r1
            goto L_0x0178
        L_0x0173:
            int r1 = r0.aj
            int r1 = r1 + r6
            r0.aj = r1
        L_0x0178:
            r18.s()
        L_0x017b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.dragdrop.RecyclerViewDragDropManager.a(android.support.v7.widget.RecyclerView, boolean):void");
    }

    private void b(float f2) {
        if (f2 == 0.0f) {
            this.E.c();
        } else if (f2 < 0.0f) {
            this.E.a(f2);
        } else {
            this.E.b(f2);
        }
    }

    private int d(int i2) {
        this.S = 0;
        this.Q = true;
        this.z.scrollBy(0, i2);
        this.Q = false;
        return this.S;
    }

    private int e(int i2) {
        this.R = 0;
        this.Q = true;
        this.z.scrollBy(i2, 0);
        this.Q = false;
        return this.R;
    }

    /* access modifiers changed from: package-private */
    public RecyclerView l() {
        return this.z;
    }

    private void t() {
        this.B.a();
    }

    private void u() {
        if (this.B != null) {
            this.B.b();
        }
    }

    private void a(RecyclerView recyclerView, int i2, @Nullable RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder2) {
        Rect a2 = CustomRecyclerViewUtils.a(viewHolder2.itemView, this.T);
        int adapterPosition = viewHolder2.getAdapterPosition();
        int abs = Math.abs(i2 - adapterPosition);
        if (i2 != -1 && adapterPosition != -1) {
            if (recyclerView.getAdapter().getItemId(i2) != this.X.c) {
                Log.v(o, "RecyclerView state has not been synchronized to data yet");
                return;
            }
            boolean z2 = false;
            boolean z3 = CustomRecyclerViewUtils.b(CustomRecyclerViewUtils.a(recyclerView)) && (!w() || !this.P);
            if (abs != 0) {
                if (abs == 1 && viewHolder != null && z3) {
                    View view = viewHolder.itemView;
                    View view2 = viewHolder2.itemView;
                    Rect rect = this.X.h;
                    if (this.ap) {
                        int min = Math.min(view.getLeft() - rect.left, view2.getLeft() - a2.left);
                        float max = ((float) min) + (((float) (Math.max(view.getRight() + rect.right, view2.getRight() + a2.right) - min)) * 0.5f);
                        float f2 = ((float) (this.aa - this.X.f)) + (((float) this.X.f10101a) * 0.5f);
                        if (adapterPosition >= i2 ? f2 > max : f2 < max) {
                            z2 = true;
                        }
                    }
                    if (!z2 && this.aq) {
                        int min2 = Math.min(view.getTop() - rect.top, view2.getTop() - a2.top);
                        float max2 = ((float) min2) + (((float) (Math.max(view.getBottom() + rect.bottom, view2.getBottom() + a2.bottom) - min2)) * 0.5f);
                        float f3 = ((float) (this.ab - this.X.g)) + (((float) this.X.b) * 0.5f);
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
        if (this.ao != null) {
            this.ao.a(i2, i3);
        }
        RecyclerView.LayoutManager layoutManager = this.z.getLayoutManager();
        boolean z2 = true;
        if (CustomRecyclerViewUtils.a(CustomRecyclerViewUtils.a(this.z)) != 1) {
            z2 = false;
        }
        int a2 = CustomRecyclerViewUtils.a(this.z, false);
        View a3 = CustomRecyclerViewUtils.a(layoutManager, i2);
        View a4 = CustomRecyclerViewUtils.a(layoutManager, i3);
        View a5 = CustomRecyclerViewUtils.a(layoutManager, a2);
        Integer a6 = a(a3, z2);
        Integer a7 = a(a4, z2);
        Integer a8 = a(a5, z2);
        this.W.j(i2, i3);
        if (a2 == i2 && a8 != null && a7 != null) {
            recyclerView.scrollBy(0, -(a7.intValue() - a8.intValue()));
            e(recyclerView);
        } else if (a2 == i3 && a3 != null && a6 != null && !a6.equals(a7)) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) a3.getLayoutParams();
            recyclerView.scrollBy(0, -(layoutManager.getDecoratedMeasuredHeight(a3) + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin));
            e(recyclerView);
        }
    }

    private static Integer a(View view, boolean z2) {
        if (view == null) {
            return null;
        }
        return Integer.valueOf(z2 ? view.getTop() : view.getLeft());
    }

    private static DraggableItemWrapperAdapter d(RecyclerView recyclerView) {
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

    private static boolean v() {
        return Build.VERSION.SDK_INT >= 14;
    }

    private static boolean w() {
        return Build.VERSION.SDK_INT >= 11;
    }

    private static void b(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ItemAnimator itemAnimator = recyclerView != null ? recyclerView.getItemAnimator() : null;
        if (itemAnimator != null) {
            itemAnimator.endAnimation(viewHolder);
        }
    }

    private static void e(RecyclerView recyclerView) {
        RecyclerView.ItemAnimator itemAnimator = recyclerView != null ? recyclerView.getItemAnimator() : null;
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
    }

    private void f(RecyclerView recyclerView) {
        if (this.Z != null) {
            e(recyclerView);
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
                i5 = Math.min(Math.max(i2, recyclerView.getPaddingLeft()), Math.max(0, (recyclerView.getWidth() - recyclerView.getPaddingRight()) - draggingItemInfo2.f10101a));
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
        swapTarget2.f10108a = viewHolder4;
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
    private static android.support.v7.widget.RecyclerView.ViewHolder b(android.support.v7.widget.RecyclerView r21, @android.support.annotation.Nullable android.support.v7.widget.RecyclerView.ViewHolder r22, com.xiaomi.dragdrop.DraggingItemInfo r23, int r24, int r25, boolean r26, boolean r27, boolean r28) {
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
            int r6 = com.xiaomi.dragdrop.CustomRecyclerViewUtils.d((android.support.v7.widget.RecyclerView) r21)
            int r7 = com.xiaomi.dragdrop.CustomRecyclerViewUtils.d((android.support.v7.widget.RecyclerView.ViewHolder) r22)
            r8 = 1065353216(0x3f800000, float:1.0)
            r9 = 0
            r10 = 1
            r11 = 2
            if (r26 == 0) goto L_0x0098
            int r12 = r24 + 1
            int r13 = r2.f10101a
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
            android.support.v7.widget.RecyclerView$ViewHolder r11 = com.xiaomi.dragdrop.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r5, (float) r6)
            float r12 = (float) r14
            android.support.v7.widget.RecyclerView$ViewHolder r13 = com.xiaomi.dragdrop.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r5, (float) r12)
            float r14 = (float) r15
            android.support.v7.widget.RecyclerView$ViewHolder r5 = com.xiaomi.dragdrop.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r5, (float) r14)
            float r3 = (float) r3
            android.support.v7.widget.RecyclerView$ViewHolder r6 = com.xiaomi.dragdrop.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r3, (float) r6)
            android.support.v7.widget.RecyclerView$ViewHolder r12 = com.xiaomi.dragdrop.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r3, (float) r12)
            android.support.v7.widget.RecyclerView$ViewHolder r0 = com.xiaomi.dragdrop.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r3, (float) r14)
            r3 = r25
            r4 = r1
            r1 = r0
            r0 = r6
            r6 = r5
            r5 = r13
            goto L_0x0116
        L_0x0098:
            int r5 = r24 + 1
            int r11 = r2.f10101a
            r12 = 2
            int r11 = r11 / r12
            int r11 = r24 + r11
            int r11 = r11 - r10
            int r13 = r2.f10101a
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
            android.support.v7.widget.RecyclerView$ViewHolder r12 = com.xiaomi.dragdrop.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r5, (float) r6)
            float r11 = (float) r11
            android.support.v7.widget.RecyclerView$ViewHolder r14 = com.xiaomi.dragdrop.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r11, (float) r6)
            float r13 = (float) r13
            android.support.v7.widget.RecyclerView$ViewHolder r6 = com.xiaomi.dragdrop.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r13, (float) r6)
            float r4 = (float) r4
            android.support.v7.widget.RecyclerView$ViewHolder r5 = com.xiaomi.dragdrop.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r5, (float) r4)
            android.support.v7.widget.RecyclerView$ViewHolder r11 = com.xiaomi.dragdrop.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r11, (float) r4)
            android.support.v7.widget.RecyclerView$ViewHolder r0 = com.xiaomi.dragdrop.CustomRecyclerViewUtils.a((android.support.v7.widget.RecyclerView) r0, (float) r13, (float) r4)
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
            int r0 = com.xiaomi.dragdrop.CustomRecyclerViewUtils.d((android.support.v7.widget.RecyclerView.ViewHolder) r5)
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
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.dragdrop.RecyclerViewDragDropManager.b(android.support.v7.widget.RecyclerView, android.support.v7.widget.RecyclerView$ViewHolder, com.xiaomi.dragdrop.DraggingItemInfo, int, int, boolean, boolean, boolean):android.support.v7.widget.RecyclerView$ViewHolder");
    }

    private static RecyclerView.ViewHolder a(RecyclerView recyclerView, @Nullable RecyclerView.ViewHolder viewHolder, DraggingItemInfo draggingItemInfo, int i2, int i3, boolean z2) {
        int i4;
        int i5;
        if (z2) {
            int i6 = draggingItemInfo.h.left;
            i5 = i2 + ((int) ((((float) ((draggingItemInfo.f10101a + (draggingItemInfo.h.right + i6)) / draggingItemInfo.i)) * 0.5f) - ((float) i6)));
            i4 = i3 + (draggingItemInfo.b / 2);
        } else {
            int i7 = draggingItemInfo.h.top;
            int i8 = draggingItemInfo.h.bottom;
            i5 = i2 + (draggingItemInfo.f10101a / 2);
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
        int i7 = i2 + (draggingItemInfo.f10101a / 2);
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
            i2 += draggingItemInfo.f10101a / 2;
            if (layoutPosition >= c) {
                i3 += draggingItemInfo.b;
            }
        } else {
            i3 += draggingItemInfo.b / 2;
            if (layoutPosition >= c) {
                i2 += draggingItemInfo.f10101a;
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
            float min = Math.min(((float) draggingItemInfo.f10101a) * 0.2f, f2);
            float min2 = Math.min(((float) draggingItemInfo.b) * 0.2f, f2);
            float f3 = ((float) i2) + (((float) draggingItemInfo.f10101a) * 0.5f);
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

    public void c(int i2) {
        this.U = i2;
    }

    public int m() {
        return this.U;
    }

    public void b(@Nullable Interpolator interpolator) {
        this.V = interpolator;
    }

    @Nullable
    public Interpolator n() {
        return this.V;
    }

    /* access modifiers changed from: package-private */
    public void o() {
        this.g = null;
        this.Y.m();
    }

    /* access modifiers changed from: package-private */
    public void a(RecyclerView.ViewHolder viewHolder) {
        this.g = viewHolder;
        this.Y.a(viewHolder);
    }

    private static class ScrollOnDraggingProcessRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<RecyclerViewDragDropManager> f10107a;
        private boolean b;

        public ScrollOnDraggingProcessRunnable(RecyclerViewDragDropManager recyclerViewDragDropManager) {
            this.f10107a = new WeakReference<>(recyclerViewDragDropManager);
        }

        public void a() {
            RecyclerViewDragDropManager recyclerViewDragDropManager;
            RecyclerView l;
            if (!this.b && (recyclerViewDragDropManager = (RecyclerViewDragDropManager) this.f10107a.get()) != null && (l = recyclerViewDragDropManager.l()) != null) {
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
            this.f10107a.clear();
            this.b = false;
        }

        public void run() {
            RecyclerViewDragDropManager recyclerViewDragDropManager = (RecyclerViewDragDropManager) this.f10107a.get();
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
        private static final int f10106a = 1;
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
                    this.c.b(this.d);
                    return;
                case 2:
                    this.c.f(true);
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
