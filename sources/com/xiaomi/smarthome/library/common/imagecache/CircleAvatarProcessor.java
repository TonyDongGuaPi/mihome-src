package com.xiaomi.smarthome.library.common.imagecache;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.request.BasePostprocessor;
import javax.annotation.Nullable;

public class CircleAvatarProcessor extends BasePostprocessor {
    public void process(Bitmap bitmap, Bitmap bitmap2) {
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        bitmap.setHasAlpha(true);
        bitmap.eraseColor(0);
        Canvas canvas = new Canvas(bitmap);
        Path path = new Path();
        float f = (float) width;
        float f2 = (float) height;
        path.addCircle((f - 1.0f) / 2.0f, (f2 - 1.0f) / 2.0f, Math.min(f, f2) / 2.0f, Path.Direction.CCW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap2, new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight()), new Rect(0, 0, width, height), (Paint) null);
    }

    @Nullable
    public CacheKey getPostprocessorCacheKey() {
        return new MyCacheKey(CircleAvatarProcessor.class.getName());
    }

    class MyCacheKey implements CacheKey {

        /* renamed from: a  reason: collision with root package name */
        String f18623a;

        public boolean containsUri(Uri uri) {
            return false;
        }

        public String getUriString() {
            return null;
        }

        MyCacheKey(String str) {
            this.f18623a = String.valueOf(str);
        }

        public boolean equals(Object obj) {
            if (obj instanceof MyCacheKey) {
                return this.f18623a.equalsIgnoreCase(((MyCacheKey) obj).f18623a);
            }
            return false;
        }

        public int hashCode() {
            return this.f18623a.hashCode();
        }
    }
}
