package com.tmall.wireless.vaf.virtualview.view.image;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.ImageView;
import com.libra.Utils;
import com.libra.virtualview.common.StringBase;
import com.mi.global.shop.model.Tags;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;

public abstract class ImageBase extends ViewBase {

    /* renamed from: a  reason: collision with root package name */
    public static SparseArray<ImageView.ScaleType> f9405a = new SparseArray<>();
    private static final String aj = "ImageBase_TMTEST";
    public String ah;
    public int ai = 1;

    public abstract void a(Bitmap bitmap, boolean z);

    public void a(Drawable drawable, boolean z) {
    }

    public void f(String str) {
    }

    static {
        f9405a.put(0, ImageView.ScaleType.MATRIX);
        f9405a.put(1, ImageView.ScaleType.FIT_XY);
        f9405a.put(2, ImageView.ScaleType.FIT_START);
        f9405a.put(3, ImageView.ScaleType.FIT_CENTER);
        f9405a.put(4, ImageView.ScaleType.FIT_END);
        f9405a.put(5, ImageView.ScaleType.CENTER);
        f9405a.put(6, ImageView.ScaleType.CENTER_CROP);
        f9405a.put(7, ImageView.ScaleType.CENTER_INSIDE);
    }

    public ImageBase(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.D = Tags.PaidService.IMG_URL;
    }

    public void e() {
        super.e();
        this.C = null;
    }

    public String b() {
        return this.ah;
    }

    public void b(Bitmap bitmap) {
        a(bitmap, true);
    }

    public void g(String str) {
        if (!TextUtils.equals(this.ah, str)) {
            this.ah = str;
            f(str);
            W();
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, String str) {
        boolean a2 = super.a(i, str);
        if (a2) {
            return a2;
        }
        if (i != 114148) {
            return false;
        }
        if (Utils.a(str)) {
            this.c.a(this, StringBase.A, str, 2);
            return true;
        }
        this.ah = str;
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean c(int i, int i2) {
        boolean c = super.c(i, i2);
        if (c) {
            return c;
        }
        if (i != -1877911644) {
            return false;
        }
        this.ai = i2;
        return true;
    }
}
