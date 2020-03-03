package com.xiaomi.smarthome.miio.message.p0.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import org.json.JSONObject;

public class P0Message implements Parcelable {
    public static final Parcelable.Creator<P0Message> CREATOR = new Parcelable.Creator<P0Message>() {
        /* renamed from: a */
        public P0Message createFromParcel(Parcel parcel) {
            return new P0Message(parcel);
        }

        /* renamed from: a */
        public P0Message[] newArray(int i) {
            return new P0Message[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f13621a;
    private String b;
    private String c;
    private String d;
    private long e;
    private MessageRecord f;

    public int describeContents() {
        return 0;
    }

    public static P0Message a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        P0Message p0Message = new P0Message();
        try {
            p0Message.a(jSONObject.optString("did"));
            p0Message.b(jSONObject.optString("homeId"));
            p0Message.c(jSONObject.optString("roomId"));
            p0Message.d(jSONObject.optString("title"));
            p0Message.a(jSONObject.optLong("timestamp"));
            if (!jSONObject.isNull("message")) {
                p0Message.f = new MessageRecord();
                MessageRecord.parse(jSONObject.optJSONObject("message"), p0Message.f);
            }
            return p0Message;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public String a() {
        return this.f13621a;
    }

    public void a(String str) {
        this.f13621a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public String d() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public long e() {
        return this.e * 1000;
    }

    public void a(long j) {
        this.e = j;
    }

    public P0Message() {
    }

    public MessageRecord f() {
        return this.f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f13621a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeLong(this.e);
        parcel.writeParcelable(this.f, i);
    }

    protected P0Message(Parcel parcel) {
        this.f13621a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readLong();
        this.f = (MessageRecord) parcel.readParcelable(MessageRecord.class.getClassLoader());
    }
}
