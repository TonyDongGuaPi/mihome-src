package com.xiaomi.phonenum.obtain;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;
import com.mi.global.shop.model.Tags;
import com.xiaomi.phonenum.bean.HttpError;
import com.xiaomi.phonenum.http.HttpFactory;
import com.xiaomi.phonenum.http.Request;
import com.xiaomi.phonenum.http.Response;
import com.xiaomi.phonenum.utils.Logger;
import com.xiaomi.phonenum.utils.LoggerManager;
import com.xiaomi.phonenum.utils.MapUtil;
import com.xiaomi.smarthome.download.Downloads;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class DataProxyParser extends Parser {
    private static final String b = "DataProxy";
    private Logger c = LoggerManager.a();
    private HttpFactory d;

    public DataProxyParser(HttpFactory httpFactory) {
        this.d = httpFactory;
    }

    public Response a(int i, String str) throws IOException, JSONException {
        JSONObject jSONObject = new JSONObject(str);
        if ("data".equals(jSONObject.getString("result"))) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("dataRequest");
            return a(i, a(jSONObject2.getJSONObject("request")), jSONObject2.getString("followup"));
        } else if (this.f12570a != null) {
            return this.f12570a.a(i, str);
        } else {
            throw new JSONException("result not support" + jSONObject);
        }
    }

    private Response a(int i, @NonNull Request request, @NonNull String str) throws IOException {
        return a(str, a(i, request));
    }

    private Response a(int i, @NonNull Request request) {
        try {
            Response a2 = this.d.a(i).a(request);
            Logger logger = this.c;
            logger.c(b, "data response" + a2);
            return a2;
        } catch (IOException e) {
            this.c.a(b, "data request", e);
            return HttpError.CELLULAR_NETWORK_IO_EXCEPTION.result();
        }
    }

    @NonNull
    private Response a(@NonNull String str, @NonNull Response response) throws IOException {
        HashMap hashMap = new HashMap();
        hashMap.put("requestTime", "" + response.f);
        hashMap.put("code", "" + response.f12561a);
        hashMap.put("body", a(response.b));
        hashMap.put(Downloads.RequestHeaders.e, a(a(response.c)));
        return this.d.a().a(new Request.Builder().a(str).c((Map<String, String>) hashMap).a());
    }

    private String a(String str) throws UnsupportedEncodingException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return Base64.encodeToString(str.getBytes("utf-8"), 10);
    }

    private String a(Map<String, List<String>> map) {
        if (map == null) {
            return null;
        }
        try {
            JSONObject c2 = MapUtil.c(map);
            if (c2 != null) {
                return c2.toString().replace(Tags.MiHome.TEL_SEPARATOR4, "");
            }
        } catch (JSONException e) {
            this.c.a(b, "joinToJson", e);
        }
        return null;
    }

    private Request a(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString("url");
        boolean z = true;
        if (jSONObject.optInt("followRedirects") != 1) {
            z = false;
        }
        Map<String, String> a2 = MapUtil.a(jSONObject.optJSONObject(Downloads.RequestHeaders.e));
        return new Request.Builder().a(string).b(a2).c(MapUtil.a(jSONObject.optJSONObject("formBody"))).a(z).a();
    }
}
