package com.xiaomi.smarthome.device.api.spec.definitions.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.mi.global.bbs.utils.Constants;
import java.util.ArrayList;
import java.util.List;

public class Access implements Parcelable {
    public static final Parcelable.Creator<Access> CREATOR = new Parcelable.Creator<Access>() {
        public Access createFromParcel(Parcel parcel) {
            return new Access(parcel);
        }

        public Access[] newArray(int i) {
            return new Access[i];
        }
    };
    public static final int NOTIFIABLE = 4;
    public static final int READABLE = 2;
    public static final int WRITABLE = 1;
    private int value;

    public int describeContents() {
        return 0;
    }

    public Access() {
    }

    protected Access(Parcel parcel) {
        this.value = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.value);
    }

    public static Access valueOf(List<String> list) {
        Access access = new Access();
        for (String next : list) {
            char c = 65535;
            int hashCode = next.hashCode();
            if (hashCode != -1039689911) {
                if (hashCode != 3496342) {
                    if (hashCode == 113399775 && next.equals(Constants.TitleMenu.MENU_WRITE)) {
                        c = 1;
                    }
                } else if (next.equals("read")) {
                    c = 0;
                }
            } else if (next.equals("notify")) {
                c = 2;
            }
            switch (c) {
                case 0:
                    access.setReadable(true);
                    break;
                case 1:
                    access.setWritable(true);
                    break;
                case 2:
                    access.setNotifiable(true);
                    break;
            }
        }
        return access;
    }

    public static Access valueOf(int i) {
        Access access = new Access();
        access.value = i;
        return access;
    }

    public boolean isWritable() {
        return (this.value & 1) == 1;
    }

    public boolean isReadable() {
        return (this.value & 2) == 2;
    }

    public boolean isNotifiable() {
        return (this.value & 4) == 4;
    }

    public int setWritable(boolean z) {
        if (z) {
            this.value |= 1;
        } else {
            this.value &= -2;
        }
        return this.value;
    }

    public int setReadable(boolean z) {
        if (z) {
            this.value |= 2;
        } else {
            this.value &= -3;
        }
        return this.value;
    }

    public int setNotifiable(boolean z) {
        if (z) {
            this.value |= 4;
        } else {
            this.value &= -5;
        }
        return this.value;
    }

    public List<String> toList() {
        ArrayList arrayList = new ArrayList();
        if (isReadable()) {
            arrayList.add("read");
        }
        if (isWritable()) {
            arrayList.add(Constants.TitleMenu.MENU_WRITE);
        }
        if (isNotifiable()) {
            arrayList.add("notify");
        }
        return arrayList;
    }

    public int value() {
        return this.value;
    }
}
