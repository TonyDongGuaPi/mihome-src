package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.drawable.IconCompat;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public class IconCompatParcelizer {
    public static IconCompat read(VersionedParcel versionedParcel) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.mType = versionedParcel.b(iconCompat.mType, 1);
        iconCompat.mData = versionedParcel.b(iconCompat.mData, 2);
        iconCompat.mParcelable = versionedParcel.b(iconCompat.mParcelable, 3);
        iconCompat.mInt1 = versionedParcel.b(iconCompat.mInt1, 4);
        iconCompat.mInt2 = versionedParcel.b(iconCompat.mInt2, 5);
        iconCompat.mTintList = (ColorStateList) versionedParcel.b(iconCompat.mTintList, 6);
        iconCompat.mTintModeStr = versionedParcel.b(iconCompat.mTintModeStr, 7);
        iconCompat.onPostParceling();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, VersionedParcel versionedParcel) {
        versionedParcel.a(true, true);
        iconCompat.onPreParceling(versionedParcel.a());
        versionedParcel.a(iconCompat.mType, 1);
        versionedParcel.a(iconCompat.mData, 2);
        versionedParcel.a(iconCompat.mParcelable, 3);
        versionedParcel.a(iconCompat.mInt1, 4);
        versionedParcel.a(iconCompat.mInt2, 5);
        versionedParcel.a((Parcelable) iconCompat.mTintList, 6);
        versionedParcel.a(iconCompat.mTintModeStr, 7);
    }
}
