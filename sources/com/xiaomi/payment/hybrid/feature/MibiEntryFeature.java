package com.xiaomi.payment.hybrid.feature;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.mibi.common.utils.UrlUtils;
import com.xiaomi.payment.CommonEntryActivity;
import com.xiaomi.payment.entry.EntryManager;
import com.xiaomi.payment.hybrid.MibiHybridUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import miuipub.hybrid.Callback;
import miuipub.hybrid.HybridFeature;
import miuipub.hybrid.LifecycleListener;
import miuipub.hybrid.NativeInterface;
import miuipub.hybrid.Request;
import miuipub.hybrid.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MibiEntryFeature implements HybridFeature {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12339a = "call";
    private static final String b = "getSupportEntry";
    private static final String c = "entryList";
    private static final int d = 1;

    public void a(Map<String, String> map) {
    }

    public Response a(Request request) {
        if (TextUtils.equals(request.a(), "call")) {
            return c(request);
        }
        if (TextUtils.equals(request.a(), b)) {
            return d(request);
        }
        a(request.c(), new Response(204, "no such action"));
        return null;
    }

    private Response c(final Request request) {
        try {
            JSONObject jSONObject = new JSONObject(request.b());
            String string = jSONObject.getString("entry");
            final NativeInterface e = request.e();
            if (e != null) {
                Intent intent = new Intent(request.e().a(), CommonEntryActivity.class);
                intent.setPackage("com.xiaomi.payment");
                intent.setData(UrlUtils.a(UrlUtils.a(Uri.parse(MibiHybridUtils.f12336a), "id", string), jSONObject));
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

    private Response d(Request request) {
        ArrayList<String> a2 = EntryManager.a().a((Context) request.e().a());
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<String> it = a2.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
            jSONObject.put(c, jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Response(0, jSONObject);
    }

    private void a(Callback callback, Response response) {
        if (callback != null) {
            callback.a(response);
        }
    }

    public HybridFeature.Mode b(Request request) {
        if (TextUtils.equals(request.a(), "call")) {
            return HybridFeature.Mode.CALLBACK;
        }
        if (TextUtils.equals(request.a(), b)) {
            return HybridFeature.Mode.SYNC;
        }
        return null;
    }
}
