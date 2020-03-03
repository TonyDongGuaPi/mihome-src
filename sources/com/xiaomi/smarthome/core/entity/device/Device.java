package com.xiaomi.smarthome.core.entity.device;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.bluetooth.XmBluetoothRecord;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.family.FamilyMemberData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Device implements Parcelable {
    public static final Parcelable.Creator<Device> CREATOR = new Parcelable.Creator<Device>() {
        /* renamed from: a */
        public Device createFromParcel(Parcel parcel) {
            return new Device(parcel);
        }

        /* renamed from: a */
        public Device[] newArray(int i) {
            return new Device[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private static int f13972a = 16;
    private static int b = 8;
    private static int c = 4;
    private static int d = 65296;
    private static int e = 30;
    private String A;
    private String B;
    private String C;
    private String D;
    private Location E;
    private String F;
    private int G;
    private boolean H;
    private boolean I;
    private String J;
    private String K;
    private String L;
    private String M;
    private byte N;
    private String f;
    private String g;
    private String h;
    private String i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private int n;
    private String o;
    private String p;
    private double q;
    private double r;
    private String s;
    private String t;
    private int u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    public int describeContents() {
        return 0;
    }

    protected Device() {
        this.E = Location.REMOTE;
        this.H = false;
        this.I = true;
        this.L = "";
    }

    protected Device(String str, String str2) {
        this.E = Location.REMOTE;
        this.H = false;
        this.I = true;
        this.L = "";
        this.f = str;
        this.g = str2;
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.f = jSONObject.optString("did");
            this.g = jSONObject.optString("model");
            this.h = jSONObject.optString("name");
            this.i = jSONObject.optString("mac");
            this.j = jSONObject.optBoolean("isOnline");
            this.k = jSONObject.optInt("pid");
            this.l = jSONObject.optInt("permitLevel");
            this.m = jSONObject.optInt("resetFlag");
            this.n = jSONObject.optInt("rssi", 0);
            this.o = jSONObject.optString("token");
            this.p = jSONObject.optString("localip");
            this.q = jSONObject.optDouble("longitude");
            this.r = jSONObject.optDouble("latitude");
            this.s = jSONObject.optString(DeviceTagInterface.e);
            this.t = jSONObject.optString("bssid");
            this.u = jSONObject.optInt("show_mode");
            this.v = jSONObject.optString("desc");
            this.w = jSONObject.optString("parent_id");
            this.x = jSONObject.optString("parent_model");
            JSONObject optJSONObject = jSONObject.optJSONObject("owner");
            if (optJSONObject != null) {
                this.y = optJSONObject.optString(FamilyMemberData.d);
                this.z = optJSONObject.optString("userid");
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject(XmBluetoothRecord.TYPE_PROP);
            if (optJSONObject2 != null) {
                this.A = optJSONObject2.toString();
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("method");
            if (optJSONArray != null) {
                this.B = optJSONArray.toString();
            }
            JSONObject optJSONObject3 = jSONObject.optJSONObject("extra");
            if (optJSONObject3 != null) {
                this.C = optJSONObject3.toString();
                this.G = optJSONObject3.optInt("isSetPincode");
                this.L = optJSONObject3.optString("keyid");
                this.F = optJSONObject3.optString("fw_version");
            }
            JSONObject optJSONObject4 = jSONObject.optJSONObject("event");
            if (optJSONObject4 != null) {
                this.D = optJSONObject4.toString();
            }
            this.J = jSONObject.optString("desc_new");
            this.M = jSONObject.optString("spec_type");
            this.N = (byte) jSONObject.optInt("voice_ctrl");
            JSONArray optJSONArray2 = jSONObject.optJSONArray("desc_time");
            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                this.K = optJSONArray2.toString();
            }
        }
    }

    public JSONObject h() {
        return new JSONObject();
    }

    public void i() {
        if (!TextUtils.isEmpty(this.C)) {
            try {
                JSONObject jSONObject = new JSONObject(this.C);
                this.G = jSONObject.optInt("isSetPincode");
                this.L = jSONObject.optString("keyid");
                this.F = jSONObject.optString("fw_version");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public String j() {
        return this.C;
    }

    public synchronized String k() {
        return this.f;
    }

    public synchronized void a(String str) {
        this.f = str;
    }

    public synchronized String l() {
        return this.g;
    }

    public synchronized void b(String str) {
        this.g = str;
    }

    public synchronized String m() {
        return this.h;
    }

    public synchronized void c(String str) {
        this.h = str;
    }

    public synchronized String n() {
        return this.i;
    }

    public synchronized void d(String str) {
        this.i = str;
    }

    public synchronized boolean o() {
        return this.j;
    }

    public synchronized void a(boolean z2) {
        this.j = z2;
    }

    public synchronized int f() {
        return this.k;
    }

    public synchronized void c(int i2) {
        this.k = i2;
    }

    public synchronized int p() {
        return this.l;
    }

    public synchronized void d(int i2) {
        this.l = i2;
    }

    public synchronized int q() {
        return this.m;
    }

    public synchronized void e(int i2) {
        this.m = i2;
    }

    public synchronized int r() {
        return this.n;
    }

    public synchronized void f(int i2) {
        this.n = i2;
    }

    public synchronized String s() {
        return this.o;
    }

    public synchronized void e(String str) {
        this.o = str;
    }

    public synchronized String t() {
        return this.p;
    }

    public synchronized void f(String str) {
        this.p = str;
    }

    public synchronized double u() {
        return this.q;
    }

    public synchronized void a(double d2) {
        this.q = d2;
    }

    public synchronized double v() {
        return this.r;
    }

    public synchronized void b(double d2) {
        this.r = d2;
    }

    public synchronized String w() {
        return this.s;
    }

    public synchronized void g(String str) {
        this.s = str;
    }

    public synchronized String x() {
        return this.t;
    }

    public synchronized void h(String str) {
        this.t = str;
    }

    public synchronized int y() {
        return this.u;
    }

    public synchronized void g(int i2) {
        this.u = i2;
    }

    public synchronized String z() {
        return this.v;
    }

    public synchronized void i(String str) {
        this.v = str;
    }

    public synchronized String A() {
        return this.w;
    }

    public synchronized void j(String str) {
        this.w = str;
    }

    public synchronized String B() {
        return this.x;
    }

    public synchronized void k(String str) {
        this.x = str;
    }

    public synchronized String C() {
        return this.y;
    }

    public synchronized void l(String str) {
        this.y = str;
    }

    public synchronized String D() {
        return this.z;
    }

    public synchronized void m(String str) {
        this.z = str;
    }

    public synchronized String E() {
        return this.A;
    }

    public synchronized void n(String str) {
        this.A = str;
    }

    public synchronized String F() {
        return this.B;
    }

    public synchronized void o(String str) {
        this.B = str;
    }

    public synchronized String G() {
        return this.C;
    }

    public synchronized void p(String str) {
        this.C = str;
    }

    public synchronized String H() {
        return this.D;
    }

    public synchronized void q(String str) {
        this.D = str;
    }

    public synchronized Location I() {
        return this.E;
    }

    public synchronized void a(Location location) {
        this.E = location;
    }

    public synchronized String J() {
        return this.F;
    }

    public synchronized void r(String str) {
        this.F = str;
    }

    public synchronized boolean K() {
        return this.H;
    }

    public synchronized void b(boolean z2) {
        this.H = z2;
    }

    public synchronized boolean L() {
        return this.I;
    }

    public synchronized void c(boolean z2) {
        this.I = z2;
    }

    public synchronized void h(int i2) {
        this.G = i2;
    }

    public synchronized int M() {
        return this.G;
    }

    public String N() {
        return this.K;
    }

    public void s(String str) {
        this.K = str;
    }

    public synchronized String O() {
        return this.J;
    }

    public synchronized void t(String str) {
        this.J = str;
    }

    public synchronized String P() {
        return this.M;
    }

    public void u(String str) {
        this.M = str;
    }

    public byte Q() {
        return this.N;
    }

    public void a(byte b2) {
        this.N = b2;
    }

    public synchronized String R() {
        return this.L;
    }

    public synchronized void v(String str) {
        this.L = str;
    }

    public synchronized boolean S() {
        return ((this.l & e) & f13972a) != 0;
    }

    public synchronized void d(boolean z2) {
        this.l &= e ^ -1;
        if (z2) {
            this.l |= f13972a;
        } else {
            this.l &= f13972a ^ -1;
        }
    }

    public boolean T() {
        return (this.l & e) != 0;
    }

    public void U() {
        this.l &= e ^ -1;
    }

    public boolean V() {
        return this.I && ((this.l & e) & c) != 0 && !TextUtils.isEmpty(this.y);
    }

    public void e(boolean z2) {
        this.l &= e ^ -1;
        if (!this.I || !z2 || TextUtils.isEmpty(this.y)) {
            this.l &= c ^ -1;
        } else {
            this.l |= c;
        }
    }

    public boolean W() {
        return ((this.l & e) & b) != 0;
    }

    public boolean X() {
        return !TextUtils.isEmpty(this.w);
    }

    public boolean Y() {
        return this.E == Location.LOCAL;
    }

    public boolean a(Device device) {
        if (device != null) {
            return TextUtils.equals(k(), device.k());
        }
        return false;
    }

    public String toString() {
        return "Device{mDid='" + this.f + Operators.SINGLE_QUOTE + ", mModel='" + this.g + Operators.SINGLE_QUOTE + ", mName='" + this.h + Operators.SINGLE_QUOTE + ", mMac='" + this.i + Operators.SINGLE_QUOTE + ", mIsOnline=" + this.j + ", mPid=" + this.k + ", mPermitLevel=" + this.l + ", mResetFlag=" + this.m + ", mRssi=" + this.n + ", mToken='" + this.o + Operators.SINGLE_QUOTE + ", mLocalIP='" + this.p + Operators.SINGLE_QUOTE + ", mLongitude=" + this.q + ", mLatitude=" + this.r + ", mSsid='" + this.s + Operators.SINGLE_QUOTE + ", mBssid='" + this.t + Operators.SINGLE_QUOTE + ", mShowMode=" + this.u + ", mDesc='" + this.v + Operators.SINGLE_QUOTE + ", mParentId='" + this.w + Operators.SINGLE_QUOTE + ", mParentModel='" + this.x + Operators.SINGLE_QUOTE + ", mOwnerName='" + this.y + Operators.SINGLE_QUOTE + ", mOwnerId='" + this.z + Operators.SINGLE_QUOTE + ", mPropInfo='" + this.A + Operators.SINGLE_QUOTE + ", mMethodInfo='" + this.B + Operators.SINGLE_QUOTE + ", mExtraInfo='" + this.C + Operators.SINGLE_QUOTE + ", mEventInfo='" + this.D + Operators.SINGLE_QUOTE + ", mLocation=" + this.E + ", mVersion='" + this.F + Operators.SINGLE_QUOTE + ", mIsSetPinCode=" + this.G + ", mCanUseNotBind=" + this.H + ", mCanAuth=" + this.I + ", mDescNew='" + this.J + Operators.SINGLE_QUOTE + ", mDescTimeJString='" + this.K + Operators.SINGLE_QUOTE + ", mKeyId='" + this.L + Operators.SINGLE_QUOTE + ", mSpecUrn='" + this.M + Operators.SINGLE_QUOTE + ", mVoiceCtrl='" + this.N + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeByte(this.j ? (byte) 1 : 0);
        parcel.writeInt(this.k);
        parcel.writeInt(this.l);
        parcel.writeInt(this.m);
        parcel.writeInt(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeDouble(this.q);
        parcel.writeDouble(this.r);
        parcel.writeString(this.s);
        parcel.writeString(this.t);
        parcel.writeInt(this.u);
        parcel.writeString(this.v);
        parcel.writeString(this.w);
        parcel.writeString(this.x);
        parcel.writeString(this.y);
        parcel.writeString(this.z);
        parcel.writeString(this.A);
        parcel.writeString(this.B);
        parcel.writeString(this.C);
        parcel.writeString(this.D);
        parcel.writeInt(this.E == null ? -1 : this.E.ordinal());
        parcel.writeString(this.F);
        parcel.writeInt(this.G);
        parcel.writeByte(this.H ? (byte) 1 : 0);
        parcel.writeByte(this.I ? (byte) 1 : 0);
        parcel.writeString(this.J);
        parcel.writeString(this.K);
        parcel.writeString(this.L);
        parcel.writeString(this.M);
        parcel.writeByte(this.N);
    }

    protected Device(Parcel parcel) {
        Location location;
        this.E = Location.REMOTE;
        boolean z2 = false;
        this.H = false;
        this.I = true;
        this.L = "";
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readByte() != 0;
        this.k = parcel.readInt();
        this.l = parcel.readInt();
        this.m = parcel.readInt();
        this.n = parcel.readInt();
        this.o = parcel.readString();
        this.p = parcel.readString();
        this.q = parcel.readDouble();
        this.r = parcel.readDouble();
        this.s = parcel.readString();
        this.t = parcel.readString();
        this.u = parcel.readInt();
        this.v = parcel.readString();
        this.w = parcel.readString();
        this.x = parcel.readString();
        this.y = parcel.readString();
        this.z = parcel.readString();
        this.A = parcel.readString();
        this.B = parcel.readString();
        this.C = parcel.readString();
        this.D = parcel.readString();
        int readInt = parcel.readInt();
        if (readInt == -1) {
            location = null;
        } else {
            location = Location.values()[readInt];
        }
        this.E = location;
        this.F = parcel.readString();
        this.G = parcel.readInt();
        this.H = parcel.readByte() != 0;
        this.I = parcel.readByte() != 0 ? true : z2;
        this.J = parcel.readString();
        this.K = parcel.readString();
        this.L = parcel.readString();
        this.M = parcel.readString();
        this.N = parcel.readByte();
    }

    public void b(Device device) {
        this.f = device.f;
        this.g = device.g;
        this.h = device.h;
        this.i = device.i;
        this.j = device.j;
        this.k = device.k;
        this.l = device.l;
        this.m = device.m;
        this.n = device.n;
        this.o = device.o;
        this.p = device.p;
        this.q = device.q;
        this.r = device.r;
        this.s = device.s;
        this.t = device.t;
        this.u = device.u;
        this.v = device.v;
        this.w = device.w;
        this.x = device.x;
        this.y = device.y;
        this.z = device.z;
        this.A = device.A;
        this.B = device.B;
        this.C = device.C;
        this.D = device.D;
        this.E = device.E;
        this.F = device.F;
        this.G = device.G;
        this.H = device.H;
        this.I = device.I;
        this.J = device.J;
        this.K = device.K;
        this.L = device.L;
        this.M = device.M;
        this.N = device.N;
    }
}
