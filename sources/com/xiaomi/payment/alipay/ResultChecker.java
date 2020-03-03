package com.xiaomi.payment.alipay;

import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.i;
import org.json.JSONObject;

public class ResultChecker {

    /* renamed from: a  reason: collision with root package name */
    private String f12149a;

    public ResultChecker(String str) {
        this.f12149a = str;
    }

    private String a(String str, String str2, String str3) {
        String str4;
        int indexOf = str.indexOf(str2) + str2.length();
        if (str3 != null) {
            try {
                str4 = str.substring(indexOf, str.indexOf(str3));
            } catch (Exception e) {
                e.printStackTrace();
                return str;
            }
        } else {
            str4 = str.substring(indexOf);
        }
        return str4;
    }

    public JSONObject a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            String[] split = str.split(str2);
            for (int i = 0; i < split.length; i++) {
                String[] split2 = split[i].split("=");
                jSONObject.put(split2[0], split[i].substring(split2[0].length() + 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String a() {
        return a(this.f12149a, "resultStatus=", ";memo=");
    }

    public String b() {
        String str = null;
        try {
            String string = a(this.f12149a, i.b).getString("result");
            String string2 = a(string.substring(1, string.length() - 1), a.b).getString("success");
            try {
                return string2.replace("\"", "");
            } catch (Exception e) {
                String str2 = string2;
                e = e;
                str = str2;
                e.printStackTrace();
                return str;
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return str;
        }
    }
}
