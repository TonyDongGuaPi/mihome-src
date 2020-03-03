package com.mi.volley;

import android.text.TextUtils;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.mi.util.MD5Util;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.json.JSONObject;

public class MultipartRequest extends MiJsonObjectRequest {

    /* renamed from: a  reason: collision with root package name */
    private MultipartEntity f7430a;
    private final List<String> b;
    private final Map<String, String> c;

    public MultipartRequest(String str, Map<String, String> map, List<String> list, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(1, str, (String) null, listener, errorListener);
        this.b = list;
        this.c = map;
        a();
    }

    private void a() {
        this.f7430a = new MultipartEntity();
        if (this.b != null) {
            for (String next : this.b) {
                if (!TextUtils.isEmpty(next)) {
                    File file = new File(next);
                    if (file.exists()) {
                        this.f7430a.addPart(a(next), new FileBody(file));
                    }
                }
            }
        }
        if (this.c != null) {
            for (Map.Entry next2 : this.c.entrySet()) {
                try {
                    this.f7430a.addPart((String) next2.getKey(), new StringBody((String) next2.getValue(), Charset.forName("UTF-8")));
                } catch (UnsupportedEncodingException unused) {
                    VolleyLog.e("UnsupportedEncodingException", new Object[0]);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public String a(String str) {
        return MD5Util.a(str);
    }

    public String getBodyContentType() {
        return this.f7430a.getContentType().getValue();
    }

    public byte[] getBody() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            this.f7430a.writeTo(byteArrayOutputStream);
        } catch (IOException unused) {
            VolleyLog.e("IOException writing to ByteArrayOutputStream", new Object[0]);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
