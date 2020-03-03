package com.tmall.wireless.vaf.virtualview.view.image;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;

public class NativeImage extends ImageBase {
    private static final String ak = "NativeImage_TMTEST";
    protected NativeImageImp aj;

    public boolean m() {
        return true;
    }

    public NativeImage(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.aj = new NativeImageImp(vafContext.m());
    }

    public void a(Bitmap bitmap, boolean z) {
        this.aj.setImageBitmap(bitmap);
    }

    public void a(Drawable drawable, boolean z) {
        this.aj.setImageDrawable(drawable);
    }

    public void g(String str) {
        this.ah = str;
        this.W.h().a(this.ah, (ImageBase) this, getComMeasuredWidth(), getComMeasuredHeight());
    }

    public View g_() {
        return this.aj;
    }

    public void e() {
        super.e();
        this.W.h().a((String) null, (ImageBase) this, getComMeasuredWidth(), getComMeasuredHeight());
    }

    public void f() {
        super.f();
        this.aj.setScaleType(ImageBase.f9405a.get(this.ai));
        g(this.ah);
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
        this.aj.measureComponent(i, i2);
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
        this.aj.onComMeasure(i, i2);
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        this.aj.onComLayout(z, i, i2, i3, i4);
    }

    public int getComMeasuredWidth() {
        return this.aj.getComMeasuredWidth();
    }

    public int getComMeasuredHeight() {
        return this.aj.getComMeasuredHeight();
    }

    public void comLayout(int i, int i2, int i3, int i4) {
        super.comLayout(i, i2, i3, i4);
        this.aj.comLayout(i, i2, i3, i4);
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new NativeImage(vafContext, viewCache);
        }
    }
}
