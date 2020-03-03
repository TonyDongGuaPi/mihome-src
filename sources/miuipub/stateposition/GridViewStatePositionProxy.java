package miuipub.stateposition;

import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import miuipub.util.Versions;
import miuipub.v6.R;
import miuipub.widget.GridViewItem;

public class GridViewStatePositionProxy {

    /* renamed from: a  reason: collision with root package name */
    private GridView f3013a;

    public GridViewStatePositionProxy(GridView gridView) {
        this.f3013a = gridView;
    }

    public void a() {
        a((ViewGroup) this.f3013a);
    }

    public static void a(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt.getVisibility() == 0 && (childAt instanceof GridViewItem)) {
                a(childAt, a(viewGroup, i, true), a(viewGroup, i, false));
            }
        }
    }

    private static void a(View view, int i, int i2) {
        if (view instanceof GridViewItem) {
            GridViewItem gridViewItem = (GridViewItem) view;
            int horizontalState = gridViewItem.getHorizontalState();
            int verticalState = gridViewItem.getVerticalState();
            if (horizontalState != i || verticalState != i2) {
                gridViewItem.setState(i, i2);
            }
        }
    }

    private static int a(ViewGroup viewGroup, int i, boolean z) {
        boolean a2 = a(viewGroup, i, z, false);
        boolean a3 = a(viewGroup, i, z, true);
        return a2 ? a3 ? z ? R.attr.v6_state_middle_h : R.attr.v6_state_middle_v : z ? R.attr.v6_state_last_h : R.attr.v6_state_last_v : a3 ? z ? R.attr.v6_state_first_h : R.attr.v6_state_first_v : z ? R.attr.v6_state_single_h : R.attr.v6_state_single_v;
    }

    private static boolean b(ViewGroup viewGroup, int i, boolean z) {
        int i2 = -1;
        int i3 = z ? 1 : -1;
        if (z) {
            i2 = viewGroup.getChildCount();
        }
        View childAt = viewGroup.getChildAt(i);
        View view = null;
        while (true) {
            i += i3;
            if (i == i2) {
                break;
            }
            View childAt2 = viewGroup.getChildAt(i);
            if (childAt2.getVisibility() != 8) {
                view = childAt2;
                break;
            }
        }
        if (view == null || view.getVisibility() == 4 || !a(view, childAt)) {
            return false;
        }
        return true;
    }

    private static int a(GridView gridView) {
        int measuredWidth;
        if (Versions.c()) {
            return gridView.getNumColumns();
        }
        int i = 0;
        if (gridView.getChildCount() > 0 && (measuredWidth = gridView.getChildAt(0).getMeasuredWidth()) > 0) {
            i = gridView.getWidth() / measuredWidth;
        }
        if (i > 0) {
            return i;
        }
        return -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a4, code lost:
        if (r3 == 0) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a8, code lost:
        if (r3 == 1) goto L_0x00ac;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0075 A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(android.view.ViewGroup r6, int r7, boolean r8, boolean r9) {
        /*
            boolean r0 = r6 instanceof android.widget.AbsListView
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0097
            android.widget.AbsListView r6 = (android.widget.AbsListView) r6
            boolean r0 = r6 instanceof android.widget.GridView
            if (r0 == 0) goto L_0x0014
            r0 = r6
            android.widget.GridView r0 = (android.widget.GridView) r0
            int r0 = a((android.widget.GridView) r0)
            goto L_0x0015
        L_0x0014:
            r0 = 1
        L_0x0015:
            int r3 = r6.getChildCount()
            if (r8 == 0) goto L_0x0048
            int r8 = r7 % r0
            if (r0 == r2) goto L_0x00b6
            if (r9 == 0) goto L_0x0036
            int r8 = r8 + r2
            if (r8 >= r0) goto L_0x00b6
            int r8 = r7 + 1
            if (r8 >= r3) goto L_0x00b6
            android.view.View r7 = r6.getChildAt(r7)
            android.view.View r6 = r6.getChildAt(r8)
            boolean r1 = a(r7, r6)
            goto L_0x00b6
        L_0x0036:
            int r8 = r8 - r2
            if (r8 < 0) goto L_0x00b6
            android.view.View r8 = r6.getChildAt(r7)
            int r7 = r7 - r2
            android.view.View r6 = r6.getChildAt(r7)
            boolean r1 = a(r8, r6)
            goto L_0x00b6
        L_0x0048:
            int r8 = r6.getFirstVisiblePosition()
            android.widget.Adapter r4 = r6.getAdapter()
            int r5 = r6.getCount()
            if (r9 == 0) goto L_0x0077
            int r0 = r0 + r7
            if (r0 >= r3) goto L_0x0067
            android.view.View r7 = r6.getChildAt(r7)
            android.view.View r6 = r6.getChildAt(r0)
            boolean r6 = a(r7, r6)
        L_0x0065:
            r1 = r6
            goto L_0x00b6
        L_0x0067:
            int r0 = r0 + r8
            if (r0 >= r5) goto L_0x00b6
            int r7 = r7 + r8
            int r6 = r4.getItemViewType(r7)
            int r7 = r4.getItemViewType(r0)
            if (r6 != r7) goto L_0x00b6
        L_0x0075:
            r1 = 1
            goto L_0x00b6
        L_0x0077:
            int r9 = r7 - r0
            if (r9 < 0) goto L_0x0088
            android.view.View r7 = r6.getChildAt(r7)
            android.view.View r6 = r6.getChildAt(r9)
            boolean r6 = a(r7, r6)
            goto L_0x0065
        L_0x0088:
            int r9 = r9 + r8
            if (r9 < 0) goto L_0x00b6
            int r7 = r7 + r8
            int r6 = r4.getItemViewType(r7)
            int r7 = r4.getItemViewType(r9)
            if (r6 != r7) goto L_0x00b6
            goto L_0x0075
        L_0x0097:
            boolean r0 = r6 instanceof android.widget.LinearLayout
            if (r0 == 0) goto L_0x00ab
            r3 = r6
            android.widget.LinearLayout r3 = (android.widget.LinearLayout) r3
            int r3 = r3.getOrientation()
            if (r8 == 0) goto L_0x00a6
            if (r3 == 0) goto L_0x00ac
        L_0x00a6:
            if (r8 != 0) goto L_0x00ab
            if (r3 != r2) goto L_0x00ab
            goto L_0x00ac
        L_0x00ab:
            r2 = 0
        L_0x00ac:
            if (r2 != 0) goto L_0x00b2
            if (r0 != 0) goto L_0x00b6
            if (r8 == 0) goto L_0x00b6
        L_0x00b2:
            boolean r1 = b(r6, r7, r9)
        L_0x00b6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.stateposition.GridViewStatePositionProxy.a(android.view.ViewGroup, int, boolean, boolean):boolean");
    }

    private static boolean a(View view, View view2) {
        if (view == null || view2 == null) {
            return false;
        }
        if (view.getBackground() == view2.getBackground() || view.getClass() == view2.getClass()) {
            return true;
        }
        return false;
    }
}
