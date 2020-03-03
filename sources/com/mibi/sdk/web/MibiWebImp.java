package com.mibi.sdk.web;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.mi.global.shop.model.Tags;
import com.mibi.sdk.IMibi;
import com.mibi.sdk.IMibiAccountProvider;
import com.mibi.sdk.account.AccountProviderHolder;
import com.mibi.sdk.common.Utils;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

public class MibiWebImp implements IMibi {

    /* renamed from: a  reason: collision with root package name */
    private final String f7611a = "MibiWebImp";
    private final String b = "http";
    private final String c = "m.mibi.mi.com";
    private final String d = "m.staging.mibi.n.xiaomi.com";
    private final String e = "m.hk.mibi.mi.com";
    private final String f = "m.staging.hk.mibi.xiaomi.com";
    private final String g = Tags.Lottery.NATIVE_TYPE_RECHARGE;
    private final String h = "pay";
    private final String i = "webUrl";
    private final String j = "region";
    private String k;

    public void a(Activity activity, String str, IMibiAccountProvider iMibiAccountProvider, Bundle bundle) {
    }

    private void a(String str) {
        if (Utils.f7602a) {
            if (TextUtils.equals(str, ServerCompact.b)) {
                this.k = "m.staging.hk.mibi.xiaomi.com";
            } else {
                this.k = "m.staging.mibi.n.xiaomi.com";
            }
        } else if (TextUtils.equals(str, ServerCompact.b)) {
            this.k = "m.hk.mibi.mi.com";
        } else {
            this.k = "m.mibi.mi.com";
        }
    }

    public void a(Activity activity, IMibiAccountProvider iMibiAccountProvider) {
        if (activity != null) {
            a(activity, b(), iMibiAccountProvider, 425);
            return;
        }
        throw new InvalidParameterException("activity cannot be null");
    }

    public void b(Activity activity, IMibiAccountProvider iMibiAccountProvider) {
        a(activity, 0, iMibiAccountProvider, (Bundle) null);
    }

    public void a(Activity activity, long j2, IMibiAccountProvider iMibiAccountProvider, Bundle bundle) {
        if (activity != null) {
            String str = "";
            if (bundle != null) {
                str = bundle.getString("region");
            }
            a(str);
            a(activity, c(), iMibiAccountProvider, 425);
            return;
        }
        throw new InvalidParameterException("activity cannot be null");
    }

    public void b(Activity activity, String str, IMibiAccountProvider iMibiAccountProvider, Bundle bundle) {
        if (activity != null) {
            String str2 = "";
            TreeMap<String, String> c2 = c(str);
            if (c2 != null) {
                str2 = c2.get("region");
            }
            a(str2);
            a(activity, b(str), iMibiAccountProvider, 424);
            return;
        }
        throw new InvalidParameterException("activity cannot be null");
    }

    private void a(Activity activity, String str, IMibiAccountProvider iMibiAccountProvider, int i2) {
        AccountProviderHolder.a(iMibiAccountProvider);
        Intent intent = new Intent(activity, MibiWebActivity.class);
        intent.putExtra("webUrl", str);
        intent.setPackage(activity.getPackageName());
        activity.startActivityForResult(intent, i2);
    }

    private String b() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http");
        builder.authority(this.k);
        return builder.toString();
    }

    private String c() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").authority(this.k).appendPath(Tags.Lottery.NATIVE_TYPE_RECHARGE);
        return builder.toString();
    }

    private String b(String str) {
        TreeMap<String, String> c2 = c(str);
        if (c2.isEmpty()) {
            return null;
        }
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").authority(this.k).appendPath("pay");
        for (String next : c2.keySet()) {
            builder.appendQueryParameter(next, c2.get(next));
        }
        return builder.toString();
    }

    private TreeMap<String, String> c(String str) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                treeMap.put(next, jSONObject.getString(next));
            }
            return treeMap;
        } catch (JSONException e2) {
            Log.w("MibiWebImp", "Order param is not right: " + e2);
        } catch (Throwable unused) {
        }
        return treeMap;
    }

    public void a() {
        AccountProviderHolder.a();
    }
}
