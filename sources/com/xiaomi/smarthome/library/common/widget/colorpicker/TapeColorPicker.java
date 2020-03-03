package com.xiaomi.smarthome.library.common.widget.colorpicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.libra.Color;
import com.xiaomi.smarthome.R;
import java.io.PrintStream;

public class TapeColorPicker extends FrameLayout {
    static final String TAG = "ColorPicker";
    View mAddBrightnessView = null;
    ColorPickerView mColorView;
    IndicatorView mIndicatorView;
    final int mMinBrightnessDraw = 80;
    View mSubBrightnessView = null;

    public interface OnColorChangedListener {
        void a(int i, boolean z);
    }

    public Bitmap resizeBitmap(Bitmap bitmap, float f, float f2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(f / ((float) width), f2 / ((float) height));
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public TapeColorPicker(Context context, AttributeSet attributeSet, OnColorChangedListener onColorChangedListener, int i, int i2) {
        super(context, attributeSet);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.tape_color_picker, this);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.color_container);
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.indicator_container);
        this.mAddBrightnessView = findViewById(R.id.indicator_add_view);
        this.mSubBrightnessView = findViewById(R.id.indicator_sub_view);
        this.mColorView = new ColorPickerView(context, onColorChangedListener, i, i2 < 80 ? 80 : i2);
        relativeLayout.addView(this.mColorView);
        this.mIndicatorView = new IndicatorView(context);
        relativeLayout2.addView(this.mIndicatorView);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mColorView.a(i, i2);
        this.mIndicatorView.a(i, i2);
    }

    private class MyPoint {

        /* renamed from: a  reason: collision with root package name */
        float f18990a;
        float b;

        public MyPoint(float f, float f2) {
            this.f18990a = f;
            this.b = f2;
        }
    }

    private class ColorPickerView extends View {
        /* access modifiers changed from: private */
        public OnColorChangedListener A;
        private Paint B;
        private final int[] C = {-65536, -256, -1, Color.g, Color.j, Color.h, Color.k, -65536};
        private int D;
        private int E;

        /* renamed from: a  reason: collision with root package name */
        final float f18985a = 4.0f;
        float b = 0.0f;
        float c = 0.0f;
        float d = 0.0f;
        float e = 0.0f;
        boolean f = false;
        boolean g;
        int h = 0;
        int i;
        int j;
        final float k = 5.0f;
        int[] l;
        MyPoint[] m;
        boolean n = false;
        Bitmap o;
        Bitmap p;
        Canvas q;
        GestureDetectorCompat r;
        Context s;
        final int t = 0;
        final int u = 30;
        boolean v = false;
        float w;
        final float x = 8.0f;
        Handler y = new Handler() {
            public void handleMessage(Message message) {
                if (message.what == 0) {
                    float f = ColorPickerView.this.w;
                    if (ColorPickerView.this.w >= 0.0f) {
                        ColorPickerView.this.w -= 8.0f;
                    } else {
                        ColorPickerView.this.w += 8.0f;
                    }
                    if (f * ColorPickerView.this.w > 0.0f) {
                        ColorPickerView colorPickerView = ColorPickerView.this;
                        colorPickerView.h = (int) (((float) colorPickerView.h) + ColorPickerView.this.w);
                        ColorPickerView.this.i = ColorPickerView.this.b(ColorPickerView.this.h);
                        ColorPickerView.this.invalidate();
                        sendEmptyMessageDelayed(0, 30);
                    } else {
                        TapeColorPicker.this.mIndicatorView.a(ColorPickerView.this.i);
                        ColorPickerView.this.A.a((ColorPickerView.this.i & 16777215) | (ColorPickerView.this.j << 24), true);
                    }
                }
                super.handleMessage(message);
            }
        };

        ColorPickerView(Context context, OnColorChangedListener onColorChangedListener, int i2, int i3) {
            super(context);
            this.s = context;
            this.A = onColorChangedListener;
            this.i = i2;
            this.j = i3;
        }

        public void a(int i2, int i3) {
            this.D = i2;
            this.E = i3;
            this.B = new Paint(1);
            Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.color_tape);
            this.l = a(TapeColorPicker.this.resizeBitmap(decodeResource, (float) ((int) (((float) this.D) * 4.0f)), (float) decodeResource.getHeight()));
            this.o = a(this.l, this.l.length + this.D, this.E);
            this.h = (-a(this.i, this.l)) + (this.D / 2);
            this.p = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
            this.q = new Canvas(this.p);
            this.r = new GestureDetectorCompat(this.s, new MyGestureListener());
            this.n = true;
            invalidate();
        }

        private Bitmap a(int[] iArr, int i2, int i3) {
            Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            int i4 = 0;
            while (i4 < i2) {
                this.B.setColor(iArr[i4 % iArr.length]);
                int i5 = i4 + 1;
                canvas.drawRect((float) i4, 0.0f, (float) i5, (float) this.E, this.B);
                i4 = i5;
            }
            return createBitmap;
        }

        private int a(int i2, int[] iArr) {
            int i3 = (i2 & 16711680) >> 16;
            int i4 = (i2 & 65280) >> 8;
            int i5 = i2 & 255;
            int i6 = Integer.MAX_VALUE;
            int i7 = 0;
            for (int i8 = 0; i8 < iArr.length; i8++) {
                int abs = Math.abs(((iArr[i8] & 16711680) >> 16) - i3) + Math.abs(((iArr[i8] & 65280) >> 8) - i4) + Math.abs((iArr[i8] & 255) - i5);
                if (i6 > abs) {
                    i7 = i8;
                    i6 = abs;
                }
            }
            return i7;
        }

        private MyPoint[] a(int i2) {
            int i3 = i2;
            MyPoint[] myPointArr = new MyPoint[i3];
            MyPoint[] myPointArr2 = {new MyPoint(0.0f, 0.0f), new MyPoint(0.1f, 0.1f), new MyPoint(0.9f, 0.9f), new MyPoint(1.0f, 1.0f)};
            int length = myPointArr2.length - 1;
            for (int i4 = 0; i4 < i3; i4++) {
                MyPoint[] myPointArr3 = new MyPoint[(length + 1)];
                for (int i5 = 0; i5 <= length; i5++) {
                    myPointArr3[i5] = new MyPoint(myPointArr2[i5].f18990a, myPointArr2[i5].b);
                }
                for (int i6 = 1; i6 <= length; i6++) {
                    int i7 = 0;
                    while (i7 <= length - i6) {
                        float f2 = ((float) i4) / ((float) i3);
                        float f3 = 1.0f - f2;
                        int i8 = i7 + 1;
                        myPointArr3[i7].f18990a = (myPointArr3[i7].f18990a * f3) + (myPointArr3[i8].f18990a * f2);
                        myPointArr3[i7].b = (f3 * myPointArr3[i7].b) + (f2 * myPointArr3[i8].b);
                        i7 = i8;
                    }
                }
                myPointArr[i4] = new MyPoint(myPointArr3[0].f18990a, myPointArr3[0].b);
            }
            return myPointArr;
        }

        private void a(int[] iArr, int i2, int i3, int i4, float f2) {
            int i5 = (i3 & 16711680) >> 16;
            int i6 = (i3 & 65280) >> 8;
            int i7 = i3 & 255;
            int i8 = (16711680 & i4) >> 16;
            int i9 = (65280 & i4) >> 8;
            int i10 = i4 & 255;
            for (int i11 = 0; ((float) i11) < f2; i11++) {
                iArr[i2 + i11] = (((int) (((float) i5) + (((float) (i8 - i5)) * this.m[i11].b))) << 16) | -16777216 | (((int) (((float) i6) + (((float) (i9 - i6)) * this.m[i11].b))) << 8) | ((int) (((float) i7) + (((float) (i10 - i7)) * this.m[i11].b)));
            }
        }

        private int[] a(Bitmap bitmap) {
            int width = bitmap.getWidth();
            int[] iArr = new int[width];
            for (int i2 = 0; i2 < width; i2++) {
                iArr[i2] = bitmap.getPixel(i2, 0);
            }
            return iArr;
        }

        private int[] a(int[] iArr, int i2) {
            int[] iArr2 = new int[i2];
            try {
                int length = i2 / (iArr.length - 1);
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    int i5 = i3 + 1;
                    if (i5 >= iArr.length) {
                        break;
                    }
                    a(iArr2, i4, iArr[i3], iArr[i5], (float) length);
                    i4 += length;
                    i3 = i5;
                }
                while (i4 < i2) {
                    iArr2[i4] = iArr[iArr.length - 1];
                    i4++;
                }
            } catch (Exception e2) {
                PrintStream printStream = System.out;
                printStream.println("error:" + e2);
            }
            return iArr2;
        }

        /* access modifiers changed from: private */
        public int b(int i2) {
            return this.l[((-i2) + (this.D / 2)) % this.l.length];
        }

        /* access modifiers changed from: protected */
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            this.h %= this.l.length;
            this.h = this.h <= 0 ? this.h : -(this.l.length - this.h);
            this.B.setAlpha(((this.j * 175) / 100) + 80);
            this.q.drawBitmap(this.o, (float) this.h, 0.0f, (Paint) null);
            canvas.drawBitmap(this.p, 0.0f, 0.0f, this.B);
        }

        private void a(float f2, boolean z2) {
            this.h = (int) (((float) this.h) + f2);
            invalidate();
            this.i = b(this.h);
            this.A.a((this.i & 16777215) | (this.j << 24), z2);
        }

        private void b(float f2, boolean z2) {
            this.j = (int) (((float) this.j) - (f2 / 5.0f));
            if (this.j > 100) {
                this.j = 100;
            }
            if (this.j <= 0) {
                this.j = 1;
            }
            invalidate();
            this.A.a((this.i & 16777215) | (this.j << 24), z2);
            TapeColorPicker.this.mIndicatorView.a(this.j, false);
        }

        class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
            MyGestureListener() {
            }

            public boolean onDown(MotionEvent motionEvent) {
                ColorPickerView.this.v = false;
                return super.onDown(motionEvent);
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (ColorPickerView.this.g) {
                    ColorPickerView.this.v = true;
                    ColorPickerView.this.w = (f * 30.0f) / 1000.0f;
                    ColorPickerView.this.y.sendEmptyMessage(0);
                }
                return true;
            }
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (!this.n) {
                return false;
            }
            this.r.onTouchEvent(motionEvent);
            int action = motionEvent.getAction();
            float x2 = motionEvent.getX();
            float y2 = motionEvent.getY();
            if (action == 0) {
                this.b = x2;
                this.c = y2;
                if (this.y.hasMessages(0)) {
                    this.y.removeMessages(0);
                }
            } else if (action == 2) {
                if (!this.f) {
                    float f2 = x2 - this.b;
                    float f3 = y2 - this.c;
                    if (Math.abs(f2) < 5.0f && Math.abs(f3) < 5.0f) {
                        return true;
                    }
                    double abs = (double) Math.abs(f2);
                    double tan = Math.tan(0.9599310885968813d);
                    Double.isNaN(abs);
                    if (abs * tan > ((double) Math.abs(f3))) {
                        this.g = true;
                        a(x2 - this.d, false);
                    } else {
                        this.g = false;
                        b(y2 - this.e, false);
                    }
                    this.f = true;
                } else if (this.g) {
                    a(x2 - this.d, false);
                } else {
                    b(y2 - this.e, false);
                }
            } else if (action == 1 || action == 3) {
                this.f = false;
                if (!this.g) {
                    TapeColorPicker.this.mIndicatorView.a(this.j, true);
                    this.A.a((16777215 & this.i) | (this.j << 24), true);
                } else if (!this.v) {
                    TapeColorPicker.this.mIndicatorView.a(this.i);
                    this.A.a((16777215 & this.i) | (this.j << 24), true);
                }
            }
            this.d = x2;
            this.e = y2;
            return true;
        }
    }

    private class IndicatorView extends View {

        /* renamed from: a  reason: collision with root package name */
        int f18988a;
        int b;
        boolean c;
        final int d = -1118482;
        final int e = -1;
        float f;
        float g;
        Bitmap h;
        int i;
        int j;
        int k;
        boolean l;
        Paint m;
        Handler n;
        final int o = 1;
        final int p = 50;
        final int q = 2;
        final int r = 50;
        float s = 1.0f;
        int t = 100;
        final float[] u = {1.1f, 1.15f, 1.1f, 1.0f, 0.9f, 1.0f};
        final int[] v = {192, 128, 64, 0};

        public IndicatorView(Context context) {
            super(context);
        }

        public void a(int i2, int i3) {
            this.b = (int) (((float) i3) / 3.6f);
            this.f18988a = this.b;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(getLayoutParams());
            layoutParams.width = this.f18988a;
            layoutParams.height = this.b;
            layoutParams.addRule(13);
            TapeColorPicker.this.mIndicatorView.setLayoutParams(layoutParams);
            this.m = new Paint();
            this.f = 2.0f;
            this.g = 4.0f;
            this.h = BitmapFactory.decodeResource(getResources(), R.drawable.tape_color_picker_brightness);
            this.i = this.f18988a / 2;
            this.j = this.b / 2;
            this.h = TapeColorPicker.this.resizeBitmap(this.h, (float) this.i, (float) this.j);
            this.n = new Handler() {
                public void handleMessage(Message message) {
                    switch (message.what) {
                        case 1:
                            IndicatorView.this.s = message.getData().getFloat("scale");
                            IndicatorView.this.invalidate();
                            break;
                        case 2:
                            IndicatorView.this.t = message.getData().getInt("alpha");
                            IndicatorView.this.l = true;
                            IndicatorView.this.invalidate();
                            break;
                    }
                    super.handleMessage(message);
                }
            };
            this.c = true;
        }

        private void a() {
            for (int i2 = 0; i2 < this.u.length; i2++) {
                Message obtainMessage = this.n.obtainMessage();
                obtainMessage.what = 1;
                Bundle bundle = new Bundle();
                bundle.putFloat("scale", this.u[i2]);
                obtainMessage.setData(bundle);
                this.n.sendMessageDelayed(obtainMessage, (long) (i2 * 50));
            }
        }

        private void b() {
            for (int i2 = 0; i2 < this.v.length; i2++) {
                Message obtainMessage = this.n.obtainMessage();
                obtainMessage.what = 2;
                Bundle bundle = new Bundle();
                bundle.putInt("alpha", this.v[i2]);
                obtainMessage.setData(bundle);
                this.n.sendMessageDelayed(obtainMessage, (long) (i2 * 50));
            }
        }

        public void a(int i2) {
            a();
            if (this.n.hasMessages(2)) {
                this.n.removeMessages(2);
            }
            this.t = 0;
        }

        public void a(int i2, boolean z) {
            if (z) {
                b();
                return;
            }
            this.k = i2;
            this.l = true;
            this.t = 255;
            invalidate();
        }

        /* access modifiers changed from: protected */
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int i2 = this.f18988a / 2;
            float f2 = (float) i2;
            this.m.setColor(-1118482);
            this.m.setStyle(Paint.Style.STROKE);
            this.m.setStrokeWidth(this.f);
            this.m.setAntiAlias(true);
            canvas.drawCircle(f2, f2, (float) ((int) (((f2 - (this.f / 2.0f)) / 1.2f) * this.s)), this.m);
            if (this.l) {
                int i3 = (int) ((f2 - (this.g / 2.0f)) / 1.2f);
                this.m.setColor(-1);
                this.m.setAlpha(this.t);
                this.m.setStyle(Paint.Style.STROKE);
                this.m.setStrokeWidth(this.g);
                this.m.setAntiAlias(true);
                float f3 = (float) (i2 - i3);
                float f4 = (float) (i2 + i3);
                canvas.drawArc(new RectF(f3, f3, f4, f4), -90.0f, (float) ((this.k * 360) / 100), false, this.m);
                this.l = false;
                canvas.drawBitmap(this.h, (float) ((this.f18988a - this.i) / 2), (float) ((this.b - this.j) / 2), this.m);
            }
        }
    }
}
