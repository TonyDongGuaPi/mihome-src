package com.mi.global.bbs.view.tab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.mi.global.bbs.view.Editor.FontTextView;

public class TabItemView extends FontTextView {
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final float DEFAULT_MSG_PADDING = 3.0f;
    private static final float DEFAULT_MSG_TEXT_SIZE = 12.0f;
    private static final float DEFAULT_POINT_RADIUS = 4.0f;
    private boolean checked;
    private CircleDrawable circleDrawable;
    private MessageDrawable messageDrawable;

    public TabItemView(Context context) {
        this(context, (AttributeSet) null);
    }

    public TabItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setGravity(1);
        this.messageDrawable = new MessageDrawable(-65536, (float) ((int) TypedValue.applyDimension(1, DEFAULT_MSG_PADDING, getResources().getDisplayMetrics())), 0, (float) ((int) TypedValue.applyDimension(2, DEFAULT_MSG_TEXT_SIZE, getResources().getDisplayMetrics())));
        this.circleDrawable = new CircleDrawable(-65536, (float) ((int) TypedValue.applyDimension(1, DEFAULT_POINT_RADIUS, getResources().getDisplayMetrics())));
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean z) {
        if (this.checked != z) {
            this.checked = z;
            refreshDrawableState();
        }
    }

    public void drawCirclePoint() {
        this.circleDrawable.setShow(true);
        invalidate();
    }

    public void clearCirclePoint() {
        this.circleDrawable.setShow(false);
        invalidate();
    }

    public void drawMsg(int i) {
        if (i >= 0) {
            this.messageDrawable.setMsgCount(i);
            invalidate();
            return;
        }
        throw new IllegalArgumentException("message count can't be a negative number");
    }

    public void setMsgTextSize(float f) {
        this.messageDrawable.setMsgTextSize(f);
    }

    public void setMsgBackgroundColor(int i) {
        this.messageDrawable.setBackgroundColor(i);
    }

    public void setMsgPadding(float f) {
        this.messageDrawable.setPadding(f);
    }

    public void setMsgTextColor(int i) {
        this.messageDrawable.setMsgTextColor(i);
    }

    public void setCirclePointColor(int i) {
        this.circleDrawable.setBackgroundColor(i);
    }

    public void setCirclePointRadius(float f) {
        this.circleDrawable.setRadius(f);
    }

    public void setDrawable(Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        setCompoundDrawables((Drawable) null, drawable, (Drawable) null, (Drawable) null);
    }

    public boolean hasCirclePoint() {
        return this.circleDrawable.isShow();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCirclePoint(canvas);
        drawMsgCount(canvas);
    }

    private void drawMsgCount(Canvas canvas) {
        if (this.messageDrawable.getMsgCount() > 0) {
            canvas.save();
            Drawable drawable = getCompoundDrawables()[1];
            int width = getWidth();
            int paddingTop = getPaddingTop();
            if (drawable != null) {
                width += drawable.getIntrinsicWidth();
            }
            canvas.translate((float) ((int) (((float) (width >> 1)) - this.messageDrawable.getRadius())), (float) paddingTop);
            this.messageDrawable.draw(canvas);
            canvas.restore();
        }
    }

    private void drawCirclePoint(Canvas canvas) {
        int i;
        int i2;
        int radius = (int) this.circleDrawable.getRadius();
        if (radius >= 0 && this.circleDrawable.isShow()) {
            canvas.save();
            Drawable drawable = getCompoundDrawables()[1];
            int width = getWidth();
            int paddingTop = getPaddingTop();
            int i3 = 0;
            if (drawable != null) {
                i = drawable.getIntrinsicWidth();
                i2 = drawable.getIntrinsicHeight();
                width += i;
            } else {
                i2 = 0;
                i = 0;
            }
            int i4 = ((width >> 1) - radius) - (i != 0 ? i >> 2 : 0);
            int i5 = paddingTop + radius;
            if (i2 != 0) {
                i3 = i2 >> 3;
            }
            canvas.translate((float) i4, (float) (i5 + i3));
            this.circleDrawable.draw(canvas);
            canvas.restore();
        }
    }
}
