package com.tmall.wireless.vaf.virtualview.view.image;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.text.TextUtils;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;

public class VirtualImage extends ImageBase {
    private static final String al = "VirtualImage_TMTEST";
    protected Bitmap aj;
    protected Matrix ak = new Matrix();
    private ViewBase.VirtualViewImp am = new ViewBase.VirtualViewImp();

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public VirtualImage(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.am.a((ViewBase) this);
    }

    public void e() {
        super.e();
        this.am.a();
        this.aj = null;
    }

    /* access modifiers changed from: protected */
    public void aj() {
        if (this.aj != null) {
            if (this.Y == null) {
                this.Y = new Rect(0, 0, this.aj.getWidth(), this.aj.getHeight());
            } else {
                this.Y.set(0, 0, this.aj.getWidth(), this.aj.getHeight());
            }
        } else if (this.O > 0 && this.P > 0 && !TextUtils.isEmpty(this.ah)) {
            f(this.ah);
        }
    }

    public void a(Bitmap bitmap, boolean z) {
        this.aj = bitmap;
        this.Y = null;
        if (z) {
            W();
        }
    }

    public void f(String str) {
        if (this.O > 0 && this.P > 0) {
            this.W.h().a(str, (ImageBase) this, this.O, this.P);
        }
    }

    public void g(String str) {
        if (!TextUtils.equals(this.ah, str)) {
            this.ah = str;
            f(str);
        }
    }

    /* access modifiers changed from: protected */
    public void b(Canvas canvas) {
        super.b(canvas);
        if (this.Y == null) {
            aj();
        }
        if (this.Y != null) {
            switch (this.ai) {
                case 0:
                    canvas.drawBitmap(this.aj, 0.0f, 0.0f, this.j);
                    return;
                case 1:
                    this.ak.setScale(((float) this.O) / ((float) this.Y.width()), ((float) this.P) / ((float) this.Y.height()));
                    canvas.drawBitmap(this.aj, this.ak, this.j);
                    return;
                case 2:
                    this.ak.setScale(((float) this.O) / ((float) this.Y.width()), ((float) this.P) / ((float) this.Y.height()));
                    canvas.drawBitmap(this.aj, this.ak, this.j);
                    return;
                default:
                    return;
            }
        }
    }

    public void f() {
        super.f();
        this.j.setFilterBitmap(true);
        f(this.ah);
    }

    public void onComMeasure(int i, int i2) {
        this.am.onComMeasure(i, i2);
    }

    public void measureComponent(int i, int i2) {
        this.am.measureComponent(i, i2);
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new VirtualImage(vafContext, viewCache);
        }
    }
}
