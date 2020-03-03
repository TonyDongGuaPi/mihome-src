package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.blurview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.util.AttributeSet;
import android.view.View;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.xiaomi.smarthome.R;

public class BlurringView extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final int f17570a = 15;
    private static final int b = 8;
    private static final int c = Color.parseColor("#AAFFFFFF");
    private ThemedReactContext d;
    private boolean e;
    private int f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private Bitmap k;
    private Bitmap l;
    private Canvas m;
    protected View mBlurredView;
    private RenderScript n;
    private ScriptIntrinsicBlur o;
    private Allocation p;
    private Allocation q;

    public BlurringView(ThemedReactContext themedReactContext) {
        this(themedReactContext, (AttributeSet) null);
    }

    public BlurringView(ThemedReactContext themedReactContext, AttributeSet attributeSet) {
        super(themedReactContext, attributeSet);
        this.e = false;
        getResources();
        int i2 = c;
        this.d = themedReactContext;
        a((Context) themedReactContext);
        TypedArray obtainStyledAttributes = themedReactContext.obtainStyledAttributes(attributeSet, R.styleable.PxBlurringView);
        setBlurRadius(obtainStyledAttributes.getInt(0, 15));
        setDownsampleFactor(obtainStyledAttributes.getInt(1, 8));
        setOverlayColor(obtainStyledAttributes.getColor(2, i2));
        obtainStyledAttributes.recycle();
    }

    public void setBlurredView(View view) {
        this.mBlurredView = view;
        a();
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mBlurredView == null) {
            return;
        }
        if (this.e) {
            View decorView = this.d.getCurrentActivity().getWindow().getDecorView();
            decorView.setDrawingCacheEnabled(true);
            decorView.destroyDrawingCache();
            decorView.buildDrawingCache();
            Bitmap drawingCache = decorView.getDrawingCache();
            canvas.drawBitmap(a(drawingCache), 0.0f, 0.0f, (Paint) null);
            drawingCache.recycle();
            return;
        }
        if (prepare()) {
            if (this.mBlurredView.getBackground() == null || !(this.mBlurredView.getBackground() instanceof ColorDrawable)) {
                this.k.eraseColor(0);
            } else {
                this.k.eraseColor(((ColorDrawable) this.mBlurredView.getBackground()).getColor());
            }
            this.mBlurredView.draw(this.m);
            blur();
            canvas.save();
            canvas.translate(this.mBlurredView.getX() - getX(), this.mBlurredView.getY() - getY());
            canvas.scale((float) this.f, (float) this.f);
            canvas.drawBitmap(this.l, 0.0f, 0.0f, (Paint) null);
            canvas.restore();
        }
        canvas.drawColor(this.g);
    }

    public void setBlurRadius(int i2) {
        this.o.setRadius((float) i2);
        invalidate();
    }

    public void setDownsampleFactor(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("Downsample factor must be greater than 0.");
        } else if (this.f != i2) {
            this.f = i2;
            this.j = true;
            invalidate();
        }
    }

    public void setOverlayColor(int i2) {
        if (this.g != i2) {
            this.g = i2;
            invalidate();
        }
    }

    private void a(Context context) {
        this.n = RenderScript.create(context);
        this.o = ScriptIntrinsicBlur.create(this.n, Element.U8_4(this.n));
    }

    /* access modifiers changed from: protected */
    public boolean prepare() {
        int width = this.mBlurredView.getWidth();
        int height = this.mBlurredView.getHeight();
        if (this.m == null || this.j || this.h != width || this.i != height) {
            this.j = false;
            this.h = width;
            this.i = height;
            int i2 = width / this.f;
            int i3 = height / this.f;
            if (!(this.l != null && this.l.getWidth() == i2 && this.l.getHeight() == i3)) {
                this.k = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
                if (this.k == null) {
                    return false;
                }
                this.l = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
                if (this.l == null) {
                    return false;
                }
            }
            this.m = new Canvas(this.k);
            this.m.scale(1.0f / ((float) this.f), 1.0f / ((float) this.f));
            this.p = Allocation.createFromBitmap(this.n, this.k, Allocation.MipmapControl.MIPMAP_NONE, 1);
            this.q = Allocation.createTyped(this.n, this.p.getType());
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void blur() {
        this.p.copyFrom(this.k);
        this.o.setInput(this.p);
        this.o.forEach(this.q);
        this.q.copyTo(this.l);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        invalidate();
        a();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.n != null) {
            this.n.destroy();
        }
    }

    public void setmIsBlurReactModalHostView(boolean z) {
        this.e = z;
    }

    private void a() {
        if (this.mBlurredView != null && getParent() != null) {
            if (Boolean.valueOf(this.mBlurredView.findViewById(getId()) != null).booleanValue()) {
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) ((ThemedReactContext) getContext()).getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("ReactNativeBlurError", "BlurView must not be a child of the view that is being blurred.");
                setBlurredView((View) null);
                invalidate();
            }
        }
    }

    private Bitmap a(Bitmap bitmap) {
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, Math.round((float) (bitmap.getWidth() * this.f)), Math.round((float) (bitmap.getHeight() * this.f)), false);
        Bitmap createBitmap = Bitmap.createBitmap(createScaledBitmap);
        Allocation createFromBitmap = Allocation.createFromBitmap(this.n, createScaledBitmap);
        Allocation createFromBitmap2 = Allocation.createFromBitmap(this.n, createBitmap);
        this.o.setInput(createFromBitmap);
        this.o.forEach(createFromBitmap2);
        createFromBitmap2.copyTo(createBitmap);
        return createBitmap;
    }
}
