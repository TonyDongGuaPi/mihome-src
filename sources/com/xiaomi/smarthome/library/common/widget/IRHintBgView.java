package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomi.smarthome.R;

public class IRHintBgView extends View {
    PorterDuffXfermode duffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    Bitmap inBitmap;
    RectF inDestRect = new RectF();
    Rect inSrcRect = new Rect();
    Bitmap outBitmap;
    RectF outDestRect = new RectF();
    Rect outSrcRect = new Rect();
    Paint paint = new Paint();

    public IRHintBgView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.inBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.inBitmap);
        this.paint.setColor(-536870912);
        canvas.drawPaint(this.paint);
    }

    public void setTranspantRect(Rect rect) {
        try {
            this.outBitmap = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(this.outBitmap);
            Drawable drawable = getResources().getDrawable(R.drawable.main_grid_card_bg_normal);
            drawable.setBounds(new Rect(0, 0, rect.width(), rect.height()));
            drawable.draw(canvas);
            this.outDestRect.set(rect);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.inBitmap != null && this.outBitmap != null) {
            canvas.drawColor(16777215);
            int saveLayer = canvas.saveLayer(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), this.paint, 31);
            this.inSrcRect.set(0, 0, this.inBitmap.getWidth(), this.inBitmap.getHeight());
            this.inDestRect.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            canvas.drawBitmap(this.inBitmap, this.inSrcRect, this.inDestRect, this.paint);
            this.paint.setXfermode(this.duffXfermode);
            this.outSrcRect.set(0, 0, this.outBitmap.getWidth(), this.outBitmap.getHeight());
            canvas.drawBitmap(this.outBitmap, this.outSrcRect, this.outDestRect, this.paint);
            this.paint.setXfermode((Xfermode) null);
            canvas.restoreToCount(saveLayer);
        }
    }
}
