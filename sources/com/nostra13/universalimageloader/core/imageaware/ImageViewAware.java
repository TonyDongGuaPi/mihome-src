package com.nostra13.universalimageloader.core.imageaware;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.utils.L;
import java.lang.reflect.Field;

public class ImageViewAware extends ViewAware {
    public ImageViewAware(ImageView imageView) {
        super(imageView);
    }

    public ImageViewAware(ImageView imageView, boolean z) {
        super(imageView, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r1 = (android.widget.ImageView) r2.c.get();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a() {
        /*
            r2 = this;
            int r0 = super.a()
            if (r0 > 0) goto L_0x0016
            java.lang.ref.Reference r1 = r2.c
            java.lang.Object r1 = r1.get()
            android.widget.ImageView r1 = (android.widget.ImageView) r1
            if (r1 == 0) goto L_0x0016
            java.lang.String r0 = "mMaxWidth"
            int r0 = a((java.lang.Object) r1, (java.lang.String) r0)
        L_0x0016:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.core.imageaware.ImageViewAware.a():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r1 = (android.widget.ImageView) r2.c.get();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int b() {
        /*
            r2 = this;
            int r0 = super.b()
            if (r0 > 0) goto L_0x0016
            java.lang.ref.Reference r1 = r2.c
            java.lang.Object r1 = r1.get()
            android.widget.ImageView r1 = (android.widget.ImageView) r1
            if (r1 == 0) goto L_0x0016
            java.lang.String r0 = "mMaxHeight"
            int r0 = a((java.lang.Object) r1, (java.lang.String) r0)
        L_0x0016:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.core.imageaware.ImageViewAware.b():int");
    }

    public ViewScaleType c() {
        ImageView imageView = (ImageView) this.c.get();
        if (imageView != null) {
            return ViewScaleType.fromImageView(imageView);
        }
        return super.c();
    }

    /* renamed from: g */
    public ImageView d() {
        return (ImageView) super.d();
    }

    /* access modifiers changed from: protected */
    public void a(Drawable drawable, View view) {
        ((ImageView) view).setImageDrawable(drawable);
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).start();
        }
    }

    /* access modifiers changed from: protected */
    public void a(Bitmap bitmap, View view) {
        ((ImageView) view).setImageBitmap(bitmap);
    }

    private static int a(Object obj, String str) {
        try {
            Field declaredField = ImageView.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            int intValue = ((Integer) declaredField.get(obj)).intValue();
            if (intValue <= 0 || intValue >= Integer.MAX_VALUE) {
                return 0;
            }
            return intValue;
        } catch (Exception e) {
            L.a((Throwable) e);
            return 0;
        }
    }
}
