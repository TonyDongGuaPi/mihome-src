package com.xiaomi.smarthome.operation.js_sdk.location;

import android.text.TextUtils;
import android.util.Log;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.miui.tsmclient.net.TSMAuthContants;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.location.SHLocationManager;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.newui.amappoi.LatLngEntity;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21087a = "LocationHelper";
    private static final String b = "GCJ02";
    private static final String c = "WGS84";
    private static final int g = 500;
    private static LocationHelper i;
    /* access modifiers changed from: private */
    public long d = 0;
    /* access modifiers changed from: private */
    public WebLocation e;
    /* access modifiers changed from: private */
    public RequestOption f;
    /* access modifiers changed from: private */
    public final AMapLocationClient h = new AMapLocationClient(SHApplication.getApplication());

    private LocationHelper() {
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.setOnceLocation(true);
        aMapLocationClientOption.setLocationCacheEnable(true);
        aMapLocationClientOption.setGpsFirst(false);
        this.h.setLocationOption(aMapLocationClientOption);
    }

    public static LocationHelper a() {
        if (i == null) {
            synchronized (LocationHelper.class) {
                if (i == null) {
                    i = new LocationHelper();
                }
            }
        }
        return i;
    }

    public String a(String str) {
        return c(str).a().toString();
    }

    @NotNull
    private WebLocation c(String str) {
        try {
            Log.d(f21087a, "getLocationBlocked: " + str);
            return b(str).blockingGet();
        } catch (Exception e2) {
            return new ErrorWebLocation(e2.getLocalizedMessage());
        }
    }

    public Single<WebLocation> b(String str) {
        if (!SHLocationManager.f()) {
            return Single.just(ErrorWebLocation.b);
        }
        try {
            final RequestOption a2 = RequestOption.a(str);
            if (System.currentTimeMillis() - this.d < 500) {
                return Single.just(ErrorWebLocation.f);
            }
            if (this.e != null && this.d != 0 && a2 == this.f && (System.currentTimeMillis() / 1000) - this.d < a2.b / 1000) {
                LogUtil.a(f21087a, "getLocationRx: use cache location");
                return Single.just(this.e);
            }
            this.e = null;
            this.f = null;
            final long currentTimeMillis = System.currentTimeMillis();
            return Single.create(new SingleOnSubscribe<WebLocation>() {
                public void subscribe(final SingleEmitter<WebLocation> singleEmitter) throws Exception {
                    LogUtil.a(LocationHelper.f21087a, "getLocationRx: start");
                    LocationHelper.this.h.setLocationListener(new AMapLocationListener() {
                        public void onLocationChanged(AMapLocation aMapLocation) {
                            LogUtil.a(LocationHelper.f21087a, "onLocationChanged: " + aMapLocation);
                            if (!singleEmitter.isDisposed()) {
                                singleEmitter.onSuccess(WebLocation.a(aMapLocation));
                            }
                        }
                    });
                    LocationHelper.this.h.startLocation();
                }
            }).map(new Function<WebLocation, WebLocation>() {
                /* renamed from: a */
                public WebLocation apply(WebLocation webLocation) throws Exception {
                    if (TextUtils.equals(a2.f21094a, webLocation.h)) {
                        return webLocation;
                    }
                    if (TextUtils.equals(webLocation.h, "GCJ02")) {
                        LatLngEntity b2 = GCJ2WGSConverter.b(webLocation.j, webLocation.i);
                        webLocation.j = b2.a();
                        webLocation.i = b2.b();
                        webLocation.h = "WGS84";
                    } else if (TextUtils.equals(webLocation.h, "WGS84")) {
                        LatLngEntity a2 = GCJ2WGSConverter.a(webLocation.j, webLocation.i);
                        webLocation.j = a2.a();
                        webLocation.i = a2.b();
                        webLocation.h = "GCJ02";
                    }
                    return webLocation;
                }
            }).timeout(60, TimeUnit.SECONDS, new SingleSource<WebLocation>() {
                public void subscribe(SingleObserver<? super WebLocation> singleObserver) {
                    singleObserver.onSuccess(ErrorWebLocation.c);
                }
            }).doOnSuccess(new Consumer<WebLocation>() {
                /* renamed from: a */
                public void accept(WebLocation webLocation) throws Exception {
                    Log.d(LocationHelper.f21087a, "getLocationRx: take: " + ((System.currentTimeMillis() - currentTimeMillis) / 1000) + " sec");
                    LogUtil.a(LocationHelper.f21087a, "getLocationRx: " + a2 + " ; " + webLocation);
                    if (!(webLocation instanceof ErrorWebLocation)) {
                        long unused = LocationHelper.this.d = System.currentTimeMillis();
                        WebLocation unused2 = LocationHelper.this.e = webLocation;
                        RequestOption unused3 = LocationHelper.this.f = a2;
                    }
                }
            });
        } catch (Exception e2) {
            return Single.just(new ErrorWebLocation(e2.getLocalizedMessage()));
        }
    }

    private static class RequestOption {

        /* renamed from: a  reason: collision with root package name */
        String f21094a = "GCJ02";
        long b = 8;

        private RequestOption() {
        }

        static RequestOption a(String str) throws JSONException {
            RequestOption requestOption = new RequestOption();
            JSONObject jSONObject = new JSONObject(str);
            requestOption.f21094a = TextUtils.equals(jSONObject.optString("csysType").toUpperCase(), "GCJ02") ? "GCJ02" : "WGS84";
            requestOption.b = Math.min(jSONObject.optLong("cacheTimeout", 30), 30);
            return requestOption;
        }

        public String toString() {
            return "RequestOption{csysType='" + this.f21094a + Operators.SINGLE_QUOTE + ", cacheTimeout=" + this.b + Operators.BLOCK_END;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            RequestOption requestOption = (RequestOption) obj;
            if (this.b != requestOption.b) {
                return false;
            }
            if (this.f21094a != null) {
                return this.f21094a.equals(requestOption.f21094a);
            }
            if (requestOption.f21094a == null) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return ((this.f21094a != null ? this.f21094a.hashCode() : 0) * 31) + ((int) (this.b ^ (this.b >>> 32)));
        }
    }

    public static class WebLocation {
        String h;
        double i;
        double j;
        float k;
        float l;
        String m;
        String n;
        String o;
        String p;
        String q;
        String r;
        String s;
        String t;
        String u;

        WebLocation() {
        }

        static WebLocation a(AMapLocation aMapLocation) {
            if (aMapLocation == null) {
                return ErrorWebLocation.d;
            }
            if (!TextUtils.isEmpty(aMapLocation.getErrorInfo()) && !TextUtils.equals("success", aMapLocation.getErrorInfo())) {
                return new ErrorWebLocation(aMapLocation.getErrorInfo());
            }
            WebLocation webLocation = new WebLocation();
            webLocation.h = aMapLocation.getCoordType() == null ? "" : aMapLocation.getCoordType().toUpperCase();
            webLocation.j = aMapLocation.getLatitude();
            webLocation.i = aMapLocation.getLongitude();
            webLocation.k = aMapLocation.getAccuracy();
            webLocation.l = aMapLocation.getSpeed();
            webLocation.o = aMapLocation.getProvince();
            webLocation.p = aMapLocation.getCity();
            webLocation.q = aMapLocation.getCityCode();
            webLocation.r = aMapLocation.getAdCode();
            webLocation.m = aMapLocation.getCountry();
            webLocation.s = aMapLocation.getDistrict();
            webLocation.t = aMapLocation.getStreet();
            webLocation.u = aMapLocation.getStreetNum();
            return webLocation;
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("result", "ok");
                jSONObject.put("csysType", this.h);
                jSONObject.put("latitude", this.j);
                jSONObject.put("longitude", this.i);
                jSONObject.put("accuracy", (double) this.k);
                jSONObject.put("speed", (double) this.l);
                jSONObject.put("province", this.o);
                jSONObject.put("city", this.p);
                jSONObject.put(TSMAuthContants.PARAM_CITYCODE, this.q);
                jSONObject.put("adCode", this.r);
                jSONObject.put("country", this.m);
                jSONObject.put("district", this.s);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(this.t, this.u);
                jSONObject.put("streetNumber", jSONObject2);
            } catch (Exception e) {
                LogUtil.a(LocationHelper.f21087a, "toJson: " + Log.getStackTraceString(e));
            }
            return jSONObject;
        }

        public String toString() {
            return a().toString();
        }
    }

    static class ErrorWebLocation extends WebLocation {

        /* renamed from: a  reason: collision with root package name */
        static final WebLocation f21093a = new ErrorWebLocation("location is disabled.");
        static final WebLocation b = new ErrorWebLocation("location permission is not granted.");
        static final WebLocation c = new ErrorWebLocation("get location timeout.");
        static final WebLocation d = new ErrorWebLocation("error unknow.");
        static final WebLocation e = new ErrorWebLocation("param error.");
        static final WebLocation f = new ErrorWebLocation("too many location request.");
        final String g;

        ErrorWebLocation(String str) {
            this.g = str;
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("result", "error");
                jSONObject.put("msg", this.g);
            } catch (JSONException e2) {
                LogUtil.a(LocationHelper.f21087a, "toJson: " + Log.getStackTraceString(e2));
            }
            return jSONObject;
        }

        public String toString() {
            return a().toString();
        }
    }
}
