package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import com.xiaomi.smarthome.R;

public class ListItemView extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private int f18874a = -1;
    boolean mMeasured = false;

    /* access modifiers changed from: package-private */
    public void init() {
    }

    public ListItemView(Context context) {
        super(context);
        init();
    }

    public ListItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
        this.f18874a = getContext().obtainStyledAttributes(attributeSet, R.styleable.ListItemView).getInt(0, -1);
    }

    public ListItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public void setPosition(int i) {
        this.f18874a = i;
    }

    public int getPosition() {
        return this.f18874a;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        ViewParent parent = getParent();
        int i = 0;
        final View view = null;
        if (parent != null && (parent instanceof AbsListView)) {
            ViewGroup viewGroup = (ViewGroup) parent;
            if (this.f18874a != -1 && this.f18874a != 0) {
                while (true) {
                    if (i < viewGroup.getChildCount()) {
                        if ((viewGroup.getChildAt(i) instanceof ListItemView) && ((ListItemView) viewGroup.getChildAt(i)).getPosition() != -1 && ((ListItemView) viewGroup.getChildAt(i)).getPosition() == this.f18874a - 1) {
                            view = viewGroup.getChildAt(i);
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
                if ((isSelected() || isPressed()) && view != null && (view instanceof ListItemView)) {
                    post(new Runnable() {
                        public void run() {
                            view.setEnabled(false);
                        }
                    });
                } else if (view != null && (view instanceof ListItemView)) {
                    post(new Runnable() {
                        public void run() {
                            view.setEnabled(true);
                        }
                    });
                }
            }
        } else if (parent != null && (parent instanceof ViewGroup)) {
            ViewGroup viewGroup2 = (ViewGroup) parent;
            int childCount = viewGroup2.getChildCount() - 1;
            while (true) {
                if (childCount < 0) {
                    break;
                }
                if (!equals(viewGroup2.getChildAt(childCount))) {
                    if (i != 0 && (viewGroup2.getChildAt(childCount) instanceof ListItemView)) {
                        view = viewGroup2.getChildAt(childCount);
                        break;
                    }
                } else {
                    i = 1;
                }
                childCount--;
            }
            if (view != null && (view instanceof ListItemView) && i != 0) {
                if (isSelected() || isPressed()) {
                    post(new Runnable() {
                        public void run() {
                            view.setEnabled(false);
                        }
                    });
                } else {
                    post(new Runnable() {
                        public void run() {
                            view.setEnabled(true);
                        }
                    });
                }
            }
        }
    }

    public void invalidateDrawable(Drawable drawable) {
        super.invalidateDrawable(drawable);
    }
}
