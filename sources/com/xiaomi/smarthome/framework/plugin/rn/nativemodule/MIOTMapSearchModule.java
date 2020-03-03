package com.xiaomi.smarthome.framework.plugin.rn.nativemodule;

import android.support.annotation.NonNull;
import com.alipay.mobile.security.bio.api.BioDetector;
import com.alipay.sdk.util.i;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.IndoorData;
import com.amap.api.services.poisearch.Photo;
import com.amap.api.services.poisearch.PoiItemExtension;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.poisearch.SubPoiItem;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.amap.api.services.route.WalkStep;
import com.facebook.places.model.PlaceFields;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.google.android.gms.actions.SearchIntents;
import com.mi.global.shop.model.Tags;
import com.miui.tsmclient.entity.CardUIInfo;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.infra.galaxy.fds.Common;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MIOTMapSearchModule extends ReactContextBaseJavaModule {
    private static final int SUCCESS = 1000;

    public String getName() {
        return "MHMapSearch";
    }

    public MIOTMapSearchModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void reGeocodeSearch(ReadableMap readableMap, final Callback callback) {
        double d = readableMap.getDouble("latitude");
        double d2 = readableMap.getDouble("longitude");
        GeocodeSearch geocodeSearch = new GeocodeSearch(getReactApplicationContext());
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                if (regeocodeResult == null || i != 1000) {
                    callback.invoke(false, null);
                    return;
                }
                RegeocodeAddress regeocodeAddress = regeocodeResult.getRegeocodeAddress();
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                if (regeocodeAddress != null) {
                    writableNativeMap.putString("title", regeocodeAddress.getProvince());
                    writableNativeMap.putString(CardUIInfo.KEY_SUBTITILE, regeocodeAddress.getFormatAddress());
                }
                callback.invoke(true, writableNativeMap);
            }

            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                callback.invoke(false, "onGeocodeSearched");
            }
        });
        geocodeSearch.getFromLocationAsyn(new RegeocodeQuery(new LatLonPoint(d, d2), 0.0f, GeocodeSearch.AMAP));
    }

    @ReactMethod
    public void poiAroundSearch(ReadableMap readableMap, String str, Callback callback) {
        double d = readableMap.getDouble("latitude");
        double d2 = readableMap.getDouble("longitude");
        PoiSearch poiSearch = new PoiSearch(getReactApplicationContext(), new PoiSearch.Query(str, ""));
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(d, d2), 0));
        poiSearch.setOnPoiSearchListener(new PoiSearchListenerImpl(callback));
        poiSearch.searchPOIAsyn();
    }

    @ReactMethod
    public void poiKeywordsSearch(String str, String str2, boolean z, Callback callback) {
        PoiSearch.Query query = new PoiSearch.Query(str2, "", str);
        query.setCityLimit(z);
        PoiSearch poiSearch = new PoiSearch(getReactApplicationContext(), query);
        poiSearch.setOnPoiSearchListener(new PoiSearchListenerImpl(callback));
        poiSearch.searchPOIAsyn();
    }

    @ReactMethod
    public void poiIDSearch(String str, Callback callback) {
        PoiSearch poiSearch = new PoiSearch(getReactApplicationContext(), (PoiSearch.Query) null);
        poiSearch.searchPOIIdAsyn(str);
        poiSearch.setOnPoiSearchListener(new PoiSearchListenerImpl(callback));
    }

    @ReactMethod
    public void walkingRouteSearch(ReadableMap readableMap, ReadableMap readableMap2, int i, final Callback callback) {
        double d = readableMap.getDouble("latitude");
        double d2 = readableMap.getDouble("longitude");
        double d3 = readableMap2.getDouble("latitude");
        double d4 = readableMap2.getDouble("longitude");
        RouteSearch routeSearch = new RouteSearch(getReactApplicationContext());
        routeSearch.calculateWalkRouteAsyn(new RouteSearch.WalkRouteQuery(new RouteSearch.FromAndTo(new LatLonPoint(d, d2), new LatLonPoint(d3, d4)), i));
        routeSearch.setRouteSearchListener(new RouteSearch.OnRouteSearchListener() {
            public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {
                callback.invoke(false, "onBusRouteSearched");
            }

            public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {
                callback.invoke(false, "onDriveRouteSearched");
            }

            public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {
                Iterator<LatLonPoint> it;
                if (walkRouteResult == null || i != 1000) {
                    callback.invoke(false, null);
                    return;
                }
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                List<WalkPath> paths = walkRouteResult.getPaths();
                if (paths == null) {
                    callback.invoke(false, null);
                    return;
                }
                StringBuilder sb = new StringBuilder();
                WritableNativeArray writableNativeArray = new WritableNativeArray();
                for (WalkPath next : paths) {
                    if (next != null) {
                        WritableNativeMap writableNativeMap2 = new WritableNativeMap();
                        writableNativeMap2.putDouble(Tags.Nearby.DISTANCE, (double) next.getDistance());
                        writableNativeMap2.putDouble("duration", (double) next.getDuration());
                        WritableNativeArray writableNativeArray2 = new WritableNativeArray();
                        for (WalkStep next2 : next.getSteps()) {
                            if (next2 != null) {
                                WritableNativeMap writableNativeMap3 = new WritableNativeMap();
                                writableNativeMap3.putString("action", next2.getAction());
                                writableNativeMap3.putString("assistantAction", next2.getAssistantAction());
                                writableNativeMap3.putString("instruction", next2.getInstruction());
                                writableNativeMap3.putString("orientation", next2.getOrientation());
                                writableNativeMap3.putString("road", next2.getRoad());
                                writableNativeMap3.putDouble(Tags.Nearby.DISTANCE, (double) next2.getDistance());
                                writableNativeMap3.putDouble("duration", (double) next2.getDuration());
                                sb.setLength(0);
                                Iterator<LatLonPoint> it2 = next2.getPolyline().iterator();
                                while (it2.hasNext()) {
                                    LatLonPoint next3 = it2.next();
                                    if (next3 != null) {
                                        it = it2;
                                        sb.append(next3.getLongitude());
                                        sb.append(",");
                                        sb.append(next3.getLatitude());
                                        sb.append(i.b);
                                    } else {
                                        it = it2;
                                    }
                                    it2 = it;
                                }
                                writableNativeMap3.putString("polyline", sb.toString());
                                writableNativeArray2.pushMap(writableNativeMap3);
                            }
                        }
                        writableNativeMap2.putArray("steps", writableNativeArray2);
                        writableNativeArray.pushMap(writableNativeMap2);
                    }
                }
                writableNativeMap.putArray("paths", writableNativeArray);
                RouteSearch.FromAndTo fromAndTo = walkRouteResult.getWalkQuery().getFromAndTo();
                writableNativeMap.putMap("origin", MIOTMapSearchModule.mapLatLon(fromAndTo.getFrom()));
                writableNativeMap.putMap("destination", MIOTMapSearchModule.mapLatLon(fromAndTo.getTo()));
                if (paths.size() > 0) {
                    callback.invoke(true, writableNativeMap);
                    return;
                }
                callback.invoke(true, writableNativeMap);
            }

            public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {
                callback.invoke(false, "onRideRouteSearched");
            }
        });
    }

    @NonNull
    public static WritableMap mapLatLon(LatLonPoint latLonPoint) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (latLonPoint != null) {
            writableNativeMap.putDouble("latitude", latLonPoint.getLatitude());
            writableNativeMap.putDouble("longitude", latLonPoint.getLongitude());
        }
        return writableNativeMap;
    }

    private static class PoiSearchListenerImpl implements PoiSearch.OnPoiSearchListener {

        /* renamed from: a  reason: collision with root package name */
        private Callback f17407a;

        public PoiSearchListenerImpl(Callback callback) {
            this.f17407a = callback;
        }

        public void onPoiSearched(PoiResult poiResult, int i) {
            if (poiResult == null || i != 1000) {
                this.f17407a.invoke(false, null);
                return;
            }
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            int pageCount = poiResult.getPageCount();
            PoiSearch.SearchBound bound = poiResult.getBound();
            PoiSearch.Query query = poiResult.getQuery();
            ArrayList<PoiItem> pois = poiResult.getPois();
            List<SuggestionCity> searchSuggestionCitys = poiResult.getSearchSuggestionCitys();
            List<String> searchSuggestionKeywords = poiResult.getSearchSuggestionKeywords();
            writableNativeMap.putInt("pageCount", pageCount);
            WritableNativeMap writableNativeMap2 = new WritableNativeMap();
            WritableNativeArray writableNativeArray = new WritableNativeArray();
            if (bound != null) {
                writableNativeMap2.putString("shape", bound.getShape());
                writableNativeMap2.putInt(Common.v, bound.getRange());
                writableNativeMap2.putMap("center", MIOTMapSearchModule.mapLatLon(bound.getCenter()));
                writableNativeMap2.putMap("lowerLeft", MIOTMapSearchModule.mapLatLon(bound.getLowerLeft()));
                writableNativeMap2.putMap("upperRight", MIOTMapSearchModule.mapLatLon(bound.getUpperRight()));
                List<LatLonPoint> polyGonList = bound.getPolyGonList();
                if (polyGonList != null) {
                    for (LatLonPoint mapLatLon : polyGonList) {
                        writableNativeArray.pushMap(MIOTMapSearchModule.mapLatLon(mapLatLon));
                    }
                }
            }
            writableNativeMap2.putArray("polyGonList", writableNativeArray);
            writableNativeMap.putMap("bound", writableNativeMap2);
            WritableNativeMap writableNativeMap3 = new WritableNativeMap();
            writableNativeMap3.putString("building", query.getBuilding());
            writableNativeMap3.putInt(BioDetector.EXT_KEY_PAGENUM, query.getPageNum());
            writableNativeMap3.putInt("pageSize", query.getPageSize());
            writableNativeMap3.putString("category", query.getCategory());
            writableNativeMap3.putString("city", query.getCity());
            writableNativeMap3.putString("queryString", query.getQueryString());
            writableNativeMap3.putBoolean("cityLimit", query.getCityLimit());
            writableNativeMap.putMap(SearchIntents.EXTRA_QUERY, writableNativeMap3);
            WritableNativeArray writableNativeArray2 = new WritableNativeArray();
            Iterator<PoiItem> it = pois.iterator();
            while (it.hasNext()) {
                writableNativeArray2.pushMap(a(it.next()));
            }
            writableNativeMap.putArray("pois", writableNativeArray2);
            WritableNativeArray writableNativeArray3 = new WritableNativeArray();
            for (SuggestionCity next : searchSuggestionCitys) {
                WritableNativeMap writableNativeMap4 = new WritableNativeMap();
                writableNativeMap4.putString("adCode", next.getAdCode());
                writableNativeMap4.putString(TSMAuthContants.PARAM_CITYCODE, next.getCityCode());
                writableNativeMap4.putString("cityName", next.getCityName());
                writableNativeMap4.putInt("suggestionNum", next.getSuggestionNum());
                writableNativeArray3.pushMap(writableNativeMap4);
            }
            writableNativeMap.putArray("searchSuggestionCitys", writableNativeArray3);
            WritableNativeArray writableNativeArray4 = new WritableNativeArray();
            for (String pushString : searchSuggestionKeywords) {
                writableNativeArray4.pushString(pushString);
            }
            writableNativeMap.putArray("searchSuggestionKeywords", writableNativeArray4);
            this.f17407a.invoke(true, writableNativeMap);
        }

        public void onPoiItemSearched(PoiItem poiItem, int i) {
            if (poiItem == null || i != 1000) {
                this.f17407a.invoke(false, null);
                return;
            }
            this.f17407a.invoke(true, a(poiItem));
        }

        private WritableNativeMap a(PoiItem poiItem) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            poiItem.getProvinceName();
            writableNativeMap.putString("adCode", poiItem.getAdCode());
            writableNativeMap.putString("adName", poiItem.getAdName());
            writableNativeMap.putString("provinceName", poiItem.getProvinceName());
            writableNativeMap.putString("businessArea", poiItem.getBusinessArea());
            writableNativeMap.putString(TSMAuthContants.PARAM_CITYCODE, poiItem.getCityCode());
            writableNativeMap.putString("cityName", poiItem.getCityName());
            writableNativeMap.putString("direction", poiItem.getDirection());
            writableNativeMap.putString("email", poiItem.getEmail());
            writableNativeMap.putString("parkingType", poiItem.getParkingType());
            writableNativeMap.putString("poiId", poiItem.getPoiId());
            writableNativeMap.putString("postcode", poiItem.getPostcode());
            writableNativeMap.putString("provinceCode", poiItem.getProvinceCode());
            writableNativeMap.putString("provinceName", poiItem.getProvinceName());
            writableNativeMap.putString("shopID", poiItem.getShopID());
            writableNativeMap.putString("snippet", poiItem.getSnippet());
            writableNativeMap.putString("tel", poiItem.getTel());
            writableNativeMap.putString("title", poiItem.getTitle());
            writableNativeMap.putString("typeCode", poiItem.getTypeCode());
            writableNativeMap.putString("typeDes", poiItem.getTypeDes());
            writableNativeMap.putString("website", poiItem.getWebsite());
            writableNativeMap.putInt(Tags.Nearby.DISTANCE, poiItem.getDistance());
            writableNativeMap.putMap("enter", MIOTMapSearchModule.mapLatLon(poiItem.getEnter()));
            writableNativeMap.putMap("latLonPoint", MIOTMapSearchModule.mapLatLon(poiItem.getLatLonPoint()));
            List<Photo> photos = poiItem.getPhotos();
            WritableNativeArray writableNativeArray = new WritableNativeArray();
            for (Photo next : photos) {
                WritableNativeMap writableNativeMap2 = new WritableNativeMap();
                writableNativeMap2.putString("title", next.getTitle());
                writableNativeMap2.putString("url", next.getUrl());
                writableNativeArray.pushMap(writableNativeMap2);
            }
            writableNativeMap.putArray(PlaceFields.PHOTOS_PROFILE, writableNativeArray);
            WritableNativeArray writableNativeArray2 = new WritableNativeArray();
            for (SubPoiItem next2 : poiItem.getSubPois()) {
                WritableNativeMap writableNativeMap3 = new WritableNativeMap();
                writableNativeMap3.putString("title", next2.getTitle());
                writableNativeMap3.putString("poiId", next2.getPoiId());
                writableNativeMap3.putString("snippet", next2.getSnippet());
                writableNativeMap3.putString("subName", next2.getSubName());
                writableNativeMap3.putString("subTypeDes", next2.getSubTypeDes());
                writableNativeMap3.putInt("url", next2.getDistance());
                writableNativeMap3.putMap("latLonPoint", MIOTMapSearchModule.mapLatLon(next2.getLatLonPoint()));
                writableNativeArray2.pushMap(writableNativeMap3);
            }
            writableNativeMap.putArray("subPois", writableNativeArray2);
            WritableNativeMap writableNativeMap4 = new WritableNativeMap();
            IndoorData indoorData = poiItem.getIndoorData();
            writableNativeMap4.putInt(Tags.Kuwan.COMMENT_FLOOR, indoorData.getFloor());
            writableNativeMap4.putString("floorName", indoorData.getFloorName());
            writableNativeMap4.putString("poiId", indoorData.getPoiId());
            writableNativeMap.putMap("indoorData", writableNativeMap4);
            WritableNativeMap writableNativeMap5 = new WritableNativeMap();
            PoiItemExtension poiExtension = poiItem.getPoiExtension();
            writableNativeMap5.putString("mRating", poiExtension.getmRating());
            writableNativeMap5.putString("opentime", poiExtension.getOpentime());
            writableNativeMap.putMap("poiExtension", writableNativeMap5);
            return writableNativeMap;
        }
    }
}
