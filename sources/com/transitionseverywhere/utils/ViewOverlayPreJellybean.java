package com.transitionseverywhere.utils;

import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.transitionseverywhere.R;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@TargetApi(14)
class ViewOverlayPreJellybean extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final Field f9508a = ReflectionUtils.a(View.class, "mParent");
    private List<Drawable> b;

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public ViewOverlayPreJellybean(Context context) {
        super(context);
        a();
    }

    public ViewOverlayPreJellybean(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public ViewOverlayPreJellybean(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.b = new ArrayList();
    }

    public void addView(View view, int i, int i2) {
        LayoutTransition layoutTransition;
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup.getLayoutTransition() != null) {
                layoutTransition = viewGroup.getLayoutTransition();
                viewGroup.setLayoutTransition((LayoutTransition) null);
            } else {
                layoutTransition = null;
            }
            viewGroup.removeView(view);
            if (layoutTransition != null) {
                viewGroup.setLayoutTransition(layoutTransition);
            }
            if (view.getParent() != null) {
                ViewGroupUtils.a(viewGroup);
                if (!(view.getParent() == null || f9508a == null)) {
                    ReflectionUtils.a((Object) view, f9508a, (Object) null);
                }
            }
            if (view.getParent() != null) {
                return;
            }
        }
        view.setTag(R.id.overlay_layout_params_backup, view.getLayoutParams());
        addView(view, b(view, i, i2));
        invalidate();
    }

    public void removeView(View view) {
        super.removeView(view);
        ViewUtils.a(view, (ViewGroup.LayoutParams) view.getTag(R.id.overlay_layout_params_backup));
        view.setTag(R.id.overlay_layout_params_backup, (Object) null);
    }

    public void a(View view, int i, int i2) {
        if (view.getParent() == this) {
            view.setLayoutParams(b(view, i, i2));
        }
    }

    private FrameLayout.LayoutParams b(View view, int i, int i2) {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        int i3 = i - iArr[0];
        int i4 = i2 - iArr[1];
        layoutParams.leftMargin = i3;
        layoutParams.topMargin = i4;
        view.setLeft(i3);
        view.setTop(i4);
        if (view.getMeasuredWidth() != 0) {
            layoutParams.width = view.getMeasuredWidth();
            view.setRight(i3 + layoutParams.width);
        }
        if (view.getMeasuredHeight() != 0) {
            layoutParams.height = view.getMeasuredHeight();
            view.setBottom(i4 + layoutParams.height);
        }
        return layoutParams;
    }

    public synchronized void a(Drawable drawable) {
        if (drawable != null) {
            this.b.add(drawable);
            invalidate();
        }
    }

    public synchronized void b(Drawable drawable) {
        this.b.remove(drawable);
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Drawable draw : this.b) {
            draw.draw(canvas);
        }
    }

    public static ViewOverlayPreJellybean a(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return null;
        }
        ViewGroup viewGroup2 = viewGroup;
        while (viewGroup2.getId() != 16908290 && viewGroup2.getParent() != null && (viewGroup2.getParent() instanceof ViewGroup)) {
            viewGroup2 = (ViewGroup) viewGroup2.getParent();
        }
        for (int i = 0; i < viewGroup2.getChildCount(); i++) {
            View childAt = viewGroup2.getChildAt(i);
            if (childAt instanceof ViewOverlayPreJellybean) {
                return (ViewOverlayPreJellybean) childAt;
            }
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 119;
        ViewOverlayPreJellybean viewOverlayPreJellybean = new ViewOverlayPreJellybean(viewGroup.getContext());
        viewGroup2.addView(viewOverlayPreJellybean, layoutParams);
        return viewOverlayPreJellybean;
    }
}
