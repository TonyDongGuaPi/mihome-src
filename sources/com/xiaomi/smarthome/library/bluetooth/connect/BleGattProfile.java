package com.xiaomi.smarthome.library.bluetooth.connect;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class BleGattProfile implements Parcelable {
    public static final Parcelable.Creator<BleGattProfile> CREATOR = new Parcelable.Creator<BleGattProfile>() {
        /* renamed from: a */
        public BleGattProfile createFromParcel(Parcel parcel) {
            return new BleGattProfile(parcel);
        }

        /* renamed from: a */
        public BleGattProfile[] newArray(int i) {
            return new BleGattProfile[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private List<BleGattService> f18513a = new ArrayList();

    public int describeContents() {
        return 0;
    }

    public BleGattProfile(Map<UUID, Map<UUID, BluetoothGattCharacteristic>> map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : map.entrySet()) {
            Map map2 = (Map) next.getValue();
            BleGattService bleGattService = new BleGattService((UUID) next.getKey());
            if (!arrayList.contains(bleGattService)) {
                arrayList.add(bleGattService);
                bleGattService.a((Set<UUID>) map2.keySet());
            }
        }
        a((List<BleGattService>) arrayList);
    }

    public BleGattProfile(Parcel parcel) {
        parcel.readTypedList(a(), BleGattService.CREATOR);
    }

    public void a(List<BleGattService> list) {
        Collections.sort(list);
        a().addAll(list);
    }

    public List<BleGattService> a() {
        if (this.f18513a == null) {
            this.f18513a = new ArrayList();
        }
        return this.f18513a;
    }

    public BleGattService a(UUID uuid) {
        for (BleGattService next : a()) {
            if (next.a().equals(uuid)) {
                return next;
            }
        }
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(a());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (BleGattService append : a()) {
            sb.append(append);
            sb.append("\n");
        }
        return sb.toString();
    }
}
