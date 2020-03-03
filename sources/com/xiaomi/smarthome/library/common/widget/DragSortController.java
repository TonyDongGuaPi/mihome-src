package com.xiaomi.smarthome.library.common.widget;

import android.graphics.Point;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

public class DragSortController extends SimpleFloatViewManager implements GestureDetector.OnGestureListener, View.OnTouchListener {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18818a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = -1;
    /* access modifiers changed from: private */
    public float A;
    private int B;
    private int C;
    private int D;
    private boolean E;
    /* access modifiers changed from: private */
    public DragSortListView F;
    /* access modifiers changed from: private */
    public int G;
    private GestureDetector.OnGestureListener H;
    int h;
    int i;
    private int j;
    private boolean k;
    private int l;
    /* access modifiers changed from: private */
    public boolean m;
    /* access modifiers changed from: private */
    public boolean n;
    private GestureDetector o;
    private GestureDetector p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int[] u;
    private int v;
    private int w;
    private int x;
    private int y;
    private boolean z;

    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public DragSortController(DragSortListView dragSortListView) {
        this(dragSortListView, 0, 0, 1);
    }

    public DragSortController(DragSortListView dragSortListView, int i2, int i3, int i4) {
        this(dragSortListView, i2, i3, i4, 0);
    }

    public DragSortController(DragSortListView dragSortListView, int i2, int i3, int i4, int i5) {
        this(dragSortListView, i2, i3, i4, i5, 0);
    }

