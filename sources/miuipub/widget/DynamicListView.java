package miuipub.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import miuipub.v6.R;

public class DynamicListView extends ListView {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3069a = 255;
    private static final TypeEvaluator<Rect> b = new TypeEvaluator<Rect>() {

        /* renamed from: a  reason: collision with root package name */
        private Rect f3070a = new Rect();

        public int a(int i, int i2, float f) {
            return (int) (((float) i) + (f * ((float) (i2 - i))));
        }

        /* renamed from: a */
        public Rect evaluate(float f, Rect rect, Rect rect2) {
            this.f3070a.set(a(rect.left, rect2.left, f), a(rect.top, rect2.top, f), a(rect.right, rect2.right, f), a(rect.bottom, rect2.bottom, f));
            return this.f3070a;
        }
    };
    private static final int c = 5;
    private static final int d = 50;
    /* access modifiers changed from: private */
    public boolean A = false;
    /* access modifiers changed from: private */
    public int B = 0;
    /* access modifiers changed from: private */
    public Drawable C;
    private int D;
    /* access modifiers changed from: private */
    public AbsListView.OnScrollListener E;
    /* access modifiers changed from: private */
    public ObjectAnimator F;
    /* access modifiers changed from: private */
    public ObjectAnimator G;
    /* access modifiers changed from: private */
    public RearrangeListener H;
    private OnItemRemoveListener I;
    /* access modifiers changed from: private */
    public Map<Long, Integer> J = new ArrayMap();
    /* access modifiers changed from: private */
    public Paint K = new Paint();
    /* access modifiers changed from: private */
    public Bitmap L;
    private AnimatorListenerAdapter M = new AnimatorListenerAdapter() {
        private boolean b;

        public void onAnimationStart(Animator animator) {
            DynamicListView.this.setEnabled(false);
            this.b = false;
        }

        public void onAnimationEnd(Animator animator) {
            View viewForId = DynamicListView.this.getViewForId(DynamicListView.this.s);
            if (viewForId != null) {
                viewForId.setVisibility(0);
            }
            long unused = DynamicListView.this.r = -1;
            long unused2 = DynamicListView.this.s = -1;
            long unused3 = DynamicListView.this.t = -1;
            BitmapDrawable unused4 = DynamicListView.this.u = null;
            DynamicListView.this.setEnabled(true);
            DynamicListView.this.invalidate();
            ObjectAnimator unused5 = DynamicListView.this.G = null;
            if (!this.b && DynamicListView.this.H != null) {
                DynamicListView.this.H.b();
            }
        }

        public void onAnimationCancel(Animator animator) {
            super.onAnimationCancel(animator);
            ObjectAnimator unused = DynamicListView.this.G = null;
            this.b = true;
        }
    };
    private Runnable N = new Runnable() {
        public void run() {
            if (DynamicListView.this.m) {
                DynamicListView.this.smoothScrollBy(DynamicListView.this.n, 10);
                DynamicListView.this.removeCallbacks(this);
                DynamicListView.this.postDelayed(this, 5);
            }
        }
    };
    private AbsListView.OnScrollListener O = new AbsListView.OnScrollListener() {
        private int b = -1;
        private int c = -1;
        private int d;
        private int e;
        private int f;

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (DynamicListView.this.E != null) {
                DynamicListView.this.E.onScroll(absListView, i, i2, i3);
            }
            this.d = i;
            this.e = i2;
            this.b = this.b == -1 ? this.d : this.b;
            this.c = this.c == -1 ? this.e : this.c;
            a();
            b();
            this.b = this.d;
            this.c = this.e;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (DynamicListView.this.E != null) {
                DynamicListView.this.E.onScrollStateChanged(absListView, i);
            }
            this.f = i;
            int unused = DynamicListView.this.B = i;
            c();
        }

        private void c() {
            if (this.e > 0 && this.f == 0) {
                if (DynamicListView.this.l && DynamicListView.this.m) {
                    DynamicListView.this.e();
                } else if (DynamicListView.this.A) {
                    DynamicListView.this.c();
                }
            }
        }

