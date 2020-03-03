package com.mibi.common.data;

import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.sys.a;
import com.mibi.common.data.ConnectionDefault;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.TreeMap;

public class ConnectionSaltSignature extends ConnectionDefault {
    private static final String h = "ConnectionSaltSignature";
    private static final String i = "8007236f-a2d6-4847-ac83-c49395ad6d65";

    ConnectionSaltSignature(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public ConnectionDefault.Parameter a(ConnectionDefault.Parameter parameter) {
        return new NonEmptyParameter(parameter);
    }

    private class NonEmptyParameter extends ConnectionDefault.Parameter {
        NonEmptyParameter(ConnectionDefault.Parameter parameter) {
            super();
            if (!parameter.b()) {
                TreeMap<String, Object> a2 = parameter.a();
                for (String next : a2.keySet()) {
                    String obj = a2.get(next).toString();
                    if (!TextUtils.isEmpty(obj)) {
                        a(next, (Object) obj);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public URL a(URL url, ConnectionDefault.Parameter parameter) {
        String str;
        String str2;
        String query = url.getQuery();
        String url2 = url.toString();
        try {
            str = URLEncoder.encode(a(parameter.a(), i), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(h, "generate signature error :" + e);
            str = "";
        }
        if (TextUtils.isEmpty(query)) {
            str2 = url2 + "?s=" + str;
        } else {
            str2 = url2 + "&s=" + str;
        }
        try {
            return new URL(str2);
        } catch (MalformedURLException e2) {
            throw new IllegalStateException(e2);
        }
    }

    private String a(TreeMap<String, Object> treeMap, String str) {
        ArrayList arrayList = new ArrayList();
        if (treeMap != null && !treeMap.isEmpty()) {
            for (String next : treeMap.keySet()) {
                String obj = treeMap.get(next).toString();
                if (!TextUtils.isEmpty(obj)) {
                    arrayList.add(String.format("%s=%s", new Object[]{next, obj}));
                }
            }
        }
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(str);
        }
        return Coder.b(Coder.e(TextUtils.join(a.b, arrayList))).toUpperCase();
    }
}
