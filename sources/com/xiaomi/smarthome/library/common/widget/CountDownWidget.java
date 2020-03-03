package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.xiaomi.smarthome.library.R;
import com.xiaomi.smarthome.library.common.FontManager;
import com.xiaomi.smarthome.library.common.util.TextViewUtils;

public class CountDownWidget extends DragCircleProgressView {
    public static String FONTNAME = "fonts/DINCond-Medium.otf";

    /* renamed from: a  reason: collision with root package name */
    private boolean f18795a;
    private Drawable b;
    private Drawable c;
    protected Typeface mTypeface;

    public CountDownWidget(Context context) {
        super(context);
        a();
    }

    public CountDownWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public CountDownWidget(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.b = this.mRes.getDrawable(R.drawable.count_down_timer_thumb_on);
        this.c = this.mRes.getDrawable(R.drawable.count_down_timer_thumb_off);
        this.mTypeface = FontManager.a(getContext(), FONTNAME);
    }

    public void setStatus(boolean z) {
        this.f18795a = z;
    }

    public void setSeekArcThumbOn() {
        this.mThumb = this.b;
        int intrinsicHeight = this.mThumb.getIntrinsicHeight() / 2;
        int intrinsicWidth = this.mThumb.getIntrinsicWidth() / 2;
        this.mThumb.setBounds(-intrinsicWidth, -intrinsicHeight, intrinsicWidth, intrinsicHeight);
        postInvalidate();
    }

    public void setSeekArcThumbOff() {
        this.mThumb = this.c;
        int intrinsicHeight = this.mThumb.getIntrinsicHeight() / 2;
        int intrinsicWidth = this.mThumb.getIntrinsicWidth() / 2;
        this.mThumb.setBounds(-intrinsicWidth, -intrinsicHeight, intrinsicWidth, intrinsicHeight);
        postInvalidate();
    }

    /* access modifiers changed from: protected */
    public void onDrawText(Canvas canvas, int i, int i2) {
        String str;
        this.mTextPaint.setTypeface(this.mTypeface);
        StringBuilder sb = new StringBuilder();
        sb.append(a(i));
        if (this.f18795a) {
            str = this.mRes.getString(R.string.close);
        } else {
            str = this.mRes.getString(R.string.open);
        }
        sb.append(str);
        String sb2 = sb.toString();
        canvas.drawText(sb2, (float) (this.mCenterPointX - (TextViewUtils.a(this.mTextPaint, sb2) / 2)), ((float) this.mCenterPointY) + (this.mTextPaint.getTextSize() / 2.0f), this.mTextPaint);
    }

    private String a(int i) {
        if (i < 60) {
            return i + this.mRes.getString(R.string.minute) + this.mRes.getString(R.string.later);
        }
        int i2 = i % 60;
        if (i2 == 0) {
            return (i / 60) + this.mRes.getString(R.string.hour) + this.mRes.getString(R.string.later);
        }
        return (i / 60) + this.mRes.getString(R.string.hour) + i2 + this.mRes.getString(R.string.minute) + this.mRes.getString(R.string.later);
    }
}
