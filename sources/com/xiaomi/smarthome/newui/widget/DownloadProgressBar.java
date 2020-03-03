package com.xiaomi.smarthome.newui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.xiaomi.smarthome.R;

public class DownloadProgressBar extends ProgressBar implements ProgressItemView {
    private static final int e = 101;
    private static final int f = 102;
    private static final int g = 103;
    private static final int h = 104;

    /* renamed from: a  reason: collision with root package name */
    private Context f20856a;
    private Paint b;
    private PorterDuffXfermode c;
    private int d;

    public float getPercent() {
        return 0.0f;
    }

    public void setVisible(boolean z) {
    }

    public DownloadProgressBar(Context context) {
        super(context, (AttributeSet) null, 16842872);
        this.f20856a = context;
        a();
    }

    public DownloadProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f20856a = context;
        a();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (this.d) {
            case 101:
                a(canvas, 101);
                return;
            case 102:
                a(canvas, 102);
                return;
            case 103:
                a(canvas, 103);
                return;
            case 104:
                a(canvas, 104);
                return;
            default:
                a(canvas, 101);
                return;
        }
    }

    private void a() {
        setIndeterminate(false);
        setIndeterminateDrawable(ContextCompat.getDrawable(this.f20856a, 17301613));
        setProgressDrawable(ContextCompat.getDrawable(this.f20856a, R.drawable.pb_shape_green));
        setMax(100);
        this.b = new Paint();
        this.b.setDither(true);
        this.b.setAntiAlias(true);
        this.b.setStyle(Paint.Style.FILL_AND_STROKE);
        this.b.setTextAlign(Paint.Align.LEFT);
        this.b.setTextSize((float) getResources().getDimensionPixelSize(R.dimen.font_size_13sp));
        this.b.setTypeface(Typeface.MONOSPACE);
        this.c = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    private void a(int i) {
        switch (i) {
            case 101:
                setProgress(0);
                this.b.setColor(ContextCompat.getColor(this.f20856a, R.color.card_slide_normal));
                return;
            case 102:
                this.b.setColor(ContextCompat.getColor(this.f20856a, R.color.card_slide_progress));
                return;
            case 103:
                this.b.setColor(ContextCompat.getColor(this.f20856a, R.color.card_slide_progress));
                return;
            case 104:
                setProgress(0);
                this.b.setColor(ContextCompat.getColor(this.f20856a, R.color.card_slide_normal));
                return;
            default:
                setProgress(0);
                this.b.setColor(ContextCompat.getColor(this.f20856a, R.color.card_slide_normal));
                return;
        }
    }

    private void a(Canvas canvas, int i) {
        a(i);
        String c2 = c(i);
        int b2 = b(i);
        Rect rect = new Rect();
        this.b.getTextBounds(c2, 0, c2.length(), rect);
        float width = (float) ((getWidth() / 2) - rect.centerX());
        float height = (float) ((getHeight() / 2) - rect.centerY());
        int color = this.b.getColor();
        if (i == 101) {
            this.b.setColor(b2);
            canvas.drawText(c2, width, height, this.b);
            this.b.setColor(color);
            return;
        }
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(createBitmap);
        this.b.setColor(b2);
        canvas2.drawText(c2, width, height, this.b);
        this.b.setColor(color);
        this.b.setXfermode(this.c);
        this.b.setColor(-1);
        canvas2.drawRect(new RectF(0.0f, 0.0f, (float) ((getWidth() * getProgress()) / 100), (float) getHeight()), this.b);
        canvas.drawBitmap(createBitmap, 0.0f, 0.0f, (Paint) null);
        this.b.setXfermode((Xfermode) null);
        if (!createBitmap.isRecycled()) {
            createBitmap.recycle();
        }
    }

    private int b(int i) {
        return ContextCompat.getColor(this.f20856a, R.color.card_bottom_text);
    }

    private String c(int i) {
        switch (i) {
            case 101:
                return getResources().getString(R.string.card_more_operation);
            case 102:
                return getResources().getString(R.string.device_loading);
            case 103:
                return getResources().getString(R.string.download_device_fail);
            case 104:
                return getResources().getString(R.string.card_more_operation);
            default:
                return getResources().getString(R.string.download_device);
        }
    }

    public void setStart() {
        this.d = 102;
        invalidate();
    }

    public void setSuccess() {
        this.d = 104;
        invalidate();
    }

    public void setFail() {
        this.d = 103;
        invalidate();
    }

    public void setCancel() {
        this.d = 101;
        invalidate();
    }

    public void setPercent(float f2) {
        setProgress((int) f2);
    }
}
