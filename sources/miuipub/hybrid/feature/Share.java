package miuipub.hybrid.feature;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import java.util.Map;
import miuipub.hybrid.Callback;
import miuipub.hybrid.HybridFeature;
import miuipub.hybrid.LifecycleListener;
import miuipub.hybrid.NativeInterface;
import miuipub.hybrid.Request;
import miuipub.hybrid.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class Share implements HybridFeature {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2966a = "HybridShare";
    private static final String b = "send";
    private static final String c = "type";
    private static final String d = "data";

    public void a(Map<String, String> map) {
    }

    public Response a(Request request) {
        if ("send".equals(request.a())) {
            return c(request);
        }
        return new Response(204, "no such action");
    }

    private Response c(Request request) {
        final NativeInterface e = request.e();
        Activity a2 = e.a();
        final Callback c2 = request.c();
        e.a(new LifecycleListener() {
            public void a(int i, int i2, Intent intent) {
                Response response;
                e.b(this);
                if (i2 == -1) {
                    response = new Response(0, "success");
                } else if (i2 == 0) {
                    response = new Response(100, "cancel");
                } else {
                    response = new Response(200);
                }
                c2.a(response);
            }
        });
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        String b2 = request.b();
        try {
            JSONObject jSONObject = new JSONObject(b2);
            String string = jSONObject.getString("type");
            String string2 = jSONObject.getString("data");
            if (string != null) {
                if (string2 != null) {
                    intent.setType(string);
                    if (string.startsWith("text/")) {
                        intent.putExtra("android.intent.extra.TEXT", string2);
                    } else {
                        intent.putExtra("android.intent.extra.STREAM", string2);
                    }
                    a2.startActivityForResult(intent, 1);
                    return null;
                }
            }
            c2.a(new Response(200, "no data to share"));
            return null;
        } catch (JSONException unused) {
            Log.i(f2966a, "invalid JSON string:" + b2);
            c2.a(new Response(200, "invalid data to share"));
        }
    }

    public HybridFeature.Mode b(Request request) {
        return HybridFeature.Mode.CALLBACK;
    }
}
