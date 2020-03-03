package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.RouterDevice;

public class RouterInfoView extends SurfaceView implements SurfaceHolder.Callback {
    static final String TAG = "RouterInfoView";
    static final int X_SIZE = 10;

    /* renamed from: a  reason: collision with root package name */
    private final SurfaceHolder f18925a = getHolder();
    private DrawThread b;
    int drawIndex;
    Paint mBgPaint;
    Paint mCurrentTimeLinePaint;
    Paint mHistoryTimeLinePaint;
    RouterDevice mRouterDevice;

    public void refreshUI() {
    }

    class DrawThread extends Thread {

        /* renamed from: a  reason: collision with root package name */
        volatile boolean f18926a;

        public DrawThread() {
            setPriority(1);
        }

        public synchronized void start() {
            this.f18926a = true;
            super.start();
        }

        public synchronized void a() {
            this.f18926a = false;
            interrupt();
            try {
                join();
            } catch (InterruptedException unused) {
            }
        }

        public void run() {
            while (this.f18926a) {
                RouterInfoView.this.drawSurface();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0015, code lost:
        r6.f18925a.unlockCanvasAndPost(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        if (r2 == null) goto L_?;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void drawSurface() {
        /*
            r6 = this;
            r0 = 0
            android.view.SurfaceHolder r1 = r6.f18925a     // Catch:{ Exception -> 0x0032, all -> 0x0029 }
            monitor-enter(r1)     // Catch:{ Exception -> 0x0032, all -> 0x0029 }
            android.view.SurfaceHolder r2 = r6.f18925a     // Catch:{ all -> 0x001b }
            android.graphics.Canvas r2 = r2.lockCanvas()     // Catch:{ all -> 0x001b }
            r6.threadDraw(r2)     // Catch:{ all -> 0x0027 }
            r3 = 30
            java.lang.Thread.sleep(r3)     // Catch:{ all -> 0x0027 }
            monitor-exit(r1)     // Catch:{ all -> 0x0027 }
            if (r2 == 0) goto L_0x0039
            android.view.SurfaceHolder r0 = r6.f18925a
            r0.unlockCanvasAndPost(r2)
            goto L_0x0039
        L_0x001b:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
        L_0x001f:
            monitor-exit(r1)     // Catch:{ all -> 0x0027 }
            throw r0     // Catch:{ Exception -> 0x0025, all -> 0x0021 }
        L_0x0021:
            r0 = move-exception
            r1 = r0
            r0 = r2
            goto L_0x002a
        L_0x0025:
            r0 = r2
            goto L_0x0032
        L_0x0027:
            r0 = move-exception
            goto L_0x001f
        L_0x0029:
            r1 = move-exception
        L_0x002a:
            if (r0 == 0) goto L_0x0031
            android.view.SurfaceHolder r2 = r6.f18925a
            r2.unlockCanvasAndPost(r0)
        L_0x0031:
            throw r1
        L_0x0032:
            if (r0 == 0) goto L_0x0039
            android.view.SurfaceHolder r1 = r6.f18925a
            r1.unlockCanvasAndPost(r0)
        L_0x0039:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.widget.RouterInfoView.drawSurface():void");
    }

    public RouterInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f18925a.addCallback(this);
        this.mBgPaint = new Paint();
        this.mBgPaint.setStyle(Paint.Style.FILL);
        this.mBgPaint.setColor(getResources().getColor(R.color.router_bg));
        this.mCurrentTimeLinePaint = new Paint();
        this.mCurrentTimeLinePaint.setAntiAlias(true);
        this.mCurrentTimeLinePaint.setColor(-1);
        this.mCurrentTimeLinePaint.setStrokeWidth(5.0f);
        this.mCurrentTimeLinePaint.setStyle(Paint.Style.STROKE);
        this.mHistoryTimeLinePaint = new Paint();
        this.mHistoryTimeLinePaint.setAntiAlias(true);
        this.mHistoryTimeLinePaint.setStrokeWidth(5.0f);
        this.mHistoryTimeLinePaint.setStyle(Paint.Style.STROKE);
        this.mHistoryTimeLinePaint.setPathEffect(new DashPathEffect(new float[]{4.0f, 4.0f}, 1.0f));
    }

    public void setRouterDevice(RouterDevice routerDevice) {
        this.mRouterDevice = routerDevice;
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "surfaceCreated");
        if (Build.VERSION.SDK_INT < 14) {
            drawSurface();
            return;
        }
        if (this.b == null) {
            this.b = new DrawThread();
        }
        this.b.start();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        Log.d(TAG, "surfaceChanged");
        if (Build.VERSION.SDK_INT < 14) {
            drawSurface();
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "surfaceDestroyed");
        if (this.b != null) {
            this.b.a();
            this.b = null;
        }
    }

    public void threadDraw(Canvas canvas) {
        this.drawIndex++;
        int height = getHeight() / 2;
        canvas.drawRect(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), this.mBgPaint);
        for (int i = 0; i < this.mRouterDevice.k.length; i++) {
            if (this.mRouterDevice.k[i] > 0) {
                if (i == this.mRouterDevice.k.length - 1) {
                    Paint paint = this.mCurrentTimeLinePaint;
                    double d = (double) (height / 5);
                    double d2 = (double) ((height * 4) / 5);
                    double d3 = this.mRouterDevice.l[i];
                    Double.isNaN(d2);
                    Double.isNaN(d);
                    drawSin(canvas, paint, (int) (d + (d2 * d3)));
                } else {
                    this.mHistoryTimeLinePaint.setColor(Color.argb((i * 10) + 15, 255, 255, 255));
                    Paint paint2 = this.mHistoryTimeLinePaint;
                    double d4 = (double) (height / 5);
                    double d5 = (double) ((height * 4) / 5);
                    double d6 = this.mRouterDevice.l[i];
                    Double.isNaN(d5);
                    Double.isNaN(d4);
                    drawSin(canvas, paint2, (int) (d4 + (d5 * d6)));
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void drawSin(Canvas canvas, Paint paint, int i) {
        float f;
        int width = getWidth();
        int i2 = i;
        int height = i2 > getHeight() / 2 ? getHeight() / 2 : i2;
        double d = (double) width;
        Double.isNaN(d);
        double d2 = 12.566370614359172d / d;
        double d3 = (double) this.drawIndex;
        Double.isNaN(d3);
        double d4 = d3 * 3.141592653589793d * 0.1d;
        int i3 = 0;
        float f2 = 0.0f;
        float f3 = 0.0f;
        while (i3 < width) {
            float f4 = (float) i3;
            double d5 = (double) i3;
            Double.isNaN(d);
            Double.isNaN(d5);
            Double.isNaN(d);
            double d6 = d;
            double d7 = (double) height;
            Double.isNaN(d7);
            double abs = d7 * (1.0d - Math.abs(((d5 - (d / 2.0d)) * 2.0d) / d));
            double d8 = (double) f4;
            Double.isNaN(d8);
            float sin = ((float) ((abs * Math.sin((d8 * d2) - d4)) / 2.0d)) + ((float) (getHeight() / 2));
            if (i3 > 0) {
                f = f4;
                canvas.drawLine(f2, f3, f4, sin, paint);
            } else {
                f = f4;
            }
            i3 += 10;
            f3 = sin;
            f2 = f;
            d = d6;
        }
    }
}