    public DragSortController(DragSortListView dragSortListView, int i2, int i3, int i4, int i5, int i6) {
        super(dragSortListView);
        this.j = 3;
        this.k = true;
        this.m = false;
        this.n = false;
        this.r = -1;
        this.s = -1;
        this.t = -1;
        this.u = new int[2];
        this.z = false;
        this.A = 500.0f;
        this.H = new GestureDetector.SimpleOnGestureListener() {
            public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (DragSortController.this.m && DragSortController.this.n) {
                    int width = DragSortController.this.F.getWidth() / 5;
                    if (f > DragSortController.this.A) {
                        if (DragSortController.this.G > (-width)) {
                            DragSortController.this.F.stopDragWithVelocity(true, f);
                        }
                    } else if (f < (-DragSortController.this.A) && DragSortController.this.G < width) {
                        DragSortController.this.F.stopDragWithVelocity(true, f);
                    }
                    boolean unused = DragSortController.this.n = false;
                }
                return false;
            }
        };
        this.F = dragSortListView;
        this.o = new GestureDetector(dragSortListView.getContext(), this);
        this.p = new GestureDetector(dragSortListView.getContext(), this.H);
        this.p.setIsLongpressEnabled(false);
        this.q = ViewConfiguration.get(dragSortListView.getContext()).getScaledTouchSlop();
        this.B = i2;
        this.C = i5;
        this.D = i6;
        b(i4);
        a(i3);
    }

    public int a() {
        return this.j;
    }

    public void a(int i2) {
        this.j = i2;
    }

    public void a(boolean z2) {
        this.k = z2;
    }

    public boolean b() {
        return this.k;
    }

    public void b(int i2) {
        this.l = i2;
    }

    public int c() {
        return this.l;
    }

    public void b(boolean z2) {
        this.m = z2;
    }

    public boolean d() {
        return this.m;
    }

    public void c(int i2) {
        this.B = i2;
    }

    public void d(int i2) {
        this.D = i2;
    }

    public void e(int i2) {
        this.C = i2;
    }

    public boolean a(int i2, int i3, int i4) {
        int i5 = (!this.k || this.n) ? 0 : 12;
        if (this.m && this.n) {
            i5 = i5 | 1 | 2;
        }
        this.z = this.F.startDrag(i2 - this.F.getHeaderViewsCount(), i5, i3, i4);
        return this.z;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.F.isDragEnabled() || this.F.listViewIntercepted()) {
            return false;
        }
        this.o.onTouchEvent(motionEvent);
        if (this.m && this.z && this.l == 1) {
            this.p.onTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction() & 255;
        if (action != 3) {
            switch (action) {
                case 0:
                    this.x = (int) motionEvent.getX();
                    this.y = (int) motionEvent.getY();
                    break;
                case 1:
                    if (this.m && this.n) {
                        if ((this.G >= 0 ? this.G : -this.G) > this.F.getWidth() / 2) {
                            this.F.stopDragWithVelocity(true, 0.0f);
                            break;
                        }
                    }
                    break;
            }
        }
        this.n = false;
        this.z = false;
        return false;
    }

    public void a(View view, Point point, Point point2) {
        if (this.m && this.n) {
            this.G = point.x;
        }
    }

    public int a(MotionEvent motionEvent) {
        return c(motionEvent);
    }

    public int b(MotionEvent motionEvent) {
        if (this.l == 1) {
            return d(motionEvent);
        }
        return -1;
    }

    public int c(MotionEvent motionEvent) {
        return a(motionEvent, this.B);
    }

    public int d(MotionEvent motionEvent) {
        return a(motionEvent, this.D);
    }

    public int a(MotionEvent motionEvent, int i2) {
        View view;
        int pointToPosition = this.F.pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        int headerViewsCount = this.F.getHeaderViewsCount();
        int footerViewsCount = this.F.getFooterViewsCount();
        int count = this.F.getCount();
        if (pointToPosition != -1 && pointToPosition >= headerViewsCount && pointToPosition < count - footerViewsCount) {
            View childAt = this.F.getChildAt(pointToPosition - this.F.getFirstVisiblePosition());
            int rawX = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            if (i2 == 0) {
                view = childAt;
            } else {
                view = childAt.findViewById(i2);
            }
            if (view != null) {
                view.getLocationOnScreen(this.u);
                if (rawX > this.u[0] && rawY > this.u[1] && rawX < this.u[0] + view.getWidth() && rawY < this.u[1] + view.getHeight()) {
                    this.v = childAt.getLeft();
                    this.w = childAt.getTop();
                    return pointToPosition;
                }
            }
        }
        return -1;
    }

    public void e() {
        a(this.r, this.h - this.v, this.i - this.w);
    }

    public boolean onDown(MotionEvent motionEvent) {
        if (this.m && this.l == 0) {
            this.t = a(motionEvent, this.C);
        }
        this.r = a(motionEvent);
        this.h = (int) motionEvent.getX();
        this.i = (int) motionEvent.getY();
        if (this.r != -1 && this.j == 0) {
            a(this.r, this.h - this.v, this.i - this.w);
        }
        this.n = false;
        this.E = true;
        this.G = 0;
        this.s = b(motionEvent);
        return true;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        if (motionEvent == null || motionEvent2 == null) {
            return false;
        }
        int x2 = (int) motionEvent.getX();
        int y2 = (int) motionEvent.getY();
        int x3 = (int) motionEvent2.getX();
        int y3 = (int) motionEvent2.getY();
        int i2 = x3 - this.v;
        int i3 = y3 - this.w;
        if (this.E && !this.z && !(this.r == -1 && this.s == -1)) {
            if (this.r != -1) {
                if (this.j == 1 && Math.abs(y3 - y2) > this.q && this.k) {
                    a(this.r, i2, i3);
                } else if (this.j != 0 && Math.abs(x3 - x2) > this.q && this.m) {
                    this.n = true;
                    a(this.s, i2, i3);
                }
            } else if (this.s != -1) {
                if (Math.abs(x3 - x2) > this.q && this.m) {
                    this.n = true;
                    a(this.s, i2, i3);
                } else if (Math.abs(y3 - y2) > this.q) {
                    this.E = false;
                }
            }
        }
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
        if (this.r != -1 && this.j == 2) {
            this.F.performHapticFeedback(0);
            a(this.r, this.x - this.v, this.y - this.w);
        }
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        if (!this.m || this.l != 0 || this.t == -1) {
            return true;
        }
        this.F.removeItem(this.t - this.F.getHeaderViewsCount());
        return true;
    }
}
