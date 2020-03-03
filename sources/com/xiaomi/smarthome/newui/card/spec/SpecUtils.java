package com.xiaomi.smarthome.newui.card.spec;

import android.text.TextUtils;
import android.util.Pair;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ConstraintValue;
import com.xiaomi.smarthome.device.api.spec.definitions.data.DataFormat;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ValueDefinition;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ValueList;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;
import com.xiaomi.smarthome.device.api.spec.instance.SpecAction;
import com.xiaomi.smarthome.device.api.spec.instance.SpecDevice;
import com.xiaomi.smarthome.device.api.spec.instance.SpecEvent;
import com.xiaomi.smarthome.device.api.spec.instance.SpecProperty;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.smarthome.newui.card.Operation;
import com.xiaomi.smarthome.newui.utils.NumberUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20610a = "prop.";
    private static final String b = "action.";
    private static final String c = "event.";

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0031  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(com.xiaomi.smarthome.device.api.spec.instance.SpecProperty r4, java.lang.Object r5) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x000b
            java.lang.String r4 = "mijia-card"
            java.lang.String r5 = "getValueDescription property is null"
            android.util.Log.i(r4, r5)
            return r0
        L_0x000b:
            com.xiaomi.smarthome.device.api.spec.definitions.PropertyDefinition r4 = r4.getPropertyDefinition()
            com.xiaomi.smarthome.device.api.spec.definitions.data.ConstraintValue r4 = r4.getConstraintValue()
            if (r5 == 0) goto L_0x0058
            boolean r1 = r4 instanceof com.xiaomi.smarthome.device.api.spec.definitions.data.ValueList
            if (r1 == 0) goto L_0x0058
            com.xiaomi.smarthome.device.api.spec.definitions.data.ValueList r4 = (com.xiaomi.smarthome.device.api.spec.definitions.data.ValueList) r4
            java.util.List r4 = r4.values()
            if (r4 == 0) goto L_0x0058
            int r1 = r4.size()
            if (r1 <= 0) goto L_0x0058
            java.util.Iterator r4 = r4.iterator()
        L_0x002b:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x0058
            java.lang.Object r1 = r4.next()
            com.xiaomi.smarthome.device.api.spec.definitions.data.ValueDefinition r1 = (com.xiaomi.smarthome.device.api.spec.definitions.data.ValueDefinition) r1
            com.xiaomi.smarthome.device.api.spec.definitions.data.DataValue r2 = r1.value()
            java.lang.Object r2 = r2.getObjectValue()
            boolean r3 = r5.equals(r2)
            if (r3 != 0) goto L_0x0053
            java.lang.String r3 = java.lang.String.valueOf(r5)
            java.lang.String r2 = java.lang.String.valueOf(r2)
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x002b
        L_0x0053:
            java.lang.String r4 = r1.description()
            return r4
        L_0x0058:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.card.spec.SpecUtils.a(com.xiaomi.smarthome.device.api.spec.instance.SpecProperty, java.lang.Object):java.lang.String");
    }

    public static int b(SpecProperty specProperty, Object obj) {
        List<ValueDefinition> values;
        ConstraintValue constraintValue = specProperty.getPropertyDefinition().getConstraintValue();
        if (obj == null || !(constraintValue instanceof ValueList) || (values = ((ValueList) constraintValue).values()) == null || values.size() <= 0) {
            return -1;
        }
        for (int i = 0; i < values.size(); i++) {
            Object objectValue = values.get(i).value().getObjectValue();
            if (obj.equals(objectValue) || String.valueOf(obj).equals(String.valueOf(objectValue))) {
                return i;
            }
        }
        return -1;
    }

    public static Number a(double d, double d2, double d3, double d4, DataFormat dataFormat) {
        if (d4 <= 0.0d) {
            d4 = 1.0d;
        }
        double d5 = (double) ((int) ((d - d2) / d4));
        Double.isNaN(d5);
        double min = Math.min(d3, Math.max(d2, (d5 * d4) + d2));
        if ((dataFormat == null || !dataFormat.isDigits()) && String.valueOf(d4).contains(".")) {
            return Double.valueOf(min);
        }
        return Long.valueOf(Math.round(min));
    }

    public static boolean a(Object obj) {
        Boolean bool = true;
        return bool.equals(obj) || NumberUtils.a(obj, 0.0d) > 0.0d;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0151  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r5, com.xiaomi.smarthome.device.Device r6, com.xiaomi.smarthome.device.api.spec.instance.SpecService r7, com.xiaomi.smarthome.device.api.spec.instance.Spec.SpecItem r8, java.lang.String r9, java.lang.Object r10, com.xiaomi.smarthome.newui.card.CardItem.State r11) {
        /*
            java.lang.String r10 = r11.toString()
            if (r7 != 0) goto L_0x0009
            java.lang.String r7 = ""
            goto L_0x000d
        L_0x0009:
            java.lang.String r7 = r7.getTypeName()
        L_0x000d:
            if (r8 != 0) goto L_0x0012
            java.lang.String r8 = ""
            goto L_0x001a
        L_0x0012:
            com.xiaomi.smarthome.device.api.spec.definitions.SpecDefinition r8 = r8.getDefinition()
            java.lang.String r8 = r8.getDescription()
        L_0x001a:
            java.lang.String r6 = com.xiaomi.smarthome.device.DeviceUtils.e(r6)
            com.xiaomi.smarthome.newui.card.MiotSpecCardManager r11 = com.xiaomi.smarthome.newui.card.MiotSpecCardManager.f()
            java.lang.String r0 = r11.d()
            boolean r1 = android.text.TextUtils.isEmpty(r9)
            r2 = 0
            if (r1 == 0) goto L_0x0030
            r9 = r8
        L_0x002e:
            r1 = r2
            goto L_0x007d
        L_0x0030:
            boolean r1 = r9.equals(r8)
            if (r1 != 0) goto L_0x002e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r5)
            r3.append(r6)
            java.lang.String r4 = "_"
            r3.append(r4)
            r3.append(r7)
            java.lang.String r4 = "_"
            r3.append(r4)
            r3.append(r8)
            java.lang.String r4 = "_"
            r3.append(r4)
            r3.append(r9)
            java.lang.String r4 = "_"
            r3.append(r4)
            r3.append(r10)
            java.lang.String r4 = ".png"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r3 = r11.a((java.lang.String) r3)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
        L_0x007d:
            boolean r3 = com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.f(r1)
            if (r3 != 0) goto L_0x00c2
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r5)
            r3.append(r6)
            java.lang.String r4 = "_"
            r3.append(r4)
            r3.append(r7)
            java.lang.String r4 = "_"
            r3.append(r4)
            r3.append(r9)
            java.lang.String r4 = "_"
            r3.append(r4)
            r3.append(r10)
            java.lang.String r4 = ".png"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r3 = r11.a((java.lang.String) r3)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
        L_0x00c2:
            boolean r3 = com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.f(r1)
            if (r3 != 0) goto L_0x00ff
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r5)
            r3.append(r7)
            java.lang.String r4 = "_"
            r3.append(r4)
            r3.append(r9)
            java.lang.String r4 = "_"
            r3.append(r4)
            r3.append(r10)
            java.lang.String r4 = ".png"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r3 = r11.a((java.lang.String) r3)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
        L_0x00ff:
            boolean r3 = com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.f(r1)
            if (r3 != 0) goto L_0x0134
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r5)
            r3.append(r9)
            java.lang.String r4 = "_"
            r3.append(r4)
            r3.append(r10)
            java.lang.String r4 = ".png"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r11 = r11.a((java.lang.String) r3)
            r1.append(r11)
            java.lang.String r1 = r1.toString()
        L_0x0134:
            boolean r11 = com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.f(r1)
            if (r11 == 0) goto L_0x0151
            java.lang.String r5 = "mijia-card"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "CardItem.getCardDrawable match:"
            r6.append(r7)
            r6.append(r1)
            java.lang.String r6 = r6.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r5, r6)
            return r1
        L_0x0151:
            java.lang.String r11 = "mijia-card"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "CardItem.getCardDrawable not match:"
            r1.append(r3)
            r1.append(r0)
            r1.append(r5)
            r1.append(r6)
            java.lang.String r5 = "_"
            r1.append(r5)
            r1.append(r7)
            java.lang.String r5 = "_"
            r1.append(r5)
            r1.append(r8)
            java.lang.String r5 = "_"
            r1.append(r5)
            r1.append(r9)
            java.lang.String r5 = "_"
            r1.append(r5)
            r1.append(r10)
            java.lang.String r5 = ".png"
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r11, r5)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.card.spec.SpecUtils.a(java.lang.String, com.xiaomi.smarthome.device.Device, com.xiaomi.smarthome.device.api.spec.instance.SpecService, com.xiaomi.smarthome.device.api.spec.instance.Spec$SpecItem, java.lang.String, java.lang.Object, com.xiaomi.smarthome.newui.card.CardItem$State):java.lang.String");
    }

    public static Map<String, ArrayList<Pair<SpecService, ? extends Spec.SpecItem>>> a(SpecDevice specDevice) {
        HashMap hashMap = new HashMap();
        Map<Integer, SpecService> services = specDevice.getServices();
        if (services != null) {
            for (Map.Entry<Integer, SpecService> value : services.entrySet()) {
                SpecService specService = (SpecService) value.getValue();
                if (specService != null) {
                    Map<Integer, SpecProperty> properties = specService.getProperties();
                    if (properties != null) {
                        for (Map.Entry<Integer, SpecProperty> value2 : properties.entrySet()) {
                            a(hashMap, specService, (Spec.SpecItem) value2.getValue());
                        }
                    }
                    Map<Integer, SpecAction> actions = specService.getActions();
                    if (actions != null) {
                        for (Map.Entry<Integer, SpecAction> value3 : actions.entrySet()) {
                            a(hashMap, specService, (Spec.SpecItem) value3.getValue());
                        }
                    }
                    Map<Integer, SpecEvent> events = specService.getEvents();
                    if (events != null) {
                        for (Map.Entry<Integer, SpecEvent> value4 : events.entrySet()) {
                            a(hashMap, specService, (Spec.SpecItem) value4.getValue());
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    private static void a(Map<String, ArrayList<Pair<SpecService, ? extends Spec.SpecItem>>> map, SpecService specService, Spec.SpecItem specItem) {
        String str;
        if (specItem instanceof SpecProperty) {
            str = "p:";
        } else if (specItem instanceof SpecAction) {
            str = "a:";
        } else {
            str = specItem instanceof SpecEvent ? "e:" : null;
        }
        String str2 = str + specService.getTypeName() + ":" + specItem.getDefinition().getTypeName();
        ArrayList arrayList = map.get(str2);
        if (arrayList == null) {
            arrayList = new ArrayList();
            map.put(str2, arrayList);
        }
        arrayList.add(new Pair(specService, specItem));
    }

    public static String a(int i, int i2) {
        return "prop." + i + "." + i2;
    }

    public static String b(int i, int i2) {
        return b + i + "." + i2;
    }

    public static String c(int i, int i2) {
        return "event." + i + "." + i2;
    }

    public static Pair<SpecService, SpecAction> a(SpecDevice specDevice, Operation operation) {
        Map<Integer, SpecAction> actions;
        if (operation == null || TextUtils.isEmpty(operation.l)) {
            return null;
        }
        String[] split = operation.l.split(":");
        if (split.length == 3) {
            for (Map.Entry<Integer, SpecService> value : specDevice.getServices().entrySet()) {
                SpecService specService = (SpecService) value.getValue();
                if (TextUtils.equals(split[1], specService.getTypeName()) && (actions = specService.getActions()) != null) {
                    for (Map.Entry<Integer, SpecAction> value2 : actions.entrySet()) {
                        SpecAction specAction = (SpecAction) value2.getValue();
                        if (TextUtils.equals(split[2], specAction.getActionDefinition().getTypeName())) {
                            return new Pair<>(specService, specAction);
                        }
                    }
                    continue;
                }
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0043 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0046 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0049 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r2) {
        /*
            int r0 = r2.hashCode()
            r1 = -1422950858(0xffffffffab2f7e36, float:-6.234764E-13)
            if (r0 == r1) goto L_0x0035
            r1 = 97
            if (r0 == r1) goto L_0x002b
            r1 = 101(0x65, float:1.42E-43)
            if (r0 == r1) goto L_0x0021
            r1 = 96891546(0x5c6729a, float:1.8661928E-35)
            if (r0 == r1) goto L_0x0017
            goto L_0x003f
        L_0x0017:
            java.lang.String r0 = "event"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x003f
            r2 = 1
            goto L_0x0040
        L_0x0021:
            java.lang.String r0 = "e"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x003f
            r2 = 0
            goto L_0x0040
        L_0x002b:
            java.lang.String r0 = "a"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x003f
            r2 = 2
            goto L_0x0040
        L_0x0035:
            java.lang.String r0 = "action"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x003f
            r2 = 3
            goto L_0x0040
        L_0x003f:
            r2 = -1
        L_0x0040:
            switch(r2) {
                case 0: goto L_0x0049;
                case 1: goto L_0x0049;
                case 2: goto L_0x0046;
                case 3: goto L_0x0046;
                default: goto L_0x0043;
            }
        L_0x0043:
            java.lang.String r2 = "prop."
            return r2
        L_0x0046:
            java.lang.String r2 = "action."
            return r2
        L_0x0049:
            java.lang.String r2 = "event."
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.card.spec.SpecUtils.a(java.lang.String):java.lang.String");
    }

    public static String a(SpecService specService, Spec.SpecItem specItem) {
        if (specItem instanceof SpecAction) {
            return b(specService.getIid(), specItem.getIid());
        }
        if (specItem instanceof SpecEvent) {
            return c(specService.getIid(), specItem.getIid());
        }
        return a(specService.getIid(), specItem.getIid());
    }
}
