package com.mipay.sdk;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.mipay.sdk.app.Constants;
import com.mipay.sdk.app.MipayWebActivity;
import java.util.Iterator;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

class b implements IMipay {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8167a = "https";
    private static final String b = "m.pay.xiaomi.com";
    private static final String c = "http";
    private static final String d = "staging.m.pay.xiaomi.com";
    private static final String e = "http";
    private static final String f = "test.m.pay.xiaomi.com";
    private static final String g = "pay";

    b() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042 A[LOOP:0: B:13:0x003c->B:15:0x0042, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.lang.String r5) {
        /*
            r4 = this;
            java.util.TreeMap r5 = r4.b(r5)
            boolean r0 = r5.isEmpty()
            if (r0 == 0) goto L_0x000c
            r5 = 0
            return r5
        L_0x000c:
            android.net.Uri$Builder r0 = new android.net.Uri$Builder
            r0.<init>()
            boolean r1 = com.mipay.sdk.app.Constants.STAGING
            if (r1 == 0) goto L_0x0020
            java.lang.String r1 = "staging.m.pay.xiaomi.com"
        L_0x0017:
            r0.authority(r1)
            java.lang.String r1 = "http"
        L_0x001c:
            r0.scheme(r1)
            goto L_0x002f
        L_0x0020:
            boolean r1 = com.mipay.sdk.app.Constants.TEST
            if (r1 == 0) goto L_0x0027
            java.lang.String r1 = "test.m.pay.xiaomi.com"
            goto L_0x0017
        L_0x0027:
            java.lang.String r1 = "m.pay.xiaomi.com"
            r0.authority(r1)
            java.lang.String r1 = "https"
            goto L_0x001c
        L_0x002f:
            java.lang.String r1 = "pay"
            r0.appendPath(r1)
            java.util.Set r1 = r5.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x003c:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0052
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r3 = r5.get(r2)
            java.lang.String r3 = (java.lang.String) r3
            r0.appendQueryParameter(r2, r3)
            goto L_0x003c
        L_0x0052:
            java.lang.String r5 = r0.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mipay.sdk.b.a(java.lang.String):java.lang.String");
    }

    private TreeMap<String, String> b(String str) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                treeMap.put(next, jSONObject.getString(next));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return treeMap;
    }

    public void pay(Activity activity, String str, Bundle bundle) {
        if (activity == null) {
            throw new IllegalArgumentException("activity should not be null");
        } else if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent(activity, MipayWebActivity.class);
            intent.putExtra(Constants.KEY_URL, a(str));
            intent.setPackage(activity.getPackageName());
            activity.startActivityForResult(intent, 424);
        } else {
            throw new IllegalArgumentException("order cannot be empty");
        }
    }

    public void pay(Fragment fragment, String str, Bundle bundle) {
        if (fragment == null) {
            throw new IllegalArgumentException("fragment should not be null");
        } else if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent(fragment.getActivity(), MipayWebActivity.class);
            intent.putExtra(Constants.KEY_URL, a(str));
            intent.setPackage(fragment.getActivity().getPackageName());
            fragment.startActivityForResult(intent, 424);
        } else {
            throw new IllegalArgumentException("order cannot be empty");
        }
    }
}
