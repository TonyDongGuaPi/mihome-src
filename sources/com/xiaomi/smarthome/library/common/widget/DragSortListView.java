package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.xiaomi.smarthome.R;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DragSortListView extends ListView {
    public static final int DRAGGING = 4;
    public static final int DRAG_NEG_X = 2;
    public static final int DRAG_NEG_Y = 8;
    public static final int DRAG_POS_X = 1;
    public static final int DRAG_POS_Y = 4;
    public static final int DROPPING = 2;
    public static final int IDLE = 0;
    public static final int REMOVING = 1;
    public static final int STOPPED = 3;
    private static final int W = 0;
    private static final int aa = 1;
    private static final int ab = 2;
    private static final int ak = 3;
    private View[] A = new View[1];
    private DragScroller B;
    private float C = 0.33333334f;
    private float D = 0.33333334f;
    private int E;
    private int F;
    /* access modifiers changed from: private */
    public float G;
    /* access modifiers changed from: private */
    public float H;
    /* access modifiers changed from: private */
    public float I;
    /* access modifiers changed from: private */
    public float J;
    /* access modifiers changed from: private */
    public float K = 0.5f;
    /* access modifiers changed from: private */
    public DragScrollProfile L = new DragScrollProfile() {
        public float a(float f, long j) {
            return DragSortListView.this.K * f;
        }
    };
    private int M;
    /* access modifiers changed from: private */
    public int N;
    private int O;
    /* access modifiers changed from: private */
    public int P;
    private int Q;
    private int R = 0;
    private boolean S = false;
    private boolean T = false;
    private FloatViewManager U = null;
    private MotionEvent V;

    /* renamed from: a  reason: collision with root package name */
    private View f18821a;
    private int ac = 0;
    private float ad = 0.25f;
    private float ae = 0.0f;
    private AdapterWrapper af;
    private boolean ag = false;
    private DragSortTracker ah;
    /* access modifiers changed from: private */
    public boolean ai = false;
    private boolean aj = false;
    private HeightCache al = new HeightCache(3);
    private RemoveAnimator am;
    private LiftAnimator an;
    private DropAnimator ao;
    /* access modifiers changed from: private */
    public boolean ap;
    /* access modifiers changed from: private */
    public float aq = 0.0f;
    private boolean ar = false;
    private boolean as = false;
    /* access modifiers changed from: private */
    public Point b = new Point();
    private Point c = new Point();
    /* access modifiers changed from: private */
    public int d;
    private boolean e = false;
    private DataSetObserver f;
    private float g = 1.0f;
    private float h = 1.0f;
    /* access modifiers changed from: private */
    public int i;
    /* access modifiers changed from: private */
    public int j;
    /* access modifiers changed from: private */
    public int k;
    private boolean l = false;
    /* access modifiers changed from: private */
    public int m;
    boolean mCancelWhenNotify = true;
    private int n;
    /* access modifiers changed from: private */
    public int o;
    private int p;
    private int q;
    private DragListener r;
    private DropListener s;
    private RemoveListener t;
    private boolean u = true;
    /* access modifiers changed from: private */
    public int v = 0;
    /* access modifiers changed from: private */
    public int w = 1;
    /* access modifiers changed from: private */
    public int x;
    /* access modifiers changed from: private */
    public int y;
    private int z = 0;

    public interface DragListener {
        void a(int i, int i2);
    }

    public interface DragScrollProfile {
        float a(float f, long j);
    }

    public interface DragSortListener extends DragListener, DropListener, RemoveListener {
    }

    public interface DropListener {
        void drop(int i, int i2);
    }

    public interface FloatViewManager {
        void a(View view);

        void a(View view, Point point, Point point2);

        View f(int i);
    }

    public interface RemoveListener {
        void a(int i);
    }

    private static int a(int i2, int i3, int i4, int i5) {
        int i6 = i5 - i4;
        int i7 = i2 + i3;
        return i7 < i4 ? i7 + i6 : i7 >= i5 ? i7 - i6 : i7;
    }

    public DragSortListView(Context context) {
        super(context);
        a((AttributeSet) null);
    }

    public DragSortListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
    }

    public DragSortListView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        int i2;
        AttributeSet attributeSet2 = attributeSet;
        int i3 = 150;
        if (attributeSet2 != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet2, R.styleable.DragSortListView, 0, 0);
            this.w = Math.max(1, obtainStyledAttributes.getDimensionPixelSize(1, 1));
            this.ag = obtainStyledAttributes.getBoolean(16, false);
            if (this.ag) {
                this.ah = new DragSortTracker();
            }
            this.g = obtainStyledAttributes.getFloat(8, this.g);
            this.h = this.g;
            this.u = obtainStyledAttributes.getBoolean(2, this.u);
            this.ad = Math.max(0.0f, Math.min(1.0f, 1.0f - obtainStyledAttributes.getFloat(14, 0.75f)));
            this.l = this.ad > 0.0f;
            setDragScrollStart(obtainStyledAttributes.getFloat(4, this.C));
            this.K = obtainStyledAttributes.getFloat(10, this.K);
            int i4 = obtainStyledAttributes.getInt(11, 150);
            i2 = obtainStyledAttributes.getInt(6, 150);
            if (obtainStyledAttributes.getBoolean(17, true)) {
                boolean z2 = obtainStyledAttributes.getBoolean(12, false);
                int i5 = obtainStyledAttributes.getInt(13, 1);
                boolean z3 = obtainStyledAttributes.getBoolean(15, true);
                int i6 = obtainStyledAttributes.getInt(5, 0);
                int resourceId = obtainStyledAttributes.getResourceId(3, 0);
                int resourceId2 = obtainStyledAttributes.getResourceId(7, 0);
                int resourceId3 = obtainStyledAttributes.getResourceId(0, 0);
                int color = obtainStyledAttributes.getColor(9, -16777216);
                DragSortController dragSortController = new DragSortController(this, resourceId, i6, i5, resourceId3, resourceId2);
                dragSortController.b(z2);
                dragSortController.a(z3);
                dragSortController.g(color);
                this.U = dragSortController;
                setOnTouchListener(dragSortController);
            }
            obtainStyledAttributes.recycle();
            i3 = i4;
        } else {
            i2 = 150;
        }
        this.B = new DragScroller();
        if (i3 > 0) {
            this.am = new RemoveAnimator(0.5f, i3);
        }
        if (i2 > 0) {
            this.ao = new DropAnimator(0.5f, i2);
        }
        this.V = MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        this.f = new DataSetObserver() {
            private void a() {
                if (DragSortListView.this.v == 4 && DragSortListView.this.mCancelWhenNotify) {
                    DragSortListView.this.cancelDrag();
                }
            }

            public void onChanged() {
                a();
            }

            public void onInvalidated() {
                a();
            }
        };
    }

    public void setFloatAlpha(float f2) {
        this.h = f2;
    }

    public float getFloatAlpha() {
        return this.h;
    }

    public void setMaxScrollSpeed(float f2) {
        this.K = f2;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (listAdapter != null) {
            this.af = new AdapterWrapper(listAdapter);
            listAdapter.registerDataSetObserver(this.f);
            if (listAdapter instanceof DropListener) {
                setDropListener((DropListener) listAdapter);
            }
            if (listAdapter instanceof DragListener) {
                setDragListener((DragListener) listAdapter);
            }
            if (listAdapter instanceof RemoveListener) {
                setRemoveListener((RemoveListener) listAdapter);
            }
        } else {
            this.af = null;
        }
        super.setAdapter(this.af);
    }

    public ListAdapter getInputAdapter() {
        if (this.af == null) {
            return null;
        }
        return this.af.a();
    }

    private class AdapterWrapper extends BaseAdapter {
        private ListAdapter b;

        public AdapterWrapper(ListAdapter listAdapter) {
            this.b = listAdapter;
            this.b.registerDataSetObserver(new DataSetObserver(DragSortListView.this) {
                public void onChanged() {
                    AdapterWrapper.this.notifyDataSetChanged();
                }

                public void onInvalidated() {
                    AdapterWrapper.this.notifyDataSetInvalidated();
                }
            });
        }

        public ListAdapter a() {
            return this.b;
        }

        public long getItemId(int i) {
            return this.b.getItemId(i);
        }

        public Object getItem(int i) {
            return this.b.getItem(i);
        }

        public int getCount() {
            return this.b.getCount();
        }

        public boolean areAllItemsEnabled() {
            return this.b.areAllItemsEnabled();
        }

        public boolean isEnabled(int i) {
            return this.b.isEnabled(i);
        }

        public int getItemViewType(int i) {
            return this.b.getItemViewType(i);
        }

        public int getViewTypeCount() {
            return this.b.getViewTypeCount();
        }

        public boolean hasStableIds() {
            return this.b.hasStableIds();
        }

        public boolean isEmpty() {
            return this.b.isEmpty();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            DragSortItemView dragSortItemView;
            DragSortItemView dragSortItemView2;
            if (view != null) {
                dragSortItemView = (DragSortItemView) view;
                View childAt = dragSortItemView.getChildAt(0);
                View view2 = this.b.getView(i, childAt, DragSortListView.this);
                if (view2 != childAt) {
                    if (childAt != null) {
                        dragSortItemView.removeViewAt(0);
                    }
                    dragSortItemView.addView(view2);
                }
            } else {
                View view3 = this.b.getView(i, (View) null, DragSortListView.this);
                if (view3 instanceof Checkable) {
                    dragSortItemView2 = new DragSortItemViewCheckable(DragSortListView.this.getContext());
                } else {
                    dragSortItemView2 = new DragSortItemView(DragSortListView.this.getContext());
                }
                dragSortItemView2.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
                dragSortItemView2.addView(view3);
                dragSortItemView = dragSortItemView2;
            }
            DragSortListView.this.a(i + DragSortListView.this.getHeaderViewsCount(), (View) dragSortItemView, true);
            return dragSortItemView;
        }
    }

    private void a(int i2, Canvas canvas) {
        ViewGroup viewGroup;
        int i3;
        int i4;
        Drawable divider = getDivider();
        int dividerHeight = getDividerHeight();
        if (divider != null && dividerHeight != 0 && (viewGroup = (ViewGroup) getChildAt(i2 - getFirstVisiblePosition())) != null) {
            int paddingLeft = getPaddingLeft();
            int width = getWidth() - getPaddingRight();
            int height = viewGroup.getChildAt(0).getHeight();
            if (i2 > this.m) {
                i3 = viewGroup.getTop() + height;
                i4 = dividerHeight + i3;
            } else {
                int bottom = viewGroup.getBottom() - height;
                int i5 = bottom - dividerHeight;
                i4 = bottom;
                i3 = i5;
            }
            canvas.save();
            canvas.clipRect(paddingLeft, i3, width, i4);
            divider.setBounds(paddingLeft, i3, width, i4);
            divider.draw(canvas);
            canvas.restore();
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        float f2;
        super.dispatchDraw(canvas);
        if (this.v != 0) {
            if (this.j != this.m) {
                a(this.j, canvas);
            }
            if (!(this.k == this.j || this.k == this.m)) {
                a(this.k, canvas);
            }
        }
        if (this.f18821a != null) {
            int width = this.f18821a.getWidth();
            int height = this.f18821a.getHeight();
            int i2 = this.b.x;
            int width2 = getWidth();
            if (i2 < 0) {
                i2 = -i2;
            }
            if (i2 < width2) {
                float f3 = ((float) (width2 - i2)) / ((float) width2);
                f2 = f3 * f3;
            } else {
                f2 = 0.0f;
            }
            canvas.save();
            canvas.translate((float) this.b.x, (float) this.b.y);
            canvas.clipRect(0, 0, width, height);
            Canvas canvas2 = canvas;
            canvas2.saveLayerAlpha(0.0f, 0.0f, (float) width, (float) height, (int) (this.h * 255.0f * f2), 31);
            this.f18821a.draw(canvas);
            canvas.restore();
            canvas.restore();
        }
    }

    /* access modifiers changed from: private */
    public int a(int i2) {
        View childAt = getChildAt(i2 - getFirstVisiblePosition());
        if (childAt != null) {
            return childAt.getHeight();
        }
        return c(i2, d(i2));
    }

    private void a() {
        Log.d("mobeta", "mSrcPos=" + this.m + " mFirstExpPos=" + this.j + " mSecondExpPos=" + this.k);
    }

    private class HeightCache {
        private SparseIntArray b;
        private ArrayList<Integer> c;
        private int d;

        public HeightCache(int i) {
            this.b = new SparseIntArray(i);
            this.c = new ArrayList<>(i);
            this.d = i;
        }

        public void a(int i, int i2) {
            int i3 = this.b.get(i, -1);
            if (i3 != i2) {
                if (i3 != -1) {
                    this.c.remove(Integer.valueOf(i));
                } else if (this.b.size() == this.d) {
                    this.b.delete(this.c.remove(0).intValue());
                }
                this.b.put(i, i2);
                this.c.add(Integer.valueOf(i));
            }
        }

        public int a(int i) {
            return this.b.get(i, -1);
        }

        public void a() {
            this.b.clear();
            this.c.clear();
        }
    }

    /* access modifiers changed from: private */
    public int a(int i2, int i3) {
        int headerViewsCount = getHeaderViewsCount();
        int footerViewsCount = getFooterViewsCount();
        if (i2 <= headerViewsCount || i2 >= getCount() - footerViewsCount) {
            return i3;
        }
        int dividerHeight = getDividerHeight();
        int i4 = this.x - this.w;
        int d2 = d(i2);
        int a2 = a(i2);
        if (this.k <= this.m) {
            if (i2 == this.k && this.j != this.k) {
                i3 = i2 == this.m ? (i3 + a2) - this.x : (i3 + (a2 - d2)) - i4;
            } else if (i2 > this.k && i2 <= this.m) {
                i3 -= i4;
            }
        } else if (i2 > this.m && i2 <= this.j) {
            i3 += i4;
        } else if (i2 == this.k && this.j != this.k) {
            i3 += a2 - d2;
        }
        if (i2 <= this.m) {
            return i3 + (((this.x - dividerHeight) - d(i2 - 1)) / 2);
        }
        return i3 + (((d2 - dividerHeight) - this.x) / 2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0114  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean b() {
        /*
            r14 = this;
            int r0 = r14.getFirstVisiblePosition()
            int r1 = r14.j
            int r2 = r1 - r0
            android.view.View r2 = r14.getChildAt(r2)
            if (r2 != 0) goto L_0x001b
            int r1 = r14.getChildCount()
            int r1 = r1 / 2
            int r1 = r1 + r0
            int r0 = r1 - r0
            android.view.View r2 = r14.getChildAt(r0)
        L_0x001b:
            int r0 = r2.getTop()
            int r2 = r2.getHeight()
            int r3 = r14.a((int) r1, (int) r0)
            int r4 = r14.getDividerHeight()
            int r5 = r14.d
            if (r5 >= r3) goto L_0x004c
        L_0x002f:
            if (r1 < 0) goto L_0x004a
            int r1 = r1 + -1
            int r2 = r14.a((int) r1)
            if (r1 != 0) goto L_0x003c
            int r0 = r0 - r4
            int r0 = r0 - r2
            goto L_0x006f
        L_0x003c:
            int r2 = r2 + r4
            int r0 = r0 - r2
            int r2 = r14.a((int) r1, (int) r0)
            int r5 = r14.d
            if (r5 < r2) goto L_0x0048
            r0 = r2
            goto L_0x006f
        L_0x0048:
            r3 = r2
            goto L_0x002f
        L_0x004a:
            r0 = r3
            goto L_0x006f
        L_0x004c:
            int r5 = r14.getCount()
        L_0x0050:
            if (r1 >= r5) goto L_0x004a
            int r6 = r5 + -1
            if (r1 != r6) goto L_0x0059
            int r0 = r0 + r4
            int r0 = r0 + r2
            goto L_0x006f
        L_0x0059:
            int r2 = r2 + r4
            int r0 = r0 + r2
            int r2 = r1 + 1
            int r6 = r14.a((int) r2)
            int r7 = r14.a((int) r2, (int) r0)
            int r8 = r14.d
            if (r8 >= r7) goto L_0x006b
            r0 = r7
            goto L_0x006f
        L_0x006b:
            r1 = r2
            r2 = r6
            r3 = r7
            goto L_0x0050
        L_0x006f:
            int r2 = r14.getHeaderViewsCount()
            int r4 = r14.getFooterViewsCount()
            r5 = 0
            int r6 = r14.j
            int r7 = r14.k
            float r8 = r14.ae
            boolean r9 = r14.l
            if (r9 == 0) goto L_0x00cd
            int r9 = r0 - r3
            int r9 = java.lang.Math.abs(r9)
            int r10 = r14.d
            if (r10 >= r0) goto L_0x008f
            r13 = r3
            r3 = r0
            r0 = r13
        L_0x008f:
            float r10 = r14.ad
            r11 = 1056964608(0x3f000000, float:0.5)
            float r10 = r10 * r11
            float r9 = (float) r9
            float r10 = r10 * r9
            int r9 = (int) r10
            float r10 = (float) r9
            int r0 = r0 + r9
            int r9 = r3 - r9
            int r12 = r14.d
            if (r12 >= r0) goto L_0x00b1
            int r3 = r1 + -1
            r14.j = r3
            r14.k = r1
            int r3 = r14.d
            int r0 = r0 - r3
            float r0 = (float) r0
            float r0 = r0 * r11
            float r0 = r0 / r10
            r14.ae = r0
            goto L_0x00d1
        L_0x00b1:
            int r0 = r14.d
            if (r0 >= r9) goto L_0x00ba
            r14.j = r1
            r14.k = r1
            goto L_0x00d1
        L_0x00ba:
            r14.j = r1
            int r0 = r1 + 1
            r14.k = r0
            r0 = 1065353216(0x3f800000, float:1.0)
            int r9 = r14.d
            int r3 = r3 - r9
            float r3 = (float) r3
            float r3 = r3 / r10
            float r3 = r3 + r0
            float r3 = r3 * r11
            r14.ae = r3
            goto L_0x00d1
        L_0x00cd:
            r14.j = r1
            r14.k = r1
        L_0x00d1:
            int r0 = r14.j
            r3 = 1
            if (r0 >= r2) goto L_0x00dc
            r14.j = r2
            r14.k = r2
            r1 = r2
            goto L_0x00f0
        L_0x00dc:
            int r0 = r14.k
            int r9 = r14.getCount()
            int r9 = r9 - r4
            if (r0 < r9) goto L_0x00f0
            int r0 = r14.getCount()
            int r0 = r0 - r4
            int r1 = r0 + -1
            r14.j = r1
            r14.k = r1
        L_0x00f0:
            int r0 = r14.j
            if (r0 != r6) goto L_0x00fe
            int r0 = r14.k
            if (r0 != r7) goto L_0x00fe
            float r0 = r14.ae
            int r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r0 == 0) goto L_0x00ff
        L_0x00fe:
            r5 = 1
        L_0x00ff:
            int r0 = r14.i
            if (r1 == r0) goto L_0x0114
            com.xiaomi.smarthome.library.common.widget.DragSortListView$DragListener r0 = r14.r
            if (r0 == 0) goto L_0x0111
            com.xiaomi.smarthome.library.common.widget.DragSortListView$DragListener r0 = r14.r
            int r4 = r14.i
            int r4 = r4 - r2
            int r2 = r1 - r2
            r0.a(r4, r2)
        L_0x0111:
            r14.i = r1
            goto L_0x0115
        L_0x0114:
            r3 = r5
        L_0x0115:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.widget.DragSortListView.b():boolean");
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.ag) {
            this.ah.b();
        }
    }

    private class SmoothAnimator implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private float f18832a;
        protected long b;
        private float d;
        private float e;
        private float f = (this.d / ((this.d - 1.0f) * 2.0f));
        private float g = (1.0f / (1.0f - this.d));
        private float h;
        private boolean i;

        public void a() {
        }

        public void a(float f2, float f3) {
        }

        public void b() {
        }

        public SmoothAnimator(float f2, int i2) {
            this.d = f2;
            this.f18832a = (float) i2;
            float f3 = 1.0f / ((this.d * 2.0f) * (1.0f - this.d));
            this.h = f3;
            this.e = f3;
        }

        public float a(float f2) {
            if (f2 < this.d) {
                return this.e * f2 * f2;
            }
            if (f2 < 1.0f - this.d) {
                return this.f + (this.g * f2);
            }
            float f3 = f2 - 1.0f;
            return 1.0f - ((this.h * f3) * f3);
        }

        public void c() {
            this.b = SystemClock.uptimeMillis();
            this.i = false;
            a();
            DragSortListView.this.post(this);
        }

        public void d() {
            this.i = true;
        }

        public void run() {
            if (!this.i) {
                float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.b)) / this.f18832a;
                if (uptimeMillis >= 1.0f) {
                    a(1.0f, 1.0f);
                    b();
                    return;
                }
                a(uptimeMillis, a(uptimeMillis));
                DragSortListView.this.post(this);
            }
        }
    }

    private class LiftAnimator extends SmoothAnimator {
        private float d;
        private float e;

        public LiftAnimator(float f, int i) {
            super(f, i);
        }

        public void a() {
            this.d = (float) DragSortListView.this.o;
            this.e = (float) DragSortListView.this.y;
        }

        public void a(float f, float f2) {
            if (DragSortListView.this.v != 4) {
                d();
                return;
            }
            int unused = DragSortListView.this.o = (int) ((this.e * f2) + ((1.0f - f2) * this.d));
            DragSortListView.this.b.y = DragSortListView.this.N - DragSortListView.this.o;
            DragSortListView.this.a(true);
        }
    }

    private class DropAnimator extends SmoothAnimator {
        private int d;
        private int e;
        private float f;
        private float g;

        public DropAnimator(float f2, int i) {
            super(f2, i);
        }

        public void a() {
            this.d = DragSortListView.this.i;
            this.e = DragSortListView.this.m;
            int unused = DragSortListView.this.v = 2;
            this.f = (float) (DragSortListView.this.b.y - e());
            this.g = (float) (DragSortListView.this.b.x - DragSortListView.this.getPaddingLeft());
        }

        private int e() {
            int firstVisiblePosition = DragSortListView.this.getFirstVisiblePosition();
            int access$1000 = (DragSortListView.this.w + DragSortListView.this.getDividerHeight()) / 2;
            View childAt = DragSortListView.this.getChildAt(this.d - firstVisiblePosition);
            if (childAt == null) {
                d();
                return -1;
            } else if (this.d == this.e) {
                return childAt.getTop();
            } else {
                if (this.d < this.e) {
                    return childAt.getTop() - access$1000;
                }
                return (childAt.getBottom() + access$1000) - DragSortListView.this.x;
            }
        }

        public void a(float f2, float f3) {
            int e2 = e();
            float paddingLeft = (float) (DragSortListView.this.b.x - DragSortListView.this.getPaddingLeft());
            float f4 = 1.0f - f3;
            if (f4 < Math.abs(((float) (DragSortListView.this.b.y - e2)) / this.f) || f4 < Math.abs(paddingLeft / this.g)) {
                DragSortListView.this.b.y = e2 + ((int) (this.f * f4));
                DragSortListView.this.b.x = DragSortListView.this.getPaddingLeft() + ((int) (this.g * f4));
                DragSortListView.this.a(true);
            }
        }

        public void b() {
            DragSortListView.this.d();
        }
    }

    private class RemoveAnimator extends SmoothAnimator {
        private float d;
        private float e;
        private float f;
        private int g = -1;
        private int h = -1;
        private int i;
        private int j;
        private int k;

        public RemoveAnimator(float f2, int i2) {
            super(f2, i2);
        }

        public void a() {
            int i2 = -1;
            this.g = -1;
            this.h = -1;
            this.i = DragSortListView.this.j;
            this.j = DragSortListView.this.k;
            this.k = DragSortListView.this.m;
            int unused = DragSortListView.this.v = 1;
            this.d = (float) DragSortListView.this.b.x;
            if (DragSortListView.this.ap) {
                float width = ((float) DragSortListView.this.getWidth()) * 2.0f;
                if (DragSortListView.this.aq == 0.0f) {
                    DragSortListView dragSortListView = DragSortListView.this;
                    if (this.d >= 0.0f) {
                        i2 = 1;
                    }
                    float unused2 = dragSortListView.aq = ((float) i2) * width;
                    return;
                }
                float f2 = width * 2.0f;
                if (DragSortListView.this.aq < 0.0f) {
                    float f3 = -f2;
                    if (DragSortListView.this.aq > f3) {
                        float unused3 = DragSortListView.this.aq = f3;
                        return;
                    }
                }
                if (DragSortListView.this.aq > 0.0f && DragSortListView.this.aq < f2) {
                    float unused4 = DragSortListView.this.aq = f2;
                    return;
                }
                return;
            }
            DragSortListView.this.m();
        }

        public void a(float f2, float f3) {
            View childAt;
            float f4 = 1.0f - f3;
            int firstVisiblePosition = DragSortListView.this.getFirstVisiblePosition();
            View childAt2 = DragSortListView.this.getChildAt(this.i - firstVisiblePosition);
            if (DragSortListView.this.ap) {
                float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.b)) / 1000.0f;
                if (uptimeMillis != 0.0f) {
                    float access$1600 = DragSortListView.this.aq * uptimeMillis;
                    int width = DragSortListView.this.getWidth();
                    DragSortListView dragSortListView = DragSortListView.this;
                    float access$16002 = DragSortListView.this.aq;
                    float f5 = ((float) (DragSortListView.this.aq > 0.0f ? 1 : -1)) * uptimeMillis;
                    float f6 = (float) width;
                    float unused = dragSortListView.aq = access$16002 + (f5 * f6);
                    this.d += access$1600;
                    DragSortListView.this.b.x = (int) this.d;
                    if (this.d < f6 && this.d > ((float) (-width))) {
                        this.b = SystemClock.uptimeMillis();
                        DragSortListView.this.a(true);
                        return;
                    }
                } else {
                    return;
                }
            }
            if (childAt2 != null) {
                if (this.g == -1) {
                    this.g = DragSortListView.this.b(this.i, childAt2, false);
                    this.e = (float) (childAt2.getHeight() - this.g);
                }
                int max = Math.max((int) (this.e * f4), 1);
                ViewGroup.LayoutParams layoutParams = childAt2.getLayoutParams();
                layoutParams.height = this.g + max;
                childAt2.setLayoutParams(layoutParams);
            }
            if (this.j != this.i && (childAt = DragSortListView.this.getChildAt(this.j - firstVisiblePosition)) != null) {
                if (this.h == -1) {
                    this.h = DragSortListView.this.b(this.j, childAt, false);
                    this.f = (float) (childAt.getHeight() - this.h);
                }
                int max2 = Math.max((int) (f4 * this.f), 1);
                ViewGroup.LayoutParams layoutParams2 = childAt.getLayoutParams();
                layoutParams2.height = this.h + max2;
                childAt.setLayoutParams(layoutParams2);
            }
        }

        public void b() {
            DragSortListView.this.e();
        }
    }

    public void removeItem(int i2) {
        this.ap = false;
        removeItem(i2, 0.0f);
    }

    public void removeItem(int i2, float f2) {
        if (this.v == 0 || this.v == 4) {
            if (this.v == 0) {
                this.m = getHeaderViewsCount() + i2;
                this.j = this.m;
                this.k = this.m;
                this.i = this.m;
                View childAt = getChildAt(this.m - getFirstVisiblePosition());
                if (childAt != null) {
                    childAt.setVisibility(4);
                }
            }
            this.v = 1;
            this.aq = f2;
            if (this.T) {
                switch (this.ac) {
                    case 1:
                        super.onTouchEvent(this.V);
                        break;
                    case 2:
                        super.onInterceptTouchEvent(this.V);
                        break;
                }
            }
            if (this.am != null) {
                this.am.c();
            } else {
                b(i2);
            }
        }
    }

    public void moveItem(int i2, int i3) {
        if (this.s != null) {
            int count = getInputAdapter().getCount();
            if (i2 >= 0 && i2 < count && i3 >= 0 && i3 < count) {
                this.s.drop(i2, i3);
            }
        }
    }

    public void cancelDrag() {
        if (this.v == 4) {
            this.B.a(true);
            m();
            c();
            i();
            if (this.T) {
                this.v = 3;
            } else {
                this.v = 0;
            }
        }
    }

    public int getDragState() {
        return this.v;
    }

    private void c() {
        this.m = -1;
        this.j = -1;
        this.k = -1;
        this.i = -1;
    }

    /* access modifiers changed from: private */
    public void d() {
        this.v = 2;
        if (this.s != null && this.i >= 0 && this.i < getCount()) {
            int headerViewsCount = getHeaderViewsCount();
            this.s.drop(this.m - headerViewsCount, this.i - headerViewsCount);
        }
        m();
        f();
        c();
        i();
        if (this.T) {
            this.v = 3;
        } else {
            this.v = 0;
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        b(this.m - getHeaderViewsCount());
    }

    private void b(int i2) {
        this.v = 1;
        if (this.t != null) {
            this.t.a(i2);
        }
        m();
        f();
        c();
        if (this.T) {
            this.v = 3;
        } else {
            this.v = 0;
        }
    }

    private void f() {
        int firstVisiblePosition = getFirstVisiblePosition();
        if (this.m < firstVisiblePosition) {
            int i2 = 0;
            View childAt = getChildAt(0);
            if (childAt != null) {
                i2 = childAt.getTop();
            }
            setSelectionFromTop(firstVisiblePosition - 1, i2 - getPaddingTop());
        }
    }

    public boolean stopDrag(boolean z2) {
        this.ap = false;
        return stopDrag(z2, 0.0f);
    }

    public boolean stopDragWithVelocity(boolean z2, float f2) {
        this.ap = true;
        return stopDrag(z2, f2);
    }

    public boolean stopDrag(boolean z2, float f2) {
        if (this.f18821a == null) {
            return false;
        }
        this.B.a(true);
        if (z2) {
            removeItem(this.m - getHeaderViewsCount(), f2);
        } else if (this.ao != null) {
            this.ao.c();
        } else {
            d();
        }
        if (this.ag) {
            this.ah.d();
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z2 = false;
        if (this.aj) {
            this.aj = false;
            return false;
        } else if (!this.u) {
            return super.onTouchEvent(motionEvent);
        } else {
            boolean z3 = this.S;
            this.S = false;
            if (!z3) {
                a(motionEvent);
            }
            if (this.v == 4) {
                onDragTouchEvent(motionEvent);
                return true;
            }
            if (this.v == 0 && super.onTouchEvent(motionEvent)) {
                z2 = true;
            }
            int action = motionEvent.getAction() & 255;
            if (action == 1 || action == 3) {
                g();
                return z2;
            } else if (!z2) {
                return z2;
            } else {
                this.ac = 1;
                return z2;
            }
        }
    }

    private void g() {
        this.ac = 0;
        this.T = false;
        if (this.v == 3) {
            this.v = 0;
        }
        this.h = this.g;
        this.ar = false;
        this.al.a();
    }

    private void a(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            this.O = this.M;
            this.P = this.N;
        }
        this.M = (int) motionEvent.getX();
        this.N = (int) motionEvent.getY();
        if (action == 0) {
            this.O = this.M;
            this.P = this.N;
        }
        this.p = ((int) motionEvent.getRawX()) - this.M;
        this.q = ((int) motionEvent.getRawY()) - this.N;
    }

    public boolean listViewIntercepted() {
        return this.ar;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z2;
        if (!this.u) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        a(motionEvent);
        this.S = true;
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            if (this.v != 0) {
                this.aj = true;
                return true;
            }
            this.T = true;
        }
        if (this.f18821a != null) {
            z2 = true;
        } else {
            if (super.onInterceptTouchEvent(motionEvent)) {
                this.ar = true;
                z2 = true;
            } else {
                z2 = false;
            }
            if (action == 1 || action == 3) {
                g();
            } else if (z2) {
                this.ac = 1;
            } else {
                this.ac = 2;
            }
        }
        if (action == 1 || action == 3) {
            this.T = false;
        }
        return z2;
    }

    public void setDragScrollStart(float f2) {
        setDragScrollStarts(f2, f2);
    }

    public void setDragScrollStarts(float f2, float f3) {
        if (f3 > 0.5f) {
            this.D = 0.5f;
        } else {
            this.D = f3;
        }
        if (f2 > 0.5f) {
            this.C = 0.5f;
        } else {
            this.C = f2;
        }
        if (getHeight() != 0) {
            h();
        }
    }

    private void b(int i2, int i3) {
        this.b.x = i2 - this.n;
        this.b.y = i3 - this.o;
        a(true);
        int min = Math.min(i3, this.d + this.y);
        int max = Math.max(i3, this.d - this.y);
        int b2 = this.B.b();
        if (min > this.P && min > this.F && b2 != 1) {
            if (b2 != -1) {
                this.B.a(true);
            }
            this.B.a(1);
        } else if (max < this.P && max < this.E && b2 != 0) {
            if (b2 != -1) {
                this.B.a(true);
            }
            this.B.a(0);
        } else if (max >= this.E && min <= this.F && this.B.a()) {
            this.B.a(true);
        }
    }

    private void h() {
        int paddingTop = getPaddingTop();
        int height = (getHeight() - paddingTop) - getPaddingBottom();
        float f2 = (float) height;
        float f3 = (float) paddingTop;
        this.H = (this.C * f2) + f3;
        this.G = ((1.0f - this.D) * f2) + f3;
        this.E = (int) this.H;
        this.F = (int) this.G;
        this.I = this.H - f3;
        this.J = ((float) (paddingTop + height)) - this.G;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        h();
    }

    private void i() {
        int firstVisiblePosition = getFirstVisiblePosition();
        int lastVisiblePosition = getLastVisiblePosition();
        int min = Math.min(lastVisiblePosition - firstVisiblePosition, ((getCount() - 1) - getFooterViewsCount()) - firstVisiblePosition);
        for (int max = Math.max(0, getHeaderViewsCount() - firstVisiblePosition); max <= min; max++) {
            View childAt = getChildAt(max);
            if (childAt != null) {
                a(firstVisiblePosition + max, childAt, false);
            }
        }
    }

    private void c(int i2) {
        View childAt = getChildAt(i2 - getFirstVisiblePosition());
        if (childAt != null) {
            a(i2, childAt, false);
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2, View view, boolean z2) {
        int i3;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (i2 == this.m || i2 == this.j || i2 == this.k) {
            i3 = c(i2, view, z2);
        } else {
            i3 = -2;
        }
        if (i3 != layoutParams.height) {
            layoutParams.height = i3;
            view.setLayoutParams(layoutParams);
        }
        if (i2 == this.j || i2 == this.k) {
            if (i2 < this.m) {
                ((DragSortItemView) view).setGravity(80);
            } else if (i2 > this.m) {
                ((DragSortItemView) view).setGravity(48);
            }
        }
        int visibility = view.getVisibility();
        int i4 = 0;
        if (i2 == this.m && this.f18821a != null) {
            i4 = 4;
        }
        if (i4 != visibility) {
            view.setVisibility(i4);
        }
    }

    /* access modifiers changed from: private */
    public int d(int i2) {
        View view;
        if (i2 == this.m) {
            return 0;
        }
        View childAt = getChildAt(i2 - getFirstVisiblePosition());
        if (childAt != null) {
            return b(i2, childAt, false);
        }
        int a2 = this.al.a(i2);
        if (a2 != -1) {
            return a2;
        }
        ListAdapter adapter = getAdapter();
        int itemViewType = adapter.getItemViewType(i2);
        int viewTypeCount = adapter.getViewTypeCount();
        if (viewTypeCount != this.A.length) {
            this.A = new View[viewTypeCount];
        }
        if (itemViewType < 0) {
            view = adapter.getView(i2, (View) null, this);
        } else if (this.A[itemViewType] == null) {
            view = adapter.getView(i2, (View) null, this);
            this.A[itemViewType] = view;
        } else {
            view = adapter.getView(i2, this.A[itemViewType], this);
        }
        int b2 = b(i2, view, true);
        this.al.a(i2, b2);
        return b2;
    }

    /* access modifiers changed from: private */
    public int b(int i2, View view, boolean z2) {
        if (i2 == this.m) {
            return 0;
        }
        if (i2 >= getHeaderViewsCount() && i2 < getCount() - getFooterViewsCount()) {
            view = ((ViewGroup) view).getChildAt(0);
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null && layoutParams.height > 0) {
            return layoutParams.height;
        }
        int height = view.getHeight();
        if (height != 0 && !z2) {
            return height;
        }
        a(view);
        return view.getMeasuredHeight();
    }

    private int c(int i2, View view, boolean z2) {
        return c(i2, b(i2, view, z2));
    }

    private int c(int i2, int i3) {
        getDividerHeight();
        boolean z2 = this.l && this.j != this.k;
        int i4 = this.x - this.w;
        int i5 = (int) (this.ae * ((float) i4));
        if (i2 != this.m) {
            return i2 == this.j ? z2 ? i3 + i5 : i3 + i4 : i2 == this.k ? (i3 + i4) - i5 : i3;
        }
        if (this.m == this.j) {
            if (z2) {
                return i5 + this.w;
            }
            return this.x;
        } else if (this.m == this.k) {
            return this.x - i5;
        } else {
            return this.w;
        }
    }

    public void requestLayout() {
        if (!this.ai) {
            super.requestLayout();
        }
    }

    private int a(int i2, View view, int i3, int i4) {
        int i5;
        int i6;
        int d2 = d(i2);
        int height = view.getHeight();
        int c2 = c(i2, d2);
        if (i2 != this.m) {
            i5 = height - d2;
            i6 = c2 - d2;
        } else {
            i5 = height;
            i6 = c2;
        }
        int i7 = this.x;
        if (!(this.m == this.j || this.m == this.k)) {
            i7 -= this.w;
        }
        if (i2 <= i3) {
            if (i2 > this.j) {
                return 0 + (i7 - i6);
            }
            return 0;
        } else if (i2 == i4) {
            if (i2 <= this.j) {
                return 0 + (i5 - i7);
            }
            return i2 == this.k ? 0 + (height - c2) : 0 + i5;
        } else if (i2 <= this.j) {
            return 0 - i7;
        } else {
            if (i2 == this.k) {
                return 0 - i6;
            }
            return 0;
        }
    }

    private void a(View view) {
        int i2;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new AbsListView.LayoutParams(-1, -2);
            view.setLayoutParams(layoutParams);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(this.z, getListPaddingLeft() + getListPaddingRight(), layoutParams.width);
        if (layoutParams.height > 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
        } else {
            i2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, i2);
    }

    private void j() {
        if (this.f18821a != null) {
            a(this.f18821a);
            this.x = this.f18821a.getMeasuredHeight();
            this.y = this.x / 2;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f18821a != null) {
            if (this.f18821a.isLayoutRequested()) {
                j();
            }
            this.e = true;
        }
        this.z = i2;
    }

    /* access modifiers changed from: protected */
    public void layoutChildren() {
        super.layoutChildren();
        if (this.f18821a != null) {
            if (this.f18821a.isLayoutRequested() && !this.e) {
                j();
            }
            this.f18821a.layout(0, 0, this.f18821a.getMeasuredWidth(), this.f18821a.getMeasuredHeight());
            this.e = false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean onDragTouchEvent(MotionEvent motionEvent) {
        motionEvent.getAction();
        switch (motionEvent.getAction() & 255) {
            case 1:
                if (this.v == 4) {
                    stopDrag(false);
                }
                g();
                return true;
            case 2:
                b((int) motionEvent.getX(), (int) motionEvent.getY());
                return true;
            case 3:
                if (this.v == 4) {
                    cancelDrag();
                }
                g();
                return true;
            default:
                return true;
        }
    }

    private void k() {
        this.as = true;
    }

    public boolean startDrag(int i2, int i3, int i4, int i5) {
        View f2;
        if (!this.T || this.U == null || (f2 = this.U.f(i2)) == null || !startDrag(i2, f2, i3, i4, i5)) {
            return false;
        }
        return true;
    }

    public boolean startDrag(int i2, View view, int i3, int i4, int i5) {
        if (this.v != 0 || !this.T || this.f18821a != null || view == null || !this.u) {
            return false;
        }
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        int headerViewsCount = i2 + getHeaderViewsCount();
        this.j = headerViewsCount;
        this.k = headerViewsCount;
        this.m = headerViewsCount;
        this.i = headerViewsCount;
        this.v = 4;
        this.R = 0;
        this.R = i3 | this.R;
        this.f18821a = view;
        j();
        this.n = i4;
        this.o = i5;
        this.Q = this.N;
        this.b.x = this.M - this.n;
        this.b.y = this.N - this.o;
        View childAt = getChildAt(this.m - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setVisibility(4);
        }
        if (this.ag) {
            this.ah.a();
        }
        switch (this.ac) {
            case 1:
                super.onTouchEvent(this.V);
                break;
            case 2:
                super.onInterceptTouchEvent(this.V);
                break;
        }
        requestLayout();
        if (this.an != null) {
            this.an.c();
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void a(boolean z2) {
        int firstVisiblePosition = getFirstVisiblePosition() + (getChildCount() / 2);
        View childAt = getChildAt(getChildCount() / 2);
        if (childAt != null) {
            d(firstVisiblePosition, childAt, z2);
        }
    }

    /* access modifiers changed from: private */
    public void d(int i2, View view, boolean z2) {
        this.ai = true;
        l();
        int i3 = this.j;
        int i4 = this.k;
        boolean b2 = b();
        if (b2) {
            i();
            setSelectionFromTop(i2, (view.getTop() + a(i2, view, i3, i4)) - getPaddingTop());
            layoutChildren();
        }
        if (b2 || z2) {
            invalidate();
        }
        this.ai = false;
    }

    private void l() {
        if (this.U != null) {
            this.c.set(this.M, this.N);
            this.U.a(this.f18821a, this.b, this.c);
        }
        int i2 = this.b.x;
        int i3 = this.b.y;
        int paddingLeft = getPaddingLeft();
        if ((this.R & 1) == 0 && i2 > paddingLeft) {
            this.b.x = paddingLeft;
        } else if ((this.R & 2) == 0 && i2 < paddingLeft) {
            this.b.x = paddingLeft;
        }
        int headerViewsCount = getHeaderViewsCount();
        int footerViewsCount = getFooterViewsCount();
        int firstVisiblePosition = getFirstVisiblePosition();
        int lastVisiblePosition = getLastVisiblePosition();
        int paddingTop = getPaddingTop();
        if (firstVisiblePosition < headerViewsCount) {
            paddingTop = getChildAt((headerViewsCount - firstVisiblePosition) - 1).getBottom();
        }
        if ((this.R & 8) == 0 && firstVisiblePosition <= this.m) {
            paddingTop = Math.max(getChildAt(this.m - firstVisiblePosition).getTop(), paddingTop);
        }
        int height = getHeight() - getPaddingBottom();
        if (lastVisiblePosition >= (getCount() - footerViewsCount) - 1) {
            height = getChildAt(((getCount() - footerViewsCount) - 1) - firstVisiblePosition).getBottom();
        }
        if ((this.R & 4) == 0 && lastVisiblePosition >= this.m) {
            height = Math.min(getChildAt(this.m - firstVisiblePosition).getBottom(), height);
        }
        if (i3 < paddingTop) {
            this.b.y = paddingTop;
        } else if (i3 + this.x > height) {
            this.b.y = height - this.x;
        }
        this.d = this.b.y + this.y;
    }

    /* access modifiers changed from: private */
    public void m() {
        if (this.f18821a != null) {
            this.f18821a.setVisibility(8);
            if (this.U != null) {
                this.U.a(this.f18821a);
            }
            this.f18821a = null;
            invalidate();
        }
    }

    public void setFloatViewManager(FloatViewManager floatViewManager) {
        this.U = floatViewManager;
    }

    public void setDragListener(DragListener dragListener) {
        this.r = dragListener;
    }

    public void setDragEnabled(boolean z2) {
        this.u = z2;
        long currentTimeMillis = System.currentTimeMillis();
        new SimpleDateFormat("MMM dd,yyyy HH:mm");
        new Date(currentTimeMillis);
    }

    public void setCancelWhenNotify(boolean z2) {
        this.mCancelWhenNotify = z2;
    }

    public boolean isDragEnabled() {
        return this.u;
    }

    public void setDropListener(DropListener dropListener) {
        this.s = dropListener;
    }

    public void setRemoveListener(RemoveListener removeListener) {
        this.t = removeListener;
    }

    public void setDragSortListener(DragSortListener dragSortListener) {
        setDropListener(dragSortListener);
        setDragListener(dragSortListener);
        setRemoveListener(dragSortListener);
    }

    public void setDragScrollProfile(DragScrollProfile dragScrollProfile) {
        if (dragScrollProfile != null) {
            this.L = dragScrollProfile;
        }
    }

    public void moveCheckState(int i2, int i3) {
        int i4;
        int i5;
        SparseBooleanArray checkedItemPositions = getCheckedItemPositions();
        if (i3 < i2) {
            i5 = i2;
            i4 = i3;
        } else {
            i4 = i2;
            i5 = i3;
        }
        int i6 = i5 + 1;
        int[] iArr = new int[checkedItemPositions.size()];
        int[] iArr2 = new int[checkedItemPositions.size()];
        int a2 = a(checkedItemPositions, i4, i6, iArr, iArr2);
        if (a2 != 1 || iArr[0] != iArr2[0]) {
            if (i2 < i3) {
                for (int i7 = 0; i7 != a2; i7++) {
                    setItemChecked(a(iArr[i7], -1, i4, i6), true);
                    setItemChecked(a(iArr2[i7], -1, i4, i6), false);
                }
                return;
            }
            for (int i8 = 0; i8 != a2; i8++) {
                setItemChecked(iArr[i8], false);
                setItemChecked(iArr2[i8], true);
            }
        }
    }

    public void removeCheckState(int i2) {
        SparseBooleanArray checkedItemPositions = getCheckedItemPositions();
        if (checkedItemPositions.size() != 0) {
            int[] iArr = new int[checkedItemPositions.size()];
            int[] iArr2 = new int[checkedItemPositions.size()];
            int keyAt = checkedItemPositions.keyAt(checkedItemPositions.size() - 1) + 1;
            int a2 = a(checkedItemPositions, i2, keyAt, iArr, iArr2);
            for (int i3 = 0; i3 != a2; i3++) {
                if (iArr[i3] != i2 && (iArr2[i3] >= iArr[i3] || iArr2[i3] <= i2)) {
                    setItemChecked(a(iArr[i3], -1, i2, keyAt), true);
                }
                setItemChecked(a(iArr2[i3], -1, i2, keyAt), false);
            }
        }
    }

    private static int a(SparseBooleanArray sparseBooleanArray, int i2, int i3, int[] iArr, int[] iArr2) {
        int keyAt;
        int a2 = a(sparseBooleanArray, i2, i3);
        if (a2 == -1) {
            return 0;
        }
        int keyAt2 = sparseBooleanArray.keyAt(a2);
        int i4 = keyAt2 + 1;
        int i5 = a2 + 1;
        int i6 = keyAt2;
        int i7 = 0;
        while (i5 < sparseBooleanArray.size() && (keyAt = sparseBooleanArray.keyAt(i5)) < i3) {
            if (sparseBooleanArray.valueAt(i5)) {
                if (keyAt == i4) {
                    i4++;
                } else {
                    iArr[i7] = i6;
                    iArr2[i7] = i4;
                    i7++;
                    i4 = keyAt + 1;
                    i6 = keyAt;
                }
            }
            i5++;
        }
        if (i4 == i3) {
            i4 = i2;
        }
        iArr[i7] = i6;
        iArr2[i7] = i4;
        int i8 = i7 + 1;
        if (i8 <= 1 || iArr[0] != i2) {
            return i8;
        }
        int i9 = i8 - 1;
        if (iArr2[i9] != i2) {
            return i8;
        }
        iArr[0] = iArr[i9];
        return i8 - 1;
    }

    private static int a(SparseBooleanArray sparseBooleanArray, int i2, int i3) {
        int size = sparseBooleanArray.size();
        int a2 = a(sparseBooleanArray, i2);
        while (a2 < size && sparseBooleanArray.keyAt(a2) < i3 && !sparseBooleanArray.valueAt(a2)) {
            a2++;
        }
        if (a2 == size || sparseBooleanArray.keyAt(a2) >= i3) {
            return -1;
        }
        return a2;
    }

    private static int a(SparseBooleanArray sparseBooleanArray, int i2) {
        int size = sparseBooleanArray.size();
        int i3 = 0;
        while (size - i3 > 0) {
            int i4 = (i3 + size) >> 1;
            if (sparseBooleanArray.keyAt(i4) < i2) {
                i3 = i4 + 1;
            } else {
                size = i4;
            }
        }
        return i3;
    }

    private class DragScroller implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public static final int f18826a = -1;
        public static final int b = 0;
        public static final int c = 1;
        private boolean e;
        private long f;
        private long g;
        private int h;
        private float i;
        private long j;
        private int k;
        private float l;
        private boolean m = false;
        private int n;
        private int o;

        public boolean a() {
            return this.m;
        }

        public int b() {
            if (this.m) {
                return this.k;
            }
            return -1;
        }

        public DragScroller() {
        }

        public void a(int i2) {
            if (!this.m) {
                this.e = false;
                this.m = true;
                this.j = SystemClock.uptimeMillis();
                this.f = this.j;
                this.k = i2;
                DragSortListView.this.post(this);
            }
        }

        public void a(boolean z) {
            if (z) {
                DragSortListView.this.removeCallbacks(this);
                this.m = false;
                return;
            }
            this.e = true;
        }

        public void run() {
            if (this.e) {
                this.m = false;
                return;
            }
            int firstVisiblePosition = DragSortListView.this.getFirstVisiblePosition();
            int lastVisiblePosition = DragSortListView.this.getLastVisiblePosition();
            int count = DragSortListView.this.getCount();
            int paddingTop = DragSortListView.this.getPaddingTop();
            int height = (DragSortListView.this.getHeight() - paddingTop) - DragSortListView.this.getPaddingBottom();
            int min = Math.min(DragSortListView.this.N, DragSortListView.this.d + DragSortListView.this.y);
            int max = Math.max(DragSortListView.this.N, DragSortListView.this.d - DragSortListView.this.y);
            if (this.k == 0) {
                View childAt = DragSortListView.this.getChildAt(0);
                if (childAt == null) {
                    this.m = false;
                    return;
                } else if (firstVisiblePosition == 0 && childAt.getTop() == paddingTop) {
                    this.m = false;
                    return;
                } else {
                    this.l = DragSortListView.this.L.a((DragSortListView.this.H - ((float) max)) / DragSortListView.this.I, this.f);
                }
            } else {
                View childAt2 = DragSortListView.this.getChildAt(lastVisiblePosition - firstVisiblePosition);
                if (childAt2 == null) {
                    this.m = false;
                    return;
                } else if (lastVisiblePosition != count - 1 || childAt2.getBottom() > height + paddingTop) {
                    this.l = -DragSortListView.this.L.a((((float) min) - DragSortListView.this.G) / DragSortListView.this.J, this.f);
                } else {
                    this.m = false;
                    return;
                }
            }
            this.g = SystemClock.uptimeMillis();
            this.i = (float) (this.g - this.f);
            this.h = Math.round(this.l * this.i);
            if (this.h >= 0) {
                this.h = Math.min(height, this.h);
                lastVisiblePosition = firstVisiblePosition;
            } else {
                this.h = Math.max(-height, this.h);
            }
            View childAt3 = DragSortListView.this.getChildAt(lastVisiblePosition - firstVisiblePosition);
            int top = childAt3.getTop() + this.h;
            if (lastVisiblePosition == 0 && top > paddingTop) {
                top = paddingTop;
            }
            boolean unused = DragSortListView.this.ai = true;
            DragSortListView.this.setSelectionFromTop(lastVisiblePosition, top - paddingTop);
            DragSortListView.this.layoutChildren();
            DragSortListView.this.invalidate();
            boolean unused2 = DragSortListView.this.ai = false;
            DragSortListView.this.d(lastVisiblePosition, childAt3, false);
            this.f = this.g;
            DragSortListView.this.post(this);
        }
    }

    private class DragSortTracker {

        /* renamed from: a  reason: collision with root package name */
        StringBuilder f18827a = new StringBuilder();
        File b = new File(Environment.getExternalStorageDirectory(), "dslv_state.txt");
        private int d = 0;
        private int e = 0;
        private boolean f = false;

        public DragSortTracker() {
            if (!this.b.exists()) {
                try {
                    this.b.createNewFile();
                    Log.d("mobeta", "file created");
                } catch (IOException e2) {
                    Log.w("mobeta", "Could not create dslv_state.txt");
                    Log.d("mobeta", e2.getMessage());
                }
            }
        }

        public void a() {
            this.f18827a.append("<DSLVStates>\n");
            this.e = 0;
            this.f = true;
        }

        public void b() {
            if (this.f) {
                this.f18827a.append("<DSLVState>\n");
                int childCount = DragSortListView.this.getChildCount();
                int firstVisiblePosition = DragSortListView.this.getFirstVisiblePosition();
                this.f18827a.append("    <Positions>");
                for (int i = 0; i < childCount; i++) {
                    StringBuilder sb = this.f18827a;
                    sb.append(firstVisiblePosition + i);
                    sb.append(",");
                }
                this.f18827a.append("</Positions>\n");
                this.f18827a.append("    <Tops>");
                for (int i2 = 0; i2 < childCount; i2++) {
                    StringBuilder sb2 = this.f18827a;
                    sb2.append(DragSortListView.this.getChildAt(i2).getTop());
                    sb2.append(",");
                }
                this.f18827a.append("</Tops>\n");
                this.f18827a.append("    <Bottoms>");
                for (int i3 = 0; i3 < childCount; i3++) {
                    StringBuilder sb3 = this.f18827a;
                    sb3.append(DragSortListView.this.getChildAt(i3).getBottom());
                    sb3.append(",");
                }
                this.f18827a.append("</Bottoms>\n");
                StringBuilder sb4 = this.f18827a;
                sb4.append("    <FirstExpPos>");
                sb4.append(DragSortListView.this.j);
                sb4.append("</FirstExpPos>\n");
                StringBuilder sb5 = this.f18827a;
                sb5.append("    <FirstExpBlankHeight>");
                sb5.append(DragSortListView.this.a(DragSortListView.this.j) - DragSortListView.this.d(DragSortListView.this.j));
                sb5.append("</FirstExpBlankHeight>\n");
                StringBuilder sb6 = this.f18827a;
                sb6.append("    <SecondExpPos>");
                sb6.append(DragSortListView.this.k);
                sb6.append("</SecondExpPos>\n");
                StringBuilder sb7 = this.f18827a;
                sb7.append("    <SecondExpBlankHeight>");
                sb7.append(DragSortListView.this.a(DragSortListView.this.k) - DragSortListView.this.d(DragSortListView.this.k));
                sb7.append("</SecondExpBlankHeight>\n");
                StringBuilder sb8 = this.f18827a;
                sb8.append("    <SrcPos>");
                sb8.append(DragSortListView.this.m);
                sb8.append("</SrcPos>\n");
                StringBuilder sb9 = this.f18827a;
                sb9.append("    <SrcHeight>");
                sb9.append(DragSortListView.this.x + DragSortListView.this.getDividerHeight());
                sb9.append("</SrcHeight>\n");
                StringBuilder sb10 = this.f18827a;
                sb10.append("    <ViewHeight>");
                sb10.append(DragSortListView.this.getHeight());
                sb10.append("</ViewHeight>\n");
                StringBuilder sb11 = this.f18827a;
                sb11.append("    <LastY>");
                sb11.append(DragSortListView.this.P);
                sb11.append("</LastY>\n");
                StringBuilder sb12 = this.f18827a;
                sb12.append("    <FloatY>");
                sb12.append(DragSortListView.this.d);
                sb12.append("</FloatY>\n");
                this.f18827a.append("    <ShuffleEdges>");
                for (int i4 = 0; i4 < childCount; i4++) {
                    StringBuilder sb13 = this.f18827a;
                    sb13.append(DragSortListView.this.a(firstVisiblePosition + i4, DragSortListView.this.getChildAt(i4).getTop()));
                    sb13.append(",");
                }
                this.f18827a.append("</ShuffleEdges>\n");
                this.f18827a.append("</DSLVState>\n");
                this.d++;
                if (this.d > 1000) {
                    c();
                    this.d = 0;
                }
            }
        }

        public void c() {
            if (this.f) {
                try {
                    FileWriter fileWriter = new FileWriter(this.b, this.e != 0);
                    fileWriter.write(this.f18827a.toString());
                    this.f18827a.delete(0, this.f18827a.length());
                    fileWriter.flush();
                    fileWriter.close();
                    this.e++;
                } catch (IOException unused) {
                }
            }
        }

        public void d() {
            if (this.f) {
                this.f18827a.append("</DSLVStates>\n");
                c();
                this.f = false;
            }
        }
    }
}
