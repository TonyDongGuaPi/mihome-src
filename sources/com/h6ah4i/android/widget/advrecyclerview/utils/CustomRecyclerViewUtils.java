package com.h6ah4i.android.widget.advrecyclerview.utils;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import com.taobao.weex.el.parse.Operators;

public class CustomRecyclerViewUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f5739a = -1;
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = -1;
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 3;
    public static final int i = 4;
    public static final int j = 5;
    public static final int k = -1;
    public static final int l = -1;

    public static boolean b(int i2) {
        return i2 == 1 || i2 == 0;
    }

    public static boolean c(int i2) {
        return i2 == 3 || i2 == 2;
    }

    public static boolean d(int i2) {
        return i2 == 5 || i2 == 4;
    }

    public static RecyclerView.ViewHolder a(@NonNull RecyclerView recyclerView, float f2, float f3) {
        View a2 = a((ViewGroup) recyclerView, f2, f3);
        if (a2 != null) {
            return recyclerView.getChildViewHolder(a2);
        }
        return null;
    }

    public static int a(@NonNull RecyclerView recyclerView) {
        return a(recyclerView.getLayoutManager());
    }

    public static int a(int i2) {
        switch (i2) {
            case -1:
                return -1;
            case 0:
            case 2:
            case 4:
                return 0;
            case 1:
            case 3:
            case 5:
                return 1;
            default:
                throw new IllegalArgumentException("Unknown layout type (= " + i2 + Operators.BRACKET_END_STR);
        }
    }

    public static int a(@Nullable RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getOrientation() == 0 ? 2 : 3;
        }
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).getOrientation() == 0 ? 0 : 1;
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getOrientation() == 0 ? 4 : 5;
        }
        return -1;
    }

    private static View a(@NonNull ViewGroup viewGroup, float f2, float f3) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (f2 >= ((float) childAt.getLeft()) && f2 <= ((float) childAt.getRight()) && f3 >= ((float) childAt.getTop()) && f3 <= ((float) childAt.getBottom())) {
                return childAt;
            }
        }
        return null;
    }

    public static RecyclerView.ViewHolder b(@NonNull RecyclerView recyclerView, float f2, float f3) {
        View findChildViewUnder = recyclerView.findChildViewUnder(f2, f3);
        if (findChildViewUnder != null) {
            return recyclerView.getChildViewHolder(findChildViewUnder);
        }
        return null;
    }

    public static Rect a(View view, Rect rect) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            rect.left = marginLayoutParams.leftMargin;
            rect.right = marginLayoutParams.rightMargin;
            rect.top = marginLayoutParams.topMargin;
            rect.bottom = marginLayoutParams.bottomMargin;
        } else {
            rect.bottom = 0;
            rect.top = 0;
            rect.right = 0;
            rect.left = 0;
        }
        return rect;
    }

    public static Rect a(@NonNull RecyclerView.LayoutManager layoutManager, View view, Rect rect) {
        rect.left = layoutManager.getLeftDecorationWidth(view);
        rect.right = layoutManager.getRightDecorationWidth(view);
        rect.top = layoutManager.getTopDecorationHeight(view);
        rect.bottom = layoutManager.getBottomDecorationHeight(view);
        return rect;
    }

    public static Rect b(@NonNull View view, @NonNull Rect rect) {
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        return rect;
    }

    public static int a(@NonNull RecyclerView recyclerView, boolean z) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (!(layoutManager instanceof LinearLayoutManager)) {
            return -1;
        }
        if (z) {
            return a((LinearLayoutManager) layoutManager);
        }
        return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
    }

    public static int b(@NonNull RecyclerView recyclerView, boolean z) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (!(layoutManager instanceof LinearLayoutManager)) {
            return -1;
        }
        if (z) {
            return b((LinearLayoutManager) layoutManager);
        }
        return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
    }

    public static int b(@NonNull RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
        }
        return -1;
    }

    public static int c(@NonNull RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
        }
        return -1;
    }

    public static int a(@NonNull RecyclerView.ViewHolder viewHolder) {
        int layoutPosition = viewHolder.getLayoutPosition();
        if (layoutPosition == viewHolder.getAdapterPosition()) {
            return layoutPosition;
        }
        return -1;
    }

    public static int d(@NonNull RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return 1;
    }

    public static int e(@NonNull RecyclerView recyclerView) {
        return b(recyclerView.getLayoutManager());
    }

    public static int b(@NonNull RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getOrientation();
        }
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).getOrientation();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getOrientation();
        }
        return -1;
    }

    private static int a(LinearLayoutManager linearLayoutManager) {
        View a2 = a(linearLayoutManager, 0, linearLayoutManager.getChildCount(), false, true);
        if (a2 == null) {
            return -1;
        }
        return linearLayoutManager.getPosition(a2);
    }

    private static int b(LinearLayoutManager linearLayoutManager) {
        View a2 = a(linearLayoutManager, linearLayoutManager.getChildCount() - 1, -1, false, true);
        if (a2 == null) {
            return -1;
        }
        return linearLayoutManager.getPosition(a2);
    }

    private static View a(LinearLayoutManager linearLayoutManager, int i2, int i3, boolean z, boolean z2) {
        int i4 = 1;
        boolean z3 = linearLayoutManager.getOrientation() == 1;
        int height = z3 ? linearLayoutManager.getHeight() : linearLayoutManager.getWidth();
        if (i3 <= i2) {
            i4 = -1;
        }
        View view = null;
        while (i2 != i3) {
            View childAt = linearLayoutManager.getChildAt(i2);
            int top = z3 ? childAt.getTop() : childAt.getLeft();
            int bottom = z3 ? childAt.getBottom() : childAt.getRight();
            if (top < height && bottom > 0) {
                if (!z) {
                    return childAt;
                }
                if (top >= 0 && bottom <= height) {
                    return childAt;
                }
                if (z2 && view == null) {
                    view = childAt;
                }
            }
            i2 += i4;
        }
        return view;
    }

    public static int b(@Nullable RecyclerView.ViewHolder viewHolder) {
        if (viewHolder != null) {
            return viewHolder.getAdapterPosition();
        }
        return -1;
    }

    public static int c(@Nullable RecyclerView.ViewHolder viewHolder) {
        if (viewHolder != null) {
            return viewHolder.getLayoutPosition();
        }
        return -1;
    }

    public static View a(RecyclerView.LayoutManager layoutManager, int i2) {
        if (i2 != -1) {
            return layoutManager.findViewByPosition(i2);
        }
        return null;
    }

    public static int d(@Nullable RecyclerView.ViewHolder viewHolder) {
        View g2 = g(viewHolder);
        if (g2 == null) {
            return -1;
        }
        ViewGroup.LayoutParams layoutParams = g2.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            return ((StaggeredGridLayoutManager.LayoutParams) layoutParams).getSpanIndex();
        }
        if (layoutParams instanceof GridLayoutManager.LayoutParams) {
            return ((GridLayoutManager.LayoutParams) layoutParams).getSpanIndex();
        }
        if (layoutParams instanceof RecyclerView.LayoutParams) {
            return 0;
        }
        return -1;
    }

    public static int e(@Nullable RecyclerView.ViewHolder viewHolder) {
        View g2 = g(viewHolder);
        if (g2 == null) {
            return -1;
        }
        ViewGroup.LayoutParams layoutParams = g2.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            if (((StaggeredGridLayoutManager.LayoutParams) layoutParams).isFullSpan()) {
                return d((RecyclerView) g2.getParent());
            }
            return 1;
        } else if (layoutParams instanceof GridLayoutManager.LayoutParams) {
            return ((GridLayoutManager.LayoutParams) layoutParams).getSpanSize();
        } else {
            if (layoutParams instanceof RecyclerView.LayoutParams) {
                return 1;
            }
            return -1;
        }
    }

    public static boolean f(@Nullable RecyclerView.ViewHolder viewHolder) {
        View g2 = g(viewHolder);
        if (g2 == null) {
            return true;
        }
        ViewGroup.LayoutParams layoutParams = g2.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            return ((StaggeredGridLayoutManager.LayoutParams) layoutParams).isFullSpan();
        }
        if (!(layoutParams instanceof GridLayoutManager.LayoutParams)) {
            return layoutParams instanceof RecyclerView.LayoutParams ? true : true;
        }
        if (d((RecyclerView) g2.getParent()) == ((GridLayoutManager.LayoutParams) layoutParams).getSpanSize()) {
            return true;
        }
        return false;
    }

    private static View g(@Nullable RecyclerView.ViewHolder viewHolder) {
        if (viewHolder == null) {
            return null;
        }
        View view = viewHolder.itemView;
        if (!ViewCompat.isLaidOut(view)) {
            return null;
        }
        return view;
    }
}
