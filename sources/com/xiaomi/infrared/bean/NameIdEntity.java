package com.xiaomi.infrared.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.infrared.utils.CommUtil;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class NameIdEntity implements Parcelable {
    public static final Parcelable.Creator<NameIdEntity> CREATOR = new Parcelable.Creator<NameIdEntity>() {
        /* renamed from: a */
        public NameIdEntity createFromParcel(Parcel parcel) {
            return new NameIdEntity(parcel);
        }

        /* renamed from: a */
        public NameIdEntity[] newArray(int i) {
            return new NameIdEntity[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f10238a;
    private String b;

    public int describeContents() {
        return 0;
    }

    public NameIdEntity() {
    }

    protected NameIdEntity(Parcel parcel) {
        this.f10238a = parcel.readString();
        this.b = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f10238a);
        parcel.writeString(this.b);
    }

    public void a(String str) {
        this.f10238a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public String a() {
        return this.f10238a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return b();
    }

    public static ArrayList<NameIdEntity> a(JSONArray jSONArray) {
        ArrayList<NameIdEntity> arrayList = new ArrayList<>();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                NameIdEntity nameIdEntity = new NameIdEntity();
                arrayList.add(nameIdEntity);
                nameIdEntity.f10238a = CommUtil.a(optJSONObject, "id");
                nameIdEntity.b = CommUtil.a(optJSONObject, "name");
                i++;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return arrayList;
    }
}
