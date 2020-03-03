package com.xiaomi.mishopsdk.util;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import java.io.Serializable;
import java.util.ArrayList;

@Deprecated
public class BundleBuilder {
    private Bundle mBundle = new Bundle();

    public Bundle toBundle() {
        return new Bundle(this.mBundle);
    }

    @Deprecated
    public static Bundle toBundleWithClassLoader(Bundle bundle, ClassLoader classLoader) {
        if (bundle == null || classLoader == null) {
            return bundle;
        }
        Parcel obtain = Parcel.obtain();
        bundle.writeToParcel(obtain, 0);
        Bundle bundle2 = new Bundle(classLoader);
        bundle2.readFromParcel(obtain);
        return bundle2;
    }

    public BundleBuilder putBoolean(String str, boolean z) {
        this.mBundle.putBoolean(str, z);
        return this;
    }

    public BundleBuilder putInt(String str, int i) {
        this.mBundle.putInt(str, i);
        return this;
    }

    public BundleBuilder putLong(String str, long j) {
        this.mBundle.putLong(str, j);
        return this;
    }

    public BundleBuilder putDouble(String str, double d) {
        this.mBundle.putDouble(str, d);
        return this;
    }

    public BundleBuilder putString(String str, String str2) {
        this.mBundle.putString(str, str2);
        return this;
    }

    public BundleBuilder putBooleanArray(String str, boolean[] zArr) {
        this.mBundle.putBooleanArray(str, zArr);
        return this;
    }

    public BundleBuilder putIntArray(String str, int[] iArr) {
        this.mBundle.putIntArray(str, iArr);
        return this;
    }

    public BundleBuilder putLongArray(String str, long[] jArr) {
        this.mBundle.putLongArray(str, jArr);
        return this;
    }

    public BundleBuilder putDoubleArray(String str, double[] dArr) {
        this.mBundle.putDoubleArray(str, dArr);
        return this;
    }

    public BundleBuilder putStringArray(String str, String[] strArr) {
        this.mBundle.putStringArray(str, strArr);
        return this;
    }

    public BundleBuilder putAll(Bundle bundle) {
        this.mBundle.putAll(bundle);
        return this;
    }

    public BundleBuilder putByte(String str, byte b) {
        this.mBundle.putByte(str, b);
        return this;
    }

    public BundleBuilder putChar(String str, char c) {
        this.mBundle.putChar(str, c);
        return this;
    }

    public BundleBuilder putShort(String str, short s) {
        this.mBundle.putShort(str, s);
        return this;
    }

    public BundleBuilder putFloat(String str, float f) {
        this.mBundle.putFloat(str, f);
        return this;
    }

    public BundleBuilder putCharSequence(String str, CharSequence charSequence) {
        this.mBundle.putCharSequence(str, charSequence);
        return this;
    }

    public BundleBuilder putParcelable(String str, Parcelable parcelable) {
        this.mBundle.putParcelable(str, parcelable);
        return this;
    }

    public BundleBuilder putSize(String str, Size size) {
        this.mBundle.putSize(str, size);
        return this;
    }

    public BundleBuilder putSizeF(String str, SizeF sizeF) {
        this.mBundle.putSizeF(str, sizeF);
        return this;
    }

    public BundleBuilder putParcelableArray(String str, Parcelable[] parcelableArr) {
        this.mBundle.putParcelableArray(str, parcelableArr);
        return this;
    }

    public BundleBuilder putParcelableArrayList(String str, ArrayList<? extends Parcelable> arrayList) {
        this.mBundle.putParcelableArrayList(str, arrayList);
        return this;
    }

    public BundleBuilder putSparseParcelableArray(String str, SparseArray<? extends Parcelable> sparseArray) {
        this.mBundle.putSparseParcelableArray(str, sparseArray);
        return this;
    }

    public BundleBuilder putIntegerArrayList(String str, ArrayList<Integer> arrayList) {
        this.mBundle.putIntegerArrayList(str, arrayList);
        return this;
    }

    public BundleBuilder putStringArrayList(String str, ArrayList<String> arrayList) {
        this.mBundle.putStringArrayList(str, arrayList);
        return this;
    }

    public BundleBuilder putCharSequenceArrayList(String str, ArrayList<CharSequence> arrayList) {
        this.mBundle.putCharSequenceArrayList(str, arrayList);
        return this;
    }

    public BundleBuilder putSerializable(String str, Serializable serializable) {
        this.mBundle.putSerializable(str, serializable);
        return this;
    }

    public BundleBuilder putByteArray(String str, byte[] bArr) {
        this.mBundle.putByteArray(str, bArr);
        return this;
    }

    public BundleBuilder putShortArray(String str, short[] sArr) {
        this.mBundle.putShortArray(str, sArr);
        return this;
    }

    public BundleBuilder putCharArray(String str, char[] cArr) {
        this.mBundle.putCharArray(str, cArr);
        return this;
    }

    public BundleBuilder putFloatArray(String str, float[] fArr) {
        this.mBundle.putFloatArray(str, fArr);
        return this;
    }

    public BundleBuilder putCharSequenceArray(String str, CharSequence[] charSequenceArr) {
        this.mBundle.putCharSequenceArray(str, charSequenceArr);
        return this;
    }

    public BundleBuilder putBundle(String str, Bundle bundle) {
        this.mBundle.putBundle(str, bundle);
        return this;
    }

    public BundleBuilder putBinder(String str, IBinder iBinder) {
        this.mBundle.putBinder(str, iBinder);
        return this;
    }
}
