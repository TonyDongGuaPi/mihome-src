package com.xiaomi.smarthome.library.bluetooth.connect;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BleGattService implements Parcelable, Comparable {
    public static final Parcelable.Creator<BleGattService> CREATOR = new Parcelable.Creator<BleGattService>() {
        /* renamed from: a */
        public BleGattService createFromParcel(Parcel parcel) {
            return new BleGattService(parcel);
        }

        /* renamed from: a */
        public BleGattService[] newArray(int i) {
            return new BleGattService[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private UUID f18514a;
    private List<ParcelUuid> b;

    public int describeContents() {
        return 0;
    }

    public BleGattService(UUID uuid) {
        this.f18514a = uuid;
        this.b = new ArrayList();
    }

    public void a(Set<UUID> set) {
        for (UUID parcelUuid : set) {
            b().add(new ParcelUuid(parcelUuid));
        }
    }

    protected BleGattService(Parcel parcel) {
        this.f18514a = (UUID) parcel.readSerializable();
        parcel.readTypedList(b(), ParcelUuid.CREATOR);
    }

    public UUID a() {
        return this.f18514a;
    }

    public List<ParcelUuid> b() {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        return this.b;
    }

    public boolean a(UUID uuid) {
        for (ParcelUuid uuid2 : b()) {
            if (uuid2.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.f18514a);
        parcel.writeTypedList(b());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Service: %s\n", new Object[]{this.f18514a}));
        List<ParcelUuid> b2 = b();
        int size = b2.size();
        for (int i = 0; i < size; i++) {
            sb.append(String.format(">>> Character: %s", new Object[]{b2.get(i)}));
            if (i != size - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public int compareTo(Object obj) {
        if (obj == null) {
            return 1;
        }
        return this.f18514a.compareTo(((BleGattService) obj).f18514a);
    }
}
