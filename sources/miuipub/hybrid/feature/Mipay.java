package miuipub.hybrid.feature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.Map;
import miuipub.hybrid.HybridFeature;
import miuipub.hybrid.LifecycleListener;
import miuipub.hybrid.NativeInterface;
import miuipub.hybrid.Request;
import miuipub.hybrid.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class Mipay implements HybridFeature {

    /* renamed from: a  reason: collision with root package name */
    private static final int f2961a = 20140424;
    private static final String b = "pay";
    private static final String c = "orderInfo";
    private static final String d = "order";
    private static final String e = "extra";
    private static final String f = "code";
    private static final String g = "message";
    private static final String h = "result";
    private static final int i = -1;
    private static final String j = "com.xiaomi.action.MIPAY_PAY_ORDER";
    private static final String k = "com.mipay.wallet";

    public void a(Map<String, String> map) {
    }

    public Response a(final Request request) {
        String str;
        if (!TextUtils.equals(request.a(), "pay")) {
            return new Response(204, "no such action");
        }
        final NativeInterface e2 = request.e();
        Activity a2 = e2.a();
        e2.a(new LifecycleListener() {
            public void a(int i, int i2, Intent intent) {
                Response response;
                if (i == Mipay.f2961a) {
                    e2.b(this);
                    if (i2 == -1) {
                        response = new Response(0, Mipay.this.a(intent));
                    } else if (i2 == 0) {
                        response = new Response(100, Mipay.this.a(intent));
                    } else {
                        response = new Response(200);
                    }
                    request.c().a(response);
                }
            }
        });
        try {
            str = new JSONObject(request.b()).getString("orderInfo");
        } catch (JSONException e3) {
            e3.printStackTrace();
            str = null;
        }
        return a(a2, str, (Bundle) null);
    }

    /* access modifiers changed from: private */
    public JSONObject a(Intent intent) {
        JSONObject jSONObject = new JSONObject();
        if (intent == null) {
            return jSONObject;
        }
        try {
            jSONObject.put("code", intent.getIntExtra("code", -1));
            String stringExtra = intent.getStringExtra("message");
            if (!TextUtils.isEmpty(stringExtra)) {
                jSONObject.put("message", stringExtra);
            }
            String stringExtra2 = intent.getStringExtra("result");
            if (!TextUtils.isEmpty(stringExtra2)) {
                jSONObject.put("result", stringExtra2);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    private Response a(Activity activity, String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            return new Response(204, "order cannot be empty");
        }
        Intent intent = new Intent(j);
        intent.setPackage("com.mipay.wallet");
        if (activity.getPackageManager().resolveActivity(intent, 0) == null) {
            return new Response(204, "mipay feature not available");
        }
        intent.putExtra("order", str);
        intent.putExtra("extra", bundle);
        activity.startActivityForResult(intent, f2961a);
        return null;
    }

    public HybridFeature.Mode b(Request request) {
        if (TextUtils.equals(request.a(), "pay")) {
            return HybridFeature.Mode.CALLBACK;
        }
        return null;
    }
}
