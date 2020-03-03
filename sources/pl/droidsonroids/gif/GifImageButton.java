package pl.droidsonroids.gif;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.RequiresApi;
import pl.droidsonroids.gif.GifViewUtils;

public class GifImageButton extends ImageButton {
    private boolean mFreezesAnimation;

    public GifImageButton(Context context) {
        super(context);
    }

    public GifImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        postInit(GifViewUtils.a(this, attributeSet, 0, 0));
    }

    public GifImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        postInit(GifViewUtils.a(this, attributeSet, i, 0));
    }

    @RequiresApi(21)
    public GifImageButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        postInit(GifViewUtils.a(this, attributeSet, i, i2));
    }

    private void postInit(GifViewUtils.GifImageViewAttributes gifImageViewAttributes) {
        this.mFreezesAnimation = gifImageViewAttributes.c;
        if (gifImageViewAttributes.f11964a > 0) {
            super.setImageResource(gifImageViewAttributes.f11964a);
        }
        if (gifImageViewAttributes.b > 0) {
            super.setBackgroundResource(gifImageViewAttributes.b);
        }
    }

    public void setImageURI(Uri uri) {
        if (!GifViewUtils.a((ImageView) this, uri)) {
            super.setImageURI(uri);
        }
    }

    public void setImageResource(int i) {
        if (!GifViewUtils.a(this, true, i)) {
            super.setImageResource(i);
        }
    }

    public void setBackgroundResource(int i) {
        if (!GifViewUtils.a(this, false, i)) {
            super.setBackgroundResource(i);
        }
    }

    public Parcelable onSaveInstanceState() {
        Drawable drawable = null;
        Drawable drawable2 = this.mFreezesAnimation ? getDrawable() : null;
        if (this.mFreezesAnimation) {
            drawable = getBackground();
        }
        return new GifViewSavedState(super.onSaveInstanceState(), drawable2, drawable);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof GifViewSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        GifViewSavedState gifViewSavedState = (GifViewSavedState) parcelable;
        super.onRestoreInstanceState(gifViewSavedState.getSuperState());
        gifViewSavedState.a(getDrawable(), 0);
        gifViewSavedState.a(getBackground(), 1);
    }

    public void setFreezesAnimation(boolean z) {
        this.mFreezesAnimation = z;
    }
}
