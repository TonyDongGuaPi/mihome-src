package com.xiaomi.smarthome.newui.card.profile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.CardItemUtils;
import com.xiaomi.smarthome.newui.card.ControlCardInfoManager;
import com.xiaomi.smarthome.newui.card.Operation;
import com.xiaomi.smarthome.newui.card.Param;
import com.xiaomi.smarthome.newui.card.profile.PropItem;
import com.xiaomi.smarthome.newui.utils.NumberUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class ProfileCardType extends Card.CardType<String> implements Cloneable {
    public String i;
    public String j;
    public long k;
    public long l;
    public String m;
    public String n;
    public int o;
    private double p;
    private String q;

    public ProfileCardType(Map<String, PropItem> map, JSONObject jSONObject) {
        super(jSONObject);
        if (jSONObject != null) {
            this.i = jSONObject.optString("sub_prop_key", (String) null);
            this.j = jSONObject.optString("prop_status_key", (String) null);
            JSONObject optJSONObject = jSONObject.optJSONObject("param_range");
            if (optJSONObject != null) {
                this.k = optJSONObject.optLong("min");
                this.l = optJSONObject.optLong("max");
            } else {
                this.k = 0;
                this.l = 0;
            }
            this.m = jSONObject.optString("start_color");
            this.n = jSONObject.optString("end_color");
            this.o = jSONObject.optInt("supportGrid", 0);
            if (map != null) {
                this.e = map.get(this.c);
                if (!(this.i == null || this.e == null || this.e.b == null)) {
                    this.e = this.e.b.get(this.i);
                }
            }
            if (this.e != null) {
                this.q = this.e.g;
                this.p = this.e.e;
                return;
            }
            this.q = null;
            this.p = 1.0d;
        }
    }

    public static ProfileCardType a(Map<String, PropItem> map, JSONObject jSONObject) {
        return new ProfileCardType(map, jSONObject);
    }

    /* renamed from: a */
    public long g(Device device, String str) {
        return CardItemUtils.a(ControlCardInfoManager.f().d(device.did), str);
    }

    public String a(String str, Object obj, double d) {
        double d2;
        if (obj == null || "null".equals(obj)) {
            return "";
        }
        if (obj instanceof Number) {
            d2 = ((Number) obj).doubleValue() * d;
        } else {
            String valueOf = String.valueOf(obj);
            try {
                d2 = Double.parseDouble(valueOf) * d;
            } catch (NumberFormatException unused) {
                return valueOf;
            }
        }
        if (this.e != null && !TextUtils.isEmpty(this.e.f)) {
            return String.format(this.e.f, new Object[]{Double.valueOf(d2)});
        } else if (d == 1.0d) {
            return String.valueOf(obj);
        } else {
            return String.valueOf(d2);
        }
    }

    /* renamed from: i */
    public ProfileCardType clone() {
        try {
            return (ProfileCardType) super.clone();
        } catch (CloneNotSupportedException unused) {
            ProfileCardType profileCardType = new ProfileCardType((Map<String, PropItem>) null, (JSONObject) null);
            profileCardType.e = this.e;
            profileCardType.c = this.c;
            profileCardType.b = this.b;
            profileCardType.d.addAll(this.d);
            profileCardType.f20466a = this.f20466a;
            profileCardType.i = this.i;
            profileCardType.j = this.j;
            profileCardType.l = this.l;
            profileCardType.k = this.k;
            profileCardType.m = this.m;
            profileCardType.n = this.n;
            profileCardType.f = this.f;
            return profileCardType;
        }
    }

    public CardItem a(String[] strArr) {
        CardItem a2 = super.a(strArr);
        return a2 == null ? ProfileCardItem.a(this) : a2;
    }

    /* renamed from: b */
    public String a(Device device, String str) {
        Map<String, String> map;
        PropItem a2 = a();
        if (a2 == null || (map = a2.c) == null) {
            return "";
        }
        return CardItem.a(map);
    }

    public String a(Device device, String str, Object obj) {
        if (obj == null || obj.toString().equals("null")) {
            return CommonApplication.getAppContext().getString(R.string.card_item_fail);
        }
        PropItem a2 = a();
        if (a2 == null) {
            return String.valueOf(obj);
        }
        List<PropItem.PropExtraItem> list = a2.i;
        if (this.d.size() != 0) {
            Operation operation = null;
            for (Operation operation2 : this.d) {
                if (operation2.a(String.valueOf(obj))) {
                    operation = operation2;
                }
            }
            if (operation == null && this.d.size() > 1) {
                operation = (Operation) this.d.get(1);
            }
            if (operation != null) {
                return CardItem.a(operation.f20520a);
            }
        }
        if (list != null) {
            for (PropItem.PropExtraItem next : list) {
                if (next.f20558a == null) {
                    try {
                        double a3 = NumberUtils.a((Object) String.valueOf(obj), 0.0d);
                        if (a3 >= next.c && a3 <= next.d) {
                            return CardItem.a(next.b);
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else if (String.valueOf(next.f20558a).equals(String.valueOf(obj))) {
                    return CardItem.a(next.b);
                }
            }
        }
        return a(device, str);
    }

    public Object c(Device device, String str) {
        Object d = b(device, str);
        if (d != null && (d instanceof Boolean)) {
            return Boolean.valueOf(!((Boolean) d).booleanValue());
        }
        return null;
    }

    public Drawable a(Device device, String str, Object obj, CardItem.State state) {
        String str2;
        for (Operation operation : this.d) {
            if (operation.a(String.valueOf(obj))) {
                switch (state) {
                    case NOR:
                        str2 = operation.h;
                        break;
                    case SELECTED:
                        str2 = operation.i;
                        break;
                    case UNABLE:
                        str2 = operation.j;
                        break;
                    default:
                        str2 = operation.h;
                        break;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(ControlCardInfoManager.f().d());
                ControlCardInfoManager f = ControlCardInfoManager.f();
                sb.append(f.a(str2 + ".png"));
                Bitmap decodeFile = BitmapFactory.decodeFile(sb.toString());
                if (decodeFile != null) {
                    return new BitmapDrawable(CommonApplication.getAppContext().getResources(), decodeFile);
                }
                if (state != CardItem.State.NOR) {
                    return a(device, str, obj, CardItem.State.NOR);
                }
            }
        }
        return CommonApplication.getAppContext().getResources().getDrawable(R.drawable.card_item_button_default_bg);
    }

    /* renamed from: d */
    public Object b(Device device, String str) {
        Object obj;
        if (device == null) {
            return "";
        }
        boolean z = false;
        if (device instanceof BleDevice) {
            ProfileCard e = ControlCardInfoManager.f().e(device.model);
            if (e == null) {
                return false;
            }
            z = e.f;
        }
        if (z) {
            Map<String, Object> b = ControlCardInfoManager.f().b(device.did);
            if (b == null || CardItem.c(b.get(str)) || (obj = b.get(str)) == null) {
                return null;
            }
            return CardItem.a(obj.toString());
        }
        PropItem a2 = a();
        if (a2 == null) {
            return null;
        }
        if (a2.d.size() > 0) {
            Pair<Object, Long> e2 = d(device, str);
            if (e2 == null) {
                return null;
            }
            return e2.first;
        }
        Map<String, Object> b2 = ControlCardInfoManager.f().b(device.did);
        if (b2 == null || CardItem.c(b2.get(str))) {
            return null;
        }
        Object obj2 = b2.get(str);
        Param param = a2.k;
        if (param == null) {
            return obj2;
        }
        return param.c(obj2);
    }

    /* renamed from: e */
    public Pair<Object, Long> d(Device device, String str) {
        Object obj;
        long j2;
        if (device == null) {
            return null;
        }
        try {
            Map<String, Object> b = ControlCardInfoManager.f().b(device.did);
            if (b == null) {
                return null;
            }
            if (this.e == null || this.e.d == null || this.e.d.size() == 0) {
                j2 = g(device, str);
                obj = b.get(str);
            } else {
                String str2 = "";
                j2 = 0;
                for (Map.Entry next : this.e.d.entrySet()) {
                    long a2 = g(device, (String) next.getKey());
                    if (a2 != 0 && a2 > j2) {
                        str2 = (String) next.getKey();
                        j2 = a2;
                    }
                }
                obj = this.e.d.get(str2);
            }
            if (j2 == 0) {
                return null;
            }
            return new Pair<>(obj, Long.valueOf(j2));
        } catch (Exception e) {
            Log.e("mijia-card", "profile getLatestEvent", e);
            return null;
        }
    }

    /* renamed from: f */
    public double e(Device device, String str) {
        return this.p;
    }

    /* renamed from: g */
    public String f(Device device, String str) {
        return this.q;
    }

    public boolean a(Device device, Operation operation) {
        boolean z;
        if (operation == null) {
            return false;
        }
        List<Pair<String, Object>> list = operation.f;
        List<Pair<String, Object>> list2 = operation.g;
        if (list == null && list2 == null) {
            return true;
        }
        HashMap hashMap = new HashMap();
        ProfileCard e = ControlCardInfoManager.f().e(device.model);
        if (e != null) {
            for (ProfileCardType profileCardType : e.c) {
                hashMap.put(profileCardType.c, profileCardType);
            }
        }
        if (list != null) {
            for (Pair next : list) {
                Object obj = next.second;
                Object a2 = a(device, (HashMap<String, ProfileCardType>) hashMap, (String) next.first);
                LogUtil.c("mijia-card", "isEnable profile prop:" + ((String) next.first) + " current desc:" + a2 + ", enable desc:" + obj);
                if (String.valueOf(obj).equals(String.valueOf(a2)) || ((obj instanceof Boolean) && String.valueOf(((Boolean) obj).booleanValue() ? 1 : 0).equals(String.valueOf(a2)))) {
                    return true;
                }
            }
            z = false;
        } else {
            z = true;
        }
        if (list2 == null) {
            return z;
        }
        for (Pair next2 : list2) {
            Object obj2 = next2.second;
            Object a3 = a(device, (HashMap<String, ProfileCardType>) hashMap, (String) next2.first);
            LogUtil.c("mijia-card", "isEnable profile prop:" + ((String) next2.first) + " current desc:" + a3 + ", disable desc:" + obj2);
            if (String.valueOf(obj2).equals(String.valueOf(a3)) || ((obj2 instanceof Boolean) && String.valueOf(((Boolean) obj2).booleanValue() ? 1 : 0).equals(String.valueOf(a3)))) {
                return false;
            }
        }
        return true;
    }

    private Object a(Device device, HashMap<String, ProfileCardType> hashMap, String str) {
        Map<String, Object> b = ControlCardInfoManager.f().b(device.did);
        if (b == null) {
            return null;
        }
        Object d = b(device, this.c);
        if (d != null && !"".equals(d)) {
            return d;
        }
        Object obj = b.get(str);
        ProfileCardType profileCardType = hashMap.get(str);
        if (profileCardType == null || profileCardType.e == null) {
            return null;
        }
        Param param = profileCardType.e.k;
        if (profileCardType.e.b == null) {
            return param.c(obj);
        }
        PropItem propItem = profileCardType.e.b.get(this.c);
        if (propItem != null) {
            return propItem.k.c(obj);
        }
        return null;
    }
}
