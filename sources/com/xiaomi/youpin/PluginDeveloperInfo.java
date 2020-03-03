package com.xiaomi.youpin;

import android.os.Parcel;
import android.os.Parcelable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "plug_developerinfo")
public class PluginDeveloperInfo implements Parcelable {
    public static final Parcelable.Creator<PluginDeveloperInfo> CREATOR = new Parcelable.Creator<PluginDeveloperInfo>() {
        /* renamed from: a */
        public PluginDeveloperInfo createFromParcel(Parcel parcel) {
            return new PluginDeveloperInfo(parcel);
        }

        /* renamed from: a */
        public PluginDeveloperInfo[] newArray(int i) {
            return new PluginDeveloperInfo[i];
        }
    };
    @DatabaseField(id = true)

    /* renamed from: a  reason: collision with root package name */
    public String f1575a;
    @DatabaseField(columnName = "key")
    public String b;

    public int describeContents() {
        return 0;
    }

    public PluginDeveloperInfo() {
    }

    protected PluginDeveloperInfo(Parcel parcel) {
        this.f1575a = parcel.readString();
        this.b = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1575a);
        parcel.writeString(this.b);
    }
}
