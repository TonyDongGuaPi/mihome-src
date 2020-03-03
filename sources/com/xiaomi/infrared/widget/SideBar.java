package com.xiaomi.infrared.widget;

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

    /* renamed from: a  reason: collision with root package name */
    private OnTouchingLetterChangedListener f10266a;
    private int c = -1;
    private Paint d = new Paint();
    private TextView e;

    public interface OnTouchingLetterChangedListener {
        void a(String str);
    }

    public void setTextView(TextView textView) {
        this.e = textView;
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
            this.d.setTypeface(Typeface.DEFAULT_BOLD);
            this.d.setAntiAlias(true);
            this.d.setTextSize(26.0f);
            this.d.setColor(-7500403);
            canvas.drawText(b[i], ((float) (width / 2)) - (this.d.measureText(b[i]) / 2.0f), ((float) ((length * i) + (length / 2))) + 10.0f, this.d);
            this.d.reset();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        int i = this.c;
        OnTouchingLetterChangedListener onTouchingLetterChangedListener = this.f10266a;
        int height = (int) ((y / ((float) getHeight())) * ((float) b.length));
        if (action == 1) {
            setBackgroundDrawable(new ColorDrawable(0));
            this.c = -1;
            invalidate();
            if (this.e != null) {
                this.e.setVisibility(4);
            }
        } else if (i != height && height >= 0 && height < b.length) {
            if (onTouchingLetterChangedListener != null) {
                onTouchingLetterChangedListener.a(b[height]);
            }
            if (this.e != null) {
                this.e.setText(b[height]);
                this.e.setVisibility(0);
            }
            this.c = height;
            invalidate();
        }
        return true;
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.f10266a = onTouchingLetterChangedListener;
    }
}
