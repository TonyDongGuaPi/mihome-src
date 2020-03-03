package miuipub.hybrid.feature;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.util.Locale;
import java.util.Map;
import miuipub.hybrid.HybridFeature;
import miuipub.hybrid.Request;
import miuipub.hybrid.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class Device implements HybridFeature {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2958a = "Device";
    private static final String b = "getDeviceInfo";
    private static final String c = "model";
    private static final String d = "romVersion";
    private static final String e = "language";
    private static final String f = "region";
    private static final String g = "deviceId";
    private static final String h = "smallDeviceId";

    public void a(Map<String, String> map) {
    }

    public Response a(Request request) {
        if (b.equals(request.a())) {
            return a((Context) request.e().a());
        }
        return new Response(204, "no such action");
    }

    public HybridFeature.Mode b(Request request) {
        if (b.equals(request.a())) {
            return HybridFeature.Mode.SYNC;
        }
        return null;
    }

    private Response a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("model", Build.MODEL);
            jSONObject.put(d, Build.VERSION.RELEASE);
            jSONObject.put("language", Locale.getDefault().getLanguage());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("deviceId", ((TelephonyManager) context.getSystemService("phone")).getDeviceId());
            return new Response(jSONObject);
        } catch (JSONException e2) {
            Log.e(f2958a, e2.getMessage());
            return new Response(200, e2.getMessage());
        }
    }
}
