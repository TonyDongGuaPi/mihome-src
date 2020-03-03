package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.tencent.smtt.sdk.TbsListener;
import com.xiaomi.smarthome.R;
import java.util.ArrayList;
import java.util.List;

public class SceneImageView extends View {

    /* renamed from: a  reason: collision with root package name */
    private List<Bitmap> f18927a = new ArrayList();
    private List<Bitmap> b = new ArrayList();
    private Paint c = new Paint(5);
    private float d;
    private float e;
    private Paint f;

    public SceneImageView(Context context) {
        super(context);
        a();
    }

    public SceneImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public SceneImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.d = getResources().getDimension(R.dimen.scene_small_margin);
        this.e = getResources().getDimension(R.dimen.scene_line);
        this.f = new Paint();
        this.f.setARGB(255, TbsListener.ErrorCode.INSTALL_FROM_UNZIP, TbsListener.ErrorCode.INSTALL_FROM_UNZIP, TbsListener.ErrorCode.INSTALL_FROM_UNZIP);
    }

    public void reset() {
        this.f18927a.clear();
        this.b.clear();
    }

    public void addConditionResource(int i) {
        try {
            this.f18927a.add(BitmapFactory.decodeResource(getResources(), i));
        } catch (OutOfMemoryError unused) {
        }
    }

    public void addActionResource(int i) {
        try {
            this.b.add(BitmapFactory.decodeResource(getResources(), i));
        } catch (OutOfMemoryError unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        for (Bitmap next : this.f18927a) {
            canvas.drawBitmap(next, new Rect(0, 0, next.getWidth(), next.getHeight()), new RectF((float) 0, 0.0f, (float) getHeight(), (float) getHeight()), this.c);
            canvas.translate(((float) getHeight()) + this.d, 0.0f);
        }
        canvas.drawRect(new Rect(0, (int) ((((float) getHeight()) - this.e) / 2.0f), 1, (int) ((((float) getHeight()) + this.e) / 2.0f)), this.f);
        canvas.translate(this.d, 0.0f);
        for (Bitmap next2 : this.b) {
            canvas.drawBitmap(next2, new Rect(0, 0, next2.getWidth(), next2.getHeight()), new RectF((float) 0, 0.0f, (float) getHeight(), (float) getHeight()), this.c);
            canvas.translate(((float) getHeight()) + this.d, 0.0f);
        }
        canvas.restore();
    }
}
