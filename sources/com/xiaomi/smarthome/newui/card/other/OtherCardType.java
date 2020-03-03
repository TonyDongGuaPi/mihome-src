package com.xiaomi.smarthome.newui.card.other;

import android.graphics.drawable.Drawable;
import android.util.Pair;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.Operation;
import com.xiaomi.smarthome.newui.card.profile.IRCardItem2;
import com.xiaomi.smarthome.newui.card.profile.MiTvCardItem;
import com.xiaomi.smarthome.newui.card.profile.PropItem;
import org.json.JSONObject;

public class OtherCardType extends Card.CardType<Object> {
    private CardItem i;

    public Drawable a(Device device, Object obj, Object obj2, CardItem.State state) {
        return null;
    }

    public PropItem a() {
        return null;
    }

    public String a(Device device, Object obj) {
        return null;
    }

    public String a(Device device, Object obj, Object obj2) {
        return null;
    }

    public String a(Object obj, Object obj2, double d) {
        return null;
    }

    public boolean a(Device device, Operation operation) {
        return true;
    }

    public Object b(Device device, Object obj) {
        return null;
    }

    public Object c(Device device, Object obj) {
        return null;
    }

    public Pair<Object, Long> d(Device device, Object obj) {
        return null;
    }

    public double e(Device device, Object obj) {
        return 1.0d;
    }

    public String f(Device device, Object obj) {
        return "";
    }

    public long g(Device device, Object obj) {
        return 0;
    }

    private OtherCardType() {
        super((JSONObject) null);
    }

    public static OtherCardType i() {
        OtherCardType otherCardType = new OtherCardType();
        otherCardType.i = new IRCardItem2(otherCardType);
        return otherCardType;
    }

    public static OtherCardType j() {
        OtherCardType otherCardType = new OtherCardType();
        otherCardType.i = new MiTvCardItem(otherCardType);
        return otherCardType;
    }

    public CardItem a(Object[] objArr) {
        return this.i;
    }
}
