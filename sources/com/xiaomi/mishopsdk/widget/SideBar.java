package com.xiaomi.mishopsdk.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.media.ExifInterface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import miuipub.reflect.Field;

public class SideBar extends View {
    public static String[] b = {"A", Field.b, Field.c, Field.g, "G", "H", Field.f, "L", "N", "Q", "S", ExifInterface.GPS_DIRECTION_TRUE, "X", "Y", Field.f3009a};
    private int choose = -1;
    private TextView mTextDialog;
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    private Paint paint = new Paint();

    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String str);
    }

    public void setTextView(TextView textView) {
        this.mTextDialog = textView;
    }

    public SideBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SideBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SideBar(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int length = height / b.length;
        for (int i = 0; i < b.length; i++) {
            this.paint.setTypeface(Typeface.DEFAULT_BOLD);
            this.paint.setAntiAlias(true);
            this.paint.setTextSize(26.0f);
            this.paint.setColor(-7500403);
            canvas.drawText(b[i], ((float) (width / 2)) + (this.paint.measureText(b[i]) / 2.0f), ((float) ((length * i) + (length / 2))) + 10.0f, this.paint);
            this.paint.reset();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        int i = this.choose;
        OnTouchingLetterChangedListener onTouchingLetterChangedListener2 = this.onTouchingLetterChangedListener;
        int height = (int) ((y / ((float) getHeight())) * ((float) b.length));
        if (action == 1) {
            setBackgroundDrawable(new ColorDrawable(0));
            this.choose = -1;
            invalidate();
            if (this.mTextDialog != null) {
                this.mTextDialog.setVisibility(4);
            }
        } else if (i != height && height >= 0 && height < b.length) {
            if (onTouchingLetterChangedListener2 != null) {
                onTouchingLetterChangedListener2.onTouchingLetterChanged(b[height]);
            }
            if (this.mTextDialog != null) {
                this.mTextDialog.setText(b[height]);
                this.mTextDialog.setVisibility(0);
            }
            this.choose = height;
            invalidate();
        }
        return true;
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener2) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener2;
    }
}