        public void a() {
            if (this.d != this.b && DynamicListView.this.l && DynamicListView.this.s != -1) {
                DynamicListView.this.a(DynamicListView.this.s);
                DynamicListView.this.b();
            }
        }

        public void b() {
            if (this.d + this.e != this.b + this.c && DynamicListView.this.l && DynamicListView.this.s != -1) {
                DynamicListView.this.a(DynamicListView.this.s);
                DynamicListView.this.b();
            }
        }
    };
    private final int e = -1;
    private final int f = -1;
    private float g = 0.0f;
    /* access modifiers changed from: private */
    public long h = 200;
    private int i = -1;
    private int j = -1;
    /* access modifiers changed from: private */
    public int k = 0;
    /* access modifiers changed from: private */
    public boolean l = false;
    /* access modifiers changed from: private */
    public boolean m = false;
    /* access modifiers changed from: private */
    public int n = 0;
    private int o;
    private int p;
    private int q = 0;
    /* access modifiers changed from: private */
    public long r = -1;
    /* access modifiers changed from: private */
    public long s = -1;
    /* access modifiers changed from: private */
    public long t = -1;
    /* access modifiers changed from: private */
    public BitmapDrawable u;
    private Rect v;
    private Rect w;
    private Rect x = new Rect();
    private Rect y = new Rect();
    private int z = -1;

    public interface OnItemRemoveListener {
        void a(List<Long> list);
    }

    public interface RearrangeListener {
        void a();

        void a(int i, int i2);

        void b();
    }

    public DynamicListView(Context context) {
        super(context);
        a(context);
    }

    public DynamicListView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context);
    }

    public DynamicListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        super.setOnScrollListener(this.O);
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        this.p = (int) (displayMetrics.density * 5.0f);
        this.o = (int) (((float) this.p) * 1.5f);
        this.q = (int) (displayMetrics.density * 50.0f);
        this.C = resources.getDrawable(R.drawable.v6_dynamic_listview_dragging_item_shadow);
        this.D = this.C.getIntrinsicHeight();
    }

    public void setDuration(long j2) {
        this.h = j2;
    }

    public void setScaleFactor(float f2) {
        this.g = f2;
    }

    public void startDragging(int i2) {
        if (this.G != null && this.G.isRunning()) {
            this.G.end();
            this.M.onAnimationEnd(this.G);
        }
        this.k = 0;
        View a2 = a(i2);
        this.s = getAdapter().getItemId(i2);
        this.u = a(a2);
        a();
        a2.setVisibility(4);
        this.l = true;
        a(this.s);
        if (this.H != null) {
            this.H.a();
        }
    }

    private void a() {
        Rect rect = new Rect(this.w);
        int height = (int) (((float) rect.height()) * this.g * 0.5f);
        rect.set(rect.left, rect.top - height, rect.right + (((int) (((float) rect.width()) * this.g * 0.5f)) * 2), rect.bottom + height);
        this.v.set(rect);
        this.w.set(rect);
        this.F = ObjectAnimator.ofObject(this, "HoverCellBounds", b, new Object[]{rect});
        this.F.setDuration(this.h);
        this.F.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                DynamicListView.this.C.setAlpha((int) (valueAnimator.getAnimatedFraction() * 255.0f));
            }
        });
        this.F.addListener(new AnimatorListenerAdapter() {
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                ObjectAnimator unused = DynamicListView.this.F = null;
            }

            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                ObjectAnimator unused = DynamicListView.this.F = null;
            }
        });
        this.F.start();
    }

    public Rect getHoverCellBounds() {
        return this.u.getBounds();
    }

    public void setHoverCellBounds(Rect rect) {
        this.y.set(this.x);
        this.u.setBounds(rect);
        this.x.set(rect.left, rect.top - (this.D / 2), rect.right, rect.bottom + (this.D / 2));
        this.C.setBounds(this.x);
        this.y.union(this.x);
        invalidate(this.y);
    }

    private BitmapDrawable a(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        int top = view.getTop();
        int left = view.getLeft();
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), b(view));
        this.w = new Rect(left, top, width + left, height + top);
        this.v = new Rect(this.w);
        bitmapDrawable.setBounds(this.v);
        return bitmapDrawable;
    }

    private Bitmap b(View view) {
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    /* access modifiers changed from: private */
    public void a(long j2) {
        int positionForId = getPositionForId(j2);
        BaseAdapter baseAdapter = (BaseAdapter) getAdapter();
        this.r = baseAdapter.getItemId(positionForId - 1);
        this.t = baseAdapter.getItemId(positionForId + 1);
    }

    public View getViewForId(long j2) {
        int firstVisiblePosition = getFirstVisiblePosition();
        BaseAdapter baseAdapter = (BaseAdapter) getAdapter();
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (baseAdapter.getItemId(firstVisiblePosition + i2) == j2) {
                return childAt;
            }
        }
        return null;
    }

    public int getPositionForId(long j2) {
        View viewForId = getViewForId(j2);
        if (viewForId == null) {
            return -1;
        }
        return getPositionForView(viewForId);
    }

    private View a(int i2) {
        int firstVisiblePosition = getFirstVisiblePosition();
        if (i2 < firstVisiblePosition || i2 > getLastVisiblePosition()) {
            return null;
        }
        return getChildAt(i2 - firstVisiblePosition);
    }

    private List<View> a(List<Integer> list) {
        ArrayList arrayList = new ArrayList(1);
        for (Integer intValue : list) {
            View a2 = a(intValue.intValue());
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.u != null) {
            this.C.draw(canvas);
            this.u.draw(canvas);
        }
        if (this.L != null) {
            canvas.drawBitmap(this.L, 0.0f, 0.0f, this.K);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action != 6) {
            switch (action) {
                case 0:
                    this.j = (int) motionEvent.getY();
                    this.z = motionEvent.getPointerId(0);
                    break;
                case 1:
                    c();
                    break;
                case 2:
                    if (this.z != -1) {
                        this.i = (int) motionEvent.getY(motionEvent.findPointerIndex(this.z));
                        int i2 = this.i - this.j;
                        if (this.l) {
                            if (this.F != null && this.F.isRunning()) {
                                this.F.end();
                            }
                            int i3 = this.w.top + i2 + this.k;
                            if (i3 < 0) {
                                i3 = 0;
                            } else if (i3 > getHeight() - this.v.height()) {
                                i3 = getHeight() - this.v.height();
                            }
                            this.v.offsetTo(this.w.left, i3);
                            setHoverCellBounds(this.v);
                            b();
                            this.m = false;
                            e();
                            return false;
                        }
                    }
                    break;
                case 3:
                    d();
                    break;
            }
        } else if (motionEvent.getPointerId((motionEvent.getAction() & 65280) >> 8) == this.z) {
            c();
        }
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: private */
    public void b() {
        final int i2 = this.i - this.j;
        int i3 = this.w.top + this.k + i2;
        int height = this.v.height() / 2;
        View viewForId = getViewForId(this.t);
        View viewForId2 = getViewForId(this.s);
        View viewForId3 = getViewForId(this.r);
        boolean z2 = true;
        boolean z3 = viewForId != null && i3 + height > viewForId.getTop();
        if (viewForId3 == null || i3 - height >= viewForId3.getTop()) {
            z2 = false;
        }
        if (z3 || z2) {
            long j2 = z3 ? this.t : this.r;
            if (!z3) {
                viewForId = viewForId3;
            }
            int positionForView = getPositionForView(viewForId2);
            if (this.H != null) {
                this.H.a(positionForView, getPositionForView(viewForId));
            }
            ((BaseAdapter) getAdapter()).notifyDataSetChanged();
            this.j = this.i;
            final int top = viewForId.getTop();
            viewForId2.setVisibility(0);
            viewForId.setVisibility(4);
            a(this.s);
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            final ViewTreeObserver viewTreeObserver2 = viewTreeObserver;
            final long j3 = j2;
            viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    viewTreeObserver2.removeOnPreDrawListener(this);
                    View viewForId = DynamicListView.this.getViewForId(j3);
                    int unused = DynamicListView.this.k = DynamicListView.this.k + i2;
                    viewForId.setTranslationY((float) (top - viewForId.getTop()));
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(viewForId, View.TRANSLATION_Y, new float[]{0.0f});
                    ofFloat.setDuration(DynamicListView.this.h);
                    ofFloat.start();
                    return true;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        View viewForId = getViewForId(this.s);
        if (this.l || this.A) {
            this.l = false;
            this.A = false;
            this.m = false;
            this.z = -1;
            if (this.B != 0) {
                this.A = true;
                return;
            }
            this.v.offsetTo(this.w.left, viewForId.getTop());
            this.v.set(this.w.left, viewForId.getTop(), this.w.left + viewForId.getWidth(), viewForId.getTop() + viewForId.getHeight());
            this.G = ObjectAnimator.ofObject(this, "HoverCellBounds", b, new Object[]{this.v});
            this.G.setDuration(this.h);
            this.G.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    DynamicListView.this.C.setAlpha((int) ((1.0f - valueAnimator.getAnimatedFraction()) * 255.0f));
                    DynamicListView.this.invalidate();
                }
            });
            this.G.addListener(this.M);
            this.G.start();
            return;
        }
        d();
    }

    private void d() {
        View viewForId = getViewForId(this.s);
        if (this.l) {
            this.r = -1;
            this.s = -1;
            this.t = -1;
            viewForId.setVisibility(0);
            this.u = null;
            invalidate();
        }
        this.l = false;
        this.m = false;
        this.z = -1;
    }

    /* access modifiers changed from: private */
    public void e() {
        this.m = handleMobileCellScroll(this.v);
    }

    public boolean handleMobileCellScroll(Rect rect) {
        int computeVerticalScrollOffset = computeVerticalScrollOffset();
        int height = getHeight();
        int computeVerticalScrollExtent = computeVerticalScrollExtent();
        int computeVerticalScrollRange = computeVerticalScrollRange();
        int i2 = rect.top;
        int height2 = rect.height();
        if (i2 > this.q * 2 || computeVerticalScrollOffset <= 0) {
            int i3 = i2 + height2;
            if (i3 < height - (this.q * 2) || computeVerticalScrollOffset + computeVerticalScrollExtent >= computeVerticalScrollRange) {
                removeCallbacks(this.N);
                return false;
            }
            this.n = this.p;
            if (i3 >= height - this.q) {
                this.n = this.o;
            }
            postDelayed(this.N, 10);
            return true;
        }
        this.n = -this.p;
        if (i2 <= this.q) {
            this.n = -this.o;
        }
        postDelayed(this.N, 10);
        return true;
    }

    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.E = onScrollListener;
    }

    public void removeItems(List<Long> list) {
        removeItems(list, (List<Long>) null);
    }

    public void removeItems(List<Long> list, List<Long> list2) {
        int i2;
        char c2;
        boolean z2;
        int i3;
        List<Long> list3 = list;
        List<Long> list4 = list2;
        final ArrayList arrayList = new ArrayList(list3);
        final ArrayList arrayList2 = new ArrayList(list.size());
        ArrayList arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (true) {
            i2 = 1;
            c2 = 0;
            if (!it.hasNext()) {
                z2 = false;
                break;
            }
            Long l2 = (Long) it.next();
            int positionForId = getPositionForId(l2.longValue());
            if (positionForId == -1) {
                z2 = true;
                break;
            }
            arrayList2.add(Integer.valueOf(positionForId));
            if (list4 != null && list4.contains(l2)) {
                arrayList3.add(Integer.valueOf(positionForId));
            }
        }
        if (z2) {
            this.L = b(this);
            this.I.a(list3);
            ((BaseAdapter) getAdapter()).notifyDataSetChanged();
            ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "LastStateAlpha", new int[]{0});
            ofInt.setDuration(this.h);
            ofInt.start();
            ofInt.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    Bitmap unused = DynamicListView.this.L = null;
                    DynamicListView.this.K.setAlpha(255);
                }
            });
            return;
        }
        Collections.sort(arrayList2);
        List<View> a2 = a((List<Integer>) arrayList2);
        List<View> a3 = a((List<Integer>) arrayList3);
        if (!a2.isEmpty()) {
            setEnabled(false);
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList4 = new ArrayList(a2.size());
            ArrayList arrayList5 = new ArrayList(a2.size());
            int size = a2.size();
            int i4 = 0;
            while (i4 < size) {
                final View view = a2.get(i4);
                if (a3.contains(view)) {
                    i3 = -view.getWidth();
                } else {
                    i3 = view.getWidth();
                }
                Property property = View.TRANSLATION_X;
                float[] fArr = new float[i2];
                fArr[c2] = (float) i3;
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, property, fArr);
                ofFloat.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationStart(Animator animator) {
                        super.onAnimationStart(animator);
                        view.setLayerType(2, (Paint) null);
                    }

                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        view.setLayerType(0, (Paint) null);
                    }

                    public void onAnimationCancel(Animator animator) {
                        super.onAnimationCancel(animator);
                        view.setLayerType(0, (Paint) null);
                    }
                });
                arrayList4.add(ofFloat);
                ofFloat.setDuration(this.h);
                ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
                ofFloat2.setDuration((this.h / 3) * ((long) i4));
                arrayList5.add(ofFloat2);
                i4++;
                i2 = 1;
                c2 = 0;
            }
            animatorSet.playTogether(arrayList5);
            for (int i5 = 0; i5 < size; i5++) {
                animatorSet.play((Animator) arrayList4.get(i5)).after((Animator) arrayList5.get(i5));
            }
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    DynamicListView.this.a(arrayList, arrayList2);
                }
            });
            animatorSet.start();
        }
    }

    public int getLastStateAlpha() {
        return this.K.getAlpha();
    }

    public void setLastStateAlpha(int i2) {
        this.K.setAlpha(i2);
        invalidate();
    }

    /* access modifiers changed from: private */
    public void a(List<Long> list, final List<Integer> list2) {
        final BaseAdapter baseAdapter = (BaseAdapter) getAdapter();
        ArrayList arrayList = new ArrayList(1);
        for (Integer intValue : list2) {
            View a2 = a(intValue.intValue());
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        int firstVisiblePosition = getFirstVisiblePosition();
        this.J.clear();
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            childAt.setTranslationX(0.0f);
            if (!arrayList.contains(childAt)) {
                this.J.put(Long.valueOf(baseAdapter.getItemId(firstVisiblePosition + i2)), Integer.valueOf(childAt.getTop()));
            }
        }
        this.I.a(list);
        baseAdapter.notifyDataSetChanged();
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                DynamicListView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                int firstVisiblePosition = DynamicListView.this.getFirstVisiblePosition();
                int childCount = DynamicListView.this.getChildCount();
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < childCount; i++) {
                    final View childAt = DynamicListView.this.getChildAt(i);
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(childAt, View.TRANSLATION_Y, new float[]{0.0f});
                    ofFloat.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationStart(Animator animator) {
                            super.onAnimationStart(animator);
                            childAt.setLayerType(2, (Paint) null);
                        }

                        public void onAnimationEnd(Animator animator) {
                            super.onAnimationEnd(animator);
                            childAt.setLayerType(0, (Paint) null);
                        }

                        public void onAnimationCancel(Animator animator) {
                            super.onAnimationCancel(animator);
                            childAt.setLayerType(0, (Paint) null);
                        }
                    });
                    arrayList.add(ofFloat);
                    Integer num = (Integer) DynamicListView.this.J.get(Long.valueOf(baseAdapter.getItemId(firstVisiblePosition + i)));
                    int top = childAt.getTop();
                    if (num == null) {
                        childAt.setTranslationY((float) (Integer.valueOf(((childAt.getHeight() + DynamicListView.this.getDividerHeight()) * list2.size()) + top).intValue() - top));
                    } else if (num.intValue() != top) {
                        childAt.setTranslationY((float) (num.intValue() - top));
                    }
                }
                DynamicListView.this.J.clear();
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(arrayList);
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        DynamicListView.this.setEnabled(true);
                    }
                });
                animatorSet.setDuration(DynamicListView.this.h);
                animatorSet.start();
                return true;
            }
        });
    }

    public void setOnItemRemoveListener(OnItemRemoveListener onItemRemoveListener) {
        this.I = onItemRemoveListener;
    }

    public void setRearrangeListener(RearrangeListener rearrangeListener) {
        this.H = rearrangeListener;
    }
}
