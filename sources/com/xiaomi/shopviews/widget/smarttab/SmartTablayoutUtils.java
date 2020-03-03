package com.xiaomi.shopviews.widget.smarttab;

import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;

final class SmartTablayoutUtils {
    SmartTablayoutUtils() {
    }

    static int a(View view) {
        return a(view, false);
    }

    static int a(View view, boolean z) {
        if (view == null) {
            return 0;
        }
        if (l(view)) {
            if (!z) {
                return view.getLeft();
            }
            return f(view) + view.getLeft();
        } else if (z) {
            return view.getRight() - f(view);
        } else {
            return view.getRight();
        }
    }

    static int b(View view) {
        if (view == null) {
            return 0;
        }
        return MarginLayoutParamsCompat.getMarginEnd((ViewGroup.MarginLayoutParams) view.getLayoutParams());
    }

    static int c(View view) {
        if (view == null) {
            return 0;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams) + MarginLayoutParamsCompat.getMarginStart(marginLayoutParams);
    }

    static int d(View view) {
        if (view == null) {
            return 0;
        }
        return MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams) view.getLayoutParams());
    }

    static int e(View view) {
        if (view == null) {
            return 0;
        }
        return view.getMeasuredWidth();
    }

    static int f(View view) {
        if (view == null) {
            return 0;
        }
        return view.getPaddingRight();
    }

    static int g(View view) {
        if (view == null) {
            return 0;
        }
        return view.getPaddingRight() + view.getPaddingLeft();
    }

    static int h(View view) {
        if (view == null) {
            return 0;
        }
        return view.getPaddingLeft();
    }

    static int i(View view) {
        return b(view, false);
    }

    static int b(View view, boolean z) {
        if (view == null) {
            return 0;
        }
        if (l(view)) {
            if (z) {
                return view.getRight() - h(view);
            }
            return view.getRight();
        } else if (!z) {
            return view.getLeft();
        } else {
            return h(view) + view.getLeft();
        }
    }

    static int j(View view) {
        if (view == null) {
            return 0;
        }
        return view.getWidth();
    }

    static int k(View view) {
        return j(view) + c(view);
    }

    static boolean l(View view) {
        return ViewCompat.getLayoutDirection(view) == 1;
    }
}
