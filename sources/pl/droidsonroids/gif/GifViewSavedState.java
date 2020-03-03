package pl.droidsonroids.gif;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import androidx.annotation.NonNull;

class GifViewSavedState extends View.BaseSavedState {
    public static final Parcelable.Creator<GifViewSavedState> CREATOR = new Parcelable.Creator<GifViewSavedState>() {
        /* renamed from: a */
        public GifViewSavedState createFromParcel(Parcel parcel) {
            return new GifViewSavedState(parcel);
        }

        /* renamed from: a */
        public GifViewSavedState[] newArray(int i) {
            return new GifViewSavedState[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    final long[][] f11962a;

    GifViewSavedState(Parcelable parcelable, Drawable... drawableArr) {
        super(parcelable);
        this.f11962a = new long[drawableArr.length][];
        for (int i = 0; i < drawableArr.length; i++) {
            GifDrawable gifDrawable = drawableArr[i];
            if (gifDrawable instanceof GifDrawable) {
                this.f11962a[i] = gifDrawable.f.r();
            } else {
                this.f11962a[i] = null;
            }
        }
    }

    private GifViewSavedState(Parcel parcel) {
        super(parcel);
        this.f11962a = new long[parcel.readInt()][];
        for (int i = 0; i < this.f11962a.length; i++) {
            this.f11962a[i] = parcel.createLongArray();
        }
    }

    GifViewSavedState(Parcelable parcelable, long[] jArr) {
        super(parcelable);
        this.f11962a = new long[1][];
        this.f11962a[0] = jArr;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f11962a.length);
        for (long[] writeLongArray : this.f11962a) {
            parcel.writeLongArray(writeLongArray);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Drawable drawable, int i) {
        if (this.f11962a[i] != null && (drawable instanceof GifDrawable)) {
            GifDrawable gifDrawable = (GifDrawable) drawable;
            gifDrawable.a((long) gifDrawable.f.a(this.f11962a[i], gifDrawable.e));
        }
    }
}
