package com.loc;

import android.os.Bundle;
import com.alipay.sdk.util.i;
import com.amap.api.fence.DistrictItem;
import com.amap.api.fence.GeoFence;
import com.amap.api.fence.PoiItem;
import com.amap.api.location.DPoint;
import com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class l {

    /* renamed from: a  reason: collision with root package name */
    private static long f6615a;

    public static int a(String str, List<GeoFence> list, Bundle bundle) {
        JSONArray optJSONArray;
        Bundle bundle2 = bundle;
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("status", 0);
            int optInt2 = jSONObject.optInt("infocode", 0);
            if (optInt != 1 || (optJSONArray = jSONObject.optJSONArray("pois")) == null) {
                return optInt2;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                GeoFence geoFence = new GeoFence();
                PoiItem poiItem = new PoiItem();
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                poiItem.setPoiId(jSONObject2.optString("id"));
                poiItem.setPoiName(jSONObject2.optString("name"));
                poiItem.setPoiType(jSONObject2.optString("type"));
                poiItem.setTypeCode(jSONObject2.optString("typecode"));
                poiItem.setAddress(jSONObject2.optString("address"));
                String optString = jSONObject2.optString("location");
                if (optString != null) {
                    String[] split = optString.split(",");
                    poiItem.setLongitude(Double.parseDouble(split[0]));
                    poiItem.setLatitude(Double.parseDouble(split[1]));
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    DPoint dPoint = new DPoint(poiItem.getLatitude(), poiItem.getLongitude());
                    arrayList2.add(dPoint);
                    arrayList.add(arrayList2);
                    geoFence.setPointList(arrayList);
                    geoFence.setCenter(dPoint);
                }
                poiItem.setTel(jSONObject2.optString("tel"));
                poiItem.setProvince(jSONObject2.optString("pname"));
                poiItem.setCity(jSONObject2.optString("cityname"));
                poiItem.setAdname(jSONObject2.optString("adname"));
                geoFence.setPoiItem(poiItem);
                StringBuilder sb = new StringBuilder();
                sb.append(a());
                geoFence.setFenceId(sb.toString());
                geoFence.setCustomId(bundle2.getString(GeoFence.BUNDLE_KEY_CUSTOMID));
                geoFence.setPendingIntentAction(bundle2.getString("pendingIntentAction"));
                geoFence.setType(2);
                geoFence.setRadius(bundle2.getFloat("fenceRadius"));
                geoFence.setExpiration(bundle2.getLong("expiration"));
                geoFence.setActivatesAction(bundle2.getInt("activatesAction", 1));
                list.add(geoFence);
            }
            return optInt2;
        } catch (Throwable unused) {
            return 5;
        }
    }

    public static synchronized long a() {
        long j;
        synchronized (l.class) {
            long c = fa.c();
            if (c > f6615a) {
                f6615a = c;
            } else {
                f6615a++;
            }
            j = f6615a;
        }
        return j;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e9 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.amap.api.location.DPoint> a(java.util.List<com.amap.api.location.DPoint> r30, float r31) {
        /*
            r29 = this;
            r0 = r29
            r1 = r30
            r2 = r31
            if (r1 != 0) goto L_0x000a
            r1 = 0
            return r1
        L_0x000a:
            int r3 = r30.size()
            r4 = 2
            if (r3 > r4) goto L_0x0012
            return r1
        L_0x0012:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r4 = 0
            java.lang.Object r5 = r1.get(r4)
            com.amap.api.location.DPoint r5 = (com.amap.api.location.DPoint) r5
            int r6 = r30.size()
            r7 = 1
            int r6 = r6 - r7
            java.lang.Object r6 = r1.get(r6)
            com.amap.api.location.DPoint r6 = (com.amap.api.location.DPoint) r6
            r8 = 0
            r11 = r8
            r10 = 1
            r13 = 0
        L_0x002f:
            int r14 = r30.size()
            int r14 = r14 - r7
            if (r10 >= r14) goto L_0x00f7
            java.lang.Object r14 = r1.get(r10)
            com.amap.api.location.DPoint r14 = (com.amap.api.location.DPoint) r14
            double r15 = r14.getLongitude()
            double r17 = r5.getLongitude()
            double r15 = r15 - r17
            double r17 = r14.getLatitude()
            double r19 = r5.getLatitude()
            double r17 = r17 - r19
            double r19 = r6.getLongitude()
            double r21 = r5.getLongitude()
            double r19 = r19 - r21
            double r21 = r6.getLatitude()
            double r23 = r5.getLatitude()
            double r21 = r21 - r23
            double r15 = r15 * r19
            double r17 = r17 * r21
            double r15 = r15 + r17
            double r17 = r19 * r19
            double r23 = r21 * r21
            double r17 = r17 + r23
            double r15 = r15 / r17
            double r17 = r5.getLongitude()
            double r23 = r6.getLongitude()
            int r25 = (r17 > r23 ? 1 : (r17 == r23 ? 0 : -1))
            if (r25 != 0) goto L_0x008d
            double r17 = r5.getLatitude()
            double r23 = r6.getLatitude()
            int r25 = (r17 > r23 ? 1 : (r17 == r23 ? 0 : -1))
            if (r25 != 0) goto L_0x008d
            r17 = 1
            goto L_0x008f
        L_0x008d:
            r17 = 0
        L_0x008f:
            int r18 = (r15 > r8 ? 1 : (r15 == r8 ? 0 : -1))
            if (r18 < 0) goto L_0x00bb
            if (r17 == 0) goto L_0x0096
            goto L_0x00bb
        L_0x0096:
            r17 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r23 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r23 <= 0) goto L_0x00a5
            double r15 = r6.getLongitude()
            double r17 = r6.getLatitude()
            goto L_0x00c3
        L_0x00a5:
            double r17 = r5.getLongitude()
            double r19 = r19 * r15
            double r17 = r17 + r19
            double r19 = r5.getLatitude()
            double r15 = r15 * r21
            double r15 = r19 + r15
            r26 = r5
            r8 = r15
            r4 = r17
            goto L_0x00c8
        L_0x00bb:
            double r15 = r5.getLongitude()
            double r17 = r5.getLatitude()
        L_0x00c3:
            r26 = r5
            r4 = r15
            r8 = r17
        L_0x00c8:
            com.amap.api.location.DPoint r15 = new com.amap.api.location.DPoint
            r27 = r8
            double r7 = r14.getLatitude()
            double r0 = r14.getLongitude()
            r15.<init>(r7, r0)
            com.amap.api.location.DPoint r0 = new com.amap.api.location.DPoint
            r7 = r27
            r0.<init>(r7, r4)
            float r0 = com.loc.fa.a((com.amap.api.location.DPoint) r15, (com.amap.api.location.DPoint) r0)
            double r0 = (double) r0
            int r4 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r4 <= 0) goto L_0x00e9
            r11 = r0
            r13 = r10
        L_0x00e9:
            int r10 = r10 + 1
            r5 = r26
            r0 = r29
            r1 = r30
            r4 = 0
            r7 = 1
            r8 = 0
            goto L_0x002f
        L_0x00f7:
            r26 = r5
            double r0 = (double) r2
            int r4 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r4 >= 0) goto L_0x0107
            r5 = r26
            r3.add(r5)
            r3.add(r6)
            return r3
        L_0x0107:
            int r0 = r13 + 1
            r1 = r30
            r4 = 0
            java.util.List r0 = r1.subList(r4, r0)
            r4 = r29
            java.util.List r0 = r4.a(r0, r2)
            int r5 = r30.size()
            java.util.List r1 = r1.subList(r13, r5)
            java.util.List r1 = r4.a(r1, r2)
            r3.addAll(r0)
            int r0 = r3.size()
            r2 = 1
            int r0 = r0 - r2
            r3.remove(r0)
            r3.addAll(r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.l.a(java.util.List, float):java.util.List");
    }

    public final int b(String str, List<GeoFence> list, Bundle bundle) {
        JSONArray optJSONArray;
        String str2;
        ArrayList arrayList;
        int i;
        long j;
        float f;
        String str3;
        String str4;
        int i2;
        float f2;
        GeoFence geoFence;
        long j2;
        Bundle bundle2 = bundle;
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("status", 0);
            int optInt2 = jSONObject.optInt("infocode", 0);
            String string = bundle2.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
            String string2 = bundle2.getString("pendingIntentAction");
            float f3 = bundle2.getFloat("fenceRadius");
            long j3 = bundle2.getLong("expiration");
            int i3 = bundle2.getInt("activatesAction", 1);
            if (optInt == 1 && (optJSONArray = jSONObject.optJSONArray("districts")) != null) {
                int i4 = 0;
                while (i4 < optJSONArray.length()) {
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    GeoFence geoFence2 = new GeoFence();
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i4);
                    String optString = jSONObject2.optString("citycode");
                    String optString2 = jSONObject2.optString("adcode");
                    String optString3 = jSONObject2.optString("name");
                    JSONArray jSONArray = optJSONArray;
                    String string3 = jSONObject2.getString("center");
                    int i5 = optInt2;
                    DPoint dPoint = new DPoint();
                    if (string3 != null) {
                        i = i4;
                        String[] split = string3.split(",");
                        arrayList = arrayList2;
                        str2 = optString3;
                        dPoint.setLatitude(Double.parseDouble(split[1]));
                        dPoint.setLongitude(Double.parseDouble(split[0]));
                        geoFence2.setCenter(dPoint);
                    } else {
                        i = i4;
                        arrayList = arrayList2;
                        str2 = optString3;
                    }
                    geoFence2.setCustomId(string);
                    geoFence2.setPendingIntentAction(string2);
                    geoFence2.setType(3);
                    geoFence2.setRadius(f3);
                    geoFence2.setExpiration(j3);
                    geoFence2.setActivatesAction(i3);
                    StringBuilder sb = new StringBuilder();
                    sb.append(a());
                    geoFence2.setFenceId(sb.toString());
                    String optString4 = jSONObject2.optString("polyline");
                    if (optString4 != null) {
                        String[] split2 = optString4.split(PaymentOptionsDecoder.pipeSeparator);
                        int length = split2.length;
                        int i6 = 0;
                        float f4 = Float.MIN_VALUE;
                        float f5 = Float.MAX_VALUE;
                        while (i6 < length) {
                            int i7 = i3;
                            String str5 = split2[i6];
                            String[] strArr = split2;
                            DistrictItem districtItem = new DistrictItem();
                            String str6 = string;
                            List arrayList4 = new ArrayList();
                            districtItem.setCitycode(optString);
                            districtItem.setAdcode(optString2);
                            String str7 = optString2;
                            String str8 = str2;
                            districtItem.setDistrictName(str8);
                            String str9 = str8;
                            String[] split3 = str5.split(i.b);
                            String str10 = string2;
                            int i8 = 0;
                            while (i8 < split3.length) {
                                String[] strArr2 = split3;
                                String[] split4 = split3[i8].split(",");
                                float f6 = f3;
                                if (split4.length > 1) {
                                    j2 = j3;
                                    geoFence = geoFence2;
                                    f2 = f5;
                                    arrayList4.add(new DPoint(Double.parseDouble(split4[1]), Double.parseDouble(split4[0])));
                                } else {
                                    j2 = j3;
                                    geoFence = geoFence2;
                                    f2 = f5;
                                }
                                i8++;
                                split3 = strArr2;
                                f3 = f6;
                                j3 = j2;
                                geoFence2 = geoFence;
                                f5 = f2;
                            }
                            float f7 = f3;
                            long j4 = j3;
                            GeoFence geoFence3 = geoFence2;
                            float f8 = f5;
                            if (((float) arrayList4.size()) > 100.0f) {
                                try {
                                    arrayList4 = a(arrayList4, 100.0f);
                                } catch (Throwable unused) {
                                    return 5;
                                }
                            }
                            arrayList3.add(arrayList4);
                            districtItem.setPolyline(arrayList4);
                            ArrayList arrayList5 = arrayList;
                            arrayList5.add(districtItem);
                            f4 = Math.max(f4, j.b(dPoint, (List<DPoint>) arrayList4));
                            f5 = Math.min(f8, j.a(dPoint, (List<DPoint>) arrayList4));
                            i6++;
                            arrayList = arrayList5;
                            i3 = i7;
                            split2 = strArr;
                            string = str6;
                            optString2 = str7;
                            str2 = str9;
                            string2 = str10;
                            f3 = f7;
                            j3 = j4;
                            geoFence2 = geoFence3;
                        }
                        i2 = i3;
                        str4 = string;
                        str3 = string2;
                        f = f3;
                        j = j3;
                        GeoFence geoFence4 = geoFence2;
                        geoFence4.setMaxDis2Center(f4);
                        geoFence4.setMinDis2Center(f5);
                        geoFence4.setDistrictItemList(arrayList);
                        geoFence4.setPointList(arrayList3);
                        list.add(geoFence4);
                    } else {
                        List<GeoFence> list2 = list;
                        i2 = i3;
                        str4 = string;
                        str3 = string2;
                        f = f3;
                        j = j3;
                    }
                    i4 = i + 1;
                    optJSONArray = jSONArray;
                    optInt2 = i5;
                    i3 = i2;
                    string = str4;
                    string2 = str3;
                    f3 = f;
                    j3 = j;
                }
            }
            return optInt2;
        } catch (Throwable unused2) {
            return 5;
        }
    }
}
