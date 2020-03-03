package com.mi.global.bbs.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.mi.global.bbs.view.Editor.FontTextView;
import com.mi.global.bbs.view.tab.MessageDrawable;

public class MsgView extends FontTextView {
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final float DEFAULT_MSG_PADDING = 3.0f;
    private static final float DEFAULT_MSG_TEXT_SIZE = 9.0f;
    private boolean checked;
    private MessageDrawable messageDrawable;

    public MsgView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MsgView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setGravity(1);
        this.messageDrawable = new MessageDrawable(-65536, (float) ((int) TypedValue.applyDimension(1, DEFAULT_MSG_PADDING, getResources().getDisplayMetrics())), 0, (float) ((int) TypedValue.applyDimension(2, DEFAULT_MSG_TEXT_SIZE, getResources().getDisplayMetrics())));
        setChecked(this.checked);
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

    public void showMsg(int i) {
        if (i < 0) {
            i = 0;
        }
        this.messageDrawable.setMsgCount(i);
        invalidate();
    }

    public int getMsgCount() {
        return this.messageDrawable.getMsgCount();
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

    public void setDrawable(Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        setCompoundDrawables((Drawable) null, drawable, (Drawable) null, (Drawable) null);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMsgCount(canvas);
    }

    private void drawMsgCount(Canvas canvas) {
        if (this.messageDrawable.getMsgCount() > 0) {
            canvas.save();
            int i = 0;
            Drawable drawable = getCompoundDrawables()[0];
            int height = getHeight();
            if (drawable != null) {
                i = 0 + drawable.getIntrinsicWidth();
                height -= drawable.getIntrinsicHeight();
            }
            canvas.translate((float) (i >> 1), (float) ((int) (((float) (height >> 1)) - this.messageDrawable.getRadius())));
            this.messageDrawable.draw(canvas);
            canvas.restore();
        }
    }
}
