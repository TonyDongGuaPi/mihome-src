package com.xiaomi.payment.hybrid.feature;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.payment.CommonEntryActivity;
import com.xiaomi.payment.data.BundleUtils;
import com.xiaomi.payment.hybrid.MibiHybridUtils;
import java.util.Map;
import miuipub.hybrid.Callback;
import miuipub.hybrid.HybridFeature;
import miuipub.hybrid.LifecycleListener;
import miuipub.hybrid.NativeInterface;
import miuipub.hybrid.Request;
import miuipub.hybrid.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class MibiRechargeFeature implements HybridFeature {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12343a = "recharge";
    private static final int b = 1;

    public void a(Map<String, String> map) {
    }

    public Response a(Request request) {
        if (TextUtils.equals(request.a(), "recharge")) {
            return c(request);
        }
        a(request.c(), new Response(204, "no such action"));
        return null;
    }

    private Response c(final Request request) {
        new Bundle();
        try {
            JSONObject jSONObject = new JSONObject(request.b());
            long j = jSONObject.getLong("rechargeAmount");
            Bundle b2 = BundleUtils.b(jSONObject.optJSONObject("extra"));
            final NativeInterface e = request.e();
            if (e != null) {
                Intent intent = new Intent(request.e().a(), CommonEntryActivity.class);
                intent.setPackage("com.xiaomi.payment");
                intent.setData(Uri.parse("https://app.mibi.xiaomi.com?id=mibi.recharge"));
                b2.putLong("rechargeAmount", j);
                intent.putExtra("payment_fragment_arguments", b2);
                e.a().startActivityForResult(intent, 1);
                e.a(new LifecycleListener() {
                    public void a(int i, int i2, Intent intent) {
                        Response response;
                        if (i == 1) {
                            e.b(this);
                            if (i2 == -1) {
                                response = new Response(0, MibiHybridUtils.a(intent));
                            } else if (i2 == 0) {
                                response = new Response(100);
                            } else {
                                response = new Response(200);
                            }
                            request.c().a(response);
                        }
                    }
                });
                return null;
            }
            throw new IllegalArgumentException("NativeInterface is null");
        } catch (JSONException e2) {
            e2.printStackTrace();
            a(request.c(), new Response(204, "call param error"));
            return null;
        }
    }

    private void a(Callback callback, Response response) {
        if (callback != null) {
            callback.a(response);
        }
    }

    public HybridFeature.Mode b(Request request) {
        return HybridFeature.Mode.CALLBACK;
    }
}
