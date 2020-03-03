package com.tmall.wireless.vaf.virtualview.core;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import com.tmall.wireless.vaf.framework.VafContext;

public class NativeViewBase extends ViewBase {

    /* renamed from: a  reason: collision with root package name */
    protected View f9383a;

    public NativeViewBase(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
    }

    public void e() {
        super.e();
        if (Build.VERSION.SDK_INT >= 16) {
            this.f9383a.setBackground((Drawable) null);
        } else {
            this.f9383a.setBackgroundDrawable((Drawable) null);
        }
    }

    public void f() {
        super.f();
    }

    /* access modifiers changed from: protected */
    public void c(int i) {
        this.f9383a.setBackgroundColor(i);
    }

    /* access modifiers changed from: protected */
    public void a(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f9383a.setBackground(new BitmapDrawable(this.W.m().getResources(), bitmap));
        } else {
            this.f9383a.setBackgroundDrawable(new BitmapDrawable(this.W.m().getResources(), bitmap));
        }
    }

    public void onComMeasure(int i, int i2) {
        if (this.G > 0) {
            switch (this.G) {
                case 1:
                    if (1073741824 == View.MeasureSpec.getMode(i)) {
                        i2 = View.MeasureSpec.makeMeasureSpec((int) ((((float) View.MeasureSpec.getSize(i)) * this.I) / this.H), 1073741824);
                        break;
                    }
                    break;
                case 2:
                    if (1073741824 == View.MeasureSpec.getMode(i2)) {
                        i = View.MeasureSpec.makeMeasureSpec((int) ((((float) View.MeasureSpec.getSize(i2)) * this.H) / this.I), 1073741824);
                        break;
                    }
                    break;
            }
        }
        if (this.f9383a instanceof IView) {
            ((IView) this.f9383a).onComMeasure(i, i2);
        }
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.f9383a instanceof IView) {
            ((IView) this.f9383a).onComLayout(z, i, i2, i3, i4);
        }
    }

    public int getComMeasuredWidth() {
        if (this.f9383a instanceof IView) {
            return ((IView) this.f9383a).getComMeasuredWidth();
        }
        return this.f9383a.getMeasuredWidth();
    }

    public int getComMeasuredHeight() {
        if (this.f9383a instanceof IView) {
            return ((IView) this.f9383a).getComMeasuredHeight();
        }
        return this.f9383a.getMeasuredHeight();
    }

    public void measureComponent(int i, int i2) {
        if (this.G > 0) {
            switch (this.G) {
                case 1:
                    if (1073741824 == View.MeasureSpec.getMode(i)) {
                        i2 = View.MeasureSpec.makeMeasureSpec((int) ((((float) View.MeasureSpec.getSize(i)) * this.I) / this.H), 1073741824);
                        break;
                    }
                    break;
                case 2:
                    if (1073741824 == View.MeasureSpec.getMode(i2)) {
                        i = View.MeasureSpec.makeMeasureSpec((int) ((((float) View.MeasureSpec.getSize(i2)) * this.H) / this.I), 1073741824);
                        break;
                    }
                    break;
            }
        }
        if (this.f9383a instanceof IView) {
            ((IView) this.f9383a).measureComponent(i, i2);
        } else {
            this.f9383a.measure(i, i2);
        }
    }

    public void comLayout(int i, int i2, int i3, int i4) {
        super.comLayout(i, i2, i3, i4);
        if (this.f9383a instanceof IView) {
            ((IView) this.f9383a).comLayout(i, i2, i3, i4);
        } else {
            this.f9383a.layout(i, i2, i3, i4);
        }
    }

    public View g_() {
        return this.f9383a;
    }
}
