package miuipub.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

public class ListPopupWindow {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3092a = 0;
    public static final int b = 1;
    public static final int c = -1;
    public static final int d = -2;
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = 2;
    private static final String i = "ListPopupWindow";
    private static final boolean j = false;
    private static final int k = 250;
    private View A;
    private int B;
    private DataSetObserver C;
    private View D;
    private Drawable E;
    private AdapterView.OnItemClickListener F;
    private AdapterView.OnItemSelectedListener G;
    private Runnable H;
    /* access modifiers changed from: private */
    public Handler I;
    private Rect J;
    private boolean K;
    int h;
    /* access modifiers changed from: private */
    public final ResizePopupRunnable l;
    private final PopupTouchInterceptor m;
    private final PopupScrollListener n;
    private final ListSelectorHider o;
    private Context p;
    /* access modifiers changed from: private */
    public ArrowPopupWindow q;
    private ListAdapter r;
    /* access modifiers changed from: private */
    public DropDownListView s;
    private int t;
    private int u;
    private int v;
    private int w;
    private boolean x;
    private boolean y;
    private boolean z;

    public ListPopupWindow(Context context) {
        this(context, (AttributeSet) null, 16843519);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16843519);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i2) {
        this.l = new ResizePopupRunnable();
        this.m = new PopupTouchInterceptor();
        this.n = new PopupScrollListener();
        this.o = new ListSelectorHider();
        this.h = Integer.MAX_VALUE;
        this.t = -2;
        this.u = -2;
        this.y = false;
        this.z = false;
        this.B = 0;
        this.I = new Handler();
        this.J = new Rect();
        this.p = context;
        this.q = new ArrowPopupWindow(context, attributeSet, i2);
    }

    public ArrowPopupWindow a() {
        return this.q;
    }

    public void a(ListAdapter listAdapter) {
        if (this.C == null) {
            this.C = new PopupDataSetObserver();
        } else if (this.r != null) {
            this.r.unregisterDataSetObserver(this.C);
        }
        this.r = listAdapter;
        if (this.r != null) {
            listAdapter.registerDataSetObserver(this.C);
        }
        if (this.s != null) {
            this.s.setAdapter(this.r);
        }
    }

    public int b() {
        return this.B;
    }

    public void a(int i2) {
        this.B = i2;
    }

    public boolean c() {
        return this.K;
    }

    public void b(boolean z2) {
        this.K = true;
        this.q.setFocusable(z2);
    }

    public void c(boolean z2) {
        this.z = z2;
    }

    public boolean d() {
        return this.y;
    }

    public void d(boolean z2) {
        this.y = z2;
    }

    public int e() {
        return this.q.getSoftInputMode();
    }

    public void b(int i2) {
        this.q.setSoftInputMode(i2);
    }

    public void a(Drawable drawable) {
        this.E = drawable;
    }

    public Drawable f() {
        return this.q.getBackground();
    }

    public void b(Drawable drawable) {
        this.q.setBackgroundDrawable(drawable);
    }

    public int g() {
        return this.q.getAnimationStyle();
    }

    public void c(int i2) {
        this.q.setAnimationStyle(i2);
    }

    public View h() {
        return this.D;
    }

    public void a(View view) {
        this.D = view;
    }

    public int i() {
        return this.v;
    }

    public void d(int i2) {
        this.v = i2;
    }

    public int j() {
        if (!this.x) {
            return 0;
        }
        return this.w;
    }

    public void e(int i2) {
        this.w = i2;
        this.x = true;
    }

    public int k() {
        return this.u;
    }

    public void f(int i2) {
        this.u = i2;
    }

    public void g(int i2) {
        Drawable background = this.q.getBackground();
        if (background != null) {
            background.getPadding(this.J);
            this.u = this.J.left + this.J.right + i2;
            return;
        }
        f(i2);
    }

    public int l() {
        return this.t;
    }

    public void h(int i2) {
        this.t = i2;
    }

    public void a(AdapterView.OnItemClickListener onItemClickListener) {
        this.F = onItemClickListener;
    }

    public void a(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.G = onItemSelectedListener;
    }

    public void b(View view) {
        boolean isShowing = isShowing();
        if (isShowing) {
            x();
        }
        this.A = view;
        if (isShowing) {
            n();
        }
    }

    public void m() {
        this.I.post(this.H);
    }

    public void n() {
        int i2;
        int i3;
        int y2 = y();
        if (this.u == -1) {
            i2 = -1;
        } else {
            if (this.u == -2) {
                this.q.setWidth(h().getWidth());
            } else {
                this.q.setWidth(this.u);
            }
            i2 = 0;
        }
        if (this.t == -1) {
            i3 = -1;
        } else {
            if (this.t == -2) {
                this.q.setHeight(y2);
            } else {
                this.q.setHeight(this.t);
            }
            i3 = 0;
        }
        boolean z2 = true;
        if (this.q.isShowing()) {
            ArrowPopupWindow arrowPopupWindow = this.q;
            if (this.z || this.y) {
                z2 = false;
            }
            arrowPopupWindow.setOutsideTouchable(z2);
            this.q.update(h(), this.v, this.w, i2, i3);
            return;
        }
        this.q.setWindowLayoutMode(i2, i3);
        ArrowPopupWindow arrowPopupWindow2 = this.q;
        if (this.z || this.y) {
            z2 = false;
        }
        arrowPopupWindow2.setOutsideTouchable(z2);
        this.q.setTouchInterceptor(this.m);
        this.q.a(h(), this.v, this.w);
        this.s.setSelection(-1);
        if (!this.K || this.s.isInTouchMode()) {
            q();
        }
        if (!this.K) {
            this.I.post(this.o);
        }
    }

    public void o() {
        a(true);
    }

    public void a(boolean z2) {
        this.q.a(z2);
        x();
        this.s = null;
        this.I.removeCallbacks(this.l);
    }

    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.q.setOnDismissListener(onDismissListener);
    }

    private void x() {
        if (this.A != null) {
            ViewParent parent = this.A.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.A);
            }
        }
    }

    public int p() {
        return this.q.getInputMethodMode();
    }

    public void i(int i2) {
        this.q.setInputMethodMode(i2);
    }

    public void j(int i2) {
        DropDownListView dropDownListView = this.s;
        if (isShowing() && dropDownListView != null) {
            boolean unused = dropDownListView.c = false;
            dropDownListView.setSelection(i2);
            if (dropDownListView.getChoiceMode() != 0) {
                dropDownListView.setItemChecked(i2, true);
            }
        }
    }

    public void q() {
        DropDownListView dropDownListView = this.s;
        if (dropDownListView != null) {
            boolean unused = dropDownListView.c = true;
            dropDownListView.requestLayout();
        }
    }

    public boolean isShowing() {
        return this.q.isShowing();
    }

    public boolean r() {
        return this.q.getInputMethodMode() == 2;
    }

    public boolean k(int i2) {
        if (!isShowing()) {
            return false;
        }
        if (this.F == null) {
            return true;
        }
        DropDownListView dropDownListView = this.s;
        int i3 = i2;
        this.F.onItemClick(dropDownListView, dropDownListView.getChildAt(i2 - dropDownListView.getFirstVisiblePosition()), i3, dropDownListView.getAdapter().getItemId(i2));
        return true;
    }

    public Object s() {
        if (!isShowing()) {
            return null;
        }
        return this.s.getSelectedItem();
    }

    public int t() {
        if (!isShowing()) {
            return -1;
        }
        return this.s.getSelectedItemPosition();
    }

    public long u() {
        if (!isShowing()) {
            return Long.MIN_VALUE;
        }
        return this.s.getSelectedItemId();
    }

    public View v() {
        if (!isShowing()) {
            return null;
        }
        return this.s.getSelectedView();
    }

    public ListView w() {
        return this.s;
    }

    /* access modifiers changed from: package-private */
    public void l(int i2) {
        this.h = i2;
    }

    public boolean a(int i2, KeyEvent keyEvent) {
        int i3;
        int i4;
        if (isShowing() && i2 != 62 && (this.s.getSelectedItemPosition() >= 0 || !(i2 == 66 || i2 == 23))) {
            int selectedItemPosition = this.s.getSelectedItemPosition();
            boolean z2 = !this.q.isAboveAnchor();
            ListAdapter listAdapter = this.r;
            int i5 = Integer.MAX_VALUE;
            if (listAdapter != null) {
                boolean areAllItemsEnabled = listAdapter.areAllItemsEnabled();
                if (areAllItemsEnabled) {
                    i4 = 0;
                } else {
                    i4 = this.s.a(0, true);
                }
                if (areAllItemsEnabled) {
                    i3 = listAdapter.getCount() - 1;
                } else {
                    i3 = this.s.a(listAdapter.getCount() - 1, false);
                }
                i5 = i4;
            } else {
                i3 = Integer.MIN_VALUE;
            }
            if ((!z2 || i2 != 19 || selectedItemPosition > i5) && (z2 || i2 != 20 || selectedItemPosition < i3)) {
                boolean unused = this.s.c = false;
                if (this.s.onKeyDown(i2, keyEvent)) {
                    this.q.setInputMethodMode(2);
                    this.s.requestFocusFromTouch();
                    n();
                    if (!(i2 == 23 || i2 == 66)) {
                        switch (i2) {
                            case 19:
                            case 20:
                                break;
                        }
                    }
                    return true;
                } else if (!z2 || i2 != 20) {
                    if (!z2 && i2 == 19 && selectedItemPosition == i5) {
                        return true;
                    }
                    return false;
                } else if (selectedItemPosition == i3) {
                    return true;
                }
            } else {
                q();
                this.q.setInputMethodMode(1);
                n();
                return true;
            }
        }
        return false;
    }

    public boolean b(int i2, KeyEvent keyEvent) {
        if (!isShowing() || this.s.getSelectedItemPosition() < 0) {
            return false;
        }
        boolean onKeyUp = this.s.onKeyUp(i2, keyEvent);
        if (onKeyUp && (i2 == 23 || i2 == 66)) {
            a(true);
        }
        return onKeyUp;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v21, resolved type: miuipub.widget.ListPopupWindow$DropDownListView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v22, resolved type: miuipub.widget.ListPopupWindow$DropDownListView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: android.widget.LinearLayout} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v26, resolved type: miuipub.widget.ListPopupWindow$DropDownListView} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int y() {
        /*
            r12 = this;
            miuipub.widget.ListPopupWindow$DropDownListView r0 = r12.s
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = -1
            r3 = 1
            r4 = 0
            if (r0 != 0) goto L_0x00be
            android.content.Context r0 = r12.p
            miuipub.widget.ListPopupWindow$1 r5 = new miuipub.widget.ListPopupWindow$1
            r5.<init>()
            r12.H = r5
            miuipub.widget.ListPopupWindow$DropDownListView r5 = new miuipub.widget.ListPopupWindow$DropDownListView
            boolean r6 = r12.K
            r6 = r6 ^ r3
            r5.<init>(r0, r6)
            r12.s = r5
            android.graphics.drawable.Drawable r5 = r12.E
            if (r5 == 0) goto L_0x0027
            miuipub.widget.ListPopupWindow$DropDownListView r5 = r12.s
            android.graphics.drawable.Drawable r6 = r12.E
            r5.setSelector(r6)
        L_0x0027:
            miuipub.widget.ListPopupWindow$DropDownListView r5 = r12.s
            android.widget.ListAdapter r6 = r12.r
            r5.setAdapter(r6)
            miuipub.widget.ListPopupWindow$DropDownListView r5 = r12.s
            android.widget.AdapterView$OnItemClickListener r6 = r12.F
            r5.setOnItemClickListener(r6)
            miuipub.widget.ListPopupWindow$DropDownListView r5 = r12.s
            r5.setFocusable(r3)
            miuipub.widget.ListPopupWindow$DropDownListView r5 = r12.s
            r5.setFocusableInTouchMode(r3)
            miuipub.widget.ListPopupWindow$DropDownListView r5 = r12.s
            miuipub.widget.ListPopupWindow$2 r6 = new miuipub.widget.ListPopupWindow$2
            r6.<init>()
            r5.setOnItemSelectedListener(r6)
            miuipub.widget.ListPopupWindow$DropDownListView r5 = r12.s
            miuipub.widget.ListPopupWindow$PopupScrollListener r6 = r12.n
            r5.setOnScrollListener(r6)
            android.widget.AdapterView$OnItemSelectedListener r5 = r12.G
            if (r5 == 0) goto L_0x005b
            miuipub.widget.ListPopupWindow$DropDownListView r5 = r12.s
            android.widget.AdapterView$OnItemSelectedListener r6 = r12.G
            r5.setOnItemSelectedListener(r6)
        L_0x005b:
            miuipub.widget.ListPopupWindow$DropDownListView r5 = r12.s
            android.view.View r6 = r12.A
            if (r6 == 0) goto L_0x00b7
            android.widget.LinearLayout r7 = new android.widget.LinearLayout
            r7.<init>(r0)
            r7.setOrientation(r3)
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams
            r8 = 1065353216(0x3f800000, float:1.0)
            r0.<init>(r2, r4, r8)
            int r8 = r12.B
            switch(r8) {
                case 0: goto L_0x0095;
                case 1: goto L_0x008e;
                default: goto L_0x0075;
            }
        L_0x0075:
            java.lang.String r0 = "ListPopupWindow"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r8 = "Invalid hint position "
            r5.append(r8)
            int r8 = r12.B
            r5.append(r8)
            java.lang.String r5 = r5.toString()
            android.util.Log.e(r0, r5)
            goto L_0x009b
        L_0x008e:
            r7.addView(r5, r0)
            r7.addView(r6)
            goto L_0x009b
        L_0x0095:
            r7.addView(r6)
            r7.addView(r5, r0)
        L_0x009b:
            int r0 = r12.u
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r1)
            r6.measure(r0, r4)
            android.view.ViewGroup$LayoutParams r0 = r6.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r0 = (android.widget.LinearLayout.LayoutParams) r0
            int r5 = r6.getMeasuredHeight()
            int r6 = r0.topMargin
            int r5 = r5 + r6
            int r0 = r0.bottomMargin
            int r5 = r5 + r0
            r0 = r5
            r5 = r7
            goto L_0x00b8
        L_0x00b7:
            r0 = 0
        L_0x00b8:
            miuipub.widget.ArrowPopupWindow r6 = r12.q
            r6.setContentView(r5)
            goto L_0x00d4
        L_0x00be:
            android.view.View r0 = r12.A
            if (r0 == 0) goto L_0x00d3
            android.view.ViewGroup$LayoutParams r5 = r0.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r5 = (android.widget.LinearLayout.LayoutParams) r5
            int r0 = r0.getMeasuredHeight()
            int r6 = r5.topMargin
            int r0 = r0 + r6
            int r5 = r5.bottomMargin
            int r0 = r0 + r5
            goto L_0x00d4
        L_0x00d3:
            r0 = 0
        L_0x00d4:
            miuipub.widget.ArrowPopupWindow r5 = r12.q
            android.graphics.drawable.Drawable r5 = r5.getBackground()
            if (r5 == 0) goto L_0x00f6
            android.graphics.Rect r6 = r12.J
            r5.getPadding(r6)
            android.graphics.Rect r5 = r12.J
            int r5 = r5.top
            android.graphics.Rect r6 = r12.J
            int r6 = r6.bottom
            int r5 = r5 + r6
            boolean r6 = r12.x
            if (r6 != 0) goto L_0x00fc
            android.graphics.Rect r6 = r12.J
            int r6 = r6.top
            int r6 = -r6
            r12.w = r6
            goto L_0x00fc
        L_0x00f6:
            android.graphics.Rect r5 = r12.J
            r5.setEmpty()
            r5 = 0
        L_0x00fc:
            miuipub.widget.ArrowPopupWindow r6 = r12.q
            int r6 = r6.getInputMethodMode()
            r7 = 2
            if (r6 != r7) goto L_0x0106
            goto L_0x0107
        L_0x0106:
            r3 = 0
        L_0x0107:
            android.view.View r4 = r12.h()
            int r6 = r12.w
            int r3 = r12.a(r4, r6, r3)
            boolean r4 = r12.y
            if (r4 != 0) goto L_0x016f
            int r4 = r12.t
            if (r4 != r2) goto L_0x011a
            goto L_0x016f
        L_0x011a:
            int r2 = r12.u
            r4 = 1073741824(0x40000000, float:2.0)
            switch(r2) {
                case -2: goto L_0x0144;
                case -1: goto L_0x0129;
                default: goto L_0x0121;
            }
        L_0x0121:
            int r1 = r12.u
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r4)
        L_0x0127:
            r7 = r1
            goto L_0x015f
        L_0x0129:
            android.content.Context r1 = r12.p
            android.content.res.Resources r1 = r1.getResources()
            android.util.DisplayMetrics r1 = r1.getDisplayMetrics()
            int r1 = r1.widthPixels
            android.graphics.Rect r2 = r12.J
            int r2 = r2.left
            android.graphics.Rect r6 = r12.J
            int r6 = r6.right
            int r2 = r2 + r6
            int r1 = r1 - r2
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r4)
            goto L_0x0127
        L_0x0144:
            android.content.Context r2 = r12.p
            android.content.res.Resources r2 = r2.getResources()
            android.util.DisplayMetrics r2 = r2.getDisplayMetrics()
            int r2 = r2.widthPixels
            android.graphics.Rect r4 = r12.J
            int r4 = r4.left
            android.graphics.Rect r6 = r12.J
            int r6 = r6.right
            int r4 = r4 + r6
            int r2 = r2 - r4
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r1)
            goto L_0x0127
        L_0x015f:
            miuipub.widget.ListPopupWindow$DropDownListView r6 = r12.s
            r8 = 0
            r9 = -1
            int r10 = r3 - r0
            r11 = -1
            int r1 = r6.a(r7, r8, r9, r10, r11)
            if (r1 <= 0) goto L_0x016d
            int r0 = r0 + r5
        L_0x016d:
            int r1 = r1 + r0
            return r1
        L_0x016f:
            int r3 = r3 + r5
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.widget.ListPopupWindow.y():int");
    }

    public int a(View view, int i2, boolean z2) {
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i3 = rect.bottom;
        if (z2) {
            i3 = view.getContext().getResources().getDisplayMetrics().heightPixels;
        }
        int max = Math.max((i3 - (iArr[1] + view.getHeight())) - i2, (iArr[1] - rect.top) + i2);
        if (this.q.getBackground() == null) {
            return max;
        }
        this.q.getBackground().getPadding(this.J);
        return max - (this.J.top + this.J.bottom);
    }

    private static class DropDownListView extends ListView {

        /* renamed from: a  reason: collision with root package name */
        public static final int f3095a = -1;
        static final int b = -1;
        /* access modifiers changed from: private */
        public boolean c;
        private boolean d;

        public DropDownListView(Context context, boolean z) {
            super(context, (AttributeSet) null, 16842861);
            this.d = z;
            setCacheColorHint(0);
        }

        /* access modifiers changed from: private */
        public int a(int i, boolean z) {
            int i2;
            ListAdapter adapter = getAdapter();
            if (adapter == null || isInTouchMode()) {
                return -1;
            }
            int count = adapter.getCount();
            if (!getAdapter().areAllItemsEnabled()) {
                if (z) {
                    i2 = Math.max(0, i);
                    while (i2 < count && !adapter.isEnabled(i2)) {
                        i2++;
                    }
                } else {
                    int min = Math.min(i, count - 1);
                    while (i2 >= 0 && !adapter.isEnabled(i2)) {
                        min = i2 - 1;
                    }
                }
                if (i2 < 0 || i2 >= count) {
                    return -1;
                }
                return i2;
            } else if (i < 0 || i >= count) {
                return -1;
            } else {
                return i;
            }
        }

        public boolean isInTouchMode() {
            return (this.d && this.c) || super.isInTouchMode();
        }

        public boolean hasWindowFocus() {
            return this.d || super.hasWindowFocus();
        }

        public boolean isFocused() {
            return this.d || super.isFocused();
        }

        public boolean hasFocus() {
            return this.d || super.hasFocus();
        }

        /* access modifiers changed from: package-private */
        public final int a(int i, int i2, int i3, int i4, int i5) {
            int i6;
            int listPaddingTop = getListPaddingTop();
            int listPaddingBottom = getListPaddingBottom();
            int dividerHeight = getDividerHeight();
            Drawable divider = getDivider();
            ListAdapter adapter = getAdapter();
            if (adapter == null) {
                return listPaddingTop + listPaddingBottom;
            }
            int i7 = listPaddingTop + listPaddingBottom;
            if (dividerHeight <= 0 || divider == null) {
                dividerHeight = 0;
            }
            int count = adapter.getCount();
            int i8 = i7;
            View view = null;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            while (i9 < count) {
                int itemViewType = adapter.getItemViewType(i9);
                if (itemViewType != i10) {
                    view = null;
                    i10 = itemViewType;
                }
                view = adapter.getView(i9, view, this);
                int i12 = view.getLayoutParams().height;
                if (i12 > 0) {
                    i6 = View.MeasureSpec.makeMeasureSpec(i12, 1073741824);
                } else {
                    i6 = View.MeasureSpec.makeMeasureSpec(0, 0);
                }
                view.measure(i, i6);
                if (i9 > 0) {
                    i8 += dividerHeight;
                }
                i8 += view.getMeasuredHeight();
                if (i8 >= i4) {
                    return (i5 < 0 || i9 <= i5 || i11 <= 0 || i8 == i4) ? i4 : i11;
                }
                if (i5 >= 0 && i9 >= i5) {
                    i11 = i8;
                }
                i9++;
            }
            return i8;
        }
    }

    private class PopupDataSetObserver extends DataSetObserver {
        private PopupDataSetObserver() {
        }

        public void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.n();
            }
        }

        public void onInvalidated() {
            ListPopupWindow.this.a(true);
        }
    }

    private class ListSelectorHider implements Runnable {
        private ListSelectorHider() {
        }

        public void run() {
            ListPopupWindow.this.q();
        }
    }

    private class ResizePopupRunnable implements Runnable {
        private ResizePopupRunnable() {
        }

        public void run() {
            if (ListPopupWindow.this.s != null && ListPopupWindow.this.s.getCount() > ListPopupWindow.this.s.getChildCount() && ListPopupWindow.this.s.getChildCount() <= ListPopupWindow.this.h) {
                ListPopupWindow.this.q.setInputMethodMode(2);
                ListPopupWindow.this.n();
            }
        }
    }

    private class PopupTouchInterceptor implements View.OnTouchListener {
        private PopupTouchInterceptor() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && ListPopupWindow.this.q != null && ListPopupWindow.this.q.isShowing() && x >= 0 && x < ListPopupWindow.this.q.getWidth() && y >= 0 && y < ListPopupWindow.this.q.getHeight()) {
                ListPopupWindow.this.I.postDelayed(ListPopupWindow.this.l, 250);
                return false;
            } else if (action != 1) {
                return false;
            } else {
                ListPopupWindow.this.I.removeCallbacks(ListPopupWindow.this.l);
                return false;
            }
        }
    }

    private class PopupScrollListener implements AbsListView.OnScrollListener {
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        private PopupScrollListener() {
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 1 && !ListPopupWindow.this.r() && ListPopupWindow.this.q.getContentView() != null) {
                ListPopupWindow.this.I.removeCallbacks(ListPopupWindow.this.l);
                ListPopupWindow.this.l.run();
            }
        }
    }
}
