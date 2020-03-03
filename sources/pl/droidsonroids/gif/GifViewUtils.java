package pl.droidsonroids.gif;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RawRes;
import com.facebook.react.modules.appstate.AppStateModule;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

final class GifViewUtils {

    /* renamed from: a  reason: collision with root package name */
    static final String f11963a = "http://schemas.android.com/apk/res/android";
    static final List<String> b = Arrays.asList(new String[]{ShareConstants.V, "drawable", "mipmap"});

    private GifViewUtils() {
    }

    static GifImageViewAttributes a(ImageView imageView, AttributeSet attributeSet, int i, int i2) {
        if (attributeSet == null || imageView.isInEditMode()) {
            return new GifImageViewAttributes();
        }
        GifImageViewAttributes gifImageViewAttributes = new GifImageViewAttributes(imageView, attributeSet, i, i2);
        int i3 = gifImageViewAttributes.d;
        if (i3 >= 0) {
            a(i3, imageView.getDrawable());
            a(i3, imageView.getBackground());
        }
        return gifImageViewAttributes;
    }

    static void a(int i, Drawable drawable) {
        if (drawable instanceof GifDrawable) {
            ((GifDrawable) drawable).a(i);
        }
    }

    static boolean a(ImageView imageView, boolean z, int i) {
        Resources resources = imageView.getResources();
        if (resources != null) {
            try {
                if (!b.contains(resources.getResourceTypeName(i))) {
                    return false;
                }
                GifDrawable gifDrawable = new GifDrawable(resources, i);
                if (z) {
                    imageView.setImageDrawable(gifDrawable);
                    return true;
                }
                imageView.setBackground(gifDrawable);
                return true;
            } catch (Resources.NotFoundException | IOException unused) {
            }
        }
        return false;
    }

    static boolean a(ImageView imageView, Uri uri) {
        if (uri == null) {
            return false;
        }
        try {
            imageView.setImageDrawable(new GifDrawable(imageView.getContext().getContentResolver(), uri));
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    static float a(@NonNull Resources resources, @RawRes @DrawableRes int i) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(i, typedValue, true);
        int i2 = typedValue.density;
        if (i2 == 0) {
            i2 = 160;
        } else if (i2 == 65535) {
            i2 = 0;
        }
        int i3 = resources.getDisplayMetrics().densityDpi;
        if (i2 <= 0 || i3 <= 0) {
            return 1.0f;
        }
        return ((float) i3) / ((float) i2);
    }

    static class GifViewAttributes {
        boolean c;
        final int d;

        GifViewAttributes(View view, AttributeSet attributeSet, int i, int i2) {
            TypedArray obtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, R.styleable.GifView, i, i2);
            this.c = obtainStyledAttributes.getBoolean(R.styleable.GifView_freezesAnimation, false);
            this.d = obtainStyledAttributes.getInt(R.styleable.GifView_loopCount, -1);
            obtainStyledAttributes.recycle();
        }

        GifViewAttributes() {
            this.c = false;
            this.d = -1;
        }
    }

    static class GifImageViewAttributes extends GifViewAttributes {

        /* renamed from: a  reason: collision with root package name */
        final int f11964a;
        final int b;

        GifImageViewAttributes(ImageView imageView, AttributeSet attributeSet, int i, int i2) {
            super(imageView, attributeSet, i, i2);
            this.f11964a = a(imageView, attributeSet, true);
            this.b = a(imageView, attributeSet, false);
        }

        GifImageViewAttributes() {
            this.f11964a = 0;
            this.b = 0;
        }

        private static int a(ImageView imageView, AttributeSet attributeSet, boolean z) {
            int attributeResourceValue = attributeSet.getAttributeResourceValue(GifViewUtils.f11963a, z ? "src" : AppStateModule.APP_STATE_BACKGROUND, 0);
            if (attributeResourceValue > 0) {
                if (!GifViewUtils.b.contains(imageView.getResources().getResourceTypeName(attributeResourceValue)) || GifViewUtils.a(imageView, z, attributeResourceValue)) {
                    return 0;
                }
                return attributeResourceValue;
            }
            return 0;
        }
    }
}
