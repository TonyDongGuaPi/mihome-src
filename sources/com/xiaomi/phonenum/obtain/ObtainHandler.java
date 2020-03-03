package com.xiaomi.phonenum.obtain;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import com.xiaomi.account.openauth.AuthorizeActivityBase;
import com.xiaomi.jr.mipay.common.MipayConstants;
import com.xiaomi.phonenum.Constant;
import com.xiaomi.phonenum.bean.Error;
import com.xiaomi.phonenum.bean.PhoneNum;
import com.xiaomi.phonenum.bean.Sim;
import com.xiaomi.phonenum.http.HttpFactory;
import com.xiaomi.phonenum.http.Request;
import com.xiaomi.phonenum.http.Response;
import com.xiaomi.phonenum.phone.PhoneUtil;
import com.xiaomi.phonenum.utils.Logger;
import com.xiaomi.phonenum.utils.LoggerManager;
import com.xiaomi.phonenum.utils.MapUtil;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class ObtainHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12569a = "ObtainHandler";
    private PhoneUtil b;
    private HttpFactory c;
    private String d;
    private Context e;
    private Logger f = LoggerManager.a();
    private Parser g;

    public ObtainHandler(Context context, @NonNull String str, @NonNull PhoneUtil phoneUtil, @NonNull HttpFactory httpFactory) {
        this.e = context;
        this.d = str;
        this.b = phoneUtil;
        this.c = httpFactory;
    }

    public void a(Parser parser) {
        if (this.g == null) {
            this.g = parser;
        } else {
            this.g.a(parser);
        }
    }

    public PhoneNum a(int i, PhoneLevel phoneLevel) throws IOException, PhoneException {
        a();
        a(i);
        String substring = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 15);
        Logger logger = this.f;
        logger.a(f12569a, "**traceId**:" + substring);
        Response a2 = a(i, substring, phoneLevel.param);
        while (!a(a2)) {
            try {
                a2 = this.g.a(i, a2.b);
            } catch (JSONException e2) {
                this.f.a(f12569a, "parse response", e2);
                throw new PhoneException(Error.JSON, "", e2);
            }
        }
        return a(i, a2.b, substring, phoneLevel);
    }

    private boolean a(Response response) throws PhoneException, IOException {
        if (response == null || response.f12561a != 200 || response.b == null) {
            throw new IOException("getInNetTime ObtainStrategy response:" + response);
        }
        try {
            JSONObject jSONObject = new JSONObject(response.b);
            int i = jSONObject.getInt("code");
            if (i == 0) {
                return MipayConstants.ah.equals(jSONObject.getString("result"));
            }
            throw new PhoneException(Error.codeToError(i), jSONObject.optString("desc"));
        } catch (JSONException e2) {
            e2.printStackTrace();
            throw new PhoneException(Error.JSON, "", e2);
        }
    }

    private void a(int i) throws PhoneException {
        if (this.b.l(i)) {
            try {
                if (!this.b.a(i, 3000)) {
                    throw new PhoneException(Error.SIM_NOT_READY);
                }
            } catch (InterruptedException e2) {
                throw new PhoneException(Error.SIM_NOT_READY, "", e2);
            }
        } else {
            throw new PhoneException(Error.SIM_NOT_READY);
        }
    }

    private void a() throws PhoneException {
        if (!this.b.a("android.permission.READ_PHONE_STATE")) {
            throw new PhoneException(Error.NO_READ_PHONE_STATE_PERMISSION);
        }
    }

    private PhoneNum a(int i, String str, String str2, PhoneLevel phoneLevel) throws IOException, PhoneException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = jSONObject.getJSONObject(MipayConstants.ah);
            String string = jSONObject2.getString("number");
            String string2 = jSONObject2.getString("numberHash");
            String string3 = jSONObject2.getString("token");
            String string4 = jSONObject2.getString("iccid");
            String optString = jSONObject.optString("copywriter", (String) null);
            return new PhoneNum.Builder().b(i).i(str2).a(string).b(string2).e(string4).d(string3).g(optString).h(jSONObject.optString(AuthorizeActivityBase.KEY_OPERATORLINK, (String) null)).a(false).c(phoneLevel.value).a();
        } catch (JSONException e2) {
            e2.printStackTrace();
            throw new PhoneException(Error.JSON, "", e2);
        }
    }

    private Response a(int i, String str, String str2) throws IOException {
        HashMap hashMap = new HashMap();
        Sim k = this.b.k(i);
        if (k != null) {
            a((Map<String, String>) hashMap, "iccid", k.f12555a);
            a((Map<String, String>) hashMap, "imsi", k.b);
            a((Map<String, String>) hashMap, "simMccmnc", k.c);
            a((Map<String, String>) hashMap, "line1Number", k.d);
        }
        a((Map<String, String>) hashMap, "networkMccmnc", this.b.d(i));
        a((Map<String, String>) hashMap, "appId", this.d);
        a((Map<String, String>) hashMap, "imei", this.b.c());
        a((Map<String, String>) hashMap, MipayConstants.aa, "" + this.b.e(i));
        a((Map<String, String>) hashMap, "traceId", str);
        a((Map<String, String>) hashMap, "versionCode", "8");
        a((Map<String, String>) hashMap, "phoneLevel", str2);
        a((Map<String, String>) hashMap, "pip", b(i));
        a((Map<String, String>) hashMap, "packageName", this.e.getPackageName());
        String a2 = MapUtil.a((Map<String, String>) hashMap);
        Logger logger = this.f;
        logger.c(f12569a, "params:" + a2);
        return this.c.a().a(new Request.Builder().a(Constant.f).a((Map<String, String>) hashMap).c(a(this.e)).a());
    }

    private String b(int i) {
        if (!this.b.b(i)) {
            Logger logger = this.f;
            logger.a(f12569a, "data disabled, sub: " + i);
            return "";
        }
        Logger logger2 = this.f;
        logger2.a(f12569a, "data enabled, sub: " + i);
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (!nextElement.getName().toLowerCase().contains("wlan")) {
                    Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement2 = inetAddresses.nextElement();
                        if (!nextElement2.isLoopbackAddress() && (nextElement2 instanceof Inet4Address)) {
                            return nextElement2.getHostAddress();
                        }
                    }
                    continue;
                }
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    private void a(Map<String, String> map, String str, String str2) {
        if (str != null && str2 != null) {
            map.put(str, str2);
        }
    }

    private String a(Context context) {
        return "Android/" + Build.VERSION.RELEASE + " SDK_INT/" + Build.VERSION.SDK_INT + " BRAND/" + Build.BRAND + " PRODUCT/" + Build.PRODUCT + " MODEL/" + Build.MODEL + " INCREMENTAL/" + Build.VERSION.INCREMENTAL + " APP/" + context.getPackageName();
    }
}
