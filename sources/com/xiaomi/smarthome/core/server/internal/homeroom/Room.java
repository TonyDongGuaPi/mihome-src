package com.xiaomi.smarthome.core.server.internal.homeroom;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.internal.homeroom.Home;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Room implements Parcelable {
    public static final Parcelable.Creator<Room> CREATOR = new Parcelable.Creator<Room>() {
        /* renamed from: a */
        public Room createFromParcel(Parcel parcel) {
            return new Room(parcel);
        }

        /* renamed from: a */
        public Room[] newArray(int i) {
            return new Room[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f14583a;
    private String b;
    private String c;
    private String d;
    private String e;
    private int f;
    private volatile List<String> g;
    private String h;

    public int describeContents() {
        return 0;
    }

    public Room() {
    }

    public Room(String str) {
        this.d = str;
    }

    public void a(Room room) {
        this.f14583a = room.f14583a;
        this.b = room.b;
        this.d = room.d;
        this.e = room.e;
        this.f = room.f;
        this.g = room.g;
        this.h = room.h;
    }

    public String a() {
        return this.h;
    }

    public void a(String str) {
        this.h = str;
    }

    public String b() {
        return this.f14583a;
    }

    public void b(String str) {
        this.f14583a = str;
    }

    public String c() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }

    public String d() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public String e() {
        return this.d;
    }

    public void e(String str) {
        this.d = str;
    }

    public String f() {
        return this.e;
    }

    public void f(String str) {
        this.e = str;
    }

    public int g() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public List<String> h() {
        if (this.g == null) {
            this.g = new ArrayList();
        }
        return this.g;
    }

    public void a(List<String> list) {
        this.g = list;
    }

    public JSONObject i() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("bssid", this.f14583a);
            jSONObject.put("desc", this.b);
            jSONObject.put("id", this.c);
            jSONObject.put("name", this.d);
            jSONObject.put("parentid", this.e);
            jSONObject.put("shareflag", this.f);
            jSONObject.put("icon", this.h);
            if (this.g != null) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < this.g.size(); i++) {
                    jSONArray.put(this.g.get(i));
                }
                jSONObject.put("dids", jSONArray);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static Room a(JSONObject jSONObject) {
        JSONArray optJSONArray;
        Room room = new Room();
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull("bssid")) {
                    room.f14583a = jSONObject.optString("bssid");
                }
                if (!jSONObject.isNull("desc")) {
                    room.b = jSONObject.optString("desc");
                }
                if (!jSONObject.isNull("id")) {
                    room.c = jSONObject.optString("id");
                }
                if (!jSONObject.isNull("name")) {
                    room.d = jSONObject.optString("name");
                }
                if (!jSONObject.isNull("parentid")) {
                    room.e = jSONObject.optString("parentid");
                }
                if (!jSONObject.isNull("shareflag")) {
                    room.f = jSONObject.optInt("shareflag");
                }
                if (!jSONObject.isNull("icon")) {
                    room.h = jSONObject.optString("icon");
                }
                if (!jSONObject.isNull("dids") && (optJSONArray = jSONObject.optJSONArray("dids")) != null) {
                    room.g = new Home.Builder.UnduplicateList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        if (!TextUtils.isEmpty(optJSONArray.optString(i))) {
                            room.g.add(optJSONArray.optString(i));
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return room;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f14583a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
        parcel.writeStringList(this.g);
        parcel.writeString(this.h);
    }

    protected Room(Parcel parcel) {
        this.f14583a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readInt();
        this.g = parcel.createStringArrayList();
        this.h = parcel.readString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Room room = (Room) obj;
        if (this.c != null) {
            return this.c.equals(room.c);
        }
        if (room.c == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.c != null) {
            return this.c.hashCode();
        }
        return 0;
    }

    public void b(Room room) {
        c(room);
    }

    private void c(Room room) {
        if (room != null && room.g != null && !room.g.isEmpty()) {
            if (this.g == null) {
                this.g = room.g;
            } else {
                this.g.addAll(room.g);
            }
        }
    }
}
