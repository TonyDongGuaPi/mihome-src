package com.xiaomi.phonenum.innetdate;

import android.content.Context;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.xiaomi.phonenum.bean.Error;
import com.xiaomi.phonenum.http.HttpFactory;
import com.xiaomi.phonenum.http.Request;
import com.xiaomi.phonenum.http.Response;
import com.xiaomi.phonenum.http.UrlConnHttpFactory;
import com.xiaomi.phonenum.phone.PhoneInfoManager;
import com.xiaomi.phonenum.phone.PhoneUtil;
import com.xiaomi.phonenum.utils.Logger;
import com.xiaomi.phonenum.utils.LoggerManager;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.cybergarage.http.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

public class InNetDateHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12566a = "InNetTimeHelper";
    private static final long b = 0;
    private Logger c = LoggerManager.a();
    private HttpFactory d;
    private PhoneUtil e;

    public InNetDateHelper(Context context) {
        this.d = new UrlConnHttpFactory(context);
        this.e = PhoneInfoManager.a(context);
    }

    public InNetDateResult a(int i) throws IOException {
        int a2 = this.e.a(i);
        if (this.e.k(a2) == null) {
            this.c.a(f12566a, "step 0 sim not ready");
            return new InNetDateResult(Error.SIM_NOT_READY);
        }
        c(a2);
        this.c.a(f12566a, "step 1 login");
        String b2 = b(a2);
        Logger logger = this.c;
        logger.a(f12566a, "step 2 get phone " + b2);
        if (TextUtils.isEmpty(b2)) {
            return new InNetDateResult(Error.UNKNOW);
        }
        long a3 = a(a2, b2);
        Logger logger2 = this.c;
        logger2.a(f12566a, "step 3 get inNetDate:" + a3);
        if (a3 == 0) {
            return new InNetDateResult(Error.UNKNOW);
        }
        return new InNetDateResult(a3, b2);
    }

    private long a(int i, String str) throws IOException {
        Request.Builder builder = new Request.Builder();
        Response a2 = this.d.a(i).a(builder.a("http://touch.10086.cn/i/v1/cust/info/" + str + "?channel=02&time=" + c()).a());
        Logger logger = this.c;
        StringBuilder sb = new StringBuilder();
        sb.append("getInNetDate response:");
        sb.append(a2);
        logger.c(f12566a, sb.toString());
        try {
            return a(new JSONObject(a2.b).getJSONObject("data").getString("inNetDate"));
        } catch (JSONException e2) {
            this.c.a(f12566a, "JSONException:", e2);
            return 0;
        }
    }

    private String b(int i) throws IOException {
        Request.Builder builder = new Request.Builder();
        Response a2 = this.d.a(i).a(builder.a("http://touch.10086.cn/i/v1/auth/loginfo?channel=02&time=" + c()).b(b()).a());
        Logger logger = this.c;
        logger.c(f12566a, "getPhoneNum response:" + a2);
        try {
            return new JSONObject(a2.b).getJSONObject("data").getString("loginValue");
        } catch (JSONException e2) {
            this.c.a(f12566a, "JSONException:", e2);
            return null;
        }
    }

    private void c(int i) throws IOException {
        Response a2 = this.d.a(i).a(new Request.Builder().a("http://touch.10086.cn/i/v1/auth/getFreeAuthArtifact").b("backUrl=http%3A%2F%2Ftouch.10086.cn%2Fi%2Fmobile%2Fcustinfoqry.html").b(a()).a());
        if (a2.e != null) {
            Logger logger = this.c;
            logger.c(f12566a, "login location:" + a2.e);
            a2 = this.d.a(i).a(new Request.Builder().a(a2.e).a());
        }
        Logger logger2 = this.c;
        logger2.c(f12566a, "login response:" + a2);
    }

    private Map<String, String> a() {
        HashMap hashMap = new HashMap();
        hashMap.put(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        hashMap.put("Accept-Encoding", "gzip, deflate, sdch");
        hashMap.put(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.8");
        hashMap.put("Connection", "keep-alive");
        hashMap.put("Upgrade-Insecure-Requests", "1");
        hashMap.put(HttpHeaders.REFERER, "http://touch.10086.cn/i/mobile/custinfoqry.html");
        return hashMap;
    }

    private Map<String, String> b() {
        HashMap hashMap = new HashMap();
        hashMap.put("connection", "Keep-Alive");
        hashMap.put("Cache-Control", "no-store, must-revalidate");
        hashMap.put("pragma", HTTP.NO_CACHE);
        hashMap.put(HttpHeaders.ACCEPT, "application/json, text/javascript, */*; q=0.01");
        hashMap.put(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
        hashMap.put(HttpHeaders.IF_MODIFIED_SINCE, "0e");
        hashMap.put("expires", "0");
        hashMap.put(HttpHeaders.REFERER, "http://touch.10086.cn/i/mobile/custinfoqry.html");
        hashMap.put("Accept-Encoding", "gzip, deflate, sdch");
        hashMap.put(HttpHeaders.ACCEPT_LANGUAGE, "Keep-Alive");
        hashMap.put("connection", "zh-CN,zh;q=0.8");
        return hashMap;
    }

    private String c() {
        return new SimpleDateFormat("yyyyMdHHmmssS").format(new Date());
    }

    private long a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            Date parse = new SimpleDateFormat("yyyyMMddHHmmss").parse(str);
            if (parse != null) {
                return parse.getTime();
            }
        } catch (ParseException e2) {
            this.c.a(f12566a, "ParseException", e2);
        }
        return 0;
    }
}
