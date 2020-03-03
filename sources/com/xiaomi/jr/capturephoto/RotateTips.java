package com.xiaomi.jr.capturephoto;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.Display;
import android.view.OrientationEventListener;
import android.view.WindowManager;
import android.widget.ImageView;
import com.libra.Color;
import com.xiaomi.jr.common.utils.MifiLog;

public class RotateTips {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10348a = 804;
    private static final int b = 494;
    /* access modifiers changed from: private */
    public Activity c;
    private Bitmap d;
    private ImageView e;
    private OrientationEventListener f;

    public RotateTips(Activity activity) {
        this.c = activity;
        this.e = (ImageView) activity.findViewById(R.id.rotate_tips);
        b();
    }

    public void a() {
        this.c.setRequestedOrientation(7);
        Display defaultDisplay = ((WindowManager) this.c.getSystemService("window")).getDefaultDisplay();
        MifiLog.b("TestRotate", "show: " + defaultDisplay.getOrientation());
        c();
        this.f = new OrientationEventListener(this.c, 3) {
            public void onOrientationChanged(int i) {
                if (i >= 140 && i <= 220) {
                    RotateTips.this.c.setRequestedOrientation(7);
                    RotateTips.this.d();
                }
            }
        };
        this.f.enable();
    }

    private void b() {
        this.d = Bitmap.createBitmap(804, 494, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.d);
        canvas.rotate(180.0f, 402.0f, 247.0f);
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight()), 20.0f, 20.0f, paint);
        paint.reset();
        Bitmap decodeResource = BitmapFactory.decodeResource(this.c.getResources(), R.drawable.capture_photo_rotate_diagram);
        canvas.drawBitmap(decodeResource, (float) (402 - (decodeResource.getWidth() / 2)), 102.0f, paint);
        decodeResource.recycle();
        paint.reset();
        paint.setColor(-16777216);
        paint.setTextSize(45.0f);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        canvas.drawText(this.c.getString(R.string.rotate_phone_title), 402.0f, 316.0f, paint);
        paint.reset();
        paint.setColor(Color.c);
        paint.setTextSize(36.0f);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        canvas.drawText(this.c.getString(R.string.rotate_phone_description), 402.0f, 376.0f, paint);
        this.e.setImageBitmap(this.d);
    }

    private void c() {
        if (this.e != null) {
            this.e.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.e != null) {
            this.e.setVisibility(8);
        }
        if (this.d != null) {
            this.d.recycle();
        }
        if (this.f != null) {
            this.f.disable();
        }
    }
}
