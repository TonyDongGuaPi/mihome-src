package com.mi.global.shop.request;

import android.text.TextUtils;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
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
    private MultipartEntity b;
    private final List<String> c;
    private final Map<String, String> d;

    public MultipartRequest(String str, Map<String, String> map, List<String> list, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(1, str, (String) null, listener, errorListener);
        this.c = list;
        this.d = map;
        c();
    }

    private void c() {
        this.b = new MultipartEntity();
        if (this.c != null) {
            for (String next : this.c) {
                if (!TextUtils.isEmpty(next)) {
                    File file = new File(next);
                    if (file.exists()) {
                        this.b.addPart("file[]", new FileBody(file));
                    }
                }
            }
        }
        if (this.d != null) {
            for (Map.Entry next2 : this.d.entrySet()) {
                try {
                    this.b.addPart((String) next2.getKey(), new StringBody((String) next2.getValue(), Charset.forName("UTF-8")));
                } catch (UnsupportedEncodingException unused) {
                    VolleyLog.e("UnsupportedEncodingException", new Object[0]);
                }
            }
        }
    }

    public String getBodyContentType() {
        return this.b.getContentType().getValue();
    }

    public byte[] getBody() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            this.b.writeTo(byteArrayOutputStream);
        } catch (IOException unused) {
            VolleyLog.e("IOException writing to ByteArrayOutputStream", new Object[0]);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
