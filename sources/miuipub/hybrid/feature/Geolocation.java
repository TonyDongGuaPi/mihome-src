package miuipub.hybrid.feature;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.alipay.mobile.common.logging.api.LogCategory;
import java.util.Map;
import miuipub.hybrid.Callback;
import miuipub.hybrid.HybridFeature;
import miuipub.hybrid.Request;
import miuipub.hybrid.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class Geolocation implements HybridFeature {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2959a = "HybridGeolocation";
    private static final String b = "enableListener";
    private static final String c = "get";
    private static final String d = "disableListener";
    private static final String e = "type";
    private String f = LogCategory.CATEGORY_NETWORK;
    /* access modifiers changed from: private */
    public Callback g;
    private LocationListener h;

    public void a(Map<String, String> map) {
        if ("gps".equals(map.get("type"))) {
            this.f = "gps";
        }
    }

    public Response a(Request request) {
        String a2 = request.a();
        Log.i(f2959a, "invoke with action: " + a2);
        LocationManager locationManager = (LocationManager) request.e().a().getSystemService("location");
        if (b.equals(a2)) {
            return a(locationManager, request);
        }
        if ("get".equals(a2)) {
            return b(locationManager, request);
        }
        if (d.equals(a2)) {
            return c(locationManager, request);
        }
        return new Response(204, "no such action");
    }

    private Response a(LocationManager locationManager, Request request) {
        this.g = request.c();
        if (this.h == null) {
            this.h = new NetworkLocationListener();
            Looper.prepare();
            locationManager.requestLocationUpdates(this.f, 0, 0.0f, this.h);
            Looper.loop();
        }
        this.g.a(a(locationManager.getLastKnownLocation(this.f)));
        return null;
    }

    private Response b(LocationManager locationManager, Request request) {
        return a(locationManager.getLastKnownLocation(this.f));
    }

    private Response c(LocationManager locationManager, Request request) {
        if (this.h != null) {
            locationManager.removeUpdates(this.h);
            this.h = null;
        }
        this.g = null;
        request.c().a(new Response(0, "remove"));
        return null;
    }

    /* access modifiers changed from: private */
    public Response a(Location location) {
        if (location != null) {
            Log.i(f2959a, "response with valid location.");
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("latitude", location.getLatitude());
                jSONObject.put("longitude", location.getLongitude());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return new Response(3, jSONObject.toString());
        }
        Log.i(f2959a, "error: response location with null.");
        return new Response(200, "no location");
    }

    private class NetworkLocationListener implements LocationListener {
        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        private NetworkLocationListener() {
        }

        public void onLocationChanged(Location location) {
            if (Geolocation.this.g != null) {
                Geolocation.this.g.a(Geolocation.this.a(location));
            }
        }
    }

    public HybridFeature.Mode b(Request request) {
        if ("get".equals(request.a())) {
            return HybridFeature.Mode.SYNC;
        }
        return HybridFeature.Mode.CALLBACK;
    }
}
