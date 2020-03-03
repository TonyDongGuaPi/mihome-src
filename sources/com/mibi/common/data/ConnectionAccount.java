package com.mibi.common.data;

import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.sys.a;
import com.mibi.common.data.ConnectionDefault;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.TreeMap;

public class ConnectionAccount extends ConnectionDefault {
    private static final String h = "ConnectionAccount";
    private String i;
    private String j;
    private String k;

    ConnectionAccount(String str, String str2, String str3, String str4) {
        super(str);
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            throw new IllegalArgumentException("account detail is empty");
        }
        this.i = str2;
        this.j = str3;
        this.k = str4;
    }

    public String f() throws PaymentException {
        super.f();
        this.e = Coder.b(this.e, this.j);
        if (this.e != null) {
            return this.e;
        }
        throw new ResultException("requestString error, decode failed");
    }

    /* access modifiers changed from: protected */
    public ConnectionDefault.Parameter a(ConnectionDefault.Parameter parameter) {
        return new EncryptParameter(parameter, this.j);
    }

    /* access modifiers changed from: protected */
    public boolean a(URL url) {
        if (!TextUtils.isEmpty(url.getQuery())) {
            return false;
        }
        return super.a(url);
    }

    /* access modifiers changed from: protected */
    public URL a(URL url, ConnectionDefault.Parameter parameter) {
        String str;
        String str2;
        String path = url.getPath();
        String query = url.getQuery();
        String url2 = url.toString();
        try {
            str = URLEncoder.encode(a(this.c ? "GET" : "POST", path, parameter.a(), this.j), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(h, "generate signature error :" + e);
            str = "";
        }
        if (TextUtils.isEmpty(query)) {
            str2 = url2 + "?signature=" + str;
        } else {
            str2 = url2 + "&signature=" + str;
        }
        try {
            return new URL(str2);
        } catch (MalformedURLException e2) {
            throw new IllegalStateException(e2);
        }
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection a(HttpURLConnection httpURLConnection) {
        StringBuilder sb = new StringBuilder();
        sb.append("userId=" + this.i);
        sb.append("; ");
        sb.append("serviceToken=" + this.k);
        httpURLConnection.setRequestProperty("Cookie", sb.toString());
        return httpURLConnection;
    }

    private String a(String str, String str2, TreeMap<String, Object> treeMap, String str3) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(str.toUpperCase());
        }
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
        }
        if (treeMap != null && !treeMap.isEmpty()) {
            for (String next : treeMap.keySet()) {
                String obj = treeMap.get(next).toString();
                if (!TextUtils.isEmpty(obj)) {
                    arrayList.add(String.format("%s=%s", new Object[]{next, obj}));
                }
            }
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(str3);
        }
        return a(TextUtils.join(a.b, arrayList));
    }

    private String a(String str) {
        byte[] d = Coder.d(str);
        if (d == null) {
            return null;
        }
        return Coder.b(d);
    }

    private class EncryptParameter extends ConnectionDefault.Parameter {
        EncryptParameter(ConnectionDefault.Parameter parameter, String str) {
            super();
            if (!parameter.b()) {
                TreeMap<String, Object> a2 = parameter.a();
                for (String next : a2.keySet()) {
                    String obj = a2.get(next).toString();
                    if (!TextUtils.isEmpty(obj)) {
                        String a3 = Coder.a(obj, str);
                        if (!TextUtils.isEmpty(a3)) {
                            a(next, (Object) a3);
                        }
                    }
                }
            }
        }
    }
}
