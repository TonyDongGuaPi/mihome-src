package com.xiaomi.smarthome.newui.mainpage.devicepage.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.MainPageOpManager;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.profile.ProfileCard;
import java.util.ArrayList;
import java.util.List;

public class MainPageDeviceModel implements Parcelable {
    public static final Parcelable.Creator<MainPageDeviceModel> CREATOR = new Parcelable.Creator<MainPageDeviceModel>() {
        /* renamed from: a */
        public MainPageDeviceModel createFromParcel(Parcel parcel) {
            return new MainPageDeviceModel(parcel);
        }

        /* renamed from: a */
        public MainPageDeviceModel[] newArray(int i) {
            return new MainPageDeviceModel[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private boolean f20689a;
    private String b;
    private String c;
    private String d;
    private boolean e;
    private String f;
    private String g;
    private String h;
    private Pair<CardItem.State, String> i;
    private String j;
    private String k;
    private String l;
    private String m;

    public int describeContents() {
        return 0;
    }

    public static MainPageDeviceModel a(Device device) {
        Pair pair;
        Pair pair2;
        boolean z;
        Pair<CardItem.State, String> pair3;
        MainPageDeviceModel mainPageDeviceModel = new MainPageDeviceModel();
        if (device == null) {
            return mainPageDeviceModel;
        }
        if (IRDeviceUtil.a(device.did)) {
            mainPageDeviceModel.f20689a = true;
        }
        mainPageDeviceModel.b = device.model;
        mainPageDeviceModel.c = device.did;
        mainPageDeviceModel.d = device.name;
        mainPageDeviceModel.g = DeviceFactory.p(device.model);
        Card<? extends Card.CardType> a2 = MainPageOpManager.a().a(device);
        mainPageDeviceModel.e = device.isOnlineAdvance() || (device instanceof BleDevice) || (a2 != null && (a2 instanceof ProfileCard) && ((ProfileCard) a2).g);
        mainPageDeviceModel.f = HomeManager.a().r(device.did);
        ArrayList<Pair> c2 = MainPageOpManager.a().c(device);
        if (!(a2 == null || c2 == null || c2.size() <= 0)) {
            List<? extends Card.CardType> a3 = a2.a();
            if (a3 != null && a3.size() == 1) {
                Card.CardType cardType = (Card.CardType) a3.get(0);
                if (cardType.b == 1 || cardType.b == 2) {
                    z = true;
                    if (mainPageDeviceModel.e && z && (pair3 = c2.get(0)) != null && (pair3.first instanceof CardItem.State) && !device.isSharedReadOnly()) {
                        mainPageDeviceModel.h = String.valueOf(c2.get(0).second);
                        mainPageDeviceModel.i = pair3;
                    }
                }
            }
            z = false;
            mainPageDeviceModel.h = String.valueOf(c2.get(0).second);
            mainPageDeviceModel.i = pair3;
        }
        if (c2 != null) {
            if (!(c2.size() <= 0 || (pair2 = c2.get(0)) == null || pair2.second == null || pair2.first == null || !(pair2.first instanceof String))) {
                mainPageDeviceModel.k = String.valueOf(pair2.second).trim();
                mainPageDeviceModel.j = String.valueOf(pair2.first).trim();
            }
            if (!(c2.size() <= 1 || (pair = c2.get(1)) == null || pair.second == null || pair.first == null || !(pair.first instanceof String))) {
                mainPageDeviceModel.m = String.valueOf(pair.second).trim();
                mainPageDeviceModel.l = String.valueOf(pair.first).trim();
            }
        }
        return mainPageDeviceModel;
    }

    public String a() {
        return this.g;
    }

    public void a(String str) {
        this.g = str;
    }

    public boolean b() {
        return this.f20689a;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.c;
    }

    public String e() {
        return this.d;
    }

    public boolean f() {
        return this.e;
    }

    public String g() {
        return this.f;
    }

    public Pair<CardItem.State, String> h() {
        return this.i;
    }

    public String i() {
        return this.j;
    }

    public String j() {
        return this.k;
    }

    public String k() {
        return this.l;
    }

    public String l() {
        return this.m;
    }

    public MainPageDeviceModel() {
    }

    public String m() {
        return this.h;
    }

    public void b(String str) {
        this.h = str;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeByte(this.f20689a ? (byte) 1 : 0);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeByte(this.e ? (byte) 1 : 0);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeString(this.m);
    }

    protected MainPageDeviceModel(Parcel parcel) {
        boolean z = false;
        this.f20689a = parcel.readByte() != 0;
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readByte() != 0 ? true : z;
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.m = parcel.readString();
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("MainPageDeviceModel{isIRDevice=");
        sb.append(this.f20689a);
        sb.append(", model='");
        sb.append(this.b);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", did='");
        sb.append(this.c);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", deviceName='");
        sb.append(this.d);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", isOnline=");
        sb.append(this.e);
        sb.append(", roomName='");
        sb.append(this.f);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", iconUrl='");
        sb.append(this.g);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", switchIconUrl='");
        sb.append(this.h);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", deviceStatus=");
        if (this.i == null) {
            str = null;
        } else {
            str = this.i.first + ":" + ((String) this.i.second);
        }
        sb.append(str);
        sb.append(", propName1='");
        sb.append(this.j);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", propValue1='");
        sb.append(this.k);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", propName2='");
        sb.append(this.l);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", propValue2='");
        sb.append(this.m);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }
}
