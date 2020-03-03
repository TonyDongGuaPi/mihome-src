package net.qiujuer.genius.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import net.qiujuer.genius.ui.R;
import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.compat.UiCompat;
import net.qiujuer.genius.ui.drawable.shape.BorderShape;

public class TextView extends android.widget.TextView {
    public static final int BORDER_ALL = 4369;
    public static final int BORDER_BOTTOM = 4096;
    public static final int BORDER_LEFT = 1;
    public static final int BORDER_RIGHT = 16;
    public static final int BORDER_TOP = 256;
    private int mBorder;
    private int mBorderColor;
    private Drawable mBorderDrawable;
    private int mBorderSize;

    public TextView(Context context) {
        super(context);
    }

    public TextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, R.attr.gTextViewStyle, R.style.Genius_Widget_TextView);
    }

    public TextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i, R.style.Genius_Widget_TextView);
    }

    @TargetApi(21)
    public TextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet, i, i2);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(TextView.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(TextView.class.getName());
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        Typeface a2;
        if (attributeSet != null) {
            Context context = getContext();
            Resources resources = getResources();
            float f = resources.getDisplayMetrics().density;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TextView, i, i2);
            int i3 = obtainStyledAttributes.getInt(R.styleable.TextView_gBorder, 0);
            int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.TextView_gBorderSize, (int) f);
            int color = obtainStyledAttributes.getColor(R.styleable.TextView_gBorderColor, UiCompat.a(resources, R.color.g_default_base_secondary));
            String string = obtainStyledAttributes.getString(R.styleable.TextView_gFont);
            obtainStyledAttributes.recycle();
            setBorder(i3, dimensionPixelOffset, color);
            if (!isInEditMode() && string != null && string.length() > 0 && (a2 = Ui.a(getContext(), string)) != null) {
                setTypeface(a2);
            }
        }
    }

    public void setBorder(int i, int i2, int i3) {
        RectF rectF;
        if (this.mBorder != i || this.mBorderSize != i2 || this.mBorderColor != i3) {
            this.mBorder = i;
            this.mBorderSize = i2;
            this.mBorderColor = i3;
            if (i <= 0) {
                this.mBorderDrawable = null;
            } else {
                if ((i & BORDER_ALL) == 4369) {
                    float f = (float) i2;
                    rectF = new RectF(f, f, f, f);
                } else {
                    int i4 = (i & 1) == 1 ? i2 : 0;
                    int i5 = (i & 16) == 16 ? i2 : 0;
                    int i6 = (i & 256) == 256 ? i2 : 0;
                    if ((i & 4096) != 4096) {
                        i2 = 0;
                    }
                    rectF = new RectF((float) i4, (float) i6, (float) i5, (float) i2);
                }
                if (this.mBorderDrawable == null) {
                    ShapeDrawable shapeDrawable = new ShapeDrawable(new BorderShape(rectF));
                    shapeDrawable.getPaint().setColor(i3);
                    shapeDrawable.setCallback(this);
                    this.mBorderDrawable = shapeDrawable;
                } else {
                    ShapeDrawable shapeDrawable2 = (ShapeDrawable) this.mBorderDrawable;
                    shapeDrawable2.getPaint().setColor(i3);
                    ((BorderShape) shapeDrawable2.getShape()).a(rectF);
                }
            }
            invalidate();
        }
    }

    public int getBorder() {
        return this.mBorder;
    }

    public int getBorderColor() {
        return this.mBorderColor;
    }

    public int getBorderSize() {
        return this.mBorderSize;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Drawable drawable = this.mBorderDrawable;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getHeight());
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        Drawable drawable2 = this.mBorderDrawable;
        return (drawable2 != null && drawable == drawable2) || super.verifyDrawable(drawable);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Drawable drawable = this.mBorderDrawable;
        if (!(drawable == null || this.mBorderSize <= 0 || this.mBorderColor == 0)) {
            drawable.draw(canvas);
        }
        super.onDraw(canvas);
    }
}
