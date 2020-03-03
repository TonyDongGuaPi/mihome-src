package miuipub.hybrid.feature;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import java.util.Map;
import miuipub.hybrid.HybridFeature;
import miuipub.hybrid.LifecycleListener;
import miuipub.hybrid.NativeInterface;
import miuipub.hybrid.Request;
import miuipub.hybrid.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class Barcode implements HybridFeature {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final int f2952a = "REQUEST_SCAN_BARCODE".hashCode();
    private static final String b = "android.intent.action.scanbarcode";
    private static final String c = "miui.intent.category.SYSAPP_TOOL";
    private static final String d = "type";
    private static final String e = "result";
    private static final String f = "isBackToThirdApp";
    private static final String g = "scan";
    private static final String h = "type";
    private static final String i = "result";

    public void a(Map<String, String> map) {
    }

    public Response a(final Request request) {
        if (!TextUtils.equals(request.a(), g)) {
            return new Response(204, "no such action");
        }
        final NativeInterface e2 = request.e();
        Activity a2 = e2.a();
        Intent intent = new Intent(b);
        intent.addCategory(c);
        intent.putExtra(f, true);
        if (a2.getPackageManager().resolveActivity(intent, 0) == null) {
            request.c().a(new Response(204, "can't find barcode scanner activity"));
            return null;
        }
        e2.a(new LifecycleListener() {
            public void a(int i, int i2, Intent intent) {
                Response response;
                if (i == Barcode.f2952a) {
                    e2.b(this);
                    if (i2 == -1) {
                        response = new Response(0, Barcode.this.a(intent));
                    } else if (i2 == 0) {
                        response = new Response(100);
                    } else {
                        response = new Response(200);
                    }
                    request.c().a(response);
                }
            }
        });
        a2.startActivityForResult(intent, f2952a);
        return null;
    }

    /* access modifiers changed from: private */
    public JSONObject a(Intent intent) {
        if (intent == null) {
            return null;
        }
        int intExtra = intent.getIntExtra("type", -1);
        String stringExtra = intent.getStringExtra("result");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", intExtra);
            jSONObject.put("result", stringExtra);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public HybridFeature.Mode b(Request request) {
        if (TextUtils.equals(request.a(), g)) {
            return HybridFeature.Mode.CALLBACK;
        }
        return null;
    }
}
