package com.xiaomi.smarthome.newui.card.profile;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.bluetooth.XmBluetoothRecord;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.api.spec.definitions.PropertyDefinition;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ConstraintValue;
import com.xiaomi.smarthome.device.api.spec.definitions.data.DataValue;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ValueDefinition;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ValueList;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ValueRange;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;
import com.xiaomi.smarthome.device.api.spec.instance.SpecDevice;
import com.xiaomi.smarthome.device.api.spec.instance.SpecProperty;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.CardItemUtils;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.newui.card.Operation;
import com.xiaomi.smarthome.newui.card.profile.PropItem;
import com.xiaomi.smarthome.newui.card.spec.SpecCard;
import com.xiaomi.smarthome.newui.card.spec.SpecCardItem;
import com.xiaomi.smarthome.newui.card.spec.SpecCardSelector;
import com.xiaomi.smarthome.newui.card.spec.SpecUnit;
import com.xiaomi.smarthome.newui.card.spec.SpecUtils;
import com.xiaomi.smarthome.newui.utils.NumberUtils;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class SpecCardType extends Card.CardType<Pair<SpecService, ? extends Spec.SpecItem>> {
    public static final Drawable i = CommonApplication.getAppContext().getResources().getDrawable(R.drawable.btn_auto_on);
    public ArrayList<String> j;

    protected SpecCardType(JSONObject jSONObject) {
        super(jSONObject);
        int length;
        if (jSONObject != null) {
            this.e = new PropItem(jSONObject.optJSONObject(XmBluetoothRecord.TYPE_PROP));
            this.e.f20557a = this.c;
            JSONArray optJSONArray = jSONObject.optJSONArray("desc_sort");
            if (optJSONArray != null && (length = optJSONArray.length()) > 0) {
                this.j = new ArrayList<>(length);
                for (int i2 = 0; i2 < length; i2++) {
                    this.j.add(CardItemUtils.a(optJSONArray.optString(i2)));
                }
            }
        }
    }

    public static SpecCardType a(JSONObject jSONObject) {
        return new SpecCardType(jSONObject);
    }

    /* renamed from: a */
    public long g(Device device, Pair<SpecService, ? extends Spec.SpecItem> pair) {
        return CardItemUtils.a(MiotSpecCardManager.f().d(device.did), SpecUtils.a((SpecService) pair.first, (Spec.SpecItem) pair.second));
    }

    public CardItem a(Pair<SpecService, ? extends Spec.SpecItem>[] pairArr) {
        CardItem a2 = super.a(pairArr);
        return a2 == null ? SpecCardItem.a(this, pairArr) : a2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0160  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0167  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x01a0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(com.xiaomi.smarthome.device.Device r4, com.xiaomi.smarthome.device.api.spec.instance.SpecService r5, com.xiaomi.smarthome.device.api.spec.instance.Spec.SpecItem r6, java.lang.String r7, java.lang.Object r8) {
        /*
            java.lang.String r0 = com.xiaomi.smarthome.newui.card.spec.SpecUtils.a((com.xiaomi.smarthome.device.api.spec.instance.SpecService) r5, (com.xiaomi.smarthome.device.api.spec.instance.Spec.SpecItem) r6)
            if (r8 == 0) goto L_0x002d
            java.lang.String r1 = ""
            boolean r1 = r1.equals(r8)
            if (r1 != 0) goto L_0x002d
            boolean r1 = r6 instanceof com.xiaomi.smarthome.device.api.spec.instance.SpecProperty
            if (r1 == 0) goto L_0x002d
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "."
            r1.append(r0)
            r0 = r6
            com.xiaomi.smarthome.device.api.spec.instance.SpecProperty r0 = (com.xiaomi.smarthome.device.api.spec.instance.SpecProperty) r0
            int r0 = com.xiaomi.smarthome.newui.card.spec.SpecUtils.b((com.xiaomi.smarthome.device.api.spec.instance.SpecProperty) r0, (java.lang.Object) r8)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
        L_0x002d:
            com.xiaomi.smarthome.newui.card.MiotSpecCardManager r1 = com.xiaomi.smarthome.newui.card.MiotSpecCardManager.f()
            java.util.Map r2 = r1.b((com.xiaomi.smarthome.device.Device) r4)
            if (r2 == 0) goto L_0x006e
            java.lang.Object r2 = r2.get(r0)
            java.util.Map r2 = (java.util.Map) r2
            java.lang.String r2 = com.xiaomi.smarthome.newui.card.CardItem.a((java.util.Map<java.lang.String, java.lang.String>) r2)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L_0x006e
            java.lang.String r4 = "mijia-card"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "CardItem.getSpecPropValueName match specLanguage value:"
            r5.append(r6)
            r5.append(r8)
            java.lang.String r6 = " key:"
            r5.append(r6)
            r5.append(r0)
            java.lang.String r6 = " showText:"
            r5.append(r6)
            r5.append(r2)
            java.lang.String r5 = r5.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r4, r5)
            return r2
        L_0x006e:
            java.lang.String r0 = r5.getTypeName()
            java.lang.String r5 = r5.getDesc()
            com.xiaomi.smarthome.device.api.spec.definitions.SpecDefinition r6 = r6.getDefinition()
            java.lang.String r6 = r6.getDescription()
            java.lang.String r4 = com.xiaomi.smarthome.device.DeviceUtils.e(r4)
            boolean r2 = android.text.TextUtils.isEmpty(r7)
            r3 = 0
            if (r2 == 0) goto L_0x008c
            r7 = r6
        L_0x008a:
            r2 = r3
            goto L_0x00e4
        L_0x008c:
            boolean r2 = r7.equals(r6)
            if (r2 != 0) goto L_0x008a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            java.lang.String r3 = "_"
            r2.append(r3)
            r2.append(r0)
            java.lang.String r3 = "_"
            r2.append(r3)
            r2.append(r6)
            java.lang.String r3 = "_"
            r2.append(r3)
            r2.append(r7)
            java.lang.String r3 = r2.toString()
            java.util.Map r2 = r1.e((java.lang.String) r3)
            if (r2 != 0) goto L_0x00e4
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            java.lang.String r3 = "_"
            r2.append(r3)
            r2.append(r5)
            java.lang.String r3 = "_"
            r2.append(r3)
            r2.append(r6)
            java.lang.String r3 = "_"
            r2.append(r3)
            r2.append(r7)
            java.lang.String r3 = r2.toString()
            java.util.Map r2 = r1.e((java.lang.String) r3)
        L_0x00e4:
            if (r2 != 0) goto L_0x0106
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            java.lang.String r3 = "_"
            r2.append(r3)
            r2.append(r0)
            java.lang.String r3 = "_"
            r2.append(r3)
            r2.append(r7)
            java.lang.String r3 = r2.toString()
            java.util.Map r2 = r1.e((java.lang.String) r3)
        L_0x0106:
            if (r2 != 0) goto L_0x0128
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            java.lang.String r3 = "_"
            r2.append(r3)
            r2.append(r5)
            java.lang.String r3 = "_"
            r2.append(r3)
            r2.append(r7)
            java.lang.String r3 = r2.toString()
            java.util.Map r2 = r1.e((java.lang.String) r3)
        L_0x0128:
            if (r2 != 0) goto L_0x0142
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r3 = "_"
            r2.append(r3)
            r2.append(r7)
            java.lang.String r3 = r2.toString()
            java.util.Map r2 = r1.e((java.lang.String) r3)
        L_0x0142:
            if (r2 != 0) goto L_0x015d
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r5)
            java.lang.String r5 = "_"
            r2.append(r5)
            r2.append(r7)
            java.lang.String r5 = r2.toString()
            java.util.Map r2 = r1.e((java.lang.String) r5)
            goto L_0x015e
        L_0x015d:
            r5 = r3
        L_0x015e:
            if (r2 != 0) goto L_0x0165
            java.util.Map r2 = r1.e((java.lang.String) r7)
            r5 = r7
        L_0x0165:
            if (r2 != 0) goto L_0x01a0
            java.lang.String r5 = "mijia-card"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "CardItem.getSpecPropValueName notmatch value:"
            r1.append(r2)
            r1.append(r8)
            java.lang.String r8 = " config:"
            r1.append(r8)
            r1.append(r4)
            java.lang.String r4 = "_"
            r1.append(r4)
            r1.append(r0)
            java.lang.String r4 = "_"
            r1.append(r4)
            r1.append(r6)
            java.lang.String r4 = "_"
            r1.append(r4)
            r1.append(r7)
            java.lang.String r4 = r1.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r5, r4)
            java.lang.String r4 = ""
            return r4
        L_0x01a0:
            java.lang.String r4 = com.xiaomi.smarthome.newui.card.CardItem.a((java.util.Map<java.lang.String, java.lang.String>) r2)
            java.lang.String r6 = "mijia-card"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "CardItem.getSpecPropValueName match value:"
            r7.append(r0)
            r7.append(r8)
            java.lang.String r8 = " config:"
            r7.append(r8)
            r7.append(r5)
            java.lang.String r5 = " showText:"
            r7.append(r5)
            r7.append(r4)
            java.lang.String r5 = r7.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r6, r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.card.profile.SpecCardType.a(com.xiaomi.smarthome.device.Device, com.xiaomi.smarthome.device.api.spec.instance.SpecService, com.xiaomi.smarthome.device.api.spec.instance.Spec$SpecItem, java.lang.String, java.lang.Object):java.lang.String");
    }

    /* renamed from: b */
    public String a(Device device, Pair<SpecService, ? extends Spec.SpecItem> pair) {
        return (device == null || pair == null) ? "" : a(device, pair, (Object) null);
    }

    public String a(Pair<SpecService, ? extends Spec.SpecItem> pair, Object obj, double d) {
        double d2;
        DataValue stepValue;
        if (pair == null || !(pair.second instanceof SpecProperty)) {
            return String.valueOf(obj);
        }
        if (obj == null || "null".equals(obj)) {
            return "";
        }
        if (obj instanceof Number) {
            d2 = ((Number) obj).doubleValue() * d;
        } else {
            String valueOf = String.valueOf(obj);
            try {
                d2 = Double.parseDouble(valueOf) * d;
            } catch (NumberFormatException e) {
                Log.e("mijia-card", "SpecCardType.formatValue", e);
                return valueOf;
            }
        }
        ConstraintValue constraintValue = ((SpecProperty) pair.second).getPropertyDefinition().getConstraintValue();
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setRoundingMode(RoundingMode.HALF_UP);
        if ((constraintValue instanceof ValueRange) && (stepValue = ((ValueRange) constraintValue).stepValue()) != null) {
            String[] split = stepValue.toString().split("\\.");
            if (split.length != 2 || NumberUtils.a((Object) split[1], 0) <= 0) {
                numberInstance.setMaximumFractionDigits(0);
            } else {
                int length = split[1].length();
                numberInstance.setMaximumFractionDigits(length);
                numberInstance.setMinimumFractionDigits(length);
            }
            return numberInstance.format(d2);
        } else if (((SpecProperty) pair.second).getPropertyDefinition().getFormat().isDigits()) {
            numberInstance.setMaximumFractionDigits(0);
            return numberInstance.format(d2);
        } else {
            String str = this.e.f;
            if (TextUtils.isEmpty(str)) {
                return String.valueOf(d2);
            }
            try {
                return String.format(str, new Object[]{Double.valueOf(d2)});
            } catch (Exception unused) {
                return String.valueOf(d2);
            }
        }
    }

    public String a(Device device, Pair<SpecService, ? extends Spec.SpecItem> pair, Object obj) {
        if (pair == null || !(pair.second instanceof SpecProperty)) {
            return String.valueOf(obj);
        }
        if (obj != null) {
            String a2 = SpecUtils.a((SpecProperty) pair.second, obj);
            String description = ((Spec.SpecItem) pair.second).getDefinition().getDescription();
            if (TextUtils.isEmpty(a2) && !SpecUtils.a(obj)) {
                a2 = "Not " + description;
            }
            String a3 = a(device, (SpecService) pair.first, (Spec.SpecItem) pair.second, a2, obj);
            if (!TextUtils.isEmpty(a3)) {
                return a3;
            }
        }
        return a(device, (SpecService) pair.first, (Spec.SpecItem) pair.second, (String) null, (Object) null);
    }

    public static Drawable a(Device device, SpecService specService, Spec.SpecItem specItem, String str, Object obj, CardItem.State state) {
        String a2 = SpecUtils.a("CardItemspec_", device, specService, specItem, str, obj, state);
        if (a2 == null) {
            return null;
        }
        return new BitmapDrawable(CommonApplication.getAppContext().getResources(), BitmapFactory.decodeFile(a2));
    }

    public Drawable a(Device device, Pair<SpecService, ? extends Spec.SpecItem> pair, Object obj, CardItem.State state) {
        String str;
        if (pair == null || !(pair.second instanceof SpecProperty)) {
            return CommonApplication.getAppContext().getResources().getDrawable(R.drawable.btn_auto_off);
        }
        if (state == null) {
            state = CardItem.State.NOR;
        }
        String a2 = SpecUtils.a((SpecProperty) pair.second, obj);
        if (TextUtils.isEmpty(a2)) {
            String description = ((Spec.SpecItem) pair.second).getDefinition().getDescription();
            if (!SpecUtils.a(obj)) {
                Drawable a3 = a(device, (SpecService) pair.first, (Spec.SpecItem) pair.second, "Not " + description, obj, state);
                if (a3 != null) {
                    return a3;
                }
            }
            str = description;
        } else {
            str = a2;
        }
        Drawable a4 = a(device, (SpecService) pair.first, (Spec.SpecItem) pair.second, str, obj, state);
        return a4 == null ? i : a4;
    }

    public String b(Device device, Pair<SpecService, ? extends Spec.SpecItem> pair, Object obj) {
        List<PropItem.PropExtraItem> list;
        if (pair != null && (pair.second instanceof SpecProperty)) {
            String typeName = ((SpecService) pair.first).getTypeName();
            String description = ((Spec.SpecItem) pair.second).getDefinition().getDescription();
            String e = DeviceUtils.e(device);
            MiotSpecCardManager f = MiotSpecCardManager.f();
            String a2 = SpecUtils.a((SpecProperty) pair.second, obj);
            if (TextUtils.isEmpty(a2)) {
                list = null;
                a2 = description;
            } else if (!a2.equals(description)) {
                list = f.g(e + JSMethod.NOT_SET + typeName + JSMethod.NOT_SET + description + JSMethod.NOT_SET + a2);
            } else {
                list = null;
            }
            if (list == null) {
                list = f.g(e + JSMethod.NOT_SET + typeName + JSMethod.NOT_SET + a2);
            }
            if (list == null) {
                list = f.g(typeName + JSMethod.NOT_SET + a2);
            }
            if (list == null) {
                list = f.g(a2);
            }
            if (list == null) {
                LogUtil.c("mijia-card", "model:" + device.model + " getSpecPropTextColor not match " + e + JSMethod.NOT_SET + typeName + JSMethod.NOT_SET + description);
                return "";
            }
            for (PropItem.PropExtraItem next : list) {
                if (next.f20558a == null) {
                    double a3 = NumberUtils.a(obj, -1.0d);
                    if (a3 >= next.c && a3 <= next.d) {
                        return next.e;
                    }
                } else if (String.valueOf(next.f20558a).equals(String.valueOf(obj))) {
                    return next.e;
                }
            }
        }
        return null;
    }

    public Object c(Device device, Pair<SpecService, ? extends Spec.SpecItem> pair) {
        if (pair == null || !(pair.second instanceof SpecProperty)) {
            return "";
        }
        Object e = b(device, pair);
        PropertyDefinition propertyDefinition = ((SpecProperty) pair.second).getPropertyDefinition();
        ConstraintValue constraintValue = propertyDefinition.getConstraintValue();
        if (e != null && !"".equals(e)) {
            if (constraintValue instanceof ValueList) {
                List<ValueDefinition> values = ((ValueList) constraintValue).values();
                if (values != null && values.size() > 1) {
                    int i2 = 0;
                    while (i2 < values.size()) {
                        ValueDefinition valueDefinition = values.get(i2);
                        if (!e.equals(valueDefinition.value().getObjectValue()) && !String.valueOf(e).equals(String.valueOf(valueDefinition.value()))) {
                            i2++;
                        } else if (i2 == 0) {
                            return values.get(1).value().getObjectValue();
                        } else {
                            return values.get(0).value().getObjectValue();
                        }
                    }
                }
            } else if (constraintValue instanceof ValueRange) {
                ValueRange valueRange = (ValueRange) constraintValue;
                DataValue minValue = valueRange.minValue();
                DataValue maxValue = valueRange.maxValue();
                if (e.equals(minValue.getObjectValue()) || String.valueOf(e).equals(String.valueOf(minValue))) {
                    return maxValue.getObjectValue();
                }
                return minValue.getObjectValue();
            }
            if (SpecUtils.a(e)) {
                return propertyDefinition.getFormat().createValue(0).getObjectValue();
            }
        }
        return propertyDefinition.getFormat().createValue(1).getObjectValue();
    }

    public Pair<Object, Long> d(Device device, Pair<SpecService, ? extends Spec.SpecItem> pair) {
        Map<String, Object> b = MiotSpecCardManager.f().b(device.did);
        if (b == null) {
            return null;
        }
        return new Pair<>(b.get(SpecUtils.a((SpecService) pair.first, (Spec.SpecItem) pair.second)), Long.valueOf(g(device, pair)));
    }

    /* renamed from: e */
    public Object b(Device device, Pair<SpecService, ? extends Spec.SpecItem> pair) {
        return MiotSpecCardManager.f().a(device.did, pair);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r0 = ((com.xiaomi.smarthome.device.api.spec.instance.SpecProperty) r3.second).getPropertyDefinition().getUnit();
     */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double e(com.xiaomi.smarthome.device.Device r2, android.util.Pair<com.xiaomi.smarthome.device.api.spec.instance.SpecService, ? extends com.xiaomi.smarthome.device.api.spec.instance.Spec.SpecItem> r3) {
        /*
            r1 = this;
            if (r3 == 0) goto L_0x001f
            java.lang.Object r0 = r3.second
            boolean r0 = r0 instanceof com.xiaomi.smarthome.device.api.spec.instance.SpecProperty
            if (r0 == 0) goto L_0x001f
            java.lang.Object r0 = r3.second
            com.xiaomi.smarthome.device.api.spec.instance.SpecProperty r0 = (com.xiaomi.smarthome.device.api.spec.instance.SpecProperty) r0
            com.xiaomi.smarthome.device.api.spec.definitions.PropertyDefinition r0 = r0.getPropertyDefinition()
            java.lang.String r0 = r0.getUnit()
            if (r0 == 0) goto L_0x001f
            java.lang.String r2 = com.xiaomi.smarthome.device.DeviceUtils.e(r2)
            com.xiaomi.smarthome.newui.card.spec.SpecUnit r2 = r1.a((android.util.Pair<com.xiaomi.smarthome.device.api.spec.instance.SpecService, ? extends com.xiaomi.smarthome.device.api.spec.instance.Spec.SpecItem>) r3, (java.lang.String) r2, (java.lang.String) r0)
            goto L_0x0020
        L_0x001f:
            r2 = 0
        L_0x0020:
            if (r2 == 0) goto L_0x0025
            double r2 = r2.f20609a
            return r2
        L_0x0025:
            com.xiaomi.smarthome.newui.card.profile.PropItem r2 = r1.e
            boolean r2 = r2.b()
            if (r2 == 0) goto L_0x0032
            com.xiaomi.smarthome.newui.card.profile.PropItem r2 = r1.e
            double r2 = r2.e
            return r2
        L_0x0032:
            r2 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.card.profile.SpecCardType.e(com.xiaomi.smarthome.device.Device, android.util.Pair):double");
    }

    /* renamed from: g */
    public String f(Device device, Pair<SpecService, ? extends Spec.SpecItem> pair) {
        if (pair != null && (pair.second instanceof SpecProperty)) {
            String unit = ((SpecProperty) pair.second).getPropertyDefinition().getUnit();
            if (!TextUtils.isEmpty(unit)) {
                SpecUnit a2 = a(pair, DeviceUtils.e(device), unit);
                if (a2 != null) {
                    String a3 = CardItem.a(a2.b);
                    if (!TextUtils.isEmpty(a3)) {
                        return a3;
                    }
                }
                return unit;
            }
        }
        return this.e.g;
    }

    private SpecUnit a(Pair<SpecService, ? extends Spec.SpecItem> pair, String str, String str2) {
        SpecUnit specUnit = null;
        if (pair == null) {
            return null;
        }
        String typeName = ((SpecService) pair.first).getTypeName();
        String description = ((Spec.SpecItem) pair.second).getDefinition().getDescription();
        MiotSpecCardManager f = MiotSpecCardManager.f();
        if (TextUtils.isEmpty(str2)) {
            str2 = description;
        } else {
            specUnit = f.f(str + JSMethod.NOT_SET + typeName + JSMethod.NOT_SET + description + JSMethod.NOT_SET + str2);
        }
        if (specUnit == null) {
            specUnit = f.f(str + JSMethod.NOT_SET + typeName + JSMethod.NOT_SET + str2);
        }
        if (specUnit == null) {
            specUnit = f.f(typeName + JSMethod.NOT_SET + str2);
        }
        if (specUnit == null) {
            specUnit = f.f(str2);
        }
        if (specUnit == null) {
            LogUtil.b("mijia-card", "CardItem.getSpecPropValueName notmatch:" + str + JSMethod.NOT_SET + typeName + JSMethod.NOT_SET + description + JSMethod.NOT_SET + str2);
        }
        return specUnit;
    }

    public boolean a(Device device, Operation operation) {
        boolean z;
        if (this.d == null || this.d.size() == 0) {
            return true;
        }
        if (operation == null) {
            operation = (Operation) this.d.get(0);
        }
        if (operation == null) {
            return true;
        }
        List<Pair<String, Object>> list = operation.f;
        List<Pair<String, Object>> list2 = operation.g;
        if (list == null && list2 == null) {
            return true;
        }
        if (list != null) {
            for (Pair next : list) {
                Object obj = next.second;
                Object a2 = a(device, (String) next.first);
                LogUtil.c("mijia-card", "isEnable spec prop:" + ((String) next.first) + " current desc:" + a2 + ", enable desc:" + obj);
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
            Object a3 = a(device, (String) next2.first);
            LogUtil.c("mijia-card", "isEnable spec prop:" + ((String) next2.first) + " current desc:" + a3 + ", disable desc:" + obj2);
            if (String.valueOf(obj2).equals(String.valueOf(a3)) || ((obj2 instanceof Boolean) && String.valueOf(((Boolean) obj2).booleanValue() ? 1 : 0).equals(String.valueOf(a3)))) {
                return false;
            }
        }
        return true;
    }

    @Nullable
    private Object a(Device device, String str) {
        Pair[] pairArr;
        Pair[] pairArr2;
        MiotSpecCardManager f = MiotSpecCardManager.f();
        SpecDevice a2 = f.a(device);
        SpecCardSelector a3 = f.a(a2);
        if (a3 == null) {
            return null;
        }
        Pair<SpecCard, Pair<SpecService, ? extends Spec.SpecItem>[][]> c = a3.c(a2);
        if (!(c == null || c.first == null)) {
            List list = ((SpecCard) c.first).c;
            for (int i2 = 0; i2 < list.size(); i2++) {
                SpecCardType specCardType = (SpecCardType) list.get(i2);
                if (TextUtils.equals(specCardType.c, str) && (pairArr2 = ((Pair[][]) c.second)[i2]) != null) {
                    int length = pairArr2.length;
                    int i3 = 0;
                    while (i3 < length) {
                        Pair pair = pairArr2[i3];
                        if (pair == null || !(pair.second instanceof SpecProperty)) {
                            i3++;
                        } else {
                            Object e = specCardType.b(device, (Pair<SpecService, ? extends Spec.SpecItem>) pair);
                            String a4 = SpecUtils.a((SpecProperty) pair.second, e);
                            return (a4 == null || "".equals(a4)) ? e : a4;
                        }
                    }
                    continue;
                }
            }
        }
        Pair<SpecCard, Pair<SpecService, ? extends Spec.SpecItem>[][]> f2 = a3.f(a2);
        if (f2 == null || f2.first == null) {
            return null;
        }
        List list2 = ((SpecCard) f2.first).c;
        for (int i4 = 0; i4 < list2.size(); i4++) {
            SpecCardType specCardType2 = (SpecCardType) list2.get(i4);
            if (TextUtils.equals(specCardType2.c, str) && (pairArr = ((Pair[][]) f2.second)[i4]) != null) {
                int length2 = pairArr.length;
                int i5 = 0;
                while (i5 < length2) {
                    Pair pair2 = pairArr[i5];
                    if (pair2 == null || !(pair2.second instanceof SpecProperty)) {
                        i5++;
                    } else {
                        Object e2 = specCardType2.b(device, (Pair<SpecService, ? extends Spec.SpecItem>) pair2);
                        String a5 = SpecUtils.a((SpecProperty) pair2.second, e2);
                        return (a5 == null || "".equals(a5)) ? e2 : a5;
                    }
                }
                continue;
            }
        }
        return null;
    }
}
