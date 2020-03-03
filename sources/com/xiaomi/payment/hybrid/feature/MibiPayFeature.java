package com.xiaomi.payment.hybrid.feature;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.payment.data.BundleUtils;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.hybrid.MibiHybridUtils;
import java.security.InvalidParameterException;
import java.util.Map;
import miuipub.hybrid.Callback;
import miuipub.hybrid.HybridFeature;
import miuipub.hybrid.LifecycleListener;
import miuipub.hybrid.NativeInterface;
import miuipub.hybrid.Request;
import miuipub.hybrid.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class MibiPayFeature implements HybridFeature {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12341a = "mibiPay";

    public void a(Map<String, String> map) {
    }

    public Response a(Request request) {
        Bundle bundle;
        String str;
        if (TextUtils.equals(request.a(), f12341a)) {
            String b = request.b();
            String str2 = "";
            Bundle bundle2 = new Bundle();
            try {
                JSONObject jSONObject = new JSONObject(b);
                str = jSONObject.getString(MibiConstants.hk);
                try {
                    bundle = BundleUtils.b(jSONObject.optJSONObject("extra"));
                } catch (JSONException e) {
                    JSONException jSONException = e;
                    str2 = str;
                    e = jSONException;
                    e.printStackTrace();
                    str = str2;
                    bundle = bundle2;
                    c(request);
                    a(request, str, bundle);
                    return null;
                }
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                str = str2;
                bundle = bundle2;
                c(request);
                a(request, str, bundle);
                return null;
            }
            c(request);
            a(request, str, bundle);
            return null;
        }
        a(request.c(), new Response(204, "no such action"));
        return null;
    }

    private void a(Request request, String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            a(request.c(), new Response(204, "order is invalide"));
        } else if (request.e().a() != null) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setPackage("com.xiaomi.payment");
            intent.setData(Uri.parse("https://app.mibi.xiaomi.com?id=mibi.pay"));
            Bundle bundle2 = new Bundle();
            if (bundle == null) {
                bundle = bundle2;
            }
            bundle.putString("order", str);
            intent.putExtra("payment_fragment_arguments", bundle);
            request.e().a().startActivityForResult(intent, 424);
        } else {
            throw new InvalidParameterException("activity cannot be null");
        }
    }

    private void c(final Request request) {
        final NativeInterface e = request.e();
        if (e != null) {
            e.a(new LifecycleListener() {
                public void a(int i, int i2, Intent intent) {
                    Response response;
                    if (i == 424) {
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
            return;
        }
        throw new IllegalArgumentException("NativeInterface is null");
    }

    public HybridFeature.Mode b(Request request) {
        return HybridFeature.Mode.CALLBACK;
    }

    private void a(Callback callback, Response response) {
        if (callback != null) {
            callback.a(response);
        }
    }
}
