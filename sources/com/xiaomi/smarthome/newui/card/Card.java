package com.xiaomi.smarthome.newui.card;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Pair;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.newui.card.Card.CardType;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.carditem.NotSupportCardItem;
import com.xiaomi.smarthome.newui.card.carditem.Type18CardItem;
import com.xiaomi.smarthome.newui.card.profile.PropItem;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Card<T extends CardType> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20465a = "mijia-card";
    public int b = -1;
    public final List<T> c = new ArrayList();

    public abstract List<T> a();

    public abstract List<T> b();

    public ArrayList<CardItem> a(Device device) {
        ArrayList<CardItem> arrayList = new ArrayList<>();
        for (T t : this.c) {
            arrayList.add(t.a(new String[]{t.c}));
        }
        return arrayList;
    }

    public static abstract class CardType<T> {

        /* renamed from: a  reason: collision with root package name */
        public double f20466a;
        public int b;
        public String c;
        public final List<Operation> d = new ArrayList();
        public PropItem e;
        public String f;
        public double g;
        public String h;

        public abstract Drawable a(Device device, T t, Object obj, CardItem.State state);

        public abstract String a(Device device, T t);

        public abstract String a(Device device, T t, Object obj);

        public abstract String a(T t, Object obj, double d2);

        public abstract boolean a(Device device, Operation operation);

        public abstract Object b(Device device, T t);

        public abstract Object c(Device device, T t);

        public abstract Pair<Object, Long> d(Device device, T t);

        public abstract double e(Device device, T t);

        public abstract String f(Device device, T t);

        public abstract long g(Device device, T t);

        public CardType(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.b = jSONObject.optInt("cardType");
                this.c = jSONObject.optString("prop_key");
                this.f20466a = jSONObject.optDouble("param_delta", 0.0d);
                this.f = jSONObject.optString("small_image");
                JSONArray optJSONArray = jSONObject.optJSONArray("param_type");
                JSONArray optJSONArray2 = jSONObject.optJSONArray(HomeManager.v);
                if (optJSONArray2 != null) {
                    for (int i = 0; i < optJSONArray2.length(); i++) {
                        this.d.add(new Operation(optJSONArray2.optJSONObject(i), optJSONArray));
                    }
                }
            }
        }

        public PropItem a() {
            return this.e;
        }

        public CardItem a(T[] tArr) {
            int i = this.b;
            if (i == 0) {
                return new NotSupportCardItem(this, tArr);
            }
            if (i != 18) {
                return null;
            }
            return new Type18CardItem(this, tArr);
        }

        @NonNull
        public String toString() {
            return "CardType{cardItemType=" + this.b + ", propKey='" + this.c + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }

        public boolean b() {
            return this.b == 8 || this.b == 7 || this.b == 18 || this.b == 9;
        }

        public boolean c() {
            return this.b == 1 || this.b == 2;
        }

        public boolean d() {
            return this.b == 3 || this.b == 11 || this.b == 5 || this.b == 4 || this.b == 12;
        }

        public boolean e() {
            return this.b == 7 || this.b == 17;
        }

        public boolean f() {
            return this.b == 1 || this.b == 2 || this.b == 3;
        }

        public boolean g() {
            return this.b == 14;
        }

        public boolean h() {
            return this.b == 2;
        }
    }
}
