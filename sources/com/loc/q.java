package com.loc;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.mi.global.shop.model.Tags;
import com.miui.tsmclient.net.TSMAuthContants;
import com.taobao.weex.common.Constants;
import org.json.JSONObject;

public final class q {

    /* renamed from: a  reason: collision with root package name */
    Object f6630a = new Object();
    AMapLocationClientOption b = null;
    a c = null;
    private Context d;
    private AMapLocationClient e = null;
    /* access modifiers changed from: private */
    public WebView f = null;
    /* access modifiers changed from: private */
    public String g = "AMap.Geolocation.cbk";
    /* access modifiers changed from: private */
    public volatile boolean h = false;

    class a implements AMapLocationListener {
        a() {
        }

        public final void onLocationChanged(AMapLocation aMapLocation) {
            if (q.this.h) {
                q.a(q.this, q.b(aMapLocation));
            }
        }
    }

    public q(Context context, WebView webView) {
        this.d = context.getApplicationContext();
        this.f = webView;
        this.c = new a();
    }

    static /* synthetic */ void a(q qVar, final String str) {
        try {
            if (qVar.f == null) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 19) {
                WebView webView = qVar.f;
                webView.evaluateJavascript("javascript:" + qVar.g + "('" + str + "')", new ValueCallback<String>() {
                    public final /* bridge */ /* synthetic */ void onReceiveValue(Object obj) {
                    }
                });
                return;
            }
            qVar.f.post(new Runnable() {
                public final void run() {
                    WebView b2 = q.this.f;
                    b2.loadUrl("javascript:" + q.this.g + "('" + str + "')");
                }
            });
        } catch (Throwable th) {
            es.a(th, "H5LocationClient", "callbackJs()");
        }
    }

    /* access modifiers changed from: private */
    public static String b(AMapLocation aMapLocation) {
        String str;
        Object obj;
        JSONObject jSONObject = new JSONObject();
        if (aMapLocation == null) {
            try {
                jSONObject.put("errorCode", -1);
                str = "errorInfo";
                obj = "unknownError";
            } catch (Throwable unused) {
            }
        } else if (aMapLocation.getErrorCode() == 0) {
            jSONObject.put("errorCode", 0);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("x", aMapLocation.getLongitude());
            jSONObject2.put(Constants.Name.Y, aMapLocation.getLatitude());
            jSONObject2.put("precision", (double) aMapLocation.getAccuracy());
            jSONObject2.put("type", aMapLocation.getLocationType());
            jSONObject2.put("country", aMapLocation.getCountry());
            jSONObject2.put("province", aMapLocation.getProvince());
            jSONObject2.put("city", aMapLocation.getCity());
            jSONObject2.put(TSMAuthContants.PARAM_CITYCODE, aMapLocation.getCityCode());
            jSONObject2.put("district", aMapLocation.getDistrict());
            jSONObject2.put("adCode", aMapLocation.getAdCode());
            jSONObject2.put("street", aMapLocation.getStreet());
            jSONObject2.put("streetNum", aMapLocation.getStreetNum());
            jSONObject2.put(Tags.Kuwan.COMMENT_FLOOR, aMapLocation.getFloor());
            jSONObject2.put("address", aMapLocation.getAddress());
            str = "result";
            obj = jSONObject2;
        } else {
            jSONObject.put("errorCode", aMapLocation.getErrorCode());
            jSONObject.put("errorInfo", aMapLocation.getErrorInfo());
            jSONObject.put("locationDetail", aMapLocation.getLocationDetail());
            return jSONObject.toString();
        }
        jSONObject.put(str, obj);
        return jSONObject.toString();
    }

    public final void a() {
        if (this.f != null && this.d != null && Build.VERSION.SDK_INT >= 17 && !this.h) {
            try {
                this.f.getSettings().setJavaScriptEnabled(true);
                this.f.addJavascriptInterface(this, "AMapAndroidLoc");
                if (!TextUtils.isEmpty(this.f.getUrl())) {
                    this.f.reload();
                }
                if (this.e == null) {
                    this.e = new AMapLocationClient(this.d);
                    this.e.setLocationListener(this.c);
                }
                this.h = true;
            } catch (Throwable unused) {
            }
        }
    }

    public final void b() {
        synchronized (this.f6630a) {
            this.h = false;
            if (this.e != null) {
                this.e.unRegisterLocationListener(this.c);
                this.e.stopLocation();
                this.e.onDestroy();
                this.e = null;
            }
            this.b = null;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(13:7|(1:9)|10|(16:11|12|13|14|15|(1:17)(1:18)|19|20|21|(1:23)|24|25|26|27|(1:30)|29)|37|38|(1:40)(1:42)|41|43|(1:45)|46|47|(1:49)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x0056 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:46:0x007b */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x005d A[Catch:{ Throwable -> 0x007b }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0065 A[Catch:{ Throwable -> 0x007b }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0073 A[Catch:{ Throwable -> 0x007b }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x007f  */
    @android.webkit.JavascriptInterface
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void getLocation(java.lang.String r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.f6630a
            monitor-enter(r0)
            boolean r1 = r8.h     // Catch:{ all -> 0x0092 }
            if (r1 != 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0092 }
            return
        L_0x0009:
            com.amap.api.location.AMapLocationClientOption r1 = r8.b     // Catch:{ all -> 0x0092 }
            if (r1 != 0) goto L_0x0014
            com.amap.api.location.AMapLocationClientOption r1 = new com.amap.api.location.AMapLocationClientOption     // Catch:{ all -> 0x0092 }
            r1.<init>()     // Catch:{ all -> 0x0092 }
            r8.b = r1     // Catch:{ all -> 0x0092 }
        L_0x0014:
            r1 = 5
            r2 = 30000(0x7530, double:1.4822E-319)
            r4 = 0
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0053 }
            r5.<init>(r9)     // Catch:{ Throwable -> 0x0053 }
            java.lang.String r9 = "to"
            long r6 = r5.optLong(r9, r2)     // Catch:{ Throwable -> 0x0053 }
            java.lang.String r9 = "useGPS"
            r2 = 1
            int r9 = r5.optInt(r9, r2)     // Catch:{ Throwable -> 0x0054 }
            if (r9 != r2) goto L_0x002f
            r9 = 1
            goto L_0x0030
        L_0x002f:
            r9 = 0
        L_0x0030:
            java.lang.String r3 = "watch"
            int r3 = r5.optInt(r3, r4)     // Catch:{ Throwable -> 0x0055 }
            if (r3 != r2) goto L_0x003a
            r4 = 1
        L_0x003a:
            java.lang.String r2 = "interval"
            int r2 = r5.optInt(r2, r1)     // Catch:{ Throwable -> 0x0055 }
            java.lang.String r1 = "callback"
            r3 = 0
            java.lang.String r1 = r5.optString(r1, r3)     // Catch:{ Throwable -> 0x0056 }
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x0056 }
            if (r3 != 0) goto L_0x0050
        L_0x004d:
            r8.g = r1     // Catch:{ Throwable -> 0x0056 }
            goto L_0x0056
        L_0x0050:
            java.lang.String r1 = "AMap.Geolocation.cbk"
            goto L_0x004d
        L_0x0053:
            r6 = r2
        L_0x0054:
            r9 = 0
        L_0x0055:
            r2 = 5
        L_0x0056:
            com.amap.api.location.AMapLocationClientOption r1 = r8.b     // Catch:{ Throwable -> 0x007b }
            r1.setHttpTimeOut(r6)     // Catch:{ Throwable -> 0x007b }
            if (r9 == 0) goto L_0x0065
            com.amap.api.location.AMapLocationClientOption r9 = r8.b     // Catch:{ Throwable -> 0x007b }
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r1 = com.amap.api.location.AMapLocationClientOption.AMapLocationMode.Hight_Accuracy     // Catch:{ Throwable -> 0x007b }
        L_0x0061:
            r9.setLocationMode(r1)     // Catch:{ Throwable -> 0x007b }
            goto L_0x006a
        L_0x0065:
            com.amap.api.location.AMapLocationClientOption r9 = r8.b     // Catch:{ Throwable -> 0x007b }
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r1 = com.amap.api.location.AMapLocationClientOption.AMapLocationMode.Battery_Saving     // Catch:{ Throwable -> 0x007b }
            goto L_0x0061
        L_0x006a:
            com.amap.api.location.AMapLocationClientOption r9 = r8.b     // Catch:{ Throwable -> 0x007b }
            r1 = r4 ^ 1
            r9.setOnceLocation(r1)     // Catch:{ Throwable -> 0x007b }
            if (r4 == 0) goto L_0x007b
            com.amap.api.location.AMapLocationClientOption r9 = r8.b     // Catch:{ Throwable -> 0x007b }
            int r2 = r2 * 1000
            long r1 = (long) r2     // Catch:{ Throwable -> 0x007b }
            r9.setInterval(r1)     // Catch:{ Throwable -> 0x007b }
        L_0x007b:
            com.amap.api.location.AMapLocationClient r9 = r8.e     // Catch:{ all -> 0x0092 }
            if (r9 == 0) goto L_0x0090
            com.amap.api.location.AMapLocationClient r9 = r8.e     // Catch:{ all -> 0x0092 }
            com.amap.api.location.AMapLocationClientOption r1 = r8.b     // Catch:{ all -> 0x0092 }
            r9.setLocationOption(r1)     // Catch:{ all -> 0x0092 }
            com.amap.api.location.AMapLocationClient r9 = r8.e     // Catch:{ all -> 0x0092 }
            r9.stopLocation()     // Catch:{ all -> 0x0092 }
            com.amap.api.location.AMapLocationClient r9 = r8.e     // Catch:{ all -> 0x0092 }
            r9.startLocation()     // Catch:{ all -> 0x0092 }
        L_0x0090:
            monitor-exit(r0)     // Catch:{ all -> 0x0092 }
            return
        L_0x0092:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0092 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.q.getLocation(java.lang.String):void");
    }

    @JavascriptInterface
    public final void stopLocation() {
        if (this.h && this.e != null) {
            this.e.stopLocation();
        }
    }
}
