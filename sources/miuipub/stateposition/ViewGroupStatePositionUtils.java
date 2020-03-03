package miuipub.stateposition;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

public class ViewGroupStatePositionUtils {
    public static void a(View view, int i) {
        if (view instanceof StatePosition) {
            ((StatePosition) view).setPosition(i);
        }
    }

    public static void a(ViewGroup viewGroup) {
        boolean z;
        int childCount = viewGroup.getChildCount();
        View view = null;
        int i = 0;
        boolean z2 = true;
        boolean z3 = true;
        while (true) {
            int i2 = 2;
            int i3 = 3;
            if (i >= childCount) {
                break;
            }
            View childAt = viewGroup.getChildAt(i);
            Drawable background = childAt.getBackground();
            Drawable.ConstantState constantState = background != null ? background.getConstantState() : null;
            int visibility = childAt.getVisibility();
            if (constantState == null && visibility == 0) {
                visibility = 4;
            }
            if (visibility == 0) {
                if (z3) {
                    z = true;
                    z3 = false;
                } else if (view == null) {
                    z = true;
                } else {
                    boolean z4 = !view.getClass().equals(childAt.getClass());
                    if (z2) {
                        if (!z4) {
                            i3 = 0;
                        }
                        a(view, i3);
                    } else {
                        if (!z4) {
                            i2 = 1;
                        }
                        a(view, i2);
                    }
                    z = false;
                }
                z2 = z;
                view = childAt;
            } else if (visibility == 4) {
                if (view != null) {
                    if (z2) {
                        i2 = 3;
                    }
                    a(view, i2);
                }
                view = null;
                z2 = true;
                z3 = false;
            }
            i++;
        }
        if (view == null) {
            return;
        }
        if (z2) {
            a(view, 3);
        } else {
            a(view, 2);
        }
    }
}
