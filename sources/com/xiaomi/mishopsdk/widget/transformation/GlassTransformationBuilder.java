package com.xiaomi.mishopsdk.widget.transformation;

import android.graphics.Bitmap;
import com.squareup.picasso.mishop.Transformation;
import com.taobao.weex.common.Constants;
import com.xiaomi.mishopsdk.util.FastBlur;

public class GlassTransformationBuilder {
    /* access modifiers changed from: private */
    public int mDownScaleFactor = 1;
    /* access modifiers changed from: private */
    public int mRadius = 20;

    public GlassTransformationBuilder radius(int i) {
        this.mRadius = i;
        return this;
    }

    public GlassTransformationBuilder downScale(int i) {
        this.mDownScaleFactor = i;
        return this;
    }

    public Transformation build() {
        return new Transformation() {
            public String key() {
                return Constants.Event.BLUR;
            }

            public Bitmap transform(Bitmap bitmap) {
                Bitmap createBitmap = Bitmap.createBitmap(bitmap.getHeight() / GlassTransformationBuilder.this.mDownScaleFactor, bitmap.getHeight() / GlassTransformationBuilder.this.mDownScaleFactor, Bitmap.Config.RGB_565);
                try {
                    createBitmap = FastBlur.doBlur(bitmap, GlassTransformationBuilder.this.mRadius, false);
                    return createBitmap;
                } finally {
                    if (!bitmap.equals(createBitmap)) {
                        bitmap.recycle();
                    }
                }
            }
        };
    }
}
