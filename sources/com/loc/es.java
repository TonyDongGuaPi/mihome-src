package com.loc;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.loc.ac;
import com.mi.global.shop.model.Tags;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONObject;

public final class es {

    /* renamed from: a  reason: collision with root package name */
    static String f6596a = "http://apilocate.amap.com/mobile/binary";
    static String b = "";
    static String c = "001;11B;11C;11F;11G;11H;11I;11K;122;135;13J;13S;14S;157;15O;15U;16P";
    public static String d = null;
    public static String e = null;
    public static int f = 30000;
    public static String g = null;
    public static String h = null;
    static String i;
    static HashMap<String, String> j;
    static boolean k = false;
    static boolean l = false;
    private static final String[] m = {"com.amap.api.location", "com.loc", "com.amap.api.fence"};
    private static ac n = null;
    private static boolean o = false;
    private static boolean p = false;

    public static Bundle a(AMapLocationClientOption aMapLocationClientOption) {
        Bundle bundle = new Bundle();
        if (aMapLocationClientOption == null) {
            aMapLocationClientOption = new AMapLocationClientOption();
        }
        try {
            bundle.putParcelable("opt", aMapLocationClientOption);
        } catch (Throwable th) {
            a(th, "CoreUtil", "getOptionBundle");
        }
        return bundle;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.amap.api.location.AMapLocation a(com.amap.api.location.AMapLocation r4, com.amap.api.location.AMapLocation r5) {
        /*
            if (r5 != 0) goto L_0x0003
            return r4
        L_0x0003:
            java.lang.String r0 = r5.getCountry()     // Catch:{ Throwable -> 0x00bb }
            r4.setCountry(r0)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r0 = r5.getRoad()     // Catch:{ Throwable -> 0x00bb }
            r4.setRoad(r0)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r0 = r5.getPoiName()     // Catch:{ Throwable -> 0x00bb }
            r4.setPoiName(r0)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r0 = r5.getStreet()     // Catch:{ Throwable -> 0x00bb }
            r4.setStreet(r0)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r0 = r5.getStreetNum()     // Catch:{ Throwable -> 0x00bb }
            r4.setNumber(r0)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r0 = r5.getCityCode()     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r1 = r5.getAdCode()     // Catch:{ Throwable -> 0x00bb }
            r4.setCityCode(r0)     // Catch:{ Throwable -> 0x00bb }
            r4.setAdCode(r1)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r0 = r5.getCity()     // Catch:{ Throwable -> 0x00bb }
            r4.setCity(r0)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r0 = r5.getDistrict()     // Catch:{ Throwable -> 0x00bb }
            r4.setDistrict(r0)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r0 = r5.getProvince()     // Catch:{ Throwable -> 0x00bb }
            r4.setProvince(r0)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r0 = r5.getAoiName()     // Catch:{ Throwable -> 0x00bb }
            r4.setAoiName(r0)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r0 = r5.getAddress()     // Catch:{ Throwable -> 0x00bb }
            r4.setAddress(r0)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r0 = r5.getDescription()     // Catch:{ Throwable -> 0x00bb }
            r4.setDescription(r0)     // Catch:{ Throwable -> 0x00bb }
            android.os.Bundle r0 = r4.getExtras()     // Catch:{  }
            if (r0 == 0) goto L_0x0092
            android.os.Bundle r0 = r4.getExtras()     // Catch:{  }
            java.lang.String r1 = "citycode"
            java.lang.String r2 = r5.getCityCode()     // Catch:{  }
            r0.putString(r1, r2)     // Catch:{  }
            android.os.Bundle r0 = r4.getExtras()     // Catch:{  }
            java.lang.String r1 = "desc"
            android.os.Bundle r2 = r5.getExtras()     // Catch:{  }
            java.lang.String r3 = "desc"
            java.lang.String r2 = r2.getString(r3)     // Catch:{  }
            r0.putString(r1, r2)     // Catch:{  }
            android.os.Bundle r0 = r4.getExtras()     // Catch:{  }
            java.lang.String r1 = "adcode"
            java.lang.String r5 = r5.getAdCode()     // Catch:{  }
            r0.putString(r1, r5)     // Catch:{  }
            goto L_0x00bb
        L_0x0092:
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{  }
            r0.<init>()     // Catch:{  }
            java.lang.String r1 = "citycode"
            java.lang.String r2 = r5.getCityCode()     // Catch:{  }
            r0.putString(r1, r2)     // Catch:{  }
            java.lang.String r1 = "desc"
            android.os.Bundle r2 = r5.getExtras()     // Catch:{  }
            java.lang.String r3 = "desc"
            java.lang.String r2 = r2.getString(r3)     // Catch:{  }
            r0.putString(r1, r2)     // Catch:{  }
            java.lang.String r1 = "adcode"
            java.lang.String r5 = r5.getAdCode()     // Catch:{  }
            r0.putString(r1, r5)     // Catch:{  }
            r4.setExtras(r0)     // Catch:{  }
        L_0x00bb:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.es.a(com.amap.api.location.AMapLocation, com.amap.api.location.AMapLocation):com.amap.api.location.AMapLocation");
    }

    public static AMapLocationClientOption a(Bundle bundle) {
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        if (bundle == null) {
            return aMapLocationClientOption;
        }
        try {
            bundle.setClassLoader(AMapLocationClientOption.class.getClassLoader());
            return (AMapLocationClientOption) bundle.getParcelable("opt");
        } catch (Throwable th) {
            a(th, "CoreUtil", "getOptionFromBundle");
            return aMapLocationClientOption;
        }
    }

    public static String a() {
        return f6596a;
    }

    public static void a(Context context) {
        try {
            if (ad.b(context)) {
                f6596a = "http://abroad.apilocate.amap.com/mobile/binary";
                return;
            }
            if (j == null) {
                j = new HashMap<>(16);
            }
            j.clear();
            j.put("a9a9d23668a1a7ea93de9b21d67e436a", "F13160D440C7D0229DA95450F66AF92154AC84DF088F8CA3100B2E8131D57F3DC67124D4C466056E7A3DFBE035E1B9A4B9DA4DB68AE65A43EDFD92F5C60EF0C9");
            j.put("fe643c382e5c3b3962141f1a2e815a78", "FB923EE67A8B4032DAA517DD8CD7A26FF7C25B0C3663F92A0B61251C4FFFA858DF169D61321C3E7919CB67DF8EFEC827");
            j.put("b2e8bd171989cb2c3c13bd89b4c1067a", "239CE372F804D4BE4EAFFD183668379BDF274440E6F246AB16BBE6F5D1D30DEACFBBF0C942485727FF12288228760A9E");
            j.put("9a571aa113ad987d626c0457828962e6", "D2FF99A88BEB04683D89470D4FA72B1749DA456AB0D0F1A476477CE5A6874F53A9106423D905F9D808C0FCE8E7F1E04AC642F01FE41D0C7D933971F45CBA72B7");
            j.put("668319f11506def6208d6afe320dfd52", "53E53D46011A6BBAEA4FAE5442E659E0577CDD336F930C28635C322FB3F51C3C63F7FBAC9EAE448DFA2E5E5D716C4807");
            j.put("256b0f26bb2a9506be6cfdb84028ae08", "AF2228680EDC323FBA035362EB7E1E38A0C33E1CF6F6FB805EE553A230CBA754CD9552EB9B546542CBE619E8293151BE");
            String a2 = eh.a(u.f(context));
            i = a2;
            if (a2 != null) {
                try {
                    if (a2.length() != 0) {
                        if (j != null && j.containsKey(a2)) {
                            String str = j.get(a2);
                            String str2 = null;
                            if (str != null && str.length() > 0) {
                                str2 = new String(eh.d(a(str), a2), "utf-8");
                            }
                            if (str2 != null && str2.length() > 0 && str2.contains("http:")) {
                                b = str2;
                                f6596a = str2;
                            }
                        }
                    }
                } catch (Throwable th) {
                    a(th, "CoreUtil", "checkUrl");
                }
            }
        } catch (Throwable th2) {
            a(th2, "CoreUtil", "checkUrl");
        }
    }

    public static void a(AMapLocation aMapLocation, JSONObject jSONObject) {
        if (jSONObject != null && aMapLocation != null) {
            try {
                double optDouble = jSONObject.optDouble(Tags.Nearby.LAT, aMapLocation.getLatitude());
                double optDouble2 = jSONObject.optDouble(Tags.Nearby.LON, aMapLocation.getLongitude());
                aMapLocation.setProvider(jSONObject.optString("provider", aMapLocation.getProvider()));
                aMapLocation.setLatitude(optDouble);
                aMapLocation.setLongitude(optDouble2);
                aMapLocation.setAltitude(jSONObject.optDouble("altitude", aMapLocation.getAltitude()));
                try {
                    String optString = jSONObject.optString("accuracy");
                    if (!TextUtils.isEmpty(optString)) {
                        aMapLocation.setAccuracy(Float.parseFloat(optString));
                    }
                } catch (Throwable unused) {
                }
                try {
                    String optString2 = jSONObject.optString("speed");
                    if (!TextUtils.isEmpty(optString2)) {
                        aMapLocation.setSpeed(Float.parseFloat(optString2));
                    }
                } catch (Throwable unused2) {
                }
                try {
                    String optString3 = jSONObject.optString("bearing");
                    if (!TextUtils.isEmpty(optString3)) {
                        aMapLocation.setBearing(Float.parseFloat(optString3));
                    }
                } catch (Throwable unused3) {
                }
                aMapLocation.setAdCode(jSONObject.optString("adcode", aMapLocation.getAdCode()));
                aMapLocation.setCityCode(jSONObject.optString("citycode", aMapLocation.getCityCode()));
                aMapLocation.setAddress(jSONObject.optString("address", aMapLocation.getAddress()));
                String optString4 = jSONObject.optString("desc", "");
                aMapLocation.setCountry(jSONObject.optString("country", aMapLocation.getCountry()));
                aMapLocation.setProvince(jSONObject.optString("province", aMapLocation.getProvince()));
                aMapLocation.setCity(jSONObject.optString("city", aMapLocation.getCity()));
                aMapLocation.setDistrict(jSONObject.optString("district", aMapLocation.getDistrict()));
                aMapLocation.setRoad(jSONObject.optString("road", aMapLocation.getRoad()));
                aMapLocation.setStreet(jSONObject.optString("street", aMapLocation.getStreet()));
                aMapLocation.setNumber(jSONObject.optString("number", aMapLocation.getStreetNum()));
                aMapLocation.setPoiName(jSONObject.optString("poiname", aMapLocation.getPoiName()));
                aMapLocation.setAoiName(jSONObject.optString("aoiname", aMapLocation.getAoiName()));
                aMapLocation.setErrorCode(jSONObject.optInt("errorCode", aMapLocation.getErrorCode()));
                aMapLocation.setErrorInfo(jSONObject.optString("errorInfo", aMapLocation.getErrorInfo()));
                aMapLocation.setLocationType(jSONObject.optInt("locationType", aMapLocation.getLocationType()));
                aMapLocation.setLocationDetail(jSONObject.optString("locationDetail", aMapLocation.getLocationDetail()));
                aMapLocation.setTime(jSONObject.optLong("time", aMapLocation.getTime()));
                boolean optBoolean = jSONObject.optBoolean("isOffset", aMapLocation.isOffset());
                aMapLocation.setOffset(optBoolean);
                aMapLocation.setBuildingId(jSONObject.optString("poiid", aMapLocation.getBuildingId()));
                aMapLocation.setFloor(jSONObject.optString(Tags.Kuwan.COMMENT_FLOOR, aMapLocation.getFloor()));
                aMapLocation.setDescription(jSONObject.optString("description", aMapLocation.getDescription()));
                aMapLocation.setCoordType(jSONObject.has("coordType") ? jSONObject.optString("coordType", AMapLocation.COORD_TYPE_GCJ02) : (!a(optDouble, optDouble2) || !optBoolean) ? AMapLocation.COORD_TYPE_WGS84 : AMapLocation.COORD_TYPE_GCJ02);
                Bundle bundle = new Bundle();
                bundle.putString("citycode", aMapLocation.getCityCode());
                bundle.putString("desc", optString4.toString());
                bundle.putString("adcode", aMapLocation.getAdCode());
                aMapLocation.setExtras(bundle);
            } catch (Throwable th) {
                a(th, "CoreUtil", "transformLocation");
            }
        }
    }

    public static void a(Throwable th, String str, String str2) {
        if (!"reportError".equals(str2) && !(th instanceof t)) {
            try {
                aq.b(th, str, str2);
            } catch (Throwable unused) {
            }
        }
    }

    public static boolean a(double d2, double d3) {
        int i2 = (int) ((d3 - 73.0d) / 0.5d);
        int i3 = (int) ((d2 - 3.5d) / 0.5d);
        if (i3 < 0 || i3 >= 101 || i2 < 0 || i2 >= 124) {
            return false;
        }
        try {
            return "00000000000000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001100000001011000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011101010111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000110111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111101111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001000110111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011010111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001110011100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000110000001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001010011100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111100110001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111000111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111110011000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111000000000111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111100000000000010111110100000011000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111110000000001111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111000000111111111111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111101111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000101111111111111111111111111111111111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000011110000000001111111111111111111111111111111111111111111110000000000000000000000000000000000000000000000000000000000011000011111100000000111111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000001111111100111111111100110111111111111111111111111111111111111111111111110000000000000000000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000000000101111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111011111000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111100100000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111100011100000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000111110000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110011111110000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110111111110000000000000000000000111011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000011111111111111111111111111111111111111111111111100001111111111111111111111111111111111111111111111111010000000000000000000000111111111111111111111111111111111111111111110000000000000001111111111111111111111111111111111111111111100000000000000000000011111111111111111111111111111111100000000000000000000000000001111111111111111111111111111111111111111110000000000000000000001111111111111111111111111111111100000000000000000000000000000001111111111111111111111111111111111111111000000000000000000000111111111111111111111111111111110000000000000000000000000000001111111111111111111111111111111111111111100000000000000000000111111111111111111111111111111000000000000000000000000000000000111111111111111111111111111111111111111111000000000000000000001111111111111111111111111110000000000000000000000000000000000001110011111111111111111111111111111111111111100000000000000000000011111111111111111100000000000000000000000000000000000000000000000001111111111111111111111111111111111111000000000000000000001111111111111111111000000000000000000000000000000000000000000000000011111111111111111111111111111111111100000000000000000000011111111111111111100000000000000000000000000000000000000000000000000011111111111111111111111111111111111000000000000000000001111111111111111100000000000000000000000000000000000000000000000000000000111111111111111111111111111111110000000000000000000000000111111111100000000000000000000000000000000000000000000000000111111111111111111111111111111111111111000000000000000000000000011111111100000000000000000000000000000000000000000000000000011111111111111111111111111111110001111100000000000000000000000000111110000000000000000000000000000000000000000000000000000001111111111111111111111111111111000000000000000000000000000000000001110000000000000000000000000000000000000000000000000000000011111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010110000000000000000000000".charAt((i3 * 124) + i2) == '1';
        } catch (Throwable th) {
            a(th, "CoreUtil", "isChina");
            return true;
        }
    }

    private static boolean a(double d2, double d3, double d4, double d5, double d6, double d7) {
        return Math.abs(((d4 - d2) * (d7 - d3)) - ((d6 - d2) * (d5 - d3))) < 1.0E-9d && (d2 - d4) * (d2 - d6) <= 0.0d && (d3 - d5) * (d3 - d7) <= 0.0d;
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00fa A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(com.amap.api.location.DPoint r33, java.util.List<com.amap.api.location.DPoint> r34) {
        /*
            r0 = r34
            double r13 = r33.getLongitude()
            double r15 = r33.getLatitude()
            double r17 = r33.getLatitude()
            r11 = 0
            java.lang.Object r1 = r0.get(r11)
            com.amap.api.location.DPoint r1 = (com.amap.api.location.DPoint) r1
            int r2 = r34.size()
            r19 = 1
            int r2 = r2 + -1
            java.lang.Object r2 = r0.get(r2)
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x002e
            java.lang.Object r1 = r0.get(r11)
            r0.add(r1)
        L_0x002e:
            r1 = 0
            r20 = 0
        L_0x0031:
            int r2 = r34.size()
            int r2 = r2 + -1
            if (r1 >= r2) goto L_0x00ff
            java.lang.Object r2 = r0.get(r1)
            com.amap.api.location.DPoint r2 = (com.amap.api.location.DPoint) r2
            double r21 = r2.getLongitude()
            java.lang.Object r2 = r0.get(r1)
            com.amap.api.location.DPoint r2 = (com.amap.api.location.DPoint) r2
            double r23 = r2.getLatitude()
            int r12 = r1 + 1
            java.lang.Object r1 = r0.get(r12)
            com.amap.api.location.DPoint r1 = (com.amap.api.location.DPoint) r1
            double r25 = r1.getLongitude()
            java.lang.Object r1 = r0.get(r12)
            com.amap.api.location.DPoint r1 = (com.amap.api.location.DPoint) r1
            double r27 = r1.getLatitude()
            r1 = r13
            r3 = r15
            r5 = r21
            r7 = r23
            r9 = r25
            r29 = r12
            r30 = 0
            r11 = r27
            boolean r1 = a(r1, r3, r5, r7, r9, r11)
            if (r1 == 0) goto L_0x0078
            return r19
        L_0x0078:
            r1 = 0
            double r31 = r27 - r23
            double r1 = java.lang.Math.abs(r31)
            r3 = 4472406533629990549(0x3e112e0be826d695, double:1.0E-9)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 < 0) goto L_0x00fa
            r9 = 4640537203540230144(0x4066800000000000, double:180.0)
            r1 = r21
            r3 = r23
            r5 = r13
            r7 = r15
            r11 = r17
            boolean r1 = a(r1, r3, r5, r7, r9, r11)
            if (r1 == 0) goto L_0x00a2
            int r1 = (r23 > r27 ? 1 : (r23 == r27 ? 0 : -1))
            if (r1 <= 0) goto L_0x00fa
        L_0x009f:
            int r20 = r20 + 1
            goto L_0x00fa
        L_0x00a2:
            r9 = 4640537203540230144(0x4066800000000000, double:180.0)
            r1 = r25
            r3 = r27
            r5 = r13
            r7 = r15
            r11 = r17
            boolean r1 = a(r1, r3, r5, r7, r9, r11)
            if (r1 == 0) goto L_0x00ba
            int r1 = (r27 > r23 ? 1 : (r27 == r23 ? 0 : -1))
            if (r1 <= 0) goto L_0x00fa
            goto L_0x009f
        L_0x00ba:
            r1 = 0
            double r25 = r25 - r21
            double r1 = r17 - r15
            double r3 = r25 * r1
            r5 = 4640537203540230144(0x4066800000000000, double:180.0)
            double r5 = r5 - r13
            double r7 = r31 * r5
            double r3 = r3 - r7
            r7 = 0
            int r9 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x00f6
            double r23 = r23 - r15
            double r5 = r5 * r23
            double r21 = r21 - r13
            double r1 = r1 * r21
            double r5 = r5 - r1
            double r5 = r5 / r3
            double r23 = r23 * r25
            double r21 = r21 * r31
            double r23 = r23 - r21
            double r23 = r23 / r3
            int r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r1 < 0) goto L_0x00f6
            r1 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r3 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r3 > 0) goto L_0x00f6
            int r3 = (r23 > r7 ? 1 : (r23 == r7 ? 0 : -1))
            if (r3 < 0) goto L_0x00f6
            int r3 = (r23 > r1 ? 1 : (r23 == r1 ? 0 : -1))
            if (r3 > 0) goto L_0x00f6
            r1 = 1
            goto L_0x00f7
        L_0x00f6:
            r1 = 0
        L_0x00f7:
            if (r1 == 0) goto L_0x00fa
            goto L_0x009f
        L_0x00fa:
            r1 = r29
            r11 = 0
            goto L_0x0031
        L_0x00ff:
            r30 = 0
            int r20 = r20 % 2
            if (r20 == 0) goto L_0x0107
            r30 = 1
        L_0x0107:
            return r30
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.es.a(com.amap.api.location.DPoint, java.util.List):boolean");
    }

    private static byte[] a(String str) {
        if (str == null || str.length() < 2) {
            return new byte[0];
        }
        String lowerCase = str.toLowerCase(Locale.US);
        int length = lowerCase.length() / 2;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = (byte) (fa.h(lowerCase.substring(i3, i3 + 2)) & 255);
        }
        return bArr;
    }

    public static ac b() {
        try {
            if (n == null) {
                n = new ac.a("loc", "4.7.1", "AMAP_Location_SDK_Android 4.7.1").a((String[]) m.clone()).a("4.7.1").a();
            }
        } catch (Throwable th) {
            a(th, "CoreUtil", "getSDKInfo");
        }
        return n;
    }

    public static String b(Context context) {
        return aa.b(u.e(context));
    }

    public static String c() {
        return c;
    }

    public static void c(Context context) {
        try {
            if (ad.b(context)) {
                f6596a = "http://abroad.apilocate.amap.com/mobile/binary";
            } else if (TextUtils.isEmpty(b)) {
                f6596a = "http://apilocate.amap.com/mobile/binary";
            } else {
                f6596a = b;
            }
        } catch (Throwable th) {
            a(th, "CoreUtil", "changeUrl");
        }
    }

    public static boolean d() {
        if (!k) {
            k = true;
            l = false;
        }
        return l;
    }
}
