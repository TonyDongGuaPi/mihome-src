package com.amap.api.services.a;

import android.text.TextUtils;
import com.adobe.xmp.XMPConst;
import com.alipay.sdk.util.i;
import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.district.DistrictItem;
import com.amap.api.services.geocoder.AoiItem;
import com.amap.api.services.geocoder.BusinessArea;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeRoad;
import com.amap.api.services.geocoder.StreetNumber;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.IndoorData;
import com.amap.api.services.poisearch.Photo;
import com.amap.api.services.poisearch.PoiItemExtension;
import com.amap.api.services.poisearch.SubPoiItem;
import com.amap.api.services.road.Crossroad;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.BusStep;
import com.amap.api.services.route.DistanceItem;
import com.amap.api.services.route.DistanceResult;
import com.amap.api.services.route.District;
import com.amap.api.services.route.Doorway;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.Railway;
import com.amap.api.services.route.RailwaySpace;
import com.amap.api.services.route.RailwayStationItem;
import com.amap.api.services.route.RidePath;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RideStep;
import com.amap.api.services.route.RouteBusLineItem;
import com.amap.api.services.route.RouteBusWalkItem;
import com.amap.api.services.route.RouteRailwayItem;
import com.amap.api.services.route.RouteSearchCity;
import com.amap.api.services.route.TMC;
import com.amap.api.services.route.TaxiItem;
import com.amap.api.services.route.TruckPath;
import com.amap.api.services.route.TruckRouteRestult;
import com.amap.api.services.route.TruckStep;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.amap.api.services.route.WalkStep;
import com.amap.api.services.routepoisearch.RoutePOIItem;
import com.amap.api.services.traffic.TrafficStatusEvaluation;
import com.amap.api.services.traffic.TrafficStatusInfo;
import com.amap.api.services.traffic.TrafficStatusResult;
import com.amap.api.services.weather.LocalDayWeatherForecast;
import com.amap.api.services.weather.LocalWeatherForecast;
import com.amap.api.services.weather.LocalWeatherLive;
import com.facebook.places.model.PlaceFields;
import com.mi.global.shop.model.Tags;
import com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder;
import com.taobao.weex.common.Constants;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.smarthome.framework.api.model.AreaPropInfo;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.lineargradient.LinearGradientManager;
import com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManager;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class z {

    /* renamed from: a  reason: collision with root package name */
    private static String[] f4428a = {"010", "021", "022", "023", "1852", "1853"};

    /* JADX WARNING: Removed duplicated region for block: B:14:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x007e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<com.amap.api.services.nearby.NearbyInfo> a(org.json.JSONObject r16, boolean r17) throws org.json.JSONException {
        /*
            java.lang.String r0 = "datas"
            r1 = r16
            org.json.JSONArray r0 = r1.optJSONArray(r0)
            if (r0 == 0) goto L_0x0088
            int r1 = r0.length()
            if (r1 != 0) goto L_0x0012
            goto L_0x0088
        L_0x0012:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            int r2 = r0.length()
            r3 = 0
            r4 = 0
        L_0x001d:
            if (r4 >= r2) goto L_0x0087
            org.json.JSONObject r5 = r0.optJSONObject(r4)
            java.lang.String r6 = "userid"
            java.lang.String r6 = a((org.json.JSONObject) r5, (java.lang.String) r6)
            java.lang.String r7 = "location"
            java.lang.String r7 = a((org.json.JSONObject) r5, (java.lang.String) r7)
            r8 = 0
            if (r7 == 0) goto L_0x004f
            java.lang.String r10 = ","
            java.lang.String[] r7 = r7.split(r10)
            int r10 = r7.length
            r11 = 2
            if (r10 != r11) goto L_0x004f
            r8 = r7[r3]
            double r8 = l((java.lang.String) r8)
            r10 = 1
            r7 = r7[r10]
            double r10 = l((java.lang.String) r7)
            r14 = r8
            r8 = r10
            r10 = r14
            goto L_0x0050
        L_0x004f:
            r10 = r8
        L_0x0050:
            java.lang.String r7 = "distance"
            java.lang.String r7 = a((org.json.JSONObject) r5, (java.lang.String) r7)
            java.lang.String r12 = "updatetime"
            java.lang.String r5 = a((org.json.JSONObject) r5, (java.lang.String) r12)
            long r12 = m((java.lang.String) r5)
            int r5 = j((java.lang.String) r7)
            com.amap.api.services.core.LatLonPoint r7 = new com.amap.api.services.core.LatLonPoint
            r7.<init>(r8, r10)
            com.amap.api.services.nearby.NearbyInfo r8 = new com.amap.api.services.nearby.NearbyInfo
            r8.<init>()
            r8.setUserID(r6)
            r8.setTimeStamp(r12)
            r8.setPoint(r7)
            if (r17 == 0) goto L_0x007e
            r8.setDrivingDistance(r5)
            goto L_0x0081
        L_0x007e:
            r8.setDistance(r5)
        L_0x0081:
            r1.add(r8)
            int r4 = r4 + 1
            goto L_0x001d
        L_0x0087:
            return r1
        L_0x0088:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.z.a(org.json.JSONObject, boolean):java.util.ArrayList");
    }

    public static ArrayList<SuggestionCity> a(JSONObject jSONObject) throws JSONException, NumberFormatException {
        JSONArray optJSONArray;
        ArrayList<SuggestionCity> arrayList = new ArrayList<>();
        if (!jSONObject.has("cities") || (optJSONArray = jSONObject.optJSONArray("cities")) == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(new SuggestionCity(a(optJSONObject, "name"), a(optJSONObject, "citycode"), a(optJSONObject, "adcode"), j(a(optJSONObject, "num"))));
            }
        }
        return arrayList;
    }

    public static ArrayList<String> b(JSONObject jSONObject) throws JSONException {
        ArrayList<String> arrayList = new ArrayList<>();
        JSONArray optJSONArray = jSONObject.optJSONArray("keywords");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            arrayList.add(optJSONArray.optString(i));
        }
        return arrayList;
    }

    public static ArrayList<PoiItem> c(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        ArrayList<PoiItem> arrayList = new ArrayList<>();
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("pois")) == null || optJSONArray.length() == 0) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(d(optJSONObject));
            }
        }
        return arrayList;
    }

    public static PoiItem d(JSONObject jSONObject) throws JSONException {
        PoiItem poiItem = new PoiItem(a(jSONObject, "id"), b(jSONObject, "location"), a(jSONObject, "name"), a(jSONObject, "address"));
        poiItem.setAdCode(a(jSONObject, "adcode"));
        poiItem.setProvinceName(a(jSONObject, "pname"));
        poiItem.setCityName(a(jSONObject, "cityname"));
        poiItem.setAdName(a(jSONObject, "adname"));
        poiItem.setCityCode(a(jSONObject, "citycode"));
        poiItem.setProvinceCode(a(jSONObject, "pcode"));
        poiItem.setDirection(a(jSONObject, "direction"));
        if (jSONObject.has(Tags.Nearby.DISTANCE)) {
            String a2 = a(jSONObject, Tags.Nearby.DISTANCE);
            if (!i(a2)) {
                try {
                    poiItem.setDistance((int) Float.parseFloat(a2));
                } catch (NumberFormatException e) {
                    s.a(e, "JSONHelper", "parseBasePoi");
                } catch (Exception e2) {
                    s.a(e2, "JSONHelper", "parseBasePoi");
                }
            }
        }
        poiItem.setTel(a(jSONObject, "tel"));
        poiItem.setTypeDes(a(jSONObject, "type"));
        poiItem.setEnter(b(jSONObject, "entr_location"));
        poiItem.setExit(b(jSONObject, "exit_location"));
        poiItem.setWebsite(a(jSONObject, "website"));
        poiItem.setPostcode(a(jSONObject, "postcode"));
        poiItem.setBusinessArea(a(jSONObject, "business_area"));
        poiItem.setEmail(a(jSONObject, "email"));
        if (h(a(jSONObject, "indoor_map"))) {
            poiItem.setIndoorMap(false);
        } else {
            poiItem.setIndoorMap(true);
        }
        poiItem.setParkingType(a(jSONObject, "parking_type"));
        ArrayList arrayList = new ArrayList();
        if (jSONObject.has("children")) {
            JSONArray optJSONArray = jSONObject.optJSONArray("children");
            if (optJSONArray == null) {
                poiItem.setSubPois(arrayList);
            } else {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        arrayList.add(x(optJSONObject));
                    }
                }
                poiItem.setSubPois(arrayList);
            }
        }
        poiItem.setIndoorDate(d(jSONObject, "indoor_data"));
        poiItem.setPoiExtension(e(jSONObject, "biz_ext"));
        poiItem.setTypeCode(a(jSONObject, "typecode"));
        poiItem.setShopID(a(jSONObject, "shopid"));
        a(poiItem, jSONObject);
        return poiItem;
    }

    private static SubPoiItem x(JSONObject jSONObject) throws JSONException {
        SubPoiItem subPoiItem = new SubPoiItem(a(jSONObject, "id"), b(jSONObject, "location"), a(jSONObject, "name"), a(jSONObject, "address"));
        subPoiItem.setSubName(a(jSONObject, "sname"));
        subPoiItem.setSubTypeDes(a(jSONObject, "subtype"));
        if (jSONObject.has(Tags.Nearby.DISTANCE)) {
            String a2 = a(jSONObject, Tags.Nearby.DISTANCE);
            if (!i(a2)) {
                try {
                    subPoiItem.setDistance((int) Float.parseFloat(a2));
                } catch (NumberFormatException e) {
                    s.a(e, "JSONHelper", "parseSubPoiItem");
                } catch (Exception e2) {
                    s.a(e2, "JSONHelper", "parseSubPoiItem");
                }
            }
        }
        return subPoiItem;
    }

    public static ArrayList<BusStationItem> e(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        ArrayList<BusStationItem> arrayList = new ArrayList<>();
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("busstops")) == null || optJSONArray.length() == 0) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(f(optJSONObject));
            }
        }
        return arrayList;
    }

    public static BusStationItem f(JSONObject jSONObject) throws JSONException {
        BusStationItem g = g(jSONObject);
        if (g == null) {
            return g;
        }
        g.setAdCode(a(jSONObject, "adcode"));
        g.setCityCode(a(jSONObject, "citycode"));
        JSONArray optJSONArray = jSONObject.optJSONArray("buslines");
        ArrayList arrayList = new ArrayList();
        if (optJSONArray == null) {
            g.setBusLineItems(arrayList);
            return g;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(h(optJSONObject));
            }
        }
        g.setBusLineItems(arrayList);
        return g;
    }

    public static BusStationItem g(JSONObject jSONObject) throws JSONException {
        BusStationItem busStationItem = new BusStationItem();
        busStationItem.setBusStationId(a(jSONObject, "id"));
        busStationItem.setLatLonPoint(b(jSONObject, "location"));
        busStationItem.setBusStationName(a(jSONObject, "name"));
        return busStationItem;
    }

    public static BusLineItem h(JSONObject jSONObject) throws JSONException {
        BusLineItem busLineItem = new BusLineItem();
        busLineItem.setBusLineId(a(jSONObject, "id"));
        busLineItem.setBusLineType(a(jSONObject, "type"));
        busLineItem.setBusLineName(a(jSONObject, "name"));
        busLineItem.setDirectionsCoordinates(c(jSONObject, "polyline"));
        busLineItem.setCityCode(a(jSONObject, "citycode"));
        busLineItem.setOriginatingStation(a(jSONObject, "start_stop"));
        busLineItem.setTerminalStation(a(jSONObject, "end_stop"));
        return busLineItem;
    }

    public static ArrayList<BusLineItem> i(JSONObject jSONObject) throws JSONException {
        ArrayList<BusLineItem> arrayList = new ArrayList<>();
        JSONArray optJSONArray = jSONObject.optJSONArray("buslines");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(j(optJSONObject));
            }
        }
        return arrayList;
    }

    public static BusLineItem j(JSONObject jSONObject) throws JSONException {
        BusLineItem h = h(jSONObject);
        if (h == null) {
            return h;
        }
        h.setFirstBusTime(s.c(a(jSONObject, SmartConfigDataProvider.F)));
        h.setLastBusTime(s.c(a(jSONObject, Tags.ReserveOrder.END_TIME)));
        h.setBusCompany(a(jSONObject, MibiConstants.fg));
        h.setDistance(k(a(jSONObject, Tags.Nearby.DISTANCE)));
        h.setBasicPrice(k(a(jSONObject, "basic_price")));
        h.setTotalPrice(k(a(jSONObject, "total_price")));
        h.setBounds(c(jSONObject, "bounds"));
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("busstops");
        if (optJSONArray == null) {
            h.setBusStations(arrayList);
            return h;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(g(optJSONObject));
            }
        }
        h.setBusStations(arrayList);
        return h;
    }

    public static DistrictItem k(JSONObject jSONObject) throws JSONException {
        String optString;
        DistrictItem districtItem = new DistrictItem();
        districtItem.setCitycode(a(jSONObject, "citycode"));
        districtItem.setAdcode(a(jSONObject, "adcode"));
        districtItem.setName(a(jSONObject, "name"));
        districtItem.setLevel(a(jSONObject, "level"));
        districtItem.setCenter(b(jSONObject, "center"));
        if (jSONObject.has("polyline") && (optString = jSONObject.optString("polyline")) != null && optString.length() > 0) {
            districtItem.setDistrictBoundary(optString.split(PaymentOptionsDecoder.pipeSeparator));
        }
        a(jSONObject.optJSONArray("districts"), new ArrayList(), districtItem);
        return districtItem;
    }

    public static void a(JSONArray jSONArray, ArrayList<DistrictItem> arrayList, DistrictItem districtItem) throws JSONException {
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    arrayList.add(k(optJSONObject));
                }
            }
            if (districtItem != null) {
                districtItem.setSubDistrict(arrayList);
            }
        }
    }

    public static ArrayList<GeocodeAddress> l(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        ArrayList<GeocodeAddress> arrayList = new ArrayList<>();
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("geocodes")) == null || optJSONArray.length() == 0) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                GeocodeAddress geocodeAddress = new GeocodeAddress();
                geocodeAddress.setFormatAddress(a(optJSONObject, "formatted_address"));
                geocodeAddress.setProvince(a(optJSONObject, "province"));
                geocodeAddress.setCity(a(optJSONObject, "city"));
                geocodeAddress.setDistrict(a(optJSONObject, "district"));
                geocodeAddress.setTownship(a(optJSONObject, "township"));
                geocodeAddress.setNeighborhood(a(optJSONObject.optJSONObject("neighborhood"), "name"));
                geocodeAddress.setBuilding(a(optJSONObject.optJSONObject("building"), "name"));
                geocodeAddress.setAdcode(a(optJSONObject, "adcode"));
                geocodeAddress.setLatLonPoint(b(optJSONObject, "location"));
                geocodeAddress.setLevel(a(optJSONObject, "level"));
                arrayList.add(geocodeAddress);
            }
        }
        return arrayList;
    }

    public static ArrayList<Tip> m(JSONObject jSONObject) throws JSONException {
        ArrayList<Tip> arrayList = new ArrayList<>();
        JSONArray optJSONArray = jSONObject.optJSONArray("tips");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            Tip tip = new Tip();
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                tip.setName(a(optJSONObject, "name"));
                tip.setDistrict(a(optJSONObject, "district"));
                tip.setAdcode(a(optJSONObject, "adcode"));
                tip.setID(a(optJSONObject, "id"));
                tip.setAddress(a(optJSONObject, "address"));
                tip.setTypeCode(a(optJSONObject, "typecode"));
                String a2 = a(optJSONObject, "location");
                if (!TextUtils.isEmpty(a2)) {
                    String[] split = a2.split(",");
                    if (split.length == 2) {
                        tip.setPostion(new LatLonPoint(Double.parseDouble(split[1]), Double.parseDouble(split[0])));
                    }
                }
                arrayList.add(tip);
            }
        }
        return arrayList;
    }

    public static void a(JSONArray jSONArray, RegeocodeAddress regeocodeAddress) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Crossroad crossroad = new Crossroad();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                crossroad.setId(a(optJSONObject, "id"));
                crossroad.setDirection(a(optJSONObject, "direction"));
                crossroad.setDistance(k(a(optJSONObject, Tags.Nearby.DISTANCE)));
                crossroad.setCenterPoint(b(optJSONObject, "location"));
                crossroad.setFirstRoadId(a(optJSONObject, "first_id"));
                crossroad.setFirstRoadName(a(optJSONObject, "first_name"));
                crossroad.setSecondRoadId(a(optJSONObject, "second_id"));
                crossroad.setSecondRoadName(a(optJSONObject, "second_name"));
                arrayList.add(crossroad);
            }
        }
        regeocodeAddress.setCrossroads(arrayList);
    }

    public static void b(JSONArray jSONArray, RegeocodeAddress regeocodeAddress) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            RegeocodeRoad regeocodeRoad = new RegeocodeRoad();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                regeocodeRoad.setId(a(optJSONObject, "id"));
                regeocodeRoad.setName(a(optJSONObject, "name"));
                regeocodeRoad.setLatLngPoint(b(optJSONObject, "location"));
                regeocodeRoad.setDirection(a(optJSONObject, "direction"));
                regeocodeRoad.setDistance(k(a(optJSONObject, Tags.Nearby.DISTANCE)));
                arrayList.add(regeocodeRoad);
            }
        }
        regeocodeAddress.setRoads(arrayList);
    }

    public static void a(JSONObject jSONObject, RegeocodeAddress regeocodeAddress) throws JSONException {
        regeocodeAddress.setCountry(a(jSONObject, "country"));
        regeocodeAddress.setProvince(a(jSONObject, "province"));
        regeocodeAddress.setCity(a(jSONObject, "city"));
        regeocodeAddress.setCityCode(a(jSONObject, "citycode"));
        regeocodeAddress.setAdCode(a(jSONObject, "adcode"));
        regeocodeAddress.setDistrict(a(jSONObject, "district"));
        regeocodeAddress.setTownship(a(jSONObject, "township"));
        regeocodeAddress.setNeighborhood(a(jSONObject.optJSONObject("neighborhood"), "name"));
        regeocodeAddress.setBuilding(a(jSONObject.optJSONObject("building"), "name"));
        StreetNumber streetNumber = new StreetNumber();
        JSONObject optJSONObject = jSONObject.optJSONObject("streetNumber");
        streetNumber.setStreet(a(optJSONObject, "street"));
        streetNumber.setNumber(a(optJSONObject, "number"));
        streetNumber.setLatLonPoint(b(optJSONObject, "location"));
        streetNumber.setDirection(a(optJSONObject, "direction"));
        streetNumber.setDistance(k(a(optJSONObject, Tags.Nearby.DISTANCE)));
        regeocodeAddress.setStreetNumber(streetNumber);
        regeocodeAddress.setBusinessAreas(n(jSONObject));
        regeocodeAddress.setTowncode(a(jSONObject, "towncode"));
        a(regeocodeAddress);
    }

    private static void a(RegeocodeAddress regeocodeAddress) {
        if ((regeocodeAddress.getCity() == null || regeocodeAddress.getCity().length() < 1) && t(regeocodeAddress.getCityCode())) {
            regeocodeAddress.setCity(regeocodeAddress.getProvince());
        }
    }

    private static boolean t(String str) {
        if (str == null || str.length() < 1) {
            return false;
        }
        for (String trim : f4428a) {
            if (str.trim().equals(trim.trim())) {
                return true;
            }
        }
        return false;
    }

    public static List<BusinessArea> n(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("businessAreas");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            BusinessArea businessArea = new BusinessArea();
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                businessArea.setCenterPoint(b(optJSONObject, "location"));
                businessArea.setName(a(optJSONObject, "name"));
                arrayList.add(businessArea);
            }
        }
        return arrayList;
    }

    public static BusRouteResult a(String str) throws AMapException {
        JSONArray optJSONArray;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("route")) {
                return null;
            }
            BusRouteResult busRouteResult = new BusRouteResult();
            JSONObject optJSONObject = jSONObject.optJSONObject("route");
            if (optJSONObject == null) {
                return busRouteResult;
            }
            busRouteResult.setStartPos(b(optJSONObject, "origin"));
            busRouteResult.setTargetPos(b(optJSONObject, "destination"));
            busRouteResult.setTaxiCost(k(a(optJSONObject, "taxi_cost")));
            if (!optJSONObject.has("transits") || (optJSONArray = optJSONObject.optJSONArray("transits")) == null) {
                return busRouteResult;
            }
            busRouteResult.setPaths(a(optJSONArray));
            return busRouteResult;
        } catch (JSONException unused) {
            throw new AMapException(AMapException.AMAP_CLIENT_ERROR_PROTOCOL);
        }
    }

    public static List<BusPath> a(JSONArray jSONArray) throws JSONException {
        BusStep o;
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            BusPath busPath = new BusPath();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                busPath.setCost(k(a(optJSONObject, "cost")));
                busPath.setDuration(m(a(optJSONObject, "duration")));
                busPath.setNightBus(n(a(optJSONObject, "nightflag")));
                busPath.setWalkDistance(k(a(optJSONObject, "walking_distance")));
                busPath.setDistance(k(a(optJSONObject, Tags.Nearby.DISTANCE)));
                JSONArray optJSONArray = optJSONObject.optJSONArray("segments");
                if (optJSONArray != null) {
                    ArrayList arrayList2 = new ArrayList();
                    float f = 0.0f;
                    float f2 = 0.0f;
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                        if (!(optJSONObject2 == null || (o = o(optJSONObject2)) == null)) {
                            arrayList2.add(o);
                            if (o.getWalk() != null) {
                                f2 += o.getWalk().getDistance();
                            }
                            if (o.getBusLines() != null && o.getBusLines().size() > 0) {
                                f += o.getBusLines().get(0).getDistance();
                            }
                        }
                    }
                    busPath.setSteps(arrayList2);
                    busPath.setBusDistance(f);
                    busPath.setWalkDistance(f2);
                    arrayList.add(busPath);
                }
            }
        }
        return arrayList;
    }

    public static BusStep o(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        BusStep busStep = new BusStep();
        JSONObject optJSONObject = jSONObject.optJSONObject("walking");
        if (optJSONObject != null) {
            busStep.setWalk(p(optJSONObject));
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("bus");
        if (optJSONObject2 != null) {
            busStep.setBusLines(q(optJSONObject2));
        }
        JSONObject optJSONObject3 = jSONObject.optJSONObject("entrance");
        if (optJSONObject3 != null) {
            busStep.setEntrance(r(optJSONObject3));
        }
        JSONObject optJSONObject4 = jSONObject.optJSONObject("exit");
        if (optJSONObject4 != null) {
            busStep.setExit(r(optJSONObject4));
        }
        JSONObject optJSONObject5 = jSONObject.optJSONObject("railway");
        if (optJSONObject5 != null) {
            busStep.setRailway(y(optJSONObject5));
        }
        JSONObject optJSONObject6 = jSONObject.optJSONObject("taxi");
        if (optJSONObject6 != null) {
            busStep.setTaxi(E(optJSONObject6));
        }
        if ((busStep.getWalk() == null || busStep.getWalk().getSteps().size() == 0) && busStep.getBusLines().size() == 0 && busStep.getRailway() == null && busStep.getTaxi() == null) {
            return null;
        }
        return busStep;
    }

    public static RouteBusWalkItem p(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        if (jSONObject == null) {
            return null;
        }
        RouteBusWalkItem routeBusWalkItem = new RouteBusWalkItem();
        routeBusWalkItem.setOrigin(b(jSONObject, "origin"));
        routeBusWalkItem.setDestination(b(jSONObject, "destination"));
        routeBusWalkItem.setDistance(k(a(jSONObject, Tags.Nearby.DISTANCE)));
        routeBusWalkItem.setDuration(m(a(jSONObject, "duration")));
        if (!jSONObject.has("steps") || (optJSONArray = jSONObject.optJSONArray("steps")) == null) {
            return routeBusWalkItem;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(s(optJSONObject));
            }
        }
        routeBusWalkItem.setSteps(arrayList);
        return routeBusWalkItem;
    }

    public static List<RouteBusLineItem> q(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("buslines")) == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(t(optJSONObject));
            }
        }
        return arrayList;
    }

    public static Doorway r(JSONObject jSONObject) throws JSONException {
        Doorway doorway = new Doorway();
        doorway.setName(a(jSONObject, "name"));
        doorway.setLatLonPoint(b(jSONObject, "location"));
        return doorway;
    }

    public static WalkStep s(JSONObject jSONObject) throws JSONException {
        WalkStep walkStep = new WalkStep();
        walkStep.setInstruction(a(jSONObject, "instruction"));
        walkStep.setOrientation(a(jSONObject, "orientation"));
        walkStep.setRoad(a(jSONObject, "road"));
        walkStep.setDistance(k(a(jSONObject, Tags.Nearby.DISTANCE)));
        walkStep.setDuration(k(a(jSONObject, "duration")));
        walkStep.setPolyline(c(jSONObject, "polyline"));
        walkStep.setAction(a(jSONObject, "action"));
        walkStep.setAssistantAction(a(jSONObject, "assistant_action"));
        return walkStep;
    }

    public static RouteBusLineItem t(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        RouteBusLineItem routeBusLineItem = new RouteBusLineItem();
        routeBusLineItem.setDepartureBusStation(v(jSONObject.optJSONObject("departure_stop")));
        routeBusLineItem.setArrivalBusStation(v(jSONObject.optJSONObject("arrival_stop")));
        routeBusLineItem.setBusLineName(a(jSONObject, "name"));
        routeBusLineItem.setBusLineId(a(jSONObject, "id"));
        routeBusLineItem.setBusLineType(a(jSONObject, "type"));
        routeBusLineItem.setDistance(k(a(jSONObject, Tags.Nearby.DISTANCE)));
        routeBusLineItem.setDuration(k(a(jSONObject, "duration")));
        routeBusLineItem.setPolyline(c(jSONObject, "polyline"));
        routeBusLineItem.setFirstBusTime(s.c(a(jSONObject, SmartConfigDataProvider.F)));
        routeBusLineItem.setLastBusTime(s.c(a(jSONObject, Tags.ReserveOrder.END_TIME)));
        routeBusLineItem.setPassStationNum(j(a(jSONObject, "via_num")));
        routeBusLineItem.setPassStations(u(jSONObject));
        return routeBusLineItem;
    }

    public static List<BusStationItem> u(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("via_stops")) == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(v(optJSONObject));
            }
        }
        return arrayList;
    }

    public static BusStationItem v(JSONObject jSONObject) throws JSONException {
        BusStationItem busStationItem = new BusStationItem();
        busStationItem.setBusStationName(a(jSONObject, "name"));
        busStationItem.setBusStationId(a(jSONObject, "id"));
        busStationItem.setLatLonPoint(b(jSONObject, "location"));
        return busStationItem;
    }

    private static RouteRailwayItem y(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null || !jSONObject.has("id") || !jSONObject.has("name")) {
            return null;
        }
        RouteRailwayItem routeRailwayItem = new RouteRailwayItem();
        routeRailwayItem.setID(a(jSONObject, "id"));
        routeRailwayItem.setName(a(jSONObject, "name"));
        routeRailwayItem.setTime(a(jSONObject, "time"));
        routeRailwayItem.setTrip(a(jSONObject, "trip"));
        routeRailwayItem.setDistance(k(a(jSONObject, Tags.Nearby.DISTANCE)));
        routeRailwayItem.setType(a(jSONObject, "type"));
        routeRailwayItem.setDeparturestop(z(jSONObject.optJSONObject("departure_stop")));
        routeRailwayItem.setArrivalstop(z(jSONObject.optJSONObject("arrival_stop")));
        routeRailwayItem.setViastops(A(jSONObject));
        routeRailwayItem.setAlters(B(jSONObject));
        routeRailwayItem.setSpaces(C(jSONObject));
        return routeRailwayItem;
    }

    private static RailwayStationItem z(JSONObject jSONObject) throws JSONException {
        RailwayStationItem railwayStationItem = new RailwayStationItem();
        railwayStationItem.setID(a(jSONObject, "id"));
        railwayStationItem.setName(a(jSONObject, "name"));
        railwayStationItem.setLocation(b(jSONObject, "location"));
        railwayStationItem.setAdcode(a(jSONObject, "adcode"));
        railwayStationItem.setTime(a(jSONObject, "time"));
        railwayStationItem.setisStart(n(a(jSONObject, "start")));
        railwayStationItem.setisEnd(n(a(jSONObject, "end")));
        railwayStationItem.setWait(k(a(jSONObject, "wait")));
        return railwayStationItem;
    }

    private static List<RailwayStationItem> A(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("via_stops")) == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(z(optJSONObject));
            }
        }
        return arrayList;
    }

    private static List<Railway> B(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("alters")) == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                Railway railway = new Railway();
                railway.setID(a(optJSONObject, "id"));
                railway.setName(a(optJSONObject, "name"));
                arrayList.add(railway);
            }
        }
        return arrayList;
    }

    private static List<RailwaySpace> C(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("spaces")) == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(D(optJSONObject));
            }
        }
        return arrayList;
    }

    private static RailwaySpace D(JSONObject jSONObject) throws JSONException {
        return new RailwaySpace(a(jSONObject, "code"), k(a(jSONObject, "cost")));
    }

    private static TaxiItem E(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        TaxiItem taxiItem = new TaxiItem();
        taxiItem.setOrigin(b(jSONObject, "origin"));
        taxiItem.setDestination(b(jSONObject, "destination"));
        taxiItem.setDistance(k(a(jSONObject, Tags.Nearby.DISTANCE)));
        taxiItem.setDuration(k(a(jSONObject, "duration")));
        taxiItem.setSname(a(jSONObject, "sname"));
        taxiItem.setTname(a(jSONObject, "tname"));
        return taxiItem;
    }

    public static DriveRouteResult b(String str) throws AMapException {
        JSONArray optJSONArray;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("route")) {
                return null;
            }
            DriveRouteResult driveRouteResult = new DriveRouteResult();
            JSONObject optJSONObject = jSONObject.optJSONObject("route");
            if (optJSONObject == null) {
                return driveRouteResult;
            }
            driveRouteResult.setStartPos(b(optJSONObject, "origin"));
            driveRouteResult.setTargetPos(b(optJSONObject, "destination"));
            driveRouteResult.setTaxiCost(k(a(optJSONObject, "taxi_cost")));
            if (!optJSONObject.has("paths") || (optJSONArray = optJSONObject.optJSONArray("paths")) == null) {
                return driveRouteResult;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                DrivePath drivePath = new DrivePath();
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                if (optJSONObject2 != null) {
                    drivePath.setDistance(k(a(optJSONObject2, Tags.Nearby.DISTANCE)));
                    drivePath.setDuration(m(a(optJSONObject2, "duration")));
                    drivePath.setStrategy(a(optJSONObject2, Constants.Name.STRATEGY));
                    drivePath.setTolls(k(a(optJSONObject2, "tolls")));
                    drivePath.setTollDistance(k(a(optJSONObject2, "toll_distance")));
                    drivePath.setTotalTrafficlights(j(a(optJSONObject2, "traffic_lights")));
                    drivePath.setRestriction(j(a(optJSONObject2, "restriction")));
                    JSONArray optJSONArray2 = optJSONObject2.optJSONArray("steps");
                    if (optJSONArray2 != null) {
                        ArrayList arrayList2 = new ArrayList();
                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                            DriveStep driveStep = new DriveStep();
                            JSONObject optJSONObject3 = optJSONArray2.optJSONObject(i2);
                            if (optJSONObject3 != null) {
                                driveStep.setInstruction(a(optJSONObject3, "instruction"));
                                driveStep.setOrientation(a(optJSONObject3, "orientation"));
                                driveStep.setRoad(a(optJSONObject3, "road"));
                                driveStep.setDistance(k(a(optJSONObject3, Tags.Nearby.DISTANCE)));
                                driveStep.setTolls(k(a(optJSONObject3, "tolls")));
                                driveStep.setTollDistance(k(a(optJSONObject3, "toll_distance")));
                                driveStep.setTollRoad(a(optJSONObject3, "toll_road"));
                                driveStep.setDuration(k(a(optJSONObject3, "duration")));
                                driveStep.setPolyline(c(optJSONObject3, "polyline"));
                                driveStep.setAction(a(optJSONObject3, "action"));
                                driveStep.setAssistantAction(a(optJSONObject3, "assistant_action"));
                                a(driveStep, optJSONObject3);
                                b(driveStep, optJSONObject3);
                                arrayList2.add(driveStep);
                            }
                        }
                        drivePath.setSteps(arrayList2);
                        arrayList.add(drivePath);
                    }
                }
            }
            driveRouteResult.setPaths(arrayList);
            return driveRouteResult;
        } catch (JSONException e) {
            s.a(e, "JSONHelper", "parseDriveRoute");
            throw new AMapException(AMapException.AMAP_CLIENT_ERROR_PROTOCOL);
        } catch (Throwable th) {
            s.a(th, "JSONHelper", "parseDriveRouteThrowable");
            throw new AMapException(AMapException.AMAP_CLIENT_ERROR_PROTOCOL);
        }
    }

    private static void b(DriveStep driveStep, JSONObject jSONObject) throws AMapException {
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("tmcs");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    TMC tmc = new TMC();
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        tmc.setDistance(j(a(optJSONObject, Tags.Nearby.DISTANCE)));
                        tmc.setStatus(a(optJSONObject, "status"));
                        tmc.setPolyline(c(optJSONObject, "polyline"));
                        arrayList.add(tmc);
                    }
                }
                driveStep.setTMCs(arrayList);
            }
        } catch (JSONException e) {
            s.a(e, "JSONHelper", "parseTMCs");
            throw new AMapException(AMapException.AMAP_CLIENT_ERROR_PROTOCOL);
        }
    }

    public static void a(DriveStep driveStep, JSONObject jSONObject) throws AMapException {
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("cities");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    RouteSearchCity routeSearchCity = new RouteSearchCity();
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        routeSearchCity.setSearchCityName(a(optJSONObject, "name"));
                        routeSearchCity.setSearchCitycode(a(optJSONObject, "citycode"));
                        routeSearchCity.setSearchCityhAdCode(a(optJSONObject, "adcode"));
                        a(routeSearchCity, optJSONObject);
                        arrayList.add(routeSearchCity);
                    }
                }
                driveStep.setRouteSearchCityList(arrayList);
            }
        } catch (JSONException e) {
            s.a(e, "JSONHelper", "parseCrossCity");
            throw new AMapException(AMapException.AMAP_CLIENT_ERROR_PROTOCOL);
        }
    }

    public static void a(RouteSearchCity routeSearchCity, JSONObject jSONObject) throws AMapException {
        if (jSONObject.has("districts")) {
            try {
                ArrayList arrayList = new ArrayList();
                JSONArray optJSONArray = jSONObject.optJSONArray("districts");
                if (optJSONArray == null) {
                    routeSearchCity.setDistricts(arrayList);
                    return;
                }
                for (int i = 0; i < optJSONArray.length(); i++) {
                    District district = new District();
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        district.setDistrictName(a(optJSONObject, "name"));
                        district.setDistrictAdcode(a(optJSONObject, "adcode"));
                        arrayList.add(district);
                    }
                }
                routeSearchCity.setDistricts(arrayList);
            } catch (JSONException e) {
                s.a(e, "JSONHelper", "parseCrossDistricts");
                throw new AMapException(AMapException.AMAP_CLIENT_ERROR_PROTOCOL);
            }
        }
    }

    public static WalkRouteResult c(String str) throws AMapException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("route")) {
                return null;
            }
            WalkRouteResult walkRouteResult = new WalkRouteResult();
            JSONObject optJSONObject = jSONObject.optJSONObject("route");
            walkRouteResult.setStartPos(b(optJSONObject, "origin"));
            walkRouteResult.setTargetPos(b(optJSONObject, "destination"));
            if (!optJSONObject.has("paths")) {
                return walkRouteResult;
            }
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = optJSONObject.optJSONArray("paths");
            if (optJSONArray == null) {
                walkRouteResult.setPaths(arrayList);
                return walkRouteResult;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                WalkPath walkPath = new WalkPath();
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                if (optJSONObject2 != null) {
                    walkPath.setDistance(k(a(optJSONObject2, Tags.Nearby.DISTANCE)));
                    walkPath.setDuration(m(a(optJSONObject2, "duration")));
                    if (optJSONObject2.has("steps")) {
                        JSONArray optJSONArray2 = optJSONObject2.optJSONArray("steps");
                        ArrayList arrayList2 = new ArrayList();
                        if (optJSONArray2 != null) {
                            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                WalkStep walkStep = new WalkStep();
                                JSONObject optJSONObject3 = optJSONArray2.optJSONObject(i2);
                                if (optJSONObject3 != null) {
                                    walkStep.setInstruction(a(optJSONObject3, "instruction"));
                                    walkStep.setOrientation(a(optJSONObject3, "orientation"));
                                    walkStep.setRoad(a(optJSONObject3, "road"));
                                    walkStep.setDistance(k(a(optJSONObject3, Tags.Nearby.DISTANCE)));
                                    walkStep.setDuration(k(a(optJSONObject3, "duration")));
                                    walkStep.setPolyline(c(optJSONObject3, "polyline"));
                                    walkStep.setAction(a(optJSONObject3, "action"));
                                    walkStep.setAssistantAction(a(optJSONObject3, "assistant_action"));
                                    arrayList2.add(walkStep);
                                }
                            }
                            walkPath.setSteps(arrayList2);
                        }
                    }
                    arrayList.add(walkPath);
                }
            }
            walkRouteResult.setPaths(arrayList);
            return walkRouteResult;
        } catch (JSONException e) {
            s.a(e, "JSONHelper", "parseWalkRoute");
            throw new AMapException(AMapException.AMAP_CLIENT_ERROR_PROTOCOL);
        }
    }

    public static LocalWeatherLive d(String str) throws AMapException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("lives")) {
                return null;
            }
            LocalWeatherLive localWeatherLive = new LocalWeatherLive();
            JSONArray optJSONArray = jSONObject.optJSONArray("lives");
            if (optJSONArray != null) {
                if (optJSONArray.length() > 0) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(0);
                    if (optJSONObject == null) {
                        return localWeatherLive;
                    }
                    localWeatherLive.setAdCode(a(optJSONObject, "adcode"));
                    localWeatherLive.setProvince(a(optJSONObject, "province"));
                    localWeatherLive.setCity(a(optJSONObject, "city"));
                    localWeatherLive.setWeather(a(optJSONObject, AreaPropInfo.h));
                    localWeatherLive.setTemperature(a(optJSONObject, AreaPropInfo.n));
                    localWeatherLive.setWindDirection(a(optJSONObject, "winddirection"));
                    localWeatherLive.setWindPower(a(optJSONObject, "windpower"));
                    localWeatherLive.setHumidity(a(optJSONObject, TopWidgetDataManager.m));
                    localWeatherLive.setReportTime(a(optJSONObject, "reporttime"));
                    return localWeatherLive;
                }
            }
            return localWeatherLive;
        } catch (JSONException e) {
            s.a(e, "JSONHelper", "WeatherForecastResult");
            throw new AMapException(AMapException.AMAP_CLIENT_ERROR_PROTOCOL);
        }
    }

    public static LocalWeatherForecast e(String str) throws AMapException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("forecasts")) {
                return null;
            }
            LocalWeatherForecast localWeatherForecast = new LocalWeatherForecast();
            JSONArray jSONArray = jSONObject.getJSONArray("forecasts");
            if (jSONArray != null) {
                if (jSONArray.length() > 0) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(0);
                    if (optJSONObject == null) {
                        return localWeatherForecast;
                    }
                    localWeatherForecast.setCity(a(optJSONObject, "city"));
                    localWeatherForecast.setAdCode(a(optJSONObject, "adcode"));
                    localWeatherForecast.setProvince(a(optJSONObject, "province"));
                    localWeatherForecast.setReportTime(a(optJSONObject, "reporttime"));
                    if (!optJSONObject.has("casts")) {
                        return localWeatherForecast;
                    }
                    ArrayList arrayList = new ArrayList();
                    JSONArray optJSONArray = optJSONObject.optJSONArray("casts");
                    if (optJSONArray != null) {
                        if (optJSONArray.length() > 0) {
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                LocalDayWeatherForecast localDayWeatherForecast = new LocalDayWeatherForecast();
                                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                                if (optJSONObject2 != null) {
                                    localDayWeatherForecast.setDate(a(optJSONObject2, "date"));
                                    localDayWeatherForecast.setWeek(a(optJSONObject2, AreaPropInfo.Forecast.ay));
                                    localDayWeatherForecast.setDayWeather(a(optJSONObject2, "dayweather"));
                                    localDayWeatherForecast.setNightWeather(a(optJSONObject2, "nightweather"));
                                    localDayWeatherForecast.setDayTemp(a(optJSONObject2, "daytemp"));
                                    localDayWeatherForecast.setNightTemp(a(optJSONObject2, "nighttemp"));
                                    localDayWeatherForecast.setDayWindDirection(a(optJSONObject2, "daywind"));
                                    localDayWeatherForecast.setNightWindDirection(a(optJSONObject2, "nightwind"));
                                    localDayWeatherForecast.setDayWindPower(a(optJSONObject2, "daypower"));
                                    localDayWeatherForecast.setNightWindPower(a(optJSONObject2, "nightpower"));
                                    arrayList.add(localDayWeatherForecast);
                                }
                            }
                            localWeatherForecast.setWeatherForecast(arrayList);
                            return localWeatherForecast;
                        }
                    }
                    localWeatherForecast.setWeatherForecast(arrayList);
                    return localWeatherForecast;
                }
            }
            return localWeatherForecast;
        } catch (JSONException e) {
            s.a(e, "JSONHelper", "WeatherForecastResult");
            throw new AMapException(AMapException.AMAP_CLIENT_ERROR_PROTOCOL);
        }
    }

    public static String a(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null && jSONObject.has(str) && !jSONObject.optString(str).equals(XMPConst.ai)) {
            return jSONObject.optString(str).trim();
        }
        return "";
    }

    public static LatLonPoint b(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null && jSONObject.has(str)) {
            return g(jSONObject.optString(str));
        }
        return null;
    }

    public static ArrayList<LatLonPoint> c(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject.has(str)) {
            return f(jSONObject.optString(str));
        }
        return null;
    }

    public static ArrayList<LatLonPoint> f(String str) {
        ArrayList<LatLonPoint> arrayList = new ArrayList<>();
        String[] split = str.split(i.b);
        for (String g : split) {
            arrayList.add(g(g));
        }
        return arrayList;
    }

    public static LatLonPoint g(String str) {
        if (str == null || str.equals("") || str.equals(XMPConst.ai)) {
            return null;
        }
        String[] split = str.split(",| ");
        if (split.length != 2) {
            return null;
        }
        return new LatLonPoint(Double.parseDouble(split[1]), Double.parseDouble(split[0]));
    }

    public static boolean h(String str) {
        return str == null || str.equals("") || str.equals("0");
    }

    public static boolean i(String str) {
        return str == null || str.equals("");
    }

    public static int j(String str) {
        if (str == null || str.equals("") || str.equals(XMPConst.ai)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            s.a(e, "JSONHelper", "str2int");
            return 0;
        }
    }

    public static float k(String str) {
        if (str == null || str.equals("") || str.equals(XMPConst.ai)) {
            return 0.0f;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            s.a(e, "JSONHelper", "str2float");
            return 0.0f;
        }
    }

    public static double l(String str) {
        if (str == null || str.equals("") || str.equals(XMPConst.ai)) {
            return 0.0d;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            s.a(e, "JSONHelper", "str2float");
            return 0.0d;
        }
    }

    public static long m(String str) {
        if (str == null || str.equals("") || str.equals(XMPConst.ai)) {
            return 0;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            s.a(e, "JSONHelper", "str2long");
            return 0;
        }
    }

    public static boolean n(String str) {
        if (str == null || str.equals("") || str.equals(XMPConst.ai) || str.equals("0") || !str.equals("1")) {
            return false;
        }
        return true;
    }

    private static IndoorData d(JSONObject jSONObject, String str) throws JSONException {
        int i;
        JSONObject optJSONObject;
        String str2 = "";
        String str3 = "";
        if (!jSONObject.has(str) || (optJSONObject = jSONObject.optJSONObject(str)) == null || !optJSONObject.has(MibiConstants.fe) || !optJSONObject.has(Tags.Kuwan.COMMENT_FLOOR)) {
            i = 0;
        } else {
            str2 = a(optJSONObject, MibiConstants.fe);
            i = j(a(optJSONObject, Tags.Kuwan.COMMENT_FLOOR));
            str3 = a(optJSONObject, "truefloor");
        }
        return new IndoorData(str2, i, str3);
    }

    public static void c(JSONArray jSONArray, RegeocodeAddress regeocodeAddress) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            AoiItem aoiItem = new AoiItem();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                aoiItem.setId(a(optJSONObject, "id"));
                aoiItem.setName(a(optJSONObject, "name"));
                aoiItem.setAdcode(a(optJSONObject, "adcode"));
                aoiItem.setLocation(b(optJSONObject, "location"));
                aoiItem.setArea(Float.valueOf(k(a(optJSONObject, "area"))));
                arrayList.add(aoiItem);
            }
        }
        regeocodeAddress.setAois(arrayList);
    }

    public static void a(PoiItem poiItem, JSONObject jSONObject) throws JSONException {
        List<Photo> F = F(jSONObject.optJSONObject("deep_info"));
        if (F.size() == 0) {
            F = F(jSONObject);
        }
        poiItem.setPhotos(F);
    }

    private static List<Photo> F(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null || !jSONObject.has(PlaceFields.PHOTOS_PROFILE)) {
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(PlaceFields.PHOTOS_PROFILE);
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            Photo photo = new Photo();
            photo.setTitle(a(optJSONObject, "title"));
            photo.setUrl(a(optJSONObject, "url"));
            arrayList.add(photo);
        }
        return arrayList;
    }

    private static PoiItemExtension e(JSONObject jSONObject, String str) throws JSONException {
        JSONObject optJSONObject;
        String str2 = "";
        String str3 = "";
        if (jSONObject.has(str) && (optJSONObject = jSONObject.optJSONObject(str)) != null) {
            str2 = a(optJSONObject, "open_time");
            str3 = a(optJSONObject, "rating");
        }
        return new PoiItemExtension(str2, str3);
    }

    public static ArrayList<RoutePOIItem> w(JSONObject jSONObject) throws JSONException {
        ArrayList<RoutePOIItem> arrayList = new ArrayList<>();
        if (jSONObject == null) {
            return arrayList;
        }
        Object opt = jSONObject.opt("pois");
        if (opt instanceof JSONArray) {
            JSONArray optJSONArray = jSONObject.optJSONArray("pois");
            if (optJSONArray == null || optJSONArray.length() == 0) {
                return arrayList;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    arrayList.add(G(optJSONObject));
                }
            }
        } else if (opt instanceof JSONObject) {
            arrayList.add(G(((JSONObject) opt).optJSONObject("poi")));
        }
        return arrayList;
    }

    private static RoutePOIItem G(JSONObject jSONObject) throws JSONException {
        RoutePOIItem routePOIItem = new RoutePOIItem();
        routePOIItem.setID(a(jSONObject, "id"));
        routePOIItem.setTitle(a(jSONObject, "name"));
        routePOIItem.setPoint(b(jSONObject, "location"));
        routePOIItem.setDistance(k(a(jSONObject, Tags.Nearby.DISTANCE)));
        routePOIItem.setDuration(k(a(jSONObject, "duration")));
        return routePOIItem;
    }

    public static RideRouteResult o(String str) throws AMapException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("data")) {
                return null;
            }
            RideRouteResult rideRouteResult = new RideRouteResult();
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            rideRouteResult.setStartPos(b(optJSONObject, "origin"));
            rideRouteResult.setTargetPos(b(optJSONObject, "destination"));
            ArrayList arrayList = new ArrayList();
            Object opt = optJSONObject.opt("paths");
            if (opt == null) {
                rideRouteResult.setPaths(arrayList);
                return rideRouteResult;
            }
            if (opt instanceof JSONArray) {
                JSONArray optJSONArray = optJSONObject.optJSONArray("paths");
                for (int i = 0; i < optJSONArray.length(); i++) {
                    RidePath H = H(optJSONArray.optJSONObject(i));
                    if (H != null) {
                        arrayList.add(H);
                    }
                }
            } else if (opt instanceof JSONObject) {
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("paths");
                if (!optJSONObject2.has("path")) {
                    rideRouteResult.setPaths(arrayList);
                    return rideRouteResult;
                }
                RidePath H2 = H(optJSONObject2.optJSONObject("path"));
                if (H2 != null) {
                    arrayList.add(H2);
                }
            }
            rideRouteResult.setPaths(arrayList);
            return rideRouteResult;
        } catch (JSONException e) {
            s.a(e, "JSONHelper", "parseRideRoute");
            throw new AMapException(AMapException.AMAP_CLIENT_ERROR_PROTOCOL);
        }
    }

    private static RidePath H(JSONObject jSONObject) throws AMapException {
        RidePath ridePath = new RidePath();
        if (jSONObject == null) {
            return null;
        }
        try {
            ridePath.setDistance(k(a(jSONObject, Tags.Nearby.DISTANCE)));
            ridePath.setDuration(m(a(jSONObject, "duration")));
            if (jSONObject.has("steps")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("steps");
                ArrayList arrayList = new ArrayList();
                if (optJSONArray == null) {
                    return null;
                }
                for (int i = 0; i < optJSONArray.length(); i++) {
                    RideStep rideStep = new RideStep();
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        rideStep.setInstruction(a(optJSONObject, "instruction"));
                        rideStep.setOrientation(a(optJSONObject, "orientation"));
                        rideStep.setRoad(a(optJSONObject, "road"));
                        rideStep.setDistance(k(a(optJSONObject, Tags.Nearby.DISTANCE)));
                        rideStep.setDuration(k(a(optJSONObject, "duration")));
                        rideStep.setPolyline(c(optJSONObject, "polyline"));
                        rideStep.setAction(a(optJSONObject, "action"));
                        rideStep.setAssistantAction(a(optJSONObject, "assistant_action"));
                        arrayList.add(rideStep);
                    }
                }
                ridePath.setSteps(arrayList);
            }
            return ridePath;
        } catch (JSONException e) {
            s.a(e, "JSONHelper", "parseRidePath");
            throw new AMapException(AMapException.AMAP_CLIENT_ERROR_PROTOCOL);
        }
    }

    public static TrafficStatusResult p(String str) throws AMapException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("trafficinfo")) {
                return null;
            }
            TrafficStatusResult trafficStatusResult = new TrafficStatusResult();
            JSONObject optJSONObject = jSONObject.optJSONObject("trafficinfo");
            trafficStatusResult.setDescription(a(optJSONObject, "description"));
            if (optJSONObject.has("evaluation")) {
                TrafficStatusEvaluation trafficStatusEvaluation = new TrafficStatusEvaluation();
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("evaluation");
                trafficStatusEvaluation.setExpedite(a(optJSONObject2, "expedite"));
                trafficStatusEvaluation.setCongested(a(optJSONObject2, "congested"));
                trafficStatusEvaluation.setBlocked(a(optJSONObject2, "blocked"));
                trafficStatusEvaluation.setUnknown(a(optJSONObject2, "unknown"));
                trafficStatusEvaluation.setStatus(a(optJSONObject2, "status"));
                trafficStatusEvaluation.setDescription(a(optJSONObject2, "description"));
                trafficStatusResult.setEvaluation(trafficStatusEvaluation);
            }
            if (!optJSONObject.has("roads")) {
                return trafficStatusResult;
            }
            ArrayList arrayList = new ArrayList();
            Object opt = optJSONObject.opt("roads");
            if (opt == null) {
                trafficStatusResult.setRoads(arrayList);
                return trafficStatusResult;
            }
            if (opt instanceof JSONArray) {
                JSONArray optJSONArray = optJSONObject.optJSONArray("roads");
                for (int i = 0; i < optJSONArray.length(); i++) {
                    TrafficStatusInfo I = I(optJSONArray.optJSONObject(i));
                    if (I != null) {
                        arrayList.add(I);
                    }
                }
            }
            trafficStatusResult.setRoads(arrayList);
            return trafficStatusResult;
        } catch (JSONException e) {
            s.a(e, "JSONHelper", "parseRideRoute");
            throw new AMapException(AMapException.AMAP_CLIENT_ERROR_PROTOCOL);
        }
    }

    private static TrafficStatusInfo I(JSONObject jSONObject) throws AMapException {
        TrafficStatusInfo trafficStatusInfo = new TrafficStatusInfo();
        try {
            trafficStatusInfo.setName(a(jSONObject, "name"));
            trafficStatusInfo.setStatus(a(jSONObject, "status"));
            trafficStatusInfo.setAngle(j(a(jSONObject, LinearGradientManager.PROP_ANGLE)));
            trafficStatusInfo.setSpeed(k(a(jSONObject, "speed")));
            trafficStatusInfo.setDirection(a(jSONObject, "direction"));
            trafficStatusInfo.setLcodes(a(jSONObject, "lcodes"));
            trafficStatusInfo.setCoordinates(c(jSONObject, "polyline"));
            return trafficStatusInfo;
        } catch (JSONException e) {
            s.a(e, "JSONHelper", "parseRoadInfo");
            throw new AMapException(AMapException.AMAP_CLIENT_ERROR_PROTOCOL);
        }
    }

    public static DistanceResult q(String str) throws AMapException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has(Tags.MiHomeStorage.RESULTS)) {
                return null;
            }
            DistanceResult distanceResult = new DistanceResult();
            JSONArray optJSONArray = jSONObject.optJSONArray(Tags.MiHomeStorage.RESULTS);
            ArrayList arrayList = new ArrayList();
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                DistanceItem distanceItem = new DistanceItem();
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                distanceItem.setOriginId(j(a(jSONObject2, "origin_id")));
                distanceItem.setDestId(j(a(jSONObject2, "dest_id")));
                distanceItem.setDistance(k(a(jSONObject2, Tags.Nearby.DISTANCE)));
                distanceItem.setDuration(k(a(jSONObject2, "duration")));
                String a2 = a(jSONObject2, "info");
                if (!TextUtils.isEmpty(a2)) {
                    distanceItem.setErrorInfo(a2);
                    distanceItem.setErrorCode(j(a(jSONObject2, "code")));
                }
                arrayList.add(distanceItem);
            }
            distanceResult.setDistanceResults(arrayList);
            return distanceResult;
        } catch (JSONException e) {
            s.a(e, "JSONHelper", "parseRouteDistance");
            throw new AMapException(AMapException.AMAP_CLIENT_ERROR_PROTOCOL);
        }
    }

    public static TruckRouteRestult r(String str) throws AMapException {
        JSONArray optJSONArray;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("data")) {
                return null;
            }
            TruckRouteRestult truckRouteRestult = new TruckRouteRestult();
            JSONObject optJSONObject = jSONObject.optJSONObject("data").optJSONObject("route");
            truckRouteRestult.setStartPos(b(optJSONObject, "origin"));
            truckRouteRestult.setTargetPos(b(optJSONObject, "destination"));
            if (!optJSONObject.has("paths") || (optJSONArray = optJSONObject.optJSONArray("paths")) == null) {
                return truckRouteRestult;
            }
            ArrayList arrayList = new ArrayList();
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                TruckPath truckPath = new TruckPath();
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                truckPath.setDistance(k(a(jSONObject2, Tags.Nearby.DISTANCE)));
                truckPath.setDuration(m(a(jSONObject2, "duration")));
                truckPath.setStrategy(a(jSONObject2, Constants.Name.STRATEGY));
                truckPath.setTolls(k(a(jSONObject2, "tolls")));
                truckPath.setTollDistance(k(a(jSONObject2, "toll_distance")));
                truckPath.setTotalTrafficlights(j(a(jSONObject2, "traffic_lights")));
                truckPath.setRestriction(j(a(jSONObject2, "restriction")));
                JSONArray optJSONArray2 = jSONObject2.optJSONArray("steps");
                if (optJSONArray2 != null) {
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        TruckStep truckStep = new TruckStep();
                        JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                        if (optJSONObject2 != null) {
                            truckStep.setInstruction(a(optJSONObject2, "instruction"));
                            truckStep.setOrientation(a(optJSONObject2, "orientation"));
                            truckStep.setRoad(a(optJSONObject2, "road"));
                            truckStep.setDistance(k(a(optJSONObject2, Tags.Nearby.DISTANCE)));
                            truckStep.setTolls(k(a(optJSONObject2, "tolls")));
                            truckStep.setTollDistance(k(a(optJSONObject2, "toll_distance")));
                            truckStep.setTollRoad(a(optJSONObject2, "toll_road"));
                            truckStep.setDuration(k(a(optJSONObject2, "duration")));
                            truckStep.setPolyline(c(optJSONObject2, "polyline"));
                            truckStep.setAction(a(optJSONObject2, "action"));
                            truckStep.setAssistantAction(a(optJSONObject2, "assistant_action"));
                            a(truckStep, optJSONObject2);
                            b(truckStep, optJSONObject2);
                            arrayList2.add(truckStep);
                        }
                    }
                    truckPath.setSteps(arrayList2);
                    arrayList.add(truckPath);
                }
            }
            truckRouteRestult.setPaths(arrayList);
            return truckRouteRestult;
        } catch (JSONException e) {
            s.a(e, "JSONHelper", "parseTruckRoute");
            throw new AMapException(AMapException.AMAP_CLIENT_ERROR_PROTOCOL);
        }
    }

    public static void a(TruckStep truckStep, JSONObject jSONObject) throws AMapException {
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("cities");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    RouteSearchCity routeSearchCity = new RouteSearchCity();
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        routeSearchCity.setSearchCityName(a(optJSONObject, "name"));
                        routeSearchCity.setSearchCitycode(a(optJSONObject, "citycode"));
                        routeSearchCity.setSearchCityhAdCode(a(optJSONObject, "adcode"));
                        a(routeSearchCity, optJSONObject);
                        arrayList.add(routeSearchCity);
                    }
                }
                truckStep.setRouteSearchCityList(arrayList);
            }
        } catch (JSONException e) {
            s.a(e, "JSONHelper", "parseCrossCity");
            throw new AMapException(AMapException.AMAP_CLIENT_ERROR_PROTOCOL);
        }
    }

    private static void b(TruckStep truckStep, JSONObject jSONObject) throws AMapException {
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("tmcs");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    TMC tmc = new TMC();
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        tmc.setDistance(j(a(optJSONObject, Tags.Nearby.DISTANCE)));
                        tmc.setStatus(a(optJSONObject, "status"));
                        tmc.setPolyline(c(optJSONObject, "polyline"));
                        arrayList.add(tmc);
                    }
                }
                truckStep.setTMCs(arrayList);
            }
        } catch (JSONException e) {
            s.a(e, "JSONHelper", "parseTMCs");
            throw new AMapException(AMapException.AMAP_CLIENT_ERROR_PROTOCOL);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:72:0x019d A[Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x019e A[Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.amap.api.services.route.DriveRoutePlanResult s(java.lang.String r16) throws com.amap.api.services.core.AMapException {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r1 = r16
            r0.<init>(r1)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            java.lang.String r1 = "errcode"
            boolean r1 = r0.has(r1)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r1 == 0) goto L_0x001e
            java.lang.String r1 = "errcode"
            r0.optInt(r1)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            java.lang.String r1 = "errmsg"
            r0.optString(r1)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            java.lang.String r1 = "errdetail"
            r0.optString(r1)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
        L_0x001e:
            java.lang.String r1 = "data"
            boolean r1 = r0.has(r1)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r1 != 0) goto L_0x0028
            r0 = 0
            return r0
        L_0x0028:
            com.amap.api.services.route.DriveRoutePlanResult r1 = new com.amap.api.services.route.DriveRoutePlanResult     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r1.<init>()     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            java.lang.String r2 = "data"
            org.json.JSONObject r0 = r0.optJSONObject(r2)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r0 != 0) goto L_0x0036
            return r1
        L_0x0036:
            java.lang.String r2 = "paths"
            boolean r2 = r0.has(r2)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r2 != 0) goto L_0x003f
            return r1
        L_0x003f:
            java.lang.String r2 = "paths"
            org.json.JSONArray r2 = r0.optJSONArray(r2)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r2 != 0) goto L_0x0048
            return r1
        L_0x0048:
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r3.<init>()     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r5 = 0
        L_0x004e:
            int r6 = r2.length()     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r7 = 1
            if (r5 >= r6) goto L_0x00ea
            com.amap.api.services.route.DrivePlanPath r6 = new com.amap.api.services.route.DrivePlanPath     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r6.<init>()     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            org.json.JSONObject r8 = r2.optJSONObject(r5)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r8 != 0) goto L_0x0062
            goto L_0x00e6
        L_0x0062:
            java.lang.String r9 = "distance"
            java.lang.String r9 = a((org.json.JSONObject) r8, (java.lang.String) r9)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            float r9 = k((java.lang.String) r9)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r6.setDistance(r9)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            java.lang.String r9 = "traffic_lights"
            java.lang.String r9 = a((org.json.JSONObject) r8, (java.lang.String) r9)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            int r9 = j((java.lang.String) r9)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r6.setTrafficLights(r9)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            java.lang.String r9 = "steps"
            org.json.JSONArray r8 = r8.optJSONArray(r9)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r8 != 0) goto L_0x0087
            goto L_0x00e6
        L_0x0087:
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r9.<init>()     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r10 = 0
        L_0x008d:
            int r11 = r8.length()     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r10 >= r11) goto L_0x00e0
            com.amap.api.services.route.DrivePlanStep r11 = new com.amap.api.services.route.DrivePlanStep     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r11.<init>()     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            org.json.JSONObject r12 = r8.optJSONObject(r10)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r12 != 0) goto L_0x009f
            goto L_0x00dd
        L_0x009f:
            java.lang.String r13 = "adcode"
            java.lang.String r13 = a((org.json.JSONObject) r12, (java.lang.String) r13)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r11.setAdCode(r13)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            java.lang.String r13 = "road"
            java.lang.String r13 = a((org.json.JSONObject) r12, (java.lang.String) r13)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r11.setRoad(r13)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            java.lang.String r13 = "distance"
            java.lang.String r13 = a((org.json.JSONObject) r12, (java.lang.String) r13)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            float r13 = k((java.lang.String) r13)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r11.setDistance(r13)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            java.lang.String r13 = "toll"
            java.lang.String r13 = a((org.json.JSONObject) r12, (java.lang.String) r13)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            int r13 = j((java.lang.String) r13)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r13 != r7) goto L_0x00cd
            r13 = 1
            goto L_0x00ce
        L_0x00cd:
            r13 = 0
        L_0x00ce:
            r11.setToll(r13)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            java.lang.String r13 = "polyline"
            java.util.ArrayList r12 = c((org.json.JSONObject) r12, (java.lang.String) r13)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r11.setPolyline(r12)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r9.add(r11)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
        L_0x00dd:
            int r10 = r10 + 1
            goto L_0x008d
        L_0x00e0:
            r6.setSteps(r9)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r3.add(r6)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
        L_0x00e6:
            int r5 = r5 + 1
            goto L_0x004e
        L_0x00ea:
            r1.setPaths(r3)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            java.lang.String r2 = "time_infos"
            boolean r2 = r0.has(r2)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r2 != 0) goto L_0x00f7
            return r1
        L_0x00f7:
            java.lang.String r2 = "time_infos"
            org.json.JSONArray r0 = r0.optJSONArray(r2)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r0 != 0) goto L_0x0101
            return r1
        L_0x0101:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r2.<init>()     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r3 = 0
        L_0x0107:
            int r5 = r0.length()     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r3 >= r5) goto L_0x01f0
            com.amap.api.services.route.TimeInfo r5 = new com.amap.api.services.route.TimeInfo     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r5.<init>()     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            org.json.JSONObject r6 = r0.optJSONObject(r3)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r6 != 0) goto L_0x011a
            goto L_0x01ec
        L_0x011a:
            java.lang.String r8 = "starttime"
            boolean r8 = r6.has(r8)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r8 != 0) goto L_0x0124
            return r1
        L_0x0124:
            java.lang.String r8 = "starttime"
            java.lang.String r8 = a((org.json.JSONObject) r6, (java.lang.String) r8)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            long r8 = m((java.lang.String) r8)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r5.setStartTime(r8)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            java.lang.String r8 = "elements"
            org.json.JSONArray r6 = r6.optJSONArray(r8)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r6 != 0) goto L_0x013c
            goto L_0x01ec
        L_0x013c:
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r8.<init>()     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r9 = 0
        L_0x0142:
            int r10 = r6.length()     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r9 >= r10) goto L_0x01e6
            com.amap.api.services.route.TimeInfosElement r10 = new com.amap.api.services.route.TimeInfosElement     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r10.<init>()     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            org.json.JSONObject r11 = r6.optJSONObject(r9)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r11 != 0) goto L_0x0155
            goto L_0x01e2
        L_0x0155:
            java.lang.String r12 = "pathindex"
            java.lang.String r12 = a((org.json.JSONObject) r11, (java.lang.String) r12)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            int r12 = j((java.lang.String) r12)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r10.setPathindex(r12)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            java.lang.String r12 = "duration"
            java.lang.String r12 = a((org.json.JSONObject) r11, (java.lang.String) r12)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            float r12 = k((java.lang.String) r12)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r10.setDuration(r12)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            java.lang.String r12 = "tolls"
            java.lang.String r12 = a((org.json.JSONObject) r11, (java.lang.String) r12)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            float r12 = k((java.lang.String) r12)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r10.setTolls(r12)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            java.lang.String r12 = "restriction"
            java.lang.String r12 = a((org.json.JSONObject) r11, (java.lang.String) r12)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            int r12 = j((java.lang.String) r12)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r13 = 2
            if (r12 == r13) goto L_0x0190
            r13 = -1
            if (r12 != r13) goto L_0x018e
            goto L_0x0190
        L_0x018e:
            r12 = 1
            goto L_0x0191
        L_0x0190:
            r12 = 0
        L_0x0191:
            r10.setRestriction(r12)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            java.lang.String r12 = "tmcs"
            org.json.JSONArray r11 = r11.optJSONArray(r12)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r11 != 0) goto L_0x019e
            goto L_0x01e2
        L_0x019e:
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r12.<init>()     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r13 = 0
        L_0x01a4:
            int r14 = r11.length()     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r13 >= r14) goto L_0x01dc
            com.amap.api.services.route.TMC r14 = new com.amap.api.services.route.TMC     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r14.<init>()     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            org.json.JSONObject r15 = r11.optJSONObject(r13)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            if (r15 != 0) goto L_0x01b6
            goto L_0x01d9
        L_0x01b6:
            java.lang.String r4 = "status"
            java.lang.String r4 = a((org.json.JSONObject) r15, (java.lang.String) r4)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r14.setStatus(r4)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            java.lang.String r4 = "distance"
            java.lang.String r4 = a((org.json.JSONObject) r15, (java.lang.String) r4)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            int r4 = j((java.lang.String) r4)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r14.setDistance(r4)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            java.lang.String r4 = "polyline"
            java.util.ArrayList r4 = c((org.json.JSONObject) r15, (java.lang.String) r4)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r14.setPolyline(r4)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r12.add(r14)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
        L_0x01d9:
            int r13 = r13 + 1
            goto L_0x01a4
        L_0x01dc:
            r10.setTMCs(r12)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r8.add(r10)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
        L_0x01e2:
            int r9 = r9 + 1
            goto L_0x0142
        L_0x01e6:
            r5.setElements(r8)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            r2.add(r5)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
        L_0x01ec:
            int r3 = r3 + 1
            goto L_0x0107
        L_0x01f0:
            r1.setTimeInfos(r2)     // Catch:{ JSONException -> 0x0205, Throwable -> 0x01f4 }
            return r1
        L_0x01f4:
            r0 = move-exception
            java.lang.String r1 = "JSONHelper"
            java.lang.String r2 = "parseDriveRouteThrowable"
            com.amap.api.services.a.s.a(r0, r1, r2)
            com.amap.api.services.core.AMapException r0 = new com.amap.api.services.core.AMapException
            java.lang.String r1 = "协议解析错误 - ProtocolException"
            r0.<init>(r1)
            throw r0
        L_0x0205:
            r0 = move-exception
            java.lang.String r1 = "JSONHelper"
            java.lang.String r2 = "parseDriveRoute"
            com.amap.api.services.a.s.a(r0, r1, r2)
            com.amap.api.services.core.AMapException r0 = new com.amap.api.services.core.AMapException
            java.lang.String r1 = "协议解析错误 - ProtocolException"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.z.s(java.lang.String):com.amap.api.services.route.DriveRoutePlanResult");
    }
}
