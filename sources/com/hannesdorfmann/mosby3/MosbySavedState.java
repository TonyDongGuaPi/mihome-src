package com.hannesdorfmann.mosby3;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;

public class MosbySavedState extends AbsSavedState {
    public static final Parcelable.Creator<MosbySavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks<MosbySavedState>() {
        /* renamed from: a */
        public MosbySavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (classLoader == null) {
                classLoader = MosbySavedState.class.getClassLoader();
            }
            return new MosbySavedState(parcel, classLoader);
        }

        /* renamed from: a */
        public MosbySavedState[] newArray(int i) {
            return new MosbySavedState[i];
        }
    });

    /* renamed from: a  reason: collision with root package name */
    private String f5744a;

    public MosbySavedState(Parcelable parcelable, String str) {
        super(parcelable);
        this.f5744a = str;
    }

    protected MosbySavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f5744a = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f5744a);
    }

    public String a() {
        return this.f5744a;
    }
}
