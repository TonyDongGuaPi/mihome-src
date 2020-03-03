package com.mi.global.bbs.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class DragableView extends ImageView {
    private int defaultHeight = 70;
    private Context mContext;
    private float x = 0.0f;
    private float y = 0.0f;

    public DragableView(Context context) {
        super(context);
        this.mContext = context;
    }

    public DragableView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public DragableView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private int getStatusAndTitleBarHeight() {
        Activity activity = (Activity) this.mContext;
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int i = rect.top;
        return i + (activity.getWindow().findViewById(16908290).getTop() - i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.defaultHeight = getStatusAndTitleBarHeight();
            this.x = motionEvent.getX();
            this.y = motionEvent.getY() + ((float) this.defaultHeight);
            return true;
        } else if (action != 2) {
            return true;
        } else {
            int rawX = (int) (motionEvent.getRawX() - this.x);
            int rawY = (int) (motionEvent.getRawY() - this.y);
            layout(rawX, rawY, getWidth() + rawX, getHeight() + rawY);
            return true;
        }
    }
}
