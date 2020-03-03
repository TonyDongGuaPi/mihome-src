package com.swmansion.gesturehandler.react;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.taobao.weex.ui.component.richtext.node.RichTextNode;

public class RNGestureHandlerButtonViewManager extends ViewGroupManager<ButtonViewGroup> {
    public String getName() {
        return "RNGestureHandlerButton";
    }

    static class ButtonViewGroup extends ViewGroup {

        /* renamed from: a  reason: collision with root package name */
        static TypedValue f8885a = new TypedValue();
        static ButtonViewGroup b;
        int c = 0;
        Integer d;
        boolean e = false;
        boolean f = false;
        float g = 0.0f;
        boolean h = false;

        public void dispatchDrawableHotspotChanged(float f2, float f3) {
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        }

        public ButtonViewGroup(Context context) {
            super(context);
            setClickable(true);
            setFocusable(true);
            this.h = true;
        }

        public void setBackgroundColor(int i) {
            this.c = i;
            this.h = true;
        }

        public void a(Integer num) {
            this.d = num;
            this.h = true;
        }

        public void a(float f2) {
            this.g = f2 * getResources().getDisplayMetrics().density;
            this.h = true;
        }

        private Drawable a(Drawable drawable) {
            if (this.d != null && drawable != null && Build.VERSION.SDK_INT >= 21 && (drawable instanceof RippleDrawable)) {
                ((RippleDrawable) drawable).setColor(new ColorStateList(new int[][]{new int[]{16842910}}, new int[]{this.d.intValue()}));
            }
            return drawable;
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (super.onInterceptTouchEvent(motionEvent)) {
                return true;
            }
            onTouchEvent(motionEvent);
            if (isPressed()) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public void a() {
            if (this.h) {
                this.h = false;
                if (this.c == 0) {
                    setBackground((Drawable) null);
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    setForeground((Drawable) null);
                }
                if (this.e && Build.VERSION.SDK_INT >= 23) {
                    setForeground(a(b()));
                    if (this.c != 0) {
                        setBackgroundColor(this.c);
                    }
                } else if (this.c == 0 && this.d == null) {
                    setBackground(b());
                } else {
                    PaintDrawable paintDrawable = new PaintDrawable(this.c);
                    Drawable b2 = b();
                    if (this.g != 0.0f) {
                        paintDrawable.setCornerRadius(this.g);
                        if (Build.VERSION.SDK_INT >= 21 && (b2 instanceof RippleDrawable)) {
                            PaintDrawable paintDrawable2 = new PaintDrawable(-1);
                            paintDrawable2.setCornerRadius(this.g);
                            ((RippleDrawable) b2).setDrawableByLayerId(16908334, paintDrawable2);
                        }
                    }
                    a(b2);
                    setBackground(new LayerDrawable(new Drawable[]{paintDrawable, b2}));
                }
            }
        }

        public void a(boolean z) {
            this.e = z;
            this.h = true;
        }

        public void b(boolean z) {
            this.f = z;
        }

        private Drawable b() {
            int i = Build.VERSION.SDK_INT;
            getContext().getTheme().resolveAttribute(getResources().getIdentifier((!this.f || i < 21) ? "selectableItemBackground" : "selectableItemBackgroundBorderless", RichTextNode.ATTR, "android"), f8885a, true);
            if (i >= 21) {
                return getResources().getDrawable(f8885a.resourceId, getContext().getTheme());
            }
            return getResources().getDrawable(f8885a.resourceId);
        }

        public void drawableHotspotChanged(float f2, float f3) {
            if (b == null || b == this) {
                super.drawableHotspotChanged(f2, f3);
            }
        }

        public void setPressed(boolean z) {
            if (z && b == null) {
                b = this;
            }
            if (!z || b == this) {
                super.setPressed(z);
            }
            if (!z && b == this) {
                b = null;
            }
        }
    }

    public ButtonViewGroup createViewInstance(ThemedReactContext themedReactContext) {
        return new ButtonViewGroup(themedReactContext);
    }

    @ReactProp(name = "foreground")
    @TargetApi(23)
    public void setForeground(ButtonViewGroup buttonViewGroup, boolean z) {
        buttonViewGroup.a(z);
    }

    @ReactProp(name = "borderless")
    public void setBorderless(ButtonViewGroup buttonViewGroup, boolean z) {
        buttonViewGroup.b(z);
    }

    @ReactProp(name = "enabled")
    public void setEnabled(ButtonViewGroup buttonViewGroup, boolean z) {
        buttonViewGroup.setEnabled(z);
    }

    @ReactProp(name = "borderRadius")
    public void setBorderRadius(ButtonViewGroup buttonViewGroup, float f) {
        buttonViewGroup.a(f);
    }

    @ReactProp(name = "rippleColor")
    public void setRippleColor(ButtonViewGroup buttonViewGroup, Integer num) {
        buttonViewGroup.a(num);
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(ButtonViewGroup buttonViewGroup) {
        buttonViewGroup.a();
    }
}
